package TestExecute.Hydroflask.Regression;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_HF_037_Checkout_GuestuserCC_my_hydro_and_configurable {
	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper Hydro=new HydroHelper(datafile);
	
  @Test
  public void checkout_with_credit_card_as_Guest_user_with_my_hydro_and_configurable_product() {
	  try { 
			 //Hydro.loginHydroflaskAccount("AccountDetails");
			 Hydro.orderSubmit("Bottles");
			 Hydro.Customize_Bottle_Standed("21 oz");
			 Hydro.checkOut();
			 Hydro.addDeliveryAddress("Address");
			 Hydro.updatePaymentAndSubmitOrder("Ccmastercard");
				
			}
			catch (Exception e) {
				
				Assert.fail(e.getMessage(), e);
			} 
		}

		  
		  
		  @BeforeTest
		  public void startTest() throws Exception {
//			 System.setProperty("configFile", "Hydroflask\\config.properties");
			  Login.signIn();
			  Hydro.acceptPrivecy();
			  Hydro.ClosADD();
			  
		  }
		  @AfterTest
		 	public void clearBrowser()
		 	{
		 		Common.closeAll();

		 	}

	   }
