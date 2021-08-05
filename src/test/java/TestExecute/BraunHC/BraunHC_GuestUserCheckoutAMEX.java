package TestExecute.BraunHC;

import org.testng.annotations.Test;

import TestComponent.BraunHC.BraunHCHelper;
import TestLib.Common;
import TestLib.Login;

import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class BraunHC_GuestUserCheckoutAMEX {
	String datafile = "BraunHC//BraunHCTestData.xlsx";	
	BraunHCHelper BraunHC=new BraunHCHelper(datafile);
	
	@Test(priority=1)
	public void RegCheckoutMaster() throws Exception {

		try {
			//BraunHC.AGREEPROCEED();
			BraunHC.selectCategoryProduct("AccountDetails");
			//BraunHC.Global_search("SearchproductName");
			BraunHC.Addtocart("AccountDetails");
			BraunHC.ShippingcartPage();
			BraunHC.GuestShippingaddress();
			//BraunHC.MoneyOrderpayment();
			//BraunHC.GuestOrderSuccesspage();
			BraunHC.UpdateGuestPaymentAndSubmitOrder("PaymentDetailsAEMX");
			BraunHC.RegistereduserOrderSuccesspage();


		}
		catch (Exception e) {
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	/*	@BeforeTest
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "BraunHC\\config.properties");
		  Login.signIn();
	}*/
	
	
	@BeforeMethod
	//@Parameters({"browser"})  
	  public void startTest() throws Exception {
		System.setProperty("configFile", "BraunHC\\config.properties");
		  Login.signIn("chrome");
		  
	  }
	
	@AfterTest
	public void clearBrowser()
	{
		
		//Common.closeAll();

	}

}
