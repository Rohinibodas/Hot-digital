package TestExecute.Febreze.ModularTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.Febreze.FebrezeHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_FBZ_02_ValidateAccountInformation {

	String datafile = "Febreze//FebrezeTestData.xlsx";	
	FebrezeHelper Febreze=new FebrezeHelper(datafile);
	
	@Test(priority=1)
	public void EditAccountinformation() throws Exception {

		try {
			
		     Febreze.Acceptcookies();	
		     Febreze.clicksignup();
			Febreze.Accountcreation("AccountCreation");
			Febreze.Account_Information();
			Febreze.changeProfileName("AccountCreation");
			Febreze.changeProfileEmail("AccountCreation");
			Febreze.changeprofilePassword("AccountCreation");
			
			}
		catch (Exception e) {
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	@AfterTest
	public void clearBrowser() {
		Common.closeAll();

	}

	@BeforeMethod
	public void startTest() throws Exception {
		System.setProperty("configFile", "Febreze\\Config_Febreze_Staging.properties");
		Login.signIn();

	}



}
