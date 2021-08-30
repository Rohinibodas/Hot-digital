package TestExecute.DryBar.ModularTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_MT_DB_004_Verify_search_functionality {
	
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void Verify_search_functionality() throws Exception {

	  try{
		  drybar.Aggree_and_proceed();
		  drybar.clickMyaccount();
		  drybar.loginApplication("AccountDetails");
		  drybar.search_product_fullname("ProductName");
		  drybar.search_product_Fourletters("ProductName");
		  drybar.search_product_invalid("ProductName");
		  drybar.search_product_Threeletters("ProductName");
		  drybar.searchresultspage_navigation("ProductName"); 
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
		// System.setProperty("configFile", "DryBar\\config.properties");
		  Login.signIn();
		 
		  
	  }
}
