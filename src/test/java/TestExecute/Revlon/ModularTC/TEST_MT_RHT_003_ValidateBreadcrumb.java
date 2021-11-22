package TestExecute.Revlon.ModularTC;

import org.testng.annotations.Test;

import TestComponent.revlon.RevelonHelper;
import TestLib.Common;
import TestLib.Login;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class TEST_MT_RHT_003_ValidateBreadcrumb {
	String datafile = "revlon//RevlonTestData.xlsx";	
	RevelonHelper revelon=new RevelonHelper(datafile);
	
	@Test(priority=1)
	public void ValidateProductDetailsPageTitle() throws Exception {

		try {
			revelon.acceptPrivecy();
			revelon.closepopup();
			revelon.selectioncategory();
			revelon.Productdetails();
			revelon.ProductDetailsBreadCrumbs();
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
		//Common.closeAll();
	}

}
