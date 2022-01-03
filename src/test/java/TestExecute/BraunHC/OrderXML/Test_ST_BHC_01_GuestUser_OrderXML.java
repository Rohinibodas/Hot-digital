package TestExecute.BraunHC.OrderXML;

import org.testng.annotations.Test;

import TestComponent.BraunHC.BraunHCHelper;
import TestLib.Common;
import TestLib.Login;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class Test_ST_BHC_01_GuestUser_OrderXML {
	String datafile = "BraunHC//BraunHCTestData.xlsx";	
	BraunHCHelper BraunHC=new BraunHCHelper(datafile);
	
	@Test(priority=1)
	public void GuestUser_OrderXML() throws Exception {

		try {
			
			BraunHC.AGREEPROCEED();
			BraunHC.PopUp();
			BraunHC.Mouseover();
			HashMap<String,HashMap<String,String>> productinfromation=BraunHC.productshoppingcart();
			//HashMap<String,HashMap<String,String>> productinfromation=BraunHC.productselect();
			BraunHC.Addtocart();
			BraunHC.ViewandEditcartPage();
			BraunHC.checkoutPage();
			HashMap<String,String> shippingaddresss=BraunHC.guestShipingAddress("ShippingAddress");
			BraunHC.ShippingMethods();
	    	BraunHC.AddressVerfication();
			HashMap<String,String> ordertaxammount=BraunHC.orderamountinfo();
			HashMap<String,String>paymentdetriles=BraunHC.paymentdetails("PaymentDetails");
			String order=BraunHC.PlaceOrder();
			BraunHC.Adminlogin("AdminLogins");
			BraunHC.selectManulExport(order);
			BraunHC.productinfromationvalidation(productinfromation, order);
			BraunHC.shippingvalidaing_GustUserXML(shippingaddresss, order); 
		    BraunHC.TotalvalidationXML(ordertaxammount, order);
			BraunHC.card_details_validationXML(paymentdetriles, order);
		}
		catch (Exception e) {   
			Assert.fail(e.getMessage(), e);
		} 
	} 
	

	@BeforeTest
    public void startTest() throws Exception {
		// System.setProperty("configFile", "BraunHC\\Config_BraunHC_Production.properties");
		 //System.setProperty("configFile", "BraunHC\\Config_BraunHC_Staging.properties");
		   	    
    Login.signIn();
    }
	
	
	@AfterTest
	public void clearBrowser()
	{
		
	//Common.closeAll();

	}
}
