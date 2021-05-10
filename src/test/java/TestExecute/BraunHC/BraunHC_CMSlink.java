package TestExecute.BraunHC;

import org.testng.annotations.Test;

import TestComponent.BraunHC.BraunHCHelper;
import TestLib.Common;
import TestLib.Login;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class BraunHC_CMSlink {
	String datafile = "BraunHC//BraunHCTestData.xlsx";	
	BraunHCHelper BraunHC=new BraunHCHelper(datafile);
	
	@Test(priority=1)
	public void CMS_links() throws Exception {

		try {
			
			BraunHC.AGREEPROCEED();
			BraunHC.validateNavigateFAQ("");
			BraunHC.validateNavigateContactUs("");
			BraunHC.validateNavigateThermometers("");
			BraunHC.validateNavigateBloodPressureMonitor("");
			BraunHC.validateNavigatePulseOximeter("");
			//BraunHC.validateNavigateAccessories("");
			//BraunHC.validateNavogateOurHistory("");
			//BraunHC.validateNavogateBlog("");
			//BraunHC.validateNavogateOurCompany("");
			//BraunHC.validateNavogateCareers("");


			
			
		}
		catch (Exception e) {
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	@BeforeMethod
	@Parameters({"browser"}) 
	  public void startTest(String browser) throws Exception {
		System.setProperty("configFile", "BraunHC\\config.properties");
		  Login.signIn(browser);
		  
	  }
	
/*	@BeforeMethod
	@Parameters({"browser"})  
	  public void startTest() throws Exception {
		System.setProperty("configFile", "BraunHC\\config.properties");
		  Login.signIn("chrome");
		  
	  }*/
	
	@AfterTest
	public void clearBrowser()
	{
		
		Common.closeAll();

	}

}
