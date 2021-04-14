
package TestExecute.Honeywell;


import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Honeywell.Honeywellhelper;

import TestLib.Common;
import TestLib.Login;

public class TEST_ST_HW_018_Checkout_as_loggedin_user_with_paymetric_method_AMEXcard {
	String datafile = "Honeywell\\HoneywellTestData.xlsx";	
	Honeywellhelper honeyWell=new Honeywellhelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	
	public void registeredUser_Checkout_CreditCard_amex() throws Exception {

		try {
			honeyWell.loginHoneywell("AccountDetails");
			honeyWell.verifyingHomePage();
			honeyWell.click_Airpurifiers();
			honeyWell.adding_product_toCart("productnameRegester");
			honeyWell.clickAddtoBag();
			honeyWell.clickminicartButton();
			honeyWell.clickminicartcheckout();
			honeyWell.addDeliveryAddress_registerUser("ShippingAddress");
			honeyWell.creditCard_payment("ccamex");
			honeyWell.order_Verifying();
		}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	
	
	@AfterTest
	public void clearBrowser()
	{
	//Common.closeAll();

	}
	
	@BeforeTest
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "Honeywell\\config.properties");
		  Login.signIn();
		 
		  
	  }

}
