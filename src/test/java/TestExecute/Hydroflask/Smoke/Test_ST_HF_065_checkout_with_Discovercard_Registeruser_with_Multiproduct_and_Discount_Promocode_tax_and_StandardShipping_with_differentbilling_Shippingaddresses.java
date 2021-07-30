
package TestExecute.Hydroflask.Smoke;

import org.testng.Assert; 
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_ST_HF_065_checkout_with_Discovercard_Registeruser_with_Multiproduct_and_Discount_Promocode_tax_and_StandardShipping_with_differentbilling_Shippingaddresses {
	String datafile = "Hydroflask//HydroTestData.xlsx";
	HydroHelper Hydro = new HydroHelper(datafile);

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)

	public void RegisteredUser_Checkout_Discovercard_Multiproduct_Discount_promocode_Tax_Shipping_differentbillingShippingaddress()
			throws Exception {

		try {
			Hydro.loginHydroflaskAccount("AccountDetails");
			Hydro.orderSubmit("Bottles");
			Hydro.searchproduct_addtocart("32 oz Tumbler Lid");
			Hydro.checkOut();
			Hydro.addDeliveryAddress_registerUser("Address");
			//Hydro.edit_BillingAddress_RegisterUser("Registerbillingaddress");
			Hydro.edit_BillingAddress_RegisterUser("Billing_Address");
			Hydro.verifyingTax_field();
			Hydro.updatePaymentAndSubmitOrder("Discovercard");
			
		} catch (Exception e) {
			e.printStackTrace();

			Assert.fail(e.getMessage(), e);
		}
	}

	@AfterTest
	public void clearBrowser() {
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
