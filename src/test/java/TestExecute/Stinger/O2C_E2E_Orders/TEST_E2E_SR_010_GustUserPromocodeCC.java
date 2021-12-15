package TestExecute.Stinger.O2C_E2E_Orders;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Stinger.StingerHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_E2E_SR_010_GustUserPromocodeCC
 {
	String datafile = "Stinger//StingerTestData.xlsx";
	StingerHelper Stinger=new StingerHelper(datafile);
		
	@Test(priority=1)
  public void GuestUser_Checkout_Aerosol_with_PO_BoxAddress_USPS_VisaCC() throws Exception {
		try { 
			
		Stinger.prepareOrdersData("Stinger_E2E_orderDetails.xlsx");
	    Thread.sleep(4000);
	    String Website=Stinger.URL();
	    String Description ="GuestUser_Checkout_VisaCC_Payment";
		Stinger.categoryMenuItem1();
		Stinger.Addtocart();
		Stinger.checkoutPage();
	    HashMap<String,String> Shipping=Stinger.guestShipingAddress("Address");
	    Stinger.Apply_promocode("promocode"); 
	    HashMap<String,String> data=Stinger.OrderSummaryValidation();
	    HashMap<String,String> Payment=Stinger.creditCard_payment1("PaymentDetails");	
	    //HashMap<String,String> Payment=Stinger.creditCard_payment("PaymentDetails");
	    String OrderIdNumber= Stinger.Verify_order();
	    System.out.println(OrderIdNumber);
	    Stinger.order_Success();
	    Stinger.writeOrderNumber1(Website, OrderIdNumber,Description, data.get("subtotlaValue"),data.get("shippingammountvalue"),data.get("Taxammountvalue"),data.get("ActualTotalammountvalue"),data.get("ExpectedTotalAmmountvalue"),data.get("Discountammountvalue"),Shipping.get("ShippingState"),Shipping.get("ShippingZip"),Payment.get("Card"));
		
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
		//System.setProperty("configFile", "Stinger\\config_Stinger_Production.properties");
		System.setProperty("configFile", "Stinger\\config_Stinger_Staging.properties");
		 Login.signIn();
		 
		 
		  
	  }
}
