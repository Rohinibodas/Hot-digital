package TestExecute.BraunHC.O2C_E2E_Orders;

import org.testng.annotations.Test;

import TestComponent.BraunHC.BraunHCHelper;
import TestLib.Common;
import TestLib.Login;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class TEST_ST_BHC_01_Guest_Checkout_CreditCard {
	String datafile = "BraunHC//BraunHCTestData.xlsx";	
	BraunHCHelper BraunHC=new BraunHCHelper(datafile);
	
	@Test(priority=1)
	public void GuestCategoryCheckoutCC() throws Exception {

		try {
			BraunHC.prepareOrdersData("BraunUS_E2E_orderDetails.xlsx");
			String Description ="Guest_Checkout_CreditCard";
			BraunHC.AGREEPROCEED();
			String Website=BraunHC.URL();
			BraunHC.PopUp();
			BraunHC.Select_ProductinThermometers("No Touch Thermometer");
			BraunHC.Addtocart();
			BraunHC.ViewandEditcartPage();
			
			BraunHC.checkoutPage();
			HashMap<String,String> Shipping=BraunHC.ShipingAddress("ShippingAddress");
			BraunHC.ShippingMethods();
	    	BraunHC.AddressVerfication();
	    	HashMap<String,String> data=BraunHC.OrderSummaryValidation();
			//BraunHC.creditCard_payment("PaymentDetails");
			HashMap<String,String> Payment=BraunHC.creditCard_payment("PaymentDetailsAEMX");
			String OrderIdNumber= BraunHC.Verify_order();
			System.out.println(OrderIdNumber);
			BraunHC.order_Success();
			BraunHC.writeOrderNumber(Website, OrderIdNumber,Description, data.get("subtotlaValue"),data.get("shippingammountvalue"),data.get("Taxammountvalue"),data.get("ActualTotalammountvalue"),data.get("ExpectedTotalAmmountvalue"),data.get("Discountammountvalue"),Shipping.get("ShippingState"),Shipping.get("ShippingZip"),Payment.get("Card"));
		}
		catch (Exception e) {
			Assert.fail(e.getMessage(), e);
		} 
	}
	

	@BeforeTest
    public void startTest() throws Exception {
		// System.setProperty("configFile", "BraunHC\\Config_BraunHC_Production.properties");
		 //System.setProperty("configFile", "BraunHC\\Config_BraunHC_Staging.properties");
		   	    
    Login.signIn();
    }
	
	
	@AfterTest
	public void clearBrowser()
	{
		
	Common.closeAll();

	}
}
