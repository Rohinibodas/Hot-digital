package TestExecute.DryBar.SystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestComponent.DryBar.DryBarMobile;
import TestLib.Common;
import TestLib.Login;

public class DB_ST_094_Validate_ShippingMethod_for__GuestUser_Checkout_Non_Aerosol_product_with_Non_PO_Box_PuertoRico_State {
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
		
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void Validate_ShippingMethod_for__GuestUser_Checkout_Non_Aerosol_product_with_Non_PO_Box_PuertoRico_State() throws Exception {
		
		
		try {	
		drybar.Accept();
		drybar.verifyingHomePage();
		drybar.clickHairProducts();
		drybar.SelectShampoos(); 
	    drybar.Selectproduct();
		drybar.Verify_PDP();
		drybar.clickAddtoBag();
	    drybar.clickminiCartButton();
	    drybar.clickCheckoutButton();
	    drybar.click_GuestCheckOut();
	    drybar.guestShipingAddress("Non-POBoxwithPuertoRico");
	    drybar.Verify_UPS_TwoDay_and_UPSNextDay_Shpping_Methods();
	    drybar.Express_shippingmethod();
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
