package TestExecute.hottools.MobSystemTC;

import org.testng.annotations.Test;

import TestComponent.Hottools.HottoolsMobHelper;
import TestLib.Common;
import TestLib.Login;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class HT_ST_Mob_DistributorForgotPassword {
	String datafile = "Hottools//HottoolsTestData.xlsx";	
	HottoolsMobHelper Hottools=new HottoolsMobHelper(datafile);
	
	@Test(priority=1)
	public void ValidateProductRegistration() throws Exception {

		try {
			Thread.sleep(10000);
			//Hottools.agreeCookiesbanner();
			Hottools.slider();
			Hottools.DistributorLoginpage();
			Hottools.DistributorforgotPassword("DistributorAccountDetails");
		}
		catch (Exception e) {
			Assert.fail(e.getMessage(), e);
		} 
	}
		
	@BeforeMethod
	@Parameters({"device"})  
	  public void startTest(String Device) throws Exception {
		System.setProperty("configFile", "Hottools\\config.properties");
		  Login.signIn("chrome",Device);
		  
	  }
	
	/*@BeforeMethod
    //@Parameters({"device"})
      public void startTest() throws Exception {
        System.setProperty("configFile", "Hottools\\config.properties");
          Login.signIn("chrome","Galaxy S5");
      }*/
	
	@AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}

}
