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

public class RHT_MOB_ST_ForgotPassword {
	String datafile = "revlon//RevlonTestData.xlsx";	
	RevelonMobHelper revlon=new RevelonMobHelper(datafile);
	
	@Test(priority=1)
	public void ForgotPassword() throws Exception {

		try {
			revlon.slider();
			revlon.Loginpage();
			revlon.forgotPassword("AccountDetails");
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
          Login.signIn("chrome","iPad");
         
      }*/
	@AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}
}
