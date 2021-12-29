package TestExecute.DryBar.SystemTC;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_DB_089_Validation_of_Payment_of_Order_XML {

	String datafile = "DryBar//DryBarTestData.xlsx";
	DryBarHelper drybar = new DryBarHelper(datafile);

	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	public void Validation_of_Payment_of_Order_XML() throws Throwable {

		try {
			
			  drybar.Accept(); 
			  drybar.verifyingHomePage();
			  drybar.clickHairProducts();
			  drybar.SelectShampoos(); 
			  drybar.Selectproduct(); 
			  drybar.Verify_PDP();
			  drybar.clickAddtoBag(); 
			  drybar.clickminiCartButton();
			  drybar.clickCheckoutButton(); 
			  drybar.click_GuestCheckOut();
			  drybar.Verify_FreeGift(); 
			  drybar.guestShipingAddress("ShippingAddress");
			  drybar.select_USPS_StandardGround_shippingMethod();
			  drybar.click_Next();
			  drybar.couponCode("FREECouponCode"); 
			  drybar.click_place_order_button(); //
			  String order=drybar.Verify_order();
			drybar.DrybarAdminlogin("MagentoAccountDetails"); 
			Thread.sleep(120000);
		    drybar.Navigate_Order_Details_Page(order); 
			drybar.selectManulExport(order);
		    drybar.PaymentInformation(order);
			

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
