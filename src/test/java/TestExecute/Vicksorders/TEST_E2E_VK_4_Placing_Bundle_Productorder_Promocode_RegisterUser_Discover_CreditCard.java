package TestExecute.Vicksorders;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Vicks.VicksHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_E2E_VK_4_Placing_Bundle_Productorder_Promocode_RegisterUser_Discover_CreditCard {
	String datafile = "Vicks//VicksTestData.xlsx";	
	VicksHelper vicks = new VicksHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	
	public void Placing_Bundle_Productorder_Promocode_RegisterUser_Discover_CreditCard() throws Throwable {

		try {
			
		  
		    String Website=vicks.URL();
		    String Description ="Placing_Bundle_Productorder_Promocode_RegisterUser_Discover_CreditCard";
			vicks.Verifyhomepage();
			vicks.Agreandproceed();
			vicks.loginVicks("AccountDetails");
			vicks.bundleproduct("Bundleproduct");
			vicks.clickonproduct();
			vicks.colorswatch();
			vicks.addtocart();
			vicks.mincat();
			vicks.checkout();
			HashMap<String,String> shippingaddresss=vicks.RegisterShipingAddress("Address1");
			vicks.Promocode("Promocode");
			HashMap<String,String> data=vicks.OrderSummaryValidation();
			HashMap<String,String>paymentdetriles=vicks.paymentdetriles("DiscoverPaymentDetails");
			String order=vicks.regPlaceOrder();
			
			 vicks.writeOrderNumber(Website, order,Description, data.get("subtotlaValue"),data.get("shippingammountvalue"),data.get("Taxammountvalue"),
					   data.get("ActualTotalAmmount"),data.get("ExpectedTotalAmmountvalue"),data.get("Discountammountvalue"),shippingaddresss.get("ShippingState"),
					   shippingaddresss.get("ShippingZip"),paymentdetriles.get("Card"));
			
			
		 
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
	
	@BeforeMethod
	public void startTest() throws Exception {
//		System.setProperty("configFile", "Vicks\\config.properties");
		Login.signIn();
 
	  }

}
