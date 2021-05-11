package TestExecute.DryBar.SystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;

public class DB_ST_027_Change_product_Quantity {
	
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  
  public void Change_product_Quantity() {
		try {
			  drybar.Accept();
			  drybar.verifyingHomePage();
			  drybar.navigateMyAccount();
			  drybar.loginApplication("AccountDetails");
			  //drybar.Search_productname("ProductName");
			 
			 //drybar.Accept();
			  drybar.clickHairProducts();
			  drybar.SelectShampoos();
			  drybar.Selectproduct();
			  drybar.Verify_PDP();
			 // drybar.selectproduct("ProductName");
			  //drybar.Select_Size();
			  drybar.increaseProductQuantity("4");
			  drybar.clickAddtoBag();
			  
		}
		catch (Exception e) {
			e.printStackTrace();
			
			Assert.fail(e.getMessage(), e);
		} 
	}
		@AfterTest
		public void clearBrowser()
		{
	Common.closeAll();

		}
		

		@BeforeTest
		  public void startTest() throws Exception {
			 System.setProperty("configFile", "DryBar\\config.properties");
			  Login.signIn();
			 
			  
		  }
}

