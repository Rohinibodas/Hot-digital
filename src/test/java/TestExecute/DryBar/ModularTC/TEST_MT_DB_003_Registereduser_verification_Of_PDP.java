package TestExecute.DryBar.ModularTC;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;


	public class TEST_MT_DB_003_Registereduser_verification_Of_PDP {
		String datafile = "DryBar//DryBarTestData.xlsx";	
		DryBarHelper drybar=new DryBarHelper(datafile);
		
		@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	  public void Registereduser_verification_Of_PDP()throws Exception {
			try {
			drybar.Accept();
		   drybar.clickMyaccount();
		   drybar.loginApplication("AccountDetails");
		   drybar.Search_productname("ProductName");
			  drybar.Verify_PDP();
		
			}
			 catch (Exception e) {
					e.printStackTrace();
					
					Assert.fail(e.getMessage(), e);
			 }
	  }
	  
		//ul[contains(@class,'header links')]/li[2]/a
	  
	  @AfterTest
		public void clearBrowser()
		{
			Common.closeAll();

		}
		
		@BeforeTest
		  public void startTest() throws Exception {
			// System.setProperty("configFile", "DryBar\\config.properties");
			 Login.signIn();
		 // Login.signIn("chrome","iPhone X");
			 
			 
			  
		  }
	}
