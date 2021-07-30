package TestExecute.Vicksorders;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.Vicks.VicksHelper;
import TestLib.Common;
import TestLib.Login;


public class TEST_VK_ST_014_RegisterUser_Checkout_MasterCC_Tax_With_Discount_and_Single_products_with_Different_Billing_and_shipping_Address {
	String datafile = "Vicks//Vickstestdata.xlsx";	
	VicksHelper vicks=new VicksHelper(datafile);
	@Test(priority=1)
	public void RegisterUser_Checkout_MasterCC_Tax_With_Discount_and_single_products_and_Different_Billing_and_shipping_Address() throws Exception {
	
		try {
			
			vicks.Verifyhomepage();
//			vicks.Agreandproceed();
			vicks.loginVicks("AccountDetails");
			vicks.Humidifiers_Vaporizers();
			vicks.productselect();
			vicks.addtocart();
			//vicks.temp();
			//vicks.tempproduct();
	//		vicks.addtocart();
			vicks.mincat();
			vicks.checkout();
			vicks.addDeliveryAddress_registerUser("Address1");
			vicks.Promocode("Promocode");
			vicks.paymentDetails("MasterCardPaymentDetails");
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
			System.setProperty("configFile", "Vicks\\config.properties");
			 Login.signIn();
		
			 
		  }


}
