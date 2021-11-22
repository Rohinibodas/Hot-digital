package TestExecute.Revlon.ModularTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.revlon.RevelonHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_MT_RHT_011_ValidateSearchFilterAsGuest {
	String datafile = "revlon//RevlonTestData.xlsx";	
	RevelonHelper revelon=new RevelonHelper(datafile);
	@Test(priority=1)
	public void ValidateProductListingPageFilter() throws Exception {

		try {
			revelon.acceptPrivecy();
			revelon.closepopup();
			revelon.searchProduct("productName");
			revelon.Searchresultsfilter();
			
		}
		catch (Exception e) {
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	
	@BeforeMethod
	  public void startTest() throws Exception {
		// System.setProperty("configFile", "RevlonUS\\Config_RevlonUS_Staging.properties");
		  Login.signIn();
}
	
	
	@AfterTest
	public void clearBrowser()
	{
		Common.closeAll();
	}
}
