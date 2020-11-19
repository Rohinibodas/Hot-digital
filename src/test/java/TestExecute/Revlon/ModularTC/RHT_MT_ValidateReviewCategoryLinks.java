package TestExecute.Revlon.ModularTC;

import org.testng.annotations.Test;

import TestComponent.revlon.RevelonHelper;
import TestLib.Login;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class RHT_MT_ValidateReviewCategoryLinks {
	String datafile = "revlon//RevlonTestData.xlsx";	
	RevelonHelper revelon=new RevelonHelper(datafile);
	
	@Test(priority=1)
	public void ValidateProductDetailsPageTitle() throws Exception {

		try {
			revelon.acceptPrivecy();
			revelon.selectioncategory();
			revelon.Productdetails();
			revelon.ProductReview("ProductReview");
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
