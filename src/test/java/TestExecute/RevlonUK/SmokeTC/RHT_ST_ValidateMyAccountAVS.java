package TestExecute.RevlonUK.SmokeTC;

import org.testng.annotations.Test;

import TestComponent.RevlonUk.RevlonUKHelper;
import TestLib.Common;
import TestLib.Login;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class RHT_ST_ValidateMyAccountAVS {
	String datafile = "revlonUK//RevlonUKTestData.xlsx";	
	RevlonUKHelper revlon=new RevlonUKHelper(datafile);
		
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void ValidateMyAccountLeftMenu() throws Exception {

		try {
			revlon.loginRevlonUK("AccountDetails");
			revlon.ValidateAddressBook();
			revlon.ValidateMyAccountAVS("Guest_shipping");
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
