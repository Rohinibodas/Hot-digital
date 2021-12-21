package TestExecute.Stinger.Order_XML;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Stinger.StingerHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_SR_001_Guestuser_XML_Validation
 {
	String datafile = "Stinger//StingerTestData.xlsx";
	StingerHelper Stinger=new StingerHelper(datafile);
		
	@Test(priority=1)
  public void Guestuser_XML_Validation() throws Exception {
		try {   
			
		
	    Stinger.categoryMenuItem();
		Stinger.Addtocart();
		Stinger.ViewandEditcart1();
		HashMap<String,HashMap<String,String>> productinfromation=Stinger.productshoppingcart();
		Stinger.checkoutPage();
		HashMap<String,String> shippingaddresss=Stinger.shippingaddress1("Address");
	   // HashMap<String,String> Shipping=Stinger.guestShipingAddress("Address");
	  
		
		HashMap<String,String> ordertaxammount=Stinger.orderamountinfo();
		HashMap<String,String>paymentdetriles=Stinger.paymentdetriles("PaymentDetails");
		//String order= Stinger.Verify_order();
		String order=Stinger.PlaceOrder();
		Stinger.vicksAdminlogin("AdminLogins");
		Stinger.selectManulExport(order);
		
		
	     
		Stinger.productinfromationvalidation(productinfromation, order);
		Stinger.shippingvalidaing_GustUserXML(shippingaddresss, order);
		Stinger.TotalvalidationXML(ordertaxammount, order);
		Stinger.card_details_validationXML(paymentdetriles, order);
	
	  /*  HashMap<String,String> data=Stinger.OrderSummaryValidation();
	    HashMap<String,String> Payment=Stinger.creditCard_payment1("PaymentDetails");	
	    //HashMap<String,String> Payment=Stinger.creditCard_payment("PaymentDetails");
	    String OrderIdNumber= Stinger.Verify_order();
	    System.out.println(OrderIdNumber);
	    Stinger.order_Success();*/
	  //  Stinger.writeOrderNumber1(Website, OrderIdNumber,Description, data.get("subtotlaValue"),data.get("shippingammountvalue"),data.get("Taxammountvalue"),data.get("ActualTotalammountvalue"),data.get("ExpectedTotalAmmountvalue"),data.get("Discountammountvalue"),Shipping.get("ShippingState"),Shipping.get("ShippingZip"),Payment.get("Card"));
		
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
