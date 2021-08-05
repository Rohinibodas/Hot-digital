package TestExecute.Honeywellorders;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Honeywell.Honeywellhelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_HW_ST_014_RegisterUser_Checkout_MasterCC_Tax_With_Promotion_Single_products_Different_Billing_shipping_Address {


	String datafile = "Honeywell\\HoneywellTestData.xlsx";	
	Honeywellhelper honeyWell=new Honeywellhelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	
	
	public void registeredUser_Checkout_MasterCC_Tax_With_Promotion_Single_products() throws Exception {

		try {
			
			honeyWell.verifyingHomePage();
			//honeyWell.accept();
			honeyWell.loginHoneywell("AccountDetails");
			honeyWell.click_Airpurifiers();
			honeyWell.adding_product_toCart("productnameRegester");
		//	honeyWell.clickAddtoBag();
			honeyWell.clickminicartButton();
			honeyWell.clickminicartcheckout();
			honeyWell.addDeliveryAddress_registerUser("ShippingAddress1");
			//honeyWell.clickOnProceed();
			honeyWell.tax();
			honeyWell.CouponCodeinCheckoutpage("Promationcode");
			honeyWell.creditCard_payment("CCmastercard");
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
