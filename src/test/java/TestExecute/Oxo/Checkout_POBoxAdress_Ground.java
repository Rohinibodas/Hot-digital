package TestExecute.Oxo;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.oxo.OxoHelper;
import TestLib.Common;
import TestLib.Login;

public class Checkout_POBoxAdress_Ground {
	String datafile = "oxo//OxoTestData.xlsx";
	OxoHelper oxo = new OxoHelper(datafile);

	@Test(priority = 1)

	public void Checkout_POBoxAdress_Ground() throws Exception {

		try {
			oxo.closetheadd();
			//oxo.PrivacyPolicy();
			oxo.Beverage();
			oxo.addproducts("1");
			oxo.checkout();
			oxo.ShippingAddress("POBoxAddress");
			oxo.selectGroundShippingMethod();
			oxo.clickAcceptingaddress();
		    oxo.Click_CreditCard();
			oxo.creditCard_payment("DiscoverPaymentDetails");
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
		System.setProperty("configFile", "Oxo\\config.properties");
		Login.signIn();

	}
}