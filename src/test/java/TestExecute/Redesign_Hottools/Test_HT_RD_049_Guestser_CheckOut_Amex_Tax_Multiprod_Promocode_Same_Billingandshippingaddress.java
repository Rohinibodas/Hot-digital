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

public class Test_HT_RD_049_Guestser_CheckOut_Amex_Tax_Multiprod_Promocode_Same_Billingandshippingaddress {
	
	String datafile = "Redesign_Hottools//Redesign_HottoolsTestData.xlsx";	
	Redesign_HottoolsHelper Redesign_Hottools=new Redesign_HottoolsHelper(datafile);
	
	
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void GU_Amex_Tax_Multiprod_Diff_Billship() throws Exception {
		try{
			
			

			 Redesign_Hottools.agreeCookiesbanner();
			 Redesign_Hottools.NewsletterPopUp();
			  Redesign_Hottools.verifyingHomePage();
			  Redesign_Hottools.ClearMiniCart_Bag();
			  Redesign_Hottools.clickHair_Tools();
			  Redesign_Hottools.Selectproduct(); 
             Redesign_Hottools.Verify_PDP();
             Redesign_Hottools.increaseProductQuantity("4");
			  Redesign_Hottools.clickAddtoBag();
			  Redesign_Hottools.clickminiCartButton();
			  Redesign_Hottools.Search_productname("ProductName");
			  Redesign_Hottools.View_Product();
			  Redesign_Hottools.clickAddtoBag();
			  Redesign_Hottools.clickminiCartButton();
			  Redesign_Hottools.clickCheckoutButton();
			  Redesign_Hottools.click_GuestCheckOut();
			  Redesign_Hottools.guestShippingAddress("TaxShippingAddress");
			  //Redesign_Hottools.Click_PaymetricPaymentMethod();
			  //Redesign_Hottools.Edit_BillingAddress_PaymetricPaymentMethod("BiillingAddress");
			  Redesign_Hottools.Verify_tax();
			  Redesign_Hottools.couponCodeHT("promocode");
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

/*		@BeforeMethod
		//@Parameters({"browser"})  
		  public void startTest() throws Exception {
			System.setProperty("configFile", "Redesign_Hottools\\Config_Redesign_Hottools_Staging.properties");
			//System.setProperty("configFile", "Redesign_Hottools\\Config_Redesign_Hottools_Production.properties");
			  Login.signIn("chrome"); 
		}*/
  
  
  @AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}
	
}