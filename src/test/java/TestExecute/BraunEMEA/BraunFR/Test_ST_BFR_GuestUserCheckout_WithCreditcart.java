package TestExecute.BraunEMEA.BraunFR;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.BraunEMEA.BraunUKHelper;
import TestComponent.BraunEMEASTAGE.BraunEMEAHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_ST_BFR_GuestUserCheckout_WithCreditcart {


	String datafile = "BraunEMEA//BraunUKTestData.xlsx";	
	BraunEMEAHelper BraunUK=new BraunEMEAHelper(datafile);


		@Test(priority=1)
		public void GuestUsercheckoutwithCreditcard() throws Exception {

			try {
				Thread.sleep(6000);
				BraunUK.Acceptcookies();
				BraunUK.closepopup();
				//BraunUK.GermanStoreSelection();
				//BraunUK.Countryselection();
				BraunUK.StoreSelection("France");
				//BraunUK.FranceStoreSelection();
				BraunUK.FranceProductselection();
				//BraunUK.Productselection();
				BraunUK.navigateMinicart();
				BraunUK.checkoutPage();
				BraunUK.shipping_Address("GuestEmail");
				BraunUK.FRCreditcardPayment("PaymentcardDetails");

			}
			catch (Exception e) {

				Assert.fail(e.getMessage(), e);
			} 
		}

		/*@BeforeMethod
		//@Parameters({"browser"}) 
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

