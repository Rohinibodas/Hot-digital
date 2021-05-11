package TestExecute.BraunEMEAProduction.BraunDEP;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.BraunEMEAPRODUCTION.BraunEMEAProducrionHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_ST_Ordersuccesspage {
	//String datafile = "BraunEMEA//BraunUKTestData.xlsx";
	String datafile = "BraunEMEAPRODUCTION//BraunUKPROTestData.xlsx";
	BraunEMEAProducrionHelper BraunUK=new BraunEMEAProducrionHelper(datafile);
	@Test(priority=1)
	public void OrderSuccess() throws Exception {

		try {
			Thread.sleep(6000);
			BraunUK.Acceptcookies();
			//BraunUK.closepopup();
			     //BraunUK.StoreSelection("Germany");
			//BraunUK.GermanStoreSelection();
			//BraunUK.Countryselection();
			//BraunUK.FranceStoreSelection();
			BraunUK.GEProductselection();
			BraunUK.GEnavigateMinicart();
			BraunUK.GEcheckoutPage();
			BraunUK.shipping_Address("GuestEmail");
			BraunUK.GECreditcardPayment("PaymentcardDetails");
			//BraunUK.order_Verifying();
			
			
		}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	/*@BeforeMethod
	@Parameters({"browser"}) 
	  public void startTest() throws Exception {
		System.setProperty("configFile", "BraunEMEAPRODUCTION\\config.properties");
		  Login.signIn("chrome");
		  
	  }*/

	
	@BeforeMethod
	@Parameters({"browser"}) 
	  public void startTest() throws Exception {
		System.setProperty("configFile", "BraunEMEA\\config.properties");
		  Login.signIn("broswer");
		  
	  }
	
	/*@BeforeMethod
	@Parameters({"browser"}) 
	  public void startTest(String browser) throws Exception {
		System.setProperty("configFile", "BraunUK\\config.properties");
		  Login.signIn(browser);
		  }*/
	
	@AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}


}


