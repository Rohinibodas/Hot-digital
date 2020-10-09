package TestExecute.Braun;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.braun.BraunHelper;
import TestLib.Login;

public class BraunStageTestCase {
	String datafile = "Braun//BraunTestData.xlsx";	
	BraunHelper Braun=new BraunHelper(datafile);
	
	@Test(priority=1)
  public void SignInBraun() {
	  
	  try {
			Braun.SignInBraun_stage("AccountDetails");
			
			
		}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		}  
	  
  }
  
  @Test(dependsOnMethods="SignInBraun")
  public void serachProduct() {
	  
	  try {
		  Braun.searchProduct_stage("productName");
		}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		}  
	  
  }
  
  @Test(dependsOnMethods="serachProduct")
  public void addproductToCart() {
	  
	  try {
		  Braun.addproductTOCart_stage();
		}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		}  
	  
  }
  
  @Test(dependsOnMethods="addproductToCart")
  public void shippingAddress() {
	  
	  try {
		  Braun.shippingAddress_stage("ShippingAddress");
		}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		}  
	  
  }
  
  
  
  @Test(dependsOnMethods="shippingAddress")
  public void payments_process() {
	  
	  try {
		  Braun.cardDeatiles_stage("PaymentDetails");
		}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		}  
	  
  }
  
  @AfterTest
 	public void clearBrowser()
 	{
 		//Common.closeAll();

 	}
 	
 	@BeforeTest
 	  public void startTest() throws Exception {
 		 System.setProperty("configFile", "Braun\\config.properties");
 		  Login.signIn();
 		 
 		  
 	  }
}
