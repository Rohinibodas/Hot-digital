package TestExecute.DryBar.SystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_DB_086_GU_Checkout_Bundle_and_SingleProductfromBundle {
	
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
		
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void Guest_Checkout_Bundle_product_and_Single_product_from_Bundle_with_AmexCC() throws Exception {
		
		
		try {
			
		drybar.Accept();
		drybar.verifyingHomePage();
		drybar.click_Gifts_and_Kits();
		drybar.Select_SpecialValueSets(); 
	    drybar.Select_Bundle_product();
		drybar.Verify_PDP();
		drybar.clickAddtoBag();
		drybar.clickHairProducts();
		drybar.SelectShampoos();
	    drybar.Selectproduct();
		drybar.Verify_PDP(); 
		drybar.clickAddtoBag();
	    drybar.clickminiCartButton();
	    drybar.clickCheckoutButton();
	    drybar.click_GuestCheckOut();
	    drybar.guestShippingAddress("ShippingAddress");
	    drybar.Edit_BillingAddress_PaymetricPaymentMethod("BiillingAddress");
	    drybar.creditCard_payment("ccamex");
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
