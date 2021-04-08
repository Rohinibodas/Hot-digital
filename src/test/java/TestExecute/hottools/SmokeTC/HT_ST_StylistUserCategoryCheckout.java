package TestExecute.hottools.SmokeTC;

import org.testng.annotations.Test;

import TestComponent.Hottools.HottoolsHelpr;
import TestLib.Common;
import TestLib.Login;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class HT_ST_StylistUserCategoryCheckout {
	String datafile = "Hottools//HottoolsTestData.xlsx";	
	HottoolsHelpr Hottools=new HottoolsHelpr(datafile);

	@Test(priority=1)
	public void StylistUserCategoryCheckoutCreditcard(){

		try{
			//Hottools.agreeCookiesbanner();
			Hottools.signin("StylistCustomerAccountDetails");
			Hottools.CategorySelection();
			Hottools.CategoryProductSelection();
			Hottools.CategoryMincart();
			Hottools.checkoutpage();
			Hottools.CreditcardPayment("PaymentDetails");
			Hottools.RegistereduserorderSuccesspage();
		}
		catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}
	
	 
	@BeforeMethod
	@Parameters({"browser"}) 
	  public void startTest(String browser) throws Exception {
		System.setProperty("configFile", "Hottools\\config.properties");
		  Login.signIn(browser);
		  
	  }
	
	/*@BeforeMethod
	@Parameters({"browser"})  
	  public void startTest() throws Exception {
		System.setProperty("configFile", "Hottools\\config.properties");
		  Login.signIn("chrome");
		  
	  }*/

	@AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}


}
