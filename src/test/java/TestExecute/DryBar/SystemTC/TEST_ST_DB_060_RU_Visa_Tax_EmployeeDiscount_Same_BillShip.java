package TestExecute.DryBar.SystemTC;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_DB_060_RU_Visa_Tax_EmployeeDiscount_Same_BillShip
 {
	
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  
  public void RegisteredUser_Checkout_VisaCC_Tax_EmployeeDiscount_with_same_Billing_and_shipping() {
		try {
			
			  drybar.Accept();
			  drybar.verifyingHomePage();
			  drybar.navigateMyAccount();
			  drybar.loginApplication("AccountDetails");
			  drybar.ClearMiniCart_Bag();
			  drybar.Search_productname("ProductName");
			  drybar.View_Product();
			  drybar.Verify_GeneralUser_PDP(); 
			  drybar.clickMyaccount();
			  drybar.Signout();
			  drybar.navigateMyAccount();
			  drybar.loginApplication("HoTEmployeeAccountDetails");
			  drybar.Search_productname("ProductName");
			  drybar.Select_Searched_Product();
			  //drybar.Click_View_Product();
			  drybar.Verify_HoT_Employee_PDP();
			  drybar.clickAddtoBag();
			  drybar.clickminiCartButton();
			  drybar.clickCheckoutButton();
			  drybar.Verify_FreeGift();
			  drybar.addDeliveryAddress_registerUser("ShippingAddress");
			  drybar.Verify_tax();
			  drybar.click_Same_Billing_Address();
			  drybar.creditCard_payment("CCVisa");
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
		 //System.setProperty("configFile", "DryBar\\config.properties");
		  Login.signIn();
		 
		  
	  }

}
