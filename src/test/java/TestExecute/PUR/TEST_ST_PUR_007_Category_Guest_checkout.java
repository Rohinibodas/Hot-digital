package TestExecute.PUR;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.PUR.PurHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_PUR_007_Category_Guest_checkout {
		
		String datafile = "PUR//PUR_TestData.xlsx";	
		PurHelper PUR=new PurHelper(datafile);
		
		@Test(priority=1)
		public void categoryGuestcheckout(){

			try{
				
				PUR.Mouseover();
				//PUR.categoryMenuItem();
				PUR.Productselection();
				PUR.navigateMinicart();
				PUR.checkoutPage();
				PUR.shipping_Address("Address");
				PUR.updatePaymentAndSubmitOrder("PaymentDetails");
				
			}
			catch (Exception e) {

				Assert.fail(e.getMessage(), e);
			}
		}
		
		
		/*@BeforeMethod
		  public void startTest() throws Exception {
			System.setProperty("configFile", "PUR//config.properties");
			  Login.signIn("firefox");
			   }*/
		
		@BeforeMethod
		//@Parameters() 
		  public void startTest() throws Exception {
			// System.setProperty("configFile", "PUR\\Config_PUR_Staging.properties");
			  Login.signIn();
		}


		@AfterTest
		public void clearBrowser()
		{
			Common.closeAll();

		}
		

	}

	




