package TestExecute.Stinger;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.Stinger.StingerHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_SR_024Register_Invalid_creditcard {

	String datafile = "Stinger//StingerTestData.xlsx";	
	StingerHelper Stinger=new StingerHelper(datafile);
	
	@Test(priority=1)
	public void Register_invalid_CardDetails() throws Exception {

		try {
			//Stinger.AgreeAndProceed();
			Stinger.Global_search("ProductName");
			Stinger.Addtocart();
			Stinger.checkoutPage();
			Stinger.shipping_Address("Address");
			Stinger.invalidCreditCard("InvalidCreditCard");
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

