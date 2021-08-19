package TestExecute.Hydroflask.Smoke;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class Guest_Checkout_CreditCard {
	String datafile = "Hydroflask//HydroTestData.xlsx";
	HydroHelper Hydro = new HydroHelper(datafile);

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)

	public void gustUserCheckout_CreditCard() throws Throwable {

		try {

			Hydro.orderSubmit("Bottles");
			Hydro.viewcart();
			HashMap<String, HashMap<String, String>> productinfromation = Hydro.productinfromation();
			Hydro.checkOut();
			HashMap<String, String> shippingaddresssf = Hydro.addDeliveryAddress_validation("Address");
			HashMap<String, String> ordertaxammount = Hydro.orderamountinfromation();
			HashMap<String, String> paymentdetriles = Hydro.add_PaymentDetails("PaymentDetails");
			String order = Hydro.verifyingSucesspage();

			Hydro.HydroAdminlogin("AdminLogins");
			Hydro.selectManulExport(order);

			/*
			 * 
			 */
			String TrantionID=Hydro.seleOrderoprtion(order);
			
			  Hydro.productinfromationvalidation(productinfromation, order);
			  Hydro.shippingvalidaing_GustUserXML(shippingaddresssf, order);
			  
			  Hydro.TotalvalidationXML(ordertaxammount, order);
			  Hydro.Billingaddress_GustUserXML_Validation(shippingaddresssf,order);
			  Hydro.card_details_validationXML(paymentdetriles, order);
			  Hydro.ValidatingTranctionIDInXML(order, TrantionID);
			  //Hydro.shippingvalidaingXML("Address","4000421161");
			 
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage(), e);
		}
	}

	@AfterTest
	public void clearBrowser() {
		 Common.closeAll();

	}

	@BeforeTest
	public void startTest() throws Exception {
		System.setProperty("configFile", "Hydroflask\\config.properties");
		Login.signIn();
		Hydro.acceptPrivecy();
		Hydro.ClosADD();
	}

}
