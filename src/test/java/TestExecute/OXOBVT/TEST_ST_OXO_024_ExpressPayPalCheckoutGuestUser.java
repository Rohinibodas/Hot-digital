package TestExecute.OXOBVT;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.oxo.OxoHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_OXO_024_ExpressPayPalCheckoutGuestUser {
	String datafile = "oxo//OxoTestData.xlsx";
	OxoHelper oxo = new OxoHelper(datafile);

	@Test(priority = 1)

	public void TEST_ST_OXO_024_ExpressPayPalCheckoutGuestUser() throws Exception {

		try {
		//	oxo.Close_popup();
			oxo.closetheadd();
			oxo.Beverage();
			//oxo.clickBaby_Toddler();
			oxo.addproducts("1");
			// oxo.checkout();
			oxo.clickViewCart();
			
			Thread.sleep(3000);
			String URL = Common.getCurrentURL();
			System.out.println(URL);
			if( URL.contains("Stg")) {
				
			
			oxo.Express_payPal_payment("PaypalDetails");
			oxo.Express_PayPal_GroundShippingMethod();
			oxo.PlaceorderButton();
			oxo.VerifyaingConformationPage();

			}
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
		//System.setProperty("configFile", "Oxo\\Config_OXO_Prouction.properties");
		// System.setProperty("configFile", "Oxo\\config.properties");
		Login.signIn();

	}
}
