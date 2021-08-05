package TestExecute.Revlon.SmokeTC;


import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.RevlonUk.RevlonUKHelper;
import TestComponent.revlon.RevelonHelper;
import TestLib.Common;
import TestLib.Login;
import junit.framework.Assert;

public class RHT_ST_ValidateGuestusercheckoutwith2products {
	String datafile = "revlon//RevlonTestData.xlsx";	
	RevelonHelper revelon=new RevelonHelper(datafile);
	
	@Test(priority=1)
	public void Guestmultipleproducctselection() throws Exception {

		try {
			
			
			revelon.Newslettersignup();
			revelon.acceptPrivecy();
		
			revelon.searchProduct("productName");
			revelon.Twoproductselection();
			revelon.checkoutPage();
			revelon.navigateCheckoutGuest("Guest_shipping");
			revelon.FreeShippingmethod();
			revelon.updatePaymentAndSubmitOrder("PaymentDetails");
		}
		catch (Exception | Error e) {
			Assert.fail(e.getMessage());
		} 
	}

    @BeforeMethod
	@Parameters({"browser"}) 
	  public void startTest(String browser) throws Exception {
		System.setProperty("configFile", "Revelon\\config.properties");
		  Login.signIn(browser);
		  
	  }/*
	
	@BeforeMethod
	  @Parameters({"browser"})  
	 	  public void startTest() throws Exception {
		System.setProperty("configFile", "Revelon\\config.properties");
		  Login.signIn("chrome");
		  
	  }*/
	
	@AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}

}
