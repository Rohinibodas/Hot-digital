package TestExecute.BraunHC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.BraunHC.BraunHCHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_ST_BHC_64_RegCCDiscoverNotaxMutiProdcuts {


	String datafile = "BraunHC//BraunHCTestData.xlsx";	
	BraunHCHelper BraunHC=new BraunHCHelper(datafile);
	
	@Test(priority=1)
	public void RegCheckoutDiscover_NoTax_with_Same_Billing_and_Shipping() throws Exception {

		try {
			
			
			BraunHC.AGREEPROCEED();
	        BraunHC.PopUp();
			//BraunHC.Mouseover();
			BraunHC.mouseoverproduct();
			//BraunHC.PopUp();
			//BraunHC.Select_ProductinThermometers("Forehead Thermometer");
			BraunHC.Addtocart();
			BraunHC.ViewandEditcartPage();
			BraunHC.checkoutPage();
			BraunHC.ShippingMethods();
			BraunHC.NoTAX_shippingAddress();
			BraunHC.UpdatePaymentAndSubmitOrder("PaymentDetailsDiscover");
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
