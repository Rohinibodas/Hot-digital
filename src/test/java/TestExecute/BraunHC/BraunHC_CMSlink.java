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
			
			//BraunHC.AGREEPROCEED();
			BraunHC.validateNavigatProductSupport("");
			BraunHC.validateNavigateFAQ("");
			BraunHC.validateNavigateWarrantyRegistration("");
			BraunHC.validateNavigateContactUs("");
			BraunHC.validateNavigateThermometers("");
			BraunHC.validateNavigateNasalAspirator("");
			BraunHC.validateNavigateBloodPressureMonitor("");
			BraunHC.validateNavigatePulseOximeter("");
			BraunHC.validateNavigateAccessories("");
			BraunHC.ourcompanyFooterlink();
			BraunHC.ourhistoryFooterlink();
			BraunHC.blogFooterlink();
			BraunHC.careersFooterlink();
			BraunHC.vicksFooterlink();
			BraunHC.honeywellFooterlink();
			BraunHC.PURFooterlink() ;
			
		}
		catch (Exception e) {
			Assert.fail(e.getMessage(), e);
		} 
	}

	/*@BeforeMethod
	@Parameters({"browser"})  
	  public void startTest1() throws Exception {
		System.setProperty("configFile", "BraunHC\\config.properties");
		  Login.signIn("chrome");
		  
	  }*/
@BeforeTest
public void startTest() throws Exception {
	System.setProperty("configFile", "BraunHC\\config.properties");
Login.signIn();
}
	  
	
	@AfterTest
	public void clearBrowser()
	{
		
		Common.closeAll();

	}
}
