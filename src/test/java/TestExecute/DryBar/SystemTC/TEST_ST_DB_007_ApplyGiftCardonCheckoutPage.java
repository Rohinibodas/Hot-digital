package TestExecute.DryBar.SystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_DB_007_ApplyGiftCardonCheckoutPage {
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void applyGiftCardonCheckoutPage() throws Exception {
	//  drybar.clickMyaccount();
	  try{
	 drybar.Accept();
	  drybar.verifyingHomePage();
		 drybar.clickHairProducts();
		  drybar.SelectShampoos();
		  drybar.Selectproduct();
		 // drybar.Accept();
		  drybar.Verify_PDP();
	  drybar.increaseProductQuantity("2");
	  drybar.clickAddtoBag();
	  drybar.clickminiCartButton();
	  drybar.clickCheckoutButton();
	  drybar.click_GuestCheckOut();
	  drybar.Verify_FreeGift();
	  drybar.guestShippingAddress("ShippingAddress");
	  drybar.gitCard("GiftCard");
	  drybar.click_place_order_button();
	  drybar.order_Success();
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
	
	@BeforeTest
	  public void startTest() throws Exception {
		// System.setProperty("configFile", "DryBar\\config.properties");
		  Login.signIn();
		 
		  
	  }
}