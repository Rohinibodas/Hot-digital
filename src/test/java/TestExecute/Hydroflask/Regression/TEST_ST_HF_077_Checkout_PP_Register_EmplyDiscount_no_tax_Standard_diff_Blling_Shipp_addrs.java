package TestExecute.Hydroflask.Regression;
 
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_HF_077_Checkout_PP_Register_EmplyDiscount_no_tax_Standard_diff_Blling_Shipp_addrs {
	String datafile = "Hydroflask//HydroTestData.xlsx";
	HydroHelper Hydro = new HydroHelper(datafile);

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)

	public void Checkout_PP_Register_EmplyDiscount_no_tax_Standard_diff_Blling_Shipp_addrs()
			throws Exception {

		try {  
			Hydro.loginHydroflaskAccount("Employlogins");
			Hydro.orderSubmit("Bottles");
			Hydro.checkOut();
//			Hydro.validating_Employ_Discount_forInlineProducts(65);
			Hydro.addDeliveryAddress_registerUser("No_TaxAddress_2");
			Hydro.verifying_NoTax_field();
			Hydro.edit_BillingAddress_RegisterUser("Billing_Address");
			Hydro.updatePaymentAndSubmitOrder("PaymentDetails");
			
		} catch (Exception e) {
			e.printStackTrace();

			Assert.fail(e.getMessage(), e);
		}
	}

	@AfterTest
	public void clearBrowser() {
	 Common.closeAll();

	}

	@BeforeTest
	public void startTest() throws Exception {
//		System.setProperty("configFile", "Hydroflask\\config.properties");
		Login.signIn();
		Hydro.acceptPrivecy();
		Hydro.ClosADD();

	}

}

