package TestExecute.RevlonUS.SystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.RevlonUS.RevelonHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_RHT_047_ValidateRegisteredUserCheckoutwithhomepageAddtocart {

	String datafile = "RevlonUS//RevlonTestData.xlsx";
	RevelonHelper revelon = new RevelonHelper(datafile);

	@Test(priority = 1)
	public void Registeruserhomepageaddtocart() throws Exception {

		try {
			Thread.sleep(3000);
			revelon.Newslettersignup();
			revelon.acceptPrivecy();
			revelon.loginRevlon("AccountDetails");
			revelon.homepageaddtocart();

			revelon.navigateCartPage();
			revelon.checkoutPage();
			
			revelon.Shippingmethod();
			revelon.updatePaymentAndSubmitOrder("PaymentDetails");
		} catch (Exception e) {
			Assert.fail(e.getMessage(), e);
		}
	}

	@BeforeMethod
	  public void startTest() throws Exception {
		// System.setProperty("configFile", "RevlonUS\\Config_RevlonUS_Staging.properties");
		  Login.signIn();
}
	@AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}

}
