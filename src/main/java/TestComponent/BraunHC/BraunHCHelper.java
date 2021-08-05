package TestComponent.BraunHC;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
			int home= Common.findElements("xpath", "//span[@class='icon-search action open']").size();

			 

			System.out.println(home);

			 

			Common.assertionCheckwithReport(home>0, "verifying home page", "Home page contines Logo of product", "sucessfully lands on home page ", "faield to land on Home page");

			 


			 

			Sync.waitElementPresent("xpath", "(//a[@title='Sign In / Sign Up'])[1]");

			Common.clickElement("xpath", "(//a[@title='Sign In / Sign Up'])[1]");


			 

			String footers=Common.getText("xpath", "//span[contains(text(),'Customer Login')]");
			
	System.out.println(footers);

			Common.assertionCheckwithReport(footers.equals("Customer Login"), "Verifying Customer Login page", "It should navigate to Customer Login page", "successfully lands on Customer Login page ","Customer Login page");

			Common.clickElement("xpath", "(//span[contains(text(),'Forgot Your Password?')])[1]");

			String nextpage=Common.getText("xpath","//div[contains(text(),'Please enter your email address below to receive a password reset link')]");
			
			//Common.textBoxInput("id", "email_address", data.get(dataSet).get("Email"));
			
	Sync.waitElementPresent("xpath", "//span[contains(text(),'Reset My Password')]");

			Common.clickElement("xpath", "//span[contains(text(),'Reset My Password')]");
			
			
			String verifying=Common.getText("xpath", "//div[contains(text(),'This is a required field.')]");
			System.out.println(verifying);
			



		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Forgot Password Succes message", "Forgot Password page success message not displayed", Common.getscreenShotPathforReport("Account Creation Failed"));
			Assert.fail();
		}
	}



	public void ClicktheSignbutton() throws Exception{
		Sync.waitElementPresent("xpath", "//a[@title='Sign In / Sign Up']");
		Common.clickElement("xpath", "//a[@title='Sign In / Sign Up']");

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
			
			Sync.waitElementClickable(30, By.xpath("(//button[@title='Add to cart'])[1]"));
			Common.clickElement("xpath" , "(//button[@title='Add to cart'])[1]");
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
			Thread.sleep(2000);
			//Sync.waitElementClickable(30, By.xpath("//button[@data-role='proceed-to-checkout']"));
			//Common.clickElement("xpath" , "//button[@data-role='proceed-to-checkout']");
			Sync.waitElementClickable(30, By.xpath("(//button[@title='Checkout'])[2]"));
			Common.clickElement("xpath" , "(//button[@title='Checkout'])[2]");
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
			Thread.sleep(3000);
			Common.actionsKeyPress(Keys.ESCAPE);
			Common.textBoxInput("name", "city", data.get(dataSet).get("City"));
			Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
			Thread.sleep(3000);
			Common.actionsKeyPress(Keys.ARROW_DOWN);
			Common.dropdown("xpath", "(//select[@name='region_id'])[1]",Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
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
			Sync.waitElementClickable(30, By.xpath("//span[text()='Checkout']"));
			Common.clickElement("xpath" , "//span[text()='Checkout']");
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
	public void  Global_search(String dataSet) throws Exception
	{
		String expectedResult="Search with Product name :"+data.get(dataSet).get("ProductName");
		try {
			Thread.sleep(1000);
			Common.clickElement("xpath", "//label[@class='label js-search-dropdown']");
			Sync.waitElementPresent("id", "search");
			try {
				Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName"));
				
			}catch(Exception e)
			{
				Common.clickElement("xpath", "//label[@class='label js-search-dropdown']");
				Thread.sleep(3000);
				Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName"));
			}
			Common.actionsKeyPress(Keys.ENTER);
			Thread.sleep(10000);
			Common.actionsKeyPress(Keys.DOWN);
			//Common.scrollIntoView("xpath", "(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[1]");
			report.addPassLog(expectedResult, "Should display Search Results Page", "Search results Page display successfully", Common.getscreenShotPathforReport("Search results success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Search Results Page", "Search results Page not display", Common.getscreenShotPathforReport("Search result Failed"));
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
			
			Common.findElement("xpath", "(//img[@class='product-image-photo'])[1]").click();
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
	public void Applypromocode(String dataSet) {
		// TODO Auto-generated method stub
		String expectedResult="Validating Apply promo code ";
		try {
        //Common.scrollIntoView("xpath", "//span[text()='Apply promo code']");
			
			Sync.waitElementPresent("xpath", "//strong[@id='block-discount-heading']");
			Common.findElement("xpath", "//strong[@id='block-discount-heading']").click();
			
			Common.textBoxInput("xpath", "//input[@id='coupon_code']", data.get(dataSet).get("promocodename"));
			
			Common.findElement("xpath", "//span[text()='Apply Discount']").click();
			

			ExtenantReportUtils.addPassLog(expectedResult,"Should display  Apply promo code page", "Apply promo code page display successfully", Common.getscreenShotPathforReport("Apply promo code page success"));

		}
		catch(Exception |Error e)
		{
			ExtenantReportUtils.addFailedLog(expectedResult,"Should display Apply promo code page", "Apply promo code page display successfully", Common.getscreenShotPathforReport("Apply promo code page Failed"));
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
	
		
	
	public void validateNAvigateInstagram() throws Exception
	{
		String expectedResult="It should navigate to instagram page";
		try {
			
			int home= Common.findElements("xpath", "//span[@class='icon-search action open']").size();
			System.out.println(home);
			Common.assertionCheckwithReport(home>0, "verifying home page", "Home page contines Logo of product", "sucessfully lands on home page ", "faield to land on Home page");
			Thread.sleep(4000);
			Common.actionsKeyPress(Keys.END);
			Thread.sleep(5000);
			
			Sync.waitElementPresent("xpath", "//span[contains(text(),'Instagram')]");
			Common.clickElement("xpath", "//span[contains(text(),'Instagram')]");
			Common.switchWindows();
			/*if(Common.isElementDisplayed("xpath", "//h1[contains(text(),'Instagram')]")) {
			String s=Common.getText("xpath", "//h1[contains(text(),'Instagram')]");
		   	System.out.println(s);
		   	Assert.assertEquals(s, "Instagram");
			}else {*/
			Thread.sleep(5000);
			/*String s=Common.getText("xpath", "//h1[contains(text(),'Instagram')]");
			System.out.println(s);
			Assert.assertEquals(s, "Instagram");*/
			String url=Common.getCurrentURL();
			Common.assertionCheckwithReport(url.contains("instagram"),"Verifying instagram page","it shoud navigate to instagram  page", "successfully  navigated to instagram Page", "instagram");	
			//Common.switchToFirstTab();
			
			
		}catch(Exception |Error e) {
			e.printStackTrace();
		ExtenantReportUtils.addFailedLog("To verify the instagram link","should navigate to instagram page", "userunable to navigate to instagram page", Common.getscreenShotPathforReport("failed to navigate to instagram page"));			
			Assert.fail();	
			}
		Common.closeCurrentWindow();
	Common.switchToFirstTab();
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



//divya//





public void ShippingFormvalidation() {
	// TODO Auto-generated method stub
	String expectedResult=" Validating shipping page ";
try {
	Common.clickElement("xpath", "//button[@class='button action continue primary']");
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
public void privacy() {
	String expectedResult = "It should navigate to privacy page";
	try{
		
		int home= Common.findElements("xpath", "//span[@class='icon-search action open']").size();
		 System.out.println(home); 
		 Common.assertionCheckwithReport(home>0, "verifying home page", "Home page contines Logo of product", "sucessfully lands on home page ", "faield to land on Home page");
		
		 Thread.sleep(5000);
		 Common.actionsKeyPress(Keys.END);
		 
		 Common.clickElement("xpath", "//a[contains(text(),'Privacy')]");
		 String title =Common.getPageTitle();
		 System.out.println(title);
		 Common.assertionCheckwithReport(title.contains("Braun Privacy Policy"),"Verifying privacy policy  page","it shoud navigate to privacy policy page", "successfully  navigated to privacy policy Page", "privacy policy");	
		 
		 report.addPassLog("To view  policy button", "Should display privacy page", "user able to navigate to privacy  button\" successfully", Common.getscreenShotPathforReport("privacy page display successfully"));
		
			}catch(Exception |Error e) {
				ExtenantReportUtils.addFailedLog("To view  policy button","should land on Privacy  button", "user unable to navigate to privacy  button", Common.getscreenShotPathforReport("failed to land on privacy button"));			
				Assert.fail();	
				}
		
	
}

	public void Terms_OF_Use() {
		String expectedResult = "It should navigate to privacy page";
		try{
			 Common.actionsKeyPress(Keys.END);
			Thread.sleep(3000);
			 
			 Common.clickElement("xpath", "//a[@title='Terms Of Use']");
			 String url =Common.getCurrentURL();
			 System.out.println(url);
			 Common.assertionCheckwithReport(url.contains("braun-terms-of-use"),"Verifying Terms Of Use   page","it shoud navigate to  Terms Of Use page", "successfully  navigated to Terms Of Use  Page", " Terms Of Use");	
			 
			 
			 report.addPassLog("To view  Terms of use  button", "Should display Terms of usepage", "user able to navigate to Terms of use  button\" successfully", Common.getscreenShotPathforReport("Terms of use display successfully"));
				}catch(Exception |Error e) {
					ExtenantReportUtils.addFailedLog("To view  Terms of use button","should land on Terms of use  button", "user unable to navigate to Terms of use  button", Common.getscreenShotPathforReport("failed to land on Terms of use button"));			
					Assert.fail();	
					}
			
		
	}
	




public void checkoutPage() {
	// TODO Auto-generated method stub
	String expectedResult="Navigate to checkout page";
	try {
		Thread.sleep(3000);
		Sync.waitElementPresent("xpath", "//button[@id='top-cart-btn-checkout']");
		Common.clickElement("xpath", "//button[@id='top-cart-btn-checkout']");
		
		Thread.sleep(5000);
		
		report.addPassLog(expectedResult, "Should display Checkout Page", "Checkout Page display successfully", Common.getscreenShotPathforReport("Checkout page success"));
	}catch(Exception |Error e)
	{
		report.addFailedLog(expectedResult,"Should display Checkout Page", "Checkout Page not displayed", Common.getscreenShotPathforReport("Checkout Failed"));
		Assert.fail();
	}
}




public void Mouseover()throws Exception{
    
    String expectedResult="navigate to Shop category page";

 

    try {
        Sync.waitElementPresent("xpath", "//span[contains(text() , 'Shop')]");
        Common.mouseOver("xpath" , "//span[contains(text() , 'Shop')]");
        Thread.sleep(4000);
        Sync.waitElementPresent("xpath", "//span[text()='Thermometers']");
        Common.clickElement("xpath", "//span[text()='Thermometers']");
        Thread.sleep(4000);
        
        report.addPassLog(expectedResult,"Navigate to Shop category page successfully", "Landed on category page", Common.getscreenShotPathforReport("Shop category page Success"));
                }

 

    catch(Exception |Error e)
    {
        report.addFailedLog(expectedResult,"Navigate to Shop category page successfully", "Not Landed on category page", Common.getscreenShotPathforReport("Shop category page failed"));
        e.printStackTrace();
        Assert.fail();
}    
}    
public void Mouseoverlearn()throws Exception{
       
    String expectedResult="Should lands on Learn page";

 

    try {
        Sync.waitElementPresent("xpath", "//span[contains(text() , 'Learn')]");
        
        Common.mouseOver("xpath" , "//span[contains(text() , 'Learn')]");
        Thread.sleep(4000);
        
        report.addPassLog(expectedResult,"Should lands on  Learn page successfully", "Landed on Learn page", Common.getscreenShotPathforReport("Learn page Success"));
                }

    

    catch(Exception |Error e)
    {
        report.addFailedLog(expectedResult,"Should lands on Learn page successfully", "Not Landed on Learn page", Common.getscreenShotPathforReport("Learn page failed"));
        e.printStackTrace();
        Assert.fail();
}    
}

 


public void Mouseoversupport()throws Exception{
       
    String expectedResult="Should lands on Support page";

 

    try {
        Sync.waitElementPresent("xpath", "//span[contains(text() , 'Support')]");
    
        Common.mouseOver("xpath" , "//span[contains(text() , 'Support')]");
        Thread.sleep(4000);
        
        report.addPassLog(expectedResult,"Should lands on  Support page successfully", "Landed on Support page", Common.getscreenShotPathforReport("Support page Success"));
                }

 

    catch(Exception |Error e)
    {
        report.addFailedLog(expectedResult,"Should lands on Support page successfully", "Not Landed on Support page", Common.getscreenShotPathforReport("Support page failed"));
        e.printStackTrace();
        Assert.fail();
}    
}


public void ViewandEditcart(String dataset) throws Exception
{
	String expectedResult="View and Edit cart page";
	try {
		
		Thread.sleep(5000);
		Sync.waitElementPresent("xpath", "//span[contains(text(),'View and Edit Cart')]");
		Common.clickElement("xpath", "//span[contains(text(),'View and Edit Cart')]");
		Thread.sleep(8000);
		//Sync.waitElementPresent("xpath", "//button[@class='qty-incrementer__increment']");
		//Common.clickElement("xpath", "//button[@class='qty-incrementer__increment']");
		Thread.sleep(5000);
		String number=Common.getText("xpath", "(//input[@type='number'])[2]");
		if(number.equals("2"))
				{
			Common.textBoxInputClear("xpath", "(//input[@type='number'])[2]");
			
			Common.textBoxInput("xpath", "(//input[@type='number'])[2]", data.get(dataset).get("number"));
			
				}
		Sync.waitElementPresent("xpath", "//span[contains(text(), 'Update Shopping Cart')]");
		Common.clickElement("xpath", "//span[contains(text(), 'Update Shopping Cart')]");
		
		
		
		//int quantity= Common.findElements("xpath", "//span[contains(text(),'(Shipping - Free Shipping)')]").size();

		//int quantity= Common.findElements("xpath", "//span[contains(text(),'(Shipping - Free Shipping)')]").size();
		int quantity= Common.findElements("xpath", "//input[@class='input-text qty qty-incrementer__input']").size();
		System.out.println(quantity); 
		Common.assertionCheckwithReport(quantity>0, "it should navigate to quantity increase", "it should navigate to quantity increase ", "sucessfully lands on quantity increase ", "faield to increase quantity");
		
		report.addPassLog(expectedResult, "Should display Mini Cart Page", "Mini Cart Page display successfully", Common.getscreenShotPathforReport("Mini Cart page success"));
	}catch(Exception |Error e)
	{
		report.addFailedLog(expectedResult,"Should display Mini Cart Page", "Mini Cart Page not displayed", Common.getscreenShotPathforReport("Mini Cart Failed"));
		e.printStackTrace();
		Assert.fail();
	}
}


public void compareproducts() {
	String expectedResult ="lands on product list page";
	try {
	
	
	Common.mouseOver("xpath", "(//img[@class='product-image-photo'])[1]");
	Common.clickElement("xpath", "(//span[text()='Compare'])[1]");
	Common.mouseOver("xpath", "(//img[@class='product-image-photo'])[3]");
	Common.clickElement("xpath", "(//span[text()='Compare'])[2]");
	Common.actionsKeyPress(Keys.PAGE_DOWN);
	Common.mouseOver("xpath", "//span[@class='product-image-container product-image-container-10_category_page_grid']");
	Common.clickElement("xpath", "(//span[text()='Compare'])[3]");
	Common.actionsKeyPress(Keys.PAGE_DOWN);
	Common.actionsKeyPress(Keys.PAGE_DOWN);
	Common.mouseOver("xpath", "(//img[@class='product-image-photo'])[7]");
	Common.clickElement("xpath", "(//span[text()='Compare'])[4]");
	Common.actionsKeyPress(Keys.ARROW_DOWN);
	Common.actionsKeyPress(Keys.PAGE_DOWN);
	Common.mouseOver("xpath", "(//img[@class='product-image-photo'])[11]");
	Common.clickElement("xpath", "(//span[text()='Compare'])[6]");
	int success= Common.findElements("xpath", "//div[text()='Maximum of 4 compared products allowed, please remove one and try again.']").size();
	System.out.println(success);
	
   // Common.assertionCheckwithReport(success>0,"verify error message","Maximum of 4 compared products allowed, please remove one and try again.","successfully lands on error page","failed to land on error page");
   // Common.assertionCheckwithReport(url.contains("braun-terms-of-use"),"Verifying Terms Of Use   page","it shoud navigate to  Terms Of Use page", "successfully  navigated to Terms Of Use  Page", " Terms Of Use");	
	 
    report.addPassLog(expectedResult,"Should display error message page", "error message display successfully", Common.getscreenShotPathforReport("error message display successfully"));
       }
       catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display error message", "error message display successfully", Common.getscreenShotPathforReport("Failed to display errror message"));
			e.printStackTrace();
			Assert.fail();
	
	
	
}
}



public void AddingTwo_Products()throws Exception{
    
    String expectedResult="Should lands on PLP page";

 

    try {
       
    	Common.mouseOver("xpath", "(//img[@class='product-image-photo'])[1]");
        
    	 Common.clickElement("xpath", "(//span[text()='Add to cart'])[1]");
        
        
    	 Common.mouseOver("xpath", "(//img[@class='product-image-photo'])[3]");
         
    	 Common.clickElement("xpath", "(//span[text()='Add to cart'])[2]");
        
        
        report.addPassLog(expectedResult,"Should lands on  Support page successfully", "Landed on Support page", Common.getscreenShotPathforReport("Support page Success"));
                }

 

    catch(Exception |Error e)
    {
        report.addFailedLog(expectedResult,"Should lands on Support page successfully", "Not Landed on Support page", Common.getscreenShotPathforReport("Support page failed"));
        e.printStackTrace();
        Assert.fail();
}   
}


public void Checkout_with_differnt_proucts()throws Exception{
    
    String expectedResult="Should lands on PLP page";

 
 
    try {
       
    	Sync.waitElementPresent("xpath", "(//img[@class='product-image-photo'])[1]");
        
    	 Common.clickElement("xpath", "(//img[@class='product-image-photo'])[1]");
    	 
    	 
    	 
        
    	 Sync.waitElementPresent("xpath", "//button[@id='product-addtocart-button']");
         
    	 Common.clickElement("xpath", "//button[@id='product-addtocart-button']");
        
    	 mouseoverproduct();
    	
        
        report.addPassLog(expectedResult,"Should lands on  Support page successfully", "Landed on Support page", Common.getscreenShotPathforReport("Support page Success"));
                }

 

    catch(Exception |Error e)
    {
        report.addFailedLog(expectedResult,"Should lands on Support page successfully", "Not Landed on Support page", Common.getscreenShotPathforReport("Support page failed"));
        e.printStackTrace();
        Assert.fail();
}   
}




public void mouseoverproduct()throws Exception{
    
    String expectedResult="navigate to Shop category page";

 

    try {
    	 
     
    	/*  Sync.waitElementPresent("xpath", "//a[@title='Nasal Aspirator']");
        Common.clickElement("xpath", "//a[@title='Nasal Aspirator']");
        
        Sync.waitElementPresent("xpath", "(//button[@title='Add to cart'])[1]");
         Common.clickElement("xpath", "(//button[@title='Add to cart'])[1]");
    */
         Sync.waitElementPresent("xpath", "//a[@title='Blood Pressure Monitors']");
        Common.clickElement("xpath", "//a[@title='Blood Pressure Monitors']");
        
            Sync.waitElementPresent("xpath", "(//button[@title='Add to cart'])[1]");
        Common.clickElement("xpath", "(//button[@title='Add to cart'])[1]");
        
        
        
    
      
        
        report.addPassLog(expectedResult,"Navigate to Shop category page successfully", "Landed on category page", Common.getscreenShotPathforReport("Shop category page Success"));
                }

 

    catch(Exception |Error e)
    {
        report.addFailedLog(expectedResult,"Navigate to Shop category page successfully", "Not Landed on category page", Common.getscreenShotPathforReport("Shop category page failed"));
        e.printStackTrace();
        Assert.fail();
}    


}
public void Two_products_in_plp()throws Exception{
    
    String expectedResult="Should lands on PLP page";

 
 
    try {
    	 Common.actionsKeyPress(Keys.PAGE_DOWN);
    	 
    		
    	
    		Common.mouseOver("xpath", "(//img[@class='product-image-photo'])[1]");
    		Common.clickElement("xpath", "(//span[text()='Add to cart'])[1]");
    		
    		Common.mouseOver("xpath", "(//img[@class='product-image-photo'])[3]");
    		Common.clickElement("xpath", "(//span[text()='Add to cart'])[2]");
    		
    	 mouseoverproduct();
    	
        
        report.addPassLog(expectedResult,"Should lands on  Support page successfully", "Landed on Support page", Common.getscreenShotPathforReport("Support page Success"));
                }

 

    catch(Exception |Error e)
    {
        report.addFailedLog(expectedResult,"Should lands on Support page successfully", "Not Landed on Support page", Common.getscreenShotPathforReport("Support page failed"));
        e.printStackTrace();
        Assert.fail();
}   
}

public void NewBillingaddress(String dataSet) throws Exception {
	String 	expectedresult = "Should add new billing address";
		try {
			Thread.sleep(3000);
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		
	Sync.waitElementVisible("xpath", "//span[text()='Billing address is same as shipping']");
		Common.clickElement("xpath", "//span[text()='Billing address is same as shipping']");
				Thread.sleep(3000);
		if(Common.isElementDisplayed("xpath", "//select[@name='billing_address_id']")) {
			Common.clickElement("xpath", "//select[@name='billing_address_id']");
			
		Common.clickElement("xpath", "//select[@name='billing_address_id']/option[text()='New Address']");
	//Common.dropdown("xpath", "//select[@name='billing_address_id']", SelectBy.VALUE, data.get(dataSet).get("UserName"));
		}
		Thread.sleep(2000);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
	
		Common.textBoxInput("xpath", "(//input[@name='firstname'])[2]", data.get(dataSet).get("FirstName"));
		Common.textBoxInput("xpath", "(//input[@name='lastname'])[2]", data.get(dataSet).get("LastName"));
		
		Common.textBoxInput("xpath", "(//input[@class='input-text'])[14]", data.get(dataSet).get("Street"));
		Common.textBoxInput("xpath", "(//input[@name='city'])[2]", data.get(dataSet).get("City"));
		
		Common.textBoxInput("xpath", "(//input[@name='postcode'])[2]", data.get(dataSet).get("postcode"));
		Common.dropdown("xpath", "(//select[@name='region_id'])[2]",Common.SelectBy.TEXT, data.get(dataSet).get("Region"));

		Common.textBoxInput("xpath", "(//input[@name='telephone'])[2]", data.get(dataSet).get("phone"));
		Common.clickElement("xpath", "//button[@class='action action-update']");
		
		Common.actionsKeyPress(Keys.PAGE_UP);
		
		report.addFailedLog(expectedresult, "Billing address will be  added", Common.getscreenShotPathforReport("successfully add billing address"));
		} catch(Exception | Error e) {
			report.addFailedLog(expectedresult, "Billing address is not  added", Common.getscreenShotPathforReport("Failed to add billing address"));
			Assert.fail();
		}
		
	}





public void RegisterUser_ShippingAddress() throws Exception
{
	String expectedResult="Navigate to Shipping Address Page";
	try {
		Thread.sleep(3000);
		Sync.waitElementPresent("xpath", "//span[text()='New Address']");
		Common.clickElement("xpath", "//span[text()='New Address']");
		
		Thread.sleep(5000);
		
		NewShippingAddress("NewShippingAddress");
		Sync.waitElementPresent("xpath", "//button[@class='button action continue primary']");
		Common.clickElement("xpath", "//button[@class='button action continue primary']");
		
		report.addPassLog(expectedResult, "Should display Shipping Address Page", "Shipping Address Page display successfully", Common.getscreenShotPathforReport("Shipping Address page display  success"));
	}catch(Exception |Error e)
	{
		report.addFailedLog(expectedResult,"Should display Shipping Address Page", "Shipping Address Page not displayed", Common.getscreenShotPathforReport("Shipping Address Failed"));
		Assert.fail();
	}
}


public void NewShippingAddress(String dataSet) throws InterruptedException {
	try {
		
		Common.textBoxInput("name", "firstname", data.get(dataSet).get("FirstName"));
		Common.textBoxInput("name", "lastname", data.get(dataSet).get("LastName"));
		Common.textBoxInput("name", "street[0]", data.get(dataSet).get("Street"));
		
		
		Common.textBoxInput("name", "city", data.get(dataSet).get("City"));
		Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.dropdown("xpath", "(//select[@name='region_id'])[1]",Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
		Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
		
		Sync.waitElementPresent("xpath", "//button[@class='action primary action-save-address']");
		Common.clickElement("xpath", "//button[@class='action primary action-save-address']");
		
				
	}catch(Exception |Error e)
	{
		e.printStackTrace();
		Assert.fail();
	}
}



public void ResgisterUser_Signout() throws InterruptedException {
	String expectedResult="naviagte to signout page";


	try {
		Common.clickElement("xpath" , "//div[@data-target='dropdown']");
		Common.clickElement("xpath", "(//a[text()='Sign Out'])[1]");
		
		
		report.addPassLog(expectedResult, "naviagte to signout page", "naviagte to signout page successfully", Common.getscreenShotPathforReport("naviagte to signout page success"));
	}catch(Exception |Error e)
	{
		report.addFailedLog(expectedResult,"naviagte to signout page", "signout page not displayed", Common.getscreenShotPathforReport("naviagte to signout pageFailed"));
		e.printStackTrace();
		Assert.fail();

	}
}





public void Signin_shipping_page(String dataSet) throws Exception {
	// TODO Auto-generated method stub
	String expectedResult="Land on login page";
	try {
		
		Common.textBoxInput("xpath", "(//input[@id='customer-email'])[1]", data.get(dataSet).get("Email"));
		Common.textBoxInput("xpath", "//input[@id='customer-password']", data.get(dataSet).get("Password"));
		
		//Common.mouseOverClick("xpath", "//span[text()='Login']");
		Common.javascriptclickElement("xpath", "//button[@data-action='checkout-method-login']");
		
		
		
		int title= Common.findElements("xpath", "//div[text()='Shipping address']").size();

		 

		System.out.println(title);

		 

		Common.assertionCheckwithReport(title>0, "verifying Shipping address page", "shipping address page should dispaly", "sucessfully lands onshipping address page ", "faield to land on shipping address page");

		 


		

		report.addPassLog(expectedResult, "Should display Registeruser login Page", "Registeruser login Page display successfully", Common.getscreenShotPathforReport("Login page success"));
	}catch(Exception |Error e)
	{
		report.addFailedLog(expectedResult,"Should display  Registeruser login  Page", " Registeruser login Page not displayed", Common.getscreenShotPathforReport("Login page Failed"));
		Assert.fail();
	}		
}


public void order_verification() throws Exception {
	try {
	String OrderID1=Common.findElement("xpath", "//a[@class='order-number']").getText();
	Common.clickElement("xpath", "(//a[@class='order-number'])");
	Sync.waitPageLoad();
	
	String OrderID2=Common.findElement("xpath", "(//span[@class='base'])").getText();
	Common.assertionCheckwithReport(OrderID2.contains(OrderID1),"Verifying My order page","The ordered id and my orders order id should be equal", "The ordered id and my orders order id is equal", " order id");
}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("verifying My order page","The ordered id and my orders order id should be equal", "The ordered id and my orders order id is not equal", Common.getscreenShotPathforReport("failed order id"));			
		Assert.fail();	
		}
	}



public void Tax_validation() {
	// TODO Auto-generated method stub
	String expectedResult=" Validating Tax  amount for a product";
try {
	

	int title= Common.findElements("xpath", "//span[text()='Order summary']").size();

	 

	System.out.println(title);

	 

	Common.assertionCheckwithReport(title>0, "verifying Order summary block page", " Order summary block page should dispaly", "sucessfully lands on Order summary block page ", "faield to land on Order summary block page");

	 if(Common.isElementDisplayed("xpath", "(//td[@class='amount'])[3]"))
	 
	 { 
		 int taxamount=Common.findElements("xpath", "(//tr[@class='totals-tax'])[1]").size();
		
		 Common.assertionCheckwithReport(taxamount>0, "verifying Tax amount in the page", "tax amount should be dispalyed","sucessfully display the tax amount", "faield to dispaly tax amount");
		
		 
		 System.out.println("The tax amount should be displayed");
	 } else {
		 System.out.println("The tax amount is not  displayed");
	 }
	
	
}
	catch(Exception |Error e) {
	 	e.printStackTrace();   
		ExtenantReportUtils.addFailedLog("verifying Tax amount in the page", "tax amount should be dispalyed", "faield to dispaly tax amount", Common.getscreenShotPathforReport("Tax_valadation"));
		Assert.fail();
	}
	

}


public void Mutiple_products()throws Exception{
    
    String expectedResult="navigate to Shop category page";

 

    try {
        Sync.waitElementPresent("xpath", "//span[contains(text() , 'Shop')]");
        Common.mouseOver("xpath" , "//span[contains(text() , 'Shop')]");
        Thread.sleep(4000);
        Sync.waitElementPresent("xpath", "//span[text()='Thermometers']");
        Common.clickElement("xpath", "//span[text()='Thermometers']");
        
        
        
        Sync.waitElementPresent("xpath", "(//img[@class='product-image-photo'])[3]");
        Common.clickElement("xpath", "(//img[@class='product-image-photo'])[3]");
        
        Common.clickElement("xpath", "(//span[text()='Add to cart'])[1]");
        
        
        Sync.waitElementPresent("xpath", "(//img[@class='product-image-photo'])[5]");
        Common.clickElement("xpath", "(//img[@class='product-image-photo'])[5]");
        
        Common.clickElement("xpath", "(//span[text()='Add to cart'])[2]");
        
        
        report.addPassLog(expectedResult,"Navigate to Shop category page successfully", "Landed on category page", Common.getscreenShotPathforReport("Shop category page Success"));
                }

 

    catch(Exception |Error e)
    {
        report.addFailedLog(expectedResult,"Navigate to Shop category page successfully", "Not Landed on category page", Common.getscreenShotPathforReport("Shop category page failed"));
        e.printStackTrace();
        Assert.fail();
} 
}




public void NoTAX_shippingAddress() throws InterruptedException {
	String expectedResult="Should navigate to Shipping address page";
	try {
		Sync.waitElementClickable(30, By.xpath("//span[text()='Checkout']"));
		Common.clickElement("xpath" , "//span[text()='Checkout']");
		Thread.sleep(4000);
		//String S=Common.getText("xpath", "//*[@id='shipping']/div[1]");
		//System.out.println(S);
		//Assert.assertTrue(Common.isElementDisplayed("xpath", "//*[@id='shipping']/div[1]"));
		//Thread.sleep(3000);	
		String s=Common.getText("xpath", "//div[text()='Shipping address']");
		System.out.println(s);
		Assert.assertTrue(Common.isElementDisplayed("xpath", "//div[text()='Shipping address']"));
		Thread.sleep(3000);	
		
		NoTAXAddress("NoTaxShippingAddress");
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


public void NoTAXAddress(String dataSet) throws InterruptedException {
	try {
		Common.textBoxInput("id", "customer-email", data.get(dataSet).get("Email"));
		Thread.sleep(3000);
		Common.textBoxInput("name", "firstname", data.get(dataSet).get("FirstName"));
		Common.textBoxInput("name", "lastname", data.get(dataSet).get("LastName"));
		Common.textBoxInput("name", "street[0]", data.get(dataSet).get("Street"));
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.ESCAPE);
		Common.textBoxInput("name", "city", data.get(dataSet).get("City"));
		Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.dropdown("xpath", "(//select[@name='region_id'])[1]",Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
		Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
		Thread.sleep(3000);		
	}catch(Exception |Error e)
	{
		e.printStackTrace();
		Assert.fail();
	}
}


public void NO_TAxRegisterUser_ShippingAddress() throws Exception
{
	String expectedResult="Navigate to Shipping Address Page";
	try {
		Thread.sleep(3000);
		Sync.waitElementPresent("xpath", "//span[text()='New Address']");
		Common.clickElement("xpath", "//span[text()='New Address']");
		
		Thread.sleep(5000);
		
		 ResgisternewAddress("NoTaxShippingAddress");
		Sync.waitElementPresent("xpath", "//button[@class='button action continue primary']");
		Common.clickElement("xpath", "//button[@class='button action continue primary']");
		
		report.addPassLog(expectedResult, "Should display Shipping Address Page", "Shipping Address Page display successfully", Common.getscreenShotPathforReport("Shipping Address page display  success"));
	}catch(Exception |Error e)
	{
		report.addFailedLog(expectedResult,"Should display Shipping Address Page", "Shipping Address Page not displayed", Common.getscreenShotPathforReport("Shipping Address Failed"));
		Assert.fail();
	}
}



public void ResgisternewAddress(String dataSet) throws InterruptedException {
	try {
		
		Common.textBoxInput("name", "firstname", data.get(dataSet).get("FirstName"));
		Common.textBoxInput("name", "lastname", data.get(dataSet).get("LastName"));
		Common.textBoxInput("name", "street[0]", data.get(dataSet).get("Street"));
		
		
		Common.textBoxInput("name", "city", data.get(dataSet).get("City"));
		Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.dropdown("xpath", "(//select[@name='region_id'])[2]",Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
		Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
		
		Sync.waitElementPresent("xpath", "//button[@class='action primary action-save-address']");
		Common.clickElement("xpath", "//button[@class='action primary action-save-address']");
		
				
	}catch(Exception |Error e)
	{
		e.printStackTrace();
		Assert.fail();
	}
}

public void selectnewaddress() throws Exception
{
	String expectedResult="Navigate to Shipping Address Page";
	try {
		Thread.sleep(3000);
		Sync.waitElementPresent("xpath", "//span[text()='New Address']");
		Common.clickElement("xpath", "//span[text()='New Address']");
		
		Thread.sleep(5000);
		
		NoTAXAddress("NoTaxShippingAddress");
		Sync.waitElementPresent("xpath", "//button[@class='button action continue primary']");
		Common.clickElement("xpath", "//button[@class='button action continue primary']");
		
		report.addPassLog(expectedResult, "Should display Shipping Address Page", "Shipping Address Page display successfully", Common.getscreenShotPathforReport("Shipping Address page display  success"));
	}catch(Exception |Error e)
	{
		report.addFailedLog(expectedResult,"Should display Shipping Address Page", "Shipping Address Page not displayed", Common.getscreenShotPathforReport("Shipping Address Failed"));
		Assert.fail();
	}
}







public void invalidCreditCard(String dataSet) throws Exception
{
	String expectedResult="Payment Method with invalid Credit card";
	try {
		UpdatePaymentAndSubmitOrder(dataSet);

		if(Common.findElements("xpath", "//div[@class='message message-error']").size()>0)
		{	
			UpdatePaymentAndSubmitOrder(dataSet);
		}

		Common.clickElement("xpath", "//span[contains(text(),'Place Order')]");
		Thread.sleep(3000);

	Common.switchFrames("xpath", "//iframe[@id='paymetric_xisecure_frame']");
		String Errormessage=Common.getText("xpath", "//div[@id='c-cardnumber-error']");
		System.out.println(Errormessage);
		Assert.assertEquals(Errormessage, "Please enter a valid card number");
		report.addPassLog(expectedResult, "Should display Error message for Credit card number feild", "Error message for Credit card number feild display successfully", Common.getscreenShotPathforReport("Error message credit card success"));
	}catch(Exception |Error e)
	{
		report.addFailedLog(expectedResult,"Should display Error message for Credit card number feild", "Error message for Credit card number feild not display", Common.getscreenShotPathforReport("Error message credit card Failed"));
		e.printStackTrace();
		Assert.fail();
	}
}





public void mouseoverheaderlink()throws Exception{
    
    String expectedResult="navigate to Shop category page";

 

    try {
    	   Sync.waitElementPresent("xpath", "//span[contains(text() , 'Shop')]");
        Common.mouseOver("xpath" , "//span[contains(text() , 'Shop')]");
        Thread.sleep(4000);

        Sync.waitElementPresent("xpath", "//a[@title='Nasal Aspirator']");
        Common.clickElement("xpath", "//a[@title='Nasal Aspirator']");
       
       Mouseover();
        Sync.waitElementPresent("xpath", "//a[@title='Blood Pressure Monitors']");
        Common.clickElement("xpath", "//a[@title='Blood Pressure Monitors']");
        Mouseover();
          
          Sync.waitElementPresent("xpath", "//a[@title='Pulse Oximeter']");
          Common.clickElement("xpath", "//a[@title='Pulse Oximeter']");
          Mouseover();
          
          
          Sync.waitElementPresent("xpath", "//a[@title='Parts & Accessories']");
          Common.clickElement("xpath", "//a[@title='Parts & Accessories']");
          

       
    
      
        
        report.addPassLog(expectedResult,"Navigate to Shop category page successfully", "Landed on category page", Common.getscreenShotPathforReport("Shop category page Success"));
                }

 

    catch(Exception |Error e)
    {
        report.addFailedLog(expectedResult,"Navigate to Shop category page successfully", "Not Landed on category page", Common.getscreenShotPathforReport("Shop category page failed"));
        e.printStackTrace();
        Assert.fail();
}    

}



public void removeproduct()throws Exception{
    
    String expectedResult="navigate toto shipping page  page";

 

    try {
    	 
     
      Sync.waitElementPresent("xpath", "(//button[@class='qty-incrementer__decrement'])[2]");
        Common.clickElement("xpath", "(//button[@class='qty-incrementer__decrement'])[2]");
        

         Sync.waitElementPresent("xpath", "(//button[@class='qty-incrementer__decrement'])[2]");
        Common.clickElement("xpath", "(//button[@class='qty-incrementer__decrement'])[2]");
        
            Sync.waitElementPresent("xpath", "//button[@title='Update Shopping Cart']");
        Common.clickElement("xpath", "//button[@title='Update Shopping Cart']");
        
       
    
      
        
        report.addPassLog(expectedResult,"Navigate to shipping page successfully", "Landed on to shipping page ", Common.getscreenShotPathforReport("to shipping page  page Success"));
                }

 

    catch(Exception |Error e)
    {
        report.addFailedLog(expectedResult,"Navigate to to shipping page page successfully", "Not Landed on to shipping page ", Common.getscreenShotPathforReport("to shipping page  failed"));
        e.printStackTrace();
        Assert.fail();
}    


}

public void GuestShippingaddress(String Street,String City,String postcode,String Region) throws InterruptedException {
	String expectedResult="Should navigate to Shipping address page";
	try {
		Sync.waitElementClickable(30, By.xpath("//button[@data-role='proceed-to-checkout']"));
		Common.clickElement("xpath" , "//button[@data-role='proceed-to-checkout']");
		Thread.sleep(4000);
		String S=Common.getText("xpath", "//*[@id='shipping']/div[1]");
		System.out.println(S);
		Assert.assertTrue(Common.isElementDisplayed("xpath", "//*[@id='shipping']/div[1]"));
		Thread.sleep(3000);
		ShippingAddress("Guest_shipping", Street, City, postcode, Region);
		Common.scrollIntoView("xpath", "//div[@class='checkout-shipping-method']/div");
		Thread.sleep(3000);
		Common.clickElement("xpath", "//*[@id='checkout-shipping-method-load']/table/tbody/tr/td[1]/label");
		Common.clickElement("xpath", "//button[@data-role='opc-continue']/span");
		report.addPassLog(expectedResult, "Should display Shipping address Page", "Shipping address page display successfully", Common.getscreenShotPathforReport("Shipping address page success"));
	}catch(Exception |Error e)
	{
		e.printStackTrace();
		report.addFailedLog(expectedResult,"Should display  Shipping address Page", "Shipping address Page not displayed", Common.getscreenShotPathforReport("Shipping address page Failed"));
		e.printStackTrace();
		Assert.fail();
	}
}




public void ShippingAddress(String dataSet,String Street,String City,String postcode,String Region) throws InterruptedException {
	try {
		Common.textBoxInput("id", "customer-email", data.get(dataSet).get("Email"));
		Thread.sleep(3000);
		Common.textBoxInput("name", "firstname", data.get(dataSet).get("FirstName"));
		Common.textBoxInput("name", "lastname", data.get(dataSet).get("LastName"));
		Common.textBoxInput("name", "street[0]", Street);
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.ESCAPE);
		Common.textBoxInput("name", "city", City);
		Common.textBoxInput("name", "postcode", postcode);
		Common.dropdown("name", "region_id",Common.SelectBy.TEXT, Region);
		Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
		Thread.sleep(3000);		
	}catch(Exception |Error e)
	{
		e.printStackTrace();
		Assert.fail();
	}
}

public void Taxcalucaltion(String taxpercent) throws Exception{
	try{
	
	
	Thread.sleep(3000);
	
	//String taxpercent=data.get(dataset).get("Tax");
	 Float giventaxvalue=Float.valueOf(taxpercent);
	
	String subtotla=Common.getText("xpath", "//tr[@class='totals sub']/td/span").replace("$", "");
	 // subtotla.replace("", newChar)
	Float subtotlaValue=Float.valueOf(subtotla);
	
	String shippingammount=Common.getText("xpath", "//span[@data-th='Shipping']").replace("$", "");
	Float shippingammountvalue=Float.valueOf(shippingammount);
	
	String TaxAmmount=Common.getText("xpath", "//td[@data-th='Tax']/span").replace("$", "");
	Float Taxammountvalue=Float.valueOf(TaxAmmount);
	
	String TotalAmmount=Common.getText("xpath", "//tr[@class='grand totals']//span").replace("$", "");
	Float Totalammountvalue=Float.valueOf(Taxammountvalue);
    Float calucaltedvalue= ((subtotlaValue+shippingammountvalue)*giventaxvalue)/100;
    
    NumberFormat nf= NumberFormat.getInstance();
    nf.setMaximumFractionDigits(2);
    String  userpaneltaxvalue=nf.format(calucaltedvalue);
  
    System.out.println(TaxAmmount);
    System.out.println(userpaneltaxvalue);      
    Common.assertionCheckwithReport(userpaneltaxvalue.equals(TaxAmmount), "verifying tax calculation", "tax rate is matches to given shipping address tax ","successfully tax rate is matches to given shipping address tax", "tax rate is not matches to given shipping address tax");
	
	}
 catch(Exception |Error e)
	{
		report.addFailedLog("verifying tax calculation", "getting price values from shipping page  ", "Faield to get price value from shipping page", Common.getscreenShotPathforReport("TaxRates"));

		e.printStackTrace();
		Assert.fail();
		
}
	
	
	
	}
	
	
	public void AGREEPROCEED() {
		// TODO Auto-generated method stub
		int sizes=Common.findElements("xpath", "//button[text()='AGREE & PROCEED']").size();
		if(sizes>0){
		Sync.waitElementClickable(30, By.xpath("//button[text()='AGREE & PROCEED']"));
	   Common.findElement("xpath", "//button[text()='AGREE & PROCEED']").click();
		}
	}
	
	
	public void prepareTaxData(String fileName)
	{
	try{
		
		
		File file=new File(System.getProperty("user.dir")+"/src/test/resources/"+fileName);
		XSSFWorkbook workbook;
		XSSFSheet sheet;
		Row row;
		Cell cell;
		int rowcount;
		if(!(file.exists()))
		{
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("TaxDetails");
		CellStyle cs = workbook.createCellStyle();
		cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cs.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
		Font f = workbook.createFont();
		f.setBold(true);
		cs.setFont(f);	 
		cs.setAlignment(HorizontalAlignment.RIGHT);
		row = sheet.createRow(0);
		cell = row.createCell(0);
		cell.setCellStyle(cs);
		cell.setCellValue("Orders details");
		
		    
		row = sheet.createRow(1);
		cell = row.createCell(0);
		cell.setCellStyle(cs);
		cell.setCellValue("OrderId");
		cell = row.createCell(1);
		cell.setCellStyle(cs);
		cell.setCellValue("SubTotal");
		cell = row.createCell(2);
		cell.setCellStyle(cs);
		cell.setCellValue("ShippingAmount");
		cell=row.createCell(3);
		cell.setCellStyle(cs);
		cell.setCellValue("TaxAmount");
		cell=row.createCell(4);
		cell.setCellStyle(cs);
		cell.setCellValue("TotalAmount");
		cell=row.createCell(5);
		cell.setCellStyle(cs);
		cell.setCellValue("ActualTax");
		cell=row.createCell(6);
		cell.setCellStyle(cs);
		cell.setCellValue("ExpectedTax");
		cell=row.createCell(7);
		cell.setCellStyle(cs);
		cell.setCellValue("status");
		rowcount=2;
		}
		
		else
		{
		workbook = new XSSFWorkbook(new FileInputStream(file));
		sheet=workbook.getSheet("TaxDetails");	
		rowcount=sheet.getLastRowNum()+1;
		}
		/*row = sheet.createRow(rowcount);
		cell = row.createCell(0);*/

		FileOutputStream fileOut = new FileOutputStream(file);
		workbook.write(fileOut);
		fileOut.flush();
		fileOut.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	

	public void writeResultstoXLSx(String OrderId,float SubTotal,float ShippingAmount,float TaxAmount, float TotalAmount,float ActualTax ,float ExpectedTax)
	{
	try{
		
		File file=new File(System.getProperty("user.dir")+"/src/test/resources/BraunTaxDetails.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
		XSSFSheet sheet;
		Row row;
		Cell cell;
		int rowcount;
		sheet = workbook.getSheet("TaxDetails");
		
		if((workbook.getSheet("TaxDetails"))==null)
		{
		sheet = workbook.createSheet("TaxDetails");
		CellStyle cs = workbook.createCellStyle();
		cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cs.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
		Font f = workbook.createFont();
		f.setBold(true);
		cs.setFont(f);	 
		cs.setAlignment(HorizontalAlignment.RIGHT);
		row = sheet.createRow(0);
		cell = row.createCell(0);
		cell.setCellStyle(cs);
		cell.setCellValue("Orders details");
		
		row = sheet.createRow(1);
		cell = row.createCell(0);
		cell.setCellStyle(cs);
		cell.setCellValue("OrderId");
		cell = row.createCell(1);
		cell.setCellStyle(cs);
		cell.setCellValue("SubTotal");
		cell = row.createCell(2);
		cell.setCellStyle(cs);
		cell.setCellValue("ShippingAmount");
		cell=row.createCell(3);
		cell.setCellStyle(cs);
		cell.setCellValue("TaxAmount");
		cell=row.createCell(4);
		cell.setCellStyle(cs);
		cell.setCellValue("TotalAmount");
		cell=row.createCell(5);
		cell.setCellStyle(cs);
		cell.setCellValue("ActualTax");
		cell=row.createCell(6);
		cell.setCellStyle(cs);
		cell.setCellValue("ExpectedTax");
		
		rowcount=2;
		
		}
		
		else
		{
		
		sheet=workbook.getSheet("TaxDetails");	
		rowcount=sheet.getLastRowNum()+1;
		}
		row = sheet.createRow(rowcount);
		cell = row.createCell(0);
		cell.setCellValue(OrderId);
		cell = row.createCell(1);
		cell.setCellType(CellType.NUMERIC);
		cell.setCellValue(SubTotal);
		cell = row.createCell(2);
		cell.setCellType(CellType.NUMERIC);
		cell.setCellValue(ShippingAmount);
		cell = row.createCell(3);
		cell.setCellType(CellType.NUMERIC);
		cell.setCellValue(TaxAmount);
		cell = row.createCell(4);
		cell.setCellType(CellType.NUMERIC);
		cell.setCellValue(TotalAmount);
		cell = row.createCell(5);
		cell.setCellType(CellType.NUMERIC);
		cell.setCellValue(ActualTax);
		cell = row.createCell(6);
		cell.setCellType(CellType.NUMERIC);
		cell.setCellValue(ExpectedTax);
		cell = row.createCell(7);
		cell.setCellType(CellType.STRING);
		String status="Fail";
		if(ActualTax==ExpectedTax)
		{
			status="pass";
		}
		cell.setCellValue(status);
		
		FileOutputStream fileOut = new FileOutputStream(file);
		workbook.write(fileOut);
		fileOut.flush();
		fileOut.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
}



