package TestExecute.DryBar;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Login;

public class ApplyOfferCodeonCheckoutPage {
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void f() throws Exception {
		  drybar.clickHairProducts();
		  drybar.selectproduct("ProductName");
		  drybar.increaseProductQuantity("2");
		  drybar.clickAddtoBag();
		  drybar.clickminiCartButton();
		  drybar.clickCheckoutButton();
		  drybar.click_GuestCheckOut();
		  drybar.guestShippingAddress("ShippingAddress");
		  drybar.couponCode();
	 // drybar.select_USPS_StandardGround_shippingMethod();
  }
	
	@AfterTest
	public void clearBrowser()
	{
	//Common.closeAll();

	}
	

	@BeforeTest
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "DryBar\\config.properties");
		  Login.signIn();
		 
		  
	  }
}
