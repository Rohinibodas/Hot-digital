package TestExecute.Oxo;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.oxo.OxoHelper;
import TestLib.BaseDriver;
import TestLib.Login;

public class LogOut {
	String datafile = "oxo//OxoTestData.xlsx";	
	OxoHelper oxo=new OxoHelper(datafile);
	@Test //(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void logout(){
		try{
			oxo.closetheadd();
			oxo.loginOxo("AccountDetails");
			oxo.logOut();
		}
catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		} 
	}
		@AfterTest
		public void clearBrowser() throws Exception
		{
			BaseDriver.setDriver(null);
			

		}
		
		@BeforeMethod
		  public void startTest() throws Exception {
			 System.setProperty("configFile", "Oxo\\config.properties");
			  Login.signIn();
			 
			  
		  }
  }
