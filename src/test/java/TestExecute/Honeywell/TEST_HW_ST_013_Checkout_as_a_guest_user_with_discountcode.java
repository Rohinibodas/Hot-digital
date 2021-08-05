package TestExecute.Honeywell;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Honeywell.Honeywellhelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_HW_ST_013_Checkout_as_a_guest_user_with_discountcode {

	String datafile = "Honeywell\\HoneywellTestData.xlsx";	
	Honeywellhelper honeyWell=new Honeywellhelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)

	  public void couponcodeguest() {
			try {
				
				honeyWell.verifyingHomePage();
				//.accept();
				honeyWell.click_Airpurifiers();
				honeyWell.adding_product_toCart("productnameRegester");
				//honeyWell.clickAddtoBag();
				honeyWell.clickminicartButton();
				honeyWell.clickminicartcheckout();
				honeyWell.addDeliveryAddress_registerUser("ShippingAddress");
				honeyWell.CouponCodeinCheckoutpage("Promationcode");
				//honeyWell.order_Verifying();
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
