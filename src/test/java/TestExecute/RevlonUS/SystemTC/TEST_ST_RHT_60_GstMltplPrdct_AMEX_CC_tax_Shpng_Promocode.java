package TestExecute.RevlonUS.SystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.RevlonUS.RevelonHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_RHT_60_GstMltplPrdct_AMEX_CC_tax_Shpng_Promocode {

	String datafile = "RevlonUS//RevlonTestData.xlsx";	
	RevelonHelper revelon=new RevelonHelper(datafile);
	
	@Test(priority=1)
	public void RegisterUserCheckoutAMEX_Promocode() throws Exception {

		try {
			revelon.Newslettersignup();
	     	revelon.acceptPrivecy();
	     	revelon.searchProduct("Promocode");
			revelon.Productselection();
			revelon.navigateMinicart();
			revelon.categoryMenuItemCurlingiron();
			revelon.navigateMinicart();
			revelon.navigateCartPage();
			revelon.checkoutPage();
			revelon.navigateCheckoutGuest("taxonfreightAddress");
			revelon.Shippingmethod();
			revelon.TaxandShippingAmountvalidation("taxonfreightAddress");
			revelon.ValidatingPromocode("Promocode");
			revelon.updatePaymentAndSubmitOrder("PaymentDetails");
			
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
