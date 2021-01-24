package TestExecute.Revlon.SmokeTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.revlon.RevelonHelper;
import TestLib.Common;
import TestLib.Login;

public class RHT_ST_RegCategory_Checkout {
	
	String datafile = "revlon//RevlonTestData.xlsx";	
	RevelonHelper revelon=new RevelonHelper(datafile);
	
	@Test(priority=1)
	public void UserCategoryCreditCheckout() throws Exception {

		try {
			revelon.loginRevlon("AccountDetails");
			revelon.categoryMenuItem();
			revelon.navigateMinicart();
			revelon.navigateCartPage();
			revelon.checkoutPage();
			revelon.navigateCheckout();
			revelon.updatePaymentAndSubmitOrder("PaymentDetails");
		}
		catch (Exception e) {
			Assert.fail(e.getMessage(), e);
		} 
	}

	@BeforeMethod
	@Parameters({"browser"}) 
	  public void startTest(String browser) throws Exception {
		System.setProperty("configFile", "Revelon\\config.properties");
		  Login.signIn(browser);
		  
	  }
	
	/*@BeforeMethod
	//@Parameters({"browser"})  
	  public void startTest() throws Exception {
		System.setProperty("configFile", "Revelon\\config.properties");
		  Login.signIn("edge");
		  
	  }*/
	
	@AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}

}


