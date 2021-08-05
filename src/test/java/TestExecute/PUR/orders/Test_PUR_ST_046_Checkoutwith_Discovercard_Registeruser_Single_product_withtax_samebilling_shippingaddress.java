package TestExecute.PUR.orders;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.PUR.PurHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_PUR_ST_046_Checkoutwith_Discovercard_Registeruser_Single_product_withtax_samebilling_shippingaddress {
	String datafile = "PUR//PUR_TestData.xlsx";
	PurHelper PUR = new PurHelper(datafile);

	@Test(priority = 1)
	public void registeruserDiscovercardTypesingletaxsamebill() {

		try {

			PUR.singin("CustomerAccountdetails");
			PUR.searchProduct("productName");
			PUR.Addtocart();
			PUR.checkoutPage();
			PUR.AddAddress();
			PUR.Verify_Tax();
			PUR.updatePaymentAndSubmitOrder("DiscoverDetails");

		} catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}
/*
	@BeforeMethod
	public void startTest() throws Exception {
		System.setProperty("configFile", "PUR//config.properties");
		Login.signIn("chrome");
	}

	*/
	  @BeforeMethod
	  @Parameters({"browser"}) public void startTest(String browser) throws
	  Exception { System.setProperty("configFile", "PUR\\config.properties");
	  Login.signIn(browser); }
	 

	@AfterTest
	public void clearBrowser() {
		Common.closeAll();

	}

}
