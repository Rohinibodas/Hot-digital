package TestExecute.DryBar.SystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;

public class DB_ST_005_GuestCheckoutwith_invalid_CC_Credentials {
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);

  @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void guestCheckoutwith_invalid_CC_Credentials() {
	  try{
		    drybar.Accept();
			drybar.verifyingHomePage();
			drybar.clickHairProducts();
			drybar.SelectShampoos();
		    drybar.Selectproduct();
			drybar.Verify_PDP(); 
			drybar.clickAddtoBag();
		    drybar.clickminiCartButton();
		    drybar.clickCheckoutButton();
		    drybar.click_GuestCheckOut();
		    drybar.guestShippingAddress("ShippingAddress");
	        drybar.Edit_BillingAddress_PaymetricPaymentMethod("BiillingAddress");
	        drybar.creditCard_payment_invalid_CC("InvalidPaymentDetails");

	  
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
 		 System.setProperty("configFile", "DryBar\\config.properties");
 		  Login.signIn();
 		 
 		  
 	  }
}