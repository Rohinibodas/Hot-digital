package TestExecute.PUR;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.PUR.PurHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_PUR_ST_056_Guestuser_Multiproduct_Promocode_tax_AmexCC {
	String datafile = "PUR//PUR_TestData.xlsx";
	PurHelper PUR = new PurHelper(datafile);

	@Test(priority = 1)
	public void GuestuserAMEX_multiproduct_Discount_promocode_taxsameShippingBillA() {

		try {

			PUR.Mouseover();
			PUR.Productselection();
			PUR.navigateMinicart();
			PUR.searchProduct("productName");
			PUR.Addtocart();
			PUR.checkoutPage();
			PUR.shipping_Address("Address");
			PUR.GuestUserApplyPromocode("promocode");
			PUR.Verify_Tax();
		    PUR.updatePaymentAndSubmitOrder("AmexDetails");
   
		} catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}

	
	/*  @BeforeMethod public void startTest() throws Exception {
	  System.setProperty("configFile", "PUR//config.properties");
	  Login.signIn("chrome"); }
*/	 
 @BeforeMethod
	@Parameters({ "browser" })
	public void startTest(String browser) throws Exception {
		System.setProperty("configFile", "PUR\\config.properties");
		Login.signIn(browser);
	}

	@AfterTest
	public void clearBrowser() {
		Common.closeAll();

	}
}
