package TestExecute.Redesign_Hottools;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Redesign_Hottools.Redesign_HottoolsHelper;
import TestLib.Login;

public class Test_HT_RD_GuestUser_Master_Checkout_Tax_Diff_Billing_Shipping_Adress {
	String datafile = "Redesign_Hottools//Redesign_HottoolsTestData.xlsx";	
	Redesign_HottoolsHelper Redesign_Hottools=new Redesign_HottoolsHelper(datafile);
	
	
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void createaccount() throws Exception {
		try{
			
			
			  Redesign_Hottools.Accept();
			  Redesign_Hottools.verifyingHomePage();
			  Redesign_Hottools.clickHair_Tools();
			  Redesign_Hottools.Selectproduct();
			  Redesign_Hottools.Verify_PDP();
			  Redesign_Hottools.clickAddtoBag();
			  Redesign_Hottools.clickminiCartButton();
			  Redesign_Hottools.clickCheckoutButton();
			  Redesign_Hottools.click_GuestCheckOut();
			  Redesign_Hottools.guestShipingAddress("ShippingAddress");
			  Redesign_Hottools.Click_PaymetricPaymentMethod();
			  Redesign_Hottools.Edit_BillingAddress_PaymetricPaymentMethod("BiillingAddress");
			  Redesign_Hottools.Verify_tax();
		      Redesign_Hottools.creditCard_payment("CCmastercard");
			  Redesign_Hottools.order_Success();
	
  }
	catch (Exception e) {
		e.printStackTrace();
		
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
		//System.setProperty("configFile", "Redesign_Hottools\\config.properties");
		  Login.signIn();
		 
		  
	  }

}
