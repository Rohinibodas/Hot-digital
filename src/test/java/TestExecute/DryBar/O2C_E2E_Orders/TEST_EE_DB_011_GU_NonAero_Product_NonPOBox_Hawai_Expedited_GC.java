package TestExecute.DryBar.O2C_E2E_Orders;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_EE_DB_011_GU_NonAero_Product_NonPOBox_Hawai_Expedited_GC {
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
		
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void GuestUser_Checkout_Non_Aerosol_product_with_Non_PO_Box_Hawai_Expedited_GC() throws Exception {
		
		
		try {
			
		String Website=drybar.URL();
		String Description ="GuestUser_Checkout_Non_Aerosol_product_with_NonPO_Box_Hawai_Address_USPS_Expedited_Shipping_GiftCardPayment";
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
	    drybar.Verify_FreeGift();
	    HashMap<String,String> Shipping=drybar.guestShipingAddress("Non-POBoxwithHawaii");
	    drybar.Verify_UPS_TwoDay_and_UPSNextDay_Shpping_Methods();
	    drybar.Expediated_shippingmethod();
	    drybar.click_Next();
	    HashMap<String,String> data=drybar.OrderSummaryValidation();
	    HashMap<String,String> Payment=drybar.gitCard("GiftCard");
	    drybar.click_place_order_button();
	    String OrderIdNumber= drybar.Verify_order();
	  	drybar.order_Success();
	  	drybar.writeOrderNumber(Website, OrderIdNumber,Description, data.get("subtotlaValue"),data.get("shippingammountvalue"),data.get("Taxammountvalue"),data.get("ActualTotalammountvalue"),data.get("ExpectedTotalAmmountvalue"),data.get("Discountammountvalue"),Shipping.get("ShippingState"),Shipping.get("ShippingZip"),Payment.get("GiftCard"));
	  		

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
