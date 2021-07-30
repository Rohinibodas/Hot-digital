package TestExecute.Hydroflask.Smoke;

import org.testng.Assert; 
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_ST_HF_053_checkout_with_Mastercard_as_Guestuser_with_Multiproduct_tax_and_FreightShipping_with_different_billing_Shippingaddresses {
	String datafile = "Hydroflask//HydroTestData.xlsx";
	HydroHelper Hydro = new HydroHelper(datafile);

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Guestuser_with_MASTERCc_Multiproduct_tax_FreightShipping_differentbilling_Shippingaddress() {
		try {

			Hydro.orderSubmit("Bottles");
			Hydro.searchproduct_addtocart("32 oz Tumbler Lid");
			Hydro.checkOut();
			Hydro.addfrieightDeliveryAddress_guestuser("Address");
			Hydro.check_box();
			Hydro.Edit_BillingAddress("GustUserOrderdetiles");
			Hydro.verifyingTax_field();
			Hydro.addPaymentDetails("Ccmastercard");

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
