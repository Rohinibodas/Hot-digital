package TestExecute.Hydroflask.O2C_E2E_Orders;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_HF_003_Guest_Checkout_Promocode_Paypal {
	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper Hydro=new HydroHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	
	public void gustUserCheckout_paypal() throws Exception {

		try {
			
			String Website=Hydro.URL();
			String Description ="Guest_Checkout_Promocode_Paypal";
			String Paymentmethod="Pay-Pal";
			
			Hydro.orderSubmit("Bottles");
			Hydro.checkOut();
			HashMap<String,String> Shipping=Hydro.E2E_addDeliveryAddress("Address");
			Hydro.promationCode("Promationcode");
			HashMap<String,String> data=Hydro.E2E_Validation();
			String OrderId=Hydro.payPal_Payment("PaypalDetails");
			
//			String OrderId="12345";
		    Hydro.E2E_writeResultstoXLSx(Website,Description,OrderId,Paymentmethod,data.get("subtotlaValue"),Shipping.get("ShippingZip"),Shipping.get("Shippingstate"),data.get("shippingammountvalue"),data.get("Taxammountvalue"),data.get("ActualTotalammountvalue"),data.get("ExpectedTotalAmmountvalue"),data.get("Discountammountvalue"));
		}
		catch (Exception e) { 
			e.printStackTrace();
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	
	
	@AfterTest
	public void clearBrowser()
	{
//	Common.closeAll();

	}
	
	@BeforeTest
	  public void startTest() throws Exception {
//		 System.setProperty("configFile", "Hydroflask\\config.properties");
		  Login.signIn();
		  Hydro.acceptPrivecy();
		  Hydro.ClosADD();
	  }
}
