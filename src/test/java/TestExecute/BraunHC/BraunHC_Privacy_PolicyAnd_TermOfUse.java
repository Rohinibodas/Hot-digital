package TestExecute.BraunHC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.BraunHC.BraunHCHelper;
import TestLib.Common;
import TestLib.Login;

public class BraunHC_Privacy_PolicyAnd_TermOfUse {

	String datafile = "BraunHC//BraunHCTestData.xlsx";	
	BraunHCHelper BraunHC=new BraunHCHelper(datafile);
	
	@Test(priority=1)
	public void Privacy_PolicyAnd_TermOfUse() throws Exception {

		try {
			//BraunHC.AGREEPROCEED();
		     BraunHC.privacy();
			BraunHC.Terms_OF_Use();
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


