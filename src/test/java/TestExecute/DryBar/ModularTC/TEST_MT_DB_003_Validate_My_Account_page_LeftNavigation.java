package TestExecute.DryBar.ModularTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_MT_DB_003_Validate_My_Account_page_LeftNavigation {
	
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void Validate_My_Account_page_LeftNavigation() throws Exception {

	  try{
		  drybar.Accept();
		  drybar.verifyingHomePage();
		  drybar.navigateMyAccount();
		  drybar.loginApplication("AccountDetails");
		  drybar.My_Orders();
		  drybar.Wishlist();
		  drybar.AddressBook();
		  drybar.AccountInformation();
		  drybar.PaymentMethods();
		  drybar.Communication_Preferences(); 
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
