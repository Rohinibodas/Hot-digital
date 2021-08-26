package TestExecute.Stinger;

import org.testng.annotations.Test;

import TestComponent.BraunHC.BraunHCHelper;
import TestComponent.Stinger.StingerHelper;
import TestLib.Common;
import TestLib.Login;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class TEST_ST_SR_031Gustuser_Invalid_promocode {
	String datafile = "Stinger//StingerTestData.xlsx";	
	StingerHelper Stinger=new StingerHelper(datafile);
	
	@Test(priority=1)
	public void Guestinvalid_Promocode() throws Exception {

		try {
			//Stinger.AgreeAndProceed();
			//Stinger.Global_search("ProductName");
			Stinger.categoryMenuItem();
			Stinger.Addtocart();
			Stinger.checkoutPage();
			Stinger.shipping_Address("Address");
			Stinger.invad_promocode("promocode"); 
			
		}
		catch (Exception e) {
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	 @BeforeTest
     public void startTest1() throws Exception {
     	//System.setProperty("configFile", "Stinger\\config_Stinger_Production.properties");
    	// System.setProperty("configFile", "Stinger\\config_Stinger_Staging.properties");
    	    
     Login.signIn();
     }
	
	@AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}

}

