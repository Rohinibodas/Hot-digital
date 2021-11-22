package TestExecute.Redesign_Hottools;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
//importTestComponent.Redesign_Hottools.Redesign_HottoolsHelper;
import TestComponent.Redesign_Hottools.Redesign_HottoolsHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_HT_RD_012_Validate_My_Account_page_LeftNavigation {
	
	String datafile = "Redesign_Hottools//Redesign_HottoolsTestData.xlsx";	
	Redesign_HottoolsHelper Redesign_Hottools=new Redesign_HottoolsHelper(datafile);
	
	
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void Validate_My_Account_page_LeftNavigation() throws Exception {
		try{
			 Redesign_Hottools.agreeCookiesbanner();
			// Redesign_Hottools.NewsletterPopUp();
			  Redesign_Hottools.verifyingHomePage();
			  Redesign_Hottools.navigateMyAccount();
			  Redesign_Hottools.loginApplication("RetailCustomerAccountDetails");
			  Redesign_Hottools.My_Orders();
			  Redesign_Hottools.Wishlist();
			  Redesign_Hottools.AddressBook();
			  Redesign_Hottools.AccountInformation();
			  Redesign_Hottools.Communication_Preferences(); 
			  Redesign_Hottools.Notifications();
			  Redesign_Hottools.pro_login();
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
	
  @BeforeMethod
	@Parameters({"browser"}) 
	  public void startTest(String browser) throws Exception {
		//System.setProperty("configFile", "Hottools\\Config_Hottools_Staging.properties");
		//System.setProperty("configFile", "Hottools\\Config_Hottools_Production.properties");
		  Login.signIn(browser);
		  
	  }
	
	/*@BeforeMethod
	//@Parameters({"browser"})  
	  public void startTest() throws Exception {
		System.setProperty("configFile", "Redesign_Hottools\\Config_Redesign_Hottools_Staging.properties");
		//System.setProperty("configFile", "Redesign_Hottools\\Config_Redesign_Hottools_Production.properties");
		  Login.signIn("chrome"); 
	}*/
	
		  

}
