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

public class HT_ST_DistributoruserBrowserSearch {
	String datafile = "Hottools//HottoolsTestData.xlsx";	
	HottoolsHelpr Hottools=new HottoolsHelpr(datafile);

	@Test(priority=1)
	public void DistributorBrowserSearch(){

		try{
			Thread.sleep(2000);
			Hottools.agreeCookiesbanner();
			Hottools.Newslettersignup();
			Hottools.distributorsignin("DistributorAccountDetails");
			Hottools.searchingProducts("productName");
			Hottools.ZerosearchProducts("productName");
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