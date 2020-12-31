package TestExecute.Oxo.Mobile;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.oxo.OxoMobileHelper;
import TestLib.Common;
import TestLib.Login;

public class Guest_Checkout_Paypal {
	
	String datafile = "oxo//OxoTestData.xlsx";	
	OxoMobileHelper oxo=new OxoMobileHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	  public void guest_checkout_Paypal() {
			try{
				oxo.closetheadd();
				oxo.clickBaby_Toddler();
				oxo.addproducts("1");
				oxo.checkout();
				oxo.ShippingAddress("ShippingAddress");
				oxo.selectGroundShippingMethod();
				oxo.clickAcceptingaddress();
				oxo.payPal_payment("PaypalDetails");
				//oxo.VerifyaingConformationPage();
				}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		} 
	}
	@AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}
	
	/*
	@BeforeMethod
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "Oxo\\config.properties");
		 Login.signIn("chrome","Galaxy S5");
	 }*/
	@BeforeTest
	@Parameters({"device"})  
	  public void startTest(String Device) throws Exception {
		System.setProperty("configFile", "Oxo\\config.properties");
		Login.signIn("chrome",Device);
	  }

	
}
