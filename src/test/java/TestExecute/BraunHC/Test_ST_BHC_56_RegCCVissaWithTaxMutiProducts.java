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

public class Test_ST_BHC_56_RegCCVissaWithTaxMutiProducts {

	String datafile = "BraunHC//BraunHCTestData.xlsx";	
	BraunHCHelper BraunHC=new BraunHCHelper(datafile);
	
	@Test(priority=1)
	public void _RegisteredUser_Checkout_visaCC_with_Tax_Multiple_produc() throws Exception {

		try {
			
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
			BraunHC.UpdatePaymentAndSubmitOrder("PaymentDetailsMaster");
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
		
		//Common.closeAll();

	}
}
