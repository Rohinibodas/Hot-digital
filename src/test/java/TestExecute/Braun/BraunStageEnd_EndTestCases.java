package TestExecute.Braun;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.braun.BraunHelper;
import TestLib.Common;
import TestLib.Login;

public class BraunStageEnd_EndTestCases {
	String datafile = "Braun//BraunTestData.xlsx";	
	BraunHelper Braun=new BraunHelper(datafile);
	
  @Test
  public void end_toEndTestCase() {
	  try {
		  Braun.SignInBraun_stage("AccountDetails");
		  Braun.searchProduct_stage("productName");
		  Braun.addproductTOCart_stage();
		  Braun.shippingAddress_stage("ShippingAddress");
		  Braun.cardDeatiles_stage("PaymentDetails");
		  Braun.Logout();
		}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		} 
	}
	  
	  
	@BeforeMethod
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "Braun\\config.properties");
		  Login.signIn();
		  
	  }
	 @AfterTest
	 	public void clearBrowser()
	 	{
	 		Common.closeAll();

	 	}
}
