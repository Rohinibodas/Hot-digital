package TestExecute.DryBar.SystemTC;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_DB_088_Validation_of_Magento_and_XML_Payment_details {

	String datafile = "DryBar//DryBarTestData.xlsx";
	DryBarHelper drybar = new DryBarHelper(datafile);

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validation_of_Magento_and_XML_Payment_details() throws Throwable {

		try {
			  drybar.Accept(); drybar.verifyingHomePage(); 
			  drybar.Search_Buttercup_Dryer();
			  drybar.Select_ButtercupDryer_Product(); 
			  drybar.clickAddtoBag();
			  drybar.Search_Special_Value_set_Product();
			  drybar.Select_Specialvalueset_Product(); 
			  drybar.clickAddtoBag();
			  drybar.Search_kit_Product(); 
			  drybar.Select_Kit_Product();
			  drybar.clickAddtoBag(); 
			  drybar.Search_productname("Product_Name");
			  drybar.Select_Lash_Blowout_Product(); 
			  drybar.clickAddtoBag();
			  drybar.clickminiCartButton(); 
			  drybar.click_View_editcart(); 
			  drybar.clickCheckoutButton();
			  drybar.click_GuestCheckOut(); 
			  drybar.Verify_FreeGift();
			  drybar.guestShipingAddress("ShippingAddress");
			  drybar.select_USPS_StandardGround_shippingMethod(); 
			  drybar.click_Next();
			  drybar.couponCode("15%Couponcode");
			  drybar.creditCard_payment("ccdiscover");
			  String order=drybar.Verify_order();
			 drybar.DrybarAdminlogin("MagentoAccountDetails");
			Thread.sleep(120000);
			 drybar.Navigate_Order_Details_Page(order);
			 HashMap<String, HashMap<String, String>> productinformation = drybar.productinfromation();
			drybar.selectManulExport(order);
			drybar.productinfromationvalidation(productinformation, order);
			

		}

		catch (Exception e) {
			e.printStackTrace();

			Assert.fail(e.getMessage(), e);
		}
	}

	@AfterTest
	public void clearBrowser() {
		 Common.closeAll();

	}

	@BeforeTest
	public void startTest() throws Exception {
		//System.setProperty("configFile", "DryBar\\config.properties");
		Login.signIn();

	}

}
