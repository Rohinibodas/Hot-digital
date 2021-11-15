package TestExecute.DryBar.O2C_E2E_Orders;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_EE_DB_018_GU_Checkout_with_EGiftcard_Only_DiscoverCC {
	
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Guestuser_Checkout_with_EGiftcard_only_and_DiscoverCC() throws Exception {

		try {
			
			String Website=drybar.URL();
			String Description ="GuestUser_Checkout_EGIFT_only_with_DiscoverCC_Payment";
			drybar.Accept();
			drybar.verifyingHomePage();
			drybar.click_Gifts_and_Kits();
			drybar.Select_GiftCards_Category();
			drybar.Select_EGiftCard();
		    drybar.Enter_GiftCard_Details("GiftCardDetails");
		    drybar.clickAddtoBag();
		    drybar.clickminiCartButton();
		    drybar.clickCheckoutButton();
		    drybar.click_GuestCheckOut();
		    drybar.Verify_FreeGift();
		    HashMap<String,String> data=drybar.Egift_Product_OrderSummaryValidation();
		    HashMap<String,String> Billing=drybar.Edit_EgiftBillingAddress_PaymetricPaymentMethod("BiillingAddress");
		    HashMap<String,String> Payment=drybar.creditCard_payment("ccdiscover");
		    String OrderIdNumber= drybar.Verify_order();
			drybar.order_Success();
			drybar.writeOrderNumber(Website, OrderIdNumber,Description, data.get("subtotlaValue"),data.get("shippingammountvalue"),data.get("Taxammountvalue"),data.get("ActualTotalammountvalue"),data.get("ExpectedTotalAmmountvalue"),data.get("Discountammountvalue"),Billing.get("BillingState"),Billing.get("BillingZip"),Payment.get("Card"));
					
				
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
