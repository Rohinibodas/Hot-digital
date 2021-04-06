package TestExecute.Oxo.Production;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.oxo.OxoHelperLive;
import TestLib.Common;
import TestLib.Login;

public class Registered_ExpressPayPal_Checkout {
	String datafile = "oxo//OxoTestData.xlsx";
	OxoHelperLive oxo = new OxoHelperLive(datafile);

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)

	public void Registered_ExpressPayPal_Checkout() {
		try {
			
			oxo.closetheadd();
			oxo.loginOxo("AccountDetails");
			oxo.acceptPrivecy();
			oxo.Beverage();
			oxo.addproducts("1");
			oxo.checkout();
			oxo.clickViewCart();
            oxo.Express_payPal_payment("PaypalDetails");
           
			

		} catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}

	@AfterTest
	public void clearBrowser() throws Exception {
		Common.closeAll();
	}

	@BeforeMethod
	public void startTest() throws Exception {
		System.setProperty("configFile", "Oxo\\config.properties");

		Login.signIn();

	}
}