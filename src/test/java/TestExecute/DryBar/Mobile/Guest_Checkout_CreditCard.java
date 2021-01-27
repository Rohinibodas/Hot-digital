package TestExecute.DryBar.Mobile;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestComponent.DryBar.DryBarMobile;
import TestLib.Common;
import TestLib.Login;

public class Guest_Checkout_CreditCard {
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarMobile drybar=new DryBarMobile(datafile);
	
	//DryBarMobile drybar=new DryBarMobile(datafile);
	
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void guest_Checkout_CreditCard() throws Exception {
		try{
	 //  drybar.clickMyaccount();
	  drybar.clicktreebarmenu();
	  drybar.clickHairProducts();
	  drybar.selectproduct("ProductName");
	  drybar.increaseProductQuantity("2");
	  drybar.clickAddtoBag();
	  drybar.clickminiCartButton();
	  drybar.clickCheckoutButton();
	  drybar.click_GuestCheckOut();
	  drybar.guestShippingAddress("ShippingAddress");
	  drybar.Click_PaymetricPaymentMethod();
	  drybar.Edit_BillingAddress_PaymetricPaymentMethod("BiillingAddress");
	  drybar.creditCard_payment("PaymentDetails");
	  drybar.order_Verifying();
  }
	
	catch (Exception e) {
		e.printStackTrace();
		
		Assert.fail(e.getMessage(), e);
	} 
	}
  
	//ul[contains(@class,'header links')]/li[2]/a
  
  @AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}
	
	/*@BeforeMethod
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "DryBar\\config.properties");
		 Login.signIn("chrome","Galaxy S5");
	 }*/
	
@BeforeTest
	@Parameters({"device"})  
	  public void startTest(String Device) throws Exception {
		System.setProperty("configFile", "DryBar\\config.properties");
		Login.signIn("chrome",Device);
	  

}

}
