package TestExecute.Hydroflask;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestComponent.oxo.OxoHelper;
import TestLib.Common;
import TestLib.Login;

public class HydroflaskTestCase {
	
	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper Hydro=new HydroHelper(datafile);
	@Test(priority=1)
	public void createAccount() throws Exception {

		try {
	        //Hydro.CreateNewAccount("AccountDetails");
	      //  Hydro.loginHydroflaskAccount("AccountDetails");
			Hydro.orderSubmit("Address");
			Hydro.checkOut();
			Hydro.addDeliveryAddress("Address");
			Hydro.addPaymentDetails("PaymentDetails");
		}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	
	
	

	@AfterSuite
	public void closeBrowser()
	{
		//Common.closeAll();

	}
	
	/*@AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}*/
	
	@BeforeTest
	//@Parameters({"browser"})  
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "Hydroflask\\config.properties");
		  Login.signIn();
		  Hydro.acceptPrivecy();
		  
	  }



}
