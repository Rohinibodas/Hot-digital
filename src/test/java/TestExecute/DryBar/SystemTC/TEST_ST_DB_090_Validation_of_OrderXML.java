package TestExecute.DryBar.SystemTC;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_DB_090_Validation_of_OrderXML {
	
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
	
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void guest_Checkout_CreditCard() throws  Throwable {
		 
		
		
		drybar.Accept();
		drybar.verifyingHomePage();
		drybar.clickHairProducts();
		drybar.SelectShampoos();
	    drybar.Selectproduct();
		drybar.Verify_PDP(); 
		drybar.clickAddtoBag();
	    drybar.clickminiCartButton();
	    drybar.click_View_editcart();
	    HashMap<String,HashMap<String,String>> productinformation=drybar.productinfromation();
		 drybar.clickCheckoutButton(); 
		 drybar.click_GuestCheckOut();
		 drybar.Verify_FreeGift();
		 HashMap<String,String> shippingaddresssf=drybar.addDeliveryAddress_validation("ShippingAddress");
		 drybar.click_Next();
		 HashMap<String,String> BillingAddressf=drybar.addBillingAddress_validation("BiillingAddress");
		 HashMap<String,String>ordertaxammount=drybar.orderamountinfromation();
		 HashMap<String,String>paymentdetriles=drybar.add_PaymentDetails("PaymentDetails"); 
		  String order=drybar.verifyingSucesspage();
		  drybar.DrybarAdminlogin("MagentoAccountDetails");
		  String Transaction=drybar.Transactionid(order);
		  Thread.sleep(120000);
		  drybar.selectManulExport(order);
		  drybar.productinfromationvalidation(productinformation, order);
		  drybar.shippingvalidaing_GustUserXML(shippingaddresssf, order);
		  drybar.Billingaddress_GustUserXML_Validation(BillingAddressf, order);
		  drybar.TotalvalidationXML(ordertaxammount, order);
		  drybar.card_details_validationXML(paymentdetriles, order);
		  drybar.ValidatingTranctionIDInXML(order,Transaction);
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
