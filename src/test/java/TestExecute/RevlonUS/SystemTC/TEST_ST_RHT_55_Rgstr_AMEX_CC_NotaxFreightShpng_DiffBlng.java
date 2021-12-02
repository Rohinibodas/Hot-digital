package TestExecute.RevlonUS.SystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.RevlonUS.RevelonHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_RHT_55_Rgstr_AMEX_CC_NotaxFreightShpng_DiffBlng {

	

	String datafile = "RevlonUS//RevlonTestData.xlsx";	
	RevelonHelper revelon=new RevelonHelper(datafile);
	
	@Test(priority=1)
	public void RegisterCheckoutAMEX_NotaxFreightShipping_DifferentBillingaddress() throws Exception {

		try {
			revelon.Newslettersignup();
			revelon.acceptPrivecy();
			revelon.loginRevlon("AccountDetails");
			revelon.navigateAddressBook();
			revelon.searchProduct("productName");
			revelon.Productselection();
			revelon.navigateMinicart();
			revelon.navigateCartPage();
			revelon.checkoutPage();
			revelon.clickaddnewaddress();
			revelon.RegisteruseraddNewAddress("taxonfreightAddress");
			revelon.Shippingmethod();
			revelon.noTaxonfrieght("NotaxonfreightAddress");
			revelon.DifferentBillingaddress();
			revelon.updatePaymentAndSubmitOrder("PaymentDetailsAMEXCard");
		}
		catch (Exception e) {
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
