package TestExecute.DryBar.SystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;

public class DB_ST_061_RegisterUser_Checkout_DiscoverCC_NoTax_Multiple_Products_with_same_Billing_and_shipping {
	
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  
  public void RegisterUser_Checkout_DiscoverCC_NoTax_Multiple_Products_with_same_Billing_and_shipping() {
		try {
			
			drybar.Accept();
			drybar.verifyingHomePage();
			drybar.navigateMyAccount();
			drybar.loginApplication("AccountDetails");
			drybar.clickHairProducts();
			drybar.SelectShampoos();
		    drybar.Selectproduct(); 
			drybar.Verify_PDP();
			drybar.clickAddtoBag();
			drybar.Search_productname("ProductName");
			drybar.Click_View_Product();
			drybar.clickAddtoBag();
		    drybar.clickminiCartButton();
		    drybar.clickCheckoutButton();
		   	drybar.addDeliveryAddress_for_registerUser("NoTaxShippingAddress");
		    drybar.select_USPS_StandardGround_shippingMethod();
			drybar.click_Next();
			drybar.click_Same_Billing_Address();
		    drybar.creditCard_payment("ccdiscover");
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
