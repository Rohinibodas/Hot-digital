
package TestExecute.Hydroflask.Smoke;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class RegisteredUser_Checkout_CreditCard {
	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper Hydro=new HydroHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	
	public void registeredUser_Checkout_CreditCard() throws Exception {

		try {
			Hydro.loginHydroflaskAccount("AccountDetails");
			Hydro.orderSubmit("Bottles");
			Hydro.checkOut();
			Hydro.addDeliveryAddress_registerUser("Address");
			Hydro.updatePaymentAndSubmitOrder("Ccmastercard");
		}
		catch (Exception e) {
			e.printStackTrace();
			
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	
	
	@AfterTest
	public void clearBrowser()
	{
	Common.closeAll();

	}
	
	@BeforeTest
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "Hydroflask\\config.properties");
		  Login.signIn();
		  Hydro.acceptPrivecy();
		  Hydro.ClosADD();
		  
	  }

}
