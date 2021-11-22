package TestExecute.Redesign_Hottools;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.Redesign_Hottools.Redesign_HottoolsHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_HT_RD_009_AddtoCart_Configurable_Product {
	
	String datafile = "Redesign_Hottools//Redesign_HottoolsTestData.xlsx";	
	Redesign_HottoolsHelper Redesign_Hottools=new Redesign_HottoolsHelper(datafile);
	
	@Test(priority=1)
	public void AddtoCart_ConfigProduct(){
		try{
			Redesign_Hottools.agreeCookiesbanner();
			Redesign_Hottools.signin("RetailCustomerAccountDetails");
			
			Redesign_Hottools.ClearMiniCart_Bag();
			Redesign_Hottools.clickHair_Tools();
			  //Redesign_Hottools.Selectproduct();
			  Redesign_Hottools.Select_Configurable_product();
			  Redesign_Hottools.Select_Size();
			  Redesign_Hottools.clickAddtoBag();
			
		     
			  
			
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
	//@Parameters({"browser"})  
	  public void startTest() throws Exception {
		System.setProperty("configFile", "Redesign_Hottools\\Config_Redesign_Hottools_Staging.properties");
		//System.setProperty("configFile", "Redesign_Hottools\\Config_Redesign_Hottools_Production.properties");
		  Login.signIn("chrome"); 
	}*/
	
		  
	 
	
	

	@AfterTest
	public void clearBrowser()
	{
	Common.closeAll();

	}

}
