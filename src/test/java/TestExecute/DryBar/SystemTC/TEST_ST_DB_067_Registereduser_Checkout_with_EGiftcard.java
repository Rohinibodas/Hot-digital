package TestExecute.DryBar.SystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_DB_067_Registereduser_Checkout_with_EGiftcard {
	
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Registereduser_Checkout_with_EGiftcard() throws Exception {

		try {
			
			drybar.Accept();
			drybar.verifyingHomePage();
			drybar.navigateMyAccount();
			  drybar.loginApplication("HoTEmployeeAccountDetails");
			  drybar.ClearMiniCart_Bag();
			drybar.click_Gifts_and_Kits();
			drybar.Select_EGiftCard();
		drybar.Enter_GiftCard_Details("GiftCardDetails");
		    drybar.clickAddtoBag();
		    drybar.clickminiCartButton();
		    drybar.clickCheckoutButton();
		    drybar.Verify_FreeGift();
			  drybar.Edit_BillingAddress_PaymetricPaymentMethod("BiillingAddress");
			  drybar.creditCard_payment("CCVisa");
			 drybar.order_Success();
			
		}
			
		catch (Exception e) {
	e.printStackTrace();
			
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	@BeforeTest
	  public void startTest() throws Exception {
		 //System.setProperty("configFile", "DryBar\\config.properties");
		  Login.signIn();
		 
		  
	  }
	
	
	@AfterTest
	public void clearBrowser()
	{
		
	Common.closeAll();

	}

}
