package TestExecute.DryBar.SystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;

public class DB_ST_067_RegisteredUser_Checkout_AmexCC_EmployeeDiscount_NoTax_with_Diff_Billing_and_shipping {
	
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  
  public void RegisteredUser_Checkout_AmexCC_EmployeeDiscount_NoTax_with_Diff_Billing_and_shipping() {
		try {
			
			  drybar.Accept();
			  drybar.verifyingHomePage();
			  drybar.navigateMyAccount();
			  drybar.loginApplication("AccountDetails");
			  drybar.Search_productname("ProductName");
			  drybar.View_Product();
			  drybar.Verify_GeneralUser_PDP();
			  drybar.clickMyaccount();
			  drybar.Signout();
			  drybar.navigateMyAccount();
			  drybar.loginApplication("HoTEmployeeAccountDetails");
			  drybar.Search_productname("ProductName");
			  drybar.Click_View_Product();
			  drybar.Verify_HoT_Employee_PDP();
			  drybar.clickAddtoBag();
			  drybar.clickminiCartButton(); 
			  drybar.clickCheckoutButton();
			  drybar.addDeliveryAddress_registerUser("NoTaxShippingAddress");
			  drybar.Edit_BillingAddress_PaymetricPaymentMethod("BiillingAddress");
			  drybar.creditCard_payment("ccamex");
			  drybar.order_Success();
			
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
			
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	
	
	@AfterTest
	public void clearBrowser()
	{
	Common.closeAll();

	}
	

	@BeforeTest
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "DryBar\\config.properties");
		  Login.signIn();
		 
		  
	  }

}
