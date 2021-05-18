package TestExecute.Hydroflask.Smoke;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_HF_046_Registered_user_with_paypal_checkout_from_minicart_with_bundlesimple_and_configurable_product {
	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper Hydro=new HydroHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	
  public void registered_user_with_paypal_checkout_from_minicart_with_bundlesimple_and_configurable_product() {
		try{
			 Hydro.loginHydroflaskAccount("AccountDetails");
			 Hydro.orderSubmit("Bottles");
			 Hydro.searchproduct_addtocart("32 oz Tumbler Lid");
			 Hydro.serachproduct_addtocart("Adventure Bundle");
			 Hydro.CheckOutPaypal("PaypalDetails");

		 }
		catch (Exception e) {
			e.printStackTrace();
			
			Assert.fail(e.getMessage(), e);
		} 
	}


	@AfterTest
	public void clearBrowser()
	{
	   Common.closeAll();

	}

	@BeforeTest
	  public void startTest() throws Exception {
		  System.setProperty("configFile", "Hydroflask\\config.properties");
		  Login.signIn();
		  Hydro.acceptPrivecy();
		  Hydro.ClosADD();		  
	  }
	}


