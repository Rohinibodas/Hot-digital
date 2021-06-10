package TestExecute.BraunHC;

import org.testng.annotations.Test;

import TestComponent.BraunHC.BraunHCHelper;
import TestLib.Common;
import TestLib.Login;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class BraunHC_GuestUserCheckout {
	String datafile = "BraunHC//BraunHCTestData.xlsx";	
	BraunHCHelper BraunHC=new BraunHCHelper(datafile);
	
	@Test(priority=1)
	public void GuestCategoryCheckoutCC() throws Exception {

		try {
			BraunHC.AGREEPROCEED();
			//BraunHC.selectCategoryProduct("AccountDetails");
			BraunHC.Global_search("SearchproductName");
			BraunHC.Addtocart("AccountDetails");
			BraunHC.ShippingcartPage();
			BraunHC.GuestShippingaddress();
			//BraunHC.MoneyOrderpayment();
			BraunHC.Taxcalucaltion("Guest_shipping");


			BraunHC.UpdatePaymentAndSubmitOrder("PaymentDetails");
			//BraunHC.GuestOrderSuccesspage();
			BraunHC.RegistereduserOrderSuccesspage();
		}
		catch (Exception e) {
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	/*@BeforeMethod
	@Parameters({"browser"}) 
	  public void startTest(String browser) throws Exception {
		System.setProperty("configFile", "BraunHC\\config.properties");
		  Login.signIn(browser);
	  }
	
	*/@BeforeMethod
	@Parameters({"browser"})  
	  public void startTest() throws Exception {
		System.setProperty("configFile", "BraunHC\\config.properties");
		  Login.signIn("chrome"); 
	  }
	
	@AfterTest
	public void clearBrowser()
	{
		
		Common.closeAll();

	}

}
