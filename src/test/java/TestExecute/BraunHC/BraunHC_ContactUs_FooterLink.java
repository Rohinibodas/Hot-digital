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

public class BraunHC_ContactUs_FooterLink {
	String datafile = "BraunHC//BraunHCTestData.xlsx";	
	BraunHCHelper BraunHC=new BraunHCHelper(datafile);
	
	@Test(priority=1)
	public void ContactUs_FooterLink() throws Exception {

		try {
			//BraunHC.AGREEPROCEED();
			//BraunHC.validateNavigateContactUs("");
		    BraunHC.validateNavigateContactUS_footer_links("ContactUs");
			
			
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
