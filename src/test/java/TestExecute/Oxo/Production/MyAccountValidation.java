package TestExecute.Oxo.Production;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.oxo.OxoHelper;
import TestComponent.oxo.OxoHelperLive;
import TestLib.Common;
import TestLib.Login;

public class MyAccountValidation {
	String datafile = "oxo//OxoTestData.xlsx";
	OxoHelperLive oxo = new OxoHelperLive(datafile);

	@Test(priority = 1)

	public void GuestUser_Checkout_MasterCard_CC () throws Exception {

		try {
			oxo.closetheadd();
			oxo.acceptPrivecy();
			oxo.loginOxo("AccountDetails");
			oxo.Click_MyAccount();
			oxo.myaccount_validation();
			oxo.myOrders();
			oxo.Mywishlist();	
			oxo.AddressBook();
	        oxo.Add_Address("ShippingAddress");
			oxo.MyInformation();
			oxo.Change_MyInformation("AccountDetails");

		} catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}

	@AfterTest
	public void clearBrowser() {
		
		//Common.closeAll();

	}

	@BeforeMethod
	public void startTest() throws Exception {
		System.setProperty("configFile", "Oxo\\config.properties");
		Login.signIn();
	}
}
