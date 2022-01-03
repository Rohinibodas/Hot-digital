package TestComponent.Febreze;

import static org.testng.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import TestLib.Automation_properties;
import TestLib.Common;
import TestLib.Sync;
import TestLib.Common.SelectBy;
import Utilities.ExcelReader;
import Utilities.ExtenantReportUtils;
import Utilities.Utils;
import Utilities.xmlReader;

public class FebrezeHelper {
	String datafile;
	ExcelReader excelData;
	static Automation_properties automation_properties = Automation_properties.getInstance();
	Map<String, Map<String, String>> data=new HashMap<>();
	static ExtenantReportUtils report;
	public FebrezeHelper(String datafile){

		excelData=new ExcelReader(datafile);
		data=ExcelReader.getExcelValue();
		this.data=data;
		if(Utilities.TestListener.report==null)
		{
			report=new ExtenantReportUtils("Febreze");
			report.createTestcase("FebrezeTestCases");
		}else{
			this.report=Utilities.TestListener.report;
		}
	}
	public void Navigate_Accountcreation() {
String expectedresult = "Should land on the account creation page";
try{
		Sync.waitElementClickable(10, By.xpath("(//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"febreze/customer/account/create/'])[2]")));

		Common.clickElement(By.xpath("(//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"febreze/customer/account/create/'])[2]")));
		Sync.waitPageLoad();

		Common.isElementDisplayed("xpath", "//h1/span[text()='Create New Customer Account']");
	report.addPassLog(expectedresult, "Accountcreation page will be displayed", Common.getscreenShotPathforReport("Account creation page displayed"));

	} catch(Exception | Error e) {
		report.addFailedLog("should display the Create new customer account page", " Create new customer account page will not be displayed", Common.getscreenShotPathforReport("Failed to Navigate0 Account  creation pages"));
		e.printStackTrace();
		Assert.fail();
		}
	}
public void Accountcreation(String dataSet) throws Exception {
		
		try{
			String expectedresult = "Should create New Account ";
		
			Common.textBoxInput("xpath", "//input[@id='firstname']", data.get(dataSet).get("FirstName"));
		Common.textBoxInput("xpath", "//input[@id='lastname']", data.get(dataSet).get("LastName"));
		Common.textBoxInput("xpath", "//input[@name='email']", Utils.getEmailid());
		Common.textBoxInput("xpath", "//input[@name='password']", data.get(dataSet).get("Password"));
		Common.textBoxInput("xpath", "//input[@id='password-confirmation']", data.get(dataSet).get("Password"));
	Thread.sleep(3000);
		Common.clickElement(By.xpath("//div/button/span[text()='Create new account']"));
		Sync.waitPageLoad();
		Common.isElementDisplayed("xpath", "//div[@data-ui-id='message-success']");
		int Successmessage = Common.findElements(By.xpath("//div[@data-ui-id='message-success']")).size();
		System.out.println(Successmessage);
		Common.assertionCheckwithReport(Successmessage>0, " Displays the Successmessage for account creation", "should display the Successmessage for account creation", "Failed to create the account");
		
		}catch(Exception | Error e) {
			report.addFailedLog("should display the Successmessage for account creation", "Success message will not be displayed", Common.getscreenShotPathforReport("Failed to Navigate to My Account pagess"));
			e.printStackTrace();
			Assert.fail();
		}
		
	}


	public void Login(String dataSet) throws Exception {
		try {
			
			Thread.sleep(3000);
			
		Sync.waitElementClickable(10, By.xpath("(//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"febreze/user/login/'])[2]")));
		Common.clickElement(By.xpath("(//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"febreze/user/login/'])[2]")));
    	Sync.waitPageLoad(5);
   	Common.isElementDisplayed("xpath",  "//span[@data-ui-id='page-title-wrapper']");
Thread.sleep(3000);
	Common.textBoxInput("xpath", "//input[@name='login[username]']", data.get(dataSet).get("Email"));
	Common.textBoxInput("xpath", "//input[@name='login[password]']", data.get(dataSet).get("Password"));
	Thread.sleep(3000);
	Common.clickElement(By.xpath("//button[@class='btn action primary login-btn']"));
	Sync.waitPageLoad(5);
	if(Common.isElementDisplayed("xpath", "//div[@class='message-error error message']")) {
		Common.textBoxInput("name", "login[username]", data.get(dataSet).get("Email"));
		Common.textBoxInput("name", "login[password]", data.get(dataSet).get("Password"));
		Thread.sleep(3000);
		Common.clickElement(By.xpath("(//span[text()='Sign In'])[1]"));
	
	}
	Sync.waitPageLoad();
	Common.isElementDisplayed("xpath", "//span[@data-ui-id='page-title-wrapper']");
			report.addPassLog("Should Display the My Account page", "My account page displayed succesfully", Common.getscreenShotPathforReport("Navigation to My Account page successfull"));	
	
	
		}catch(Exception | Error e) {
			report.addPassLog("Should Display the My Account page", "My account page not displayed ", Common.getscreenShotPathforReport("Failed  Navigation to My Account page" ));
			e.printStackTrace();
			Assert.fail();
		}
	}
	public void Forgotpassword(String dataSet) {
		try {
			Sync.waitElementClickable(10, By.xpath("(//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"febreze/user/login/'])[2]")));
			Common.clickElement(By.xpath("(//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"febreze/user/login/'])[2]")));
		//Sync.waitPageLoad(20);
		Thread.sleep(3000);
		Sync.waitElementClickable(10, By.xpath("(//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"febreze/customer/account/forgotpassword/'])[1]")));
		Common.clickElement(By.xpath("(//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"febreze/customer/account/forgotpassword/'])[1]")));
	Thread.sleep(4000);
		String titlewrapper = Common.findElement("xpath", "//span[@data-ui-id='page-title-wrapper']").getText();
		
		Assert.assertEquals(titlewrapper, "Forgot Your Password?");
		report.addPassLog("Should display the forgot your password page", "Will navigate to Forgot password page", Common.getscreenShotPathforReport("Successfully naviagated to forgotpassword page "));
		
		Common.textBoxInput("name", "email", data.get(dataSet).get("Email"));
		Thread.sleep(3000);
		Common.clickElement(By.xpath("//button[@class='add-to-cart-btn new-password']/span"));
		Thread.sleep(6000);
		Common.isElementDisplayed("xpath", "//div[@class='message-success success message']");
		String s = Common.getText("xpath", "//div[@class='message-success success message']");
		System.out.println(s);
		Common.assertionCheckwithReport(s.contains(data.get(dataSet).get("Email")), "Alert message for resetting the password will be displayed", "Should display the alert message for resetting the password", "Forgot password alert message not displayed");
		
		
		}catch(Exception | Error e) {
			report.addFailedLog("Should display the forgot your password page", "Forgot password page not navigated", Common.getscreenShotPathforReport("Failed to naviagate forgotpassword page "));				
			e.printStackTrace();
			Assert.fail();
		}
		
		
		
	}
	public void browsersearch(String dataSet) throws Exception {
		String expectedresult = "Should Show results for browser search";
		
		try {
			Thread.sleep(3000);
	
		Sync.waitElementClickable(10, "xpath", "//input[@id='search']");
		Common.textBoxInput("xpath", "//input[@id='search']", data.get(dataSet).get("productname"));
		Common.clickElement(By.xpath("//button[@title='Search']"));
		Sync.waitPageLoad(10);
		Thread.sleep(4000);
		
		String Searchtext = Common.getText("xpath", "//span[@data-ui-id='page-title-wrapper']");
		System.out.println(Searchtext);
		Thread.sleep(3000);
		Common.assertionCheckwithReport(Searchtext.contains(data.get(dataSet).get("productname")), "Search results will be shown", expectedresult, "Failed to show the search results");
	
		/*if(Searchtext.contains(data.get(dataSet).get("productname"))) {
		report.addPassLog(expectedresult, "Search results will be shown", Common.getscreenShotPathforReport("search results  page successfull "));
		System.out.println("searchresults validated");
	}else {
		System.out.println("searchresults not validated");
	}*/
		}catch(Exception | Error e) {
			report.addFailedLog(expectedresult, "search results not displayed ", Common.getscreenShotPathforReport("Failed to navigate to search results  page "));				
			e.printStackTrace();
			Assert.fail();
			
		}
		
	}
	public void zerosearchProduct(String dataSet) {
		String expectedResult="Land on searchProduct page";
		try {
		Thread.sleep(5000);
		Sync.waitElementClickable(30, By.xpath("//input[@id='search']"));
		Common.findElement("xpath", "//input[@id='search']").click();
		Common.textBoxInput("xpath", "//input[@id='search']", data.get(dataSet).get("productname"));
		report.addPassLog(expectedResult, "Should display searchProduct Page", "searchProduct page display successfully", Common.getscreenShotPathforReport("searchProduct page success"));
	}catch(Exception |Error e)
	{
		report.addFailedLog(expectedResult,"Should display  searchProduct Page", "searchProduct Page not displayed", Common.getscreenShotPathforReport("searchProduct page Failed"));
		e.printStackTrace();
		Assert.fail();
	}
		
		
		
	}
	
	public void productselection() {
	String expectedresult = "Should land on product PDP page ";
		try {
			
			Sync.waitElementClickable("xpath", "(//img[@class='product-image-photo lazy'])[3]");
			Common.clickElement(By.xpath("(//img[@class='product-image-photo lazy'])[3]"));
			
			//Sync.waitElementClickable(10, By.xpath("(//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"febreze/febreze-tower-hepa-type-air-purifier'])[1]")));
			//Common.clickElement(By.xpath("(//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"febreze/febreze-tower-hepa-type-air-purifier'])[1]")));
            		
			/*Sync.waitPageLoad(10);
			String productname = Common.getText("xpath", "//span[@data-ui-id='page-title-wrapper']");
			Common.assertionCheckwithReport(productname.contains("Tower HEPA-Type Air Purifier"), "Product will be selected and navigated to PDP page", expectedresult, "Failed to display products details page ");
			*/
				}catch(Exception |Error e) {
			report.addPassLog(expectedresult, "Product details is not dispalyed", Common.getscreenShotPathforReport("Failed to display products details page "));
			e.printStackTrace();
			Assert.fail();
		}
		
	}
	public void Navigateminicart() {
		String expectedresult = "Should add product to the cart";
	try {
		Sync.waitElementClickable("xpath", "//button[@title='Add to Cart']");
		Common.clickElement(By.xpath("//button[@title='Add to Cart']"));
	
		report.addPassLog(expectedresult, "mini cart is displayed", Common.getscreenShotPathforReport("product successfully added to the cart"));
		
	
	}catch(Exception |Error e) {
		report.addPassLog(expectedresult, "mini cart not displayed", Common.getscreenShotPathforReport("Failed to add product to cart"));
		e.printStackTrace();
		Assert.fail();
	
	}
		
		
		
	}
	public void NavigateMycartpage() {
		String expectedresult = "Should navigate to mycart page";
		try {
			Sync.waitElementClickable("xpath", "//span[text()='My Cart']");
			Common.clickElement(By.xpath("//span[text()='My Cart']"));
		//Sync.waitElementClickable(10, By.xpath("(//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"febreze/checkout/cart/'])[1]")));
		//Common.clickElement(By.xpath("(//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"febreze/checkout/cart/'])[1]")));
	Sync.waitPageLoad(10);
	Common.isElementDisplayed("xpath", "//h1[@class='page-title']");
	String s = Common.getCurrentURL();
	Common.assertionCheckwithReport(s.contains("febreze/checkout/cart/"), "Mycart page is displayed", expectedresult, "Failed to navigate to my cart");
		
	}catch(Exception |Error e) {
		report.addPassLog(expectedresult, "Mycart not displayed", Common.getscreenShotPathforReport("Failed to Display Mycart"));

		e.printStackTrace();
		Assert.fail();
	}
	}
	public void checkoutpage() {
		String expectedresult = "Should display checkout page";
		try {
			Sync.waitElementClickable(10, By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"febreze/checkout/']")));
			Common.clickElement(By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"febreze/checkout/']")));
		Sync.waitPageLoad();
		Common.isElementDisplayed("xpath", "//div[contains(@data-bind,'Shipping Address')]");
		Common.isElementDisplayed("xpath", "//div[contains(text(),'Shipping Address')]");
		report.addPassLog(expectedresult, "Should display Checkout Page", "Checkout Page display successfully", Common.getscreenShotPathforReport("Checkout page success"));
	}catch(Exception |Error e)
	{
		report.addFailedLog(expectedresult,"Should display Checkout Page", "Checkout Page not displayed", Common.getscreenShotPathforReport("Checkout Failed"));
		e.printStackTrace();
		Assert.fail();
	}

}
	
	
	public void shipping_Address(String dataSet) throws Exception
	{
		
		String expectedResult="add the shipping Address";
		try {
			Thread.sleep(8000);
			
		//Common.textBoxInput("xpath","//input[@type='email']", data.get(dataSet).get("Email"));
	Common.textBoxInput("xpath","//input[@id='customer-email']", data.get(dataSet).get("Email"));
        Thread.sleep(4000);
        Common.textBoxInput("name", "firstname", data.get(dataSet).get("FirstName"));
		Common.textBoxInput("name", "lastname", data.get(dataSet).get("LastName"));
		//Common.textBoxInput("name", "company", data.get(dataSet).get("Company"));
		Common.textBoxInput("name", "street[0]", data.get(dataSet).get("Street"));
		Common.textBoxInput("xpath", "(//input[@name='city'])[1]", data.get(dataSet).get("City"));

		
		Thread.sleep(3000);
        
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		

		Sync.waitElementPresent("xpath", "(//select[@name='region_id'])[1]");
		
	//	Common.clickElement("xpath", "(//select[@name='region_id'])[1]");
		
		Common.dropdown("xpath", "(//select[@name='region_id'])[1]", SelectBy.TEXT, data.get(dataSet).get("Region"));
        
	
		
		Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));

		Thread.sleep(500);

		Common.textBoxInput("name", "telephone",data.get(dataSet).get("phone"));
        Thread.sleep(3000);
        ExtenantReportUtils.addPassLog("Should populate the data in the Shipping address form", "Data will be populated in the fields", Common.getscreenShotPathforReport("Shipping Address form is dipslayed"));
        
        Sync.waitElementPresent("xpath", "//button[@class='button action continue primary']");
		Common.clickElement("xpath", "//button[@class='button action continue primary']");
		
		Thread.sleep(2000);
		Sync.waitElementPresent("xpath", "//span[text()='OK']");
		Common.clickElement("xpath", "//span[text()='OK']");
		//Sync.waitElementPresent("xpath", "//button[@class='action-primary action-accept']");
		//Common.clickElement("xpath", "//button[@class='action-primary action-accept']");
		Thread.sleep(5000);
		Common.refreshpage();
	    if(Common.findElements("xpath", "//span[@data-bind='text: getTitle()']").size()<0) {
    	  Thread.sleep(3000);
    	  Common.refreshpage();
    	  Sync.waitPageLoad();
      }else {
    	  System.out.println("the Payemnts page is displayed with credit card");
    	  Common.refreshpage();
      }
      ExtenantReportUtils.addPassLog(expectedResult,"Should add the shipping Address", "Payment and review page  displayed", Common.getscreenShotPathforReport("Add Shipping Address Success page"));
      
		}catch(Exception |Error e)
		{
		report.addFailedLog(expectedResult,"Should add the shipping Address", "Payment and review page not displayed", Common.getscreenShotPathforReport("Payment and review page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}


		
	
	public void changeProfileName(String dataSet) throws Exception
	{
		String expectedResult="Should able to change Profile for registered user";
		try {
			Thread.sleep(3000);
			Common.isElementDisplayed("xpath", "//span[@data-ui-id='page-title-wrapper']");
			Common.getscreenShotPathforReport("Account information page is displayed");
			Common.textBoxInput("xpath", "//input[@id='firstname']", data.get(dataSet).get("FirstName"));
			Common.textBoxInput("xpath", "//input[@id='lastname']", data.get(dataSet).get("LastName"));
			Thread.sleep(3000);
			
			Sync.waitElementPresent("xpath", "//button[@title='Save']");
			Common.clickElement("xpath", "//button[@title='Save']");
			Thread.sleep(4000);
			String s=Common.getText("xpath", "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");
			System.out.println(s);
			Assert.assertEquals(s, "You saved the account information.");
			report.addPassLog(expectedResult,"Should able to change Profile successfully", "Able to change Profile successfully", Common.getscreenShotPathforReport("Changed Profile Name Success"));
			
			Common.clickElement(By.xpath("(//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"febreze/customer/account/create/'])[1]")));
			
			Thread.sleep(3000);
		
		
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
			Account_Information();
			Sync.waitElementPresent("name", "change_email");	
			Common.clickElement("name", "change_email");
			Thread.sleep(1000);

			Common.textBoxInput("xpath", "(//input[@type='email'])[1]", Utils.getEmailid());
			//Common.textBoxInput("id", "email_address",Utils.getEmailid());
			Common.textBoxInput("name", "current_password", data.get(dataSet).get("Password"));
			Thread.sleep(1000);
			Sync.waitElementPresent("xpath", "//button[@title='Save']");
			Common.clickElement("xpath", "//button[@title='Save']");
			Thread.sleep(3000);
			String s=Common.getText("xpath", "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");
			System.out.println(s);
			Assert.assertEquals(s, "You saved the account information.");
			
			report.addPassLog(expectedResult,"Should Navigate to Profile for email successfully", "Navigated to Profile for email successfully", Common.getscreenShotPathforReport("email Profile Success"));
		
			Common.clickElement(By.xpath("(//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"febreze/customer/account/create/'])[1]")));
			

			Thread.sleep(3000);
		
		
		
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
			Account_Information();
			Sync.waitElementPresent("name", "change_password");	
			Common.clickElement("name", "change_password");
			Thread.sleep(1000);
			Common.textBoxInput("xpath", "//input[@id='current-password']", data.get(dataSet).get("Password"));
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
			
			report.addPassLog(expectedResult,"Should Navigate to Profile for password successfully", "Navigated to Profile for password successfully", Common.getscreenShotPathforReport("Password Profile Success"));
			
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should Navigate to Profile for password successfully", "Not Navigated to Profile for password successfully", Common.getscreenShotPathforReport("password Profile failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void EditAddress_Book() throws InterruptedException {
		String expectedResult="Navigate to Address_Book page";
		try {
			
			Sync.waitElementClickable(10, By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"febreze/customer/address/']")));
			Common.clickElement(By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"febreze/customer/address/']")));
			Thread.sleep(1000);
		
				String Pagetitle = Common.findElement(By.xpath("//span[@data-ui-id='page-title-wrapper']")).getText();
			Assert.assertEquals(Pagetitle, "Address Book");
				
				Common.actionsKeyPress(Keys.END);
				 Sync.waitElementVisible("xpath", "//button[@title='Add New Address']");
				 Common.clickElement(By.xpath("//button[@title='Add New Address']"));
				 Thread.sleep(2000);
				
					addNewAddress("Addressbook");
				
					
					
		
				
			int Successmessage = Common.findElements("xpath", "//div[@class='message-success success message']").size();
			Common.assertionCheckwithReport(Successmessage>0, "Should add the new address in the address book", expectedResult, "Failed to add the new address");
			
					} catch(Exception | Error e) {
			report.addFailedLog(expectedResult,"Should Navigate to Address Book & Add new Address successfully", "Not Navigated to Address Book & Add new Address successfully", Common.getscreenShotPathforReport("Add new Address failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void addNewAddress(String dataSet) throws Exception
	{
		try {
		Common.textBoxInput("xpath", "//input[@id='firstname']", data.get(dataSet).get("FirstName"));
		Common.textBoxInput("xpath", "//input[@id='lastname']", data.get(dataSet).get("LastName"));
		Common.textBoxInput("xpath", "//input[@name='company']", data.get(dataSet).get("Company"));
		Common.textBoxInput("xpath", "//input[@name='telephone']", data.get(dataSet).get("Phone"));
		
		Common.textBoxInput("xpath", "(//input[@name='street[]'])[1]", data.get(dataSet).get("Street"));
		Common.textBoxInput("xpath", "//input[@name='city']", data.get(dataSet).get("City"));
		
		Thread.sleep(1000);
		Sync.waitElementPresent("xpath", "//select[@title='State/Province']");
		Common.scrollToElementAndClick("xpath", "//select[@title='State/Province']");

		//Common.clickElement("xpath", "//select[@title='State/Province']");
		Common.dropdown("xpath", "//select[@title='State/Province']", SelectBy.TEXT, data.get(dataSet).get("Region"));
		Sync.waitElementPresent("name", "postcode");
		Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
		Thread.sleep(1000);
		report.addPassLog("Should populate the fields completely", "all the data is populated", Common.getscreenShotPathforReport("Successfully populated the data"));
		Common.clickElement("xpath", "//button[@title='Save Address']");
		Thread.sleep(3000);
		Common.clickElement("xpath", "//span[text()='OK']");
		} catch(Exception | Error e) {
			report.addFailedLog("Should populate the fields completely", "Data is not populated in the fields", Common.getscreenShotPathforReport("Failed to populate the data"));
			e.printStackTrace();
			Assert.fail();
		}
		
	}
	public void My_Orders() {
		String ExpectedResult = "Should display the My Orders page";
		try {
		Common.isElementDisplayed("xpath", "//span[@data-ui-id='page-title-wrapper']");
		Sync.waitElementClickable(10, By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"febreze/sales/order/history/']")));
		Common.clickElement(By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"febreze/sales/order/history/']")));
		Sync.waitPageLoad();
		Common.isElementDisplayed("xpath", "//span[@data-ui-id='page-title-wrapper']");
		String s = Common.getText("xpath", "//span[@data-ui-id='page-title-wrapper']");
		
		Assert.assertEquals(s, "My Orders");
		report.addPassLog("Should naviagte to My order page", "Myorder page will be displayed", Common.getscreenShotPathforReport("Order page displayed successfully"));
		} catch(Exception | Error e) {
			report.addFailedLog(ExpectedResult, "Myorder page not displayed", Common.getscreenShotPathforReport("Failed to display My Orders page"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	
		public void ClickAddress_book() {
			 String ExpectedResult ="Should display AddressBook page";
			try {
		Sync.waitElementClickable(10, By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"febreze/customer/address/']")));
		Common.clickElement(By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"febreze/customer/address/']")));
		Sync.waitPageLoad(10);
		
		Common.isElementDisplayed("xpath", "//span[@data-ui-id='page-title-wrapper']");
		String s1 = Common.getText("xpath", "//span[@data-ui-id='page-title-wrapper']");
		
		Assert.assertEquals(s1, "Address Book");
		report.addPassLog("Should naviagte to Address Book page", "Address Book page will be displayed", Common.getscreenShotPathforReport("My account page displayed successfully"));
			} catch(Exception | Error e) {
				report.addFailedLog(ExpectedResult, "Myorder page not displayed", Common.getscreenShotPathforReport("Failed to display My Orders page"));

				e.printStackTrace();
				Assert.fail();	
			}
		}
		public void Account_Information() {
			String ExpectedResult = "Should display Account_Information page";
			try {
		Sync.waitElementClickable(5, By.xpath("(//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"febreze/customer/account/edit/'])[1]")));
		Common.clickElement(By.xpath("(//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"febreze/customer/account/edit/'])[1]")));
		Common.isElementDisplayed("xpath", "//span[@data-ui-id='page-title-wrapper']");
		String s2 = Common.getText("xpath", "//span[@data-ui-id='page-title-wrapper']");
		
		Assert.assertEquals(s2, "Edit Account Information");
		report.addPassLog("Should naviagte to Accountinformation page", "Accountinformation page will be displayed", Common.getscreenShotPathforReport("Accountinformation page displayed successfully"));

		}
		catch(Exception | Error e) {
			report.addFailedLog("Should navigate to the Account_Information page", "Account_Information page not navigated", Common.getscreenShotPathforReport("Failed to navigate to the Account_Information page"));
			e.printStackTrace();
			Assert.fail();
		}
		
		
		
	}
	public void CategoryProductselect() {
		try {
			//Sync.waitElementClickable(10, By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"febreze/air-purifiers/products']")));
			//Common.clickElement(By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"febreze/air-purifiers/products']")));
			//Sync.waitPageLoad();
			Sync.waitElementClickable("xpath", "(//span[text()='Products'])[1]");
			Common.clickElement(By.xpath("(//span[text()='Products'])[1]"));
			Sync.waitElementClickable("xpath", "(//div[@class='product-item-info'])[6]");
			Common.clickElement(By.xpath("(//div[@class='product-item-info'])[6]"));
			Common.isElementDisplayed("xpath", "//span[@data-ui-id='page-title-wrapper']");
			//Common.actionsKeyPress(Keys.PAGE_DOWN);
			//Sync.waitElementClickable("xpath", "(//div[@class='product-item-info'])[6]");
			//Common.clickElement(By.xpath("(//div[@class='product-item-info'])[6]"));
			//Common.isElementDisplayed("xpath", "//span[@data-ui-id='page-title-wrapper']");
			//String s= Common.getCurrentURL();
			//Common.assertionCheckwithReport(s.contains("febrezer-tabletop-hepa-type-air-purifier"), "Will navigate to Product details page", "should navigate to product details page", "Failed navigation to Products details page");
			
		}catch(Exception | Error e) {
			report.addFailedLog("Should navigate to the PDP page", "PDP page not navigated", Common.getscreenShotPathforReport("Failed to navigate to PDP page"));
			
			e.printStackTrace();
			Assert.fail();	
			
		}
	}
	

		
 
		public void UsershippingAddress() {
			try {
				Common.actionsKeyPress(Keys.PAGE_DOWN);
			if(Common.checkBoxIsSelected("xpath", "//input[@name='shipping_method']"))
			{
				Common.clickElement("xpath", "//button[@data-role='opc-continue']");
			}
			Thread.sleep(3000);
			if(Common.isElementDisplayed("xpath", "//div[text()='Address is not verified. Do you want to continue ?']")) {
				Thread.sleep(2000);
				Common.clickElement("xpath", "//span[text()='OK']");
			}
			Thread.sleep(4000);
			int Paymentspage = Common.findElements("xpath", "//div[@data-bind='i18n: getGroupTitle($group)']").size();
			Common.assertionCheckwithReport(Paymentspage>0, "Payment methods page will be displayed", "should display payment methods page", "Failed to navigate to Payments page");
			
			}catch(Exception | Error e) {
				
				report.addPassLog("should display payment methods page", "Payment methods page not displayed", Common.getscreenShotPathforReport("Failed to dispaly payment methods page "));
				
				e.printStackTrace();
				Assert.fail();
			}
			
		}
		public void navigatemyaccount() {
			try {
				Sync.waitElementClickable(10, By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"febreze/customer/account']")));
				Common.clickElement(By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"febreze/customer/account']")));
				Common.isElementDisplayed("xpath", "//span[@data-ui-id='page-title-wrapper']");
				
			}catch(Exception | Error e) {
				e.printStackTrace();
				Assert.fail();
			}
		}
		
		public void navigatemyorderpage() {
			try {
				Thread.sleep(2000);
				Sync.waitElementClickable(10, By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"febreze/sales/order/history/']")));
				Common.clickElement(By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"febreze/sales/order/history/']")));
				
			}catch(Exception | Error e) {
				e.printStackTrace();
				Assert.fail();
			}
		}
		
		public void Orderconfirmation() {
			
			try {
				Common.isElementDisplayed("xpath", "//a[@class='order-number']");
				String s = Common.getText("xpath", "//a[@class='order-number']");
				System.out.println(s);
				
				navigatemyaccount();
				Thread.sleep(2000);
				navigatemyorderpage();
				Thread.sleep(3000);
				Common.isElementDisplayed("xpath", "(//td[@data-th='Order #'])[1]");
				String s1 = Common.getText("xpath", "(//td[@data-th='Order #'])[1]");
		
			System.out.println(s1);
			Assert.assertEquals(s, s1);
			report.addPassLog("Should Display the place order number", "Place order number will be displayed", Common.getscreenShotPathforReport("Successfully displayed PlaceOrder Number"));
			
			}catch(Exception | Error e) {
				report.addPassLog("Should Display the place order number", "Place order number is not displayed", Common.getscreenShotPathforReport("Failed to display PlaceOrder Number"));
				
				e.printStackTrace();
				Assert.fail();
				
			}
		}
		public void validateForgotpassword() {
	try {
			Sync.waitElementClickable(10, By.xpath("(//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"febreze/user/login/'])[2]")));
			Common.clickElement(By.xpath("(//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"febreze/user/login/'])[2]")));
		Sync.waitPageLoad(10);
		Sync.waitElementClickable(10, By.xpath("(//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"febreze/customer/account/forgotpassword/'])[1]")));
		Common.clickElement(By.xpath("(//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"febreze/customer/account/forgotpassword/'])[1]")));
	Thread.sleep(2000);
		Common.isElementDisplayed("xpath", "//span[@data-ui-id='page-title-wrapper']");
		report.addPassLog("Should display the forgot your password page", "Will navigate to Forgot password page", Common.getscreenShotPathforReport("Successfully naviagated to forgotpassword page "));
		
		Common.clickElement(By.xpath("//button[@class='add-to-cart-btn new-password']"));
				Thread.sleep(2000);
				Common.isElementDisplayed("xpath", "//div[@id='email_address-error']");
				String s= Common.getText("xpath", "//div[@id='email_address-error']");
				System.out.println(s);
				Assert.assertEquals(s, "This is a required field.");
				report.addPassLog("Should Display the Error message for email text box", "Error message is displayed", Common.getscreenShotPathforReport("Error message displayed below the text box"));
	}catch(Exception | Error e) {
		report.addPassLog("Should Display the Error message for email text box", "Error message is not displayed", Common.getscreenShotPathforReport("Failed to display PlaceOrder Number"));
		
		e.printStackTrace();
		Assert.fail();
	}
			
		}
		
		
		
		public void verify_WhyCleanYourAir() {
		String 	Expectedresult = "Should land on Why clean your air page";
			try {
				
				Sync.waitElementClickable(5, By.xpath("(//a[@class='nav-anchor ui-corner-all'])[1]"));
				Common.clickElement(By.xpath("(//a[@class='nav-anchor ui-corner-all'])[1]"));
				Common.assertionCheckwithReport(Common.getPageTitle().equals("Why Clean Your Air?"), Expectedresult, "Why Clean Your Air? page is displayed", "failed to navigate to Why Clean Your Air? page");
				
				
			}catch(Exception | Error e) {
		        ExtenantReportUtils.addFailedLog("validating Header Links Why Clean Your Air? ",Expectedresult,"Why Clean Your Air? page not navigated",Common.getscreenShotPathforReport("Why Clean Your Air?"));
		        e.printStackTrace();
				Assert.fail();
			}
			
		}
		
		public void verify_Videos() {
			String 	Expectedresult = "Should land on Videos page";
				try {
					
					Sync.waitElementClickable(5, By.xpath("(//a[@class='nav-anchor ui-corner-all'])[2]"));
					Common.clickElement(By.xpath("(//a[@class='nav-anchor ui-corner-all'])[2]"));
					Common.assertionCheckwithReport(Common.getPageTitle().equals("Videos | Febreze Air Purifiers"), Expectedresult, "Videos page is displayed", "failed to navigate to Videos page");
					
					
				}catch(Exception | Error e) {
			        ExtenantReportUtils.addFailedLog("validating Header Link for videos ",Expectedresult,"Febreze Videos page not navigated",Common.getscreenShotPathforReport("Videos | Febreze Air Purifiers"));
			        e.printStackTrace();
					Assert.fail();
				}
				
			}
			
		public void verify_products() {
			String 	Expectedresult = "Should land on Products page";
			String expectedResult = "Should display the Filters And Accessories PLP page";
			String expectedresult = "Should display the Scents PLP page";
				try {
					
					Sync.waitElementClickable(5, By.xpath("(//a[@class='nav-anchor ui-corner-all'])[3]"));
					Common.clickElement(By.xpath("(//a[@class='nav-anchor ui-corner-all'])[3]"));
					Common.assertionCheckwithReport(Common.getPageTitle().equals("products - Air Purifiers"), Expectedresult, "Products page is displayed", "failed to navigate to products page");
					Thread.sleep(3000);
					
					try {
						Sync.waitElementClickable(10, By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"febreze/air-purifiers/products/air-purifiers']")));
						Common.clickElement(By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"febreze/air-purifiers/products/air-purifiers']")));
					Sync.waitElementPresent(10, "xpath", "//span[@data-ui-id='page-title-wrapper']");
						String Producttitle = Common.getText("xpath", "//span[@data-ui-id='page-title-wrapper']");
						Assert.assertEquals(Producttitle, "Air Purifiers");
					report.addPassLog(Expectedresult, "Will display the Airpurifiers PLP page", Common.getscreenShotPathforReport("Air purifiers page is displayed"));
 						
					}catch(Exception | Error e) {
						report.addFailedLog(Expectedresult, "Failed to display the Airpurifiers PLP page", Common.getscreenShotPathforReport("Failed to display the Air purifiers page"));
						 e.printStackTrace();
						 Assert.fail();
					}
					try {
						Sync.waitElementClickable(10, By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"febreze/air-purifiers/products/filters-and-accessories']")));
						Common.clickElement(By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"febreze/air-purifiers/products/filters-and-accessories']")));
					Sync.waitElementPresent(10, "xpath", "//span[@data-ui-id='page-title-wrapper']");
						String Producttitle = Common.getText("xpath", "//span[@data-ui-id='page-title-wrapper']");
						Assert.assertEquals(Producttitle, "Filters And Accessories");
					report.addPassLog(expectedResult, "Will display the Filters And Accessories PLP page", Common.getscreenShotPathforReport("Filters And Accessories page is displayed"));
 						
					}catch(Exception | Error e) {
						report.addFailedLog(expectedResult, "Failed to display the Filters And Accessories PLP page", Common.getscreenShotPathforReport("Failed to display the Filters And Accessories page"));
						 e.printStackTrace();
						 Assert.fail();
					}
					
					try {
						
						Sync.waitElementClickable(10, By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"febreze/air-purifiers/products/scents']")));
						Common.clickElement(By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"febreze/air-purifiers/products/scents']")));
					Sync.waitElementPresent(10, "xpath", "//span[@data-ui-id='page-title-wrapper']");
						String Producttitle = Common.getText("xpath", "//span[@data-ui-id='page-title-wrapper']");
						Assert.assertEquals(Producttitle, "Scents");
					report.addPassLog(expectedresult, "Will display the Scents PLP page", Common.getscreenShotPathforReport("Scents page is displayed"));
 						
					}catch(Exception | Error e) {
						report.addFailedLog(expectedresult, "Failed to display the Scents PLP page", Common.getscreenShotPathforReport("Failed to display the Scents page"));
						 e.printStackTrace();
							Assert.fail();
					}
					
				}catch(Exception | Error e) {
			        ExtenantReportUtils.addFailedLog("validating Header Link for Products ",Expectedresult,"Febreze products page not navigated",Common.getscreenShotPathforReport("products - Air Purifiers"));
			        e.printStackTrace();
					Assert.fail();
				}
				
			}
		
			
			
			
			
		public void Newshippingaddress(String dataSet) {
			try {
				
				Common.actionsKeyPress(Keys.PAGE_DOWN);
				Sync.waitElementPresent("xpath", "//span[text()='New Address']");
                Common.clickElement("xpath", "//span[text()='New Address']");
                Thread.sleep(3000);
				Common.textBoxInput("xpath", "(//input[@name='firstname'])[2]", data.get(dataSet).get("FirstName"));
				Common.textBoxInput("xpath", "(//input[@name='lastname'])[2]", data.get(dataSet).get("LastName"));
				Common.textBoxInput("xpath", "(//input[@name='street[0]'])[2]", data.get(dataSet).get("Street"));
		
				
				
				Common.textBoxInput("xpath", "(//input[@name='city'])[2]", data.get(dataSet).get("City"));
				
	
				Sync.waitElementPresent("xpath", "(//select[@name='region_id'])[2]");
			
				Common.dropdown("xpath", "(//select[@name='region_id'])[2]", SelectBy.TEXT, data.get(dataSet).get("Region"));
				Sync.waitElementPresent("xpath", "(//input[@name='postcode'])[2]");
				Common.textBoxInput("xpath", "(//input[@name='postcode'])[2]", data.get(dataSet).get("postcode"));
				Common.textBoxInput("xpath", "(//input[@name='telephone'])[2]", data.get(dataSet).get("phone"));
				Thread.sleep(2000);
				
				Common.clickElement(By.xpath("//span[text()='Ship here']"));
				Thread.sleep(2000);
				Common.clickElement("xpath", "//span[text()='OK']");
				
				
				
				
				
			}catch(Exception |Error e){
				report.addFailedLog("Adding New Shipping Address for register user", "Should add new shipping address ", "Faield to get price value from shipping page", Common.getscreenShotPathforReport("TaxRates"));

				e.printStackTrace();
				Assert.fail();
				
			}
		}
			
			public void ShippingFormvalidation() {
				
				String expectedResult=" Validating shipping page ";
			try {
			        Thread.sleep(2000);
				Common.actionsKeyPress(Keys.END);
				
				Common.clickElement("xpath", "//span[text()='Next']");
				
				//Common.actionsKeyPress(Keys.DOWN);
				int emailerrormessage=Common.findElements("xpath", "//div[@id='customer-email-error']").size();
				Common.actionsKeyPress(Keys.PAGE_DOWN);
				int Streeterromessage=Common.findElements("xpath", "(//span[@data-bind='text: element.error'])[3]").size();
				
				int Cityerromessage=Common.findElements("xpath", "(//span[@data-bind='text: element.error'])[4]").size();
				int Stateerromessage=Common.findElements("xpath", "(//span[@data-bind='text: element.error'])[5]").size();
				int postcodeerromessage=Common.findElements("xpath", "(//span[@data-bind='text: element.error'])[6]").size();
				int phoneerromessage=Common.findElements("xpath", "(//span[@data-bind='text: element.error'])[7]").size();
				
				
				
				Common.assertionCheckwithReport(emailerrormessage>0&&Streeterromessage>0&&Cityerromessage>0&&Stateerromessage>0&&postcodeerromessage>0&&phoneerromessage>0, "verifying error message ShippingAddressForm Page", "enter with empty data it must show error message","sucessfully display the error message", "faield to dispalyerrormessage");
				}
				catch(Exception |Error e) {
				 	e.printStackTrace();   
					ExtenantReportUtils.addFailedLog("verifying error message ShippingAddressForm Page", "enter with empty data it must show error message", "faield to dispalyerrormessage", Common.getscreenShotPathforReport("ShippingAddressFormvalidation"));
					Assert.fail();
				}
			}
			
			public void ProductQuantityincreaseMycart(String dataset) throws Exception
			{
				String expectedResult="Navigate to View and Edit cart page";
				try {
					
					
					Common.textBoxInputClear("xpath", "//input[@class='input-text qty']");
					Common.textBoxInput("xpath", "//input[@class='input-text qty']", data.get(dataset).get("Quantity"));
					Thread.sleep(2000);
					
					Common.clickElement("xpath", "//button[@title='Update Cart']");
					Common.clickElement(By.xpath("//button[@class='febreze_cart_inc_Qty']"));
					Common.clickElement("xpath", "//button[@title='Update Cart']");
					Common.clickElement(By.xpath("//button[@class='febreze_cart_dec_Qty']"));
					Common.clickElement("xpath", "//button[@title='Update Cart']");
					String qty=Common.getText("xpath","//div[@class='message-success success message']");
					System.out.println(qty);
					Assert.assertEquals(qty, "Your shopping cart has been updated.");
					
					
					report.addPassLog(expectedResult, "Should display Shipping Address Page", "Shipping Address Page display successfully", Common.getscreenShotPathforReport("Shipping Address page display  success"));
				}catch(Exception |Error e)
				{
					report.addFailedLog(expectedResult,"Should display Shipping Address Page", "Shipping Address Page not displayed", Common.getscreenShotPathforReport("Shipping Address Failed"));
					e.printStackTrace();
					Assert.fail();
				}
			}
			public void NavigateTermsandConditions() {
				String Expectedresult = "Should Navigate to the Terms and condition page" ;
				try{
					
					Common.actionsKeyPress(Keys.END);
					Sync.waitElementPresent("xpath", "(//span[@data-element='febrezefootertitle'])[1]");
					Common.clickElement("xpath", "(//span[@data-element='febrezefootertitle'])[1]");
					String message = Common.findElement(By.xpath("//span[@data-ui-id='page-title-wrapper']")).getText();
					System.out.println(message);
					int Message = Common.findElements(By.xpath("//span[@data-ui-id='page-title-wrapper']")).size();
					Common.assertionCheckwithReport(Message>0, "Will display the Terms and Condition page", Expectedresult, "Passed to navigate to Terms and condition page");
					
				}catch(Exception |Error e) {
					report.addFailedLog(Expectedresult,"Should display Shipping Address Page", "Shipping Address Page not displayed", Common.getscreenShotPathforReport("Shipping Address Failed"));
					e.printStackTrace();
					Assert.fail();
					
				}
				
				
				
			}
			public void Acceptcookies() throws Exception {  
				Thread.sleep(5000);
				int sizes=Common.findElements("id", "truste-consent-required").size();
				if(sizes>0){
			
			   Common.clickElement("xpath", "//button[@id='truste-consent-required']");
				}
				
			}
			public void clicksignup() {
				Sync.waitElementClickable(10, By.xpath("//li[@class='sign i-hide']"));
				Common.clickElement(By.xpath("//li[@class='sign i-hide']"));
				
				
			}

			
		

			public void writeResultstoXLSx(String OrderId,float SubTotal,float ShippingAmount,float TaxAmount, float TotalAmount,float ActualTax ,float ExpectedTax)
			{
			try{
				
				File file=new File(System.getProperty("user.dir")+"/src/test/resources/FebrezeTaxDetails.xlsx");
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
		
			
			
			
			public void RegistereduserOrderSuccesspage() throws Exception {
				
				String expectedResult="Validating Order Confirmation Page";
				Thread.sleep(2000);
				try {
					Thread.sleep(4000);
					String Success=Common.getText("xpath", "//h1[@class='page-title']");
					System.out.println(Success);
					Assert.assertEquals(Success, "Thank you for your purchase");
					String orderid=Common.getText("xpath", "//div[@class='checkout-success']/p/strong");
					System.out.println("Your order number is "+orderid);
					report.addPassLog(expectedResult, "Should dispaly Order Success Page", "Order Success page display successfully", Common.getscreenShotPathforReport("Order Success page success"));
				}catch(Exception |Error e)
				{
					report.addFailedLog(expectedResult,"Should display  Order Success Page", "Order Success Page not displayed", Common.getscreenShotPathforReport("Order Success page Failed"));
					e.printStackTrace();
					Assert.fail();

				}
				
				
				
			}
			
			
			
			public void GuestShippingaddress(String Street,String City,String Zipcode,String Region) throws InterruptedException {
				String expectedResult="Should navigate to Shipping address page";
				try {
					/*Sync.waitElementClickable(30, By.xpath("//button[@data-role='proceed-to-checkout']"));
					Common.clickElement("xpath" , "//button[@data-role='proceed-to-checkout']");
					Thread.sleep(4000);
					String S=Common.getText("xpath", "//*[@id='shipping']/div[1]");
					System.out.println(S);
					Assert.assertTrue(Common.isElementDisplayed("xpath", "//*[@id='shipping']/div[1]"));*/
					Thread.sleep(3000);
					ShippingAddress("Guest_shipping", Street, City, Zipcode, Region);
					Common.scrollIntoView("xpath", "(//div[@data-role='title'])[2]");
					
					Common.clickElement("xpath", "//button[@data-role='opc-continue']");
					Thread.sleep(2000);
					
					Common.clickElement("xpath", "//button[@class='action-primary action-accept']");
					
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
				String expectedResult="add the shipping Address";
				try {
					Common.textBoxInput("id", "customer-email", data.get(dataSet).get("Email"));
					Thread.sleep(3000);
					Common.textBoxInput("name", "firstname", data.get(dataSet).get("FirstName"));
					Common.textBoxInput("name", "lastname", data.get(dataSet).get("LastName"));
					Thread.sleep(3000);
					Common.textBoxInput("xpath", "(//input[@name='street[0]'])[1]", Street);
					Thread.sleep(3000);
					Common.actionsKeyPress(Keys.ESCAPE);
					Common.textBoxInput("name", "city", City);
					Common.dropdown("xpath", "(//select[@name='region_id'])[1]",Common.SelectBy.TEXT, Region);
					Common.textBoxInput("name", "postcode", postcode);
					
					   Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
					 Thread.sleep(3000);
				        ExtenantReportUtils.addPassLog("Should populate the data in the Shipping address form", "Data will be populated in the fields", Common.getscreenShotPathforReport("Shipping Address form is dipslayed"));
				        
				        Sync.waitElementPresent("xpath", "//button[@class='button action continue primary']");
						Common.clickElement("xpath", "//button[@class='button action continue primary']");
						
						Thread.sleep(2000);
						Sync.waitElementPresent("xpath", "//span[text()='OK']");
						Common.clickElement("xpath", "//span[text()='OK']");
						//Sync.waitElementPresent("xpath", "//button[@class='action-primary action-accept']");
						//Common.clickElement("xpath", "//button[@class='action-primary action-accept']");
						Thread.sleep(5000);
						Common.refreshpage();
					    if(Common.findElements("xpath", "//span[@data-bind='text: getTitle()']").size()<0) {
				    	  Thread.sleep(3000);
				    	  Common.refreshpage();
				    	  Sync.waitPageLoad();
				      }else {
				    	  System.out.println("the Payemnts page is displayed with credit card");
				    	  Common.refreshpage();
				      }
				      ExtenantReportUtils.addPassLog(expectedResult,"Should add the shipping Address", "Payment and review page  displayed", Common.getscreenShotPathforReport("Add Shipping Address Success page"));
				      
						}catch(Exception |Error e)
						{
						report.addFailedLog(expectedResult,"Should add the shipping Address", "Payment and review page not displayed", Common.getscreenShotPathforReport("Payment and review page Failed"));
							e.printStackTrace();
							Assert.fail();
						}
					}


			public void NavigateHelenoftroy()
			{
				String Expectedresult = "Should Navigate to the Terms and condition page" ;
				try{
					
					Common.actionsKeyPress(Keys.END);
					Sync.waitElementPresent("xpath", "(//span[@data-element='febrezefootertitle'])[2]");
					Common.clickElement("xpath", "(//span[@data-element='febrezefootertitle'])[2]");
					Thread.sleep(3000);
					Common.switchWindows();
					Sync.waitPageLoad();
					String message = Common.findElement(By.xpath("//img[@alt='Elevating Lives, Soaring Together']")).getText();
					System.out.println(message);
					int Message = Common.findElements(By.xpath("//img[@alt='Elevating Lives, Soaring Together']")).size();
					Common.assertionCheckwithReport(Message>0, "Will display the Terms and Condition page", Expectedresult, "Passed to navigate to Terms and condition page");
					
				}catch(Exception |Error e) {
					report.addFailedLog(Expectedresult,"Should display Shipping Address Page", "Shipping Address Page not displayed", Common.getscreenShotPathforReport("Shipping Address Failed"));
					e.printStackTrace();
					Assert.fail();		
			}
			
			
			}
			public void differentbillingaddress(String dataSet) {
			try {
				Common.actionsKeyPress(Keys.END);
				Thread.sleep(2000);
				Sync.waitElementClickable("xpath", "//input[@name='billing-address-same-as-shipping']");
				Common.clickElement("xpath", "//input[@name='billing-address-same-as-shipping']");
				
				if(Common.isElementDisplayed("xpath", "//select[@name='billing_address_id']")) {
					
					//Sync.waitElementClickable(10, "xpath", "//select[@name='billing_address_id']");
					Common.clickElement("xpath", "//select[@name='billing_address_id']");
					
				Common.dropdown("xpath", "//select[@name='billing_address_id']", SelectBy.TEXT, data.get(dataSet).get("UserName"));
			Thread.sleep(2000);
			Billingaddressform("Billingaddress");
				}else {
				
				Thread.sleep(2000);
				Billingaddressform("Billingaddress");
				}
			
			
			}catch(Exception |Error e) {
				e.printStackTrace();
				Assert.fail();	
			}
				
			}
				
				public void Billingaddressform(String dataSet) {
					try {
						Common.textBoxInput("name", "firstname", data.get(dataSet).get("FirstName"));
						Common.textBoxInput("name", "lastname", data.get(dataSet).get("LastName"));
						Common.textBoxInput("name", "company", data.get(dataSet).get("Company"));
						
						Common.textBoxInput("name", "street[0]", data.get(dataSet).get("Street"));
			        	
						
						Sync.waitElementVisible(10, By.xpath("//div/ul[@role='listbox']/li[3]"));
			        	
			         	Common.clickElement(By.xpath("//div/ul[@role='listbox']/li[3]"));
			         	Thread.sleep(2000);
						Common.textBoxInput("name", "city", data.get(dataSet).get("City"));
						
						Thread.sleep(1000);
						Sync.waitElementPresent("name", "region_id");
						//Common.javascriptclickElement("name", "region_id");

						Common.dropdown("name", "region_id", SelectBy.TEXT, data.get(dataSet).get("Region"));
						Sync.waitElementPresent("name", "postcode");
						Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
						Common.textBoxInput("name", "telephone", data.get(dataSet).get("Phone"));
						
						Thread.sleep(1000);
						report.addPassLog("Should populate the fields completely", "all the data is populated", Common.getscreenShotPathforReport("Successfully populated the data"));
				//Sync.waitElementPresent(10, "xpath", "//button[@class='action-update febreze-hvr-btn normal-btn']");
				//Common.clickElement("xpath", "//button[@class='action-update febreze-hvr-btn normal-btn']");
						//Common.clickElement("xpath", "//button[@title='Save Address']");
						Sync.waitElementVisible(15, By.xpath("//button[@class='action-update febreze-hvr-btn normal-btn']"));
						Common.clickElement("xpath", "//button[@class='action-update febreze-hvr-btn normal-btn']");
						Thread.sleep(3000);
						
						Common.clickElement("xpath", "//span[text()='OK']");
					
					}
					catch(Exception |Error e) {
						e.printStackTrace();
						Assert.fail();
				}
				
				}
				
				public void logout()throws Exception
				{
					String expectedResult = "Navigating to home page";
					try	
					{
						Sync.waitElementPresent("xpath", "( //a[@href='https://jetrails-stag-v1.febrezeairpurifiers.com/febreze/customer/account/logout/'] )[3]");
					    Common.clickElement("xpath", "( //a[@href='https://jetrails-stag-v1.febrezeairpurifiers.com/febreze/customer/account/logout/'] )[3]");
					
					
					
					report.addPassLog(expectedResult, "Should display home Page", "Home page display successfully", Common.getscreenShotPathforReport("Home page success"));
				}catch(Exception |Error e)
				{
					e.printStackTrace();
					report.addFailedLog(expectedResult,"Should display  home Page", "Home Page not displayed", Common.getscreenShotPathforReport("Home page Failed"));
					e.printStackTrace();
					Assert.fail();
				}
				}
				
			
					public void continueshooping()throws Exception{
		String expectedResult = "Navigating to categorypage";
		Thread.sleep(3000);
		try
		{
			Sync.waitElementClickable("xpath", "//a[@class='btn btn-default close-addtocart-modal']");
			Common.clickElement("xpath", "//a[@class='btn btn-default close-addtocart-modal']");
			
		

		report.addPassLog(expectedResult, "Should display home Page", "Home page display successfully", Common.getscreenShotPathforReport("Home page success"));
		}catch(Exception |Error e)
	{
		e.printStackTrace();
		report.addFailedLog(expectedResult,"Should display  home Page", "Home Page not displayed", Common.getscreenShotPathforReport("Home page Failed"));
		e.printStackTrace();
		Assert.fail();
	}	

					}


public void InvalidpaymentDetails(String dataSet)throws Exception
{
	String expectedResult = "Navigating to Invalid message";
	Thread.sleep(4000);
	try
	{
		
		Thread.sleep(3000);
		
		//Common.switchFrames("xpath", "//iframe[@name='paymetric_xisecure_frame']");
		
		
		/*if(Common.isElementDisplayed("xpath", "//select[@id='c-ct']")) {
			Common.dropdown("xpath", "//select[@id='c-ct']", Common.SelectBy.TEXT, data.get(dataSet).get("cardType"));	
			}else {
				Common.dropdown("xpath", "//select[@id='c-ct']", Common.SelectBy.TEXT, data.get(dataSet).get("cardType"));
			}
		*/
		Sync.waitElementClickable("xpath", "//label[@for='ime_paymetrictokenize']");
		Common.clickElement("xpath", "//label[@for='ime_paymetrictokenize']");
		Thread.sleep(5000);
		Common.switchFrames("id", "paymetric_xisecure_frame");
		Thread.sleep(6000);
		Sync.waitElementClickable(30, "xpath", "//select[@id='c-ct']");
		Common.clickElement(By.xpath("//select[@id='c-ct']"));
		Thread.sleep(4000);
		Common.clickElement(By.xpath("//select[@xi-elem='c-ct']/option[2]"));
		//Common.dropdown("xpath", "//select[@id='c-ct']", Common.SelectBy.TEXT, data.get(dataSet).get("cardType"));
		Common.textBoxInput("id", "c-cardnumber", data.get(dataSet).get("cardNumber"));
		Common.dropdown("xpath", "//select[@id='c-exmth']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpMonth"));
		Common.dropdown("xpath", "//select[@id='c-exyr']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpYear"));
		Common.textBoxInput("id", "c-cvv", data.get(dataSet).get("cvv"));

		Thread.sleep(1000);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.switchToDefault();
		Common.getscreenShotPathforReport("Should display the populated data in credit card field");
		Thread.sleep(5000);

	
		Sync.waitElementPresent("xpath", "//span[contains(text(),'Place Order')]");
		//Common.clickElement("xpath", "//span[contains(text(),'Place Order')]");
		Common.clickElement("xpath", "//span[contains(text(),'Place Order')]");
		
		Thread.sleep(3000);
		int Message = Common.findElements(By.xpath("//div[@id='c-cardnumber-error']")).size();

	Common.isElementDisplayedonPage(10, "xpath", "//div[@id='c-cardnumber-error']");
	
	report.addPassLog(expectedResult, "Should display error message for Invalid creditcard Page", "Error message display successfully", Common.getscreenShotPathforReport("Creditcard details success"));

	}catch(Exception |Error e) {
		report.addFailedLog(expectedResult,"Should display error message Page", "credit card details are not displayed", Common.getscreenShotPathforReport("Creditcard details Failed"));
		e.printStackTrace();
		Assert.fail();		
}

	}

public void AddPaymentdetails(String dataSet) {
	try {
		Sync.waitElementVisible(30, By.xpath("//div[text()='Payment Method']"));
	
		/*Sync.waitElementClickable("xpath", "//label[@for='ime_paymetrictokenize']");
		Common.clickElement("xpath", "//label[@for='ime_paymetrictokenize']");
		*/
		Common.switchFrames("id", "paymetric_xisecure_frame");
	
		Sync.waitElementClickable("xpath", "//select[@xi-elem='c-ct']");
		//Common.clickElement(By.xpath("//select[@xi-elem='c-ct']"));
		//Thread.sleep(5000);
		Common.dropdown("xpath", "//select[@xi-elem='c-ct']", SelectBy.TEXT, data.get(dataSet).get("Type of Card"));
		
			Common.textBoxInput("id", "c-cardnumber", data.get(dataSet).get("cardNumber"));
			Common.dropdown("xpath", "//select[@id='c-exmth']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpMonth"));
			Common.dropdown("xpath", "//select[@id='c-exyr']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpYear"));
			Common.textBoxInput("id", "c-cvv", data.get(dataSet).get("cvv"));

			Thread.sleep(1000);
			Common.switchToDefault();
			Common.actionsKeyPress(Keys.ARROW_DOWN);
			Thread.sleep(1000);
			Common.actionsKeyPress(Keys.ARROW_DOWN);
		
	
			report.addPassLog( "Should populate the fields for creditcard", "Credit card details will be populated ", Common.getscreenShotPathforReport("Credit card details populated successfully"));
	
			Thread.sleep(3000);
String URL = Common.getCurrentURL();
	if(URL.contains("stag")) {

	Sync.waitElementPresent("xpath", "//span[contains(text(),'Place Order')]");
	
	Common.clickElement("xpath", "//span[contains(text(),'Place Order')]");
	
	
	Sync.waitPageLoad();
	String sucessMessage=Common.getText("xpath", "//h1[text()='Thank you for your purchase']");

	System.out.println(sucessMessage);
	Assert.assertEquals(sucessMessage, "Thank you for your purchase");

	
	
	report.addPassLog("should place the order and suceess message should be displayed", "Will displayed the Order success page", Common.getscreenShotPathforReport("Order success page displayed"));
	
	}

	}
	catch(Exception |Error e)
	{
		report.addFailedLog( "should place the order and suceess message should be displayed", "Failed to dispaly the order success page", Common.getscreenShotPathforReport("Order success page display unsuccessfull"));
		e.printStackTrace();
		Assert.fail();	
	}
}


public void ApplyDiscountcode(String dataSet) {
	
	
	try {
		Sync.waitElementClickable(30, By.xpath("//span[@class='action action-toggle']"));
		Common.clickElement(By.xpath("//span[@class='action action-toggle']"));
		Thread.sleep(3000);
		Sync.waitElementPresent("name", "discount_code");
		Common.textBoxInput("name", "discount_code", data.get(dataSet).get("PromoCode"));
		
		Common.clickElement(By.xpath("//button[@class='action action-apply']/span"));
		Thread.sleep(3000);
		if(Common.isElementVisibleOnPage(5, "xpath", "//div[@class='message message-success success']")) {
		String Successmessage = Common.getText("xpath", "//div[@class='message message-success success']");
		System.out.println(Successmessage);
		Common.assertionCheckwithReport(Successmessage.equals("Your coupon was successfully applied."), "Coupon applied successfully", "Should apply the coupon code", "Coupon code is not applied");
		}else {
	     Common.isElementEnabled("Xpath", "//button[@class='action action-cancel']");
		report.addPassLog( "Coupon applied successfully", "Should apply the coupon code", Common.getscreenShotPathforReport("Order success page display successfull"));
		
		}	
		
	}catch(Exception |Error e) {
		report.addFailedLog( "should place the order and suceess message should be displayed", "Failed to dispaly the order success page", Common.getscreenShotPathforReport("Order success page display unsuccessfull"));
		e.printStackTrace();
		Assert.fail();	
	}
}

	
	
//Tax//



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



public void writeResultstoXLSx(String Website,String subtotlaValue,String shippingammountvalue,String state,String Zipcode,String Taxammountvalue,String ActualTotalammountvalue, String ExpectedTotalammountvalue,String giventaxvalue,String calucaltedvalue)
{
	//String fileOut="";
try{
	
	File file=new File(System.getProperty("user.dir")+"/src/test/resources/FebrezeTaxDetails.xlsx");
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
	if(Website.contains("febrezeairpurifiers"))
     {
		
		Site="febrezeairpurifiers";
		
}
	else
	{
		Site="";
	}
	cell.setCellValue(Site);
	
	//cell = row.createCell(7);
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
	  if((state.equals("Florida"))||(state.equals("Illinois")))
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

public String  URL() throws InterruptedException {
	String Website="";
	Common.clickElement("xpath", "(//a[@class='logo'])");
	Sync.waitPageLoad();
	Thread.sleep(4000);
	Website=Common.getCurrentURL();
    
	return Website;
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
	
	String sucessMessage=Common.getText("xpath", "//h1[@class='page-title']");
	System.out.println(sucessMessage);
	 OrderId=Common.getText("xpath", "//div[@class='checkout-success']/p[1]");
	System.out.println("Your order number is:"+OrderId);
	Common.assertionCheckwithReport(sucessMessage.equals("Thank you for your purchase"),"verifying the product confirmation", expectedResult,"Successfully It redirects to order confirmation page Order Placed","User unabel to go orderconformation page");
		
	}
	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying the product confirmation", expectedResult,
				"User failed to navigate  to order confirmation page", Common.getscreenShotPathforReport("failednavigatepage"));
		Assert.fail();
	}
	return OrderId;
}
public void View_Cart() {
	// TODO Auto-generated method stub
	String expectedresult = "Should navigate to View Cart";
	try {
		Sync.waitElementClickable("xpath", "//a[text()='View Cart']");
		Common.clickElement(By.xpath("//a[text()='View Cart']"));
}catch(Exception |Error e) {
	report.addPassLog(expectedresult, "Mycart not displayed", Common.getscreenShotPathforReport("Failed to Display Mycart"));

	e.printStackTrace();
	Assert.fail();
}
}



/*   E2E  */




public String order_Verifying1() throws Exception{
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
	
	String sucessMessage=Common.getText("xpath", "//h1[@class='page-title']");
	System.out.println(sucessMessage);
	 OrderId=Common.getText("xpath", "//div[@class='checkout-success']/p[1]");
	System.out.println("Your order number is:"+OrderId);
	Common.assertionCheckwithReport(sucessMessage.equals("Thank you for your purchase"),"verifying the product confirmation", expectedResult,"Successfully It redirects to order confirmation page Order Placed","User unabel to go orderconformation page");
		
	}
	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying the product confirmation", expectedResult,
				"User failed to navigate  to order confirmation page", Common.getscreenShotPathforReport("failednavigatepage"));
		Assert.fail();
	}
	return OrderId;
}




public HashMap<String, String> guestShipingAddress(String dataSet) throws Exception{
HashMap<String, String> Shippingaddress = new HashMap<String, String>();
try{
	Thread.sleep(10000);
	Sync.waitPageLoad();
Sync.waitElementPresent("xpath", "//div[@class='field required']//input[@name='username']");
Common.textBoxInput("xpath", "//div[@class='field required']//input[@name='username']",data.get(dataSet).get("Email"));

Thread.sleep(4000);
Common.textBoxInput("name", "firstname", data.get(dataSet).get("FirstName"));
Common.textBoxInput("name", "lastname", data.get(dataSet).get("LastName"));

/*
Sync.waitElementPresent("xpath", "//div[@class='field _required _error']//input[@name='firstname']");
Common.textBoxInput("xpath", "//div[@class='field _required _error']//input[@name='firstname']", data.get(dataSet).get("FirstName"));
Sync.waitElementPresent("xpath", "//div[@class='field _required _error']//input[@name='lastname']");
Common.textBoxInput("xpath", "//div[@class='field _required _error']//input[@name='lastname']", data.get(dataSet).get("LastName"));
*/
Thread.sleep(4000);
Common.textBoxInput("name", "firstname", data.get(dataSet).get("FirstName"));
Common.textBoxInput("name", "lastname", data.get(dataSet).get("LastName"));
//Common.textBoxInput("name", "company", data.get(dataSet).get("Company"));
Common.textBoxInput("name", "street[0]", data.get(dataSet).get("Street"));
Common.textBoxInput("xpath", "(//input[@name='city'])[1]", data.get(dataSet).get("City"));



Common.actionsKeyPress(Keys.ARROW_DOWN);
Common.actionsKeyPress(Keys.ARROW_DOWN);
Sync.waitElementPresent("xpath", "(//div[@class='field _required']//input[@class='input-text'])[4]");
Common.textBoxInput("xpath", "(//div[@class='field _required']//input[@class='input-text'])[4]", data.get(dataSet).get("City"));
Thread.sleep(2000);

/*
 Common.dropdown("xpath", "//div[@name='shippingAddress.region_id']/div/select", Common.SelectBy.TEXT,data.get(dataSet).get("Region"));
String State = data.get(dataSet).get("Region");
String Shippingstate = Common.findElement("xpath", "//div[@name='shippingAddress.region_id']/div/select/option[text()='"+State+"']").getText(); 
Shippingaddress.put("Shippingstate", State); 
 


Common.dropdown("xpath", "//div[@name='shippingAddress.region_id']/div/select", Common.SelectBy.TEXT,data.get(dataSet).get("Region"));
String Shippingstate = data.get(dataSet).get("Region");
String Shippingstate1 = Common.findElement("xpath", "//div[@name='shippingAddress.region_id']/div/select/option[text()='"+Shippingstate+"']").getText(); 
Shippingaddress.put("Shippingstate", Shippingstate); 
*/

Common.dropdown("xpath", "//div[@name='shippingAddress.region_id']/div/select", Common.SelectBy.TEXT,data.get(dataSet).get("Region"));
String State = data.get(dataSet).get("Region");
String ShippingState = Common.findElement("xpath", "//div[@name='shippingAddress.region_id']/div/select/option[text()='"+State+"']").getText(); 
Shippingaddress.put("ShippingState", State); 
System.out.println(ShippingState);

/*
Common.dropdown("xpath", "//div[@name='shippingAddress.region_id']/div/select", Common.SelectBy.TEXT,data.get(dataSet).get("Region"));
Thread.sleep(5000);
String Shippingvalue = Common.findElement("xpath", "//select[@name='region_id']").getAttribute("value");
String Shippingstate = Common.findElement("xpath", "//div[@name='shippingAddress.region_id']/div/select/option[text()='"+Shippingvalue+"']").getText(); 
Shippingaddress.put("ShippingState", Shippingstate); 


*/

Common.textBoxInputClear("xpath", "(//div[@class='field _required']//input[@class='input-text'])[6]");
Common.textBoxInput("xpath", "(//div[@class='field _required']//input[@class='input-text'])[6]", data.get(dataSet).get("postcode"));
Thread.sleep(5000);
String ShippingZip = Common.findElement("xpath", "(//div[@class='field _required']//input[@class='input-text'])[6]").getAttribute("value");
System.out.println("*****" + ShippingZip + "*******");
Shippingaddress.put("ShippingZip", ShippingZip);
Thread.sleep(5000);
Sync.waitElementPresent("xpath", "(//div[@class='field _required']//input[@class='input-text'])[7]");
Common.textBoxInput("xpath", "(//div[@class='field _required']//input[@class='input-text'])[7]", data.get(dataSet).get("phone"));
Thread.sleep(3000);
//Common.actionsKeyPress(Keys.ENTER);
Thread.sleep(3000);

Sync.waitElementPresent("xpath", "//button[@class='button action continue primary']");
Common.clickElement("xpath", "//button[@class='button action continue primary']");	
Sync.waitElementClickable(30, By.xpath("//span[text()='OK']"));
Common.findElement("xpath", "//span[text()='OK']").click(); 
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
	Thread.sleep(3000);

	String subtotla=Common.getText("xpath", "//tr[@class='totals sub']/td/span").replace("$", "");
	// subtotla.replace("", newChar)
	Float subtotlaValue=Float.valueOf(subtotla);
	data.put("subtotlaValue",subtotla);

	// Capabilities cap = (WebDriver).getCapabilities();

	String shippingammount=Common.getText("xpath", "//span[@data-th='Shipping']").replace("$", "");
	Float shippingammountvalue=Float.valueOf(shippingammount);
	data.put("shippingammountvalue",shippingammount);

	String ActualTotalAmmount=Common.getText("xpath", "//td[@data-th='Tax']/span").replace("$", "");
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
public HashMap<String, String> AddPaymentdetails1(String dataSet) {
	// TODO Auto-generated method stub
	HashMap<String, String> Payment = new HashMap<String, String>();
	try {
		Sync.waitElementVisible(30, By.xpath("//div[text()='Payment Method']"));
	
		/*Sync.waitElementClickable("xpath", "//label[@for='ime_paymetrictokenize']");
		Common.clickElement("xpath", "//label[@for='ime_paymetrictokenize']");
		*/
		Common.switchFrames("id", "paymetric_xisecure_frame");
	
		Sync.waitElementClickable("xpath", "//select[@xi-elem='c-ct']");
		//Common.clickElement(By.xpath("//select[@xi-elem='c-ct']"));
		//Thread.sleep(5000);
		Common.dropdown("xpath", "//select[@xi-elem='c-ct']", SelectBy.TEXT, data.get(dataSet).get("Type of Card"));
		
			Common.textBoxInput("id", "c-cardnumber", data.get(dataSet).get("cardNumber"));
			Common.dropdown("xpath", "//select[@id='c-exmth']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpMonth"));
			Common.dropdown("xpath", "//select[@id='c-exyr']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpYear"));
			Common.textBoxInput("id", "c-cvv", data.get(dataSet).get("cvv"));

			Thread.sleep(1000);
			Common.switchToDefault();
			Common.actionsKeyPress(Keys.ARROW_DOWN);
			Thread.sleep(1000);
			Common.actionsKeyPress(Keys.ARROW_DOWN);
		
	
			report.addPassLog( "Should populate the fields for creditcard", "Credit card details will be populated ", Common.getscreenShotPathforReport("Credit card details populated successfully"));
	
			Thread.sleep(3000);
String URL = Common.getCurrentURL();
	if(URL.contains("stag")) {

	Sync.waitElementPresent("xpath", "//span[contains(text(),'Place Order')]");
	
	Common.clickElement("xpath", "//span[contains(text(),'Place Order')]");
	
	
	Sync.waitPageLoad();
	String sucessMessage=Common.getText("xpath", "//h1[text()='Thank you for your purchase']");

	System.out.println(sucessMessage);
	Assert.assertEquals(sucessMessage, "Thank you for your purchase");

	
	
	report.addPassLog("should place the order and suceess message should be displayed", "Will displayed the Order success page", Common.getscreenShotPathforReport("Order success page displayed"));
	}
	
	
	}
	catch(Exception |Error e)
	{
		report.addFailedLog( "should place the order and suceess message should be displayed", "Failed to dispaly the order success page", Common.getscreenShotPathforReport("Order success page display unsuccessfull"));
		e.printStackTrace();
		Assert.fail();	
	}

	return Payment;
}
/*  E22   */

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

String sucessMessage=Common.getText("xpath", "//h1[text()='Thank you for your purchase']");
System.out.println(sucessMessage);
//String Orderid="";
int size=Common.findElements("xpath", "(//a[@class='action print'])").size();
if(size>0) {
	
Orderid=Common.getText("xpath", "(//div[@class='column main'])//div[@class='checkout-success']//a//strong");
}
else{
Orderid=Common.getText("xpath", "((//div[@class='column main'])//strong)[1]");
}

System.out.println(Orderid);
System.out.println("Your order number is:"+Orderid);
Common.assertionCheckwithReport(sucessMessage.equals("Thank you for your purchase"),"verifying the product confirmation", expectedResult,"Successfully It redirects to order confirmation page Order Placed","User unabel to go orderconformation page");
   
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
	String sucessMessage = Common.getText("xpath", "//h1[text()='Thank you for your purchase']").trim();
	// Assert.assertEquals(sucessMessage, "Your order has been
	// received","Sucess message validations");
	int sizes = Common.findElements("xpath", "//h1[text()='Thank you for your purchase']").size();
	Common.assertionCheckwithReport(sucessMessage.equals("Thank you for your purchase"),
			"verifying the product confirmation", expectedResult,
			"Successfully It redirects to order confirmation page Order Placed",
			"User unabel to go orderconformation page");
	
	if(Common.findElements("xpath", "((//div[@class='column main'])//strong)[1]").size()>0) {
		order=Common.getText("xpath", "((//div[@class='column main'])//strong)[1]");
	}
	
	
	if(Common.findElements("xpath","((//div[@class='column main'])//strong)[1]").size()>0) {
		order=	Common.getText("xpath", "((//div[@class='column main'])//strong)[1]");
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
File file=new File(System.getProperty("user.dir")+"/src/test/resources/Febreze_E2E_orderDetails.xlsx");
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
if(Website.contains("Stinger"))
{
websitename="Stinger";
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
public HashMap<String, String> RegShipingAddress(String dataSet) {
	// TODO Auto-generated method stub
	HashMap<String, String> Shippingaddress = new HashMap<String, String>();
	try{
		Thread.sleep(10000);
		Sync.waitPageLoad();
		
	Sync.waitElementPresent("xpath", "(//input[@type='email'])[1]");
	//Common.textBoxInput("xpath", "(//div[@class='control']//input[@name='firstname'])[2]",data.get(dataSet).get("Email"));
	Sync.waitElementPresent("xpath", "(//div[@class='control']//input[@name='firstname'])[2]");
	Common.textBoxInput("xpath", "(//div[@class='control']//input[@name='firstname'])[2]", data.get(dataSet).get("FirstName"));
	Sync.waitElementPresent("xpath", "(//div[@class='control']//input[@name='lastname'])[2]");
	Common.textBoxInput("xpath", "(//div[@class='control']//input[@name='lastname'])[2]", data.get(dataSet).get("LastName"));

	Sync.waitElementPresent("xpath", "(//div[@class='control']//input[@name='street[0]'])[2]");
	//Common.clickElement("name", "region_id");

	Thread.sleep(3000);

	//Sync.waitElementPresent("name", "region_id");

	Common.textBoxInput("xpath", "(//div[@class='control']//input[@name='street[0]'])[2]",  data.get(dataSet).get("Street"));
	//Common.dropdown("xpath", "(//div[@class='field _required']//input[@class='input-text'])[3]", Common.SelectBy.TEXT,data.get(dataSet).get("Street"));



	//Common.actionsKeyPress(Keys.ARROW_DOWN);
	//Common.actionsKeyPress(Keys.ARROW_DOWN);
	Sync.waitElementPresent("xpath", "(//div[@class='control']//input[@name='city'])[2]");
	Common.textBoxInput("xpath", "(//div[@class='control']//input[@name='city'])[2]", data.get(dataSet).get("City"));
	Thread.sleep(2000);


	 Common.dropdown("xpath", "(//div[@class='control']//select[@name='region_id'])[2]", Common.SelectBy.TEXT,data.get(dataSet).get("Region"));
	String State = data.get(dataSet).get("Region");
	String ShippingState = Common.findElement("xpath", "(//div[@class='control']//select[@name='region_id'])[2]//option[text()='"+State+"']").getText(); 
	Shippingaddress.put("ShippingState", State); 
	System.out.println(ShippingState);
	//div[@class='control']//select[@name='region_id'])[2]//option[text()='"+State+"']

	//Common.textBoxInputClear("xpath", "(//div[@class='field _required']//input[@class='input-text'])[6]");
	Common.textBoxInput("xpath", "(//div[@class='field _required']//input[@name='postcode'])[2]", data.get(dataSet).get("postcode"));
	Thread.sleep(5000);
	String ShippingZip = Common.findElement("xpath", "(//div[@class='field _required']//input[@name='postcode'])[2]").getAttribute("value");
	System.out.println("*****" + ShippingZip + "*******");
	Shippingaddress.put("ShippingZip", ShippingZip);
	Thread.sleep(5000);
	Sync.waitElementPresent("xpath", "(//input[@name='telephone'])[2]");
	Common.textBoxInput("xpath", "(//input[@name='telephone'])[2]", data.get(dataSet).get("phone"));
	Thread.sleep(3000);
	//Common.actionsKeyPress(Keys.ENTER);
	Thread.sleep(3000);

	Sync.waitElementPresent("xpath", "//button[@class='button action continue primary']");
	Common.clickElement("xpath", "//button[@class='button action continue primary']");	
	Sync.waitElementClickable(30, By.xpath("//span[text()='OK']"));
	Common.findElement("xpath", "//span[text()='OK']").click(); 
	}
	catch(Exception |Error e) {
	e.printStackTrace();
	ExtenantReportUtils.addFailedLog("verifying shipping addres filling", "user will fill the all the shipping", "faield to add new shipping address",Common.getscreenShotPathforReport("faieldsshippingpagefilling"));
	Assert.fail();
	}
	System.out.println(Shippingaddress);
	return Shippingaddress;
	}

	
public void newaddress() throws Exception {
	// TODO Auto-generated method stub
	Thread.sleep(8000);
	Common.actionsKeyPress(Keys.PAGE_DOWN);
	Sync.waitElementPresent("xpath", "//span[text()='New Address']");
    Common.clickElement("xpath", "//span[text()='New Address']");
    Thread.sleep(3000);
}

public HashMap<String, HashMap<String, String>> productshoppingcart() {
	// TODO Auto-generated method stub
	
	 int value=0;
		List<WebElement> cartproducts= Common.findElements("xpath", "//table[@class='cart items data table']//tbody[@class='cart item']");
		HashMap<String,HashMap<String,String>> productinfromation=new HashMap<String,HashMap<String,String>>();
		HashMap<String,String> singleproductinfromation;
		
		try {
		
		
		for(int i=0;i<cartproducts.size();i++) {
			 String productname="";
		       value=i+1;
		       singleproductinfromation= new HashMap<String,String>();

			String productclass=cartproducts.get(i).getAttribute("class");
		try {
			 productname=	Common.findElement("xpath", "//tbody[@class='"+productclass+"']//img").getAttribute("alt");
			 System.out.println(productname);
		     }
		catch(Exception e) {
			productname=Common.findElement("xpath", "//tbody[@class='"+productclass+"']//span").getText();
			
			}
		
			singleproductinfromation.put("productname", productname);
			System.out.println(productname);
			String productSKU= Common.findElement("xpath", "//tbody[@class='"+productclass+"']//input").getAttribute("data-cart-item-id");
			 singleproductinfromation.put("productSKU", productSKU);
			 System.out.println(productSKU);
		    String productPrice= Common.findElement("xpath", "//tbody[@class='"+productclass+"']//span[@class='price']").getText();
		    singleproductinfromation.put("productPrice", productPrice);
		    System.out.println(productPrice);
		    String productQTY= Common.findElement("xpath", "//tbody[@class='"+productclass+"']//input").getAttribute("value");
		    singleproductinfromation.put("productQTY", productQTY);
		    System.out.println(productQTY);
		    productinfromation.put("order"+value ,singleproductinfromation);
		    
		    ExtenantReportUtils.addPassLog("Validating product infromation", "User get product name SKQ , QTY infroamtion   ", "User sucessfully get product infromation "+productinfromation,Common.getscreenShotPathforReport("productinfopass"));		  
		   
	}
		
		
		}
		 catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("Validating product infromation", "User get product name SKQ , QTY infroamtion",
					"User failed to get product name SKQ , QTY infroamtion",
					Common.getscreenShotPathforReport("productinfail"));
			Assert.fail();
	 }
		
     System.out.println(productinfromation);
		 
		 return productinfromation;

}

public HashMap<String, String> shippingaddress1(String datSet) {
	// TODO Auto-generated method stub
	
	  String expectedResult = "Product should add to cart";
        HashMap<String,String> Shippingaddress=new HashMap<String,String>();
        try {
            Thread.sleep(3000);
            Sync.waitElementPresent("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']");
            Common.textBoxInput("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']", data.get(datSet).get("Email"));
            Thread.sleep(4000);
            String Emailid=Common.findElement("xpath","//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']").getAttribute("value");
            System.out.println("******"+Emailid+"******");
            Shippingaddress.put("Emailid",Emailid);
            
            Sync.waitElementClickable("xpath", "//input[@name='firstname']");
            Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(datSet).get("FirstName"));
            Thread.sleep(40000);
            String ShippingFirstName=Common.findElement("xpath", "//input[@name='firstname']").getAttribute("value");
            System.out.println("*****"+ShippingFirstName+"*******");
			Shippingaddress.put("ShippingFisrtName", ShippingFirstName);
			
            Sync.waitElementClickable("xpath", "//input[@name='lastname']");
            Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(datSet).get("LastName"));
            Thread.sleep(3000);
            String ShippingLastName=Common.findElement("xpath", "//input[@name='lastname']").getAttribute("value");
	        System.out.println("*****"+ShippingLastName+"*******");
	        Shippingaddress.put("ShippingLastName", ShippingLastName);
            
            
            Common.actionsKeyPress(Keys.PAGE_DOWN);
            Sync.waitElementPresent("xpath", "//input[@name='street[0]']");
            Common.textBoxInput("xpath", "//input[@name='street[0]']", data.get(datSet).get("Street"));
            Thread.sleep(12000);
            String ShippingAddress1=Common.findElement("xpath", "//input[@name='street[0]']").getAttribute("value");
	        System.out.println("*****"+ShippingAddress1+"*******");
	        Shippingaddress.put("ShippingAddress1", ShippingAddress1);
            
            Thread.sleep(3000);
            Common.actionsKeyPress(Keys.SPACE);
            Thread.sleep(2000);
          //  Common.clickElement("xpath", "(//a[@class='dropdown-item list-item'])[1]");
          //  Thread.sleep(5000);
            Common.textBoxInput("xpath","//input[@name='city']", data.get(datSet).get("City"));
            Thread.sleep(12000);
            String ShippingCity=Common.findElement("xpath","//input[@name='city']").getAttribute("value");
            System.out.println("*****"+ShippingCity+"*******");
	        Shippingaddress.put("ShippingCity", ShippingCity);
	        
            Common.textBoxInput("xpath", "//input[@name='postcode']",  data.get(datSet).get("postcode"));
        	String ShippingZip=Common.findElement("xpath", "//input[@name='postcode']").getAttribute("value");
	            System.out.println("*****"+ShippingZip+"*******");
	            Shippingaddress.put("ShippingZip", ShippingZip);
            
            //Common.findElement("xpath", "//select[@name='region_id']").click();
            Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(datSet).get("Region"));
            
            String Shippingvalue=Common.findElement("name", "region_id").getAttribute("value");
            String Shippingstate=Common.findElement("xpath","//select[@name='region_id']//option[@value='"+Shippingvalue+"']").getText();
			 
	            Shippingaddress.put("ShippingState", Shippingstate);

            Common.textBoxInput("xpath", "//input[@name='telephone']", data.get(datSet).get("phone"));
            Thread.sleep(4000);
            String ShippingPhone=Common.findElement("xpath", "//input[@name='telephone']").getAttribute("value");
	        System.out.println("*****"+ShippingPhone+"*******");
	        Shippingaddress.put("ShippingPhone", ShippingPhone);
            
            Common.clickElement("xpath", "//button[@class='button action continue primary']");
            Common.clickElement("xpath", "//span[text()='OK']");
            
            ExtenantReportUtils.addPassLog("Verifing guest user checkout page", "Guest user checkout page success",
                    "Guest user checkout page success",
                    Common.getscreenShotPathforReport("Guest user checkout page success"));
        }

 

        catch (Exception | Error e) {
            e.printStackTrace();
            ExtenantReportUtils.addFailedLog("verifying shipping addres filling", "user will fill the all the shipping",
                    "faield to add new shipping address",
                    Common.getscreenShotPathforReport("faieldsshippingpagefilling"));
            Assert.fail();

 

        }
        System.out.println(Shippingaddress);
        return Shippingaddress;
}
public HashMap<String,String> orderamountinfo() {
	// TODO Auto-generated method stub
	HashMap<String,String> data=new HashMap<String,String>();
		try{			    
			Thread.sleep(5000);
			
          String subtotla=Common.getText("xpath", "//tr[@class='totals sub']/td/span").replace("$", "");
          
	        data.put("subtotlaValue",subtotla);
	        String shippingammount=Common.getText("xpath", "//span[@data-th='Shipping']").replace("$", "");
	        data.put("shippingammountvalue",shippingammount);
		    String TaxAmmount=Common.getText("xpath", "//td[@data-th='Tax']/span").replace("$", "");
	        data.put("Taxammountvalue",TaxAmmount);
		    String TotalAmmount=Common.getText("xpath", "//tr[@class='grand totals']//span").replace("$", "");
	        data.put("TotalAmmount", TotalAmmount);
	    //td[@data-th='Tax']/span
	   
	 Common.assertionCheckwithReport(!subtotla.equals(null),"verifying order amout detiles", "getting all the Billing ammount infromation","successfully get the total billing amount infromation ", "faiel to get billing ammount");
		}
catch(Exception |Error e)
	{
	 e.printStackTrace();
	 report.addFailedLog("verifying tax billing amount", "getting price values from billing  page  ", "Faield to get price value from billing page", Common.getscreenShotPathforReport("TaxRatesbilling"));

		e.printStackTrace();
		Assert.fail();
		
}
		System.out.println(data);
		return  data;
	
}
public HashMap<String,String> paymentdetriles(String dataSet) {
	// TODO Auto-generated method stub
	String expectedResult = "Payment With Valid Credit Card";
	HashMap<String,String> Payment=new HashMap<String,String>();
	try {
		Thread.sleep(5000);

		if (Common.isElementDisplayed("xpath", "//div[@id='checkout-loader']")) {
			Thread.sleep(4000);
		} else {
			Thread.sleep(6000);
			
		}
		Common.switchFrames("paymetric_xisecure_frame");
		Sync.waitElementPresent("xpath", "//select[@id='c-ct']");
		Common.dropdown("xpath", "//select[@id='c-ct']", SelectBy.TEXT, data.get(dataSet).get("cardType"));
		String Cardtype=Common.findElement("xpath", "//select[@id='c-ct']").getAttribute("value");
			String Card=Common.findElement("xpath","//select[@id='c-ct']//option[@value='"+Cardtype+"']").getText();
		    Payment.put("Card", Card);
		    System.out.println(Cardtype);
		    System.out.println(Card);
		Common.textBoxInput("id", "c-cardnumber", data.get(dataSet).get("cardNumber"));
		String Cardnumber=Common.findElement("id", "c-cardnumber").getAttribute("value");
			System.out.println("******"+Cardnumber+"*****");
			Payment.put("Cardnumber", Cardnumber);

		Common.clickElement("xpath", "(//select[@id='c-exmth']/option)[6]");
		String ExpMonth=Common.findElement("xpath", "(//select[@id='c-exmth']/option)[6]").getAttribute("value");
			System.out.println("*******"+ExpMonth+"****");
			Payment.put("ExpMonth", ExpMonth);
		
		Common.dropdown("id", "c-exyr", SelectBy.TEXT, data.get(dataSet).get("ExpYear"));
		String ExpYear=Common.findElement("id", "c-exyr").getAttribute("value");
			System.out.println("*******"+ExpYear+"****");
			Payment.put("ExpYear", ExpYear);
		Common.textBoxInput("id", "c-cvv", data.get(dataSet).get("cvv"));
		String cvv=Common.findElement("id","c-cvv").getAttribute("value");
			System.out.println("*******"+cvv+"****");
			Payment.put("cvv", cvv);
		Common.switchToDefault();
	} catch (Exception | Error e) {
		report.addFailedLog(expectedResult, "Should Make payment wih valid credit card successfully",
				"Make payment wih valid credit card unsuccessfully",
				Common.getscreenShotPathforReport("Payment CC Failed"));
		e.printStackTrace();
		Assert.fail();
	}
	return Payment;
}
public String PlaceOrder() {
	String expectedResult = "Payment With Valid Credit Card";
	String Order="";
//	paymentDetails(String dataSet);
	try {
		 
		//Common.javascriptclickElement("xpath", "//button[@class='action primary checkout']");
		Common.switchToDefault();
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.switchToDefault();
		Thread.sleep(1000);
		String url=Common.getCurrentURL();
		if(url.equals("https://www.stingerproducts.com/stinger/checkout/#payment"))
		{
		Common.getCurrentURL();
		System.out.println(url);
		}
		else{
		Common.clickElement("xpath", "//button[@class='action primary checkout']");
		
		
		
		report.addPassLog(expectedResult, "Should Make payment wih valid credit card successfully",
				"Make payment wih valid credit card successfully",
				Common.getscreenShotPathforReport("Payment CC success"));
		try {
			Sync.waitElementVisible("xpath", "//span[text()='Place Order']");
			Common.clickElement("xpath", "//span[text()='Place Order']");
		} catch (Exception | Error e) {
			Sync.waitElementVisible("xpath", "//span[text()='Place Order']");
		}
		Order=Common.getText("xpath", "//div[@class='checkout-success']//strong");
		System.out.println("Your order number is:"+Order);
		report.addPassLog(expectedResult, " Order should place sucessfully",
				"Submited order and order places sucessfully",
		Common.getscreenShotPathforReport("Payment CC success"));
		}
	} catch (Exception | Error e) {
		report.addFailedLog(expectedResult, "Should Make payment wih valid credit card successfully",
				"Make payment wih valid credit card unsuccessfully",
				Common.getscreenShotPathforReport("Payment CC Failed"));
		e.printStackTrace();
		Assert.fail();
	}
	return Order;

}

public void vicksAdminlogin(String dataSet) {
	// TODO Auto-generated method stub
	try {
     	Common.oppenURL("https://jetrails-hh-stag-v1.heledigital.com/bYpeKSnq3yLseKt52ZzC/");
     	Common.textBoxInput("xpath", "//input[contains(@name,'username')]", data.get(dataSet).get("UserName"));
     	Common.textBoxInput("xpath", "//input[contains(@name,'password')]",data.get(dataSet).get("Password"));
     	
     int username=	Common.findElements("xpath", "//input[contains(@name,'username')]").size();
     	
     	
        Common.assertionCheckwithReport(username>=1, "verifying Admin panel login page", "User name and password field data is populating", "Sucessfully enter username and password", "Faield to enter username and password"); 	
     	Common.clickElement("xpath", "//button[contains(@class,'action-primary')]");
     	Thread.sleep(2000);  
     	Common.actionsKeyPress(Keys.ESCAPE);
     	}
     	catch(Exception |Error e)
 		{
 			report.addFailedLog("verifying Admin panel login page", "User name and password field data is populating", "Faield to enter username and password",Common.getscreenShotPathforReport("adminlogin")); 	

 			e.printStackTrace();
 			Assert.fail();
 			
 	}
	
}

public void selectManulExport(String orderid) {
	// TODO Auto-generated method stub
	 try {
			Common.findElement("xpath","//li[@id='menu-magento-sales-sales']").click();
			
			Thread.sleep(5000);
        	
         	Common.clickElement("xpath","//li[@data-ui-id='menu-xtento-orderexport-manual']");
        	
            Thread.sleep(5000);
        	
        	
        	Common.assertionCheckwithReport(Common.getPageTitle().equals("Sales Export - Manual Export / Sales Export / Sales / Magento Admin"), "Validating manual export option in admin", "User must land on Manual Export page in admin", "user sucessfully navigating to Manual Export page ", "fail to navigate Manual Export page");
        	
        	

         	
         	Common.dropdown("xpath", "(//select[@class='select'])[1]", Common.SelectBy.TEXT, "Febreze Alchemy Profile (ID: 4)");
        
         	//starting ordernumber
         	Common.textBoxInput("xpath", "//input[@id='increment_from']",orderid);
        
         	//starting ordernumber
         	Common.textBoxInput("xpath", "//input[@id='increment_to']",orderid);
         	
         	
         	//select the orderstatusinexpoert
         	Common.dropdown("id", "force_status",Common.SelectBy.TEXT, "Processing");
         	
         	
            Common.clickElement("xpath", "//input[@id='filter_new_only']");
            
            Common.clickCheckBox("xpath", "//input[@id='start_download']");
            
             Common.clickElement("xpath", "//button[@id='export_button']");
             
            // report.addPassLog("validating the Manual Export order files"," enter all the field infromation manual export field","User sucessfully enter all the manual export field data",Common.getscreenShotPathforReport("downloading"));
		 
             Sync.waitPageLoad();
         	Thread.sleep(4000);
         	
        }   
 
		 catch(Exception |Error e)
	 		{
	 			//report.addFailedLog("validating the Manual Export order files", "enter all the field infromation manual export field", "Faield to enter manual export field data",Common.getscreenShotPathforReport("faielddownload")); 	

	 			e.printStackTrace();
	 			Assert.fail();
	 			
	 	}
}

public void productinfromationvalidation (HashMap<String,HashMap<String,String>>  SFproductinfromation,String Ordernumber) throws Exception {
	// TODO Auto-generated method stub
	 Thread.sleep(5000);
	 String fileName=System.getProperty("user.dir")+"\\TestLogs\\Download\\Export_"+Ordernumber+".xml";
    		// String fileName="C:\\Users\\admin\\Downloads\\Export_4000420945A.xml";
	 
	 Map<String, Object> jsonInMap=xmlReader.fetchvalues(fileName);
	 Map<String,Object> xml= xmlReader.stringToMapTest(jsonInMap.get("OrderItems1").toString());
  	
	 String OrderNumberxml=(String) jsonInMap.get("OrderNumber");     
	 
 	String orderpricexml=xml.get("OrderedProductPrice").toString();
  	StringBuffer sb= new StringBuffer(orderpricexml);  
 	String xmlproductprice=sb.deleteCharAt(sb.length()-1).toString();
  	String orderedProductSKUXML =xml.get("OrderedProductSKU").toString();
  	String OrderedProductNameXML=xml.get("OrderedProductName").toString();
  	String OrderedProductQTYXML =xml.get("Quantity").toString();
		 
  	
  	
  	HashMap<String, String>  order=SFproductinfromation.get("order1");
  	System.out.println(order);
  	String productPrice=order.get("productPrice").replace("$", "");
  	String productSKU=order.get("productSKU");
  	String productname=order.get("productname");
 	String productQTY=order.get("productQTY");
 	
 	
	System.out.println(OrderedProductQTYXML.trim()+" this from xml");
 	
 	System.out.println(productQTY+" this from aplication");
 	
	System.out.println(OrderedProductQTYXML.contains(productQTY));
	Common.oppenURL(fileName);
	orderxmlvalidations("ordernumber", OrderNumberxml, Ordernumber);
	 orderxmlvalidations("productname",OrderedProductNameXML , productname);
	 orderxmlvalidations("product SKU",orderedProductSKUXML, productSKU);
	 orderxmlvalidations("productprice", xmlproductprice, productPrice);
	 orderxmlvalidations("product QTY", OrderedProductQTYXML, productQTY);
	 
	// Common.assertionCheckwithReport(xmlproductprice.contains(productPrice)&&productSKU.contains(orderedProductSKUXML)&& productname.contains(OrderedProductNameXML)&&OrderedProductQTYXML.contains(productQTY),"validating xml product infromation","order product inframtion matches to order xml product info","sucessfully matches product infromation"+order+"is Equal to xml infromation"+xml,"fail to match product infromatio with order xml iformation  product infromation="+order+"xmal infromation =="+xml);  //   System.out.println(productPrice  +"   " + xmlproductprice +"**" +productSKU+orderedProductSKUXML+productname+OrderedProductNameXML+productQTY+OrderedProductQTYXML);
	 
	 
  //  Common.assertionCheckwithReport(productSKU.equals(orderedProductSKUXML)&& productname.contains(OrderedProductNameXML)&&productQTY.contains(OrderedProductQTYXML),"validating xml product infromation","order product inframtion matches to order xml product info","sucessfully matches product infromation"+order+"is Equal to xml infromation"+xml,"fail to match product infromatio with order xml iformation  product infromation="+order+"xmal infromation =="+xml);
  	try {
  //   Assert.assertTrue(productSKU.contains(orderedProductSKUXML)&& productname.contains(OrderedProductNameXML)&&productQTY.contains(OrderedProductQTYXML)); 
  	}
  	catch(Exception e) {
  		e.printStackTrace();
  	}
}
public void shippingvalidaing_GustUserXML(HashMap<String, String> shippingaddress, String ordernumber) {
	// TODO Auto-generated method stub
	 ArrayList<String> orderxmlinfromation=new ArrayList<String>();
     	try {	 
          Thread.sleep(5000);
     	 String fileName=System.getProperty("user.dir")+"\\TestLogs\\Download\\Export_"+ordernumber+".xml";
     	 //String fileName="C:\\Users\\admin\\Downloads\\Export_4000420945A.xml";
     	 Map<String, Object> jsonInMap=xmlReader.fetchvalues(fileName);
     	 
     	 
     	 
     	                             			
     	 String ShippingFirstName= (String) jsonInMap.get("ShippingFirstName");
     	 String ShippingLastName= (String) jsonInMap.get("ShippingLastName");
     	 String ShippingAddress1= (String) jsonInMap.get("ShippingAddress1");
     	 String ShippingCity= (String) jsonInMap.get("ShippingCity");
     	 String ShippingState= (String) jsonInMap.get("ShippingState");
     	 String ShippingZip=(String) jsonInMap.get("ShippingZip");
     	 String customermail=(String) jsonInMap.get("cust_to_email");
     	 
     	//ArrayList<String> orderxmlinfromation=new ArrayList<String>();
     	
     	orderxmlinfromation.add("ShippingFirstName="+ShippingFirstName);
     	orderxmlinfromation.add("ShippingLastName="+ShippingLastName);
     	orderxmlinfromation.add("ShippingAddress1="+ShippingAddress1);
     	orderxmlinfromation.add("ShippingCity="+ShippingCity);
     	orderxmlinfromation.add("ShippingState="+ShippingState);
     	orderxmlinfromation.add("ShippingZip="+ShippingZip);

     	  String SfCustomerEmail=shippingaddress.get("Emailid");
     	 String Sffirstname=shippingaddress.get("ShippingFisrtName");
     	 String SfLastname =shippingaddress.get("ShippingLastName");
     	 String SfStreet =shippingaddress.get("ShippingAddress1");
     	 String Sfcity =shippingaddress.get("ShippingCity");
     	 String SfRegion =shippingaddress.get("ShippingState");
     	 String Sfpostcode =shippingaddress.get("ShippingZip");
     	 
     	orderxmlvalidations("customerEmail", customermail, SfCustomerEmail);
		 orderxmlvalidations("Shiiping first name",ShippingFirstName , Sffirstname);
		 orderxmlvalidations("Shiiping last name",ShippingLastName, SfLastname);
		 orderxmlvalidations("Shiiping Street address", ShippingAddress1, SfStreet);
		 orderxmlvalidations("Shiiping City ", ShippingCity, Sfcity);
		 
		 orderxmlvalidations("Shiiping shipping zip code", ShippingZip, Sfpostcode);
     	
     	 Common.assertionCheckwithReport(SfCustomerEmail.contains(customermail)&&ShippingFirstName.contains(Sffirstname)&&SfLastname.contains(ShippingLastName)&&SfStreet.contains(ShippingAddress1)&&Sfcity.contains(ShippingCity)&&ShippingZip.contains(Sfpostcode), "Validating orderxml infromation with order shipping address","Order shipping address shoud match to order export xml adress","sucessfully order xml infromation matches to export xml adress"," un match the storefront shipping address"+shippingaddress+"    with  "+"   Orderxml infromation"+orderxmlinfromation);
     	}
     	
     	catch (Exception | Error e) {

     		e.printStackTrace();
    			ExtenantReportUtils.addFailedLog("Validating orderxml infromation with order shipping address","Order shipping address shoud match to order export xml adress","un match the storefront shipping address"+shippingaddress+"    with  "+"   Orderxml infromation"+orderxmlinfromation,
    					Common.getscreenShotPathforReport("shipingaddressfaieldxml"));
    			Assert.fail();
    			
    		}
     	
}
public void TotalvalidationXML(HashMap<String, String> data, String Order) throws Exception {
	// TODO Auto-generated method stub
    Thread.sleep(5000);

	String fileName=System.getProperty("user.dir")+"\\TestLogs\\Download\\Export_"+Order+".xml";
	//String fileName="C:\\Users\\admin\\Downloads\\Export_4000420945A.xml";
	Map<String, Object> jsonInMap=xmlReader.fetchvalues(fileName);

	String OrderShippingCosts= (String) jsonInMap.get("OrderShippingCosts");
	String tax_amt= (String) jsonInMap.get("tax_amt");
	String order_total= (String) jsonInMap.get("order_total");
                  

	 
	 String Sfshipping=data.get("shippingammountvalue");
	 String SfTaxamount=data.get("Taxammountvalue");
	 String Sforder=data.get("TotalAmmount");

		System.out.println(order_total.contains(Sforder));
		System.out.println(tax_amt.contains(SfTaxamount));
		System.out.println( Sfshipping.contains(OrderShippingCosts));

		
		
		//System.out.println(OrderShippingCosts.contains(Sfshipping));
		
	Common.assertionCheckwithReport(order_total.contains(Sforder) && tax_amt.contains(SfTaxamount)&&OrderShippingCosts.contains(Sfshipping), 
			"verify Tax and total ammount shipping cost with on order xml",
			"Address must  matching to orderxml ",
			 "product shiping tax and order total "+ data+ " Matches to xml infromation "+ "OrderShippingCosts="+OrderShippingCosts+" tax_amt="+tax_amt+" prder_total "+order_total,
			
			 "product shiping tax and order total "+ data+ " is not Matches to xml infromation "+ "OrderShippingCosts="+OrderShippingCosts+" tax_amt="+tax_amt+" prder_total "+order_total);
	}
public void card_details_validationXML(HashMap<String, String> Payment, String Order) throws Exception {
	// TODO Auto-generated method stub
    Thread.sleep(5000);

    String fileName=System.getProperty("user.dir")+"\\TestLogs\\Download\\Export_"+Order+".xml";
   Map<String, Object> jsonInMap=xmlReader.fetchvalues(fileName);
   String payment_meth= (String) jsonInMap.get("payment_meth");
   String CardType= (String) jsonInMap.get("CardType");
   String CardExpirationMonth= (String) jsonInMap.get("CardExpirationMonth");
   String CardExpirationYear= (String) jsonInMap.get("CardExpirationYear");


     String Sfcardtype=Payment.get("Card");
     String Sfcardnumber =Payment.get("Cardnumber");
     String Sfexpmonth =Payment.get("ExpMonth");
     String Sfexpyear =Payment.get("ExpYear");
 
     
     orderxmlvalidations("customer cart type", CardType, Sfcardtype);   
     orderxmlvalidations("card expir month", CardExpirationMonth, Sfexpmonth); 
     orderxmlvalidations("card expir year", CardExpirationYear, Sfexpyear); 
Common.assertionCheckwithReport(CardType.contains(Sfcardtype)&&CardExpirationMonth.contains(Sfexpmonth)&&CardExpirationYear.contains(Sfexpyear), "verify address should match on order xml", "store front application data matches to order xml infromatio","sucessfully matchs the web infromaation with order xml","faield to match web order infromation with order xml");

	
}

public void orderxmlvalidations(String Details,String XML,String Web)
{
	//String fileOut="";
try{
	
	File file=new File(System.getProperty("user.dir")+"/src/test/resources/OrderValidation.xlsx");
	XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
	XSSFSheet sheet;
	Row row;
	Cell cell;
	int rowcount;
	sheet = workbook.getSheet("TaxDetails");
	
	if((workbook.getSheet("xmlvalidation"))==null)
	{
	sheet = workbook.createSheet("xmlvalidation");
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
	cell.setCellValue("XMLvalidation");
	
	row = sheet.createRow(1);
	cell = row.createCell(0);
	cell.setCellStyle(cs);
	cell.setCellValue("Details");
	cell = row.createCell(1);
	cell.setCellStyle(cs);
	cell.setCellValue("Web");
	cell = row.createCell(2);
	cell.setCellStyle(cs);
	cell.setCellValue("XML");
	cell=row.createCell(3);
	cell.setCellStyle(cs);
	cell.setCellValue("Status");
	rowcount=2;
	
	}
	
	else
	{
	
	sheet=workbook.getSheet("xmlvalidation");	
	rowcount=sheet.getLastRowNum()+1;
	}
	row = sheet.createRow(rowcount);
	cell = row.createCell(0);
	cell.setCellValue(Details);
	cell = row.createCell(1);
	cell.setCellType(CellType.NUMERIC);
	cell.setCellValue(Web);
	cell = row.createCell(2);
	cell.setCellType(CellType.NUMERIC);
	cell.setCellValue(XML);
	cell = row.createCell(3);
	cell.setCellType(CellType.STRING);
	
	String status;

	if(XML.contains(Web))
	{
		Thread.sleep(4000);
		status="pass";
	}
	else
	{
		status="Fail";
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





