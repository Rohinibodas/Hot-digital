package TestExecute.hottools.SmokeTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;
import TestComponent.Hottools.HottoolsHelpr;
import TestLib.Common;
import TestLib.Login;

public class Test_HT_ST_GoldCard_PremiereAccessCode {


	String datafile = "Hottools//HottoolsTestData.xlsx";	
	HottoolsHelpr Hottools=new HottoolsHelpr(datafile);
	@Test(priority=1)
	public void GoldCardPremiere(){

		try{
			Hottools.agreeCookiesbanner();
			Hottools.GoldCardVideoClose();
			Hottools.GoldCardPremiere("GoldCardDetails");
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
		//System.setProperty("configFile", "Hottools\\Config_Hottools_Staging.properties");
		//System.setProperty("configFile", "Hottools\\Config_Hottools_Production.properties");
		Login.signIn(browser);
	}

	/*@BeforeMethod
		//s@Parameters({"browser"})  
		  public void startTest() throws Exception {
			//System.setProperty("configFile", "Hottools\\Config_Hottools_Staging.properties");
		//System.setProperty("configFile", "Hottools\\Config_Hottools_Production.properties");
			  Login.signIn("Edge");

     }*/

	@AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}
}

