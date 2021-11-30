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

public class Test_ST_BHC_33_RegUserPromocodeCC {
	String datafile = "BraunHC//BraunHCTestData.xlsx";	
	BraunHCHelper BraunHC=new BraunHCHelper(datafile);
	
	@Test(priority=1)
	public void BarunHC_RegUser_CheckoutCC_applypromo_code() throws Exception {

		try {
			
			
			BraunHC.AGREEPROCEED();
			BraunHC.PopUp();
			BraunHC.loginBraunHC("AccountDetails");
			BraunHC.Select_ProductinThermometers("Forehead Thermometer");
			BraunHC.Addtocart();
			BraunHC.ViewandEditcartPage();
			BraunHC.Applypromocode("promocode");
			BraunHC.checkoutPage();
			BraunHC.ShippingMethods();
			//BraunHC.AddressVerfication();
			BraunHC.UpdatePaymentAndSubmitOrder("PaymentDetails");
			BraunHC.RegistereduserOrderSuccesspage();
			
		}
		catch (Exception e) {
			Assert.fail(e.getMessage(), e);
		} 
	}
	

	@BeforeTest
    public void startTest() throws Exception {
		// System.setProperty("configFile", "BraunHC\\Config_BraunHC_Production.properties");
		 //System.setProperty("configFile", "BraunHC\\Config_BraunHC_Staging.properties");
		   	    
    Login.signIn();
    }
	
	
	@AfterTest
	public void clearBrowser()
	{
		
		Common.closeAll();

	}
}
