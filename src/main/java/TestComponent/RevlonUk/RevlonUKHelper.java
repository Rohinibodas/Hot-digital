package TestComponent.RevlonUk;

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
import Utilities.Utils;

import com.asprise.ocr.Ocr;


public class RevlonUKHelper {

	String datafile;
	ExcelReader excelData;
	static Automation_properties automation_properties = Automation_properties.getInstance();
	Map<String, Map<String, String>> data=new HashMap<>();
	static ExtenantReportUtils report;

	public  RevlonUKHelper(String datafile)
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
	
	public void acceptPrivacy()
	{
		Common.clickElementStale("xpath", "//button[text()='AGREE & PROCEED']");
	}
	
	public void navigateLogin() throws InterruptedException
	{
		String expectedResult="Naviagating to Login page";
		try {
			Sync.waitElementClickable(30, By.xpath("//a[@title='My Account']"));
			Common.findElement("xpath", "//a[@title='My Account']").click();
			Sync.waitElementClickable(30, By.xpath("//button[text()='Create an Account']"));
			Thread.sleep(2000);
			report.addPassLog(expectedResult, "Should display Login Page", "Login Page displayed successfully", Common.getscreenShotPathforReport("Login page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Login Page", "Login Page not display", Common.getscreenShotPathforReport("Login page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}	
	
	public void navigateAccountCreation() throws InterruptedException
	{
		String expectedResult="Naviagating to Account Creation page";
		try {
			Thread.sleep(3000);
			Sync.waitElementClickable(30, By.xpath("//button[text()='Create an Account']"));
			Common.findElement("xpath", "//button[text()='Create an Account']").click();
			report.addPassLog(expectedResult, "Should display Account Creation Page", "Account Creation Page displayed successfully", Common.getscreenShotPathforReport("Account Creation page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Account Creation Page", "Account Creation Page not displayed", Common.getscreenShotPathforReport("Account Creation page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}	

	public void CreateNewAccount(String dataSet) throws Exception
	{
		navigateLogin();
		navigateAccountCreation();
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
		navigateLogin();
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
			}while(i<3 && !Common.isElementDisplayed("xpath", "//span[contains(text(),'My Account')]")); 

			report.addPassLog(expectedResult, "Should display My Account Page with user details", "My Account Page with user details displayed successfully", Common.getscreenShotPathforReport("Login page with details success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display  My Account Page with user details", "My Account Page with user details not displayed", Common.getscreenShotPathforReport("Login page with details Failed"));
			Assert.fail();
		}
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
			Assert.fail();
		}
	}
	
	public void ValidateHomepagelogo() throws Exception
	{
		String expectedResult="Validating Home page logo and should lands on Home Page";
		try {
			Sync.waitElementClickable(30, By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"uk_en/']")));
			Common.findElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"uk_en/']")).click();
			report.addPassLog(expectedResult, "Should redirect to home page", "Redirected to Home Page successfully", Common.getscreenShotPathforReport("Home Page Logo success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should redirect to home page", "Not Redirected to Home Page", Common.getscreenShotPathforReport("Home Page Logo Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void ValidateCollections() throws Exception
	{
		String expectedResult="Validating Home page Megamenu Collections and should lands on Collections page";
		try {
			Sync.waitElementClickable(30, By.xpath("//a[@href='/collections/one-step-family']"));
			Common.mouseOver("xpath", "//a[@href='/collections/one-step-family']");
			//Common.findElement("xpath", "//a[@href='/collections/one-step-family']").click();
			
			Sync.waitElementClickable(30, By.xpath("//span[contains(text(),'One Step Family')]"));
			Common.clickElement("xpath", "//span[contains(text(),'One Step Family')]");
			
			Thread.sleep(3000);
			
			String Collections=Common.getText("xpath", "//h1[contains(text(),'The One-Step Collection')]");
			Assert.assertEquals(Collections, "The One-Step Collection");
			report.addPassLog(expectedResult, "Should redirect to Collections page", "Redirected to Collections page successfully", Common.getscreenShotPathforReport("Collections page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should redirect to Collections page", "Not Redirected to Collections page", Common.getscreenShotPathforReport("Collections page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void ValidateDryers() throws Exception
	{
		String expectedResult="Validating Home page Megamenu Dryers and should lands on Dryers page";
		try {
			Sync.waitElementClickable(30, By.xpath("//span[contains(text(),'Dryers')]"));
			Common.findElement("xpath", "//span[contains(text(),'Dryers')]").click();
			Thread.sleep(2000);
			
			String Dryers=Common.getText("xpath", "//span[contains(text(),'Dryers')]");
			Assert.assertEquals(Dryers, "Dryers");
			report.addPassLog(expectedResult, "Should redirect to Dryers page", "Redirected to Dryers page successfully", Common.getscreenShotPathforReport("Dryers page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should redirect to Dryers page", "Not Redirected to Dryers page", Common.getscreenShotPathforReport("Dryers page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void ValidateStraighteners() throws Exception
	{
		String expectedResult="Validating Home page Megamenu straighteners and should lands on straighteners page";
		try {
			Sync.waitElementClickable(30, By.xpath("//span[contains(text(),'Straighteners')]"));
			Common.findElement("xpath", "//span[contains(text(),'Straighteners')]").click();
			Thread.sleep(2000);
			
			String straighteners=Common.getText("xpath", "//span[contains(text(),'Straighteners')]");
			Assert.assertEquals(straighteners, "Straighteners");
			report.addPassLog(expectedResult, "Should redirect to straighteners page", "Redirected to straighteners page successfully", Common.getscreenShotPathforReport("straighteners page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should redirect to straighteners page", "Not Redirected to straighteners page", Common.getscreenShotPathforReport("straighteners page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void ValidateCurlingIrons() throws Exception
	{
		String expectedResult="Validating Home page Megamenu straighteners and should lands on straighteners page";
		try {
			Sync.waitElementClickable(30, By.xpath("//span[contains(text(),'Curling Irons')]"));
			Common.findElement("xpath", "//span[contains(text(),'Curling Irons')]").click();
			Thread.sleep(2000);
			
			String curlingiron=Common.getText("xpath", "//span[contains(text(),'Curling Irons')]");
			Assert.assertEquals(curlingiron, "Curling Irons");
			report.addPassLog(expectedResult, "Should redirect to Curling Irons page", "Redirected to Curling Irons page successfully", Common.getscreenShotPathforReport("Curling Irons page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should redirect to Curling Irons page", "Not Redirected to Curling Irons page", Common.getscreenShotPathforReport("Curling Irons page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void ValidateHairBrushesElastics() throws Exception
	{
		String expectedResult="Validating Home page Megamenu Hair Brushes Elastics and should lands on Hair Brushes Elastics page";
		try {
			Sync.waitElementClickable(30, By.xpath("//span[contains(text(),'Hair Brushes & Elastics')]"));
			Common.findElement("xpath", "//span[contains(text(),'Hair Brushes & Elastics')]").click();
			Thread.sleep(2000);
			
			String Burshes=Common.getText("xpath", "//h1[@id='page-title-heading']");
			Assert.assertEquals(Burshes, "Brushes");
			report.addPassLog(expectedResult, "Should redirect to Hair Brushes Elastics page", "Redirected to Hair Brushes Elastics page successfully", Common.getscreenShotPathforReport("HairBrushesElastics page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should redirect to Hair Brushes Elastics page", "Not Redirected to Hair Brushes Elastics page", Common.getscreenShotPathforReport("HairBrushesElastics page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void ValidateSpecialty() throws Exception
	{
		String expectedResult="Validating Home page Megamenu straighteners and should lands on straighteners page";
		try {
			Sync.waitElementClickable(30, By.xpath("//span[contains(text(),'Specialty')]"));
			Common.findElement("xpath", "//span[contains(text(),'Specialty')]").click();
			Thread.sleep(2000);
			
			String Speciality=Common.getText("xpath", "//span[contains(text(),'Specialty')]");
			Assert.assertEquals(Speciality, "Specialty");
			report.addPassLog(expectedResult, "Should redirect to Specialty page", "Redirected to Specialty page successfully", Common.getscreenShotPathforReport("Specialty page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should redirect to Specialty page", "Not Redirected to Specialty page", Common.getscreenShotPathforReport("Specialty page Failed"));
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
			Assert.assertTrue(Common.isElementDisplayed("id", "nosearchresultcount"));
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
			if(Common.isElementDisplayed("xpath", "(//div[@class='product-item-info']/div/div/form//button[@title='Add to Cart'])[1]")) {
				Sync.waitElementPresent("xpath", "(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[1]");
				Common.clickElement("xpath", "(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[1]");
			}else if(Common.isElementDisplayed("xpath", "(//div[@class='product-item-info']/div/div/form//button[@title='Add to Cart'])[2]")) {
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
		//Common.clickElement("xpath", "//select[@title='State/Province']");
		Common.textBoxInput("name", "region", data.get(dataSet).get("Region"));
		//Common.dropdown("name", "region_id", SelectBy.TEXT, data.get(dataSet).get("Region"));
		//Common.dropdown("xpath", "//div[@name='shippingAddress.region_id']/div//select[@name='region_id']", SelectBy.TEXT, data.get(dataSet).get("Region"));

		Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));

		//Common.dropdown("xpath", "//select[@name='country_id']", SelectBy.TEXT, data.get(dataSet).get("Country"));

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
	
	public void navigateCMSLink() throws Exception
	{
		String expectedResult="Lands On Home page footer links";
		try {
			Sync.waitElementPresent("xpath", "//div[@class='copy-right']");
			Common.scrollIntoView("xpath", "//div[@class='copy-right']");
			Thread.sleep(1000);
			Common.clickElement("xpath", "//h4[contains(text(),'Company')]");
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
			Sync.waitElementPresent("xpath", "//a[@href='about-us']");
			Common.scrollToElementAndClick("xpath", "//a[@href='about-us']");

			Thread.sleep(1000);

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
				Thread.sleep(7000);
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
				Thread.sleep(7000);
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
	
	
}
