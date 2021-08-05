package TestExecute.Honeywellorders;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Honeywell.Honeywellhelper;

import TestLib.Common;
import TestLib.Login;

public class TEST_HW_ST_013_GuestUser_Checkout_VisaCC_Tax_with_Promotion_and_Single_products_with_Same_Billing_and_shipping_Address {
	String datafile = "Honeywell\\HoneywellTestData.xlsx";	
	Honeywellhelper honeyWell=new Honeywellhelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	
	
	public void tEST_HW_ST_013_GuestUser_Checkout_VisaCC_Tax_with_Promotion_and_Single_products_with_Same_Billing_and_shipping_Address() throws Exception {

		try {
			
			honeyWell.verifyingHomePage();
			//honeyWell.accept();
			//honeyWell.loginHoneywell("AccountDetails");
			honeyWell.click_fans();
			honeyWell.adding_product_toCart("productnameRegester1");
			honeyWell.click_Airpurifiers();
			honeyWell.adding_product_toCart("productnameRegester");
			honeyWell.clickminicartButton();
			honeyWell.clickminicartcheckout();
			honeyWell.guestShippingAddress("ShippingAddress1");
			//honeyWell.clickOnProceed();
			honeyWell.tax();
			honeyWell.CouponCodeinCheckoutpage("Promationcode");
			honeyWell.creditCard_payment("CCVisa");
		
			honeyWell.order_Verifying();
		}
		catch (Exception e) {
			e.printStackTrace();
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

