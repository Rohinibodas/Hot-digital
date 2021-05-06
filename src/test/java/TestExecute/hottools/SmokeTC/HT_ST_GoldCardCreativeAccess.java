package TestExecute.hottools.SmokeTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.Hottools.HottoolsHelpr;
import TestLib.Common;
import TestLib.Login;

public class HT_ST_GoldCardCreativeAccess {

	String datafile = "Hottools//HottoolsTestData.xlsx";	
	HottoolsHelpr Hottools=new HottoolsHelpr(datafile);
	@Test(priority=1)
	public void GoldCardCreative(){

		try{
			Hottools.agreeCookiesbanner();
			Hottools.GoldCardVideoClose();
			Hottools.GoldCardCreative("GoldCardDetails");
			Hottools.Checkout();
			Hottools.GoldCardShipping("GoldCardDetails");
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
		//s@Parameters({"browser"})  
		  public void startTest() throws Exception {
			System.setProperty("configFile", "Hottools\\config.properties");
			  Login.signIn("edge");

     }*/

	@AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}
}
