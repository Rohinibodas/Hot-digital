package TestExecute.Vicks;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.Vicks.VicksHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_VK_027_Add_to_cart_from_PLPpage {
	String datafile = "Vicks//Vickstestdata.xlsx";
	VicksHelper vicks = new VicksHelper(datafile);

	@Test(priority = 1)
	public void add_to_cart_from_plppage() throws Exception {

		try {
			vicks.Verifyhomepage();
//			vicks.Agreandproceed();
//			vicks.loginVicks("AccountDetails");
			vicks.Humidifiers_Vaporizers();
			vicks.addtocartPLPpage("productname");
			vicks.mincat();

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
