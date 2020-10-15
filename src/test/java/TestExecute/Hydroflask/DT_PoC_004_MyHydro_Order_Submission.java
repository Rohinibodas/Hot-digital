package TestExecute.Hydroflask;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class DT_PoC_004_MyHydro_Order_Submission {
	
	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper Hydro=new HydroHelper(datafile);
	
  @Test
  public void customizeBottleOrder() {
	  try {
	       Hydro.Customize_Bottle();
	       Hydro.addDeliveryAddress("Address");
		   Hydro.updatePaymentAndSubmitOrder("PaymentDetails");
	        
			
		}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		} 
  }
  
  @AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}
	
  
  
  @BeforeTest
  public void startTest() throws Exception {
	 System.setProperty("configFile", "Hydroflask\\config.properties");
	  Login.signIn();
	  Hydro.acceptPrivecy();
	  
  }

}
