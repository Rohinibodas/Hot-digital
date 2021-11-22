package TestExecute.Revlon.SystemTC;

import org.testng.annotations.Test;

import TestComponent.revlon.RevelonHelper;
import TestLib.Common;
import TestLib.Login;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class TEST_ST_RHT_013_CMSNavigation_Links_Validation  {
	String datafile = "revlon//RevlonTestData.xlsx";	
	RevelonHelper revelon=new RevelonHelper(datafile);
	
	@Test(priority=1)
	public void Navigation_CMS_links() throws Exception {

		try {
			revelon.Newslettersignup();
			revelon.acceptPrivecy();
			revelon.navigateCMSLink();
			revelon.navigateAboutUs();
			
			revelon.navigateFAQ();
			
			revelon.navigatePrivacyPolicy();
			
			revelon.navigateFindStore();
			
			revelon.navigateReturns();
			
			revelon.navigatePressRoom();
			
			revelon.navigateTermsOfUse();
		
			revelon.navigateProductRegistration();
			revelon.ProductRegistration();
			revelon.navigateHomePage();
			revelon.navigateContactUs();
			revelon.contactus();
		}
		catch (Exception e) {
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	@BeforeTest
	  public void startTest() throws Exception {
		// System.setProperty("configFile", "RevlonUS\\Config_RevlonUS_Staging.properties");
		  Login.signIn();
}
	
	@AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}


}
