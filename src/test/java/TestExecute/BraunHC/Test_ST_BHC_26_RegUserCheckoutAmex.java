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

public class Test_ST_BHC_26_RegUserCheckoutAmex {
	String datafile = "BraunHC//BraunHCTestData.xlsx";	
	BraunHCHelper BraunHC=new BraunHCHelper(datafile);
	
	@Test(priority=1)
	public void RegisteredUserCheckout() throws Exception {

		try {
			
			BraunHC.AGREEPROCEED();
			BraunHC.PopUp();
			BraunHC.loginBraunHC("AccountDetails");
			//BraunHC.ClearMiniCart_Bag();
			BraunHC.Select_ProductinThermometers("No Touch Thermometer");
		    BraunHC.Addtocart();
			BraunHC.ViewandEditcartPage();
			BraunHC.checkoutPage();	
			//BraunHC.Registeruseraddress();
         	BraunHC.ShippingMethods();
		   BraunHC.UpdateGuestPaymentAndSubmitOrder("PaymentDetailsAEMX");
		   BraunHC.RegistereduserOrderSuccesspage();
		}
		catch (Exception e) {
			Assert.fail(e.getMessage(), e);
		} 
	}
	

	@BeforeTest
    public void startTest() throws Exception {
		// System.setProperty("configFile", "BraunHC\\Config_BraunHC_Production.properties");
		// System.setProperty("configFile", "BraunHC\\Config_BraunHC_Staging.properties");
		   	    
    Login.signIn();
    }
	
	
	@AfterTest
	public void clearBrowser()
	{
		
		Common.closeAll();

	}
}
