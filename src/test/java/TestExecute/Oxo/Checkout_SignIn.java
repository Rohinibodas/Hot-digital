package TestExecute.Oxo;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.oxo.OxoHelper;
import TestLib.Common;
import TestLib.Login;

public class Checkout_SignIn {
	String datafile = "oxo//OxoTestData.xlsx";
	OxoHelper oxo = new OxoHelper(datafile);

	@Test(priority = 1)

	public void Checkout_SignIn() throws Exception {

		try {
			oxo.closetheadd();
			oxo.acceptPrivecy();
			//oxo.acceptPrivecy();
			oxo.clickBaby_Toddler();
			oxo.addproducts("1");
			// oxo.checkout();
			oxo.clickViewCart();
			oxo.SignIn_CheckoutPage("AccountDetails");

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
		System.setProperty("configFile", "Oxo\\config.properties");
		Login.signIn();

	}
}
