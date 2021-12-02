package TestExecute.RevlonUS;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.HelenoftroyBrands;
import TestComponent.RevlonUS.RevelonHelper;
import TestLib.BaseDriver;
import TestLib.Common;
import TestLib.Login;

public class RevlonBusiness {
	
	String datafile = "revlon//RevlonTestData.xlsx";	
	RevelonHelper revelon=new RevelonHelper(datafile);
	
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
		

	}
	
	@AfterTest
	public void clearBrowser()
	{
		
		Common.closeAll();

	}
	/*@BeforeMethod
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "Revelon\\config.properties");
		  Login.signInRevolon();
		  
	  }
*/	
	@BeforeMethod
	//@Parameters({"browser"})  
	  public void startTest() throws Exception {
		System.setProperty("configFile", "Revelon\\config.properties");
		  Login.signIn("firfox");
		  
	  }

}
