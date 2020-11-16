package TestExecute.Oxo;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.oxo.OxoHelper;
import TestLib.BaseDriver;
import TestLib.Login;

public class Guest_Checkout_Paypal {
	String datafile = "oxo//OxoTestData.xlsx";	
	OxoHelper oxo=new OxoHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)


	public void guest_Checkout_Paypal() {
		try{
		oxo.closetheadd();
		oxo.clickBaby_Toddler();
		oxo.addproducts("1");
		oxo.checkout();
		oxo.ShippingAddress("ShippingAddress");
		oxo.selectGroundShippingMethod();
		oxo.clickAcceptingaddress();
		oxo.payPal_payment("PaypalDetails");
		oxo.VerifyaingConformationPage();
		
  }
	catch (Exception e) {
		
		Assert.fail(e.getMessage(), e);
	} 
}
	
	@AfterTest
	public void clearBrowser() throws Exception
	{
		BaseDriver.setDriver(null);
		

	}
	
	@BeforeMethod
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "Oxo\\config.properties");
		  Login.signIn();
		 
		  
	  }

}
