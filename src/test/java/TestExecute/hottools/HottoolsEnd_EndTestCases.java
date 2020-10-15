package TestExecute.hottools;

import org.junit.Before;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.Hottools.HottoolsHelpr;
import TestLib.Common;
import TestLib.Login;

public class HottoolsEnd_EndTestCases {
	
	String datafile = "Hottools//HottoolsTestData.xlsx";	
	HottoolsHelpr Hottools=new HottoolsHelpr(datafile);
	
  @Test
  public void end_toEndTestCases() {
	  try{
		  Hottools.singin("RetailCustomerAccountDetails");
		  Hottools.searchingProducts("productName");
		  Hottools.AddToCartProduct("productName");
		  Hottools.shippingAddress("shippingAddress");
		  Hottools.addCardDetiles("PaymentDetails");
		  Hottools.LogOut();
	  }
	  catch (Exception e) {
			Assert.fail(e.getMessage(), e);
		}
  }
	  
  
  @BeforeMethod
  
  public void startTest() throws Exception {
	 System.setProperty("configFile", "Hottools\\config.properties");
	  Login.signIn();
	  
  }
  @AfterMethod
	public void closeBrowser()
	{
		Common.closeAll();

	}
  }

