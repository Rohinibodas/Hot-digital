package TestExecute.Braun;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.braun.BraunHelper;
import TestLib.Login;

public class BraunTestCase {
	String datafile = "Braun//BraunTestData.xlsx";	
	BraunHelper Braun=new BraunHelper(datafile);
	
	 
	@Test(priority=1)
	public void createAccount() throws Exception {

		try {
			Braun.CreateNewAccount("AccountDetails");
		}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	
	@Test(dependsOnMethods="createAccount")
	public void SignInBraun() throws Exception {

		try {
			Braun.SignInBraun("AccountDetails");
		}
		catch (Exception e) {
			e.printStackTrace();
			
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	@Test(dependsOnMethods="SignInBraun")
	public void searchProduct() throws Exception {

		try {
			Braun.searchProduct("productName");
		}
		catch (Exception e) {
			e.printStackTrace();
			
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	@Test(dependsOnMethods="searchProduct")
	public void productCheckout() throws Exception {

		try {
			Braun.productCheckout("productName");
		}
		catch (Exception e) {
			e.printStackTrace();
			
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	
	@Test(dependsOnMethods="productCheckout")
	public void addPaymentDetails() throws Exception {

		try {
			Braun.addPaymentDetails("PaymentDetails");
		}
		catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace();
			
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	
	
	@BeforeMethod
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "Braun\\config.properties");
		  Login.signIn();
		  
	  }
}
