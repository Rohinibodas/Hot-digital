package TestExecute.Vicksorders;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.Vicks.VicksHelper;
import TestLib.Common;
import TestLib.Login;


public class TEST_ST_VK_027_Reguser_Checkout_VisaCCNoTax_DiffBill_Shipping {
	String datafile = "Vicks//Vickstestdata.xlsx";	
	VicksHelper vicks=new VicksHelper(datafile);
	@Test(priority=1)
	public void Registereduser_Checkout_VisaCC_NoTax_with_Diff_Billing_and_Shipping (){
		try {
			
			vicks.Verifyhomepage();
//			vicks.Agreandproceed();
			vicks.loginVicks("AccountDetails");
			vicks.Humidifiers_Vaporizers();
			vicks.productselect();
			vicks.addtocart();
			vicks.mincat();
			vicks.checkout();
			vicks.addDeliveryAddress_registerUser("Address");
			vicks.paymentDetails("PaymentDetails");
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
