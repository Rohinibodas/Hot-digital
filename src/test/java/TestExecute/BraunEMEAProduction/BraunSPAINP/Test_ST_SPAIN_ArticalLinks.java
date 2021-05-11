package TestExecute.BraunEMEAProduction.BraunSPAINP;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.BraunEMEAPRODUCTION.BraunEMEAProducrionHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_ST_SPAIN_ArticalLinks {
			String datafile = "BraunEMEAPRODUCTION//BraunUKPROTestData.xlsx";
			BraunEMEAProducrionHelper BraunUK=new BraunEMEAProducrionHelper(datafile);
			
			
			
			@Test(priority=1)
			public void Articallinks() throws Exception {

				try {
					Thread.sleep(6000);
					BraunUK.Acceptcookies();
					//BraunUK.closepopup();
					//BraunUK.StoreSelection("Spain");
					//BraunUK.SpainStoreSelection();
					/*BraunUK.SpainFacebook();
					BraunUK.SpainInstagram();
					BraunUK.SpainYoutube();*/
					
					BraunUK.Facebook1();
					BraunUK.SpainYoutube();
					//BraunUK.Instagram1();
			        
					
					
					
				}
				catch (Exception e) {
					
					Assert.fail(e.getMessage(), e);
				} 
			}
			
			@BeforeMethod
			@Parameters({"browser"}) 
			  public void startTest() throws Exception {
				System.setProperty("configFile", "BraunEMEAPRODUCTION\\config.properties");
				  Login.signIn("chrome");
				  
			  }

			
		/*@BeforeMethod
			@Parameters({"browser"}) 
			  public void startTest() throws Exception {
				System.setProperty("configFile", "BraunEMEAPRODUCTION\\config.properties");
				  Login.signIn("broswer");
				  
			  }*/
			
			@AfterTest
			public void clearBrowser()
			{
				//Common.closeAll();

			}


		}



