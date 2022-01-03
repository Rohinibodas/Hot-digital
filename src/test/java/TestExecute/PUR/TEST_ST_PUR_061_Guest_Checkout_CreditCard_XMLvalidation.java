package TestExecute.PUR;
import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.PUR.PurHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_PUR_061_Guest_Checkout_CreditCard_XMLvalidation {
public class User_Browser_Search {
		
		String datafile = "PUR//PUR_TestData.xlsx";	
		PurHelper PUR=new PurHelper(datafile);
		
		@Test(priority=1)
		public void User_Browser_Search() throws Throwable{

			try{
				
				
				
			PUR.AgreeAndProceed();
			
			PUR.Mouseover();	
			PUR.Productselect();
			PUR.Addtobag();
			PUR.View_cart();
			HashMap<String,HashMap<String,String>> productinfromation=PUR.productvalidation();				
			PUR.checkoutPage();
		  	HashMap<String,String> shippingaddresssf=PUR.addDeliveryAddress_validation("Address"); 
		  	HashMap<String,String> ordertaxammount=PUR.orderamountinfo();

		  	PUR.click_action();	
			HashMap<String,String>paymentdetriles= PUR.add_PaymentDetail("PaymentDetails");

			String OrderIdNumber= PUR.Verify_order_page();
		      PUR.magentoAdminlogin("Adminlogin");
		    
		      PUR.selectManulExport(OrderIdNumber);
			
			
			        
		         PUR.productinfromationvalidation(productinfromation, OrderIdNumber);
				 PUR.shippingvalidaing_GustUserXML(shippingaddresssf, OrderIdNumber);
				// PUR.TotalvalidationXML(ordertaxammount, OrderIdNumber);
				 PUR.card_details_validationXML(paymentdetriles, OrderIdNumber);
			
			
			
			
			
			
			}
			catch (Exception e) {

				Assert.fail(e.getMessage(), e);
			}
		}
		
		
		/*@BeforeMethod
		  public void startTest() throws Exception {
			System.setProperty("configFile", "PUR//config.properties");
			  Login.signIn("chrome");
			   }*/
		
		@BeforeMethod
		//@Parameters() 
		  public void startTest() throws Exception {
			// System.setProperty("configFile", "PUR\\Config_PUR_Staging.properties");
			  Login.signIn();
		}

		@AfterTest
		public void clearBrowser()
		{
			//Common.closeAll();

		}
		

	}

	









}
