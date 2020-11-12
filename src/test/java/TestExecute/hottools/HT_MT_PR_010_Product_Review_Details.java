package TestExecute.hottools;

import org.testng.annotations.Test;

import TestComponent.Hottools.HottoolsHelpr;
import TestLib.Common;
import TestLib.Login;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class HT_MT_PR_010_Product_Review_Details {
	String datafile = "Hottools//HottoolsTestData.xlsx";	
	HottoolsHelpr Hottools=new HottoolsHelpr(datafile);


	@Test(priority=1)
	public void SignIn(){

		try{
			Hottools.singin("RetailCustomerAccountDetails");
			
		}
		catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}

	@Test(dependsOnMethods="SignIn")
	public void ProductReviewInput(){

		try{
			Hottools.searchingProducts("productName");
			Hottools.Productselection("productName");
			Hottools.ProductReview("ProductReview");
		}
		catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}
	
	@Test(dependsOnMethods="ProductReviewInput")
	public void NavigateProductReviewDetails(){

		try{
			Hottools.navigateProductReviewDetails("productName");
		}
		catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}




	@BeforeMethod
	@Parameters({"browser"})  

	public void startTest() throws Exception {
		System.setProperty("configFile", "Hottools\\config.properties");
		Login.signIn();

	}

	@AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}

}
