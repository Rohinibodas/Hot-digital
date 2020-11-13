
package TestExecute.Oxo;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.oxo.OxoHelper;
import TestComponent.revlon.RevelonHelper;
import TestLib.BaseDriver;
import TestLib.Common;
import TestLib.Login;
import Utilities.TestListener;

//@Listeners(TestListener.class)
public class OxoEndToEndTestCase 
{
	String datafile = "oxo//OxoTestData.xlsx";	
	OxoHelper oxo=new OxoHelper(datafile);
	@Test(priority=1)
	public void createAccount() throws Exception {

		try {
			oxo.CreateNewAccount("AccountDetails");
		}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	
	
	@Test(dependsOnMethods="createAccount")
	public void loginApplication() throws Exception {

		try {
			oxo.loginOxo("AccountDetails");
		}
		catch (Exception e) {
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	@Test(dependsOnMethods="loginApplication")
	public void searchProduct() throws Exception {

		try {
			oxo.searchProductAndAddtoCart("Beverage");
		}
		catch (Exception e) {
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	@Test(dependsOnMethods="searchProduct")
	public void checkOut() throws Exception {

		try {
			oxo.checkOut();
		}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	@Test(dependsOnMethods="checkOut")
	public void addDeliveryAddress() throws Exception {

		try {
			oxo.addDeliveryAddress("Address");
		}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	@Test(dependsOnMethods="addDeliveryAddress")
	public void proceedPayment() throws Exception {

		try {
			oxo.addPaymentDetails("PaymentDetails");
		}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		} 
	}

	@AfterSuite
	public void closeBrowser()
	{
		Common.closeAll();

	}
	
	/*@AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}*/
	
	@BeforeTest
	@Parameters({"browser"})  
	  public void startTest(String browser) throws Exception {
		 System.setProperty("configFile", "Oxo\\config.properties");
		  Login.signIn(browser);
		  
	  }


}
