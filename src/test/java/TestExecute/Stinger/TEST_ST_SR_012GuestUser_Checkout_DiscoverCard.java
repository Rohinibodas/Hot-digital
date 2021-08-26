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

public class TEST_ST_SR_012GuestUser_Checkout_DiscoverCard {
	String datafile = "Stinger//StingerTestData.xlsx";	
	StingerHelper Stinger=new StingerHelper(datafile);
	
	@Test(priority=1)
	public void GuestCheckout() throws Exception {

		try {
			
			
			//Stinger.AgreeAndProceed();
			Stinger.categoryMenuItem();
			Stinger.Addtocart();
			Stinger.checkoutPage();
			Stinger.shipping_Address("Address");
			Stinger.addPaymentDetails("PaymentDetailsDiscover");
			
			
			
		}
		catch (Exception e) {
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	 @BeforeTest
     public void startTest1() throws Exception {
     	//System.setProperty("configFile", "Stinger\\config_Stinger_Production.properties");
    	 //System.setProperty("configFile", "Stinger\\config_Stinger_Staging.properties");
    	    
     Login.signIn();
     }
	
	@AfterTest
	public void clearBrowser()
	{
		
		Common.closeAll();

	}

}

