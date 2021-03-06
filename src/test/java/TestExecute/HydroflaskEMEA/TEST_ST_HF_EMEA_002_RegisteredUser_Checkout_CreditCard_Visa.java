   
package TestExecute.HydroflaskEMEA;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import TestComponent.Hydroflask.HydroFlaskEMEA;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_HF_EMEA_002_RegisteredUser_Checkout_CreditCard_Visa {
	String datafile = "HydroflaskEMEA//HydroEMEATestData.xlsx";	
	HydroFlaskEMEA Hydro=new HydroFlaskEMEA(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	
	public void registeredUser_Checkout_CreditCardVisa() throws Exception {

		try {
			Hydro.loginHydroflaskAccount("AccountDetails");
			Hydro.orderSubmit("Bottles");
			Hydro.checkOut();
		//	Hydro.addDeliveryAddress("Address");
			Hydro.addDeliveryAddress_registerUser("Address");
			Hydro.updatePaymentAndSubmitOrder("VisaCC");
			
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
	@Parameters("URL")  
	  public void startTest(String URL) throws Exception {
		 System.setProperty("configFile", "HydroflaskEMEA\\config.properties");
		  Login.openwebsite(URL);
		  Hydro.acceptPrivecy();
		  //Hydro.ClosADD();*/
		  
	  }

}
