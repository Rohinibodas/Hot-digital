package TestExecute.DryBar.SystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestComponent.DryBar.DryBarMobile;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_DB_053_RU_Master_NoTax_MultiProduct_Diff_BillShip {
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
		
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void Registereduser_Checkout_MastercardCC_NoTax_MultipleProduct_with_Diff_Billing_and_Shipping() throws Exception {
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
		drybar.Search_productname("ProductName");
		drybar.Select_Searched_Product();
		//drybar.Click_View_Product();
		drybar.clickAddtoBag();
	    drybar.clickminiCartButton();
	    drybar.clickCheckoutButton();
	    drybar.Verify_FreeGift();
	   	drybar.addDeliveryAddress_registerUser("NoTaxShippingAddress");
		drybar.Edit_BillingAddress_PaymetricPaymentMethod("BiillingAddress");
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
		 //System.setProperty("configFile", "DryBar\\config.properties");
		 Login.signIn();
		 
		 
		  
	  }
}
