package TestExecute.HydroflaskEMEA;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroFlaskEMEA;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_HF_EMEA_022_Registeruser_checkout_With_3D_Creditcard_Visa {
	String datafile = "HydroflaskEMEA//HydroEMEATestData.xlsx";	
	HydroFlaskEMEA Hydro=new HydroFlaskEMEA(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  
  public void RegisterUser_Checkoutwith_3D_Creaditcard_Visa() {
		try {
			
			Hydro.loginHydroflaskAccount("AccountDetails");
				Hydro.orderSubmit("Bottles");
				Hydro.checkOut();
				Hydro.addDeliveryAddress_registerUser("Address");
				Hydro.update_3D_PaymentAndSubmitOrder("3D_Card");
				
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
//		 System.setProperty("configFile", "HydroflaskEMEA\\config.properties");
		  Login.signIn();
		  Hydro.acceptPrivecy();
		  //Hydro.ClosADD();*/
		  
	  }

}
