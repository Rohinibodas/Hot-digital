package TestExecute.DryBar.Mobile;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestComponent.DryBar.DryBarMobile;
import TestLib.Common;
import TestLib.Login;


	public class Registereduser_verificationOf_PDP {
		String datafile = "DryBar//DryBarTestData.xlsx";	
		DryBarMobile drybar=new DryBarMobile(datafile);
		
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
			//Common.closeAll();

		}

		@BeforeMethod
		  public void startTest() throws Exception {
			 System.setProperty("configFile", "DryBar\\config.properties");
			 Login.signIn("chrome","iPad");
		 }
		/*@BeforeTest
		@Parameters({"device"})  
		  public void startTest(String Device) throws Exception {
			System.setProperty("configFile", "DryBar\\config.properties");
			Login.signIn("chrome",Device);
		  }*/

	  	
	
	}
