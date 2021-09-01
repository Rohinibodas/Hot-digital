package TestExecute.vicks;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestComponent.Vicks.VicksHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_VK_052_Guestuser_XML_Validation {
	String datafile = "Vicks//Vickstestdata.xlsx";	
	VicksHelper vicks = new VicksHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	
	public void Guestuser_XML_Validation() throws Throwable {

		try {
			
			vicks.Verifyhomepage();
			vicks.Agreandproceed();
			vicks.Humidifiers_Vaporizers();
			vicks.productselect();
			vicks.addtocart();
			vicks.mincat();
			vicks.viewandedit();
			HashMap<String,HashMap<String,String>> productinfromation=vicks.productshoppingcart();
			vicks.mincat();
			vicks.checkout();
			HashMap<String,String> shippingaddresss=vicks.shippingaddress1("Address1");
			HashMap<String,String> ordertaxammount=vicks.orderamountinfo();
			HashMap<String,String>paymentdetriles=vicks.paymentdetriles("PaymentDetails");
		//	String order=vicks.PlaceOrder();
			
			 vicks.vicksAdminlogin("AdminLogins");
		/*     vicks.selectManulExport(order);
			
			
		      vicks.productinfromationvalidation(productinfromation, order);
			 vicks.shippingvalidaing_GustUserXML(shippingaddresss, order);
			 vicks.TotalvalidationXML(ordertaxammount, order);
			 vicks.card_details_validationXML(paymentdetriles, order);*/
			
		 
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
