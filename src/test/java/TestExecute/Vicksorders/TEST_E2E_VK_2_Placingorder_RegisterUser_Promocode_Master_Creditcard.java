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

public class TEST_E2E_VK_2_Placingorder_RegisterUser_Promocode_Master_Creditcard {
	String datafile = "Vicks//VicksTestData.xlsx";	
	VicksHelper vicks = new VicksHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	
	public void Placingorder_RegisterUser_Promocode_Master_Creditcard() throws Throwable {

		try {
			
		  
		    String Website=vicks.URL();
		    String Description ="Placingorder_RegisterUser_Promocode_Master_Creditcard";
			vicks.Verifyhomepage();
//			vicks.Agreandproceed();
			vicks.loginVicks("AccountDetails");
			vicks.Humidifiers_Vaporizers();
			vicks.productselect();
			vicks.addtocart();
			vicks.mincat();
			vicks.checkout();
			HashMap<String,String> shippingaddresss=vicks.RegisterShipingAddress("Address1");
			vicks.Promocode("Promocode");
			HashMap<String,String> data=vicks.OrderSummaryValidation();
			HashMap<String,String>paymentdetriles=vicks.paymentdetriles("MasterCardPaymentDetails");
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
		System.setProperty("configFile", "Vicks\\config.properties");
		Login.signIn();
 
	  }

}
