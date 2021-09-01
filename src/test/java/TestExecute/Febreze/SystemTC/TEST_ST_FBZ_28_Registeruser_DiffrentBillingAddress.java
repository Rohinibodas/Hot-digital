package TestExecute.Febreze.SystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.Febreze.FebrezeHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_FBZ_28_Registeruser_DiffrentBillingAddress {
	String datafile = "Febreze//FebrezeTestData.xlsx";	
	FebrezeHelper febreze=new FebrezeHelper(datafile);
	@Test(priority=1)
	public void registeruserdifferentbillingaddress() {
		try {
			febreze.Acceptcookies();
			febreze.Login("AccountDetails");
			febreze.browsersearch("ProductName");
			febreze.productselection();
			febreze.Navigateminicart();
			febreze.NavigateMycartpage();
			febreze.checkoutpage();
			febreze.UsershippingAddress();
			febreze.differentbillingaddress("Billing Address");
			febreze.AddPaymentdetails("PaymentDetailsVISA");
		}catch(Exception | Error e){
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


