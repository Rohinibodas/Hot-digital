package TestExecute.BraunEMEA.BraunFR;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.BraunEMEA.BraunUKHelper;
import TestLib.Common;
import TestLib.Login;

public class AccounLogin {
		
		String datafile = "BraunUK//BraunUKTestData.xlsx";	
		BraunUKHelper BraunUK=new BraunUKHelper(datafile);
		
		
		@Test(priority=1)
		public void AccountLogin() throws Exception {

			try {
				Thread.sleep(6000);
				BraunUK.Acceptcookies();
				BraunUK.closepopup();
				BraunUK.FranceStoreSelection();
				BraunUK.FRsingin("AccountDetails");
					
			
			}
			catch (Exception e) {
				
				Assert.fail(e.getMessage(), e);
			} 
		}
		
	@BeforeMethod
		@Parameters({"browser"}) 
		  public void startTest() throws Exception {
			System.setProperty("configFile", "BraunUK\\config.properties");
			  Login.signIn("chrome");
			  
		  }
		
		
		
		
		/*@BeforeMethod
		@Parameters({"browser"}) 
		  public void startTest(String browser) throws Exception {
			System.setProperty("configFile", "BraunUK\\config.properties");
			  Login.signIn(browser);
			  }*/
		
		@AfterTest
		public void clearBrowser()
		{
			Common.closeAll();

		}

	}

