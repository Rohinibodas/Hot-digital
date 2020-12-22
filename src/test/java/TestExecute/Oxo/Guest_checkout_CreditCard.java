package TestExecute.Oxo;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.oxo.OxoHelper;
import TestLib.BaseDriver;
import TestLib.Common;
import TestLib.Login;

public class Guest_checkout_CreditCard {
	
	String datafile = "oxo//OxoTestData.xlsx";	
	OxoHelper oxo=new OxoHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class,invocationCount = 1)

  public void guest_checkout_CreditCard() {
		try{
		oxo.closetheadd();
		
		oxo.clickBaby_Toddler();
		oxo.addproducts("1");
		oxo.checkout();
		oxo.ShippingAddress("ShippingAddress");
		oxo.selectGroundShippingMethod();
		oxo.clickAcceptingaddress();
		oxo.Click_CreditCard();
		oxo.Edit_BillingAddress("BiillingAddress");
		oxo.clickAcceptingaddress();
		oxo.creditCard_payment("PaymentDetails");
		oxo.VerifyaingConformationPage();
		Common.refreshpage();
  }
	catch (Exception e) {
		
		Assert.fail(e.getMessage(), e);
	} 
}
	
	@AfterTest
	public void clearBrowser() throws Exception
	{
		//BaseDriver.setDriver(null);
		Common.closeAll();

	}
	
	@BeforeMethod
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "Oxo\\config.properties");
		  Login.signIn();
		 
		  
	  }

}
