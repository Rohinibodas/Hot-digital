package TestExecute.DryBar.SystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_DB_030_Product_Back_in_Stock_Subscription_for_GuestUser {

	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  
  public void Product_Back_in_Stock_Subscription_for_GuestUser() {
		try {
			  drybar.Accept();
			  drybar.verifyingHomePage();
			  drybar.Search_outofstock_productname("ProductName");
			  drybar.Verify_OutofStockPDP();
			  drybar.Out_Of_Stock_Subscription("AccountDetails");
			  	
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
