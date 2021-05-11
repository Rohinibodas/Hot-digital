package TestExecute.BraunEMEAProduction.BraunFRP;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.BraunEMEAPRODUCTION.BraunEMEAProducrionHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_ST_MyOrders {
	//String datafile = "BraunEMEA//BraunUKTestData.xlsx";
			String datafile = "BraunEMEAPRODUCTION//BraunUKPROTestData.xlsx";
			BraunEMEAProducrionHelper BraunUK=new BraunEMEAProducrionHelper(datafile);
			
			
			
			@Test(priority=1)
			public void Myorders() throws Exception {

				try {
					Thread.sleep(6000);
					BraunUK.Acceptcookies();
					//BraunUK.closepopup();
					//BraunUK.Countryselection();
					  //BraunUK.FranceStoreSelection();
					BraunUK.FRsingin("AccountDetails");
					//BraunUK.FRMyaccountInformation();
					BraunUK.FRMyaccountInformation("FRMyorders");
					//BraunUK.Revieworder();
					
					
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
				  
			  }
*/
			
			@BeforeMethod
			@Parameters({"browser"}) 
			  public void startTest() throws Exception {
				System.setProperty("configFile", "BraunEMEAPRODUCTION\\config.properties");
				  Login.signIn("broswer");
				  
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
