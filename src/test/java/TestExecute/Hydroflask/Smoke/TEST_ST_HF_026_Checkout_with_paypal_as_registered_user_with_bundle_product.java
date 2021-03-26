package TestExecute.Hydroflask.Smoke;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_HF_026_Checkout_with_paypal_as_registered_user_with_bundle_product {
	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper Hydro=new HydroHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void Checkout_with_paypal_as_registered_user_with_bundle_product() {
		try{
			Hydro.loginHydroflaskAccount("AccountDetails");
			Hydro.serachproduct_addtocart("Adventure Bundle");
			Hydro.checkOut();
			Hydro.addDeliveryAddress_registerUser("Address");
			Hydro.payPal_Payment("PaypalDetails");
			
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
