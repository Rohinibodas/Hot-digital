package TestExecute.DryBar.ModularTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;

public class DB_MT_002_PDP_For_OutofStock {
	
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  
  public void GuestUser_PDP() {
		try {
			  drybar.Accept();
			  drybar.verifyingHomePage();
			  drybar.Search_outofstock_productname("ProductName");
			  drybar.Verify_OutofStockPDP();
			  	
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
