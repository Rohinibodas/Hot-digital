package TestExecute.BraunEMEA.BraunDE;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.BraunEMEA.BraunUKHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_ST_BraunUK_Store_category {
		
	String datafile = "BraunEMEA//BraunUKTestData.xlsx";	
	BraunUKHelper BraunUK=new BraunUKHelper(datafile);
		
		
		@Test(priority=1)
		public void Store_category() throws Exception {

			try {
				Thread.sleep(6000);
				BraunUK.Acceptcookies();
				BraunUK.closepopup();
				//BraunUK.Storeselection();
				BraunUK.GermanStoreSelection();
				//BraunUK.FranceStoreSelection();
				
					
			
			}
			catch (Exception e) {
				
				Assert.fail(e.getMessage(), e);
			} 
		}
		
		@BeforeMethod
		@Parameters({"browser"}) 
		  public void startTest() throws Exception {
			System.setProperty("configFile", "BraunEMEA\\config.properties");
			  Login.signIn("chrome");
			  
		  }
		
		/*@BeforeMethod
		@Parameters({"browser"}) 
		  public void startTest(String browser) throws Exception {
			System.setProperty("configFile", "BraunEMEA\\config.properties");
			  Login.signIn(browser);
			  }*/
		
		@AfterTest
		public void clearBrowser()
		{
			Common.closeAll();
		}

	}



