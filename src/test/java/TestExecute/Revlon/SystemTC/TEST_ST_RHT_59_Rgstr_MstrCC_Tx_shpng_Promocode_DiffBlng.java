package TestExecute.Revlon.SystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.revlon.RevelonHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_RHT_59_Rgstr_MstrCC_Tx_shpng_Promocode_DiffBlng {

	String datafile = "revlon//RevlonTestData.xlsx";	
	RevelonHelper revelon=new RevelonHelper(datafile);
	
	@Test(priority=1)
	public void RegisterUserCheckoutPromocode_differentBA() throws Exception {

		try {
			revelon.Newslettersignup();
			revelon.acceptPrivecy();
			revelon.loginRevlon("AccountDetails");
			revelon.categoryMenuItemCurlingiron();
			revelon.navigateMinicart();
		    revelon.navigateCartPage();
			revelon.checkoutPage();
			revelon.navigateCheckout();
			revelon.clickaddnewaddress();
			revelon.RegisteruseraddNewAddress("taxonfreightAddress");
			revelon.Shippingmethod();
			revelon.TaxandShippingAmountvalidation("taxonfreightAddress");
			revelon.DifferentBillingaddress();
			revelon.ValidatingPromocode("Promocode");
			revelon.updatePaymentAndSubmitOrder("PaymentDetailsMasterCard");
			
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
		//Common.closeAll();

	}
}