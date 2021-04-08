package TestExecute.Vicks;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.Vicks.VicksHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_VK_011_GuestCheckoutInvalidCC {
	
	String datafile = "Vicks//Vickstestdata.xlsx";
	VicksHelper vicks = new VicksHelper(datafile);

	@Test(priority = 1)
	public void loginApplication() throws Exception {

		try {
			vicks.Verifyhomepage();
			vicks.Humidifiers_Vaporizers();
			//vicks.clickHumidifiers();
			vicks.productselect();
			vicks.addtocart();
			vicks.mincat();
			vicks.checkout();
			vicks.shippingaddress("Address");
			vicks.invalidData("InvalidPaymentDetails");
        } catch (Exception e) {
			Assert.fail(e.getMessage(), e);
		}
	}
	
	

	@AfterTest
	public void clearBrowser() {
	
		Common.closeAll();

	}
	

	@BeforeMethod
	public void startTest() throws Exception {
		System.setProperty("configFile", "Vicks\\config.properties");
		Login.signIn();

	}

}