
package TestExecute.Hydroflask.Smoke;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_ST_HF_080_checkout_with_Expresspaypal_as_Guestuserwith_Multi_product_and_Discount_Promocode_tax_and_Standard_Shipping_with_Same_billing_Shippingaddresses {
	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper Hydro=new HydroHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	
	public void GuestUser_Checkout_Express_paypal_Multi_product_Discount_tax_Standard_shipping() throws Exception {

		try {
		   
			Hydro.searchproduct_addtocart("32 oz Tumbler Lid");
			Hydro.orderSubmit("Bottles");
			Hydro.CheckOutPaypal_Promocode("PaypalDetails");
			
	
						
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
		 System.setProperty("configFile", "Hydroflask\\config.properties");
		  Login.signIn();
		  Hydro.acceptPrivecy();
		  Hydro.ClosADD();
		  
	  }

}
