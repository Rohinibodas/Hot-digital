package TestExecute.DryBar.Mobile;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestComponent.DryBar.DryBarMobile;
import TestLib.Common;
import TestLib.Login;

public class ApplyOfferCodeonCheckoutPage {
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarMobile drybar=new DryBarMobile(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void applyOfferCodeonCheckoutPage() throws Exception {
		try{ 
			 drybar.Accept();
			 drybar.verifyingHomePage();
			 drybar.Search_productname("ProductName");
			 drybar.Verify_PDP();
		  drybar.increaseProductQuantity("2");
		  drybar.clickAddtoBag();
		  drybar.clickminiCartButton();
		  drybar.clickCheckoutButton();
		  drybar.click_GuestCheckOut();
		  drybar.guestShippingAddress("ShippingAddress");
		  drybar.couponCode();
		  //drybar.order_Verifying();
	 // drybar.select_USPS_StandardGround_shippingMethod();
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
	/*@BeforeTest
	
	@Parameters({"device"})  
	  public void startTest(String Device) throws Exception {
		System.setProperty("configFile", "DryBar\\config.properties");
		Login.signIn("chrome",Device);
	  }
}*/
