package TestExecute.Hydroflask.O2C_E2E_Orders;
  
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_HF_004_Bundleproduct_GuestUserCC_Checkout {
	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper Hydro=new HydroHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
 
  public void Checkout_with_credit_card_as_Guest_user_with_bundle_product() {
		try{
			
			String Website=Hydro.URL();
			String Description ="Bundleproduct_GuestUserCC_Checkout";
			String Paymentmethod="Pay-Pal";
			
		    Hydro.serachproduct_addtocart("Wide Mouth Accessory Bundle");
		    Hydro.checkOut();
		    HashMap<String,String> Shipping=Hydro.E2E_addDeliveryAddress("Address");
		    HashMap<String,String> data=Hydro.E2E_Validation();
		    String OrderId=Hydro.updatePaymentAndSubmitOrder("Ccmastercard");
		
		
//		   String OrderId="12345";
	       Hydro.E2E_writeResultstoXLSx(Website,Description,OrderId,Paymentmethod,data.get("subtotlaValue"),Shipping.get("ShippingZip"),Shipping.get("Shippingstate"),data.get("shippingammountvalue"),data.get("Taxammountvalue"),data.get("ActualTotalammountvalue"),data.get("ExpectedTotalAmmountvalue"),data.get("Discountammountvalue"));
		
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