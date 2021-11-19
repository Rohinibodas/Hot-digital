package TestExecute.Oxo.Mobile.Oxo_Mobile;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.oxo.OXO_Mobilehelper;
import TestLib.Login;

public class Regiuster_User_Checkout {
	String datafile = "oxo//OxoTestData.xlsx";	
	OXO_Mobilehelper oxo=new OXO_Mobilehelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	  public void guest_checkout_Paypal() {
			try{
				
				oxo.acceptPrivecy();
				oxo.loginOxo("AccountDetails");
				oxo.Click_Cooking_baking();
				oxo.addproducts("1");
				oxo.checkout();
				oxo.addNewAddress("ShippingAddress");
				oxo.clickAcceptingaddress();
				oxo.selectGroundShippingMethod();
				oxo.Click_CreditCard();
				oxo.Edit_BillingAddress("BiillingAddress");
				oxo.clickAcceptingaddress();
				
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
