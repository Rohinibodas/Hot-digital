package TestExecute.DryBar.O2C_E2E_Orders;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;

import TestLib.Common;
import TestLib.Login;

public class TEST_EE_DB_002_GU_Checkout_Aerosol_NonPOBox_UPS_DiscoverCC {
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
		
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void Guestuser_Checkout_Aerosol_product_with_Non_PO_Box_UPS_DiscoverCC() throws Exception {
		try {
			
		
	    String Website=drybar.URL();
	    String Description ="Guestuser_Checkout_Aerosol_product_with_Non_PO_BoxAddress_UPS_Shipping_DiscoverCCPayment";
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
	    HashMap<String,String> Shipping=drybar.guestShipingAddress("ShippingAddress");
	    drybar.select_USPS_StandardGround_shippingMethod();
	    drybar.click_Next();
	    HashMap<String,String> data=drybar.OrderSummaryValidation();
	    HashMap<String,String> Payment=drybar.creditCard_payment("ccdiscover");
	    String OrderIdNumber= drybar.Verify_order();
	    drybar.order_Success();
	    drybar.writeOrderNumber(Website, OrderIdNumber,Description, data.get("subtotlaValue"),data.get("shippingammountvalue"),data.get("Taxammountvalue"),data.get("ActualTotalammountvalue"),data.get("ExpectedTotalAmmountvalue"),data.get("Discountammountvalue"),Shipping.get("ShippingState"),Shipping.get("ShippingZip"),Payment.get("Card"));
		

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
