package TestExecute.Vicks;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.Vicks.VicksHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_VK_009_Promecode_for_Registeruser {
	
	String datafile = "Vicks//vicksURLs.xlsx";	
	VicksHelper vicks=new VicksHelper(datafile);
	@Test(priority=1)
	public void Promecode() throws Exception {

		try {
			vicks.Verifyhomepage();
//			vicks.Agreandproceed();
			vicks.loginVicks_Promecode("Promecode");
			vicks.Humidifiers_Vaporizers();
			vicks.productselect();
			vicks.addtocart();
			vicks.mincat();
		/*	vicks.checkout();
			vicks.shipingmethod();
			//vicks.shippingaddress("Promecode");
			vicks.paymentDetails("Promecode");
			vicks.Promocode("Promecode");
			//vicks.order_Verifying();*/
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
