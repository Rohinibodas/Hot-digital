package TestExecute.Hydroflask.Smoke;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_ST_HF_066_checkout_with_VISA_card_as_Register_user_with_Employee_Discount_tax_and_Standard_Shipping_with_same_billingShippingaddresses {
	String datafile = "Hydroflask//HydroTestData.xlsx";
	HydroHelper Hydro = new HydroHelper(datafile);

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)

	public void RegisteredUser_Checkout_VISA_Multiproduct_Tax_FreightShipping_samebillingShippingaddress()
			throws Exception {

		try {
			Hydro.loginHydroflaskAccount("Employlogins");
			Hydro.orderSubmit("Bottles");
			Hydro.checkOut();
			Hydro.validating_Employ_Discount_forInlineProducts(65);
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
