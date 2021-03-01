package TestExecute.DryBar.ModularTC;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;


	public class Loginuser_verificationOf_PDP {
		String datafile = "DryBar//DryBarTestData.xlsx";	
		DryBarHelper drybar=new DryBarHelper(datafile);
		
		//DryBarMobile drybar=new DryBarMobile(datafile);
		
		@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	  public void Loginuser_PDP()throws Exception {
			try {
			drybar.Accept();
		   drybar.clickMyaccount();
		   drybar.loginApplication("AccountDetails");
		   drybar.Search_productname("ProductName");
		  // drybar.Accept();
			  drybar.Verify_PDP();
		 //  drybar.Loginuser_PDP();
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
			 System.setProperty("configFile", "DryBar\\config.properties");
			 Login.signIn();
		 // Login.signIn("chrome","iPhone X");
			 
			 
			  
		  }
	}
