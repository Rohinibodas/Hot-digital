package TestExecute.DryBar.SystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;

public class DB_ST_026_PrivacyPolicy_TermsOfUse {
	
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  
  public void Privacypolicy_and_TermsOfuse() {
		try {
			  drybar.Accept();
			  drybar.verifyingHomePage();
			  //Thread.sleep(5000);
			  drybar.privacy_and_policy(); 
			  drybar.Terms_Of_Use();
			  
		}
		catch (Exception e) {
			e.printStackTrace();
			
			Assert.fail(e.getMessage(), e);
		} 
	}
		@AfterTest
		public void clearBrowser()
		{
		Common.closeAll();

		}
		

		@BeforeTest
		  public void startTest() throws Exception {
			 System.setProperty("configFile", "DryBar\\config.properties");
			  Login.signIn();
			 
			  
		  }
}
