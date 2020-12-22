package TestExecute.Oxo.Production;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.oxo.OxoHelper;
import TestComponent.oxo.OxoHelperLive;
import TestLib.BaseDriver;
import TestLib.Common;
import TestLib.Login;

public class Guest_checkout_CreditCard {
	
	String datafile = "oxo//OxoTestData.xlsx";	
	OxoHelperLive oxo=new OxoHelperLive(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)

  public void guest_checkout_CreditCard() {
		try{
		oxo.closetheadd();
		oxo.acceptPrivecy();
		
		oxo.clickBaby_Toddler();
		oxo.addproducts("2");
		oxo.checkout();
		oxo.ShippingAddress("ShippingAddress");
		oxo.selectGroundShippingMethod();
		oxo.clickAcceptingaddress();
		oxo.Click_CreditCard();
		oxo.Edit_BillingAddress("BiillingAddress");
		oxo.clickAcceptingaddress();
		oxo.creditCard_payment("PaymentDetails");
  }
	catch (Exception e) {
		
		Assert.fail(e.getMessage(), e);
	} 
}
	
	@AfterTest
	public void clearBrowser() throws Exception
	{
		//Common.closeAll();
		

	}
	
	@BeforeMethod
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "Oxo\\config.properties");
		
		  Login.signIn();
		 
		  
	  }

}
