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

public class TEST_ST_BHC_03_Reg_Checkout_CreditCard {
	String datafile = "BraunHC//BraunHCTestData.xlsx";	
	BraunHCHelper BraunHC=new BraunHCHelper(datafile);
	
	@Test(priority=1)
	public void GuestCategoryCheckoutCC() throws Exception {

		try {
			BraunHC.prepareOrdersData("RegBraunUS_E2E_orderDetails.xlsx");
			String Description ="Reg_Checkout_Promocode_CreditCard";
			BraunHC.AGREEPROCEED();
			String Website=BraunHC.URL();
			BraunHC.PopUp();
			BraunHC.loginBraunHC("AccountDetails");
			BraunHC.Select_ProductinThermometers("No Touch Thermometer");
			BraunHC.Addtocart();
			BraunHC.ViewandEditcartPage();
			BraunHC.checkoutPage();
			BraunHC.E2ERegisterUser_ShippingAddress();
			BraunHC.ShippingMethods();
			HashMap<String,String> Shipping=BraunHC.RegShipingAddress("ShippingAddress");
	    	HashMap<String,String> data=BraunHC.OrderSummaryValidation();
			HashMap<String,String> Payment=BraunHC.creditCard_payment("PaymentDetails");
			String OrderIdNumber= BraunHC.Verify_order();
			System.out.println(OrderIdNumber);
			BraunHC.order_Success();
			BraunHC.RegwriteOrderNumber(Website, OrderIdNumber,Description, data.get("subtotlaValue"),data.get("shippingammountvalue"),data.get("Taxammountvalue"),data.get("ActualTotalammountvalue"),data.get("ExpectedTotalAmmountvalue"),data.get("Discountammountvalue"),Shipping.get("ShippingState"),Shipping.get("ShippingZip"),Payment.get("Card"));
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
