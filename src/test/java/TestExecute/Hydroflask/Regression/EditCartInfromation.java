package TestExecute.Hydroflask.Regression;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class EditCartInfromation {
	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper Hydro=new HydroHelper(datafile);

	
  @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void EditCartInfromation() throws Exception {
	  try {   
	 Hydro.orderSubmit("Bottles");
	 Hydro.orderSubmit("Accessories");
	 Hydro.orderSubmit("Tumblers"); 
	// Hydro.minicart();
		
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
	  
  }
  @AfterTest
 	public void clearBrowser()
 	{
 		Common.closeAll();

 	}
 	
}
