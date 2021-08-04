package TestExecute.Revlon.SmokeTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.revlon.RevelonHelper;
import TestLib.Common;
import TestLib.Login;

public class RHT_CT_066_RegisterUserCheckout_Employeediscount_taxshipping_SameBillingaddress {


	String datafile = "revlon//RevlonTestData.xlsx";	
	RevelonHelper revelon=new RevelonHelper(datafile);
	
	@Test(priority=1)
	public void UserEmployeeDiscount_samebillingaddress() throws Exception {

		try {
		
			revelon.Newslettersignup();
			revelon.acceptPrivecy();
			revelon.loginRevlon("EmployeeDetails");
			revelon.searchProduct("productName");
			revelon.Productselection();
			revelon.navigateMinicart();
			revelon.navigateCartPage();
			revelon.checkoutPage();
			revelon.clickaddnewaddress();
			revelon.RegisteruseraddNewAddress("NewAddress");
			revelon.FreeShippingmethod();
			revelon.TaxandShippingAmountvalidation();
			revelon.updatePaymentAndSubmitOrder("PaymentDetails");
			
		}
		catch (Exception e) {
			Assert.fail(e.getMessage(), e);
		} 
	}

	@BeforeMethod
	@Parameters({"browser"}) 
	  public void startTest(String browser) throws Exception {
		System.setProperty("configFile", "Revelon\\config.properties");
		  Login.signIn(browser);
		  
	  }
	
	/*
	@BeforeMethod
	@Parameters({"browser"})  
	  public void startTest() throws Exception {
		System.setProperty("configFile", "Revelon\\config.properties");
		  Login.signIn("chrome");
		  
	  } 
	*/
	@AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}

	
}
