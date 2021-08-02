package TestExecute.PUR.orders;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.PUR.PurHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_PUR_ST_045_checkoutwith_AMEX_Guestuser_Singleproduct_tax_Shippingwith_Different_billing_shippingaddress {

	String datafile = "PUR//PUR_TestData.xlsx";
	PurHelper PUR = new PurHelper(datafile);

	@Test(priority = 1)
	public void GuestusercheckoutAMEX_single_taxdiffShippingBillA() {

		try {
			PUR.searchProduct("productName");
			PUR.Addtocart();
			PUR.checkoutPage();
			PUR.shipping_Address("Guest_shipping");
			PUR.DifferentBillAddress("DifferentBillandshipping");
			PUR.Verify_Tax();
			PUR.updatePaymentAndSubmitOrder("AmexDetails");

		} catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}

/*	@BeforeMethod
	public void startTest() throws Exception {
		System.setProperty("configFile", "PUR//config.properties");
		Login.signIn("chrome");
	}*/

		 @BeforeMethod
	  
	  @Parameters({"browser"}) public void startTest(String browser) throws
	  Exception { System.setProperty("configFile", "PUR\\config.properties");
	  Login.signIn(browser); }
	 

	@AfterTest
	public void clearBrowser() {
		Common.closeAll();

	}

}
