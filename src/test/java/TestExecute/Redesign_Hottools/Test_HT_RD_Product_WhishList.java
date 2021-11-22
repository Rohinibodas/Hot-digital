package TestExecute.Redesign_Hottools;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import TestComponent.Redesign_Hottools.Redesign_HottoolsHelper;
import TestLib.Login;

public class Test_HT_RD_Product_WhishList {
	
	String datafile = "Redesign_Hottools//Redesign_HottoolsTestData.xlsx";	
	Redesign_HottoolsHelper Redesign_Hottools=new Redesign_HottoolsHelper(datafile);

	@Test(priority=1)
	public void Contactus(){
		try{
			Redesign_Hottools.agreeCookiesbanner();
			Redesign_Hottools.navigateMyAccount();
			Redesign_Hottools.loginApplication("RetailCustomerAccountDetails");
			Redesign_Hottools.clickHair_Tools();
			  Redesign_Hottools.Selectproduct();
			Redesign_Hottools.Verify_PDP();
			Redesign_Hottools.Add_product_to_Wishlist();
			Redesign_Hottools.remove_from_wishlist();
		
		
			
			
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
		//System.setProperty("configFile", "Hottools\\Config_Hottools_Staging.properties");
		//System.setProperty("configFile", "RedesignHottools\\Config_RedesignHottools_Production.properties");
		  Login.signIn("chrome"); 
	}

	@AfterTest
	public void clearBrowser()
	{
	//Common.closeAll();
	}


}
