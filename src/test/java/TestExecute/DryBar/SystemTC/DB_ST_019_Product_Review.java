package TestExecute.DryBar.SystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;

public class DB_ST_019_Product_Review {
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
	
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void Loginuser_PDP()throws Exception {
		try {
		drybar.Accept();
	    drybar.navigateMyAccount();
	   drybar.loginApplication("AccountDetails");
	   drybar.Search_productname("ProductName");
		  drybar.Verify_PDP();
		  drybar.Product_Review("ProductReview");
		  drybar.profile("AccountDetails");
		  
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
		 Login.signIn("chrome");
	 // Login.signIn("chrome","iPhone X");
		 
		 
		  
	  }

}
