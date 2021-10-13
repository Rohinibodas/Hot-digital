package TestExecute.Hydroflask.Regression;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_HF_060_checkout_MASTER_RegUser_MultiProduct_Notax_Standard_Diff_Addrs {
	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper Hydro=new HydroHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	
	public void RegisterUserCheckout_MasterCc_MultiProduct_Notax_Standardshipping_Different_Billing_shippingaddress() throws Exception {

		try {
			Hydro.loginHydroflaskAccount("AccountDetails");
			Hydro.orderSubmit("Bottles");
			Hydro.searchproduct_addtocart("32 oz Tumbler Lid");
			Hydro.checkOut();
			Hydro.addDeliveryAddress_registerUser("No_TaxAddress");
			Hydro.edit_BillingAddress_RegisterUser("Billing_Address");
			Hydro.verifying_NoTax_field();
			Hydro.addPaymentDetails("Ccmastercard");
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
