
package TestExecute.Oxo.Production;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.oxo.OxoHelperLive;
import TestLib.Common;
import TestLib.Login;

public class RegisteredUser_Checkout_MasterCard_CC {
	String datafile = "oxo//OxoTestData.xlsx";
	OxoHelperLive oxo = new OxoHelperLive(datafile);

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)

	public void RegisteredUser_Checkout_MasterCard_CC() {
		try {
			oxo.closetheadd();
			oxo.acceptPrivecy();
			oxo.loginOxo("AccountDetails");
			//oxo.clickBaby_Toddler();
			oxo.Beverage();
			oxo.addproducts("1");
			oxo.checkout();
            oxo.selectGroundShippingMethod();
			oxo.Click_CreditCard();
			oxo.creditCard_payment("MasterCardPaymentDetails");
			

		} catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}

	@AfterTest
	public void clearBrowser() throws Exception {
		//Common.closeAll();
	}

	@BeforeMethod
	public void startTest() throws Exception {
		System.setProperty("configFile", "Oxo\\config.properties");

		Login.signIn();

	}
}