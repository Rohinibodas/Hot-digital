package TestExecute.DryBar.Mobile;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestComponent.DryBar.DryBarMobile;
import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class RegisteredUserCheckoutwith_invalid_CC_Credentials {
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarMobile drybar=new DryBarMobile(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void registeredUserCheckoutwith_invalid_CC_Credentials() {
		try {
			drybar.Accept();
			   drybar.clickMyaccount();
			   drybar.loginApplication("AccountDetails");
			   drybar.Search_productname("ProductName");
			  // drybar.Accept();
				  drybar.Verify_PDP();
			  drybar.increaseProductQuantity("2");
			  drybar.clickAddtoBag();
			  drybar.clickminiCartButton();
			  drybar.clickCheckoutButton();
			  drybar.addDeliveryAddress_registerUser("ShippingAddress");
			 // drybar.Edit_BillingAddress_PaymetricPaymentMethod("BiillingAddress");
			  drybar.creditCard_payment_invalid_CC("InvalidPaymentDetails");
			 // drybar.select_USPS_StandardGround_shippingMethod();
			  //drybar.Edit_BillingAddress_PaymetricPaymentMethod("BiillingAddress");
			   /*drybar.Accept();
			   drybar.clickMyaccount();
			   drybar.loginApplication("AccountDetails");
			   drybar.Search_productname("ProductName");
			  // drybar.Accept();
			 drybar.Verify_PDP();
			  drybar.increaseProductQuantity("2");
			  drybar.clickAddtoBag();
			  drybar.clickminiCartButton();
			  drybar.clickCheckoutButton();
			  drybar.addDeliveryAddress_registerUser("ShippingAddress");
			  drybar.select_USPS_StandardGround_shippingMethod();
			  drybar.Edit_BillingAddress_PaymetricPaymentMethod("BiillingAddress");
			  drybar.creditCard_payment_invalid_CC("InvalidPaymentDetails");*/

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
	

	@BeforeMethod
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "DryBar\\config.properties");
		 Login.signIn("chrome","iPad");
	 }
	/*@BeforeTest
	@Parameters({"device"})  
	  public void startTest(String Device) throws Exception {
		System.setProperty("configFile", "DryBar\\config.properties");
		Login.signIn("chrome",Device);
	  
	}*/
}
