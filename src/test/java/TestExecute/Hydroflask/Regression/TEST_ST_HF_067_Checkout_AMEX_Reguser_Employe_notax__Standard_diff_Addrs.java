package TestExecute.Hydroflask.Regression;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_HF_067_Checkout_AMEX_Reguser_Employe_notax__Standard_diff_Addrs {
	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper Hydro=new HydroHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	
	public void checkoutwith_AMEXcard_Registeruser_EmployeeDiscount_notax__StandardShipping_differentBllingShippingaddress() throws Exception {

		try {
			
			Hydro.loginHydroflaskAccount("Employlogins");
			Hydro.orderSubmit("Bottles");
			Hydro.checkOut();
//			Hydro.validating_Employ_Discount_forInlineProducts(65);

			Hydro.addDeliveryAddress_registerUser("No_TaxAddress");
			Hydro.verifyingTax_field();
			Hydro.edit_BillingAddress_RegisterUser("Billing_Address");
			Hydro.updatePaymentAndSubmitOrder("Amexcard");
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
