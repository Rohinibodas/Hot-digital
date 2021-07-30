
package TestExecute.Hydroflask.Smoke;


import org.testng.Assert; 
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_ST_HF_052_checkout_with_VISACard_as_Registeruser_with_MultiProduct_tax_and_Freight_Shipping_with_same_billing_Shippingaddresses {
	String datafile = "Hydroflask//HydroTestData.xlsx";
	HydroHelper Hydro = new HydroHelper(datafile);

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)

	public void RegisteredUser_Checkout_VISA_Multiproduct_Tax_FreightShipping_samebillingShippingaddress()
			throws Exception {

		try {
			Hydro.loginHydroflaskAccount("AccountDetails");
			Hydro.orderSubmit("Bottles");
			Hydro.searchproduct_addtocart("32 oz Tumbler Lid");
			Hydro.checkOut();
			Hydro.addfrieightDeliveryAddress_registerUser("Address");
			Hydro.verifyingTax_field();
			Hydro.updatePaymentAndSubmitOrder("PaymentDetails");
			
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
