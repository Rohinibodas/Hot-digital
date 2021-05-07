package TestComponent.BraunHC;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import TestLib.Automation_properties;
import TestLib.Common;
import TestLib.Sync;
import TestLib.Common.SelectBy;
import Utilities.ExcelReader;
import Utilities.ExtenantReportUtils;
import Utilities.Utils;

public class BraunHCHelper {
	String datafile;
	ExcelReader excelData;
	static Automation_properties automation_properties = Automation_properties.getInstance();
	Map<String, Map<String, String>> data=new HashMap<>();
	static ExtenantReportUtils report;
	public BraunHCHelper(String datafile){

		excelData=new ExcelReader(datafile);
		data=excelData.getExcelValue();
		this.data=data;
		if(Utilities.TestListener.report==null)
		{
			report=new ExtenantReportUtils("BraunHC");
			report.createTestcase("BraunHCTestCases");
		}else{
			this.report=Utilities.TestListener.report;
		}
	}	
	public void loginBraunHC(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		String expectedResult="Land on login page";
		try {
			//Sync.waitElementClickable(30, By.xpath("//button[text()='AGREE & PROCEED']"));
			//Common.findElement("xpath", "//button[text()='AGREE & PROCEED']").click();
			Sync.waitElementClickable(30, By.xpath("//a[@title='Sign In / Sign Up']"));
			Common.findElement("xpath", "//a[@title='Sign In / Sign Up']").click();
			Thread.sleep(1000);
			Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
			Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
			//Common.actionsKeyPress(Keys.ARROW_DOWN);
			//Common.actionsKeyPress(Keys.ARROW_DOWN);
			//Common.actionsKeyPress(Keys.ARROW_DOWN);
			Common.actionsKeyPress(Keys.ARROW_DOWN);
			//Common.mouseOverClick("xpath", "(//span[text()='Sign In'])[1]");
			Common.javascriptclickElement("xpath", "(//span[text()='Sign In'])[1]");
			//Common.actionsKeyPress(a);
			//Common.findElement("xpath", "(//span[text()='Sign In'])[1]").click();
			//Sync.waitElementPresent("xpath", "(//span[text()='Sign In'])[1]");
			//Common.scrollIntoView("xpath", "(//span[text()='Sign In'])[1]");
			String header=Common.getText("xpath", "//span[text()='Account Overview']");
			System.out.println(header+" page Displayed successfully");
			Assert.assertEquals(header, "Account Overview");
			report.addPassLog(expectedResult, "Should display loginBraunHC Page", "loginBraunHC page display successfully", Common.getscreenShotPathforReport("Login page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display  loginBraunHC Page", "loginBraunHC Page not displayed", Common.getscreenShotPathforReport("Login page Failed"));
			Assert.fail();
		}		
	}
	public void forgotPassword(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		String expectedResult="Forgot Password for Registered User";
		try {
			String expectedResult1="Landed on Login page";
			Sync.waitElementClickable(30, By.xpath("//a[@title='Sign In / Sign Up']"));
			Common.findElement("xpath", "//a[@title='Sign In / Sign Up']").click();
			Thread.sleep(1000);
			Common.findElement("xpath", "(//span[text()='Forgot Your Password?'])[1]").click();
			Thread.sleep(2000);
			Common.textBoxInput("id", "email_address", data.get(dataSet).get("Email"));
			//Common.textBoxInput("id", "captcha_user_forgotpassword", data.get(dataSet).get("Password"));
			String S=Common.getText("xpath", "//span[text()='Forgot Your Password?']");
			System.out.println(S);
			Assert.assertEquals(S, "Forgot Your Password?");
			Common.findElement("xpath", "//span[text()='Reset My Password']").click();

			report.addPassLog(expectedResult, "Should display Forgot Password Succes message", "Forgot Password page success message displayed successfully", Common.getscreenShotPathforReport("Forgot Password text"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Forgot Password Succes message", "Forgot Password page success message not displayed", Common.getscreenShotPathforReport("Account Creation Failed"));
			Assert.fail();
		}
	}
	public void Subscribe(String dataSet) throws Exception {
		String expectedResult=" Subscribe page";
		try {
			Common.textBoxInput("id", "newsletter", Utils.getEmailid());
			Thread.sleep(5000);
			Common.clickElement("xpath", "//span[text()='Subscribe']");
			Thread.sleep(5000);
			String S=Common.getText("xpath", "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");
			System.out.println(S);
			Assert.assertEquals(S, "Thank you for your subscription.");
			report.addPassLog(expectedResult, "Should display Subscribe Page", "Subscribe Page display successfully", Common.getscreenShotPathforReport("Subscribe page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Subscribe Page", "Subscribe Page not displayed", Common.getscreenShotPathforReport("Subscribe Failed"));
			Assert.fail();
		}
	}
	public void invalidSubscribe(String dataSet) throws Exception {
		String expectedResult="Lands on invalidSubscribe page";
		try {
			Common.textBoxInput("id", "newsletter",data.get(dataSet).get("Email"));
			Thread.sleep(5000);
			Common.clickElement("xpath", "//span[text()='Subscribe']");
			Thread.sleep(7000);
			String S=Common.getText("xpath", "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");
			System.out.println(S);
			Assert.assertEquals(S, "This email address is already subscribed.");
			report.addPassLog(expectedResult, "Should display invalidSubscribe Page", "invalidSubscribe Page display successfully", Common.getscreenShotPathforReport("invalidSubscribe page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display invalidSubscribe Page", "invalidSubscribe Page not displayed", Common.getscreenShotPathforReport("invalidSubscribe Failed"));
			Assert.fail();
		}	
	}
	public void SearchProduct(String dataSet) {
		// TODO Auto-generated method stub
		String expectedResult="Search with Product name :"+data.get(dataSet).get("ProductName");
		try {
			Sync.waitElementPresent("xpath", "//*[@id='search_mini_form']/div/label");
			Common.clickElement("xpath", "//*[@id='search_mini_form']/div/label");
			Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName").toString());

			report.addPassLog(expectedResult, "Should display Search Results Page", "Search results Page display successfully", Common.getscreenShotPathforReport("Search results success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Search Results Page", "Search results Page not display", Common.getscreenShotPathforReport("Search result Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}	
	public void AccountCreationBraunHC(String dataSet) {
		String expectedResult="Account Creation of User with valid details";
		try {
			Sync.waitElementClickable(30, By.xpath("//button[text()='AGREE & PROCEED']"));
			Common.findElement("xpath", "//button[text()='AGREE & PROCEED']").click();
			Sync.waitElementClickable(30, By.xpath("//a[@title='Sign In / Sign Up']"));
			Common.findElement("xpath", "//a[@title='Sign In / Sign Up']").click();
			//Common.findElement("xpath","//span[text()='Create an Account']\"").click();

			Thread.sleep(1000);
			Sync.waitElementPresent("xpath", "(//span[text()='Create an Account'])[1]");
			Common.clickElement("xpath", "(//span[text()='Create an Account'])[1]");
			Thread.sleep(2000);
			Common.textBoxInput("id", "firstname", data.get(dataSet).get("FirstName").toString());
			Common.textBoxInput("id", "lastname", data.get(dataSet).get("LastName").toString());
			Common.textBoxInput("id", "email_address",Utils.getEmailid());
			Common.textBoxInput("id", "password", data.get(dataSet).get("Password").toString());
			Common.textBoxInput("id", "password-confirmation", data.get(dataSet).get("Password").toString());
			Thread.sleep(1000);
			//Common.actionsKeyPress(Keys.DOWN);
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			//Common.scrollIntoView("xpath", "(//span[text()='Create an Account'])[1]");
			Sync.waitElementPresent("xpath", "(//span[text()='Create an Account'])[1]");
			Common.findElement("xpath", "(//span[text()='Create an Account'])[1]").click();
			String S=Common.getText("xpath", "//*[@id='maincontent']/div[2]/div[2]/div/div[1]/strong");
			System.out.println(S);
			Assert.assertEquals(S, "MY ACCOUNT");
			report.addPassLog(expectedResult, "Should display Login page", "Login page displayed successfully", Common.getscreenShotPathforReport("Login Page"));
			Thread.sleep(2000);
			report.addPassLog(expectedResult, "Should display My Account Page", "My Account Page display successfully", Common.getscreenShotPathforReport("Account Creation success"));
			//report.addPassLog(expectedResult, "Should display My Account Page", "My Account Page display successfully", Common.getscreenShotPathforReport("Account Creation success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display My Account Page", "My Account Page not display", Common.getscreenShotPathforReport("Account Creation Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	public void OrderStatus(String dataSet) {
		// TODO Auto-generated method stub
		String expectedResult="Lands on OrderStatus page";
		try {
			Sync.waitElementClickable(30, By.xpath("(//a[@title='Order Status'])[1]"));
			Common.findElement("xpath", "(//a[@title='Order Status'])[1]").click();
			Common.textBoxInput("id", "oar-order-id", data.get(dataSet).get("FirstName").toString());
			Common.textBoxInput("id", "oar-billing-lastname", data.get(dataSet).get("LastName").toString());
			Common.textBoxInput("xpath", "//*[@id='oar_email']", data.get(dataSet).get("Email").toString());
			Common.findElement("xpath", "//span[text()='Continue']").click();
			Thread.sleep(1000);
			String header=Common.getText("xpath", "//span[text()='Orders and Returns']");
			System.out.println(header+" page navigated");
			Assert.assertEquals(header, "Orders and Returns");
			report.addPassLog(expectedResult, "Should display OrderStatus Page", "OrderStatus Page display successfully", Common.getscreenShotPathforReport("OrderStatus page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display OrderStatus Page", "OrderStatus Page not displayed", Common.getscreenShotPathforReport("OrderStatus Failed"));
			Assert.fail();
		}
	}
	public void RegisterProduct(String dataSet) {
		// TODO Auto-generated method stub
		String expectedResult="Lands on Register Product page";
		try {
			Sync.waitElementClickable(30, By.xpath("(//a[@title='Order Status'])[1]"));
			Common.findElement("xpath", "(//a[@title='Order Status'])[1]").click();
			Common.textBoxInput("xapth", "//*[@id='oar-order-id']", data.get(dataSet).get("dataCode").toString());
			Common.textBoxInput("xapth", "//*[@id='oar-billing-lastname']", data.get(dataSet).get("FirstName").toString());
			Common.textBoxInput("xpath", "//*[@id='oar_email']", data.get(dataSet).get("Email").toString());
			Common.findElement("xpath", "//span[text()='Continue']").click();
			Thread.sleep(1000);
			/*String header=Common.getText("xpath", "//span[text()='My Orders']");
			System.out.println(header+"OrderStatus in page");
       		Assert.assertEquals(header, "OrderStatus");*/
			report.addPassLog(expectedResult, "Should display RegisterProduct Page", "RegisterProduct Page display successfully", Common.getscreenShotPathforReport("RegisterProduct page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display RegisterProduct Page", "RegisterProduct Page not displayed", Common.getscreenShotPathforReport("RegisterProduct Failed"));
			Assert.fail();
		}
	}
	public void MyOrdersPage() throws InterruptedException {
		String expectedResult="Land on MyOrders page";
		try {
			Common.findElement("xpath", "//*[@id='block-collapsible-nav']/ul/li[3]/a").click();
			Thread.sleep(3000);
			String header=Common.getText("xpath", "//span[text()='My Orders']");
			System.out.println(header+" page navigated successfully");
			Assert.assertEquals(header, "My Orders");
			report.addPassLog(expectedResult, "Should display MyOrdersPage Page", "MyOrdersPage page display successfully", Common.getscreenShotPathforReport("MyOrdersPage page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display  MyOrdersPage Page", "MyOrdersPage Page not displayed", Common.getscreenShotPathforReport("MyOrdersPage page Failed"));
			Assert.fail();
		}
	}
	public void Order_by_SKU(String dataSet) throws InterruptedException {
		// TODO Auto-generated method stub
		String expectedResult="Land on Order_by_SKU page";
		try {
			Sync.waitElementClickable(30, By.xpath("//a[@title='Sign In / Sign Up']"));
			Common.findElement("xpath", "//a[@title='Sign In / Sign Up']").click();
			Thread.sleep(1000);
			Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
			Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
			Common.findElement("xpath", "(//span[text()='Sign In'])[1]").click();
			Common.findElement("xpath", "//*[@id='block-collapsible-nav']/ul/li[3]/a").click();
			Thread.sleep(1000);
			String header=Common.getText("xpath", "//span[text()='Order by SKU']");
			System.out.println(header+"Order_by_SKU in page");
			Assert.assertEquals(header, "Order by SKU");

			report.addPassLog(expectedResult, "Should display Order_by_SKU Page", "Order_by_SKU page display successfully", Common.getscreenShotPathforReport("Order_by_SKU page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display  Order_by_SKU Page", "Order_by_SKU Page not displayed", Common.getscreenShotPathforReport("Order_by_SKU page Failed"));
			Assert.fail();
		}
	}

	public void My_Product_Reviews() throws InterruptedException {
		// TODO Auto-generated method stub
		String expectedResult="Land on MyOrders page";
		try {
			
			Common.findElement("xpath", "//a[contains(text(),'My Product Reviews')]").click();
			Thread.sleep(3000);
			String header=Common.getText("xpath", "//span[text()='My Product Reviews']");
			System.out.println(header+" page navigated successfully");
			Assert.assertEquals(header, "My Product Reviews");
			report.addPassLog(expectedResult, "Should display MyOrdersPage Page", "MyOrdersPage page display successfully", Common.getscreenShotPathforReport("MyOrdersPage page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display  MyOrdersPage Page", "MyOrdersPage Page not displayed", Common.getscreenShotPathforReport("MyOrdersPage page Failed"));
			Assert.fail();
		}
	}

	public void RedeemGiftCard(String dataSet) throws InterruptedException {
		String expectedResult="Navigating to GiftCard page";
		try {
			Sync.waitElementClickable(30, By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/giftcard/customer/']")));
			Common.findElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/giftcard/customer/']")).click();
			Thread.sleep(3000);
			Common.textBoxInput("id", "giftcard-code", data.get(dataSet).get("cardNumber"));
			Thread.sleep(3000);
			Common.findElement("xpath", "//span[text()='Redeem Gift Card']").click();
			Thread.sleep(5000);
			/*String header=Common.getText("xpath", "//span[text()='Gift Card']");
			System.out.println(header+" GiftCard page");
			Assert.assertEquals(header, "Gift Card");*/
			String S=Common.getText("xpath", "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");
			System.out.println(S);
			Assert.assertEquals(S, "We cannot redeem this gift card.");
			report.addPassLog(expectedResult, "Should display GiftCard Page", "GiftCard page display successfully", Common.getscreenShotPathforReport("GiftCard page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display  GiftCard Page", "GiftCard Page not displayed", Common.getscreenShotPathforReport("GiftCard page Failed"));
			Assert.fail();
		}
	}
	
	public void BalacecheckGiftCard(String dataSet) throws InterruptedException {
		String expectedResult="Navigating to GiftCard page";
		try {
			Common.textBoxInput("id", "giftcard-code", data.get(dataSet).get("cardNumber"));
			Thread.sleep(3000);
			Common.findElement("xpath", "//span[text()='Check status and balance']").click();
			Thread.sleep(3000);
			/*String header=Common.getText("xpath", "//span[text()='Gift Card']");
			System.out.println(header+" GiftCard page");
			Assert.assertEquals(header, "Gift Card");*/
			String E=Common.getText("xpath", "//div[@role='alert']");
			System.out.println(E);
			Assert.assertEquals(E, "Please enter a valid gift card code.");
			report.addPassLog(expectedResult, "Should display GiftCard Page", "GiftCard page display successfully", Common.getscreenShotPathforReport("GiftCard page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display  GiftCard Page", "GiftCard Page not displayed", Common.getscreenShotPathforReport("GiftCard page Failed"));
			Assert.fail();
		}
	}
	public void StoreCredit(String dataSet) throws InterruptedException {
		// TODO Auto-generated method stub	
		String expectedResult="Land on StoreCredit page";
		try {
			Sync.waitElementClickable(30, By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/storecredit/info/']")));
			Common.findElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/storecredit/info/']")).click();
			Thread.sleep(3000);
			String header=Common.getText("xpath", "//span[text()='Store Credit']");
			System.out.println(header+" page Navigated");
			Assert.assertEquals(header, "Store Credit");
			report.addPassLog(expectedResult, "Should display StoreCredit Page", "StoreCredit page display successfully", Common.getscreenShotPathforReport("StoreCredit page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display  StoreCredit Page", "StoreCredit Page not displayed", Common.getscreenShotPathforReport("StoreCredit page Failed"));
			Assert.fail();
		}
	}
	public void DownloadableProduct(String dataSet) throws InterruptedException {
		String expectedResult="Land on DownloadableProduct page";
		try {
			Sync.waitElementClickable(30, By.xpath("//*[@id='block-collapsible-nav']/ul/li[4]/a"));
			Common.findElement("xpath", "//*[@id='block-collapsible-nav']/ul/li[4]/a").click();

			String header=Common.getText("xpath", "//span[text()='My Downloadable Products']");
			System.out.println(header+" page navigated successfully");
			Assert.assertEquals(header, "My Downloadable Products");
			report.addPassLog(expectedResult, "Should display DownloadableProduct Page", "DownloadableProduct page display successfully", Common.getscreenShotPathforReport("DownloadableProduct page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display  DownloadableProduct Page", "DownloadableProduct Page not displayed", Common.getscreenShotPathforReport("DownloadableProduct page Failed"));
			Assert.fail();
		}
	}
	public void My_Wish_List() throws InterruptedException {
		String expectedResult="Land on My_Wish_List page";
		try {
			Sync.waitElementClickable(30, By.xpath("//*[@id='block-collapsible-nav']/ul/li[5]/a"));
			Common.findElement("xpath", "//*[@id='block-collapsible-nav']/ul/li[5]/a").click();

			String header=Common.getText("xpath", "//span[text()='My Wish List']");
			System.out.println(header+" page navigated successfully");
			Assert.assertEquals(header, "My Wish List");
			report.addPassLog(expectedResult, "Should display My_Wish_List Page", "My_Wish_List page display successfully", Common.getscreenShotPathforReport("My_Wish_List page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display  My_Wish_List Page", "My_Wish_List Page not displayed", Common.getscreenShotPathforReport("My_Wish_List page Failed"));
			Assert.fail();  
		}	
	}
	public void Address_Book() throws InterruptedException {
		String expectedResult="Navigate to Address_Book page";
		try {
			//Sync.waitElementClickable(30, By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/customer/address/")));
			//Common.findElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/customer/address/")).click();
			Common.clickElement("xpath", "//a[contains(text(),'Address Book')]");
			Thread.sleep(1000);
		/*	String header=Common.getText("xpath", "//span[text()='Address Book']");
			System.out.println(header+" page navigated");
			Assert.assertEquals(header, "Address Book");
			Thread.sleep(3000);*/
			if(Common.isElementDisplayed("xpath", "//button[@title='Add New Address']"))
			{
				System.out.println("Add New Address clicked");
				Sync.waitElementPresent("xpath", "//button[@title='Add New Address']");
				Common.clickElement("xpath", "//button[@title='Add New Address']");
				Thread.sleep(5000);
				if(Common.isElementDisplayed("name", "firstname")) {
					System.out.println("Entered into Add New Address page");
					addNewAddress("Addressbook");
				}else {
					System.out.println("Not Entered into Add New Address page");
					Thread.sleep(2000);
					Sync.waitElementPresent("xpath", "//button[@title='Add New Address']");
					Common.clickElement("xpath", "//button[@title='Add New Address']");
					Thread.sleep(5000);
					addNewAddress("Addressbook");
				}
			}
			else {
				System.out.println("Add New Address not clicked");
				addNewAddress("Addressbook");
			}
			report.addFailedLog(expectedResult,"Should Navigate to Address Book & Add new Address successfully", "Navigated to Address Book & Add new Address successfully", Common.getscreenShotPathforReport("Add new Address Success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should Navigate to Address Book & Add new Address successfully", "Not Navigated to Address Book & Add new Address successfully", Common.getscreenShotPathforReport("Add new Address failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void addNewAddress(String dataSet) throws Exception
	{
		Common.textBoxInput("name", "firstname", data.get(dataSet).get("FirstName"));
		Common.textBoxInput("name", "lastname", data.get(dataSet).get("LastName"));
		Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
		Common.textBoxInput("name", "street[]", data.get(dataSet).get("Street"));
		Common.textBoxInput("name", "city", data.get(dataSet).get("City"));
		
		Thread.sleep(1000);
		Sync.waitElementPresent("xpath", "//select[@title='State/Province']");
		Common.scrollToElementAndClick("xpath", "//select[@title='State/Province']");

		Sync.waitElementPresent("xpath", "//select[@title='State/Province']");
		//Common.clickElement("xpath", "//select[@title='State/Province']");
		Common.dropdown("xpath", "//select[@title='State/Province']", SelectBy.TEXT, data.get(dataSet).get("Region"));
		Sync.waitElementPresent("name", "postcode");
		Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
		Thread.sleep(1000);
		Sync.waitElementPresent("xpath", "//button[@class='action submit primary']");
		Common.clickElement("xpath", "//button[@title='Save Address']");
		Thread.sleep(3000);
		String message=Common.getText("xpath", "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");
		System.out.println(message);
		Assert.assertEquals(message, "You saved the address.");
		
	}
	public void Newsletter_Subscription() throws InterruptedException {
		String expectedResult="Navigating to Newsletter Subscription page";
		try {
			//Sync.waitElementPresent("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/newsletter/manage/']"));
			//Common.findElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/newsletter/manage/']")).click();
			Common.findElement("xpath", "//a[contains(text(),'Newsletter Subscriptions')]").click();
			Thread.sleep(3000);
			Common.clickElement("xpath", "//span[contains(text(),'General Subscription')]");
			Thread.sleep(3000);
			Common.clickElement("xpath", "//button[@title='Save']");
			Thread.sleep(5000);
			String S=Common.getText("xpath", "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");
			System.out.println(S);
			Assert.assertEquals(S, "We have saved your subscription.");
			report.addPassLog(expectedResult, "Should display Newsletter Subscription Page", "Newsletter Subscription page display successfully", Common.getscreenShotPathforReport("Newsletter_Subscription page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display  Newsletter Subscription Page", "Newsletter Subscription Page not displayed", Common.getscreenShotPathforReport("Newsletter_Subscription page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	public void Newsletter_UnSubscription() throws InterruptedException {
		String expectedResult="Navigating to Newsletter UnSubscription page";
		try {
			//Sync.waitElementPresent("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/newsletter/manage/']"));
			//Common.findElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/newsletter/manage/']")).click();
			Common.findElement("xpath", "//a[contains(text(),'Newsletter Subscriptions')]").click();
			Thread.sleep(3000);
			Common.clickElement("xpath", "//span[contains(text(),'General Subscription')]");
			Thread.sleep(3000);
			Common.clickElement("xpath", "//button[@title='Save']");
			Thread.sleep(5000);
			String S=Common.getText("xpath", "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");
			System.out.println(S);
			Assert.assertEquals(S, "We have removed your newsletter subscription.");
			report.addPassLog(expectedResult, "Should display Newsletter UnSubscription Page", "Newsletter UnSubscription page display successfully", Common.getscreenShotPathforReport("Newsletter_UnSubscription page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display  Newsletter UnSubscription Page", "Newsletter UnSubscription Page not displayed", Common.getscreenShotPathforReport("Newsletter_UnSubscription page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	public void searchProduct(String dataSet) throws InterruptedException {
		String expectedResult="Land on searchProduct page";
		try {
			
			Sync.waitElementClickable(30, By.xpath("//*[@id='ui-id-3']/span"));
			Common.findElement("id", "ui-id-3").click();
			Thread.sleep(1000);
			Common.findElement("xpath", "//*[@id='ui-id-2']/li[1]/div/ul/li/div/div/div[1]/div[2]/figure/a/img[1]").click();
			Common.findElement("xpath", "(//img[@class='product-image-photo'])[1]").click();
			//Thread.sleep(5000);
			//Common.clickElement("xpath", "//a[@class='product photo product-item-photo']");
			//Common.clickElement("xpath" , "//a[@class='product-item-link']");
			//Common.mouseOverClick("xpath" , "//span[text()='Add to Cart']");
			//Common.findElement("xpath", "//span[text()='Add to Cart']").click();
			//Common.mouseOverClick("xpath" , "//span[text()='Add to Cart']"); */
			report.addPassLog(expectedResult, "Should display searchProduct Page", "searchProduct page display successfully", Common.getscreenShotPathforReport("searchProduct page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display  searchProduct Page", "searchProduct Page not displayed", Common.getscreenShotPathforReport("searchProduct page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	public void Addtocart(String string) throws InterruptedException {
		String expectedResult="Land on Addtocart page";
		try {
			
			Sync.waitElementClickable(30, By.xpath("(//span[text()='Add to cart'])[1]"));
			Common.clickElement("xpath" , "(//span[text()='Add to cart'])[1]");
			Thread.sleep(4000);
			//Sync.waitElementClickable(30, By.xpath("//a[@class='action showcart']"));
			Common.clickElement("xpath" , "//a[@class='action showcart']");
			Thread.sleep(1000);
			String header=Common.getText("xpath", "//span[text()='Cart Overview']");
			System.out.println(header+" page is displayed");
			Assert.assertEquals(header, "Cart Overview");
			report.addPassLog(expectedResult, "Should display Addtocart Page", "Addtocart page display successfully", Common.getscreenShotPathforReport("Addtocart page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display  Addtocart Page", "Addtocart Page not displayed", Common.getscreenShotPathforReport("Addtocart page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	public void ShippingcartPage() throws InterruptedException {
		String expectedResult="Should navigate to shipping cart page";
		try {
			Sync.waitElementClickable(30, By.xpath("//*[@id='minicart-content-wrapper']/div[2]/div[5]/div[2]/a/span"));
			//Common.clickElement("xpath" , "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/checkout/cart/']/span"));
			Common.javascriptclickElement("xpath" , "//*[@id='minicart-content-wrapper']/div[2]/div[5]/div[2]/a/span");
			Thread.sleep(4000);
			String S=Common.getText("xpath", "//strong[@class='product-item-name']");
			System.out.println(S);
			Assert.assertTrue(Common.isElementDisplayed("xpath", "//strong[@class='product-item-name']"));
			report.addPassLog(expectedResult, "Should display Shipping cart Page", "Shipping cart page display successfully", Common.getscreenShotPathforReport("Shipping cart page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display  Shipping cart Page", "Shipping cart Page not displayed", Common.getscreenShotPathforReport("Shipping cart page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void navigateShippingaddress() throws InterruptedException {
		String expectedResult="Should navigate to Shipping address page";
		try {
			Sync.waitElementClickable(30, By.xpath("//button[@data-role='proceed-to-checkout']"));
			Common.clickElement("xpath" , "//button[@data-role='proceed-to-checkout']");
			Thread.sleep(4000);
			String S=Common.getText("xpath", "//*[@id='shipping']/div[1]");
			System.out.println(S);
			Assert.assertTrue(Common.isElementDisplayed("xpath", "//*[@id='shipping']/div[1]"));
			Thread.sleep(3000);
			Common.scrollIntoView("xpath", "//div[@class='checkout-shipping-method']/div");
			Thread.sleep(3000);
			Common.clickElement("xpath", "//*[@id='checkout-shipping-method-load']/table/tbody/tr/td[1]/label");
			Common.clickElement("xpath", "//button[@data-role='opc-continue']/span");
			//Common.clickElement("xpath","//span[text()='Next']");
			report.addPassLog(expectedResult, "Should display Shipping address Page", "Shipping address page display successfully", Common.getscreenShotPathforReport("Shipping address page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display  Shipping address Page", "Shipping address Page not displayed", Common.getscreenShotPathforReport("Shipping address page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void ShippingAddress(String dataSet) throws InterruptedException {
		try {
			Common.textBoxInput("id", "customer-email", data.get(dataSet).get("Email"));
			Thread.sleep(3000);
			Common.textBoxInput("name", "firstname", data.get(dataSet).get("FirstName"));
			Common.textBoxInput("name", "lastname", data.get(dataSet).get("LastName"));
			Common.textBoxInput("name", "street[0]", data.get(dataSet).get("Street"));
			Common.textBoxInput("name", "city", data.get(dataSet).get("City"));
			Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
			Common.dropdown("name", "region_id",Common.SelectBy.TEXT, data.get(dataSet).get("City"));
			Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
			Thread.sleep(3000);		
		}catch(Exception |Error e)
		{
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void GuestShippingaddress() throws InterruptedException {
		String expectedResult="Should navigate to Shipping address page";
		try {
			Sync.waitElementClickable(30, By.xpath("//button[@data-role='proceed-to-checkout']"));
			Common.clickElement("xpath" , "//button[@data-role='proceed-to-checkout']");
			Thread.sleep(4000);
			String S=Common.getText("xpath", "//*[@id='shipping']/div[1]");
			System.out.println(S);
			Assert.assertTrue(Common.isElementDisplayed("xpath", "//*[@id='shipping']/div[1]"));
			Thread.sleep(3000);
			ShippingAddress("Guest_shipping");
			Common.scrollIntoView("xpath", "//div[@class='checkout-shipping-method']/div");
			Thread.sleep(3000);
			Common.clickElement("xpath", "//*[@id='checkout-shipping-method-load']/table/tbody/tr/td[1]/label");
			Common.clickElement("xpath", "//button[@data-role='opc-continue']/span");
			report.addPassLog(expectedResult, "Should display Shipping address Page", "Shipping address page display successfully", Common.getscreenShotPathforReport("Shipping address page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display  Shipping address Page", "Shipping address Page not displayed", Common.getscreenShotPathforReport("Shipping address page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void MoneyOrderpayment() throws InterruptedException {
		String expectedResult="Navigate to Review & payment Page";
		Thread.sleep(2000);
		try {
			Thread.sleep(3000);
			Sync.waitElementPresent("xpath", "//input[@id='c-cardnumber']");
		    Common.clickElement("xpath", "//input[@id='c-cardnumber']");
			Thread.sleep(5000);
			//Common.clickElement("xpath", "//button[@title='Place Order']");
			report.addPassLog(expectedResult, "Should display Review & payment Page", "Review & payment page display successfully", Common.getscreenShotPathforReport("Review & payment page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display  Review & payment Page", "Review & payment Page not displayed", Common.getscreenShotPathforReport("Review & payment page Failed"));
			Assert.fail();

		}
	}
	
	public void RegistereduserOrderSuccesspage() throws InterruptedException {
		String expectedResult="Validating Order Confirmation Page";
		Thread.sleep(2000);
		try {
			Thread.sleep(10000);
			String Success=Common.getText("xpath", "//h1[@class='page-title']");
			System.out.println(Success);
			Assert.assertEquals(Success, "Thank you for your purchase!");
			//String orderid=Common.getText("xpath", "//*[@id='maincontent']/div[3]/div/div[2]/p[1]/a/strong");
			//System.out.println("Your order number is "+orderid);
			report.addPassLog(expectedResult, "Should Order Success Page", "Order Success page display successfully", Common.getscreenShotPathforReport("Order Success page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display  Order Success Page", "Order Success Page not displayed", Common.getscreenShotPathforReport("Order Success page Failed"));
			e.printStackTrace();
			Assert.fail();

		}
	}
	
	public void GuestOrderSuccesspage() throws InterruptedException {
		
		String expectedResult="Validating Order Confirmation Page";
		Thread.sleep(2000);
		try {
			Thread.sleep(10000);
			String Success=Common.getText("xpath", "//h1[@class='page-title']");
			System.out.println(Success);
			Assert.assertEquals(Success, "Thank you for your purchase!");
			//String orderid=Common.getText("xpath", "//*[@id='maincontent']/div[3]/div/div[2]/p[1]/a/strong");
			//System.out.println("Your order number is "+orderid);
			report.addPassLog(expectedResult, "Should Order Success Page", "Order Success page display successfully", Common.getscreenShotPathforReport("Order Success page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display  Order Success Page", "Order Success Page not displayed", Common.getscreenShotPathforReport("Order Success page Failed"));
			e.printStackTrace();
			Assert.fail();

		}
		
		/*String expectedResult="Validating Order Confirmation Page";
		Thread.sleep(2000);
		try {
			Thread.sleep(3000);
			String Success=Common.getText("xpath", "//h1[@class='page-title']");
			System.out.println(Success);
			Assert.assertEquals(Success, "Thank you for your purchase!");
			String orderid=Common.getText("xpath", "//*[@id='maincontent']/div[3]/div/div[2]/p[1]/span");
			System.out.println("Your order number is "+orderid);
			report.addPassLog(expectedResult, "Should Order Success Page", "Order Success page display successfully", Common.getscreenShotPathforReport("Order Success page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display  Order Success Page", "Order Success Page not displayed", Common.getscreenShotPathforReport("Order Success page Failed"));
			Assert.fail();

		}*/
	}
	
	
	public void ChangeQtyMinicart() throws InterruptedException {
		// TODO Auto-generated method stub
		String expectedResult="Land on ChangeQtyMinicart";
		try {
			Common.clickElement("className" , "qty-incrementer__increment");
			Thread.sleep(4000);
			report.addPassLog(expectedResult, "Should display ChangeQtyMinicart Page", "ChangeQtyMinicart page display successfully", Common.getscreenShotPathforReport("ChangeQtyMinicart page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display  ChangeQtyMinicart Page", "ChangeQtyMinicart Page not displayed", Common.getscreenShotPathforReport("ChangeQtyMinicartpage Failed"));
			Assert.fail();
		}
	}
	
	public void QTYInminicart() throws InterruptedException {
		String expectedResult="Navigate to mini cart and validate quantity in mini cart";
		try {
			Common.clickElement("xpath" , "//button[@id='product-addtocart-button']");
			Thread.sleep(4000);
			Common.clickElement("xpath" , "//span[text()='My Cart']");
			//Common.clickElement("xpath" , "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/checkout/cart/']"));
			Thread.sleep(1000);
			/*String QTY=Common.getText("xpath", "//input[@class='item-qty cart-item-qty']");
			System.out.println(QTY);*/
			Assert.assertTrue(Common.isElementDisplayed("xpath", "//input[@class='item-qty cart-item-qty']"));
			report.addPassLog(expectedResult, "Should display Mini cart Page and validate quantity", "Navigated mini cart page and validated quantity display successfully", Common.getscreenShotPathforReport("Mini Cart QTY success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display  Mini cart Page and validate quantity", "Navigated mini cart page and validated quantity not displayed", Common.getscreenShotPathforReport("Mini Cart QTY Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	public void ShippingAddress1(String dataSet) throws InterruptedException {
		String expectedResult="ShippingAddress1";
		Thread.sleep(2000);
		try {

			Common.findElement("xpath", "//span[text()='Proceed to Review & Payment']").click();
			report.addPassLog(expectedResult, "Should display ShippingAddress1 Page", "ShippingAddress1 page display successfully", Common.getscreenShotPathforReport("ShippingAddress page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display  ShippingAddress1 Page", "ShippingAddress1 Page not displayed", Common.getscreenShotPathforReport("ShippingAddress page Failed"));
			Assert.fail();

		}
	}
	public void Accountinfomation() throws InterruptedException {
		String expectedResult="Navigating to Profile for registered user";
		try {
			/*Sync.waitElementClickable(30, By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/customer/account/edit/']")));
			Common.javascriptclickElement("xpath" , "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/customer/account/edit/']"));*/
			
			//Sync.waitElementClickable(30, By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/customer/account/edit/']")));
			//Common.findElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/customer/account/edit/']")).click();
			Common.findElement("xpath", "//*[@id='block-collapsible-nav']/ul/li[2]/a").click();
			report.addFailedLog(expectedResult,"Should Navigate to Profile successfully", "Navigated to Profile successfully", Common.getscreenShotPathforReport("Profile Success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should Navigate to Profile successfully", "Not Navigated to Profile successfully", Common.getscreenShotPathforReport("Profile failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void changeProfileName(String dataSet) throws Exception
	{
		String expectedResult="Should able to change Profile for registered user";
		try {
			Common.textBoxInput("name", "firstname", data.get(dataSet).get("FirstName"));
			Common.textBoxInput("name", "lastname", data.get(dataSet).get("LastName"));
			Thread.sleep(1000);
			Sync.waitElementPresent("xpath", "//button[@title='Save']");
			Common.clickElement("xpath", "//button[@title='Save']");
			Thread.sleep(3000);
			String s=Common.getText("xpath", "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");
			System.out.println(s);
			Assert.assertEquals(s, "You saved the account information.");
			report.addFailedLog(expectedResult,"Should able to change Profile successfully", "Able to change Profile successfully", Common.getscreenShotPathforReport("Changed Profile Name Success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should able to change Profile successfully", "Not able to change Profile successfully", Common.getscreenShotPathforReport("Changed Profile Name failed"));
			e.printStackTrace();
			Assert.fail();
		}		
	}
	
	public void changeProfileEmail(String dataSet) throws Exception
	{

		String expectedResult="Navigating to Profile for email for registered user";
		try {
			Sync.waitElementPresent("name", "change_email");	
			Common.clickElement("name", "change_email");
			Thread.sleep(1000);

			Common.textBoxInput("name", "email", data.get(dataSet).get("Email"));
			Common.textBoxInput("name", "current_password", data.get(dataSet).get("Password"));
			Thread.sleep(1000);
			Sync.waitElementPresent("xpath", "//button[@title='Save']");
			Common.clickElement("xpath", "//button[@title='Save']");
			Thread.sleep(3000);
			String s=Common.getText("xpath", "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");
			System.out.println(s);
			Assert.assertEquals(s, "You saved the account information.");
			report.addFailedLog(expectedResult,"Should Navigate to Profile for email successfully", "Navigated to Profile for email successfully", Common.getscreenShotPathforReport("email Profile Success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should Navigate to Profile for email successfully", "Not Navigated to Profile for email successfully", Common.getscreenShotPathforReport("email Profile failed"));
			e.printStackTrace();
			Assert.fail();
		}
		
	}

	public void changeprofilePassword(String dataSet) throws Exception
	{
		String expectedResult="Navigating to Profile for password for registered user";
		try {
			Sync.waitElementPresent("name", "change_password");	
			Common.clickElement("name", "change_password");
			Thread.sleep(1000);
			Common.textBoxInput("xpath", "//input[@data-input='current-password']", data.get(dataSet).get("Password"));
			Common.textBoxInput("xpath", "//input[@data-input='new-password']", data.get(dataSet).get("Password"));
			Common.textBoxInput("name", "password_confirmation", data.get(dataSet).get("Password"));
			Thread.sleep(1000);
			Sync.waitElementPresent("xpath", "//button[@title='Save']");
			Common.clickElement("xpath", "//button[@title='Save']");
			Thread.sleep(3000);
			String s=Common.getText("xpath", "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");
			System.out.println(s);
			if(s.contains("You saved the account information.")) {
				Assert.assertEquals(s, "You saved the account information.");
			}else {
				Assert.assertEquals(s, "Unable to send mail: Unknown error");
			}
			report.addFailedLog(expectedResult,"Should Navigate to Profile for password successfully", "Navigated to Profile for password successfully", Common.getscreenShotPathforReport("Password Profile Success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should Navigate to Profile for password successfully", "Not Navigated to Profile for password successfully", Common.getscreenShotPathforReport("password Profile failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	public void UpdatePaymentAndSubmitOrder(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		String expectedResult="Payment & Order submition success page with Credit card";
		try {
		Thread.sleep(1000);
		Sync.waitElementPresent("xpath", "//iframe[@name='paymetric_xisecure_frame']");
		Common.switchFrames("xpath", "//iframe[@name='paymetric_xisecure_frame']");
		Thread.sleep(2000);
		Sync.waitElementPresent("xpath", "//select[@id='c-ct']");
		Common.dropdown("xpath", "//select[@id='c-ct']", Common.SelectBy.TEXT, data.get(dataSet).get("cardType"));
		Thread.sleep(3000);
		Sync.waitElementPresent("xpath", "//input[@name='c-cn']");
		Common.textBoxInput("xpath", "//input[@id='c-cardnumber']", data.get(dataSet).get("cardNumber"));
		Thread.sleep(4000);
		Sync.waitElementPresent("xpath", "//select[@id='c-exmth']");
		Common.dropdown("xpath", "//select[@id='c-exmth']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpMonth"));
		Thread.sleep(5000);
		Sync.waitElementPresent("xpath", "//select[@id='c-exyr']");
		Common.dropdown("xpath", "//select[@id='c-exyr']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpYear"));
		Thread.sleep(6000);
		Sync.waitElementPresent("xpath", "//input[@id='c-cvv']");
		Common.textBoxInput("xpath", "//input[@id='c-cvv']", data.get(dataSet).get("cvv"));
		Common.switchToDefault();
		Thread.sleep(5000);
		
		//Sync.waitElementPresent("xpath", "//span[text()='Place Order']");
		Common.scrollIntoView("xpath", "//span[text()='Place Order']");
	    Common.clickElement("xpath", "//span[text()='Place Order']");
	    String header=Common.getText("xpath", "//div[contains(text(),'Payment method')]");
		System.out.println(header+"Payment method");
		Assert.assertEquals(header, "Payment method");
		    report.addPassLog(expectedResult, "Should display Order Success Page", "Order Success Page display successfully", Common.getscreenShotPathforReport("Order success page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Order Success Page", "Order Success Page not displayed", Common.getscreenShotPathforReport("Order Success Page Failed"));
			Assert.fail();
		}
	}
	public void PlaceOrder() {
		// TODO Auto-generated method stub
		String expectedResult="Navigating to Profile for registered user";
		try {
			Sync.waitElementPresent("xpath", "//span[text()='Place Order']");
		    Common.clickElement("xpath", "//*[@id='checkout-payment-method-load']/div/div/div[2]/div[2]/div/div[4]/div/button/span");
			report.addFailedLog(expectedResult,"Should Navigate to Profile successfully", "Navigated to Profile successfully", Common.getscreenShotPathforReport("Profile Success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should Navigate to Profile successfully", "Not Navigated to Profile successfully", Common.getscreenShotPathforReport("Profile failed"));
			e.printStackTrace();
			Assert.fail();
		}
		
	}
	public void UpdateGuestPaymentAndSubmitOrder(String dataSet) {
		// TODO Auto-generated method stubtry {
		String expectedResult="Payment & Order submition success page with AEMX card";
		try {
		Thread.sleep(1000);
		Sync.waitElementPresent("xpath", "//iframe[@name='paymetric_xisecure_frame']");
		Common.switchFrames("xpath", "//iframe[@name='paymetric_xisecure_frame']");
		Thread.sleep(2000);
		Sync.waitElementPresent("xpath", "//select[@id='c-ct']");
		Common.dropdown("xpath", "//select[@id='c-ct']", Common.SelectBy.TEXT, data.get(dataSet).get("cardType"));
		Thread.sleep(3000);
		Sync.waitElementPresent("xpath", "//input[@name='c-cn']");
		Common.textBoxInput("xpath", "//input[@id='c-cardnumber']", data.get(dataSet).get("cardNumber"));
		Thread.sleep(4000);
		Sync.waitElementPresent("xpath", "//select[@id='c-exmth']");
		Common.dropdown("xpath", "//select[@id='c-exmth']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpMonth"));
		Thread.sleep(5000);
		Sync.waitElementPresent("xpath", "//select[@id='c-exyr']");
		Common.dropdown("xpath", "//select[@id='c-exyr']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpYear"));
		Thread.sleep(6000);
		Sync.waitElementPresent("xpath", "//input[@id='c-cvv']");
		Common.textBoxInput("xpath", "//input[@id='c-cvv']", data.get(dataSet).get("cvv"));
		Common.switchToDefault();
		Thread.sleep(5000);
		Common.scrollIntoView("xpath", "//span[text()='Place Order']");
	    Common.clickElement("xpath", "//span[text()='Place Order']");
	    String header=Common.getText("xpath", "//div[contains(text(),'Payment method')]");
		System.out.println(header+"Payment method");
		Assert.assertEquals(header, "Payment method");
		
	    report.addPassLog(expectedResult, "Should display Order Success Page", "Order Success Page display successfully", Common.getscreenShotPathforReport("Order success page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Order Success Page", "Order Success Page not displayed", Common.getscreenShotPathforReport("Order Success Page Failed"));
			Assert.fail();
	}	
}
	public void Validate_Address_book_AVS(String dataSet) {
		// TODO Auto-generated method stub
		String expectedResult="Navigate to Address_Book page";
		try {
			//Sync.waitElementClickable(30, By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/customer/address/")));
			//Common.findElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/customer/address/")).click();
			Common.clickElement("xpath", "//a[contains(text(),'Address Book')]");
			Thread.sleep(1000);
			String header=Common.getText("xpath", "//span[text()='Address Book']");
			System.out.println(header+" page navigated");
			Assert.assertEquals(header, "Address Book");
			Thread.sleep(3000);
			if(Common.isElementDisplayed("xpath", "//button[@title='Add New Address']"))
			{
				System.out.println("Add New Address clicked");
				Sync.waitElementPresent("xpath", "//button[@title='Add New Address']");
				Common.clickElement("xpath", "//button[@title='Add New Address']");
				Thread.sleep(5000);
				if(Common.isElementDisplayed("name", "firstname")) {
					System.out.println("Entered into Add New Address page");
					//addNewAddress("Addressbook");
					Common.textBoxInput("name", "lastname", data.get(dataSet).get("LastName"));
					//Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
					Common.textBoxInput("name", "street[]", data.get(dataSet).get("Street(AVS)"));
					Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
				}else {
					System.out.println("Not Entered into Add New Address page");
					Thread.sleep(2000);
					Sync.waitElementPresent("xpath", "//button[@title='Add New Address']");
					Common.clickElement("xpath", "//button[@title='Add New Address']");
					Thread.sleep(5000);
					addNewAddress("Addressbook");
				}
			}
			else {
				System.out.println("Add New Address not clicked");
				addNewAddress("Addressbook");
			}
			report.addFailedLog(expectedResult,"Should Navigate to Address Book & Add new Address successfully", "Navigated to Address Book & Add new Address successfully", Common.getscreenShotPathforReport("Add new Address Success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should Navigate to Address Book & Add new Address successfully", "Not Navigated to Address Book & Add new Address successfully", Common.getscreenShotPathforReport("Add new Address failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	public void Global_search(String dataSet) {
		// TODO Auto-generated method stub
		String expectedResult="Land on searchProduct page";
		try {
			Thread.sleep(3000);
			
			Sync.waitElementClickable(30, By.xpath("//span[@class='icon-search action open']"));
			Common.findElement("xpath", "//span[@class='icon-search action open']").click();
			Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName"));
			Common.findElement("xpath", "//div[@class='mst-product-image-wrapper']").click();
			//Common.actionsKeyPress(Keys.ENTER);
			//Common.findElement("xpath", "(//img[@class='product-image-photo'])[1]").click();
			Thread.sleep(10000);
			String header=Common.getText("xpath", "//strong[text()='Forehead Thermometer']");
			System.out.println(header+"Forehead Thermometer");
			Assert.assertEquals(header, "Forehead Thermometer");
			
			
			report.addPassLog(expectedResult, "Should display searchProduct Page", "searchProduct page display successfully", Common.getscreenShotPathforReport("searchProduct page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display  searchProduct Page", "searchProduct Page not displayed", Common.getscreenShotPathforReport("searchProduct page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	
		
	}
	
	public void zerosearchProduct(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		String expectedResult="Land on searchProduct page";
		try {
		Thread.sleep(3000);
		Sync.waitElementClickable(30, By.xpath("//span[@class='icon-search action open']"));
		Common.findElement("xpath", "//span[@class='icon-search action open']").click();
		Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName"));
		report.addPassLog(expectedResult, "Should display searchProduct Page", "searchProduct page display successfully", Common.getscreenShotPathforReport("searchProduct page success"));
	}catch(Exception |Error e)
	{
		report.addFailedLog(expectedResult,"Should display  searchProduct Page", "searchProduct Page not displayed", Common.getscreenShotPathforReport("searchProduct page Failed"));
		e.printStackTrace();
		Assert.fail();
	}
		
		
		
	}
	public void selectCategoryProduct(String string) {
		// TODO Auto-generated method stub
		String expectedResult="Land on searchProduct page";
		try {
			   
			Sync.waitElementClickable(30, By.xpath("//*[@id='ui-id-3']/span"));
		    //Common.findElement("xpath", "//*[@id='ui-id-3']/span").click();
			Common.mouseOver("xpath" , "//*[@id='ui-id-3']/span");
			Thread.sleep(1000);
			Common.findElement("xpath", "//*[@id='ui-id-2']/li[1]/div/ul/li/div/div/div[1]/div[1]/figure/a/img[1]").click();
			
			Common.findElement("xpath", "(//img[@class='product-image-photo'])[3]").click();
			//Common.findElement("xpath", "//strong[text()='Forehead Thermometer']").click();
			String header=Common.getText("xpath", "//strong[text()='Forehead Thermometer']");
			System.out.println(header+"Forehead Thermometer");
			Assert.assertEquals(header, "Forehead Thermometer");
			report.addPassLog(expectedResult, "Should display searchProduct Page", "searchProduct page display successfully", Common.getscreenShotPathforReport("searchProduct page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display  searchProduct Page", "searchProduct Page not displayed", Common.getscreenShotPathforReport("searchProduct page Failed"));
			e.printStackTrace();
			Assert.fail();
	}
		
	}
	public void CMS_links(String string) {
		// TODO Auto-generated method stub
		String expectedResult="Validating CMS Link About US";
		try {
			Sync.waitElementPresent("xpath", "//div[@class='copyrights']");
			Common.scrollIntoView("xpath", "//div[@class='copyrights']");
			Sync.waitElementPresent("xpath", "//a[@href='" + System.getProperty("url",
					automation_properties.getInstance().getProperty(automation_properties.BASEURL) + "about-us/']"));
			Common.clickElement("xpath", "//a[@href='" + System.getProperty("url",
					automation_properties.getInstance().getProperty(automation_properties.BASEURL) + "about-us/']"));
			Thread.sleep(10000);
			Common.switchWindows();
			String s = Common.getText("xpath",
					"//div[@class='page-title-wrapper']/h1[@class='page-title']/span[@class='base']");
			System.out.println(s);
			Assert.assertEquals(s, "ABOUT HOT TOOLS");
			//reporter parse log

			report.addPassLog(expectedResult,"Should display AboutUs page", "AboutUs page display successfully", Common.getscreenShotPathforReport("AboutUs page success"));

		}
		catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display AboutUs page", "AboutUs page display successfully", Common.getscreenShotPathforReport("AboutUs page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
		this.closeCurrentTabandSwitchtoHome();
	}
	private void closeCurrentTabandSwitchtoHome() {
		// TODO Auto-generated method stub
		
	}
	public void validateNavigateFAQ(String string) {
		// TODO Auto-generated method stub
		String expectedResult="Validating FAQs Link ";
		try {
			Common.scrollIntoView("xpath", "//span[text()='FAQs']");
			Sync.waitElementPresent("xpath", "//span[text()='FAQs']");
			Common.findElement("xpath", "//span[text()='FAQs']").click();
			Common.switchWindows();
			String s = Common.getText("xpath","//h1[contains(text(),'Frequent Asked Questions')]");
			System.out.println(s);
			Assert.assertEquals(s, "Frequent Asked Questions");
			//reporter parse log

			report.addPassLog(expectedResult,"Should display AboutUs page", "AboutUs page display successfully", Common.getscreenShotPathforReport("AboutUs page success"));

		}
		catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display AboutUs page", "AboutUs page display successfully", Common.getscreenShotPathforReport("AboutUs page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
		this.closeCurrentTabandSwitchtoHome();
	}
	public void validateNavigateContactUs(String string) {
		// TODO Auto-generated method stub
		String expectedResult="Validating ContactUs Link ";
		try 
		{
			
			Common.scrollIntoView("xpath", "//span[text()='Contact Us']");
			Sync.waitElementPresent("xpath", "//span[text()='Contact Us']");
			Common.findElement("xpath", "//span[text()='Contact Us']").click();
			Common.switchWindows();
			String s = Common.getText("xpath","//h1[contains(text(),'Contact Us')]");
			System.out.println(s);
			Assert.assertEquals(s, "Contact Us");
			//reporter parse log

			report.addPassLog(expectedResult,"Should display ContactUs page", "ContactUs page display successfully", Common.getscreenShotPathforReport("ContactUs page success"));

		}
		catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display ContactUs page", "ContactUs page display successfully", Common.getscreenShotPathforReport("ContactUs page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	public void validateNavigateThermometers(String string) {
		// TODO Auto-generated method stub
		String expectedResult="Validating Thermometers ";
		try {
			Common.scrollIntoView("xpath", "//span[text()='Thermometers']");
			Sync.waitElementPresent("xpath", "//span[text()='Thermometers']");
			Common.findElement("xpath", "//span[text()='Thermometers']").click();
			Common.switchWindows();
			String s = Common.getText("xpath","(//span[text()='Thermometers'])[1]");
			System.out.println(s);
			Assert.assertEquals(s, "Thermometers");
			//reporter parse log

			report.addPassLog(expectedResult,"Should display Thermometers page", "Thermometers page display successfully", Common.getscreenShotPathforReport("Thermometers page success"));

		}
		catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Thermometers page", "Thermometers page display successfully", Common.getscreenShotPathforReport("Thermometers page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	public void validateNavigateBloodPressureMonitor(String string) {
		// TODO Auto-generated method stub
		String expectedResult="Validating Blood Pressure Monitor ";
		try {
			Common.scrollIntoView("xpath", "//span[text()='Blood Pressure Monitor']");
			Sync.waitElementPresent("xpath", "//span[text()='Blood Pressure Monitor']");
			Common.findElement("xpath", "//span[text()='Blood Pressure Monitor']").click();
			Common.switchWindows();
			String s = Common.getText("xpath","//span[text()='ExactFit 3 Blood Pressure Monitor']");
			System.out.println(s);
			Assert.assertEquals(s, "ExactFit 3 Blood Pressure Monitor");
			//reporter parse log

			report.addPassLog(expectedResult,"Should display Blood Pressure Monitor page", "Blood Pressure Monitor page display successfully", Common.getscreenShotPathforReport("Thermometers page success"));

		}
		catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Blood Pressure Monitor page", "Blood Pressure Monitor page display successfully", Common.getscreenShotPathforReport("Thermometers page Failed"));
			e.printStackTrace();
			Assert.fail();
		
	}
	
		
	}
	public void validateNavigatePulseOximeter(String string) {
		// TODO Auto-generated method stub
		String expectedResult="Validating PulseOximeter ";
		try {
			Common.scrollIntoView("xpath", "(//span[text()='Pulse Oximeter'])[1]");
			Sync.waitElementPresent("xpath", "(//span[text()='Pulse Oximeter'])[1]");
			Common.findElement("xpath", "(//span[text()='Pulse Oximeter'])[1]").click();
			Common.switchWindows();
			String s = Common.getText("xpath","//span[text()='Braun Pulse Oximeter']");
			System.out.println(s);
			Assert.assertEquals(s, "Braun Pulse Oximeter");
			//reporter parse log

			report.addPassLog(expectedResult,"Should display Braun Pulse Oximeter page", "Braun Pulse Oximeter page display successfully", Common.getscreenShotPathforReport("Braun Pulse Oximeter page success"));

		}
		catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Braun Pulse Oximeter page", "Braun Pulse Oximeter page display successfully", Common.getscreenShotPathforReport("Braun Pulse Oximeter page Failed"));
			e.printStackTrace();
			Assert.fail();
		
	}
	}
	public void validateNavigateAccessories(String string) {
		// TODO Auto-generated method stub
		String expectedResult="Validating Accessories ";
		try {
			Common.scrollIntoView("xpath", "(//span[text()='Accessories'])[1]");
			Sync.waitElementPresent("xpath", "(//span[text()='Accessories'])[1]");
			Common.findElement("xpath", "(//span[text()='Accessories'])[1]").click();
			Common.switchWindows();
			String s = Common.getText("xpath","//span[text()='ThermoScan Lens Filters - 40 count']");
			System.out.println(s);
			Assert.assertEquals(s, "ThermoScan Lens Filters - 40 count");
			//reporter parse log

			report.addPassLog(expectedResult,"Should display Braun Pulse Accessories page", "Braun Pulse Accessories page display successfully", Common.getscreenShotPathforReport("Braun Pulse Accessories page success"));

		}
		catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Braun Pulse Accessories page", "Braun Pulse Accessories page display successfully", Common.getscreenShotPathforReport("Braun Pulse Accessories page Failed"));
			e.printStackTrace();
			Assert.fail();
		
	}
}
	public void validateNavogateOurCompany(String string) {
		// TODO Auto-generated method stub
		String expectedResult="Validating OurCompany ";
		try {
			Common.scrollIntoView("xpath", "(//span[text()='Our Company'])[1]");
			Sync.waitElementPresent("xpath", "(//span[text()='Our Company'])[1]");
			Common.findElement("xpath", "(//span[text()='Our Company'])[1]").click();
			Common.switchWindows();
			String s = Common.getText("xpath","(//span[text()='Company'])[1]");
			System.out.println(s);
			Assert.assertEquals(s, "Company");
			//reporter parse log

			report.addPassLog(expectedResult,"Should display  Company page", "Company page display successfully", Common.getscreenShotPathforReport("Company page success"));

		}
		catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Company page", "Company page display successfully", Common.getscreenShotPathforReport("Company page Failed"));
			e.printStackTrace();
			Assert.fail();
			this.closeCurrentTabandSwitchtoHome();
		
	}
	
		this.closeCurrentTabandSwitchtoHome();
}
	public void validateNavogateOurHistory(String string) {
		// TODO Auto-generated method stub
		String expectedResult="Validating Our History ";
		try {
			Common.scrollIntoView("xpath", "(//span[text()='Our History'])[1]");
			Sync.waitElementPresent("xpath", "(//span[text()='Our History'])[1]");
			Common.findElement("xpath", "(//span[text()='Our History'])[1]").click();
			Common.switchWindows();
			String s = Common.getText("xpath","(//h1[text()='Our History'])[1]");
			System.out.println(s);
			Assert.assertEquals(s, "Our 52 Year History");
			//reporter parse log

			report.addPassLog(expectedResult,"Should display  Our History page", "Our History page display successfully", Common.getscreenShotPathforReport("Our History page success"));

		}
		catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Our History page", "Our History page display successfully", Common.getscreenShotPathforReport("Our History page Failed"));
			e.printStackTrace();
			Assert.fail();
			this.closeCurrentTabandSwitchtoHome();
	}
		this.closeCurrentTabandSwitchtoHome();	
}
	public void validateNavogateBlog(String string) {
		// TODO Auto-generated method stub
		String expectedResult="Validating Blog ";
		try {
			Common.scrollIntoView("xpath", "(//span[text()='Blog'])[2]");
			Sync.waitElementPresent("xpath", "(//span[text()='Blog'])[2]");
			Common.findElement("xpath", "(//span[text()='Blog'])[2]").click();
			Common.switchWindows();
			String s = Common.getText("xpath","(//span[text()='Blog'])[1]");
			System.out.println(s);
			Assert.assertEquals(s, "Blog");
			//reporter parse log

			report.addPassLog(expectedResult,"Should display  Blog page", "Blog page display successfully", Common.getscreenShotPathforReport("Blog page success"));

		}
		catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Blog page", "Blog page display successfully", Common.getscreenShotPathforReport("Blog page Failed"));
			e.printStackTrace();
			Assert.fail();
			this.closeCurrentTabandSwitchtoHome();
	}
		this.closeCurrentTabandSwitchtoHome();	
	}
	public void validateNavogateCareers(String string) {
		// TODO Auto-generated method stub
		String expectedResult="Validating Careers ";
		try {
			Common.scrollIntoView("xpath", "//span[text()='Careers']");
			Sync.waitElementPresent("xpath", "//span[text()='Careers']");
			Common.findElement("xpath", "//span[text()='Careers']").click();
			Common.switchWindows();
			String s = Common.getText("xpath","//h1[text()='Elevating  ']");
			System.out.println(s);
			Assert.assertEquals(s, "Elevating Lives,Soaring Together");
			//reporter parse log

			report.addPassLog(expectedResult,"Should display  Careers page", "Careers page display successfully", Common.getscreenShotPathforReport("Careers page success"));

		}
		catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Careers page", "Careers page display successfully", Common.getscreenShotPathforReport("Careers page Failed"));
			e.printStackTrace();
			Assert.fail();
			this.closeCurrentTabandSwitchtoHome();
	}
		this.closeCurrentTabandSwitchtoHome();	
	}
	public void validateNavigateFacebook(String string) {
		// TODO Auto-generated method stub
		String expectedResult="Validating facebook ";
		try {
			
			Common.scrollIntoView("xpath", "//a[@class='social-icons-link icon-pagebuilder-social-icons-item--facebook']");
			Sync.waitElementPresent("xpath", "//a[@class='social-icons-link icon-pagebuilder-social-icons-item--facebook']");
			Common.findElement("xpath", "//a[@class='social-icons-link icon-pagebuilder-social-icons-item--facebook']").click();
			Common.switchWindows();
			/*String s = Common.getText("xpath","(//span[text()='Braun Healthcare'])[1]");
			System.out.println(s);
			Assert.assertEquals(s, "Braun Healthcare");*/
			//reporter parse log
			report.addPassLog(expectedResult,"Should display  facebook page", "facebook page display successfully", Common.getscreenShotPathforReport("facebook page success"));
		}
		catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display facebook page", "facebook page display successfully", Common.getscreenShotPathforReport("facebook page Failed"));
			e.printStackTrace();
			Assert.fail();
			//this.closeCurrentTabandSwitchtoHome();
	}
		 Common.closeCurrentWindow();
		   Common.switchToFirstTab();
	}
	public void validateNavigateYoutube(String string) {
		// TODO Auto-generated method stub
		String expectedResult="Validating Youtube ";
		try {
			
			Common.scrollIntoView("xpath", "//a[@class='social-icons-link icon-pagebuilder-social-icons-item--youtube']");
			Sync.waitElementPresent("xpath", "//a[@class='social-icons-link icon-pagebuilder-social-icons-item--youtube']");
			Common.findElement("xpath", "//a[@class='social-icons-link icon-pagebuilder-social-icons-item--youtube']").click();
			Common.switchWindows();
			String s = Common.getText("xpath","(//div[@class='style-scope ytd-channel-name'])[1]");
			System.out.println(s);
			Assert.assertEquals(s, "Braun Healthcare United States");
			//reporter parse log

			report.addPassLog(expectedResult,"Should display  Youtube page", "Youtube page display successfully", Common.getscreenShotPathforReport("Youtube page success"));

		}
		catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Youtube page", "Youtube page display successfully", Common.getscreenShotPathforReport("Youtube page Failed"));
			e.printStackTrace();
			Assert.fail();
			//this.closeCurrentTabandSwitchtoHome();
	}
		 Common.closeCurrentWindow();
		   Common.switchToFirstTab();
	}
	public void RegistereduserApplypromocode(String dataSet) {
		// TODO Auto-generated method stub
		String expectedResult="Validating Apply promo code ";
		try {
			Common.scrollIntoView("xpath", "//span[text()='Apply promo code']");
			
			Sync.waitElementPresent("xpath", "//span[text()='Apply promo code']");
			Common.findElement("xpath", "//span[text()='Apply promo code']").click();
			
			Common.textBoxInput("xpath", "//input[@name='discount_code']", data.get(dataSet).get("promocodename"));
			
			Common.findElement("xpath", "//span[text()='Apply Discount']").click();
			Sync.waitElementPresent("xpath", "//span[text()='Place Order']");
		  Common.scrollIntoView("xpath", "//span[text()='Place Order']");
		  //Common.clickElement("xpath", "//span[text()='Place Order']");
		     Common.scrollIntoView("xpath", "//span[text()='Place Order']");
		      Common.clickElement("xpath", "//span[text()='Place Order']");
			String s = Common.getText("xpath","//span[text()='Apply promo code']");
			System.out.println(s);
			Assert.assertEquals(s, "Apply promo code ply Discou");
			//reporter parse log

			report.addPassLog(expectedResult,"Should display  Apply promo code page", "Apply promo code page display successfully", Common.getscreenShotPathforReport("Apply promo code page success"));

		}
		catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display YoApply promo codeutube page", "Apply promo code page display successfully", Common.getscreenShotPathforReport("Apply promo code page Failed"));
			e.printStackTrace();
			Assert.fail();
			

	}
	
	}
	public void UpdatePayment(String dataSet) {
		// TODO Auto-generated method stub
		String expectedResult="Payment & Order submition success page with Credit card";
		try {
		Thread.sleep(1000);
		Sync.waitElementPresent("xpath", "//iframe[@name='paymetric_xisecure_frame']");
		Common.switchFrames("xpath", "//iframe[@name='paymetric_xisecure_frame']");
		Thread.sleep(2000);
		Sync.waitElementPresent("xpath", "//select[@id='c-ct']");
		Common.dropdown("xpath", "//select[@id='c-ct']", Common.SelectBy.TEXT, data.get(dataSet).get("cardType"));
		Thread.sleep(3000);
		Sync.waitElementPresent("xpath", "//input[@name='c-cn']");
		Common.textBoxInput("xpath", "//input[@id='c-cardnumber']", data.get(dataSet).get("cardNumber"));
		Thread.sleep(4000);
		Sync.waitElementPresent("xpath", "//select[@id='c-exmth']");
		Common.dropdown("xpath", "//select[@id='c-exmth']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpMonth"));
		Thread.sleep(5000);
		Sync.waitElementPresent("xpath", "//select[@id='c-exyr']");
		Common.dropdown("xpath", "//select[@id='c-exyr']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpYear"));
		Thread.sleep(6000);
		Sync.waitElementPresent("xpath", "//input[@id='c-cvv']");
		Common.textBoxInput("xpath", "//input[@id='c-cvv']", data.get(dataSet).get("cvv"));
		Common.switchToDefault();
		Thread.sleep(5000);
		
		//Sync.waitElementPresent("xpath", "//span[text()='Place Order']");
	    Common.scrollIntoView("xpath", "//span[text()='Place Order']");
	    Common.clickElement("xpath", "//span[text()='Place Order']");
	    String header=Common.getText("xpath", "//div[contains(text(),'Payment method')]");
		System.out.println(header+"Payment method");
		Assert.assertEquals(header, "Payment method");
		    report.addPassLog(expectedResult, "Should display Order Success Page", "Order Success Page display successfully", Common.getscreenShotPathforReport("Order success page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Order Success Page", "Order Success Page not displayed", Common.getscreenShotPathforReport("Order Success Page Failed"));
			Assert.fail();
		}
	}
	
		
	
	public void validateNAvigateInstagram() {
		// TODO Auto-generated method stub
		String expectedResult="Validating Instagram ";
		try {
			
			Common.scrollIntoView("xpath", "//a[@class='social-icons-link icon-pagebuilder-social-icons-item--instagram']");
			Sync.waitElementPresent("xpath", "//a[@class='social-icons-link icon-pagebuilder-social-icons-item--instagram']");
			/*Common.findElement("xpath", "//a[@class='social-icons-link icon-pagebuilder-social-icons-item--instagram']").click();
			Common.switchWindows();
		    String s = Common.getText("xpath","//h2[text()='braunhealthcareus']");
			System.out.println(s);
			Assert.assertEquals(s, "braunhealthcareus");*/
			//reporter parse log

			report.addPassLog(expectedResult,"Should display  Instagram page", "Instagram page display successfully", Common.getscreenShotPathforReport("Instagram page success"));

		}
		catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Instagram page", "Instagram page display successfully", Common.getscreenShotPathforReport("Instagram page Failed"));
			e.printStackTrace();
			Assert.fail();
			//this.closeCurrentTabandSwitchtoHome();
	}
	}
	public void validateNavigateContactUS_footer_links(String dataset) throws Exception {
		// TODO Auto-generated method stub
		  String expectedResult="Validating Contact Us";
	       try
	       {
	    	   
	      
		   Common.actionsKeyPress(Keys.END); 
		   Sync.waitElementClickable(30, By.xpath("(//span[@class='mobile-accordion-link-text'])[4]"));
			
			Common.findElement("xpath","(//span[@class='mobile-accordion-link-text'])[4]").click();
			
			Common.switchFrames("xpath", "//iframe[contains(@src,'custhelp')]");
			Sync.waitPageLoad();
			Common.textBoxInput("xpath", "//input[@name='Contact.Name.First']",data.get(dataset).get("FirstName"));
			Common.textBoxInput("xpath", "//input[@name='Contact.Name.Last']",data.get(dataset).get("LastName"));
			
			Common.textBoxInput("xpath", "//input[@name='Contact.CustomFields.c.company']",data.get(dataset).get("Company"));
			Common.textBoxInput("xpath", "//input[@name='Contact.Phones.MOBILE.Number']",data.get(dataset).get("phone"));
			Common.textBoxInput("xpath", "//input[@name='Contact.Emails.PRIMARY.Address']",data.get(dataset).get("Email"));

			
			Common.clickElement("xpath", "//select[@name='Contact.Address.Country']");
			Common.dropdown("xpath", "//select[@name='Contact.Address.Country']", SelectBy.INDEX,"2");
			
			Common.textBoxInput("xpath", "//input[@name='Contact.Address.PostalCode']",data.get(dataset).get("postcode"));
			
			Common.dropdown("xpath", "//select[@name='Contact.Address.StateOrProvince']", SelectBy.TEXT, data.get(dataset).get("Region"));
			Common.textBoxInput("xpath", "//input[@name='Contact.Address.City']",data.get(dataset).get("City"));
			
			Common.textBoxInput("xpath", "//input[@name='Contact.Address.Street']",data.get(dataset).get("Street"));
			Common.textBoxInput("xpath", "//input[@name='Incident.CustomFields.c.ordernumber']",data.get(dataset).get("OrderNumber"));
			//Common.textBoxInput("xpath", "//input[@id='searchKeyword']",data.get(dataset).get("ProductName"));
			Common.clickElement("xpath", "//span[@id='all_button_subtext']");
			Common.clickElement("xpath", "(//div[contains(@title,'BFD')])[4]");
			Thread.sleep(2000);
			Common.clickElement("xpath", "(//button[@class='rn_DisplayButton'])[1]");
			//Common.dropdown("xpath", "//a[@class='ygtvlabel']", SelectBy.INDEX,"1");
			Common.javascriptclickElement("xpath","(//a[@class='ygtvlabel'])[3]");
			
			Common.textBoxInput("xpath", "//textarea[@class='rn_TextArea']",data.get(dataset).get("Message"));
			Common.clickElement("xpath", "(//button[@Class='rn_DisplayButton'])[2]");	
	       
	       report.addPassLog(expectedResult,"Should display Contact Us page", "Contact Us display successfully", Common.getscreenShotPathforReport("Contact Us page success"));
	       }
	       catch(Exception |Error e)
			{
				report.addFailedLog(expectedResult,"Should display Contact Us page", "Contact Us page display successfully", Common.getscreenShotPathforReport("Contact Us page Failed"));
				e.printStackTrace();
				Assert.fail();
				
		}
			
	}		
			
				
		
		
	
		public void WarrantyRegistration_footerlink(String dataset) throws Exception {
			
			
			String expectedResult="Warranty Registration page";
			try {
				 Sync.waitElementClickable(50, By.xpath("//button[text()='AGREE & PROCEED']"));
			       Common.findElement("xpath", "//button[text()='AGREE & PROCEED']").click();
				   
			
			   Common.actionsKeyPress(Keys.END);
			   Sync.waitElementClickable(30, By.xpath("(//span[@class='mobile-accordion-link-text'])[3]"));
				
				Common.findElement("xpath","(//span[@class='mobile-accordion-link-text'])[3]").click();
				Sync.waitPageLoad();
				
			
				Common.clickElement("xpath", "//select[@title='Product Information']");
				Common.dropdown("xpath", "//select[@title='Product Information']", SelectBy.INDEX,"2");
				
				Common.textBoxInput("xpath", "//input[@title='Product Model Number']",data.get(dataset).get("ProductModelNumber"));
				
				Common.clickElement("xpath", "//input[@title='Approximate Date of Purchase']");
				Thread.sleep(5000);
				if(Common.isElementDisplayed("xpath", "//span[@class='ui-datepicker-month']")) {
				Common.javascriptclickElement("xpath", "//*[@id='ui-datepicker-div'] /table/tbody/tr[1]/td[7]/a[1]");
				}
				else
				{
					Common.clickElement("id","date_field");
					Thread.sleep(2000);
					Common.javascriptclickElement("xpath", "//*[@id='ui-datepicker-div'] /table/tbody/tr[1]/td[7]/a[1]");
				}
				
				Common.textBoxInput("xpath","//input[@class='input-text validate-number']",data.get(dataset).get("LotCode"));
				
				Common.clickElement("xpath", "//select[@title='Place of Purchase']");
				Common.dropdown("xpath","//select[@title='Place of Purchase']", SelectBy.INDEX,"2");
				
			
				Common.textBoxInput("xpath","//input[@title='First Name']",data.get(dataset).get("Firstname"));
				
				Common.textBoxInput("xpath","//input[@title='Last Name']",data.get(dataset).get("LastName"));
				
				Common.textBoxInput("xpath","//input[@title='Email Address']",data.get(dataset).get("Email"));
				
				Common.textBoxInput("xpath","//input[@title='Phone Number']",data.get(dataset).get("phone"));
				
				Common.textBoxInput("xpath","(//input[@title='Street'])[1]",data.get(dataset).get("Street"));
				
				//Common.textBoxInput("xpath","(//input[@title='Street'])[2]",data.get(dataset).get("Street1"));
				Sync.waitElementPresent("xpath", "//input[@title='City']");
			
				Common.textBoxInput("xpath","//input[@title='City']",data.get(dataset).get("City"));
				try {
					Common.dropdown("xpath","//select[@class='input-text validate-select required-entry']", Common.SelectBy.TEXT, data.get(dataset).get("Region"));
				} catch (Exception |Error e) {
					// TODO: handle exception
					Thread.sleep(3000);
					Common.dropdown("xpath", "//select[@class='input-text validate-select required-entry']", Common.SelectBy.TEXT, data.get(dataset).get("Region"));
				}
				Common.textBoxInputClear("xpath", "//input[@title='Zipcode']");
				Common.textBoxInput("xpath","//input[@title='Zipcode']",data.get(dataset).get("postcode"));
				Sync.waitPageLoad();
				//Common.textBoxInput("xpath","//select[@title='Country']",data.get(dataset).get("Country"));
				Common.clickElement("xpath", "//select[@title='Country']");
				Common.dropdown("xpath","//select[@title='Country']", SelectBy.INDEX,"0");
				
				Common.clickElement("xpath","//button[@type='submit']");	
			
			
				 report.addPassLog(expectedResult,"Should display Warranty Registration page", "Warranty Registration display successfully", Common.getscreenShotPathforReport("Warranty Registration page success"));
		       }
		       catch(Exception |Error e)
				{
					report.addFailedLog(expectedResult,"Should display Warranty Registration page", "Warranty Registration page display successfully", Common.getscreenShotPathforReport("Warranty Registration page Failed"));
					e.printStackTrace();
					Assert.fail();
					
			}
				
					}
		public void AGREEPROCEED() {
			// TODO Auto-generated method stub
			Sync.waitElementClickable(30, By.xpath("//button[text()='AGREE & PROCEED']"));
		Common.findElement("xpath", "//button[text()='AGREE & PROCEED']").click();
		}
		
		

	}
	


