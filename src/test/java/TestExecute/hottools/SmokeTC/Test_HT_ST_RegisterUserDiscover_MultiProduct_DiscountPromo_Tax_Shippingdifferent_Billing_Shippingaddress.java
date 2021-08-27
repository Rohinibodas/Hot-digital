package TestExecute.hottools.SmokeTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.Hottools.HottoolsHelpr;
import TestLib.Common;
import TestLib.Login;

public class Test_HT_ST_RegisterUserDiscover_MultiProduct_DiscountPromo_Tax_Shippingdifferent_Billing_Shippingaddress {




		String datafile = "Hottools//HottoolsTestData.xlsx";	
		HottoolsHelpr Hottools=new HottoolsHelpr(datafile);

		@Test(priority=1)
		public void Retailer_User_MultiProduct_promocodecheckout_Discover(){

			try{
				Hottools.agreeCookiesbanner();
				Hottools.Newslettersignup();
				Hottools.singin("RetailCustomerAccountDetails");
				Hottools.categoryMenuItem();
				Hottools.minicartProduct("productName");
				Hottools.miniCart("productName");
				Hottools.checkoutpage();
			   
				Hottools.ValidatingPromocode("PromocodeproductName");
				Hottools.CreditcardPayment_promocde("PaymentDetails");
			    
				Hottools.RegistereduserorderSuccesspage();
			}
			catch (Exception e) {

				Assert.fail(e.getMessage(), e);
			}
		}
		
		@BeforeMethod
		@Parameters({"browser"}) 
		  public void startTest(String browser) throws Exception {
			//System.setProperty("configFile", "Hottools\\Config_Hottools_Staging.properties");
			//System.setProperty("configFile", "Hottools\\Config_Hottools_Production.properties");
			  Login.signIn(browser); 
		  }

	   /*@BeforeMethod
		@Parameters({"browser"})  
		  public void startTest() throws Exception {
			System.setProperty("configFile", "Hottools\\Config_Hottools_Staging.properties");
		//System.setProperty("configFile", "Hottools\\Config_Hottools_Production.properties");
			  Login.signIn("chrome");
		  }*/

		@AfterTest
		public void clearBrowser()
		{
			Common.closeAll();

		}


	}

