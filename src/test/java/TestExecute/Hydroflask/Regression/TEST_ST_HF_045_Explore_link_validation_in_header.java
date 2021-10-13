package TestExecute.Hydroflask.Regression;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_HF_045_Explore_link_validation_in_header {
	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper Hydro=new HydroHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	
  
  public void explore_link_validation_in_header() {
		try{
			 //Hydro.headLinksValidations_Ex("ExploreHeaderLinks");
			 
			 Hydro.verifying_letsGo();
			 Hydro.verifying_Parks_For_All();
			 Hydro.verifying_OurStory();
			 Hydro.verifying_WSL_Partnership();
			 Hydro.verifying_Contact();
			 
	    
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
