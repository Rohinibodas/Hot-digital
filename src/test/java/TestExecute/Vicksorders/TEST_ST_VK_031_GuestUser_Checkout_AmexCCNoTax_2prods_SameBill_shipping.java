package TestExecute.Vicksorders;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.Vicks.VicksHelper;
import TestLib.Common;
import TestLib.Login;


public class TEST_ST_VK_031_GuestUser_Checkout_AmexCCNoTax_2prods_SameBill_shipping {
	String datafile = "Vicks//Vickstestdata.xlsx";	
	VicksHelper vicks=new VicksHelper(datafile);
	@Test(priority=1)
	public void GuestUser_Checkout_AmexCC_NoTax_multiple_products_and_Same_Billing_and_shipping_Address() throws Exception {
	
		try {
			
			vicks.Verifyhomepage();
//			vicks.Agreandproceed();
			vicks.Humidifiers_Vaporizers();
			vicks.productselect();
			vicks.addtocart();
			vicks.temp();
			vicks.tempproduct();
			vicks.addtocart();
			vicks.mincat();
			vicks.checkout();
			vicks.shippingaddress("Address");
			vicks.paymentDetails("AMEXPaymentDetails");
			vicks.PlaceOrder();
		}
		catch (Exception e) {
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
//			System.setProperty("configFile", "Vicks\\config.properties");
			 Login.signIn();
		
			 
		  }


}
