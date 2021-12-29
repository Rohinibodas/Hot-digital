package TestExecute.DryBar.SystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_DB_028_Travel_Size_Product_Checkout_with_MasterCC {
	
	
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  
  public void Travel_Size_Product_Checkout_with_MasterCC() {
		try {
			
			drybar.Accept();
			drybar.verifyingHomePage();
			drybar.Select_TravelSize_Category();
		    drybar.Select_TravelSize_product();
			drybar.clickAddtoBag();
		    drybar.clickminiCartButton();
		    drybar.clickCheckoutButton();
		    drybar.click_GuestCheckOut();
		    drybar.guestShippingAddress("ShippingAddress");
		    drybar.Click_PaymetricPaymentMethod();
		    drybar.creditCard_payment("CCmastercard");
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
		// System.setProperty("configFile", "DryBar\\config.properties");
		  Login.signIn();
		 
		  
	  }

}

