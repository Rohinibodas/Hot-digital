package TestExecute.Revlon.MobSystemTC;

import org.testng.annotations.Test;

import TestComponent.revlon.RevelonHelper;
import TestComponent.revlon.RevelonMobHelper;
import TestLib.Common;
import TestLib.Login;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class RHT_ST_Mob_RegisterUserCheckout_Creditcard {
	String datafile = "revlon//RevlonTestData.xlsx";	
	RevelonMobHelper revlon=new RevelonMobHelper(datafile);
	
	@Test(priority=1)
	public void RegisterUserCheckout() throws Exception {

		try {
			revlon.slider();
			revlon.Loginpage();
			revlon.loginRevlon("AccountDetails");
			revlon.searchProduct("productName");
			revlon.Productselection();
			revlon.navigateMinicart();
			revlon.navigateCartPage();
			revlon.checkoutPage();
			revlon.navigateCheckout();
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
