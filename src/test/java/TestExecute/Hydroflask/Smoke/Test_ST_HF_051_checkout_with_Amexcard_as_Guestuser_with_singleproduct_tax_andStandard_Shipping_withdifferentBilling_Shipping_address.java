package TestExecute.Hydroflask.Smoke;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;


public class Test_ST_HF_051_checkout_with_Amexcard_as_Guestuser_with_singleproduct_tax_andStandard_Shipping_withdifferentBilling_Shipping_address {
	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper Hydro=new HydroHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	
	public void GuestUserCheckout_Amex_Singleproduct_tax_Standardshipping_different_billing_shippingaddress() throws Exception {

		try {
			Hydro.orderSubmit("Bottles");
			Hydro.checkOut();
			Hydro.addDeliveryAddress("Address");
			Hydro.check_box();
			Hydro.Edit_BillingAddress("GustUserOrderdetiles");
			Hydro.verifyingTax_field();
			Hydro.addPaymentDetails("Amexcard");
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	
	
	@AfterTest
	public void clearBrowser()
	{
     Common.closeAll();

	}
	
	@BeforeTest
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "Hydroflask\\config.properties");
		  Login.signIn();
		  Hydro.acceptPrivecy();
		  Hydro.ClosADD();		  
	  }

}
