package TestExecute.Hydroflask.Smoke;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_ST_HF_051_checkout_with_Amex_card_as_Guest_user_with_single_product_tax_with_different_Shipping_Billingaddress

{
	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper Hydro=new HydroHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)

  public void gustuserCheckoutusingAmexCC_withDifferent_Shipping_BillingAddress() {
		try {
		Hydro.orderSubmit("Bottles");
		Hydro.checkOut();
		Hydro.addDeliveryAddress("Address");
		Hydro.verifyingTax_field();
		Hydro.edit_BillingAddress_gustuser("");
		Hydro.updatePaymentAndSubmitOrder("PaymentDetails");
		
		
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


