package TestExecute.hottools.MobSystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.Hottools.HottoolsMobHelper;
import TestLib.Common;
import TestLib.Login;

public class HT_ST_Mob_NewsletterSubscription {

	String datafile = "Hottools//HottoolsTestData.xlsx";	
	HottoolsMobHelper Hottools=new HottoolsMobHelper(datafile);
	@Test(priority=1)
	public void ValidatingNewsletterSubscription() {

		try{
			//Hottools.agreeCookiesbanner();
			Hottools.FooterNewletterSubcription();
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
          Login.signIn("chrome","iPad");
      }*/
	
	@AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}

}


