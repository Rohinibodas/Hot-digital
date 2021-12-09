package TestExecute.Oxo.CheckoutTestCases;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.oxo.OxoHelper;
import TestLib.Common;
import TestLib.Login;

public class  TEST_ST_OXO_003_Guest_Checkout_Mastercard_MultiProduct_Tax_ESO_Diferent_BaS{
	String datafile = "oxo//OxoTestData.xlsx";
	OxoHelper oxo = new OxoHelper(datafile);

	@Test(priority = 1)

	public void TEST_ST_OXO_003_Guest_Checkout_Mastercard_MultiProduct_Tax_ESO_Diferent_BaS() throws Exception {

		try {
			oxo.closetheadd();
			oxo.Beverage();
			oxo.addproducts("1");
			oxo.clickBaby_Toddler();
			oxo.addproducts("1");
			oxo.CleaningandOrganization();
			oxo.addproducts("1");
			oxo.CookingAndBaking();
			oxo.addproducts("1");
			oxo.checkout();
			oxo.ShippingAddress("ShippingAddress");
			oxo.clickAcceptingaddress();
			oxo.selectExpressStandardOvernightShippingMethod();
			oxo.Click_CreditCard();
			oxo.Edit_BillingAddress("BiillingAddress");
			oxo.clickAcceptingaddress();
			oxo.creditCard_payment("MasterCardPaymentDetails");
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
