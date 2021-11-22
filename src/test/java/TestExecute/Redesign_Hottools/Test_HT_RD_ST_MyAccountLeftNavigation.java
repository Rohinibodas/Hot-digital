package TestExecute.Redesign_Hottools;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import TestComponent.Redesign_Hottools.Redesign_HottoolsHelper;
import TestLib.Login;

public class Test_HT_RD_ST_MyAccountLeftNavigation {

	String datafile = "Redesign_Hottools//Redesign_HottoolsTestData.xlsx";	
	Redesign_HottoolsHelper Resign_Hottools=new Redesign_HottoolsHelper(datafile);

	@Test(priority=1)
	public void MyAccountLeftNavigation(){
		try{
			Resign_Hottools.agreeCookiesbanner();
			Resign_Hottools.signin("RetailCustomerAccountDetails");
			Resign_Hottools.My_Orders();
			Resign_Hottools.Wishlist();
			Resign_Hottools.AddressBook();
			Resign_Hottools.AccountInformation();
			Resign_Hottools.Communication_Preferences();
			Resign_Hottools.Notifications();
			Resign_Hottools.pro_login();
		}
		catch (Exception e) {
			Assert.fail(e.getMessage(), e);
		}
	}
	
	
	@BeforeMethod
	
	  public void startTest() throws Exception {
		System.setProperty("configFile", "Redesign_Hottools\\config.properties");
		Login.signIn(); 
	}

	@AfterTest
	public void clearBrowser()
	{
	//Common.closeAll();

	}

}
