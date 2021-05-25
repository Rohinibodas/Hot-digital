package TestExecute.DryBar.SystemTC;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestComponent.DryBar.DryBarMobile;
import TestLib.Common;
import TestLib.Login;

public class DB_ST_003_Guest_Checkout_CreditCard {
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
	
	//DryBarMobile drybar=new DryBarMobile(datafile);
	
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void guest_Checkout_CreditCard() throws Exception {
		 
		drybar.Accept();
		drybar.verifyingHomePage();
		  drybar.clickHairProducts();
		  //drybar.Close_popup();
		  drybar.SelectShampoos();
		 // drybar.Accept();
		  drybar.Selectproduct();
		  //drybar.Accept();
		//  drybar.Verify_PDP();
	      drybar.increaseProductQuantity("2");
	     // drybar.Accept();
	      drybar.clickAddtoBag();
	     drybar.clickminiCartButton();
	    drybar.clickCheckoutButton();
	    drybar.click_GuestCheckOut();
	    drybar.guestShippingAddress("ShippingAddress");
	    drybar.Click_PaymetricPaymentMethod();
	    //drybar.select_CC();
	    //drybar.Edit_BillingAddress_PaymetricPaymentMethod("BiillingAddress");
	    drybar.creditCard_payment("CCVisa");
	  drybar.order_Success();
  }
  
	//ul[contains(@class,'header links')]/li[2]/a
  
  @AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}
	
	@BeforeTest
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "DryBar\\config.properties");
		 Login.signIn();
	 // Login.signIn("chrome","iPhone X");
		 
		 
		  
	  }
}
