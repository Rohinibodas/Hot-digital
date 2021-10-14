package TestExecute.OXOBVT;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.oxo.OxoHelper;
import TestLib.BaseDriver;
import TestLib.Common;
import TestLib.Login;

public class TEST_MT_OXO_016_Validation_Logout {
	String datafile = "oxo//OxoTestData.xlsx";
	OxoHelper oxo = new OxoHelper(datafile);

	@Test // (retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void TEST_MT_OXO_016_Validation_Logout() {
		try {
			//soxo.Close_popup();
			oxo.closetheadd();
			oxo.loginOxo("AccountDetails");
			oxo.logOut();
			
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
	//	System.setProperty("configFile", "Oxo\\Config_OXO_Prouction.properties");
		//System.setProperty("configFile", "Oxo\\config.properties");
		Login.signIn();
	}
}