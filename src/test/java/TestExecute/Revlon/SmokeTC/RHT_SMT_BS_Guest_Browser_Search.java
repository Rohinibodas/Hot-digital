package TestExecute.Revlon.SmokeTC;

import org.testng.annotations.Test;

import TestComponent.revlon.RevelonHelper;
import TestLib.Common;
import TestLib.Login;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class RHT_SMT_BS_Guest_Browser_Search {
	String datafile = "revlon//RevlonTestData.xlsx";	
	RevelonHelper revelon=new RevelonHelper(datafile);

	@Test(priority=1)
	public void searchProduct() throws Exception {

		try {
			revelon.acceptPrivecy();
			revelon.searchProduct("productName");
			revelon.zerosearchProduct("Zero_Search");
		}
		catch (Exception e) {
			Assert.fail(e.getMessage(), e);
		} 
	}
	


	@BeforeMethod
	@Parameters({"browser"}) 
	  public void startTest(String browser) throws Exception {
		System.setProperty("configFile", "Revelon\\config.properties");
		  Login.signIn(browser);
		  
	  }

	@AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}

}