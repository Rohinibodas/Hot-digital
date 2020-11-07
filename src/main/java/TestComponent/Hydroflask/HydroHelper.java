package TestComponent.Hydroflask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import TestLib.Common;
import TestLib.Sync;
import TestLib.Common.SelectBy;
import Utilities.ExcelReader;
import Utilities.ExtenantReportUtils;


public class HydroHelper {

	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data=new HashMap<>();
	 static ExtenantReportUtils report;
	

	public void navigateMyAccount() throws InterruptedException
	{	
		String expectedResult="User should land on the home page";
		try {
		report.addPassLog(expectedResult,"Successfully landed on the home page",Common.getscreenShotPathforReport("Successfully landed on the home page"));
		
		Sync.waitElementClickable(30, By.xpath("//a[@class='social-login']"));
		Common.findElement("xpath", "//a[@class='social-login']").click();
		Thread.sleep(3000);
		}
		catch(Exception e)
		{
			report.addFailedLog(expectedResult, "Failed to get Expected results", Common.getscreenShotPathforReport(expectedResult));
		}
		/*String expectedResult="Open login, register pop up";
		report.addPassLog(expectedResult,"click on the 'My Acount' link",Common.getscreenShotPathforReport("my account"));*/
	}

	public void acceptPrivecy()
	{
		Common.clickElementStale("xpath", "//button[text()='AGREE & PROCEED']");
	}
	public void CreateNewAccount(String dataSet) throws Exception
	{	
		
		navigateMyAccount();
		String expectedResult="opens Sign up pop up";
		
		try{
		
		try {
			Sync.waitElementClickable(30, By.xpath("//div[contains(text(),'Sign Up')]"));
			
		
			report.addPassLog(expectedResult,"Successfully opeans Sign up pop_up page",Common.getscreenShotPathforReport("Successfully opeans Sign up pop_up page"));
			
			
		}catch (Exception e) {
			if(Common.findElement("xpath", "//div[contains(text(),'Sign Up')]")==null)
			{
				
				Common.clickElement("xpath", "//a[@class='social-login']");
				Thread.sleep(2000);
			}
		}
		
		String email=Common.genrateRandomEmail(data.get(dataSet).get("Email"));
		Common.clickElement("xpath", "//div[contains(text(),'Sign Up')]");
		 expectedResult="opens Sign up pop up";
		report.addPassLog(expectedResult,"Successfully opeans Sign up pop_up page",Common.getscreenShotPathforReport("Successfully opeans Sign up pop_up page"));
		//report.addPassLog("opens registration pop up",Common.getscreenShotPathforReport("register"));
		Common.textBoxInput("id", "social-login-popup-create-firstname", data.get(dataSet).get("FirstName"));
		Common.textBoxInput("id", "social-login-popup-create-lastname", data.get(dataSet).get("LastName"));
		Common.textBoxInput("id", "social-login-popup-create-email", email);
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Common.textBoxInput("id", "social-login-popup-create-pass", data.get(dataSet).get("Password"));
		Common.textBoxInput("id", "password-confirmation-social", data.get(dataSet).get("Password"));
		Common.clickElement("id", "social-login-popup-create-is-subscribed");
		 expectedResult="see the fields populated with the data";
		report.addPassLog(expectedResult,"key in the data: first name, last name, email address, password and pasword confirmation",Common.getscreenShotPathforReport("Sign up with data"));
		Common.clickElement("xpath", "//button[@title='Sign Up']");
		//Common.actionsKeyPress(Keys.ESCAPE);
		
	
	
		Thread.sleep(2000);
		Sync.waitElementVisible("xpath", "//span[@data-ui-id='page-title-wrapper']");
		Assert.assertEquals(Common.getText("xpath", "//span[@data-ui-id='page-title-wrapper']"), "My Account");
		expectedResult="it creates an account and logs in the user";
		report.addPassLog(expectedResult,"Successfully Created an account and logged in the application",Common.getscreenShotPathforReport("Successfully Created an account and logged in the application"));
		}
		catch(Exception e)
		{
			report.addFailedLog(expectedResult, "Failed to get Expected results", Common.getscreenShotPathforReport(expectedResult));
		}
		
		}	

	
	public void loginHydroflaskAccount(String dataSet) throws Exception
	{
		Thread.sleep(3000);
		navigateMyAccount();
		String expectedResult="Opens login pop up";
		try{
		report.addPassLog(expectedResult,"Successfully  Opened login pop up",Common.getscreenShotPathforReport("login pop up"));
		Sync.waitElementClickable(30, By.id("social-login-popup-log-in-email"));
		if(Common.findElement("id", "social-login-popup-log-in-email")==null)
		{
			Common.clickElement("xpath", "//a[@class='social-login']");
			Thread.sleep(2000);
		}
		
		
		Common.textBoxInput("id", "social-login-popup-log-in-email",data.get(dataSet).get("Email"));
		Common.textBoxInput("id", "social-login-popup-log-in-pass",data.get(dataSet).get("Password"));
		expectedResult="see the fields populated with the data";
		report.addPassLog(expectedResult,"Successfully Enter in the login data, email address and password",Common.getscreenShotPathforReport("LoginForm"));
		Common.clickElement("id", "bnt-social-login-authentication");
		Thread.sleep(4000);
        Sync.waitElementPresent("xpath", "//span[@data-ui-id='page-title-wrapper']");
		Assert.assertEquals(Common.getText("xpath", "//span[@data-ui-id='page-title-wrapper']"), "My Account");
		expectedResult="it will successfully logs in and will see the customer name on the header and customer is redirected to 'My Account' page";
		report.addPassLog(expectedResult,"Logged in the application and customer is redirected to 'My Account' page",Common.getscreenShotPathforReport("Myaccount"));
		}
		catch(Exception e)
		{
			report.addFailedLog(expectedResult, "Failed to get Expected results", Common.getscreenShotPathforReport(expectedResult));
		}
		
		}
	
/*	public void orderSubmit(String category) throws Exception
	{	
		report.addPassLog("Successfully landed on the home page",Common.getscreenShotPathforReport("Successfully landed on the home page"));
		Thread.sleep(5000);
		//Common.getDriver().switchTo().frame(0);
	//	Thread.sleep(2000);
		Sync.waitElementClickable("xpath", "//ul[@class='megamenu-list']/li[1]/div[1]");
		System.out.println(Common.getText("xpath", "//ul[@class='megamenu-list']/li[1]/div[1]/button"));
		Thread.sleep(4000);
		Common.mouseOverClick("xpath", "//ul[@class='megamenu-list']/li[1]/div[1]/button");	
		//Common.clickElement("css", "ul.megamenu-list > li:nth-of-type(1) > div:nth-of-type(1) > button");
		Thread.sleep(3000);
		try {
		Common.mouseOver("xpath", "//a[contains(text(),'"+category+"')]");
		
		report.addPassLog("click the"+category,Common.getscreenShotPathforReport("click the category as shop option as  "+category));
		}catch (Exception e) {
			// TODO: handle exception
			Common.clickElement("xpath", "//ul[@class='megamenu-list']/li[1]/div[1]/button");
			//report.addPassLog("click the option as shop in category",Common.getscreenShotPathforReport("click the category as shop "));		
			}
		report.addPassLog("Clicked shop option in category",Common.getscreenShotPathforReport("clicked shop option in category"));
		Thread.sleep(1000);
		Common.clickElement("xpath", "//a[contains(text(),'"+category+"')]");
		//Sync.waitElementVisible("xpath", "//div[text()='Drink in the adventure.']");
		Thread.sleep(4000);
		report.addPassLog("Selected the "+category+" category	",Common.getscreenShotPathforReport("click the category as shop option as  "+category));
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Thread.sleep(2000);
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Thread.sleep(4000);
		Sync.waitElementClickable("xpath", "//button[@title='Add to Cart']");
		Common.clickElement("xpath", "//button[@title='Add to Cart']");
		Thread.sleep(5000);
		report.addPassLog("Added Product to Cart",Common.getscreenShotPathforReport("Added Product to Cart"));
		}*/
	public void orderSubmit(String category) throws Exception
	{	
		String expectedResult="User should land on the home page";
		try {
		report.addPassLog(expectedResult,"Successfully landed on the home page",Common.getscreenShotPathforReport("Successfully landed on the home page"));
		Thread.sleep(5000);
		//Common.getDriver().switchTo().frame(0);
	//	Thread.sleep(2000);
		Sync.waitElementClickable("xpath", "//ul[@class='megamenu-list']/li[1]/div[1]");
		System.out.println(Common.getText("xpath", "//ul[@class='megamenu-list']/li[1]/div[1]/button"));
		Thread.sleep(4000);
		Common.mouseOverClick("xpath", "//ul[@class='megamenu-list']/li[1]/div[1]/button");	
		//Common.clickElement("css", "ul.megamenu-list > li:nth-of-type(1) > div:nth-of-type(1) > button");
		Thread.sleep(3000);
		try {
		Common.mouseOver("xpath", "//a[contains(text(),'"+category+"')]");
		expectedResult="User should click the"+category;
		report.addPassLog(expectedResult,"Successfully landed on the home page",Common.getscreenShotPathforReport(expectedResult));
		//report.addPassLog("click the"+category,Common.getscreenShotPathforReport("click the category as shop option as  "+category));
		}catch (Exception e) {
			// TODO: handle exception
			Common.clickElement("xpath", "//ul[@class='megamenu-list']/li[1]/div[1]/button");
			//report.addPassLog("click the option as shop in category",Common.getscreenShotPathforReport("click the category as shop "));		
			}
		Thread.sleep(1000);
		Common.clickElement("xpath", "//a[contains(text(),'"+category+"')]");
		//Sync.waitElementVisible("xpath", "//div[text()='Drink in the adventure.']");
		Thread.sleep(4000);
		expectedResult="User should select the "+category+"category";
		report.addPassLog(expectedResult,"Selected the "+category+" category",Common.getscreenShotPathforReport(expectedResult));
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Thread.sleep(2000);
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Thread.sleep(8000);
		Sync.waitElementClickable("xpath", "//button[@title='Add to Cart']");
		Common.clickElement("xpath", "//button[@title='Add to Cart']");
		Thread.sleep(5000);
		expectedResult="Product should add to Cart";
		report.addPassLog(expectedResult,"Added Product to Cart",Common.getscreenShotPathforReport(expectedResult));
		}catch(Exception e)
		{
			report.addFailedLog(expectedResult, "Failed to get Expected results", Common.getscreenShotPathforReport(expectedResult));
		}
		}
	public void checkOut() throws Exception
	{
		String expectedResult ="it should land on the checkout intermediate page";
		try{
		Common.clickElement("xpath", "//a[@aria-label='minicart']");
		Thread.sleep(2000);
		Common.clickElement("id", "top-cart-btn-checkout");
		//Sync.waitElementVisible("className", "checkout-step-title");
		Thread.sleep(3000);
		
		report.addPassLog(expectedResult," Successfully Clicked the checkout button ",Common.getscreenShotPathforReport("checked out page"));
		}
		catch(Exception e)
		{
			report.addFailedLog(expectedResult, "Failed to get Expected results", Common.getscreenShotPathforReport(expectedResult));
		}
		
		
		}
	public void checkOutCart() throws Exception
	{
		try {
			checkOut();
		}
		catch (Exception e) {
			Common.refreshpage();
			Thread.sleep(6000);
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(2000);
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(4000);
			Sync.waitElementClickable("xpath", "//button[@title='Add to Cart']");
			Common.clickElement("xpath", "//button[@title='Add to Cart']");
			Thread.sleep(5000);
			checkOut();
		}
	}

	
	public void closeFreeGift() throws Exception {
		 for(int i=0;i<10;i++){
				List<WebElement> webelemts=Common.findElements("xpath", "//div[@id='checkout-loader']");
				Thread.sleep(1000);
				int siz=webelemts.size();
			    if(siz==0){
					int size=Common.findElements("xpath", "//button[contains(@class,'ampromo-close')]").size();
					if(size!=0){
						Common.clickElement("xpath", "//button[contains(@class,'ampromo-close')]");
						break;
					}
					
					break;
				}
			   		 }
	}
	
	public void addDeliveryAddress_registerUser(String dataSet) throws Exception{
		
		  closeFreeGift();
		  String expectedResult="shipping address is filled in to the fields";
		 try{
		 
		   int size=Common.findElements(By.xpath("//span[contains(text(),'Add New Address')]")).size();
		      if(size>0){
			    Common.clickElement("xpath", "//span[contains(text(),'Add New Address')]");
			    Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']", data.get(dataSet).get("FirstName"));
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']", data.get(dataSet).get("LastName"));
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']", data.get(dataSet).get("Street"));
				Thread.sleep(2000);
				Common.actionsKeyPress(Keys.SPACE);
				Thread.sleep(3000);
				try {
				Common.clickElement("xpath", "//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
				}catch(Exception e)
				{
					Common.actionsKeyPress(Keys.BACK_SPACE);
					Thread.sleep(1000);
					Common.actionsKeyPress(Keys.SPACE);
					Common.clickElement("xpath", "//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");		
				}
				if(data.get(dataSet).get("StreetLine2")!=null)
				{
					Common.textBoxInput("name", "street[1]", data.get(dataSet).get("Street"));
				}
				if(data.get(dataSet).get("StreetLine3")!=null)
				{
					Common.textBoxInput("name", "street[2]", data.get(dataSet).get("Street"));
				}
				Common.actionsKeyPress(Keys.PAGE_DOWN);
				Thread.sleep(3000);
				//Common.mouseOverClick("name", "region_id");
				try {
				Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));}
				catch (ElementClickInterceptedException e) {
					// TODO: handle exception
					Thread.sleep(3000);
					Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
				}
				Thread.sleep(2000);
				Common.textBoxInputClear("xpath", "//form[@id='co-shipping-form']//input[@name='postcode']");
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='postcode']", data.get(dataSet).get("postcode"));
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='telephone']", data.get(dataSet).get("phone"));
				
				
				//report.addPassLog("Filled the shipping address for check out page",Common.getscreenShotPathforReport("fill the shipping address"));
	            Common.clickElement("xpath", "//button[contains(@class,'save-address')]");
	            Thread.sleep(3000);
	            
	            Common.clickElement("xpath", "//input[@id='label_method_bestway']");
	
	            
				report.addPassLog(expectedResult,"Filled the shipping address",Common.getscreenShotPathforReport("fill the shipping address"));          
	            
				    Common.clickElement("xpath", "//button[@data-ac-test='form-shipping-address_action_submit']");
					Thread.sleep(3000);
					/*
					 * need to implement 
					 * 
					 */
					
				//report.addPassLog("clicked on the proceed to payment section",Common.getscreenShotPathforReport("land on the payment section"));
		      
					  closeFreeGift();
		 }
		 
		 
		 
		 
		 else
		    	
		    
		 {
		
			    Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']", data.get(dataSet).get("FirstName"));
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']", data.get(dataSet).get("LastName"));
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']", data.get(dataSet).get("Street"));
				Thread.sleep(2000);
				Common.actionsKeyPress(Keys.SPACE);
				Thread.sleep(3000);
				try {
				Common.clickElement("xpath", "//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
				}catch(Exception e)
				{
					Common.actionsKeyPress(Keys.BACK_SPACE);
					Thread.sleep(1000);
					Common.actionsKeyPress(Keys.SPACE);
					Common.clickElement("xpath", "//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");		
				}
				if(data.get(dataSet).get("StreetLine2")!=null)
				{
					Common.textBoxInput("name", "street[1]", data.get(dataSet).get("Street"));
				}
				if(data.get(dataSet).get("StreetLine3")!=null)
				{
					Common.textBoxInput("name", "street[2]", data.get(dataSet).get("Street"));
				}
				Common.actionsKeyPress(Keys.PAGE_DOWN);
				Thread.sleep(3000);
				//Common.mouseOverClick("name", "region_id");
				try {
				Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));}
				catch (ElementClickInterceptedException e) {
					// TODO: handle exception
					Thread.sleep(3000);
					Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
				}
				Thread.sleep(2000);
				Common.textBoxInputClear("name", "postcode");
				Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
				Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));

				
			 expectedResult="shipping address is filled in to the fields";
				report.addPassLog(expectedResult,"Filled the shipping address",Common.getscreenShotPathforReport("fill the shipping address"));
	            Common.clickElement("xpath", "//button[@data-ac-test='form-shipping-address_action_submit']");
	            
	            Common.mouseOverClick("xpath", "//input[@id='label_method_bestway']");
	            
	            
				Thread.sleep(3000);
				//report.addPassLog("clicked on the proceed to payment section",Common.getscreenShotPathforReport("land on the payment section"));
				  closeFreeGift();
		 }

		 }catch(Exception e)
			{
				report.addFailedLog(expectedResult, "Failed to get Expected results", Common.getscreenShotPathforReport(expectedResult));
			}
		
	}
	
	
	public void addDeliveryAddress(String dataSet) throws Exception
	{
		   String expectedResult="email field will have email address";
			try{
				
			
		 try {	
			Sync.waitElementVisible("id", "customer-email-address");
			Common.textBoxInput("id", "customer-email-address",data.get(dataSet).get("Email"));
			}catch (NoSuchElementException e) {
				checkOut();
				Common.textBoxInput("id", "customer-email-address",data.get(dataSet).get("Email"));
				//report.addPassLog("enter the email address",Common.getscreenShotPathforReport("fill in the email id"));
			}
			Thread.sleep(3000);
             expectedResult="email field will have email address";
			report.addPassLog(expectedResult,"Filled Email address",Common.getscreenShotPathforReport("fill in the email id"));
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']", data.get(dataSet).get("FirstName"));
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']", data.get(dataSet).get("LastName"));
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']", data.get(dataSet).get("Street"));
			Thread.sleep(2000);
			Common.actionsKeyPress(Keys.SPACE);
			Thread.sleep(3000);
			try {
			Common.clickElement("xpath", "//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
			}catch(Exception e)
			{
				Common.actionsKeyPress(Keys.BACK_SPACE);
				Thread.sleep(1000);
				Common.actionsKeyPress(Keys.SPACE);
				Common.clickElement("xpath", "//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");		
			}
			if(data.get(dataSet).get("StreetLine2")!=null)
			{
				Common.textBoxInput("name", "street[1]", data.get(dataSet).get("Street"));
			}
			if(data.get(dataSet).get("StreetLine3")!=null)
			{
				Common.textBoxInput("name", "street[2]", data.get(dataSet).get("Street"));
			}
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(3000);
			//Common.mouseOverClick("name", "region_id");
			try {
			Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));}
			catch (ElementClickInterceptedException e) {
				// TODO: handle exception
				Thread.sleep(3000);
				Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			}
			Thread.sleep(2000);
			Common.textBoxInputClear("name", "postcode");
			Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
			Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
			expectedResult="shipping address is filled in to the fields";
			report.addPassLog(expectedResult,"Filled the shipping address",Common.getscreenShotPathforReport("fill the shipping address"));
            Common.clickElement("xpath", "//button[@data-ac-test='form-shipping-address_action_submit']");
			Thread.sleep(3000);
			
			expectedResult="land on the payment section";
			report.addPassLog(expectedResult,"Successfully  land on the payment section",Common.getscreenShotPathforReport("land on the payment section"));
	
			
			}
			catch(Exception e)
			{
				report.addFailedLog(expectedResult, "Failed to get Expected results", Common.getscreenShotPathforReport(expectedResult));
			}
			
			
			}

	
	public void addNewAddress_ShipPage(String dataSet) throws Exception{
		
		
		Common.clickElement("xpath", "//span[text()='Add New Address']");
		Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']", data.get(dataSet).get("FirstName"));
		Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']", data.get(dataSet).get("LastName"));
		Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']", data.get(dataSet).get("Street"));
		if(data.get(dataSet).get("StreetLine2")!=null)
		{
			Common.textBoxInput("name", "street[1]", data.get(dataSet).get("Street"));
		}
		if(data.get(dataSet).get("StreetLine3")!=null)
		{
			Common.textBoxInput("name", "street[2]", data.get(dataSet).get("Street"));
		}
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Thread.sleep(3000);
		//Common.mouseOverClick("name", "region_id");
		try {
		Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));}
		catch (ElementClickInterceptedException e) {
			Thread.sleep(3000);
			Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
		}
		Thread.sleep(2000);
		Common.textBoxInputClear("name", "postcode");
		Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
		Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
		
		Common.clickElement("xpath", "//span[text()='Save Address']");
		Thread.sleep(3000);
	}
	
	
	
	
	
	
	
	
	
	
	
	public void addPaymentDetails(String dataSet) throws Exception
	{
		//Common.switchFrames("xpath", "//iframe[@id='paymetric_xisecure_frame']");
		//Common.switchFrames("xpath", "//iframe[@id='paymetric_xisecure_frame']");
		String expectedResult="credit card fields are filled with the data";
		try{
			
		
		
		
				Sync.waitElementClickable("xpath", "//label[@for='ime_paymetrictokenize']");
				Common.clickElement("xpath", "//label[@for='ime_paymetrictokenize']");
				Thread.sleep(2000);
				Common.switchFrames("id", "paymetric_xisecure_frame");
				Common.dropdown("xpath", "//select[@id='c-ct']", Common.SelectBy.TEXT, data.get(dataSet).get("cardType"));
				Common.textBoxInput("id", "c-cardnumber", data.get(dataSet).get("cardNumber"));
				Common.dropdown("xpath", "//select[@id='c-exmth']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpMonth"));
				Common.dropdown("xpath", "//select[@id='c-exyr']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpYear"));
				Common.textBoxInput("id", "c-cvv", data.get(dataSet).get("cvv"));	
				Thread.sleep(2000);
				//expectedResult="credit card fields are filled with the data";
				report.addPassLog(expectedResult,"Filled the Card details ",Common.getscreenShotPathforReport("filling the Card details"));
				Common.actionsKeyPress(Keys.ARROW_DOWN);
				Common.switchToDefault();
				Thread.sleep(1000);
				Common.clickElement("xpath", "//button[@title='Place Order']");
				//report.addPassLog(" navigating to order confirmation page ",Common.getscreenShotPathforReport("place the order "));
		}
		
		
		catch(Exception e)
		{
		
			report.addFailedLog(expectedResult, "Failed to get Expected results", Common.getscreenShotPathforReport(expectedResult));
		}
		
	}
	
	public void updatePaymentAndSubmitOrder(String dataSet) throws Exception
	{
		addPaymentDetails(dataSet);
		String expectedResult="It redirects to order confirmation page";
		try{
		
		
		if(Common.findElements("xpath", "//div[@class='message message-error']").size()>0)
		{	
			addPaymentDetails(dataSet);
		}
			String sucessMessage=Common.getText("xpath", "//h1[@class='checkout-success-title']").trim();
			Assert.assertEquals(sucessMessage, "Your order has been received","Sucess message validations");
			
			
			report.addPassLog(expectedResult,"Successfully It redirects to order confirmation page Order Placed ",Common.getscreenShotPathforReport("order  confromation "));
		}
		catch(Exception e)
		{
		
			report.addFailedLog(expectedResult, "Failed to get Expected results", Common.getscreenShotPathforReport(expectedResult));
		}
	}
	public void payPal_Payment(String dataSet ) throws Exception{
		
		
		String expectedResult="It should open paypal site window.";
		try{
		
		Sync.waitElementPresent("xpath", "//input[@id='paypal_express']");
		Common.clickElement("xpath", "//input[@id='paypal_express']");
		
		Thread.sleep(5000);
//		Sync.waitElementClickable("id", "paypal_express");
//		Common.clickElement("id", "paypal_express");
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Common.switchFrames("xpath", "//iframe[contains(@class,'zoid-component-frame')]"); 	
		
		
		
		
		Sync.waitElementClickable("xpath", "//div[contains(@class,'paypal-button-container')]");
		Common.clickElement("xpath", "//div[contains(@class,'paypal-button-container')]");
		
		Common.switchToDefault();
		Thread.sleep(5000);
		Common.switchWindows();
		int size=Common.findElements("id", "acceptAllButton").size();
		if(size>0){
			
			Common.clickElement("id", "acceptAllButton");
			
		}
		report.addPassLog(expectedResult,"open paypal site window ",Common.getscreenShotPathforReport("paypal window"));
		
		
		Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
		Common.textBoxInput("id", "password", data.get(dataSet).get("Password"));
		
		expectedResult="Paypal window should display Email and Password.";
		report.addPassLog(expectedResult,"Paypal user name password page",Common.getscreenShotPathforReport("paypal loginpage"));
		
		Common.clickElement("id", "btnLogin");
		Thread.sleep(5000);
		Common.actionsKeyPress(Keys.END);
		Thread.sleep(5000);
		Common.clickElement("id", "payment-submit-btn");
		
		
		
		Thread.sleep(8000);
		Common.switchToFirstTab();
		String sucessMessage=Common.getText("xpath", "//h1[@class='checkout-success-title']").trim();
		Assert.assertEquals(sucessMessage, "Your order has been received","Sucess message validations");
		expectedResult="Verify order confirmation number which was dynamically generated";
		report.addPassLog(expectedResult,"Order Placed successfull",Common.getscreenShotPathforReport("order  confromation  "));
		}
		catch(Exception e)
		{
			report.addFailedLog(expectedResult, "Failed to get Expected results", Common.getscreenShotPathforReport(expectedResult));
		}
	}
	
	
	
	
	
	

	public void clickWarranty()throws Exception{
		
		String expectedResult="User should land on the home page";
	    try{
		report.addPassLog(expectedResult,"Successfully landed on the home page",Common.getscreenShotPathforReport("Successfully landed on the home page"));
		Common.actionsKeyPress(Keys.END);
		Sync.waitElementPresent("xpath", "//a[text()='warranty']");
		Common.clickElement("xpath", "//a[text()='warranty']");
		
		//report.addPassLog("Clicking the Warranty button",Common.getscreenShotPathforReport("click the Warranty button "));
	    }
	    catch(Exception e)
		{
			report.addFailedLog(expectedResult, "Failed to get Expected results", Common.getscreenShotPathforReport(expectedResult));
		}
		
	}
	
	public void submitWarranty(String dataSet) throws Exception{
		String expectedResult="It should land  warranty page ";
		try{
		
		
		Common.actionsKeyPress(Keys.END);
		clickWarranty();
		
		report.addPassLog(expectedResult,"Successfully land on warranty page  ",Common.getscreenShotPathforReport("warranty page"));
		
		Sync.waitElementPresent("xpath", "//div[@class='wararnty-cta']/a");
		Common.clickElement("xpath", "//div[@class='wararnty-cta']/a");
		expectedResult="User is redirected to login page";
		report.addPassLog(expectedResult,"Successfully  redirected to login page ",Common.getscreenShotPathforReport("clcik the Submit a Warranty clim button"));
		
		
		Sync.waitElementPresent("id", "email");
		Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
		
		
		Sync.waitElementPresent("id", "pass");
		Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
		
		
		//report.addPassLog("Enter the login Infromation ",Common.getscreenShotPathforReport("Loginto application"));
		
		
		Sync.waitElementPresent("xpath", "//button[@class='login-page-submit-action']");
		Common.clickElement("xpath", "//button[@class='login-page-submit-action']");
		expectedResult="User is logged in and able to view warranty form";
		report.addPassLog(expectedResult,"Successfully login user and able to view warranty form ",Common.getscreenShotPathforReport("Loginto application"));
		Thread.sleep(6000);
		//Submit a Warranty Claim form
		
		Sync.waitElementPresent("xpath", "//iframe[contains(@src,'warranty')]");
		Common.switchFrames("xpath", "//iframe[contains(@src,'warranty')]");
		
		try {
		Sync.waitElementPresent("xpath", "//input[contains(@name,'Contact.Name.First')]");
		Common.textBoxInput("xpath", "//input[contains(@name,'Contact.Name.First')]", data.get(dataSet).get("FirstName"));
		}catch(Exception e)
		{
			Common.textBoxInput("xpath", "//input[contains(@name,'Contact.Name.First')]", data.get(dataSet).get("FirstName"));
		}
		
		String s=data.get(dataSet).get("LastName");
		System.out.println(s);
		Sync.waitElementPresent("xpath", "//input[@name='Contact.Name.Last']");
		Common.textBoxInput("xpath", "//input[@name='Contact.Name.Last']", data.get(dataSet).get("LastName"));
		
		Sync.waitElementPresent("xpath", "//input[contains(@id,'Emails')]");
		Common.textBoxInput("xpath", "//input[contains(@id,'Emails')]", data.get(dataSet).get("Email"));
		
		Sync.waitElementPresent("xpath", "//input[contains(@id,'Street')]");
		Common.textBoxInput("xpath", "//input[contains(@id,'Street')]", data.get(dataSet).get("Street"));
		
		
		Sync.waitElementPresent("xpath", "//input[contains(@id,'City')]");
		Common.textBoxInput("xpath", "//input[contains(@id,'City')]", data.get(dataSet).get("City"));
		
		Common.clickElement(By.xpath("//select[contains(@id,'Country')]"));

		Sync.waitElementPresent("xpath", "//select[contains(@id,'Country')]");
		Common.dropdown("xpath", "//select[contains(@id,'Country')]", SelectBy.TEXT, data.get(dataSet).get("Country"));
		
		
		Sync.waitElementPresent("xpath", "//select[contains(@id,'StateOrProvince')]");
		Common.clickElement(By.xpath("//select[contains(@id,'StateOrProvince')]"));
		Thread.sleep(5000);
		Common.dropdown("xpath", "//select[contains(@id,'StateOrProvince')]", SelectBy.TEXT,data.get(dataSet).get("State"));
		
		Sync.waitElementPresent("xpath", "//input[contains(@id,'PostalCode')]");
		Common.textBoxInput("xpath", "//input[contains(@id,'PostalCode')]", data.get(dataSet).get("postcode"));
		
		
		Sync.waitElementPresent("xpath", "//input[contains(@id,'MOBILE')]");
		Common.textBoxInput("xpath", "//input[contains(@id,'MOBILE')]", data.get(dataSet).get("phone"));
		
		Sync.waitElementPresent("xpath", "//span[text()='View All']");
		Common.clickElement(By.xpath("//span[text()='View All']"));
		
		Thread.sleep(5000);
	    List<WebElement> Productselemts=	Common.findElements("xpath", "//div[contains(@class,'nameset')]");
		
	       for(int i=0;i<Productselemts.size();i++){
	    	   
	    	    if(Productselemts.get(i).getAttribute("title").equals( data.get(dataSet).get("Products"))){
	    	    	Productselemts.get(i).click();
	    	    	break;
	    	    }
	    	   
	       }
	
	     //input[contains(@class,'product_quantity')]
		
		
		Sync.waitElementPresent("xpath", "//input[contains(@class,'product_quantity')]");
		Common.textBoxInput("xpath", "//input[contains(@class,'product_quantity')]", data.get(dataSet).get("ProductQuantity"));
		
		
		Sync.waitElementPresent("xpath", "//input[contains(@class,'problem_description')]");
		Common.textBoxInput("xpath", "//input[contains(@class,'problem_description')]", data.get(dataSet).get("ProblemDescription"));
		
		Sync.waitElementPresent("xpath", "//input[contains(@id,'FileInput')]");
		String path=System.getProperty("user.dir")+("\\src\\test\\resources\\TestData\\Hydroflask\\TestScreen.jpg");
		try {
		Common.fileUpLoad("xpath", "//input[contains(@id,'FileInput')]", path);
		}
		catch(Exception e)
		{
			
		}
		expectedResult="No validation errors";
		report.addPassLog(expectedResult,"Enter the warrenty from infromation with out any validation  ",Common.getscreenShotPathforReport("Filling the Warranty from "));
		
		
		Thread.sleep(5000);
		Sync.waitElementPresent("xpath", "//button[contains(@id,'CustomFormSubmit')]");
		Common.clickElement("xpath", "//button[contains(@id,'CustomFormSubmit')]");
		
		//
		Common.actionsKeyPress(Keys.HOME);
		Thread.sleep(6000);
		
		String sucessMessage=Common.getText("xpath", "//body[@id='rn_BlankBody']//h1").trim();
		Assert.assertEquals(sucessMessage, "Your warranty request has been submitted!");
		
		expectedResult="User gets redirected to confirmation page, it includes a reference number and email is sent to email provided. No validation errors.";
		report.addPassLog(expectedResult,"warranty applied  successfull,and redirected to confirmation page",Common.getscreenShotPathforReport("warranty  submitted "));
		
		}
		catch(Exception e)
		{
			report.addFailedLog(expectedResult, "Failed to get Expected results", Common.getscreenShotPathforReport(expectedResult));
		}
		
		}

	
	public void clickContact()throws Exception{
		String expectedResult="User should land on the home page";
		try{
		report.addPassLog(expectedResult,"Successfully landed on the home page",Common.getscreenShotPathforReport("Successfully landed on the home page"));
		Common.actionsKeyPress(Keys.END);
		Sync.waitElementPresent("xpath", "//a[text()='Contact']");
		Common.clickElement("xpath", "//a[text()='Contact']");
		}
		catch(Exception e)
		{
			report.addFailedLog(expectedResult, "Failed to get Expected results", Common.getscreenShotPathforReport(expectedResult));
		}
		
	}
	
	public void contactUsPage(String dataSet) throws Exception{
		clickContact();
     	Thread.sleep(9000);
     	String expectedResult="It should land successfully on the explore/contact page";
     	try{
     	report.addPassLog(expectedResult,"successfully land to contact page ",Common.getscreenShotPathforReport("click contact Us button "));
     	
     	//WebElement activeelemet=Common.findElement("xpath", "//*[@id='HNNEN6W']/div[1]");
  //   
     
     for(int i=0;i<10;i++){
    	 Thread.sleep(5000);
    	 WebElement activeelemet=Common.findElement("xpath", "//*[@id='HNNEN6W']/div[1]");
    	 String className=activeelemet.getAttribute("class");
    	 if(className.contains("active")){
    		 
        	 Common.clickElement("xpath", "//*[@id='HNNEN6W']/div[2]/div[2]/div[1]/ul/li[3]/img[2]");
        	
        	 Common.actionsKeyPress(Keys.PAGE_DOWN);
        	 break;
         }
     }
     
     
    	 Common.switchFrames("xpath", "//iframe[contains(@src,'custhelp')]");
    	 
    	//input[contains(@id,'Emails')]
    	 expectedResult ="Email us form is visible in tab";
         report.addPassLog(expectedResult," Email us form is visible in tab ",Common.getscreenShotPathforReport("Email Us "));
    	 
    	 Sync.waitElementPresent("xpath", "//input[contains(@id,'Emails')]");
  		Common.textBoxInput("xpath", "//input[contains(@id,'Emails')]", data.get(dataSet).get("Email"));
 		
 		Sync.waitElementPresent("xpath", "//input[contains(@id,'First')]");
 		Common.textBoxInput("xpath", "//input[contains(@id,'First')]", data.get(dataSet).get("FirstName"));
 		
 		
 		Sync.waitElementPresent("xpath", "//input[contains(@id,'Last')]");
 		Common.textBoxInput("xpath", "//input[contains(@id,'Last')]", data.get(dataSet).get("LastName"));
 		
 		Sync.waitElementPresent("xpath", "//input[contains(@id,'company')]");
 		Common.textBoxInput("xpath", "//input[contains(@id,'company')]", data.get(dataSet).get("Company"));
 		
 		Sync.waitElementPresent("xpath", "//input[contains(@id,'MOBILE')]");
 		Common.textBoxInput("xpath", "//input[contains(@id,'MOBILE')]", data.get(dataSet).get("phone"));
 		
 		Sync.waitElementPresent("xpath", "//input[contains(@id,'Street')]");
 		Common.textBoxInput("xpath", "//input[contains(@id,'Street')]", data.get(dataSet).get("Street"));
 		
 		Sync.waitElementPresent("xpath", "//input[contains(@id,'City')]");
 		Common.textBoxInput("xpath", "//input[contains(@id,'City')]", data.get(dataSet).get("City"));
 		
 		Sync.waitElementPresent("xpath", "//select[contains(@id,'Country')]");
 		Common.dropdown("xpath", "//select[contains(@id,'Country')]", SelectBy.TEXT, data.get(dataSet).get("Country"));
 		
 		
 		Sync.waitElementPresent("xpath", "//select[contains(@id,'State')]");
 		Common.dropdown("xpath", "//select[contains(@id,'State')]", SelectBy.TEXT, data.get(dataSet).get("State"));
 		
 		
 		Sync.waitElementPresent("xpath", "//input[contains(@id,'PostalCode')]");
 		Common.textBoxInput("xpath", "//input[contains(@id,'PostalCode')]", data.get(dataSet).get("postcode"));
 		
 	
 		Sync.waitElementPresent("xpath", "//select[contains(@id,'SelectObject_lvl1')]");
 		Common.dropdown("xpath", "//select[contains(@id,'SelectObject_lvl1')]",SelectBy.TEXT, data.get(dataSet).get("Howcanwehelp"));
 		
 		
 		Thread.sleep(5000);
 		Sync.waitElementPresent("xpath", "//select[contains(@id,'SelectObject_lvl2')]");
 		Common.dropdown("xpath", "//select[contains(@id,'SelectObject_lvl2')]",SelectBy.TEXT, data.get(dataSet).get("category"));
 		
 		Sync.waitElementPresent("xpath", "//input[contains(@id,'ordernumber')]");
 		Common.textBoxInput("xpath", "//input[contains(@id,'ordernumber')]", data.get(dataSet).get("OrderNumber"));
 		
 		
 		
 		
 		Sync.waitElementPresent("xpath", "//input[contains(@id,'DateTimeUI')]");
 		Common.textBoxInput("xpath", "//input[contains(@id,'DateTimeUI')]", data.get(dataSet).get("OrderDate"));
 	
 		Thread.sleep(5000);
 		Sync.waitElementPresent("xpath", "//input[contains(@id,'billing_name')]");
 		Common.clickElement("xpath", "//input[contains(@id,'billing_name')]");
 		Common.textBoxInput("xpath", "//input[contains(@id,'billing_name')]", data.get(dataSet).get("BillName"));
 		
 		
 		Sync.waitElementPresent("xpath", "//textarea[contains(@id,'TextInputPlaceholder_94')]");
 		Common.clickElement("xpath", "//textarea[contains(@id,'TextInputPlaceholder_94')]");
 		Common.textBoxInput("xpath", "//textarea[contains(@id,'TextInputPlaceholder_94')]", data.get(dataSet).get("Question*"));
 		
 		//input[contains(@id,'DateTimeUI')]
 		
 		//
 	
 		Common.clickElement("xpath", "//button[@id='rn_FormSubmit_95_Button']");
 	
 		
 		Thread.sleep(7000);
 		Common.actionsKeyPress(Keys.PAGE_UP);
 		String Text=Common.getText("xpath", "//div[@class='rn_Container']/h1");
 		
 		Assert.assertEquals(Text, "Your question has been submitted!","Conatct message sucess");
 		expectedResult ="User gets confirmation under the same tab. It includes a reference number and email is sent to email provided. No validation errors.";
 	     
 		report.addPassLog(expectedResult,"User gets confirmation under the same tab ",Common.getscreenShotPathforReport("Confromation Page"));
 		
     	}
     	catch(Exception e)
		{
			report.addFailedLog(expectedResult, "Failed to get Expected results", Common.getscreenShotPathforReport(expectedResult));
		}
    	 
    	 
     }
     
     
     
     
     	
    /* int size=	Common.findElements("xpath","//*[@id='HNNEN6W']/div[2]/div[2]/div[1]/ul/li[3]/img[2]").size();
     System.out.println(size);
    		Sync.waitElementPresent("xpath", "//li[@data-tab-name='E-mail Us']");
    		
    		Common.clickElement("xpath","//li[@data-tab-name='E-mail Us']");
    		Common.clickElement("xpath", "//*[@id='HNNEN6W']/div[2]/div[2]/div[1]/ul/li[3]/img[2]");
    		
    		//*[@id="HNNEN6W"]//ul/li[3]
		
//		Common.clickElement("xpath","//li[@data-tab-name='E-mail Us']");
		
		report.addPassLog(" navigate to contact page ",Common.getscreenShotPathforReport("click email Us button "));
		//Common.switchFrames("xpath", "//iframe[contains(@src,'custhelp')]");
		1
		
		try{
			Common.switchFrames("xpath", "//iframe[contains(@src,'custhelp')]");
             Sync.waitElementPresent("xpath", "//input[contains(@id,'Emails')]");
			Common.textBoxInput("xpath", "//input[contains(@id,'Emails')]", data.get(dataSet).get("Email"));
		}
			catch(Exception e){
				Common.switchToDefault();
				Common.clickElement("xpath","//li[@data-tab-name='E-mail Us']");
				report.addPassLog(" navigate to contact page ",Common.getscreenShotPathforReport("click email Us button "));
				Common.switchFrames("xpath", "//iframe[contains(@src,'custhelp')]");
			}
		
		
		
		Sync.waitElementPresent("xpath", "//input[contains(@id,'Emails')]");
		Common.textBoxInput("xpath", "//input[contains(@id,'Emails')]", data.get(dataSet).get("Email"));
		//
	*/	
	
	
	public void clickProDeal()throws Exception{
		String expectedResult="User should land on the home page";
try{
		report.addPassLog(expectedResult,"Successfully landed on the home page",Common.getscreenShotPathforReport("Successfully landed on the home page"));
		
		Common.actionsKeyPress(Keys.END);
		Sync.waitElementPresent("xpath", "//a[text()='Pro Deal']");
		Common.clickElement("xpath", "//a[text()='Pro Deal']");
}
catch(Exception e)
{
	report.addFailedLog(expectedResult, "Failed to get Expected results", Common.getscreenShotPathforReport(expectedResult));
}
		
	}
	public void ProdelerPage(String dataSet) throws Exception{
		clickProDeal();
		
		String expectedResult="User is redirected to the Apply For Pro Deal page";
		try{
		report.addPassLog(expectedResult,"redirected prodeal page",Common.getscreenShotPathforReport("click the prodeal button"));
		
		try {
		Sync.waitElementPresent("xpath", "//a[@title='Sign in or register']");
		 
		}catch(Exception e)
		{
			clickProDeal();
			Thread.sleep(3000);
		}
		Common.clickElement("xpath", "//a[@title='Sign in or register']");
		
		expectedResult="User is redirected to login page";
		report.addPassLog(expectedResult,"Successfully redirected to login page",Common.getscreenShotPathforReport("click the Sign in or register button"));
		
		Sync.waitElementPresent("id", "email");
		Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
		Sync.waitElementPresent("id", "pass");
		Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
		Sync.waitElementPresent("xpath", "//button[@class='login-page-submit-action']");
		Common.clickElement("xpath", "//button[@class='login-page-submit-action']");
		
		
		Sync.waitElementPresent("xpath", "//a[contains(@class,'pro-deal-action-primary')]");
		Common.clickElement("xpath", "//a[contains(@class,'pro-deal-action-primary')]");
		
		Thread.sleep(3000);
		Common.switchWindows();
		
		
		
		Thread.sleep(3000);
		expectedResult="User is redirected to Pro Deal application page";
		report.addPassLog(expectedResult,"User is redirected to Pro Deal application page",Common.getscreenShotPathforReport("Apply to Pro Deal button"));
		
		Sync.waitElementPresent("id", "first_name");
		Common.textBoxInput("id", "first_name", data.get(dataSet).get("FirstName"));
	
		Sync.waitElementPresent("id", "last_name");
		Common.textBoxInput("id", "last_name", data.get(dataSet).get("LastName"));
		
		
		Sync.waitElementPresent("id", "association");
		Common.textBoxInput("id", "association", data.get(dataSet).get("Company"));
		
		Sync.waitElementPresent("id", "association_email");
		Common.textBoxInput("id", "association_email", data.get(dataSet).get("AssociationEmailAddress"));
		
		
		 Common.actionsKeyPress(Keys.PAGE_DOWN);
		    Thread.sleep(6000);
		     String path=System.getProperty("user.dir")+("\\src\\test\\resources\\TestData\\Hydroflask\\TestScreen.jpg");
			//Sync.waitElementInvisible("xpath", "//input[@id='supporting_document']");
			Common.fileUpLoad("xpath", "//input[@id='supporting_document']", path);
		
		Sync.waitElementPresent("id", "group_id");
		
		System.out.println(data.get(dataSet).get("GropName"));
		Common.dropdown("xpath", "//select[@id='group_id']", SelectBy.TEXT, data.get(dataSet).get("GropName"));
		
		Sync.waitElementPresent("id", "comment");
		Common.textBoxInput("id", "comment",  data.get(dataSet).get("CommetsHydroflask"));
		
		
		Sync.waitElementPresent("xpath", "//button[@title='Submit']");
		Common.clickElement("xpath", "//button[@title='Submit']");
		
		//String text=Common.getText("xpath", "//div[@class='pro-deal-header']/h4");
		
		String text=Common.getText("xpath","//span[@class='base']");
		Assert.assertEquals(text, "Pro Deal Application Complete", "pro Deal application completed");
		
		expectedResult="User gets redirected to confirmation page and email is sent to email provided.";
		report.addPassLog(expectedResult,"User gets redirected to confirmation page",Common.getscreenShotPathforReport("Pro Deal confirmation page"));
		}
		catch(Exception e)
		{
			report.addFailedLog(expectedResult, "Failed to get Expected results", Common.getscreenShotPathforReport(expectedResult));
		}
	}
	public void stayIntouch(String dataSet) throws Exception{
		String expectedResult="User should land on the home page";
	try{
		report.addPassLog(expectedResult,"Successfully landed on the home page",Common.getscreenShotPathforReport("Successfully landed on the home page"));
		
		Common.actionsKeyPress(Keys.END);
		Thread.sleep(5000);
		
		Sync.waitElementPresent("id", "newsletter");
		Common.clickElement("id", "newsletter");
		
		String email=Common.genrateRandomEmail(data.get(dataSet).get("Email"));
		
		Common.textBoxInput("id", "newsletter", email);
		Thread.sleep(5000);
		Common.actionsKeyPress(Keys.ENTER);
		Thread.sleep(3000);
		
		
		
		// Text=Common.getTextBoxInput("xpath", "//input[@id='newsletter']//following::div[1]");
		
		String Text=Common.getText("xpath", "//input[@id='newsletter']//following::div[1]");
		System.out.println(Text);
		
		expectedResult="User gets confirmation message that it was submitted";
		
		report.addPassLog(expectedResult,"confirmation message that it was submitted",Common.getscreenShotPathforReport(Text));
	}
	catch(Exception e)
	{
		report.addFailedLog(expectedResult, "Failed to get Expected results", Common.getscreenShotPathforReport(expectedResult));
	}
		//Common.clickElement("xpath", "//div[contains(@class,'sign-button')]");
		//Thread.sleep(5000);
	   // System.out.println(Common.getText("class", "mage-success"));
		
		
	}
	public void forgetpassword(String dataSet) throws Exception{
		navigateMyAccount();
		String expectedResult="Forgot password pop up is displayed to customer";
		try{
		Sync.waitElementPresent("xpath", "//a[contains(@class,'forgot-password')]");
		Common.clickElement("xpath","//a[contains(@class,'forgot-password')]");
		
		
		
		
		
		
		Sync.waitElementPresent("xpath", "//input[contains(@id,'forgot-password-email')]");
		report.addPassLog(expectedResult,"Successfully Forgot password pop up is displayed to customer",Common.getscreenShotPathforReport("foget password button"));
		Common.textBoxInput("xpath", "//input[contains(@id,'forgot-password-email')]",data.get(dataSet).get("Email"));
		
		
		Common.clickElement("xpath", "//button[contains(text(),'Reset my Password')]");
		
		
		expectedResult="Confirmation message is presented to customer saying if there is an associated account they would get email with instructions. Email is sent to customer.";
		report.addPassLog(expectedResult,"user get confirmatin message",Common.getscreenShotPathforReport("reset my passowrd button"));
		//Common.actionsKeyPress(Keys.ESCAPE);
		}
		catch(Exception e)
		{
			report.addFailedLog(expectedResult, "Failed to get Expected results", Common.getscreenShotPathforReport(expectedResult));
		}
	}
	
    public void Customize_Bottle() throws Exception{
        
    	String expectedResult="User should land on the home page";
    	try{
    	report.addPassLog(expectedResult,"Successfully landed on the home page",Common.getscreenShotPathforReport("Successfully landed on the home page"));
    	
		Thread.sleep(8000);

    	Sync.waitElementPresent("xpath","//ul[@class='megamenu-list']/li[2]/div[1]/button");
        Thread.sleep(4000);

    	Common.mouseOverClick("xpath", "//ul[@class='megamenu-list']/li[2]/div[1]/button");
    	
    	Sync.waitElementPresent("xpath", "//span[contains(text(),'Create Yours Now')]");
        Thread.sleep(4000);
        Common.clickElement("xpath", "//span[contains(text(),'Create Yours Now')]");
    	
    	
        Thread.sleep(5000);
        expectedResult= "It should land successfully on my-hydro-landing page";
        report.addPassLog(expectedResult," successfully land  on my-hydro-landing page ",Common.getscreenShotPathforReport("on my-hydro-landing page"));
    	
        
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Sync.waitElementPresent("xpath", "//div[text()='Standard Mouth Bottle']/following::a[1]");
		WebElement linkEnabel=Common.findElementBy("xpath", "//div[text()='Standard Mouth Bottle']/following::a[1]");
		
      /*  Thread.sleep(5000);

    	Common.mouseOverClick("xpath", "//div[text()='Standard Mouth Bottle']/following::a[1]");*/
		
		
		for(int i=1;i<10;i++){
    		Thread.sleep(4000);
    		
    		
    		String attribute=linkEnabel.getAttribute("href");
    		attribute.length();
    		System.out.println(attribute.length());
    		if(attribute.contains("hydro")){
    			System.out.println(attribute+" IN F");
    			Sync.waitElementPresent("xpath", "//div[text()='Standard Mouth Bottle']/following::a[1]");
    			Common.clickElement("xpath", "//div[text()='Standard Mouth Bottle']/following::a[1]");
    			break;
    		}
    		
    	}
    	
		
		
		


		expectedResult="It should land successfully on the my hydro configurator";
    
    	report.addPassLog(expectedResult,"opean the  my hydro configurator page",Common.getscreenShotPathforReport("customize bottle page"));
    	
    	
    	Thread.sleep(6000);
    	/*Common.actionsKeyPress(Keys.PAGE_DOWN);
    	Sync.waitElementPresent("xpath", "//span[text()='Add To Cart']");
    	Common.clickElement("xpath", "//span[text()='Add To Cart']");
    	
    	report.addPassLog("add the product the cart",Common.getscreenShotPathforReport("click to add to Card"));*/
    	
    	Sync.waitElementPresent("xpath", "//div[@id='fc-nav-flyout-header-80251']");
    	Common.clickElement("xpath", "//div[@id='fc-nav-flyout-header-80251']");
    	
    	
    	Sync.waitElementPresent("xpath", "//div[contains(@aria-label,'24 oz Our')]");
    	Common.clickElement("xpath", "//div[contains(@aria-label,'24 oz Our')]");
    	
    	
    	Sync.waitElementPresent("xpath", "//div[@id='fc-nav-flyout-header-80262']");
    	Common.clickElement("xpath", "//div[@id='fc-nav-flyout-header-80262']");
    	
    	Sync.waitElementPresent("xpath", "//fieldset[@id='fc-ca-swatch-80262-fieldset']//div[5]/span");
    	Common.clickElement("xpath", "//fieldset[@id='fc-ca-swatch-80262-fieldset']//div[5]/span");
    	
    	
    	Sync.waitElementPresent("xpath", "//div[@id='fc-nav-flyout-header-80263']");
    	Common.clickElement("xpath", "//div[@id='fc-nav-flyout-header-80263']");
    	
    	Sync.waitElementPresent("xpath", "//fieldset[@id='fc-ca-swatch-80263-fieldset']//div[5]/span");
    	Common.clickElement("xpath", "//fieldset[@id='fc-ca-swatch-80263-fieldset']//div[5]/span");
    	
    	
      	Sync.waitElementPresent("xpath", "//div[@id='fc-nav-flyout-header-80268']");
    	Common.clickElement("xpath", "//div[@id='fc-nav-flyout-header-80268']");
    	
    	Sync.waitElementPresent("xpath", "//fieldset[@id='fc-ca-swatch-80268-fieldset']//div[5]/span");
    	Common.clickElement("xpath", "//fieldset[@id='fc-ca-swatch-80268-fieldset']//div[5]/span");
    	
    
    	
      	Sync.waitElementPresent("xpath", "//div[@id='fc-nav-flyout-header-80270']");
    	Common.clickElement("xpath", "//div[@id='fc-nav-flyout-header-80270']");
    	
    	Sync.waitElementPresent("xpath", "//fieldset[@id='fc-ca-swatch-80270-fieldset']//div[5]/span");
    	Common.clickElement("xpath", "//fieldset[@id='fc-ca-swatch-80270-fieldset']//div[5]/span");
    	expectedResult="chenage the bottle configuration size and colo";
    	report.addPassLog(expectedResult, "Successfully chenage the bottle configuration size and color",Common.getscreenShotPathforReport("changing Configurations"));
    	
    	Sync.waitElementPresent("xpath", "//span[text()='Add To Cart']");
    	Common.clickElement("xpath", "//span[text()='Add To Cart']");
    	
    	Thread.sleep(6000);
    	expectedResult="Item should be added to cart and user taken to cart page";
    	report.addPassLog(expectedResult,"expectedResult the product the cart",Common.getscreenShotPathforReport("product page "));
    	checkOut();
    	}
    	catch(Exception e)
		{
			report.addFailedLog(expectedResult, "Failed to get Expected results", Common.getscreenShotPathforReport(expectedResult));
		}
    	/*Sync.waitElementPresent("xpath", "//button[@id='top-cart-btn-checkout']");
    	Common.clickElement("xpath", "//button[@id='top-cart-btn-checkout']");
    	report.addPassLog("navigating  to check out page ",Common.getscreenShotPathforReport("click to check out"));*/
    	}
    
    
    public void shop_bottles() throws Exception{
    	Sync.waitElementPresent("xpath", "//ul[@class='megamenu-list']/li[ 1]/div[1]/button");
    	Common.clickElement("xpath", "//ul[@class='megamenu-list']/li[ 1]/div[1]/button");
    	
    	Sync.waitElementPresent("xpath", "//ul[@class='megamenu-list-ancestor']//a[contains(text(),'Bottles')]");
    	Common.clickElement("xpath","//ul[@class='megamenu-list-ancestor']//a[contains(text(),'Bottles')]");
    	 }
public void review_bottles(String dataSet) throws Exception{
	    String expectedResult="User should land on the home page";
	    try{
        report.addPassLog(expectedResult,"Successfully landed on the home page",Common.getscreenShotPathforReport("Successfully landed on the home page"));
		Thread.sleep(8000);
		
		Sync.waitElementClickable("xpath", "//ul[@class='megamenu-list']/li[1]/div[1]");
		
		Thread.sleep(4000);
		Common.mouseOverClick("xpath", "//ul[@class='megamenu-list']/li[1]/div[1]/button");
	
		//Common.clickElement("css", "ul.megamenu-list > li:nth-of-type(1) > div:nth-of-type(1) > button");
		Thread.sleep(3000);
		expectedResult="It Shoud lands on the Product Listing Page";
		try {
		Common.mouseOver("xpath", "//a[contains(text(),'Bottles')]");
		
		
		
		}catch (Exception e) {
			
			Common.clickElement("xpath", "//ul[@class='megamenu-list']/li[1]/div[1]/button");
			Thread.sleep(1000);
		    Common.clickElement("xpath", "//a[contains(text(),'Bottles')]");
		
		}
		Thread.sleep(4000);
		report.addPassLog(expectedResult,"lands on the Product Listing Page",Common.getscreenShotPathforReport(expectedResult));
		
		
		Common.actionsKeyPress(Keys.PAGE_DOWN);
				
    	Sync.waitElementPresent("xpath", "//img[contains(@src,'hibiscus')]");
		Common.clickElement("xpath","//img[contains(@src,'hibiscus')]");
		
		//Thread.sleep(4000);
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Common.actionsKeyPress(Keys.PAGE_DOWN);

		
		
		
		
		
		Thread.sleep(8000);
		expectedResult="Click on  product image/ product name, it should  be redirect to the product details page";
		report.addPassLog(expectedResult," redirect to the product details page",Common.getscreenShotPathforReport(" product details page"));
    	Sync.waitElementPresent("id", "tab-title-reviews");
        Common.clickElement("id", "tab-title-reviews");
        
        expectedResult ="It should navigated to review options.";
       
        
        Thread.sleep(4000);
        report.addPassLog(expectedResult,"Review option selected",Common.getscreenShotPathforReport("review button"));
        Sync.waitElementPresent("xpath", "//button[contains(@class,'write-review')]");
        Common.clickElement("xpath", "//button[contains(@class,'write-review')]");
        
      //  report.addPassLog("click write review button ",Common.getscreenShotPathforReport("write review button"));
        Thread.sleep(4000);
        expectedResult ="It should shows My Review Pop-up";
        report.addPassLog(expectedResult,"Review pop-up open ",Common.getscreenShotPathforReport("Review Page"));
        overallRating(data.get(dataSet).get("OverallRating"));
        
      
        
        Sync.waitElementPresent("id", "bv-text-field-title");
        Common.textBoxInput("id", "bv-text-field-title", data.get(dataSet).get("Reviewtitle"));
        
        
        Common.textBoxInput("xpath", "//textarea[contains(@id,'reviewtext')]", data.get(dataSet).get("ReviewDescription"));
        
        
        
        Wouldyourecommendthiproduct(data.get(dataSet).get("recommendthiproduct"));
        
        //nicName
        Sync.waitElementPresent("xpath", "//input[contains(@id,'usernickname')]");
        Common.textBoxInput("xpath", "//input[contains(@id,'usernickname')]", data.get(dataSet).get("Nickname"));
      
        //User location
        Sync.waitElementPresent("xpath", "//input[contains(@id,'userlocation')]");
        Common.textBoxInput("xpath", "//input[contains(@id,'userlocation')]",data.get(dataSet).get("Location"));
        
        //user email
        
        Sync.waitElementPresent("xpath", "//input[contains(@id,'hostedauthentication')]");
        Common.textBoxInput("xpath","//input[contains(@id,'hostedauthentication')]",data.get(dataSet).get("Email"));    
        selectAgeforReview(Integer.valueOf(data.get(dataSet).get("Age")));
        selectGenderforReview(data.get(dataSet).get("gender"));
        qualityof_ProductRating(data.get(dataSet).get("ProductRating"));
        valueof_productRating(data.get(dataSet).get("valueofProductRating"));
        recommendHydroFlask( Integer.valueOf(data.get(dataSet).get("HydroFlaskOverallRating")));
        
        
        Common.textBoxInput("xpath", "//textarea[contains(@id,'netpromotercomment')]", data.get(dataSet).get("Pleasetelluswhy"));
        
        
        Common.clickElement("xpath", "//button[contains(@class,'form-actions-submit')]");
        
        
        expectedResult  ="Review pop-up page should display Overall Rating,Review Title,Review, Nickname, Email, age,gender,quality rating and value rating of the product in the My Review pop-up";
        report.addPassLog(expectedResult,"Writting  and submit the review ",Common.getscreenShotPathforReport("submit the review "));
        String sucesstext=    Common.getText("xpath", "//span[contains(@class,'bv-submission-text')]");
        
        Assert.assertEquals(sucesstext, "Your review was submitted!", "submit the Review");
        
        expectedResult="Click on post review button, it shouldshows Pop-up with text Your review was Submitted!";
        
        report.addPassLog(expectedResult,"Your review was Submitted",Common.getscreenShotPathforReport("Review"));
        
	    }
	    catch(Exception e)
		{
			report.addFailedLog(expectedResult, "Failed to get Expected results", Common.getscreenShotPathforReport(expectedResult));
		}
         
        
	}

   
   

	
     public void  recommendHydroFlask(int number){
	
	 int numbers=number+1;
	 Common.clickElement("xpath", "//span[contains(@class,'netpromoterscore-wrapper')]//li["+numbers+"]");
	}

	public void overallRating(String OverallRating) throws Exception{
		Thread.sleep(3000);
		System.out.println(OverallRating);
		switch (OverallRating) {
	
		case "poor":
			Sync.waitElementPresent("xpath", "//a[contains(@id,'rating-1')]");
			 Common.clickElement("xpath", "//a[contains(@id,'rating-1')]");
			break;
		case "Fair":
			Sync.waitElementPresent("xpath", "//a[contains(@id,'rating-2')]");
			 Common.clickElement("xpath", "//a[contains(@id,'rating-2')]");
			break;
		case "Average":
			Sync.waitElementPresent("xpath", "//a[contains(@id,'rating-3')]");
			 Common.clickElement("xpath", "//a[contains(@id,'rating-3')]");;
			 System.out.println("clicked");
			 
			break;
		case "Good":
			 Sync.waitElementPresent("xpath", "//a[contains(@id,'rating-4')]");
			 Common.clickElement("xpath", "//a[contains(@id,'rating-4')]");
			break;
			
		case "Excellent":
			 Sync.waitElementPresent("xpath", "//a[contains(@id,'rating-5')]");
			 Common.clickElement("xpath", "//a[contains(@id,'rating-5')]");
			break;
			
		
	}
			}
	
	public void qualityof_ProductRating(String ProductRating) throws Exception{
        
		
		switch (ProductRating) {
		
		case "poor":
			Sync.waitElementPresent("xpath", "//a[contains(@id,'Quality-1')]");
			 Common.clickElement("xpath", "//a[contains(@id,'Quality-1')]");
			break;
		case "Fair":
			Sync.waitElementPresent("xpath", "//a[contains(@id,'Quality-2')]");
			 Common.clickElement("xpath", "//a[contains(@id,'rating-2')]");
			break;
		case "Average":
			Sync.waitElementPresent("xpath", "//a[contains(@id,'Quality-3')]");
			 Common.clickElement("xpath", "//a[contains(@id,'Quality-3')]");;
			break;
		case "Good":
			 Sync.waitElementPresent("xpath", "//a[contains(@id,'Quality-4')]");
			 Common.clickElement("xpath", "//a[contains(@id,'Quality-4')]");
			break;
			
		case "Excellent":
			 Sync.waitElementPresent("xpath", "//a[contains(@id,'Quality-5')]");
			 Common.clickElement("xpath", "//a[contains(@id,'Quality-5')]");
			break;
		}
		
	}
		
	
	
		public void valueof_productRating(String valueofProductRating) throws Exception{
	        
			
			switch (valueofProductRating) {
			
			case "poor":
				Sync.waitElementPresent("xpath", "//a[contains(@id,'rating_Value-1')]");
				 Common.clickElement("xpath", "//a[contains(@id,'rating_Value-1')]");
				break;
			case "Fair":
				Sync.waitElementPresent("xpath", "//a[contains(@id,'rating_Value-2')]");
				 Common.clickElement("xpath", "//a[contains(@id,'rating_Value-2')]");
				break;
			case "Average":
				Sync.waitElementPresent("xpath", "//a[contains(@id,'rating_Value-3')]");
				 Common.clickElement("xpath", "//a[contains(@id,'rating_Value-3')]");;
				break;
			case "Good":
				 Sync.waitElementPresent("xpath", "//a[contains(@id,'rating_Value-4')]");
				 Common.clickElement("xpath", "//a[contains(@id,'rating_Value-4')]");
				break;
				
			case "Excellent":
				 Sync.waitElementPresent("xpath", "//a[contains(@id,'rating_Value-5')]");
				 Common.clickElement("xpath", "//a[contains(@id,'rating_Value-5')]");
				break;
				
			
		}
			
			}
	public void selectAgeforReview(int Age) throws Exception{
		
		
		 		if(Age<=17){
		 			 Sync.waitElementPresent("id", "bv-select-field-contextdatavalue_Age");

		 			Common.dropdown("id", "bv-select-field-contextdatavalue_Age", SelectBy.VALUE, "17orUnder");
		         }
				
		 	   else if(Age<=24&&Age>17){
		 			 Sync.waitElementPresent("id", "bv-select-field-contextdatavalue_Age");
	               Common.dropdown("id", "bv-select-field-contextdatavalue_Age", SelectBy.VALUE, "18to24");
	           }
              else if(Age>=25&&Age<=34){
		 			 Sync.waitElementPresent("id", "bv-select-field-contextdatavalue_Age");
            	  Common.dropdown("id", "bv-select-field-contextdatavalue_Age", SelectBy.VALUE, "25to34");
                }

              else if(Age>=35&&Age<=44){
		 			 Sync.waitElementPresent("id", "bv-select-field-contextdatavalue_Age");
            	  Common.dropdown("id", "bv-select-field-contextdatavalue_Age", SelectBy.VALUE, "35to44");
               }
		
              else if(Age>=45&&Age<=54){
		 			 Sync.waitElementPresent("id", "bv-select-field-contextdatavalue_Age");
            	  Common.dropdown("id", "bv-select-field-contextdatavalue_Age", SelectBy.VALUE, "45to54");
              }
              else if(Age>=55&&Age<=64){
		 			 Sync.waitElementPresent("id", "bv-select-field-contextdatavalue_Age");
            	  Common.dropdown("id", "bv-select-field-contextdatavalue_Age", SelectBy.VALUE, "55to64");
              }
              else if(Age<=65){
		 			 Sync.waitElementPresent("id", "bv-select-field-contextdatavalue_Age");
            	  Common.dropdown("id", "bv-select-field-contextdatavalue_Age", SelectBy.VALUE, "65orOver");
              }
		  }
	
    public void selectGenderforReview(String gender) throws Exception{
    	
    	if(gender.equals("Male")){
			 Sync.waitElementPresent("id", "bv-select-field-contextdatavalue_Gender");
    		Common.dropdown("id", "bv-select-field-contextdatavalue_Gender", SelectBy.VALUE, "Male");
    	}
    	else if(gender.equals("Female")){
    		Sync.waitElementPresent("id", "bv-select-field-contextdatavalue_Gender");
    		Common.dropdown("id", "bv-select-field-contextdatavalue_Gender", SelectBy.VALUE, "Female");
    	}
    	 
    	 
    	
    }
	
	
	public void  Wouldyourecommendthiproduct(String recommendthiproduct) throws Exception{
		
		if(recommendthiproduct.equals("Yes")){
			Sync.waitElementPresent("xpath", "//label[contains(@id,'isrecommended-true')]");
			Common.clickElement("xpath", "//label[contains(@id,'isrecommended-true')]");
			
		
		}
		else if(recommendthiproduct.equals("No")){
			
		
			Sync.waitElementPresent("xpath", "//label[contains(@id,'isrecommended-false')]");
		Common.clickElement("xpath", "//label[contains(@id,'isrecommended-false')]");
	}}
	
	public void logOut() throws Exception{
		
		Thread.sleep(5000);
		String expectedResult="It should land on the signout page and redireted to homepage after 5 seconds.";
		try{
		Sync.waitElementPresent("xpath", "//li[contains(@class,'account-component')]/a");
		Common.mouseOverClick("xpath", "//li[contains(@class,'account-component')]/a");
		
		Sync.waitElementPresent("xpath", "//ul[contains(@class,'component-content')]/li[2]/a");
		Common.mouseOverClick("xpath", "//ul[contains(@class,'component-content')]/li[2]/a");
		
	    
		
		report.addPassLog(expectedResult,"Log out from aplication  ",Common.getscreenShotPathforReport("logOut"));
		}
		catch(Exception e)
		{
			report.addFailedLog(expectedResult, "Failed to get Expected results", Common.getscreenShotPathforReport(expectedResult));
		}
	}
	public void promationCode(String dataSet) throws Exception{
		
		String expectedResult="It should opens textbox input.";
		try{
		Sync.waitElementClickable("id", "discount-accordion");
		Common.clickElement("id", "discount-accordion");
		
		Sync.waitElementPresent("id","discount-code");
	
		
		Common.textBoxInput("id", "discount-code", data.get(dataSet).get("Promationcode"));
		report.addPassLog(expectedResult,"it open the discount input box  ",Common.getscreenShotPathforReport("click the sicount box"));
		
		
		expectedResult="See the fields populated with the"+data.get(dataSet).get("Promationcode")+"data.";
		report.addPassLog(expectedResult,"Enter data is populated ",Common.getscreenShotPathforReport(expectedResult));
		
		Sync.waitElementClickable("xpath", "//button[@class='action action-apply']");
		Common.clickElement("xpath", "//button[@class='action action-apply']");
		
		Thread.sleep(4000);
		expectedResult="It should apply discount on your price.If user enters invalid promocode it should display coupon code is not valid message.";
		
		
		String codetext=Common.getText("xpath", "//span[@class='rule-coupon-code']");
		Assert.assertEquals( data.get(dataSet).get("Promationcode"), codetext,"Promation code is not applied ");
		report.addPassLog(expectedResult,"promotion code working as expected",Common.getscreenShotPathforReport("pomotion code"));
		}
		catch(Exception e)
		{
			report.addFailedLog(expectedResult, "Failed to get Expected results", Common.getscreenShotPathforReport(expectedResult));
		}
		
		}
	
	
	

	
	public void changeAddressIn_AddressBook(String dataSet) throws Exception{
		try{
		Sync.waitElementClickable("xpath", "//a[contains(text(),'Address Book')]");
		Common.clickElementStale("xpath", "//a[contains(text(),'Address Book')]");
		report.addPassLog("click the my address book page ",Common.getscreenShotPathforReport("my address  book option"));
        String PageTitle=	Common.getText("xpath", "//h1[@class='page-title']/span");
        if(PageTitle.contains("New")){
		Common.textBoxInput("xpath", "//input[@id='firstname']", data.get(dataSet).get("FirstName"));
		Common.textBoxInput("xpath", "//input[@id='lastname']", data.get(dataSet).get("LastName"));
		Common.textBoxInput("xpath", "//input[@id='street[0]']", data.get(dataSet).get("Street"));
		try {
			Common.dropdown("id", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));}
			catch (ElementClickInterceptedException e) {
				// TODO: handle exception
				Thread.sleep(3000);
				Common.dropdown("id", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			}
			Thread.sleep(2000);
			Common.textBoxInputClear("id", "postcode");
			Common.textBoxInput("id", "zip", data.get(dataSet).get("postcode"));
			Common.textBoxInput("id", "telephone", data.get(dataSet).get("phone"));
			Common.clickElement("xpath", "//button[@title='Save Address']");
			report.addPassLog("Filled the shipping address for myaccount page",Common.getscreenShotPathforReport("fill the shipping address myaccount"));
     	}
		
	 else{
		Common.clickElementStale("xpath", "//a[contains(text(),'Change Billing Address')]");
		report.addPassLog("click update button myaccount page",Common.getscreenShotPathforReport("edit the shipping address myaccount"));
		Common.textBoxInput("xpath", "//input[@id='firstname']", data.get(dataSet).get("FirstName"));
		Common.textBoxInput("xpath", "//input[@id='lastname']", data.get(dataSet).get("LastName"));
		Common.textBoxInput("xpath", "//input[@id='street_1']", data.get(dataSet).get("Street"));
		try {
			Common.dropdown("id", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));}
			catch (ElementClickInterceptedException e) {
				// TODO: handle exception
				Thread.sleep(3000);
				Common.dropdown("id", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			}
			Thread.sleep(2000);
			//Common.textBoxInputClear("id", "postcode");
			Common.textBoxInput("id", "zip", data.get(dataSet).get("postcode"));
			Common.textBoxInput("id", "telephone", data.get(dataSet).get("phone"));
			Common.clickElement("xpath", "//button[@title='Save Address']");
			Thread.sleep(6000);
			
			String Sucess=Common.getText("xpath","//div[contains(@class,'message-success')]/div");
			Assert.assertEquals(Sucess, "You saved the address.", "Adress is saved");
			
			report.addPassLog("Filled the shipping address for myaccount page",Common.getscreenShotPathforReport("fill the shipping address myaccount"));
            }
        
		}
		catch(Exception e)
		{
			//report.addFailedLog(expectedResult, "Failed to get Expected results", Common.getscreenShotPathforReport(expectedResult));
		}
	    }
	
	
	public void SearchProduct(String dataSet) throws Exception{
		
		Sync.waitElementClickable("xpath", "//input[@id='search']");
		Common.clickElement("xpath", "//input[@id='search']");
		
		Common.textBoxInput("xpath", "//input[@id='search']",data.get(dataSet).get("sampleproduct"));
		
		
		Common.clickElement("xpath", "//a[contains(text(),'"+data.get(dataSet).get("sampleproduct")+"')]");
		report.addPassLog("Clicking search button ",Common.getscreenShotPathforReport("search button"));
		
		
		
		
		
		//h4[text()='12 oz Cooler Cup']
		
		
		
		
	}
	
	public void myAccountInformation(){
		Sync.waitElementClickable("xpath", "//a[contains(text(),'Account Information')]");
	    Common.mouseOverClick("xpath", "//a[contains(text(),'Account Information')]");
	    report.addPassLog("click the my account information button ",Common.getscreenShotPathforReport("account information"));
		
	}
	
	public void edit_Accountinfo(String dataSet) throws Exception{
		
		 CreateNewAccount(dataSet);
		 myAccountInformation();
		 Thread.sleep(5000);
		 Sync.waitElementClickable("xpath", "//button[@data-role='change-email']");
		 
		 Thread.sleep(4000);
		 Common.clickElement("xpath", "//button[@data-role='change-email']");
		 report.addPassLog("click change email link ",Common.getscreenShotPathforReport("change email"));
		 Thread.sleep(4000);
		 Sync.waitElementClickable("xpath", "//button[@data-role='change-password']");
		 Common.clickElement("xpath", "//button[@data-role='change-password']");
		 report.addPassLog("click change password link ",Common.getscreenShotPathforReport("change password"));
		 Thread.sleep(4000);
		 
		 String change_Email=Common.genrateRandomEmail(data.get(dataSet).get("NewEmail"));
		Common.textBoxInput("xpath", "//input[@id='email']", change_Email);
		
		Common.textBoxInput("xpath", "//input[@id='current-password']", data.get(dataSet).get("Password"));
		
		Common.textBoxInput("xpath", "//input[@id='password']", data.get(dataSet).get("Newpassword"));
		
		Common.textBoxInput("xpath", "//input[@id='password-confirmation']", data.get(dataSet).get("Newpassword"));
		
		 report.addPassLog("new email password Enter ",Common.getscreenShotPathforReport("change Email password Email"));
		
		Common.clickElement("xpath", "//button[contains(text(),'Save')]");
		
		
		//logOut();
		
		
		Common.textBoxInput("id", "email",change_Email);
		Common.textBoxInput("id", "pass",data.get(dataSet).get("Newpassword"));
		report.addPassLog("enter new email password",Common.getscreenShotPathforReport("Customer Login"));
		Common.clickElement("xpath", "//button[@class='login-page-submit-action']");
		Thread.sleep(4000);
        Sync.waitElementPresent("xpath", "//span[@data-ui-id='page-title-wrapper']");
		Assert.assertEquals(Common.getText("xpath", "//span[@data-ui-id='page-title-wrapper']"), "My Account");
		report.addPassLog("Logged in the application and customer is redirected to 'My Account' page",Common.getscreenShotPathforReport("Myaccount"));
		}
	
	public void minicart() throws Exception
	{
		Common.clickElement("xpath", "//a[@aria-label='minicart']");
		Thread.sleep(2000);
		Common.clickElement("xpath", "//a[contains(@class,'viewcart')]");
		//div[contains(@class,'no-edit')]/a[2]
		//Sync.waitElementVisible("className", "checkout-step-title");
		//report.addPassLog("Clicked the checkout button",Common.getscreenShotPathforReport("checked out page"));
	}
	
	
	public void new_arrivals(String dataSet){
		
		
		Common.oppenURL(data.get(dataSet).get("newarrivals"));
		String bannerText=Common.getText("xpath", "//div[@class='description-banner-title']");
		Assert.assertTrue(Common.getCurrentURL().contains("https"));
		report.addPassLog("this url contains https",Common.getscreenShotPathforReport("https"));
		Assert.assertTrue(bannerText.equals("New Arrivals"));
		report.addPassLog("Give URL Contains Expected Template",Common.getscreenShotPathforReport("Template checking"));
		Assert.assertTrue(Common.getPageTitle().contains(bannerText));
		report.addPassLog("Give URL Contains Expected title",Common.getscreenShotPathforReport("title checking"));
		
	}
	public void trail_Series(String dataSet){
		Common.oppenURL(data.get(dataSet).get("trailseries"));
		String bannerText=Common.getText("xpath", "//div[@class='description-banner-title']");
		Assert.assertTrue(Common.getCurrentURL().contains("https"));
		report.addPassLog("this url contains https",Common.getscreenShotPathforReport("https"));
		Assert.assertTrue(bannerText.equals("Less weight. More miles."));
		report.addPassLog("Give URL Contains Expected Template",Common.getscreenShotPathforReport("Template checking"));
		//System.out.println(Common.getPageTitle());
		Assert.assertTrue(Common.getPageTitle().contains("Hydro Flask | Trail Series"));
		
		report.addPassLog("Give URL Contains Expected title",Common.getscreenShotPathforReport("title checking"));
	}
	
	public void limited_edition(String dataSet){
		Common.oppenURL(data.get(dataSet).get("limitededition"));
		String bannerText=Common.getText("xpath", "//div[@class='description-banner-title']");
		Assert.assertTrue(Common.getCurrentURL().contains("https"));
		report.addPassLog("this url contains https",Common.getscreenShotPathforReport("https"));
		Assert.assertTrue(bannerText.equals("Limited Edition collections."));
		report.addPassLog("Give URL Contains Expected Template",Common.getscreenShotPathforReport("Template checking"));
		System.out.println(Common.getPageTitle());
		Assert.assertTrue(Common.getPageTitle().contains("Limited Edition Product | Hydro Flask"));
		
		report.addPassLog("Give URL Contains Expected title",Common.getscreenShotPathforReport("title checking"));
	}
	
	
	
	
	
	public void urls(String URL){
		Common.oppenURL(URL);
		String current=Common.getCurrentURL();
		String pagetitle =Common.getPageTitle();
		System.out.println(current+"---- present url");
		System.out.println(pagetitle+"--------page title");
	}

	public  HydroHelper(String datafile)
	{
		excelData=new ExcelReader(datafile);
		data=excelData.getExcelValue();
		this.data=data;
		if(Utilities.TestListener.report==null)
		{
			report=new ExtenantReportUtils("Hydro");
			report.createTestcase("HydroTestCases");
		}else{
			this.report=Utilities.TestListener.report;
		}
	}
}
