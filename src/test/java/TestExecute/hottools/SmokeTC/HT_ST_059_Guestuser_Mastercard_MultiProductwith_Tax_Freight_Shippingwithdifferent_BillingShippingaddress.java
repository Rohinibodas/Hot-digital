package TestExecute.hottools.SmokeTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.Hottools.HottoolsHelpr;
import TestLib.Common;
import TestLib.Login;

public class HT_ST_059_Guestuser_Mastercard_MultiProductwith_Tax_Freight_Shippingwithdifferent_BillingShippingaddress {
	
	String datafile = "Hottools//HottoolsTestData.xlsx";	
	HottoolsHelpr Hottools=new HottoolsHelpr(datafile);

	@Test(priority=1)
	public void GuestCheckoutwithCreditCard(){
		try{
			Hottools.Newslettersignup();
			Hottools.agreeCookiesbanner();
			Hottools.CategorySelection();
			Hottools.TwoCategoryProductSelection();
			Hottools.TwoproductCategoryMincart();
			Hottools.Guestcheckoutpage("Guestshippingaddress");
			Hottools.Taxdifferentshippingandbilling("shippingAddress");
			Hottools.VerifyTax();
			Hottools.GuestCreditcard("MastercardDetails");
			//Hottools.GuestuserorderSuccesspage();		
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
	
	/*@BeforeMethod
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
