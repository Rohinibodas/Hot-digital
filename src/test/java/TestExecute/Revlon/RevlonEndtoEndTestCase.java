package TestExecute.Revlon;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.HelenoftroyBrands;
import TestComponent.revlon.RevelonHelper;
import TestLib.BaseDriver;
import TestLib.Common;
import TestLib.Login;

public class RevlonEndtoEndTestCase {
	
	String datafile = "revlon//RevlonTestData.xlsx";	
	RevelonHelper revelon=new RevelonHelper(datafile);
	/*@Test
	public void createAccount() throws Exception {
		String datafile = "revlon//RevlonTestData.xlsx";	
		try {
			RevelonHelper revelon=new RevelonHelper(datafile);
			revelon.navigateAccount();
			revelon.CreateNewAccount("AccountDetails");
			revelon.loginRevlon("AccountDetails");
			revelon.searchProduct("Dryers");
			revelon.proccedToCheckout();
			revelon.addShippingAddress();
		}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		} 
		finally 
		{ 
			//Common.closeAll();

		}
	}*/

	@Test(priority=1)
	public void createAccount() throws Exception {

		try {
			revelon.CreateNewAccount("AccountDetails");
		}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	
	
	@Test(dependsOnMethods="createAccount")
	public void loginApplication() throws Exception {

		try {
			revelon.loginRevlon("AccountDetails");
		}
		catch (Exception e) {
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	@Test(dependsOnMethods="loginApplication")
	public void searchProduct() throws Exception {

		try {
			revelon.searchProduct("Dryers");
		}
		catch (Exception e) {
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	@Test(dependsOnMethods="searchProduct")
	public void checkOut() throws Exception {

		try {
			revelon.proccedToCheckout();
		}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	@Test(dependsOnMethods="checkOut")
	public void addDeliveryAddress() throws Exception {

		try {
			revelon.addShippingAddress("Address");
		}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	/*@Test(dependsOnMethods="addDeliveryAddress")
	public void proceedPayment() throws Exception {

		try {
			revelon.addPaymentDetails("PaymentDetails");
		}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		} 
	}
*/
	@AfterSuite
	public void closeBrowser()
	{
		Common.closeAll();

	}
	
	@AfterTest
	public void clearBrowser()
	{
		BaseDriver.setDriver(null);

	}
	@BeforeMethod
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "Revelon\\config.properties");
		  Login.signInRevolon();
		  
	  }

}
