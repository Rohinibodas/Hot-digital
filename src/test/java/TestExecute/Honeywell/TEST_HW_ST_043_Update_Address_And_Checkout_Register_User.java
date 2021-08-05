
package TestExecute.Honeywell;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Honeywell.Honeywellhelper;

import TestLib.Common;
import TestLib.Login;


public class TEST_HW_ST_043_Update_Address_And_Checkout_Register_User {
	String datafile = "Honeywell\\HoneywellTestData.xlsx";	
	Honeywellhelper honeyWell=new Honeywellhelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	
	public void Update_Address_And_Checkout_Register_User() throws Exception {

		try {
			
			honeyWell.verifyingHomePage();
		//.accept();
			honeyWell.loginHoneywell("AccountDetails");
			honeyWell.click_Airpurifiers();
			honeyWell.adding_product_toCart("productnameRegester");
			//honeyWell.clickAddtoBag();
			honeyWell.clickminicartButton();
			honeyWell.clickminicartcheckout();
			honeyWell.addDeliveryAddress_registerUser("ShippingAddress");
			//honeyWell.clickOnProceed();
			honeyWell.creditCard_payment("ccdiscover");
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