package TestExecute.Hydroflask.O2C_E2E_Orders;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;
  
public class TEST_ST_HF_008_BundleProduct_RegUserCC__promocode_Express_Checkout {
	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper Hydro=new HydroHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void Checkout_with_credit_card_as_registered_user_with_bundle_product() {
	  
	  try{
			String Website=Hydro.URL();
			String Description ="BundleProduct_RegUserCC__promocode_Express_Checkout";
			String Paymentmethod="Matser_CC";
			Hydro.loginHydroflaskAccount("AccountDetails");
            Hydro.serachproduct_addtocart("Wide Mouth Accessory Bundle");
			Hydro.checkOut();
			HashMap<String,String> Shipping=Hydro.addfrieightDeliveryAddress_registerUser("Address");
			Hydro.promationCode("Promationcode");
		    HashMap<String,String> data=Hydro.E2E_Validation();
		    String OrderId=Hydro.updatePaymentAndSubmitOrder("Ccmastercard");
			//String OrderId="12345";
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
//			 System.setProperty("configFile", "Hydroflask\\config.properties");
			  Login.signIn();
			  Hydro.acceptPrivecy();
			  Hydro.ClosADD();		  
		  }
  
}
