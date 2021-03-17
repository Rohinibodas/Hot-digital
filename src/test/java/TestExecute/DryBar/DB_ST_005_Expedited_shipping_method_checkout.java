package TestExecute.DryBar;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;

public class DB_ST_005_Expedited_shipping_method_checkout {
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
	
	//DryBarMobile drybar=new DryBarMobile(datafile);
	
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void Expediated_ShippingMethod() throws Exception {
		 drybar.Accept();
	   //drybar.clickMyaccount();
	  // drybar.Guestuser_PDP();
	   drybar.Search_productname("ProductName");
		  drybar.Verify_PDP();
	  //drybar.clicktreebarmenu();
	  //drybar.clickHairProducts();
	  //drybar.selectproduct("ProductName");
	  drybar.increaseProductQuantity("2");
	  drybar.clickAddtoBag();
	  drybar.clickminiCartButton();
	  drybar.clickCheckoutButton();
	  drybar.click_GuestCheckOut();
	  drybar.guestShipingAddress("ShippingAddress");
	  drybar.Expediated_shippingmethod();
	  //drybar.Edit_BillingAddress_PaymetricPaymentMethod("BiillingAddress");
	  drybar.creditCard_payment("PaymentDetails");
	  drybar.order_Verifying();
	  //drybar.Click_PaymetricPaymentMethod();
	  //drybar.Edit_BillingAddress_PaymetricPaymentMethod("BiillingAddress");
	  //drybar.creditCard_payment("PaymentDetails");
	  //drybar.order_Verifying();
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
