package TestExecute.DryBar.SystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_DB_087_GU_Checkout_TwoBundleproducts_with_Same_SKU_in_each_Bundle {
	
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
		
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void Guest_Checkout_Two_Bundle_products_and_Single_product_from_Bundle_with_discoverCC() throws Exception {
		
		
		try {
			
			
		drybar.Accept();
		drybar.verifyingHomePage();
		drybar.click_Gifts_and_Kits();
		drybar.Select_SpecialValueSets(); 
	    drybar.Select_Bundle_product();
		drybar.Verify_PDP();
		drybar.clickAddtoBag();
		drybar.click_Gifts_and_Kits();
		drybar.Select_SpecialValueSets();
		drybar.Select_Another_Bundle_product();
		drybar.Verify_PDP(); 
		drybar.clickAddtoBag();
	    drybar.clickminiCartButton();
	    drybar.clickCheckoutButton();
	    drybar.click_GuestCheckOut();
	    drybar.guestShipingAddress("ShippingAddress");
	    drybar.select_USPS_StandardGround_shippingMethod();
	    drybar.click_Next();
	    drybar.Edit_BillingAddress_PaymetricPaymentMethod("BiillingAddress");
	    drybar.creditCard_payment("ccdiscover");
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
