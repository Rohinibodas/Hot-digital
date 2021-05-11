package TestExecute.BraunEMEAProduction.BraunFRP;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.BraunEMEAPRODUCTION.BraunEMEAProducrionHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_ST_RegisterUserCheckoutwith_InvalidCreditcard {
	String datafile = "BraunEMEAPRODUCTION//BraunUKPROTestData.xlsx";
	BraunEMEAProducrionHelper BraunUK=new BraunEMEAProducrionHelper(datafile);
	
	
	
	@Test(priority=1)
	public void RegistercheckoutwithCreditcard() throws Exception {

		try {
			Thread.sleep(6000);
			BraunUK.Acceptcookies();
			//BraunUK.closepopup();
			//BraunUK.Countryselection();
			   //BraunUK.FranceStoreSelection();
			BraunUK.FRsingin("AccountDetails");
		    BraunUK.Productselection();
			BraunUK.navigateMinicart();
			BraunUK.shippingAddressDetails();
			BraunUK.FRInvalidCreditcardPayment("InvalidCreditCard");
			//BraunUK.CreditcardPayment("PaymentcardDetails");
			/*BraunUK.checkoutPage();
			BraunUK.shipping_Address("GuestEmail");
			BraunUK.CreditcardPayment("PaymentcardDetails");*/
			
			
			
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
		System.setProperty("configFile", "BraunEMEAPRODUCTION\\config.properties");
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


