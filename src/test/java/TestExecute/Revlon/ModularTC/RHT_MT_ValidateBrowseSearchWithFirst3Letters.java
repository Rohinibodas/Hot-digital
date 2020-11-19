package TestExecute.Revlon.ModularTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.revlon.RevelonHelper;
import TestLib.Common;
import TestLib.Login;

public class RHT_MT_ValidateBrowseSearchWithFirst3Letters {
	String datafile = "revlon//RevlonTestData.xlsx";	
	RevelonHelper revelon=new RevelonHelper(datafile);
	@Test(priority=1)
	public void ValidateSearchWith3Letters() throws Exception {

		try {
			revelon.acceptPrivecy();
			revelon.searchProductwithmultiple("SearchFullName");
			revelon.searchProductwithmultiple("Search3Letters");
			revelon.searchProductwithmultiple("SearchNumber");
			revelon.searchProductwithmultiple("SearchSpecialCharacter");
		}
		catch (Exception e) {
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	
	/*@BeforeMethod
	@Parameters({"browser"}) 
	  public void startTest(String browser) throws Exception {
		System.setProperty("configFile", "Revelon\\config.properties");
		  Login.signIn(browser);
		  
	  }*/
	
	@BeforeMethod
	@Parameters({"browser"})  
	  public void startTest() throws Exception {
		System.setProperty("configFile", "Revelon\\config.properties");
		  Login.signIn("chrome");
		  
	  }
	
	@AfterTest
	public void clearBrowser()
	{
		//Common.closeAll();
	}
}
