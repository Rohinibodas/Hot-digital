package TestExecute.Honeywell;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Honeywell.Honeywellhelper;
import TestLib.Common;
import TestLib.Login;

public class SearchProduct {
	String datafile = "Honeywell\\HoneywellTestData.xlsx";	
	Honeywellhelper honeyWell=new Honeywellhelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	
 
  public void searchproduct() {
		try {
			honeyWell.loginHoneywell("AccountDetails");
		honeyWell.searchProduct("ProductName");
		honeyWell.clickminicartButton();
		honeyWell.click_View_editcart();
		honeyWell.changeQuntity_UpdateProduct("2");
		honeyWell.clickCheckoutButton_minicart();
		honeyWell.addDeliveryAddress_registerUser("ShippingAddress");
		honeyWell.creditCard_payment("ccamex");
		honeyWell.order_Verifying();
		}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		} 
		
  }
  
  
	
	@AfterTest
	public void clearBrowser()
	{
	//Common.closeAll();

	}
	
	@BeforeTest
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "Honeywell\\config.properties");
		  Login.signIn();
		 
		  
	  }
}
