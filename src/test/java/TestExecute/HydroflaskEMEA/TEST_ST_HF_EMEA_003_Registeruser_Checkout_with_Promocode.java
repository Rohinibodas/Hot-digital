package TestExecute.HydroflaskEMEA;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroFlaskEMEA;
import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_HF_EMEA_003_Registeruser_Checkout_with_Promocode {
	String datafile = "HydroflaskEMEA//HydroEMEATestData.xlsx";
	HydroFlaskEMEA Hydro = new HydroFlaskEMEA(datafile);

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)

	public void Register_promoCodeCheck() throws Exception {

		try {

			Hydro.loginHydroflaskAccount("AccountDetails");
			Hydro.orderSubmit("Bottles");
			Hydro.checkOut();
			Hydro.addDeliveryAddress_registerUser("Address");
			Hydro.promationCode("Promationcode");
			Hydro.updatePaymentAndSubmitOrder("VisaCC");
		} catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}

	@AfterTest
	public void clearBrowser() {
		Common.closeAll();

	}

	@BeforeTest
	public void startTest() throws Exception {
//		 System.setProperty("configFile", "HydroflaskEMEA\\config.properties");
		Login.signIn();
		Hydro.acceptPrivecy();
		// Hydro.ClosADD();*/

	}

}
