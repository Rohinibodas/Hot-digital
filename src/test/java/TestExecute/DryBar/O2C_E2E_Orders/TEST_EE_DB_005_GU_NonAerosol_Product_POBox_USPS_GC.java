package TestExecute.DryBar.O2C_E2E_Orders;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestComponent.DryBar.DryBarMobile;
import TestLib.Common;
import TestLib.Login;

public class TEST_EE_DB_005_GU_NonAerosol_Product_POBox_USPS_GC {
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
		
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void GuestUser_Checkout_Non_Aerosol_product_with_PO_Box_USPS_GC() throws Exception {
		
		
		try {
			
		String Website=drybar.URL();
	    String Description ="GuestUser_Checkout_Aerosol_and_Non_Aerosol_products_with_PO_Box_Address_USPS_Shipping_GiftCardPayment";
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
	    HashMap<String,String> Shipping=drybar.guestShipingAddress("POBoxAddress");
	    drybar.Verify_Single_USPS_Ground_Shpping_Method();
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
