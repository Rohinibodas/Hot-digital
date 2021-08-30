package TestExecute.DryBar.SystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestComponent.DryBar.DryBarMobile;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_DB_069_GU_ShipMethod_AerosolProduct_POBox_Alaska {
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
		
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void Validate_ShippingMethod_for__Guestuser_Checkout_Aerosol_product_with_PO_Box_AlaskaState() throws Exception {
		try {
			
		drybar.Accept();
		drybar.verifyingHomePage();
		drybar.clickHairProducts(); 
		drybar.Select_DryShampoo();
		drybar.Select_Aerosol_product();
		drybar.Verify_PDP();
		drybar.clickAddtoBag();
	    drybar.clickminiCartButton();
	    drybar.clickCheckoutButton();
	    drybar.click_GuestCheckOut();
	   drybar.Verify_FreeGift();
	    drybar.guestShipingAddress("POBoxAddresswithAlaska");
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
		 //System.setProperty("configFile", "DryBar\\config.properties");
		 Login.signIn();
		 
		 
		  
	  }
}
