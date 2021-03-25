package TestExecute.DryBar.Mobile;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestComponent.DryBar.DryBarMobile;
import TestLib.Common;
import TestLib.Login;

public class Express_shippingmethod_checkout {
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarMobile drybar=new DryBarMobile(datafile);
	
	//DryBarMobile drybar=new DryBarMobile(datafile);
	
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void Expres_shipping_method() throws Exception {
		 drybar.Accept();
	   //drybar.clickMyaccount();
	   drybar.Search_productname("ProductName");
	   drybar.Verify_PDP();
	  drybar.increaseProductQuantity("2");
	  drybar.clickAddtoBag();
	  drybar.clickminiCartButton();
	  drybar.clickCheckoutButton();
	  drybar.click_GuestCheckOut();
	  drybar.guestShipingAddress("ShippingAddress");
	  drybar.Express_shippingmethod();
	  drybar.creditCard_payment("PaymentDetails");
	  drybar.order_Verifying();
	  
  }
  
	//ul[contains(@class,'header links')]/li[2]/a
  
  @AfterTest
	public void clearBrowser()
	{
		//Common.closeAll();

	}
	
  @BeforeMethod
  public void startTest() throws Exception {
	 System.setProperty("configFile", "DryBar\\config.properties");
	 Login.signIn("chrome","iPad");
		 
		 
		  
	  }

}
