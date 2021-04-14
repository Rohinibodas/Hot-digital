package TestExecute.Honeywell;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Honeywell.Honeywellhelper;
import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_HW_0019_Checkout_as_guest_user_with_paymetric_method_MASTER_card {
	String datafile = "Honeywell\\HoneywellTestData.xlsx";	
	Honeywellhelper honeyWell=new Honeywellhelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	
	public void gustUserCheckout_CreditCard_Mastercard() throws Exception {

		try {
			
			
			honeyWell.verifyingHomePage();
			honeyWell.click_Airpurifiers();
			honeyWell.adding_product_toCart("ProductName");
			honeyWell.clickAddtoBag();
			honeyWell.clickminicartcheckout();
			honeyWell.guestShippingAddress("ShippingAddress");
			honeyWell.creditCard_payment("CCmastercard");
			honeyWell.order_Verifying();
			
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
	
	@BeforeTest
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "Honeywell\\config.properties");
		  Login.signIn();
		 
		  
	  }

}
