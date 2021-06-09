package TestExecute.HydroflaskEMEA;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroFlaskEMEA;
import TestLib.Login;

public class Test_HFEMEA_UK_ST_002_Social_Media_links_validation {
	String datafile = "HydroflaskEMEA//HydroEMEATestData.xlsx";	
	HydroFlaskEMEA Hydro=new HydroFlaskEMEA(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void social_links_validation() {
		try {
			
				Hydro.socialLinkValidation("socialLinks");
				
			}
			catch (Exception e) {
				e.printStackTrace();
				
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
		 System.setProperty("configFile", "HydroflaskEMEA\\config.properties");
		  Login.signIn("chrome");
		  Hydro.acceptPrivecy();
		  //Hydro.ClosADD();*/
		  
	  }

}

