package TestExecute.PUR.O2C_E2E;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.PUR.PurHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_05_PUR_Gusetuser_checkout_Mastercard {
		
		String datafile = "PUR//PUR_TestData.xlsx";	
		PurHelper PUR=new PurHelper(datafile);
		
		@Test(priority=1)
		public void Gusetuser_checkout_Mastercard(){

			try{
				PUR.prepareOrdersData("PUR_E2E_orderDetails.xlsx");
				Thread.sleep(5000);
				PUR.AgreeAndProceed();
				String Website=PUR.URL();
				String Description ="TEST_ST_05_PUR_Gusetuser_checkout_Mastercard";
				//PUR.singin("CustomerAccountdetails");
				PUR.Mouseover();
				
				PUR.Productselect();
				PUR.Addtobag();
                PUR.checkoutPage();
    			HashMap<String,String> Shipping=PUR.gus2_Shipingdetails("Address");
    			//HashMap<String,String> data=PUR.OrderSummaryValidation();
				//PUR.shipping_Address("Address");
    			PUR.click_Next();
    			HashMap<String,String> data=PUR.OrderSummaryValidation();
				//PUR.GuestUserApplyPromocode("promocode");
				HashMap<String,String> Payment=PUR.creditCard_payment("MastercardDetails");
				
				//PUR.updatePaymentAndSubmitOrder("PaymentDetails");
				 String OrderIdNumber= PUR.Verify_order_page();
				 System.out.println(OrderIdNumber);
				 PUR.order_Success();
				    PUR.writeOrderNumber(Website, OrderIdNumber,Description, data.get("subtotlaValue"),data.get("shippingammountvalue"),data.get("Taxammountvalue"),data.get("ActualTotalammountvalue"),data.get("ExpectedTotalAmmountvalue"),data.get("Discountammountvalue"),Shipping.get("ShippingState"),Shipping.get("ShippingZip"),Payment.get("Card"));

				
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
		  public void startTest() throws Exception {
			// System.setProperty("configFile", "PUR\\Config_PUR_Staging.properties");
			  Login.signIn();
	}

		@AfterTest
		public void clearBrowser()
		{
			Common.closeAll();

		}
		

	}

	




