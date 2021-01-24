package TestExecute.Revlon.Mob_SystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.revlon.RevelonMobHelper;
import TestLib.Common;
import TestLib.Login;

public class RHT_ST_Mob_GuestUser_CategoryCheckout_Paypal {

	String datafile = "revlon//RevlonTestData.xlsx";	
	RevelonMobHelper revlon=new RevelonMobHelper(datafile);
	
	@Test(priority=1)
	public void GuestUserCategoryPayPalCheckout() throws Exception {

		try {
			revlon.acceptPrivecy();
			revlon.categoryMenuItem();
			revlon.navigateMinicart();
			revlon.navigateCartPage();
			revlon.checkoutPage();
			revlon.navigateCheckoutGuest("Guest_shipping");
			revlon.updatePaypalPaymentAndSubmitOrder("Paypal");
		}
		catch (Exception e) {
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	
	/*@BeforeMethod
	@Parameters({"browser"}) 
	  public void startTest(String browser) throws Exception {
		System.setProperty("configFile", "Revelon\\config.properties");
		  Login.signIn(browser);
		  
	  }*/
	
	@BeforeMethod
	@Parameters({"device"})  
	  public void startTest(String Device) throws Exception {
		System.setProperty("configFile", "Revelon\\config.properties");
		  Login.signIn("chrome",Device);
		  
	  }
	
	/*@BeforeMethod
    //@Parameters({"device"})
      public void startTest() throws Exception {
        System.setProperty("configFile", "Revelon\\config.properties");
          Login.signIn("chrome","iPhone X");
         
      }*/
	
	@AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}

}

