package TestExecute.Vicks;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.Vicks.VicksHelper;
import TestLib.Common;
import TestLib.Login;


public class VicksGuestusercheckoutwithmastercard {
	String datafile = "Vickshumdifier//Vickstestdata.xlsx";	
	VicksHelper vicks=new VicksHelper(datafile);
	@Test(priority=1)
	public void Searchproducr() throws Exception {
	
		try {
			vicks.Verifyhomepage();
			vicks.clickHumidifiers();
			vicks.productselect();
			vicks.addtocart();
			vicks.mincat();
			vicks.checkout();
			vicks.shipingaddress("Address");
			vicks.paymentDetailsandPlaceOrder("PaymentDetails");
		}
		catch (Exception e) {
			Assert.fail(e.getMessage(), e);
		} 
	}

	
	@AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}
		
		@BeforeMethod
		  public void startTest() throws Exception {
			System.setProperty("configFile", "Vicks\\config.properties");
			  Login.signIn();
			 
			  
		  }


}
