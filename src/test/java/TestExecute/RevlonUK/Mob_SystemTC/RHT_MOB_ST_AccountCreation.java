package TestExecute.RevlonUK.Mob_SystemTC;

import org.testng.annotations.Test;

import TestComponent.RevlonUk.RevlonUKHelper;
import TestComponent.RevlonUk.RevlonUKMobileHelper;
import TestLib.Common;
import TestLib.Login;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class RHT_MOB_ST_AccountCreation {
	String datafile = "revlonUK//RevlonUKTestData.xlsx";	
	RevlonUKMobileHelper revlon=new RevlonUKMobileHelper(datafile);
	
	
	@Test(priority=1)
	public void createAccount() throws Exception {

		try {
			revlon.slider();
			revlon.Accountcreation();
			revlon.CreateNewAccount("AccountCreation");
		}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	@BeforeMethod
	@Parameters({"device"})  
	  public void startTest(String Device) throws Exception {
		System.setProperty("configFile", "RevlonUK\\config.properties");
		  Login.signIn("chrome",Device);
		  
	  }
	
	/*@BeforeMethod
    //@Parameters({"device"})
      public void startTest() throws Exception {
        System.setProperty("configFile", "RevlonUK\\config.properties");
          Login.signIn("chrome","iPhone X");
         
      }*/
	
	@AfterTest
	public void clearBrowser()
	{
		Common.closeAll();
	}

}
