package TestExecute.Honeywell;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Honeywell.Honeywellhelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_HW_ST_011_CheckoutDiscount {
	
	String datafile = "Honeywell\\HoneywellTestData.xlsx";	
	Honeywellhelper honeyWell=new Honeywellhelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)

	  public void couponcode() {
			try {
				honeyWell.loginHoneywell("Promationcode");
				honeyWell.verifyingHomePage();
				honeyWell.click_Airpurifiers();
				honeyWell.adding_product_toCart("productnameRegester");
				//honeyWell.clickAddtoBag();
				honeyWell.clickminicartButton();
				honeyWell.clickminicartcheckout();
				honeyWell.addDeliveryAddress_registerUser("ShippingAddress");
				honeyWell.CouponCodeinCheckoutpage("Promationcode");
				//honeyWell.creditCard_payment_Invalid("InvalidCC");
				//honeyWell.order_Verifying();
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

