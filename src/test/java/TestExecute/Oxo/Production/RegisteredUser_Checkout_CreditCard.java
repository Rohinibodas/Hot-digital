package TestExecute.Oxo.Production;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.oxo.OxoHelperLive;
import TestLib.Common;
import TestLib.Login;

public class RegisteredUser_Checkout_CreditCard {
	String datafile = "oxo//OxoTestData.xlsx";	
	OxoHelperLive oxo=new OxoHelperLive(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)

  public void RegisteredUser_Checkout_CreditCard() {
		try{
			oxo.closetheadd();
			oxo.acceptPrivecy();
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
	
	@BeforeMethod
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "Oxo\\config.properties");
		
		  Login.signIn();
		 
		  
	  }

}
