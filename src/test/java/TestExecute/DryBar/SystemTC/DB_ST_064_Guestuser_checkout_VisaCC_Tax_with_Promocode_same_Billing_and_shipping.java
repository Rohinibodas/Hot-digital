package TestExecute.DryBar.SystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;

public class DB_ST_064_Guestuser_checkout_VisaCC_Tax_with_Promocode_same_Billing_and_shipping {
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void Guestuser_checkout_VisaCC_Tax_with_Promocode_same_Billing_and_shipping() throws Exception {
		 
		try{
			
			drybar.Accept();
			  drybar.verifyingHomePage();
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
			  drybar.click_GuestCheckOut();
			  drybar.guestShippingAddress("ShippingAddress");
			  drybar.Verify_tax();
			  drybar.couponCode("couponCode2");
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
