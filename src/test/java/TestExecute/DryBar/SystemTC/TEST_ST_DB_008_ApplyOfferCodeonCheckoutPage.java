package TestExecute.DryBar.SystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_DB_008_ApplyOfferCodeonCheckoutPage {
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void applyOfferCodeonCheckoutPage() throws Exception {
		 
		try{
			
			
		  drybar.Accept();
		  drybar.verifyingHomePage();
			 drybar.clickHairProducts();
			  drybar.SelectShampoos();
			  drybar.Selectproduct();
			 // drybar.Accept();
			  drybar.Verify_PDP();
		  drybar.increaseProductQuantity("2");
		  drybar.clickAddtoBag();
		  drybar.clickminiCartButton();
		  drybar.clickCheckoutButton();
		  drybar.click_GuestCheckOut();
		  drybar.Verify_FreeGift();
		  drybar.guestShippingAddress("ShippingAddress");
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
		// System.setProperty("configFile", "DryBar\\config.properties");
		  Login.signIn();
		 
		  
	  }
}
