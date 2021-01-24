package TestExecute.RevlonUK.Sprint1Web;

import org.testng.annotations.Test;

import TestComponent.RevlonUk.RevlonUKHelper;
import TestLib.Common;
import TestLib.Login;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class RUK_ST_CMSLinks {
	String datafile = "revlonUK//RevlonUKTestData.xlsx";	
	RevlonUKHelper revlon=new RevlonUKHelper(datafile);
	
	@Test(priority=1)
	public void NavigationCMSLinks() throws Exception {
		try {
			revlon.navigateCMSLink();
			revlon.navigateAboutUs();
			revlon.navigateTermsAndConditions();
			revlon.navigatePrivacyPolicy();
			revlon.navigateCookiePolicy();
			revlon.navigateFAQ();
			revlon.navigateTermsofSale();
			revlon.navigateShippingAndReturns();
			revlon.navigateWarranty();
			revlon.navigateContactUs();
			revlon.contactus();
		}
		catch (Exception e) {
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	@BeforeMethod
	@Parameters({"browser"}) 
	  public void startTest(String browser) throws Exception {
		System.setProperty("configFile", "RevlonUK\\config.properties");
		  Login.signIn(browser);
	  }
	
	/*@BeforeMethod
	@Parameters({"browser"})  
	  public void startTest() throws Exception {
		System.setProperty("configFile", "RevlonUK\\config.properties");
		  Login.signIn("firefox");
		  
	  }*/
	
	@AfterTest
	public void clearBrowser()
	{
		Common.closeAll();
	}
}
