package TestExecute.RevlonUK.SmokeTC;

import org.testng.annotations.Test;

import TestComponent.RevlonUk.RevlonUKHelper;
import TestComponent.revlon.RevelonHelper;
import TestLib.Common;
import TestLib.Login;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class RHT_ST_GuestUserCheckoutCreditCard {
	String datafile = "revlonUK//RevlonUKTestData.xlsx";	
	RevlonUKHelper revlon=new RevlonUKHelper(datafile);
	
	
	@Test(priority=1)
  public void Guestusercheckout() {
		try {
			revlon.SearchProduct("productName");
			revlon.Productselection();
			revlon.navigateMinicart();
			revlon.checkoutPage();
			revlon.navigateCheckoutGuest("Guest_shipping");
			revlon.updatePaymentAndSubmitOrder("PaymentDetails");
		}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		} 
		
	  
  }
	@BeforeMethod
	@Parameters({"browser"}) 
	  public void startTest(String browser) throws Exception {
		System.setProperty("configFile", "RevlonUK\\config.properties");
		  Login.signIn(browser);
		  
	  }
	
	/*@BeforeMethod
	@Parameters({"browser"})  
	  public void startTest() throws Exception {
		System.setProperty("configFile", "RevlonUK\\config.properties");
		  Login.signIn("chrome");
		  
	  }*/
	
	@AfterTest
	public void clearBrowser()
	{
		Common.closeAll();
	}

}
