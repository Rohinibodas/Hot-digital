package TestExecute.DryBar.MagentoTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_DB_001_Admin_panel_login {
	
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  
  public void Admin_panel_login() {
		try {
			
			  drybar.verifyingMagentoLoginPage();
			  drybar.Admin_Login("MagentoAccountDetails");
			  drybar.verifyingMagentoHomepage();
			  
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
		 //System.setProperty("configFile", "DryBarMagento\\config.properties");
		  Login.signIn();
		 
		  
	  }

  
}
