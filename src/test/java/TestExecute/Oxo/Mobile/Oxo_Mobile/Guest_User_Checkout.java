 package TestExecute.Oxo.Mobile.Oxo_Mobile;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.oxo.OXO_Mobilehelper;
import TestComponent.oxo.OxoMobileHelper;
import TestLib.Login;

public class Guest_User_Checkout {
	String datafile = "oxo//OxoTestData.xlsx";	
	OXO_Mobilehelper oxo=new OXO_Mobilehelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	  public void guest_checkout_Paypal() {
			try{
				
				oxo.acceptPrivecy();
				oxo.Click_Cooking_baking();
				oxo.addproducts("1");
				oxo.checkout();
				oxo.ShippingAddress("ShippingAddress");
				oxo.selectGroundShippingMethod();
				//oxo.Click_CreditCard();
				oxo.creditCard_payment("PaymentDetails");
				oxo.payPal_payment("PaypalDetails");
				
				}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		} 
	}
	@AfterTest
	public void clearBrowser()
	{
		//Common.closeAll();

	}
	
   /*@BeforeMethod
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "Oxo\\config.properties");
		 Login.signIn("chrome","iPhone X");
	 }*/
	@BeforeTest
	  public void startTest() throws Exception {
		System.setProperty("configFile", "Oxo//mobile_config.properties");
		Login.mobilesignIn("ios");
	  }

	

}
