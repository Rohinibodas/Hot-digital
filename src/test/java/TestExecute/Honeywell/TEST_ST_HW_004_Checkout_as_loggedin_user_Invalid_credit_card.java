package TestExecute.Honeywell;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Honeywell.Honeywellhelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_HW_004_Checkout_as_loggedin_user_Invalid_credit_card {
	String datafile = "Honeywell\\HoneywellTestData.xlsx";	
	Honeywellhelper honeyWell=new Honeywellhelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	
	
  public void In_valid_credit_card_LoginUser() {
		try {
			
			honeyWell.verifyingHomePage();
			honeyWell.accept();
			honeyWell.loginHoneywell("AccountDetails");
			honeyWell.Click_Heaters();
			honeyWell.adding_product_toCart("productnameRegester2");
//			honeyWell.clickAddtoBag();
			honeyWell.clickminicartButton();
			honeyWell.clickminicartcheckout();
//			honeyWell.addDeliveryAddress_registerUser("ShippingAddress");
			honeyWell.clickOnProceed();
			honeyWell.creditCard_payment_Invalid("InvalidCC");
			//honeyWell.order_Verifying();
		}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	
	
	@AfterTest
	public void clearBrowser()
	{
//	Common.closeAll();

	}
	
	@BeforeTest
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "Honeywell\\config.properties");
		  Login.signIn();
		 
		  
	  }



  }

