package TestExecute.Febreze.Order_XML;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.Febreze.FebrezeHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_FBZ_001_Guestuser_XML_Validation {
	String datafile = "Febreze//FebrezeTestData.xlsx";
	FebrezeHelper febreze = new FebrezeHelper(datafile);

	@Test(priority = 1)
	public void Guestuser_XML_Validation() throws Exception {

		try {
			febreze.Acceptcookies();
			febreze.browsersearch("ProductName");
			febreze.productselection();
			febreze.Navigateminicart();
			febreze.NavigateMycartpage();
		HashMap<String,HashMap<String,String>> productinfromation=febreze.productshoppingcart();
			febreze.checkoutpage();
			HashMap<String,String> shippingaddresss=febreze.shippingaddress1("Address");
			HashMap<String,String> ordertaxammount=febreze.orderamountinfo();
			HashMap<String,String>paymentdetriles=febreze.paymentdetriles("PaymentDetails");

			String order=febreze.PlaceOrder();
			febreze.vicksAdminlogin("AdminLogins");
			febreze.selectManulExport(order);
			
			
		     
			febreze.productinfromationvalidation(productinfromation, order);
			febreze.shippingvalidaing_GustUserXML(shippingaddresss, order);
			febreze.TotalvalidationXML(ordertaxammount, order);
			febreze.card_details_validationXML(paymentdetriles, order);
		
			
			
			
			
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
		System.setProperty("configFile", "Febreze\\Config_Febreze_Staging.properties");
		Login.signIn();

	}

}
