package TestExecute.BraunEMEAProduction.BraunSPAINP;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.BraunEMEAPRODUCTION.BraunEMEAProducrionHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_ST_SPAIN_Footerlinks {
		String datafile = "BraunEMEAPRODUCTION//BraunUKPROTestData.xlsx";
		BraunEMEAProducrionHelper BraunUK=new BraunEMEAProducrionHelper(datafile);
		
		@Test(priority=1)
		public void FooterLinks() throws Exception {

			try {
				Thread.sleep(6000);
				BraunUK.Acceptcookies();
				//BraunUK.closepopup();
				//BraunUK.StoreSelection("Spain");
				/*BraunUK.SpainnavigateYourhealth();
				BraunUK.SpainnavigateYourhearthealth();*/
				BraunUK.SpainNavigateYourhealth("LinkText");
				BraunUK.SpainNavigateYourhearthealth("LinkText");
				
				
			}
			catch (Exception e) {
				
				Assert.fail(e.getMessage(), e);
			} 
		}
		
		/*@BeforeMethod
		@Parameters({"browser"}) 
		  public void startTest() throws Exception {
			System.setProperty("configFile", "BraunEMEAPRODUCTION\\config.properties");
			  Login.signIn("chrome");
			  
		  }*/

		
		@BeforeMethod
		@Parameters({"browser"}) 
		  public void startTest() throws Exception {
			System.setProperty("configFile", "BraunEMEAPRODUCTION\\config.properties");
			  Login.signIn("broswer");
			  
		  }
		
		@AfterTest
		public void clearBrowser()
		{
			Common.closeAll();

		}


	}




