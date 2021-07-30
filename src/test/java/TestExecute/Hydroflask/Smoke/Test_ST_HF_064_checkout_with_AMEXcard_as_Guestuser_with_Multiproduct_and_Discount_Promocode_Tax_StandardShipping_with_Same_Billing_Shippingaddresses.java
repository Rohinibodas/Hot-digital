
package TestExecute.Hydroflask.Smoke;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_ST_HF_064_checkout_with_AMEXcard_as_Guestuser_with_Multiproduct_and_Discount_Promocode_Tax_StandardShipping_with_Same_Billing_Shippingaddresses {
	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper Hydro=new HydroHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	
	public void GuestUser_Checkout_Amex_Cc_MultiProduct_Discount_Samebilling_ShippingAddress() throws Exception {

		try {
			
			Hydro.searchproduct_addtocart("32 oz Tumbler Lid");
			Hydro.orderSubmit("Bottles");
			Hydro.checkOut();
			Hydro.addDeliveryAddress("Address");
			Hydro.verifyingTax_field();
			Hydro.promationCode("Promationcode");
			Hydro.updatePaymentAndSubmitOrder("Amexcard");
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
