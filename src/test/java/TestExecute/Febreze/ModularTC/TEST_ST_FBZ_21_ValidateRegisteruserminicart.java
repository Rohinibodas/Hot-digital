package TestExecute.Febreze.ModularTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.Febreze.FebrezeHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_FBZ_21_ValidateRegisteruserminicart {
	String datafile = "Febreze//FebrezeTestData.xlsx";	
	FebrezeHelper febreze=new FebrezeHelper(datafile);

	@Test(priority=1)
	public void Registeruserminicart() {
		try {
			febreze.Acceptcookies();
			febreze.Login("AccountDetails");
			febreze.CategoryProductselect();
			febreze.Navigateminicart();
			febreze.NavigateMycartpage();
			
		}catch(Exception | Error e){
			Assert.fail();
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


