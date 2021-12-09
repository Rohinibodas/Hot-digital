package TestExecute.Oxo.CheckoutTestCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.oxo.OxoHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_ST_OXO_036_Validate_RegisterUser_Checkout_AmexCC_NoTax_Employee_Discount_with_Different_BaS {
	String datafile = "oxo//OxoTestData.xlsx";
	OxoHelper oxo = new OxoHelper(datafile);

	@Test(priority = 1)

	public void Test_ST_OXO_036_Validate_RegisterUser_Checkout_AmexCC_NoTax_Employee_Discount_with_Different_BaS () throws Exception {

		try {
			
			oxo.closetheadd();
			oxo.loginOxo("NoTaxAddress");
			oxo.clickBaby_Toddler();
			oxo.addproducts("1");
			oxo.checkout();
			oxo.addNewAddress("NoTaxAddress");
			oxo.clickAcceptingaddress();
			oxo.selectGroundShippingMethod();
			oxo.Promocode("EmployeeDiscount");
			oxo.Click_CreditCard();
			oxo.creditCard_payment("AMEXPaymentDetails");
			oxo.VerifyaingConformationPage();
			
			
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
