package TestExecute.DryBar.SystemTC;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_DB_018_Express_shipping_method_checkout {
	
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
	
	//DryBarMobile drybar=new DryBarMobile(datafile);
	
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void Express_shipping_method_checkout() throws Exception {
		
		
		 drybar.Accept();
		 drybar.verifyingHomePage();
		 drybar.clickHairProducts();
		  drybar.SelectShampoos();
		  drybar.Selectproduct();
		  drybar.Verify_PDP();
	  drybar.increaseProductQuantity("2");
	  drybar.clickAddtoBag();
	  drybar.clickminiCartButton();
	  drybar.clickCheckoutButton();
	  drybar.click_GuestCheckOut();
	  drybar.Verify_FreeGift();
	  drybar.guestShipingAddress("ShippingAddress");
	  drybar.Express_shippingmethod();
	  drybar.click_Next();
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
