package TestExecute.DryBar.SystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;

public class DB_ST_038_GuestUser_BrowseSearch_Checkout {
	
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  
  public void GuestUser_SearchCheckout_CreditCard() {
		try {
			  drybar.verifyingHomePage();
			  drybar.Search_productname("ProductName");
			  drybar.Accept();
			  drybar.Click_View_Product();
			  drybar.Accept();
			 // drybar.Verify_PDP();
			  drybar.increaseProductQuantity("2");
		      drybar.clickAddtoBag();
		     drybar.clickminiCartButton();
		    drybar.clickCheckoutButton();
		    drybar.click_GuestCheckOut();
		    drybar.guestShippingAddress("ShippingAddress");
		    drybar.Click_PaymetricPaymentMethod();
		    drybar.Edit_BillingAddress_PaymetricPaymentMethod("BiillingAddress");
		    drybar.creditCard_payment("CCVisa");
		    drybar.order_Success();
			
			
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


