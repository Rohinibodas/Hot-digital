package TestExecute.hottools.MobSystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.Hottools.HottoolsMobHelper;
import TestLib.Common;
import TestLib.Login;

public class HT_ST_Mob_RegistrationForSylistSaloonOwnerUser {
	String datafile = "Hottools//HottoolsTestData.xlsx";	
	HottoolsMobHelper Hottools=new HottoolsMobHelper(datafile);

	@Test(priority=1)
	public void CreateAnAccountStylistSalonOwner() {
		try {
			//Hottools.agreeCookiesbanner();
			Hottools.createNewCustomerAccount_Stylist_SalonOwner("Stylist_registration");
		} catch (Exception e) {

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

