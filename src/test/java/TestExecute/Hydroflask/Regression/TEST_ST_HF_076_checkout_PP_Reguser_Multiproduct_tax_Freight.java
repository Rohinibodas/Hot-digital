
package TestExecute.Hydroflask.Regression;

import org.testng.Assert; 
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_HF_076_checkout_PP_Reguser_Multiproduct_tax_Freight {
	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper Hydro=new HydroHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	
	public void GuestUser_Checkout_Multi_product_tax_freight() throws Exception {

		try {
			Hydro.loginHydroflaskAccount("AccountDetails");
			Hydro.serachproduct_addtocart("Wide Mouth Accessory Bundle");
			Hydro.orderSubmit("Bottles");
			Hydro.checkOut();
			Hydro.addDeliveryAddress_registerUser_With_Federal_Express_Shipping("Address");
			Hydro.verifyingTax_field();
			Hydro.payPal_Payment("PaypalDetails");
		}
		catch (Exception e) {
			e.printStackTrace();
			
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	
	
	@AfterTest
	public void clearBrowser()
	{
	Common.closeAll();

	}
	
	@BeforeTest
	  public void startTest() throws Exception {
//		 System.setProperty("configFile", "Hydroflask\\config.properties");
		  Login.signIn();
		  Hydro.acceptPrivecy();
		  Hydro.ClosADD();
		  
	  }

}
