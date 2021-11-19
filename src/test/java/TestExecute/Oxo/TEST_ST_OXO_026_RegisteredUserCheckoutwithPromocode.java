package TestExecute.Oxo;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.oxo.OxoHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_OXO_026_RegisteredUserCheckoutwithPromocode {
	String datafile = "oxo//OxoTestData.xlsx";
	OxoHelper oxo = new OxoHelper(datafile);

	@Test(priority = 1)

	public void PromoCode_RegisteredUser_Checkout() throws Exception {

		try {
			oxo.closetheadd();
			oxo.loginOxo("AccountDetails");
			oxo.clickBaby_Toddler();
			oxo.addproducts("1");
			oxo.checkout();
			oxo.selectGroundShippingMethod();
			oxo.Promocode("Promocode");
			oxo.Click_CreditCard();
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
		System.setProperty("configFile", "Oxo\\config.properties");
		Login.signIn();
	}
}
