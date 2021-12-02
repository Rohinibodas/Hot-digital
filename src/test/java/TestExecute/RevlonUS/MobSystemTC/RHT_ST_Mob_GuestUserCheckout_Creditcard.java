package TestExecute.RevlonUS.MobSystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.RevlonUS.RevelonMobHelper;
import TestLib.Common;
import TestLib.Login;

public class RHT_ST_Mob_GuestUserCheckout_Creditcard {
	String datafile = "revlon//RevlonTestData.xlsx";	
	RevelonMobHelper revlon=new RevelonMobHelper(datafile);
	
	@Test(priority=1)
	public void GuestCheckout() throws Exception {

		try {
			revlon.acceptPrivecy();
			revlon.searchProduct("productName");
			revlon.Productselection();
			revlon.navigateMinicart();
			revlon.navigateCartPage();
			revlon.checkoutPage();
			revlon.navigateCheckoutGuest("Guest_shipping");
			revlon.updatePaymentAndSubmitOrder("PaymentDetails");
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
          Login.signIn("chrome","iPad");
         
      }*/
      
	@AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}

}


