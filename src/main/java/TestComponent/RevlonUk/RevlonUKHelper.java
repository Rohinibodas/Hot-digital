package TestComponent.RevlonUk;

import static org.testng.Assert.fail;

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
import Utilities.MailAPI;
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
	
	public void navigateMyAccount() throws InterruptedException
	{
		String expectedResult="Naviagating to Login page";
		try {
			Sync.waitElementClickable(30, By.xpath("//div[@class='authorization-link header_right user-dropdown m-hide non_ipad']"));
			Common.findElement("xpath", "//div[@class='authorization-link header_right user-dropdown m-hide non_ipad']").click();
			Thread.sleep(3000);
			//Sync.waitElementClickable(30, By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"customer/account/']")));
			Common.findElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"customer/account/']")).click();
			
			report.addPassLog(expectedResult, "Should display My Account Page", "My Account Page displayed successfully", Common.getscreenShotPathforReport("My Account Page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display My Account Page", "My Account Page not display", Common.getscreenShotPathforReport("My Account Page Failed"));
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
			Sync.waitElementClickable(30, By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"customer/account/forgotpassword/']")));
			Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"customer/account/forgotpassword/']"));
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
	
	public void forgotPasswordEmailtrigger(String dataSet) throws Exception
	{
		String expectedResult="Forgot Password email trigger for Registered User";
		Thread.sleep(2000);
		try {
			String expectedResult1="Landed on Login page";
			Sync.waitElementClickable(30, By.xpath("//a[@title='My Account']"));
			Common.findElement("xpath", "//a[@title='My Account']").click();
			report.addPassLog(expectedResult1, "Should display login page", "Login page displayed successfully", Common.getscreenShotPathforReport("Login page"));
			Sync.waitElementClickable(30, By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"customer/account/forgotpassword/']")));
			Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"customer/account/forgotpassword/']"));
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
			Assert.assertTrue(hm.get("link").contains("createPassword"));
			//System.out.println(hm.get("body"));
			report.addPassLog(expectedResult, "Should display Forgot Password email trigger Success message", "Forgot Password email triggered successfully", Common.getscreenShotPathforReport("Forgot Password email trigger Success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Forgot Password email trigger Success message", "Forgot Password email triggered  not successfully", Common.getscreenShotPathforReport("Forgot Password email trigger Failed"));
			e.printStackTrace();
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
	
	public void NavigateNewsLetterSubscription() throws Exception
	{
		String expectedResult="Navigating News Letter Subscription and should lands on News Letter Subscription";
		try {
			Sync.waitElementClickable(30, By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"newsletter/manage/']")));
			Common.findElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"newsletter/manage/']")).click();
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
			report.addPassLog(expectedResult, "Should able to NewsLetter UnSubscription", "should select to NewsLetter UnSubscription successfully", Common.getscreenShotPathforReport("NewsLetter UnSubscription success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should able to NewsLetter UnSubscription", "Not selected to NewsLetter UnSubscription", Common.getscreenShotPathforReport("NewsLetter UnSubscription Failed"));
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
			/*if(Common.isElementDisplayed("xpath", "(//div[@class='product-item-info']/div/div/form//button[@title='Add to Bag'])[1]")) {
				Sync.waitElementPresent("xpath", "(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[1]");
				Common.clickElement("xpath", "(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[1]");
			}else if(Common.isElementDisplayed("xpath", "(//div[@class='product-item-info']/div/div/form//button[@title='Add to Bag'])[2]")) {
				Sync.waitElementPresent("xpath", "(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[2]");
				Common.clickElement("xpath", "(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[2]");
			}else {
				Sync.waitElementPresent("xpath", "(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[3]");
				Common.clickElement("xpath", "(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[3]");
			}*/
			
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
	public void twoproductsaddtocart(){
		try {
			
			Common.scrollIntoView("xpath", "(//span[contains(text(),'Add to Bag')])[5]");
					String s = Common.getText("xpath", "(//div[@class='rev_product']//h2[@class='title'])[5]");
					System.out.println(s);
					Common.javascriptclickElement("xpath", "(//span[contains(text(),'Add to Bag')])[5]");
					Thread.sleep(3000);
					
					Common.actionsKeyPress(Keys.HOME);
					Common.isElementDisplayed("xpath", "//div[@data-bind='html: message.text']");
					String s1 = Common.getText("xpath", "//div[@data-bind='html: message.text']");
				System.out.println(s1);
				//Common.assertionCheckwithReport(s1.contains("Turbo Ionic Hair Dryer"), "Should add products to cart from plp", "products added successfully", "failed to add products to cart");

					if(Common.isElementDisplayed("xpath", "//div[@class='quickcart-top']")) {
						Common.clickElement("xpath", "(//button[@type='button'])[1]");
					}
					
					
					Common.scrollIntoView("xpath", "(//span[contains(text(),'Add to Bag')])[9]");
					String s2 = Common.getText("xpath", "(//div[@class='rev_product']//h2[@class='title'])[9]");
					System.out.println(s2);
					Common.javascriptclickElement("xpath", "(//span[contains(text(),'Add to Bag')])[9]");
					Thread.sleep(3000);
					
					Common.actionsKeyPress(Keys.HOME);
					 Common.isElementDisplayed("xpath", "//div[@data-bind='html: message.text']");
					
					 String s3 = Common.getText("xpath", "//div[@data-bind='html: message.text']");
						System.out.println(s3);
					//Common.assertionCheckwithReport(s3.contains("Styler"), "Should add products to cart from plp", "products added successfully", "failed to add products to cart");
		   Sync.waitElementPresent("xpath", "//div[contains(@class,'minicart-wrapper')]");
Common.clickElement("xpath", "//div[contains(@class,'minicart-wrapper')]");
						Sync.waitElementPresent("xpath", "//a[contains(@class,'action viewcart')]/span");
						Common.clickElement("xpath", "//a[contains(@class,'action viewcart')]/span");
						
					
					
					
				}catch(Exception | Error e) {
					report.addFailedLog("should add two products to the cart", "products not added to cart", Common.getscreenShotPathforReport("failed to add the products"));
					Assert.fail();
				}
			}	
	
	
	
	
	
	public void closeCurrentTabandSwitchtoHome() throws Exception
	{
		Common.closeCurrentWindow();
		Common.switchToFirstTab();
		Thread.sleep(3000);
	}
	
	public void AmazonHatchproduct() throws Exception
	{
		String expectedResult="Amazon Hatch implementation process from Revlon site";
		try {
			Thread.sleep(3000);
			if(Common.isElementDisplayed("xpath", "//a[@id='Amazon.co.uk']")) {
				Common.clickElement("xpath", "//a[@id='Amazon.co.uk']");
			}else {
				Common.clickElement("xpath", "//span[contains(text(),'More dealers')]");
				Thread.sleep(3000);
				Common.clickElement("xpath", "//a[@id='Amazon.co.uk']");
			}
			
			Thread.sleep(5000);
			
			Common.switchWindows();
			
			Thread.sleep(5000);
			if(Common.isElementDisplayed("xpath", "//input[@id='sp-cc-accept']")) {
				System.out.println("Cookies pop up displayed");
				Common.clickElementStale("xpath", "//input[@id='sp-cc-accept']");
			}else {
				System.out.println("Cookies pop up not displayed");
			}
			
			String Productname=Common.getText("xpath", "//span[@id='productTitle']");
			System.out.println(Productname);
			
			Thread.sleep(3000);
			
			report.addPassLog(expectedResult, "Should display Amazon Hatch implementation Page", "Amazon Hatch implementation Page display successfully", Common.getscreenShotPathforReport("Amazon Hatch implementation success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Amazon Hatch implementation Page", "Amazon Hatch implementation Page not displayed", Common.getscreenShotPathforReport("Amazon Hatch implementation Failed"));
			e.printStackTrace();
			Assert.fail();
		}
		this.closeCurrentTabandSwitchtoHome();

	}
	
	public void BootsHatchproduct() throws Exception
	{
		String expectedResult="Boots Hatch implementation process from Revlon site";
		try {
			Thread.sleep(3000);
			if(Common.isElementDisplayed("xpath", "//a[@id='Boots']")) {
				Common.clickElement("xpath", "//a[@id='Boots']");
			}else {
				Common.clickElement("xpath", "//span[contains(text(),'More dealers')]");
				Thread.sleep(3000);
				Common.clickElement("xpath", "//a[@id='Boots']");
			}
			
			
			Thread.sleep(5000);
			
			Common.switchWindows();
			
			Thread.sleep(5000);
			
			String Productname=Common.getText("xpath", "//div[@id='estore_product_title']");
			System.out.println(Productname);
			
			Thread.sleep(10000);
			if(Common.isElementDisplayed("xpath", "//button[@id='onetrust-accept-btn-handler']")) {
				System.out.println("Cookies pop up displayed");
				Common.clickElementStale("xpath", "//button[@id='onetrust-accept-btn-handler']");
			}else {
				System.out.println("Cookies pop up not displayed");
			}
			Thread.sleep(2000);
			
			report.addPassLog(expectedResult, "Should display Boots Hatch implementation Page", "Boots Hatch implementation Page display successfully", Common.getscreenShotPathforReport("Boots Hatch implementation success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Boots Hatch implementation Page", "Boots Hatch implementation Page not displayed", Common.getscreenShotPathforReport("Boots Hatch implementation Failed"));
			e.printStackTrace();
			Assert.fail();
		}
		this.closeCurrentTabandSwitchtoHome();

	}
	
	public void navigateMinicart() throws Exception
	{
		String expectedResult="Product adding to mini cart";
		try {
			Thread.sleep(4000);
			Common.actionsKeyPress(Keys.ARROW_DOWN);
			Sync.waitElementPresent("xpath", "//button[@id='product-addtocart-button']");
			Common.clickElement("xpath", "//button[@id='product-addtocart-button']");
			Thread.sleep(7000);
			String qty=Common.getText("xpath", "//span[@class='qty']");
			System.out.println("YOUR BAG - "+qty);
			Thread.sleep(6000);
			/*if(Common.isElementDisplayed("xpath", "//div[@class='actions action-viewcart bottom-aligned']/div//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"checkout/cart/']"))) {
				Common.clickElement("xpath", "//div[@class='actions action-viewcart bottom-aligned']/div//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"checkout/cart/']"));
				
			}else {
				Common.clickElement("xpath", "//div[@class='actions action-viewcart bottom-aligned']/div/a//div[@class='actions action-viewcart bottom-aligned']/div/a");
				Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"checkout/cart/']"));
				Thread.sleep(3000);
				Common.clickElement("xpath", "//div[@class='actions action-viewcart bottom-aligned']/div//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"checkout/cart/']"));
			
			}*/
			//Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"uk_en/checkout/cart/']"));
			Common.clickElement("xpath", "//a[@class='action viewcart hvr-sweep-to-right rev-checkout-btn rev-cart-view']");
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
			Common.actionsKeyPress(Keys.PAGE_DOWN);			
			Sync.waitElementPresent("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"checkout/']"));
			Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"checkout/']"));
			Thread.sleep(1000);
			Common.isElementDisplayed("xpath", "//div[contains(text(),'Shipping Address')]");
			Thread.sleep(2000);
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
		String expectedResult="payment page of Register user";
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
			report.addPassLog(expectedResult, "Should display Payment Page", "Payment Page display successfully", Common.getscreenShotPathforReport("Checkout page success"));
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
		/*Sync.waitElementPresent("name", "region");
		Common.clickElement("name", "region");
		Thread.sleep(4000);*/
		//Sync.waitElementPresent("name", "region");
		//Common.clickElement("xpath", "//select[@title='State/Province']");
		//Common.textBoxInput("name", "region", data.get(dataSet).get("Region"));
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
			
			Common.scrollIntoView("xpath", "//div[contains(text(),'Payment Method')]");
			Thread.sleep(3000);
			Common.clickElement("xpath", "//input[@id='agreement_adyen_cc_1']");
			Thread.sleep(3000);
			Common.clickElement("xpath", "//button[@title='Place Order']");
			Thread.sleep(3000);

			//String sucessMessage=Common.getText("xpath", "//h1[@class='page-title']");
			String sucessMessage=Common.getText("xpath", "//h1[contains(text(),'Thank you for your purchase')]");
			System.out.println(sucessMessage);
			/*String orderid=Common.getText("xpath", "//a[@class='order-number']");
			System.out.println(orderid);*/
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
	
	public void navigateMyorders() throws Exception
	{
		String expectedResult="Lands On My Order Page & Should display list of orders";
		try {
			Thread.sleep(5000);
			Sync.waitElementClickable(30, By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"sales/order/history/']")));
			Common.findElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"sales/order/history/']")).click();
			Thread.sleep(3000);
			
			String Order=Common.getText("xpath", "//*[@id='my-orders-table']/tbody/tr[1]/td[1]");
			System.out.println(Order);
			
			report.addPassLog(expectedResult, "Should display My Order page with List of orders", "My Order page with List of orders display successfully", Common.getscreenShotPathforReport("MyOrder Page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display My Order page with List of orders", "My Order page with List of orders not displayed", Common.getscreenShotPathforReport("MyOrder Page Failed"));
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
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void navigateAboutUs() throws Exception
	{
		String expectedResult="Lands on About Us page";
		try {
			
			Common.actionsKeyPress(Keys.UP);
			
			//Sync.waitElementPresent("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"uk_en/about-us/"));
			//Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"uk_en/about-us/"));
			Common.clickElement("xpath", "//a[contains(text(),'About')]");
			Thread.sleep(1000);

			String s=Common.getText("xpath", "//span[contains(text(),'ABOUT US')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "ABOUT US");

			report.addPassLog(expectedResult, "Should display ABOUT US Page", "ABOUT US Page display successfully", Common.getscreenShotPathforReport("ABOUT US page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display ABOUT US Page", "ABOUT US Page not displayed", Common.getscreenShotPathforReport("ABOUT US Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void navigateTermsAndConditions() throws Exception
	{
		String expectedResult="Lands on Terms & Conditions page";
		try {
			Sync.waitElementPresent("xpath", "//a[contains(text(),'Terms & Conditions')]");
			Common.scrollToElementAndClick("xpath", "//a[contains(text(),'Terms & Conditions')]");

			Thread.sleep(1000);
			
			String s=Common.getText("xpath", "//span[contains(text(),'Terms & Conditions')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "Terms & Conditions");
			report.addPassLog(expectedResult, "Should display Terms & Conditions Page", "Terms & Conditions Page display successfully", Common.getscreenShotPathforReport("Terms & Conditions page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Terms & Conditions Page", "Terms & Conditions Page not displayed", Common.getscreenShotPathforReport("Terms & Conditions Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void navigatePrivacyPolicy() throws Exception
	{
		String expectedResult="Lands on Privacy policy page";
		try {
			Sync.waitElementPresent("xpath", "//a[contains(text(),'Privacy Policy')]");
			Common.scrollToElementAndClick("xpath", "//a[contains(text(),'Privacy Policy')]");

			Thread.sleep(1000);

			String s=Common.getText("xpath", "//span[contains(text(),'Privacy Policy')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "Privacy Policy");

			report.addPassLog(expectedResult, "Should display Privacy Policy Page", "Privacy Policy Page display successfully", Common.getscreenShotPathforReport("Privacy Policy page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Privacy Policy Page", "Privacy Policy Page not displayed", Common.getscreenShotPathforReport("Privacy Policy Failed"));
			Assert.fail();
		}
	}
	
	public void navigateCookiePolicy() throws Exception
	{
		String expectedResult="Lands on Cookie Policy page";
		try {
			Sync.waitElementPresent("xpath", "//a[contains(text(),'Cookie Policy')]");
			Common.scrollToElementAndClick("xpath", "//a[contains(text(),'Cookie Policy')]");

			Thread.sleep(1000);

			String s=Common.getText("xpath", "//span[contains(text(),'Your Privacy')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "Your Privacy");

			report.addPassLog(expectedResult, "Should display Cookie Policy Page", "Cookie Policy Page display successfully", Common.getscreenShotPathforReport("Cookie Policy page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Cookie Policy Page", "Cookie Policy Page not displayed", Common.getscreenShotPathforReport("Cookie Policy Failed"));
			Assert.fail();
		}
	}

	public void navigateFAQ() throws Exception
	{
		String expectedResult="Lands on FAQ page";
		try {
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//a[contains(text(),'FAQ')]");
			Common.scrollToElementAndClick("xpath", "//a[contains(text(),'FAQ')]");

			Thread.sleep(2000);

			String s=Common.getText("xpath", "//div[@class='breadcrumbs']/ul/li[2]");
			//String s=Common.getText("xpath", "//span[contains(text(),'Faqs')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "frequently asked questions");

			report.addPassLog(expectedResult, "Should display FAQ Page", "FAQ Page display successfully", Common.getscreenShotPathforReport("FAQ page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display FAQ Page", "FAQ Page not displayed", Common.getscreenShotPathforReport("FAQ Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}
	
	public void navigateTermsofSale() throws Exception
	{
		String expectedResult="Lands on Terms Of Sale page";
		try {
			Sync.waitElementPresent("xpath", "//a[contains(text(),'Terms of sale')]");
			Common.scrollToElementAndClick("xpath", "//a[contains(text(),'Terms of sale')]");

			Thread.sleep(1000);

			String s=Common.getText("xpath", "//span[contains(text(),'Terms of Sale')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "Terms of Sale");

			report.addPassLog(expectedResult, "Should display Terms Of Sale Page", "Terms Of Sale Page display successfully", Common.getscreenShotPathforReport("Terms Of Sale page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Terms Of Sale Page", "Terms Of Sale Page not displayed", Common.getscreenShotPathforReport("Terms Of Sale Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void navigateShippingAndReturns() throws Exception
	{
		String expectedResult="Lands on Shipping & Returns page";
		try {
			Sync.waitElementPresent("xpath", "//a[contains(text(),'Shipping & Returns')]");
			Common.scrollToElementAndClick("xpath", "//a[contains(text(),'Shipping & Returns')]");

			Thread.sleep(1000);

			String s=Common.getText("xpath", "//span[contains(text(),'Shipping & Returns')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "Shipping & Returns");

			report.addPassLog(expectedResult, "Should display Shipping & Returns Page", "Shipping & Returns Page display successfully", Common.getscreenShotPathforReport("Shipping & Returns page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Shipping & Returns Page", "Shipping & Returns Page not displayed", Common.getscreenShotPathforReport("Shipping & Returns Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}
	
	public void navigateWarranty() throws Exception
	{
		String expectedResult="Lands on Warranty page";
		try {
			Sync.waitElementPresent("xpath", "//a[contains(text(),'Warranty')]");
			Common.scrollToElementAndClick("xpath", "//a[contains(text(),'Warranty')]");

			Thread.sleep(1000);

			String s=Common.getText("xpath", "//span[contains(text(),'Warranty')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "Warranty");

			report.addPassLog(expectedResult, "Should display Warranty Page", "Warranty Page display successfully", Common.getscreenShotPathforReport("Warranty page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Warranty Page", "Warranty Page not displayed", Common.getscreenShotPathforReport("Warranty Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}
		
	public void navigateContactUs() throws Exception
	{
		String expectedResult="Lands on Contact page";
		try {
			Sync.waitElementPresent("xpath", "//a[contains(text(),'Contact')]");
			Common.scrollToElementAndClick("xpath", "//a[contains(text(),'Contact')]");

			Thread.sleep(1000);

			String s=Common.getText("xpath", "//span[contains(text(),'Contact Us')]");
			System.out.println(s +" navigated successfully");
			Assert.assertEquals(s, "Contact Us");
			
			report.addPassLog(expectedResult, "Should display Contact Page", "Contact Page display successfully", Common.getscreenShotPathforReport("Contact page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Contact Page", "Contact Page not displayed", Common.getscreenShotPathforReport("Contact Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}
	
	public void ContactUsform(String dataSet) throws Exception
	{
		String expectedResult="Contact Us Form submited";
		
		Common.textBoxInput("name", "name", data.get(dataSet).get("FirstName"));
		Common.textBoxInput("name", "email", data.get(dataSet).get("Email"));
		Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
		Common.textBoxInput("name", "comment", data.get(dataSet).get("Message"));
		report.addPassLog(expectedResult, "Should dispaly Contact us page with data", "Contact Us Page with data displayed successfully", Common.getscreenShotPathforReport("Contact Us Page with data"));
		Thread.sleep(15000);
		Common.clickElement("xpath", "//button[@title='Submit']");
		Thread.sleep(5000);
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
			String s=Common.getText("xpath", "//div[@data-bind='html: message.text']");
			System.out.println(s);
			System.out.println("Contact us success page Test cases passed successfully");

			Assert.assertEquals(s, "Thanks for contacting us. We'll respond to you very soon");

			report.addPassLog(expectedResult, "Should display Product Registration success Page", "Product Registration success Page display successfully", Common.getscreenShotPathforReport("Product Registration success page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Product Registration success Page", "Product Registration success Page not displayed", Common.getscreenShotPathforReport("Product Registration success Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}

	
	public void headLinks(String dataSet) throws Exception{
		String expectedResult="Header Link validations";
		String Headerlinks=data.get(dataSet).get("HeaderNames");
		String[] headers=Headerlinks.split(",");
		for(int i=0;i<headers.length;i++){
			Sync.waitElementClickable("xpath", "//span[text()='"+headers[i]+"']");
			Common.clickElement("xpath", "//span[text()='"+headers[i]+"']");
			Thread.sleep(3000);
			System.out.println(Common.getPageTitle());
			report.addPassLog(expectedResult, "Should display "+headers[i]+" success Page", ""+headers[i]+" Page display successfully", Common.getscreenShotPathforReport("Product Registration success page success"));
			
		}
		
	}
	
	public void InstaGramArticle() throws Exception{
		String expectedResult="Instagram article page selection";
		try {
			Sync.waitElementPresent("xpath", "//div[@class='copy-right']");
			Common.scrollIntoView("xpath", "//div[@class='copy-right']");
			Thread.sleep(2000);
			Common.clickElement("xpath", "//a[@href='https://www.instagram.com/revlonhairtoolsuk/']");
			Thread.sleep(2000);
			Common.switchToSecondTab();
		   	Thread.sleep(10000);
			if(Common.isElementDisplayed("xpath", "//h1[contains(text(),'Instagram')]")) {
				String s=Common.getText("xpath", "//h1[contains(text(),'Instagram')]");
			   	System.out.println(s);
			   	Assert.assertEquals(s, "Instagram");
			}else {
				String s=Common.getText("xpath", "//h2[contains(text(),'revlonhairtoolsuk')]");
			   	System.out.println(s);
			   	Assert.assertEquals(s, "revlonhairtoolsuk");
			}
			report.addPassLog(expectedResult, "Should display Instagram page", "Instagram page display successfully", Common.getscreenShotPathforReport("Instagram page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Instagram page", "Instagram page not displayed", Common.getscreenShotPathforReport("Instagram page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
		Common.closeCurrentWindow();
		Common.switchToFirstTab();
	}
	
	public void FacebookArticle() throws Exception{
		String expectedResult="Facebook article page selection";
		try {
			Common.clickElement("xpath", "//a[@href='https://www.facebook.com/RevlonHairToolsUK/']");
			Thread.sleep(2000);
			Common.switchToSecondTab();
		   	Thread.sleep(15000);
		   	//String s=Common.getText("xpath", "//a[@href='https://www.facebook.com/revlonhairtools/']");
		   	String s=Common.getText("xpath", "(//span[contains(text(),'Revlon Hair Tools_UK')])[1]");
		   	System.out.println(s);
		   	Assert.assertEquals(s, "Revlon Hair Tools_UK");
			report.addPassLog(expectedResult, "Should display Facebook page", "Facebook page display successfully", Common.getscreenShotPathforReport("Facebook page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Facebook page", "Facebook page not displayed", Common.getscreenShotPathforReport("Facebook page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
		Common.closeCurrentWindow();
		Common.switchToFirstTab();
	}
	
	public void youtubeArticle() throws Exception{
		String expectedResult="Youtube article page selection";
		try {
			Common.clickElement("xpath", "//a[@href='https://www.youtube.com/user/RevlonHairTools']");
			Thread.sleep(2000);
			Common.switchToSecondTab();
		   	Thread.sleep(13000);
		   	String s=Common.getText("xpath", "(//*[contains(text(),'Revlon Hair Tools')])[1]");
		   	System.out.println(s);
			Assert.assertEquals(s, "Revlon Hair Tools");
			report.addPassLog(expectedResult, "Should display Youtube page", "Youtube page display successfully", Common.getscreenShotPathforReport("Youtube page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Youtube page", "Youtube page not displayed", Common.getscreenShotPathforReport("Youtube page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
		Common.closeCurrentWindow();
		Common.switchToFirstTab();

	}
	
	public void pinterestArticle() throws Exception{
		String expectedResult="Pinterest article page selection";
		try {
			Common.clickElement("xpath", "//a[@href='https://co.pinterest.com/revlonhairtools/']");
			Thread.sleep(2000);
			Common.switchToSecondTab();
		   	Thread.sleep(10000);
		   	String s=Common.getText("xpath", "//h1[contains(text(),'Revlon Hair Tools')]");
		   	System.out.println(s);
			Assert.assertEquals(s, "Revlon Hair Tools");
			report.addPassLog(expectedResult, "Should display Pinterest page", "Pinterest page display successfully", Common.getscreenShotPathforReport("Pinterest page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Pinterest page", "Pinterest page not displayed", Common.getscreenShotPathforReport("Pinterest page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
		Common.closeCurrentWindow();
		Common.switchToFirstTab();

	}
	
	public void FooterNewletterSubcription() throws Exception{
		String expectedResult="Newletter Subcription from footer";
		try {
			Sync.waitElementPresent("xpath", "//div[@class='copy-right']");
			Common.scrollIntoView("xpath", "//div[@class='copy-right']");
			Thread.sleep(2000);
			Common.textBoxInput("name", "email", Utils.getEmailid());
			Thread.sleep(2000);
			Common.clickElement("xpath", "//span[@class='checkmark']");
			Common.actionsKeyPress(Keys.UP);
			Thread.sleep(10000);
			Common.clickElement("xpath", "//button[@id='news-submission']");
			Thread.sleep(10000);
			if(Common.isElementDisplayed("xpath", "//div[@data-bind='html: message.text']")) {
				String s=Common.getText("xpath", "//div[@data-bind='html: message.text']");
				System.out.println(s);
				Assert.assertEquals(s, "Thank you for your subscription.");
				Thread.sleep(4000);
			}else {
				System.out.println("success message not dispalyed");
			}
			report.addPassLog(expectedResult, "Should display Successful message of subscription", "Successful message of subscription successfully", Common.getscreenShotPathforReport("Newsletter footer success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Successful message of subscription", "Successful message of subscription not displayed", Common.getscreenShotPathforReport("Newsletter footer Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}
	
	public void ProductReview(String dataSet) throws Exception{
		String expectedResult="Validate Product Review of "+data.get(dataSet).get("ProductName");
		try {
			Thread.sleep(3000);
			Common.scrollIntoView("xpath", "//a[@href='#product.info.reviewwidget']");
			Thread.sleep(3000);
			Common.clickElement("xpath", "//a[@href='#product.info.reviewwidget']");
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
			report.addPassLog(expectedResult, "Should display Successful message of subscription", "Successful message of subscription successfully", Common.getscreenShotPathforReport("Newsletter footer success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Successful message of subscription", "Successful message of subscription not displayed", Common.getscreenShotPathforReport("Newsletter footer Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}
	
	public void ValidateMyOrder() throws Exception
	{
		String expectedResult="Lands On My Order Page & Should display list of orders";
		try {
			Thread.sleep(5000);
			Sync.waitElementClickable(30, By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"sales/order/history/']")));
			Common.findElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"sales/order/history/']")).click();
			Thread.sleep(3000);
			
			String Order=Common.getText("xpath", "//h1[@class='page-title']");
			System.out.println(Order);
			Assert.assertEquals(Order, "My Orders");
			report.addPassLog(expectedResult, "Should display My Order page with List of orders", "My Order page with List of orders display successfully", Common.getscreenShotPathforReport("MyOrder Page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display My Order page with List of orders", "My Order page with List of orders not displayed", Common.getscreenShotPathforReport("MyOrder Page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void ValidateAddressBook() throws Exception
	{
		String expectedResult="Lands On AddressBook Page & Should display Billing address";
		try {
			Thread.sleep(5000);
			Sync.waitElementClickable(30, By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"customer/address/']")));
			Common.findElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"customer/address/']")).click();
			Thread.sleep(3000);
			String AddressBook=Common.getText("xpath", "//h1[@class='page-title']");
			System.out.println(AddressBook);
			Assert.assertEquals(AddressBook, "Address Book");
			report.addPassLog(expectedResult, "Should display AddressBook Page & Should display Billing address", "AddressBook Page & Should display Billing address display successfully", Common.getscreenShotPathforReport("Address Book Page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display AddressBook Page & Should display Billing address", "AddressBook Page & Should display Billing address not displayed", Common.getscreenShotPathforReport("Address Book Page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void ValidateAccountInformation() throws Exception
	{
		String expectedResult="Lands On Account information Page & Should display Account information";
		try {
			Thread.sleep(5000);
			Sync.waitElementClickable(30, By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"customer/account/edit/']")));
			Common.findElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"customer/account/edit/']")).click();
			Thread.sleep(3000);
			String Accountinformation=Common.getText("xpath", "//h1[@class='page-title']");
			System.out.println(Accountinformation);
			Assert.assertEquals(Accountinformation, "Edit Account Information");
			report.addPassLog(expectedResult, "Should display Account Information Page & Should display Account Information", "Account Information Page & Should display Account Information display successfully", Common.getscreenShotPathforReport("Account Information Page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Account Information Page & Should display Account Information", "Account Information Page & Should display Account Information not displayed", Common.getscreenShotPathforReport("Account Information Page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void ValidateProductReview() throws Exception
	{
		String expectedResult="Lands On Product Review Page & Should display Product Review";
		try {
			Thread.sleep(5000);
			Sync.waitElementClickable(30, By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"review/customer/']")));
			Common.findElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"review/customer/']")).click();
			Thread.sleep(3000);
			String ProductReview=Common.getText("xpath", "//h1[@class='page-title']");
			System.out.println(ProductReview);
			Assert.assertEquals(ProductReview, "My Product Reviews");
			report.addPassLog(expectedResult, "Should display Product Review Page & Should display Product Review", "Product Review Page & Should display Product Review display successfully", Common.getscreenShotPathforReport("Product Review Page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Product Review Page & Should display Product Review", "Product Review Page & Should display Product Review not displayed", Common.getscreenShotPathforReport("Product Review Page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void ValidateNewSubscription() throws Exception
	{
		String expectedResult="Lands On Newsletter Subscription Page & Should display Newsletter Subscription";
		try {
			Thread.sleep(5000);
			Sync.waitElementClickable(30, By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"newsletter/manage/']")));
			Common.findElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"newsletter/manage/']")).click();
			Thread.sleep(3000);
			String newletter=Common.getText("xpath", "//h1[@class='page-title']");
			System.out.println(newletter);
			Assert.assertEquals(newletter, "Newsletter Subscription");
			report.addPassLog(expectedResult, "Should display Newsletter Subscription Page & Should display Newsletter Subscription", "Newsletter Subscription Page & Should display Newsletter Subscription display successfully", Common.getscreenShotPathforReport("Newsletter Subscription Page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Newsletter Subscription Page & Should display Newsletter Subscription", "Newsletter Subscription Page & Should display Newsletter Subscription not displayed", Common.getscreenShotPathforReport("Newsletter Subscription Page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void Validatestocksubscription() throws Exception
	{
		String expectedResult="Lands On back to stock subscription Page & Should display back to stock subscription";
		try {
			Thread.sleep(5000);
			Sync.waitElementClickable(30, By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"xnotif/stock/index/']")));
			Common.findElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"xnotif/stock/index/']")).click();
			Thread.sleep(3000);
			String stocksubscription=Common.getText("xpath", "//h1[@class='page-title']");
			System.out.println(stocksubscription);
			Assert.assertEquals(stocksubscription, "My Back In Stock Subscriptions");
			report.addPassLog(expectedResult, "Should display back to stock subscription Page & Should display back to stock subscription", "back to stock subscription Page & Should display back to stock subscription display successfully", Common.getscreenShotPathforReport("back to stock subscription Page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display back to stock subscription Page & Should display back to stock subscription", "back to stock subscription Page & Should display back to stock subscription not displayed", Common.getscreenShotPathforReport("back to stock subscription Page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void ValidateProductquestion() throws Exception
	{
		String expectedResult="Lands On Product Question Page & Should display Product Question";
		try {
			Thread.sleep(5000);
			Sync.waitElementClickable(30, By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"productquestion/customer/']")));
			Common.findElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"productquestion/customer/']")).click();
			Thread.sleep(3000);
			String productQuestion=Common.getText("xpath", "//h1[@class='page-title']");
			System.out.println(productQuestion);
			Assert.assertEquals(productQuestion, "Product Questions");
			report.addPassLog(expectedResult, "Should display Product Question Page & Should display Product Question", "Product Question Page & Should display Product Question display successfully", Common.getscreenShotPathforReport("Product Question Page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Product Question Page & Should display Product Question", "Product Question Page & Should display Product Question not displayed", Common.getscreenShotPathforReport("Product Question Page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void shipping_Address_AVS(String dataSet) throws Exception
	{
		Common.textBoxInput("name", "firstname", data.get(dataSet).get("FirstName"));
		Common.textBoxInput("name", "lastname", data.get(dataSet).get("LastName"));
		Common.textBoxInput("name", "street[0]", data.get(dataSet).get("Street"));
		Common.textBoxInput("name", "city", data.get(dataSet).get("City"));
		Thread.sleep(10000);
		Common.actionsKeyPress(Keys.DOWN);
		Common.actionsKeyPress(Keys.ENTER);
		Thread.sleep(10000);
		Common.textBoxInput("name", "telephone",data.get(dataSet).get("phone"));
	}
	
	public void ValidateAVS(String dataSet) throws Exception
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

			shipping_Address_AVS("Guest_shipping");
			report.addPassLog(expectedResult2, "Should dispaly Shipping address page", "shipping address Page displayed successfully", Common.getscreenShotPathforReport("Shipping address"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Checkout Page", "Checkout Page not displayed", Common.getscreenShotPathforReport("Checkout Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void MyAccount_Address_AVS(String dataSet) throws Exception
	{
		Common.textBoxInput("name", "firstname", data.get(dataSet).get("FirstName"));
		Common.textBoxInput("name", "lastname", data.get(dataSet).get("LastName"));
		Common.textBoxInput("name", "telephone",data.get(dataSet).get("phone"));
		Common.textBoxInput("name", "street[]", data.get(dataSet).get("Street"));
		Common.textBoxInput("name", "city", data.get(dataSet).get("City"));
		Thread.sleep(10000);
		Common.actionsKeyPress(Keys.DOWN);
		Common.actionsKeyPress(Keys.ENTER);
		Thread.sleep(10000);
		
	}
	
	public void ValidateMyAccountAVS(String dataSet) throws Exception
	{
		String expectedResult="Validating My Account Address Book AVS";
		try {
			
			Common.clickElement("xpath", "//button[@title='Add New Address']");
			Thread.sleep(5000);
			MyAccount_Address_AVS("Guest_shipping");
			report.addPassLog(expectedResult, "Should dispaly New Address Book page", "New Address Book page displayed successfully", Common.getscreenShotPathforReport("Address Book Pass"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display New Address Book Page", "New Address Book Page not displayed", Common.getscreenShotPathforReport("Address Book Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void increaseproductquantity(String dataSet) {
 String expectedresult = "should Increase product quantity in View cart page";
 try {
	 Sync.waitElementClickable("xpath", "//select[@data-title='Qty']");
	// Common.clickElement("xpath", "//select[@data-title='Qty']");
	
	Common.dropdown("xpath", "//select[@data-title='Qty']", SelectBy.VALUE, data.get(dataSet).get("Quantity"));
	 Thread.sleep(2000);
	 Common.actionsKeyPress(Keys.ENTER);
	 Thread.sleep(3000);
	 
	 
	 String s = Common.getText("xpath", "//span[@data-ui-id='page-title-wrapper']");
	 System.out.println(s);
	String s1 = data.get(dataSet).get("Quantity"); 
	System.out.println(s1);
	Common.assertionCheckwithReport(s.contains(s1), "incresed product quantity will be displayed", expectedresult, "Failed to increase product quantity"); 
	 
	 
 }catch(Exception | Error e)
 {
	report.addFailedLog(expectedresult, "product quantity not increased", Common.getscreenShotPathforReport("failed to increase product quantity"));
	e.printStackTrace();
	Assert.fail();
 }		
		
		
		
	}

	public void Registerusernewshippingaddress(String dataSet) {
	
		String expectedresult = "should add new address in shipping address page";
		
		try {
			Common.scrollIntoView("xpath", "//button[@data-bind='click: showFormPopUp, visible: !isNewAddressAdded()']");
			Common.javascriptclickElement("xpath", "//button[@data-bind='click: showFormPopUp, visible: !isNewAddressAdded()']");
			Thread.sleep(3000);
			if(Common.isElementDisplayed("xpath", "//h1[@class='modal-title']"))
			{
				Thread.sleep(3000);
				Common.textBoxInput("xpath", "(//input[@name='firstname'])[2]", data.get(dataSet).get("FirstName"));
				Common.textBoxInput("xpath", "(//input[@name='lastname'])[2]", data.get(dataSet).get("LastName"));
				Common.textBoxInput("xpath", "(//input[@name='street[0]'])[2]", data.get(dataSet).get("Street"));
				Common.textBoxInput("xpath", "(//input[@name='city'])[2]", data.get(dataSet).get("City"));
				Common.textBoxInput("xpath", "(//input[@name='postcode'])[2]", data.get(dataSet).get("postcode"));
				Common.textBoxInput("xpath", "(//input[@name='telephone'])[2]", data.get(dataSet).get("phone"));
				Common.clickElement("xpath", "//input[@id='shipping-save-in-address-book']");
				Thread.sleep(3000);
				Common.clickElement("xpath", "//span[contains(text(),'Ship here')]");
				Thread.sleep(3000);
			if(Common.isElementDisplayed("xpath", "//div[text()='Address is not verified. Do you want to continue ?']")){
				
				Common.clickElement("xpath", "//span[text()='OK']");
			}
				report.addPassLog(expectedresult, "New address added and displayed in shipping address page", Common.getscreenShotPathforReport("Successfully added new address"));
	
			
			
			}
			
			
		}catch(Exception | Error e) {
			report.addFailedLog(expectedresult, "new Address not added", Common.getscreenShotPathforReport("failed to Add New Address"));
			e.printStackTrace();
			Assert.fail();
			
		}
		
		
	}

	public void NewBillingaddress(String dataSet) throws Exception {
	String 	expectedresult = "Should add new billing address";
		try {
			Thread.sleep(3000);
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		
	Sync.waitElementVisible("xpath", "//input[@name='billing-address-same-as-shipping']");
		Common.clickElement("xpath", "//input[@name='billing-address-same-as-shipping']");
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
		Common.textBoxInput("xpath", "(//input[@name='company'])", data.get(dataSet).get("Company"));
Common.actionsKeyPress(Keys.PAGE_DOWN);
		Common.textBoxInput("xpath", "(//input[@name='street[0]'])[2]", data.get(dataSet).get("Street"));
		Common.textBoxInput("xpath", "(//input[@name='city'])[2]", data.get(dataSet).get("City"));
		Common.textBoxInput("xpath", "(//input[@name='postcode'])[2]", data.get(dataSet).get("postcode"));
		Common.textBoxInput("xpath", "(//input[@name='telephone'])[2]", data.get(dataSet).get("phone"));
		Common.clickElement("xpath", "//span[text()='Update']");
		if(Common.isElementDisplayed("xpath", "//div[text()='Address is not verified. Do you want to continue ?']"));
		{
			Common.clickElement("xpath", "//span[text()='OK']");
		}
		Thread.sleep(3000);
		
		Common.actionsKeyPress(Keys.PAGE_UP);
		
		report.addFailedLog(expectedresult, "Billing address will be  added", Common.getscreenShotPathforReport("successfully add billing address"));
		} catch(Exception | Error e) {
			report.addFailedLog(expectedresult, "Billing address is not  added", Common.getscreenShotPathforReport("Failed to add billing address"));
			Assert.fail();
		}
		
	}

	public void HomepageAddtocart() {
	
		String expectedresult = "Should add product to cart";
		try {
			if(Common.isElementDisplayed("xpath", "//span[text()='My Account']")){
				Common.findElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"']")).click();
			
			}
			Thread.sleep(3000);
		Common.scrollIntoView("xpath", "(//div/button/span[text()='Add to Bag'])[1]");
		Common.getscreenShot("Product Displayed");
		String s1 = Common.getText("xpath", "//h2[text()='One-Step Original Hair Dryer and Volumiser']");
		System.out.println(s1);
		
		Common.javascriptclickElement("xpath", "(//div/button/span[text()='Add to Bag'])[1]");
		Thread.sleep(5000);
		Sync.waitElementPresent("xpath", "//span[text()='YOUR BAG - ']");
		Common.actionsKeyPress(Keys.HOME);
		Common.isElementDisplayedonPage(10, "xpath", "//div[@class='message-success success message']");
		String s = Common.getText("xpath", "//div[@class='message-success success message']");
		
		System.out.println(s);
		Thread.sleep(3000);
	Common.assertionCheckwithReport(s.contains("One-Step Original Hair Dryer and Volumiser"), "Added product to cart successfully", expectedresult, "failed to add Product from homepage ");
	Thread.sleep(2000);
	Sync.waitElementClickable(10, By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"checkout/cart/']")));
	Common.findElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"checkout/cart/']")).click();
	Thread.sleep(3000);
	Sync.waitElementClickable(10, By.xpath("(//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"checkout/cart/'])[2]")));
	Common.findElement("xpath", "(//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"checkout/cart/'])[2]")).click();
	Thread.sleep(4000);
	String s3 = Common.getCurrentURL();
	Common.assertionCheckwithReport(s3.contains("checkout/cart/"), "successfully Displayed checkout page ", expectedresult, "Failed to navigate checkout page");
	
		}catch(Exception | Error e) {
			report.addFailedLog(expectedresult, "Added product to cart successfully", Common.getscreenShotPathforReport("Failed to add product to cart"));

			Assert.fail();
		}
	}
	
}
