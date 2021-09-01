package TestComponent.Febreze;

import static org.testng.Assert.fail;

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
			
		//	Sync.waitElementClickable("xpath", "(//img[@class='product-image-photo lazy'])[2]");
			//Common.clickElement(By.xpath("(//img[@class='product-image-photo lazy'])[2]"));
			
			Sync.waitElementClickable(10, By.xpath("(//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"febreze/febreze-tower-hepa-type-air-purifier'])[1]")));
			Common.clickElement(By.xpath("(//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"febreze/febreze-tower-hepa-type-air-purifier'])[1]")));
            		
			Sync.waitPageLoad(10);
			String productname = Common.getText("xpath", "//span[@data-ui-id='page-title-wrapper']");
			Common.assertionCheckwithReport(productname.contains("Tower HEPA-Type Air Purifier"), "Product will be selected and navigated to PDP page", expectedresult, "Failed to display products details page ");
			
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
		Thread.sleep(3000);
		int Successmessage = Common.findElements("xpath", "//p[@class='alert alert-success']").size();
		Common.assertionCheckwithReport(Successmessage>0, "product added to cart and viewcart will be displayed", expectedresult, "Failed to Add product to Cart");
		Common.isElementDisplayed("xpath", "//div[@class='col-xs-10']/p");
		
		String productname = Common.getText("xpath", "(//div[@class='col-xs-10']/p)[1]");
		
		if(productname.contains("Tower HEPA-Type Air Purifier")) {
			System.out.println(productname+"Is displayed in minicart");
		}else {
			System.out.println(productname+"Is not added to minicart");
			
		}
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
		Sync.waitElementClickable(10, By.xpath("(//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"febreze/checkout/cart/'])[2]")));
		Common.clickElement(By.xpath("(//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"febreze/checkout/cart/'])[2]")));
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
		
		Assert.assertEquals(s1, "Add New Address");
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
			Sync.waitElementClickable(10, By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"febreze/air-purifiers/products']")));
			Common.clickElement(By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"febreze/air-purifiers/products']")));
			Sync.waitPageLoad();
			Common.isElementDisplayed("xpath", "//span[@data-ui-id='page-title-wrapper']");
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Sync.waitElementClickable("xpath", "(//div[@class='product-item-info'])[7]");
			Common.clickElement(By.xpath("(//div[@class='product-item-info'])[7]"));
			Common.isElementDisplayed("xpath", "//span[@data-ui-id='page-title-wrapper']");
			String s= Common.getCurrentURL();
			Common.assertionCheckwithReport(s.contains("febrezer-tabletop-hepa-type-air-purifier"), "Will navigate to Product details page", "should navigate to product details page", "Failed navigation to Products details page");
			
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
		public void Taxcalucaltion(String taxpercent) throws Exception{
			
			
			try{
				Thread.sleep(3000);
			
			/*String taxpercent=data.get(dataset).get("tax");
			 System.out.println(taxpercent);
*/
			 Float giventaxvalue=Float.valueOf(taxpercent);
			 System.out.println(giventaxvalue);
			
			String subtotla=Common.getText("xpath", "//tr[@class='totals sub']/td/span").replace("$", "");
			 // subtotla.replace("", newChar)
			Float subtotlaValue=Float.valueOf(subtotla);
			System.out.println(subtotlaValue);
			
			String shippingammount=Common.getText("xpath", "//span[@data-th='Shipping']").replace("$", "");
			Float shippingammountvalue=Float.valueOf(shippingammount);
			System.out.println(shippingammountvalue);
			
			String TaxAmmount=Common.getText("xpath", "//td[@data-th='Tax']/span").replace("$", "").replace("0", "");
			Float Taxammountvalue=Float.valueOf(TaxAmmount);
			System.out.println(Taxammountvalue);
			
			String TotalAmmount=Common.getText("xpath", "//tr[@class='grand totals']//span").replace("$", "");
			Float Totalammountvalue=Float.valueOf(Taxammountvalue);
			
		    Float calucaltedvalue= ((subtotlaValue+shippingammountvalue)*giventaxvalue)/100;
			System.out.println(calucaltedvalue);
		    
		    NumberFormat nf= NumberFormat.getInstance();
	        nf.setMaximumFractionDigits(2);
	        String  userpaneltaxvalue=nf.format(calucaltedvalue);
	      
	        System.out.println(TaxAmmount);
	        System.out.println(userpaneltaxvalue);      
		   // Common.assertionCheckwithReport(userpaneltaxvalue.equals(TaxAmmount), "verifying tax calculation", "tax rate is matches to given shipping address tax ","successfully tax rate is matches to given shipping address tax", "tax rate is not matches to given shipping address tax");
			if(userpaneltaxvalue.equals(TaxAmmount)) {
				System.out.println("The tax amount displayed in the site and the calculated tax values are equal");
			}else {
				System.out.println("The tax amount displayed in the site and the calculated tax values are  Not equal");
			}
			
		//	writeResultstoXLSx(subtotlaValue, shippingammountvalue, Taxammountvalue,  Totalammountvalue, userpaneltaxvalue);
			
			
			}
		 catch(Exception |Error e)
			{
				report.addFailedLog("verifying tax calculation", "getting price values from shipping page  ", "Faield to get price value from shipping page", Common.getscreenShotPathforReport("TaxRates"));

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
					Common.assertionCheckwithReport(Message>0, "Will display the Terms and Condition page", Expectedresult, "Failed to navigate to Terms and condition page");
					
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
				try {
					Common.textBoxInput("id", "customer-email", data.get(dataSet).get("Email"));
					Thread.sleep(3000);
					Common.textBoxInput("name", "firstname", data.get(dataSet).get("FirstName"));
					Common.textBoxInput("name", "lastname", data.get(dataSet).get("LastName"));
					Common.textBoxInput("xpath", "(//input[@name='street[0]'])[1]", Street);
					Thread.sleep(3000);
					Common.actionsKeyPress(Keys.ESCAPE);
					Common.textBoxInput("name", "city", City);
					Common.dropdown("xpath", "(//select[@name='region_id'])[1]",Common.SelectBy.TEXT, Region);
					Common.textBoxInput("name", "postcode", postcode);
					
					Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
					Thread.sleep(3000);		
				}catch(Exception |Error e)
				{
					e.printStackTrace();
					Assert.fail();
				}
			}
			public void NavigateHelenoftroy() {
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
					Common.assertionCheckwithReport(Message>0, "Will display the Terms and Condition page", Expectedresult, "Failed to navigate to Terms and condition page");
					
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
}
	
		
		
	
	
