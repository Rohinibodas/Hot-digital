package TestExecute.Honeywellorders;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Honeywell.Honeywellhelper;

import TestLib.Common;
import TestLib.Login;

public class TEST_HW_ST_006_Registereduser_Checkout_VisaCC_NoTax_with_Diff_Billing_and_Shipping {
	String datafile = "Honeywell\\HoneywellTestData.xlsx";	
	Honeywellhelper honeyWell=new Honeywellhelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	
	
	public void Registereduser_Checkout_VisaCC_NoTax_with_Diff_Billing_and_Shipping() throws Exception {

		try {
			
			honeyWell.verifyingHomePage();
			//honeyWell.accept();
			honeyWell.loginHoneywell("AccountDetails");
			  honeyWell.click_fans();
			honeyWell.adding_product_toCart("productnameRegester1");
			honeyWell.clickminicartButton();
			honeyWell.clickminicartcheckout();
			honeyWell.addDeliveryAddress_registerUser("ShippingAddress");
			//honeyWell.clickOnProceed();
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

