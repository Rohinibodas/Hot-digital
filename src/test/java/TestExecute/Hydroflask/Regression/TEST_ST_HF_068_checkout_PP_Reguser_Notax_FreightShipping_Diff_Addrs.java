package TestExecute.Hydroflask.Regression;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_HF_068_checkout_PP_Reguser_Notax_FreightShipping_Diff_Addrs {
	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper Hydro=new HydroHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	
	public void RegisterUserCheckout_Single_product_Notax_Freightshipping_Paypal_Different_billing_shippingaddress() throws Exception {

		try {
			Hydro.loginHydroflaskAccount("AccountDetails");
			Hydro.orderSubmit("Bottles");  
			
			Hydro.checkOut();
			Hydro.addfrieightDeliveryAddress_registerUser("No_TaxAddress");			
//			Hydro.edit_BillingAddress_RegisterUser("Billing_Address");
			Hydro.verifying_NoTax_field();
			Hydro.edit_BillingAddress_RegisterUser("Billing_Address");
			Hydro.payPal_Payment("PaypalDetails");
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
