package TestExecute.hottools.MobSystemTC;

import org.testng.annotations.Test;

import TestComponent.Hottools.HottoolsMobHelper;
import TestLib.Common;
import TestLib.Login;

import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeMethod;

public class HT_ST_Mob_ValidateCMSPages {
	String datafile = "Hottools//HottoolsTestData.xlsx";	
	HottoolsMobHelper Hottools=new HottoolsMobHelper(datafile);
	
	@Test(priority=1)
	public void ValidateCMSPages() throws Exception {

		try {
			//Hottools.agreeCookiesbanner();
			Thread.sleep(10000);
			Hottools.validateAboutUsLink();
			Hottools.validateNavigateFAQ();
			Hottools.validateNavigateContactUs("ContactUs");
			Hottools.validateNavigatePrivacyPolicy();
			Hottools.validateNavigateTermsAndConditions();
			Hottools.validateNavigatehottoolsUniversity();
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
          Login.signIn("chrome","iPhone X");
	}*/
	
	@AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}

}
