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

public class Test_ST_BHC_57_RegCCAmexWithTaxMutiProducts {


	String datafile = "BraunHC//BraunHCTestData.xlsx";	
	BraunHCHelper BraunHC=new BraunHCHelper(datafile);
	
	@Test(priority=1)
	public void RegisteredUser_Checkout_AmexCC_Tax_with_Multiple_Products() throws Exception {

		try {
			
		/*	BraunHC.AGREEPROCEED();
			BraunHC.loginBraunHC("AccountDetails");
			//BraunHC.Mouseover();
			BraunHC.mouseoverproduct();
			//BraunHC.PopUp();
			//BraunHC.Select_ProductinThermometers("No Touch Thermometer");
			BraunHC.Addtocart();
			BraunHC.ViewandEditcartPage();
			BraunHC.checkoutPage();
			BraunHC.ShippingMethods();
			BraunHC.Tax_validation();
			BraunHC.UpdateGuestPaymentAndSubmitOrder("PaymentDetailsAEMX");
			BraunHC.RegistereduserOrderSuccesspage();
*/
			
			BraunHC.AGREEPROCEED();
	        BraunHC.PopUp();
			BraunHC.loginBraunHC("AccountDetails");
			BraunHC.Mouseover();
			//BraunHC.PopUp();
			BraunHC.Two_products_in_plp();
			BraunHC.Addtocart();
			BraunHC.ViewandEditcartPage();
			BraunHC.checkoutPage();
			BraunHC.ShippingMethods();
			BraunHC.Tax_validation();
			BraunHC.UpdatePaymentAndSubmitOrder("PaymentDetailsAEMX");
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
