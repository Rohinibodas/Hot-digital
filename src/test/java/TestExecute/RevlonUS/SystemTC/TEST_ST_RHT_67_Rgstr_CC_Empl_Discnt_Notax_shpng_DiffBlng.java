package TestExecute.RevlonUS.SystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.RevlonUS.RevelonHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_RHT_67_Rgstr_CC_Empl_Discnt_Notax_shpng_DiffBlng {

	String datafile = "RevlonUS//RevlonTestData.xlsx";
	RevelonHelper revelon = new RevelonHelper(datafile);

	@Test(priority = 1)
	public void UserCheckout_Employeediscount_Notaxshipping_DifferentBillingaddress() throws Exception {

		try {
			revelon.Newslettersignup();
			revelon.acceptPrivecy();
			revelon.loginRevlon("EmployeeDetails");
			revelon.searchProduct("productName");
			revelon.Productselection();
			revelon.navigateMinicart();
			revelon.navigateCartPage();
			revelon.checkoutPage();
			revelon.clickaddnewaddress();
			revelon.RegisteruseraddNewAddress("NotaxAddress");
			revelon.Shippingmethod();
			revelon.TaxandShippingAmountvalidation("NotaxAddress");
			revelon.DifferentBillingaddress();
			revelon.updatePaymentAndSubmitOrder("PaymentDetailsAMEXCard");

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
