package TestExecute.BraunHC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.BraunHC.BraunHCHelper;
import TestLib.Common;
import TestLib.Login;

public class BraunHC_Checkout_with_New_shipping_Address {
	String datafile = "BraunHC//BraunHCTestData.xlsx";	
	BraunHCHelper BraunHC=new BraunHCHelper(datafile);
	
	@Test(priority=1)
	public void RegisteredUserCheckoutwithnewaddress() throws Exception {

		try {
			
			//BraunHC.AGREEPROCEED();
			BraunHC.loginBraunHC("AccountDetails");
			BraunHC.Mouseover();
			BraunHC.mouseoverproduct();
			BraunHC.Addtocart("AccountDetails");
			BraunHC.ShippingcartPage();
			BraunHC.navigateShippingaddress();
			BraunHC.RegisterUser_ShippingAddress();
			BraunHC.UpdatePaymentAndSubmitOrder("PaymentDetails");
			BraunHC.RegistereduserOrderSuccesspage();
			
		}
		catch (Exception e) {
			Assert.fail(e.getMessage(), e);
		} 
	}
	


	/*@BeforeMethod
	@Parameters({"browser"})  
	  public void startTest1() throws Exception {
		System.setProperty("configFile", "BraunHC\\config.properties");
		  Login.signIn("chrome");
		  
	  }*/
@BeforeTest
public void startTest() throws Exception {
	System.setProperty("configFile", "BraunHC\\config.properties");
Login.signIn();
}
	  
	
	@AfterTest
	public void clearBrowser()
	{
		
		Common.closeAll();

	}
}
