package TestExecute.BraunEMEAProduction.BraunNORP;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.BraunEMEAPRODUCTION.BraunEMEAProducrionHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_ST_NOR_FooterLinksValidation {
			String datafile = "BraunEMEAPRODUCTION//BraunUKPROTestData.xlsx";
			BraunEMEAProducrionHelper BraunUK=new BraunEMEAProducrionHelper(datafile);
			
			@Test(priority=1)
			public void FooterLinks() throws Exception {

				try {
					Thread.sleep(6000);
					BraunUK.Acceptcookies();
					//BraunUK.closepopup();
					//BraunUK.StoreSelection("Norway");
					/*BraunUK.NorwaynavigateYourhealth();
					BraunUK.NorwaynavigateYourhearthealth();*/
					/*BraunUK.NorwaynavigateHealthMagazine();*/
					BraunUK.NorwayNavigateYourhealth("LinkText");
					BraunUK.NorwayNavigateYourhearthealth("LinkText");
							
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





