
package TestExecute.Hydroflask.Regression;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;
 
public class TEST_ST_HF_070_Checkout_VISA_Registeruser_with_PO_Box {
	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper Hydro=new HydroHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	
	public void registeredUser_Checkout_PoBoxCreditCard() throws Exception {

		try {
			Hydro.loginHydroflaskAccount("AccountDetails");
			Hydro.orderSubmit("Bottles");
			Hydro.checkOut();
			Hydro.addDeliveryAddress_registerUser("PO_Box_Address");
			Hydro.updatePaymentAndSubmitOrder("PaymentDetails");
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
//		 System.setProperty("configFile", "Hydroflask\\config.properties");
		  Login.signIn();
		  Hydro.acceptPrivecy();
		  Hydro.ClosADD();
		  
	  }

}
