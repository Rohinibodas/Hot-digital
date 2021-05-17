package TestExecute.BraunEMEA.BraunCanadafr;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.BraunEMEASTAGE.BraunEMEAHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_ST_CAFR_Footerlinks_validation {
	String datafile = "BraunEMEA//BraunUKTestData.xlsx";	
	BraunEMEAHelper BraunUK=new BraunEMEAHelper(datafile);
		
		
		@Test(priority=1)
		public void FooterLinks() throws Exception {

			try {
				Thread.sleep(6000);
				BraunUK.Acceptcookies();
				BraunUK.NavigateCanadaYourhealth("LinkText");
				BraunUK.CanadaYourhealth("LinkText");
				
				
					
			
			}
			catch (Exception e) {
				e.printStackTrace();
				
				Assert.fail(e.getMessage(), e);
			} 
		}
		
	  @BeforeMethod
		@Parameters({"browser"}) 
		  public void startTest() throws Exception {
			System.setProperty("configFile", "BraunEMEA\\config.properties");
			  Login.signIn("chrome");
			  
		  }
		
    /* @BeforeMethod
		@Parameters({"browser"}) 
		  public void startTest(String browser) throws Exception {
			System.setProperty("configFile", "BraunEMEA\\config.properties");
			  Login.signIn(browser);
			  }*/
		
		
		@AfterTest
		public void clearBrowser()
		{
			//Common.closeAll();

		}
	}



