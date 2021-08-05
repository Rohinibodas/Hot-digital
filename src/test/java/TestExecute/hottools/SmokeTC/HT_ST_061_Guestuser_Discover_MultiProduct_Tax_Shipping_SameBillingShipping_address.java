package TestExecute.hottools.SmokeTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.Hottools.HottoolsHelpr;
import TestLib.Common;
import TestLib.Login;

public class HT_ST_061_Guestuser_Discover_MultiProduct_Tax_Shipping_SameBillingShipping_address {

	String datafile = "Hottools//HottoolsTestData.xlsx";
	HottoolsHelpr Hottools = new HottoolsHelpr(datafile);

	@Test(priority = 1)
	public void GuestCheckoutwithCreditCard() {
		try {
			Hottools.agreeCookiesbanner();
			Hottools.Newslettersignup();
			Hottools.CategorySelection();
			Hottools.TwoCategoryProductSelection();
			Hottools.TwoproductCategoryMincart();
			Hottools.Guestcheckoutpage("Guestshippingaddress");
			Hottools.VerifyTax();
			Hottools.CreditcardPayment("DiscovercardDetails");
			Hottools.GuestuserorderSuccesspage();
		} catch (Exception e) {
			Assert.fail(e.getMessage(), e);
		}
	}

	
	 @BeforeMethod
  
  @Parameters({"browser"}) public void startTest(String browser) throws
  Exception { System.setProperty("configFile", "Hottools\\config.properties");
 Login.signIn(browser); }
	 
/*
	@BeforeMethod
	@Parameters({ "browser" })
	public void startTest() throws Exception {
		System.setProperty("configFile", "Hottools\\config.properties");
		Login.signIn("chrome");
	}
*/
	@AfterTest
	public void clearBrowser() {
		 Common.closeAll();

	}

}
