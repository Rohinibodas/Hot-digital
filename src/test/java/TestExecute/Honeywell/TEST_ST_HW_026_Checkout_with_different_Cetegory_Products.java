package TestExecute.Honeywell;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Honeywell.Honeywellhelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_HW_026_Checkout_with_different_Cetegory_Products {
	String datafile = "Honeywell\\HoneywellTestData.xlsx";	
	Honeywellhelper honeyWell=new Honeywellhelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	
	
	public void checkoutWithDifferentCetegoryProducts() throws Exception {

		try {
			
			honeyWell.verifyingHomePage();
			honeyWell.accept();
			honeyWell.loginHoneywell("AccountDetails");
			honeyWell.click_fans();
			honeyWell.adding_product_toCart("productnameRegester1");
			//honeyWell.clickAddtoBag();
			honeyWell.click_Airpurifiers();
			honeyWell.adding_product_toCart("productnameRegester");
			//honeyWell.clickAddtoBag();
			honeyWell.clickminicartButton();
			honeyWell.clickminicartcheckout();
			//honeyWell.addDeliveryAddress_registerUser("ShippingAddress");
			honeyWell.clickOnProceed();
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
		// System.setProperty("configFile", "Honeywell\\config.properties");
		  Login.signIn();
		 
		  
	  }


}
