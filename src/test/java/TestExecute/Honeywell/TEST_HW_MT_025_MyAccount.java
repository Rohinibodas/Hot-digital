package TestExecute.Honeywell;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Honeywell.Honeywellhelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_HW_MT_025_MyAccount {
		String datafile = "Honeywell\\HoneywellTestData.xlsx";	
		Honeywellhelper honeyWell=new Honeywellhelper(datafile);
		@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	  
	  public void Myaccount() throws Exception {
			
			honeyWell.verifyingHomePage();
			//honeyWell.accept();
			honeyWell.myAccount("AccountDetails");
			honeyWell.profile("AccountDetails");
			honeyWell.myOrders();
			honeyWell.myAddress();
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
			  
			  
		  }
	
}

