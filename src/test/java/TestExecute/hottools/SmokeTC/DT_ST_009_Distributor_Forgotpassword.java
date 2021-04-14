package TestExecute.hottools.SmokeTC;

import org.testng.annotations.Test;
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

@Test
public class DT_ST_009_Distributor_Forgotpassword {

	String datafile = "Hottools//HottoolsTestData.xlsx";	
	HottoolsHelpr Hottools=new HottoolsHelpr(datafile);
	@Test(priority=1)
	public void forgotPassword1(){

		try{
			//Hottools.agreeCookiesbanner();
			Hottools.DistributorforgotPassword("DistributorAccountDetails");
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
