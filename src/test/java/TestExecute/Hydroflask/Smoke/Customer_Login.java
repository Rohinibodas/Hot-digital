package TestExecute.Hydroflask.Smoke;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

//@Listeners(Utilities.TestListener.class)
public class Customer_Login 
{
	
	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper Hydro=new HydroHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void customerLogin() throws Exception {

		try {
	        Hydro.loginHydroflaskAccount("AccountDetails");
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
	
	/*@BeforeTest
	  @Parameters({"browser"})
	  public void startTest(String browser) throws Exception {
		 System.setProperty("configFile", "Hydroflask\\config.properties");
		  Login.signIn(browser);
		  Hydro.acceptPrivecy();
		 
	  }
	*/
	@BeforeTest
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "Hydroflask\\config.properties");
		  Login.signIn();
		  Hydro.acceptPrivecy();
		  
	  }

	
}
