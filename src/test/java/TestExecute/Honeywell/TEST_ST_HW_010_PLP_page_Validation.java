package TestExecute.Honeywell;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Honeywell.Honeywellhelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_HW_010_PLP_page_Validation {
	String datafile = "Honeywell\\HoneywellTestData.xlsx";	
	Honeywellhelper honeyWell=new Honeywellhelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  
  public void PLP_Page() throws Exception {
		
		honeyWell.verifyingHomePage();
		honeyWell.loginHoneywell("AccountDetails");
		honeyWell.click_fans();
		honeyWell.filtertype();
		honeyWell.pricefilter();
//		honeyWell.searchProduct("ProductName");
		
		honeyWell.sortby("sort");
		
		
  }
	
	
	@AfterTest
	public void clearBrowser()
	{
	Common.closeAll();

	}
	
	@BeforeTest
	  public void startTest() throws Exception {
		// System.setProperty("configFile", "Honeywell\\config.properties");
		  Login.signIn();
		 
		  
	  }}

