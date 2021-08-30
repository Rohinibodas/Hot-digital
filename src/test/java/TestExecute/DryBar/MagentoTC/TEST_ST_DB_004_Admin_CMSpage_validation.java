package TestExecute.DryBar.MagentoTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_DB_004_Admin_CMSpage_validation{
	
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  
  public void Admin_CMSpage_validation() {
		try {
			
			  drybar.verifyingMagentoLoginPage();
			  drybar.Admin_Login("MagentoAccountDetails");
			  drybar.verifyingMagentoHomepage();
			  drybar.CMSpage();	  
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
