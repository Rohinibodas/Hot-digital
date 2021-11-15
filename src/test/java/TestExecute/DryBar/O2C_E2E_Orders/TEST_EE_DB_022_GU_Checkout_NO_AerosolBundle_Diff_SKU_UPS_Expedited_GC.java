package TestExecute.DryBar.O2C_E2E_Orders;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_EE_DB_022_GU_Checkout_NO_AerosolBundle_Diff_SKU_UPS_Expedited_GC {
	
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
		
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void GuestUser_Checkout_No_AerosolBundle_and_Different_SKU_UPS_Expedited_with_GiftCard_Payment() throws Exception {
		
		
		try {
			
		String Website=drybar.URL();
		String Description ="GuestUser_Checkout_with_AerosolBundle_product_and Another_different_SKU_UPS_Expedited_with_GiftCard";
		drybar.Accept();
		drybar.verifyingHomePage();
		drybar.click_Gifts_and_Kits();
		drybar.Select_SpecialValueSets(); 
	    drybar.Select_No_Aerosols_Bundle_product();
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
	    HashMap<String,String> Shipping=drybar.guestShipingAddress("ShippingAddress");
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
