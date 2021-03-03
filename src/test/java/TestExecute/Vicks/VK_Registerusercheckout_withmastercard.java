package TestExecute.Vicks;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.Vicks.VicksHelper;
import TestLib.Common;
import TestLib.Login;

public class VK_Registerusercheckout_withmastercard {
	String datafile = "Vickshumdifier//Vickstestdata.xlsx";	
	VicksHelper vicks=new VicksHelper(datafile);
	@Test(priority=1)
	public void Searchproducr() throws Exception {

		try {
			
			vicks.Verifyhomepage();
			vicks.loginVicks("AccountDetails");
			vicks.clickHumidifiers();
			vicks.productselect();
			vicks.addtocart();
			vicks.mincat();
			vicks.checkout();
			//Vickshumdifier.shipingaddress("Address");
			vicks.shipingmethod();
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
			System.setProperty("configFile", "Vickshumdifier\\config.properties");
			  Login.signIn();
			 
			  
		  }

}
