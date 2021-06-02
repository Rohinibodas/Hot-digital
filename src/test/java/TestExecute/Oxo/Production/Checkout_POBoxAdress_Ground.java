package TestExecute.Oxo.Production;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import TestComponent.oxo.OxoHelperLive;
import TestLib.Common;
import TestLib.Login;

public class Checkout_POBoxAdress_Ground {
	String datafile = "oxo//OxoTestData.xlsx";
	OxoHelperLive oxo = new OxoHelperLive(datafile);

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)

	public void Checkout_POBoxAdress_Ground() {
		try {
			oxo.closetheadd();
			//oxo.PrivacyPolicy();
			oxo.acceptPrivecy();
			//oxo.ProdclickBaby_Toddler();
		
			oxo.Beverage();
			oxo.addproducts("1");
			oxo.checkout();
			oxo.ShippingAddress("POBoxAddress");
			oxo.selectGroundShippingMethod();
			oxo.clickAcceptingaddress();
		    oxo.Click_CreditCard();
			oxo.creditCard_payment("AMEXPaymentDetails");
		
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