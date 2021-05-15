package TestComponent.Honeywell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import TestLib.Common;
import TestLib.Sync;
import TestLib.Common.SelectBy;
import Utilities.ExcelReader;
import Utilities.ExtenantReportUtils;
import junit.framework.Assert;

public class Honeywellhelper {

		String datafile;
		ExcelReader excelData;
		Map<String, Map<String, String>> data=new HashMap<>();
		static ExtenantReportUtils report;
		public  Honeywellhelper(String datafile)
		{
			excelData=new ExcelReader(datafile);
			data=excelData.getExcelValue();
			this.data=data;
			if(Utilities.TestListener.report==null)
			{
				report=new ExtenantReportUtils("Honeywell");
				report.createTestcase("HoneywellTestCases");
			}
			else
			{
				this.report=Utilities.TestListener.report;
			}
		}
		
		/*public void loginHoneywell(String dataSet) throws Exception
		{
			Thread.sleep(7000);	
			Common.clickElement("xpath", "//a[@class='header-content__right-link']");
			//Thread.sleep(7000);
			Common.textBoxInput("xpath", "//input[@id='email']", data.get(dataSet).get("Email"));
			Common.textBoxInput("xpath", "//input[@name='login[password]']", data.get(dataSet).get("Password"));
			Common.clickElement("xpath", "//button[@class='action login primary']");
			}
		public void accountcreation(String dataset) throws Exception
		{
			Thread.sleep(5000);
			Common.clickElement("xpath", "//a[@class='header-content__right-link']");
			Common.clickElement("xpath", "//a[@class='action create primary']");
			Common.textBoxInput("id", "firstname", data.get(dataset).get("FirstName"));
			Common.textBoxInput("id", "lastname", data.get(dataset).get("LastName"));
			Common.textBoxInput("id", "email_address", data.get(dataset).get("Email"));
			Common.textBoxInput("id", "password", data.get(dataset).get("Password"));
			Common.textBoxInput("id", "password-confirmation", data.get(dataset).get("Password"));
			Common.clickElement("xpath", "//button[@class='action submit primary']");
		}	
		public void forgotpassword(String dataset) throws Exception
		{
			Thread.sleep(5000);
			Common.clickElement("xpath", "//span[contains(text(), 'Forgot Your Password?')]");
			Common.textBoxInput("id", "email_address", data.get(dataset).get("Email"));
			Common.clickElement("xpath", "//span[contains(text(), 'Reset Your Password')]");
			
		}*/
		
		
		public void verifyingHomePage() {
			try {
				Sync.waitPageLoad();
			int hompageiconsize= Common.findElements("xpath", "//img[@class='desktop-logo']").size();
			Common.assertionCheckwithReport(hompageiconsize>0, "verifying home page", "Home page contines Logo of product", "sucessfully lands on home page ", "faield lo land on Home page");
			}
			 catch(Exception |Error e) {
				     e.printStackTrace();
						ExtenantReportUtils.addFailedLog("verifying my home page", "Home page contines Logo of product", "user faield lands land on Home page", Common.getscreenShotPathforReport("homepage"));
						Assert.fail();
			}
		}
		public void clicksigninbutton() throws Exception{
			
			try{
			Sync.waitElementPresent("xpath", "//a[@class='header-content__right-link']");
			Common.clickElement("xpath", "//a[@class='header-content__right-link']");
			ExtenantReportUtils.addPassLog("verifying my account button", "It should lands on Customer login page", "user successfully  lands on Customer login page", Common.getscreenShotPathforReport("myaccountpass"));
			}
			 catch(Exception |Error e) {
		     
				ExtenantReportUtils.addFailedLog("verifying my account button", "It should lands on Customer login page", "user faield lands on Customer login page", Common.getscreenShotPathforReport("myaccountfaield"));
				Assert.fail();
			}
			
		}
		public void loginHoneywell(String dataSet) throws Exception
		{
			try{
				Thread.sleep(7000);
				Common.clickElement("xpath", "//a[@class='header-content__right-link']");
				Common.textBoxInput("xpath", "//input[@id='email']", data.get(dataSet).get("Email"));
				Common.textBoxInput("xpath", "//input[@id='pass']", data.get(dataSet).get("Password"));
				Common.clickElement("xpath", "//button[@class='action login primary']");
				ExtenantReportUtils.addPassLog("verifying account login", "It should lands on Customer my account page", "user successfully  lands on my account page", Common.getscreenShotPathforReport("loginpass"));
				}
			catch(Exception |Error e) {
				ExtenantReportUtils.addFailedLog("verifying account login", "It should lands on Customer my account page", "user failed lands on my account page", Common.getscreenShotPathforReport("loginfailed"));
				Assert.fail();
			}
		}
		public void createaccount(String dataSet) 

		{
			try{
				Thread.sleep(7000);	
				Common.clickElement("xpath", "//span[contains(text(), 'Create an Account')]");
				ExtenantReportUtils.addPassLog("verifying Create Account button", "It should lands on Create New Customer from Account form Page", "user  lands on Customer Account creation form Page", Common.getscreenShotPathforReport("createaccount"));
				}
			catch(Exception |Error e) {
				e.printStackTrace();
			        ExtenantReportUtils.addFailedLog("verifying Create Account button", "It should lands on Create New Customer from Account form Page", "user faield lands on Account form Page", Common.getscreenShotPathforReport("createaccount"));
					Assert.fail();
				}
			try{
				
				Common.textBoxInput("id", "firstname", data.get(dataSet).get("FirstName"));
				Common.textBoxInput("id", "lastname", data.get(dataSet).get("LastName"));
				Common.clickElement("xpath", "//input[@id='is_subscribed']");
				Common.textBoxInput("id", "email_address", data.get(dataSet).get("Email"));
				Common.textBoxInput("id", "password", data.get(dataSet).get("Password"));
				Common.textBoxInput("id", "password-confirmation", data.get(dataSet).get("Password"));
				Common.clickElement("xpath", "//button[@title='Create an Account']");
				ExtenantReportUtils.addPassLog("verifying Create Account from", "Account should be created successfully navigate to My Account page", "user successfully naviagted to account", Common.getscreenShotPathforReport("createdaccount"));
			}
			catch(Exception |Error e) {
		        ExtenantReportUtils.addFailedLog("verifying Create Account from", "Account should be created successfully navigate to My Account page", "user faield to create account", Common.getscreenShotPathforReport("createaccountfaield"));
				Assert.fail();
			}
		}

		public void Forgetpassword(String dataSet)
		{
			try{
				Thread.sleep(7000);	
				Common.clickElement("xpath","//a[@class='header-content__right-link']");
				Sync.waitElementClickable("xpath", "//a[@class='action remind']");
				Common.clickElement("xpath", "//form[@id='login-form']/fieldset//a");
				Common.textBoxInput("id", "email_address", data.get(dataSet).get("Email"));
				Common.textBoxInput("id", "captcha_user_forgotpassword", data.get(dataSet).get("Password"));
				Common.clickElement("xpath", "//span[text()='Reset My Password']");
				ExtenantReportUtils.addPassLog("verifying Forgot my password button", "It should land on forgot password form from account form Page", "user  lands on forgot password form Page", Common.getscreenShotPathforReport("forgotpassword"));
				}
			catch(Exception |Error e) {
				e.printStackTrace();
			        ExtenantReportUtils.addFailedLog("verifying Forgot my password button", "It should lands on forgot password form from Account form Page", "user faield land on forgot password form Page", Common.getscreenShotPathforReport("createaccount"));
					Assert.fail();
			}
			try
			{
				
				Common.textBoxInput("id", "email_address", data.get(dataSet).get("Email"));
				Common.clickElement("xpath", "//button[@class='action submit primary']");
				ExtenantReportUtils.addPassLog("verifying Forgot password from", "It should successfully navigate to login page and reset link should be sent to given email", "user successfully reset password", Common.getscreenShotPathforReport("passwordreset"));
		
			}
			catch(Exception |Error e) {
				ExtenantReportUtils.addFailedLog("verifying Forgot password from", "It should successfully navigate to login page and reset link should be sent to given email", "user failed to  reset password", Common.getscreenShotPathforReport("passwordreset"));
				Assert.fail();	
			}
			
		}
		public void clickShopButton() {
			try{
				Thread.sleep(4000);
				//Common.clickElement("id","ui-id-3");
				Common.mouseOver("id", "ui-id-3");
				ExtenantReportUtils.addPassLog("verifying Shop button in Header", "User navigate to shop category", "User successfully  navigate to shop category", Common.getscreenShotPathforReport("shopbutton"));
				
			}
				catch(Exception |Error e) {
					ExtenantReportUtils.addFailedLog("verifying Shop button in Header", "User navigate to shop category", "User unable navigate to shop category", Common.getscreenShotPathforReport("shopbutton"));		
					Assert.fail();	

					}
		}
		
		public void click_Airpurifiers()
		
		{
			clickShopButton();
			try{
				Sync.waitElementClickable("xpath", "//a[@title='Air Purifiers']");
				Common.clickElement("xpath", "//a[@title='Air Purifiers']");
				Common.assertionCheckwithReport(Common.getPageTitle().equals("Air Purifiers - Shop"),"Verifying Air Purifiers page","it shoud navigate to Air Purifiers", "successfully  navigate to Air Purifiers", "airpurifires");	

				
				
				
				/*
				Sync.waitElementClickable("xpath", "//a[@title='Thermometers']");
				Common.clickElement("xpath", "//a[@title='Thermometers']");
				Common.assertionCheckwithReport(Common.getPageTitle().equals("Air Purifiers - Shop"),"Verifying Air Purifiers page","it shoud navigate to Air Purifiers", "successfully  navigate to Air Purifiers", "airpurifires");	
				*/
			}
			catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("verifying airpurifiers Product category", "User navigate to airpurifiers product page", "user not able to click airpurifiers Product", Common.getscreenShotPathforReport("productincr"));		
			Assert.fail();	

			}
			
		}
			
		
		
		
		public void adding_product_toCart(String dataSet) {
			try {
				Thread.sleep(4000);
			String productname= data.get(dataSet).get("productname");
			System.out.println(productname);
			Common.clickElement("xpath", "//a[contains (text(),'"+productname+"')]");
			Common.assertionCheckwithReport(Common.getPageTitle().contains(productname),"verifying product name in PDP page", "after click the product in PLP page the same product navigating to PDP page", "sucssfully navigate to pdp page", "faield to navigate to pdp page");
			}
			catch(Exception |Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying product name in PDP pag", "after click the product in PLP page the same product navigating to PDP page", "sucssfully navigate to pdp page", Common.getscreenShotPathforReport("pdppage"));		
				Assert.fail();	

				}
			clickAddtoBag();

		}
		
		
		
		public void increaseProductQuantity(String Quantity) throws Exception{
			try{
			Sync.waitPageLoad();
			
			int size=Common.findElements("xpath", "//select[@id='qty']").size();
			
			if(size>0){
				Common.dropdown("xpath", "//select[@id='qty']",SelectBy.VALUE, Quantity);
			}
			else{
			for(int i=1;i<Integer.valueOf(Quantity);i++){
				Sync.waitElementPresent("id", "qty");
				Common.clickElement("xpath", "//input[@id='qty']/following::button[1]");
			    }
			}
			ExtenantReportUtils.addPassLog("verifying product Quantity", "User increaseProductQuantity", "user successfully increaseProductQuantity", Common.getscreenShotPathforReport("productIncr"));	
			}        
			catch(Exception |Error e) {	    
				e.printStackTrace();		
				ExtenantReportUtils.addFailedLog("verifying product quantity", "User increase product quantity", "user failed to increase Product quantity", Common.getscreenShotPathforReport("productIncr"));		
				Assert.fail();	}
		}
		public void clickAddtoBag() {
			try{
			Sync.waitPageLoad();
			Thread.sleep(5000);
			Common.scrollIntoView("id", "product-addtocart-button");
			Common.clickElement("id", "product-addtocart-button");
			ExtenantReportUtils.addPassLog("verifying add to cart button", "User click add to card button", "user successfully click add to cart button", Common.getscreenShotPathforReport("Clickaadtocart"));
			}
			catch(Exception |Error e) {
			   e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying add to cart button", "User click add to card button", "user failed to click add to cart button", Common.getscreenShotPathforReport("failedtoclickutton"));
				Assert.fail();
			}
			
		}
		public void clickminicartButton() throws Exception{
			try{
				Thread.sleep(3000);
			    Sync.waitElementPresent("xpath", "//a[contains(@class,'showcart')]");
		        Common.clickElement("xpath", "//a[contains(@class,'showcart')]");
		        ExtenantReportUtils.addPassLog("verifying mini cart button", "User click mini cart button", "user successfilly click mini cart button", Common.getscreenShotPathforReport("minicartbutton"));
			}
		    catch(Exception |Error e) {
		 	   ExtenantReportUtils.addFailedLog("verifying mini cart button", "User click mini cart button", "user failed to click mini cart button", Common.getscreenShotPathforReport("faieldtominicartbutton"));
				Assert.fail();
			}
		}
		public void clickminicartcheckout() throws Exception{
			try{
				 Thread.sleep(3000);
				 Sync.waitElementPresent("id", "top-cart-btn-checkout");
				 Common.clickElement("id", "top-cart-btn-checkout");
			      ExtenantReportUtils.addPassLog("verifying mini cart checkout button", "User click mini cart checkout button", "user successfilly click mini cart checkout button", Common.getscreenShotPathforReport("minicartcheckoutbutton")); 
			}
			catch(Exception |Error e) {
				ExtenantReportUtils.addFailedLog("verifying mini cart checkout button", "User click mini cart checkout button", "user failed to click mini cart checkout button", Common.getscreenShotPathforReport("failedminicartcheckoutbutton")); 
				Assert.fail();
		}
	}
		
		
		
		public void validating_Shippingpage(String dataSet){
			try{
			Sync.waitElementPresent("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']");
			Common.textBoxInput("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']",data.get(dataSet).get("Email"));
			}
			catch(Exception |Error e) {
				ExtenantReportUtils.addFailedLog("verifying mini cart checkout button", "User click mini cart checkout button", "user failed to click mini cart checkout button", Common.getscreenShotPathforReport("failedminicartcheckoutbutton")); 
				Assert.fail();
		}
			}
		
		
		public void guestShippingAddress(String dataSet) throws Exception{
			try{
			Sync.waitElementPresent("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']");
			Common.textBoxInput("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']",data.get(dataSet).get("Email"));

			
			Sync.waitElementPresent("xpath", "//input[@name='firstname']");
			Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(dataSet).get("FirstName"));
			
			Sync.waitElementPresent("xpath", "//input[@name='lastname']");
			Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));
			
			

			Sync.waitElementPresent("xpath", "//input[@name='street[0]']");
			Common.textBoxInput("xpath", "//input[@name='street[0]']", data.get(dataSet).get("Street"));
			
			Sync.waitElementPresent("xpath", "//input[@name='city']");
			Common.textBoxInput("xpath", "//input[@name='city']", data.get(dataSet).get("City"));
			Common.actionsKeyPress(Keys.ESCAPE);
			Sync.waitElementPresent("xpath", "//input[@name='postcode']");
			Common.textBoxInput("xpath", "//input[@name='postcode']", data.get(dataSet).get("postcode"));
			
			
			Sync.waitElementPresent("xpath", "//select[@name='region_id']");
			Common.dropdown("xpath", "//select[@name='region_id']", SelectBy.TEXT, data.get(dataSet).get("Region"));
			
			/*
			 * Sync.waitElementPresent("xpath", "//select[@name='country_id']");
			 * Common.dropdown("xpath", "//select[@name='country_id']", SelectBy.TEXT,
			 * data.get(dataSet).get("Countryname"));
			 */
			
			
			
			Sync.waitElementPresent("xpath", "//input[@name='telephone']");
			Common.textBoxInput("xpath", "//input[@name='telephone']", data.get(dataSet).get("phone"));
			
			//select_USPS_StandardGround_shippingMethod();
		    Thread.sleep(5000);
			
		    Common.clickElement("xpath","//div[@id='shipping-method-buttons-container']/div/button");
		    
			//Common.clickElement("xpath","//span[text()='Next']");
			//div[contains(@class,'error')]
			Sync.waitPageLoad();
			int sizeerrormessage=Common.findElements("xpath", "//span[contains(text(),'This is a required field')]").size();
		    Common.assertionCheckwithReport(sizeerrormessage<=0, "verifying shipping addres filling ", "user will fill the all the shipping", "user fill the shiping address click save button", "faield to add new shipping address");
		   
		}
			catch(Exception |Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying shipping addres filling", "user will fill the all the shipping", "faield to add new shipping address",Common.getscreenShotPathforReport("faieldsshippingpagefilling"));
				Assert.fail();
				
			}  
		}
		public void CouponCodeinCheckoutpage(String dataSet){
			try{
			//	Sync.waitPageLoad();
				Thread.sleep(3000);
				Common.actionsKeyPress(Keys.END);
			Sync.waitElementPresent("id", "block-discount-heading");
			Common.scrollIntoView("id", "block-discount-heading");
			Common.clickElement("id", "block-discount-heading");
			Sync.waitElementPresent("id", "discount-code");
            Common.textBoxInput("id", "discount-code", data.get(dataSet).get("Promationcode"));
			Common.clickElement("xpath", "//span[text()='Apply Discount']");
		    Sync.waitPageLoad();
		    Common.actionsKeyPress(Keys.HOME);
			int Discountcopuon=Common.findElements("xpath", "//tr[@class='totals discount']").size();
		System.out.println(Discountcopuon);
			Common.assertionCheckwithReport(Discountcopuon>0,"verifying pomocode", "It should apply discount If user enters valid promocode", "promotion code working as expected","Promation code is not applied");

			
			}
			catch(Exception |Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying shipping addres filling", "user will fill the all the shipping", "faield to add new shipping address",Common.getscreenShotPathforReport("faieldsshippingpagefilling"));
				Assert.fail();
				
			}  
		}
		
		
		
		
		public void creditCard_payment(String dataSet) throws Exception{
			
			try{
				
		
				Thread.sleep(7000);
				Common.switchFrames("xpath", "//iframe[@id='paymetric_xisecure_frame']");
				int size=Common.findElements("xpath", "//select[@id='c-ct']").size();
				Common.switchToDefault();
				Common.assertionCheckwithReport(size>0, "validating Creditcard option", "click the creadit card label", "clicking credit card label and open the card fields", "user faield to open credit card form");
				}
			   catch(Exception |Error e) {
				   e.printStackTrace();
					ExtenantReportUtils.addFailedLog("validating the Credit Card option", "click the creadit card label", "faield to click Credit Card option",  Common.getscreenShotPathforReport("Cardinoption"));
					Assert.fail();
					
				}
			
			
			try{
			
			Thread.sleep(2000);
			Common.switchFrames("id", "paymetric_xisecure_frame");
			
			System.out.println(data.get(dataSet).get("cardType"));
			Common.dropdown("xpath", "//select[@id='c-ct']", Common.SelectBy.TEXT, data.get(dataSet).get("cardType"));
			
			
			
			Common.textBoxInput("id", "c-cardnumber", data.get(dataSet).get("cardNumber"));
			Common.dropdown("xpath", "//select[@id='c-exmth']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpMonth"));
			Common.dropdown("xpath", "//select[@id='c-exyr']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpYear"));
			Common.textBoxInput("id", "c-cvv", data.get(dataSet).get("cvv"));	
			Thread.sleep(2000);
			
			Common.actionsKeyPress(Keys.ARROW_DOWN);
			Common.switchToDefault();
			Thread.sleep(1000);
			Common.clickElement("xpath", "//span[contains(text(),'Place Order')]");
			Common.switchFrames("id", "paymetric_xisecure_frame");
			String expectedResult="credit card fields are filled with the data";
		    String errorTexts=	Common. findElement("xpath", "//div[contains(@id,'error')]").getText();
		    Common.switchToDefault();
		    Common.assertionCheckwithReport(errorTexts.isEmpty(), "validating the credit card information with valid data", expectedResult, "Filled the Card detiles", "missing field data it showinng error");
		    	
			Sync.waitPageLoad();
			}
			catch(Exception |Error e) {
				e.printStackTrace();
			    ExtenantReportUtils.addFailedLog("validating the Credit Card infromation", "credit card fields are filled with the data", "faield  to fill the Credit Card infromation",  Common.getscreenShotPathforReport("Cardinfromationfail"));
				Assert.fail();
				
			}
			
			
			
		}
		public void order_Verifying() throws Exception{
			//Thread.sleep(10000);
			//Common.textBoxInput("id", "//textarea[contains(@id,'tt-c-comment-field')]","Ceate accounts test ");
			String expectedResult = "It redirects to order confirmation page";
			try{
			Sync.waitPageLoad();
			
			
			
			for(int i=0;i<10;i++){
				
				if(Common.getCurrentURL().contains("success")){
					break;
				}
				Thread.sleep(5000);
			}
			
			String sucessMessage=Common.getText("xpath", "//h1[@class='page-title']/span");
			System.out.println(sucessMessage);
			Common.assertionCheckwithReport(sucessMessage.equals("Thank you for your purchase!"),"verifying the product confirmation", expectedResult,"Successfully It redirects to order confirmation page Order Placed","User unabel to go orderconformation page");
				
			}
			catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying the product confirmation", expectedResult,
						"User failed to navigate  to order confirmation page", Common.getscreenShotPathforReport("failednavigatepage"));
				Assert.fail();
			}
		}
		public void addDeliveryAddress_registerUser(String dataSet) throws Exception {

			String expectedResult = "shipping address is entering in the fields";
		    int size = Common.findElements(By.xpath("//span[contains(text(),'New Address')]")).size();
			if (size > 0) {

				try {
					Common.clickElement("xpath", "//span[contains(text(),'New Address')]");
					Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",
							data.get(dataSet).get("FirstName"));
					Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']",
							data.get(dataSet).get("LastName"));
					Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']",
							data.get(dataSet).get("Street"));
					Thread.sleep(2000);
					Common.actionsKeyPress(Keys.SPACE);
					Thread.sleep(3000);
					
					if (data.get(dataSet).get("StreetLine2") != null) {
						Common.textBoxInput("name", "street[1]", data.get(dataSet).get("Street"));
					}
					if (data.get(dataSet).get("StreetLine3") != null) {
						Common.textBoxInput("name", "street[2]", data.get(dataSet).get("Street"));
					}
					Common.actionsKeyPress(Keys.PAGE_DOWN);
					Thread.sleep(3000);
					Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='city']",
							data.get(dataSet).get("City"));
					// Common.mouseOverClick("name", "region_id");
					try {
						Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
					} catch (ElementClickInterceptedException e) {
						// TODO: handle exception
						Thread.sleep(3000);
						Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
					}
					Thread.sleep(2000);
					Common.textBoxInputClear("xpath", "//form[@id='co-shipping-form']//input[@name='postcode']");
					Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='postcode']",
							data.get(dataSet).get("postcode"));
					Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='telephone']",
							data.get(dataSet).get("phone"));

					ExtenantReportUtils.addPassLog("validating the shipping address", expectedResult,
							"user add the shipping address",
							Common.getscreenShotPathforReport("faield to add shipping address"));


					Common.clickElement("xpath", "//button[contains(@class,'save-address')]");

					int sizeerrormessage = Common.findElements("xpath", "//span[contains(text(),'This is a required field')]").size();

					Common.assertionCheckwithReport(sizeerrormessage <= 0, "verifying shipping addres filling ",
							"user will fill the all the shipping", "user fill the shiping address click save button",
							"faield to add new shipping address");
					
					
					//select_USPS_StandardGround_shippingMethod();
		            Thread.sleep(5000);
					Common.clickElement("xpath", "//div[@id='shipping-method-buttons-container']/div/button");
					
					
					
				} catch (Exception | Error e) {
					e.printStackTrace();

					ExtenantReportUtils.addFailedLog("validating adding  address", expectedResult,
							"User unabel add shipping address",
							Common.getscreenShotPathforReport("shipping address faield"));
								Assert.fail();

				}
			}

			else

			{
				try {
					Sync.waitElementPresent("xpath", "//input[@name='firstname']");
					Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(dataSet).get("FirstName"));
					
					Sync.waitElementPresent("xpath", "//input[@name='lastname']");
					Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));
					
					

					Sync.waitElementPresent("xpath", "//input[@name='street[0]']");
					Common.textBoxInput("xpath", "//input[@name='street[0]']", data.get(dataSet).get("Street"));
					
					Sync.waitElementPresent("xpath", "//input[@name='city']");
					Common.textBoxInput("xpath", "//input[@name='city']", data.get(dataSet).get("City"));
					Common.actionsKeyPress(Keys.ESCAPE);
					Sync.waitElementPresent("xpath", "//input[@name='postcode']");
					Common.textBoxInput("xpath", "//input[@name='postcode']", data.get(dataSet).get("postcode"));
					
					
					Sync.waitElementPresent("xpath", "//select[@name='region_id']");
					Common.dropdown("xpath", "//select[@name='region_id']", SelectBy.TEXT, data.get(dataSet).get("Region"));
					
					/*
					 * Sync.waitElementPresent("xpath", "//select[@name='country_id']");
					 * Common.dropdown("xpath", "//select[@name='country_id']", SelectBy.TEXT,
					 * data.get(dataSet).get("Countryname"));
					 */
					
					
					
					Sync.waitElementPresent("xpath", "//input[@name='telephone']");
					Common.textBoxInput("xpath", "//input[@name='telephone']", data.get(dataSet).get("phone"));
					
					//select_USPS_StandardGround_shippingMethod();
				    Thread.sleep(5000);
					
				    Common.clickElement("xpath","//div[@id='shipping-method-buttons-container']/div/button");
				    
					//Common.clickElement("xpath","//span[text()='Next']");
					//div[contains(@class,'error')]
					Sync.waitPageLoad();
					int sizeerrormessage=Common.findElements("xpath", "//span[contains(text(),'This is a required field')]").size();
				    Common.assertionCheckwithReport(sizeerrormessage<=0, "verifying shipping addres filling ", "user will fill the all the shipping", "user fill the shiping address click save button", "faield to add new shipping address");
				   
				
					
				}
				 catch (Exception | Error e) {
					e.printStackTrace();

					ExtenantReportUtils.addFailedLog("validating adding  address", expectedResult,
							"User unabel add shipping address",
							Common.getscreenShotPathforReport("shipping address faield"));
					// ExtenantReportUtils.addFailedLog("User click check out
					// button", "User unabel click the checkout button",
					// Common.getscreenShotPathforReport("check out miniCart"));
					Assert.fail();

				}
			}

			

		}
		
		public void searchProduct(String dataSet) throws Exception {
			Thread.sleep(5000);
			String productname= data.get(dataSet).get("productname");
			try {
				
				Sync.waitElementVisible("xpath", "//form[@id='search_mini_form']//label");
				Thread.sleep(5000);
				Common.clickElement("xpath", "//form[@id='search_mini_form']//label");
				Common.textBoxInput("xpath", "//input[@id='search']", productname);
				ExtenantReportUtils.addPassLog("validating Search box", "enter product name will display in search box",
						"user enter the product name in  search box", Common.getscreenShotPathforReport("searchproduct"));
			} catch (Exception | Error e) {
				ExtenantReportUtils.addFailedLog("validating Search box", "enter product name will display in search box",
						"User failed to enter product name", Common.getscreenShotPathforReport("searchproduct"));
				Assert.fail();
			}
			
		
				Common.actionsKeyPress(Keys.ENTER);
				Thread.sleep(2000);
				try {
					List<WebElement> product=Common.findElements("xpath", "//img[contains(@alt,'"+productname+"')]");
					System.out.println(product.size());
					Common.scrollIntoView(product.get(1));
					Thread.sleep(3000);
					String Attributr=product.get(1).getAttribute("src");
					Common.javascriptclickElement("xpath", "//img[@src='"+Attributr+"']");
				//	product.get(1).click();
					System.out.println(product.size());
					Thread.sleep(3000);
					clickAddtoBag();
				}
				catch(Exception | Error e) {
					e.printStackTrace();
					ExtenantReportUtils.addFailedLog("verifying search product in pdp page", "search the selected product in plp page it navigate to pdp page","failed to navigate to pdp page", Common.getscreenShotPathforReport("searchproduct"));
					Assert.fail();
				}
				
				
				
		}
		public void click_View_editcart(){
			try{
			Sync.waitElementPresent("xpath", "//a[@class='action viewcart']");
			Common.clickElement("xpath", "//a[@class='action viewcart']");
			ExtenantReportUtils.addPassLog("verifying the view edit cart button from mincart page","user after click the  view and edit button it navigate to SHOPPING CART page","User navigate to SHOPPING CART page",Common.getscreenShotPathforReport("SHOPPINGCARTPAGE"));
			}
			catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying the view edit cart button from mincart page","user after click the  view and edit button it navigate to SHOPPING CART page","User unabel navigate to SHOPPING CART page",Common.getscreenShotPathforReport("SHOPPINGCARTPAGE"));
		        Assert.fail();
		        }
			}
		
		
		public void changeQuntity_UpdateProduct(String productcount){
			try{
			Sync.waitElementPresent("xpath", "//input[@title='Qty']");
			
			String Value=Common.findElement("xpath", "//input[@title='Qty']").getAttribute("value");
			
			Common.clickElement("xpath", "//input[@title='Qty']");
			Common.actionsKeyPress(Keys.BACK_SPACE);
			
			Common.textBoxInput("xpath", "//input[@title='Qty']",productcount);
			Common.clickElement("xpath", "//button[@name='update_cart_action']");
			Sync.waitPageLoad();
			String value=Common.findElement("xpath", "//input[@title='Qty']").getAttribute("value");
			
			
			Common.assertionCheckwithReport(value.equals(productcount), "verifying the product Quntity","user change the quntity of product","user successfully  change quntity","Failed to chage the quntity");
			}
			catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying the product Quntity","user change the quntity of product","User faield to chage the quntity",Common.getscreenShotPathforReport("failed changequntity"));
		        Assert.fail();
		        }
		} 
		
		public void clickCheckoutButton_minicart(){
			try{
				Thread.sleep(3000);
			 Sync.waitElementPresent("xpath", "//*[@id='maincontent']//li/button");
			   Common.findElement("xpath", "//*[@id='maincontent']//li/button").click();
			   
			  //Common.assertionCheckwithReport(checkoutbuttonSize>0, "verifying mini cart button", "User click mini cart button", "user successfully click mini cart button", "Failed click mini cart button");
			}
			    catch(Exception |Error e) {
			 	   
					ExtenantReportUtils.addFailedLog("verifying checkOut button in minicart", "User click checkOut  button in mini cart", "user faield to click checkOut button", Common.getscreenShotPathforReport("checkOut"));
					Assert.fail();
				}
		}
		
		
		public void update_product_miniCartBag(String productQTY) throws Exception {
			try{
			    Common.clickElement("xpath", "//input[contains(@class,'cart-item-qty')]");
			    Common.actionsKeyPress(Keys.BACK_SPACE);
				Common.textBoxInput("xpath", "//input[contains(@class,'cart-item-qty')]",productQTY);
				
				Common.clickElement("xpath", "//span[text()='Update']");
				
			int alermessage=Common.findElements("xpath", "//button[@data-role='action']").size();
			if(alermessage>0) {
				Common.clickElement("xpath", "//button[@data-role='action']");
				
			}
			else {
				clickminicartButton();
				String productvalue=Common.findElement("xpath", "//input[contains(@class,'cart-item-qty')]").getAttribute("data-item-qty");
	   Common.assertionCheckwithReport(productvalue.equals(productQTY),"verifying product update in minicart", "update product in mini cart bag page", "successfullyupdate product in minicart bag page ", "faield to update product in cart page");
			}
			}
			catch(Exception |Error e) {
			 	   
				ExtenantReportUtils.addFailedLog("verifying product update in minicart", "update product in mini cart bag page", "user faield to updateproducttocartpage", Common.getscreenShotPathforReport("cartpageupdate"));
				Assert.fail();
			}
			
		}
		public void removeproductinBagPage() {
			
			int size=Common.findElements("xpath", "//span[text()='Remove']").size();
			for(int i=0;i<=size;i++) {
				Common.clickElement("xpath", "//span[text()='Remove']");
			    Common.clickElement("xpath", "//button[@data-role='action']");
			}
			int errormessage=Common.findElements("xpath", "//strong[@class='subtitle empty']").size();
			Common.assertionCheckwithReport(errormessage>0, "validating removeproducts mini cart page", "remove all the products from mini cart","successfully remove the products from mini cart page","faield to remove all the products from cart");
		}
		
		
		/*
		 * public void price_range_plp(int minprice,int maxpric) { try {
		 * Common.clickElement("xpath", "//div[@id='narrow-by-list']/div[2]");
		 * if(minprice>=0 && maxpric<=100 ) { Common.findElement("xpath",
		 * "//a[@data-opt-path='price=0-100']").click();
		 * 
		 * //String Price=Common.findElement("xpath",
		 * "//span[@class='price-wrapper ']").getAttribute("data-price-amount");
		 * List<WebElement> priceranges=Common.findElements("xpath",
		 * "//span[@class='price-wrapper ']"); for(int i=0;i<priceranges.size();i++) {
		 * priceranges. } }
		 * 
		 * else if(minprice>=0 && maxpric<=200 ) { Common.findElement("xpath",
		 * "//a[@data-opt-path='price=100-200']").click();
		 * 
		 * } else if(minprice>=0 && maxpric<=300 ) { Common.findElement("xpath",
		 * "//a[@data-opt-path='price=200-300']").click(); } else if(minprice>=0 ||
		 * maxpric<=1000 ) { Common.findElement("xpath",
		 * "//a[@data-opt-path='price=600-700']").click();
		 * 
		 * 
		 * } ExtenantReportUtils.addPassLog("validting price rage button in plp page",
		 * "price Range button click with in range"+
		 * String.valueOf(minprice)+","+String.valueOf(maxpric),
		 * "successfully click the price range",
		 * Common.getscreenShotPathforReport("pricerage")); } catch(Exception |Error e)
		 * {
		 * 
		 * ExtenantReportUtils.addFailedLog("validting price rage button in plp page",
		 * "price Range button click with in range"+
		 * String.valueOf(minprice)+","+String.valueOf(maxpric),
		 * "faield to click the price range",
		 * Common.getscreenShotPathforReport("pricerage")); Assert.fail(); }
		 * 
		 * try { List<WebElement> priceranges=Common.findElements("xpath",
		 * "//span[@class='price-wrapper ']");
		 * 
		 * 
		 * }
		 */
			
		/*
		 * }
		 * 
		 * public void PriceRangefilter(int PriceRangeBelowGiveValue) {
		 * //span[@class='price-wrapper ']
		 * 
		 * 
		 * Common.clickElement("xpath", "//div[@id='narrow-by-list']/div[2]");
		 * 
		 * if(PriceRangeBelowGiveValue>100) { Common.findElement("xpath",
		 * "//a[@data-opt-path='price=0-100']"); } else if(PriceRangeBelowGiveValue>200)
		 * { Common.findElement("xpath", "//a[@data-opt-path='price=100-200']");
		 * 
		 * } else if(PriceRangeBelowGiveValue>3000)
		 * 
		 * }
		 */
		
		
		public void customerloginvalidation() {
			try {
				
				
				// click the sign button  xptah need to implement
				
			int emailerrormessage=Common.findElements("xpath", "//div[@id='email-error']").size();
			int passworderromessage=Common.findElements("xpath", "//div[@id='pass-error']").size();
			
			Common.assertionCheckwithReport(emailerrormessage>0&&passworderromessage>0, "verifying error message signpage", "enter with empety data it must show error message","sucessfully display the error message", "faield to dispalyerrormessage");
			}
			catch(Exception |Error e) {
			 	   
				ExtenantReportUtils.addFailedLog("verifying error message signpage", "enter with empty data it must show error message", "faield to dispalyerrormessage", Common.getscreenShotPathforReport("loginpagevalidation"));
				Assert.fail();
			}
			
		}
		
		public void stayIntouch(String dataSet) throws Exception {

			String expectedResult = "User should land on the home page";
			try {
				verifyingHomePage();

				Common.actionsKeyPress(Keys.END);
				Thread.sleep(5000);

				Sync.waitElementPresent("id", "newsletter");
				Common.clickElement("id", "newsletter");

				String email = Common.genrateRandomEmail(data.get(dataSet).get("Email"));

				Common.textBoxInput("id", "newsletter", email);
				Thread.sleep(3000);
				Common.clickElement("xpath", "//button[contains(@class,'action subscribe primary')]");
				Common.actionsKeyPress(Keys.PAGE_UP);
				Thread.sleep(5000);
				String Text = Common.getText("xpath", "//div[contains(@data-ui-id,'message')]/div");
				expectedResult = "User gets confirmation message that it was submitted";
				ExtenantReportUtils.addPassLog("verifying newsletter subscription",
						"User get confirmation message if new email if it used mail it showing error message ", Text,
						Common.getscreenShotPathforReport("NewsLetter Subscrptionsuccess"));

			} catch (Exception | Error e) {
				
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying newsletter subscription", "NewsLetter Subscrption success",
						"User faield to subscrption for newLetter  ",
						Common.getscreenShotPathforReport("NewsLetter Subscrptionsuccess"));
				Assert.fail();
	     	}
					}
	public void creditCard_payment_Invalid(String dataSet) throws Exception{
			
			try{
				
		
				Thread.sleep(7000);
				Common.switchFrames("xpath", "//iframe[@id='paymetric_xisecure_frame']");
				int size=Common.findElements("xpath", "//select[@id='c-ct']").size();
				Common.switchToDefault();
				Common.assertionCheckwithReport(size>0, "validating Creditcard option", "click the creadit card label", "clicking credit card label and open the card fields", "user faield to open credit card form");
				}
			   catch(Exception |Error e) {
				   e.printStackTrace();
					ExtenantReportUtils.addFailedLog("validating the Credit Card option", "click the creadit card label", "faield to click Credit Card option",  Common.getscreenShotPathforReport("Cardinoption"));
					Assert.fail();
					
				}
			
			
			try{
			
			Thread.sleep(2000);
			Common.switchFrames("id", "paymetric_xisecure_frame");
			Common.dropdown("xpath", "//select[@id='c-ct']", Common.SelectBy.TEXT, data.get(dataSet).get("cardType"));
			Common.textBoxInput("id", "c-cardnumber", data.get(dataSet).get("cardNumber"));
			Common.dropdown("xpath", "//select[@id='c-exmth']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpMonth"));
			Common.dropdown("xpath", "//select[@id='c-exyr']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpYear"));
			Common.textBoxInput("id", "c-cvv", data.get(dataSet).get("cvv"));	
			Thread.sleep(2000);
			
			Common.actionsKeyPress(Keys.ARROW_DOWN);
			Common.switchToDefault();
			Thread.sleep(1000);
			Common.clickElement("xpath", "//span[contains(text(),'Place Order')]");
			Common.switchFrames("id", "paymetric_xisecure_frame");
			String expectedResult="credit card fields are filled with the data";
		    String errorTexts=	Common. findElement("xpath", "//div[contains(@id,'error')]").getText();
		    Common.switchToDefault();
		    Common.assertionCheckwithReport(!errorTexts.isEmpty(), "validating the credit card information with invalid  data", expectedResult, "Filled the Card detiles with in valid data", "missing the showinng error message");
		    	
			Sync.waitPageLoad();
			}
			catch(Exception |Error e) {
				e.printStackTrace();
			    ExtenantReportUtils.addFailedLog("validating the Credit Card infromation", "credit card fields are filled with the data", "faield  to fill the Credit Card infromation",  Common.getscreenShotPathforReport("Cardinfromationfail"));
				Assert.fail();
				
			}
			
			
			
		}
	
	public void ShippingFormValidation() throws Exception{
		
		Common.clickElement("xpath", "//button[@class='button action continue primary']");
		Thread.sleep(4000);
		try {
		
		Sync.waitElementClickable("xpath", "//button[@class='button action continue primary']");	
		Common.findElement("xpath", "//button[@class='button action continue primary']").click();
		
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		//Common.actionsKeyPress(Keys.DOWN);
		int emailerrormessage=Common.findElements("xpath", "//div[@id='customer-email-error']").size();
		int Streeterromessage=Common.findElements("xpath", "//div[@class='field-error']").size();
		
		Common.assertionCheckwithReport(emailerrormessage>0&&Streeterromessage>0, "verifying error message ShippingAddressForm Page", "enter with empty data it must show error message","sucessfully display the error message", "faield to dispalyerrormessage");
		}
		catch(Exception |Error e) {
		 	e.printStackTrace();   
			ExtenantReportUtils.addFailedLog("verifying error message ShippingAddressForm Page", "enter with empty data it must show error message", "faield to dispalyerrormessage", Common.getscreenShotPathforReport("ShippingAddressFormvalidation"));
			Assert.fail();
		}
		
	}
	public void ForgotPasswordValidation() {
		try {
			
			Thread.sleep(4000);	
			Sync.waitElementClickable("xpath","//a[@class='header-content__right-link']");
			Common.findElement("xpath", "//a[@class='header-content__right-link']").click();
			Thread.sleep(2000);
			//Common.scrollIntoView("xpath", "//a[@class='action remind']");
			Sync.waitElementClickable("xpath", "//a[@class='action remind']");
			Common.findElement("xpath","//a[@class='action remind']").click();
			Thread.sleep(4000);
			Sync.waitElementClickable("xpath", "//button[@class='action submit primary']");
			Common.clickElement("xpath", "//button[@class='action submit primary']");
			Thread.sleep(3000);
			int emailerrormessage=Common.findElements("xpath", "//div[@id='email_address-error']").size();
			Common.assertionCheckwithReport(emailerrormessage>0, "verifying error message ForgotPasswordPage", "enter with empty data it must show error message","sucessfully display the error message", "faield to dispalyerrormessage");
			}
			catch(Exception |Error e) {
			 	   
				ExtenantReportUtils.addFailedLog("verifying error message ForgotPasswordPage", "enter with empty data it must show error message", "faield to dispalyerrormessage", Common.getscreenShotPathforReport("loginpagevalidation"));
				Assert.fail();
			}
		}
	

	
	public void headLinksValidations_Shop(String dataSet) throws Exception{
		Thread.sleep(3000);
		Sync.waitPageLoad();
		Common.mouseOver("xpath", "//span[text()='Shop']");
		
		String Hederlinks=data.get(dataSet).get("HeaderNames");
		String[] hedrs=Hederlinks.split(",");
		int i=0;
		
		try{
		for(i=0;i<hedrs.length;i++){
			System.out.println(hedrs[i]);
			Sync.waitElementClickable("xpath", "//a[@title='"+hedrs[i]+"']");
			Common.clickElement("xpath", "//a[@title='"+hedrs[i]+"']");
			Thread.sleep(3000);
			System.out.println(Common.getPageTitle());
			Common.assertionCheckwithReport(Common.getPageTitle().contains(hedrs[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
			Common.mouseOver("xpath", "//span[text()='Shop']");	
		}
		}
		catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validating Header Links " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the header link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the headerlink"));
		
			Assert.fail();

		}
	}

	public void warranty(String dataSet) throws Exception{
		try {
			Common.clickElement("xpath","//a[text()='Warranty Registration']");
			Thread.sleep(4000);
			Common.switchFrames("xpath","//iframe[contains(@src,'product_registration')]");
			Sync.waitElementPresent("xpath", "//input[contains(@name,'Contact.Name.First')]");
			Common.textBoxInput("xpath", "//input[contains(@name,'Contact.Name.First')]",
					data.get(dataSet).get("FirstName"));
		} catch (Exception e) {
			try {
				Common.textBoxInput("xpath", "//input[contains(@name,'Contact.Name.First')]",data.get(dataSet).get("FirstName"));
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
		}
		try {
			Sync.waitElementPresent("xpath", "//input[@name='Contact.Name.Last']");
			Common.textBoxInput("xpath", "//input[@name='Contact.Name.Last']", data.get(dataSet).get("LastName"));

			Sync.waitElementPresent("xpath", "//input[contains(@id,'Emails')]");
			Common.textBoxInput("xpath", "//input[contains(@id,'Emails')]", data.get(dataSet).get("Email"));

			Sync.waitElementPresent("xpath", "//input[contains(@id,'Street')]");
			Common.textBoxInput("xpath", "//input[contains(@id,'Street')]", data.get(dataSet).get("Street"));

			Sync.waitElementPresent("xpath", "//input[contains(@id,'City')]");
			Common.textBoxInput("xpath", "//input[contains(@id,'City')]", data.get(dataSet).get("City"));

	/*		Common.clickElement(By.xpath("//select[contains(@id,'Country')]"));

			Sync.waitElementPresent("xpath", "//select[contains(@id,'Country')]");
			Common.dropdown("xpath", "//select[contains(@id,'Country')]", SelectBy.TEXT,
					data.get(dataSet).get("Country"));
*/
			Sync.waitElementPresent("xpath", "//select[contains(@id,'StateOrProvince')]");
			Common.clickElement(By.xpath("//select[contains(@id,'StateOrProvince')]"));
			Thread.sleep(5000);
			Common.dropdown("xpath", "//select[contains(@id,'StateOrProvince')]", SelectBy.TEXT,
					data.get(dataSet).get("State"));

			Sync.waitElementPresent("xpath", "//input[contains(@id,'PostalCode')]");
			Common.textBoxInput("xpath", "//input[contains(@id,'PostalCode')]", data.get(dataSet).get("postcode"));

			Sync.waitElementPresent("xpath", "//input[contains(@id,'MOBILE')]");
			Common.textBoxInput("xpath", "//input[contains(@id,'MOBILE')]", data.get(dataSet).get("phone"));

			Thread.sleep(3000);
			// Common.actionsKeyPress(Keys.PAGE_DOWN);

			Sync.waitElementPresent("xpath", "//i[contains(@class,'fa-arrow-down')]");
			// Common.clickElement(By.xpath("//span[text()='View All']"));
			// Common.clickElement(By.xpath("//i[@class='all_button_arrow fa
			// fa-arrow-down']"));
			Common.javascriptclickElement("xpath", "//i[contains(@class,'fa-arrow-down')]");
			// Common.mouseOverClick("xpath", "//i[@class='all_button_arrow fa
			// fa-arrow-down']");
			// Common.clickElement("xpath","//i[@class='all_button_arrow fa
			// fa-arrow-down']");
			// Common.mouseOverClick("xpath","//span[text()='View All']");
			Thread.sleep(5000);
			List<WebElement> Productselemts = Common.findElements("xpath", "//div[contains(@class,'resultset')]");

			for (int i = 0; i < Productselemts.size(); i++) {

				if (Productselemts.get(i).getAttribute("title").equals(data.get(dataSet).get("productname"))) {
					Productselemts.get(i).click();
					break;
				}

			}
			Common.dropdown("xpath", "//select[@name='Asset.CustomFields.HOT.store_purchased']", SelectBy.TEXT,data.get(dataSet).get("placeofpurchese"));
			// input[contains(@class,'product_quantity')]

			Sync.waitElementPresent("xpath", "//input[contains(@name,'date_code')]");
			Common.textBoxInput("xpath", "//input[contains(@name,'date_code')]",
					data.get(dataSet).get("ProductQuantity"));

			Sync.waitElementPresent("xpath", "//input[contains(@class,'hasDatepicker')]");
			Common.textBoxInput("xpath", "//input[contains(@class,'hasDatepicker')]",
					data.get(dataSet).get("ProblemDescription"));

			
			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "//button[contains(@id,'CustomFormSubmit')]");
			Common.javascriptclickElement("xpath", "//button[contains(@id,'CustomFormSubmit')]");
			

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying warranty form field data",
					"warrenty from infromation with out any validation", "warrenty from with invalid data",
					Common.getscreenShotPathforReport("warrenty from invalid data"));
			e.printStackTrace();
			Assert.fail();

		}
		
	//	Common.actionsKeyPress(Keys.HOME);

		int sizeerrormessage = Common.findElements("xpath", "//div[contains(@id,'ErrorLocation')]").size();
		Common.assertionCheckwithReport(sizeerrormessage > 0, "verifying warranty form field data",
				"Enter the warrenty from infromation with out any validation", "mandatory data missing in the from",
				Common.getscreenShotPathforReport("mandatory data missing in the from"));
		// (sizeerrormessage>0, "Enter the warrenty from infromation with out
		// any validation ", expectedResult, "mandatory data missing in the
		// from");
		Common.actionsKeyPress(Keys.HOME);
		Thread.sleep(6000);
System.out.println("pr");
		String sucessMessage = Common.getText("xpath", "//div[@id='rn_ProdRegConfirmDiv']/div/h1");
		
		System.out.println(sucessMessage);
		// Assert.assertEquals(sucessMessage, "Your warranty request has been
		// submitted!");
		String expectedResult = "User gets redirected to confirmation page, it includes a reference number and email is sent to email provided. No validation errors.";
		Common.assertionCheckwithReport(sucessMessage.equals("Thank you for registering your product! Your request has been processed."),
				"warranty applied  successfull,and redirected to confirmation page", expectedResult,
				"submit the warranty but confirmation page  message missing");
		// report.addPassLog(expectedResult,"warranty applied successfull,and
		// redirected to confirmation
		// page",Common.getscreenShotPathforReport("warranty submitted "));

	}

	
	public void productsupport(){
		try{
		Common.actionsKeyPress(Keys.PAGE_DOWN);
	    Common.clickElement("xpath", "//span[@class='mobile-accordion-link-text' and text()='Product Support']");
		Sync.waitPageLoad();
		Common.assertionCheckwithReport(Common.getPageTitle().equals("Support & FAQs Page - Honeywell"), "verifying the product support page", "after click the productsupport page it will navigate to product support page ", "sucessfully navigate to product support page", "faield to open product support page");
		}
		catch (Exception | Error e) {
			 e.printStackTrace();
             ExtenantReportUtils.addFailedLog("validating product support page","open the product supportpage","User failed to open productsupportpage",Common.getscreenShotPathforReport("supportpage"));
             Assert.fail();

		}
	
	}
	
	
	public void fottorValidations_Shop(String dataSet) throws Exception{
		Thread.sleep(3000);
		Sync.waitPageLoad();
		
		Common.actionsKeyPress(Keys.END);
		String Hederlinks=data.get(dataSet).get("HeaderNames");
		String[] hedrs=Hederlinks.split(",");
		int i=0;
		
		try{
		for(i=0;i<hedrs.length;i++){
			System.out.println(hedrs[i]);
			Sync.waitElementClickable("xpath", "//span[@class='mobile-accordion-link-text' and text()='"+hedrs[i]+"']");
			Common.clickElement("xpath", "//span[@class='mobile-accordion-link-text' and text()='"+hedrs[i]+"']");
			Thread.sleep(3000);
			System.out.println(Common.getPageTitle());
			Common.assertionCheckwithReport(Common.getPageTitle().contains(hedrs[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
			Common.actionsKeyPress(Keys.END);	
		}
		}
		catch (Exception | Error e) {
			e.printStackTrace();
            ExtenantReportUtils.addFailedLog("validating Header Links " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the header link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the headerlink"));
            Assert.fail();

		}
	}
	

  public void headerlinkLearnEducation(String dataSet) {
	  try{
	  
	 Common.mouseOver("xpath", "//span[text()='Learn']");
	 String Blogpagetitle= data.get(dataSet).get("Blog");
	 String CleanAirMatterspagetitle=data.get(dataSet).get("CleanAirMatters");
     String DreamWeaverSleepFanpagetitle= data.get(dataSet).get("DreamWeaverSleepFan");
	 String SafetyMatterspagetitle=data.get(dataSet).get("SafetyMatters");
	 String WhyHumidifypagetitle=data.get(dataSet).get("WhyHumidify");
	 ArrayList<String> elemtstext=new ArrayList<String>();
     List<WebElement> LearnEductionLinks=Common.findElements("xpath", "//ul[@data-menu='menu-67']//li//span");
     for(int j=0;j<LearnEductionLinks.size();j++){
    	  elemtstext.add(LearnEductionLinks.get(j).getText());
     }

  
    int i=0;
     for(i=0;i<elemtstext.size();i++){
    	 Thread.sleep(4000);
    	 System.out.println(elemtstext.get(i));
    	 
    	if(elemtstext.get(i).equals("Blog")){
    		Common.clickElement("xpath", "//span[text()='"+elemtstext.get(i)+"']");
            Thread.sleep(2000);
    	    Common.assertionCheckwithReport(Common.getPageTitle().equals(Blogpagetitle), "validating header ling blog page in linkLearnEducation", "after click the blog page in header it must navigate to blog page", "sucessfully navigate to blog page", "Failed to navigate to blogpage");
    	    Common.mouseOver("xpath", "//span[text()='Learn']");
    	}
    	else if(elemtstext.get(i).equals("Clean Air Matters")){
    		Common.clickElement("xpath", "//span[text()='"+elemtstext.get(i)+"']");
    	    Thread.sleep(2000);
    	    Common.assertionCheckwithReport(Common.getPageTitle().equals(CleanAirMatterspagetitle), "validating header ling Clean Air Matters page in linkLearnEducation", "after click the Clean Air Matters page in header it must navigate to Clean Air Matters page", "sucessfully navigate to Clean Air Matters page", "Failed to navigate to Clean Air Matters");
    	    Common.mouseOver("xpath", "//span[text()='Learn']");
    	}
    	
    	else if(elemtstext.get(i).equals("DreamWeaver Sleep Fan")){
    		Common.clickElement("xpath", "//span[text()='"+elemtstext.get(i)+"']");
    	    Thread.sleep(2000);
    	    Common.assertionCheckwithReport(Common.getPageTitle().equals(DreamWeaverSleepFanpagetitle), "validating header ling DreamWeaverSleepFan page in linkLearnEducation", "after click the DreamWeaverSleepFan page in header it must navigate to DreamWeaverSleepFan page", "sucessfully navigate to DreamWeaverSleepFan page", "Failed to navigate to DreamWeaverSleepFan");
    	    Common.mouseOver("xpath", "//span[text()='Learn']");
    	}
    	else if(elemtstext.get(i).equals("Safety Matters")){
    		Common.clickElement("xpath", "//span[text()='"+elemtstext.get(i)+"']");
    	    Thread.sleep(2000);
    	    Common.assertionCheckwithReport(Common.getPageTitle().equals(SafetyMatterspagetitle), "validating header ling SafetyMatters page in linkLearnEducation", "after click the SafetyMatters page in header it must navigate to SafetyMatters page", "sucessfully navigate to SafetyMatters page", "Failed to navigate to SafetyMatters");
    	    Common.mouseOver("xpath", "//span[text()='Learn']");
    	}
    	else if(elemtstext.get(i).equals("Why Humidify")){
    		Common.clickElement("xpath", "//span[text()='"+elemtstext.get(i)+"']");
    	    Thread.sleep(2000);
    	    Common.assertionCheckwithReport(Common.getPageTitle().equals(WhyHumidifypagetitle), "validating header link WhyHumidify page linkLearnEducation", "after click the WhyHumidify page in header it must navigate to WhyHumidify page", "sucessfully navigate WhyHumidify page", "Failed to navigate to WhyHumidify");
    	    Common.mouseOver("xpath", "//span[text()='Learn']");
    	}
        }
	    }
	  catch(Exception  | Error e){
		  e.printStackTrace();
		  ExtenantReportUtils.addFailedLog("validating header link learn in linkLearnEducation","user open the Lean option from header ","User unabel open the header link Learn",Common.getscreenShotPathforReport("Learnheaderlink"));
		  Assert.fail();
	  }
	  }
	  

public void headLinksValidations_LeanBy_Products(String dataSet) throws Exception{
	int i=0;
	String Hederlinks=data.get(dataSet).get("HeaderNames");
	String[] hedrs=Hederlinks.split(",");
	System.out.println(hedrs.length);
	try{
	Thread.sleep(3000);
	Sync.waitPageLoad();
	Common.mouseOver("xpath", "//span[text()='Learn']");
	for(i=0;i<hedrs.length;i++){
		System.out.println(hedrs[i]);
		//ul[@data-menu='menu-70']/li[1]/a
		int j=i+1;
		Sync.waitElementClickable("xpath", "//ul[@data-menu='menu-70']/li["+j+"]/a");
		Common.clickElement("xpath", "//ul[@data-menu='menu-70']/li["+j+"]/a");
		Thread.sleep(3000);
		System.out.println(Common.getPageTitle());
		Common.assertionCheckwithReport(Common.getPageTitle().contains(hedrs[i]), "verifying Header link of "+hedrs[i]+"for LeanBy_Shop","user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
		Common.mouseOver("xpath", "//span[text()='Learn']");	
	}
	}
	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating Header Links " +hedrs[i],"user open the "+hedrs[i]+" LeanBy_Shop","User unabel open the header link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the headerlink"));
		Assert.fail();

	}
}

public void headerlink_Support_GeneralSupport(String dataSet) {
	  try{
	  
	 Common.mouseOver("xpath", "//a[@data-menu='menu-79']");
	 String FAQstitle= data.get(dataSet).get("FAQs");
	 String ProductSupportpagetitle=data.get(dataSet).get("ProductSupport");
     String WarrantyRegistrationpagetitle= data.get(dataSet).get("WarrantyRegistration");
	 String OrderStatuspagetitle=data.get(dataSet).get("OrderStatus");
	 
	 ArrayList<String> elemtstext=new ArrayList<String>();
	 List<WebElement> LearnEductionLinks=Common.findElements("xpath", "//ul[@data-menu='menu-79']//li//span");
	 for(int j=0;j<LearnEductionLinks.size();j++){
  	  elemtstext.add(LearnEductionLinks.get(j).getText());
   }


  int i=0;
   for(i=0;i<elemtstext.size();i++){
  	 Thread.sleep(4000);
  	 System.out.println(elemtstext.get(i));
  	 
  	if(elemtstext.get(i).equals("FAQs")){
  		Common.clickElement("xpath", "//span[text()='"+elemtstext.get(i)+"']");
          Thread.sleep(2000);
  	    Common.assertionCheckwithReport(Common.getPageTitle().equals(FAQstitle), "validating header ling"+elemtstext.get(i)+"page in GeneralSupport", "after click the "+elemtstext.get(i)+"page in header it must navigate to "+elemtstext.get(i)+" page", "sucessfully navigate to "+elemtstext.get(i)+" page", "Failed to navigate to "+elemtstext.get(i)+"");
  	  Common.mouseOver("xpath", "//a[@data-menu='menu-79']");
  	}
  	else if(elemtstext.get(i).equals("Product Support")){
  		Common.clickElement("xpath", "//span[text()='"+elemtstext.get(i)+"']");
  	    Thread.sleep(2000);
  	    Common.assertionCheckwithReport(Common.getPageTitle().equals(ProductSupportpagetitle), "validating header ling"+elemtstext.get(i)+"page in GeneralSupport", "after click the "+elemtstext.get(i)+"page in header it must navigate to "+elemtstext.get(i)+" page", "sucessfully navigate to "+elemtstext.get(i)+" page", "Failed to navigate to "+elemtstext.get(i)+"");
  	  Common.mouseOver("xpath", "//a[@data-menu='menu-79']");
  	}
  	
  	else if(elemtstext.get(i).equals("Warranty Registration")){
  		Common.clickElement("xpath", "//span[text()='"+elemtstext.get(i)+"']");
  	    Thread.sleep(2000);
  	    Common.assertionCheckwithReport(Common.getPageTitle().equals(WarrantyRegistrationpagetitle), "validating header ling"+elemtstext.get(i)+"page in GeneralSupport", "after click the "+elemtstext.get(i)+"page in header it must navigate to "+elemtstext.get(i)+" page", "sucessfully navigate to "+elemtstext.get(i)+" page", "Failed to navigate to "+elemtstext.get(i)+"");
  	  Common.mouseOver("xpath", "//a[@data-menu='menu-79']");

  	}
  	else if(elemtstext.get(i).equals("Order Status")){
  		Common.clickElement("xpath", "//span[text()='"+elemtstext.get(i)+"']");
  	    Thread.sleep(2000);
  	    Common.assertionCheckwithReport(Common.getPageTitle().equals(OrderStatuspagetitle), "validating header ling"+elemtstext.get(i)+"page in GeneralSupport", "after click the "+elemtstext.get(i)+"page in header it must navigate to "+elemtstext.get(i)+" page", "sucessfully navigate to "+elemtstext.get(i)+" page", "Failed to navigate to "+elemtstext.get(i)+"");
  	  Common.mouseOver("xpath", "//a[@data-menu='menu-79']");
  	}
  	
      }
	    }
	  catch(Exception  | Error e){
		  e.printStackTrace();
		  ExtenantReportUtils.addFailedLog("validating header link learn in support","user open the support option from header ","User unabel open the header link Support",Common.getscreenShotPathforReport("supportLink"));
		  Assert.fail();
	  }
	  }


public void headLinksValidations_SupportbyProduct(String dataSet) {
	int i=0;
	String Hederlinks=data.get(dataSet).get("HeaderNames");
	String[] hedrs=Hederlinks.split(",");
	try{
	Thread.sleep(3000);
	Sync.waitPageLoad();
	Common.mouseOver("xpath", "//a[@data-menu='menu-79']");
	for(i=0;i<hedrs.length;i++){
		System.out.println(hedrs[i]);
		//ul[@data-menu='menu-70']/li["+j+"]/a
		int j=i+1;
		Sync.waitElementClickable("xpath", "//ul[@data-menu='menu-86']/li["+j+"]/a");
		Common.clickElement("xpath", "//ul[@data-menu='menu-86']/li["+j+"]/a");
		Thread.sleep(3000);
		System.out.println(Common.getPageTitle());
		if(hedrs[i].equals("Humidifiers")){
			System.out.println(Common.getPageTitle());
			Common.assertionCheckwithReport(Common.getPageTitle().contains(""), "verifying Header link of "+hedrs[i]+"for SupportbyProduct","user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);	
		 break;
		}
		
		Common.assertionCheckwithReport(Common.getPageTitle().contains(hedrs[i]), "verifying Header link of "+hedrs[i]+"for SupportbyProduct","user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
		Common.mouseOver("xpath", "//a[@data-menu='menu-79']");
	}
	}
	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating Header Links " +hedrs[i],"user open the "+hedrs[i]+" for SupportbyProduct","User unabel open the header link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the headerlink"));
		Assert.fail();

	}
}



	

 /*public void OrderStatus(){
	 try{
	 Common.clickElement("xpath", "//a[text()='Order Status']");
	 
	 
	 Common.textBoxInput("id", "oar-order-id","");
	 Common.textBoxInput("id", "oar_billing_lastname","");
	 Common.dropdown("id", "quick-search-type-id", SelectBy.VALUE, "email");
	 Common.textBoxInput("id", "oar_email","");
	 Common.textBoxInput("id", "oar-order-id","");
 }
*/
public void agree_proceed(){
	int size=Common.findElements("id", "truste-consent-required").size();
	if(size>0){
		Common.findElement("id", "truste-consent-required").click();
	}
}
}





	