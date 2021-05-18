package TestExecute.Hydroflask.Smoke;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;

import TestLib.Login;

public class TEST_ST_HF_031_Checkout_with_credit_card_as_Guest_user_with_bundle_product_and_Myhydro_product {
	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper Hydro=new HydroHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  
  public void checkout_with_credit_card_as_Guest_user_with_bundle_product_and_Myhydro_product() {
	  try{
        
		
	    Hydro.serachproduct_addtocart("Adventure Bundle");
	    Hydro.Customize_Bottle_Standed();
	    Hydro.checkOut();
		Hydro.addDeliveryAddress("Address");
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
      // Common.closeAll();

	}
	
	@BeforeTest
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "Hydroflask\\config.properties");
		  Login.signIn();
		  Hydro.acceptPrivecy();
		//  Hydro.ClosADD();		  
	  }
}