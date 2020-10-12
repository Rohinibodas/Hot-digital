package TestExecute.Oxo;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import TestComponent.oxo.OxoHelper;
import TestComponent.revlon.RevelonHelper;
import TestLib.BaseDriver;
import TestLib.Common;
import TestLib.Login;
import Utilities.TestListener;

//@Listeners(TestListener.class)
public class OxoTestCases 
{
	String datafile = "oxo//OxoTestData.xlsx";	
	OxoHelper oxo=new OxoHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void createAccount() throws Exception {

		try {
			//oxo.CreateNewAccount("AccountDetails");
			oxo.loginOxo("AccountDetails");
			oxo.searchProductAndAddtoCart("Beverage");
			oxo.checkOut();
			oxo.addDeliveryAddress("Address");
			oxo.addPaymentDetails("PaymentDetails");
		}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	
	@AfterTest
	public void clearBrowser()
	{
		BaseDriver.setDriver(null);

	}
	
	@BeforeMethod
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "Oxo\\config.properties");
		  Login.signIn();
		  
	  }


}
