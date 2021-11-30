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

public class Test_ST_BHC_45_CCWithTwoProductsInPlp {
	String datafile = "BraunHC//BraunHCTestData.xlsx";	
	BraunHCHelper BraunHC=new BraunHCHelper(datafile);
	
	@Test(priority=1)
	public void Checkout_with_Two_products_in_plp() throws Exception {

		try {
			
			
			
			BraunHC.AGREEPROCEED();
			BraunHC.PopUp();
			BraunHC.Mouseover();
			BraunHC.Two_products_in_plp();
			BraunHC.Addtocart();
			BraunHC.ViewandEditcartPage();
			BraunHC.checkoutPage();
			BraunHC.GuestShippingaddress("Address");
			BraunHC.ShippingMethods();
			BraunHC.AddressVerfication();
			BraunHC.UpdatePaymentAndSubmitOrder("PaymentDetails");
			//BraunHC.RegistereduserOrderSuccesspage();
			BraunHC.GuestOrderSuccesspage();
			
			
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
