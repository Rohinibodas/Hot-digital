package TestExecute.Febreze.SystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.Febreze.FebrezeHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_FBZ_38_GuestUser_multiproductcheckoutAMEX_differentbillingaddress {
	String datafile = "Febreze//FebrezeTestData.xlsx";	
	FebrezeHelper febreze=new FebrezeHelper(datafile);

	@Test(priority=1)
	public void GuestuserdifferentaddressCCAMEX() {
		try {
			febreze.Acceptcookies();
			febreze.CategoryProductselect();
			febreze.Navigateminicart();
			febreze.NavigateMycartpage();
			febreze.checkoutpage();
			//febreze.UsershippingAddress();
			febreze.shipping_Address("Guest_BillingAddress");
			febreze.differentbillingaddress("Billing Address");
			febreze.AddPaymentdetails("PaymentDetailsAEMX");
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


