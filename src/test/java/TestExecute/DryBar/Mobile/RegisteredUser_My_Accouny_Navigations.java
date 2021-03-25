package TestExecute.DryBar.Mobile;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestComponent.DryBar.DryBarMobile;
import TestLib.Common;
import TestLib.Login;

public class RegisteredUser_My_Accouny_Navigations {
	
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarMobile drybar=new DryBarMobile(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void Validate_My_Account_Navigation () throws Exception {

	  try{
		  drybar.Aggree_and_proceed();
		  drybar.clickMyaccount();
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
		//Common.closeAll();

	}
  @BeforeMethod
  public void startTest() throws Exception {
	 System.setProperty("configFile", "DryBar\\config.properties");
	 Login.signIn("chrome","iPhone X");
 }
/*@BeforeTest
@Parameters({"device"})  
  public void startTest(String Device) throws Exception {
	System.setProperty("configFile", "DryBar\\config.properties");
	Login.signIn("chrome",Device);
  }*/

}
