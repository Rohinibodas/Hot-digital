package TestExecute.Vickshumdifier;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.Vickshumdifier.VickshumdifierHelper;
import TestLib.Common;
import TestLib.Login;

public class VicksGuestusercheckoutwithmastercard {
	String datafile = "Vickshumdifier//Vickstestdata.xlsx";	
	VickshumdifierHelper Vickshumdifier=new VickshumdifierHelper(datafile);
	@Test(priority=1)
	public void Searchproducr() throws Exception {

		try {
			Vickshumdifier.Verifyhomepage();
			Vickshumdifier.clickHumidifiers();
			Vickshumdifier.productselect();
			Vickshumdifier.addtocart();
			Vickshumdifier.mincat();
			Vickshumdifier.checkout();
			Vickshumdifier.shipingaddress("Address");
			Vickshumdifier.carddetails("PaymentDetails2");
		}
		catch (Exception e) {
			Assert.fail(e.getMessage(), e);
		} 
	}

	
	@AfterTest
	public void clearBrowser()
	{
		//Common.closeAll();

	}
		
		@BeforeMethod
		  public void startTest() throws Exception {
			System.setProperty("configFile", "Vickshumdifier\\config.properties");
			  Login.signIn();
			 
			  
		  }


}
