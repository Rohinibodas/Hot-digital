package TestExecute.DryBar.SystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestComponent.DryBar.DryBarMobile;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_DB_079_RU_ShipMethod_NonAerosol_Product_POBox_Alaska {
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
		
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void Validate_ShippingMethod_for__RegisterUser_Checkout_Non_Aerosol_product_with_PO_Box_Alaska_State() throws Exception {
		
		
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
	    drybar.addDeliveryAddress_for_registerUser("POBoxAddresswithAlaska");
	    drybar.Verify_No_Shipping_Method();

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
