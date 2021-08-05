package TestExecute.Oxo;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.oxo.OxoHelper;
import TestLib.Common;
import TestLib.Login;

public class ValidateBrowse_Search_for_Product {
	String datafile = "oxo//OxoTestData.xlsx";
	OxoHelper oxo = new OxoHelper(datafile);

	@Test
	public void validateBrowse_Search_for_Product() {
		try {
			oxo.closetheadd();
			// oxo.loginOxo("AccountDetails");
			oxo.validatingSearchBoxWithOutData();
			oxo.validatingSearchBoxWithNumberText("9 cup");
			oxo.validatingSearchProductInformation("Nylon Potato Masher");
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
		System.setProperty("configFile", "Oxo\\config.properties");
		Login.signIn();

	}
}