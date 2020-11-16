package TestExecute.Oxo;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.oxo.OxoHelper;
import TestLib.BaseDriver;
import TestLib.Login;

public class RegisteredUser_Checkout_CreditCard {
	String datafile = "oxo//OxoTestData.xlsx";	
	OxoHelper oxo=new OxoHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)

  public void registeredUser_Checkout_CreditCard() {
		try{
		oxo.closetheadd();
		oxo.loginOxo("AccountDetails");
		oxo.clickBaby_Toddler();
		oxo.addproducts("1");
		oxo.checkout();
		oxo.addNewAddress("ShippingAddress");
		oxo.clickAcceptingaddress();
		oxo.selectGroundShippingMethod();
		oxo.Click_CreditCard();
		oxo.Edit_BillingAddress("BiillingAddress");
		oxo.clickAcceptingaddress();
		oxo.creditCard_payment("PaymentDetails");
		//oxo.creditCard_payment("PaypalDetails");
		//oxo.VerifyaingConformationPage();
		
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
