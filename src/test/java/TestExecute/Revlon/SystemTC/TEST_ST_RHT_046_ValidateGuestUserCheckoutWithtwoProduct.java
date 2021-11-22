package TestExecute.Revlon.SystemTC;


import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.RevlonUk.RevlonUKHelper;
import TestComponent.revlon.RevelonHelper;
import TestLib.Common;
import TestLib.Login;
import junit.framework.Assert;

public class TEST_ST_RHT_046_ValidateGuestUserCheckoutWithtwoProduct {
	String datafile = "revlon//RevlonTestData.xlsx";	
	RevelonHelper revelon=new RevelonHelper(datafile);
	
	@Test(priority=1)
	public void Guestmultipleproducctselection() throws Exception {

		try {
			
			
			revelon.Newslettersignup();
			revelon.acceptPrivecy();
		
			revelon.searchProduct("productName");
			revelon.Twoproductselection();
			revelon.checkoutPage();
			revelon.navigateCheckoutGuest("Guest_shipping");
			revelon.Shippingmethod();
			revelon.updatePaymentAndSubmitOrder("PaymentDetails");
		}
		catch (Exception | Error e) {
			Assert.fail(e.getMessage());
		} 
	}

	@BeforeMethod
	  public void startTest() throws Exception {
		 //System.setProperty("configFile", "RevlonUS\\Config_RevlonUS_Staging.properties");
		  Login.signIn();
}
	@AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}


}
