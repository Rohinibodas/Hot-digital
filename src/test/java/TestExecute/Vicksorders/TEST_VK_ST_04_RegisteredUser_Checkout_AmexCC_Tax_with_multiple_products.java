package TestExecute.Vicksorders;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.Vicks.VicksHelper;
import TestLib.Common;
import TestLib.Login;


public class TEST_VK_ST_04_RegisteredUser_Checkout_AmexCC_Tax_with_multiple_products {
	String datafile = "Vicks//Vickstestdata.xlsx";	
	VicksHelper vicks=new VicksHelper(datafile);
	@Test(priority=1)
	public void RegisteredUser_Checkout_AmexCC_Tax_with_multiple_product() throws Exception {
	
		try {
			
			vicks.Verifyhomepage();
//			vicks.Agreandproceed();
			vicks.loginVicks("AccountDetails");
			vicks.Humidifiers_Vaporizers();
			vicks.productselect();
			vicks.addtocart();
			vicks.temp();
			vicks.tempproduct();
			vicks.addtocart();
			vicks.mincat();
			vicks.checkout();
			vicks.shipingmethod();
			vicks.tax();
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
			System.setProperty("configFile", "Vicks\\config.properties");
			 Login.signIn();
		
			 
		  }


}
