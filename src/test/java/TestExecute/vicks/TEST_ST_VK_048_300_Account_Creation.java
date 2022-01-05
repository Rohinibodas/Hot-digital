package TestExecute.vicks;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.testng.annotations.Parameters;

import TestComponent.Vicks.VicksHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_VK_048_300_Account_Creation {
	String datafile = "Vicks//Vickstestdata.xlsx";
	VicksHelper vicks = new VicksHelper(datafile);

	@Parameters({"start","end"})
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void _300_Account_Creation(int start,int end) {

		try {
			System.out.println(start);
			System.out.println(end);
			vicks.Verifyhomepage();
			
			for(int i=start;i<=end;i++)
				{

				String Email="perftest"+i+"@example.com";
			try {

			
//			vicks.Agreandproceed();
//			vicks.CreateAccount("AccountDetails");
			vicks.CreateAccounts("AccountDetails",Email);
			vicks.Logout();

		
			}
				
		catch (Exception e) {
			Common.closeAll();
		}
//			Assert.fail(e.getMessage(), e);
		}
				
	
		
	}
		catch (Exception e) {
			
		}
	}

	@AfterTest
	public void clearBrowser() {
		Common.closeAll();

	}

	@BeforeMethod
	public void startTest() throws Exception {
		System.setProperty("configFile", "Vicks\\config.properties");
		Login.signIn();

	}

}
