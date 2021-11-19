package TestExecute.Oxo;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.oxo.OxoHelper;
import TestLib.BaseDriver;
import TestLib.Common;
import TestLib.Login;

public class TEST_OXO_ST_004_GuestCheckoutCreditcard {
	String datafile = "oxo//OxoTestData.xlsx";
	OxoHelper oxo = new OxoHelper(datafile);

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class, invocationCount = 1)

	public void Guest_Checkout_CreditCard() {
		try {

			oxo.closetheadd();
			oxo.clickBaby_Toddler();
			//oxo.CookingAndBaking();
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
			Common.refreshpage();

		} catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}

	}

	@AfterTest
	public void clearBrowser() {
		//Common.closeAll();

	}

	@BeforeMethod
	public void startTest() throws Exception {
		System.setProperty("configFile", "Oxo//config.properties");
		Login.signIn();

	}
}