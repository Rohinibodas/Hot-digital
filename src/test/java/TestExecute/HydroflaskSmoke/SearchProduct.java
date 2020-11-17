package TestExecute.HydroflaskSmoke;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Login;

public class SearchProduct {

	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper Hydro=new HydroHelper(datafile);
  @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void searchProduct() {
	  try {
	      Hydro.SampleSearchProduct("hydro-flask-small-tag-along-bottle-sling-brick");
	        
	        
		}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	@AfterTest
	public void clearBrowser()
	{
		
    // Common.closeAll();

	}
	
	@BeforeTest
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "Hydroflask\\config.properties");
		  Login.signIn();
		  Hydro.acceptPrivecy();
		  
	  }
}
