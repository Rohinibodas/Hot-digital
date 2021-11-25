 package TestExecute.HydroflaskEMEA;
  
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroFlaskEMEA;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_HF_EMEA_007_Newsletter_Subscription {
	String datafile = "HydroflaskEMEA//HydroEMEATestData.xlsx";	
	HydroFlaskEMEA Hydro=new HydroFlaskEMEA(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  
  public void News_Letter_Subscription() {
		try {
			 Hydro.stayIntouch("WarrantyDetails");
				
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
//		 System.setProperty("configFile", "HydroflaskEMEA\\config.properties");
		  Login.signIn();
		  Hydro.acceptPrivecy();
		  //Hydro.ClosADD();*/
		  
	  }

}
