package TestExecute.RevlonUK.SmokeTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.RevlonUk.RevlonUKHelper;
import TestLib.Common;
import TestLib.Login;

public class RUK_ST_RegisterUserPDPamazonhatch {

	String datafile = "revlonUK//RevlonUKTestData.xlsx";	
	RevlonUKHelper revlon=new RevlonUKHelper(datafile);
	
	
	@Test(priority=1)
	public void RegistereduserPDPAmazonHatch() throws Exception {

		try {
			revlon.loginRevlonUK("AccountDetails");
			revlon.SearchProduct("productName");
			revlon.Productselection();
			revlon.AmazonHatchproduct();
			
		}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	@BeforeMethod
	@Parameters({"browser"}) 
	  public void startTest(String browser) throws Exception {
		System.setProperty("configFile", "RevlonUK\\config.properties");
		  Login.signIn(browser);
		  
	  }/*
	
	@BeforeMethod
	@Parameters({"browser"})  
	  public void startTest() throws Exception {
		System.setProperty("configFile", "RevlonUK\\config.properties");
		  Login.signIn("chrome");
		  
	  }*/
	
	@AfterTest
	public void clearBrowser()
	{
		Common.closeAll();
	}

}
