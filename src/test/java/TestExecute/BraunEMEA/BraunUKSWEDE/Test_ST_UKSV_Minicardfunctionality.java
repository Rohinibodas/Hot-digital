package TestExecute.BraunEMEA.BraunUKSWEDE;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.BraunEMEA.BraunUKHelper;
import TestComponent.BraunEMEASTAGE.BraunEMEAHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_ST_UKSV_Minicardfunctionality {
		
	String datafile = "BraunEMEA//BraunUKTestData.xlsx";	
	BraunEMEAHelper BraunUK=new BraunEMEAHelper(datafile);
		
		
		@Test(priority=1)
		public void MinicartFunctionality() throws Exception {

			try {
				BraunUK.Acceptcookies();
				BraunUK.StoreSelection("Sweden");
				BraunUK.UKSVproductname("productName");
				BraunUK.Addtocart();
	            BraunUK.SDnavigateMinicart();
			}
			catch (Exception e) {
				
				Assert.fail(e.getMessage(), e);
			} 
		}
		
	/*	@BeforeMethod
		@Parameters({"browser"}) 
		  public void startTest() throws Exception {
			System.setProperty("configFile", "BraunEMEA\\config.properties");
			  Login.signIn("chrome");
			  
		  }*/
		
	@BeforeMethod
		@Parameters({"browser"}) 
		  public void startTest(String browser) throws Exception {
			System.setProperty("configFile", "BraunEMEA\\config.properties");
			  Login.signIn(browser);
			  }
		
		@AfterTest
		public void clearBrowser()
		{
			Common.closeAll();

		}
	}


	


