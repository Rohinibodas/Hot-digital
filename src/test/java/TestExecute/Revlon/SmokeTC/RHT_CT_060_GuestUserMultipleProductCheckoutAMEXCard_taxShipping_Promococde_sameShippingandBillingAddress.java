package TestExecute.Revlon.SmokeTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.revlon.RevelonHelper;
import TestLib.Common;
import TestLib.Login;

public class RHT_CT_060_GuestUserMultipleProductCheckoutAMEXCard_taxShipping_Promococde_sameShippingandBillingAddress {

	String datafile = "revlon//RevlonTestData.xlsx";	
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
			revelon.navigateCheckoutGuest("Guest_shipping");
			revelon.FreeShippingmethod();
			revelon.TaxandShippingAmountvalidation();
			revelon.ValidatingPromocode("Promocode");
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
	
/*	@BeforeMethod
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
