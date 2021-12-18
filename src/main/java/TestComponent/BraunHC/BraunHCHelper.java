package TestComponent.BraunHC;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
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
			Sync.waitElementClickable(30, By.xpath("(//a[contains(text(),'Sign In / Sign Up')])[1]"));
			Common.findElement("xpath", "(//a[contains(text(),'Sign In / Sign Up')])[1]").click();
			Thread.sleep(3000);
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
			e.printStackTrace();

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
			Common.actionsKeyPress(Keys.ARROW_DOWN);
			
			
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
		Thread.sleep(5000);
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
			Sync.waitElementPresent("xpath", "//a[@title='Sign In / Sign Up']");
			Common.clickElement("xpath", "//a[@title='Sign In / Sign Up']");
			Sync.waitElementPresent("xpath", "//a[@class='action create primary']");
			
			Common.clickElement("xpath", "//a[@class='action create primary']");
			Thread.sleep(5000);
			report.addPassLog(expectedResult, "Should display Account Creation page", "Account Creation page display successfully", Common.getscreenShotPathforReport("Account Creation"));
			
			Common.textBoxInput("id", "firstname", data.get(dataSet).get("FirstName"));
			Common.textBoxInput("id", "lastname", data.get(dataSet).get("LastName"));
			Common.actionsKeyPress(Keys.ARROW_DOWN);
			
			//Sync.waitElementPresent("xpath", "//input[@title='Sign Up for Newsletter']");
			//Common.clickElement("xpath", "//input[@title='Sign Up for Newsletter']");
			

			Common.textBoxInput("id", "email_address", Utils.getEmailid());
			
			//Common.textBoxInput("id", "email_address",data.get(DataSet).get("Email"));
			Common.textBoxInput("id", "password", data.get(dataSet).get("Password"));
			Common.textBoxInput("id", "password-confirmation", data.get(dataSet).get("Password"));
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(4000);
			Common.clickElement("xpath", "//button[@title='Create an Account']");
			Thread.sleep(10000);
			report.addPassLog(expectedResult, "Should display My Account Page", "My Account Page display successfully", Common.getscreenShotPathforReport("Account Creation success"));
			
			String s=Common.getText("xpath", "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");
			System.out.println(s);
			
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
			Thread.sleep(4000);

			Common.findElement("xpath", "//button[@class='action submit primary']").click();
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
	
	
	
	
	public void Addtocart() throws Exception
	{
		String expectedResult="Product adding to mini cart";
		try {


            Thread.sleep(4000);
            Sync.waitElementPresent("xpath", "(//span[text()='Add to cart'])[1]");
            Common.clickElement("xpath", "(//span[text()='Add to cart'])[1]"); 
            
            minicartwrapper();
            report.addPassLog(expectedResult, "Should display Mini Cart Page", "Mini Cart Page display successfully", Common.getscreenShotPathforReport("Mini Cart Page display successfully"));
        }catch(Exception |Error e)
        {
            report.addFailedLog(expectedResult,"Should display Mini Cart Page", "Mini Cart Page not displayed", Common.getscreenShotPathforReport("Mini Cart Page display Failed"));
            e.printStackTrace();
            Assert.fail();
        }
    }


	public void minicartwrapper() throws Exception
	{
		String expectedResult="View and Edit cart page";
		try {
			
			Thread.sleep(8000);
			Sync.waitElementPresent("xpath", "//div[@class='minicart-wrapper']");
			Common.clickElement("xpath", "//div[@class='minicart-wrapper']");
			
			
			report.addPassLog(expectedResult, "Should display Mini Cart Page", "Mini Cart Page display successfully", Common.getscreenShotPathforReport("Mini Cart page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Mini Cart Page", "Mini Cart Page not displayed", Common.getscreenShotPathforReport("Mini Cart Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	
	
	public void ViewandEditcartPage() throws Exception
	{
		String expectedResult="View and Edit cart page";
		try {
			
			Thread.sleep(8000);
			Sync.waitElementPresent("xpath", "//span[text()='View and Edit Cart']");
			Common.clickElement("xpath", "//span[text()='View and Edit Cart']");
			
			
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
		String expectedResult="Navigate to checkout page";      
	        try {
	            Thread.sleep(5000);
	          //  Sync.waitElementPresent("xpath", "//button[@id='top-cart-btn-checkout']");
	            //Common.clickElement("xpath", "//button[@id='top-cart-btn-checkout']");
	            
	            Sync.waitElementPresent("xpath", "//span[text()='Checkout']");
	            Common.clickElement("xpath", "//span[text()='Checkout']");
	           
	           // Thread.sleep(5000);
	           
	            report.addPassLog(expectedResult, "Should display Checkout Page", "Checkout Page display successfully", Common.getscreenShotPathforReport("Checkout page success"));
	        }catch(Exception |Error e)
	        {
	            report.addFailedLog(expectedResult,"Should display Checkout Page", "Checkout Page not displayed", Common.getscreenShotPathforReport("Checkout Failed"));
	            Assert.fail();
	        }
	    }
		
	
	public void Registeruseraddress() throws InterruptedException {
		String expectedResult="Should navigate to shipping cart page";
		try {
			
			Common.actionsKeyPress(Keys.ARROW_DOWN);
			Sync.waitElementPresent("xpath", "//span[text()='Proceed to review & payment']");
			Common.clickElement("xpath", "//span[text()='Proceed to review & payment']");
			
		}
			catch(Exception |Error e) {
			 	e.printStackTrace();   
				ExtenantReportUtils.addFailedLog("should display the checkout page", "should display the checkout page", "faield to  display the checkout page", Common.getscreenShotPathforReport("should display the checkout page"));
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
	
	public void GuestShippingaddress(String dataSet) throws InterruptedException 
	{
		
			
			String expectedResult="add the shipping Address";
			try {
				Thread.sleep(10000);
				
			//Common.textBoxInput("xpath","//input[@type='email']", data.get(dataSet).get("Email"));
			Common.textBoxInput("xpath","//input[@id='customer-email']", data.get(dataSet).get("Email"));
	        Thread.sleep(4000);
	        Common.textBoxInput("name", "firstname", data.get(dataSet).get("FirstName"));
			Common.textBoxInput("name", "lastname", data.get(dataSet).get("LastName"));
			//Common.textBoxInput("name", "company", data.get(dataSet).get("Company"));
			Common.textBoxInput("name", "street[0]", data.get(dataSet).get("Street"));
			
			
			Thread.sleep(3000);

			Sync.waitElementPresent("name", "region_id");
			Common.clickElement("name", "region_id");

			Thread.sleep(3000);

			Sync.waitElementPresent("name", "region_id");
			
			Common.dropdown("name", "region_id", SelectBy.TEXT, data.get(dataSet).get("Region"));
	        
			Thread.sleep(4000);
			Common.textBoxInput("name", "city", data.get(dataSet).get("City"));
			
			Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));

			Thread.sleep(500);

			Common.textBoxInput("name", "telephone",data.get(dataSet).get("phone"));
	        
	        
	       // Common.actionsKeyPress(Keys.ARROW_DOWN);
		//	Sync.waitElementPresent("xpath", "//span[text()='Proceed to review & payment']");
			//Common.clickElement("xpath", "//span[text()='Proceed to review & payment']");
			
			
		report.addPassLog(expectedResult,"Should add the shipping Address", "Payment and review page  displayed", Common.getscreenShotPathforReport("Add Shipping Address Success page"));
	       }catch(Exception |Error e)
			{
			report.addFailedLog(expectedResult,"Should add the shipping Address", "Payment and review page not displayed", Common.getscreenShotPathforReport("Payment and review page Failed"));
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
				
				String sucessMessage=Common.getText("xpath", "//span[contains(text(),'Thank you for your purchase!')]");

				System.out.println(sucessMessage);
				Thread.sleep(3000);
				Assert.assertEquals(sucessMessage, "Thank you for your purchase!");
				Thread.sleep(3000);
				report.addPassLog(expectedResult, "Should display Order Success Page", "Order Success Page display successfully", Common.getscreenShotPathforReport("Order success page success"));
			}catch(Exception |Error e)
			{
				report.addFailedLog(expectedResult,"Should display Order Success Page", "Order Success Page not displayed", Common.getscreenShotPathforReport("Order Success Page Failed"));
				Assert.fail();
			}
		}
	
	public void GuestOrderSuccesspage() throws InterruptedException {
		
			String expectedResult="Validating Order Confirmation Page";
			Thread.sleep(2000);
			try {
				 Thread.sleep(10000);
					
					String sucessMessage=Common.getText("xpath", "//span[contains(text(),'Thank you for your purchase!')]");

					System.out.println(sucessMessage);
					Thread.sleep(3000);
					Assert.assertEquals(sucessMessage, "Thank you for your purchase!");
					Thread.sleep(3000);
					report.addPassLog(expectedResult, "Should display Order Success Page", "Order Success Page display successfully", Common.getscreenShotPathforReport("Order success page success"));
				}catch(Exception |Error e)
				{
					report.addFailedLog(expectedResult,"Should display Order Success Page", "Order Success Page not displayed", Common.getscreenShotPathforReport("Order Success Page Failed"));
					Assert.fail();
				}
			}
	
	
	public void ChangeQtyMinicart() throws InterruptedException {
		// TODO Auto-generated method stub
		String expectedResult="Land on ChangeQtyMinicart";
		try {
			Thread.sleep(5000);
			Common.clickElement("xpath" , "(//a[contains(text(),'No Touch Thermometer')])[1]");
			Thread.sleep(5000);

			Common.clickElement("xpath" , "(//button[@class='qty-incrementer__increment'])[1]");
			Thread.sleep(4000);
			report.addPassLog(expectedResult, "Should display ChangeQtyMinicart Page", "ChangeQtyMinicart page display successfully", Common.getscreenShotPathforReport("ChangeQtyMinicart page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display  ChangeQtyMinicart Page", "ChangeQtyMinicart Page not displayed", Common.getscreenShotPathforReport("ChangeQtyMinicartpage Failed"));
			e.printStackTrace();
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
		try
		{
			
			Sync.waitElementPresent("xpath", "//a[contains(text(), 'Profile')]");
				Common.clickElement("xpath", "//a[contains(text(), 'Profile')]");
				
				
		/*	String s= Common.getText("xpath", "//span[contains(text(),'Profile')]");
				System.out.println("My Account info page navigated "+s);
				Assert.assertEquals(s, "Profile");
				*/report.addPassLog(expectedResult, "Navigation of Account information", "My Account Information Page navigated successfully", Common.getscreenShotPathforReport("Accountinformation page Success"));
			
			
			
			}catch(Exception |Error e)
			{
				report.addFailedLog(expectedResult,"Navigation of Account information", "My Account Information Page navigated successfully", Common.getscreenShotPathforReport("Accountinformation page Failed"));
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
			int a=Common.findElements("xpath", "//button[@class='action-primary action-accept']").size();

			if(a>0)
			{
			Common.clickElement("xpath", "//button[@class='action-primary action-accept']");
			}
		else{
			
			}
			Common.refreshpage();
			Sync.waitPageLoad();
			Thread.sleep(3000);
		Sync.waitElementPresent("xpath", "//iframe[@name='paymetric_xisecure_frame']");
		Common.switchFrames("xpath", "//iframe[@name='paymetric_xisecure_frame']");
		Thread.sleep(2000);
		Sync.waitElementPresent("xpath", "//select[@id='c-ct']");
		Thread.sleep(2000);
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
		String URL = Common.getCurrentURL();
        Thread.sleep(4000);
        if (URL.contains("https://www.braunhealthcare.com/us_en") )
        {



            Common.getCurrentURL();



        } else {

        	Common.actionsKeyPress(Keys.DOWN);
        	Common.scrollIntoView("xpath", "//span[text()='Place Order']");
      Common.javascriptclickElement("xpath", "//span[contains(text(),'Place Order')]");

           // Common.mouseOverClick("xpath", "//span[contains(text(),'Place Order')]");
        }

		
		//Sync.waitElementPresent("xpath", "//span[text()='Place Order']");
		//Common.scrollIntoView("xpath", "//span[text()='Place Order']");
	    //Common.clickElement("xpath", "//span[text()='Place Order']");
	    String header=Common.getText("xpath", "//div[contains(text(),'Payment method')]");
		System.out.println(header+"Payment method");
		Assert.assertEquals(header, "Payment method");
		    report.addPassLog(expectedResult, "Should display Order Success Page", "Order Success Page display successfully", Common.getscreenShotPathforReport("Order success page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Order Success Page", "Order Success Page not displayed", Common.getscreenShotPathforReport("Order Success Page Failed"));
			e.printStackTrace();
			
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
	public void UpdateGuestPaymentAndSubmitOrder(String dataSet) throws InterruptedException {
		// TODO Auto-generated method stubtry {
		Thread.sleep(5000);
		Common.refreshpage();
		Sync.waitPageLoad();
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
		String URL = Common.getCurrentURL();
        Thread.sleep(4000);
        if (URL.contains("https://www.braunhealthcare.com/us_en") )
        {



            Common.getCurrentURL();



        } else {

      Common.javascriptclickElement("xpath", "//span[contains(text(),'Place Order')]");

           
        }
	    String header=Common.getText("xpath", "//div[contains(text(),'Payment method')]");
		System.out.println(header+"Payment method");
		Assert.assertEquals(header, "Payment method");
		    report.addPassLog(expectedResult, "Should display Order Success Page", "Order Success Page display successfully", Common.getscreenShotPathforReport("Order success page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Order Success Page", "Order Success Page not displayed", Common.getscreenShotPathforReport("Order Success Page Failed"));
			e.printStackTrace();
			
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
	
	
	
		
		public void Select_ProductinThermometers(String productname) {
			/*
			* Please write logfiles nothing but a condition for screen short
			*i just Implement logic for this
			* Manojk
			*/
			try {
			Sync.waitElementClickable(30, By.xpath("//div[@id='store.menu']//span[text()='Shop']"));
			Common.mouseOver("xpath" , "//div[@id='store.menu']//span[text()='Shop']");
			Common.clickElement("xpath", "//a[@title='Thermometers']");
			Sync.waitPageLoad();


			String title=Common.getText("xpath", "//span[@data-ui-id='page-title-wrapper']");
			Thread.sleep(3000);
			Common.assertionCheckwithReport(title.equals("Thermometers"),"verifying select the prodcut from plp page","User should select  the prodcut from plp page", "User sucessfully  select the prodcut from plp page", "Thermometers");	
			
		}catch(Exception |Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying select the prodcut from plp page","User should select  the prodcut from plp page", "userunable to select the prodcut from plp page", Common.getscreenShotPathforReport("Failed to select the prodcut from plp page"));			
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
	
	
	public void validateNavigateWarrantyRegistration(String string) {
		// TODO Auto-generated method stub
		String expectedResult="Validating Warranty RegistrationLink ";
		try {
			Common.scrollIntoView("xpath", "(//span[text()='Warranty Registration'])[2]");
			Sync.waitElementPresent("xpath", "(//span[text()='Warranty Registration'])[2]");
			Common.findElement("xpath", "(//span[text()='Warranty Registration'])[2]").click();
			Common.switchWindows();
			String s = Common.getText("xpath","(//span[text()='Warranty Registration'])[2]");
			System.out.println(s);
			Assert.assertEquals(s, "Warranty Registration");
			//reporter parse log

			report.addPassLog(expectedResult,"Should display Warranty Registration page", "Warranty Registration display successfully", Common.getscreenShotPathforReport("Warranty Registration page success"));

		}
		catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Warranty Registration", "Warranty Registration display successfully", Common.getscreenShotPathforReport("Warranty Registration Failed"));
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
	public void validateNavigatProductSupport(String string) {
		// TODO Auto-generated method stub
		String expectedResult="Validating ProductSupport Link ";
		try {
			Common.scrollIntoView("xpath", "//span[text()='Product Support']");
			Sync.waitElementPresent("xpath", "//span[text()='Product Support']");
			Common.findElement("xpath", "//span[text()='Product Support']").click();
			Common.switchWindows();
			String s = Common.getText("xpath","//h1[contains(text(),'Frequent Asked Questions')]");
			System.out.println(s);
			Assert.assertEquals(s, "Frequent Asked Questions");
			//reporter parse log

			report.addPassLog(expectedResult,"Should display ProductSupport Link", "ProductSupport Link display successfully", Common.getscreenShotPathforReport("ProductSupport Link success"));

		}
		catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display ProductSupport Link", "ProductSupport Link display successfully", Common.getscreenShotPathforReport("ProductSupport Link Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	
		this.closeCurrentTabandSwitchtoHome();
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
	
	
	public void validateNavigateNasalAspirator(String string) {
		// TODO Auto-generated method stub
		String expectedResult="Validating Nasal Aspirator ";
		try {
			Common.scrollIntoView("xpath", "//span[text()='Nasal Aspirator']");
			Sync.waitElementPresent("xpath", "//span[text()='Nasal Aspirator']");
			Common.findElement("xpath", "//span[text()='Nasal Aspirator']").click();
			Common.switchWindows();
			String s = Common.getText("xpath","(//span[text()='Nasal Aspirator'])[1]");
			System.out.println(s);
			Assert.assertEquals(s, "Nasal Aspirator");
			//reporter parse log

			report.addPassLog(expectedResult,"Should display Nasal Aspirator page", "Nasal Aspirator page display successfully", Common.getscreenShotPathforReport("Nasal Aspirator page success"));

		}
		catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Nasal Aspirator page", "Nasal Aspirator page display successfully", Common.getscreenShotPathforReport("Nasal Aspirator page Failed"));
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
			
			Thread.sleep(1000);
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
			
			Thread.sleep(1000);
			Common.actionsKeyPress(Keys.END);
			Common.scrollIntoView("xpath", "//span[text()='Parts & Accessories']");
			Sync.waitElementPresent("xpath", "//span[text()='Parts & Accessories']");
			Common.clickElement("xpath", "//span[text()='Parts & Accessories']");
			//Common.findElement("xpath", "//span[text()='Parts & Accessories']").click();
			Common.switchWindows();
			String s = Common.getText("xpath","//span[text()='ThermoScan® Lens Filters - 40 count']");
			System.out.println(s);
			Assert.assertEquals(s, "ThermoScan® Lens Filters - 40 count");
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
	public void ourcompanyFooterlink() throws Exception
	{
		String expectedResult="It should navigate to our company";
		try {
			
			Common.actionsKeyPress(Keys.END);
			Sync.waitElementPresent("xpath", "//span[contains(text(),'Our Company')]");
			Common.clickElement("xpath", "//span[contains(text(),'Our Company')]");
			Common.switchWindows();
			String title=Common.getText("xpath", "(//a[@href='https://www.helenoftroy.com/brands/'])[1]");
			Thread.sleep(3000);
			Common.assertionCheckwithReport(title.equals("Brands"),"Verifying our company page","it shoud navigate to our company page", "successfully  navigated to our company Page", "our company");	
			}catch(Exception |Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("To verify the our company footer link","should navigate to our company footerlink", "userunable to navigate to our company footerlink", Common.getscreenShotPathforReport("failed to navigate to our company footerlinkpage"));			
				Assert.fail();	
		Common.closeCurrentWindow();		}
		Common.switchToFirstTab();
	}
	    

	public void ourhistoryFooterlink() throws Exception
	{
		String expectedResult="It should navigate to our history";
		try {
			
			//Common.actionsKeyPress(Keys.END);
			Sync.waitElementPresent("xpath", "//span[contains(text(),'Our History')]");
			Common.clickElement("xpath", "//span[contains(text(),'Our History')]");
			Common.switchWindows();
			String url=Common.getCurrentURL();
			Common.assertionCheckwithReport(url.contains("helenoftroy"),"Verifying our history page","it shoud navigate to our history page", "successfully  navigated to Under Sink Page", "Under Sink");	
			}catch(Exception |Error e) {
				ExtenantReportUtils.addFailedLog("To verify the our history footer link","should navigate to our history footerlink", "userunable to navigate to Under Sink footerlink", Common.getscreenShotPathforReport("failed to navigate to Under Sink footerlinkpage"));			
				Assert.fail();	
				}
		Common.closeCurrentWindow();
		Common.switchToFirstTab();
	}
	   
	public void blogFooterlink() throws Exception
	{
		String expectedResult="It should navigate to blog";
		try {
			
			Common.actionsKeyPress(Keys.END);
			Sync.waitElementPresent("xpath", "//span[contains(text(),'Blog')]");
			Common.clickElement("xpath", "//span[contains(text(),'Blog')]");
			
			String s=Common.getText("xpath", "(//span[text()='Blog'])[1]");
			Common.assertionCheckwithReport(s.contains("Blog"),"Verifying blog page","it shoud navigate to blog  page", "successfully  navigated to blog Page", "Under Sink");	
			}catch(Exception |Error e) {
				ExtenantReportUtils.addFailedLog("To verify the blog footer link","should navigate to blog footerlink", "userunable to navigate to blog footerlink", Common.getscreenShotPathforReport("failed to navigate to blog footerlinkpage"));			
				Assert.fail();	
				}
		
	}
	public void careersFooterlink() throws Exception
	{
		String expectedResult="It should navigate to careers page";
		try {
			
			Thread.sleep(5000);
			Common.actionsKeyPress(Keys.END);
			//Thread.sleep(5000);
			//Common.scrollIntoView("xpath", "(//a[@class='mobile-accordion-link'])[13]");
			//Sync.waitElementPresent("xpath", "(//a[@class='mobile-accordion-link'])[13]");
			
			Sync.waitElementPresent("xpath", "//span[text()='Careers']");
			Common.clickElement("xpath", "//span[text()='Careers']");
			Common.switchWindows();
			String url=Common.getCurrentURL();
			Common.assertionCheckwithReport(url.contains("helenoftroy"),"Verifying careers page","it shoud navigate to careers  page", "successfully  navigated to careers Page", "careers");	
			}catch(Exception |Error e) {
				ExtenantReportUtils.addFailedLog("To verify the careers footer link","should navigate to career footerlink", "userunable to navigate to careers footerlink", Common.getscreenShotPathforReport("failed to navigate to careers footerlinkpage"));			
			e.printStackTrace();
				Assert.fail();
				
				}
		Common.closeCurrentWindow();
		Common.switchToFirstTab();
	}






	
	public void  PURFooterlink() throws Exception
	{
		String expectedResult="It should navigate to braun page";
		try {
			
			Common.actionsKeyPress(Keys.END);
			
			Thread.sleep(2000);
			Sync.waitElementPresent("xpath", "//span[text()='PUR']");
			Common.clickElement("xpath", "//span[text()='PUR']");
			Common.switchWindows();
			report.addPassLog(expectedResult,"Should display pur page", "purpage display successfully", Common.getscreenShotPathforReport("pur page success"));
			}catch(Exception |Error e) {
				
				ExtenantReportUtils.addFailedLog("To verify the PUR footer link","should navigate to PUR footerlink", "userunable to navigate to PUR footerlink", Common.getscreenShotPathforReport("failed to navigate to braun footerlinkpage"));			
				e.printStackTrace();
				Assert.fail();	
				}
		Common.closeCurrentWindow();
		Common.switchToFirstTab();
	}
	
	
	public void  honeywellFooterlink() throws Exception
	{
		String expectedResult="It should navigate to honeywell page";
		try {
			
			Common.actionsKeyPress(Keys.END);
			
			
			Sync.waitElementPresent("xpath", "//span[contains(text(),'Honeywell')]");
			Common.clickElement("xpath", "//span[contains(text(),'Honeywell')]");
			Common.switchWindows();
			String url=Common.getCurrentURL();
			Common.assertionCheckwithReport(url.contains("honeywell"),"Verifying honeywell page","it shoud navigate to honeywell  page", "successfully  navigated to honeywell Page", "honeywell");	
			}catch(Exception |Error e) {
				ExtenantReportUtils.addFailedLog("To verify the honeywell footer link","should navigate to honeywell footerlink", "userunable to navigate to honeywell footerlink", Common.getscreenShotPathforReport("failed to navigate to honeywell footerlinkpage"));			
				e.printStackTrace();
				Assert.fail();	
				}
		Common.closeCurrentWindow();
		Common.switchToFirstTab();
	}
	
	
	public void  vicksFooterlink() throws Exception
	{
		String expectedResult="It should navigate to vicks page";
		try {
			
			Common.actionsKeyPress(Keys.END);
			
			Sync.waitElementPresent("xpath", "//span[contains(text(),'Vicks')]");
			Common.clickElement("xpath", "//span[contains(text(),'Vicks')]");
			Common.switchWindows();
			String url=Common.getCurrentURL();
			Common.assertionCheckwithReport(url.contains("vickshumidifiers"),"Verifying vicks page","it shoud navigate to vicks  page", "successfully  navigated to vicks Page", "vicks");	
			}catch(Exception |Error e) {
				ExtenantReportUtils.addFailedLog("To verify the vicks footer link","should navigate to vicks footerlink", "userunable to navigate to vicks footerlink", Common.getscreenShotPathforReport("failed to navigate to vicks footerlinkpage"));			
				Assert.fail();	
				}
		Common.closeCurrentWindow();
		Common.switchToFirstTab();
	}


	public void instagramFooterlink() throws Exception
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

	public void facebookFooterlink() throws Exception
	{
		String expectedResult="It should navigate to facebook page";
		try {
			
			
			
			Sync.waitElementPresent("xpath", "//span[contains(text(),'Facebook')]");
			Common.clickElement("xpath", "//span[contains(text(),'Facebook')]");
			Common.switchWindows();
			
			Thread.sleep(10000);
			
			String url=Common.getCurrentURL();
			Common.assertionCheckwithReport(url.contains("facebook"),"Verifying facebook page","it shoud navigate to facebook  page", "successfully  navigated to facebook Page", "facebook");	
			//Common.switchToFirstTab();
			
			
		}catch(Exception |Error e) {
			e.printStackTrace();
		ExtenantReportUtils.addFailedLog("To verify the facebook link","should navigate to facebook page", "userunable to navigate to facebook page", Common.getscreenShotPathforReport("failed to navigate to facebook page"));			
			Assert.fail();	
			}
		Common.closeCurrentWindow();
	Common.switchToFirstTab();
	}
	
	
	
	public void youtubeFooterlink() throws Exception
	{
		String expectedResult="It should navigate to youtube page";
		try {
			
			
			
			Sync.waitElementPresent("xpath", "//span[contains(text(),'Youtube')]");
			Common.clickElement("xpath", "//span[contains(text(),'Youtube')]");
			Common.switchWindows();
			Thread.sleep(5000);
			String url=Common.getCurrentURL();
			Common.assertionCheckwithReport(url.contains("youtube"),"Verifying youtube page","it shoud navigate to youtube  page", "successfully  navigated to youtube Page", "youtube");	
			Common.switchToFirstTab();
			
			
		}catch(Exception |Error e) {
			e.printStackTrace();
		ExtenantReportUtils.addFailedLog("To verify the youtube link","should navigate to youtube page", "userunable to navigate to youtube page", Common.getscreenShotPathforReport("failed to navigate to youtube page"));			
			Assert.fail();	
			}
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
			Common.actionsKeyPress(Keys.END);

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
			Thread.sleep(2000);
			Common.textBoxInput("xpath", "//input[@id='searchKeyword']",data.get(dataset).get("ProductName"));
			
			 Sync.waitElementClickable("xpath", "//div[@title='BDH100 (BRAUN DEHUMIDIFIER HEARING AIDS)']");
			 Common.javascriptclickElement("xpath", "//div[@title='BDH100 (BRAUN DEHUMIDIFIER HEARING AIDS)']");
		  
			Thread.sleep(2000);
			Sync.waitElementPresent("xpath", "(//button[@class='rn_DisplayButton'])[1]");
			 Common.findElement("xpath", "(//button[@class='rn_DisplayButton'])[1]").click();
			 Thread.sleep(5000);
			 Common.javascriptclickElement("xpath", "//a[contains(text(), 'Order Issues')]");
			 Common.javascriptclickElement("xpath", "//a[contains(text(), 'Billing Issue')]");
			 
			 
			Common.textBoxInput("xpath", "//textarea[@class='rn_TextArea']",data.get(dataset).get("Message"));
			Common.clickElement("xpath", "(//button[@Class='rn_DisplayButton'])[2]");	
			Sync.waitElementPresent("xpath", "//button[contains(text(),'Submit')]");
			 Common.javascriptclickElement("xpath", "//button[contains(text(),'Submit')]");

			 Thread.sleep(6000);
			 Common.actionsKeyPress(Keys.PAGE_UP);

			 Common.actionsKeyPress(Keys.PAGE_UP);

			 String submitted=Common.getText("xpath", "//h1[@style='font-size:16px;']");

			 System.out.println();

			 Common.assertionCheckwithReport(submitted.equals("Your question has been submitted!"), "Verifying Contact Us submitted page", "It should navigate to Contact Us submitted page", "successfully lands on Contact Us submitted page ","Contact Us submitted page");

			report.addPassLog(expectedResult, "Should display contact Us submitted page", "Contact Us submitted Page display successfully", Common.getscreenShotPathforReport("Contact us submitted page display successfully"));
			  }catch(Exception |Error e)


			  {

			  e.printStackTrace();
			  report.addFailedLog(expectedResult,"Should display contact Us submitted  page", "contact us submitted Page not displayed", Common.getscreenShotPathforReport("Contact us submitted page display Failed"));
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
				Thread.sleep(5000);
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
				
				Thread.sleep(5000);
				Common.textBoxInput("xpath","//input[@title='First Name']",data.get(dataset).get("FirstName"));
				
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
				
				Thread.sleep(3000);
				
				Sync.waitElementPresent("xpath", "//button[@type='submit']");
				
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
	Thread.sleep(6000);
	Common.clickElement("xpath", "//button[@class='button action continue primary']");
	Common.actionsKeyPress(Keys.ARROW_DOWN);
	//Common.actionsKeyPress(Keys.DOWN);
	int emailerrormessage=Common.findElements("xpath", "//div[@id='customer-email-error']").size();
	int Streeterromessage=Common.findElements("xpath", "//div[@class='field-error']").size();
	
	
	Common.assertionCheckwithReport(emailerrormessage>0&&Streeterromessage>0, "verifying error message ShippingAddressForm Page", "enter with empty data it must show error message","sucessfully display the error message", "faield to dispalyerrormessage");
	
	 Registeruseraddress();
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
		 Thread.sleep(5000);
		 Common.actionsKeyPress(Keys.END);
		 
		 Common.clickElement("xpath", "//a[contains(text(),'Terms Of Use')]");
		 String title =Common.getCurrentURL();
		 System.out.println(title);
		 Common.assertionCheckwithReport(title.contains("braun-terms-of-use"),"Verifying Terms Of Use   page","it shoud navigate to  Terms Of Use page", "successfully  navigated to Terms Of Use  Page", " Terms Of Use");	
		 
		 
		 report.addPassLog("To view  Terms of use  button", "Should display Terms of usepage", "user able to navigate to Terms of use  button\" successfully", Common.getscreenShotPathforReport("Terms of use display successfully"));
			}catch(Exception |Error e) {
				 
				ExtenantReportUtils.addFailedLog("To view  Terms of use button","should land on Terms of use  button", "user unable to navigate to Terms of use  button", Common.getscreenShotPathforReport("failed to land on Terms of use button"));			
				Assert.fail();
				e.printStackTrace();  
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




public void Shoppingcartpage() throws Exception
{
	String expectedResult="View and Edit cart page";
	try {
		
		Thread.sleep(5000);
		Sync.waitElementPresent("xpath", "//span[contains(text(),'View and Edit Cart')]");
		Common.clickElement("xpath", "//span[contains(text(),'View and Edit Cart')]");
		Thread.sleep(8000);
		Sync.waitElementPresent("xpath", "//button[@class='qty-incrementer__increment']");
		Common.clickElement("xpath", "//button[@class='qty-incrementer__increment']");
		Thread.sleep(5000);

		Sync.waitElementPresent("xpath", "//span[contains(text(), 'Update Shopping Cart')]");
		Common.clickElement("xpath", "//span[contains(text(), 'Update Shopping Cart')]");
		Thread.sleep(5000);
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
    	 
     
    	  Sync.waitElementPresent("xpath", "//a[@title='Nasal Aspirator']");
        Common.clickElement("xpath", "//a[@title='Nasal Aspirator']");
        
        Sync.waitElementPresent("xpath", "(//button[@title='Add to cart'])[1]");
         Common.clickElement("xpath", "(//button[@title='Add to cart'])[1]");
   
    // Sync.waitElementPresent("xpath", "//a[@title='Blood Pressure Monitors']");
      //  Common.clickElement("xpath", "//a[@title='Blood Pressure Monitors']");
        
           // Sync.waitElementPresent("xpath", "(//button[@title='Add to cart'])[1]");
         //Common.clickElement("xpath", "(//button[@title='Add to cart'])[1]");
        
        
        
    
      
        
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
			Thread.sleep(10000);

			Common.actionsKeyPress(Keys.PAGE_DOWN);
			//Common.actionsKeyPress(Keys.PAGE_DOWN);
			//Sync.waitElementVisible("xpath", "(//div[@class='billing-address-same-as-shipping-block field choice'])[1]");
			//Common.clickElement("xpath", "(//div[@class='billing-address-same-as-shipping-block field choice'])[1]");
		
	Sync.waitElementVisible("xpath", "//span[text()='Billing address is same as shipping']");
		Common.clickElement("xpath", "//span[text()='Billing address is same as shipping']");
				Thread.sleep(3000);
		if(Common.isElementDisplayed("xpath", "//select[@name='billing_address_id']")) {
			Common.clickElement("xpath", "//select[@name='billing_address_id']");
			
		Common.clickElement("xpath", "//select[@name='billing_address_id']/option[text()='New Address']");
	Common.dropdown("xpath", "//select[@name='billing_address_id']", SelectBy.VALUE, data.get(dataSet).get("UserName"));
		}
		
	
		Common.textBoxInput("xpath", "(//input[@name='firstname'])[2]", data.get(dataSet).get("FirstName"));
		Common.textBoxInput("xpath", "(//input[@name='lastname'])[2]", data.get(dataSet).get("LastName"));
		Common.textBoxInput("xpath", "(//input[@name='street[0]'])[2]", data.get(dataSet).get("Street"));
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.ESCAPE);
		Common.textBoxInput("xpath", "(//input[@name='city'])[2]", data.get(dataSet).get("City"));
		Common.textBoxInput("xpath", "(//input[@name='postcode'])[2]", data.get(dataSet).get("postcode"));
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.dropdown("xpath", "(//select[@name='region_id'])[2]",Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
		Common.textBoxInput("xpath", "(//input[@name='telephone'])[2]", data.get(dataSet).get("phone"));
		
		Common.actionsKeyPress(Keys.PAGE_UP);
		
		report.addFailedLog(expectedresult, "Billing address will be  added", Common.getscreenShotPathforReport("successfully add billing address"));
		} catch(Exception | Error e) {
			
			
			
report.addFailedLog(expectedresult, "Billing address is not  added", Common.getscreenShotPathforReport("Failed to add billing address"));
			
e.printStackTrace();
Assert.fail();
		}
		
	}





public void RegisterUser_ShippingAddress() throws Exception
{
	String expectedResult="Navigate to Shipping Address Page";
	try {
		Thread.sleep(5000);
		Sync.waitElementPresent("xpath", "//span[text()='New Address']");
		Common.clickElement("xpath", "//span[text()='New Address']");
		
		Thread.sleep(5000);
		
		NewShippingAddress("NewShippingAddress");
		
		Sync.waitElementPresent("xpath", "//button[@class='action primary action-save-address']//span[contains(text(),'Ship Here')]");
		Common.clickElement("xpath", "//button[@class='action primary action-save-address']//span[contains(text(),'Ship Here')]");
		
		report.addPassLog(expectedResult, "Should display Shipping Address Page", "Shipping Address Page display successfully", Common.getscreenShotPathforReport("Shipping Address page display  success"));
	}catch(Exception |Error e)
	{
		report.addFailedLog(expectedResult,"Should display Shipping Address Page", "Shipping Address Page not displayed", Common.getscreenShotPathforReport("Shipping Address Failed"));
		Assert.fail();
	}
}


public void NewShippingAddress(String dataSet) throws InterruptedException {
	try {
		Thread.sleep(8000);
		Sync.waitElementPresent("xpath", "//input[@name='firstname']");
		Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(dataSet).get("FirstName"));
		
		Sync.waitElementPresent("xpath", "//input[@name='lastname']");
		Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));
		
		Sync.waitElementPresent("xpath", "(//input[@name='street[0]'])[2]");
		Common.textBoxInput("xpath", "(//input[@name='street[0]'])[2]", data.get(dataSet).get("Street"));
		Thread.sleep(4000);

		Common.clickElement("xpath", "(//a[@class='dropdown-item list-item'])[1]");

		Thread.sleep(5000);
		Sync.waitElementPresent("xpath", "(//input[@name='city'])[2]");
		Common.textBoxInput("xpath", "(//input[@name='city'])[2]", data.get(dataSet).get("City"));
		
		
		Sync.waitElementPresent("xpath", "(//input[@name='postcode'])[2]");
		Common.textBoxInput("xpath", "(//input[@name='postcode'])[2]", data.get(dataSet).get("postcode"));
		Thread.sleep(5000);
Common.actionsKeyPress(Keys.PAGE_DOWN);
Thread.sleep(5000);

		Sync.waitElementPresent("xpath", "(//select[@name='region_id'])[2]");
		Common.dropdown("xpath", "(//select[@name='region_id'])[2]", SelectBy.TEXT, data.get(dataSet).get("Region"));
		
		/*Sync.waitElementPresent("xpath", "//input[@name='lastname']");
		Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));*/
		
		
		
		Sync.waitElementPresent("xpath", "(//input[@name='telephone'])[2]");
		Common.textBoxInput("xpath", "(//input[@name='telephone'])[2]", data.get(dataSet).get("phone"));
		
		
	/*	
		Sync.waitElementPresent("xpath", "//select[@name='country_id']");
		Common.dropdown("xpath", "//select[@name='country_id']", SelectBy.TEXT, data.get(dataSet).get("Countryname"));*/
		


	   Sync.waitElementPresent("xpath", "(//button[@class='action primary action-save-address'])");
		Common.clickElement("xpath", "(//button[@class='action primary action-save-address'])");
		Thread.sleep(5000);

		int a=Common.findElements("xpath", "//button[@class='action-primary action-accept']").size();

		if(a>0)
		{
		Common.clickElement("xpath", "//button[@class='action-primary action-accept']");
		}
	else{
		
		}

		//Sync.waitElementPresent("xpath", "//button[@class='action primary action-save-address']");
		//Common.clickElement("xpath", "//button[@class='action primary action-save-address']");
				
				
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
		//Sync.waitElementClickable(30, By.xpath("//span[text()='Checkout']"));
		//Common.clickElement("xpath" , "//span[text()='Checkout']");
		//Thread.sleep(4000);
		//String S=Common.getText("xpath", "//*[@id='shipping']/div[1]");
		//System.out.println(S);
		//Assert.assertTrue(Common.isElementDisplayed("xpath", "//*[@id='shipping']/div[1]"));
		//Thread.sleep(3000);	
		//String s=Common.getText("xpath", "//div[text()='Shipping address']");
		//System.out.println(s);
		//Assert.assertTrue(Common.isElementDisplayed("xpath", "//div[text()='Shipping address']"));
		Thread.sleep(3000);	
		
		NoTAXAddress("NoTaxShippingAddress");
		Thread.sleep(6000);	
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Thread.sleep(5000);
		Common.scrollIntoView("xpath", "//div[contains(text(),'Shipping methods')]");
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
		Thread.sleep(6000);
		Common.textBoxInput("xpath", "(//input[@id='customer-email'])[1]", data.get(dataSet).get("Email"));
		Thread.sleep(3000);
		Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(dataSet).get("FirstName"));
		Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));
		Common.textBoxInput("xpath", "//input[@name='street[0]']", data.get(dataSet).get("Street"));
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.ESCAPE);
		Common.textBoxInput("xpath", "//input[@name='city']", data.get(dataSet).get("City"));
		Common.textBoxInput("xpath", "//input[@name='postcode']", data.get(dataSet).get("postcode"));
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.dropdown("xpath", "(//select[@name='region_id'])[1]",Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
		Common.textBoxInput("xpath", "//input[@name='telephone']", data.get(dataSet).get("phone"));
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
		Thread.sleep(3000);

		Common.clickElement("xpath", "(//a[@class='dropdown-item list-item'])[1]");
		
		Common.textBoxInput("name", "city", data.get(dataSet).get("City"));
		Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
		Thread.sleep(3000);

		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.dropdown("xpath", "(//select[@class='select'])[1]",Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
		Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
		
		Sync.waitElementPresent("xpath", "//button[@class='action primary action-save-address']");
		Common.clickElement("xpath", "//button[@class='action primary action-save-address']");
		Thread.sleep(3000);

		int a=Common.findElements("xpath", "//button[@class='action-primary action-accept']").size();

		if(a>0)
		{
		Common.clickElement("xpath", "//button[@class='action-primary action-accept']");
		}
		else{
		
		}
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

        Sync.waitElementPresent("xpath", "(//a[@class='product-item-link'])[2]");
        Common.clickElement("xpath", "(//a[@class='product-item-link'])[2]");


       Mouseover();
        Sync.waitElementPresent("xpath", "//a[@title='Blood Pressure Monitors']");
        Common.clickElement("xpath", "//a[@title='Blood Pressure Monitors']");
      
		
        Mouseover();
          
          Sync.waitElementPresent("xpath", "//a[@title='Pulse Oximeter']");
          Common.clickElement("xpath", "//a[@title='Pulse Oximeter']");
         
  		
          Mouseover();
          
          
          Sync.waitElementPresent("xpath", "//a[contains(text(), 'Parts & Accessories')]");
          Common.clickElement("xpath", "//a[contains(text(), 'Parts & Accessories')]");
        
        
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
        Thread.sleep(8000);
        Sync.waitElementPresent("xpath", "(//input[@id='customer-email'])[1]");
        Common.textBoxInput("xpath", "(//input[@id='customer-email'])[1]", data.get(dataSet).get("Email"));
        Thread.sleep(3000);
        Common.textBoxInput("name", "firstname", data.get(dataSet).get("FirstName"));
        Common.textBoxInput("name", "lastname", data.get(dataSet).get("LastName"));
        Common.textBoxInput("name", "street[0]", Street);
        Thread.sleep(3000);
        Common.actionsKeyPress(Keys.ESCAPE);
        Common.textBoxInput("name", "city", City);
        Common.textBoxInput("name", "postcode", postcode);
        Thread.sleep(9000);
 
        Common.dropdown("name", "region_id",Common.SelectBy.TEXT, Region);
        Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));


    }catch(Exception |Error e)
    {
        e.printStackTrace();
        Assert.fail();
    }
}

public String  URL() throws InterruptedException {
	String Website="";
	Common.clickElement("xpath", "(//a[@class='logo'])");
	Sync.waitPageLoad();
	Thread.sleep(4000);
	Website=Common.getCurrentURL();
    
	return Website;
}
	
	
	public void AGREEPROCEED() throws InterruptedException {
		// TODO Auto-generated method stub
             Thread.sleep(5000);
		
		int sizes=Common.findElements("xpath", "//button[@id='truste-consent-button']").size();
		if(sizes>0){
		Sync.waitElementClickable(30, By.xpath("//button[@id='truste-consent-button']"));
	   Common.findElement("xpath", "//button[@id='truste-consent-button']").click();
		}
	}
	
	
	public void prepareTaxData(String fileName) {
		// TODO Auto-generated method stub

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
			cell.setCellValue("Orders Details");
			
			    
			row = sheet.createRow(1);
			cell = row.createCell(0);
			cell.setCellStyle(cs);
			cell.setCellValue("S.No");
			cell = row.createCell(1);
			cell.setCellStyle(cs);
			cell.setCellValue("UC No.");
			cell = row.createCell(2);
			cell.setCellStyle(cs);
			cell.setCellValue("Bussiness Segmet");
			cell = row.createCell(3);
			cell.setCellStyle(cs);
			cell.setCellValue("Test Phase");
			cell = row.createCell(4);
			cell.setCellStyle(cs);
			cell.setCellValue("Tester Name");
			cell = row.createCell(5);
			cell.setCellStyle(cs);
			cell.setCellValue("Web Type");
			cell = row.createCell(6);
			cell.setCellStyle(cs);
			cell.setCellValue("Website");
			
			
			cell = row.createCell(7);
			cell.setCellStyle(cs);
			cell.setCellValue("Web order Number");
			cell = row.createCell(8);
			cell.setCellStyle(cs);
			cell.setCellValue("SubTotal");
			cell = row.createCell(9);
			cell.setCellStyle(cs);
			cell.setCellValue("Shipping");
			cell = row.createCell(10);
			cell.setCellStyle(cs);
			cell.setCellValue("State");
			cell = row.createCell(11);
			cell.setCellStyle(cs);
			cell.setCellValue("Zipcode");
			cell = row.createCell(12);
			cell.setCellStyle(cs);
			cell.setCellValue("Tax");
			cell=row.createCell(13);
			cell.setCellStyle(cs);
			cell.setCellValue("Order Total");
			cell=row.createCell(14);
			cell.setCellStyle(cs);
			cell.setCellValue("Tax on Shiping (Y/N)");
			cell=row.createCell(15);
			cell.setCellStyle(cs);
			cell.setCellValue("Web Configured Tax Rate");
			cell=row.createCell(16);
			cell.setCellStyle(cs);
			cell.setCellValue("Expected TaxAmount");
			cell=row.createCell(17);
			cell.setCellStyle(cs);
			cell.setCellValue("Expected OrderTotal Amount");
			cell=row.createCell(18);
			cell.setCellStyle(cs);
			cell.setCellValue("Actual OrderTotal Amount");
			cell=row.createCell(19);
			cell.setCellStyle(cs);
			cell.setCellValue("Digital QA Status(PASS/FAIL)");
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
	

	public void writeResultstoXLSx(String Website,String OrderId,String subtotlaValue,String shippingammountvalue,String state,String Zipcode,String Taxammountvalue,String ActualTotalammountvalue, String ExpectedTotalammountvalue,String giventaxvalue,String calucaltedvalue)
	{
		//String fileOut="";
	try{
		
		File file=new File(System.getProperty("user.dir")+"/src/test/resources/BraunTaxDetails_Guest.xlsx");
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
		cell.setCellValue("Orders Details");
		
		row = sheet.createRow(1);
		cell = row.createCell(0);
		cell.setCellStyle(cs);
		cell.setCellValue("Web Order Number");
		cell = row.createCell(1);
		cell.setCellStyle(cs);
		cell.setCellValue("SubTotal");
		cell = row.createCell(2);
		cell.setCellStyle(cs);
		cell.setCellValue("Shipping");
		cell=row.createCell(3);
		cell.setCellStyle(cs);
		cell.setCellValue("TaxRate");
		cell=row.createCell(4);
		cell.setCellStyle(cs);
		cell.setCellValue("Web Configured TaxRate");
		cell=row.createCell(5);
		cell.setCellStyle(cs);
		cell.setCellValue("Actual TaxAmount");
		cell=row.createCell(6);
		cell.setCellStyle(cs);
		cell.setCellValue("Expected TaxAmount");
		
		rowcount=2;
		
		}
		
		else
		{
		
		sheet=workbook.getSheet("TaxDetails");	
		rowcount=sheet.getLastRowNum()+1;
		}
		row = sheet.createRow(rowcount);
		
		cell = row.createCell(0);
		cell.setCellType(CellType.NUMERIC);
		int SNo=rowcount-1;
		cell.setCellValue(SNo);
		cell = row.createCell(1);
		cell.setCellType(CellType.NUMERIC);
		cell.setCellValue("");
		cell = row.createCell(2);
		cell.setCellType(CellType.STRING);
		cell.setCellValue("HOMEANDHEALTH");
		cell = row.createCell(3);
		cell.setCellType(CellType.STRING);
		cell.setCellValue("User");
		cell = row.createCell(4);
		cell.setCellType(CellType.STRING);
		cell.setCellValue("Lotuswave");
		cell = row.createCell(5);
		cell.setCellType(CellType.STRING);
		cell.setCellValue("B2C");
		cell = row.createCell(6);
		cell.setCellType(CellType.STRING);
		
		String Site;
		if(Website.contains("braunhealth"))
	     {
			
			Site="Braun US";
			
	}
		else
		{
			Site="";
		}
		cell.setCellValue(Site);
		
		cell = row.createCell(7);
		cell.setCellValue(OrderId);
		cell = row.createCell(8);
		cell.setCellType(CellType.NUMERIC);
		cell.setCellValue(subtotlaValue);
		cell = row.createCell(9);
		cell.setCellType(CellType.NUMERIC);
		cell.setCellValue(shippingammountvalue);
		cell = row.createCell(10);
		cell.setCellType(CellType.NUMERIC);
		cell.setCellValue(state);
		cell = row.createCell(11);
		cell.setCellType(CellType.NUMERIC);
		cell.setCellValue(Zipcode);
		cell = row.createCell(12);
		cell.setCellType(CellType.NUMERIC);
		cell.setCellValue(Taxammountvalue);
		cell = row.createCell(13);
		cell.setCellType(CellType.NUMERIC);
		cell.setCellValue(ActualTotalammountvalue);
		cell = row.createCell(14);
		cell.setCellType(CellType.STRING);
		String TaxonShipping;
		  if((state.equals("Illinois"))||(state.equals("Florida")))
	     {
			TaxonShipping="NO";	
	}
		else
		{
			TaxonShipping="YES";
		}
		cell.setCellValue(TaxonShipping);
		cell = row.createCell(15);
		cell.setCellType(CellType.NUMERIC);
		cell.setCellValue(giventaxvalue);
		cell = row.createCell(16);
		cell.setCellType(CellType.NUMERIC);
		cell.setCellValue(calucaltedvalue);
		cell = row.createCell(17);
		cell.setCellType(CellType.NUMERIC);
		cell.setCellValue(ExpectedTotalammountvalue);
		cell = row.createCell(18);
		cell.setCellType(CellType.NUMERIC);
		cell.setCellValue(ActualTotalammountvalue);
		cell = row.createCell(19);
		cell.setCellType(CellType.STRING);
		String status;
		if(Taxammountvalue.contains(calucaltedvalue)&&(ActualTotalammountvalue).contains(ExpectedTotalammountvalue))
	     {
			
			status="PASS";
			CellStyle style = workbook.createCellStyle();
			Font font= workbook.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			style.setFont(font);
			cell.setCellStyle(style);
		}
		else
		{
			status="FAIL";
			CellStyle style = workbook.createCellStyle();
			Font font= workbook.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			style.setFont(font);
			cell.setCellStyle(style);
			}
		
		
		cell.setCellValue(status);
		
		System.out.println(OrderId);
		//String subtotla = Float.toString(subtotlaValue);
		//System.out.println("String is: "+subtotla);
		System.out.println(subtotlaValue);
		//String shippingammount = Float.toString(shippingammountvalue);
		//System.out.println("String is: "+shippingammount);
		System.out.println(shippingammountvalue);
		//String Taxammount = Float.toString(Taxammountvalue);
		//System.out.println("String is: "+Taxammount);
		System.out.println(Taxammountvalue);
		//String Totalammount = Float.toString(Totalammountvalue);
		//System.out.println("String is: "+Totalammount);
		System.out.println(ActualTotalammountvalue);
		System.out.println(ExpectedTotalammountvalue);
		//String Actualtax = Float.toString(ActualTax);
		//System.out.println("String is: "+Actualtax);
		System.out.println(giventaxvalue);
		//String userpaneltax = Float.toString(userpaneltaxvalue);
		//System.out.println("String is: "+userpaneltax);
		System.out.println(calucaltedvalue);
		
			FileOutputStream fileOut = new FileOutputStream(file);
		
		workbook.write(fileOut);

		fileOut.flush();
		fileOut.close();
	//return writeResultstoXLSx(String OrderId,String subtotla,String shippingammount,String TaxAmmount,String Totalammount,String giventaxvalue1,String userpaneltaxvalue);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	//return fileOut;
	//return writeResultstoXLSx(String OrderId,String subtotla,String shippingammount,String TaxAmmount,String Totalammount,String giventaxvalue1,String userpaneltaxvalue);

	}






	
	public void SignIn_Shippingpage(String DataSet) {
	    String expectedResult=" SignIn in  shipping page ";
	try {
		
		  Thread.sleep(6000);
	    
	    Sync.waitElementPresent("xpath", "(//input[@class='input-text'])[3]");
	    Common.textBoxInput("xpath", "(//input[@class='input-text'])[3]", data.get(DataSet).get("Email"));
	    Sync.waitElementPresent("xpath", "//input[@id='customer-password']");
	    Common.textBoxInput("xpath", "//input[@id='customer-password']", data.get(DataSet).get("Password"));
       Common.clickElement(By.xpath("//button[@class='action login primary']"));
	    Thread.sleep(8000);
	    Common.actionsKeyPress(Keys.END);
	    //Common.clickElement("xpath", "//button[@class='button action continue primary']");
           report.addPassLog(expectedResult,"Should  login in shippg page ", "Payment and review page  displayed", Common.getscreenShotPathforReport("Payment and review page  displayed"));
	   }catch(Exception |Error e) {
	         e.printStackTrace();   
	        ExtenantReportUtils.addFailedLog(expectedResult, "Should  login in shippg page", "Payment and review page  displayed", Common.getscreenShotPathforReport("Payment and review page  displayed"));
	        Assert.fail();
	    }
	}



	public void ShippingMethods() {
		// TODO Auto-generated method stub
		String expectedResult=" Validating  shipping methods";
	try {
		

		int title= Common.findElements("xpath", "//div[text()='Shipping methods']").size();

		 

		System.out.println(title);

		 

		Common.assertionCheckwithReport(title>0, "verifying Shipping methods", " Shipping methods page should dispaly", "sucessfully lands on  Shipping methods page ", "faield to land on Shipping methods page");

		 
		 { 
			 int subtotal=Common.findElements("xpath", "//td[text()='Free Shipping']").size();
			 

			 if(subtotal>0)
				 
			 { 
				 
				 Sync.waitPageLoad();
				 
				 
				 Sync.waitElementPresent("xpath", "//td[text()='Free Shipping']");
			        Common.clickElement("xpath", "//td[text()='Free Shipping']");
				    
				// Common.findElement("xpath", "(//td[text()='Free Shipping'])");
				 
				
			        ExtenantReportUtils.addPassLog("verifying Shipping methods", "User should select the free Shipping method", "User sucessfully  selected the free Shipping method", Common.getscreenShotPathforReport("Failed to select free shipping method"));
				
				 
				// System.out.println("The subtotal amount should be displayed");
			 } else {
				  Thread.sleep(5000);
				 Sync.waitElementPresent("xpath", "//td[text()='Fedex SmartPost']");
			        Common.clickElement("xpath", "//td[text()='Fedex SmartPost']");
				    
			        ExtenantReportUtils.addPassLog("verifying Shipping methods", "User should select the US - Fedex SmartPost", "User sucessfully  selected the US - Fedex SmartPost", Common.getscreenShotPathforReport("Failed to select US - Fedex SmartPost method"));
			 }

			 
		 }
		 
		 Registeruseraddress();


			
		
	}
		catch(Exception |Error e) {
		 	e.printStackTrace();   
			ExtenantReportUtils.addFailedLog("verifying Shipping methods", "verifying Shipping methods", "faield to verifying Shipping methods", Common.getscreenShotPathforReport("verifying Shipping methods"));
			Assert.fail();
		}
	}



public void AddressVerfication () {



	// TODO Auto-generated method stub
	String expectedResult=" Validating  Address verfication";
try {
	
		
	int Address=Common.findElements("xpath", "//div[text()='Address is not verified. Do you want to continue ?']").size();
	
	Thread.sleep(4000);
	Sync.waitElementPresent("xpath", "//span[text()='OK']");
	Common.clickElement("xpath", "//span[text()='OK']");
	//Sync.waitElementPresent("xpath", "//button[@class='action-primary action-accept']");
	//Common.clickElement("xpath", "//button[@class='action-primary action-accept']");
	Thread.sleep(2000);
	//Common.refreshpage();
  /* if(Common.findElements("xpath", "//span[@data-bind='text: getTitle()']").size()<0) {
	  Thread.sleep(3000);
	  Common.refreshpage();
	  Sync.waitPageLoad();
  }else {
	  System.out.println("the Payemnts page is displayed with credit card");
	  Common.refreshpage();
  
  }
	*/
}
catch(Exception |Error e) {
 	e.printStackTrace();   
	ExtenantReportUtils.addFailedLog("verifyingAddressVerfication", "verifyingAddressVerfication", "faield to verifying AddressVerfication", Common.getscreenShotPathforReport("verifyingAddressVerfication"));
	Assert.fail();
}
}














public String order_Verifying() throws Exception{
	String OrderId="";
	//Thread.sleep(10000);
	//Common.textBoxInput("id", "//textarea[contains(@id,'tt-c-comment-field')]","Ceate accounts test ");
	String expectedResult = "It redirects to order confirmation page";
	try{
	Sync.waitPageLoad();
	
	
	
	for(int i=0;i<10;i++){
		Thread.sleep(5000);
		if(Common.getCurrentURL().contains("success")){
			break;
		}
		
	}
	
	String sucessMessage=Common.getText("xpath", "//h1[@class='page-title']/span");
	System.out.println(sucessMessage);
	 OrderId=Common.getText("xpath", "//div[@class='checkout-success']//p//strong");
	System.out.println("Your order number is:"+OrderId);
	Common.assertionCheckwithReport(sucessMessage.equals("Thank you for your purchase!"),"verifying the product confirmation", expectedResult,"Successfully It redirects to order confirmation page Order Placed","User unabel to go orderconformation page");
		
	}
	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying the product confirmation", expectedResult,
				"User failed to navigate  to order confirmation page", Common.getscreenShotPathforReport("failednavigatepage"));
		Assert.fail();
	}
	return OrderId;
}




public void writeResultstoXLSxReg(String Website,String subtotlaValue,String shippingammountvalue,String state,String Zipcode,String Taxammountvalue,String ActualTotalammountvalue, String ExpectedTotalammountvalue,String giventaxvalue,String calucaltedvalue)
{
	//String fileOut="";
try{
	
	File file=new File(System.getProperty("user.dir")+"/src/test/resources/BraunTaxDetails_Reg.xlsx");
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
	cell.setCellValue("Orders Details");
	
	row = sheet.createRow(1);
	cell = row.createCell(0);
	cell.setCellStyle(cs);
	cell.setCellValue("Web Order Number");
	cell = row.createCell(1);
	cell.setCellStyle(cs);
	cell.setCellValue("SubTotal");
	cell = row.createCell(2);
	cell.setCellStyle(cs);
	cell.setCellValue("Shipping");
	cell=row.createCell(3);
	cell.setCellStyle(cs);
	cell.setCellValue("TaxRate");
	cell=row.createCell(4);
	cell.setCellStyle(cs);
	cell.setCellValue("Web Configured TaxRate");
	cell=row.createCell(5);
	cell.setCellStyle(cs);
	cell.setCellValue("Actual TaxAmount");
	cell=row.createCell(6);
	cell.setCellStyle(cs);
	cell.setCellValue("Expected TaxAmount");
	
	rowcount=2;
	
	}
	
	else
	{
	
	sheet=workbook.getSheet("TaxDetails");	
	rowcount=sheet.getLastRowNum()+1;
	}
	row = sheet.createRow(rowcount);
	
	cell = row.createCell(0);
	cell.setCellType(CellType.NUMERIC);
	int SNo=rowcount-1;
	cell.setCellValue(SNo);
	cell = row.createCell(1);
	cell.setCellType(CellType.NUMERIC);
	cell.setCellValue("");
	cell = row.createCell(2);
	cell.setCellType(CellType.STRING);
	cell.setCellValue("HOMEANDHEALTH");
	cell = row.createCell(3);
	cell.setCellType(CellType.STRING);
	cell.setCellValue("User");
	cell = row.createCell(4);
	cell.setCellType(CellType.STRING);
	cell.setCellValue("Lotuswave");
	cell = row.createCell(5);
	cell.setCellType(CellType.STRING);
	cell.setCellValue("B2C");
	cell = row.createCell(6);
	cell.setCellType(CellType.STRING);
	
	String Site;
	if(Website.contains("braunhealth"))
     {
		
		Site="Braun US";
		
}
	else
	{
		Site="";
	}
	cell.setCellValue(Site);
	
	cell = row.createCell(7);
	//cell.setCellValue(OrderId);
	cell = row.createCell(8);
	cell.setCellType(CellType.NUMERIC);
	cell.setCellValue(subtotlaValue);
	cell = row.createCell(9);
	cell.setCellType(CellType.NUMERIC);
	cell.setCellValue(shippingammountvalue);
	cell = row.createCell(10);
	cell.setCellType(CellType.NUMERIC);
	cell.setCellValue(state);
	cell = row.createCell(11);
	cell.setCellType(CellType.NUMERIC);
	cell.setCellValue(Zipcode);
	cell = row.createCell(12);
	cell.setCellType(CellType.NUMERIC);
	cell.setCellValue(Taxammountvalue);
	cell = row.createCell(13);
	cell.setCellType(CellType.NUMERIC);
	cell.setCellValue(ActualTotalammountvalue);
	cell = row.createCell(14);
	cell.setCellType(CellType.STRING);
	String TaxonShipping;
	  if((state.equals("Illinois"))||(state.equals("Florida")))
     {
		TaxonShipping="NO";	
}
	else
	{
		TaxonShipping="YES";
	}
	cell.setCellValue(TaxonShipping);
	cell = row.createCell(15);
	cell.setCellType(CellType.NUMERIC);
	cell.setCellValue(giventaxvalue);
	cell = row.createCell(16);
	cell.setCellType(CellType.NUMERIC);
	cell.setCellValue(calucaltedvalue);
	cell = row.createCell(17);
	cell.setCellType(CellType.NUMERIC);
	cell.setCellValue(ExpectedTotalammountvalue);
	cell = row.createCell(18);
	cell.setCellType(CellType.NUMERIC);
	cell.setCellValue(ActualTotalammountvalue);
	cell = row.createCell(19);
	cell.setCellType(CellType.STRING);
	String status;
	if(Taxammountvalue.contains(calucaltedvalue)&&(ActualTotalammountvalue).contains(ExpectedTotalammountvalue))
     {
		
		status="PASS";
		CellStyle style = workbook.createCellStyle();
		Font font= workbook.createFont();
		font.setColor(IndexedColors.GREEN.getIndex());
		font.setBold(true);
		style.setFont(font);
		cell.setCellStyle(style);
	}
	else
	{
		status="FAIL";
		CellStyle style = workbook.createCellStyle();
		Font font= workbook.createFont();
		font.setColor(IndexedColors.RED.getIndex());
		font.setBold(true);
		style.setFont(font);
		cell.setCellStyle(style);
		}
	
	
	cell.setCellValue(status);
	
	//System.out.println(OrderId);
	//String subtotla = Float.toString(subtotlaValue);
	//System.out.println("String is: "+subtotla);
	System.out.println(subtotlaValue);
	//String shippingammount = Float.toString(shippingammountvalue);
	//System.out.println("String is: "+shippingammount);
	System.out.println(shippingammountvalue);
	//String Taxammount = Float.toString(Taxammountvalue);
	//System.out.println("String is: "+Taxammount);
	System.out.println(Taxammountvalue);
	//String Totalammount = Float.toString(Totalammountvalue);
	//System.out.println("String is: "+Totalammount);
	System.out.println(ActualTotalammountvalue);
	System.out.println(ExpectedTotalammountvalue);
	//String Actualtax = Float.toString(ActualTax);
	//System.out.println("String is: "+Actualtax);
	System.out.println(giventaxvalue);
	//String userpaneltax = Float.toString(userpaneltaxvalue);
	//System.out.println("String is: "+userpaneltax);
	System.out.println(calucaltedvalue);
	
		FileOutputStream fileOut = new FileOutputStream(file);
	
	workbook.write(fileOut);

	fileOut.flush();
	fileOut.close();
//return writeResultstoXLSx(String OrderId,String subtotla,String shippingammount,String TaxAmmount,String Totalammount,String giventaxvalue1,String userpaneltaxvalue);
    } catch (Exception e) {
        e.printStackTrace();
    }
//return fileOut;
//return writeResultstoXLSx(String OrderId,String subtotla,String shippingammount,String TaxAmmount,String Totalammount,String giventaxvalue1,String userpaneltaxvalue);

}





public void writeResultstoXLSxReg(String Website,String OrderId,String subtotlaValue,String shippingammountvalue,String state,String Zipcode,String Taxammountvalue,String ActualTotalammountvalue, String ExpectedTotalammountvalue,String giventaxvalue,String calucaltedvalue)
{
	//String fileOut="";
try{
	
	File file=new File(System.getProperty("user.dir")+"/src/test/resources/BraunTaxDetails_Reg.xlsx");
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
	cell.setCellValue("Orders Details");
	
	row = sheet.createRow(1);
	cell = row.createCell(0);
	cell.setCellStyle(cs);
	cell.setCellValue("Web Order Number");
	cell = row.createCell(1);
	cell.setCellStyle(cs);
	cell.setCellValue("SubTotal");
	cell = row.createCell(2);
	cell.setCellStyle(cs);
	cell.setCellValue("Shipping");
	cell=row.createCell(3);
	cell.setCellStyle(cs);
	cell.setCellValue("TaxRate");
	cell=row.createCell(4);
	cell.setCellStyle(cs);
	cell.setCellValue("Web Configured TaxRate");
	cell=row.createCell(5);
	cell.setCellStyle(cs);
	cell.setCellValue("Actual TaxAmount");
	cell=row.createCell(6);
	cell.setCellStyle(cs);
	cell.setCellValue("Expected TaxAmount");
	
	rowcount=2;
	
	}
	
	else
	{
	
	sheet=workbook.getSheet("TaxDetails");	
	rowcount=sheet.getLastRowNum()+1;
	}
	row = sheet.createRow(rowcount);
	
	cell = row.createCell(0);
	cell.setCellType(CellType.NUMERIC);
	int SNo=rowcount-1;
	cell.setCellValue(SNo);
	cell = row.createCell(1);
	cell.setCellType(CellType.NUMERIC);
	cell.setCellValue("");
	cell = row.createCell(2);
	cell.setCellType(CellType.STRING);
	cell.setCellValue("HOMEANDHEALTH");
	cell = row.createCell(3);
	cell.setCellType(CellType.STRING);
	cell.setCellValue("User");
	cell = row.createCell(4);
	cell.setCellType(CellType.STRING);
	cell.setCellValue("Lotuswave");
	cell = row.createCell(5);
	cell.setCellType(CellType.STRING);
	cell.setCellValue("B2C");
	cell = row.createCell(6);
	cell.setCellType(CellType.STRING);
	
	String Site;
	if(Website.contains("braunhealth"))
     {
		
		Site="Braun US";
		
}
	else
	{
		Site="";
	}
	cell.setCellValue(Site);
	
	cell = row.createCell(7);
	cell.setCellValue(OrderId);
	cell = row.createCell(8);
	cell.setCellType(CellType.NUMERIC);
	cell.setCellValue(subtotlaValue);
	cell = row.createCell(9);
	cell.setCellType(CellType.NUMERIC);
	cell.setCellValue(shippingammountvalue);
	cell = row.createCell(10);
	cell.setCellType(CellType.NUMERIC);
	cell.setCellValue(state);
	cell = row.createCell(11);
	cell.setCellType(CellType.NUMERIC);
	cell.setCellValue(Zipcode);
	cell = row.createCell(12);
	cell.setCellType(CellType.NUMERIC);
	cell.setCellValue(Taxammountvalue);
	cell = row.createCell(13);
	cell.setCellType(CellType.NUMERIC);
	cell.setCellValue(ActualTotalammountvalue);
	cell = row.createCell(14);
	cell.setCellType(CellType.STRING);
	String TaxonShipping;
	  if((state.equals("Illinois"))||(state.equals("Florida")))
     {
		TaxonShipping="NO";	
}
	else
	{
		TaxonShipping="YES";
	}
	cell.setCellValue(TaxonShipping);
	cell = row.createCell(15);
	cell.setCellType(CellType.NUMERIC);
	cell.setCellValue(giventaxvalue);
	cell = row.createCell(16);
	cell.setCellType(CellType.NUMERIC);
	cell.setCellValue(calucaltedvalue);
	cell = row.createCell(17);
	cell.setCellType(CellType.NUMERIC);
	cell.setCellValue(ExpectedTotalammountvalue);
	cell = row.createCell(18);
	cell.setCellType(CellType.NUMERIC);
	cell.setCellValue(ActualTotalammountvalue);
	cell = row.createCell(19);
	cell.setCellType(CellType.STRING);
	String status;
	if(Taxammountvalue.contains(calucaltedvalue)&&(ActualTotalammountvalue).contains(ExpectedTotalammountvalue))
     {
		
		status="PASS";
		CellStyle style = workbook.createCellStyle();
		Font font= workbook.createFont();
		font.setColor(IndexedColors.GREEN.getIndex());
		font.setBold(true);
		style.setFont(font);
		cell.setCellStyle(style);
	}
	else
	{
		status="FAIL";
		CellStyle style = workbook.createCellStyle();
		Font font= workbook.createFont();
		font.setColor(IndexedColors.RED.getIndex());
		font.setBold(true);
		style.setFont(font);
		cell.setCellStyle(style);
		}
	
	
	cell.setCellValue(status);
	
	System.out.println(OrderId);
	//String subtotla = Float.toString(subtotlaValue);
	//System.out.println("String is: "+subtotla);
	System.out.println(subtotlaValue);
	//String shippingammount = Float.toString(shippingammountvalue);
	//System.out.println("String is: "+shippingammount);
	System.out.println(shippingammountvalue);
	//String Taxammount = Float.toString(Taxammountvalue);
	//System.out.println("String is: "+Taxammount);
	System.out.println(Taxammountvalue);
	//String Totalammount = Float.toString(Totalammountvalue);
	//System.out.println("String is: "+Totalammount);
	System.out.println(ActualTotalammountvalue);
	System.out.println(ExpectedTotalammountvalue);
	//String Actualtax = Float.toString(ActualTax);
	//System.out.println("String is: "+Actualtax);
	System.out.println(giventaxvalue);
	//String userpaneltax = Float.toString(userpaneltaxvalue);
	//System.out.println("String is: "+userpaneltax);
	System.out.println(calucaltedvalue);
	
		FileOutputStream fileOut = new FileOutputStream(file);
	
	workbook.write(fileOut);

	fileOut.flush();
	fileOut.close();
//return writeResultstoXLSx(String OrderId,String subtotla,String shippingammount,String TaxAmmount,String Totalammount,String giventaxvalue1,String userpaneltaxvalue);
    } catch (Exception e) {
        e.printStackTrace();
    }
//return fileOut;
//return writeResultstoXLSx(String OrderId,String subtotla,String shippingammount,String TaxAmmount,String Totalammount,String giventaxvalue1,String userpaneltaxvalue);

}
















public HashMap<String,String> taxValidation(String tax,String state) {
	// TODO Auto-generated method stub
	HashMap<String,String> data=new HashMap<String,String>();
	try{
	Thread.sleep(3000);
	/*
	* NumberFormat n_f= NumberFormat.getInstance();
	* n_f.setMaximumFractionDigits(2); String tax_percent=n_f.format(taxpercent);
	*/
	Float giventaxvalue=Float.valueOf(tax);
	String giventaxvalue1=Float.toString(giventaxvalue);
	data.put("giventaxvalue",giventaxvalue1);
	String subtotla=Common.getText("xpath", "//tr[@class='totals sub']/td/span").replace("$", "");
	Thread.sleep(3000);
	// subtotla.replace("", newChar)
	Float subtotlaValue=Float.valueOf(subtotla);
	data.put("subtotlaValue",subtotla);
	// Capabilities cap = (WebDriver).getCapabilities();
	String shippingammount=Common.getText("xpath", "//span[@data-th='Shipping']").replace("$", "");
	Thread.sleep(3000);
	Float shippingammountvalue=Float.valueOf(shippingammount);
	data.put("shippingammountvalue",shippingammount);
	Thread.sleep(3000);
	String TaxAmmount=Common.getText("xpath", "//td[@data-th='Tax']//span").replace("$", "");
	Float Taxammountvalue=Float.valueOf(TaxAmmount);
	data.put("Taxammountvalue",TaxAmmount);
	/*
	* NumberFormat n_f= NumberFormat.getInstance();
	* n_f.setMaximumFractionDigits(2); String
	* Tax_Amount=n_f.format(Taxammountvalue);
	*/
	Thread.sleep(3000);
	String ActualTotalAmmount=Common.getText("xpath", "//tr[@class='grand totals']//span").replace("$", "");
	Float ActualTotalammountvalue=Float.valueOf(ActualTotalAmmount);
	data.put("ActualTotalammountvalue",ActualTotalAmmount);
	// Float Total=(subtotlaValue+shippingammountvalue);
	Float ExpectedTotalAmmount = subtotlaValue+shippingammountvalue+Taxammountvalue;
    String ExpectedTotalAmount=String.valueOf(ExpectedTotalAmmount);
    String ExpectedTotalAmount2 = new BigDecimal(ExpectedTotalAmount).setScale(2, BigDecimal.ROUND_DOWN).toString();
	
    data.put("ExpectedTotalAmmountvalue",ExpectedTotalAmount2);
    System.out.println(ExpectedTotalAmount);
 
	//data.put("ExpectedTotalAmmountvalue",ExpectedTotalAmount);
	if((state.equals("Illinois"))||(state.equals("Florida"))) {
	Float calucaltedvalue= ((subtotlaValue)*giventaxvalue)/100;
	//String userpaneltaxvalue = new BigDecimal(calucaltedvalue).setScale(2, BigDecimal.ROUND_UP).toString();
	NumberFormat nf= NumberFormat.getInstance(); nf.setMaximumFractionDigits(2); 
	String userpaneltaxvalue=nf.format(calucaltedvalue);
	data.put("calculatedvalue",userpaneltaxvalue);
	System.out.println(TaxAmmount);
	System.out.println(userpaneltaxvalue);
	}
	else {
	Float calucaltedvalue= ((subtotlaValue+shippingammountvalue)*giventaxvalue)/100;
	String userpaneltaxvalue = new BigDecimal(calucaltedvalue).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
	data.put("calculatedvalue",userpaneltaxvalue);
	System.out.println(TaxAmmount);
	System.out.println(userpaneltaxvalue);
	}
	Common.assertionCheckwithReport(TaxAmmount.equals(TaxAmmount),"verifying tax calculation", "tax rate is matches to given shipping address tax ","successfully tax rate is matches to given shipping address tax", "tax rate is not matches to given shipping address tax");
	}
	catch(Exception |Error e)
	{
	report.addFailedLog("verifying tax calculation", "getting price values from shipping page ", "Faield to get price value from shipping page", Common.getscreenShotPathforReport("TaxRates"));

	 e.printStackTrace();
	Assert.fail();
	}

	return data;
	}



public void RegisterShippingAddress(String dataSet,String Street,String City,String postcode,String Region) throws InterruptedException {
    try {
        Thread.sleep(8000);
        Common.textBoxInput("name", "firstname", data.get(dataSet).get("FirstName"));
        Common.textBoxInput("name", "lastname", data.get(dataSet).get("LastName"));
        Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']", Street);
       // Thread.sleep(3000);
        //Common.actionsKeyPress(Keys.ESCAPE);
        Thread.sleep(2000);
        Sync.waitElementPresent("xpath", "//form[@id='co-shipping-form']//input[@name='city']");
       Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='city']", City);
       Thread.sleep(5000);
        Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='postcode']", postcode);
        Thread.sleep(5000);
 
        Common.dropdown("name", "region_id",Common.SelectBy.TEXT, Region);
        Thread.sleep(5000);
        
        Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='telephone']", data.get(dataSet).get("phone"));
        
        

  		Sync.waitElementPresent("xpath", "//button[@class='action primary action-save-address']");
  		Common.clickElement("xpath", "//button[@class='action primary action-save-address']");
  				
        
        
      Sync.waitElementPresent("xpath", "(//span[text()='Ship Here'])[2]");
      		Common.clickElement("xpath", "(//span[text()='Ship Here'])[2]");
      		
      				


    }catch(Exception |Error e)
    {
        e.printStackTrace();
        Assert.fail();
    }
}


public void NewAdressfrom() throws Exception
{
	String expectedResult="Navigate to Shipping Address Page";
	try {
		
		Thread.sleep(3000);
		 Common.actionsKeyPress(Keys.ESCAPE);
		Sync.waitElementPresent("xpath", "//span[text()='New Address']");
		Common.clickElement("xpath", "//span[text()='New Address']");
		
		report.addPassLog(expectedResult, "Should display Shipping Address Page", "Shipping Address Page display successfully", Common.getscreenShotPathforReport("Shipping Address page display  success"));
	}catch(Exception |Error e)
	{
		report.addFailedLog(expectedResult,"Should display Shipping Address Page", "Shipping Address Page not displayed", Common.getscreenShotPathforReport("Shipping Address Failed"));
		Assert.fail();
	}
}

public void RegprepareTaxData(String fileName) {
	// TODO Auto-generated method stub

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
		cell.setCellValue("Orders Details");
		
		    
		row = sheet.createRow(1);
		cell = row.createCell(0);
		cell.setCellStyle(cs);
		cell.setCellValue("S.No");
		cell = row.createCell(1);
		cell.setCellStyle(cs);
		cell.setCellValue("UC No.");
		cell = row.createCell(2);
		cell.setCellStyle(cs);
		cell.setCellValue("Bussiness Segmet");
		cell = row.createCell(3);
		cell.setCellStyle(cs);
		cell.setCellValue("Test Phase");
		cell = row.createCell(4);
		cell.setCellStyle(cs);
		cell.setCellValue("Tester Name");
		cell = row.createCell(5);
		cell.setCellStyle(cs);
		cell.setCellValue("Web Type");
		cell = row.createCell(6);
		cell.setCellStyle(cs);
		cell.setCellValue("Website");
		
		
		cell = row.createCell(7);
		cell.setCellStyle(cs);
		cell.setCellValue("Web order Number");
		cell = row.createCell(8);
		cell.setCellStyle(cs);
		cell.setCellValue("SubTotal");
		cell = row.createCell(9);
		cell.setCellStyle(cs);
		cell.setCellValue("Shipping");
		cell = row.createCell(10);
		cell.setCellStyle(cs);
		cell.setCellValue("State");
		cell = row.createCell(11);
		cell.setCellStyle(cs);
		cell.setCellValue("Zipcode");
		cell = row.createCell(12);
		cell.setCellStyle(cs);
		cell.setCellValue("Tax");
		cell=row.createCell(13);
		cell.setCellStyle(cs);
		cell.setCellValue("Order Total");
		cell=row.createCell(14);
		cell.setCellStyle(cs);
		cell.setCellValue("Tax on Shiping (Y/N)");
		cell=row.createCell(15);
		cell.setCellStyle(cs);
		cell.setCellValue("Web Configured Tax Rate");
		cell=row.createCell(16);
		cell.setCellStyle(cs);
		cell.setCellValue("Expected TaxAmount");
		cell=row.createCell(17);
		cell.setCellStyle(cs);
		cell.setCellValue("Expected OrderTotal Amount");
		cell=row.createCell(18);
		cell.setCellStyle(cs);
		cell.setCellValue("Actual OrderTotal Amount");
		cell=row.createCell(19);
		cell.setCellStyle(cs);
		cell.setCellValue("Digital QA Status(PASS/FAIL)");
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
public String guestorder_Verifying() throws Exception{
	String OrderId="";
	//Thread.sleep(10000);
	//Common.textBoxInput("id", "//textarea[contains(@id,'tt-c-comment-field')]","Ceate accounts test ");
	String expectedResult = "It redirects to order confirmation page";
	try{
	Sync.waitPageLoad();
	
	
	
	for(int i=0;i<10;i++){
		Thread.sleep(5000);
		if(Common.getCurrentURL().contains("success")){
			break;
		}
		
	}
	
	String sucessMessage=Common.getText("xpath", "//h1[@class='page-title']/span");
	System.out.println(sucessMessage);
	 OrderId=Common.getText("xpath", "//div[@class='checkout-success']//p//span");
	System.out.println("Your order number is:"+OrderId);
	Common.assertionCheckwithReport(sucessMessage.equals("Thank you for your purchase!"),"verifying the product confirmation", expectedResult,"Successfully It redirects to order confirmation page Order Placed","User unabel to go orderconformation page");
		
	}
	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying the product confirmation", expectedResult,
				"User failed to navigate  to order confirmation page", Common.getscreenShotPathforReport("failednavigatepage"));
		Assert.fail();
	}
	return OrderId;
}







public void PopUp() throws Exception
{
String expectedResult="Navigate to newsletter popup window";
try {
Thread.sleep(5000);
Sync.waitElementPresent("xpath", "//div[@id='wpn-lightbox-close-newsletter']");
Common.clickElement("xpath", "//div[@id='wpn-lightbox-close-newsletter']");
report.addPassLog(expectedResult, "Should display newsletter popup window", "Shipping newsletter popup window display successfully", Common.getscreenShotPathforReport("newsletter popup window display success"));
}catch(Exception |Error e)
{
report.addFailedLog(expectedResult,"Should display newsletter popup window", "newsletter popup window not displayed", Common.getscreenShotPathforReport("newsletter popup window Failed"));
Assert.fail();
}
}



//new//




public void plpvaladation(String dataSet) throws InterruptedException 
{
String expectedResult="plppage valadation";
try {

	

	int title= Common.findElements("xpath", "(//label[text()='Sort By'])[1]").size();

	 


	System.out.println(title);
	 

	Common.assertionCheckwithReport(title>0, "verifying Sort by block", " Sort by block  should dispaly", "sucessfully lands on  Sort by block page ", "faield to land on Sort by block page");
	Thread.sleep(3000);
	Sync.waitElementPresent("xpath", "(//select[@id='sorter'])[1]");
	//Common.clickElement("xpath", "(//select[@id='sorter'])[1]");
	
	Common.dropdown("xpath", "(//select[@id='sorter'])[1]", SelectBy.TEXT, data.get(dataSet).get("Position"));

	int prodcutname= Common.findElements("xpath", "(//select[@id='sorter'])[1]").size();

	 


	System.out.println(prodcutname);
	 
	Common.getscreenShotPathforReport("prodcutname");
	Common.assertionCheckwithReport(title>0, "verifying Sort by block", " Sort by block  should dispaly", "sucessfully lands on  Sort by block page ", "faield to land on Sort by block page");

	Common.actionsKeyPress(Keys.ARROW_DOWN);
	Common.actionsKeyPress(Keys.ARROW_DOWN);
	Common.actionsKeyPress(Keys.ARROW_DOWN);
	Common.actionsKeyPress(Keys.ARROW_DOWN);
	Common.actionsKeyPress(Keys.ARROW_DOWN);
	Common.actionsKeyPress(Keys.ARROW_DOWN);
	Common.actionsKeyPress(Keys.ARROW_DOWN);
	Common.actionsKeyPress(Keys.ARROW_DOWN);
	Common.actionsKeyPress(Keys.ARROW_DOWN);
	Common.actionsKeyPress(Keys.ARROW_DOWN);
	Common.actionsKeyPress(Keys.ARROW_DOWN);
	Common.actionsKeyPress(Keys.ARROW_DOWN);
	Common.actionsKeyPress(Keys.ARROW_DOWN);
	Common.actionsKeyPress(Keys.ARROW_DOWN);
	Common.actionsKeyPress(Keys.ARROW_DOWN);
	Common.actionsKeyPress(Keys.ARROW_DOWN);
	Common.actionsKeyPress(Keys.ARROW_DOWN);
	Thread.sleep(3000);
	ExtenantReportUtils.addPassLog("verifying  sort option", "User should select  sort",
			"user faield to Select the Most Viewed sort option",
			Common.getscreenShotPathforReport("list of prodcts name"));
	Thread.sleep(5000);
} catch (Exception | Error e) {

	ExtenantReportUtils.addFailedLog("verifying  Most Viewed sort option",
			"User should  select Most Viewed sort option",
			"user Successfully Selected the Most Viewed Sort option",
			Common.getscreenShotPathforReport("list of prodcuts displayed"));
	Assert.fail();
}
try{
Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Thread.sleep(3000);
		ExtenantReportUtils.addPassLog("verifying  sort option", "User should select  sort",
				"user faield to Select the Most Viewed sort option",
				Common.getscreenShotPathforReport("list of prodcts name"));
		Thread.sleep(5000);
	} catch (Exception | Error e) {

		ExtenantReportUtils.addFailedLog("verifying  Most Viewed sort option",
				"User should  select Most Viewed sort option",
				"user Successfully Selected the Most Viewed Sort option",
				Common.getscreenShotPathforReport("list of prodcuts displayed"));
		Assert.fail();
	
		}
		
	}




public void productprice(String dataSet) throws Exception
{
String expectedResult="plp vallidation ";
try {
int title=Common.findElements("xpath", "(//label[contains(text(),'Sort By')])[1]").size();

System.out.println(title);

Thread.sleep(3000);

Sync.waitElementPresent("xpath", "(//select[@data-role='sorter'])[1]");

Thread.sleep(3000);
/*int a=Common.findElements("xpath", "//div[contains(text(),'X')]").size();

if(a>0)
{
Common.clickElement("xpath", "//div[contains(text(),'X')]");
}
else{

}*/
Thread.sleep(2000);


Common.dropdown("xpath", "(//select[@data-role='sorter'])[1]", Common.SelectBy.TEXT, data.get(dataSet).get("Position"));
Thread.sleep(3000);
Common.getscreenShotPathforReport("productPrice");
int product=Common.findElements("xpath", "(//select[@id='sorter'])[1]").size();

System.out.println(product);
Thread.sleep(3000);
Common.actionsKeyPress(Keys.ARROW_DOWN);
Common.actionsKeyPress(Keys.ARROW_DOWN);
Common.actionsKeyPress(Keys.ARROW_DOWN);
Common.actionsKeyPress(Keys.ARROW_DOWN);
Common.actionsKeyPress(Keys.ARROW_DOWN);
Common.actionsKeyPress(Keys.ARROW_DOWN);
Common.actionsKeyPress(Keys.ARROW_DOWN);
Common.actionsKeyPress(Keys.ARROW_DOWN);
Common.actionsKeyPress(Keys.ARROW_DOWN);
Common.actionsKeyPress(Keys.ARROW_DOWN);
Common.actionsKeyPress(Keys.ARROW_DOWN);
Common.actionsKeyPress(Keys.ARROW_DOWN);
Common.actionsKeyPress(Keys.ARROW_DOWN);
Thread.sleep(3000);

ExtenantReportUtils.addPassLog("verifying product Price", "User should select product Price",
"user faield to Select product price",
Common.getscreenShotPathforReport("productpage"));
Thread.sleep(5000);
} catch (Exception | Error e) {
e.printStackTrace();
ExtenantReportUtils.addFailedLog("verifying  Most Viewed sort option",
"User should  select Most Viewed sort option",
"user Successfully Selected product Price",
Common.getscreenShotPathforReport("productpage"));
e.printStackTrace();
Assert.fail();
}
}



public void Filterprice() throws Exception{
String expectedResult="It should navigate to Filterprice";
try {
Common.actionsKeyPress(Keys.PAGE_UP);

Thread.sleep(5000);
Common.mouseOver("xpath", "//a[@data-opt-path='price=0-10']");

Thread.sleep(3000);
String title=Common.getText("xpath", "//a[@data-opt-path='price=0-10']");
System.out.println(title);
Common.clickElement("xpath", "//a[@data-opt-path='price=0-10']");
//Common.actionsKeyPress(Keys.ARROW_DOWN);

Thread.sleep(5000);

Common.assertionCheckwithReport(title.equals("$0.00 - $10.00"),"Verifying  Filter price page","it shoud navigate to  Filter price page", "successfully  navigated to Replacement Filters Page", "$10.00 - $20.00"); 
Common.getscreenShotPathforReport("succes to navigate to  Filter price page");

} catch (Exception | Error e) {
ExtenantReportUtils.addFailedLog("To verify the Filterprice","should navigate to  Filter price page", "userunable to navigate to  Filter price page", Common.getscreenShotPathforReport("failed to navigate to  Filter price page")); 
e.printStackTrace();

Assert.fail(); 
}




try {

	
	Common.actionsKeyPress(Keys.PAGE_UP);
	
	
	Common.actionsKeyPress(Keys.ARROW_DOWN);
	Common.actionsKeyPress(Keys.ARROW_DOWN);

Thread.sleep(5000);
Common.mouseOver("xpath", "//a[@data-opt-path='price=30-40']");
Common.clickElement("xpath", "//a[@data-opt-path='price=30-40']");
Thread.sleep(6000);
Common.mouseOver("xpath", "//a[@data-opt-path='price=40-50']");
String title=Common.getText("xpath", "//a[@data-opt-path='price=30-40']");
System.out.println(title);
Thread.sleep(6000);
Common.mouseOver("xpath", "//a[@data-opt-path='price=40-50']");
Common.clickElement("xpath", "//a[@data-opt-path='price=40-50']");
String titles=Common.getText("xpath", "//a[@data-opt-path='price=40-50']");
Thread.sleep(5000);
Common.actionsKeyPress(Keys.ARROW_DOWN);
Common.actionsKeyPress(Keys.ARROW_DOWN);

//Common.assertionCheckwithReport(title.equals("$30.00 - $40.00 "),"Verifying  Filter price page","it shoud navigate to  Filter price page", "successfully  navigated to Replacement Filters Page", "$20.00 - $30.00"); 
Common.getscreenShotPathforReport("succes to navigate to  Filter price page");


Common.assertionCheckwithReport(titles.equals("$40.00 - $50.00"),"Verifying  Filter price page","it shoud navigate to  Filter price page", "successfully  navigated to Replacement Filters Page", "$20.00 - $30.00"); 
Common.getscreenShotPathforReport("succes to navigate to  Filter price page");
} catch (Exception | Error e) {
ExtenantReportUtils.addFailedLog("To verify the Filterprice","should navigate to  Filter price page", "userunable to navigate to  Filter price page", Common.getscreenShotPathforReport("failed to navigate to  Filter price page")); 
e.printStackTrace();

Assert.fail(); 
}








try {

	Common.actionsKeyPress(Keys.PAGE_UP);
Common.actionsKeyPress(Keys.PAGE_UP);
Common.actionsKeyPress(Keys.ARROW_DOWN);
Common.actionsKeyPress(Keys.ARROW_DOWN);

Thread.sleep(5000);
/*Common.mouseOver("xpath", "//a[@data-opt-path='price=50-60']");
Common.clickElement("xpath", "//a[@data-opt-path='price=50-60']");
String ammount=Common.getText("xpath", "//a[@data-opt-path='price=50-60']");
System.out.println(ammount);
Thread.sleep(5000);*/
Common.actionsKeyPress(Keys.ARROW_DOWN);
Common.actionsKeyPress(Keys.ARROW_DOWN);

Common.mouseOver("xpath", "//a[@data-opt-path='price=60-70']");
Common.clickElement("xpath", "//a[@data-opt-path='price=60-70']");
String title=Common.getText("xpath", "//a[@data-opt-path='price=60-70']");
System.out.println(title);
Common.actionsKeyPress(Keys.PAGE_UP);
Common.actionsKeyPress(Keys.ARROW_DOWN);
Common.actionsKeyPress(Keys.ARROW_DOWN);

Common.mouseOver("xpath", "//a[@data-opt-path='price=70-80']//span[contains(text(), '$70.00')]");

Common.clickElement("xpath", "//a[@data-opt-path='price=70-80']//span[contains(text(), '$70.00')]");
String price=Common.getText("xpath", "//a[@data-opt-path='price=70-80']//span[contains(text(), '$70.00')]");
System.out.println(price);
Thread.sleep(5000);


//Common.assertionCheckwithReport(ammount.equals("$50.00 - $60.00"),"Verifying  Filter price page","it shoud navigate to  Filter price page", "successfully  navigated to  Filter price page", "$30.00 - $40.00"); 
Common.getscreenShotPathforReport("succes to navigate to  Filter price page");
Common.assertionCheckwithReport(title.equals("$60.00 - $70.00"),"Verifying  Filter price page","it shoud navigate to  Filter price page", "successfully  navigated to  Filter price page", "$30.00 - $40.00"); 
Common.getscreenShotPathforReport("succes to navigate to  Filter price page");

//Common.assertionCheckwithReport(price.equals("$70.00 and above"),"Verifying  Filter price page","it shoud navigate to  Filter price page", "successfully  navigated to  Filter price page", "$30.00 - $40.00"); 
Common.getscreenShotPathforReport("succes to navigate to  Filter price page");
} catch (Exception | Error e) {
ExtenantReportUtils.addFailedLog("To verify the Filterprice","should navigate to  Filter price page", "userunable to navigate to  Filter price page", Common.getscreenShotPathforReport("failed to navigate to  Filter price page")); 
e.printStackTrace();

Assert.fail(); 
}






}

public void PDP_Validation(String dataSet) throws InterruptedException
{
Thread.sleep(5000);
String expectedResult="It should navigate to PDP_page";

try {

String title=Common.getText("xpath", "(//a[contains(text(), 'Forehead Thermometer')])[1]");
System.out.println(title);
Common.clickElement("xpath", "(//a[contains(text(), 'Forehead Thermometer')])[1]");
Thread.sleep(5000);


/*int a=Common.findElements("xpath", "//div[contains(text(),'X')]").size();

if(a>0)
{
Common.clickElement("xpath", "//div[contains(text(),'X')]");
}
else{

}*/

Common.assertionCheckwithReport(title.equals("Forehead Thermometer"),"Verifying  Filter price page","it shoud navigate to  Filter price page", "successfully  navigated to PDP_page", "PUR PLUS Faucet Filtration System"); 
Common.getscreenShotPathforReport("succes to navigate to  PDP_page");


} catch (Exception | Error e) {
ExtenantReportUtils.addFailedLog("To verify the PDP_page","should navigate to  PDP_page", "userunable to navigate to  PDP_page", Common.getscreenShotPathforReport("failed to navigate to  PDP_page")); 
e.printStackTrace();

Assert.fail(); 
}


try {

String title=Common.getText("xpath", "(//span[contains(text(), 'Forehead Thermometer')])[1]");
System.out.println(title);



Common.assertionCheckwithReport(title.equals("Forehead Thermometer"),"Verifying  Filter price page","it shoud navigate to  Filter price page", "successfully  navigated to PDP_page", "PUR PLUS Faucet Filtration System"); 
Common.getscreenShotPathforReport("succes to navigate to  PDP_page");

String plpTotalAmmount=Common.getText("xpath", "//span[@id='product-price-6']").replace("$", "");
System.out.println("plpTotalAmmount"+plpTotalAmmount);


String pdpTotalAmmount=Common.getText("xpath", "(//span[@id='product-price-6'])[1]");
System.out.println("pdpTotalAmmount"+pdpTotalAmmount);

Common.assertionCheckwithReport(plpTotalAmmount.equals(pdpTotalAmmount),"Verifying  Filter price page","it shoud navigate to  Filter price page", "successfully  navigated to PDP_page", "PUR PLUS Faucet Filtration System"); 
Common.getscreenShotPathforReport("succes to navigate to  PDP_page");


Common.textBoxInput("xpath","(//input[@id='qty'])[1]", data.get(dataSet).get("Qunty"));

Sync.waitElementPresent("xpath", "(//span[text()='Add to cart'])[1]");
Common.clickElement("xpath", "(//span[text()='Add to cart'])[1]"); 

String errormessage=Common.getText("xpath", "//div[text()='The maximum you may purchase is 2.']");
System.out.println(errormessage);

Common.assertionCheckwithReport(errormessage.equals("The maximum you may purchase is 2."),"Verifying  the qunty of  the product","it shoud verify the qunty of the product", "successfully  verifyed error message", "The maximum you may purchase is 2."); 

} catch (Exception | Error e) {
ExtenantReportUtils.addFailedLog("To verify the PDP_page","should navigate to  PDP_page", "userunable to navigate to  PDP_page", Common.getscreenShotPathforReport("failed to navigate to  PDP_page")); 
e.printStackTrace();

Assert.fail(); 
} 

}


public void headerlinkLearnEducation(String dataSet) throws Exception {
	Sync.waitPageLoad();
	//Sync.waitElementClickable("xpath", "//span[contains(text() , 'Learn')]");
	Common.mouseOver("xpath", "//span[contains(text() , 'Learn')]");
	

String Hederlinks=data.get(dataSet).get("megamenus");
	
	String[] hedrs=Hederlinks.split(",");
	String titles = data.get(dataSet).get("Title");
	String[] title = titles.split(",");
	int i=0;;
	
	try{
	for(i=0;i<hedrs.length;i++){
		System.out.println(hedrs[i]);
		Thread.sleep(3000);
		Sync.waitElementClickable("xpath", "//ul[@data-menu='menu-114']//span[text()='"+hedrs[i]+"']");
		Common.clickElement("xpath", "//ul[@data-menu='menu-114']//span[text()='"+hedrs[i]+"']");
		Thread.sleep(3000);
		System.out.println(Common.getPageTitle());
		Common.assertionCheckwithReport(Common.getCurrentURL().contains(title[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
		Common.mouseOver("xpath", "//span[contains(text() , 'Learn')]");
	}
	}
	catch (Exception | Error e) {
		e.printStackTrace();
        ExtenantReportUtils.addFailedLog("validating Header Links " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the header link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the headerlink"));
        e.printStackTrace();
        Assert.fail();

	}
}

public void headLinksValidations_Shop(String dataSet) throws Exception{
	Thread.sleep(3000);
	Sync.waitPageLoad();
	Common.mouseOver("xpath", "//span[text()='Shop']");
	
	String Hederlinks=data.get(dataSet).get("megamenus");
	
	String[] hedrs=Hederlinks.split(",");
	String titles = data.get(dataSet).get("Title");
	String[] title = titles.split(",");
	int i=0;
	
	try{
	for(i=0;i<hedrs.length;i++){

		System.out.println(hedrs[i]);
		//Sync.waitElementClickable("xpath", "//a[@title='"+hedrs[i]+"']");
		Common.clickElement("xpath", "//a[@title='"+hedrs[i]+"']");
		Thread.sleep(5000);
		System.out.println(Common.getPageTitle());
		
		Common.assertionCheckwithReport(Common.getPageTitle().contains(title[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
		Common.mouseOver("xpath", "//span[text()='Shop']");	
	}
	}
	catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("validating Header Links " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the header link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the headerlink"));
	
		Assert.fail();

	}
}


public void headLinksValidations_Support(String dataSet) throws Exception{
	Thread.sleep(3000);
	Sync.waitPageLoad();
	Common.mouseOver("xpath", "//span[text()='Support']");
	
	String Hederlinks=data.get(dataSet).get("megamenus");
	
	String[] hedrs=Hederlinks.split(",");
	String titles = data.get(dataSet).get("Title");
	String[] title = titles.split(",");
	int i=0;
	
	try{
	for(i=0;i<hedrs.length;i++){

		System.out.println(hedrs[i]);
		//Sync.waitElementClickable("xpath", "//a[@title='"+hedrs[i]+"']");
		Common.clickElement("xpath", "//li[@class='nav-main nav-main-braun__item nav-main nav-main-braun__item--parent level-top parent nav-learn nav-dropdown nav-support ui-menu-item']//span[text()='"+hedrs[i]+"']");
		Thread.sleep(5000);
		System.out.println(Common.getPageTitle());
		Thread.sleep(5000);
		System.out.println(Common.getPageTitle());
		//Common.assertionCheckwithReport(Common.getPageTitle().contains(title[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
		Common.mouseOver("xpath", "//span[text()='Support']");	
	}
	}
	catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("validating Header Links " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the header link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the headerlink"));
	
		Assert.fail();

	}
}




public void footerValidations_Shop(String dataSet) throws Exception{
	Thread.sleep(3000);
	Sync.waitPageLoad();
	
	Common.actionsKeyPress(Keys.END);
  String Hederlinks=data.get(dataSet).get("megamenus");
	
	String[] hedrs=Hederlinks.split(",");
	String titles = data.get(dataSet).get("Title");
	String[] title = titles.split(",");
	int i=0;
	
	try{
	for(i=0;i<hedrs.length;i++){
		System.out.println(hedrs[i]);
		Sync.waitElementClickable("xpath", "//span[text()='"+hedrs[i]+"']");
		Common.clickElement("xpath", "//span[text()='"+hedrs[i]+"']");
		Thread.sleep(3000);
		System.out.println(Common.getPageTitle());
		Common.assertionCheckwithReport(Common.getPageTitle().contains(title[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
		Common.actionsKeyPress(Keys.END);	
	}
	}
	catch (Exception | Error e) {
		e.printStackTrace();
        ExtenantReportUtils.addFailedLog("validating footer Links " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the footer link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the footer link"));
        Assert.fail();

	}
}




//E2E//

public void prepareOrdersData(String fileName) {
	// TODO Auto-generated method stub
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
		sheet = workbook.createSheet("OrderDetails");
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
		cell.setCellValue("Orders Details");


		row = sheet.createRow(1);
		cell = row.createCell(0);
		cell.setCellStyle(cs);
		cell.setCellValue("S.No");
		cell = row.createCell(1);
		cell.setCellStyle(cs);
		cell.setCellValue("Website");
		cell = row.createCell(2);
		cell.setCellStyle(cs);
		cell.setCellValue("Tester");
		cell = row.createCell(3);
		cell.setCellStyle(cs);
		cell.setCellValue("Test scenario Description");
		cell = row.createCell(4);
		cell.setCellStyle(cs);
		cell.setCellValue("Web Order Number");
		cell = row.createCell(5);
		cell.setCellStyle(cs);
		cell.setCellValue("Order Confirnmation Message");
		cell = row.createCell(6);
		cell.setCellStyle(cs);
		cell.setCellValue("Order Status Magento");


		cell = row.createCell(7);
		cell.setCellStyle(cs);
		cell.setCellValue("Subtotal");
		cell = row.createCell(8);
		cell.setCellStyle(cs);
		cell.setCellValue("Shipping");
		cell = row.createCell(9);
		cell.setCellStyle(cs);
		cell.setCellValue("State");
		cell = row.createCell(10);
		cell.setCellStyle(cs);
		cell.setCellValue("Zipcode");
		cell = row.createCell(11);
		cell.setCellStyle(cs);
		cell.setCellValue("Tax");
		cell = row.createCell(12);
		cell.setCellStyle(cs);
		cell.setCellValue("Estimated Order Total");
		cell=row.createCell(13);
		cell.setCellStyle(cs);
		cell.setCellValue("Discount");
		cell=row.createCell(14);
		cell.setCellStyle(cs);
		cell.setCellValue("Actual Order Total");
		cell=row.createCell(15);
		cell.setCellStyle(cs);
		cell.setCellValue("Payment Method");
		rowcount=2;
		}

		else
		{
		workbook = new XSSFWorkbook(new FileInputStream(file));
		sheet=workbook.getSheet("OrderDetails");
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






	
	





public HashMap<String, String> ShipingAddress(String dataSet) throws Exception{
HashMap<String, String> Shippingaddress = new HashMap<String, String>();
try{
Sync.waitElementPresent("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']");
Common.textBoxInput("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']",data.get(dataSet).get("Email"));
Sync.waitElementPresent("xpath", "//input[@name='firstname']");
Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(dataSet).get("FirstName"));
Sync.waitElementPresent("xpath", "//input[@name='lastname']");
Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));
/*Sync.waitElementPresent("xpath", "//input[@name='lastname']");
Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));*/
Common.textBoxInput("xpath", "//input[@name='street[0]']", data.get(dataSet).get("Street"));
/*
Sync.waitElementPresent("xpath", "//select[@name='country_id']");
Common.dropdown("xpath", "//select[@name='country_id']", SelectBy.TEXT, data.get(dataSet).get("Countryname"));*/
Thread.sleep(2000);
Common.dropdown("xpath", "//select[@name='region_id']", Common.SelectBy.TEXT,data.get(dataSet).get("Region"));
Thread.sleep(2000);
String Shippingvalue = Common.findElement("xpath", "//select[@name='region_id']").getAttribute("value");
String Shippingstate = Common.findElement("xpath", "//select[@name='region_id']//option[@value='" + Shippingvalue + "']").getText(); Shippingaddress.put("ShippingState", Shippingstate); Sync.waitElementPresent("xpath", "//input[@name='city']");
Common.textBoxInput("xpath", "//input[@name='city']", data.get(dataSet).get("City"));
Thread.sleep(1000);
Common.textBoxInputClear("name", "postcode");
Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
Thread.sleep(2000);
String ShippingZip = Common.findElement("name", "postcode").getAttribute("value");
System.out.println("*****" + ShippingZip + "*******");
Shippingaddress.put("ShippingZip", ShippingZip);
Thread.sleep(5000);
Sync.waitElementPresent("xpath", "//input[@name='telephone']");
Common.textBoxInput("xpath", "//input[@name='telephone']", data.get(dataSet).get("phone"));
Thread.sleep(1000);
Common.actionsKeyPress(Keys.ENTER);
}
catch(Exception |Error e) {
e.printStackTrace();
ExtenantReportUtils.addFailedLog("verifying shipping addres filling", "user will fill the all the shipping", "faield to add new shipping address",Common.getscreenShotPathforReport("faieldsshippingpagefilling"));
Assert.fail();
}
System.out.println(Shippingaddress);
return Shippingaddress;
}



public HashMap<String,String> OrderSummaryValidation() {
	// TODO Auto-generated method stub
	HashMap<String,String> data=new HashMap<String,String>();
	try{
	Thread.sleep(2000);
   String subtotla=Common.getText("xpath", "//tr[@class='totals sub']/td/span").replace("$", "");
	// subtotla.replace("", newChar)
	Float subtotlaValue=Float.valueOf(subtotla);
	data.put("subtotlaValue",subtotla);

	// Capabilities cap = (WebDriver).getCapabilities();

	String shippingammount=Common.getText("xpath", "//span[@data-th='Shipping']").replace("$", "");
	Float shippingammountvalue=Float.valueOf(shippingammount);
	data.put("shippingammountvalue",shippingammount);

	String ActualTotalAmmount=Common.getText("xpath", "//tr[@class='grand totals']//span").replace("$", "");
	Float ActualTotalammountvalue=Float.valueOf(ActualTotalAmmount);
	data.put("ActualTotalammountvalue",ActualTotalAmmount);

	int SizesofDis= Common.findElements("xpath", "(//span[@data-th='checkout.sidebar.summary.totals.discount'])").size();
	if(SizesofDis>0) {
	String Discount=Common.getText("xpath", "(//span[@data-th='checkout.sidebar.summary.totals.discount'])").replace("-$", "");
	Float Discountammountvalue=Float.valueOf(Discount);
	data.put("Discountammountvalue",Discount);

	}
	else {

	String Discountammountvalue= "0.00";
	data.put("Discountammountvalue",Discountammountvalue);

	}


	int Sizes= Common.findElements("xpath", "//td[@data-th='Tax']//span").size();
	if(Sizes>0) {

	// Float Taxrate=data.get(dataset).get("tax");

	String TaxAmmount=Common.getText("xpath", "//td[@data-th='Tax']//span").replace("$", "");
	Float Taxammountvalue=Float.valueOf(TaxAmmount);
	data.put("Taxammountvalue",TaxAmmount);


	Float ExpectedTotalAmmount = subtotlaValue+shippingammountvalue+Taxammountvalue;
	String ExpectedTotalAmount=String.valueOf(ExpectedTotalAmmount);
	data.put("ExpectedTotalAmmountvalue",ExpectedTotalAmount);

	}
	else {
	String Taxammountvalue= "0.00";
	data.put("Taxammountvalue",Taxammountvalue);

	Float ExpectedTotalAmmount = subtotlaValue+shippingammountvalue;
	String ExpectedTotalAmount=String.valueOf(ExpectedTotalAmmount);
	data.put("ExpectedTotalAmmountvalue",ExpectedTotalAmount);
	}


	ExtenantReportUtils.addPassLog("verifying tax calculation", "tax rate is matches to given shipping address tax ","successfully tax rate is matches to given shipping address tax", "tax rate is not matches to given shipping address tax");
	}
	catch(Exception |Error e)
	{
	report.addFailedLog("verifying tax calculation", "getting price values from shipping page ", "Faield to get price value from shipping page", Common.getscreenShotPathforReport("TaxRates"));



	e.printStackTrace();
	Assert.fail();

	}




	return data;

	}





public HashMap<String, String> creditCard_payment(String dataSet) throws Exception {
HashMap<String, String> Payment = new HashMap<String, String>();



try {



// Common.scrollIntoView("id", "paymetric_xisecure_frame");
// Common.clickElement("xpath", "//label[@for='ime_paymetrictokenize']");
Thread.sleep(5000);
Common.switchFrames("id", "paymetric_xisecure_frame");
int size = Common.findElements("xpath", "//select[@id='c-ct']").size();
Common.switchToDefault();
Common.assertionCheckwithReport(size > 0, "validating Creditcard option", "click the creadit card label",
"clicking credit card label and open the card fields", "user faield to open credit card form");
} catch (Exception | Error e) {
e.printStackTrace();
ExtenantReportUtils.addFailedLog("validating the Credit Card option", "click the creadit card label",
"faield to click Credit Card option", Common.getscreenShotPathforReport("Cardinoption"));
Assert.fail();



}



try {



Thread.sleep(2000);
// Common.refreshpage();
Common.switchFrames("id", "paymetric_xisecure_frame");
Common.dropdown("xpath", "//select[@id='c-ct']", Common.SelectBy.TEXT, data.get(dataSet).get("cardType"));
String Cardtype=Common.findElement("xpath", "//select[@id='c-ct']").getAttribute("value");
String Card=Common.findElement("xpath","//select[@id='c-ct']//option[@value='"+Cardtype+"']").getText();
Payment.put("Card", Card);
System.out.println("******" +Card+ "*****");
Common.textBoxInput("id", "c-cardnumber", data.get(dataSet).get("cardNumber"));
Common.dropdown("xpath", "//select[@id='c-exmth']", Common.SelectBy.TEXT,data.get(dataSet).get("ExpMonth"));
Common.dropdown("xpath", "//select[@id='c-exyr']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpYear"));
Common.textBoxInput("id", "c-cvv", data.get(dataSet).get("cvv"));
Thread.sleep(2000);



// Common.actionsKeyPress(Keys.ARROW_DOWN);
Common.switchToDefault();

Thread.sleep(1000);
Common.scrollIntoView("xpath", "//span[contains(text(),'Place Order')]");
Thread.sleep(2000);
String URL = Common.getCurrentURL();
Thread.sleep(4000);
if (URL.equals("https://www.braunhealthcare.com/us_en/checkout/#payment")) {



Common.getCurrentURL();
System.out.println(URL);



} else {



	 Common.javascriptclickElement("xpath", "//span[contains(text(),'Place Order')]");
}



// Common.clickElement("xpath", "(//button[@title='Place Order'])");



Common.switchFrames("id", "paymetric_xisecure_frame");
String expectedResult = "credit card fields are filled with the data";
String errorTexts = Common.findElement("xpath", "//div[contains(@id,'error')]").getText();
Common.switchToDefault();
Common.assertionCheckwithReport(errorTexts.isEmpty(),
"validating the credit card information with valid data", expectedResult, "Filled the Card detiles",
"missing field data it showinng error");



Sync.waitPageLoad();
} catch (Exception | Error e) {
ExtenantReportUtils.addFailedLog("validating the Credit Card infromation",
"credit card fields are filled with the data", "faield to fill the Credit Card infromation",
Common.getscreenShotPathforReport("Cardinfromationfail"));
Assert.fail();



}
return Payment;



}




public String  Verify_order() throws InterruptedException {		
	 String Orderid="";
       Thread.sleep(5000);
       //Common.textBoxInput("id", "//textarea[contains(@id,'tt-c-comment-field')]","Ceate accounts test ");
       String expectedResult = "It redirects to order confirmation page";
       try{
       Sync.waitPageLoad();
       Thread.sleep(5000);
      
      
       for(int i=0;i<10;i++){
           Thread.sleep(5000);
           if(Common.getCurrentURL().contains("success")){
               break;
           }
          
       }
      
       String sucessMessage=Common.getText("xpath", "//h1[@class='page-title']/span");
       System.out.println(sucessMessage);
       //String Orderid="";
       int size=Common.findElements("xpath", "(//a[@class='action print'])").size();
       if(size>0) {
       	
       Orderid=Common.getText("xpath", "(//div[@class='column main'])//div[@class='checkout-success']//a//strong");
       }
       else{
       Orderid=Common.getText("xpath", "((//div[@class='column main'])//span)[1]");
       }
       
   	System.out.println(Orderid);
       System.out.println("Your order number is:"+Orderid);
       Common.assertionCheckwithReport(sucessMessage.equals("Thank you for your purchase!"),"verifying the product confirmation", expectedResult,"Successfully It redirects to order confirmation page Order Placed","User unabel to go orderconformation page");
          
       }
       catch (Exception | Error e) {
           e.printStackTrace();
           ExtenantReportUtils.addFailedLog("verifying the product confirmation", expectedResult,
                   "User failed to navigate  to order confirmation page", Common.getscreenShotPathforReport("failednavigatepage"));
           Assert.fail();
           
       }
      
	return Orderid;
   }





public String order_Success() throws Exception {

	String order="";
	//addPaymentDetails(dataSet);
	String expectedResult = "It redirects to order confirmation page";

	//if (Common.findElements("xpath", "//div[@class='message message-error']").size() > 0) {
		//addPaymentDetails(dataSet);
	
	Sync.waitPageLoad();
	Thread.sleep(6000);
	//int placeordercount = Common.findElements("xpath", "//span[text()='Place Order']").size();
	//Juttriles code //("xpath", "//span[text()='Place Order']")
	////button[@title='Place Order']   stage
	
	//String url=automation_properties.getInstance().getProperty(automation_properties.BASEURL);
	String url=Common.getCurrentURL();
	Thread.sleep(3000);
	System.out.println(url);
	
	if(!url.contains("success")){
		//ExtenantReportUtils.addPassLog(description, expectedResult, actualResult, Common.getscreenShotPathforReport(expectedResult));
		/*int sizeofelement=Common.findElements("id", "email").size();
		Common.assertionCheckwithReport(sizeofelement > 0, "verifying the paypal payment ", expectedResult,"open paypal site window", "faild to open paypal account");*/
	}
	
	else{
		try{
	String sucessMessage = Common.getText("xpath", "//h1[@class='page-title']").trim();
	// Assert.assertEquals(sucessMessage, "Your order has been
	// received","Sucess message validations");
	int sizes = Common.findElements("xpath", "//h1[@class='page-title']").size();
	Common.assertionCheckwithReport(sucessMessage.equals("Thank you for your purchase!"),
			"verifying the product confirmation", expectedResult,
			"Successfully It redirects to order confirmation page Order Placed",
			"User unabel to go orderconformation page");
	
	if(Common.findElements("xpath", "(//a[@class='order-number'])//strong").size()>0) {
		order=Common.getText("xpath", "(//a[@class='order-number'])//strong");
	}
	
	
	if(Common.findElements("xpath","//a[@class='order-number']/strong").size()>0) {
		order=	Common.getText("xpath", "//a[@class='order-number']/strong");
	}

		}
		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the product confirmation", expectedResult,
					"User failed to navigate  to order confirmation page", Common.getscreenShotPathforReport("failednavigatepage"));
			Assert.fail();
		}
		

}
	return order;
	   
	}





public void writeOrderNumber(String Website, String OrderIdNumber,String Description, String subtotlaValue, String shippingammountvalue, String Taxammountvalue,String ActualTotalammountvalue,String ExpectedTotalammountvalue,String Discountammountvalue, String ShippingState,String ShippingZip, String Card) throws FileNotFoundException, IOException
{
//String fileOut="";
try{
File file=new File(System.getProperty("user.dir")+"/src/test/resources/BraunUS_E2E_orderDetails.xlsx");
XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
XSSFSheet sheet;
Row row;
Cell cell;
int rowcount;
sheet = workbook.getSheet("OrderDetails");
if((workbook.getSheet("OrderDetails"))==null)
{
sheet = workbook.createSheet("OrderDetails");
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
cell.setCellValue("Orders Details");
row = sheet.createRow(1);
cell = row.createCell(0);
cell.setCellStyle(cs);
cell.setCellValue("Web Order Number");
rowcount=2;
}
else
{
sheet=workbook.getSheet("OrderDetails");
rowcount=sheet.getLastRowNum()+1;
}
row = sheet.createRow(rowcount);
cell = row.createCell(0);
cell.setCellType(CellType.NUMERIC);
int SNo=rowcount-1;
cell.setCellValue(SNo);
cell = row.createCell(1);
cell.setCellType(CellType.STRING);
String websitename;
if(Website.contains("braunhealth"))
{
websitename="Braun US";
}
else
{
websitename="";
}
cell.setCellValue(websitename);
cell = row.createCell(2);
cell.setCellType(CellType.STRING);
cell.setCellValue("Lotuswave");
cell = row.createCell(3);
cell.setCellType(CellType.STRING);
cell.setCellValue(Description);
cell = row.createCell(4);
cell.setCellType(CellType.NUMERIC);
cell.setCellValue(OrderIdNumber);
cell = row.createCell(5);
cell.setCellType(CellType.STRING);
cell.setCellValue("Order placed Successfully");
cell = row.createCell(6);
cell.setCellType(CellType.STRING);
cell.setCellValue("Processing"); cell = row.createCell(7);
cell.setCellType(CellType.NUMERIC);
cell.setCellValue(subtotlaValue);
cell = row.createCell(8);
cell.setCellType(CellType.NUMERIC);
cell.setCellValue(shippingammountvalue);
cell = row.createCell(9);
cell.setCellType(CellType.STRING);
cell.setCellValue(ShippingState);
cell = row.createCell(10);
cell.setCellType(CellType.NUMERIC);
cell.setCellValue(ShippingZip);
cell = row.createCell(11);
cell.setCellType(CellType.STRING);
cell.setCellValue(Taxammountvalue);
cell = row.createCell(12);
cell.setCellType(CellType.NUMERIC);
cell.setCellValue(ExpectedTotalammountvalue);
cell = row.createCell(13);
cell.setCellType(CellType.NUMERIC);
cell.setCellValue(Discountammountvalue);
cell = row.createCell(14);
cell.setCellType(CellType.NUMERIC);
cell.setCellValue(ActualTotalammountvalue);
cell = row.createCell(15);
cell.setCellType(CellType.NUMERIC);
cell.setCellValue(Card);
System.out.println(OrderIdNumber);
//String subtotla = Float.toString(subtotlaValue);
//System.out.println("String is: "+subtotla);
System.out.println(subtotlaValue);
//String shippingammount = Float.toString(shippingammountvalue);
//System.out.println("String is: "+shippingammount);
System.out.println(shippingammountvalue);
//String Taxammount = Float.toString(Taxammountvalue);
//System.out.println("String is: "+Taxammount);
System.out.println(Taxammountvalue);
//String Totalammount = Float.toString(Totalammountvalue);
//System.out.println("String is: "+Totalammount);
System.out.println(ActualTotalammountvalue);
System.out.println(ExpectedTotalammountvalue);
//String Actualtax = Float.toString(ActualTax);
//System.out.println("String is: "+Actualtax);
//System.out.println(giventaxvalue);
//String userpaneltax = Float.toString(userpaneltaxvalue);
//System.out.println("String is: "+userpaneltax);
//System.out.println(calucaltedvalue);
FileOutputStream fileOut = new FileOutputStream(file);
workbook.write(fileOut);
fileOut.flush();
fileOut.close();
//return writeResultstoXLSx(String OrderId,String subtotla,String shippingammount,String TaxAmmount,String Totalammount,String giventaxvalue1,String userpaneltaxvalue);
} catch (Exception e) {
e.printStackTrace();
}
// return fileOut;
// return writeResultstoXLSx(String OrderId,String subtotla,String shippingammount,String TaxAmmount,String Totalammount,String giventaxvalue1,String userpaneltaxvalue); }
}







public void E2ERegisterUser_ShippingAddress() throws Exception
{
	String expectedResult="Navigate to Shipping Address Page";
	try {
		Thread.sleep(5000);
		Sync.waitElementPresent("xpath", "//span[text()='New Address']");
		Common.clickElement("xpath", "//span[text()='New Address']");
		report.addPassLog(expectedResult, "Should display Shipping Address Page", "Shipping Address Page display successfully", Common.getscreenShotPathforReport("Shipping Address page display  success"));
	}catch(Exception |Error e)
	{
		report.addFailedLog(expectedResult,"Should display Shipping Address Page", "Shipping Address Page not displayed", Common.getscreenShotPathforReport("Shipping Address Failed"));
		Assert.fail();
	}
}





public HashMap<String, String> RegShipingAddress(String dataSet) throws Exception{
HashMap<String, String> Shippingaddress = new HashMap<String, String>();
try{
	
//Sync.waitElementPresent("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']");
//Common.textBoxInput("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']",data.get(dataSet).get("Email"));
Sync.waitElementPresent("xpath", "//input[@name='firstname']");
Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(dataSet).get("FirstName"));
Sync.waitElementPresent("xpath", "//input[@name='lastname']");
Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));
/*Sync.waitElementPresent("xpath", "//input[@name='lastname']");
Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));*/
Common.textBoxInput("xpath", "//input[@name='street[0]']", data.get(dataSet).get("Street"));
Common.clickElement("xpath", "(//a[@class='dropdown-item list-item'])[1]");
Thread.sleep(3000);
/*
Sync.waitElementPresent("xpath", "//select[@name='country_id']");
Common.dropdown("xpath", "//select[@name='country_id']", SelectBy.TEXT, data.get(dataSet).get("Countryname"));*/
Thread.sleep(2000);
Common.dropdown("xpath", "//select[@name='region_id']", Common.SelectBy.TEXT,data.get(dataSet).get("Region"));
Thread.sleep(2000);
String Shippingvalue = Common.findElement("xpath", "//select[@name='region_id']").getAttribute("value");
String Shippingstate = Common.findElement("xpath", "//select[@name='region_id']//option[@value='" + Shippingvalue + "']").getText(); Shippingaddress.put("ShippingState", Shippingstate); Sync.waitElementPresent("xpath", "//input[@name='city']");
Common.textBoxInput("xpath", "//input[@name='city']", data.get(dataSet).get("City"));
Thread.sleep(1000);
Common.textBoxInputClear("name", "postcode");
Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
Thread.sleep(2000);
String ShippingZip = Common.findElement("name", "postcode").getAttribute("value");
System.out.println("*****" + ShippingZip + "*******");
Shippingaddress.put("ShippingZip", ShippingZip);
Thread.sleep(5000);
Sync.waitElementPresent("xpath", "//input[@name='telephone']");
Common.textBoxInput("xpath", "//input[@name='telephone']", data.get(dataSet).get("phone"));
Thread.sleep(5000);
int a=Common.findElements("xpath", "//button[@class='action primary action-save-address']//span[contains(text(),'Ship Here')]").size();

if(a>0)
{
Common.clickElement("xpath", "//button[@class='action primary action-save-address']//span[contains(text(),'Ship Here')]");
}
else{

}
Thread.sleep(1000);
Common.actionsKeyPress(Keys.ENTER);

}
catch(Exception |Error e) {
e.printStackTrace();
ExtenantReportUtils.addFailedLog("verifying shipping addres filling", "user will fill the all the shipping", "faield to add new shipping address",Common.getscreenShotPathforReport("faieldsshippingpagefilling"));
Assert.fail();
}
System.out.println(Shippingaddress);
return Shippingaddress;
}





public void RegwriteOrderNumber(String Website, String OrderIdNumber,String Description, String subtotlaValue, String shippingammountvalue, String Taxammountvalue,String ActualTotalammountvalue,String ExpectedTotalammountvalue,String Discountammountvalue, String ShippingState,String ShippingZip, String Card) throws FileNotFoundException, IOException
{
//String fileOut="";
try{
File file=new File(System.getProperty("user.dir")+"/src/test/resources/RegBraunUS_E2E_orderDetails.xlsx");
XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
XSSFSheet sheet;
Row row;
Cell cell;
int rowcount;
sheet = workbook.getSheet("OrderDetails");
if((workbook.getSheet("OrderDetails"))==null)
{
sheet = workbook.createSheet("OrderDetails");
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
cell.setCellValue("Orders Details");
row = sheet.createRow(1);
cell = row.createCell(0);
cell.setCellStyle(cs);
cell.setCellValue("Web Order Number");
rowcount=2;
}
else
{
sheet=workbook.getSheet("OrderDetails");
rowcount=sheet.getLastRowNum()+1;
}
row = sheet.createRow(rowcount);
cell = row.createCell(0);
cell.setCellType(CellType.NUMERIC);
int SNo=rowcount-1;
cell.setCellValue(SNo);
cell = row.createCell(1);
cell.setCellType(CellType.STRING);
String websitename;
if(Website.contains("braunhealth"))
{
websitename="Braun US";
}
else
{
websitename="";
}
cell.setCellValue(websitename);
cell = row.createCell(2);
cell.setCellType(CellType.STRING);
cell.setCellValue("Lotuswave");
cell = row.createCell(3);
cell.setCellType(CellType.STRING);
cell.setCellValue(Description);
cell = row.createCell(4);
cell.setCellType(CellType.NUMERIC);
cell.setCellValue(OrderIdNumber);
cell = row.createCell(5);
cell.setCellType(CellType.STRING);
cell.setCellValue("Order placed Successfully");
cell = row.createCell(6);
cell.setCellType(CellType.STRING);
cell.setCellValue("Processing"); cell = row.createCell(7);
cell.setCellType(CellType.NUMERIC);
cell.setCellValue(subtotlaValue);
cell = row.createCell(8);
cell.setCellType(CellType.NUMERIC);
cell.setCellValue(shippingammountvalue);
cell = row.createCell(9);
cell.setCellType(CellType.STRING);
cell.setCellValue(ShippingState);
cell = row.createCell(10);
cell.setCellType(CellType.NUMERIC);
cell.setCellValue(ShippingZip);
cell = row.createCell(11);
cell.setCellType(CellType.STRING);
cell.setCellValue(Taxammountvalue);
cell = row.createCell(12);
cell.setCellType(CellType.NUMERIC);
cell.setCellValue(ExpectedTotalammountvalue);
cell = row.createCell(13);
cell.setCellType(CellType.NUMERIC);
cell.setCellValue(Discountammountvalue);
cell = row.createCell(14);
cell.setCellType(CellType.NUMERIC);
cell.setCellValue(ActualTotalammountvalue);
cell = row.createCell(15);
cell.setCellType(CellType.NUMERIC);
cell.setCellValue(Card);
System.out.println(OrderIdNumber);
//String subtotla = Float.toString(subtotlaValue);
//System.out.println("String is: "+subtotla);
System.out.println(subtotlaValue);
//String shippingammount = Float.toString(shippingammountvalue);
//System.out.println("String is: "+shippingammount);
System.out.println(shippingammountvalue);
//String Taxammount = Float.toString(Taxammountvalue);
//System.out.println("String is: "+Taxammount);
System.out.println(Taxammountvalue);
//String Totalammount = Float.toString(Totalammountvalue);
//System.out.println("String is: "+Totalammount);
System.out.println(ActualTotalammountvalue);
System.out.println(ExpectedTotalammountvalue);
//String Actualtax = Float.toString(ActualTax);
//System.out.println("String is: "+Actualtax);
//System.out.println(giventaxvalue);
//String userpaneltax = Float.toString(userpaneltaxvalue);
//System.out.println("String is: "+userpaneltax);
//System.out.println(calucaltedvalue);
FileOutputStream fileOut = new FileOutputStream(file);
workbook.write(fileOut);
fileOut.flush();
fileOut.close();
//return writeResultstoXLSx(String OrderId,String subtotla,String shippingammount,String TaxAmmount,String Totalammount,String giventaxvalue1,String userpaneltaxvalue);
} catch (Exception e) {
e.printStackTrace();
}
// return fileOut;
// return writeResultstoXLSx(String OrderId,String subtotla,String shippingammount,String TaxAmmount,String Totalammount,String giventaxvalue1,String userpaneltaxvalue); }
}


//new//






public void  Relatedproducts(String dataSet) throws Exception
{
	String expectedResult="Search with Product name :"+data.get(dataSet).get("RelatedproductNames");
	try {
		Thread.sleep(1000);
		Common.clickElement("xpath", "//label[@class='label js-search-dropdown']");
		Sync.waitElementPresent("id", "search");
		try {
			Common.textBoxInput("id", "search", data.get(dataSet).get("RelatedproductNames"));
			
		}catch(Exception e)
		{
			Common.clickElement("xpath", "//label[@class='label js-search-dropdown']");
			Thread.sleep(3000);
			Common.textBoxInput("id", "search", data.get(dataSet).get("RelatedproductNames"));
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






public void Relatedproductsvaladation()throws Exception
{
	String expectedResult="Relatedproductsvaladation";
	try {

		Thread.sleep(5000);
		
		

		int title= Common.findElements("xpath", "(//li[@class='search-header-nav__item'])[1]").size();

		 


		System.out.println(title);
		 
		
		Sync.waitElementPresent("xpath", "(//li[@class='search-header-nav__item'])[1]");
		Common.clickElement("xpath", "(//li[@class='search-header-nav__item'])[1]");
		
		Common.actionsKeyPress(Keys.ARROW_DOWN);

		Common.assertionCheckwithReport(title>0, "verifying the rleated prodcuts", " the rleated prodcuts  should dispaly", "sucessfully lands on the rleated prodcuts page ", "faield to lands on the rleated prodcutspage");
		Thread.sleep(3000);
		
		
		int prodcutname= Common.findElements("xpath", "(//li[@class='search-header-nav__item'])[2]").size();

		System.out.println(prodcutname);
		Thread.sleep(3000);
		
		Sync.waitElementPresent("xpath", "(//li[@class='search-header-nav__item'])[2]");
		Common.clickElement("xpath", "(//li[@class='search-header-nav__item'])[2]");
		 

		
		
		Common.getscreenShotPathforReport("prodcutname");
		Common.assertionCheckwithReport(prodcutname>0, "verifying the rleated prodcuts", " the rleated prodcuts  should dispaly", "sucessfully lands on the rleated prodcuts page  ", "faield to lands on the rleated prodcutspage");
		 
		
		int Relatedsupport= Common.findElements("xpath", "//li[@class='search-header-nav__item active']").size();

		System.out.println(Relatedsupport);
		Sync.waitElementPresent("xpath", "//li[@class='search-header-nav__item active']");
		Common.clickElement("xpath", "//li[@class='search-header-nav__item active']");
		
		Common.getscreenShotPathforReport("Relatedsupport");
		Common.assertionCheckwithReport(Relatedsupport>0, "verifying the rleated prodcuts", " the rleated prodcuts  should dispaly", "sucessfully lands on the rleated prodcuts page  ", "faield to lands on the rleated prodcutspage");
		ExtenantReportUtils.addPassLog("verifying  related prodcuts", "User should select  related prodcuts",
				"user faield to Select the related prodcuts",
				Common.getscreenShotPathforReport("related prodcuts block"));
		
	} catch (Exception | Error e) {

		ExtenantReportUtils.addFailedLog("verifying  related prodcuts",
				"User should  related prodcuts",
				"user Successfully Selected the related prodcuts",
				Common.getscreenShotPathforReport("list of related prodcuts"));
		e.printStackTrace();
		Assert.fail();
	}
	try{
		
		Thread.sleep(5000);
			
		Sync.waitElementPresent("xpath", "(//li[@class='search-header-nav__item'])[1]");
		Common.clickElement("xpath", "(//li[@class='search-header-nav__item'])[1]");
		
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		
		Common.mouseOver("xpath" , "(//a[@class='product-item-link'])[1]");
		Sync.waitElementPresent("xpath", "(//span[text()='Add to cart'])[1]");
		Common.clickElement("xpath", "(//span[text()='Add to cart'])[1]");
		
		
		
		Common.mouseOver("xpath" , "(//a[@class='product-item-link'])[2]");
		Sync.waitElementPresent("xpath", "(//span[text()='Add to cart'])[2]");
		Common.clickElement("xpath", "(//span[text()='Add to cart'])[2]");
		
		
		Common.mouseOver("xpath" , "(//a[@class='product-item-link'])[3]");
		Sync.waitElementPresent("xpath", "(//span[text()='Add to cart'])[3]");
		Common.clickElement("xpath", "(//span[text()='Add to cart'])[3]");
		
		
		ExtenantReportUtils.addPassLog("verifying  sort option", "User should select  sort",
					"user faield to Select the Most Viewed sort option",
					Common.getscreenShotPathforReport("list of prodcts name"));
			Thread.sleep(5000);
		} catch (Exception | Error e) {

			ExtenantReportUtils.addFailedLog("verifying  Most Viewed sort option",
					"User should  select Most Viewed sort option",
					"user Successfully Selected the Most Viewed Sort option",
					Common.getscreenShotPathforReport("list of prodcuts displayed"));
			e.printStackTrace();
			Assert.fail();
		
			}
			
		}

}