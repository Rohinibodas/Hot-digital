package TestExecute.Vicks;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.Vicks.VicksHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_VK_003_ForgotPassword {
	
	String datafile = "Vicks//Vickstestdata.xlsx";
	VicksHelper vicks = new VicksHelper(datafile);

	@Test(priority = 1)

	public void ForgotPassword() throws Exception {

		try {
			
		
		 vicks.Verifyhomepage();
			vicks.forgotpassword("AccountDetails");

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
		System.setProperty("configFile", "Vickshumdifier\\config.properties");
		Login.signIn();

	}

}