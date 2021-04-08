package TestExecute.hottools.MobSystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.Hottools.HottoolsMobHelper;
import TestLib.Common;
import TestLib.Login;

public class HT_ST_Mob_DistributorCheckoutPurchaseOrder {

	String datafile = "Hottools//HottoolsTestData.xlsx";	
	HottoolsMobHelper Hottools=new HottoolsMobHelper(datafile);
	
	@Test(priority=1)
	public void DistributorCheckoutPurchaseOrder() throws Exception {

		try {
			Thread.sleep(10000);
			//Hottools.agreeCookiesbanner();
			Hottools.slider();
			Hottools.DistributorLoginpage();
			Hottools.distributorsignin("DistributorAccountDetails");
			Hottools.searchingProducts("productName");
			Hottools.minicartProduct("productName");
			Hottools.checkoutpage();
			Hottools.PurchaseOrder("PurchaseOrderDetails");
			Hottools.PurchaseRegistereduserorderSuccesspage();
		}
		catch (Exception e) {
			Assert.fail(e.getMessage(), e);
		} 
	}
		
	@BeforeMethod
	@Parameters({"device"})  
	  public void startTest(String Device) throws Exception {
		System.setProperty("configFile", "Hottools\\config.properties");
		  Login.signIn("chrome",Device);
		  
	  }
	
	/*@BeforeMethod
    //@Parameters({"device"})
      public void startTest() throws Exception {
        System.setProperty("configFile", "Hottools\\config.properties");
          Login.signIn("chrome","Galaxy S5");
      }*/
	
	@AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}

}

