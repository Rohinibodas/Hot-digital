
package TestExecute.Honeywellorders;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Honeywell.Honeywellhelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_HW_045_GuestUser_Checkout_VISACCTax_1prod_SameBill_Ship {

	
	String datafile = "Honeywell\\HoneywellTestData.xlsx";	
	Honeywellhelper honeyWell=new Honeywellhelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	
	
	public void GuestUser_Checkout_VISACC_Tax() throws Exception {

		try {
			
			honeyWell.verifyingHomePage();
			honeyWell.accept();
			honeyWell.click_fans();
			honeyWell.adding_product_toCart("productnameRegester1");
               honeyWell.clickminicartButton();
			honeyWell.clickminicartcheckout();
			honeyWell.guestShippingAddress("ShippingAddress1");
			honeyWell.tax();
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
