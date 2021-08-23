package TestExecute.Hydroflask.Smoke2;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_HF_041_Paypal_checkout_GuestUser_bundle_simple_configurable_products {
	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper Hydro=new HydroHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	
	
  public void Paypal_checkout_as_guest_user_with_bundle_simple_and_configurable_products() {
		try{
		 Hydro.orderSubmit("Bottles");
		 Hydro.searchproduct_addtocart("32 oz Tumbler Lid");
	     Hydro.checkOut();
	     Hydro.addDeliveryAddress("Address");
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
	 // System.setProperty("configFile", "Hydroflask\\config.properties");
	  Login.signIn();
	  Hydro.acceptPrivecy();
	  Hydro.ClosADD();		  
  }
}
