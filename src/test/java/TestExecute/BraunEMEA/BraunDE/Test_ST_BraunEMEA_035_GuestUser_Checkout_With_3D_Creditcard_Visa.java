package TestExecute.BraunEMEA.BraunDE;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.BraunEMEASTAGE.BraunEMEAHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_ST_BraunEMEA_035_GuestUser_Checkout_With_3D_Creditcard_Visa {

	

	String datafile = "BraunEMEA//BraunUKTestData.xlsx";	
	BraunEMEAHelper BraunUK=new BraunEMEAHelper(datafile);


	@Test(priority=1)
	public void GuestUser_CheckoutwithCreditcard() throws Exception {

		try {
			Thread.sleep(6000);
			//BraunUK.Acceptcookies();
			//BraunUK.closepopup();
			BraunUK.StoreSelection("Germany");
			//BraunUK.GermanStoreSelection();
			BraunUK.Productselection();
			BraunUK.GEnavigateMinicart();
			BraunUK.GEcheckoutPage();
			BraunUK.shipping_Address("GuestEmail");
			BraunUK.CreditcardPayment("3DCardDetails");
			BraunUK.password("3DPassword");
			//BraunUK.order_Verifying();

		}
		catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		} 
	}

	/*@BeforeMethod
	@Parameters({"browser"}) 
	  public void startTest() throws Exception {
		System.setProperty("configFile", "BraunEMEA\\config.properties");
		  Login.signIn("chrome");
		  
	}*/
	
	
	@BeforeMethod
	@Parameters({"browser"}) 
	  public void startTest(String browser) throws Exception {
		System.setProperty("configFile", "BraunEMEA\\config.properties");
		  Login.signIn(browser);
		  }

	@AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}


}




	
	

