package TestExecute.DryBar.SystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;

public class DB_ST_024_OrderID_Verification {
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  
  public void To_verify_orderID() {
		try {
			
			  drybar.Accept();
			  drybar.verifyingHomePage();
			  drybar.navigateMyAccount();
			  drybar.loginApplication("AccountDetails");
			  drybar.clickHairProducts();
			  drybar.SelectShampoos();
			 // drybar.Accept();
			  drybar.Selectproduct();
			 // drybar.Accept();
			  drybar.Verify_PDP();
			  drybar.clickAddtoBag();
			  drybar.clickminiCartButton();
			  drybar.clickCheckoutButton();
			  drybar.addDeliveryAddress_registerUser("ShippingAddress");
			  drybar.Edit_BillingAddress_PaymetricPaymentMethod("BiillingAddress");
			  drybar.creditCard_payment("CCVisa");
			  drybar.order_Success();
			  drybar.order_verification();
			
			
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
