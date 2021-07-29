package TestExecute.Hydroflask.Smoke;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_ST_HF_038_PDP_thumbnail_verification_of_bundle_product {
	
	
	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper Hydro=new HydroHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  
	 public void checkout_with_credit_card_as_registered_user_with_bundle_my_hydro_and_configurable_product() {
		  try{
	        
				
				  Hydro.loginHydroflaskAccount("AccountDetails");
				  
				  Hydro.Customize_Bottle_Standed("24 oz");;
				 
			  Hydro.serachproduct_addtocart("Serving Bundle");
			  
			
			 Hydro.checkOut(); 
			 Hydro.addDeliveryAddress_registerUser("Address");
			 Hydro.updatePaymentAndSubmitOrder("Ccmastercard");
			 
			 
	  
		  }
			catch (Exception e) {
				e.printStackTrace();
				
				Assert.fail(e.getMessage(), e);
			} 
	}
		

		@AfterTest
		public void clearBrowser()
		{
	      // Common.closeAll();

		}
		
		@BeforeTest
		  public void startTest() throws Exception {
			 System.setProperty("configFile", "Hydroflask\\config.properties");
			  Login.signIn();
			  Hydro.acceptPrivecy();
			  Hydro.ClosADD();		  
		  }
	}
