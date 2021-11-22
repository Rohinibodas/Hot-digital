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

public class TEST_HT_RD_055_Share_WishList_Product {
	
	String datafile = "Redesign_Hottools//Redesign_HottoolsTestData.xlsx";	
	Redesign_HottoolsHelper Redesign_Hottools=new Redesign_HottoolsHelper(datafile);
	
	
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void Product_Wishlist() throws Exception {
		try{
			
			Redesign_Hottools.agreeCookiesbanner();
			Redesign_Hottools.NewsletterPopUp();
			Redesign_Hottools.verifyingHomePage();
			Redesign_Hottools.signin("RetailCustomerAccountDetails");
			Redesign_Hottools.ClearMiniCart_Bag();
				  Redesign_Hottools.clickHair_Tools();
				  Redesign_Hottools.Selectproduct(); 
	              Redesign_Hottools.Verify_PDP();
	              Redesign_Hottools.Add_product_to_Wishlist();
	              Redesign_Hottools.Share_WishList("WishListEmail");
	          
				  
		  
	  
 }
	catch (Exception e) {
		e.printStackTrace();
		
		Assert.fail(e.getMessage(), e);
	} 
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
		//System.setProperty("configFile", "Redesign_Hottools\\Config_Redesign_Hottools_Staging.properties");
		System.setProperty("configFile", "Redesign_Hottools\\Config_Redesign_Hottools_Production.properties");
		  Login.signIn("chrome"); 
	}*/
  
  @AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}

}