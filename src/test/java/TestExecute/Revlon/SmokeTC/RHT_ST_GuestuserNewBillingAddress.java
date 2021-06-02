package TestExecute.Revlon.SmokeTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.revlon.RevelonHelper;
import TestLib.Common;
import TestLib.Login;

public class RHT_ST_GuestuserNewBillingAddress {
	String datafile = "revlon//RevlonTestData.xlsx";	
	RevelonHelper revelon=new RevelonHelper(datafile);
	
	@Test(priority=1)
	public void Guesthomepageaddtocart() throws Exception {

	
	
	try {
		revelon.Newslettersignup();
		revelon.acceptPrivecy();
		revelon.searchProduct("productName");
		revelon.Productselection();
		revelon.navigateMinicart();
		revelon.navigateCartPage();
		
		revelon.checkoutPage();
		revelon.navigateCheckoutGuest("Guest_shipping");
		revelon.userBillingaddress();
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
/*
@BeforeMethod
@Parameters({"browser"})  
  public void startTest() throws Exception {
	System.setProperty("configFile", "Revelon\\config.properties");
	  Login.signIn("chrome");
	  
  }
*/
@AfterTest
public void clearBrowser()
{
	Common.closeAll();

}

}
