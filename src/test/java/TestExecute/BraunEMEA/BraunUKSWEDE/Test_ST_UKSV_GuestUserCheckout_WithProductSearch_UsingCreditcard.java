package TestExecute.BraunEMEA.BraunUKSWEDE;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.BraunEMEASTAGE.BraunEMEAHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_ST_UKSV_GuestUserCheckout_WithProductSearch_UsingCreditcard {
	String datafile = "BraunEMEA//BraunUKTestData.xlsx";	
	BraunEMEAHelper BraunUK=new BraunEMEAHelper(datafile);
		
		
	@Test(priority = 1)
	public void GuestUserCheckoutWithCreditcard() throws Exception {

		try {
			Thread.sleep(6000);
			BraunUK.Acceptcookies();
			BraunUK.closepopup();
			BraunUK.StoreSelection("Sweden");
			//BraunUK.UKSVproductname("productName");
			BraunUK.SD_productname("ItalyProductname");
			//BraunUK.Italyproductselection();
			//BraunUK.Swedenproductselection();
			BraunUK.Addtocart();
            BraunUK.SDnavigateMinicart();
            BraunUK.SDcheckoutPage();
            BraunUK.SDshipping_Address("Shipping_Address");
            BraunUK.SDCreditcardPayment("PaymentcardDetails");
		} catch (Exception e) {

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




