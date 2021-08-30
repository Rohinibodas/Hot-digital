package TestExecute.DryBar.SystemTC;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;
import Utilities.ExcelReader;

public class TEST_ST_DB_066_Tax_Calculation_in_Paymentpage {
	
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Tax_Calculation_in_Paymentpage() throws Exception {

		try {
			
			drybar.Accept();
			drybar.verifyingHomePage();
		    drybar.clickHairProducts();
		    drybar.SelectShampoos();
			drybar.Selectproduct();
		    drybar.increaseProductQuantity("3");
		    drybar.clickAddtoBag();
		    drybar.clickminiCartButton();
		    drybar.clickCheckoutButton(); 
		    drybar.click_GuestCheckOut();
		    drybar.ClearMiniCart_Bag();
		    drybar.guestShipingAddress("ShippingAddress");
		    drybar.Expediated_shippingmethod();
		    drybar.click_Next();
			drybar.tax_calculation("TaxRate");
		    drybar.Click_PaymetricPaymentMethod();
		    drybar.Edit_BillingAddress_PaymetricPaymentMethod("BiillingAddress");
			drybar.creditCard_payment("CCVisa");
		    drybar.order_Success();
			
		}
			
		catch (Exception e) {
	e.printStackTrace();
			
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	@BeforeTest
	  public void startTest() throws Exception {
		// System.setProperty("configFile", "DryBar\\config.properties");
		  Login.signIn();
		 
		  
	  }
	
	
	@AfterTest
	public void clearBrowser()
	{
		
		Common.closeAll();

	}

}
