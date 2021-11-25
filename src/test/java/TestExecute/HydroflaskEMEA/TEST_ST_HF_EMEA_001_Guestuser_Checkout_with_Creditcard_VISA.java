package TestExecute.HydroflaskEMEA;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroFlaskEMEA;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_HF_EMEA_001_Guestuser_Checkout_with_Creditcard_VISA {
	String datafile = "HydroflaskEMEA//HydroEMEATestData.xlsx";
	HydroFlaskEMEA Hydro = new HydroFlaskEMEA(datafile);

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)

	public void gustCheckoutwithCreaditcard_Visa() {
		try {

			Hydro.orderSubmit("Bottles");
			Hydro.checkOut();
			Hydro.addDeliveryAddress("Address");
			Hydro.updatePaymentAndSubmitOrder("VisaCC");

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
//		 System.setProperty("configFile", "HydroflaskEMEA\\config.properties");
		Login.signIn();
		Hydro.acceptPrivecy();
//	  Hydro.ClosADD();

	}

}
