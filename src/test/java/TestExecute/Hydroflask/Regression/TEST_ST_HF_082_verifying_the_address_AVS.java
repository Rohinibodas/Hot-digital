package TestExecute.Hydroflask.Regression;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;


public class TEST_ST_HF_082_verifying_the_address_AVS {
	String datafile = "Hydroflask//HydroTestData.xlsx";	   
	HydroHelper Hydro=new HydroHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	
	public void Verify_AVS_In_shippingaddress() throws Exception {

		try {
			Hydro.orderSubmit("Bottles");
			Hydro.checkOut();
			Hydro.Verify_AVS_addDeliveryAddress1("AVS_Address");
			Hydro.addPaymentDetails("Amexcard");
		}
		catch (Exception e) {
			e.printStackTrace();
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
//		 System.setProperty("configFile", "Hydroflask\\config.properties");
		  Login.signIn();
		  Hydro.acceptPrivecy();
		 Hydro.ClosADD();		  
	  }

}
