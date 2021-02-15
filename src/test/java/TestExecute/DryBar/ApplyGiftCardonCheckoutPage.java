package TestExecute.DryBar;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;

public class ApplyGiftCardonCheckoutPage {
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void applyGiftCardonCheckoutPage() throws Exception {
	//  drybar.clickMyaccount();
	  try{
	  drybar.clickHairProducts();
	  drybar.select_shampoos();
	  drybar.selectproduct("ProductName");
	  
	  drybar.increaseProductQuantity("2");
	  drybar.clickAddtoBag();
	  drybar.clickminiCartButton();
	  drybar.clickCheckoutButton();
	  drybar.click_GuestCheckOut();
	  drybar.guestShippingAddress("ShippingAddress");
	  drybar.gitCard("GiftCard");
	  drybar.click_place_order_button();
	  drybar.order_Verifying();
	  //  drybar.Click_PaymetricPaymentMethod();
	 // drybar.Edit_BillingAddress_PaymetricPaymentMethod("BiillingAddress");
	  //drybar.creditCard_payment("PaymentDetails");
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
		//Common.closeAll();

	}
	
	@BeforeTest
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "DryBar\\config.properties");
		  Login.signIn();
		 
		  
	  }
}