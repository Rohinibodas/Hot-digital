package TestExecute.Febreze.SystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import TestComponent.Febreze.FebrezeHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_FBZ_07_GuestuserBrowserSearch {
	String datafile = "Febreze//FebrezeTestData.xlsx";	
	FebrezeHelper febreze=new FebrezeHelper(datafile);
	
	@Test(priority=1)
	public void guestbrowsersearch() {
		try {
			febreze.Acceptcookies();
            febreze.browsersearch("ProductName");
            febreze.zerosearchProduct("Zero_search");

		}catch(Exception | Error e){
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
