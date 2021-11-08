package TestExecute.Hydroflask.Regression;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_HF_075_checkout_PP_Register_EmployeeDiscount_tax_standard_same_addresses {
	String datafile = "Hydroflask//HydroTestData.xlsx";
	HydroHelper Hydro = new HydroHelper(datafile);

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)

	public void checkout_PP_Register_EmployeeDiscount_tax_standard_same_addresses()
			throws Exception {

		try {  
			Hydro.loginHydroflaskAccount("Employlogins");
			Hydro.orderSubmit("Bottles");
			Hydro.checkOut();
//			Hydro.validating_Employ_Discount_forInlineProducts(65);
			Hydro.addDeliveryAddress_registerUser("Address");
			Hydro.verifyingTax_field();
			Hydro.payPal_Payment("PaypalDetails");

		
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


