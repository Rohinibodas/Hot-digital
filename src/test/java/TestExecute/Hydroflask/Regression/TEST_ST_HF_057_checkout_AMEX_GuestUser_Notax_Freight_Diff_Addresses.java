package TestExecute.Hydroflask.Regression;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_HF_057_checkout_AMEX_GuestUser_Notax_Freight_Diff_Addresses {
	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper Hydro=new HydroHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	
	public void GuestUserCheckout_AmexCc_Single_product_Notax_Freightshipping_Different_billing_shippingaddress() throws Exception {

		try {
			  
			
			Hydro.orderSubmit("Bottles");
			Hydro.checkOut();
			Hydro.addfrieightDeliveryAddress_guestuser("No_TaxAddress");
			Hydro.check_box();
			Hydro.Edit_BillingAddress("GustUserOrderdetiles");
			Hydro.verifying_NoTax_field();
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
//		 System.setProperty("configFile", "Hydroflask\\config.properties");
		  Login.signIn();
		  Hydro.acceptPrivecy();
		  Hydro.ClosADD();		  
	  }

}
