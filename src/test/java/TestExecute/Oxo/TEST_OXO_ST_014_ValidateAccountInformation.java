package TestExecute.Oxo;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.oxo.OxoHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_OXO_ST_014_ValidateAccountInformation {
	
	String datafile = "oxo//OxoTestData.xlsx";
	OxoHelper oxo = new OxoHelper(datafile);

	@Test(priority = 1)

	public void MyAccountValidation() throws Exception {

		try {
			
			oxo.closetheadd();
			oxo.loginOxo("AccountDetails");
			oxo.click_MyAccount();
			oxo.Myaccount_validation();
			oxo.MyOrders();
			oxo.MyWishlist();	
			oxo.Address_Book();
	         oxo.AddAddress("ShippingAddress");
			oxo.My_Information();
			oxo.ChangeMyInformation("AccountDetails");
			
			
			
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
