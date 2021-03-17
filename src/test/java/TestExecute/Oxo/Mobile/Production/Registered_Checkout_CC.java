package TestExecute.Oxo.Mobile.Production;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.oxo.OxoHelper;
import TestComponent.oxo.OxoMobileHelper;
import TestComponent.oxo.OxoMobileLiveHelper;
import TestLib.BaseDriver;
import TestLib.Common;
import TestLib.Login;

public class Registered_Checkout_CC {
	
	String datafile = "oxo//OxoTestData.xlsx";	
	OxoMobileLiveHelper oxo=new OxoMobileLiveHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)

  public void Registered_Checkout_CC() {
		try{
			oxo.closetheadd();
			oxo.AcceptPrivacy();
			oxo.NavigationToggle();
			//oxo.OXOLogin();
            oxo.login();
			oxo.loginOxo("AccountDetails");
			oxo.NavigationToggle();
			//oxo.clickBaby_Toddler();
			oxo.Beverage();
			oxo.addproducts("1");
			oxo.checkout();
			oxo.addNewAddress("ShippingAddress");
			oxo.clickAcceptingaddress();
			oxo.selectGroundShippingMethod();
			oxo.Click_CreditCard();
			//oxo.Edit_BillingAddress("BiillingAddress");
			//oxo.clickAcceptingaddress();
			oxo.creditCard_payment("PaymentDetails");
			//oxo.VerifyaingConformationPage();	
	   
  }
	catch (Exception e) {
		
		Assert.fail(e.getMessage(), e);
	} 
}
	
	@AfterTest
	public void clearBrowser() throws Exception
	{
		Common.closeAll();
		

	}
	/*
	@BeforeMethod
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "Oxo\\config.properties");
		 Login.signIn("chrome","iPad");
   }*/
	@BeforeTest
	@Parameters({"device"})  
	  public void startTest(String Device) throws Exception {
		System.setProperty("configFile", "Oxo\\config.properties");
		Login.signIn("chrome",Device);
	  }

}
