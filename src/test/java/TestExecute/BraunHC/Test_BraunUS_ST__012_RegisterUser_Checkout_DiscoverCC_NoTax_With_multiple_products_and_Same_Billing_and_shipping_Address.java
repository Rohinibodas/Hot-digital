package TestExecute.BraunHC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.BraunHC.BraunHCHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_BraunUS_ST__012_RegisterUser_Checkout_DiscoverCC_NoTax_With_multiple_products_and_Same_Billing_and_shipping_Address {


	String datafile = "BraunHC//BraunHCTestData.xlsx";	
	BraunHCHelper BraunHC=new BraunHCHelper(datafile);
	
	@Test(priority=1)
	public void RegCheckoutDiscover_Tax_with_Same_Billing_and_Shipping() throws Exception {

		try {
			//BraunHC.AGREEPROCEED();
			BraunHC.loginBraunHC("AccountDetails");
			BraunHC.Mouseover();
			BraunHC.mouseoverproduct();
			BraunHC.Addtocart("AccountDetails");
			BraunHC.ShippingcartPage();
			BraunHC.navigateShippingaddress();
			//BraunHC.NO_TAxRegisterUser_ShippingAddress();

			BraunHC.UpdatePaymentAndSubmitOrder("PaymentDetailsDiscover");
			BraunHC.RegistereduserOrderSuccesspage();
		}
		catch (Exception e) {
			Assert.fail(e.getMessage(), e);
		} 
	}
	@BeforeMethod
	@Parameters({"browser"}) 
	  public void startTest(String browser) throws Exception {
		System.setProperty("configFile", "BraunHC\\config.properties");
		  Login.signIn(browser);
		  
	  } 
	/*
	@BeforeMethod
	@Parameters({"browser"})  
	  public void startTest() throws Exception {
		System.setProperty("configFile", "BraunHC\\config.properties");
		  Login.signIn("chrome");
		  
	  }*/
	
	@AfterTest
	public void clearBrowser()
	{
		
		Common.closeAll();

	}

}
