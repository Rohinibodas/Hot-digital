package TestExecute.Oxo.Mobile;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.oxo.OxoMobileHelper;
import TestLib.Common;
import TestLib.Login;

public class Forgot_Password{
	
	String datafile = "oxo//OxoTestData.xlsx";	
	OxoMobileHelper oxo=new OxoMobileHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	  public void Forgot_Password() {
			try{
				oxo.closetheadd();
				oxo.NavigationToggle();
				//oxo.OXOLogin();
				oxo.login();
				oxo.ForgotPassword("Forgetpassoword");
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
	
	
	/*@BeforeMethod
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "Oxo\\config.properties");
		 Login.signIn("chrome","iPhone X");
	 }*/
	@BeforeTest
	@Parameters({"device"})  
	  public void startTest(String Device) throws Exception {
		System.setProperty("configFile", "Oxo\\config.properties");
		Login.signIn("chrome",Device);
	  }
}
