package TestExecute.DryBar.SystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_DB_020_Product_Wishlist {
	
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
	
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void Product_Wishlist()throws Exception {
		try {
		  drybar.Accept();
		  drybar.verifyingHomePage();
	      drybar.navigateMyAccount();
	      drybar.loginApplication("AccountDetails");
	      drybar.clickHairProducts();
		  drybar.SelectShampoos();
		  drybar.Selectproduct();
		  drybar.Verify_PDP();
		  drybar.Add_product_to_Wishlist();
		  drybar.remove_from_wishlist();
		  
		
		  
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
		 //System.setProperty("configFile", "DryBar\\config.properties");
		 Login.signIn();
	 // Login.signIn("chrome","iPhone X");
		 
		 
		  
	  }

}
