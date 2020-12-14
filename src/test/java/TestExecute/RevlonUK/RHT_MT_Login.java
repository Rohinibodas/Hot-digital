package TestExecute.RevlonUK;

import org.testng.annotations.Test;

import TestComponent.RevlonUk.RevlonUKHelper;
import TestComponent.revlon.RevelonHelper;
import TestLib.Common;
import TestLib.Login;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class RHT_MT_Login {
	String datafile = "revlonUK//RevlonUKTestData.xlsx";	
	RevlonUKHelper revlon=new RevlonUKHelper(datafile);

	@Test(priority=1)
	public void loginApplication() throws Exception {

		try {
			revlon.loginRevlonUK("AccountDetails");
		}
		catch (Exception e) {
			Assert.fail(e.getMessage(), e);
		} 
	}

	/*@BeforeMethod
	@Parameters({"browser"}) 
	  public void startTest(String browser) throws Exception {
		System.setProperty("configFile", "RevlonUK\\config.properties");
		  Login.signIn(browser);
		  
	  }*/
	
	@BeforeMethod
	@Parameters({"browser"})  
	  public void startTest() throws Exception {
		System.setProperty("configFile", "RevlonUK\\config.properties");
		  Login.signIn("chrome");
		  
	  }

	@AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}

}