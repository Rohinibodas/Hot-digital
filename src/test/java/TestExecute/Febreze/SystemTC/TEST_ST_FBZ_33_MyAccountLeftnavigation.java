package TestExecute.Febreze.SystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.Febreze.FebrezeHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_FBZ_33_MyAccountLeftnavigation {

	String datafile = "Febreze//FebrezeTestData.xlsx";	
	FebrezeHelper febreze=new FebrezeHelper(datafile);
	
	@Test(priority=1)
	public void MyAccountLeftnavigation() throws Exception {

		try {
			febreze.Acceptcookies();
			febreze.Login("AccountDetails");
			febreze.Account_Information();
			febreze.My_Orders();
			febreze.ClickAddress_book();
			febreze.Account_Information();
			 
			
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
		//System.setProperty("configFile", "Febreze\\Config_Febreze_Staging.properties");
		Login.signIn();

	}

	
}
