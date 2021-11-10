
package TestExecute.Honeywell;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Honeywell.Honeywellhelper;

import TestLib.Common;
import TestLib.Login;


public class TEST_ST_HW_029_Update_Address_And_Checkout_Register_User {
	String datafile = "Honeywell\\HoneywellTestData.xlsx";	
	Honeywellhelper honeyWell=new Honeywellhelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	
	public void Update_Address_And_Checkout_Register_User() throws Exception {

		try {
			
			honeyWell.verifyingHomePage();
			honeyWell.accept();
			honeyWell.loginHoneywell("AccountDetails");
			honeyWell.click_fans();
			honeyWell.adding_product_toCart("productnameRegester1");

			honeyWell.clickminicartButton();
			honeyWell.clickminicartcheckout();
			
			honeyWell.addDeliveryAddress_registerUser("ShippingAddress");
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
//		 System.setProperty("configFile", "Honeywell\\config.properties");
		  Login.signIn();
		 
		  
	  }

}
