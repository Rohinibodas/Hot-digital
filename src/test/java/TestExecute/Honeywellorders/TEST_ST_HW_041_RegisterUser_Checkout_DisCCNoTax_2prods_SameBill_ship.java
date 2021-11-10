package TestExecute.Honeywellorders;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Honeywell.Honeywellhelper;

import TestLib.Common;
import TestLib.Login;

public class TEST_ST_HW_041_RegisterUser_Checkout_DisCCNoTax_2prods_SameBill_ship {
	String datafile = "Honeywell\\HoneywellTestData.xlsx";	
	Honeywellhelper honeyWell=new Honeywellhelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	
	
	public void EST_HW_ST_010_RegisterUser_Checkout_MasterCC_NoTax_With_multiple_products_and_Different_Billing_and_shipping_Addres() throws Exception {

		try {
			
			honeyWell.verifyingHomePage();
			honeyWell.accept();
			honeyWell.loginHoneywell("AccountDetails");
			  honeyWell.click_fans();
			honeyWell.adding_product_toCart("productnameRegester1");
			honeyWell.Click_Heaters();
			honeyWell.adding_product_toCart("productnameRegester2");
			honeyWell.clickminicartButton();
			honeyWell.clickminicartcheckout();
		//	honeyWell.addDeliveryAddress_registerUser("ShippingAddress1");
			honeyWell.clickOnProceed();
			honeyWell.creditCard_payment("ccdiscover");
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
//		 System.setProperty("configFile", "Honeywell\\config.properties");
		  Login.signIn();
		 
		  
	  }

}

