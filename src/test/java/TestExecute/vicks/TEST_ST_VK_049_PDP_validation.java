package TestExecute.vicks;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.Vicks.VicksHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_VK_049_PDP_validation {
	String datafile = "Vicks//Vickstestdata.xlsx";
	VicksHelper vicks = new VicksHelper(datafile);

	@Test(priority = 1)
	public void CreateNewAccount() {

		try {
		

			vicks.Verifyhomepage();
			vicks.CreateAccount("AccountDetails");
			vicks.Humidifiers_Vaporizers();
			vicks.productselect();
			vicks.SKU_validation1();
			vicks.title1();
		 
		
			

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