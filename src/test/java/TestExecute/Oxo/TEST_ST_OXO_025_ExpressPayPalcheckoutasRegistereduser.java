package TestExecute.Oxo;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import TestComponent.oxo.OxoHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_OXO_025_ExpressPayPalcheckoutasRegistereduser {
	String datafile = "oxo//OxoTestData.xlsx";
	OxoHelper oxo = new OxoHelper(datafile);

	@Test(priority = 1)

	public void Registered_ExpressPayPal_Checkout() throws Exception {

		try {
			oxo.closetheadd();
			oxo.loginOxo("AccountDetails");
			oxo.clickBaby_Toddler();
			//oxo.Beverage();
			oxo.addproducts("1");
			//oxo.checkout();
			oxo.clickViewCart();
            oxo.Express_payPal_payment("PaypalDetails");
            oxo.Express_PayPal_GroundShippingMethod();
            oxo.PlaceorderButton();
           // oxo.VerifyaingConformationPage();

			
			
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
