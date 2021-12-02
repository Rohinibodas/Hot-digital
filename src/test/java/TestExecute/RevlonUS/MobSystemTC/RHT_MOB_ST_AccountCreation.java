package TestExecute.RevlonUS.MobSystemTC;

import org.testng.annotations.Test;

import TestComponent.RevlonUS.RevelonMobHelper;
import TestLib.Common;
import TestLib.Login;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class RHT_MOB_ST_AccountCreation {
	String datafile = "revlon//RevlonTestData.xlsx";	
	RevelonMobHelper revlon=new RevelonMobHelper(datafile);
	
	@Test(priority=1)
	public void AccountCreation() throws Exception {

		try {
			revlon.slider();
			revlon.AccountCreationPage();
			revlon.CreateNewAccount("AccountCreation");
			
		}
		catch (Exception e) {
			Assert.fail(e.getMessage(), e);
		} 
	}
		
	@BeforeMethod
	@Parameters({"device"})  
	  public void startTest(String Device) throws Exception {
		System.setProperty("configFile", "Revelon\\config.properties");
		  Login.signIn("chrome",Device);
		  
	  }
	
	/*@BeforeMethod
    //@Parameters({"device"})
      public void startTest() throws Exception {
        System.setProperty("configFile", "Revelon\\config.properties");
          Login.signIn("chrome","Galaxy S5");
         
      }*/
	@AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}
}
