package TestExecute.RevlonUK.SmokeTC;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.RevlonUk.RevlonUKHelper;
import TestLib.Common;
import TestLib.Login;

public class RUK_ST_ValidateRegisteredUserIncreaseProductQTY {
	String datafile = "revlonUK//RevlonUKTestData.xlsx";	
	RevlonUKHelper revlon=new RevlonUKHelper(datafile);
	
	
	@Test(priority=1)

	public void Registeruserincreaseproductquantity() throws Exception {
		revlon.loginRevlonUK("AccountDetails");
		revlon.SearchProduct("productName");
		revlon.Productselection();
		revlon.navigateMinicart();
		revlon.increaseproductquantity("productName");
		revlon.checkoutPage();
		revlon.navigateCheckout();
		revlon.updatePaymentAndSubmitOrder("PaymentDetails");
		
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
