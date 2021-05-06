package TestExecute.hottools.SmokeTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.Hottools.HottoolsHelpr;
import TestLib.Common;
import TestLib.Login;

public class HT_ST_DitributorQuickOrderCasesCCMastercard {
	
	String datafile = "Hottools//HottoolsTestData.xlsx";	
	HottoolsHelpr Hottools=new HottoolsHelpr(datafile);

	@Test(priority=1)
	public void DitributorQuickOrderCasesM(){

		try{
			Hottools.agreeCookiesbanner();
			Hottools.Newslettersignup();
			Hottools.distributorsignin("DistributorAccountDetails");
			Hottools.QuickOrder();
			Hottools.QuickOrderDetailsCases("QuickOrderDetails");
			Hottools.Quickcheckoutpage();
			//Hottools.QuickPaymentMethod();
			Hottools.QuickCreditcardPayment("MastercardDetails");
			Hottools.CasesRegistereduserorderSuccesspage();
		}
		catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}
	
	 
	@BeforeMethod
	@Parameters({"browser"}) 
	  public void startTest(String browser) throws Exception {
		System.setProperty("configFile", "Hottools\\config.properties");
		  Login.signIn(browser);
		  
	  }
	
	/*@BeforeMethod
	@Parameters({"browser"})  
	  public void startTest() throws Exception {
		System.setProperty("configFile", "Hottools\\config.properties");
		  Login.signIn("chrome");
	}*/

	@AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}

}
