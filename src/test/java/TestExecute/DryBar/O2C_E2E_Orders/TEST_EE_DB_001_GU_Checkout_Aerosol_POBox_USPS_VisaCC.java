package TestExecute.DryBar.O2C_E2E_Orders;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_EE_DB_001_GU_Checkout_Aerosol_POBox_USPS_VisaCC
 {
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
		
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void GuestUser_Checkout_Aerosol_with_PO_BoxAddress_USPS_VisaCC() throws Exception {
		try { 
			
	    drybar.prepareOrdersData("DryBar_E2E_orderDetails.xlsx");
	    Thread.sleep(4000);
	    String Website=drybar.URL();
	    String Description ="GuestUser_Checkout_with_Aerosol_Product_with_PO_BoxAddress_USPS_Shipping method_VisaCC_Payment";
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
	    HashMap<String,String> Shipping=drybar.guestShipingAddress("POBoxAddress");
	    drybar.Verify_Single_USPS_Ground_Shpping_Method();
	    drybar.click_Next();
	    HashMap<String,String> data=drybar.OrderSummaryValidation();
	    HashMap<String,String> Payment=drybar.creditCard_payment("CCVisa");
	    String OrderIdNumber= drybar.Verify_order();
	    System.out.println(OrderIdNumber);
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
