package TestExecute.BraunEMEA.BraunUKTUR;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.BraunEMEA.BraunUKHelper;
import TestComponent.BraunEMEASTAGE.BraunEMEAHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_ST_UKTUR_FooterLinksValidation {
	String datafile = "BraunEMEA//BraunUKTestData.xlsx";	
	BraunEMEAHelper BraunUK=new BraunEMEAHelper(datafile);
		
		@Test(priority=1)
		public void FooterLinksValidation() throws Exception {

			try {
				Thread.sleep(6000);
				BraunUK.Acceptcookies();
				BraunUK.closepopup();
				BraunUK.TurkeyStoreSelection();
				/*BraunUK.TurkeynavigateYourhealth();
				BraunUK.TurkeynavigateYourhearthealth();*/
				//BraunUK.TurkeynavigateHealthMagazine();
				
				BraunUK.NavigateYourhealth("LinkText");
				BraunUK.NavigateYourhearthealth("LinkText");
				BraunUK.NavigateHealthMagazine("LinkText");
				
					
			
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
		
		
		public void clearBrowser()
		{
			//Common.closeAll();

		}


	}



