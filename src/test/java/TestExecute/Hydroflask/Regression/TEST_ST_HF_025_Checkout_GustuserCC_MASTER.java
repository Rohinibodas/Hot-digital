package TestExecute.Hydroflask.Regression;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_HF_025_Checkout_GustuserCC_MASTER {
	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper Hydro=new HydroHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void guestuser_with_credit_card_MASTER() {
	  try{
			
			Hydro.order("Bottles");
			Hydro.checkOut();
			Hydro.addDeliveryAddress("Address");
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

