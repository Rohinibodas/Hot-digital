package TestExecute.DryBar.ModularTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;

public class DB_MT_004_Searchproduct {
	
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void Search_function() throws Exception {

	  try{
		  drybar.Aggree_and_proceed();
		  drybar.clickMyaccount();
		  drybar.loginApplication("AccountDetails");
		  drybar.search_product_fullname("ProductName");
		  drybar.search_product_Fourletters("ProductName");
		 // drybar.search_product_Dublicate("ProductName");
		  drybar.search_product_invalid("ProductName");
		 // drybar.verify_viewproduct_button("ProductName");
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
		 System.setProperty("configFile", "DryBar\\config.properties");
		  Login.signIn();
		 
		  
	  }
}
