package TestExecute.Hydroflask.Smoke;

import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;

public class Test_HF_ST_038_PDP_thumbnail_verification_of_bundle_product {
	
	
	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper Hydro=new HydroHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  
  public void f() {
  }
}
