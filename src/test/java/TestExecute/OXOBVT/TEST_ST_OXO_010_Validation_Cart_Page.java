package TestExecute.OXOBVT;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.oxo.OxoHelper;
import TestLib.BaseDriver;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_OXO_010_Validation_Cart_Page {
	String datafile = "oxo//OxoTestData.xlsx";
	OxoHelper oxo = new OxoHelper(datafile);

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)

	public void TEST_ST_OXO_010_Validation_Cart_Page() {
		try {
		//	oxo.Close_popup();
			oxo.closetheadd();
			// oxo.loginOxo("AccountDetails");
			//oxo.clickBaby_Toddler();
			oxo.Candb();
			oxo.addproducts("1");
			oxo.clickViewCart();
			oxo.CheckPost();
			
		} catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}

	@AfterTest
	public void clearBrowser() throws Exception {

		Common.closeAll();

	}

	@BeforeMethod
	public void startTest() throws Exception {
	//System.setProperty("configFile", "Oxo\\Config_OXO_Prouction.properties");
		// System.setProperty("configFile", "Oxo\\config.properties");
		Login.signIn();

	}
}