package TestExecute.PUR;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.PUR.PurHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_PUR_054_Guestuser_Promocode_tax_VisaCC {

	String datafile = "PUR//PUR_TestData.xlsx";
	PurHelper PUR = new PurHelper(datafile);

	@Test(priority = 1)
	public void VISAcardGuestsingleproductDiscountPromocode_tax_StandardShippSameBillShipaddress() {

		try {
			PUR.searchProduct("productName");
			PUR.Addtocart();
			PUR.checkoutPage();
			PUR.shipping_Address("Address");
			PUR.GuestUserApplyPromocode("promocode");
			PUR.Verify_Tax();
			PUR.updatePaymentAndSubmitOrder("PaymentDetails");

		} catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}

	/*
	  @BeforeMethod public void startTest() throws Exception {
	  System.setProperty("configFile", "PUR//config.properties");
	  Login.signIn("chrome"); }
	 */

	@BeforeMethod
	  public void startTest() throws Exception {
		 //System.setProperty("configFile", "PUR\\Config_PUR_Staging.properties");
		  Login.signIn();
}

	@AfterTest
	public void clearBrowser() {
		Common.closeAll();

	}

}
