package TestExecute.Honeywell;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Honeywell.Honeywellhelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_HW_MT_027_Contact_Us_form_validation {
	String datafile = "Honeywell\\HoneywellTestData.xlsx";	
	Honeywellhelper honeyWell=new Honeywellhelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	
	

  public void contact_Us_form_validation() {
		try {
			honeyWell.verifyingHomePage();
			honeyWell.Contact_Us("ContactUs");
			
  }
catch (Exception e) {
			
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
		 System.setProperty("configFile", "Honeywell\\config.properties");
		  Login.signIn();
		  honeyWell.agree_proceed();
		  
	  }
}
