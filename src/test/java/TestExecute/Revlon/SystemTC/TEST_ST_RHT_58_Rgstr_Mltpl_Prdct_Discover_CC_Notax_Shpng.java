package TestExecute.Revlon.SystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.revlon.RevelonHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_RHT_58_Rgstr_Mltpl_Prdct_Discover_CC_Notax_Shpng {

	

	String datafile = "revlon//RevlonTestData.xlsx";	
	RevelonHelper revelon=new RevelonHelper(datafile);
	
	@Test(priority=1)
	public void RegisterUserMultipleproductccheckoutDiscover_NOtax() throws Exception {

		try {
			revelon.Newslettersignup();
			revelon.acceptPrivecy();
			revelon.searchProduct("productName");
			revelon.Productselection();
			revelon.navigateMinicart();
			revelon.categoryMenuItemCurlingiron();
			revelon.navigateMinicart();
			revelon.navigateCartPage();
			revelon.checkoutPage();
			revelon.navigateCheckoutGuest("Address");
			revelon.FreeShippingmethod();
			revelon.TaxandShippingAmountvalidation();
			revelon.updatePaymentAndSubmitOrder("PaymentDetailsDiscoverCard");
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