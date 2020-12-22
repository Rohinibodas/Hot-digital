package TestExecute.Oxo.Mobile;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;


import TestComponent.oxo.OxoMobileHelper;
import TestLib.Common;
import TestLib.Login;

public class CreateNewAccount {
	String datafile = "oxo//OxoTestData.xlsx";	
	OxoMobileHelper oxo=new OxoMobileHelper(datafile);
	@Test(priority=1)
	
	public void createAccount() throws Exception {

		try {
			oxo.closetheadd();
			oxo.CreateNewAccount("AccountDetails");
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	@AfterTest
	public void clearBrowser()
	{
		//Common.closeAll();

	}
	@BeforeMethod
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "Oxo\\config.properties");
		 Login.signIn("chrome","Galaxy S5");
		 // Login.signIn();
		 
		  
	  }
}
