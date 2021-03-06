package TestExecute.Hydroflask.Regression;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_HF_034_Track_yor_order_as_guest_user {
	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper Hydro=new HydroHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)

  public void Track_yor_order_as_guest_use() {
		try{
			Hydro.gustuserorderStatus();
			
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
//			 System.setProperty("configFile", "Hydroflask\\config.properties");
			  Login.signIn();
			  Hydro.acceptPrivecy();
			  Hydro.ClosADD();		  
		  }
  
}
