package TestExecute.PUR;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.PUR.PurHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_PUR_041_RegUser_Adding_two_productsCheckout {
		
		String datafile = "PUR//PUR_TestData.xlsx";	
		PurHelper PUR=new PurHelper(datafile);
		
		@Test(priority=1)
		public void registerAdding_Twoproducts(){

			try{
				//PUR.AgreeAndProceed();
				PUR.ClicktheSignbutton();
				PUR.singin("CustomerAccountdetails");
				PUR.Mouseover();
				PUR.Multiple_Products();
				PUR.Addtocart();
				PUR.checkoutPage();
				PUR.AddAddress();
				PUR.updatePaymentAndSubmitOrder("PaymentDetails");
			
				
			}
			catch (Exception e) {

				Assert.fail(e.getMessage(), e);
			}
		}
		
		
		/*@BeforeMethod
		  public void startTest() throws Exception {
			System.setProperty("configFile", "PUR//config.properties");
			  Login.signIn("chrome");
			   }*/
		
		
		@BeforeMethod
		@Parameters({"browser"}) 
		  public void startTest(String browser) throws Exception {
			//System.setProperty("configFile", "PUR\\config.properties");
			  Login.signIn(browser);
		}

		@AfterTest
		public void clearBrowser()
		{
			Common.closeAll();

		}
		

	}

	





	


