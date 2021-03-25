package TestExecute.DryBar.Mobile;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestComponent.DryBar.DryBarMobile;
import TestLib.Common;
import TestLib.Login;

public class Registereduser_checkout_with_Master_CC {
	
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarMobile drybar=new DryBarMobile(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  
  public void registeredUser_Checkout_MasterCreditCard() {
		try {
			drybar.Accept();
			drybar.verifyingHomePage();
			   drybar.clickMyaccount();
			   drybar.loginApplication("AccountDetails");
			
			 //drybar.Accept();
			  drybar.clickHairProducts();
			  drybar.selectproduct("ProductName");
			  //drybar.Verify_PDP();
			  drybar.increaseProductQuantity("2");
			  drybar.clickAddtoBag();
			  drybar.clickminiCartButton();
			  drybar.clickCheckoutButton();
			  drybar.addDeliveryAddress_registerUser("ShippingAddress");
			  drybar.select_USPS_StandardGround_shippingMethod();
			  drybar.Edit_BillingAddress_PaymetricPaymentMethod("BiillingAddress");
			  drybar.MastercreditCard_payment("PaymentDetails");
			  drybar.order_Verifying();
			//  drybar.creditCard_payment_invalid_CC("InvalidPaymentDetails");
			
			
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

}
