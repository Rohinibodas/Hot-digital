package TestExecute.DryBar.ModularTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_MT_DB_002_OutofStock_Subscription_for_RegisterUser {
	
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  
  public void OutofStock_Subscription_for_RegisterUser() {
		try {
			  drybar.Accept();
			  drybar.verifyingHomePage();
			  drybar.navigateMyAccount();
			  drybar.loginApplication("AccountDetails");
			  drybar.Search_outofstock_productname("ProductName");
			  drybar.Verify_OutofStockPDP();
			  drybar.RegisterUser_Out_Of_Stock_Subscription();
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
		 

}

}
