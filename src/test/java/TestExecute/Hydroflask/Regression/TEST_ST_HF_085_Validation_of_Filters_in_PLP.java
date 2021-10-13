package TestExecute.Hydroflask.Regression;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_HF_085_Validation_of_Filters_in_PLP{

	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper Hydro=new HydroHelper(datafile);
  @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void plpPageValidation() throws Exception {
	  try {
	      
		  Hydro.PLP_Validation("Bottles");
		  Hydro.clorevliadtion("Black");
		  Hydro.Validation_ofVolume("volume");
		 

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
//		 System.setProperty("configFile", "Hydroflask\\config.properties");
		  Login.signIn();
		  Hydro.acceptPrivecy();
		  Hydro.ClosADD();
	  }
}
