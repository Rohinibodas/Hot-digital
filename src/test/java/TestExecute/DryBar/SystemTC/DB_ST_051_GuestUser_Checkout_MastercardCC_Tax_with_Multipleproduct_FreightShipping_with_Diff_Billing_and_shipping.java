package TestExecute.DryBar.SystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;

public class DB_ST_051_GuestUser_Checkout_MastercardCC_Tax_with_Multipleproduct_FreightShipping_with_Diff_Billing_and_shipping {
	
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  
  public void GuestUser_Checkout_MastercardCC_Tax_with_Multipleproduct_FreightShipping_with_Diff_Billing_and_shipping() {
		try {
			
			  drybar.Accept();
			  drybar.verifyingHomePage();
			  drybar.clickHairProducts();
			  drybar.SelectShampoos();
			  drybar.Selectproduct(); 
			  drybar.Verify_PDP();
			  drybar.clickAddtoBag();
			  drybar.Search_productname("ProductName");
			  drybar.Click_View_Product();
			  drybar.clickAddtoBag();
			  drybar.clickminiCartButton();
			  drybar.clickCheckoutButton();
			  drybar.click_GuestCheckOut();
			  drybar.guestShipingAddress("ShippingAddress");
			  drybar.Express_shippingmethod();
			  drybar.click_Next();
			  drybar.Verify_tax();
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
		 System.setProperty("configFile", "DryBar\\config.properties");
		  Login.signIn();
		 
		  
	  }

}