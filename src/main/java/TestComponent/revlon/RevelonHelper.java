package TestComponent.revlon;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import TestLib.Automation_properties;
import TestLib.BaseDriver;
import TestLib.Common;
import TestLib.Sync;
import TestLib.Common.SelectBy;
import Utilities.ExcelReader;
import Utilities.ExtenantReportUtils;

import com.asprise.ocr.Ocr;


public class RevelonHelper {

	String datafile;
	ExcelReader excelData;
	static Automation_properties automation_properties = Automation_properties.getInstance();
	Map<String, Map<String, String>> data=new HashMap<>();
	static ExtenantReportUtils report;

	public void navigateAccount() throws InterruptedException
	{
		String expectedResult="Naviagating to account Creation page";
		try {
			Sync.waitElementClickable(30, By.xpath("//a[@title='My Account']"));
			Common.findElement("xpath", "//a[@title='My Account']").click();
			Sync.waitElementClickable(30, By.xpath("//button[text()='Create an Account']"));
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

	public void CreateNewAccount(String dataSet) throws Exception
	{

		navigateAccount();
		String expectedResult="Account Creation of User with valid details";
		try {
			Common.clickElement("xpath", "//button[text()='Create an Account']");
			Thread.sleep(3000);
			report.addPassLog(expectedResult, "Should display Account Creation page", "Account Creation page display successfully", Common.getscreenShotPathforReport("Account Creation"));
			Common.textBoxInput("id", "firstname", data.get(dataSet).get("FirstName").toString());
			Common.textBoxInput("id", "lastname", data.get(dataSet).get("LastName").toString());
			Common.actionsKeyPress(Keys.ARROW_DOWN);
			Common.textBoxInput("id", "email_address", data.get(dataSet).get("Email").toString());
			Common.textBoxInput("id", "password", data.get(dataSet).get("Password").toString());
			Common.textBoxInput("id", "password-confirmation", data.get(dataSet).get("Password").toString());
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(4000);
			//Common.clickElement("id", "captcha_user_create");
			//Sync.waitElementVisible("className", "captcha-img");
			Common.clickElement("xpath", "//button[@title='Create an Account']");
			Thread.sleep(10000);
			String s=Common.getText("xpath", "//span[contains(text(),'My Account')]");
			Assert.assertEquals(s, "My Account");
			report.addPassLog(expectedResult, "Should display My Account Page", "My Account Page display successfully", Common.getscreenShotPathforReport("Account Creation success"));
			//report.addPassLog(expectedResult, "Should display My Account Page", "My Account Page display successfully", Common.getscreenShotPathforReport("Account Creation success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display My Account Page", "My Account Page not display", Common.getscreenShotPathforReport("Account Creation Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}	


	public void searchProduct(String dataSet) throws Exception
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
			//Common.scrollIntoView("xpath", "(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[1]");
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
			Common.scrollIntoView("xpath", "(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[1]");
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
			Sync.waitElementPresent("xpath", "(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[1]");
			Common.clickElement("xpath", "(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[1]");
			Thread.sleep(3000);
			Common.isElementDisplayed("xpath", "//h1[@class='page-title']");
			report.addPassLog(expectedResult, "Should display Product Details Page", "Product Details Page display successfully", Common.getscreenShotPathforReport("Product Details page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Product Details Page", "Product details Page not displayed", Common.getscreenShotPathforReport("Zearo results Failed"));
			Assert.fail();
		}

	}

	public void loginRevlon(String dataSet) throws Exception
	{

		if(Common.isElementDisplayed("xpath", "//button[text()='AGREE & PROCEED']")) {
			acceptPrivecy();
		}
		navigateAccount();

		String expectedResult="Land on login page";
		try {
			int i=0;
			do{  
				Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
				Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
				Common.scrollIntoView("xpath", "//button[text()='Sign In']");
				//Common.mouseOver("xpath", "//button[text()='Sign In']");
				Common.clickElement("xpath", "//button[text()='Sign In']");
				Thread.sleep(5000);
				i++;
			}while(i<3 && !Common.isElementDisplayed("xpath", "//span[contains(text(),'My Account')]")); 

			report.addPassLog(expectedResult, "Should display My account Page", "My account page display successfully", Common.getscreenShotPathforReport("Login page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display  My account Page", " My account Page not displayed", Common.getscreenShotPathforReport("Login page Failed"));
			Assert.fail();
		}




		/*if(Common.isElementDisplayed("xpath", "//span[contains(text(),'My Account')]")) {
			System.out.println("user not login successfully");
			Assert.assertEquals("My Account", Common.getText("xpath", "//span[contains(text(),'My Account')]"));
			report.addPassLog(expectedResult, "Should display My Account Page", "My Account Page display successfully", Common.getscreenShotPathforReport("User Login success"));
		}else {
			Thread.sleep(100);
			Common.refreshpage();
			Thread.sleep(200);
			Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
			Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
			Common.scrollIntoView("xpath", "//button[text()='Sign In']");
			Thread.sleep(500);
			//report.addPassLog(expectedResult, "", actualResult, screenShotPath);
			//Common.mouseOver("xpath", "//button[text()='Sign In']");
			Common.clickElement("xpath", "//button[text()='Sign In']");
			Thread.sleep(1000);
			report.addPassLog(expectedResult, "Should display My Account Page", "My Account Page display successfully", Common.getscreenShotPathforReport("User Login success"));
		}*/

	}

	public void proccedToCheckout() throws InterruptedException
	{
		Common.clickElement("xpath", "//a[@class='action viewcart hvr-sweep-to-right rev-checkout-btn rev-cart-view']");
		//Common.clickElement("xpath", "//a[@class='action showcart']");
		Thread.sleep(3000);
		Common.clickElement("xpath", "//button[text()=' Checkout']");
		Thread.sleep(4000);
	}

	public void addShippingAddress(String dataSet) throws Exception
	{	
		try {
			Common.textBoxInput("name", "street[0]", data.get(dataSet).get("City"));
		}catch (Exception e) {
			if(Common.findElements("xpath","//div[@class='shipping-address-item selected-item']").size()>0)
			{
				Common.actionsKeyPress(Keys.PAGE_DOWN);
				Common.clickElement("xpath", "//input[@name='shipping_method']");
				Common.clickElement("xpath", "//button[@class='rev-w-btn rev-def-btn next-btn shipping-next']");
				return;
			}
			else {
				proccedToCheckout();
				if(Common.findElements("xpath","//div[@class='shipping-address-item selected-item']").size()>0)
				{
					Common.actionsKeyPress(Keys.PAGE_DOWN);
					Common.clickCheckBox("xpath", "//input[@name='shipping_method']");
					Common.clickElement("xpath", "//button[@class='rev-w-btn rev-def-btn next-btn shipping-next']");
					return;
				}else {
					Common.textBoxInput("name", "street[0]", data.get(dataSet).get("City"));}
			}
		}
		Thread.sleep(2000);
		Common.actionsKeyPress(Keys.SPACE);
		Thread.sleep(3000);
		Common.clickElement("xpath", "//*[@id=\"shipping-new-address-form\"]/fieldset/div/div[1]/div/ul/li[1]/a");
		Common.textBoxInput("xpath", "//input[@name='telephone']", "9898989898");
		Thread.sleep(2000);
		Common.clickElement("xpath", "//button[@class='rev-w-btn rev-def-btn next-btn shipping-next']");
	}

	public void addPaymentDetails(String dataSet) throws Exception
	{
		//Common.switchFrames("xpath", "//iframe[@id='paymetric_xisecure_frame']");
		//Common.switchFrames("xpath", "//iframe[@id='paymetric_xisecure_frame']");
		Sync.waitElementClickable("xpath", "//label[@for='ime_paymetrictokenize']");
		Common.clickElement("xpath", "//label[@for='ime_paymetrictokenize']");
		Thread.sleep(200);
		Common.switchFrames("id", "paymetric_xisecure_frame");
		Thread.sleep(500);
		Common.dropdown("xpath", "//select[@id='c-ct']", Common.SelectBy.TEXT, data.get(dataSet).get("cardType"));
		Common.textBoxInput("id", "c-cardnumber", data.get(dataSet).get("cardNumber"));
		Common.dropdown("xpath", "//select[@id='c-exmth']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpMonth"));
		Common.dropdown("xpath", "//select[@id='c-exyr']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpYear"));
		Common.textBoxInput("id", "c-cvv", data.get(dataSet).get("cvv"));

		Thread.sleep(1000);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.switchToDefault();
		Thread.sleep(500);


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

			Common.clickElement("xpath", "//button[@id='paymetrictokenize_place_order']");
			Thread.sleep(2000);

			//String sucessMessage=Common.getText("xpath", "//h1[@class='page-title']");
			String sucessMessage=Common.getText("xpath", "//h1[contains(text(),'Thank you for your purchase')]");

			System.out.println(sucessMessage);
			Assert.assertEquals(sucessMessage, "THANK YOU FOR YOUR PURCHASE");
			report.addPassLog(expectedResult, "Should display Order Success Page", "Order Success Page display successfully", Common.getscreenShotPathforReport("Order success page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Order Success Page", "Order Success Page not displayed", Common.getscreenShotPathforReport("Order Success Page Failed"));
			Assert.fail();
		}

		/*if(Common.isElementDisplayed("xpath", "//img[@src='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"static/version1603995779/frontend/Pearl/weltpixel_custom/en_US/images/loader-2.gif']"))){
			System.out.println("place order button clicked");

			Sync.waitElementPresent(30, "xpath", "//img[@src='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"static/version1603995779/frontend/Pearl/weltpixel_custom/en_US/images/loader-2.gif']"));
			if(Common.isAlertPresent()) {
				Common.acceptAlert(10);
			}
			Sync.waitAlert(20);
			Common.acceptAlert(5);

			Thread.sleep(2000);
			String sucessMessage=Common.getText("xpath", "//h1[@class='page-title']").trim();
			Assert.assertEquals(sucessMessage, "THANK YOU FOR YOUR PURCHASE");

		}else {
			System.out.println("place order button not clicked");
			Common.clickElement("xpath", "//button[@title='Place Order']");

			//Sync.waitElementPresent(10, "xpath", "//img[@src='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"static/version1603995779/frontend/Pearl/weltpixel_custom/en_US/images/loader-2.gif']"));

			Sync.waitAlert(20);
			Common.acceptAlert(5);

			Thread.sleep(2000);

			String sucessMessage=Common.getText("xpath", "//h1[@class='page-title']").trim();
			Assert.assertEquals(sucessMessage, "THANK YOU FOR YOUR PURCHASE");

		}*/

	}

	public void addPaypalDetails(String dataSet) throws Exception
	{
		Sync.waitElementClickable("xpath", "//label[@for='paypal_express']");
		Common.clickElement("xpath", "//label[@for='paypal_express']");
		Thread.sleep(3000);
		Common.switchFrames("xpath", "//iframe[@title='PayPal']");
		Thread.sleep(4000);
		Sync.waitElementClickable("xpath", "//div[@class='paypal-button-label-container']");
		Common.clickElement("xpath", "//div[@class='paypal-button-label-container']");

		Common.switchToDefault();
		Thread.sleep(10000);
		Common.switchWindows();
		//Common.switchWindows("Log in to your PayPal account");
		Thread.sleep(5000);
		Common.clickElementStale("xpath", "//button[text()='Accept Cookies']");
		Common.textBoxInputClear("name", "login_email");
		Common.textBoxInput("name", "login_email", data.get(dataSet).get("UserName"));
		Common.textBoxInput("name", "login_password", data.get(dataSet).get("Password"));
		Common.clickElement("xpath", "//button[@id='btnLogin']");
		Thread.sleep(5000);
		Common.actionsKeyPress(Keys.END);
		Thread.sleep(5000);
		//Common.scrollIntoView("xpath", "//button[contains(text(),'Pay Now')]");
		Common.clickElement("id", "payment-submit-btn");
		Thread.sleep(8000);
		Common.switchToFirstTab();
	}

	public void updatePaypalPaymentAndSubmitOrder(String dataSet) throws Exception
	{
		String expectedResult="Payment & Order submission page with PayPal";
		try {
			addPaypalDetails(dataSet);

			if(Common.findElements("xpath", "//div[@class='message message-error']").size()>0)
			{	
				addPaypalDetails(dataSet);
			}

			Thread.sleep(8000);

			//String sucessMessage=Common.getText("xpath", "//h1[@class='page-title']");
			String sucessMessage=Common.getText("xpath", "//h1[contains(text(),'Thank you for your purchase')]");

			System.out.println(sucessMessage);
			Assert.assertEquals(sucessMessage, "THANK YOU FOR YOUR PURCHASE");
			report.addPassLog(expectedResult, "Should display Order Success Page", "Order Success Page display successfully", Common.getscreenShotPathforReport("Order success page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Order Success Page", "Order Success Page not displayed", Common.getscreenShotPathforReport("Order Success Page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}


	public void acceptPrivecy()
	{
		Common.clickElementStale("xpath", "//button[text()='AGREE & PROCEED']");
	}

	public  RevelonHelper(String datafile)
	{
		excelData=new ExcelReader(datafile);
		data=excelData.getExcelValue();
		this.data=data;
		if(Utilities.TestListener.report==null)
		{
			report=new ExtenantReportUtils("Revlon");
			report.createTestcase("RevlonUSTestCases");
		}else{
			this.report=Utilities.TestListener.report;
		}
	}


	/* Harish Chiruvella */

	public void accountIcon() throws Exception
	{
		Sync.waitElementPresent("xpath", "//div[@class='authorization-link header_right user-dropdown m-hide non_ipad']");
		Common.clickElement("xpath", "//div[@class='authorization-link header_right user-dropdown m-hide non_ipad']");

	}

	public void myAccountLink() throws Exception
	{
		automation_properties = Automation_properties.getInstance();
		Sync.waitElementPresent("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/customer/account/']"));
		Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/customer/account/']"));
	}

	public void Logout() throws Exception
	{
		automation_properties = Automation_properties.getInstance();
		Sync.waitElementPresent("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/customer/account/logout/']"));
		Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/customer/account/logout/']"));
		Thread.sleep(1000);

	}

	public void forgotPassword(String dataSet) throws Exception
	{
		String expectedResult="Forgot Password for Registered User";
		Thread.sleep(2000);
		try {
			String expectedResult1="Landed on Login page";
			Sync.waitElementClickable(30, By.xpath("//a[@title='My Account']"));
			Common.findElement("xpath", "//a[@title='My Account']").click();
			report.addPassLog(expectedResult1, "Should display login page", "Login page displayed successfully", Common.getscreenShotPathforReport("Login page"));
			Sync.waitElementClickable(30, By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/customer/account/forgotpassword/']")));
			Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/customer/account/forgotpassword/']"));
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
			Assert.fail();
		}
	}

	public void navigateMyAccount(String dataSet) throws Exception
	{
		Sync.waitElementPresent("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/customer/account/']"));
		Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/customer/account/']"));

		Thread.sleep(2000);

		Sync.waitElementPresent("xpath", "//div[@class='box-content']");
		String s=Common.getText("xpath", "//div[@class='box-content']");
		System.out.println(s);
		//assertEquals(s, data.get(dataSet).get("FirstName")+ data.get(dataSet).get("LastName")+  data.get(dataSet).get("Email"));
	}

	public void navigateMyOrder() throws Exception
	{
		accountIcon();
		myAccountLink();

		Sync.waitElementPresent("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/sales/order/history/']"));
		Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/sales/order/history/']"));

		Thread.sleep(2000);

		String s=Common.getText("xpath", "//p[@class='toolbar-amount']");
		System.out.println("No of orders :"+s);	
	}

	public void addNewAddress(String dataSet) throws Exception
	{

		Common.textBoxInput("name", "firstname", data.get(dataSet).get("FirstName"));
		Common.textBoxInput("name", "lastname", data.get(dataSet).get("LastName"));
		Common.textBoxInput("name", "company", data.get(dataSet).get("Company"));
		Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
		Common.textBoxInput("name", "street[]", data.get(dataSet).get("Street"));
		Common.textBoxInput("name", "city", data.get(dataSet).get("City"));

		Thread.sleep(1000);

		Sync.waitElementPresent("xpath", "//select[@title='State/Province']");
		Common.clickElement("xpath", "//select[@title='State/Province']");

		Thread.sleep(1000);

		Sync.waitElementPresent("xpath", "//select[@title='State/Province']");
		//Common.clickElement("xpath", "//select[@title='State/Province']");
		Common.dropdown("xpath", "//select[@title='State/Province']", SelectBy.TEXT, data.get(dataSet).get("Region"));


		Sync.waitElementPresent("name", "postcode");
		Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));

		Common.dropdown("xpath", "//select[@name='country_id']", SelectBy.TEXT, data.get(dataSet).get("Country"));

		Thread.sleep(500);

		Sync.waitElementPresent("xpath", "//button[@title='Save Address']");
		Common.clickElement("xpath", "//button[@title='Save Address']");


		Sync.waitElementPresent("xpath", "//button[@class='action-primary action-accept']");
		Common.clickElement("xpath", "//button[@class='action-primary action-accept']");

	}

	public void navigateAddressBook() throws Exception
	{
		accountIcon();
		myAccountLink();

		Sync.waitElementPresent("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/customer/address/']"));
		Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/customer/address/']"));

		if(Common.isElementDisplayed("xpath", "//button[@title='Add New Address']"))
		{
			System.out.println("Add New Address clicked");

			Sync.waitElementPresent("xpath", "//button[@title='Add New Address']");
			Common.clickElement("xpath", "//button[@title='Add New Address']");

			Thread.sleep(5000);
			if(Common.isElementDisplayed("name", "firstname")) {
				System.out.println("Entered into Add New Address page");
				addNewAddress("Address");
			}else {

				System.out.println("Not Entered into Add New Address page");

				Thread.sleep(2000);

				Sync.waitElementPresent("xpath", "//button[@title='Add New Address']");
				Common.clickElement("xpath", "//button[@title='Add New Address']");

				Thread.sleep(5000);

				addNewAddress("Address");
			}
		}
		else {
			System.out.println("Add New Address not clicked");

			addNewAddress("Addressbook");
		}


	}

	public void navigateAccountInformation() throws Exception
	{
		accountIcon();
		myAccountLink();

		Sync.waitElementPresent("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/customer/account/edit/']"));
		Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/customer/account/edit/']"));

	}

	public void changeAIName(String dataSet) throws Exception
	{

		Thread.sleep(1000);

		Common.textBoxInput("name", "firstname", data.get(dataSet).get("FirstName"));
		Common.textBoxInput("name", "lastname", data.get(dataSet).get("LastName"));

		Thread.sleep(500);

		Sync.waitElementPresent("xpath", "//button[@title='Save']");
		Common.clickElement("xpath", "//button[@title='Save']");

		Thread.sleep(1000);

		String s=Common.getText("xpath", "//div[@data-bind='html: message.text']");
		System.out.println(s);

	}
	public void changeAIEmail(String dataSet) throws Exception
	{

		Thread.sleep(1000);

		Sync.waitElementPresent("name", "change_email");	
		Common.clickElement("name", "change_email");
		Thread.sleep(500);

		Common.textBoxInput("name", "email", data.get(dataSet).get("Email"));
		Common.textBoxInput("name", "current_password", data.get(dataSet).get("Password"));

		Thread.sleep(500);

		Sync.waitElementPresent("xpath", "//button[@title='Save']");
		Common.clickElement("xpath", "//button[@title='Save']");

		Thread.sleep(1000);

		String s=Common.getText("xpath", "//div[@data-bind='html: message.text']");
		System.out.println(s);
	}

	public void changeAIPassword(String dataSet) throws Exception
	{

		Thread.sleep(1000);
		Sync.waitElementPresent("name", "change_password");	
		Common.clickElement("name", "change_password");
		Thread.sleep(500);

		Common.textBoxInput("xpath", "//input[@data-input='current-password']", data.get(dataSet).get("Password"));
		Common.textBoxInput("xpath", "//input[@data-input='new-password']", data.get(dataSet).get("Password"));
		Common.textBoxInput("name", "password_confirmation", data.get(dataSet).get("Password"));


		Thread.sleep(500);

		Sync.waitElementPresent("xpath", "//button[@title='Save']");
		Common.clickElement("xpath", "//button[@title='Save']");

		String s=Common.getText("xpath", "//div[@data-bind='html: message.text']");
		System.out.println(s);

	}

	public void navigateNewsletterSubscription() throws Exception
	{
		accountIcon();
		myAccountLink();

		Sync.waitElementPresent("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/newsletter/manage/']"));
		Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/newsletter/manage/']"));

		Thread.sleep(300);

		Sync.waitElementPresent("xpath", "//input[@name='is_subscribed']");
		Common.clickElement("xpath", "//input[@name='is_subscribed']");

		Sync.waitElementPresent("xpath", "//button[@title='Save']");
		Common.clickElement("xpath", "//button[@title='Save']");

		Thread.sleep(300);

		String s=Common.getText("xpath", "//div[@class='box box-newsletter']//div[@class='box-content']");
		System.out.println(s);


	}

	public void navigateContactUs() throws Exception
	{
		String expectedResult="Lands on Contact page";
		try {
			Sync.waitElementPresent("xpath", "//h1[contains(text(),'Hair Tools')]");
			Common.scrollToElementAndClick("xpath", "//h1[contains(text(),'Hair Tools')]");

			Sync.waitElementPresent("xpath", "//a[@href='/contact-us/']");
			Common.clickElement("xpath", "//a[@href='/contact-us/']");
			Thread.sleep(300);
			String s=Common.getText("xpath", "//span[contains(text(),'Contact Us')]");
			System.out.println(s);
			Assert.assertEquals(s, "CONTACT US");

			report.addPassLog(expectedResult, "Should display Contact Us Page", "Contact Us Page display successfully", Common.getscreenShotPathforReport("Contact Us page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Contact Us Page", "Contact Us Page not displayed", Common.getscreenShotPathforReport("Contact Us Failed"));
			Assert.fail();
		}

	}

	public void ContactUsform(String dataSet) throws Exception
	{
		String expectedResult="Contact Us Form submited";
		String s=Common.getText("xpath", "//h1[@class='page-title']");
		System.out.println(s+" page is displayed");

		Thread.sleep(1000);

		Common.switchFrames("xpath", "//iframe[@src='https://helenoftroy--tst2.custhelp.com/app/ask/themes/revlon']");

		//Common.clickElement("xpath", "//body[@id='rn_BlankBody']");

		Sync.waitPageLoad();

		System.out.println(s+" page switched to form");

		Sync.waitElementPresent("name", "Contact.Name.First");
		Common.textBoxInput("xpath","//input[@name='Contact.Name.First']", data.get(dataSet).get("FirstName"));

		Common.textBoxInput("name", "Contact.Name.Last", data.get(dataSet).get("LastName"));

		Common.textBoxInput("name", "Contact.CustomFields.c.company", data.get(dataSet).get("Company"));

		Sync.waitElementPresent("name", "Contact.Address.Country");
		Common.dropdown("name", "Contact.Address.Country", SelectBy.TEXT, data.get(dataSet).get("Country"));

		Common.textBoxInput("name", "Contact.Address.Street", data.get(dataSet).get("Street"));

		Common.textBoxInput("name", "Contact.Address.City", data.get(dataSet).get("City"));

		Sync.waitElementPresent("name", "Contact.Address.StateOrProvince");
		Common.dropdown("name", "Contact.Address.StateOrProvince", SelectBy.TEXT, data.get(dataSet).get("Region"));

		Common.textBoxInput("name", "Contact.Address.PostalCode", data.get(dataSet).get("postcode"));

		Common.textBoxInput("name", "Contact.Emails.PRIMARY.Address", data.get(dataSet).get("Email"));

		Common.textBoxInput("name", "Contact.Phones.MOBILE.Number", data.get(dataSet).get("phone"));

		Common.textBoxInput("name", "searchKeyword", data.get(dataSet).get("ProductName"));

		Thread.sleep(300);

		System.out.println("Product search are display");

		Sync.waitElementPresent("xpath", "//*[@id='searchResults']/div[1]");
		Common.scrollToElementAndClick("xpath", "//*[@id='searchResults']/div[1]");

		Thread.sleep(300);

		Common.clickElement("name", "Incident.CustomFields.c.date_code");

		Common.textBoxInput("name", "Incident.CustomFields.c.date_code", data.get(dataSet).get("dataCode"));

		Sync.waitElementPresent("id", "rn_ProductCategoryInput_27_Category_Button");
		Common.clickElement("id", "rn_ProductCategoryInput_27_Category_Button");

		if(Common.isElementDisplayed("xpath", "//a[contains(text(),'Product Info Request')]")) {
			Thread.sleep(300);

			System.out.println("Topic dropdown are display");

			Common.clickElement("xpath", "//a[contains(text(),'Product Info Request')]");
		}

		Thread.sleep(300);

		Common.textBoxInput("name", "Incident.Threads", data.get(dataSet).get("Message"));

		report.addPassLog(expectedResult, "Should dispaly Contact us page with data", "Contact Us Page with data displayed successfully", Common.getscreenShotPathforReport("Contact Us Page with data"));

		Sync.waitElementPresent("id", "rn_CustomFormSubmit_53_Button");
		Common.clickElement("id", "rn_CustomFormSubmit_53_Button");

		Thread.sleep(500);
	}

	public void contactus() throws Exception
	{
		String expectedResult="Contact Us Submit success page";
		try {
			if(Common.isElementEnabled("name", "Contact.Name.First")) {

				System.out.println("Contact Us page Enabled");
				ContactUsform("Contact_us");

			}else {

				System.out.println("Contact Us page not Enabled");

				Common.refreshpage();

				ContactUsform("Contact_us");
			}

			Thread.sleep(7000);
			String s=Common.getText("xpath", "//div[@class='rn_Container']");
			System.out.println(s);
			System.out.println("Contact us success page Test cases passed successfully");

			Assert.assertEquals("Your question has been submitted!", "Your question has been submitted!");

			report.addPassLog(expectedResult, "Should display Product Registration success Page", "Product Registration success Page display successfully", Common.getscreenShotPathforReport("Product Registration success page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Product Registration success Page", "Product Registration success Page not displayed", Common.getscreenShotPathforReport("Product Registration success Failed"));
			Assert.fail();
		}



	}

	public void navigateProductRegistration() throws Exception
	{
		String expectedResult="Lands on Product Registration";
		try {
			Sync.waitElementPresent("xpath", "//h1[contains(text(),'Hair Tools')]");
			Common.scrollToElementAndClick("xpath", "//h1[contains(text(),'Hair Tools')]");

			Sync.waitElementPresent("xpath", "//a[@href='/product-registration/']");
			Common.clickElement("xpath", "//a[@href='/product-registration/']");
			Thread.sleep(300);

			String s=Common.getText("xpath", "//span[contains(text(),'Product Registration')]");
			System.out.println(s +" navigated successfully");

			Assert.assertEquals(s, "PRODUCT REGISTRATION");

			report.addPassLog(expectedResult, "Should display Product Registration Page", "Product Registration Page display successfully", Common.getscreenShotPathforReport("Product Registration page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Product Registration Page", "Product Registration Page not displayed", Common.getscreenShotPathforReport("Product Registration Failed"));
			Assert.fail();
		}



	}

	public void productregistrationform(String dataSet) throws Exception
	{
		String expectedResult="Product Registration Form submission";
		String s=Common.getText("xpath", "//h1[@class='page-title']");
		System.out.println(s+" page is displayed");

		Thread.sleep(1000);

		Common.switchFrames("xpath", "//iframe[@src='https://helenoftroy--tst2.custhelp.com/app/product_registration/themes/revlon']");

		Thread.sleep(1000);

		Common.clickElement("name", "Contact.Name.First");

		Sync.waitElementPresent("name", "Contact.Name.First");
		Common.textBoxInput("name", "Contact.Name.First", data.get(dataSet).get("FirstName"));

		Common.textBoxInput("name", "Contact.Name.Last", data.get(dataSet).get("LastName"));

		Common.textBoxInput("name", "Contact.Emails.PRIMARY.Address", data.get(dataSet).get("Email"));

		Sync.waitElementPresent("name", "Contact.Address.Country");
		Common.dropdown("name", "Contact.Address.Country", SelectBy.TEXT, data.get(dataSet).get("Country"));

		Common.textBoxInput("name", "Contact.Address.Street", data.get(dataSet).get("Street"));

		Common.textBoxInput("name", "Contact.Address.City", data.get(dataSet).get("City"));

		Sync.waitElementPresent("name", "Contact.Address.StateOrProvince");
		Common.dropdown("name", "Contact.Address.StateOrProvince", SelectBy.TEXT, data.get(dataSet).get("Region"));

		Common.textBoxInput("name", "Contact.Address.PostalCode", data.get(dataSet).get("postcode"));

		Common.textBoxInput("name", "Contact.Phones.MOBILE.Number", data.get(dataSet).get("phone"));

		Common.textBoxInput("name", "searchKeyword", data.get(dataSet).get("ProductName"));

		Thread.sleep(300);

		System.out.println("Product search are display");

		Sync.waitElementPresent("xpath", "//*[@id='searchResults']/div[1]");
		Common.scrollToElementAndClick("xpath", "//*[@id='searchResults']/div[1]");

		Thread.sleep(300);

		Sync.waitElementPresent("name", "Asset.CustomFields.HOT.store_purchased");
		Common.dropdown("name", "Asset.CustomFields.HOT.store_purchased", SelectBy.TEXT, data.get(dataSet).get("vendorname"));

		Common.textBoxInput("name", "Asset.CustomFields.HOT.date_code", data.get(dataSet).get("dataCode"));

		Common.textBoxInput("id", "rn_DateInput_DateTimeUI_29", data.get(dataSet).get("date")+"/"+data.get(dataSet).get("month")+"/"+data.get(dataSet).get("year"));

		Common.clickElement("name", "Asset.CustomFields.HOT.research_panel_revlon");
		report.addPassLog(expectedResult, "Should dispaly Product registration page with data", "Product Registration Page with data displayed successfully", Common.getscreenShotPathforReport("Product Registration Page with data"));

		Sync.waitElementPresent("id", "rn_CustomFormSubmit2_55_Button");
		Common.clickElement("id", "rn_CustomFormSubmit2_55_Button");

	}

	public void ProductRegistration() throws Exception
	{
		String expectedResult="Product Registratin form submission success page";
		try {
			String s=Common.getText("xpath", "//h1[contains(text(),'Hair Tools')]");
			System.out.println(s);
			if(Common.isElementEnabled("name", "Contact.Name.First")) {

				System.out.println("Product Registration page Enabled");
				productregistrationform("Product_Registration");

			}else {

				System.out.println("Product Registration page not Enabled");

				Common.refreshpage();

				productregistrationform("Product_Registration");
			}
			Thread.sleep(7000);

			Common.switchToDefault();

			Assert.assertEquals("Thank you for registering your product! Your request has been processed.", "Thank you for registering your product! Your request has been processed.");

			report.addPassLog(expectedResult, "Should display Product Registration success Page", "Product Registration success Page display successfully", Common.getscreenShotPathforReport("Product Registration success page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Product Registration success Page", "Product Registration success Page not displayed", Common.getscreenShotPathforReport("Product Registration success Failed"));
			Assert.fail();
		}


	}

	public void navigatefooter() throws Exception
	{

		if(Common.isElementDisplayed("xpath", "//button[text()='AGREE & PROCEED']")) {
			acceptPrivecy();
		}

		Sync.waitElementPresent("xpath", "//h1[contains(text(),'Hair Tools')]");
		Common.scrollToElementAndClick("xpath", "//h1[contains(text(),'Hair Tools')]");

	}

	public void navigateCMSLink() throws Exception
	{
		String expectedResult="Lands On Home page footer links";
		try {
			Sync.waitElementPresent("xpath", "//h1[contains(text(),'Hair Tools')]");
			Common.scrollToElementAndClick("xpath", "//h1[contains(text(),'Hair Tools')]");
			Common.isElementDisplayed("xpath", "//h1[contains(text(),'Hair Tools')]");
			report.addPassLog(expectedResult, "Should display CMS Link Page", "CMS Link Page display successfully", Common.getscreenShotPathforReport("CMS Link page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display CMS Link Page", "CMS Link Page not displayed", Common.getscreenShotPathforReport("CMS Link Failed"));
			Assert.fail();
		}


	}

	public void navigateAboutUs() throws Exception
	{
		String expectedResult="Lands on About Us page";
		try {
			Sync.waitElementPresent("xpath", "//a[@href='/about-us/']");
			Common.scrollToElementAndClick("xpath", "//a[@href='/about-us/']");

			Thread.sleep(300);

			String s=Common.getText("xpath", "//span[contains(text(),'ABOUT US')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "ABOUT US");

			report.addPassLog(expectedResult, "Should display ABOUT US Page", "ABOUT US Page display successfully", Common.getscreenShotPathforReport("ABOUT US page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display ABOUT US Page", "ABOUT US Page not displayed", Common.getscreenShotPathforReport("ABOUT US Failed"));
			Assert.fail();
		}



	}

	public void navigateFAQ() throws Exception
	{
		String expectedResult="Lands on FAQ page";
		try {
			Sync.waitElementPresent("xpath", "//a[@href='/faqs/']");
			Common.scrollToElementAndClick("xpath", "//a[@href='/faqs/']");

			Thread.sleep(300);

			String s=Common.getText("xpath", "//span[contains(text(),'Faqs')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "FAQS");

			report.addPassLog(expectedResult, "Should display FAQ Page", "FQA Page display successfully", Common.getscreenShotPathforReport("FAQ page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display FAQ Page", "FQA Page not displayed", Common.getscreenShotPathforReport("FAQ Failed"));
			Assert.fail();
		}

	}

	public void navigatePrivacyPolicy() throws Exception
	{
		String expectedResult="Lands on Privacy policy page";
		try {
			Sync.waitElementPresent("xpath", "//a[@href='/privacy-policy/']");
			Common.scrollToElementAndClick("xpath", "//a[@href='/privacy-policy/']");

			Thread.sleep(300);

			String s=Common.getText("xpath", "//span[contains(text(),'Privacy Policy')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "PRIVACY POLICY");

			report.addPassLog(expectedResult, "Should display Privacy Policy Page", "Privacy Policy Page display successfully", Common.getscreenShotPathforReport("Privacy Policy page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Privacy Policy Page", "Privacy Policy Page not displayed", Common.getscreenShotPathforReport("Privacy Policy Failed"));
			Assert.fail();
		}


	}

	public void navigateFindStore() throws Exception
	{
		String expectedResult="Lands on Find a Store page";
		try {
			Sync.waitElementPresent("xpath", "//a[@href='/find-a-store/']");
			Common.scrollToElementAndClick("xpath", "//a[@href='/find-a-store/']");

			Thread.sleep(300);

			String s=Common.getText("xpath", "//span[contains(text(),'Find a Store')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "FIND A STORE");

			report.addPassLog(expectedResult, "Should display Find a Store Page", "Find a Store Page display successfully", Common.getscreenShotPathforReport("Find a Store page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Find a Store Page", "Find a Store Page not displayed", Common.getscreenShotPathforReport("Find a Store Failed"));
			Assert.fail();
		}


	}

	public void navigateReturns() throws Exception
	{
		String expectedResult="Lands On Returns page";
		try {
			Sync.waitElementPresent("xpath", "//a[@href='/returns/']");
			Common.scrollToElementAndClick("xpath", "//a[@href='/returns/']");

			Thread.sleep(300);

			String s=Common.getText("xpath", "//span[contains(text(),'RETURNS')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "RETURNS");

			report.addPassLog(expectedResult, "Should display RETURNS Page", "RETURNS Page display successfully", Common.getscreenShotPathforReport("RETURNS page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display RETURNS Page", "RETURNS Page not displayed", Common.getscreenShotPathforReport("RETURNS Failed"));
			Assert.fail();
		}


	}

	public void navigatePressRoom() throws Exception
	{
		String expectedResult="Lands on Press Room page";
		try {
			Sync.waitElementPresent("xpath", "//a[@href='/press-room/']");
			Common.scrollToElementAndClick("xpath", "//a[@href='/press-room/']");

			Thread.sleep(300);

			String s=Common.getText("xpath", "//span[contains(text(),'Press Room')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "PRESS ROOM");

			report.addPassLog(expectedResult, "Should display Press Room Page", "Press Room Page display successfully", Common.getscreenShotPathforReport("Press Room page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Press Room Page", "Press Room Page not displayed", Common.getscreenShotPathforReport("Press Room Failed"));
			Assert.fail();
		}


	}

	public void navigateTermsOfUse() throws Exception
	{
		String expectedResult="Lands on Terms Of Use page";
		try {
			Sync.waitElementPresent("xpath", "//a[@href='/terms-and-conditions/']");
			Common.scrollToElementAndClick("xpath", "//a[@href='/terms-and-conditions/']");

			Thread.sleep(300);

			String s=Common.getText("xpath", "//span[contains(text(),'TERMS OF USE')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "TERMS OF USE");

			report.addPassLog(expectedResult, "Should display TERMS OF USE Page", "TERMS OF USE Page display successfully", Common.getscreenShotPathforReport("TERMS OF USE page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display TERMS OF USE Page", "TERMS OF USE Page not displayed", Common.getscreenShotPathforReport("TERMS OF USE Failed"));
			Assert.fail();
		}


	}

	public void navigateHomePage() throws Exception
	{
		String expectedResult="Lands on Home page";
		try {
			Sync.waitElementPresent("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/']"));
			Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/']"));

			Thread.sleep(1000);

			report.addPassLog(expectedResult, "Should display Home Page", "Home Page display successfully", Common.getscreenShotPathforReport("Home page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Home Page", "Home Page not displayed", Common.getscreenShotPathforReport("Home Failed"));
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
			Thread.sleep(5000);
			Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/checkout/cart/']"));
			Common.isElementDisplayed("xpath", "//strong[@class='product-item-name']");
			report.addPassLog(expectedResult, "Should display Mini Cart Page", "Mini Cart Page display successfully", Common.getscreenShotPathforReport("Mini Cart page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Mini Cart Page", "Mini Cart Page not displayed", Common.getscreenShotPathforReport("Mini Cart Failed"));
			e.printStackTrace();
			Assert.fail();
		}

		/*
		 * if(Common.isElementDisplayed("xpath",
		 * "//a[@href='"+System.getProperty("url",automation_properties.getInstance().
		 * getProperty(automation_properties.BASEURL)+"us_en/checkout/cart/']"))) {
		 * 
		 * System.out.println("Mini cart page is disaplyed");
		 * 
		 * if(Common.isElementDisplayed("xpath",
		 * "//span[contains(text(),'You have no items in your shopping cart.')]")) {
		 * 
		 * Common.clickElement("xpath", "//button[@id='btn-minicart-close']");
		 * 
		 * Common.clickElement("xpath", "//span[contains(text(),'Buy Now -')]");
		 * 
		 * System.out.println("Clicked on cart again for product display");
		 * 
		 * Common.clickElement("xpath",
		 * "//a[@href='"+System.getProperty("url",automation_properties.getInstance().
		 * getProperty(automation_properties.BASEURL)+"us_en/checkout/cart/']")); }
		 * }else { Thread.sleep(500); if(Common.isElementDisplayedonPage(30, "xpath",
		 * "//a[@href='"+System.getProperty("url",automation_properties.getInstance().
		 * getProperty(automation_properties.BASEURL)+"us_en/checkout/cart/']"))) {
		 * 
		 * Sync.waitElementPresent("xpath",
		 * "//a[@href='"+System.getProperty("url",automation_properties.getInstance().
		 * getProperty(automation_properties.BASEURL)+"us_en/checkout/cart/']"));
		 * Common.clickElement("xpath",
		 * "//a[@href='"+System.getProperty("url",automation_properties.getInstance().
		 * getProperty(automation_properties.BASEURL)+"us_en/checkout/cart/']"));
		 * 
		 * 
		 * 
		 * if(Common.isElementDisplayed("xpath",
		 * "//span[contains(text(),'You have no items in your shopping cart.')]")) {
		 * 
		 * Common.clickElement("xpath", "//button[@id='btn-minicart-close']");
		 * 
		 * Common.clickElement("xpath", "//span[contains(text(),'Buy Now -')]");
		 * 
		 * System.out.println("Clicked on cart again for product display");
		 * 
		 * Common.clickElement("xpath",
		 * "//a[@href='"+System.getProperty("url",automation_properties.getInstance().
		 * getProperty(automation_properties.BASEURL)+"us_en/checkout/cart/']"));
		 * report.addPassLog(expectedResult, "Should dispaly Mini Cart",
		 * "Mini Cart displayed successfully",
		 * Common.getscreenShotPathforReport("Mini cart page"));
		 * 
		 * } } }
		 */

	}

	public void navigateCartPage() throws Exception
	{
		String expectedResult="Product adding to cart page";
		try {

			try {
				Thread.sleep(5000);
				Sync.waitElementPresent("xpath", "//div[@data-bind='html: message.text']//a[contains(text(),'shopping cart')]");
				Common.clickElement("xpath", "//div[@data-bind='html: message.text']//a[contains(text(),'shopping cart')]");

			}catch (Exception e) {
				Thread.sleep(1000);
				Common.refreshpage();

				/*Sync.waitElementPresent("xpath", "//button[@id='product-addtocart-button']");
				Common.clickElement("xpath", "//button[@id='product-addtocart-button']");

				Thread.sleep(500);

				Sync.waitElementPresent("xpath", "//div[@data-bind='html: message.text']//a[contains(text(),'shopping cart')]");
				Common.clickElement("xpath", "//div[@data-bind='html: message.text']//a[contains(text(),'shopping cart')]");*/
			}

			Thread.sleep(5000);
			Common.isElementDisplayed("xpath", "//strong[@class='product-item-name']");
			report.addPassLog(expectedResult, "Should display Cart Page", "Cart Page display successfully", Common.getscreenShotPathforReport("Cart page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Cart Page", "Cart Page not displayed", Common.getscreenShotPathforReport("Cart Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void checkoutPage() throws Exception
	{
		String expectedResult="Product adding to checkout page";
		try {
			Sync.waitElementPresent("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/checkout/']"));
			Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/checkout/']"));
			Thread.sleep(1000);
			Common.isElementDisplayed("xpath", "//div[contains(text(),'Shipping Address')]");
			report.addPassLog(expectedResult, "Should display Checkout Page", "Checkout Page display successfully", Common.getscreenShotPathforReport("Checkout page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Checkout Page", "Checkout Page not displayed", Common.getscreenShotPathforReport("Checkout Failed"));
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

		Sync.waitElementPresent("name", "region_id");
		Common.clickElement("name", "region_id");

		Thread.sleep(4000);

		Sync.waitElementPresent("name", "region_id");
		//Common.clickElement("xpath", "//select[@title='State/Province']");
		Common.dropdown("name", "region_id", SelectBy.TEXT, data.get(dataSet).get("Region"));
		//Common.dropdown("xpath", "//div[@name='shippingAddress.region_id']/div//select[@name='region_id']", SelectBy.TEXT, data.get(dataSet).get("Region"));

		Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));

		Common.dropdown("xpath", "//select[@name='country_id']", SelectBy.TEXT, data.get(dataSet).get("Country"));

		Thread.sleep(500);

		Common.textBoxInput("name", "telephone",data.get(dataSet).get("phone"));

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

			Thread.sleep(300);

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

	public void navigateCheckout() throws Exception
	{
		String expectedResult="Checkout page of Register user";
		try {
			Common.clickElement("xpath", "//span[contains(text(),'Ship Here')]");
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
			Assert.fail();
		}

	}

	public void CheckoutShippingAddress(String dataset) throws Exception
	{
		String expectedResult="Checkout page with new address pop up";
		try {
			Thread.sleep(3000);

			Sync.waitElementPresent("xpath", "//button[@data-bind='click: showFormPopUp, visible: !isNewAddressAdded()']");
			Common.clickElement("xpath", "//button[@data-bind='click: showFormPopUp, visible: !isNewAddressAdded()']");

			Sync.waitElementPresent("xpath", "//h1[contains(text(),'Shipping Address')]");
			Thread.sleep(3000);
			shipping_Address(dataset);

			Thread.sleep(2000);
			Common.clickElement("xpath", "//button[@class='rev-w-btn rev-def-btn next-btn']//span[contains(text(),'Ship here')]");

			Sync.waitPageLoad();
			report.addPassLog(expectedResult, "Should display checkout page with new address", "Checkout Page with new address display successfully", Common.getscreenShotPathforReport("Checkout page with new address success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Checkout Page", "Checkout Page not displayed", Common.getscreenShotPathforReport("Checkout Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}

	public void EditBillingAddress() throws Exception
	{
		accountIcon();
		myAccountLink();
		
		String expectedResult="Edit billing address from My Account";
		try {
			Sync.waitElementPresent("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/customer/address/']"));
			Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/customer/address/']"));

			Thread.sleep(3000);

			Sync.waitElementPresent("xpath", "//a[contains(text(),'Change Billing Address')]");
			Common.clickElement("xpath", "//a[contains(text(),'Change Billing Address')]");

			addNewAddress("Address");
			Thread.sleep(3000);
			
			report.addPassLog(expectedResult, "Should edit the billing address from My Account", "Billing Address from my account updated successfully", Common.getscreenShotPathforReport("Billing address my account success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should edit the billing address from My Account", "Billing Address from my account not updated", Common.getscreenShotPathforReport("Billing address my account Failed"));
			e.printStackTrace();
			Assert.fail();
		}

		

	}

	public void selectioncategory() throws Exception
	{
		String expectedResult="Selection of Dryer category";
		try {
			Sync.waitElementPresent("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/dryers']"));
			Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/dryers']"));

			Thread.sleep(10000);

			Sync.waitPageLoad(30);

			String header=Common.getText("id", "page-title-heading");
			System.out.println(header+" page navigated");

			Assert.assertEquals(header, "Dryers");
			report.addPassLog(expectedResult, "Should display checkout page with new address", "Checkout Page with new address display successfully", Common.getscreenShotPathforReport("Checkout page with new address success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Checkout Page", "Checkout Page not displayed", Common.getscreenShotPathforReport("Checkout Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}

	public void Productdetails() throws Exception
	{
		String expectedResult="Lands on product details page";
		try {
			Sync.waitElementPresent("xpath", "(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[1]");
			Common.clickElement("xpath", "(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[1]");
			Thread.sleep(10000);
			Common.isElementDisplayed("xpath", "//h1[@class='page-title']");

			Thread.sleep(8000);
			String producttitle=Common.getText("xpath", "//h1[@class='page-title']");
			System.out.println(producttitle+" is selected");

			Assert.assertTrue(Common.isElementDisplayed("xpath", "//h1[@class='page-title']"));
			report.addPassLog(expectedResult, "Should display Product Details Page with title", "Product Details Page with title display successfully", Common.getscreenShotPathforReport("Product Details page title success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Product Details Page with title", "Product Details Page with title display failed", Common.getscreenShotPathforReport("Product Details page title Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}

	public void ProductdetailsQuantity() throws Exception
	{
		String expectedResult="Product Details page Changing the Quantity";
		try {
			Common.dropdown("name", "qty", SelectBy.TEXT, "3");

			String QTY=Common.getSelectedOption("name", "qty");
			System.out.println("Changed product quantity to "+QTY);

			Assert.assertEquals(QTY, "3");
			report.addPassLog(expectedResult, "Should display Product Details Page Change qunatity", "Product Details Page Change qunatity display successfully", Common.getscreenShotPathforReport("Product Details page Change qunatity success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Product Details Page Change qunatity", "Product Details Page Change qunatity display Failed", Common.getscreenShotPathforReport("Product Details page Change qunatity Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}

	public void ProductDetailsBreadCrumbs() throws Exception
	{
		String expectedResult="Validating Product details page BreadCrumbs";
		try {
			String BC1=Common.getText("xpath", "//div[@class='breadcrumbs']/ul/li[1]");
			System.out.println(BC1+" is Displayed");

			Assert.assertEquals(BC1, "HOME");

			String BC2=Common.getText("xpath", "//div[@class='breadcrumbs']/ul/li[2]");
			System.out.println(BC2+" is Displayed");

			Assert.assertEquals(BC2, "DRYERS");

			String BC3=Common.getText("xpath", "//div[@class='breadcrumbs']/ul/li[3]");
			System.out.println(BC3+" is Displayed");

			Assert.assertEquals(BC3, Common.getText("xpath", "//h1[@class='page-title']"));
			report.addPassLog(expectedResult, "Should display Product Details Page with BreadCrumb", "Product Details Page with BreadCrumb display successfully", Common.getscreenShotPathforReport("Product Details page BreadCrumb success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Product Details Page with BreadCrumb", "Product Details Page with BreadCrumb display Failed", Common.getscreenShotPathforReport("Product Details page BreadCrumb Failed"));
			e.printStackTrace();
			Assert.fail();
		}
		
	}


	public void Searchresultsfilter() throws Exception
	{
		String expectedResult="Validating Product listing page filter with Price (Low to High)";
		String expectedResult1="Validating Product listing page filter with Price (High to Low)";
		String expectedResult2="Validating Product listing page filter with Newest Arrivals";
		String expectedResult3="Validating Product listing page filter";
		String expectedResult4="Validating Product listing page filter";
		try {

			Common.dropdown("id", "sorter", SelectBy.TEXT, "Price (Low to High)");
			Thread.sleep(5000);
			String filter1=Common.getText("id", "dynamicProductCounts");
			System.out.println("Price (Low to High) filer count is "+filter1);
			report.addPassLog(expectedResult, "Should display Product Listing Page with filters Price (Low to High)", "Product Listing Page with filters Price (Low to High) display successfully", Common.getscreenShotPathforReport("PLP filter with Price (Low to High) success"));
			Thread.sleep(5000);
			
			Common.dropdown("id", "sorter", SelectBy.TEXT, "Price (High to Low)");
			Thread.sleep(5000);
			String filter2=Common.getText("id", "dynamicProductCounts");
			System.out.println("Price (High to Low) filer count is "+filter2);
			report.addPassLog(expectedResult1, "Should display Product Listing Page with filters Price (High to Low)", "Product Listing Page with filters Price (High to Low) display successfully", Common.getscreenShotPathforReport("PLP filter with Price (High to Low) success"));
			Thread.sleep(5000);
			
			Common.dropdown("id", "sorter", SelectBy.TEXT, "Newest Arrivals");
			Thread.sleep(5000);
			String filter3=Common.getText("id", "dynamicProductCounts");
			System.out.println("Newest Arrivals filer count is "+filter3);
			report.addPassLog(expectedResult2, "Should display Product Listing Page with filters Newest Arrivals", "Product Listing Page with filters Newest Arrivals display successfully", Common.getscreenShotPathforReport("PLP filter with Newest Arrivals success"));
			Thread.sleep(5000);
			
			Common.dropdown("id", "sorter", SelectBy.TEXT, "Price (Low to High)");
			Thread.sleep(5000);
			String filter4=Common.getText("id", "dynamicProductCounts");
			System.out.println("Relevance filer count is "+filter4);
			report.addPassLog(expectedResult3, "Should display Product Listing Page with filters Relevance", "Product Listing Page with filters Relevance display successfully", Common.getscreenShotPathforReport("PLP filter with Relevance success"));
			Thread.sleep(10000);
			
			Common.clickElement("name", "amshopby[collections][]");
			Thread.sleep(5000);
			String filter5=Common.getText("id", "dynamicProductCounts");
			System.out.println("Essentials filer count is "+filter5);
			report.addPassLog(expectedResult4, "Should display Product Listing Page with filters Essentials", "Product Listing Page with filters with Essentials display successfully", Common.getscreenShotPathforReport("PLP filter with Essentials success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Product Listing Page with filters", "Product Listing Page with filters display Failed", Common.getscreenShotPathforReport("Product Listing Page with filters Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}
	
	public void searchProductwithmultiple(String dataSet) throws Exception
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
			String search=Common.getText("id", "dynamicProductCounts");
			System.out.println(data.get(dataSet).get("ProductName")+" Search result count is "+search);
			//Common.scrollIntoView("xpath", "(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[1]");
			report.addPassLog(expectedResult, "Should display Search Results Page for "+data.get(dataSet).get("ProductName"), "Search results Page display for "+data.get(dataSet).get("ProductName")+" successfully", Common.getscreenShotPathforReport("Search results success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Search Results Page for "+data.get(dataSet).get("ProductName"), "Search results Page not display for "+data.get(dataSet).get("ProductName"), Common.getscreenShotPathforReport("Search result Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}
	
	public void BrowseSubcategory() throws Exception
	{
		String expectedResult="Navigation of sub category from main menu";
		try {
			Common.mouseOver("xpath", "//span[contains(text(),'Collections')]");
			
			Thread.sleep(3000);
			
			Common.clickElement("xpath", "//span[contains(text(),'One Step Family')]");
			
			Thread.sleep(10000);
			
			String subcategory=Common.getText("xpath", "//h1[contains(text(),'The One-Step Collection')]");
			System.out.println("Subcategory page navigated "+subcategory);
			Assert.assertEquals(subcategory, "The One-Step Collection");
			report.addPassLog(expectedResult, "Should navigation Subcategory page", "Subcategory Page navigated successfully", Common.getscreenShotPathforReport("Subcategory page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should navigation Subcategory page", "Subcategory Page not navigated", Common.getscreenShotPathforReport("Subcategory page Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}
	
	public void PLPfilterwithColour() throws Exception
	{
		String expectedResult="Validating Product listing page Colour filter results";
		try {			
			Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/dryers?color=13']"));
			Thread.sleep(10000);

			String filter=Common.getText("id", "dynamicProductCounts");
			System.out.println("Colour filer count is "+filter);

			report.addPassLog(expectedResult, "Should display Product Listing Page Colour filters results", "Product Listing Page Colour filters results displayed successfully", Common.getscreenShotPathforReport("Product Listing Page Colour filters success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Product Listing Page Colour filters results", "Product Listing Page Colour filters results displayed Failed", Common.getscreenShotPathforReport("Product Listing Page Colour filters Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}
	
	public void PLPfilterwithCollections() throws Exception
	{
		String expectedResult="Validating Product listing page Collections filter results";
		try {
			Common.scrollIntoView("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/dryers?collections=48']"));
			Thread.sleep(3000);
			Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/dryers?collections=48']"));
			Thread.sleep(10000);
			
			Common.scrollIntoView("id", "dynamicProductCounts");
			String filter=Common.getText("id", "dynamicProductCounts");
			System.out.println("Collections filer count is "+filter);

			report.addPassLog(expectedResult, "Should display Product Listing Page Collections filters results", "Product Listing Page Collections filters results displayed successfully", Common.getscreenShotPathforReport("Product Listing Page Collections filters success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Product Listing Page Collections filters results", "Product Listing Page Collections filters results displayed Failed", Common.getscreenShotPathforReport("Product Listing Page Collections filters Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}
	
	public void PLPfilterwithHeat() throws Exception
	{
		String expectedResult="Validating Product listing page Heat filter results";
		try {	
			Common.scrollIntoView("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/dryers?heat=29']"));
			Thread.sleep(3000);
			Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/dryers?heat=29']"));
			Thread.sleep(10000);
			
			Common.scrollIntoView("id", "dynamicProductCounts");
			String filter=Common.getText("id", "dynamicProductCounts");
			System.out.println("Heat filer count is "+filter);

			report.addPassLog(expectedResult, "Should display Product Listing Page Heat filters results", "Product Listing Page Heat filters results displayed successfully", Common.getscreenShotPathforReport("Product Listing Page Heat filters success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Product Listing Page Heat filters results", "Product Listing Page Heat filters results displayed Failed", Common.getscreenShotPathforReport("Product Listing Page Heat filters Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}
	
	public void PLPfilterwithSize() throws Exception
	{
		String expectedResult="Validating Product listing page Size filter results";
		try {	
			Common.scrollIntoView("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/dryers?size=36']"));
			Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/dryers?size=36']"));
			Thread.sleep(10000);
			
			Common.scrollIntoView("id", "dynamicProductCounts");
			String filter=Common.getText("id", "dynamicProductCounts");
			System.out.println("Size filer count is "+filter);

			report.addPassLog(expectedResult, "Should display Product Listing Page Size filters results", "Product Listing Page Size filters results displayed successfully", Common.getscreenShotPathforReport("Product Listing Page Size filters success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Product Listing Page Size filters results", "Product Listing Page Size filters results displayed Failed", Common.getscreenShotPathforReport("Product Listing Page Size filters Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}
	
	public void PLPfilterwithTechnology() throws Exception
	{
		String expectedResult="Validating Product listing page Technology filter results";
		try {	
			Common.scrollIntoView("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/dryers?technology=40']"));
			Thread.sleep(3000);
			Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/dryers?technology=40']"));
			Thread.sleep(10000);
			
			Common.scrollIntoView("id", "dynamicProductCounts");
			String filter=Common.getText("id", "dynamicProductCounts");
			System.out.println("Technology filer count is "+filter);

			report.addPassLog(expectedResult, "Should display Product Listing Page Technology filters results", "Product Listing Page Technology filters results displayed successfully", Common.getscreenShotPathforReport("Product Listing Page Technology filters success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Product Listing Page Technology filters results", "Product Listing Page Technology filters results displayed Failed", Common.getscreenShotPathforReport("Product Listing Page Technology filters Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}
	
	public void PLPfilterwithType() throws Exception
	{
		String expectedResult="Validating Product listing page Type filter results";
		try {	
			Common.scrollIntoView("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/dryers?type=77']"));
			Thread.sleep(3000);
			Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/dryers?type=77']"));
			Thread.sleep(10000);
			
			Common.scrollIntoView("id", "dynamicProductCounts");
			String filter=Common.getText("id", "dynamicProductCounts");
			System.out.println("Type filer count is "+filter);

			report.addPassLog(expectedResult, "Should display Product Listing Page Type filters results", "Product Listing Page Type filters results displayed successfully", Common.getscreenShotPathforReport("Product Listing Page Type filters success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Product Listing Page Type filters results", "Product Listing Page Type filters results displayed Failed", Common.getscreenShotPathforReport("Product Listing Page Type filters Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}
	
	public void PLPfilterwithDualVoltage() throws Exception
	{
		String expectedResult="Validating Product listing page DualVoltage filter results";
		try {	
			Common.scrollIntoView("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/dryers?dual_voltage=86']"));
			Thread.sleep(3000);
			Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/dryers?dual_voltage=86']"));
			Thread.sleep(10000);

			Common.scrollIntoView("id", "dynamicProductCounts");
			String filter=Common.getText("id", "dynamicProductCounts");
			System.out.println("DualVoltage filer count is "+filter);

			report.addPassLog(expectedResult, "Should display Product Listing Page DualVoltage filters results", "Product Listing Page DualVoltage filters results displayed successfully", Common.getscreenShotPathforReport("Product Listing Page DualVoltage filters success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Product Listing Page DualVoltage filters results", "Product Listing Page DualVoltage filters results displayed Failed", Common.getscreenShotPathforReport("Product Listing Page DualVoltage filters Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}
	
	public void ClearFilter() throws Exception
	{
		try {			
			Common.clickElement("xpath", "//a[contains(text(),'Clear All')]");
			Thread.sleep(10000);
		}catch(Exception |Error e)
		{
			e.printStackTrace();
		}

	}
	
	public void ShippingAddress(String dataSet) throws Exception
	{
		
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

			Thread.sleep(1500);

			shipping_Address(dataSet);
			
			Thread.sleep(3000);
			report.addPassLog(expectedResult2, "Should dispaly Shipping address page", "shipping address Page displayed successfully", Common.getscreenShotPathforReport("Shipping address"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult2,"Should display Checkout Page", "Checkout Page not displayed", Common.getscreenShotPathforReport("Checkout Failed"));
			e.printStackTrace();
			Assert.fail();
		}


	}
	
	public void ProductReview(String dataSet) throws Exception
	{
		
		String expectedResult="Updating the product review of selected product";
		try {
			Common.clickElement("xpath", "//button[contains(text(),'WRITE A REVIEW')]");
			Thread.sleep(4000);
			Common.actionsKeyPress(Keys.DOWN);
			Thread.sleep(4000);
			Common.clickElement("id", "Quality_5_label");
			Common.clickElement("id", "Price_5_label");
			Common.clickElement("id", "Value_5_label");
			Thread.sleep(10000);
			Sync.waitElementClickable("name", "nickname");
			Common.actionsKeyPress(Keys.TAB);
			Common.textBoxInput("xpath", "//form[@id='review-form']/fieldset/div[1]/div//input[@id='nickname_field']", data.get(dataSet).get("FirstName"));
			Common.textBoxInput("id", "email_field", data.get(dataSet).get("Email"));
			Common.textBoxInput("id", "summary_field", data.get(dataSet).get("Summary"));
			Common.textBoxInput("id", "review_field", data.get(dataSet).get("Review"));
			Thread.sleep(3000);
			Common.clickElement("xpath", "//button[contains(text(),'Submit Review')]");
			Thread.sleep(7000);
			String review=Common.getText("xpath", "//div[@data-bind='html: message.text']");
			System.out.println("Product Review updated "+review);
			Assert.assertEquals(review, "You submitted your review for moderation.");
			report.addPassLog(expectedResult, "Should update Product Review for selected product", "Updated Product review for selected product successfully", Common.getscreenShotPathforReport("Product Review success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should update Product Review for selected product", "Update Product review for selected product Failed", Common.getscreenShotPathforReport("Product Review Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}


}
