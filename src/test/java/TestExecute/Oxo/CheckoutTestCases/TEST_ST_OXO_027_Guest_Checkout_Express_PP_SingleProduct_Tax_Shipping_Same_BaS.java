package TestExecute.Oxo.CheckoutTestCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.oxo.OxoHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_OXO_027_Guest_Checkout_Express_PP_SingleProduct_Tax_Shipping_Same_BaS {
	String datafile = "oxo//OxoTestData.xlsx";
	OxoHelper oxo = new OxoHelper(datafile);

	@Test(priority = 1)

	public void TEST_ST_OXO_027_Guest_Checkout_Express_PP_SingleProduct_Tax_Shipping_Same_BaS () throws Exception {

		try {
			oxo.closetheadd();
			oxo.CookingAndBaking();
			oxo.addproducts("1");
			oxo.clickViewCart();
			oxo.Express_payPal_payment("PaypalDetails");
			oxo.selectGroundShippingMethod();
			oxo.PlaceorderButton();
			oxo.tax();
			
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
