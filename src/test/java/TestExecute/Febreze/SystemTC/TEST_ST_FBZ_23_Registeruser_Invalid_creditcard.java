package TestExecute.Febreze.SystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.Febreze.FebrezeHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_FBZ_23_Registeruser_Invalid_creditcard {

	String datafile = "Febreze//FebrezeTestData.xlsx";
	FebrezeHelper febreze = new FebrezeHelper(datafile);

	@Test(priority = 1)
	public void RegisteruserInvalidcreditcard() {
		try {
			febreze.Acceptcookies();
			febreze.Login("AccountDetails");
			febreze.browsersearch("ProductName");
			febreze.productselection();
			febreze.Navigateminicart();
			febreze.NavigateMycartpage();
			febreze.checkoutpage();
			febreze.UsershippingAddress();
			febreze.InvalidpaymentDetails("InvalidCreditCard");;

		} catch (Exception | Error e) {
			Assert.fail();
		}
	}
	@AfterTest
	public void clearBrowser() {
		Common.closeAll();

	}

	@BeforeMethod
	public void startTest() throws Exception {
		//System.setProperty("configFile", "Febreze\\Config_Febreze_Staging.properties");
		Login.signIn();

	}

}

