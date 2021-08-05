package TestExecute.Honeywell;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Honeywell.Honeywellhelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_HW_MT_032_Validation_Of_Promocode {

	
	
	String datafile = "Honeywell\\HoneywellTestData.xlsx";	
	Honeywellhelper honeyWell=new Honeywellhelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)

	  public void Validation_Of_Promocode() {
			try {
				honeyWell.loginHoneywell("Promationcode");
				honeyWell.verifyingHomePage();
				honeyWell.accept();
				honeyWell.click_Airpurifiers();
				honeyWell.adding_product_toCart("productnameRegester");
				honeyWell.clickminicartButton();
				honeyWell.clickminicartcheckout();
				honeyWell.addDeliveryAddress_registerUser("ShippingAddress");
				honeyWell.CouponCodeinCheckoutpage("Promationcode");
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
