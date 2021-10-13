package TestExecute.Hydroflask.Regression;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_HF_047_GuestUser_checkout_PP_minicart_Bundle_configurable_simple {
	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper Hydro=new HydroHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void guest_user_checkout_with_paypal_from_mini_cart_with_Bundle_configurable_and_simple_product() {
		try{
			 Hydro.orderSubmit("Bottles");
			 Hydro.searchproduct_addtocart("32 oz Tumbler Lid");
			 Hydro.serachproduct_addtocart("Wide Mouth Accessory Bundle");
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
//		  System.setProperty("configFile", "Hydroflask\\config.properties");
		  Login.signIn();
		  Hydro.acceptPrivecy();
		  Hydro.ClosADD();		  
	  }
	}
