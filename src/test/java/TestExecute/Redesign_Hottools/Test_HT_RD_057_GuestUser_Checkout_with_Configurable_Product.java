package TestExecute.Redesign_Hottools;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.Redesign_Hottools.Redesign_HottoolsHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_HT_RD_057_GuestUser_Checkout_with_Configurable_Product {
	
	String datafile = "Redesign_Hottools//Redesign_HottoolsTestData.xlsx";	
	Redesign_HottoolsHelper Redesign_Hottools=new Redesign_HottoolsHelper(datafile);
	
	@Test(priority=1)
	public void AddtoCart_ConfigProduct(){
		try{
			Redesign_Hottools.agreeCookiesbanner();
				Redesign_Hottools.Close_popup();
				Redesign_Hottools.ClearMiniCart_Bag();
			Redesign_Hottools.clickHair_Tools();
			  Redesign_Hottools.Select_Configurable_product();
			  Redesign_Hottools.Select_Size();
			  Redesign_Hottools.clickminiCartButton();
			  Redesign_Hottools.clickCheckoutButton();
			  Redesign_Hottools.click_GuestCheckOut();
			  Redesign_Hottools.guestShipingAddress("ShippingAddress");
			  //Redesign_Hottools.Click_PaymetricPaymentMethod();
			  Redesign_Hottools.creditCard_payment("ccamex");
			  Redesign_Hottools.order_Success();

			
		     
			  
			
		}
		catch (Exception e) {
			Assert.fail(e.getMessage(), e);
		}
	}
	
	/*@BeforeMethod
	@Parameters({"browser"}) 
	  public void startTest(String browser) throws Exception {
		//System.setProperty("configFile", "Hottools\\Config_Hottools_Staging.properties");
		//System.setProperty("configFile", "Hottools\\Config_Hottools_Production.properties");
		  Login.signIn(browser);
		  
	  }*/
	
	@BeforeMethod
	//@Parameters({"browser"})  
	  public void startTest() throws Exception {
		System.setProperty("configFile", "Redesign_Hottools\\Config_Redesign_Hottools_Staging.properties");
		//System.setProperty("configFile", "Redesign_Hottools\\Config_Redesign_Hottools_Production.properties");
		  Login.signIn("chrome"); 
	}
  
  @AfterTest
	public void clearBrowser()
	{
		//Common.closeAll();

	}

}



