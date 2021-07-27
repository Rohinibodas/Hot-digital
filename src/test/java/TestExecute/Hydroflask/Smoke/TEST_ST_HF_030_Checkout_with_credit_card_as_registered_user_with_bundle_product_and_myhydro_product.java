package TestExecute.Hydroflask.Smoke;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_HF_030_Checkout_with_credit_card_as_registered_user_with_bundle_product_and_myhydro_product {
	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper Hydro=new HydroHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  
  public void Checkout_with_credit_card_as_registered_user_with_bundle_product_and_myhydro_product() {
	  try{
        Hydro.loginHydroflaskAccount("AccountDetails");
        Hydro.Customize_Bottle_Standed("24 oz");
	    Hydro.serachproduct_addtocart("Adventure Bundle");
		Hydro.checkOut();
		Hydro.addDeliveryAddress_registerUser("Address");
		Hydro.payPal_Payment("PaypalDetails");
		Hydro.updatePaymentAndSubmitOrder("Ccmastercard");
  
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
