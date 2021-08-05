package TestExecute.BraunHC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.BraunHC.BraunHCHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_BraunUS_ST_004_RegisteredUser_Checkout_visaCC_with_Tax_Multiple_product {

	String datafile = "BraunHC//BraunHCTestData.xlsx";	
	BraunHCHelper BraunHC=new BraunHCHelper(datafile);
	
	@Test(priority=1)
	public void _RegisteredUser_Checkout_visaCC_with_Tax_Multiple_produc() throws Exception {

		try {
			
			//BraunHC.AGREEPROCEED();
			BraunHC.loginBraunHC("AccountDetails");
			BraunHC.Mouseover();
			BraunHC.Two_products_in_plp();
			BraunHC.Addtocart("AccountDetails");
			BraunHC.ShippingcartPage();
			BraunHC.navigateShippingaddress();
			BraunHC.Tax_validation();
			BraunHC.UpdatePaymentAndSubmitOrder("PaymentDetailsMaster");
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