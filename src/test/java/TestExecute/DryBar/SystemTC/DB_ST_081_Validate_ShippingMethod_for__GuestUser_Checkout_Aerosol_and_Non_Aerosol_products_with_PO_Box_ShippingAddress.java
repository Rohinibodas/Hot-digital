package TestExecute.DryBar.SystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestComponent.DryBar.DryBarMobile;
import TestLib.Common;
import TestLib.Login;

public class DB_ST_081_Validate_ShippingMethod_for__GuestUser_Checkout_Aerosol_and_Non_Aerosol_products_with_PO_Box_ShippingAddress {
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
		
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void Validate_ShippingMethod_for__GuestUser_Checkout_Aerosol_and_Non_Aerosol_products_with_PO_Box_ShippingAddress() throws Exception {
		try {
			
		drybar.Accept();
		drybar.verifyingHomePage();
		drybar.clickHairProducts(); 
		drybar.Select_DryShampoo();
		drybar.Select_Aerosol_product();
		drybar.Verify_PDP();
		drybar.clickAddtoBag();
		Thread.sleep(5000);
		drybar.clickHairProducts();
		drybar.SelectShampoos(); 
	    drybar.Selectproduct();
		drybar.Verify_PDP();
		drybar.clickAddtoBag();
	    drybar.clickminiCartButton();
	    drybar.clickCheckoutButton();
	    drybar.guestShipingAddress("POBoxAddress");
	    drybar.Verify_Single_USPS_Ground_Shpping_Method();
	    drybar.click_Next();
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
		 System.setProperty("configFile", "DryBar\\config.properties");
		 Login.signIn();
		 
		 
		  
	  }
}
