
package TestExecute.Hydroflask.Smoke;

import org.testng.Assert; 
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_ST_HF_079_checkout_with_Paypal_as_Registeruser_withMultiproduct_and_Discount_Promocode_tax_and_StandardShipping_with_differentbilling_Shipping_addresses {
	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper Hydro=new HydroHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	
	public void RegisterUser_Checkout_Multi_product_tax_Standard_shipping() throws Exception {

		try {
			Hydro.loginHydroflaskAccount("AccountDetails");
			Hydro.searchproduct_addtocart("32 oz Tumbler Lid");
			Hydro.orderSubmit("Bottles");
			Hydro.checkOut();
			Hydro.addDeliveryAddress_registerUser("Address");
			Hydro.verifyingTax_field();
			Hydro.edit_BillingAddress_RegisterUser("Billing_Address");
			Hydro.promationCode("Promationcode");
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
