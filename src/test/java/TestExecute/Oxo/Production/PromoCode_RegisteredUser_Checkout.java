package TestExecute.Oxo.Production;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.oxo.OxoHelperLive;
import TestLib.Common;
import TestLib.Login;

public class PromoCode_RegisteredUser_Checkout {
	String datafile = "oxo//OxoTestData.xlsx";
	OxoHelperLive oxo = new OxoHelperLive(datafile);

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)

	public void PromoCode_RegisteredUser_Checkout() {
		try {
			oxo.closetheadd();
			oxo.acceptPrivecy();
			oxo.loginOxo("AccountDetails");
			//oxo.clickBaby_Toddler();
			oxo.Beverage();
			oxo.addproducts("1");
			oxo.checkout();
			oxo.selectGroundShippingMethod();
			oxo.Promocode("Promocode");
			oxo.Click_CreditCard();
			oxo.creditCard_payment("PaymentDetails");
			
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