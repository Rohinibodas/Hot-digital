package TestExecute.PUR;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.PUR.PurHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_PUR_ST_023_OrderbySKU {
	
	String datafile = "PUR//PUR_TestData.xlsx";	
	PurHelper PUR=new PurHelper(datafile);
	
	@Test(priority=1)
	public void Orderbysku(){

		try{
			
			
			PUR.singin("CustomerAccountdetails");
			PUR.orderbySKU("OrderbySKU");
			PUR.checkoutPage();
			PUR.AddAddress();
			PUR.updatePaymentAndSubmitOrder("PaymentDetails");
			
			
			
		}
		catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}
	
	
	/*@BeforeMethod
	@Parameters({"browser"})  
	  public void startTest() throws Exception {
		System.setProperty("configFile", "PUR\\config.properties");
		  Login.signIn("chrome");
		   }*/
	
	@BeforeMethod
	@Parameters({"browser"}) 
	  public void startTest(String browser) throws Exception {
		System.setProperty("configFile", "PUR\\config.properties");
		  Login.signIn(browser);
	}
	

	@AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}

}