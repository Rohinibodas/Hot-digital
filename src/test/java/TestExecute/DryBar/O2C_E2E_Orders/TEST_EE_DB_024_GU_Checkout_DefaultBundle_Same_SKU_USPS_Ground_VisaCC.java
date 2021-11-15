package TestExecute.DryBar.O2C_E2E_Orders;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_EE_DB_024_GU_Checkout_DefaultBundle_Same_SKU_USPS_Ground_VisaCC {
	
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
		
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void GuestUser_Checkout_DefaultBundle_and_Same_SKU_USPS_Ground_with_VisaCC() throws Exception {
		
		
		try {
			
		String Website=drybar.URL();
		String Description ="GuestUser_Checkout_DefaultBundle_and_another_Same_SKU_product_from_Bundle_USPS_Ground_with_VisaCC_Payment";
		drybar.Accept();
		drybar.verifyingHomePage();
		drybar.click_Gifts_and_Kits();
		drybar.Select_SpecialValueSets(); 
	    drybar.Select_Default_Options_Bundle_product();
		drybar.Verify_PDP();
		drybar.clickAddtoBag();
		drybar.clickHairProducts();
		drybar.SelectShampoos(); 
	    drybar.Select_Another_Same_SKU_product();
	    drybar.Verify_PDP();
	    drybar.Select_Full_Size();
		drybar.clickAddtoBag();
	    drybar.clickminiCartButton();
	    drybar.clickCheckoutButton();
	    drybar.click_GuestCheckOut();
	    HashMap<String,String> Shipping=drybar.guestShipingAddress("POBoxAddress");
	    drybar.Verify_Single_USPS_Ground_Shpping_Method();
	    drybar.click_Next();
	    HashMap<String,String> data=drybar.OrderSummaryValidation();
	    HashMap<String,String> Payment=drybar.creditCard_payment("CCVisa");
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
		// System.setProperty("configFile", "DryBar\\config.properties");
		 Login.signIn();
		 
		 
		  
	  }

}
