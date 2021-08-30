package TestExecute.DryBar.SystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_DB_041_RUser_Checkout_Master_Tax_same_BillingShipping {
	
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  
  public void RegisteredUser_Checkout_masterCC_Tax_with_same_Billing_and_shipping() {
		try {
			
			  drybar.Accept();
			  drybar.verifyingHomePage();
			  drybar.navigateMyAccount();
			  drybar.loginApplication("AccountDetails");
			  drybar.ClearMiniCart_Bag();
			  drybar.clickHairProducts();
			  drybar.SelectShampoos();
			  drybar.Selectproduct();
			  drybar.Verify_PDP(); 
			  drybar.clickAddtoBag();
			  drybar.clickminiCartButton();
			  drybar.clickCheckoutButton();
			  drybar.Verify_FreeGift();
			  drybar.addDeliveryAddress_registerUser("ShippingAddress");
			  drybar.Verify_tax();
			  drybar.click_Same_Billing_Address();
			  drybar.creditCard_payment("CCmastercard");
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
		// System.setProperty("configFile", "DryBar\\config.properties");
		  Login.signIn();
		 
		  
	  }

}
