package TestExecute.PUR;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.PUR.PurHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_PUR_014_MyOrders {
	
	String datafile = "PUR//PUR_TestData.xlsx";	
	PurHelper PUR=new PurHelper(datafile);
	
	@Test(priority=1)
	public void validateMyOrders(){

		try{
			
			//PUR.ClicktheSignbutton();
			PUR.singin("CustomerAccountdetails");
			PUR.searchProduct("productName");
			PUR.Addtocart();
			PUR.checkoutPage();
			PUR.AddAddress();
			PUR.updatePaymentAndSubmitOrder("PaymentDetails");
			PUR.navigateMyOrder();
				
		}
		catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}
	
	
	@BeforeMethod
	//@Parameters() 
	  public void startTest() throws Exception {
	 System.setProperty("configFile", "PUR\\Config_PUR_Staging.properties");
		  Login.signIn();
	}
	
	/*@BeforeMethod
	@Parameters({"browser"}) 
	  public void startTest(String browser) throws Exception {
		System.setProperty("configFile", "PUR\\config.properties");
		  Login.signIn(browser);
	}
*/
	@AfterTest
	public void clearBrowser()
	{
	Common.closeAll();

	}

}
