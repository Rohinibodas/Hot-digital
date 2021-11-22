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

public class TEST_HT_RD_045_RegisteredUser_Checkout_AmexCC_Tax_with_multiple_products_Same_Billing_and_shipping {
	
	String datafile = "Redesign_Hottools//Redesign_HottoolsTestData.xlsx";	
	Redesign_HottoolsHelper Redesign_Hottools=new Redesign_HottoolsHelper(datafile);
	
	
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void RegisteredUser_Checkout_AmexCC_Tax_with_multiple_products_Same_Billing_and_shipping() throws Exception {
		try{
			
			
			 Redesign_Hottools.agreeCookiesbanner();
			 Redesign_Hottools.NewsletterPopUp();
			  Redesign_Hottools.verifyingHomePage();
			  Redesign_Hottools.signin("RetailCustomerAccountDetails");
			  Redesign_Hottools.ClearMiniCart_Bag();
			  Redesign_Hottools.clickHair_Tools();
			  Redesign_Hottools.Selectproduct(); 
              Redesign_Hottools.Verify_PDP();
			  Redesign_Hottools.clickAddtoBag();
			  Redesign_Hottools.clickminiCartButton();
			  Redesign_Hottools.Search_productname("ProductName");
			  Redesign_Hottools.View_Product();
			  Redesign_Hottools.clickAddtoBag();
			  Redesign_Hottools.clickminiCartButton();
			  Redesign_Hottools.clickCheckoutButton();
			  Redesign_Hottools.addDeliveryAddress_registerUser("TaxShippingAddress");
			  Redesign_Hottools.Verify_tax();
			  Redesign_Hottools.click_Same_Billing_Address();
			  Redesign_Hottools.creditCard_payment("ccamex");  
			  Redesign_Hottools.order_Success();
			
			
	  
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
	
/*	@BeforeMethod
	//@Parameters({"browser"})  
	  public void startTest() throws Exception {
		System.setProperty("configFile", "Redesign_Hottools\\Config_Redesign_Hottools_Staging.properties");
		//System.setProperty("configFile", "Redesign_Hottools\\Config_Redesign_Hottools_Production.properties");
		  Login.signIn("chrome"); 
	}
  */
  @AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}
	
	
}
