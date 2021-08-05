package TestExecute.hottools.SmokeTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.Hottools.HottoolsHelpr;
import TestLib.Common;
import TestLib.Login;

public class HT_ST_Stylist_User_promocode {

	String datafile = "Hottools//HottoolsTestData.xlsx";	
	HottoolsHelpr Hottools=new HottoolsHelpr(datafile);

	@Test(priority=1)
	public void Stylist_User_promocode(){

		try{
			Hottools.agreeCookiesbanner();		
			//Hottools.Newslettersignup();
			Hottools.signin("StylistCustomerAccountDetails");
			Hottools.CategorySelection();
			Hottools.CategoryProductSelection();
			Hottools.CategoryMincart();
			Hottools.checkoutpage();
		    Hottools.ValidatingPromocode("StylistPromocode");
			//Hottools.CreditcardPayment_promocde("PaymentDetails");
		    
		
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
	/*
      @BeforeMethod
	@Parameters({"browser"})  
	  public void startTest() throws Exception {
		System.setProperty("configFile", "Hottools\\config.properties");
		  Login.signIn("chrome");
	  }
*/
	@AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}

}

