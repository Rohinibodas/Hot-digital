package TestComponent.RevlonUk;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import TestLib.Automation_properties;
import TestLib.Common;
import TestLib.Sync;
import Utilities.ExcelReader;
import Utilities.ExtenantReportUtils;
import Utilities.Utils;

public class RevlonUKMobileHelper {
	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data=new HashMap<>();
	static ExtenantReportUtils report;
	static Automation_properties automation_properties = Automation_properties.getInstance();
	
	public  RevlonUKMobileHelper(String datafile)
	{
		excelData=new ExcelReader(datafile);
		data=excelData.getExcelValue();
		this.data=data;
		if(Utilities.TestListener.report==null)
		{
			report=new ExtenantReportUtils("RevlonUK");
			report.createTestcase("RevlonUKTestCases");
		}else{
			this.report=Utilities.TestListener.report;
		}
	}
	
	public void acceptPrivecy()
	{
		Common.clickElementStale("xpath", "//button[text()='AGREE & PROCEED']");
	}
	public void slider() throws InterruptedException
	{
		try {
			Thread.sleep(3000);
			if(Common.isElementDisplayed("xpath", "//span[contains(text(),'WE VALUE YOUR PRIVACY')]")) {
				acceptPrivecy();
			}
			Thread.sleep(2000);
			Sync.waitElementClickable(30, By.className("nav-toggle"));
			Common.clickElementStale("xpath", "//span[@class='action nav-toggle']/small[2]");
		}catch(Exception |Error e)
		{
			e.printStackTrace();
			Assert.fail();
		}
		
	}
	
	public void Loginpage() throws InterruptedException
	{
		Thread.sleep(5000);
		Sync.waitElementClickable(30, By.xpath("//a[@href='#store.links']"));
		Common.clickElement("xpath", "//a[@href='#store.links']");
		
		Thread.sleep(1000);
		
		Sync.waitElementClickable(30, By.xpath("//*[@id='store.links']/ul/li[2]/a"));
		Common.clickElement("xpath", "//*[@id='store.links']/ul/li[2]/a");
	}
	
	public void MyAccount() throws InterruptedException
	{
		Thread.sleep(5000);
		Sync.waitElementClickable(30, By.xpath("//a[@href='#store.links']"));
		Common.clickElement("xpath", "//a[@href='#store.links']");
		
		Thread.sleep(1000);
		
		Sync.waitElementClickable(30, By.xpath("//*[@id='store.links']/ul/li[3]/a"));
		Common.clickElement("xpath", "//*[@id='store.links']/ul/li[3]/a");
	}
	public void Accountcreation() throws InterruptedException
	{
		Thread.sleep(5000);
		Sync.waitElementClickable(30, By.xpath("//a[@href='#store.links']"));
		Common.clickElement("xpath", "//a[@href='#store.links']");
		
		Thread.sleep(1000);
		
		Sync.waitElementClickable(30, By.xpath("//*[@id='store.links']/ul/li[2]/a"));
		Common.clickElement("xpath", "//*[@id='store.links']/ul/li[3]/a");
	}
	
	public void CreateNewAccount(String dataSet) throws Exception
	{
		String expectedResult="Account Creation of User with valid details";
		try {
			Thread.sleep(3000);
			Common.textBoxInput("id", "firstname", data.get(dataSet).get("FirstName").toString());
			Common.textBoxInput("id", "lastname", data.get(dataSet).get("LastName").toString());
			Common.actionsKeyPress(Keys.ARROW_DOWN);
			Common.textBoxInput("id", "email_address", Utils.getEmailid());
			Common.textBoxInput("id", "password", data.get(dataSet).get("Password").toString());
			Common.textBoxInput("id", "password-confirmation", data.get(dataSet).get("Password").toString());
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(4000);
			Common.clickElement("xpath", "//button[@title='Create an Account']");
			Thread.sleep(10000);
			String s=Common.getText("xpath", "//span[contains(text(),'My Account')]");
			Assert.assertEquals(s, "My Account");
			report.addPassLog(expectedResult, "Should display My Account Page with user details", "My Account Page with user details displayed successfully", Common.getscreenShotPathforReport("Account Creation success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display My Account Page with user details", "My Account Page with user details not displayed", Common.getscreenShotPathforReport("Account Creation Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}	
	
	public void loginRevlonUK(String dataSet) throws Exception
	{
		
		String expectedResult="Land on login page and able to login with credentials";
		try {
			int i=0;
			do{  
				Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
				Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
				Common.scrollIntoView("xpath", "//button[text()='Sign In']");
				Common.clickElement("xpath", "//button[text()='Sign In']");
				Thread.sleep(5000);
				i++;
			}while(i<3 && !Common.isElementDisplayed("xpath", "//main[@id='maincontent']")); 

			report.addPassLog(expectedResult, "Should display My Account Page with user details", "My Account Page with user details displayed successfully", Common.getscreenShotPathforReport("Login page with details success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display  My Account Page with user details", "My Account Page with user details not displayed", Common.getscreenShotPathforReport("Login page with details Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void forgotPassword(String dataSet) throws Exception
	{
		String expectedResult="Forgot Password for Registered User";
		Thread.sleep(2000);
		try {
			Sync.waitElementClickable(30, By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"uk_en/customer/account/forgotpassword/']")));
			Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"uk_en/customer/account/forgotpassword/']"));
			int i=0;
			do{  
				Common.textBoxInput("id", "email_address", data.get(dataSet).get("Email"));
				Sync.waitElementClickable(30, By.xpath("//button[contains(text(),'Reset My Password')]"));
				Common.clickElement("xpath", "//button[contains(text(),'Reset My Password')]");
				Thread.sleep(4000);

				i++;
			}while(i<3 && !Common.isElementDisplayed("xpath", "//span[contains(text(),'Customer Login')]")); 
			Thread.sleep(5000);
			if(Common.isElementDisplayed("xpath", "//div[@data-bind='html: message.text']")) {
				String s=Common.getText("xpath", "//div[@data-bind='html: message.text']");
				Thread.sleep(4000);
				Assert.assertEquals(s, "If there is an account associated with "+data.get(dataSet).get("Email")+" you will receive an email with a link to reset your password.");
				Thread.sleep(4000);
			}
			report.addPassLog(expectedResult, "Should display Forgot Password Succes message", "Forgot Password page success message displayed successfully", Common.getscreenShotPathforReport("Forgot Password text"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Forgot Password Succes message", "Forgot Password page success message not displayed", Common.getscreenShotPathforReport("Account Creation Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void forgotPasswordMail(String dataSet) throws Exception
	{
		String expectedResult="Forgot Password for Registered User";
		Thread.sleep(2000);
		try {
			Sync.waitElementClickable(30, By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"uk_en/customer/account/forgotpassword/']")));
			Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"uk_en/customer/account/forgotpassword/']"));
			int i=0;
			do{  
				Common.textBoxInput("id", "email_address", data.get(dataSet).get("Email"));
				Sync.waitElementClickable(30, By.xpath("//button[contains(text(),'Reset My Password')]"));
				Common.clickElement("xpath", "//button[contains(text(),'Reset My Password')]");
				Thread.sleep(4000);

				i++;
			}while(i<3 && !Common.isElementDisplayed("xpath", "//span[contains(text(),'Customer Login')]")); 
			Thread.sleep(5000);
			if(Common.isElementDisplayed("xpath", "//div[@data-bind='html: message.text']")) {
				String s=Common.getText("xpath", "//div[@data-bind='html: message.text']");
				Thread.sleep(4000);
				Assert.assertEquals(s, "If there is an account associated with "+data.get(dataSet).get("Email")+" you will receive an email with a link to reset your password.");
				Thread.sleep(4000);
			}
			HashMap<String, String> hm = Utilities.GmailAPI.getGmailData("\"Your Password Reset Request\"");
			Assert.assertTrue(hm.get("body").contains("set a new password"));
			report.addPassLog(expectedResult, "Should display Forgot Password Succes message", "Forgot Password page success message displayed successfully", Common.getscreenShotPathforReport("Forgot Password text"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Forgot Password Succes message", "Forgot Password page success message not displayed", Common.getscreenShotPathforReport("Account Creation Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void SearchProduct(String dataSet) throws Exception
	{
		String expectedResult="Search with Product name :"+data.get(dataSet).get("ProductName");
		try {
			Thread.sleep(1000);
			Common.clickElement("xpath", "//div[@class='block block-search search-visible-md minisearch-v2']/div/i");
			Sync.waitElementPresent("id", "search");
			try {
				Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName"));
			}catch(Exception e)
			{
				Common.clickElement("xpath", "//div[@class='block block-search search-visible-md minisearch-v2']/div/i");
				Thread.sleep(3000);
				Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName"));
			}
			Common.actionsKeyPress(Keys.ENTER);
			Thread.sleep(10000);
			Common.actionsKeyPress(Keys.DOWN);
			Common.scrollIntoView("xpath", "(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[1]");
			report.addPassLog(expectedResult, "Should display Search Results Page", "Search results Page display successfully", Common.getscreenShotPathforReport("Search results success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Search Results Page", "Search results Page not display", Common.getscreenShotPathforReport("Search result Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void zerosearchProduct(String dataSet) throws Exception
	{		
		String expectedResult="Search with Product name :"+data.get(dataSet).get("ProductName");
		try {
			Sync.waitElementPresent("id", "search");
			try {
				Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName"));

			}catch(Exception e)
			{
				Common.clickElement("xpath", "//div[@class='block block-search search-visible-md minisearch-v2']/div/i");
				Thread.sleep(3000);
				Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName"));
			}
			Common.actionsKeyPress(Keys.ENTER);
			Thread.sleep(10000);
			Assert.assertTrue(Common.isElementDisplayed("xpath", "//div[@class='message notice']"));
			//Common.scrollIntoView("xpath", "(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[1]");
			report.addPassLog(expectedResult, "Should display Zero search results Page", "Zero search results Page display successfully", Common.getscreenShotPathforReport("Zero results page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Zero search results Page", "Zero search results Page not display", Common.getscreenShotPathforReport("Zearo results Failed"));
			Assert.fail();
		}

	}
	
	public void Productselection() throws Exception
	{
		String expectedResult="Product Selection from search results";
		try {
			if(Common.isElementDisplayed("xpath", "(//div[@class='product-item-info']/div/div/form//button[@title='Add to Bag'])[1]")) {
				Sync.waitElementPresent("xpath", "(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[1]");
				Common.clickElement("xpath", "(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[1]");
			}else if(Common.isElementDisplayed("xpath", "(//div[@class='product-item-info']/div/div/form//button[@title='Add to Bag'])[2]")) {
				Sync.waitElementPresent("xpath", "(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[2]");
				Common.clickElement("xpath", "(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[2]");
			}else {
				Sync.waitElementPresent("xpath", "(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[3]");
				Common.clickElement("xpath", "(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[3]");
			}
			Thread.sleep(3000);
			Common.isElementDisplayed("xpath", "//h1[@class='page-title']");
			report.addPassLog(expectedResult, "Should display Product Details Page", "Product Details Page display successfully", Common.getscreenShotPathforReport("Product Details page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Product Details Page", "Product details Page not displayed", Common.getscreenShotPathforReport("Zearo results Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}
	
	public void navigateMinicart() throws Exception
	{
		String expectedResult="Product adding to mini cart";
		try {
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//button[@id='product-addtocart-button']");
			Common.clickElement("xpath", "//button[@id='product-addtocart-button']");
			Thread.sleep(7000);
			String qty=Common.getText("xpath", "//span[@class='qty']");
			System.out.println("YOUR BAG - "+qty);
			Thread.sleep(3000);
			Common.clickElement("xpath", "//div[@class='actions action-viewcart bottom-aligned']/div//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"uk_en/checkout/cart/']"));
			//Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"uk_en/checkout/cart/']"));
			Thread.sleep(3000);
			Common.isElementDisplayed("xpath", "//strong[@class='product-item-name']");
			report.addPassLog(expectedResult, "Should display Mini Cart Page", "Mini Cart Page display successfully", Common.getscreenShotPathforReport("Mini Cart page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Mini Cart Page", "Mini Cart Page not displayed", Common.getscreenShotPathforReport("Mini Cart Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}
	
	public void checkoutPage() throws Exception
	{
		String expectedResult="Product adding to checkout page";
		try {
			Sync.waitElementPresent("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"uk_en/checkout/']/button"));
			Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"uk_en/checkout/']/button"));
			Thread.sleep(1000);
			Common.isElementDisplayed("xpath", "//div[contains(text(),'Shipping Address')]");
			report.addPassLog(expectedResult, "Should display Checkout Page", "Checkout Page display successfully", Common.getscreenShotPathforReport("Checkout page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Checkout Page", "Checkout Page not displayed", Common.getscreenShotPathforReport("Checkout Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void navigateCheckout() throws Exception
	{
		String expectedResult="Checkout page of Register user";
		try {
			//Common.clickElement("xpath", "//span[contains(text(),'Ship Here')]");
			Thread.sleep(2000);
			Common.scrollToElementAndClick("xpath", "//div[contains(text(),'Shipping Methods')]");

			if(Common.checkBoxIsSelected("xpath", "//*[@id='checkout-shipping-method-load']/table/tbody/tr[1]/td[1]/input")) {
				System.out.println("Shipping method is selected");
			}else {
				System.out.println("Shipping method is not selected");
				Sync.waitElementPresent("xpath", "//*[@id='checkout-shipping-method-load']/table/tbody/tr[1]/td[1]/input");
				Common.clickElement("xpath", "//*[@id='checkout-shipping-method-load']/table/tbody/tr[1]/td[1]/input");
			}
			Thread.sleep(1000);

			System.out.println("Next button clicked");
			Thread.sleep(300);
			Common.actionsKeyPress(Keys.DOWN);

			Sync.waitElementPresent("xpath", "//button[@data-role='opc-continue']");
			Common.clickElement("xpath", "//button[@data-role='opc-continue']");

			/*Sync.waitElementPresent("xpath", "//span[contains(text(),'Next')]");
			Common.clickElement("xpath", "//span[contains(text(),'Next')]");*/
			Thread.sleep(300);

			if(Common.isElementVisibleOnPage(30, "xpath", "//div[contains(text(),'Address is not verified. Do you want to continue ?')]")) {

				System.out.println("Address not verified pop up displayed");

				Sync.waitElementPresent("xpath", "//button[@class='action-primary action-accept']");
				Common.clickElement("xpath", "//button[@class='action-primary action-accept']");
			}
			Common.isElementDisplayed("xpath", "//div[contains(text(),'Payment Method')]");
			report.addPassLog(expectedResult, "Should display Checkout Page", "Checkout Page display successfully", Common.getscreenShotPathforReport("Checkout page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Checkout Page", "Checkout Page not displayed", Common.getscreenShotPathforReport("Checkout Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}
	
	public void navigateCheckoutGuest(String dataSet) throws Exception
	{
		String expectedResult="Checkout page with payment for Guest user";
		String expectedResult1="Email page of shipping address";
		String expectedResult2="Shipping address of Checkout page";
		try {
			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "//div[contains(text(),'Email')]");
			Thread.sleep(12000);
			//Sync.waitPageLoad(10);
			Common.clickElement("id", "customer-email");
			Common.textBoxInput("xpath", "//fieldset[@id='customer-email-fieldset']/div/div//input[@type='email']", data.get(dataSet).get("Email"));
			report.addPassLog(expectedResult1, "Should dispaly Shipping address page", "shipping address Page displayed successfully", Common.getscreenShotPathforReport("shipping address page"));

			Sync.waitElementPresent("xpath", "//span[contains(text(),'Next')]");
			Common.clickElement("xpath", "//span[contains(text(),'Next')]");

			Thread.sleep(1000);

			shipping_Address("Guest_shipping");
			report.addPassLog(expectedResult2, "Should dispaly Shipping address page", "shipping address Page displayed successfully", Common.getscreenShotPathforReport("Shipping address"));

			if(Common.checkBoxIsSelected("xpath", "//*[@id='checkout-shipping-method-load']/table/tbody/tr[1]/td[1]/input")) {

				System.out.println("Shipping method is selected");
			}else {

				System.out.println("Shipping method is not selected");

				Sync.waitElementPresent("xpath", "//*[@id='checkout-shipping-method-load']/table/tbody/tr[1]/td[1]/input");
				Common.clickElement("xpath", "//*[@id='checkout-shipping-method-load']/table/tbody/tr[1]/td[1]/input");

			}

			/*Common.actionsKeyPress(Keys.DOWN);

			Sync.waitElementPresent("xpath", "//button[@data-role='opc-continue']");
			Common.clickElement("xpath", "//button[@data-role='opc-continue']");*/

			Sync.waitElementPresent("xpath", "//span[contains(text(),'Next')]");
			Common.clickElement("xpath", "//span[contains(text(),'Next')]");

			Thread.sleep(300);

			if(Common.isElementVisibleOnPage(30, "xpath", "//div[contains(text(),'Address is not verified. Do you want to continue ?')]")) {

				System.out.println("Address not verified pop up displayed");

				Sync.waitElementPresent("xpath", "//button[@class='action-primary action-accept']");
				Common.clickElement("xpath", "//button[@class='action-primary action-accept']");
			}
			Common.isElementDisplayed("xpath", "//div[contains(text(),'Payment Method')]");
			report.addPassLog(expectedResult, "Should display Checkout Page", "Checkout Page display successfully", Common.getscreenShotPathforReport("Checkout page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Checkout Page", "Checkout Page not displayed", Common.getscreenShotPathforReport("Checkout Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void shipping_Address(String dataSet) throws Exception
	{
		Common.textBoxInput("name", "firstname", data.get(dataSet).get("FirstName"));
		Common.textBoxInput("name", "lastname", data.get(dataSet).get("LastName"));
		Common.textBoxInput("name", "street[0]", data.get(dataSet).get("Street"));
		Common.textBoxInput("name", "city", data.get(dataSet).get("City"));
		Thread.sleep(3000);
		Sync.waitElementPresent("xpath", "//div[contains(text(),'Shipping Address')]");
		Common.clickElement("xpath", "//div[contains(text(),'Shipping Address')]");
		Thread.sleep(3000);
		Sync.waitElementPresent("name", "region");
		Common.clickElement("name", "region");
		Thread.sleep(4000);
		Sync.waitElementPresent("name", "region");
		Common.textBoxInput("name", "region", data.get(dataSet).get("Region"));
		Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
		Thread.sleep(500);
		Common.textBoxInput("name", "telephone",data.get(dataSet).get("phone"));
	}
	
	public void addPaymentDetails(String dataSet) throws Exception
	{
		Sync.waitElementClickable("id", "adyen_cc");
		Common.clickElement("id", "adyen_cc");
		Thread.sleep(3000);
		Common.switchFrames("xpath", "//*[@id='cardContainer']/div/div/div[2]/div[1]/div[1]/label/span[2]/span/iframe");
		Common.textBoxInput("xpath", "//input[@id='encryptedCardNumber']", data.get(dataSet).get("cardNumber"));
		Common.switchToDefault();
		Thread.sleep(1000);
		Common.switchFrames("xpath", "//*[@id='cardContainer']/div/div/div[2]/div[1]/div[2]/div[1]/label/span[2]/span/iframe");
		Common.textBoxInput("xpath", "//input[@id='encryptedExpiryDate']", data.get(dataSet).get("ExpYear"));
		Common.switchToDefault();
		Thread.sleep(1000);
		Common.switchFrames("xpath", "//*[@id='cardContainer']/div/div/div[2]/div[1]/div[2]/div[2]/label/span[2]/span/iframe");
		Common.textBoxInput("xpath", "//input[@id='encryptedSecurityCode']", data.get(dataSet).get("cvv"));
		Common.switchToDefault();
		Common.textBoxInput("xpath", "//div[@class='adyen-checkout__field adyen-checkout__card__holderName']/label/span//input[@placeholder='J. Smith']", data.get(dataSet).get("CardName"));
	}
	
	public void updatePaymentAndSubmitOrder(String dataSet) throws Exception
	{
		String expectedResult="Payment & Order submition success page with Credit card";
		try {
			addPaymentDetails(dataSet);

			if(Common.findElements("xpath", "//div[@class='message message-error']").size()>0)
			{	
				addPaymentDetails(dataSet);
			}

			Common.scrollIntoView("xpath", "//div[contains(text(),'Payment Method')]");
			Thread.sleep(3000);
			Common.clickElement("xpath", "//input[@id='agreement_adyen_cc_1']");
			Thread.sleep(3000);
			Common.clickElement("xpath", "//button[@title='Place Order']");
			Thread.sleep(3000);

			//String sucessMessage=Common.getText("xpath", "//h1[@class='page-title']");
			String sucessMessage=Common.getText("xpath", "//h1[contains(text(),'Thank you for your purchase')]");

			System.out.println(sucessMessage);
			Assert.assertEquals(sucessMessage, "Thank you for your purchase");
			report.addPassLog(expectedResult, "Should display Order Success Page", "Order Success Page display successfully", Common.getscreenShotPathforReport("Order success page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Order Success Page", "Order Success Page not displayed", Common.getscreenShotPathforReport("Order Success Page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void EmailOrderTrigger() throws Exception
	{
		String expectedResult="Order Success Email confirmation";
		try {
			String orderid=Common.getText("xpath", "//a[@class='order-number']");
			System.out.println(orderid);
			Thread.sleep(5000);
			HashMap<String, String> hm = Utilities.GmailAPI.getGmailData("\"Your Order Confirmation\"");
			Assert.assertTrue(hm.get("body").contains("ORDER NUMBER"));
			//Assert.assertTrue(hm.get("body").contains("ORDER NUMBER #"+orderid));
			//System.out.println(hm.get("body").contains("ORDER NUMBER"));

			report.addPassLog(expectedResult, "Should display Order Success Page", "Order Success Page display successfully", Common.getscreenShotPathforReport("Emailtrigger success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Order Success Page", "Order Success Page not displayed", Common.getscreenShotPathforReport("Emailtrigger Page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void invalidCreditCard(String dataSet) throws Exception
	{
		String expectedResult="Payment Method with invalid Credit card";
		try {
			addPaymentDetails(dataSet);

			if(Common.findElements("xpath", "//div[@class='message message-error']").size()>0)
			{	
				addPaymentDetails(dataSet);
			}

			/*Common.clickElement("xpath", "//button[@id='paymetrictokenize_place_order']");
			Thread.sleep(2000);*/

			//Common.switchFrames("xpath", "//*[@id='cardContainer']/div/div/div[2]/div[1]/div[1]/label/span[2]/span/iframe");
			Common.scrollIntoView("xpath", "//ul[@class='opc-progress-bar']");
			Thread.sleep(1000);
			String Errormessage=Common.getText("xpath", "//span[@class='adyen-checkout__error-text']");
			System.out.println(Errormessage);
			Assert.assertEquals(Errormessage, "Invalid card number");
			report.addPassLog(expectedResult, "Should display Error message for Credit card number feild", "Error message for Credit card number feild display successfully", Common.getscreenShotPathforReport("Error message credit card success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Error message for Credit card number feild", "Error message for Credit card number feild not display", Common.getscreenShotPathforReport("Error message credit card Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}
	
	public void ValidateHomepagelogo() throws Exception
	{
		String expectedResult="Validating Home page logo and should lands on Home Page";
		try {
			Sync.waitElementClickable(30, By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/']")));
			Common.findElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/']")).click();
			Thread.sleep(4000);
			report.addPassLog(expectedResult, "Should redirect to home page", "Redirected to Home Page successfully", Common.getscreenShotPathforReport("Home Page Logo success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should redirect to home page", "Not Redirected to Home Page", Common.getscreenShotPathforReport("Home Page Logo Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void Collections() throws Exception{
		String expectedResult="Validating Home page Megamenu Collections and should lands on Collections page";
		try {
			Thread.sleep(5000);
			Sync.waitElementClickable(30, By.className("nav-toggle"));
			//Common.clickElement("className", "nav-toggle");
			//Common.clickElement("xpath", "//span[@class='action nav-toggle']/small[2]");
			//Common.javascriptclickElement("xpath", "//span[@class='action nav-toggle']/small[2]");
			//Common.clickElement("xpath", "//span[@class='action nav-toggle']");
			Common.clickElementStale("xpath", "//span[@class='action nav-toggle']");
			Thread.sleep(3000);
			Common.clickElement("xpath", "//span[@class='opener']");
			Common.clickElement("xpath", "//span[contains(text(),'One Step Family')]");
			Thread.sleep(4000);
			String collections=Common.getText("xpath", "//h1[contains(text(),'The One-Step Collection')]");
			Assert.assertEquals(collections, "The One-Step Collection");
			report.addPassLog(expectedResult, "Should redirect to Collections page", "Redirected to Collections Page successfully", Common.getscreenShotPathforReport("Collections Page success"));
		}catch(Exception |Error e){
			report.addFailedLog(expectedResult,"Should redirect to Collections page", "Not Redirected to Collections page", Common.getscreenShotPathforReport("Collections page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
		
	}
	
	public void Dryers() throws Exception{
		String expectedResult="Validating Home page Megamenu Dryers and should lands on Dryers page";
		try {
			Thread.sleep(5000);
			Sync.waitElementClickable(30, By.className("nav-toggle"));
			Common.clickElementStale("xpath", "//span[@class='action nav-toggle']");
			
			Thread.sleep(3000);
			
			Common.clickElement("xpath", "//span[contains(text(),'Dryers')]");
			Thread.sleep(4000);
			String Dryer=Common.getText("xpath", "//span[contains(text(),'Dryers')]");
			Assert.assertEquals(Dryer, "Dryers");
			report.addPassLog(expectedResult, "Should redirect to Dryers page", "Redirected to Dryers Page successfully", Common.getscreenShotPathforReport("Dryers Page success"));
		}catch(Exception |Error e){
			report.addFailedLog(expectedResult,"Should redirect to Dryers page", "Not Redirected to Dryers page", Common.getscreenShotPathforReport("Dryers page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
		
	}
	
	public void Straighteners() throws Exception{
		String expectedResult="Validating Home page Megamenu Straighteners and should lands on Straighteners page";
		try {
			Thread.sleep(5000);
			Sync.waitElementClickable(30, By.className("nav-toggle"));
			Common.clickElementStale("xpath", "//span[@class='action nav-toggle']");
			
			Thread.sleep(3000);	
			Common.clickElement("xpath", "//span[contains(text(),'Straighteners')]");
			Thread.sleep(4000);
			String Straighteners=Common.getText("xpath", "//span[contains(text(),'Straighteners')]");
			Assert.assertEquals(Straighteners, "Straighteners");
			report.addPassLog(expectedResult, "Should redirect to Straighteners page", "Redirected to Straighteners Page successfully", Common.getscreenShotPathforReport("Straighteners Page success"));
		
		}catch(Exception |Error e){
			report.addFailedLog(expectedResult,"Should redirect to Straighteners page", "Not Redirected to Straighteners page", Common.getscreenShotPathforReport("Straighteners page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
		
	}
	
	public void CurlingIrons() throws Exception{
		String expectedResult="Validating Home page Megamenu CurlingIrons and should lands on CurlingIrons page";
		try {
			Thread.sleep(5000);
			Sync.waitElementClickable(30, By.className("nav-toggle"));
			Common.clickElementStale("xpath", "//span[@class='action nav-toggle']");
			Thread.sleep(3000);		
			Common.clickElement("xpath", "//span[contains(text(),'Curling Irons')]");
			Thread.sleep(4000);
			String CurlingIrons=Common.getText("xpath", "//span[contains(text(),'Curling Irons')]");
			Assert.assertEquals(CurlingIrons, "Curling Irons");
			report.addPassLog(expectedResult, "Should redirect to CurlingIrons page", "Redirected to CurlingIrons Page successfully", Common.getscreenShotPathforReport("CurlingIrons Page success"));
		
		}catch(Exception |Error e){
			//report.addFailedLog(expectedResult,"Should redirect to CurlingIrons page", "Not Redirected to CurlingIrons page", Common.getscreenShotPathforReport("CurlingIrons page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
		
	}
	
	public void HairBrushesElastics() throws Exception{
		String expectedResult="Validating Home page Megamenu HairBrushesElastics and should lands on HairBrushesElastics page";
		try {
			Thread.sleep(5000);
			Sync.waitElementClickable(30, By.className("nav-toggle"));
			Common.clickElementStale("xpath", "//span[@class='action nav-toggle']");
			Thread.sleep(3000);			
			Common.clickElement("xpath", "//span[contains(text(),'Hair Brushes & Elastics')]");
			Thread.sleep(4000);
			String Brushes=Common.getText("xpath", "//span[contains(text(),'Brushes')]");
			Assert.assertEquals(Brushes, "Brushes");
			report.addPassLog(expectedResult, "Should redirect to HairBrushesElastics page", "Redirected to HairBrushesElastics Page successfully", Common.getscreenShotPathforReport("HairBrushesElastics Page success"));
		
		}catch(Exception |Error e){
			report.addFailedLog(expectedResult,"Should redirect to HairBrushesElastics page", "Not Redirected to HairBrushesElastics page", Common.getscreenShotPathforReport("HairBrushesElastics page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
		
	}
	
	public void Specialty() throws Exception{
		String expectedResult="Validating Home page Megamenu Specialty and should lands on Specialty page";
		try {
			Thread.sleep(5000);
			Sync.waitElementClickable(30, By.className("nav-toggle"));
			Common.clickElementStale("xpath", "//span[@class='action nav-toggle']");
			Thread.sleep(3000);		
			Common.clickElement("xpath", "//span[contains(text(),'Specialty')]");
			Thread.sleep(4000);
			String Specialty=Common.getText("xpath", "//span[contains(text(),'Specialty')]");
			Assert.assertEquals(Specialty, "Specialty");
			report.addPassLog(expectedResult, "Should redirect to Specialty page", "Redirected to Specialty Page successfully", Common.getscreenShotPathforReport("Specialty Page success"));
		
		}catch(Exception |Error e){
			report.addFailedLog(expectedResult,"Should redirect to Specialty page", "Not Redirected to Specialty page", Common.getscreenShotPathforReport("Specialty page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
		
	}
	
	public void NavigateNewsLetterSubscription() throws Exception
	{
		String expectedResult="Navigating News Letter Subscription and should lands on News Letter Subscription";
		try {
			Sync.waitElementClickable(30, By.xpath("//strong[contains(text(),'My Account')]"));
			Common.clickElement("xpath", "//strong[contains(text(),'My Account')]");
			
			Sync.waitElementClickable(30, By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"uk_en/newsletter/manage/']")));
			Common.findElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"uk_en/newsletter/manage/']")).click();
			
			
			report.addPassLog(expectedResult, "Should redirect to NewsLetter Subscription page", "Redirected to Home PageNewsLetter Subscription page successfully", Common.getscreenShotPathforReport("NewsLetter Subscription page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should redirect to NewsLetter Subscription page", "Not Redirected to NewsLetter Subscription page", Common.getscreenShotPathforReport("NewsLetter Subscription page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void NewsLetterSubscription() throws Exception
	{
		String expectedResult="Navigating News Letter Subscription and should lands on News Letter Subscription";
		try {
			Sync.waitElementClickable(30, By.xpath("//form[@id='form-validate']/fieldset/div/label//span[contains(text(),'General Subscription')]"));
			Common.clickElement("xpath", "//form[@id='form-validate']/fieldset/div/label//span[contains(text(),'General Subscription')]");
			Thread.sleep(2000);
			Sync.waitElementClickable(30, By.xpath("//button[@title='Save']"));
			Common.clickElement("xpath", "//button[@title='Save']");
			Thread.sleep(3000);
			String message=Common.getText("xpath", "//div[@data-bind='html: message.text']");
			System.out.println(message);
			Assert.assertEquals(message, "We have saved your subscription.");
			report.addPassLog(expectedResult, "Should able to NewsLetter Subscription", "should select to NewsLetter Subscription successfully", Common.getscreenShotPathforReport("NewsLetter Subscription success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should able to NewsLetter Subscription", "Not selected to NewsLetter Subscription", Common.getscreenShotPathforReport("NewsLetter Subscription Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void NewsLetterSubscriptionMail() throws Exception
	{
		String expectedResult="Navigating News Letter Subscription Mail Verification";
		try {
			HashMap<String, String> hm = Utilities.GmailAPI.getGmailData("\"Newsletter subscription confirmation\"");
			Assert.assertTrue(hm.get("body").contains("Thank you for signing up to the Revlon Hair Tools newsletter"));
			report.addPassLog(expectedResult, "Should able to NewsLetter Subscription Mail Verification", "NewsLetter Subscription Mail Verfication done successfully", Common.getscreenShotPathforReport("NewsLetter Subscription mail success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should able to NewsLetter Subscription Mail Verfication", "Not selected to NewsLetter Subscription Mail Verfication", Common.getscreenShotPathforReport("NewsLetter Subscription mail Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void NewsLetterUnSubscription() throws Exception
	{
		String expectedResult="Navigating News Letter Subscription and should lands on News Letter Subscription";
		try {
			Sync.waitElementClickable(30, By.xpath("//form[@id='form-validate']/fieldset/div/label//span[contains(text(),'General Subscription')]"));
			Common.clickElement("xpath", "//form[@id='form-validate']/fieldset/div/label//span[contains(text(),'General Subscription')]");
			Thread.sleep(2000);
			Sync.waitElementClickable(30, By.xpath("//button[@title='Save']"));
			Common.clickElement("xpath", "//button[@title='Save']");
			Thread.sleep(3000);
			String message=Common.getText("xpath", "//div[@data-bind='html: message.text']");
			System.out.println(message);
			Assert.assertEquals(message, "We have removed your newsletter subscription.");
			report.addPassLog(expectedResult, "Should able to NewsLetter Subscription", "should select to NewsLetter Subscription successfully", Common.getscreenShotPathforReport("NewsLetter Subscription success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should able to NewsLetter Subscription", "Not selected to NewsLetter Subscription", Common.getscreenShotPathforReport("NewsLetter Subscription Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void NewsLetterUnSubscriptionMail() throws Exception
	{
		String expectedResult="Navigating News Letter UnSubscription Mail Verification";
		try {
			HashMap<String, String> hm = Utilities.GmailAPI.getGmailData("\"Newsletter Unsubscribe\"");
			Assert.assertTrue(hm.get("body").contains("You have unsubsuscribed from the Revlon Hair Tools newsletter."));
			report.addPassLog(expectedResult, "Should able to NewsLetter UnSubscription Mail Verification", "NewsLetter UnSubscription Mail Verfication done successfully", Common.getscreenShotPathforReport("NewsLetter UnSubscription Mail success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should able to NewsLetter UnSubscription Mail Verfication", "Not selected to NewsLetter UnSubscription Mail Verfication", Common.getscreenShotPathforReport("NewsLetter UnSubscription mail Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void NavigateMyOrder() throws Exception
	{
		String expectedResult="Navigating My Order and should display List of my Orders";
		try {
			Sync.waitElementClickable(30, By.xpath("//strong[contains(text(),'My Account')]"));
			Common.clickElement("xpath", "//strong[contains(text(),'My Account')]");
			
			Thread.sleep(1000);
			Sync.waitElementClickable(30, By.xpath("//a[contains(text(),'My Orders')]"));
			Common.findElement("xpath", "//a[contains(text(),'My Orders')]").click();
			
			
			report.addPassLog(expectedResult, "Should redirect to My Orders page", "Redirected to My Orders successfully", Common.getscreenShotPathforReport("My Orders page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should redirect to My Orders page", "Not Redirected to My Orders page", Common.getscreenShotPathforReport("My Orders page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void headLinks(String dataSet) throws Exception{
		String expectedResult="Header Link validations";
		String Headerlinks=data.get(dataSet).get("HeaderNames");
		String[] headers=Headerlinks.split(",");
		for(int i=0;i<headers.length;i++){
			slider();
			Sync.waitElementClickable("xpath", "//span[text()='"+headers[i]+"']");
			Common.clickElement("xpath", "//span[text()='"+headers[i]+"']");
			Thread.sleep(3000);
			System.out.println(Common.getPageTitle());
			report.addPassLog(expectedResult, "Should display "+headers[i]+" success Page", ""+headers[i]+" Page display successfully", Common.getscreenShotPathforReport("Product Registration success page success"));
			
		}
		
	}

}
