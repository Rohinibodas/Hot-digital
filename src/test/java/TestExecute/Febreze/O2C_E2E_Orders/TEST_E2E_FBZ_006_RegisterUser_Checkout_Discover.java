package TestExecute.Febreze.O2C_E2E_Orders;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Febreze.FebrezeHelper;

import TestLib.Common;
import TestLib.Login;
  
public class TEST_E2E_FBZ_006_RegisterUser_Checkout_Discover
 {
	String datafile = "Febreze//FebrezeTestData.xlsx";
	FebrezeHelper febreze = new FebrezeHelper(datafile);

	@Test(priority = 1)
  public void RegisterUser_Checkout_Discover() throws Exception {
		try { 
			
			febreze.prepareOrdersData("Febreze_E2E_orderDetails.xlsx");
		    Thread.sleep(4000);
		    String Website=febreze.URL();
		    String Description ="GuestUser_Checkout_VisaCC_Payment";
			febreze.Acceptcookies();
			febreze.Login("AccountDetails");
			//febreze.Accountcreation("AccountCreation");
			febreze.browsersearch("ProductName");
			febreze.productselection();
			febreze.Navigateminicart();
			febreze.NavigateMycartpage();
			febreze.checkoutpage();
			febreze.newaddress();
	   // HashMap<String,String> Shipping=febreze.guestShipingAddress("Address");
	    HashMap<String,String> Shipping=febreze.RegShipingAddress("Address");
	  
	    HashMap<String,String> data=febreze.OrderSummaryValidation();
	    HashMap<String,String> Payment=febreze.AddPaymentdetails1("PaymentDetailsDiscover");	
	    //HashMap<String,String> Payment=Stinger.creditCard_payment("PaymentDetails");
	    String OrderIdNumber= febreze.Verify_order();
	    System.out.println(OrderIdNumber);
	    febreze.order_Success();
	    febreze.writeOrderNumber(Website, OrderIdNumber,Description, data.get("subtotlaValue"),data.get("shippingammountvalue"),data.get("Taxammountvalue"),data.get("ActualTotalammountvalue"),data.get("ExpectedTotalAmmountvalue"),data.get("Discountammountvalue"),Shipping.get("ShippingState"),Shipping.get("ShippingZip"),Payment.get("Card"));
		
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
		
		System.setProperty("configFile", "Febreze\\Config_Febreze_Staging.properties");
		 Login.signIn();
		 
		 
		  
	  }
}
