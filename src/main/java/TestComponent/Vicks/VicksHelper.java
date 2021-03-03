package TestComponent.Vicks;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import TestLib.Common;
import TestLib.Sync;
import TestLib.Common.SelectBy;
import Utilities.ExcelReader;
import Utilities.ExtenantReportUtils;

public class VicksHelper {
		String datafile;
		ExcelReader excelData;
		Map<String, Map<String, String>> data=new HashMap<>();
		static ExtenantReportUtils report;
		public VicksHelper (String datafile)
		{
			excelData=new ExcelReader(datafile);
			data=ExcelReader.getExcelValue();
			this.data=data;
			if(Utilities.TestListener.report==null)
			{
				report=new ExtenantReportUtils("Vickshumdifier");
				ExtenantReportUtils.createTestcase("VickshumdifierTestCases");
			}else{
				VicksHelper.report=Utilities.TestListener.report;
			}
		}
		
		
		
		
		public void loginVicks(String dataSet) throws Exception
		{
			String expectedResult="Land on login page and able to login with credentials";
			try {
		
					Common.clickElement("xpath", "//a[@class='header-content__right-link']");
					Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
					Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
					Common.scrollIntoView("xpath", "//button[@class='action login primary']");
					Sync.waitElementClickable("xpath", "//button[@class='action login primary']");
					Common.clickElement("xpath", "//button[@class='action login primary']");
					Thread.sleep(5000);
					Sync.waitElementClickable("xpath", "//a[@class='logo']");
					Common.clickElement("xpath", "//a[@class='logo']");
					ExtenantReportUtils.addPassLog("Should login with details", "Should display My Account Page with user details", "My Account Pae with user details displayed successfully", Common.getscreenShotPathforReport("Login page with details Failed"));
			}
			catch(Exception |Error e)
			{
				ExtenantReportUtils.addFailedLog("Should login with details","Should display  My Account Page with user details", "My Account Page with user details not displayed", Common.getscreenShotPathforReport("Login page with details Failed"));
				Assert.fail();
			}
		
			}
			
		public void CreateAccount(String dataSet){
			
			try{
				Thread.sleep(5000);
			    Common.clickElement("xpath", "//a[@class='header-content__right-link']");
			    Sync.waitElementClickable("xpath", "//a[@class='action create primary']");
			    Common.clickElement("xpath", "//a[@class='action create primary']");
			    ExtenantReportUtils.addPassLog("verifying Create Account button", "It should lands on Create New Customer from Account form Page", "user  lands on Customer Account creation form Page", Common.getscreenShotPathforReport("createaccount"));
			   }
			catch(Exception |Error e) {
			    e.printStackTrace();
			      ExtenantReportUtils.addFailedLog("verifying Create Account button", "It should lands on Create New Customer from Account form Page", "user faield lands on Account form Page", Common.getscreenShotPathforReport("createaccount"));
				  Assert.fail();
				}
			
			try{
			    Common.textBoxInput("id", "firstname",data.get(dataSet).get("FirstName"));
		        Common.textBoxInput("id", "lastname",data.get(dataSet).get("LastName"));
			    Common.textBoxInput("id", "email_address",data.get(dataSet).get("Email"));
			    Common.textBoxInput("id", "password",data.get(dataSet).get("Password"));
			    Common.textBoxInput("id", "password-confirmation", data.get(dataSet).get("Password"));
			    Common.clickElement("xpath", "//button[@title='Create an Account']");
			   }
			 catch(Exception |Error e) {
			        ExtenantReportUtils.addFailedLog("verifying Create Account from", "Account should be created successfully navigate to My Account page", "user faield to create account", Common.getscreenShotPathforReport("createaccountfaield"));
					Assert.fail();
				}
			
		}
		public void clickHumidifiers(){
			String expectedResult="It Should be navigate to Humdifiers & Vaporizers.";
			try{
			Thread.sleep(6000);
			Sync.waitElementClickable("xpath", "(//a[@class='pagebuilder-button-primary clear'])[1]");
			Common.clickElement("xpath", "(//a[@class='pagebuilder-button-primary clear'])[1]");
	        ExtenantReportUtils.addPassLog("verifying category Humdifiers & Vaporizers","lands on Humdifiers & Vaporizers", "User lands on the Humdifiers & Vaporizers", Common.getscreenShotPathforReport("faield to click"));
			}
	       catch(Exception |Error e) {
	    	   e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validating the category page.", expectedResult, "user faield to navigate Humdifiers & Vaporizers",  Common.getscreenShotPathforReport("faield to navgate categorypage"));
				Assert.fail();
				
			}                
		}
		public void productselect()throws Exception{
			String expectedResult="It should select a product";
			try{
			Thread.sleep(2000);
			Common.actionsKeyPress(Keys.ARROW_DOWN);
			Sync.waitElementClickable("xpath", "//ol[@class='products list items product-items']/li[1]/div/a/span/span/img");
			Common.findElement("xpath", "//ol[@class='products list items product-items']/li[1]/div/a/span/span/img").click();
			ExtenantReportUtils.addPassLog("Verifing product list page", "Should select a product", "Should select a product", Common.getscreenShotPathforReport("Product is selected successfully"));
		}
			catch (Exception |Error e ){
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("Verifing product list page", "Should select a product", "Should select a product", Common.getscreenShotPathforReport("Failed to selected product"));
			}	
			
			}
		public void addtocart ()throws Exception{
			String expectedResult="Product should add to cart";
			try{
			Thread.sleep(1000);
			Sync.waitElementClickable("xpath", "//button[@id='product-addtocart-button']");
			Common.findElement("xpath", "//button[@id='product-addtocart-button']").click();
			ExtenantReportUtils.addPassLog("Verifing product to add cart", "Product should add ti cart", "Product should add to cart", Common.getscreenShotPathforReport("Product successfully added to cart"));
			}
				catch (Exception |Error e ){
				e.printStackTrace();
					ExtenantReportUtils.addFailedLog("Verifing product to add to cart", "product should add to cart", "product should add to cart", Common.getscreenShotPathforReport("Failed to add to cart"));
				}
		}
	
	public void checkout()throws Exception{
		String expectedResult="Product should add to cart";
		try{
			Thread.sleep(5000);
			Sync.waitElementClickable("id", "top-cart-btn-checkout");
			Common.clickElement("id", "top-cart-btn-checkout");
			ExtenantReportUtils.addPassLog("Verifing guest user checkout page", "Guest user checkout page success", "Guest user checkout page success", Common.getscreenShotPathforReport("Guest user checkout page success"));
		}
		catch (Exception |Error e ){
			e.printStackTrace();
				ExtenantReportUtils.addFailedLog("Verifing guest user checkout page", "Guest user checkout page success", "Guest user checkout page success", Common.getscreenShotPathforReport("Failed to go geust user checkout"));
			}
	}
		public void shipingaddress (String datSet)throws Exception{
			String expectedResult="Product should add to cart";
			try{
				Thread.sleep(3000);
				Sync.waitElementClickable("xpath", "(//input[@id='customer-email'])[1]");
				Common.textBoxInput("xpath", "(//input[@id='customer-email'])[1]", data.get(datSet).get("Email"));
				Sync.waitElementClickable("xpath", "//input[@name='firstname']");
				Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(datSet).get("FirstName"));
				Sync.waitElementClickable("xpath", "//input[@name='lastname']");
				Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(datSet).get("LastName"));
				Common.actionsKeyPress(Keys.PAGE_DOWN);
				Sync.waitElementPresent("xpath", "//input[@name='street[0]']");
				Common.textBoxInput("xpath", "//input[@name='street[0]']", data.get(datSet).get("Street"));
				Thread.sleep(3000);
				Common.actionsKeyPress(Keys.SPACE);
				Thread.sleep(2000);
				Common.clickElement("xpath", "(//a[@class='dropdown-item list-item'])[2]");
				Thread.sleep(5000);
				Common.textBoxInput("xpath", "//input[@name='telephone']", data.get(datSet).get("phone"));
				Common.clickElement("xpath", "//button[@class='button action continue primary']");
				ExtenantReportUtils.addPassLog("Verifing guest user checkout page", "Guest user checkout page success", "Guest user checkout page success", Common.getscreenShotPathforReport("Guest user checkout page success"));
			}
			
				catch(Exception |Error e) {
					e.printStackTrace();
					ExtenantReportUtils.addFailedLog("verifying shipping addres filling", "user will fill the all the shipping", "faield to add new shipping address",Common.getscreenShotPathforReport("faieldsshippingpagefilling"));
					Assert.fail();
					
				} 
		}	
		public void paymentDetailsandPlaceOrder(String dataSet){
			String expectedResult="Payment With Valid Credit Card";
			try {
				Thread.sleep(5000);

				if(Common.isElementDisplayed("xpath", "//div[@id='checkout-loader']")) {
					Thread.sleep(8000);
				}else {
					Thread.sleep(6000);
					//Sync.waitElementPresent("id", "ime_paymetrictokenize");
					//Common.clickElement(By.id("ime_paymetrictokenize"));
					//Thread.sleep(8000);
				}	
				Common.switchFrames("paymetric_xisecure_frame");
				Sync.waitElementPresent("xpath", "//select[@id='c-ct']");
				Common.dropdown("xpath", "//select[@id='c-ct']", SelectBy.TEXT, data.get(dataSet).get("cardType"));
				Common.textBoxInput("id", "c-cardnumber", data.get(dataSet).get("cardNumber"));
				
				Common.clickElement("xpath", "(//select[@id='c-exmth']/option)[6]");
				//Common.dropdown("id", "c-exmth", SelectBy.VALUE, data.get(dataSet).get("ExpMonth"));
				
				//Common.textBoxInput("xpath", "//select[@id='c-exmth']", data.get(dataSet).get("ExpMonth"));
				Common.dropdown("id", "c-exyr", SelectBy.TEXT, data.get(dataSet).get("ExpYear"));
				Common.textBoxInput("id", "c-cvv",  data.get(dataSet).get("cvv"));
				Common.switchToDefault();

				Sync.waitElementPresent("xpath", "//button[@class='action primary checkout']");
				
				//Common.clickElement(By.xpath("//button[@class='action primary checkout']"));
				//Common.findElement("xpath", "//button[@class='action primary checkout']").click();
				
				Common.javascriptclickElement("xpath", "//button[@class='action primary checkout']");
				report.addPassLog(expectedResult,"Should Make payment wih valid credit card successfully", "Make payment wih valid credit card successfully", Common.getscreenShotPathforReport("Payment CC success"));
				Sync.waitElementVisible("xpath", "//p[text()='Your order number is: ']")	;
				report.addPassLog(expectedResult," Order should place sucessfully", "Submited order and order places sucessfully", Common.getscreenShotPathforReport("Payment CC success"));
			}catch(Exception |Error e)
			{
				report.addFailedLog(expectedResult,"Should Make payment wih valid credit card successfully", "Make payment wih valid credit card unsuccessfully", Common.getscreenShotPathforReport("Payment CC Failed"));
				e.printStackTrace();
				Assert.fail();
			}
		}

	public void Logout()throws Exception{
			String expectedResult="log out";
			try{
				Thread.sleep(5000);
				Sync.waitElementClickable("xpath", "//button[@class='action switch']");
				Common.clickElement("xpath", "//button[@class='action switch']");
				Thread.sleep(1000);
				Sync.waitElementClickable("xpath", "//a[text()='Sign Out']");
				Common.clickElement("xpath", "//a[text()='Sign Out']");
				ExtenantReportUtils.addPassLog("Verfing log out of register", "user successfully loged out", "user successfully loged out", Common.getscreenShotPathforReport("user successfully loged out"));
			}
			
				catch(Exception |Error e) {
					e.printStackTrace();
				ExtenantReportUtils.addFailedLog("Verfing log out of register", "user successfully loged out", "user successfully loged out",Common.getscreenShotPathforReport("faield to log out"));
					Assert.fail();
				
				
			
		}
			
		}
		public void shipingmethod () throws InterruptedException{
			String expectedResult="shipping method";
			try{
			Thread.sleep(2000);
			Sync.waitElementClickable("xpath", "//button[@class='button action continue primary']");
			Common.clickElement("xpath", "//button[@class='button action continue primary']");
			ExtenantReportUtils.addPassLog("Verfing log out of register", "user successfully loged out", "user successfully loged out", Common.getscreenShotPathforReport("user successfully loged out"));
		}
		
			catch(Exception |Error e) {
				e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Verfing log out of register", "user successfully loged out", "user successfully loged out",Common.getscreenShotPathforReport("faield to log out"));
				Assert.fail();		
		
	}
		
	}
		public void forgotpassword(String dataSet) throws Exception {
			String expectedResult="forgotpassword";
			try{
			Thread.sleep(3000);
			Common.clickElement("xpath", "//a[@class='header-content__right-link']");
			Sync.waitElementClickable("xpath", "//a[@class='action remind']");
			Common.clickElement("xpath", "//a[@class='action remind']");
			Common.textBoxInput("xpath", "//input[@id='email_address']", data.get(dataSet).get("Email"));
			Sync.waitElementClickable("xpath", "//button[@class='action submit primary']");
			Common.findElement("xpath", "//button[@class='action submit primary']").click();
			ExtenantReportUtils.addPassLog("Verfing forgot password", "user can successfully  rest password", "user can successfully  rest password", Common.getscreenShotPathforReport("user  successfully  rested password"));
		}	
			
			
			catch(Exception |Error e) {
				e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Verfing forgot password", "user can successfully  rest password", "user can successfully  rest password",Common.getscreenShotPathforReport("faield to rest password"));
				Assert.fail();		
			}
		}
		public void mincat () throws Exception {
			String expectedResult="minicat";
			try{
			Thread.sleep(3000);
			Sync.waitElementClickable("xpath", "//a [@class='action showcart']");
			Common.findElement("xpath", "//a [@class='action showcart']").click();
			ExtenantReportUtils.addPassLog("Verfing mini cart", "user can successfully view mini cart", "user can successfully view mini cart", Common.getscreenShotPathforReport("user  successfully can view mini cart"));
			}	
				catch(Exception |Error e) {
					e.printStackTrace();
				ExtenantReportUtils.addFailedLog("Verfing mini cart", "user can successfully view mini cart", "user can successfully view mini cart",Common.getscreenShotPathforReport("faield to view mini cart"));
					Assert.fail();
}	}
		public void validatingcart() throws Exception {
			String expectedResult="minicart validating";
			try{
			Thread.sleep(1000);
			Sync.waitElementClickable("xpath", "//a[@class='action viewcart']");
			Common.findElement("xpath", "//a[@class='action viewcart']").click();
			Sync.waitElementClickable("xpath", "//button[@class='qty-incrementer__increment']");
			Common.findElement("xpath", "//button[@class='qty-incrementer__increment']").click();
			Sync.waitElementClickable("xpath", "//button[@name='update_cart_action']");
			Common.findElement("xpath", "//button[@name='update_cart_action']").click();
			Common.findElement("xpath", "//a [@class='action showcart']").click();
			ExtenantReportUtils.addPassLog("Verfing cart validation", "user can successfully update cart", "user can successfully update cart", Common.getscreenShotPathforReport("user can successfully update cart"));
			}	
				catch(Exception |Error e) {
					e.printStackTrace();
				ExtenantReportUtils.addFailedLog("Verfing cart validating", "user can successfully update cart", "user can successfully update cart",Common.getscreenShotPathforReport("faield to update cart"));
					Assert.fail();
		}
		}
		public void search(String dataset) {
			try {
				Thread.sleep(2000);
				Sync.waitElementPresent("xpath", "//*[@id=\"search_mini_form\"]/div/label");
				Common.clickElement("xpath", "//*[@id=\"search_mini_form\"]/div/label");
				Sync.waitElementPresent("xpath", "(//input[@id='search'])");
				Common.textBoxInput("xpath", "(//input[@id='search'])",data.get(dataset).get("ProductName"));
				ExtenantReportUtils.addPassLog("To verify the search functionality with full productname","should get the product name in search field","user  successfully Entered the productname", Common.getscreenShotPathforReport("Searched productname Successfully"));
			}catch(Exception |Error e) {
				ExtenantReportUtils.addFailedLog("To verify the search functionality with fullproduct name","Should get the productname in search field","user unable to Enter the productname", Common.getscreenShotPathforReport("Failed to search proudctname"));			
				Assert.fail();	
				}

		try {
			    Common.actionsKeyPress(Keys.ENTER);
			    Thread.sleep(2000);
			   // Common.scrollIntoView("xpath", "(//h3[@class='result-title text-ellipsis'])[1]");
			    ExtenantReportUtils.addPassLog("To verify search results page","Should land on  product list page","user successfully landed on  product search results page", Common.getscreenShotPathforReport("Successfully landed on PLP Page"));
			}catch(Exception |Error e) {
				ExtenantReportUtils.addFailedLog("To verify search results page","Should land on  product list page","user successfully landed on  product search results page", Common.getscreenShotPathforReport("Failed to land on PLP Page"));			
				Assert.fail();	
				}
			}
		public void Myaccountinformation () throws Exception {
			Thread.sleep(2000);
			Sync.waitElementClickable("xpath", "//button[@class='action switch']");
			Common.findElement("xpath", "//button[@class='action switch']").click();
			Sync.waitElementClickable("xpath", "//a[@id='ideWmZDsN0']");
			Common.findElement("xpath", "//a[@id='ideWmZDsN0']").click();
		}
		public void MyAccountpage() throws Exception{

			Thread.sleep(3000);

			Sync.waitElementPresent("xpath", "(//li[@class='nav item'])[1]");

			Common.clickElement("xpath", "(//li[@class='nav item'])[1]");

       

		Sync.waitElementPresent("xpath", "(//td[@class='col actions'])/a");
		
		Common.clickElement("xpath", "(//td[@class='col actions'])/a");
		
		       
		Thread.sleep(1000);
		Sync.waitElementPresent("xpath", "//div[@class='actions-toolbar order-actions-toolbar']/div");
		
		Common.clickElement("xpath", "//div[@class='actions-toolbar order-actions-toolbar']/div");
		
		       
		
		report.addPassLog("Should display My Orders Page", "My Orders Page displayed successfully", Common.getscreenShotPathforReport("My Orders Page success"));
		
		}		
		public void Verifyhomepage ()  {
			try {
				Sync.waitElementPresent("xpath", "//p[@id='YCP3PN0']");
				String title=Common.findElement("xpath", "//p[@id='YCP3PN0']").getText();
				title.equals("Promo Banner Information Here");
				
				ExtenantReportUtils.addPassLog("To verify search results page","Should land on  product list page","user successfully landed on  product search results page", Common.getscreenShotPathforReport("Successfully landed on PLP Page"));
			}catch(Exception |Error e) {
				ExtenantReportUtils.addFailedLog("To verify search results page","Should land on  product list page","user successfully landed on  product search results page", Common.getscreenShotPathforReport("Failed to land on PLP Page"));			
				Assert.fail();	
				}
			
			
		}			
		public void Addnewaddress (String dataSet){
			try{
				Thread.sleep(3000);
				Sync.waitElementClickable("xpath", "//button[@class='action primary add']");
				Common.findElement("xpath", "//button[@class='action primary add']").click();
				ExtenantReportUtils.addPassLog("To verify my address page","Should land on my address page","user successfully landed on my address page", Common.getscreenShotPathforReport("Successfully landed on My address Page"));
				Thread.sleep(2000);
				Sync.waitElementPresent("xpath", "//input[@id='street_1']");
				Common.textBoxInput("xpath", "//input[@id='street_1']", data.get(dataSet).get("Street"));
				Thread.sleep(8000);
				Common.clickElement("xpath", "//a[@class='dropdown-item list-item']");
				Thread.sleep(5000);
				Common.textBoxInput("xpath", "//input[@name='telephone']", data.get(dataSet).get("phone"));
				Thread.sleep(2000);
				Sync.waitElementClickable("xpath", "//button[@class='action submit primary']");
				Common.clickElement("xpath", "//button[@class='action submit primary']");
				ExtenantReportUtils.addPassLog("Verifing guest user checkout page", "Guest user checkout page success", "Guest user checkout page success", Common.getscreenShotPathforReport("Guest user checkout page success"));
			}
			
				catch(Exception |Error e) {
					e.printStackTrace();
					ExtenantReportUtils.addFailedLog("verifying shipping addres filling", "user will fill the all the shipping", "faield to add new shipping address",Common.getscreenShotPathforReport("faieldsshippingpagefilling"));
					Assert.fail();
			
		}
		}
		public void myaccount(){
			try{
				Thread.sleep(2000);
				Sync.waitElementClickable("xpath", "(//li[@class='nav item']/a)[4]");
				Common.findElement("xpath", "(//li[@class='nav item']/a)[4]").click();
				ExtenantReportUtils.addPassLog("To verify My account page","Should land on my account page","User Should land on my account page", Common.getscreenShotPathforReport("Successfully landed on Myaccount Page"));
			}
			catch(Exception |Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("To verify My account page", "Should land on my account page", "faield to land on my account",Common.getscreenShotPathforReport("faield to land on my account page"));
				Assert.fail();
		}
	}
		public void viewandeditcart (String dataSet){
			try{
				Thread.sleep(5000);
				Sync.waitElementClickable("xpath", "//a[@class='action viewcart']");
				Common.findElement("xpath", "//a[@class='action viewcart']").click();
				
				ExtenantReportUtils.addPassLog("To verify My account page","Should land on my account page","User Should land on my account page", Common.getscreenShotPathforReport("Successfully landed on Myaccount Page"));
			}
			catch(Exception |Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("To verify My account page", "Should land on my account page", "faield to land on my account",Common.getscreenShotPathforReport("faield to land on my account page"));
				Assert.fail();
					
			}	
		}
		public void PLPvalidation(){
			try{
				Thread.sleep(2000);
				Sync.waitElementClickable("xpath", "//div[@class='filter-options-title']");
				Common.findElement("xpath", "//div[@class='filter-options-title']").click();
				Sync.waitElementClickable("xpath", "//a[@data-opt-path='price=40-50']");
				Common.findElement("xpath", "//a[@data-opt-path='price=40-50']").click();
				Thread.sleep(3000);
				Sync.waitElementClickable("xpath", "(//select[@id='sorter']/option)[3]");
				Common.clickElement("xpath", "(//select[@id='sorter']/option)[3]");
				
				ExtenantReportUtils.addPassLog("To verify My account page","Should land on my account page","User Should land on my account page", Common.getscreenShotPathforReport("Successfully landed on Myaccount Page"));
			}
			catch(Exception |Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("To verify My account page", "Should land on my account page", "faield to land on my account",Common.getscreenShotPathforReport("faield to land on my account page"));
				Assert.fail();
	
			}
		}
	}
