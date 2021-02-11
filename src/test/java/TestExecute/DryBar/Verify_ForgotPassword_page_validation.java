package TestExecute.DryBar;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Automation_properties;
import TestLib.Common;
import TestLib.Login;

public class Verify_ForgotPassword_page_validation {
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);

  @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)

	
	

public void verify_ForgotPassword_page_validation() throws Exception {
		try{
	  drybar.navigateMyAccount();
	  drybar.forgetpasswordPageValidation("123");
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