package TestExecute.Hydroflask.Smoke;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_HF_042_Myaccount_page_validation {
	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper Hydro=new HydroHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void myaccount_page_validation() {
		try{
			Hydro.loginHydroflaskAccount("AccountDetails");
			Hydro.verifying_account_dashboard();
			Hydro.Account_information();
			Hydro.Adress_Book();
			Hydro.my_orders();
			Hydro.Newsletter_subscription();
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
			 System.setProperty("configFile", "Hydroflask\\config.properties");
			  Login.signIn();
			  Hydro.acceptPrivecy();
			  Hydro.ClosADD();		  
		  }
}
