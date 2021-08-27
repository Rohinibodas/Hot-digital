package TestExecute.hottools.SmokeTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.Hottools.HottoolsHelpr;
import TestLib.Common;
import TestLib.Login;

public class Test_HT_ST_Guestuser_Checkout_Promocode {


	String datafile = "Hottools//HottoolsTestData.xlsx";	
	HottoolsHelpr Hottools=new HottoolsHelpr(datafile);

	@Test(priority=1)
	public void Promocode(){

		try{
			Hottools.agreeCookiesbanner();
			Hottools.Newslettersignup();
			Hottools.searchingProducts("PromocodeproductName");
			Hottools.minicartProduct("PromocodeproductName");
			Hottools.miniCart("PromocodeproductName");
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

