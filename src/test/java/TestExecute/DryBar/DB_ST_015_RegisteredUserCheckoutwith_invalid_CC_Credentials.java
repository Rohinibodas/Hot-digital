package TestExecute.DryBar;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class DB_ST_015_RegisteredUserCheckoutwith_invalid_CC_Credentials {
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void registeredUserCheckoutwith_invalid_CC_Credentials() {
		try {
			  drybar.Accept();
			  drybar.navigateMyAccount();
			  drybar.loginApplication("AccountDetails");
			 // drybar.Guestuser_PDP();
			  drybar.Search_productname("ProductName");
			  drybar.Verify_PDP();
			 // drybar.clickHairProducts();
			  //drybar.selectproduct("ProductName");
			  drybar.increaseProductQuantity("2");
			  drybar.clickAddtoBag();
			  drybar.clickminiCartButton();
			  drybar.clickCheckoutButton();
			  drybar.addDeliveryAddress_registerUser("ShippingAddress");
			 // drybar.select_USPS_StandardGround_shippingMethod();
			  drybar.Edit_BillingAddress_PaymetricPaymentMethod("BiillingAddress");
			  drybar.creditCard_payment_invalid_CC("InvalidPaymentDetails");

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
