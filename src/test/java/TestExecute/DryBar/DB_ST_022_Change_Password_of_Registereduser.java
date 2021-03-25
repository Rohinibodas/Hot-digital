package TestExecute.DryBar;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;

public class DB_ST_022_Change_Password_of_Registereduser {
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
	
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void Chage_Password()throws Exception {
		try {
		drybar.Accept();
	    drybar.navigateMyAccount();
	   drybar.loginApplication("AccountDetails");
	  drybar.AccountInformation();
	 drybar.change_Password("AccountDetails");
	  drybar.loginApplication("AccountDetails");
		
		  
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
