package TestExecute.Hydroflask.Regression;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;


public class TEST_ST_HF_081_Partiallyplaced_inline_order_verification {
	
	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper Hydro=new HydroHelper(datafile);

	
  @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void Partial_inline_Order() throws Exception {
	  try { 
		  Hydro.Customize_Bottle_Standed("24 oz");
	 Hydro.orderSubmit("Bottles");   
//	 Hydro.Customize_Bottle();
	 Hydro.checkOut();
	 Hydro.addDeliveryAddress("InlineOrder");
	 Hydro.updatePaymentAndSubmitOrder("PaymentDetails");
	 Hydro.Partial_order_verification();
	}
	catch (Exception e) {
		
		Assert.fail(e.getMessage(), e);
	} 
}

  
  
  @BeforeTest
  public void startTest() throws Exception {
//	 System.setProperty("configFile", "Hydroflask\\config.properties");
	  Login.signIn();
	  Hydro.acceptPrivecy();
	  Hydro.ClosADD();
  }
  @AfterTest
 	public void clearBrowser()
 	{
 	Common.closeAll();

 	}
 	
}
