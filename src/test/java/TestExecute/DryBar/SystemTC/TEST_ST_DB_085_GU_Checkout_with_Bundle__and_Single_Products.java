package TestExecute.DryBar.SystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_DB_085_GU_Checkout_with_Bundle__and_Single_Products {
	
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
		
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void Guest_Checkout_Bundle_product_and_Single_Product_with_MasterCC() throws Exception {
		
		
		try {
			
		drybar.Accept();
		drybar.verifyingHomePage();
		drybar.click_Gifts_and_Kits();
		drybar.Select_SpecialValueSets(); 
	    drybar.Select_Bundle_product();
		drybar.Verify_PDP();
		drybar.clickAddtoBag();
		drybar.clickHairProducts(); 
		drybar.Select_DryShampoo();
		drybar.Select_Aerosol_product();
		drybar.Verify_PDP();
		drybar.clickAddtoBag();
	    drybar.clickminiCartButton();
	    drybar.clickCheckoutButton();
	    drybar.click_GuestCheckOut();
	    drybar.guestShippingAddress("ShippingAddress");
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
