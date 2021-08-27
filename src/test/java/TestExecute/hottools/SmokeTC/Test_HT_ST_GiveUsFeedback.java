package TestExecute.hottools.SmokeTC;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.sun.jna.platform.win32.Netapi32Util.Group;

import TestComponent.Hottools.HottoolsHelpr;
import TestLib.BaseDriver;
import TestLib.Common;
import TestLib.Login;

public class Test_HT_ST_GiveUsFeedback {

	String datafile = "Hottools//HottoolsTestData.xlsx";	
	HottoolsHelpr Hottools=new HottoolsHelpr(datafile);
	@Test(priority=1)
	public void giveUsFeedbackValidation(){
		try {
			Hottools.agreeCookiesbanner();
			Hottools.Newslettersignup();
			Hottools.validateNavigateGiveUsFeedback("GiveUsFeedback");
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
	@Parameters({"browser"})  
	  public void startTest() throws Exception {
		//System.setProperty("configFile", "Hottools\\Config_Hottools_Staging.properties");
		//System.setProperty("configFile", "Hottools\\Config_Hottools_Production.properties");
		  Login.signIn("firefox");

	  }*/

	@AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}

}
