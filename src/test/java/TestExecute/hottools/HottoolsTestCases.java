package TestExecute.hottools;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import TestComponent.Hottools.HottoolsHelpr;
import TestLib.BaseDriver;
import TestLib.Common;
import TestLib.Login;

public class HottoolsTestCases {
	
	String datafile = "Hottools//HottoolsTestData.xlsx";	
	HottoolsHelpr Hottools=new HottoolsHelpr(datafile);
	
	
 /* @Test(priority=1)
  public void CreateAnAccount() {
	  try {
		  Hottools.createNewCustomerAccount_RetailCustomer("RetailCustomerAccountDetails");
		
		
	} catch (Exception e) {
	
		
		Assert.fail(e.getMessage(), e);
	}
  }
  */
  
  @Test(priority=2)
  public void SigIn(){
	  
	  try{
		  Hottools.singin("RetailCustomerAccountDetails");
	  }
	  catch (Exception e) {
			
			
			
			Assert.fail(e.getMessage(), e);
		}
  }
  
  @Test(dependsOnMethods="SigIn")
  public void SerchProduct(){
	  
	  try{
		  Hottools.searchingProducts("productName");
	  }
	  catch (Exception e) {
			
		
			
			Assert.fail(e.getMessage(), e);
		}
  }
  
  @Test(dependsOnMethods="SerchProduct")
  public void AddToCartProduct(){
	  
	  try{
		  Hottools.AddToCartProduct("productName");
	  }
	  catch (Exception e) {
			
			
			
			Assert.fail(e.getMessage(), e);
		}
  }
  @Test(dependsOnMethods="AddToCartProduct")
  public void shippingAddress(){
	  
	  try{
		  Hottools.shippingAddress("shippingAddress");
		 
	  }
	  catch (Exception e) {
			
			
			
			Assert.fail(e.getMessage(), e);
		}
  }
  
  
  @Test(dependsOnMethods="shippingAddress")
  public void addCardDetiles(){
	  
	  try{
		  Hottools.addCardDetiles("PaymentDetails");
	  }
	  catch (Exception e) {
			
			
			Assert.fail(e.getMessage(), e);
		}
  }
  @Test(dependsOnMethods="addCardDetiles")
  public void logout(){
	  
	  try{
		  Hottools.LogOut();
	  }
	  catch (Exception e) {
			
			e.printStackTrace();
			
			Assert.fail(e.getMessage(), e);
		}
  }
  
  
  
  @BeforeMethod
	@Parameters({"browser"})  
 
  public void startTest() throws Exception {
	 System.setProperty("configFile", "Hottools\\config.properties");
	  Login.signIn();
	  
  }
  @AfterSuite
	public void closeBrowser()
	{
		//Common.closeAll();

	}
	
	@AfterTest
	public void clearBrowser()
	{
		BaseDriver.setDriver(null);

	}
  
}
