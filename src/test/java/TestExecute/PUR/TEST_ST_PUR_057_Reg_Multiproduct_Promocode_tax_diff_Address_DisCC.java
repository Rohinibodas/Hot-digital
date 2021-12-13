package TestExecute.PUR;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.PUR.PurHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_PUR_057_Reg_Multiproduct_Promocode_tax_diff_Address_DisCC {
	String datafile = "PUR//PUR_TestData.xlsx";
	PurHelper PUR = new PurHelper(datafile);

	@Test(priority = 1)
	public void registeruserdiscovercardTypemulti_discountpromo_taxdifferentbillship() {

		try {

			PUR.singin("CustomerAccountdetails");
			PUR.Mouseover();
			PUR.Productselection();
			PUR.navigateMinicart();
			PUR.searchProduct("productName");
			PUR.Addtocart();
			PUR.checkoutPage();
			PUR.AddAddress();
			PUR.DifferentBillAddress("DifferentBillandshipping");
			PUR.Applypromocode("promocode");
			PUR.Verify_Tax();
			PUR.updatePaymentAndSubmitOrder("DiscoverDetails");

		} catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}
/*
	@BeforeMethod
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "PUR\\Config_PUR_Staging.properties");
		  Login.signIn();
}

	*/
	@BeforeMethod
	  public void startTest() throws Exception {
		// System.setProperty("configFile", "PUR\\Config_PUR_Staging.properties");
		  Login.signIn();
}
	 

	@AfterTest
	public void clearBrowser() {
		Common.closeAll();

	}

}
