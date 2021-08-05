package TestExecute.hottools.SmokeTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.Hottools.HottoolsHelpr;
import TestLib.Common;
import TestLib.Login;

public class HT_ST_064_Guestuser_AMEX_MultiProduct_Discount_Promo_Tax_Shipping_SameBilling_Shippingaddress {


		

		String datafile = "Hottools//HottoolsTestData.xlsx";	
		HottoolsHelpr Hottools=new HottoolsHelpr(datafile);

		@Test(priority=1)
		public void Promocode(){

			try{
				Hottools.agreeCookiesbanner();
				Hottools.Newslettersignup();
				Hottools.CategorySelection();
				Hottools.TwoCategoryProductSelection();
				Hottools.TwoproductCategoryMincart();
				Hottools.checkoutpage();
				Hottools.Guestcheckoutpages("Guestshippingaddress");
				Hottools.ValidatingPromocode("PromocodeproductName");
				Hottools.GuestCreditcard("Guestshippingaddress");
				Hottools.GuestuserorderSuccesspage();
			}
			catch (Exception e) {

				Assert.fail(e.getMessage(), e);
			}
		}
		
		@BeforeMethod
		@Parameters({"browser"}) 
		  public void startTest(String browser) throws Exception {
			System.setProperty("configFile", "Hottools\\config.properties");
			  Login.signIn(browser);
		  }
		
	      /*
		@BeforeMethod
		@Parameters({"browser"})  
		  public void startTest() throws Exception {
			System.setProperty("configFile", "Hottools\\config.properties");
			  Login.signIn("chrome");
			  
		  }
*/
		@AfterTest
		public void clearBrowser()
		{
			Common.closeAll();

		}


	}
