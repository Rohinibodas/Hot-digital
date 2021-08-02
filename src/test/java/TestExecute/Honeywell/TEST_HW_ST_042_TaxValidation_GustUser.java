
package TestExecute.Honeywell;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Honeywell.Honeywellhelper;
import TestLib.Common;
import TestLib.Login;

 

public class TEST_HW_ST_042_TaxValidation_GustUser {
    String datafile = "Honeywell\\Taxaddresscode.xlsx";    
    Honeywellhelper honeyWell=new Honeywellhelper(datafile);
    @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
    
    public void taxValidation() throws Exception {

 

        try {
            
            honeyWell.verifyingHomePage();
           // honeyWell.loginHoneywell("AccountDetails");
            honeyWell.click_fans();
            honeyWell.adding_product_toCart("ProductName");
            //honeyWell.adding_product_toCart("productnameRegester");
            honeyWell.clickminicartButton();
            honeyWell.clickminicartcheckout();
           // honeyWell.addDeliveryAddress_registerUser("Register (Michigan2)");
            honeyWell.guestShippingAddress("Guest (Michigan)");
            honeyWell.Taxcalucaltion("Guest (Michigan)");
            honeyWell.creditCard_payment("CCVisa");
            honeyWell.order_Verifying();
        }
        catch (Exception e) {
            
            Assert.fail(e.getMessage(), e);
        } 
    }
    
    
    
    @AfterTest
    public void clearBrowser()
    {
   // Common.closeAll();

 

    }
    
    @BeforeTest
      public void startTest() throws Exception {
         System.setProperty("configFile", "Honeywell\\config.properties");
          Login.signIn();
         
          
      }

 

}