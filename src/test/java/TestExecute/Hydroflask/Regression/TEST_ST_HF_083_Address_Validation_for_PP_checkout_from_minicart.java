package TestExecute.Hydroflask.Regression;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import TestComponent.Hydroflask.HydroHelper;

import TestLib.Common;
import TestLib.Login;

public class TEST_ST_HF_083_Address_Validation_for_PP_checkout_from_minicart {
	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper Hydro=new HydroHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)

  public void checkoutWithPayPal() {
		try {
			//Hydro.loginHydroflaskAccount("AccountDetails");
			Hydro.verifytheHomepage();
			//Hydro.orderSubmit("Bottles");
			Hydro.serachproduct_addtocart("Wide Mouth Accessory Bundle");
			
			Hydro.CheckOutPaypalminicart("PaypalDetails");
	  
			Hydro.CheckOutPaypaladdress("InvalidAddress");
			
			//Hydro.Paypal_checkout("PaypalDetails");
			
		}
		catch (Exception e) {
			
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
