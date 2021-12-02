package TestExecute.RevlonUS;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.RevlonUS.RevelonHelper;
import TestLib.Common;
import TestLib.Login;

public class RHT_MT_AC_Account_Creation {
	String datafile = "revlon//RevlonTestData.xlsx";	
	RevelonHelper revelon=new RevelonHelper(datafile);
	
	@Test(priority=1)
	public void createAccount() throws Exception {

		try {
			revelon.CreateNewAccount("AccountDetails");
		}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	@BeforeMethod
	//@Parameters({"browser"})  
	  public void startTest() throws Exception {
		System.setProperty("configFile", "Revelon\\config.properties");
		  Login.signIn("browser");
		  
	  }
	
	@AfterTest
	public void clearBrowser()
	{
		
		Common.closeAll();

	}

	

}
