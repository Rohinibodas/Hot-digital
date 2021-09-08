package TestExecute.OXOBVT;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.oxo.OxoHelper;
import TestLib.BaseDriver;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_OXO_004_Guest_Checkout_Creditcard {
	String datafile = "oxo//OxoTestData.xlsx";
	OxoHelper oxo = new OxoHelper(datafile);

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class, invocationCount = 1)

	public void TEST_ST_OXO_004_Guest_Checkout_Creditcard() {
		try {

			oxo.closetheadd();
			oxo.clickBaby_Toddler();
			// oxo.CookingAndBaking();
			oxo.addproducts("1");
			oxo.checkout();
			oxo.ShippingAddress("ShippingAddress");
			oxo.selectGroundShippingMethod();
			oxo.clickAcceptingaddress();
			oxo.Click_CreditCard();
			oxo.Edit_BillingAddress("BiillingAddress");
			oxo.clickAcceptingaddress();
			oxo.creditCard_payment("PaymentDetails");
			oxo.VerifyaingConformationPage();

		} catch (Exception e) {
			Assert.fail(e.getMessage(), e);
		}
	}

	@AfterTest
	public void clearBrowser() {
		Common.closeAll();
	}

	@BeforeMethod
	public void startTest() throws Exception {
		// System.setProperty("configFile", "Oxo\\Config_OXO_Staging.properties");
		Login.signIn();
	}
}