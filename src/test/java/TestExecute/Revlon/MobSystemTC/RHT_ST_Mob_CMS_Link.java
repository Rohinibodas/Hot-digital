package TestExecute.Revlon.Mob_SystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.revlon.RevelonMobHelper;
import TestLib.Common;
import TestLib.Login;

public class RHT_ST_Mob_CMS_Link {

	String datafile = "revlon//RevlonTestData.xlsx";	
	RevelonMobHelper revlon=new RevelonMobHelper(datafile);
	@Test(priority=1)
	public void NavigationCMSLinks() throws Exception {

		try {
			revlon.acceptPrivecy();
			revlon.navigateCMSLink();
			revlon.navigateAboutUs();
			revlon.navigateFAQ();
			revlon.navigatePrivacyPolicy();
			revlon.navigateFindStore();
			revlon.navigateReturns();
			revlon.navigatePressRoom();
			revlon.navigateTermsOfUse();
			revlon.navigateProductRegistration();
			revlon.ProductRegistration();
			revlon.navigateHomePage();
			revlon.navigateContactUs();
			revlon.contactus();
			
			
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




