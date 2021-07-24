package TestExecute.DryBar.SystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class DB_ST_002_RegisteredUser_Checkout_visa_CreditCard {
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  
  public void registeredUser_Checkout_CreditCard() {
		try {
			  drybar.Accept();
			  drybar.verifyingHomePage();
			  drybar.navigateMyAccount();
			 // drybar.Accept();
			  drybar.loginApplication("AccountDetails");
			  drybar.clickHairProducts();
			  //drybar.Close_popup();
			  //drybar.Accept();
			 // Thread.sleep(4000);
			  drybar.SelectShampoos();
			  drybar.Selectproduct();
			 // drybar.Verify_PDP();
			  drybar.increaseProductQuantity("2");
			  drybar.clickAddtoBag();
			  drybar.clickminiCartButton();
			  drybar.clickCheckoutButton();
			  drybar.addDeliveryAddress_registerUser("ShippingAddress");
			    //drybar.click_Next();
			    //drybar.select_CC();
			  drybar.Edit_BillingAddress_PaymetricPaymentMethod("BiillingAddress");
			  drybar.creditCard_payment("CCVisa");
			  drybar.order_Success();
			
			
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
		 System.setProperty("configFile", "DryBar\\config.properties");
		  Login.signIn();
		 
		  
	  }

  
}
