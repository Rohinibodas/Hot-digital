package TestComponent.Redesign_Hottools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.hssf.record.PageBreakRecord.Break;
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
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import TestLib.Automation_properties;
import TestLib.Common;
import TestLib.Common.SelectBy;
import TestLib.Sync;
import Utilities.ExcelReader;
import Utilities.ExtenantReportUtils;
import Utilities.Utils;

public class Redesign_HottoolsHelper {
	

	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data=new HashMap<>();
	static ExtenantReportUtils report;
	static Automation_properties automation_properties = Automation_properties.getInstance();
public Redesign_HottoolsHelper(String datafile){
	
	excelData=new ExcelReader(datafile);
	data=excelData.getExcelValue();
	this.data=data;
	if(Utilities.TestListener.report==null)
	{
		report=new ExtenantReportUtils("Redesign_Hottools");
		report.createTestcase("Redesign_HottoolsTestCases");
	}else{
		this.report=Utilities.TestListener.report;
	}

	
}


public void closeCookiesbanner() throws Exception{
	Sync.waitElementPresent("xpath", "//button[@class='truste-button truste-required-btn']");

	Common.clickElement("xpath", "//button[@class='truste-button truste-required-btn']");
}

public void agreeCookiesbanner() throws Exception{
	Thread.sleep(8000);
	
	if(Common.isElementDisplayed("xpath", "//button[@class='truste-button truste-accpt-btn']")) {
		Sync.waitElementPresent("xpath", "//button[@class='truste-button truste-accpt-btn']");
		Common.clickElement("xpath", "//button[@class='truste-button truste-accpt-btn']");
	}else {
		System.out.println("Cookies pop up not displayed");
	}
	
}

public void NewsletterPopUp() throws Exception{
	Thread.sleep(8000);
	Common.refreshpage();
	if(Common.isElementDisplayed("xpath", "//div[@id='wpn-lightbox-close-newsletter']")) {
		Sync.waitElementPresent("xpath", "//div[@id='wpn-lightbox-close-newsletter']");
		Common.clickElement("xpath", "//div[@id='wpn-lightbox-close-newsletter']");
	}else {
		System.out.println("Newsletter pop up not displayed");
	}
	
}



public void AccountCreation(String dataSet) throws Exception{
	
	String expectedResult="Account creation for Retail user";
			
	try{
		
	Sync.waitElementPresent("xpath", "(//a[@class='account-link top-link'])[2]");
	Common.clickElement("xpath", "(//a[@class='account-link top-link'])[2]");
	
	Thread.sleep(3000);
	
	Common.actionsKeyPress(Keys.PAGE_DOWN);
	
	
	Sync.waitElementPresent("xpath", "(//span[contains(text(), 'Create an Account')])[1]");
	Common.clickElement("xpath", "(//span[contains(text(), 'Create an Account')])[1]");
	
	Thread.sleep(3000);
	Common.textBoxInput("id", "firstname", data.get(dataSet).get("FirstName"));
	//	Common.textBoxInput("id", "middlename", data.get(dataSet).get("MiddleName"));
	Common.textBoxInput("id", "lastname", data.get(dataSet).get("LastName"));
	
	Sync.waitElementPresent("xpath", "//input[@title='Sign Up for Emails']");
	Common.clickElement("xpath", "//input[@title='Sign Up for Emails']");
	

	Common.textBoxInput("id", "email_address", Utils.getEmailid());
	Common.textBoxInput("id", "password", data.get(dataSet).get("Password"));
	Common.textBoxInput("id", "password-confirmation", data.get(dataSet).get("Password"));

	Thread.sleep(5000);

	Common.clickElement("xpath", "//button[@title='Create an Account']");

	Thread.sleep(8000);
	Common.actionsKeyPress(Keys.PAGE_UP);
	Thread.sleep(1000);

	String Success=	Common.getText("xpath", "//span[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");
	System.out.println(Success+" Account created");

	Assert.assertEquals(Success, "Thank you for registering with Hot Tools Professional.");
	report.addPassLog(expectedResult,"Should Retail Account create successfully", "Retailer User account created successfully", Common.getscreenShotPathforReport("Retailer Account Creation success"));
   }
	catch(Exception |Error e)
{
	report.addFailedLog(expectedResult,"Should Retail Account create successfully", "Retailer User account not created", Common.getscreenShotPathforReport("Retailer Account Creation Failed"));
	e.printStackTrace();
	Assert.fail();
}

}

public void FooterNewletterSubcription() throws Exception{
	String expectedResult="Newletter Subcription from footer";
	try {

		Common.actionsKeyPress(Keys.PAGE_DOWN);
		
		Thread.sleep(6000);
		Common.scrollIntoView("xpath", "//input[@id='newsletter']");
		Sync.waitElementPresent("xpath", "//input[@id='newsletter']");
		Common.textBoxInput("name", "email", Utils.getEmailid());
		Thread.sleep(5000);
		Common.clickElement("xpath", "//span[contains(text(), 'Join')]");
		Thread.sleep(6000);
		if(Common.isElementDisplayed("xpath", "//span[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")) {
			String s=Common.getText("xpath", "//span[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");
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


public void click_forgotpassword(String dataSet) throws Exception{
	try{
	Common.clickElement("xpath", "//a[@class='action remind']");
    Common.textBoxInput("id", "email_address",data.get(dataSet).get("Email"));
	Common.clickElement("xpath", "(//span[text()='Reset My Password'])");
	//Common.actionsKeyPress(Keys.PAGE_UP);
	int size=Common.findElements("xpath", "(//div[contains(@class,'message')])[1]").size();
	Common.assertionCheckwithReport(size>0, "validating forgot password","Email has been sent to given email with instructions on resetting your password.", "Successfully Email has been sent to given email with instructions on resetting your password.","Failed to send forgetpassword");
	}
	catch (Exception | Error e) {
        ExtenantReportUtils.addFailedLog("validating forgot password","Email has been sent to given email with instructions on resetting your password.","User faield to send forgetpassword",Common.getscreenShotPathforReport("forgetpassword"));
        Assert.fail();
        }
}

public void signin(String dataSet){
	String expectedResult="Account creation for Retail user";
	
	try{
		
	Sync.waitElementPresent("xpath", "(//a[@class='account-link top-link'])[2]");
	Common.clickElement("xpath", "(//a[@class='account-link top-link'])[2]");
	
	Thread.sleep(3000);
	   
			Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
			Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
			Thread.sleep(4000);
			Common.clickElement("xpath", "(//span[text()='Sign In'])[1]");
			Thread.sleep(6000);
			Common.clickElement("xpath", "//ul[@class='header links']//li[2]/span");
			Thread.sleep(3000);
			Common.clickElement("xpath", "//ul[@class='header links']//li[2]//a[text()='My Account']");
	
			
			
	   }
	   catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("verifying login page with credentials", expectedResult,
					"User failed to login in account  ", Common.getscreenShotPathforReport("login faield"));
			Assert.fail();

		}
	}

public void validateAboutUsLink() throws Exception
{
	String expectedResult="Validating CMS Link About US";
	try {
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Sync.waitElementPresent("xpath", "//a[contains(text(), 'About Us')]");
		Common.clickElement("xpath", "//a[contains(text(), 'About Us')]");
		Thread.sleep(5000);
		Common.switchWindows();
		String s = Common.getText("xpath", "//h2[contains(text(), 'How It All Started')]");
		System.out.println(s);
		Assert.assertEquals(s, "HOW IT ALL STARTED");
		//reporter parse log

		report.addPassLog(expectedResult,"Should display AboutUs page", "AboutUs page display successfully", Common.getscreenShotPathforReport("AboutUs page success"));

	}
	catch(Exception |Error e)
	{
		report.addFailedLog(expectedResult,"Should display AboutUs page", "AboutUs page display successfully", Common.getscreenShotPathforReport("AboutUs page Failed"));
		e.printStackTrace();
		Assert.fail();
	}
	//this.closeCurrentTabandSwitchtoHome();
}

public void BecomeanInfluencer() throws Exception
{
	String expectedResult="Validating CMS Links Become an Influencer";
	try {
		Common.actionsKeyPress(Keys.PAGE_DOWN);

		Sync.waitElementPresent("xpath", "//a[contains(text(), 'Become an Influencer')]");
		Common.clickElement("xpath", "//a[contains(text(), 'Become an Influencer')]");

		Thread.sleep(1000);
		
		String s = Common.getText("xpath", "//span[contains(text(), 'Become an Influencer')]");
		System.out.println(s);
		Assert.assertEquals(s, "BECOME AN INFLUENCER");

		report.addPassLog(expectedResult,"Should navigate Become an Influencer", "Become an Influencer page navigated successfully", Common.getscreenShotPathforReport("Influencer page success"));
	}catch(Exception |Error e)
	{
		report.addFailedLog(expectedResult,"Should display Become an Influencer", "Become an Influencer page navigated successfully", Common.getscreenShotPathforReport("Influencer page Failed"));
		e.printStackTrace();
		Assert.fail();
	}

}

public void  productRegistration(String dataSet) throws Exception
{
	String expectedResult="Product Registration page submission";
	try {  
		
		Common.actionsKeyPress(Keys.PAGE_DOWN);

		Sync.waitElementPresent("xpath", "//a[contains(text(), 'Registration')]");
		Common.clickElement("xpath", "//a[contains(text(), 'Registration')]");

		Thread.sleep(1000);
		
		Sync.waitElementPresent("xpath", "//a[contains(text(), 'product registration form')]");
		Common.clickElement("xpath", "//a[contains(text(), 'product registration form')]");
		
		Thread.sleep(3000);
		
		Common.switchFrames("xpath", "//*[@id='maincontent']/div[3]/div/div[2]/div/div/iframe");


		Common.textBoxInput("name", "Contact.Name.First", data.get(dataSet).get("FirstName"));



		Common.textBoxInput("name", "Contact.Name.Last", data.get(dataSet).get("LastName"));



		Sync.waitElementPresent("name", "Asset.CustomFields.HOT.ht_consumer_type");
		Common.dropdown("name", "Asset.CustomFields.HOT.ht_consumer_type", SelectBy.TEXT, data.get(dataSet).get("Customer Type"));



		Common.textBoxInput("name", "Contact.Emails.PRIMARY.Address", data.get(dataSet).get("Email"));

		Common.textBoxInput("name", "Contact.Address.Street", data.get(dataSet).get("Street"));



		Common.textBoxInput("name","Contact.Address.City", data.get(dataSet).get("City"));



		Common.dropdown("name", "Contact.Address.StateOrProvince", SelectBy.TEXT, data.get(dataSet).get("Region"));
		//Common.textBoxInput("name","Contact.Address.StateOrProvince", data.get(dataSet).get("Region"));



		Common.textBoxInput("name", "Contact.Address.PostalCode", data.get(dataSet).get("postcode"));



		Common.textBoxInput("name", "Contact.Phones.HOME.Number", data.get(dataSet).get("phone"));



		//Common.textBoxInput("name", "textinput-50", data.get(dataSet).get("Email"));



		Common.textBoxInput("name", "searchKeyword", data.get(dataSet).get("ProductName"));
		Thread.sleep(300);
		System.out.println("Product search are display");
		Sync.waitElementPresent("xpath", "//*[@id='searchResults']/div[1]");



		Common.scrollToElementAndClick("xpath", "//*[@id='searchResults']/div[1]");

		//Common.textBoxInput("name", "Asset.CustomFields.HOT.store_purchased", data.get(dataSet).get("Storewherepurchased"));



		Common.dropdown("name", "Asset.CustomFields.HOT.store_purchased", SelectBy.TEXT, data.get(dataSet).get("Storewherepurchased"));
		//Common.scrollIntoView("xpath", "//input[@class='amform-date _has-datepicker']");

		Common.textBoxInput("name", "Asset.CustomFields.HOT.date_code", data.get(dataSet).get("DataCode"));
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Common.actionsKeyPress(Keys.TAB);



		/*Common.javascriptclickElement("xpath", "//input[@class='amform-date _has-datepicker']");
        //Common.clickElement("xpath", "//input[@class='amform-date _has-datepicker']");
        Thread.sleep(4000);
        Common.clickElement("xpath", "//*[@id='ui-datepicker-div']/table/tbody/tr[5]/td[2]/a");*/



		Common.textBoxInput("id", "rn_DateInput_DateTimeUI_31", data.get(dataSet).get("Date"));



		Common.clickElement("name", "Asset.CustomFields.HOT.ht_donot_miss_out");



		Common.clickElement("name", "Asset.CustomFields.HOT.research_panel_hottools");



		Common.scrollIntoView("xpath", "//button[contains(text(),'Register My Product')]");
		Sync.waitElementPresent("xpath", "//button[contains(text(),'Register My Product')]");
		Common.clickElement("xpath", "//button[contains(text(),'Register My Product')]");

		Thread.sleep(8000);

		Common.actionsKeyPress(Keys.PAGE_UP);

		Common.scrollIntoView("xpath", "//*[contains(text(),'Thank you for registering your product!  Your request has been processed.')]");
		String Success=    Common.getText("xpath", "//*[contains(text(),'Thank you for registering your product!  Your request has been processed.')]");
		System.out.println(Success+" Product Registration done");
		Assert.assertEquals(Success, "Thank you for registering your product! Your request has been processed.");
		Common.switchToDefault();
		Thread.sleep(2000);
		//Common.actionsKeyPress(Keys.PAGE_UP);
		Common.scrollIntoView("xpath", "//span[contains(text(),'Product Registration')]");
		Thread.sleep(1000);
		
		

		report.addPassLog(expectedResult,"Should Submit Product Registration", "Product Registration submitted successfully", Common.getscreenShotPathforReport("Product Registration success"));
	}catch(Exception |Error e)
	{
		report.addFailedLog(expectedResult,"Should Submit Product Registration", "Product Registration not submitted", Common.getscreenShotPathforReport("Product Registration Failed"));
		e.printStackTrace();
		Assert.fail();
	}

}

public void validateWarrantyFAQs() throws Exception
{
	String expectedResult="Validating CMS Link Warranty FAQs";
	try {
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Sync.waitElementPresent("xpath", "//a[contains(text(), 'Warranty FAQs')]");
		Common.clickElement("xpath", "//a[contains(text(), 'Warranty FAQs')]");
		Thread.sleep(5000);
		Common.switchWindows();
		String s = Common.getText("xpath", "//span[contains(text(), 'Warranty')]");
		System.out.println(s);
		Assert.assertEquals(s, "WARRANTY");
		//reporter parse log

		report.addPassLog(expectedResult,"Should display Warranty page", "Warranty FAQ page display successfully", Common.getscreenShotPathforReport("Warranty FAQs page success"));

	}
	catch(Exception |Error e)
	{
		report.addFailedLog(expectedResult,"Should display Warranty page", "Warranty page display successfully", Common.getscreenShotPathforReport("Warranty page Failed"));
		e.printStackTrace();
		Assert.fail();
	}
	//this.closeCurrentTabandSwitchtoHome();
}

public void  validateNavigateContactUs(String dataSet) throws Exception {

	String expectedResult="Validating CMS Links Contact us with form submission";
	try {
		
		Common.actionsKeyPress(Keys.PAGE_DOWN);

		Sync.waitElementPresent("xpath", "//a[contains(text(), 'Contact Us')]");
		Common.clickElement("xpath", "//a[contains(text(), 'Contact Us')]");
		Thread.sleep(6000);
		Common.switchWindows();
		
		Common.switchFrames("xpath", "//iframe[@id='iframe-contact-us']");

		
		/*Common.textBoxInput("xpath", "//input[@title='Name']", data.get(dataSet).get("FirstName"));
		
		
		Common.textBoxInput("xpath", "(//input[@title='Email'])[1]", data.get(dataSet).get("Email"));
		Common.textBoxInput("xpath", "//input[@title='Phone Number']", data.get(dataSet).get("phone"));
		
		
		Common.textBoxInput("xpath", "//textarea[@title='Message']", data.get(dataSet).get("review"));
		
		Common.scrollToElementAndClick("xpath", "//button[@class='action submit primary']");
		Thread.sleep(5000);*/
		
		Common.textBoxInput("name", "Contact.Name.First", data.get(dataSet).get("FirstName"));
		Common.textBoxInput("name", "Contact.Name.Last", data.get(dataSet).get("LastName"));
		Common.textBoxInput("name", "Contact.Address.Street", data.get(dataSet).get("Street"));
		Common.textBoxInput("name", "Contact.Address.City", data.get(dataSet).get("City"));
		Sync.waitElementPresent("name", "Contact.Address.StateOrProvince");
		Common.dropdown("name", "Contact.Address.StateOrProvince", SelectBy.TEXT, data.get(dataSet).get("Region"));
		Common.textBoxInput("name", "Contact.Address.PostalCode", data.get(dataSet).get("postcode"));
		Common.textBoxInput("name", "Contact.Emails.PRIMARY.Address", data.get(dataSet).get("Email"));
		Common.textBoxInput("name", "Contact.Phones.MOBILE.Number", data.get(dataSet).get("phone"));
		
		Common.textBoxInput("name", "searchKeyword", data.get(dataSet).get("Model"));
		
		Common.clickElement("xpath", "//div[@title='1023 (Salon Turbo Ionic Dryer)']");
		Thread.sleep(3000);
		
		Common.textBoxInput("xpath", "//input[@placeholder='Product Date Code']", data.get(dataSet).get("DataCode"));
		Thread.sleep(5000);
		Sync.waitElementPresent("xpath", "//button[@id='rn_ProductCategoryInput_27_Category_Button']");
		Common.clickElement("xpath", "//button[@id='rn_ProductCategoryInput_27_Category_Button']");
		
		Sync.waitElementPresent("xpath", "//a[@id='yui_3_18_1_1_1633078264190_680']");
		Common.clickElement("xpath", "//a[@id='yui_3_18_1_1_1633078264190_680']");
		
		Sync.waitElementPresent("xpath", "//a[contains(text(), 'Marketing')]");
		Common.clickElement("xpath", "//a[contains(text(), 'Marketing')]");
		
		Common.textBoxInput("name", "Incident.Threads", data.get(dataSet).get("review"));
		Common.scrollToElementAndClick("xpath", "//div[@class='amcform-toolbar']/button[@class='amcform-submit action submit primary ']");
		
		
		if (Common.isElementDisplayed("xpath",
				"//span[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")) {

			Thread.sleep(7000);
			String s = Common.getText("xpath", "//span[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");
			System.out.println(s);
			Thread.sleep(7000);
			Assert.assertEquals(s, "Thanks for contacting us with your comments and questions. We'll respond to you very soon.");

			Common.switchToDefault();
			
		} else {
			System.out.println("Alert not displayed");
		}
		report.addPassLog(expectedResult,"Should display ContactUs page", "ContactUs page display successfully", Common.getscreenShotPathforReport("ContactUs page success"));
	}
	catch(Exception |Error e)
	{
		report.addFailedLog(expectedResult,"Should display ContactUs page", "ContactUs display successfully", Common.getscreenShotPathforReport("ContactUs page Failed"));
		e.printStackTrace();
		Assert.fail();
	}
	//this.closeCurrentTabandSwitchtoHome();
}

public void validateOrderStatus() throws Exception
{
	String expectedResult="Validating CMS Link Order Status";
	try {
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Sync.waitElementPresent("xpath", "//a[contains(text(), 'Order Status')]");
		Common.clickElement("xpath", "//a[contains(text(), 'Order Status')]");
		Thread.sleep(5000);
		Common.switchWindows();
		String s = Common.getText("xpath", "//span[contains(text(), 'Order Status')]");
		System.out.println(s);
		Assert.assertEquals(s, "ORDER STATUS");
		//reporter parse log

		report.addPassLog(expectedResult,"Should display OrderStatus page", "OrderStatus page display successfully", Common.getscreenShotPathforReport("OrderStatus page success"));

	}
	catch(Exception |Error e)
	{
		report.addFailedLog(expectedResult,"Should display OrderStatus page", "OrderStatus page display successfully", Common.getscreenShotPathforReport("OrderStatus page Failed"));
		e.printStackTrace();
		Assert.fail();
	}
	//this.closeCurrentTabandSwitchtoHome();
}

public void validateReturnsandRefund() throws Exception
{
	String expectedResult="Validating CMS Link ReturnsandRefund";
	try {
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Sync.waitElementPresent("xpath", "//a[contains(text(), 'Returns & Refunds')]");
		Common.clickElement("xpath", "//a[contains(text(), 'Returns & Refunds')]");
		Thread.sleep(5000);
		Common.switchWindows();
		String s = Common.getText("xpath", "//span[contains(text(), 'Refunds & Return Policy')]");
		System.out.println(s);
		Assert.assertEquals(s, "REFUNDS & RETURN POLICY");
		//reporter parse log

		report.addPassLog(expectedResult,"Should display ReturnsandRefund page", "ReturnsandRefund page display successfully", Common.getscreenShotPathforReport("ReturnsandRefund page success"));

	}
	catch(Exception |Error e)
	{
		report.addFailedLog(expectedResult,"Should display ReturnsandRefund page", "ReturnsandRefund page display successfully", Common.getscreenShotPathforReport("ReturnsandRefund page Failed"));
		e.printStackTrace();
		Assert.fail();
	}
	//this.closeCurrentTabandSwitchtoHome();
}

public void homePage(String dataSet) throws Exception
{

	String expectedResult="Validating the Home page";
	try {
		Thread.sleep(5000);
		Sync.scrollDownToView("xpath", "//h2[contains(text(),'Best Sellers')]");
		Sync.waitElementPresent("xpath", "//h2[contains(text(),'Best Sellers')]");
		Thread.sleep(3000);
		String s=Common.getText("xpath", "//h2[contains(text(),'Best Sellers')]");
		System.out.println(s);

		if(s.contains(data.get(dataSet).get("HomePage"))) {
			System.out.println("Test case passed successfully");
		}else {
			System.out.println("Test case failed");
		}
		
		Sync.scrollDownToView("xpath", "//h2[contains(text(),'Popular Tutorials')]");
		Sync.waitElementPresent("xpath", "//h2[contains(text(),'Popular Tutorials')]");
		Thread.sleep(3000);
		String s1=Common.getText("xpath", "//h2[contains(text(),'Popular Tutorials')]");
		System.out.println(s1);

		if(s1.contains(data.get(dataSet).get("HomePage1"))) {
			System.out.println("Test case passed successfully");
		}else {
			System.out.println("Test case failed");
		}
		report.addPassLog(expectedResult,"Should display Home page successfully", "Home page showed successfully", Common.getscreenShotPathforReport("Home page success"));
	}catch(Exception |Error e)
	{
		report.addFailedLog(expectedResult,"Should display Home page successfully", "Home page not showed", Common.getscreenShotPathforReport("Home page failed"));
		e.printStackTrace();
		Assert.fail();
	}
}

public void validateTopLevelNavigation(String dataSet) throws Exception{
	String expectedResult="Header Link validations";
	String Headerlinks=data.get(dataSet).get("HeaderNames");
	String[] headers=Headerlinks.split(",");
	for(int i=0;i<headers.length;i++){
		Sync.waitElementClickable("xpath", "(//span[text()='"+headers[i]+"'])[1]");
		Common.clickElement("xpath", "(//span[text()='"+headers[i]+"'])[1]");
		Thread.sleep(3000);
		System.out.println(Common.getPageTitle());
		report.addPassLog(expectedResult, "Should display "+headers[i]+" success Page", ""+headers[i]+" Page display successfully", Common.getscreenShotPathforReport("Product Registration success page success"));
	}
	
	Sync.waitElementClickable("xpath", "(//span[text()='One-Step Products'])[2]");
	Common.clickElement("xpath", "(//span[text()='One-Step Products'])[2]");
}





public void ui(){
	String name =automation_properties.getInstance().getProperty("DeviceName");
	System.out.println(name);
}


public void verifyingHomePage(){
	try{
		Thread.sleep(5000);
	
		Sync.waitElementPresent("xpath", "//a[@class='logo']");
		Thread.sleep(5000);
		Common.actionsKeyPress(Keys.END);
	String HomepageTitle=Common.findElement("xpath", "//a[@class='logo']").getAttribute("title");
	Common.assertionCheckwithReport(HomepageTitle.equals("Hot Tools"), "verifying the homepage", "navigate the home page", "user successfully navigate the home page", "Failed to navigate to home page");
}
	catch(Exception |Error e) {
	     
			ExtenantReportUtils.addFailedLog("verifying the homepage","navigate the home page", "user successfully navigate the home page", Common.getscreenShotPathforReport("failedtohomepage"));
			Assert.fail();
		}
}


public void clickMyaccount() throws Exception{
	
	verifyingHomePage();
	try{
	Sync.waitElementPresent("xpath", "//li[@class='account-links-wrap'][1]");
	Common.clickElement("xpath", "//li[@class='account-links-wrap'][1]");
	ExtenantReportUtils.addPassLog("verifying my account button", "It should lands on Customer login page", "user successfully  lands on Customer login page", Common.getscreenShotPathforReport("myaccountpass"));
	}
	 catch(Exception |Error e) {
     
		ExtenantReportUtils.addFailedLog("verifying my account button", "It should lands on Customer login page", "user faield lands on Customer login page", Common.getscreenShotPathforReport("myaccountfaield"));
		Assert.fail();
	}
	
}

public void pro_login(){

	 

    try {
        Sync.waitPageLoad();
        Thread.sleep(4000);
        Common.clickElement("xpath","(//a[text()='Pro Login'])");
        Sync.waitPageLoad();
         String Title=Common.getPageTitle();
         Thread.sleep(5000);
        Common.assertionCheckwithReport(Title.equals("Customer Login"),"Verifying pro login page","it should navigate to pro login page", "successfully  navigated to pro login page", "pro login");        
         Thread.sleep(3000);
    }
    
        catch(Exception |Error e) {
        ExtenantReportUtils.addFailedLog("To verify the pro login page","Should land on pro login page", "user unable to land on pro login page", Common.getscreenShotPathforReport("failed to land on pro login page"));            
        Assert.fail();    
        }
    }

public void CreateAccountReg(String dataSet) throws Exception{
	
	try{
		Thread.sleep(5000);
		Common.clickElement("xpath", "(//a[@class='account-link top-link'])[2]");
		Thread.sleep(3000);
		ExtenantReportUtils.addPassLog("verifying login page", "It should lands on login page", "user  lands on Customer login form Page", Common.getscreenShotPathforReport("Login page"));
	Common.clickElement("xpath", "(//span[text()='Create an Account'])[1]");
	
	ExtenantReportUtils.addPassLog("verifying Create Account button", "It should lands on Create New Customer from Account form Page", "user  lands on Customer Account creation form Page", Common.getscreenShotPathforReport("createaccount"));
	}
	catch(Exception |Error e) {
		e.printStackTrace();
	        ExtenantReportUtils.addFailedLog("verifying Create Account button", "It should lands on Create New Customer from Account form Page", "user faield lands on Account form Page", Common.getscreenShotPathforReport("createaccount"));
			Assert.fail();
		}
	//Thread.sleep(3000);
	
	try{
	Thread.sleep(9000);
/*Common.refreshpage();
	Common.textBoxInput("xpath", "//span[contains(text(), 'First Name')]",data.get(dataSet).get("FirstName"));
    Common.textBoxInput("xpath", "//span[contains(text(), 'Last Name')]",data.get(dataSet).get("LastName"));
	Common.clickElement("xpath", "//input[@title='Sign Up for Emails']");
    Common.textBoxInput("xpath", "//input[@title='Email']", data.get(dataSet).get("Email"));
   Common.textBoxInput("xpath","(//span[contains(text(), 'Password')])[1]", data.get(dataSet).get("Password")); 
	Common.textBoxInput("xpath", "(//span[contains(text(), 'Password')])[1]", data.get(dataSet).get("Password"));
	Common.clickElement("xpath", "//button[@title='Create an Account']");*/
	Common.textBoxInput("id", "firstname",data.get(dataSet).get("FirstName"));
	Common.textBoxInput("id", "lastname",data.get(dataSet).get("LastName"));
	Common.clickElement("xpath", "//input[@id='is_subscribed']");
	Common.textBoxInput("id", "email_address", Utils.getEmailid());
	//Common.textBoxInput("xpath", "//input[@title='Email']", data.get(dataSet).get("Email"));
	Common.textBoxInput("id", "password",data.get(dataSet).get("Password"));
	Common.textBoxInput("id", "password-confirmation", data.get(dataSet).get("Password"));
	Common.clickElement("xpath", "//button[@title='Create an Account']");
	Sync.waitPageLoad();
	Thread.sleep(4000);
	String title=Common.getPageTitle();
	String Head = Common.findElement("xpath", "(//h1[@class='page-title'])").getText();
	System.out.println(title);
	//Common.assertionCheckwithReport(title.equals("My Account | Hot Tools"), "verifying Create My Account functionality","User navigate to My Account page","user successfully created new account and landed on My Account page", "user faield to create new account");
	//ExtenantReportUtils.addPassLog("verifying Create My account Page", "It should lands on MyAccount  Page", "user lands on My Account  Page", Common.getscreenShotPathforReport("My Account page"));
	
	}
	 catch(Exception |Error e) {
	        ExtenantReportUtils.addFailedLog("verifying  MyAccount page", "Account should be created successfully navigate to My Account page", "user faield to create account", Common.getscreenShotPathforReport("createaccountfaield"));
			Assert.fail();
		}
	
}


public void clickHair_Tools() throws Exception {
	try {

		Sync.waitElementPresent("xpath", "//span[text()='Hair Tools']");
		Common.mouseOver("xpath" , "//span[text()='Hair Tools']");
		Thread.sleep(4000);
		Sync.waitElementPresent("xpath", "(//span[text()='One-Step Products'])[1]");
		Common.clickElement("xpath", "(//span[text()='One-Step Products'])[1]");
		Thread.sleep(4000);
		/*Common.assertionCheckwithReport(Common.getPageTitle().equals("hair-tools"),
				"verifying Hair Product category", "User navigate to hair product page",
				"user successfully open the Hair Category page", "user faield to click the Hair Product");*/
	} catch (Exception | Error e) {

		ExtenantReportUtils.addFailedLog("verifying Hair Product category", "User navigate to hair product page",
				"user faield to click the Hair Product", Common.getscreenShotPathforReport("hair-tools"));
		Assert.fail();
	}
	
}

public void click_Gifts_and_Kits(){
	try{
		
		Sync.waitElementPresent("xpath", "(//span[text()='Gifts & Kits'])[1]");
	Common.clickElement("xpath", "(//span[text()='Gifts & Kits'])[1]");
	Common.assertionCheckwithReport(Common.getPageTitle().equals("Gifts & Kits | Drybar"), "verifying Gifts & Kits category","User navigate to Gifts & Kits page","user successfully open the Gifts & Kits Category page", "user faield to click the Gifts & Kits");
}
	catch(Exception |Error e) {
	     
			ExtenantReportUtils.addFailedLog("verifying Gifts & Kits category", "User navigate to Gifts & Kits page", "user faield to click the Gifts & Kits", Common.getscreenShotPathforReport("Gifts & Kits"));
			Assert.fail();
		}
	
}






public void Select_Bundle_product(){
	try{
		Sync.waitPageLoad();
		Sync.waitElementPresent("xpath", "(//a[@class='product-item-link'])[4]");
	Common.clickElement("xpath", "(//a[@class='product-item-link'])[4]");
	Thread.sleep(5000);
	Common.assertionCheckwithReport(Common.getPageTitle().equals("The Smooth & Shine Special Value Set | Drybar"), "verifying The smoothing and shine special valueset PDP","Should land on The smoothing&shine special valueset PDP","user successfully landed on The smoothing&shine special valueset PDP", "user faield to click on The smoothing special valueset product");
}
	catch(Exception |Error e) {
	     
			ExtenantReportUtils.addFailedLog("verifying Gifts&kits category PDP", "User should select The smoothingand shine special valueset product", "user faield to select The smoothing special valueset product", Common.getscreenShotPathforReport("FAILED to select  The smoothing&shine special valueset"));
			Assert.fail();
	}
}
public void ShampoosMegamenu()throws Exception{
	try {

	Thread.sleep(3000);
	Sync.waitPageLoad();
	Common.mouseOver("xpath", "(//span[contains(text(),'Hair Products')])[1]");
		Sync.waitElementClickable("xpath", "(//span[contains(text(),'Shampoos')])");
		Common.clickElement("xpath", "(//span[contains(text(),'Shampoos')])");
		Thread.sleep(3000);
		Close_popup();
		System.out.println(Common.getPageTitle());
		Common.assertionCheckwithReport(Common.getPageTitle().contains("Shampoos"), "verifying Header link of Shampoos","user open the Shampoos option", "user successfully open the header link Shampoos","Failed open the header link Shampoos");
			
}
	catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("validating Header Links  Shampoos","user open the Shampoos option","User unabel open the header link Shampoos",Common.getscreenShotPathforReport("user failed to open the Shampoos headerlink"));
	
		Assert.fail();

	}}


public void Hair_ProductsMegamenuValidations(String dataSet) throws Exception{
	Thread.sleep(3000);
	Sync.waitPageLoad();
	ShampoosMegamenu();
	Common.mouseOver("xpath", "(//span[contains(text(),'Hair Products')])[1]");
	
	String Hederlinks2=data.get(dataSet).get("Megamenu");
	String[] hedrs=Hederlinks2.split(",");
	int i=0;
	
	try{
	for(i=0;i<hedrs.length;i++){
		System.out.println(hedrs[i]);
		Sync.waitElementClickable("xpath", "(//span[contains(text(),'"+hedrs[i]+"')])");
		Common.clickElement("xpath", "(//span[contains(text(),'"+hedrs[i]+"')])");
		Thread.sleep(3000);
		//Close_popup();
		System.out.println(Common.getPageTitle());
		Common.assertionCheckwithReport(Common.getPageTitle().contains(hedrs[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
		Common.mouseOver("xpath", "(//span[contains(text(),'Hair Products')])[1]");	
	}
	
	}
	catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("validating Header Links " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the header link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the headerlink"));
	
		Assert.fail();

	}
}

public void Hair_ToolsMegamenuValidations(String dataSet) throws Exception{
	Thread.sleep(3000);
	Sync.waitPageLoad();
	Common.mouseOver("xpath", "(//span[contains(text(),'Hair Tools')])");
	
	String Hederlinks2=data.get(dataSet).get("Megamenu");
	String[] hedrs=Hederlinks2.split(",");
	int i=0;
	
	try{
	for(i=0;i<hedrs.length;i++){
		System.out.println(hedrs[i]);
		Sync.waitElementClickable("xpath", "(//span[contains(text(),'"+hedrs[i]+"')])");
		Common.clickElement("xpath", "(//span[contains(text(),'"+hedrs[i]+"')])");
		Thread.sleep(3000);
		System.out.println(Common.getPageTitle());
		Common.assertionCheckwithReport(Common.getPageTitle().contains(hedrs[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
		Common.mouseOver("xpath", "(//span[contains(text(),'Hair Tools')])[1]");	
	}
	
	}
	catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("validating Header Links " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the header link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the headerlink"));
	
		Assert.fail();

	}
}

public void Benefits_MegamenuValidations(String dataSet) throws Exception{
	Thread.sleep(3000);
	Sync.waitPageLoad();
	Common.mouseOver("xpath", "(//span[contains(text(),'Benefits')])");
	
	String Hederlinks2=data.get(dataSet).get("Megamenu");
	String[] hedrs=Hederlinks2.split(",");
	int i=0;
	
	try{
	for(i=0;i<hedrs.length;i++){
		System.out.println(hedrs[i]);
		Sync.waitElementClickable("xpath", "(//span[contains(text(),'"+hedrs[i]+"')])[1]");
		Common.clickElement("xpath", "(//span[contains(text(),'"+hedrs[i]+"')])[1]");
		Thread.sleep(3000);
		System.out.println(Common.getPageTitle());
		Common.assertionCheckwithReport(Common.getPageTitle().contains(hedrs[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
		Common.mouseOver("xpath", "(//span[contains(text(),'Benefits')])");	
	}
	
	}
	catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("validating Header Links " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the header link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the headerlink"));
	
		Assert.fail();

	}
}

public void Gifts_MegamenuValidations(String dataSet) throws Exception{
	Thread.sleep(3000);
	Sync.waitPageLoad();
	Common.mouseOver("xpath", "(//span[contains(text(),'Gifts')])[1]");
	
	String Hederlinks2=data.get(dataSet).get("Megamenu");
	String[] hedrs=Hederlinks2.split(",");
	int i=0;
	
	try{
	for(i=0;i<hedrs.length;i++){
		System.out.println(hedrs[i]);
		Sync.waitElementClickable("xpath", "(//span[contains(text(),'"+hedrs[i]+"')])[1]");
		Common.clickElement("xpath", "(//span[contains(text(),'"+hedrs[i]+"')])[1]");
		Thread.sleep(3000);
		System.out.println(Common.getPageTitle());
		Common.assertionCheckwithReport(Common.getPageTitle().contains(hedrs[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
		Common.mouseOver("xpath", "(//span[contains(text(),'Gifts')])[1]");	
	}
	
	}
	catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("validating Header Links " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the header link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the headerlink"));
	
		Assert.fail();

	}
}

public void Inspo_MegamenuValidations(String dataSet) throws Exception{
	Thread.sleep(3000);
	Sync.waitPageLoad();
	Common.mouseOver("xpath", "(//span[contains(text(),'How To & Inspo')])");
	
	String Hederlinks2=data.get(dataSet).get("Megamenu");
	String[] hedrs=Hederlinks2.split(",");
	int i=0;
	
	try{
	for(i=0;i<hedrs.length;i++){
		System.out.println(hedrs[i]);
		Sync.waitElementClickable("xpath", "(//span[contains(text(),'"+hedrs[i]+"')])[1]");
		Common.clickElement("xpath", "(//span[contains(text(),'"+hedrs[i]+"')])[1]");
		Thread.sleep(3000);
		System.out.println(Common.getPageTitle());
		Common.assertionCheckwithReport(Common.getPageTitle().contains(hedrs[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
		Common.mouseOver("xpath", "(//span[contains(text(),'How To & Inspo')])");	
	}
	
	}
	catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("validating Header Links " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the header link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the headerlink"));
	
		Assert.fail();

	}
}

public void New_MegamenuValidations(String dataSet) throws Exception{
	Thread.sleep(3000);
	Sync.waitPageLoad();
	Common.mouseOver("xpath", "(//span[contains(text(),'New')])");
	
	String Hederlinks2=data.get(dataSet).get("Megamenu");
	String[] hedrs=Hederlinks2.split(",");
	int i=0;
	
	try{
	for(i=0;i<hedrs.length;i++){
		System.out.println(hedrs[i]);
		Sync.waitElementClickable("xpath", "(//a[contains(text(),'"+hedrs[i]+"')])[1]");
		Common.clickElement("xpath", "(//a[contains(text(),'"+hedrs[i]+"')])[1]");
		Thread.sleep(3000);
		System.out.println(Common.getPageTitle());
		Common.assertionCheckwithReport(Common.getPageTitle().contains(hedrs[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
		Common.mouseOver("xpath", "(//span[contains(text(),'New')])");	
	}
	
	}
	catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("validating Header Links " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the header link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the headerlink"));
	
		Assert.fail();

	}
}
	
	

	
public void SelectShampoos(){
	try{
		Thread.sleep(5000);
		Sync.waitElementPresent("xpath", "((//div[@class='sidebar sidebar-main'])//div[@class='category-item-title'])[8]");
	Common.clickElement("xpath", "((//div[@class='sidebar sidebar-main'])//div[@class='category-item-title'])[8]");
	Sync.waitPageLoad();
	Thread.sleep(3000);
	Common.assertionCheckwithReport(Common.getPageTitle().equals("Shampoos - Sulfate-Free Shampoo & Hair Products | Drybar"), "verifying Shampoos sub-category","User navigate to Shampoos PLP page","user successfully landed on Shampoos PLP", "user faield to click the Shampoos sub-category");
}
	catch(Exception |Error e) {
	     
			ExtenantReportUtils.addFailedLog("verifying Shampoos sub-category", "User navigate to Shampoos PLP page", "user faield to click the Shampoos sub-category", Common.getscreenShotPathforReport("hairproduct"));
			Assert.fail();
		}
	
}

public void Select_DryShampoo(){
	try{
		Thread.sleep(5000);
		Close_popup();
		//Common.actionsKeyPress(Keys.PAGE_UP);
		Sync.waitElementPresent("xpath", "(//li[@class='item category-item'])[10]");
	Common.clickElement("xpath", "(//li[@class='item category-item'])[10]");
	Common.assertionCheckwithReport(Common.getPageTitle().equals("Dry Shampoos - Cruelty & Sulfate Free Dry Conditioner & Shampoo | Drybar"), "verifying DryShampoos sub-category","User navigate to DryShampoos PLP page","user successfully landed on DryShampoos PLP", "user faield to click the DryShampoos sub-category");
	}
		catch(Exception |Error e) {
		     
				ExtenantReportUtils.addFailedLog("verifying DryShampoos sub-category", "User navigate to DryShampoos PLP page", "user faield to click the DryShampoos sub-category", Common.getscreenShotPathforReport("hairproduct"));
				Assert.fail();
			}
		
	}

/*public void Select_GiftCards_Category(){
	try{
		Thread.sleep(5000);
		Sync.waitElementPresent("xpath", "(//li[@class='item category-item'])[9]");
		Thread.sleep(5000);
	Common.clickElement("xpath", "(//li[@class='item category-item'])[9]");
	String Card=Common.findElement("xpath", "(//a[@class='product-item-link'])").getText();
	Common.assertionCheckwithReport(Card.equals("EGift Card"), "verifying Hair Product sub-category","User navigate to E-Gift Card page","user successfully open the E-Gift Card page", "user faield to click the E-Gift Card sub-category");
}
	catch(Exception |Error e) {
	     
			ExtenantReportUtils.addFailedLog("verifying E-Gift Card category", "User navigate to E-Gift Card page", "user faield to click the E-Gift Card", Common.getscreenShotPathforReport("E-Gift Card"));
			Assert.fail();
		}
	
}*/
public void Select_GiftCards_Category(){
    try{
        Thread.sleep(5000);
        Sync.waitElementPresent("xpath", "(//div[contains(text(),'Gift Cards')])[2]");
        Thread.sleep(5000);
    Common.clickElement("xpath", "(//div[contains(text(),'Gift Cards')])[2]");
    Sync.waitPageLoad();
    Thread.sleep(2000);
    String Card=Common.findElement("xpath", "(//a[@class='product-item-link'])").getText();
    Common.assertionCheckwithReport(Card.equals("EGift Card"), "verifying Hair Product sub-category","User navigate to E-Gift Card page","user successfully open the E-Gift Card page", "user faield to click the E-Gift Card sub-category");
}
    catch(Exception |Error e) {
        
            ExtenantReportUtils.addFailedLog("verifying E-Gift Card category", "User navigate to E-Gift Card page", "user faield to click the E-Gift Card", Common.getscreenShotPathforReport("E-Gift Card"));
            Assert.fail();
        }
   
}

public void Enter_GiftCard_Details(String dataSet) {
	
	try {
		
		Common.textBoxInput("xpath", "(//input[(@id='giftcard-amount-input')])",data.get(dataSet).get("GiftCardAmount"));
		Common.textBoxInput("xpath", "(//input[(@id='giftcard_sender_name')])",data.get(dataSet).get("GiftCardSenderName"));
		Common.textBoxInput("xpath", "(//input[(@id='giftcard_sender_email')])",data.get(dataSet).get("GiftCardsenderEmail"));
		Common.textBoxInput("xpath", "(//input[(@id='giftcard_recipient_name')])",data.get(dataSet).get("GiftCardRecipientName"));
		Common.textBoxInput("xpath", "(//input[(@id='giftcard_recipient_email')])",data.get(dataSet).get("GiftCardRecipientEmail"));
		Common.textBoxInput("xpath", "(//textarea[(@id='giftcard-message')])",data.get(dataSet).get("GiftCardMessage"));
		Common.clickElement("xpath", "(//button[@class='ui-datepicker-trigger v-middle'])");
		Sync.waitElementPresent("xpath", "(//select[@class='ui-datepicker-month'])");
		Common.dropdown("xpath", "(//select[@class='ui-datepicker-month'])", Common.SelectBy.TEXT, data.get(dataSet).get("Month"));
		Sync.waitElementPresent("xpath", "(//a[contains(text(),'19')])");
		Common.clickElement("xpath", "(//a[contains(text(),'19')])");
		Thread.sleep(3000);
		Sync.waitElementPresent("xpath", "(//a[@title='Happy Birthday'])");
		Common.clickElement("xpath", "(//a[@title='Happy Birthday'])");
		ExtenantReportUtils.addPassLog("faield  to fill the EGiftCard infromation", "EGiftcard fields Should  fill with the data", "User Successfully filled the EGiftCard infromation", Common.getscreenShotPathforReport("faield  to fill the EGiftCard infromation"));
	}catch(Exception |Error e) {
    
		ExtenantReportUtils.addFailedLog("validating the EGiftCard infromation", "EGiftcard fields Should  fill with the data", "faield  to fill the EGiftCard infromation", Common.getscreenShotPathforReport("faield  to fill the EGiftCard infromation"));
		Assert.fail();
	}

}

public void Hair_Products_CategoryValidations(String dataSet) throws Exception{
	Thread.sleep(3000);
	Sync.waitPageLoad();
	Common.clickElement("xpath", "(//span[contains(text(),'Hair Products')])[1]");
	
	String Hederlinks2=data.get(dataSet).get("Megamenu");
	String[] hedrs=Hederlinks2.split(",");
	int i=0;
	
	try{
	for(i=0;i<hedrs.length;i++){
		System.out.println(hedrs[i]);
		Sync.waitElementClickable("xpath", "(//div[contains(text(),'"+hedrs[i]+"')])[2]");
		Common.clickElement("xpath", "(//div[contains(text(),'"+hedrs[i]+"')])[2]");
		Thread.sleep(3000);
		System.out.println(Common.getPageTitle());
		Common.assertionCheckwithReport(Common.getPageTitle().contains(hedrs[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
		Common.clickElement("xpath", "(//span[contains(text(),'Hair Products')])[1]");	
	}
	}
	catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("validating Header Links " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the header link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the headerlink"));
	
		Assert.fail();

	}
}

public void Hair_Tools_CategoryValidations(String dataSet) throws Exception{
	Thread.sleep(3000);
	Sync.waitPageLoad();
	Common.clickElement("xpath", "(//span[contains(text(),'Hair Tools')])[1]");
	
	String Hederlinks2=data.get(dataSet).get("Megamenu");
	String[] hedrs=Hederlinks2.split(",");
	int i=0;
	
	try{
	for(i=0;i<hedrs.length;i++){
		System.out.println(hedrs[i]);
		Sync.waitElementClickable("xpath", "(//div[contains(text(),'"+hedrs[i]+"')])[2]");
		Common.clickElement("xpath", "(//div[contains(text(),'"+hedrs[i]+"')])[2]");
		Thread.sleep(3000);
		System.out.println(Common.getPageTitle());
		Common.assertionCheckwithReport(Common.getPageTitle().contains(hedrs[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
		Common.clickElement("xpath", "(//span[contains(text(),'Hair Tools')])[1]");	
	}
	}
	catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("validating Header Links " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the header link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the headerlink"));
	
		Assert.fail();

	}
}

public void Benefits_CategoryValidations(String dataSet) throws Exception{
	Thread.sleep(3000);
	Sync.waitPageLoad();
	Common.clickElement("xpath", "(//span[contains(text(),'Benefits')])[1]");
	
	String Hederlinks2=data.get(dataSet).get("Megamenu");
	String[] hedrs=Hederlinks2.split(",");
	int i=0;
	
	try{
	for(i=0;i<hedrs.length;i++){
		System.out.println(hedrs[i]);
		Sync.waitElementClickable("xpath", "(//div[contains(text(),'"+hedrs[i]+"')])[2]");
		Common.clickElement("xpath", "(//div[contains(text(),'"+hedrs[i]+"')])[2]");
		Thread.sleep(3000);
		System.out.println(Common.getPageTitle());
		Common.assertionCheckwithReport(Common.getPageTitle().contains(hedrs[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
		Common.clickElement("xpath", "(//span[contains(text(),'Benefits')])[1]");	
	}
	}
	catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("validating Header Links " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the header link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the headerlink"));
	
		Assert.fail();

	}
}

public void Gifts_CategoryValidations(String dataSet) throws Exception{
	Thread.sleep(3000);
	Sync.waitPageLoad();
	Common.clickElement("xpath", "(//span[contains(text(),'Gifts & Kits')])[1]");
	
	String Hederlinks2=data.get(dataSet).get("Megamenu");
	String[] hedrs=Hederlinks2.split(",");
	int i=0;
	
	try{
	for(i=0;i<hedrs.length;i++){
		System.out.println(hedrs[i]);
		Sync.waitElementClickable("xpath", "(//div[contains(text(),'"+hedrs[i]+"')])[2]");
		Common.clickElement("xpath", "(//div[contains(text(),'"+hedrs[i]+"')])[2]");
		Thread.sleep(3000);
		System.out.println(Common.getPageTitle());
		Common.assertionCheckwithReport(Common.getPageTitle().contains(hedrs[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
		Common.clickElement("xpath", "(//span[contains(text(),'Gifts & Kits')])[1]");	
	}
	}
	catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("validating Header Links " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the header link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the headerlink"));
	
		Assert.fail();

	}
}

public void Inspo_CategoryValidations(String dataSet) throws Exception{
	Thread.sleep(3000);
	Sync.waitPageLoad();
	Common.clickElement("xpath", "(//span[contains(text(),'How To & Inspo')])[1]");
	
	String Hederlinks2=data.get(dataSet).get("Megamenu");
	String[] hedrs=Hederlinks2.split(",");
	int i=0;
	
	try{
	for(i=0;i<hedrs.length;i++){
		System.out.println(hedrs[i]);
		Sync.waitElementClickable("xpath", "(//div[contains(text(),'"+hedrs[i]+"')])[2]");
		Common.clickElement("xpath", "(//div[contains(text(),'"+hedrs[i]+"')])[2]");
		Thread.sleep(3000);
		System.out.println(Common.getPageTitle());
		Common.assertionCheckwithReport(Common.getPageTitle().contains(hedrs[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
		Common.clickElement("xpath", "(//span[contains(text(),'How To & Inspo')])[1]");	
	}
	}
	catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("validating Header Links " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the header link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the headerlink"));
	
		Assert.fail();

	}
}


public void Select_sort(String dataSet) {

	try {
		Sync.waitPageLoad();
		Sync.waitElementPresent("id", "sorter");
		Thread.sleep(5000);
		Common.mouseOver("id", "sorter");
		Thread.sleep(5000);
		Common.dropdown("id", "sorter", Common.SelectBy.TEXT, data.get(dataSet).get("MostViewed"));
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
		Thread.sleep(3000);
		Common.mouseOver("xpath", "(//img[@class='product-image-photo'])[11]");
		Thread.sleep(3000);
		ExtenantReportUtils.addPassLog("verifying Most Viewed sort option", "User should select Most Viewed sort",
				"user faield to Select the Most Viewed sort option",
				Common.getscreenShotPathforReport("Most Viewed"));
		Thread.sleep(5000);
	} catch (Exception | Error e) {

		ExtenantReportUtils.addFailedLog("verifying  Most Viewed sort option",
				"User should  select Most Viewed sort option",
				"user Successfully Selected the Most Viewed Sort option",
				Common.getscreenShotPathforReport("Most Viewed"));
		Assert.fail();
	}
	
	try {
		Sync.waitPageLoad();
		Common.actionsKeyPress(Keys.PAGE_UP);
		Common.actionsKeyPress(Keys.PAGE_UP);
		Sync.waitElementPresent("id", "sorter");
		Thread.sleep(5000);
		Common.dropdown("id", "sorter", Common.SelectBy.TEXT, data.get(dataSet).get("New"));
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
		Thread.sleep(5000);
		ExtenantReportUtils.addPassLog("verifying New sort option", "User should select New sort",
				"user faield to Select the New sort option", Common.getscreenShotPathforReport("New"));
		Thread.sleep(5000);
	} catch (Exception | Error e) {

		ExtenantReportUtils.addFailedLog("verifying New sort option", "User should  select New sort option",
				"user Successfully Selected the  New Sort option", Common.getscreenShotPathforReport("New"));
		Assert.fail();
	}
	
	try {
		Sync.waitPageLoad();
		Common.actionsKeyPress(Keys.PAGE_UP);
		Common.actionsKeyPress(Keys.PAGE_UP);
		Sync.waitElementPresent("id", "sorter");
		Thread.sleep(5000);
		Common.dropdown("id", "sorter", Common.SelectBy.TEXT, data.get(dataSet).get("low to high"));
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
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Thread.sleep(3000);
		
		Common.mouseOver("xpath", "(//img[@class='product-image-photo'])[11]");
		Thread.sleep(3000);
		ExtenantReportUtils.addPassLog("verifying low to high sort option", "User should select low to high sort",
				"user faield to Select the low to high sort option",
				Common.getscreenShotPathforReport("low to high"));
		Thread.sleep(5000);
	} catch (Exception | Error e) {

		ExtenantReportUtils.addFailedLog("verifying low to high sort option",
				"User should  select low to high sort option", "user faield to Select the low to high Sort option",
				Common.getscreenShotPathforReport("low to high"));
		Assert.fail();
	}
	
	

	try {
		Sync.waitPageLoad();
		Common.actionsKeyPress(Keys.PAGE_UP);
		Common.actionsKeyPress(Keys.PAGE_UP);
		Sync.waitElementPresent("id", "sorter");
		Thread.sleep(5000);
		Common.dropdown("id", "sorter", Common.SelectBy.TEXT, data.get(dataSet).get("high to low"));
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Thread.sleep(5000);
		ExtenantReportUtils.addPassLog("verifying high to low sort option", "User should select high to low sort",
				"user faield to Select the high to low sort option",
				Common.getscreenShotPathforReport("high to low"));
		Thread.sleep(5000);
	} catch (Exception | Error e) {

		ExtenantReportUtils.addFailedLog("verifying high to low sort option",
				"User should  select high to low sort option", "user faield to Select the high to low Sort option",
				Common.getscreenShotPathforReport("high to low"));
		Assert.fail();
	}

	try {
		Sync.waitPageLoad();
		Common.actionsKeyPress(Keys.PAGE_UP);
		Common.actionsKeyPress(Keys.PAGE_UP);
		Sync.waitElementPresent("id", "sorter");
		Thread.sleep(5000);
		Common.dropdown("id", "sorter", Common.SelectBy.TEXT, data.get(dataSet).get("Top Rated"));
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
		Thread.sleep(3000);
		ExtenantReportUtils.addPassLog("verifying Top Rated sort option", "User should select Top Rated sort",
				"user faield to Select the Top Rated sort option", Common.getscreenShotPathforReport("Top Rated"));
		Thread.sleep(5000);	
	} catch (Exception | Error e) {

		ExtenantReportUtils.addFailedLog("verifying Top Rated sort option",
				"User should  select Top Rated sort option", "user Successfully selected the Top Rated Sort option",
				Common.getscreenShotPathforReport("Top Rated"));
		Assert.fail();
	}
	
	try {
		Sync.waitPageLoad();
		Common.actionsKeyPress(Keys.PAGE_UP);
		Common.actionsKeyPress(Keys.PAGE_UP);
		Sync.waitElementPresent("id", "sorter");
		Thread.sleep(5000);
		Common.dropdown("id", "sorter", Common.SelectBy.TEXT, data.get(dataSet).get("Product Name"));
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
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Thread.sleep(5000);
		List<WebElement> Products = Common.findElements("xpath", "//ol//div[2]//a");
		List<String> OrginalSort=Products.stream().map(s->s.getText()).collect(Collectors.toList());
		List<String> Sortedlist=OrginalSort.stream().sorted().collect(Collectors.toList());
		
		
		Common.assertionCheckwithReport(OrginalSort.equals(Sortedlist),
				"verifying Product Name sort option", "User should select Product Name sort","user faield to Select the Product Name sort option",Common.getscreenShotPathforReport("Product Name"));
		
		ExtenantReportUtils.addPassLog("verifying Product Name sort option", "User should select Product Name sort",
				"user faield to Select the Product Name sort option",
				Common.getscreenShotPathforReport("Product Name"));
		Thread.sleep(5000);
	} catch (Exception | Error e) {

		ExtenantReportUtils.addFailedLog("verifying Product Name sort option",
				"User should  select Product Name sort option",
				"user faield to Select the Product Name Sort option",
				Common.getscreenShotPathforReport("Product Name"));
		Assert.fail();
	}
	
	try {
		Sync.waitPageLoad();
		Common.actionsKeyPress(Keys.PAGE_UP);
		Common.actionsKeyPress(Keys.PAGE_UP);
		Sync.waitElementPresent("id", "sorter");
		Thread.sleep(5000);
		Common.dropdown("id", "sorter", Common.SelectBy.TEXT, data.get(dataSet).get("Best Sellers"));
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
		Thread.sleep(3000);
		Common.mouseOver("xpath", "(//img[@class='product-image-photo'])[11]");
		Thread.sleep(3000);
		ExtenantReportUtils.addPassLog("verifying Best Sellers sort option", "User should select Best Sellers sort",
				"user faield to Select the best sellers sort option",
				Common.getscreenShotPathforReport("Best Sellers"));
		Thread.sleep(5000);
	} catch (Exception | Error e) {
		 e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying Best Sellers sort option",
				"User should  select Best sellers sort option",
				"user faield to Select the best sellers Sort option",
				Common.getscreenShotPathforReport("Best Sellers"));
		Assert.fail();
	}

}




public void Select_Configurable_product(){
	try{
		Sync.waitPageLoad();
		Common.scrollIntoView("xpath", "//a[contains(text(),'Hot Air Brush ')]");
		
		Sync.waitElementPresent("xpath", "//a[contains(text(),'Hot Air Brush ')]");
	Common.clickElement("xpath", "//a[contains(text(),'Hot Air Brush ')]");
	
	Sync.waitPageLoad();
	String Title=Common.getCurrentURL();
	System.out.println(Title);
	//Common.assertionCheckwithReport(Title.equals("air-brush"), "verifying Configurable product PDP","Should land on Configurable product  PDP","user successfully landed on Configurable product PDP", "user faield to click on Configurable product");
}
	catch(Exception |Error e) {
	     
			ExtenantReportUtils.addFailedLog("verifying Configurable product PDP", "User should land on  Configurable product", "user faield to select Configurable product", Common.getscreenShotPathforReport("FAILED to select Configurable product"));
			Assert.fail();
		}
	
}


public void Selectproduct() {
	try {
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Sync.waitElementPresent("xpath", "(//a[@class='product-item-link'])[2]");
		Thread.sleep(5000);
		Common.clickElement("xpath", "(//a[@class='product-item-link'])[2]");
		/*Common.assertionCheckwithReport(Common.getPageTitle().equals("meta title | Hot Tools"),"verifying Liquid glass smooothing PDP", "Should land on Liquid glass smoothing shampoo PDP",
				"user successfully landed on Liquid glass smoothing shampoo PDP",
				"user faield to click on Liqui glass smoothing shampoo product");
		String equal=Common.getText("xpath", "//strong[text()='24K Gold One-Step Blowout']");
		System.out.println(equal);
		Assert.assertEquals(equal, "24K Gold One-Step Blowout");*/
	} catch (Exception | Error e) {
       e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying Hair Product category PDP",
				"User should select liquid glass smoothing shampoo product",
				"user faield to select liquid glass smoothing shampoo product",
				Common.getscreenShotPathforReport("FAILED to select  liquid glass smoothing shampoo product"));
		Assert.fail();
	}

}





public void Select_Size() throws Exception {
	try {
		Thread.sleep(3000);
		Sync.waitElementPresent("xpath", "//div[@aria-label='1.5']");
		Common.clickElement("xpath", "//div[@aria-label='1.5']");
		Thread.sleep(5000);
		String Size=Common.getText("xpath", "//span[@class='swatch-attribute-selected-option']");
		System.out.println(Size);
		//Common.assertionCheckwithReport(FinalPrice.equals("$10.00"),"To Verify the  Swath option","It should select swath options", "successfully selected Swatch options", "Swatch options");
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the Swath options","should select Swath options", "user unable to select Swath options", Common.getscreenShotPathforReport("failed to select Swath options"));			
		Assert.fail();	
		}
}
		
	
public void increaseProductQuantity(String Quantity) throws Exception{
	try{
	Sync.waitPageLoad();
	
	int size=Common.findElements("xpath", "//select[@id='qty']").size();
	
	if(size>0){
		Common.dropdown("xpath", "//select[@id='qty']",SelectBy.VALUE, Quantity);
	}
	else{
	for(int i=1;i<Integer.valueOf(Quantity);i++){
		Sync.waitElementPresent("id", "qty");
		Common.clickElement("xpath", "//input[@id='qty']/following::button[1]");
	    }
	
	}

	
	/*
	Common.findElement("id","qty").clear();
	Common.textBoxInput("id", "qty",Quantity);*/
	
	ExtenantReportUtils.addPassLog("verifying product Quntity", "User increaseProductQuantity", "user successfully increaseProductQuantity", Common.getscreenShotPathforReport("productIncr"));
	}
	catch(Exception |Error e) {
	     e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying Hair Product category", "User navigate to hair product page", "user faield to click the Hair Product", Common.getscreenShotPathforReport("productIncr"));
		Assert.fail();
	}
}


public void clickAddtoBag() throws Exception {
	try {
		Thread.sleep(5000);
		//Common.clickElement("xpath", "(//div[@class='swatch-option text'])[2]");

		Common.clickElement("id", "product-addtocart-button");
		Thread.sleep(5000);
		Sync.waitElementPresent("xpath", "(//div[@class='message-success success message'])");
		int message = Common.findElements("xpath", "(//div[@class='message-success success message'])").size();
		Common.assertionCheckwithReport(message > 0, "verifying add to cart button",
				"User click add to card button", "user successfully click add to cart button",
				Common.getscreenShotPathforReport("faieldtoclickutton"));
	} catch (Exception | Error e) {

		ExtenantReportUtils.addFailedLog("verifying add to cart button", "User click add to card button",
				"user faield to click add to cart button", Common.getscreenShotPathforReport("faieldtoclickutton"));
		Assert.fail();
	}

}

public void Select_SpecialValueSets() {
	try {
		Thread.sleep(6000);
		Sync.waitElementPresent("xpath", "(//div[@class='category-item-title'])[10]");
		Common.clickElement("xpath", "(//div[@class='category-item-title'])[10]");
	
	Common.assertionCheckwithReport(Common.getPageTitle().equals("Hair Care Product Bundles & Hair Styling Bundles | Drybar"), "verifying special value set sub-category","User navigate to Special value sets PLP page","user successfully landed on Special valuesets PLP", "user faield to click the Special valuesets sub-category");
}
	catch(Exception |Error e) {	
		ExtenantReportUtils.addFailedLog("verifying Special valuesets sub-category", "User navigate to Special valuesets PLP page", "user faield to click the Special valuesets sub-category", Common.getscreenShotPathforReport("Gifts&kits"));
		Assert.fail();
		
	}
	}

public void Speciavalueset(){
	try{
		Sync.waitPageLoad();
		Thread.sleep(3000);
		Sync.waitElementPresent("xpath", "//a[contains(text(),'The Tools Special Value Set #2')]");
		Thread.sleep(3000);
		Common.scrollIntoView("xpath", "//a[contains(text(),'The Tools Special Value Set #2')]");
		Thread.sleep(3000);
	Common.clickElement("xpath", "//a[contains(text(),'The Tools Special Value Set #2')]");
	Common.assertionCheckwithReport(Common.getPageTitle().equals("The Tools Special Value Set #2 | Drybar"), "verifying Special Value Set PDP","Should land on Special Value Set PDP","user successfully landed on Special Value Set PDP", "user faield to click on ASpecial Value Set product");
}
	catch(Exception |Error e) {
	     
			ExtenantReportUtils.addFailedLog("verifying Special Value Set PDP", "User should select Special Value Set product", "user faield to select Special Value Set product", Common.getscreenShotPathforReport("FAILED to select Special Value Set product"));
			Assert.fail();
		}
	
}


public void How_To_Use() throws Exception{
	try{
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Sync.waitPageLoad();
		ExtenantReportUtils.addPassLog("To view How to step by step button","Should display How to step by step button ", "user unable to view How to step by step button", Common.getscreenShotPathforReport("How to step by step button"));			
		
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the warranty footer link","Should navigate to warranty footerlink page", "userunable to  navigate to warranty foterlink", Common.getscreenShotPathforReport("failed to "));			
		Assert.fail();	
		}
	
	
	try {
		
		//Sync.waitPageLoad();
		//Sync.waitElementPresent("xpath", "(//span[contains(text(),'contact@drybar.com')])");
		Common.clickElement("xpath", "(//button[@data-action='show-how-to'])");
		//Common.switchToSecondTab();
		//Sync.waitPageLoad();
		ExtenantReportUtils.addPassLog("To verify the How to use step by step Functionality","Should diaply How to use pop-up page", "user unable to  navigate to  How to Use pop-up page", Common.getscreenShotPathforReport("failed to land on How to Use pop-up page"));			
		
		}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify the How to use step by step Functionality ","should land on How to Use pop-up page", "user unable to navigate to How to Use pop-up page", Common.getscreenShotPathforReport("failed to land on How to Use pop-up page"));			
			Assert.fail();	
			}
		}
		


public void clickminiCartButton() throws Exception{
	try{
		Thread.sleep(8000);
	Sync.waitElementPresent("xpath", "(//img[@class='minicart-link__image'])[2]");
    Common.clickElement("xpath", "(//img[@class='minicart-link__image'])[2]");
    
    Thread.sleep(3000);
    
    Sync.waitElementPresent("id", "top-cart-btn-checkout");
   int checkoutbuttonSize= Common.findElements("id", "top-cart-btn-checkout").size();
   
  Common.assertionCheckwithReport(checkoutbuttonSize>0, "verifying mini cart button", "User click mini cart button", "user successfully click mini cart button", "Failed click mini cart button");
}
    catch(Exception |Error e) {
 	   
		ExtenantReportUtils.addFailedLog("verifying mini cart button", "User click mini cart button", "user faield to click mini cart button", Common.getscreenShotPathforReport("faieldtominicartbutton"));
		Assert.fail();
	}
}


public void selectproduct(String dataSet)
{
	
try{
    String productname=data.get(dataSet).get("ProductName");
	Common.clickElement("xpath", "//a[contains(text(),'"+productname+"')]");
	String titletext=Common.findElement("xpath", "//h1[@class='page-title']/span").getText();

    Thread.sleep(3000);
	Common.assertionCheckwithReport(titletext.equals(productname), "verifying product detail page", "user navigate to product detailepage", "user successfully navigate to product detail page", "User Failed navigate to product detail page");
	
}

catch(Exception |Error e) {
    e.printStackTrace();
	ExtenantReportUtils.addFailedLog("verifying product detail page", "user navigate to product detailepage", "user Failed navigate to product detail page", Common.getscreenShotPathforReport("productpage"));
	Assert.fail();
}
}

public void clickCheckoutButton(){
	try{
		Thread.sleep(6000);
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		//Common.actionsKeyPress(Keys.PAGE_DOWN);
		//Sync.scrollSideToView("xpath", "(//button[@class='action primary checkout'])");
	 Sync.waitElementPresent("xpath", "(//button[@class='action primary checkout'])");
	 Thread.sleep(5000);
	   Common.findElement("xpath", "(//button[@class='action primary checkout'])").click();
	   
	   Thread.sleep(5000);
	   
	  //Common.assertionCheckwithReport(checkoutbuttonSize>0, "verifying mini cart button", "User click mini cart button", "user successfully click mini cart button", "Failed click mini cart button");
	}
	    catch(Exception |Error e) {
	 	   
			ExtenantReportUtils.addFailedLog("verifying checkOut button", "User click checkOut  button", "user faield to click checkOut button", Common.getscreenShotPathforReport("checkOut"));
			Assert.fail();
		}
}

public void addDeliveryAddress_for_registerUser(String dataSet) throws Exception {

	String expectedResult = "shipping address is entering in the fields";
    int size = Common.findElements(By.xpath("//span[contains(text(),'New Address')]")).size();
	if (size > 0) {

		try {
			Common.clickElement("xpath", "//span[contains(text(),'New Address')]");
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",
					data.get(dataSet).get("FirstName"));
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']",
					data.get(dataSet).get("LastName"));
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']",
					data.get(dataSet).get("Street"));
			Thread.sleep(2000);
			Common.actionsKeyPress(Keys.SPACE);
			Thread.sleep(5000);
			
			if (data.get(dataSet).get("StreetLine2") != null) {
				Common.textBoxInput("name", "street[1]", data.get(dataSet).get("Street"));
			}
		if (data.get(dataSet).get("StreetLine3") != null) {
				Common.textBoxInput("name", "street[2]", data.get(dataSet).get("Street"));
			}
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(3000);
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='city']",
					data.get(dataSet).get("City"));
			// Common.mouseOverClick("name", "region_id");
			try {
				Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			} catch (ElementClickInterceptedException e) {
				// TODO: handle exception
				Thread.sleep(3000);
				Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			}
			Thread.sleep(2000);
			Common.textBoxInputClear("xpath", "//form[@id='co-shipping-form']//input[@name='postcode']");
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='postcode']",
					data.get(dataSet).get("postcode"));
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='telephone']",
					data.get(dataSet).get("phone"));

			ExtenantReportUtils.addPassLog("validating the shipping address", expectedResult,
					"user add the shipping address",
					Common.getscreenShotPathforReport("faield to add shipping address"));


			Common.clickElement("xpath", "//button[contains(@class,'save-address')]");

			int sizeerrormessage = Common.findElements("xpath", "//span[contains(text(),'This is a required field')]").size();

			Common.assertionCheckwithReport(sizeerrormessage <= 0, "verifying shipping addres filling ",
					"user will fill the all the shipping", "user fill the shiping address click save button",
					"faield to add new shipping address");
			
			
			
			/*Common.actionsKeyPress(Keys.END);
			Sync.waitPageLoad();
			select_USPS_StandardGround_shippingMethod();
            Thread.sleep(7000);
			Common.mouseOverClick("xpath", "//div[@id='shipping-method-buttons-container']/div/button");*/
			
			
			
		} catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validating adding  address", expectedResult,
					"User unabel add shipping address",
					Common.getscreenShotPathforReport("shipping address faield"));
						Assert.fail();

		}
	}

	else

	{
		try {
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",data.get(dataSet).get("FirstName"));
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']",
					data.get(dataSet).get("LastName"));
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']",
					data.get(dataSet).get("Street"));
			Thread.sleep(2000);
			Common.actionsKeyPress(Keys.SPACE);
			Thread.sleep(3000);
			try {
				Common.clickElement("xpath",
						"//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
			} catch (Exception e) {
				Common.actionsKeyPress(Keys.BACK_SPACE);
				Thread.sleep(1000);
				Common.actionsKeyPress(Keys.SPACE);
				Common.clickElement("xpath", "//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div");
				//Common.clickElement("xpath","//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
			}
			if (data.get(dataSet).get("StreetLine2") != null) {
				Common.textBoxInput("name", "street[1]", data.get(dataSet).get("Street"));
			}
			if (data.get(dataSet).get("StreetLine3") != null) {
				Common.textBoxInput("name", "street[2]", data.get(dataSet).get("Street"));
			}
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(3000);
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='city']",
					data.get(dataSet).get("City"));
			// Common.mouseOverClick("name", "region_id");
			try {
				Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			} catch (ElementClickInterceptedException e) {
				// TODO: handle exception
				Thread.sleep(3000);
				Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			}
			Thread.sleep(2000);
			Common.textBoxInputClear("name", "postcode");
			Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
			Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));

			//Sync.waitElementClickable("xpath", "//span[contains(text(),'Continue To Payment')]");
			Common.clickElement("xpath", "//span[contains(text(),'Continue To Payment')]");

		
			ExtenantReportUtils.addPassLog("verifying shipping addres filling ", expectedResult,
					"user enter the shipping address ",
					Common.getscreenShotPathforReport("fill the shipping address first time"));

			//Common.findElements("xpath", "").size();
			expectedResult = "shipping address is filled in to the fields";
			// report.addPassLog(expectedResult,"Filled the shipping
			// address",Common.getscreenShotPathforReport("fill the shipping
			// address"));
			
			//Common.mouseOverClick("xpath", "//input[@id='label_method_bestway']");
			//Sync.waitElementClickable("xpath","//input[@id='label_method_bestway']");
			//Common.clickElement("xpath", "//input[@id='label_method_bestway']");
			select_USPS_StandardGround_shippingMethod();
            Thread.sleep(5000);
			Common.clickElement("xpath", "//button[@data-role='opc-continue']");
			
			Thread.sleep(3000);

		} catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validating adding  address", expectedResult,
					"User unabel add shipping address",
					Common.getscreenShotPathforReport("shipping address faield"));
			// ExtenantReportUtils.addFailedLog("User click check out
			// button", "User unabel click the checkout button",
			// Common.getscreenShotPathforReport("check out miniCart"));
			Assert.fail();

		}
	}

	// report.addPassLog("clicked on the proceed to payment
	// section",Common.getscreenShotPathforReport("land on the payment
	// section"));

}

public void click_GuestCheckOut() throws Exception{
	try{
	 Sync.waitElementPresent("xpath", "//span[text()='Checkout As Guest']");
	   Common.clickElement("xpath", "//span[text()='Checkout As Guest']");
	}
	   catch(Exception |Error e) {
	 	   
			ExtenantReportUtils.addFailedLog("verifying mini checkout as gust button", "User click checkout as gust button", "user faield to click checkout as gust button", Common.getscreenShotPathforReport("faieldcheckoutasgust"));
			Assert.fail();
		}
	
}

public void guestShippingAddress(String dataSet) throws Exception{
	try{
	Sync.waitElementPresent("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']");
	Common.textBoxInput("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']",data.get(dataSet).get("Email"));

	
	Sync.waitElementPresent("xpath", "//input[@name='firstname']");
	Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(dataSet).get("FirstName"));
	
	Sync.waitElementPresent("xpath", "//input[@name='lastname']");
	Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));
	
	/*Sync.waitElementPresent("xpath", "//input[@name='lastname']");
	Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));*/
	
	Sync.waitElementPresent("xpath", "//input[@name='company']");
	Common.textBoxInput("xpath", "//input[@name='company']", data.get(dataSet).get("CompanyName"));
	
	

	Sync.waitElementPresent("xpath", "//input[@name='street[0]']");
	Common.textBoxInput("xpath", "//input[@name='street[0]']", data.get(dataSet).get("Street"));
	
/*	
	Sync.waitElementPresent("xpath", "//select[@name='country_id']");
	Common.dropdown("xpath", "//select[@name='country_id']", SelectBy.TEXT, data.get(dataSet).get("Countryname"));*/
	
	
	Sync.waitElementPresent("xpath", "//select[@name='region_id']");
	Common.dropdown("xpath", "//select[@name='region_id']", SelectBy.TEXT, data.get(dataSet).get("Region"));
	
	Sync.waitElementPresent("xpath", "//input[@name='city']");
	Common.textBoxInput("xpath", "//input[@name='city']", data.get(dataSet).get("City"));
	
	Sync.waitElementPresent("xpath", "//input[@name='postcode']");
	Common.textBoxInput("xpath", "//input[@name='postcode']", data.get(dataSet).get("postcode"));
	
	Sync.waitElementPresent("xpath", "//input[@name='telephone']");
	Common.textBoxInput("xpath", "//input[@name='telephone']", data.get(dataSet).get("phone"));
	Thread.sleep(2000);
	Common.actionsKeyPress(Keys.ENTER);
	Thread.sleep(7000);
	//select_USPS_StandardGround_shippingMethod();
    Thread.sleep(10000);
    //Common.scrollIntoView("xpath", "(//span[contains(text(),'Next')])[2]");
    //Common.actionsKeyPress(Keys.END);
    //click_Next();
    Sync.waitElementPresent("xpath", "(//span[contains(text(),'Next')])[2]");
	Common.mouseOverClick("xpath","(//span[contains(text(),'Next')])[2]");
	//div[contains(@class,'error')]
	Sync.waitPageLoad();
	int sizeerrormessage=Common.findElements("xpath", "//span[contains(text(),'This is a required field')]").size();
    Common.assertionCheckwithReport(sizeerrormessage<=0, "verifying shipping addres filling ", "user will fill the all the shipping", "user fill the shiping address click save button", "faield to add new shipping address");
   
}
	catch(Exception |Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying shipping addres filling", "user will fill the all the shipping", "faield to add new shipping address",Common.getscreenShotPathforReport("faieldsshippingpagefilling"));
		Assert.fail();
		
	}  
}

public void Click_CreditCard_BrainTree(){
	try{
		Sync.waitPageLoad();
		//Common.actionsKeyPress(Keys.PAGE_DOWN);
		Sync.waitElementPresent("xpath", "//label[@for='braintree']");
         Common.clickElement("xpath", "//label[@for='braintree']");
          ExtenantReportUtils.addPassLog("verifying CreditCardbutton", "user click CreditCard ", "user clicked CreditCard option",Common.getscreenShotPathforReport("creditoption"));
	}
	catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("verifying CreditCardbutton", "user click CreditCard ", "faield to click CreditCard option",Common.getscreenShotPathforReport("creditfaeild"));
		Assert.fail();
		
	} 
}

public void Edit_BillingAddress_BrainTree(String dataSet)throws Exception{
	
	try{
	
	
		Common.clickElement("xpath","//div[@class='billing-address-details']//button");
		

   //Common.clickElement("Xpath","//span[contains(text(),'My billing and shipping address are the same')]");
	Sync.waitElementPresent("xpath", "//div[@class='payment-method-billing-address']//input[@name='firstname']");
	Common.textBoxInput("xpath", "//div[@class='payment-method-billing-address']//input[@name='firstname']",data.get(dataSet).get("FirstName"));
	Sync.waitElementPresent("xpath", "//div[@class='payment-method-billing-address']//input[@name='lastname']");
	Common.textBoxInput("xpath", "//div[@class='payment-method-billing-address']//input[@name='lastname']",data.get(dataSet).get("LastName"));
	Sync.waitElementPresent("xpath", "//div[@class='payment-method-billing-address']//input[@name='firstname']");
	Common.textBoxInput("xpath", "//div[@class='payment-method-billing-address']//input[@name='firstname']",data.get(dataSet).get("FirstName"));
	Sync.waitElementPresent("xp	ath", "//div[@class='payment-method-billing-address']//input[@name='company']");
	Common.textBoxInput("xpath", "//div[@class='payment-method-billing-address']//input[@name='company']",data.get(dataSet).get("CompanyName"));
	Sync.waitElementPresent("xpath", "//div[@class='payment-method-billing-address']//input[@name='street[0]']");
	Common.textBoxInput("xpath", "//div[@class='payment-method-billing-address']//input[@name='street[0]']", data.get(dataSet).get("Street"));
	Common.textBoxInput("xpath", "//div[@class='payment-method-billing-address']//input[@name='city']", data.get(dataSet).get("City"));
	Common.dropdown("xpath", "//div[@class='payment-method-billing-address']//select[@name='region_id']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
	Common.textBoxInput("xpath", "//div[@class='payment-method-billing-address']//input[@name='postcode']", data.get(dataSet).get("postcode"));
	Common.textBoxInput("xpath", "//div[@class='payment-method-billing-address']//input[@name='telephone']", data.get(dataSet).get("phone"));
	Common.actionsKeyPress(Keys.PAGE_DOWN);
	
	Common.clickElement("xpath", "//button[contains(@class,'action-update')]");
	
	Thread.sleep(5000);
	int sizeerrormessage=Common.findElements("xpath", "//span[contains(text(),'This is a required field')]").size();
    System.out.println("error messagess    "+sizeerrormessage);
	Common.assertionCheckwithReport(sizeerrormessage<=0, "verifying Billing addres filling ", "user will fill the all the billing address", "user fill the shipping address click save button", "faield to add new shipping address");

	}
	catch(Exception |Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying Billing addres filling", "user will fill the all the Billing address", "faield to add new billing address",Common.getscreenShotPathforReport("faieldssbillingpagefilling"));
		Assert.fail();
		
	}  
}
public void Click_PaymetricPaymentMethod(){
	try{
		Sync.waitPageLoad();
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.PAGE_DOWN);
        // Common.clickElement("xpath", "//label[@for='paymetric']");
         Common.clickElement("xpath", "//label[@for='ime_paymetrictokenize']");
          ExtenantReportUtils.addPassLog("verifying PaymetricPaymentMethod", "user click PaymetricPaymentMethod ", "user clicked PaymetricPaymentMethod option",Common.getscreenShotPathforReport("PaymetricPaymentMethodoption"));
	}
	catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("verifying PaymetricPaymentMethod", "user click PaymetricPaymentMethod ", "faield to click PaymetricPaymentMethod",Common.getscreenShotPathforReport("PaymetricPaymentMethodoption"));
		Assert.fail();
		
	} 
}
public void Edit_BillingAddress_PaymetricPaymentMethod(String dataSet)throws Exception{
	
	Thread.sleep(3000);// //button[contains(@class,'action-edit-address')]
	
		int sizes=Common.findElements("xpath", "//div[@class='billing-address-details']//button[contains(@class,'action-edit-address')]").size();
		if(sizes>0){
         Common.clickElement("xpath", "//div[@class='billing-address-details']//button[contains(@class,'action-edit-address')]");
try{
	    int selectbutton=Common.findElements("xpath", "//select[@name='billing_address_id']").size();
	
	    if(selectbutton>0){
	    	
	    	Common.dropdown("xpath", "//select[@name='billing_address_id']", SelectBy.TEXT, "New Address");
	    }
	   
         Sync.waitElementPresent("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='firstname']");
     	Common.textBoxInput("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='firstname']",data.get(dataSet).get("FirstName"));
     	Sync.waitElementPresent("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='lastname']");
     	Common.textBoxInput("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='lastname']",data.get(dataSet).get("LastName"));
     	/*Sync.waitElementPresent("xpath", "//div[contains(@name,'billingAddressime_paymetrictokenize')]//input[@name='company']");
     	Common.textBoxInput("xpath", "//div[contains(@name,'billingAddressime_paymetrictokenize')]//input[@name='company']",data.get(dataSet).get("CompanyName"));*/
     	Sync.waitElementPresent("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='company']");
     	Common.textBoxInput("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='company']",data.get(dataSet).get("CompanyName"));
     	Sync.waitElementPresent("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='street[0]']");
     	Common.textBoxInput("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='street[0]']", data.get(dataSet).get("Street"));
     	Common.dropdown("xpath", "//fieldset[contains(@class,'fieldset')]//select[@name='region_id']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
     	Thread.sleep(4000);
     	Common.textBoxInput("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='city']", data.get(dataSet).get("City"));
     	//Common.dropdown("xpath", "//div[contains(@name,'billingAddressime_paymetrictokenize')]//select[@name='region_id']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
     	//Thread.sleep(4000);
     	Common.textBoxInput("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='postcode']", data.get(dataSet).get("postcode"));
     	Common.textBoxInput("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='telephone']", data.get(dataSet).get("phone"));
     	Common.actionsKeyPress(Keys.PAGE_DOWN);
     	Thread.sleep(5000);
     	Common.scrollIntoView("xpath", "//button[contains(@class,'action-update')]");
     	Thread.sleep(5000);
     	Common.mouseOverClick("xpath", "//button[contains(@class,'action-update')]");
     	
     	Thread.sleep(5000);
     	int sizeerrormessage=Common.findElements("xpath", "//span[contains(text(),'This is a required field')]").size();
         System.out.println("error messagess    "+sizeerrormessage);
     	Common.assertionCheckwithReport(sizeerrormessage<=0, "verifying Billing addres filling ", "user will fill the all the billing address", "user fill the shipping address click save button", "faield to add new shipping address");
}
     	
     	catch(Exception |Error e) {
     		e.printStackTrace();
     		ExtenantReportUtils.addFailedLog("verifying Billing addres filling", "user will fill the all the Billing address", "faield to add new billing address",Common.getscreenShotPathforReport("faieldssbillingpagefilling"));
     		Assert.fail();
     		
     	}  
		}
	//Common.clickElement("xpath","//label[contains(@for,'billing-address-same-as-shipping-ime_paymetrictokenize')]");
	
  
	   /* Common.actionsKeyPress(Keys.PAGE_UP);
	    Thread.sleep(3000);
	    Common.*///dropdown("xpath", "//select[@name='billing_address_id']",Common.SelectBy.TEXT, "New Address");
	}
	

public void Verify_Out_of_US_BillingAddress(String dataSet)throws Exception{
	
	Thread.sleep(5000);// //button[contains(@class,'action-edit-address')]
	
	int sizes=Common.findElements("xpath", "//div[@class='billing-address-details']//button[contains(@class,'action-edit-address')]").size();
	if(sizes>0){
     Common.clickElement("xpath", "//div[@class='billing-address-details']//button[contains(@class,'action-edit-address')]");
try{
    int selectbutton=Common.findElements("xpath", "//select[@name='billing_address_id']").size();

    if(selectbutton>0){
    	Thread.sleep(4000);
    	Common.dropdown("xpath", "//select[@name='billing_address_id']", SelectBy.TEXT, "New Address");
    }
    verify_Billing_Country();
     Sync.waitElementPresent("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='firstname']");
 	Common.textBoxInput("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='firstname']",data.get(dataSet).get("FirstName"));
 	Sync.waitElementPresent("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='lastname']");
 	Common.textBoxInput("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='lastname']",data.get(dataSet).get("LastName"));
 	/*Sync.waitElementPresent("xpath", "//div[contains(@name,'billingAddressime_paymetrictokenize')]//input[@name='company']");
 	Common.textBoxInput("xpath", "//div[contains(@name,'billingAddressime_paymetrictokenize')]//input[@name='company']",data.get(dataSet).get("CompanyName"));*/
 	Sync.waitElementPresent("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='company']");
 	Common.textBoxInput("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='company']",data.get(dataSet).get("CompanyName"));
 	Sync.waitElementPresent("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='street[0]']");
 	Common.textBoxInput("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='street[0]']", data.get(dataSet).get("Street"));
 	Common.dropdown("xpath", "//fieldset[contains(@class,'fieldset')]//select[@name='region_id']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
 	Thread.sleep(4000);
 	Common.textBoxInput("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='city']", data.get(dataSet).get("City"));
 	//Common.dropdown("xpath", "//div[contains(@name,'billingAddressime_paymetrictokenize')]//select[@name='region_id']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
 	//Thread.sleep(4000);
 	Common.textBoxInput("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='postcode']", data.get(dataSet).get("postcode"));
 	Common.textBoxInput("xpath", "//fieldset[contains(@class,'fieldset')]//input[@name='telephone']", data.get(dataSet).get("phone"));
 	Common.actionsKeyPress(Keys.PAGE_DOWN);
 	Thread.sleep(5000);
 	Common.scrollIntoView("xpath", "//button[contains(@class,'action-update')]");
 	Thread.sleep(2000);
 	Common.mouseOverClick("xpath", "//button[contains(@class,'action-update')]");
 	
 	Thread.sleep(5000);
 	int sizeerrormessage=Common.findElements("xpath", "//span[contains(text(),'This is a required field')]").size();
     System.out.println("error messagess    "+sizeerrormessage);
 	Common.assertionCheckwithReport(sizeerrormessage<=0, "verifying Billing addres filling ", "user will fill the all the billing address", "user fill the shipping address click save button", "faield to add new shipping address");
}
 	
 	catch(Exception |Error e) {
 		e.printStackTrace();
 		ExtenantReportUtils.addFailedLog("verifying Billing addres filling", "user will fill the all the Billing address", "faield to add new billing address",Common.getscreenShotPathforReport("faieldssbillingpagefilling"));
 		Assert.fail();
 		
 	}  
	}
}

   //Common.clickElement("Xpath","//span[contains(text(),'My billing and shipping address are the same')]");
	public void select_USPS_StandardGround_shippingMethod() throws Exception{
		try{
			Thread.sleep(3000);
	    	Common.actionsKeyPress(Keys.END);
	    	Thread.sleep(3000);
	    	Sync.waitElementPresent("xpath", "(//td[text()='Standard'])");
	    	Thread.sleep(3000);
	    	Common.scrollIntoView("xpath", "(//td[text()='Standard'])");
	    	Thread.sleep(5000);
	         Common.mouseOverClick("xpath", "(//td[text()='Standard'])");
	          Thread.sleep(3000);
	          ExtenantReportUtils.addPassLog("To verify the Standard shipping method", "Should click on standard shipping method", "user successfully clicked on standard shipping method", Common.getscreenShotPathforReport("Successfully clicked on standard shipping method"));
	        	//Common.assertionCheckwithReport(Addtobag.equals("Add to Bag"), "To verify the PDP Page", "Should land on  PDP page", "user successfully landed on PDP page", Common.getscreenShotPathforReport(""));
	      }catch(Exception |Error e) {
	      		ExtenantReportUtils.addFailedLog("To verify the standard shipping method","Should click on standard shipping method", "user unable to click on standard shipping method", Common.getscreenShotPathforReport("failed to click on standard shipping method"));			
	      		Assert.fail();	
	      		}
	  }
	public void creditCard_payment(String dataSet) throws Exception {

		try {
			Thread.sleep(3000);

			// Common.scrollIntoView("id", "paymetric_xisecure_frame");
		
			//Common.clickElement("xpath", "//span[text()='Credit Card']");
			//Thread.sleep(5000);
		 	Common.actionsKeyPress(Keys.PAGE_DOWN);
		 	//Common.actionsKeyPress(Keys.PAGE_DOWN);
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
			Common.textBoxInput("id", "c-cardnumber", data.get(dataSet).get("cardNumber"));
			Common.dropdown("xpath", "//select[@id='c-exmth']", Common.SelectBy.TEXT,
					data.get(dataSet).get("ExpMonth"));
			Common.dropdown("xpath", "//select[@id='c-exyr']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpYear"));
			Common.textBoxInput("id", "c-cvv", data.get(dataSet).get("cvv"));
			Common.switchToDefault();
			Common.scrollIntoView("xpath", "//span[contains(text(),'Place Order')]");

			String URL = Common.getCurrentURL();
			Thread.sleep(4000);
			if (URL.equals("https://www.hottools.com/checkout/#payment")) {
				//Common.clickElement("xpath", "(//button[@type='button'])[11]");
				//Common.clickElement("xpath", "(//button[@type='button'])[11]");
				Common.getCurrentURL();
				System.out.println(URL);

			} else {

				Common.mouseOverClick("xpath", "//span[contains(text(),'Place Order')]");
			}

			// Common.clickElement("xpath", "(//button[@title='Place Order'])");
			Common.switchFrames("id", "paymetric_xisecure_frame");
			String expectedResult = "credit card fields are filled with the data";
			String errorTexts = Common.findElement("xpath", "//div[contains(@id,'error')]").getText();
			Common.switchToDefault();
		    //Sync.waitElementPresent("xpath", "(//button[@class='action-primary action-accept'])[1]");
			//Common.clickElement("xpath", "(//button[@class='action-primary action-accept'])[1]");
			Thread.sleep(3000);
			//Common.clickElement("xpath", "(//button[@class='action-primary action-accept'])[2]");
			
			//Thread.sleep(3000);
			
			Common.assertionCheckwithReport(errorTexts.isEmpty(),
					"validating the credit card information with valid data", expectedResult, "Filled the Card detiles",
					"missing field data it showinng error");

			Sync.waitPageLoad();
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Credit Card infromation",
					"credit card fields are filled with the data", "faield  to fill the Credit Card infromation",
					Common.getscreenShotPathforReport("Cardinfromationfail"));
			Assert.fail();

		}

	}
public void creditCard_payment_invalid_CC(String dataSet) throws Exception{
	
	try{
		
		//Common.actionsKeyPress(Keys.PAGE_DOWN);
		//Common.clickElement("xpath", "//label[@for='paymetric']");
		Thread.sleep(5000);
		 Common.clickElement("xpath", "//span[text()='Credit Card']");
		Common.switchFrames("id", "paymetric_xisecure_frame");
		int size=Common.findElements("xpath", "//select[@id='c-ct']").size();
		Common.switchToDefault();
		Common.assertionCheckwithReport(size>0, "validating Creditcard option", "click the creadit card label", "clicking credit card label and open the card fields", "user faield to open credit card form");
		}
	   catch(Exception |Error e) {
		   e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Credit Card option", "click the creadit card label", "faield to click Credit Card option",  Common.getscreenShotPathforReport("Cardinoption"));
			Assert.fail();
			
		}
	
	
	try{
	
	Thread.sleep(2000);
	Common.switchFrames("id", "paymetric_xisecure_frame");
	Common.dropdown("xpath", "//select[@id='c-ct']", Common.SelectBy.TEXT, data.get(dataSet).get("cardType"));
	Common.textBoxInput("id", "c-cardnumber", data.get(dataSet).get("cardNumber"));
	Common.dropdown("xpath", "//select[@id='c-exmth']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpMonth"));
	Common.dropdown("xpath", "//select[@id='c-exyr']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpYear"));
	Common.textBoxInput("id", "c-cvv", data.get(dataSet).get("cvv"));	
	Thread.sleep(2000);
	
	Common.actionsKeyPress(Keys.ARROW_DOWN);
	Common.switchToDefault();
	Thread.sleep(1000);
	Common.clickElement("xpath", "//span[contains(text(),'Place Order')]");
	Common.switchFrames("id", "paymetric_xisecure_frame");
	
	ExtenantReportUtils.addPassLog("validating the invalid Credit Card infromation", "credit card fields are filled with the invalid data", "successfully user enter credit card data", Common.getscreenShotPathforReport("cardinformation"));
	
	
	/*String expectedResult="credit card fields are filled with the data";
    String errorTexts=	Common. findElement("xpath", "//div[contains(@id,'error')]").getText();
    Common.switchToDefault();
    Common.assertionCheckwithReport(errorTexts.isEmpty(), "validating the credit card information with valid data", expectedResult, "Filled the Card detiles", "missing field data it showinng error");
    	
	*/
	}
	catch(Exception |Error e) {
	    ExtenantReportUtils.addFailedLog("validating the Credit Card infromation", "credit card fields are filled with the data", "faield  to fill the Credit Card infromation",Common.getscreenShotPathforReport("Cardinfromationfail"));
		Assert.fail();
		
	}
	
	
	
}




public void braintree_creditCard_payment(String dataSet) throws Exception{
	
	try{
		
	//	Common.actionsKeyPress(Keys.PAGE_DOWN);
		//Common.clickElement("xpath", "//label[@for='ime_paymetrictokenize']");
		Thread.sleep(2000);
		Common.switchFrames("id", "braintree-hosted-field-number");
		int size=Common.findElements("id", "credit-card-number").size();
		Common.switchToDefault();
		Common.assertionCheckwithReport(size>0, "validating Creditcard option", "click the creadit card label", "clicking credit card label and open the card fields", "user faield to open credit card form");
		}
	   catch(Exception |Error e) {
		   e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Credit Card option", "click the creadit card label", "faield to click Credit Card option",  Common.getscreenShotPathforReport("Cardinoption"));
			Assert.fail();
			
		}
	
	
	try{
	
		
	Thread.sleep(2000);
	Common.switchFrames("id", "braintree-hosted-field-number");
    Common.textBoxInput("id", "credit-card-number", data.get(dataSet).get("cardNumber"));
    
  String month= data.get(dataSet).get("ExpMonth");
  String year=data.get(dataSet).get("ExpYear");
  System.out.println(month+year);
  Common.switchToDefault();
  Common.switchFrames("id", "braintree-hosted-field-expirationDate");
  Common.textBoxInput("id", "expiration",month+year);
  Common.switchToDefault();
  Common.switchFrames("id","braintree-hosted-field-cvv");
  Common.textBoxInput("id", "cvv", data.get(dataSet).get("cvv"));
	/*Common.dropdown("xpath", "//select[@id='c-exmth']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpMonth"));
	Common.dropdown("xpath", "//select[@id='c-exyr']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpYear"));*/
	//Common.textBoxInput("id", "c-cvv", data.get(dataSet).get("cvv"));	
	Thread.sleep(2000);
	
	/*Common.actionsKeyPress(Keys.ARROW_DOWN);
	Common.switchToDefault();
	Thread.sleep(1000);
	Common.clickElement("xpath", "//span[contains(text(),'Place Order')]");
	Common.switchFrames("id", "paymetric_xisecure_frame");
	String expectedResult="credit card fields are filled with the data";
    String errorTexts=	Common. findElement("xpath", "//div[contains(@id,'error')]").getText();
    Common.switchToDefault();
    Common.assertionCheckwithReport(errorTexts.isEmpty(), "validating the credit card information with valid data", expectedResult, "Filled the Card detiles", "missing field data it showinng error");
    	*/
	
	}
	catch(Exception |Error e) {
		e.printStackTrace();
	    ExtenantReportUtils.addFailedLog("validating the Credit Card infromation", "credit card fields are filled with the data", "faield  to fill the Credit Card infromation",  Common.getscreenShotPathforReport("Cardinfromationfail"));
		Assert.fail();
		
	}	
}

public void navigateMyAccount() throws InterruptedException {
	
	String expectedResult = "User should land on the home page";
	int size =Common.findElements("xpath", "//a[@class='logo']").size();
	Common.assertionCheckwithReport(size > 0, " verifying the home page", expectedResult,"Successfully landed on the home page", "User unabel to land on home page");
	 Common.assertionCheckwithReport(size>0, "Successfully landed on th home page", expectedResult,"User unabel to land on home page");
	try {
		Sync.waitElementClickable(30, By.xpath("//ul[@class='header links']//li[2]/a"));
		Common.findElement("xpath", "//ul[@class='header links']//li[2]/a").click();
		int sizeelement=Common.findElements("xpath", "//ul[@class='header links']//li[2]//a[text()='My Account']").size();
		if(sizeelement>0){
			Common.clickElement("xpath", "//ul[@class='header links']//li[2]//a[text()='My Account']");
		}
		Thread.sleep(3000);
	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying my account option", "clcik the my account button",
				"User failed to clcik the my account button",
				Common.getscreenShotPathforReport("my account button"));
		Assert.fail();

	
	}}

public void loginApplication(String dataSet){
	String expectedResult = "Opens login pop_up";
   
	   int size= Common.findElements("id", "email").size();
	   Common.assertionCheckwithReport(size>0, "verifying login page", "open the login page", "successfully open the login page", "Failed open the login page");
	   try {
		   
			Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
			Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
			Common.clickElement("xpath", "(//span[text()='Sign In'])[1]");
			Common.clickElement("xpath", "//ul[@class='header links']//li[2]/span");
			Common.clickElement("xpath", "//ul[@class='header links']//li[2]//a[text()='My Account']");
			Thread.sleep(6000);
			
			/*String gettext=Common.findElement(By.xpath("//*[@id=\"maincontent\"]/div[1]/h1/span")).getText();
			System.out.println(gettext);
			Common.assertionCheckwithReport(gettext.equals("MY ACCOUNT"), "verifying login account",
					"customer is redirected to My Account page",
					"Logged in the application and customer is redirected to My Account page",
					"Unabel to login Account");*/
	    
	   }
	   catch (Exception | Error e) {
		   e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying login page with credentials", expectedResult,
					"User failed to login in account  ", Common.getscreenShotPathforReport("login faield"));
			Assert.fail();

		}
	}

public void click_Same_Billing_Address() throws Exception{
	try{
	Sync.waitPageLoad();
	Thread.sleep(5000);
	
	Common.clickElement("xpath", "(//input[@name='billing-address-same-as-shipping'])");
	
	ExtenantReportUtils.addPassLog("verifying Billing Address", "The Billing and shipping should be same", "user successfully added shipping and billing address same", Common.getscreenShotPathforReport("faield to add same billing address"));
	}
	catch(Exception |Error e) {
	   
		ExtenantReportUtils.addFailedLog("verifying Billing Address", "The Billing and shipping should be same", "user failed to add same shipping and billing address", Common.getscreenShotPathforReport("faield to add same billing address"));
		Assert.fail();
	}

}

public void addDeliveryAddress_registerUser(String dataSet) throws Exception {

	String expectedResult = "shipping address is entering in the fields";
    int size = Common.findElements(By.xpath("//span[contains(text(),'New Address')]")).size();
	if (size > 0) {

		try {
			Thread.sleep(5000);
			Common.clickElement("xpath", "//span[contains(text(),'New Address')]");
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",
					data.get(dataSet).get("FirstName"));
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']",
					data.get(dataSet).get("LastName"));
			Thread.sleep(5000);
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']",
					data.get(dataSet).get("Street"));
			Thread.sleep(2000);
			Common.actionsKeyPress(Keys.SPACE);
			Thread.sleep(3000);
			
			if (data.get(dataSet).get("StreetLine2") != null) {
				Common.textBoxInput("name", "street[1]", data.get(dataSet).get("Street"));
			}
		if (data.get(dataSet).get("StreetLine3") != null) {
				Common.textBoxInput("name", "street[2]", data.get(dataSet).get("Street"));
			}
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(3000);
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='city']",
					data.get(dataSet).get("City"));
			// Common.mouseOverClick("name", "region_id");
			try {
				Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			} catch (ElementClickInterceptedException e) {
				// TODO: handle exception
				Thread.sleep(3000);
				Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			}
			Thread.sleep(2000);
			Common.textBoxInputClear("xpath", "//form[@id='co-shipping-form']//input[@name='postcode']");
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='postcode']",
					data.get(dataSet).get("postcode"));
			
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='telephone']",
					data.get(dataSet).get("phone"));
			Thread.sleep(1000);
            Sync.waitElementPresent("xpath", "(//span[text()='Save in address book'])");
            Common.clickElement("xpath", "(//span[text()='Save in address book'])");
			ExtenantReportUtils.addPassLog("validating the shipping address", expectedResult,
					"user add the shipping address",
					Common.getscreenShotPathforReport("faield to add shipping address"));


			Common.clickElement("xpath", "//button[contains(@class,'save-address')]");
		
				//	Common.clickElement("xpath", "(//button[@type='button'])[11]");


			int sizeerrormessage = Common.findElements("xpath", "//span[contains(text(),'This is a required field')]").size();

			Common.assertionCheckwithReport(sizeerrormessage <= 0, "verifying shipping addres filling ",
					"user will fill the all the shipping", "user fill the shiping address click save button",
					"faield to add new shipping address");
			
			
			
			Common.actionsKeyPress(Keys.END);
			Sync.waitPageLoad();
			
			/*select_USPS_StandardGround_shippingMethod();
            Thread.sleep(5000);
			Common.mouseOverClick("xpath", "//div[@id='shipping-method-buttons-container']/div/button");*/
            Thread.sleep(5000);
			//Common.clickElement("xpath", "(//button[@type='button'])[11]");
			
			Sync.waitElementPresent("xpath", "//span[text()='Next']");
			Common.clickElement("xpath", "//span[text()='Next']");
			
		} catch (Exception | Error e) {
			e.printStackTrace();
			//ExtenantReportUtils.addFailedLog("validating adding  address", expectedResult,
					//"User unabel add shipping address",
					//Common.getscreenShotPathforReport("shipping address faield"));
						Assert.fail();

		}
	}

	else

	{
		try {
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",data.get(dataSet).get("FirstName"));
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']",
					data.get(dataSet).get("LastName"));
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']",
					data.get(dataSet).get("Street"));
			Thread.sleep(2000);
			Common.actionsKeyPress(Keys.SPACE);
			Thread.sleep(3000);
			try {
				Common.clickElement("xpath",
						"//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
			} catch (Exception e) {
				Common.actionsKeyPress(Keys.BACK_SPACE);
				Thread.sleep(1000);
				Common.actionsKeyPress(Keys.SPACE);
				Common.clickElement("xpath", "//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div");
				//Common.clickElement("xpath","//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
			}
			if (data.get(dataSet).get("StreetLine2") != null) {
				Common.textBoxInput("name", "street[1]", data.get(dataSet).get("Street"));
			}
			if (data.get(dataSet).get("StreetLine3") != null) {
				Common.textBoxInput("name", "street[2]", data.get(dataSet).get("Street"));
			}
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(3000);
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='city']",
					data.get(dataSet).get("City"));
			// Common.mouseOverClick("name", "region_id");
			try {
				Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			} catch (ElementClickInterceptedException e) {
		
				Thread.sleep(3000);
				Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			}
			Thread.sleep(2000);
			Common.textBoxInputClear("name", "postcode");
			Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
			Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));

			//Sync.waitElementClickable("xpath", "//span[contains(text(),'Continue To Payment')]");
			//Common.clickElement("xpath", "//span[contains(text(),'Continue To Payment')]");

		
			ExtenantReportUtils.addPassLog("verifying shipping addres filling ", expectedResult,
					"user enter the shipping address ",
					Common.getscreenShotPathforReport("fill the shipping address first time"));

			//Common.findElements("xpath", "").size();
			expectedResult = "shipping address is filled in to the fields";
			// report.addPassLog(expectedResult,"Filled the shipping
			// address",Common.getscreenShotPathforReport("fill the shipping
			// address"));
			
			//Common.mouseOverClick("xpath", "//input[@id='label_method_bestway']");
			//Sync.waitElementClickable("xpath","//input[@id='label_method_bestway']");
			//Common.clickElement("xpath", "//input[@id='label_method_bestway']");
			//select_USPS_StandardGround_shippingMethod();
            Thread.sleep(5000);
			Common.clickElement("xpath", "//button[@data-role='opc-continue']");
			
			Thread.sleep(3000);

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating adding  address", expectedResult,
					"User unabel add shipping address",
					Common.getscreenShotPathforReport("shipping address faield"));
			// ExtenantReportUtils.addFailedLog("User click check out
			// button", "User unabel click the checkout button",
			// Common.getscreenShotPathforReport("check out miniCart"));
			Assert.fail();

		}
	}

	// report.addPassLog("clicked on the proceed to payment
	// section",Common.getscreenShotPathforReport("land on the payment
	// section"));

}
	//Email: xshzsmsmstzenzojra@mhzayt.online
//Email: xshzsmsmstzenzojra@mhzayt.onlineEmail: xshzsmsmstzenzojra@mhzayt.online
//Rajkumar@1155
	
public void headLinksValidations(String dataSet) throws Exception{
	String Hederlinks=data.get(dataSet).get("HeaderNames");
	String[] hedrs=Hederlinks.split(",");
	int i=0;
	
	try{
	for(i=0;i<hedrs.length;i++){
		System.out.println(hedrs[i]);
		Sync.waitElementClickable("xpath", "//span[text()='"+hedrs[i]+"']");
		Common.clickElement("xpath", "//span[text()='"+hedrs[i]+"']");
		Thread.sleep(3000);
		System.out.println(Common.getPageTitle());
		Common.assertionCheckwithReport(Common.getPageTitle().contains(Common.getPageTitle()), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
		}
	}
	catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("validating Header Links " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the header link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the headerlink"));
	
		Assert.fail();

	}

	
}

public void Validation_of_categorylist(String dataSet) throws Exception{
	String Hederlinks=data.get(dataSet).get("MegaMenu");
	String[] hedrs=Hederlinks.split(",");
	int i=0;
	
	try{
	for(i=0;i<hedrs.length;i++){
		System.out.println(hedrs[i]);
		Sync.waitElementClickable("xpath", "//span[text()='"+hedrs[i]+"']");
		Common.clickElement("xpath", "//span[text()='"+hedrs[i]+"']");
		Thread.sleep(3000);
		System.out.println(Common.getPageTitle());
		Common.assertionCheckwithReport(Common.getPageTitle().contains(Common.getPageTitle()), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
		}
	}
	catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("validating Header Links " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the header link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the headerlink"));
	
		Assert.fail();

	}
}


public void updateProductInMinicart(String Product){
	try {
		
		
		Sync.waitElementPresent("xpath", "//input[contains(@id,'cart-item')]");
		Common.findElement("xpath", "//input[contains(@id,'cart-item')]").sendKeys(Keys.BACK_SPACE);
		//Common.textBoxInput("xpath", "//input[contains(@id,'cart-item')]",Keys.BACK_SPACE);
		
		Common.textBoxInput("xpath", "//input[contains(@id,'cart-item')]","2");
		Common.clickElement("xpath", "//span[text()='Update']");
		ExtenantReportUtils.addPassLog("verifying the update product in mini cart page","user update the product in mini cart page","user successfully update the product in mini cart page", Common.getscreenShotPathforReport("updateproduct"));
		
	}
	catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("verifying the update product in mini cart page","user update the product in mini cart page","User unabel update product in mini cart page",Common.getscreenShotPathforReport("userminicartupdate"));
	
		Assert.fail();

	}
	
	
}

public void click_View_editcart(){
	try{
		
		Thread.sleep(4000);
	Sync.waitElementPresent("xpath", "//a[@class='action viewcart']");
	Common.clickElement("xpath", "//a[@class='action viewcart']");
	Sync.waitPageLoad();
	Thread.sleep(4000);
	ExtenantReportUtils.addPassLog("verifying the view wdit cart button from mincart page","user after click the  view and edit button it navigate to SHOPPING CART page","User navigate to SHOPPING CART page",Common.getscreenShotPathforReport("SHOPPINGCARTPAGE"));
	}
	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying the view edit cart button from mincart page","user after click the  view and edit button it navigate to SHOPPING CART page","User unabel navigate to SHOPPING CART page",Common.getscreenShotPathforReport("SHOPPINGCARTPAGE"));
        Assert.fail();
        }
	}

public void ClearMiniCart_Bag() throws InterruptedException {
	try {
		Thread.sleep(5000);
		Common.clickElement("xpath", "//a[@class='action showcart desktop_only']");
		int sizes = Common.findElements("xpath", "(//strong[text()='You have no items in your shopping bag.'])").size();
		if (sizes > 0) {
			Common.getCurrentURL();
		} else {
			int size = Common.findElements("xpath", "(//span[contains(text(),'$0.00')])").size();
			if (size > 0) {
				List<WebElement> Products = Common.findElements("xpath", "(//ol//li)");
				System.out.println(Products);
				int ProductCount = Products.size();
				int ProductCount2 = ProductCount++;
				System.out.println(ProductCount);
				// if (Common.findElement("xpath", "//a[@class='action showcart desktop_only']")
				// != null) {
				while (ProductCount2 > 0) {
					Common.clickElement("xpath", "(//span[text()='Remove'])");
					Sync.waitPageLoad();
					Thread.sleep(3000);
					Common.clickElement("xpath", "(//span[text()='OK'])");
					Sync.waitPageLoad();
					Thread.sleep(3000);
					ProductCount2--;

				}
			} else {

				Thread.sleep(5000);
				List<WebElement> Products = Common.findElements("xpath", "(//ol//li)");
				int ProductCount = Products.size();
				// int ProductCount2=ProductCount++;
				System.out.println(ProductCount);
				// if (Common.findElement("xpath", "//a[@class='action showcart desktop_only']")
				// != null) {
				while (ProductCount > 0) {
					Sync.waitElementClickable("xpath", "(//span[text()='Remove'])");
					Common.mouseOver("xpath", "(//span[text()='Remove'])");
					Common.clickElement("xpath", "(//span[text()='Remove'])");
					Sync.waitPageLoad();
					Thread.sleep(3000);
					Common.mouseOver("xpath", "(//span[text()='OK'])");
					Common.clickElement("xpath", "(//span[text()='OK'])");
					Thread.sleep(7000);
					ProductCount--;

				}

			}

		}
	} catch (Exception | Error e) {
		Assert.fail();
	}
}


public void edit_ShopingCart(){
	try{
	Sync.waitElementPresent("xpath", "//a[contains(@class,'action-edit')]");
	Common.clickElement("xpath", "//a[contains(@class,'action-edit')]");
	int updatebuttonsize=Common.findElements("id","product-updatecart-button").size();
	
	Common.assertionCheckwithReport(updatebuttonsize>0, "Verifying the edit button in cart page", "user after click the edit button in cart detail page it will navigate product detail page", "user successfully navigate to product detail page ", "Failed to navigate to product detail page");
	Common.clickElement("id","product-updatecart-button");
	}catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying the EditShoping cart button","user after click the edit button in cart detail page it will navigate product detail page","User faield click edit button",Common.getscreenShotPathforReport("edit button"));
        Assert.fail();
        }
}

public void changeQuntity_UpdateProduct(String productcount){
	try{
	Sync.waitElementPresent("xpath", "//select[contains(@id,'cart')]");
	Common.dropdown("xpath", "//select[contains(@id,'cart')]", SelectBy.VALUE, productcount);
	Common.clickElement("xpath", "//button[@id='update_cart_action']");
	Sync.waitPageLoad();
	String value=Common.findElement("xpath", "//select[contains(@id,'cart')]").getAttribute("value");
	System.out.println(Common.getPageTitle());
	
	Common.assertionCheckwithReport(value.equals(productcount), "verifying the product Quntity","user change the quntity of product","user successfully  change quntity","Failed to chage the quntity");
	}
	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying the product Quntity","user change the quntity of product","User faield to chage the quntity",Common.getscreenShotPathforReport("failed changequntity"));
        Assert.fail();
        }
}
public void click_ContinueShopping() throws Exception{
	
	Sync.waitElementPresent("xpath", "//a[@title='Continue Shopping']");
	Common.clickElement("xpath", "//a[@title='Continue Shopping']");
	//Thread.sleep(5000);
   //  System.out.println(Common.getPageTitle());
	
}
public void addproductInMiniCartPage() throws Exception{
	
	Common.textBoxInput("xpath", "//input[contains(@id,'cart-item')]","2");
	
	Common.clickElement("xpath", "//span[text()='Update']");
	Common.clickElement("xpath", "//span[text()='View and Edit Cart']");
	
	// Cart page update//
	
	Common.dropdown("xpath", "//select[contains(@id,'cart')]", SelectBy.VALUE, "10");
	
	
	
	//Disscount
	Common.clickElement("xpath", "//div[@id='block-discount']");
	
	//Countinu shopping
	
	//Common.clickElement("xpath", "//a[@title='Continue Shopping']");
	
	
	//Removeproduct
	
	
	
}
public void gitCard(String dataSet) throws Exception{
	try{
		Thread.sleep(5000);
	Common.scrollIntoView("id", "block-giftcard-heading");
	Common.clickElement("id", "block-giftcard-heading");
	
	Thread.sleep(5000);

Common.textBoxInput("id","giftcard-code",data.get(dataSet).get("GiftCardCode"));
	
	Common.textBoxInput("id","giftcard-pin",data.get(dataSet).get("GiftCardPin"));
	
	
	Common.clickElement("xpath", "//button[@value='Apply']");
	
	Thread.sleep(3000);
	int size=Common.findElements("xpath", "//div[contains(@class,'message-success')]/div").size();
	Common.assertionCheckwithReport(size>0, "validating the gift card", "Gift Card was added.", "successfully gift card was added","Failed to add gift card");
	}
	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the gift card","Gift Card was added.","User faield to to add gift card",Common.getscreenShotPathforReport("gitcard"));
        Assert.fail();
        }
	
}
public void click_place_order_button(){
	try{
	Common.clickElement("xpath", "//button[contains(@class,'checkout')]");
	System.out.println(Common.getPageTitle());
	Common.assertionCheckwithReport(Common.getPageTitle().contains("Checkout"),"verifying place order button", "navigate order confirmation page", "User navigate order confirmation message","User faield to navigate order confirmation message");
	}
	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying place order button","navigate order confirmation page","User faield to navigate order confirmation message",Common.getscreenShotPathforReport("confirmationmessage"));
        Assert.fail();
        }
		
}

public void click_forgotpassword() throws Exception{
	try{
	Common.clickElement("xpath", "//a[@class='action remind']");
    Common.textBoxInput("id", "email_address","varaprasad.raikanti@gmail.com");
	Common.clickElement("xpath", "(//span[text()='Reset My Password'])");
	//Common.actionsKeyPress(Keys.PAGE_UP);
	int size=Common.findElements("xpath", "(//div[contains(@class,'message')])[1]").size();
	Common.assertionCheckwithReport(size>0, "validating forgot password","Email has been sent to given email with instructions on resetting your password.", "Successfully Email has been sent to given email with instructions on resetting your password.","Failed to send forgetpassword");
	}
	catch (Exception | Error e) {
        ExtenantReportUtils.addFailedLog("validating forgot password","Email has been sent to given email with instructions on resetting your password.","User faield to send forgetpassword",Common.getscreenShotPathforReport("forgetpassword"));
        Assert.fail();
        }
}


public void couponCodeHT(String dataSet){
	try{
		Sync.waitPageLoad();
		Sync.waitElementPresent("xpath","(//span[text()='Enter Discount Code'])");
		
	Common.clickElement("xpath","(//span[text()='Enter Discount Code'])");
	System.out.println(data.get(dataSet).get("couponCode"));
	Thread.sleep(3000);
    Common.textBoxInput("xpath", "(//input[@name='discount_code'])[1]",data.get(dataSet).get("couponCode"));
    Thread.sleep(2000);
    Common.clickElement("xpath", "//button[@value='Apply Code']");
    /*
    Thread.sleep(5000);
	int size=Common.findElements("xpath", "//div[contains(@class,'message-success')]/div").size();
	Common.assertionCheckwithReport(size>0, "validating the offer code", "offer code was added.", "successfully offer code added","Failed to added offer code");
	*/
    }
	
	catch (Exception | Error e) {
		e.printStackTrace();
       // ExtenantReportUtils.addFailedLog("validating offer code","offer code is applicable","User faield to add offer code",Common.getscreenShotPathforReport("offercode"));
        Assert.fail();
        }
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
	Common.assertionCheckwithReport(sucessMessage.equals("THANK YOU FOR YOUR PURCHASE!"),
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

public void Open_Drybar_URL() throws Exception {
	try {
		String url=Common.getCurrentURL();
		if(url.contains("staging")) {
			Common.oppenURL("https://jetrails-staging.drybar.com/");
			Sync.waitPageLoad();
			Thread.sleep(5000);
		}else {
			Common.oppenURL("https://www.drybar.com/");
			Sync.waitPageLoad();
			Thread.sleep(5000);
			
		}
		Common.oppenURL("");
	String OrderID1=Common.findElement("xpath", "(//a[@class='order-number'])").getText();
	Common.clickElement("xpath", "(//a[@class='order-number'])");
	Sync.waitPageLoad();
	
	String OrderID2=Common.findElement("xpath", "(//span[@class='base'])").getText();
	Common.assertionCheckwithReport(OrderID2.contains(OrderID1),"Verifying My order page","The ordered id and my orders order id should be equal", "The ordered id and my orders order id is equal", " order id");
}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("verifying My order page","The ordered id and my orders order id should be equal", "The ordered id and my orders order id is not equal", Common.getscreenShotPathforReport("failed order id"));			
		Assert.fail();	
		}
	}


public void order_verification() throws Exception {
	try {
	String OrderID1=Common.findElement("xpath", "(//a[@class='order-number'])").getText();
	System.out.println(OrderID1);
	Common.clickElement("xpath", "(//a[@class='order-number'])");
	Sync.waitPageLoad();
	
	String OrderID2=Common.findElement("xpath", "(//span[@class='base'])").getText();
	Common.assertionCheckwithReport(OrderID2.contains(OrderID1),"Verifying My order page","The ordered id and my orders order id should be equal", "The ordered id and my orders order id is equal", " order id");
}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("verifying My order page","The ordered id and my orders order id should be equal", "The ordered id and my orders order id is not equal", Common.getscreenShotPathforReport("failed order id"));			
		Assert.fail();	
		}
	}

public void Facebook()  {
	try{
		 Common.actionsKeyPress(Keys.END);
		Sync.waitPageLoad();
		ExtenantReportUtils.addPassLog("To view the  Article links","should land at End of the page with displaying Article links", "Sucessfully displayed footer Article links", Common.getscreenShotPathforReport("Successfully landed at end of the page with displaying Article links"));			
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To view the  Article links","should land at End of the page with displaying Article links", "user unable to display footer Article links", Common.getscreenShotPathforReport("failed to land at end of the page with displaying Article links"));			
		Assert.fail();	
		}
	try {
		Common.clickElement("xpath", "(//a[@title='Like Us On Facebook'])");
		Thread.sleep(5000);
		Common.switchToSecondTab();
		Sync.waitPageLoad();
		Thread.sleep(5000);
         String URL=Common.getCurrentURL();
		Common.assertionCheckwithReport(URL.contains("facebook"),"To Verify the  Facebook Article link","it shoud navigate to facebook page", "successfully  navigated to Facebook page", "Facebook");
		}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify the Facebook Article link","should land on Facebook Articlelink page", "user unable to navigate to Facebook Articlelink", Common.getscreenShotPathforReport("failed to land on Facebook page"));			
			Assert.fail();	
			}
	Common.closeCurrentWindow();
	Common.switchToFirstTab();
		}
public void Instagram() {
	try{
		 //Common.actionsKeyPress(Keys.END);
		Sync.waitPageLoad();
		ExtenantReportUtils.addPassLog("To view the Article links","should land at End of the page with displaying Article links", "Sucessfully displayed footer Article links", Common.getscreenShotPathforReport("Successfully landed at end of the page with displaying Article links"));			
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To view the  Article links","should land at End of the page with displaying Article links", "user unable to display footer Article links", Common.getscreenShotPathforReport("failed to land at end of the page with displaying Article links"));			
		Assert.fail();	
		}
	try {
		Common.clickElement("xpath", "(//img[@alt='Instagram'])");
		Common.switchToSecondTab();
		Sync.waitPageLoad();
         String URL=Common.getCurrentURL();
         Thread.sleep(3000);
		Common.assertionCheckwithReport(URL.contains("instagram"),"To Verify the  Instagram Article link","It should navigate to Instagram page", "successfully  navigated to Instagram page", "Instagram");
		}catch(Exception |Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("To verify the Instagram Article link","should land on Instagram Articlelink page", "user unable to navigate to Instagram Articlelink", Common.getscreenShotPathforReport("failed to land on Instagram page"));			
			Assert.fail();	
			}
	Common.closeCurrentWindow();
	Common.switchToFirstTab();
		}

public void Twitter () {
	try{
		 //Common.actionsKeyPress(Keys.END);
		Sync.waitPageLoad();
		ExtenantReportUtils.addPassLog("To view the Article links","should land at End of the page with displaying Article links", "Sucessfully displayed footer Article links", Common.getscreenShotPathforReport("Successfully landed at end of the page with displaying Article links"));			
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To view the  Article links","should land at End of the page with displaying Article links", "user unable to display footer Article links", Common.getscreenShotPathforReport("failed to land at end of the page with displaying Article links"));			
		Assert.fail();	
		}
	try {
		Common.clickElement("xpath", "(//a[@title='Follow Us On Twitter'])");
		Common.switchToSecondTab();
		Sync.waitPageLoad();
		Thread.sleep(5000);
         String URL=Common.getCurrentURL();
		Common.assertionCheckwithReport(URL.contains("https://twitter.com/HotToolsPro"),"To Verify the  Twitter Article link","It should navigate to Twitter page", "successfully  navigated to Twitter page", "Twitter");
		}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify the Twitter Article link","should land on Twitter Articlelink page", "user unable to navigate to Twitter Articlelink", Common.getscreenShotPathforReport("failed to land on Twitter page"));			
			Assert.fail();	
			}
	Common.closeCurrentWindow();
	Common.switchToFirstTab();
		}

public void Pinterest() {
	try{
		 //Common.actionsKeyPress(Keys.END);
		Sync.waitPageLoad();
		ExtenantReportUtils.addPassLog("To view the Article links","should land at End of the page with displaying Article links", "Sucessfully displayed footer Article links", Common.getscreenShotPathforReport("Successfully landed at end of the page with displaying Article links"));			
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To view the  Article links","should land at End of the page with displaying Article links", "user unable to display footer Article links", Common.getscreenShotPathforReport("failed to land at end of the page with displaying Article links"));			
		Assert.fail();	
		}
	try {
		Thread.sleep(4000);
		Common.clickElement("xpath", "(//img[@alt='Pinterest'])");
		Common.switchToSecondTab();
		Sync.waitPageLoad();
		 String URL=Common.getCurrentURL();
		Common.assertionCheckwithReport(URL.contains("https://www.pinterest.com/HotToolsPro/"),"To Verify the  Pinterest Article link","It should navigate to Pinterest page", "successfully  navigated to Pinterest page", "Pinterest");
		}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify the Pinterest Article link","should land on Pinterest Articlelink page", "user unable to navigate to Pinterest Articlelink", Common.getscreenShotPathforReport("failed to land on Pinterest page"));			
			Assert.fail();	
			}
	Common.closeCurrentWindow();
	Common.switchToFirstTab();
		}

public void Youtube() {
	try{
		 //Common.actionsKeyPress(Keys.END);
		Sync.waitPageLoad();
		ExtenantReportUtils.addPassLog("To view the Article links","should land at End of the page with displaying Article links", "Sucessfully displayed footer Article links", Common.getscreenShotPathforReport("Successfully landed at end of the page with displaying Article links"));			
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To view the  Article links","should land at End of the page with displaying Article links", "user unable to display footer Article links", Common.getscreenShotPathforReport("failed to land at end of the page with displaying Article links"));			
		Assert.fail();	
		}
	try {
		Common.clickElement("xpath", "(//img[@alt='Youtube'])");
		Common.switchToSecondTab();
		Sync.waitPageLoad();
		Thread.sleep(5000);
		 String URL=Common.getCurrentURL();
         System.out.println(URL);
		Common.assertionCheckwithReport(URL.contains("https://www.youtube.com/c/hottoolspro"),"To Verify the  Youtube Article link","It should navigate to Youtube page", "successfully  navigated to Youtube page", "Youtube");
		}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify the Youtube Article link","should land on youtube Articlelink page", "user unable to navigate to Youtube Articlelink", Common.getscreenShotPathforReport("failed to land on Youtube page"));			
			Assert.fail();	
			}
	Common.closeCurrentWindow();
	Common.switchToFirstTab();
		}

public void privacy_and_policy() {
	try{
		Thread.sleep(5000);
		 Common.actionsKeyPress(Keys.END);
		Sync.waitPageLoad();
		Common.actionsKeyPress(Keys.PAGE_UP);
		//Common.scrollIntoView("xpath", "(//a[@title='Privacy Policy'])");
		Thread.sleep(4000);
		ExtenantReportUtils.addPassLog("To view privacy policy button", "Should land on privacy policy button", "User successfully navigated to privacy policy button", Common.getscreenShotPathforReport("Successfully landed on privacy button"));
		}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To view privacy policy button","should land on Privacy policy button", "user unable to navigate to privacy policy button", Common.getscreenShotPathforReport("failed to land on privacy button"));			
			Assert.fail();	
			}

try{
	
	
	Common.clickElement("xpath", "(//a[@title='Privacy Preferences'])");
	//Sync.waitPageLoad();
	Thread.sleep(5000);
	Close_popup();
	
	String Title=Common.getPageTitle();
    System.out.println(Title);
	Common.assertionCheckwithReport(Title.equals("Privacy Policy | Hot Tools"),"To Verify the Privacy policy Page","It should navigate to Privacy policy page", "successfully  navigated to Privacy policy page", "Privacy policy");
	
	ExtenantReportUtils.addPassLog("To view privacy policy button", "Should land on privacy policy button", "User successfully navigated to privacy policy button", Common.getscreenShotPathforReport("Successfully landed on privacy button"));
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To view privacy policy button","should land on Privacy policy button", "user unable to navigate to privacy policy button", Common.getscreenShotPathforReport("failed to land on privacy button"));			
		Assert.fail();	
		}

}

public void Terms_Of_Use() {
	try{
		 Common.actionsKeyPress(Keys.END);
		Sync.waitPageLoad();
		Common.scrollIntoView("xpath", "(//a[@title='Terms of Use'])");
		//Common.clickElement("xpath", "(//a[@title='Terms of Use'])");
		
		Thread.sleep(4000);
		ExtenantReportUtils.addPassLog("To view Terms of use button", "Should land on Terms of use button", "User successfully navigated to terms of use button", Common.getscreenShotPathforReport("Successfully landed on terms of use button"));
		}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To view terms of use button","should land on Terms of use button", "user unable to navigate to terms of use button", Common.getscreenShotPathforReport("failed to land on terms of use button"));			
			Assert.fail();	
			}

try{
	
	
	Common.clickElement("xpath", "(//a[@title='Terms of Use'])");
	Sync.waitPageLoad();
	Thread.sleep(5000);
    String Title=Common.getPageTitle();
    System.out.println(Title);
	//Common.assertionCheckwithReport(Title.equals("Hot Tools Professional | Best Hair Appliances | Hot Tools"),"To Verify the terms of use page","It should navigate to terms of use page", "successfully  navigated to terms of use page ", "terms of use");
	
	//ExtenantReportUtils.addPassLog("To view privacy policy button", "Should land on privacy policy button", "User successfully navigated to privacy policy button", Common.getscreenShotPathforReport("Successfully landed on privacy button"));
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To view terms of use Page","should land on terms of use page", "user unable to navigate to terms of use page", Common.getscreenShotPathforReport("failed to land on terms of use page"));			
		Assert.fail();	
		}

}





public void forgetpasswordPageValidation(){

		try{
			Common.actionsKeyPress(Keys.ARROW_DOWN);
			Common.actionsKeyPress(Keys.ARROW_DOWN);
			Common.actionsKeyPress(Keys.ARROW_DOWN);
			Common.clickElement("xpath", "(//span[contains(text(),'Forgot Your Password?')])[1]");
			Sync.waitPageLoad();
			Common.actionsKeyPress(Keys.ARROW_DOWN);
			Thread.sleep(3000);
		    Common.textBoxInput("id", "email_address","manojkoppanadam");
			Common.scrollToElementAndClick("xpath", "(//span[contains(text(),'Reset My Password')])[1]");
			//Common.actionsKeyPress(Keys.PAGE_UP);
			int errormessage=Common.findElements("id", "email_address-error").size();
			
	Common.assertionCheckwithReport(errormessage>0, "Validating forget password from", "Enter invalid data it must dispaly error message", "User enter Invalid data in Forgetpassword field", "Failed to display error message ");		
	        }
		catch (Exception | Error e) {
	        ExtenantReportUtils.addFailedLog("validating forgot password form with  invalid data","I will showing error message","forgotpassword form faield dispaly  error message",Common.getscreenShotPathforReport("forgetpassword error message"));
	        Assert.fail();
	        }
	
}

public void Aggree_and_proceed() throws Exception {

		Sync.waitElementPresent("xpath", "(//button[@id='truste-consent-required'])");
		Common.clickElement("xpath", "(//button[@id='truste-consent-required'])");

 
}
	



public void checkorderstatus_footerlink() {
	try{
	    Common.actionsKeyPress(Keys.END);
		Sync.waitPageLoad();
		Thread.sleep(4000);
		Common.clickElement("xpath", "(//a[@title='Check Order Status'])[2]");
		Sync.waitPageLoad();
		String Title=Common.getPageTitle();
		Common.assertionCheckwithReport(Title.equals("Online Order | Drybar"),"Verifying online order page","it shoud navigate to online order page", "successfully  navigated to online order page", "online order");
	}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("verifying the order status footer link","user should navigate to order status footerlink", "user unable to navigate to  order status foterlink", Common.getscreenShotPathforReport("failed to land on checkorderstatus page"));			
			Assert.fail();	
			}
		}
public void Returns_footerlink() {
	try{
		 Common.actionsKeyPress(Keys.END);
		Sync.waitPageLoad();
		Thread.sleep(4000);
		Common.clickElement("xpath", "(//a[@title='Returns'])");
		Sync.waitPageLoad();
         String Title=Common.getPageTitle();
		Common.assertionCheckwithReport(Title.equals("Returns | Drybar"),"Verifying Returns page","it shoud navigate to returns page", "successfully  navigated to returns page", "Returns");
		}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify the returns footer link","should land on returns footerlink page", "user unable to navigate to returns foterlink", Common.getscreenShotPathforReport("failed to land on returns page"));			
			Assert.fail();	
			}
		}

public void Shipping_Delivery_footerlink() {
	try{
		Common.actionsKeyPress(Keys.END);
		Sync.waitPageLoad();
		Thread.sleep(4000);
		Common.clickElement("xpath","(//a[@title='Shipping & Delivery'])");
		Sync.waitPageLoad();
		 Close_popup();
		String Title= Common.getPageTitle();
		Common.assertionCheckwithReport(Title.equals("Shipping | Drybar"),"Verifying shipping page","it shoud navigate to shipping page", "successfully  navigated to shipping page", "Shipping");	
		}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify the shipping footer link","should land on shipping footerlink page", "user unable to navigate to shipping foterlink", Common.getscreenShotPathforReport("failed to land on ahipping page"));			
			Assert.fail();	
			}
		}

public void Special_offers_footerlink() {
	try{
		Common.actionsKeyPress(Keys.END);
		Sync.waitPageLoad();
		Thread.sleep(4000);
		Common.clickElement("xpath", "(//a[@title='Special Offers'])[2]");
		Sync.waitPageLoad();
		 Close_popup();
		 String Title=Common.getPageTitle();
		Common.assertionCheckwithReport(Title.equals("Special Offers | Drybar"),"Verifying special offers page","it shoud navigate to special offers page", "successfully  navigated to special offers page", "Special offers");	
		}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify special offers footer link","should navigate to special offer footerlink", "user unable to navigate to special offers foterlink", Common.getscreenShotPathforReport("failed to  land on special offers page"));			
			Assert.fail();	
			}
		}

public void warranty_footerlink() {
	try{
		Common.actionsKeyPress(Keys.END);
		Sync.waitPageLoad();
		Thread.sleep(4000);
		Common.clickElement("xpath","(//a[@title='Warranty'])");
		Sync.waitPageLoad();
		 Close_popup();
		String Title=Common.getPageTitle();
		Common.assertionCheckwithReport(Title.equals("Warranty | Drybar"),"Verifying Warranty page","it shoud navigate to warranty page", "successfully  navigated to warranty page", "Warranty");
		}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify the warranty footer link","Should navigate to warranty footerlink page", "userunable to  navigate to warranty foterlink", Common.getscreenShotPathforReport("failed to land on warranty page"));			
			Assert.fail();	
			}
		}

public void safetydata_footerlink() {
	try{
		Common.actionsKeyPress(Keys.END);
		Sync.waitPageLoad();
		Thread.sleep(4000);
		Common.clickElement("xpath","(//a[@title='Safety Data Sheets'])");
		Sync.waitPageLoad();
		 Close_popup();
		String Title= Common.getPageTitle();
		Common.assertionCheckwithReport(Title.equals("Safety Data Sheets | Drybar"),"Verifying Safety data page","it shoud navigate to safety data page", "successfully  navigated to safety data page", "Safety data");	
		}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify the safety data sheets footer link","Should navigate to safety data sheets footerlink", "user unable to navigate to safety data sheets foterlink", Common.getscreenShotPathforReport("failed to navigate to data sheets page"));			
			Assert.fail();	
			}
		}

public void Glossary_footerlink() {
	try{
		Common.actionsKeyPress(Keys.END);
		Sync.waitPageLoad();
		Thread.sleep(4000);
		Common.clickElement("xpath","(//a[@title='Glossary'])");
		Sync.waitPageLoad();
		 Close_popup();
		String Title= Common.getPageTitle();
		Common.assertionCheckwithReport(Title.equals("Glossary | Drybar"),"Verifying Glossary page","it shoud navigate to Glossary page", "successfully  navigated to Glossary page", "Glossary");	
		}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify the glossary footer link","should navigate to glossary footerlink", "user unable to navigate to glossary foterlink", Common.getscreenShotPathforReport("failed to navigate to warranty page"));			
			Assert.fail();	
			}
		}

public void Blowout_footerlink() {
	try{
		Common.actionsKeyPress(Keys.END);
		Sync.waitPageLoad();
		Thread.sleep(4000);
		Common.clickElement("xpath","(//a[@title=\"What's a Blowout\"])");
		Sync.waitPageLoad();
		 Close_popup();
		String Title= Common.getPageTitle();
		Common.assertionCheckwithReport(Title.equals("What's a Blowout? | Drybar"),"Verifying Blowout page","it shoud navigate to Blowout page", "successfully  navigated to Blowout page", "Blowout");	
		}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify the blowout footer link","navigate to blowout footerlink", "user unable to  navigate to blowout foterlink", Common.getscreenShotPathforReport("failed to navigate to blowout page"));			
			Assert.fail();	
			}
		}

public void WheretoBuy_footerlink() {

	try {
		Common.actionsKeyPress(Keys.END);
		Sync.waitPageLoad();
		Thread.sleep(4000);
		Common.clickElement("xpath", "(//a[text()='Where to Buy'])[2]");
		Thread.sleep(5000);
		Sync.waitPageLoad();
		String URL = Common.getCurrentURL();
		System.out.println(URL);
		Common.assertionCheckwithReport(URL.contains("where-to-buy"), "To Verify the Where to Buy Footerlink",
				"it shoud navigate to Drybar Shop page", "successfully  navigated to  Drybar Shop page",
				" Drybar Shop");
	} catch (Exception | Error e) {
		ExtenantReportUtils.addFailedLog("To Verify the Where to Buy Footerlink",
				"should land on  Drybar Shop page", "user unable to navigate to  Drybar Shop page",
				Common.getscreenShotPathforReport("failed to land on  Drybar Shop page"));
		Assert.fail();
	}
}

public void aboutUs_footerlink() {
	try{
		Common.actionsKeyPress(Keys.END);
		Sync.waitPageLoad();
		Thread.sleep(4000);
		Common.clickElement("xpath","(//a[@title='About Us'])");
		Sync.waitPageLoad();
		 Close_popup();
		String Title= Common.getPageTitle();
		Common.assertionCheckwithReport(Title.equals("About Us | Drybar"),"Verifying About US page","it shoud navigate to About US page", "successfully  navigated to About US Page", "About US");	
		}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify the about us footer link","should navigate to about us footerlink", "userunable to navigate to about us foterlink", Common.getscreenShotPathforReport("failed to navigate to about us footerlinkpage"));			
			Assert.fail();	
			}
		}

public void Email_To__a_Friend(String dataset) {
	try {
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		
		ExtenantReportUtils.addPassLog("To verify the Email a Friend icon","should display Email a Friend icon", "unable to display Email a Friend icon", Common.getscreenShotPathforReport("failed to display email a Friend icon"));			

	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the Email a  Friend icon","should display Email a Friend icon", "userunable to view Email a Friend icon", Common.getscreenShotPathforReport("failed to view Email a Friend icon"));			
		Assert.fail();	
		}
	
	try {
		Sync.waitElementPresent("xpath", "(//a[@title='Share by Email'])");
		Common.clickElement("xpath", "(//a[@title='Share by Email'])");
		String Title=Common.getText("xpath", "(//span[contains(text(),'Email to a Friend')])");	
		Common.assertionCheckwithReport(Title.equals("EMAIL TO A FRIEND"),"Verifying Email to a Friend page","it shoud navigate to Email to a Friend page", "successfully  navigated to Email to a Friend Page", "Email to a Friend");	
		
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the Email to a Friend icon ","should navigate to Email to a Friend page", "userunable to navigate to Email to a Friend page", Common.getscreenShotPathforReport("failed to navigate to Email to a Friend page"));			
		Assert.fail();	
		}
	try {
		Sync.waitElementPresent("id", "recipients-name0");
		Common.textBoxInput("id", "recipients-name0",data.get(dataset).get("FirstName"));
		Sync.waitElementPresent("id", "recipients-email0");
		Common.textBoxInput("id", "recipients-email0",data.get(dataset).get("Email"));
		Sync.waitElementPresent("id", "sender-message");
		Common.textBoxInput("id", "sender-message",data.get(dataset).get("Message"));
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the about us footer link","should navigate to about us footerlink", "userunable to navigate to about us foterlink", Common.getscreenShotPathforReport("failed to navigate to about us footerlinkpage"));			
		Assert.fail();	
		}
	try {
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Common.clickElement("xpath", "(//span[contains(text(),'Send Email')])");
		Sync.waitPageLoad();
		int message=Common.findElements("xpath", "(//div[@class='message-success success message'])").size();
        
		 Common.assertionCheckwithReport(message>0, "To verify the Email to a Friend success message", "Should Successfully send the Email with success message","sucessfully sent the message through Email a Friend", "faield to click sent email button");
	}catch(Exception |Error e) {
		Assert.fail();	
	}
}

public void add_to_cart() {
	
	try{
		Sync.waitPageLoad();
		Thread.sleep(5000);
	    Common.scrollIntoView("xpath", "(//span[contains(text(),'Liquid Glass Smoothing Shampoo')])");
	    Thread.sleep(2000);
	    Common.mouseOver("xpath", "(//img[@class='product-image-photo'])[1]");
	    Thread.sleep(4000);
	   // Common.clickElement("xpath", "");
	    Sync.waitElementPresent("xpath", "(//button[@title='Add to Bag'])[1]");
		Common.mouseOverClick("xpath", "(//button[@title='Add to Bag'])[1]");
		Thread.sleep(5000);
		Common.actionsKeyPress(Keys.PAGE_UP);
		Thread.sleep(5000);
		int message=Common.findElements("xpath", "(//div[@class='message-success success message'])").size();

		 Common.assertionCheckwithReport(message>0, "To verify the product added to My Wishlist", "Should add product to  My wishlist page","Product sucessfully added to My wishlist", "faield to add product to Wishlist");
	
		//ExtenantReportUtils.addPassLog("verifying add to cart button in Homepage", "User click add to card button on Homepage", "user successfully click add to cart button in Homepage", Common.getscreenShotPathforReport("faieldtoclickaddtocartbuttonin Homepage"));
		}
		catch(Exception |Error e) {
		   
			ExtenantReportUtils.addFailedLog("verifying add to cart button", "User click add to card button", "user faield to click add to cart button", Common.getscreenShotPathforReport("faieldtoclickutton"));
			Assert.fail();
		}
		
	}

public void add_to_cart_from_Homepage() {
	
	try{
		Sync.waitPageLoad();
		Thread.sleep(5000);
	    Common.scrollIntoView("xpath", "((//div[@class='slick-track'])[3]//div//a)[4]");
	    Thread.sleep(3000);
	    Common.actionsKeyPress(Keys.ARROW_UP);
	    Common.actionsKeyPress(Keys.ARROW_UP);
	    Common.actionsKeyPress(Keys.ARROW_UP);
	    Common.actionsKeyPress(Keys.ARROW_UP);
	    Common.actionsKeyPress(Keys.ARROW_UP);
	    Thread.sleep(5000);
	    Common.mouseOver("xpath", "((//div[@class='slick-track'])[3]//div//a)[4]");
	    Thread.sleep(4000);
	   // Common.clickElement("xpath", "");
	    Sync.waitElementPresent("xpath", "((//div[@class='slick-track'])[3]//button)[1]");
		Common.mouseOverClick("xpath", "((//div[@class='slick-track'])[3]//button)[1]");
		Thread.sleep(5000);
		ExtenantReportUtils.addPassLog("verifying add to cart button", "User click add to card button", "user successfully click add to cart button", Common.getscreenShotPathforReport("faieldtoclickutton"));
	}
	catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("verifying add to cart button", "User click add to card button", "user faield to click add to cart button", Common.getscreenShotPathforReport("faieldtoclickutton"));
		Assert.fail();
	}
	
	try {
		Common.actionsKeyPress(Keys.PAGE_UP);
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.PAGE_UP);
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.PAGE_UP);
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.PAGE_UP);
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.PAGE_UP);
		Thread.sleep(3000);
		/*Common.actionsKeyPress(Keys.PAGE_UP);
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.PAGE_UP);
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.PAGE_UP);
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.PAGE_UP);
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.PAGE_UP);
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.PAGE_UP);
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.PAGE_UP);
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.PAGE_UP);*/
		//Common.scrollIntoView("xpath", "(//div[@class='message-success success message'])[2]");
		Sync.waitPageLoad();
		Thread.sleep(2000);
		int message=Common.findElements("xpath", "(//div[@class='message-success success message'])").size();

		 Common.assertionCheckwithReport(message>0, "To verify the product added success message from Homepage", "Should display product added to bag success message","Product sucessfully added to bag from Homepage", "faield to add product to bag from Homepage");
	
		//ExtenantReportUtils.addPassLog("verifying add to cart button in Homepage", "User click add to card button on Homepage", "user successfully click add to cart button in Homepage", Common.getscreenShotPathforReport("faieldtoclickaddtocartbuttonin Homepage"));
		}
		catch(Exception |Error e) {
		   
			ExtenantReportUtils.addFailedLog("verifying product added success message", "User should get success message", "user faield to click add to cart button", Common.getscreenShotPathforReport("faieldtoclickutton"));
			Assert.fail();
		}
}



	public void search_product_fullname(String dataset) {
		
		try {
			Sync.waitElementPresent("xpath", "(//span[@class='search-link top-link'])");
			Common.clickElement("xpath", "(//span[@class='search-link top-link'])");
		    Sync.waitPageLoad();
			Common.textBoxInput("id", "search",data.get(dataset).get("ProductName"));
			ExtenantReportUtils.addPassLog("To verify the search functionality with full productname","should get the product name in search field","user  successfully Entered the productname", Common.getscreenShotPathforReport("Searched productname Successfully"));
			
		}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify the search functionality with fullproduct name","Should get the productname in search field","user unable to Enter the productname", Common.getscreenShotPathforReport("Failed to search proudctname"));			
			Assert.fail();	
			}
	
	try {
		    Common.actionsKeyPress(Keys.ENTER);
		    Thread.sleep(2000);
		   // Common.scrollIntoView("xpath", "(//h3[@class='result-title text-ellipsis'])[1]");
		    ExtenantReportUtils.addPassLog("To verify search results page","Should land on  product list page","user successfully landed on  product search results page", Common.getscreenShotPathforReport("Successfully landed on PLP Page"));
		}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify search results page","Should land on  product list page","user successfully landed on  product search results page", Common.getscreenShotPathforReport("Failed to land on PLP Page"));			
			Assert.fail();	
			}
		}
public void search_product_Threeletters(String dataset) {
		
		try {
			Sync.waitElementPresent("xpath", "(//span[@class='search-link top-link'])");
			Common.clickElement("xpath", "(//span[@class='search-link top-link'])");
		    Sync.waitPageLoad();
			Common.textBoxInput("id", "search",data.get(dataset).get("Threedigitproductname"));
			Thread.sleep(2000);
		    ExtenantReportUtils.addPassLog("To verify productsearch suggessions","should open product suggessions after searching","user Should get the search related product suggesstions", Common.getscreenShotPathforReport("Searchedproduct suggesstions displayed Successfully"));
			
		}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify productsearch suggessions","should open product suggesstions after searching","user unable to get the product suggesstions", Common.getscreenShotPathforReport("Failed to get searched product suggesstions"));			
			Assert.fail();	
			}
		}

public void search_product_Fourletters(String dataset) {
	
	try {
		Common.actionsKeyPress(Keys.ENTER);
		Sync.waitElementPresent("xpath", "(//span[@class='search-link top-link'])");
		Common.clickElement("xpath", "(//span[@class='search-link top-link'])");
	    Sync.waitPageLoad();
		Common.textBoxInput("id", "search",data.get(dataset).get("Fourdigitproductname"));
		Thread.sleep(4000);
		 ExtenantReportUtils.addPassLog("To verify search function by entering four letters of productname","user  should enter the four letters of productname","user successfully entred the four letters of productname",Common.getscreenShotPathforReport("Searched Successfully with four letters of productname"));
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify search function by entering four leters of productname","user should enter the four leters of product name","user unable to enter the four letters of productname", Common.getscreenShotPathforReport("Failed to search productname bu four letters"));			
		Assert.fail();	
		}
	
try {
		Common.actionsKeyPress(Keys.ENTER);
		Thread.sleep(2000);
	    //Common.scrollIntoView("xpath", "(//h3[@class='result-title text-ellipsis'])[1]");
	    ExtenantReportUtils.addPassLog("To verify  search results page by searching with four letters of productname","user should land on a product list page by searching with four lettes of productname","user successfully landed on PLP Page by searching with four letters of productname", Common.getscreenShotPathforReport("Searched Successfully with four letters of productname"));
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify search results page by searching with four letters of productname","user should land on productlist page by searching with four letters of productname","user successfully unable to land on search results page", Common.getscreenShotPathforReport("Failed to search with four letters of productname"));			
		Assert.fail();	
		}
	}
public void search_product_Dublicate(String dataset) {
	
	try {
		Sync.waitElementPresent("xpath", "(//span[@class='search-link top-link'])");
		Common.clickElement("xpath", "(//span[@class='search-link top-link'])");
	    Sync.waitPageLoad();
		Common.textBoxInput("id", "search",data.get(dataset).get("dublicateproductname"));
		Common.actionsKeyPress(Keys.ENTER);
		Thread.sleep(2000);
	    //Common.scrollIntoView("xpath", "(//h3[@class='result-title text-ellipsis'])[1]");
	    ExtenantReportUtils.addPassLog("verifying search results page","navigate to product list page","user successfully navigate to product search results page", Common.getscreenShotPathforReport("Searched Successfully"));
		
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("verifying search results page","navigate to product list page","user successfully navigate to product search results page", Common.getscreenShotPathforReport("Failed to search"));			
		Assert.fail();	
		}
	}

public void search_product_invalid(String dataset) {
	
	try {
		Common.actionsKeyPress(Keys.PAGE_UP);
		Thread.sleep(5000);
		Sync.waitElementPresent("xpath", "(//span[@class='search-link top-link'])");
		Common.clickElement("xpath", "(//span[@class='search-link top-link'])");
	    Sync.waitPageLoad();
		Common.textBoxInput("id", "search",data.get(dataset).get("invalidname"));
		ExtenantReportUtils.addPassLog("To verify search function with invalid product","user should enter invalid productname","user successfully entered invalid productname", Common.getscreenShotPathforReport("Searched Successfully with invalid productname"));
		
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify search function with invalid product","user should Enter invalid productname","user unable to enter invalid producrname", Common.getscreenShotPathforReport("Failed to  search with invalid product name"));			
		Assert.fail();	
		}
	
try{
		Common.actionsKeyPress(Keys.ENTER);
		Thread.sleep(2000);
	    //Common.scrollIntoView("xpath", "(//h3[@class='result-title text-ellipsis'])[1]");
	    ExtenantReportUtils.addPassLog("To verify search results page with invalid productname","Should land on a PLP Page with zero products","user successfully landed on PLP page with zero products", Common.getscreenShotPathforReport("Searched Successfully with invalid productname"));
		
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify seaarch results page with invalid productname ","should land on PLP page with zero products","user unable to land on  product search results page with zero products", Common.getscreenShotPathforReport("Failed to search with invalid productname"));			
		Assert.fail();	
		}
	}

public void verify_viewproduct_button(String dataset) {
	
	try {
		Sync.waitElementPresent("xpath", "(//span[@class='search-link top-link'])");
		Common.clickElement("xpath", "(//span[@class='search-link top-link'])");
	    Sync.waitPageLoad();
		Common.textBoxInput("id", "search",data.get(dataset).get("ProductName"));
		ExtenantReportUtils.addPassLog("verifying search results page","navigate to product list page","user successfully navigate to product search results page", Common.getscreenShotPathforReport("viewed cart button Successfully"));
		
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("verifying the view cart button","navigate to product list page","user successfully navigate to the view cart button", Common.getscreenShotPathforReport("Failed to view cart buttom"));			
		Assert.fail();	
		}
	
try {
		
		Common.actionsKeyPress(Keys.ENTER);
		Thread.sleep(2000);
	    Common.scrollIntoView("xpath", "//*[@id=\"instant-search-results-container\"]/div/div/ol/li[1]/div/div/div[2]/a/span");
	  //  Common.clickElement("xpath", "//*[@id=\\\"instant-search-results-container\\\"]/div/div/ol/li[1]/div/div/div[2]/a/span\"");
	    String viewcartbutton=Common.findElement("xpath", "(//h3[@itemprop='name'])[1]").getText();
	    Common.assertionCheckwithReport(viewcartbutton.equals("Liquid Glass Smoothing Shampoo"), "verifying the viewcartbutton", "navigate to PLP Page", "user successfully navigate to the viewcartbutton", "Failed to navigate to vie cart button");
	  //  ExtenantReportUtils.addPassLog("verifying search results page","navigate to product list page","user successfully navigate to product search results page", Common.getscreenShotPathforReport("viewed cart button Successfully"));
		
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("verifying the view cart button","navigate to product list page","user successfully navigate to the view cart button", Common.getscreenShotPathforReport("Failed to view cart buttom"));			
		Assert.fail();	
		}
	}
public void outofstock_productname() {
	
	try {
		Common.scrollIntoView("xpath", "//a[contains(text(), 'One-Step Round Brush Detachable Blowout & Volumizer ')]");
		Sync.waitElementPresent("xpath", "//a[contains(text(), 'One-Step Round Brush Detachable Blowout & Volumizer ')]");
		Common.clickElement("xpath", "//a[contains(text(), 'One-Step Round Brush Detachable Blowout & Volumizer ')]");
	    Thread.sleep(3000);
		ExtenantReportUtils.addPassLog("verifying the search functionality","Should get the  product name in search field","user successfully Entered the product name in search field", Common.getscreenShotPathforReport("searched productname successfully"));
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog(" To verify the search functionality","should get the product name in search field along with suggested products","user successfully Entered the product name in search field", Common.getscreenShotPathforReport("Failed to search the product"));			
		Assert.fail();	
		}

	}

public void searchresultspage_navigation(String dataset) {
	
	try {
		Common.actionsKeyPress(Keys.ENTER);
		Thread.sleep(4000);
		Sync.waitElementPresent("xpath", "(//span[@class='search-link top-link'])");
		Common.clickElement("xpath", "(//span[@class='search-link top-link'])");
	    Sync.waitPageLoad();
		Common.textBoxInput("id", "search",data.get(dataset).get("Pname"));
		ExtenantReportUtils.addPassLog("To verify the search functionality with full productname","should get the product name in search field","user  successfully Entered the productname", Common.getscreenShotPathforReport("Searched productname Successfully"));
		
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the search functionality with fullproduct name","Should get the productname in search field","user unable to Enter the productname", Common.getscreenShotPathforReport("Failed to search proudctname"));			
		Assert.fail();	
		}
try {
		
		Common.actionsKeyPress(Keys.ENTER);
		Thread.sleep(2000);
		ExtenantReportUtils.addPassLog("To verify the PLP Page","should land on PLP Page","user  successfully landed on PLP Page", Common.getscreenShotPathforReport("user Successfully landed on PLP Page"));
		
		Sync.waitPageLoad();
	    Common.scrollIntoView("xpath", "//*[@id=\"instant-search-pagination-container\"]/div/ul/li[3]");
	    Thread.sleep(2000);
	    Common.clickElement("xpath", "//*[@id=\"instant-search-pagination-container\"]/div/ul/li[3]");
	    Common.scrollIntoView("xpath","//*[@id=\"instant-search-pagination-container\"]/div/ul/li[3]");
	    //Common.assertionCheckwithReport(viewcartbutton.equals("Liquid Glass Smoothing Shampoo"), "verifying the viewcartbutton", "navigate to PLP Page", "user successfully navigate to the viewcartbutton", "Failed to navigate to vie cart button");
	   ExtenantReportUtils.addPassLog("To verify search results second page","navigate to second product list page","user successfully navigated to second product search results page", Common.getscreenShotPathforReport("navigated to second PLP page Successfully"));
		
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("verifying the search results second page","navigate to second product list page","user unable to navigate to second PLP Page", Common.getscreenShotPathforReport("Failed to navigate to second PLP page"));			
		Assert.fail();	
		}
	}

public void navigate_PDP_Page(String dataset) {
	
	try {
		Common.scrollIntoView("xpath", "(//span[@class='search-link top-link'])");
		Sync.waitElementPresent("xpath", "(//span[@class='search-link top-link'])");
		Common.clickElement("xpath", "(//span[@class='search-link top-link'])");
		Thread.sleep(2000);
	    Sync.waitPageLoad();
		Common.textBoxInput("name", "q",data.get(dataset).get("ProductName"));
		Common.actionsKeyPress(Keys.ENTER);
		Thread.sleep(2000);
	    Common.scrollIntoView("xpath", "//*[@id=\"instant-search-results-container\"]/div/div/ol/li[1]/div/div/div[2]/a/span");
	    Thread.sleep(2000);
	    Common.clickElement("xpath", "//*[@id=\\\"instant-search-results-container\\\"]/div/div/ol/li[1]/div/div/div[2]/a/span");
	    Sync.waitPageLoad();
	    Sync.waitElementPresent("xpath", "(//button[@title='Add to Bag'])[1]");
	    String addtocart=Common.findElement("xpath", "(//button[@title='Add to Bag'])[1]").getAttribute("type");
	    Common.assertionCheckwithReport(addtocart.equals("submit"), "verifying the viewcartbutton", "navigate to PLP Page", "user successfully navigate to the viewcartbutton", "Failed to navigate to vie cart button");
	  //  ExtenantReportUtils.addPassLog("verifying search results page","navigate to product list page","user successfully navigate to product search results page", Common.getscreenShotPathforReport("viewed cart button Successfully"));
		
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("verifying the view cart button","navigate to product list page","user successfully navigate to the view cart button", Common.getscreenShotPathforReport("Failed to view cart buttom"));			
		Assert.fail();	
		}
	}

public void Accept() {

	try {

		Thread.sleep(5000);
		if (Common.findElement("xpath", "//button[@id='truste-consent-required']") != null) {

			Common.clickElement("xpath", "//button[@id='truste-consent-required']");
		} 
		}catch (Exception e) {

		e.printStackTrace();

	}
}

public void Verify_FreeGift() throws Exception {
	Sync.waitPageLoad();
	Thread.sleep(3000);
	int sizes = Common.findElements("xpath", "(//button[@title='Add to cart'])").size();
	if (sizes > 0) {
		Common.clickElement("xpath", "(//button[@title='Add to cart'])");
	} else {
		Thread.sleep(4000);
		System.out.println(Common.getCurrentURL());
	}
	Thread.sleep(4000);
}
   
public void vara(){

try {

Thread.sleep(5000);

Sync.waitElementPresent("xpath", "//button[@id='truste-consent-required']");

      Common.clickElement("xpath", "//button[@id='truste-consent-required']");

} catch (Exception e) {

// TODO Auto-generated catch block

e.printStackTrace();

}

 

      

}

       

       

       public void Productionselection(){

try {

Thread.sleep(5000);

Sync.waitElementPresent("xpath", "//button[@id='truste-consent-required']");

      Common.clickElement("xpath", "//button[@id='truste-consent-required']");

} catch (Exception e) {

// TODO Auto-generated catch block

e.printStackTrace();

}

 

      

}

       

 

public void Loginuser_PDP(){

       try {

Thread.sleep(2000);

      Sync.waitElementPresent("xpath", "(//span[contains(text(), 'Hair Products')])[1]");
      Common.clickElement("xpath", "(//span[contains(text(), 'Hair Products')])[1]");
      Sync.waitElementPresent("xpath", "(//div[contains(text(), 'Shampoos')])[2]");
      Common.clickElement("xpath", "(//div[contains(text(), 'Shampoos')])[2]");
      Sync.waitElementPresent("xpath", "(//h2[@class='product name product-item-name'])[1]");
      Common.clickElement("xpath", "(//h2[@class='product name product-item-name'])[1]");
      Sync.waitPageLoad();
      String Addtobag=Common.findElement("xpath","//button[@title='Add to Bag']").getAttribute("title");
		Common.assertionCheckwithReport(Addtobag.equals("Add to Bag"), "verifying the Loginuser PDP", "navigate to Loginuser PDP", "user successfully navgate to Loginuser PDP");
	}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("verifying the Loginuser PDP","navigate to Loginuser PDP", "user successfully navigate to Loginuser PDP", Common.getscreenShotPathforReport("failed to Verify login PDP"));			
			Assert.fail();	
			}
		}

      

    //  Sync.waitElementPresent("xpath", "//button[@title='Add to Bag']");

     // Common.clickElement("xpath", "//button[@title='Add to Bag']");

      

    //  Sync.waitElementPresent("xpath", "//a[@class='action showcart desktop_only']");

      //Common.clickElement("xpath", "//a[@class='action showcart desktop_only']");

      

      //Sync.waitElementPresent("xpath", "//button[@title='Proceed to Checkout']");

      //Common.clickElement("xpath", "//button[@title='Proceed to Checkout']");
    


public void Close_popup() {
try {
		  Thread.sleep(2000); 
		  if (Common.findElement("xpath","(//div[@id='wpn-lightbox-close-newsletter'])") != null) {
		  
		  Common.clickElement("xpath", "(//div[text()='X'])"); 
		  }
		  }catch
		  (Exception e) {
		  e.printStackTrace(); 
		  }
}
		 
	
	
public void Verify_PDP() {

	try {
		String verifyaddtobag = Common.findElement("xpath", "(//button[@class='action primary tocart'])")
				.getAttribute("title");
		//Accept();
		String Title = Common.getPageTitle();
		System.out.println(Title);
	   // Close_popup();
		//Common.assertionCheckwithReport(verifyaddtobag.equals("24k-gold-one-step-blowout"), "Verifying PDP page",
				//"it shoud navigate to PDP page", "successfully  navigated to PDP Page", "PDP Page");
	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("To verify the  the PDP Page", "Should land on PDP page",
				"user unable to land on PDP page", Common.getscreenShotPathforReport("failed to land on PDP page"));
		Assert.fail();
	}
}
 public void Verify_Single_USPS_Ground_Shpping_Method(){

	    try {
	    	 Sync.waitPageLoad();
	    	 Common.clickElement("xpath", "(//div[@class='step-title'])[2]");
	    	 Thread.sleep(3000);
	    	 Common.actionsKeyPress(Keys.END);
	          Thread.sleep(2000);
	          Common.clickElement("xpath", "(//label[@for='s_method_flatrate'])");
	        String Verify_Shipping_Method=Common.findElement("xpath", "(//td[@id='label_method_flatrate_flatrate'])").getAttribute("id");
	        String Title= Common.getPageTitle();
	  		System.out.println(Title);
	  		Common.assertionCheckwithReport(Verify_Shipping_Method.equals("label_method_flatrate_flatrate"),"Verifying Shipping Method for Aerosol Product with PO Box Shipping Address","Shoud display single USPS Ground shipping method ", "successfully displayed Single USPS Ground Shipping method", "Shipping methods");
	   }catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("Verifying Shipping Method for Aerosol Product with PO Box Shipping Address","Shoud display single USPS Ground shipping method ", "Failed to display Single USPS Ground Shipping method", Common.getscreenShotPathforReport("failed to display Single USPS Ground method"));			
			Assert.fail();	
			}
		}
 
 
 
 public void Verify_UPS_TwoDay_and_UPSNextDay_Shpping_Methods(){

	    try {
	    	 Sync.waitPageLoad();
	    	 Common.clickElement("xpath", "(//div[@class='step-title'])[2]");
	    	 Thread.sleep(3000);
	    	 Common.actionsKeyPress(Keys.END);
	          Thread.sleep(2000);
	         String Price= Common.findElement("xpath", "(//span[@class='price'])[1]").getText(); 
	       // String Verify_Shipping_Method=Common.findElement("xpath", "(//div[@id='checkout-shipping-method-load'])").getAttribute("id");
	        String Title= Common.getPageTitle();
	  		System.out.println(Title);
	  		Common.assertionCheckwithReport(Price.equals("$15.00"),"Verifying Shipping Method for Aerosol Product with PO Box Shipping Address","Shoud display single USPS Ground shipping method ", "successfully displayed Single USPS Ground Shipping method", "Shipping methods");
	   }catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("Verifying Shipping Method for Aerosol Product with PO Box Shipping Address","Shoud display single USPS Ground shipping method ", "Failed to display Single USPS Ground Shipping method", Common.getscreenShotPathforReport("failed to display Single USPS Ground method"));			
			Assert.fail();	
			}
		}
 
 public void Verify_Single_UPS_Ground_Shpping_Method(){

	    try {
	    	 Sync.waitPageLoad();
	    	 Common.clickElement("xpath", "(//div[@class='step-title'])[2]");
	    	 Thread.sleep(3000);
	    	 Common.actionsKeyPress(Keys.END);
	          Thread.sleep(2000);
	          Common.clickElement("xpath", "(//label[@for='s_method_bestway'])");
	        String Verify_Shipping_Method=Common.findElement("xpath", "(//td[@id='label_carrier_bestway_tablerate'])").getText();
	        String Title= Common.getPageTitle();
	  		System.out.println(Title);
	  		Common.assertionCheckwithReport(Verify_Shipping_Method.equals("5 - 7 Business Days (Monday-Friday)"),"Verifying Shipping Method for Aerosol and Non-Aerosol Products with Non-PO Box Shipping Address","Shoud display single UPS Ground shipping method ", "successfully displayed Single UPS Ground Shipping method", "Shipping methods");
	   }catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("Verifying Shipping Method for Aerosol and Non-Aerosol Products with Non-PO Box Shipping Address","Shoud display single UPS Ground shipping method ", "Failed to display Single USPS Ground Shipping method", Common.getscreenShotPathforReport("failed to display Single UPS Ground method"));			
			Assert.fail();	
			}
		}
 
 public void Verify_No_Shipping_Method(){

	    try {
	        Common.clickElement("xpath", "(//div[@class='step-title'])[2]");
	        Thread.sleep(3000);
	    	Common.actionsKeyPress(Keys.END);
	    	Thread.sleep(3000);
	    	int NoShippingAddressMessage=Common.findElements("xpath", "(//div[@class='no-quotes-block'])[1]").size();
	        Common.assertionCheckwithReport(NoShippingAddressMessage>0, "To verify Shipping Methods", "Should display Zero Shipping methods","Sucessfully displayed Zero shipping methods", "faield to display zero shipping methods");
		}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify Shipping Methods","Shoud display Zero shipping method ", "Failed to display zero Shipping method", Common.getscreenShotPathforReport("failed to display zero shipping method"));			
			Assert.fail();	
			}
		}
 
 
 
 public void Verify_tax(){

	    try {
	    	 Sync.waitPageLoad();
	          Thread.sleep(6000);
	        String verifyTax=Common.findElement("xpath", "(//th[text()='Tax'])").getText();
	        String Title= Common.getPageTitle();
	  		System.out.println(Title);
	  		Common.assertionCheckwithReport(verifyTax.equals("Tax"),"Verifying tax in Payment page","Should display tax at right of the Payment details page", "successfully  displayed tax at right of the Payment details page", "Payment Page");
	   }catch(Exception |Error e) {
		   e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Verifying tax in Payment page","Should display tax at right of the  Payment details page", "user unable to land on Payment page", Common.getscreenShotPathforReport("failed to land on Payment page"));			
			Assert.fail();	
			}
		}
 
 
 
 public void Verify_HoT_Employee_PDP(){

	    try {
	    	 Sync.waitPageLoad();
	          Thread.sleep(5000);
	        String DiscountPrice=Common.findElement("xpath", "(//span[contains(text(),'$90.00')])[1]").getText();
	        String Title= Common.getPageTitle();
	        Thread.sleep(2000);
	  		System.out.println(Title);
	  		Common.assertionCheckwithReport(DiscountPrice.equals("$90.00"),"Verifying PDP page of HoT Employee","It shoud navigate to PDP page with displaing Discount Price", "successfully  navigated to PDP Page of HoT Employee with displaying Discount Price", "PDP Page");
	   }catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("Verifying PDP page of HoT Employee","It shoud navigate to PDP page with displaing Discount Price", "Failed display HoT Employee PDP with  Discount Price", Common.getscreenShotPathforReport("failed to land on PDP page"));			
			Assert.fail();	
			}
		}
 
 
 public void Verify_Guest_Discount_PDP(){

	    try {
	    	 Sync.waitPageLoad();
	          Thread.sleep(2000);
	        String DiscountPrice=Common.findElement("xpath", "(//span[@class='price'])[1]").getText();
	        String Title= Common.getPageTitle();
	  		System.out.println(Title);
	  		Common.assertionCheckwithReport(DiscountPrice.equals("$90.00"),"Verifying PDP page of HoT Employee","It shoud navigate to PDP page with displaing Discount Price", "successfully  navigated to PDP Page of HoT Employee with displaying Discount Price", "PDP Page");
	   }catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("Verifying PDP page of HoT Employee","It shoud navigate to PDP page with displaing Discount Price", "Failed display HoT Employee PDP with  Discount Price", Common.getscreenShotPathforReport("failed to land on PDP page"));			
			Assert.fail();	
			}
		}
 
 
 public void Verify_GeneralUser_PDP(){

	    try {
	    	 Sync.waitPageLoad();
	          Thread.sleep(5000);
	        String Price=Common.findElement("xpath", "(//span[@class='price'])[1]").getText();
	        String Title= Common.getPageTitle();
	  		System.out.println(Title);
	  		Common.assertionCheckwithReport(Price.equals("$150.00"),"Verifying Product price for General user in PDP page","It should display Product price in PDP without any discount", "successfully displayed Product price in PDP without any discount");
	   }catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("Verifying Product price for General user in PDP page","It should display Product price in PDP without any discount", "Failed to  display Product price in PDP without any discount", Common.getscreenShotPathforReport("failed to land on 404 PDP page"));			
			Assert.fail();	
			}
		}
 
 public static String getEmailid() {
		String Email=null;
		try {
			long currentTimestamp = System.currentTimeMillis();
			 Email ="newuser"+currentTimestamp+"@gmail.com";
		}

		catch (Exception e) {

			e.printStackTrace();
		}
		return Email;
	}
   

  // Sync.waitElementPresent("xpath", "//button[@title='Add to Bag']");

  // Common.clickElement("xpath", "//button[@title='Add to Bag']");

   

  // Sync.waitElementPresent("xpath", "//a[@class='action showcart desktop_only']");

  // Common.clickElement("xpath", "//a[@class='action showcart desktop_only']");

   

 //  Sync.waitElementPresent("xpath", "//button[@title='Proceed to Checkout']");

  // Common.clickElement("xpath", "//button[@title='Proceed to Checkout']");
   //Sync.waitPageLoad();
   //Sync.waitElementPresent("xpath", "(//a[@class='link'])");

  // Common.clickElement("xpath", "(//a[@class='link'])");
 
 
 
 public void Select_Searched_Product() {
	 try {
	        Sync.waitPageLoad(); 
	         Close_popup(); 
	          //Accept();
	        Thread.sleep(5000);
	        Sync.waitElementPresent("xpath", "(//a[text()=' The Double Shot Oval Blow-Dryer Brush '])");
	        Thread.sleep(2000);
	        Common.mouseOver("xpath", "(//a[text()=' The Double Shot Oval Blow-Dryer Brush '])");
	        Thread.sleep(5000);
	        Common.clickElement("xpath", "(//a[text()=' The Double Shot Oval Blow-Dryer Brush '])");
	        Sync.waitPageLoad();
	        Thread.sleep(4000);
	        Sync.waitElementPresent("xpath", "(//button[@class='action primary tocart'])");
	        Thread.sleep(1000);
	        Common.actionsKeyPress(Keys.ARROW_DOWN);
	        String AddtoBag = Common.findElement("xpath", "(//button[@class='action primary tocart'])")
	                .getAttribute("title");
	        Common.assertionCheckwithReport(AddtoBag.equals("Add to Bag"), "Verifying PDP page",
	                "it shoud navigate to PDP page", "successfully  navigated to PDP Page", "PDP Page");
	    } catch (Exception | Error e) {
	    	e.printStackTrace();
	        ExtenantReportUtils.addFailedLog("To verify the  the PDP Page", "Should land on PDP page",
	                "user unable to land on PDP page", Common.getscreenShotPathforReport("failed to land on PDP page"));
	        Assert.fail();
	    }
	}
 
 
 
 
 
 
 

 /*public void Click_View_Product(){

	    try {
	    	 Sync.waitPageLoad();
	    	 Common.actionsKeyPress(Keys.ARROW_DOWN);
	          Thread.sleep(5000);
	          Common.clickElement("xpath", "(//span[text()='View Product'])[1]");
	         Sync.waitPageLoad();
	         Common.actionsKeyPress(Keys.ARROW_DOWN);
	         String AddtoBag=Common.findElement("xpath", "(//button[@class='action primary tocart'])").getAttribute("title");
	        Common.assertionCheckwithReport(AddtoBag.equals("Add to Bag"),"Verifying PDP page","it shoud navigate to PDP page", "successfully  navigated to PDP Page", "PDP Page");  
	         }catch(Exception |Error e) {		
	        	 ExtenantReportUtils.addFailedLog("To verify the  the PDP Page","Should land on PDP page", "user unable to land on PDP page", Common.getscreenShotPathforReport("failed to land on PDP page"));	
	        	 Assert.fail();			
	        	 } 
	    }*/

 public void View_Product(){

	    try {
	    	 Sync.waitPageLoad();
	    	 Common.actionsKeyPress(Keys.ARROW_DOWN);
	          Thread.sleep(2000);
	          Common.clickElement("xpath", "(//span[text()='View Product'])[1]");
	         Sync.waitPageLoad();
	         //Common.actionsKeyPress(Keys.ARROW_DOWN);
	         String AddtoBag=Common.findElement("xpath", "(//button[@class='action primary tocart'])").getAttribute("title");
	         Common.assertionCheckwithReport(AddtoBag.equals("Add to Bag"),"Verifying PDP page","it shoud navigate to PDP page", "successfully  navigated to PDP Page", "PDP Page");  
	         }catch(Exception |Error e) {		
	        	 ExtenantReportUtils.addFailedLog("To verify the  the PDP Page","Should land on PDP page", "user unable to land on PDP page", Common.getscreenShotPathforReport("failed to land on PDP page"));	
	        	 Assert.fail();			
	        	 } 
	    }   
		

public void search(String dataset) {
	try {
		Sync.waitElementPresent("xpath", "//*[@id=\"search_mini_form\"]/div/label");
		Common.clickElement("xpath", "//*[@id=\"search_mini_form\"]/div/label");
		Sync.waitElementPresent("xpath", "(//input[@id='search'])");
		Common.textBoxInput("id", "search",data.get(dataset).get("vicksproductname"));
		ExtenantReportUtils.addPassLog("To verify the search functionality with full productname","should get the product name in search field","user  successfully Entered the productname", Common.getscreenShotPathforReport("Searched productname Successfully"));
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the search functionality with fullproduct name","Should get the productname in search field","user unable to Enter the productname", Common.getscreenShotPathforReport("Failed to search proudctname"));			
		Assert.fail();	
		}

try {
	    Common.actionsKeyPress(Keys.ENTER);
	    Thread.sleep(2000);
	   // Common.scrollIntoView("xpath", "(//h3[@class='result-title text-ellipsis'])[1]");
	    ExtenantReportUtils.addPassLog("To verify search results page","Should land on  product list page","user successfully landed on  product search results page", Common.getscreenShotPathforReport("Successfully landed on PLP Page"));
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify search results page","Should land on  product list page","user successfully landed on  product search results page", Common.getscreenShotPathforReport("Failed to land on PLP Page"));			
		Assert.fail();	
		}
	}


public void Enter_EmployeeDisdount_productname(String dataset) {
	
	try {
		Sync.waitElementPresent("xpath", "(//span[@class='search-link top-link'])");
		Common.clickElement("xpath", "(//span[@class='search-link top-link'])");
	    Sync.waitPageLoad();
		Common.textBoxInput("id", "search",data.get(dataset).get("ProductName"));
		Thread.sleep(5000);
		Common.actionsKeyPress(Keys.ENTER);
	     Thread.sleep(5000);
	     ExtenantReportUtils.addPassLog("verifying product search results page","User should land on Product list page with Displaying zero products","user successfully land on product search results page with displaying Zero Products", Common.getscreenShotPathforReport("Successfully landed on PLP Page with displaying Zero products"));
			
		}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("verifying product search results page","User should land on product list page with Displaying zero products ","user successfully land on product list page of searched product with Displaying zero products", Common.getscreenShotPathforReport("Failed to land on PLP Page"));			
			Assert.fail();	
			}
		}	

public void Enter_EmployeeDisdount_productname_In_HyattGroupAcc(String dataset) {
	
	try {
		Sync.waitElementPresent("xpath", "(//span[@class='search-link top-link'])");
		Common.clickElement("xpath", "(//span[@class='search-link top-link'])");
	    Sync.waitPageLoad();
		Common.textBoxInput("id", "search",data.get(dataset).get("ProductName"));
		Thread.sleep(2000);
		Common.actionsKeyPress(Keys.ENTER);
	     Sync.waitPageLoad();	
	     ExtenantReportUtils.addPassLog("verifying product search results page","User should land on Product list page with Displaying products","user successfully land on product search results page with displaying Products", Common.getscreenShotPathforReport("Successfully landed on PLP Page with displaying products"));
			
		}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("verifying product search results page","User should land on product list page with Displaying products ","user successfully land on product list page of searched product with Displaying products", Common.getscreenShotPathforReport("Failed to land on PLP Page"));			
			Assert.fail();	
			}
		}	

		
public void Search_productname(String dataset) {
	
	try {
		Sync.waitElementPresent("xpath", "//img[@alt='search icon']");
		Common.clickElement("xpath", "//img[@alt='search icon']");
	    Sync.waitPageLoad();
		Common.textBoxInput("id", "search",data.get(dataset).get("ProductName"));
		Thread.sleep(2000);
		ExtenantReportUtils.addPassLog("verifying the search functionality","Should get the  product name in search field","user successfully Entered the product name in search field", Common.getscreenShotPathforReport("searched productname successfully"));
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog(" To verify the search functionality","should get the product name in search field along with suggested products","user successfully Entered the product name in search field", Common.getscreenShotPathforReport("Failed to search the product"));			
		Assert.fail();	
		}
try {
		Common.actionsKeyPress(Keys.ENTER);
	     
	     Common.scrollIntoView("xpath", "(//div[@class='result-content'])[1]");
	    String viewcartbutton=Common.findElement("xpath", "(//h3[@itemprop='name'])[1]").getText();
	    String pname=Common.getText("xpath", "(//h3[@itemprop='name'])[1]") ;
	   Common.assertionCheckwithReport(viewcartbutton.equals(pname), "verifying the viewcartbutton", "navigate to PLP Page", "user successfully navigate to the viewcartbutton", "Failed to navigate to vie cart button");
	   System.out.println(viewcartbutton);
	   
	  ExtenantReportUtils.addPassLog("verifying product search results page","User should land on Product list page","user successfully land on product search results page of searched product", Common.getscreenShotPathforReport("Successfully landed on PLP Page"));
		
	}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("verifying product search results page","User should land on product list page ","user successfully land on product list page of searched product", Common.getscreenShotPathforReport("Failed to land on PLP Page"));			
		Assert.fail();	
		}
	}	
public void Verify_OutofStockPDP(String dataSet){

    try {

Thread.sleep(5000);
String Pname= Common.getText("xpath", "//div[@class='page-title-wrapper product']");
System.out.println(Pname);
Thread.sleep(3000);

String OSS=Common.findElement("xpath","(//span[contains(text(),'Sorry, we’ve sold out!')])").getText();
Common.assertionCheckwithReport(OSS.equals("SORRY, WE’VE SOLD OUT!"), "To verify the PDP with out of stock", "Should land on out of stock PDP page","User unable to land on Out of Stock PDP", "faield to land on out of stock PDP page");
Thread.sleep(3000);
Sync.waitElementPresent("xpath", "//input[@name='guest_email']");
Common.textBoxInput("xpath", "//input[@name='guest_email']",Utils.getEmailid());
Thread.sleep(5000);
Sync.waitElementPresent("xpath", "//span[contains(text(), 'Subscribe')]");
Common.clickElement("xpath", "//span[contains(text(), 'Subscribe')]");

}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the  the PDP Page with out of stock","Should land on out of stock PDP page", "user unable to land on  Out of Stock PDP", Common.getscreenShotPathforReport("failed to land on out of stock PDP page"));			
		Assert.fail();	
		}
	}

public void Out_Of_Stock_Subscription(String DataSet){

    try {
          Sync.waitPageLoad();
          Common.actionsKeyPress(Keys.PAGE_DOWN);
          Sync.waitElementPresent("xpath", "(//input[@name='guest_email'])");
          Common.textBoxInput("xpath", "(//input[@name='guest_email'])",data.get(DataSet).get("Email"));
          Thread.sleep(3000);
         ExtenantReportUtils.addPassLog("To verify the Email text field", "Should enter the email address in the email text field", "user successfully entered the Email address", Common.getscreenShotPathforReport("Successfully Entered Email address"));

	//Common.assertionCheckwithReport(Addtobag.equals("Add to Bag"), "To verify the PDP Page", "Should land on  PDP page", "user successfully landed on PDP page", Common.getscreenShotPathforReport(""));
}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the Email text field","Should enter the email address in the email text field ", "user unable to Enter Email address", Common.getscreenShotPathforReport("failed to enter Email address"));			
		Assert.fail();	
		}
try {
    Common.clickElement("xpath", "(//button[@class='action submit primary'])"); 
    Thread.sleep(4000);
    int message=Common.findElements("xpath", "(//div[@class='message-success success message'])").size();

	 Common.assertionCheckwithReport(message>0, "To verify the product added to My Wishlist", "Should add product to  My wishlist page","Product sucessfully added to My wishlist", "faield to add product to Wishlist");

    }catch(Exception |Error e) {
	ExtenantReportUtils.addFailedLog("To verify the Email text field","Should enter the email address in the email text field ", "user unable to Enter Email address", Common.getscreenShotPathforReport("failed to enter Email address"));			
	Assert.fail();	
	}
}

public void RegisterUser_Out_Of_Stock_Subscription(){
	try {
	    Common.clickElement("xpath", "(//a[@title='Notify me when this product is in stock'])"); 
	    Thread.sleep(4000);
	    int message=Common.findElements("xpath", "(//div[@class='message-success success message'])").size();

		 Common.assertionCheckwithReport(message>0, "To verify the product added to My Wishlist", "Should add product to  My wishlist page","Product sucessfully added to My wishlist", "faield to add product to Wishlist");

	    }catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the Email text field","Should enter the email address in the email text field ", "user unable to Enter Email address", Common.getscreenShotPathforReport("failed to enter Email address"));			
		Assert.fail();	
		}
	}


public void Add_product_to_Wishlist() {
	   
	    try {
	        Thread.sleep(5000);
	        Sync.waitElementPresent("xpath", "((//div[@class='product-info-main'])//a)[1]");
	        Common.clickElement("xpath", "((//div[@class='product-info-main'])//a)[1]");
	        Thread.sleep(5000);
	        int message=Common.findElements("xpath", "(//div[@class='message-success success message'])").size();
	        Common.assertionCheckwithReport(message>0, "To verify the product added to My Wishlist", "Should add product to  My wishlist page","Product sucessfully added to My wishlist", "faield to add product to Wishlist");
	    }
	    catch(Exception |Error e) {
	        ExtenantReportUtils.addFailedLog("To verify the  the PDP Page with out of stock","Should land on ou of stock PDP page", "user unable to land on PDP page", Common.getscreenShotPathforReport("failed to land on out of stock PDP page"));           
	        Assert.fail();   
	        }
	   
	}
public void remove_from_wishlist() throws Exception {
	
	Sync.waitElementPresent("xpath", "(//a[text()='here'])");
	Common.clickElement("xpath", "(//a[text()='here'])");
	Sync.waitPageLoad();
	Thread.sleep(5000);
	Sync.waitElementPresent("xpath", "(//span[text()='Remove item'])");
	Common.clickElement("xpath", "(//span[text()='Remove item'])");
	int message=Common.findElements("xpath", "(//div[@class='message info empty'])").size();
    
	
}
public void My_Orders(){

    try {
    	Common.clickElement("xpath", "(//a[text()='My Orders'])");
    	Sync.waitPageLoad();
		String Title= Common.getPageTitle();
		Thread.sleep(5000);
	    System.out.println(Title);
		//Common.assertionCheckwithReport(Title.equals("MY ORDERS"),"Verifying My Orders page","it shoud navigate to My Orders page", "successfully  navigated to My Orders Page", "My Orders");	
		Thread.sleep(3000);
       
        }catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the My orders page","Should land on my orders  page", "user unable to land on my orders page", Common.getscreenShotPathforReport("failed to land on my orders page"));			
		Assert.fail();	
		}
	}
public void Wishlist(){

    try {
    	Common.clickElement("xpath", "(//a[text()='Wish List'])");
    	Sync.waitPageLoad();
		String Title= Common.getPageTitle();
		Thread.sleep(5000);
		Common.assertionCheckwithReport(Title.equals("My Wish List | Hot Tools"),"Verifying My Wishlist page","it shoud navigate to My Wishlist page", "successfully  navigated to My Wishlist Page", "My Wishlist");	
		Thread.sleep(5000);
    
    }catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the My wishlist page","Should land on my wishlist  page", "user unable to land on my wishlist page", Common.getscreenShotPathforReport("failed to land on my wishlist page"));			
		Assert.fail();	
		}
	}
public void AddressBook(){

    try {
    	Common.clickElement("xpath", "(//a[text()='Address Book'])");
    	Sync.waitPageLoad();
		String Title= Common.getPageTitle();
		Thread.sleep(5000);
		System.out.println(Title);
	//	Common.assertionCheckwithReport(Title.equals("Address Book | Hot Tools"),"Verifying AddressBook page","it shoud navigate to AddressBook page", "successfully  navigated to Address Book Page", "AddressBook");	
		Thread.sleep(5000);
    }catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the AddressBook page","Should land on AddressBook  page", "user unable to land on AddressBook page", Common.getscreenShotPathforReport("failed to land on AddressBook page"));			
		Assert.fail();	
		}
	}

public void AccountInformation(){

    try {
    	Common.clickElement("xpath", "(//a[text()='Account Information'])");
    	Sync.waitPageLoad();
		String Title= Common.getPageTitle();
		Thread.sleep(5000);
	       System.out.println(Title);
		//Common.assertionCheckwithReport(Title.equals("Account Information | Hot Tools"),"Verifying Account information page","it shoud navigate to Account informaion page", "successfully  navigated to Account information Page", "Account information");
		Thread.sleep(5000);
    }catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the Account Information page","Should land on Account Information  page", "user unable to land on Account Information page", Common.getscreenShotPathforReport("failed to land on Account Information page"));			
		Assert.fail();	
		}
	}
public void change_Email(String dataSet) throws Exception {
	try {
		Sync.waitElementPresent("id", "change-email");
		Common.clickElement("id", "change-email");
		Sync.waitElementPresent("id", "email");
		Common.textBoxInput("id", "email",data.get(dataSet).get("Email"));
		Sync.waitElementPresent("id", "current-password");
		Common.textBoxInput("id", "current-password",data.get(dataSet).get("Password"));
		ExtenantReportUtils.addPassLog("To verify Whether the New Email  is Entered or not", "Should Enter the New Email in the TextField", "user successfully Entered the new Email in Textfield", Common.getscreenShotPathforReport("Successfully Entered New Email"));

		//Common.assertionCheckwithReport(Addtobag.equals("Add to Bag"), "To verify the PDP Page", "Should land on  PDP page", "user successfully landed on PDP page", Common.getscreenShotPathforReport(""));
	}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify whether the New Email is Entered or not ","Should enter the new email in textfield", "user unable to Enter New Email", Common.getscreenShotPathforReport("failed to enter new Email"));			
			Assert.fail();	
			}
		
try {
	Common.clickElement("xpath", "(//button[@title='Save'])");
	int message=Common.findElements("xpath", "(//div[@class='message-success success message'])").size();

	 Common.assertionCheckwithReport(message>0, "To change the Email Address", "Should  change Email Address","Successfully changed the Email Address", "faield to change Email address");
}catch(Exception |Error e) {
	ExtenantReportUtils.addFailedLog("To change the Email Address","Should Change Email Address", "user unable change Email Address", Common.getscreenShotPathforReport("failed to change Email"));			
	Assert.fail();	
	}
}

public void change_Password(String dataSet) throws Exception {
	try {
		Sync.waitElementPresent("id", "change-password");
		Common.clickElement("id", "change-password");
		Sync.waitElementPresent("xpath", "(//input[@id='current-password'])");
		Common.textBoxInput("xpath", "(//input[@id='current-password'])",data.get(dataSet).get("Password"));
		Sync.waitElementPresent("xpath", "(//input[@id='password'])");
		Common.textBoxInput("xpath", "(//input[@id='password'])",data.get(dataSet).get("Password"));
		Sync.waitElementPresent("xpath", "(//input[@id='password-confirmation'])");
		Common.textBoxInput("xpath", "(//input[@id='password-confirmation'])",data.get(dataSet).get("Password"));
		ExtenantReportUtils.addPassLog("To verify Whether the New Password  is Entered or not", "Should Enter the New password in the TextField", "user successfully Entered the new password in Textfield", Common.getscreenShotPathforReport("Successfully Entered New Password"));

		//Common.assertionCheckwithReport(Addtobag.equals("Add to Bag"), "To verify the PDP Page", "Should land on  PDP page", "user successfully landed on PDP page", Common.getscreenShotPathforReport(""));
	}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify whether the New Password is Entered or not ","Should enter the new password in textfield", "user unable to Enter New Password", Common.getscreenShotPathforReport("failed to enter new password"));			
			Assert.fail();	
			}
		
try {
	Common.clickElement("xpath", "(//button[@title='Save'])");
	int message=Common.findElements("xpath", "(//div[@class='message-success success message'])").size();

	 Common.assertionCheckwithReport(message>0, "To change the password of the user", "Should  change the password od the user","Successfully changed the Password", "faield to change the password");
}catch(Exception |Error e) {
	ExtenantReportUtils.addFailedLog("To change the password of the user","Should Change password of the user", "user unable change password", Common.getscreenShotPathforReport("failed to change the password"));			
	Assert.fail();	
	}
}
	
public void Add_NewAddress(String dataSet) throws Exception {
	
	try {
		Sync.waitPageLoad();
	Common.scrollIntoView("xpath","(//button[@title='Add New Address'])");
	Common.clickElement("xpath","(//button[@title='Add New Address'])");
	
	Sync.waitElementPresent("xpath", "//input[@name='firstname']");
	Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(dataSet).get("FirstName"));
	
	Sync.waitElementPresent("xpath", "//input[@name='lastname']");
	Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));
	
	/*Sync.waitElementPresent("xpath", "//input[@name='lastname']");
	Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));*/
	
	Sync.waitElementPresent("xpath", "//input[@name='company']");
	Common.textBoxInput("xpath", "//input[@name='company']", data.get(dataSet).get("CompanyName"));
	
	Sync.waitElementPresent("xpath", "//input[@name='telephone']");
	Common.textBoxInput("xpath", "//input[@name='telephone']", data.get(dataSet).get("phone"));
	
	

	Sync.waitElementPresent("xpath", "//input[@name='street[]'][1]");
	Common.textBoxInput("xpath", "//input[@name='street[]'][1]", data.get(dataSet).get("Street"));
	
/*	
	Sync.waitElementPresent("xpath", "//select[@name='country_id']");
	Common.dropdown("xpath", "//select[@name='country_id']", SelectBy.TEXT, data.get(dataSet).get("Countryname"));*/
	
	
	Sync.waitElementPresent("xpath", "//select[@name='region_id']");
	Common.dropdown("xpath", "//select[@name='region_id']", SelectBy.TEXT, data.get(dataSet).get("Region"));
	
	Sync.waitElementPresent("xpath", "//input[@name='city']");
	Common.textBoxInput("xpath", "//input[@name='city']", data.get(dataSet).get("City"));
	
	Sync.waitElementPresent("xpath", "//input[@name='postcode']");
	Common.textBoxInput("xpath", "//input[@name='postcode']", data.get(dataSet).get("postcode"));
	
	ExtenantReportUtils.addPassLog("To verify Whether the New addres Entered or not", "Should Enter the New Address in the TextField", "user successfully Entered the new Address in Textfield", Common.getscreenShotPathforReport("Successfully Entered New Address"));

	//Common.assertionCheckwithReport(Addtobag.equals("Add to Bag"), "To verify the PDP Page", "Should land on  PDP page", "user successfully landed on PDP page", Common.getscreenShotPathforReport(""));
}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify whether the New Address is Entered or not ","Should enter the new Address in textfield", "user unable to Enter New Address", Common.getscreenShotPathforReport("failed to enter new Address"));			
		Assert.fail();	
		}
	
try {
Common.clickElement("xpath", "(//button[@title='Save Address'])");
int message=Common.findElements("xpath", "(//div[@class='message-success success message'])").size();

 Common.assertionCheckwithReport(message>0, "To add the new address for the user", "Should add the address for the user","Successfully added the new Address", "faield to add New Address");
}catch(Exception |Error e) {
ExtenantReportUtils.addFailedLog("To add the new Address for the user","Should add the new Addrss for the user", "user unable to Add new Address", Common.getscreenShotPathforReport("failed to add New Address"));			
Assert.fail();	
}
}

public void SelectcategoryProduct() throws Exception {
	 
	 String expectedResult="Select from  category" ;
		try {

			Sync.waitElementPresent("xpath", "//span[text()='Hair Tools']");
			Common.mouseOver("xpath", "//span[text()='Hair Tools']");
			
			Sync.waitElementPresent("xpath", "(//span[contains(text(), 'One-Step Products')])[1]");
			Common.clickElement("xpath", "(//span[contains(text(), 'One-Step Products')])[1]");
			
			Sync.waitPageLoad();
			Thread.sleep(4000);
			String plp=Common.getText("xpath","//div[@class='page-title-wrapper']");
			System.out.println(plp);
			//Close_popup();
			report.addPassLog(expectedResult, "Should display item from  menucategory", "product display successfully", Common.getscreenShotPathforReport(" product display success"));

		}catch(Exception e)
		{
			report.addFailedLog(expectedResult,"Should display Search Results Page", "Search results Page not display", Common.getscreenShotPathforReport("Search result Failed"));
			e.printStackTrace();
			Assert.fail();
		}
}

public void product_add_to_cart() {
	
	try{
		Sync.waitPageLoad();
		Thread.sleep(5000);
	    Common.scrollIntoView("xpath", "//a[contains(text(), '24K Gold One-Step Blowout ')]");
	    Thread.sleep(5000);
		Sync.waitElementPresent("xpath", "//a[contains(text(), '24K Gold One-Step Blowout ')]");
	Common.clickElement("xpath", "//a[contains(text(), '24K Gold One-Step Blowout ')]");
	Sync.waitPageLoad();
	Thread.sleep(3000);
	   // Common.mouseOver("xpath", "(//img[@class='product-image-photo'])[1]");
	   
	    Sync.waitElementPresent("xpath", "(//button[@title='Add to Bag'])[1]");
		Common.mouseOverClick("xpath", "(//button[@title='Add to Bag'])[1]");
		Thread.sleep(5000);
		Common.actionsKeyPress(Keys.PAGE_UP);
		Thread.sleep(5000);
		int message=Common.findElements("xpath", "(//div[@class='message-success success message'])").size();

		 Common.assertionCheckwithReport(message>0, "To verify the product added to My Wishlist", "Should add product to  My wishlist page","Product sucessfully added to My wishlist", "faield to add product to Wishlist");
	
		//ExtenantReportUtils.addPassLog("verifying add to cart button in Homepage", "User click add to card button on Homepage", "user successfully click add to cart button in Homepage", Common.getscreenShotPathforReport("faieldtoclickaddtocartbuttonin Homepage"));
		}
		catch(Exception |Error e) {
		   
			ExtenantReportUtils.addFailedLog("verifying add to cart button", "User click add to card button", "user faield to click add to cart button", Common.getscreenShotPathforReport("faieldtoclickutton"));
			Assert.fail();
		}
		
	}


public void PaymentMethods(){

    try {
    	Common.clickElement("xpath", "(//a[text()='Payment Methods'])");
    	Sync.waitPageLoad();
		String Title= Common.getPageTitle();
		Thread.sleep(5000);
		Common.assertionCheckwithReport(Title.equals("Payment Methods | Drybar"),"Verifying Payment Methods page","it shoud navigate to Payment Methods page", "successfully  navigated to Payment Methods Page", "Payment Methods");
		Thread.sleep(5000);   
    }catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the Payment Methods page","Should land on Payment Methods  page", "user unable to land on Payment Methods page", Common.getscreenShotPathforReport("failed to land on Payment Methods page"));			
		Assert.fail();	
		}
	}

public void Communication_Preferences(){

    try {
		Common.clickElement("xpath","(//a[text()='Communication Preferences'])");
		Sync.waitPageLoad();
		 String Title=Common.getPageTitle();
		 Thread.sleep(5000);
		Common.assertionCheckwithReport(Title.equals("Communication Preferences | Hot Tools"),"Verifying Communication preferences page","it should navigate to communication preferences page", "successfully  navigated to Communication preferences page", "Communication preferences");		
		Thread.sleep(5000);
    }
    
    	catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the Communication Preferences page","Should land on Communication Preferences  page", "user unable to land on Communication Preferences page", Common.getscreenShotPathforReport("failed to land on Communication Preferences page"));			
		Assert.fail();	
		}
	}

public void Notifications(){

	 

    try {
        Sync.waitPageLoad();
        Thread.sleep(4000);
        Common.clickElement("xpath","(//a[text()='Notifications'])");
        Sync.waitPageLoad();
         String Title=Common.getPageTitle();
         Thread.sleep(5000);
        Common.assertionCheckwithReport(Title.equals("Notifications / My Account | Hot Tools"),"Verifying Notifications page","it should navigate to Notifications page", "successfully  navigated to Notifications page", "Notifications");        
         Thread.sleep(3000);
    }
    
        catch(Exception |Error e) {
        ExtenantReportUtils.addFailedLog("To verify the Notifications page","Should land on Notifications  page", "user unable to land on Notifications page", Common.getscreenShotPathforReport("failed to land on Notifications page"));            
        Assert.fail();    
        }
    }
public void Subscribe_To_Communication_preferences(){

    try {
    	Close_popup();
    	Sync.waitElementPresent("id", "subscription");
         Common.clickElement("id", "subscription");
          Thread.sleep(3000);
          Common.clickElement("xpath", "(//button[@type='submit'])[1]");
          String message=Common.findElement("xpath", "(//div[@class='message-success success message'])").getText();
          message.equals("We have saved your subscription.");
         ExtenantReportUtils.addPassLog("To verify the Communication Preferences subscription message", "Should land on my account page with subscription", "user successfully landed on my acount page with subscription", Common.getscreenShotPathforReport("Successfully subscribed to Communication Preferences"));

	//Common.assertionCheckwithReport(Addtobag.equals("Add to Bag"), "To verify the PDP Page", "Should land on  PDP page", "user successfully landed on PDP page", Common.getscreenShotPathforReport(""));
}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the Communication Preferences subscription message","Should land on my naccount page with subscription", "user unable subscribe to Communication Preferences page", Common.getscreenShotPathforReport("failed to subscribe to Communication Preferences"));			
		Assert.fail();	
		}
	}

public void Click_Wishlist(){

    try {
    	Sync.waitElementPresent("xpath", "(//a[text()='Wish List'])");
         Common.clickElement("xpath", "(//a[text()='Wish List'])");
          Sync.waitPageLoad();
         String Title=Common.getPageTitle();
	Common.assertionCheckwithReport(Title.equals("My Wish List | Drybar"), "To verify the My Wishlist Page", "Should land on  Wishlist page", "user successfully landed on Wishlist page", Common.getscreenShotPathforReport("Wishlist"));
}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the My Wishlist page","Should land on My wishlist page", "user unable to land on My Wishlist page", Common.getscreenShotPathforReport("failed to land on My Wishlist page"));			
		Assert.fail();	
		}
	}

public void Wishlist_AddtoBag() {
	try {
		Sync.waitPageLoad();
		//Common.mouseOver("xpath", "(//div[@class='product-item-info'])[1]");
		Sync.waitElementPresent("xpath", "(//span[text()='Add to Bag'])[1]");
		Common.clickElement("xpath", "(//span[text()='Add to Bag'])[1]");
		ExtenantReportUtils.addPassLog("verifying add to cart button", "User click add to card button", "user successfully click add to cart button", Common.getscreenShotPathforReport("faieldtoclickutton"));
		
	
}catch(Exception |Error e) {
	   
		ExtenantReportUtils.addFailedLog("verifying add to cart button", "User click add to card button", "user faield to click add to cart button", Common.getscreenShotPathforReport("faieldtoclickutton"));
		Assert.fail();
	}
	
}

public void MyAccount() {
	
	try {
			Sync.waitElementPresent("xpath", "(//span[contains(text(),'Account')])[1]");
			Common.clickElement("xpath", "(//span[contains(text(),'Account')])[1]");
			Sync.waitPageLoad();
		Sync.waitElementClickable(30, By.xpath("//ul[@class='header links']//li[1]/a"));
		Common.findElement("xpath", "//ul[@class='header links']//li[1]/a").click();
		int sizeelement=Common.findElements("xpath", "//ul[@class='header links']//li[2]//a[text()='My Account']").size();
		if(sizeelement>0){
			Common.clickElement("xpath", "//ul[@class='header links']//li[2]//a[text()='My Account']");
		}
		Thread.sleep(3000);
	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying my account option", "clcik the my account button",
				"User failed to clcik the my account button",
				Common.getscreenShotPathforReport("my account button"));
		Assert.fail();

	
	}}
	

public void Signout() {
	
	try {
		
		
		Common.clickElement("xpath", "(//img[@alt='account icon'])[2]");
	Thread.sleep(3000);
		//Sync.waitElementClickable(30, By.xpath("//ul[@class='header links']//li[2]/a"));
		Common.findElement("xpath", "(//a[@class='logout-link'])[2]").click();
		Thread.sleep(7000);
		//Sync.waitPageLoad();
		//verifyingHomePage();
		Close_popup();
		
		
	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying Signout button functionality", "clcik the Signout button",
				"User failed to clcik the signout button",
				Common.getscreenShotPathforReport("signout button"));
		Assert.fail();

	
	}}
		



public void guestShipingAddress(String dataSet) throws Exception{
	try{
	Sync.waitElementPresent("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']");
	Common.textBoxInput("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']",data.get(dataSet).get("Email"));

	
	Sync.waitElementPresent("xpath", "//input[@name='firstname']");
	Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(dataSet).get("FirstName"));
	
	Sync.waitElementPresent("xpath", "//input[@name='lastname']");
	Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));
	
	/*Sync.waitElementPresent("xpath", "//input[@name='lastname']");
	Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));*/
	
	Sync.waitElementPresent("xpath", "//input[@name='company']");
	Common.textBoxInput("xpath", "//input[@name='company']", data.get(dataSet).get("CompanyName"));
	
	

	Sync.waitElementPresent("xpath", "//input[@name='street[0]']");
	Common.textBoxInput("xpath", "//input[@name='street[0]']", data.get(dataSet).get("Street"));
	
/*	
	Sync.waitElementPresent("xpath", "//select[@name='country_id']");
	Common.dropdown("xpath", "//select[@name='country_id']", SelectBy.TEXT, data.get(dataSet).get("Countryname"));*/
	
	Thread.sleep(3000);
	Sync.waitElementPresent("xpath", "//select[@name='region_id']");
	Common.dropdown("xpath", "//select[@name='region_id']", SelectBy.TEXT, data.get(dataSet).get("Region"));
	
	Sync.waitElementPresent("xpath", "//input[@name='city']");
	Common.textBoxInput("xpath", "//input[@name='city']", data.get(dataSet).get("City"));
	
	Sync.waitElementPresent("xpath", "//input[@name='postcode']");
	Common.textBoxInput("xpath", "//input[@name='postcode']", data.get(dataSet).get("postcode"));
	
	Sync.waitElementPresent("xpath", "//input[@name='telephone']");
	Common.textBoxInput("xpath", "//input[@name='telephone']", data.get(dataSet).get("phone"));
	Thread.sleep(3000);
	Common.actionsKeyPress(Keys.ENTER);
	Sync.waitElementPresent("xpath", "//span[text()='Next']");
	Common.clickElement("xpath", "//span[text()='Next']");
	
	}
	catch(Exception |Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying shipping addres filling", "user will fill the all the shipping", "faield to add new shipping address",Common.getscreenShotPathforReport("faieldsshippingpagefilling"));
		Assert.fail();
		
	}  
}
public void verify_Country() {
	try {
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Thread.sleep(1000);
		Common.clickElement("xpath", "//select[@name='country_id']");
		Thread.sleep(10000);
		ExtenantReportUtils.addPassLog("verifying Country in Shipping Address page", "Should display only Single country US ", "Successfully displayed Single country US", Common.getscreenShotPathforReport("FailedtodisplaysinglecountryUS"));
		//Common.dropdown("xpath", "//select[@name='country_id']", SelectBy.TEXT, data.get(dataSet).get("Countryname"));
		Thread.sleep(5000);
		}
		catch(Exception |Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying Country in Shipping Address page", "Should display only Single country US ", "faield to display Single country US",Common.getscreenShotPathforReport("FailedtodisplaysinglecountryUS"));
			Assert.fail();
			
		} }

public void verify_Billing_Country() {
	try {
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Thread.sleep(1000);
		Common.clickElement("xpath", "//fieldset[contains(@class,'fieldset')]//select[@name='country_id']");
		Thread.sleep(10000);
		ExtenantReportUtils.addPassLog("verifying Country in Shipping Address page", "Should display only Single country US ", "Successfully displayed Single country US", Common.getscreenShotPathforReport("FailedtodisplaysinglecountryUS"));
		//Common.dropdown("xpath", "//select[@name='country_id']", SelectBy.TEXT, data.get(dataSet).get("Countryname"));
		Thread.sleep(5000);
		}
		catch(Exception |Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying Country in Shipping Address page", "Should display only Single country US ", "faield to display Single country US",Common.getscreenShotPathforReport("FailedtodisplaysinglecountryUS"));
			Assert.fail();
			
		} }


public void guest_Out_of_US_ShipingAddress(String dataSet) throws Exception{
	try{
	Sync.waitElementPresent("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']");
	Common.textBoxInput("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']",data.get(dataSet).get("Email"));

	
	Sync.waitElementPresent("xpath", "//input[@name='firstname']");
	Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(dataSet).get("FirstName"));
	
	Sync.waitElementPresent("xpath", "//input[@name='lastname']");
	Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));
	
	/*Sync.waitElementPresent("xpath", "//input[@name='lastname']");
	Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));*/
	
	Sync.waitElementPresent("xpath", "//input[@name='company']");
	Common.textBoxInput("xpath", "//input[@name='company']", data.get(dataSet).get("CompanyName"));
	
	try {
		Thread.sleep(3000);
		Sync.waitElementPresent("xpath", "//select[@name='country_id']");
		Sync.scrollDownToView("xpath", "//select[@name='country_id']");
		Thread.sleep(5000);
		Common.clickElement("xpath", "//select[@name='country_id']");
		Thread.sleep(5000);
		ExtenantReportUtils.addPassLog("verifying Country in Shipping Address page", "Should display only Single country US ", "Successfully displayed Single country US", Common.getscreenShotPathforReport("FailedtodisplaysinglecountryUS"));
		//Common.dropdown("xpath", "//select[@name='country_id']", SelectBy.TEXT, data.get(dataSet).get("Countryname"));
		Thread.sleep(5000);
		}
		catch(Exception |Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying Country in Shipping Address page", "Should display only Single country US ", "faield to display Single country US",Common.getscreenShotPathforReport("FailedtodisplaysinglecountryUS"));
			Assert.fail();
			
		} 

	Sync.waitElementPresent("xpath", "//input[@name='street[0]']");
	Common.textBoxInput("xpath", "//input[@name='street[0]']", data.get(dataSet).get("Street"));
	Thread.sleep(3000);
	Sync.waitElementPresent("xpath", "//select[@name='country_id']");
	Common.clickElement("xpath", "//select[@name='country_id']");
	Thread.sleep(3000);
	Common.dropdown("xpath", "//select[@name='country_id']", SelectBy.TEXT, data.get(dataSet).get("Countryname"));
	
	Sync.waitElementPresent("xpath", "//select[@name='region_id']");
	Common.dropdown("xpath", "//select[@name='region_id']", SelectBy.TEXT, data.get(dataSet).get("Region"));
	
	Sync.waitElementPresent("xpath", "//input[@name='city']");
	Common.textBoxInput("xpath", "//input[@name='city']", data.get(dataSet).get("City"));
	
	Sync.waitElementPresent("xpath", "//input[@name='postcode']");
	Common.textBoxInput("xpath", "//input[@name='postcode']", data.get(dataSet).get("postcode"));
	
	Sync.waitElementPresent("xpath", "//input[@name='telephone']");
	Common.textBoxInput("xpath", "//input[@name='telephone']", data.get(dataSet).get("phone"));
	}
	catch(Exception |Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying shipping addres filling with out of US", "user will fill the all the shipping with out of US", "faield to add Out of US shipping address",Common.getscreenShotPathforReport("faieldsshippingpagefilling"));
		Assert.fail();
		
	}  
}


	
public void Expediated_shippingmethod(){

    try {
    	Thread.sleep(3000);
    	Common.actionsKeyPress(Keys.END);
    	Thread.sleep(3000);
    	Sync.waitElementPresent("xpath", "(//td[text()='Expedited'])");
    	Thread.sleep(5000);
    	Common.scrollIntoView("xpath", "(//td[text()='Expedited'])");
    	Thread.sleep(5000);
         Common.clickElement("xpath", "(//td[text()='Expedited'])");
          Thread.sleep(3000);
          ExtenantReportUtils.addPassLog("To verify the Expedited shipping method", "Should click on expediated shipping method", "user successfully clicked on expediated shipping method", Common.getscreenShotPathforReport("Successfully clicked on Expediated shipping method"));
      	//Common.assertionCheckwithReport(Addtobag.equals("Add to Bag"), "To verify the PDP Page", "Should land on  PDP page", "user successfully landed on PDP page", Common.getscreenShotPathforReport(""));
    }catch(Exception |Error e) {
    		ExtenantReportUtils.addFailedLog("To verify the Expediated shipping method","Should click on Expediated shipping method", "user unable to click on Expediated shipping method", Common.getscreenShotPathforReport("failed to click on expediated shipping method"));			
    		Assert.fail();	
    		}
}
public void click_Next() {
    try {
    	Thread.sleep(4000);
    	Common.actionsKeyPress(Keys.END);
    	Thread.sleep(10000);

    	   //click_Next();

    	   Sync.waitElementPresent("xpath", "(//span[contains(text(),'Next')])[2]");

    	Common.mouseOver("xpath","(//span[contains(text(),'Next')])[2]");
    	Thread.sleep(5000);
    	Common.clickElement("xpath", "(//span[contains(text(),'Next')])[2]");

    	       //div[contains(@class,'error')]

    	Sync.waitPageLoad();
          //String message=Common.findElement("xpath", "(//div[@class='message-success success message'])").getText();
          //message.equals("We have saved your subscription.");
         ExtenantReportUtils.addPassLog("To verify the payment page", "Should land on payment page", "user successfully landed on payment page", Common.getscreenShotPathforReport("Successfully land on payment page"));
Thread.sleep(6000);
	//Common.assertionCheckwithReport(Addtobag.equals("Add to Bag"), "To verify the PDP Page", "Should land on  PDP page", "user successfully landed on PDP page", Common.getscreenShotPathforReport(""));
}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify the payment page","Should land on payment page", "user unableto land on payment page", Common.getscreenShotPathforReport("failed to land on payment page"));			
		Assert.fail();	
		}
	}


public void Taxcalucaltion(String taxpercent) throws Exception{
	try{
	
		
	
	Thread.sleep(5000);
	
	//String taxpercent=data.get(dataset).get("Tax");
	 Float giventaxvalue=Float.valueOf(taxpercent);
	
	String subtotal=Common.getText("xpath", "//tr[@class='totals sub']/td/span").replace("$", "");
	 // subtotla.replace("", newChar)
	Float subtotalValue=Float.valueOf(subtotal);
	
	String shippingammount=Common.getText("xpath", "//span[@data-th='Shipping']").replace("$", "");
	Float shippingammountvalue=Float.valueOf(shippingammount);
	
	String TaxAmmount=Common.getText("xpath", "//td[@data-th='Tax']/span").replace("$", "");
	Float Taxammountvalue=Float.valueOf(TaxAmmount);
	
	String TotalAmmount=Common.getText("xpath", "//tr[@class='grand totals']//span").replace("$", "");
	Float Totalammountvalue=Float.valueOf(TotalAmmount);
	 Float Total=(subtotalValue+shippingammountvalue);
	
    Float calucaltedvalue= (Total*giventaxvalue)/100;
   // System.out.println(subtotalValue);
   // System.out.println(Total);
   // System.out.println(giventaxvalue);
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






public void select_CC(){
	try{
		Sync.waitPageLoad();
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Thread.sleep(4000);
		Sync.waitElementPresent("xpath", "(//input[@type='radio'])[5]");
		Common.mouseOverClick("xpath", "(//input[@type='radio'])[5]");
		
	}catch(Exception |Error e) {
		//ExtenantReportUtils.addFailedLog("To verify the payment page","Should land on payment page", "user unableto land on payment page", Common.getscreenShotPathforReport("failed to land on payment page"));			
		Assert.fail();	
		}
	}


public void Express_shippingmethod(){

    try {
    	Thread.sleep(3000);
    	Common.actionsKeyPress(Keys.END);
    	Thread.sleep(3000);
    	Sync.waitElementPresent("xpath", "(//td[text()='Express'])");
    	Thread.sleep(3000);
    	Common.scrollIntoView("xpath", "(//td[text()='Express'])");
    	Thread.sleep(5000);
         Common.clickElement("xpath", "(//td[text()='Express'])");
          Thread.sleep(3000);
          ExtenantReportUtils.addPassLog("To verify the Express shipping method", "Should click on express shipping method", "user successfully clicked on Express shipping method", Common.getscreenShotPathforReport("Successfully clicked on Express shipping method"));
      	//Common.assertionCheckwithReport(Addtobag.equals("Add to Bag"), "To verify the PDP Page", "Should land on  PDP page", "user successfully landed on PDP page", Common.getscreenShotPathforReport(""));
    }catch(Exception |Error e) {
    		ExtenantReportUtils.addFailedLog("To verify the Express shipping method","Should click on Express shipping method", "user unable to click on Express shipping method", Common.getscreenShotPathforReport("failed to click on express shipping method"));			
    		Assert.fail();	
    		}
}
  
public void Product_Review(String dataSet) throws Exception {

	try {
		Thread.sleep(3000);

		Sync.waitElementPresent("xpath", "(//button[@class='TTteaser__write-review'])");
		// Common.scrollIntoView("xpath", "(");
		Common.clickElement("xpath", "(//button[@class='TTteaser__write-review'])");

		Thread.sleep(5000);
		Common.clickElement("xpath", "(//span[contains(text(),'Select to rate 5 stars')])");

		Common.textBoxInput("id", "tt-review-form-text", data.get(dataSet).get("Review"));
		Common.textBoxInput("id", "tt-review-form-title", data.get(dataSet).get("Reviewtitle"));
		Common.clickElement("xpath", "(//span[contains(text(),'Select to rate 5 out of 5')])[1]");
		Common.clickElement("xpath", "(//span[contains(text(),'Select to rate 5 out of 5')])[2]");
		Thread.sleep(5000);
		Common.clickElement("xpath", "(//label[text()='No'])");
		Common.clickElement("xpath", "(//label[contains(text(),'Thick/Coarse')])");
		Common.dropdown("xpath", "(//div[@class='tt-o-selectbox'])//select", Common.SelectBy.TEXT,data.get(dataSet).get("Hairtype"));
		Sync.waitPageLoad();
		Common.clickElement("xpath", "(//label[contains(text(),'Thin')])");
		Common.clickElement("xpath", "(//label[contains(text(),'Long')])");
		Common.clickElement("xpath", "(//label[contains(text(),'Texture')])");
		Common.clickElement("xpath", "(//label[contains(text(),'Hot')])");

		ExtenantReportUtils.addPassLog("To verify the Product Review details", "Should enter the review details ",
				"user successfully entered the product review details ",
				Common.getscreenShotPathforReport("Successfully Entered product review detailis"));
		// Common.assertionCheckwithReport(Addtobag.equals("Add to Bag"), "To verify the
		// PDP Page", "Should land on PDP page", "user successfully landed on PDP page",
		// Common.getscreenShotPathforReport(""));
	} catch (Exception | Error e) {
		ExtenantReportUtils.addFailedLog("To verify the Product review details", "Should ener the review details",
				"user unable to enter product review details",
				Common.getscreenShotPathforReport("failed to enter product review details"));
		Assert.fail();
	}
	try {
		Common.scrollIntoView("xpath", "(//button[text()='Submit'])");
		Thread.sleep(5000);
		Common.clickElement("xpath", "(//button[text()='Submit'])");
		// Thread.sleep(4000);
		// String message=Common.findElement("xpath", "(//div[@class='message-success
		// success message'])").getText();
		// message.equals("We have saved your subscription.");
		// ExtenantReportUtils.addPassLog("To verify the payment page", "Should land on
		// payment page", "user successfully landed on payment page",
		// Common.getscreenShotPathforReport("Successfully land on payment page"));
		// Thread.sleep(6000);
		// Common.assertionCheckwithReport(Addtobag.equals("Add to Bag"), "To verify the
		// PDP Page", "Should land on PDP page", "user successfully landed on PDP page",
		// Common.getscreenShotPathforReport(""));
	} catch (Exception | Error e) {
		// ExtenantReportUtils.addFailedLog("To verify the payment page","Should land on
		// payment page", "user unableto land on payment page",
		// Common.getscreenShotPathforReport("failed to land on payment page"));
		Assert.fail();
	}
}

public void profile(String dataset) {
	try{
		Thread.sleep(5000);
		Common.textBoxInput("xpath", "(//input[@name='firstName'])",data.get(dataset).get("FirstName"));
	    Common.textBoxInput("xpath", "(//input[@name='lastName'])",data.get(dataset).get("LastName"));
		//Common.clickElement("xpath", "//input[@id='is_subscribed']");
		//Common.clickElement("xpath", "//input[@id='assistance_allowed_checkbox']");
		Common.textBoxInput("xpath","(//input[@name='email'])[2]",data.get(dataset).get("Email"));
		
		Common.scrollIntoView("xpath", "(//button[@class='tt-o-button tt-o-button--primary tt-c-auth__email-submit tt-c-auth__email-submit'])");
 	   Thread.sleep(5000);
       Common.clickElement("xpath", "(//button[@class='tt-o-button tt-o-button--primary tt-c-auth__email-submit tt-c-auth__email-submit'])");
		
		ExtenantReportUtils.addPassLog("verifying product review funcionality", " A confirmation message should sent to customer", "Successfully got a confirmation message", Common.getscreenShotPathforReport("Confirmation message"));
		
		}
		 catch(Exception |Error e) {
		        ExtenantReportUtils.addFailedLog("verifying product review functionality", "A confirmation message should present", "user faield to get confirmation message", Common.getscreenShotPathforReport("confirmation messge faield"));
				Assert.fail();
			}
		
	}

public void tax_calculation(String dataset) {
	   
    try {
        Thread.sleep(5000);
        String Taxrate=data.get(dataset).get("Tax");
       
         Float giventaxvalue=Float.valueOf(Taxrate);
       
        String subtotal=Common.getText("xpath", "//tr[@class='totals sub']/td/span").replace("$", "");
         // subtotla.replace("", newChar)
        Float subtotalValue=Float.valueOf(subtotal);
       
        String shippingammount=Common.getText("xpath", "//span[@data-th='Shipping']").replace("$", "");
        Float shippingammountvalue=Float.valueOf(shippingammount);
       
        String TaxAmmount=Common.getText("xpath", "//td[@data-th='Tax']/span").replace("$", "");
        Float Taxammountvalue=Float.valueOf(TaxAmmount);
       
        String TotalAmmount=Common.getText("xpath", "//tr[@class='grand totals']//span").replace("$", "");
        Float Totalammountvalue=Float.valueOf(TotalAmmount);
         Float Total=(subtotalValue+shippingammountvalue);
       
        Float calucaltedvalue= (Total*giventaxvalue)/100;
       // System.out.println(subtotalValue);
       // System.out.println(Total);
       // System.out.println(giventaxvalue);
       NumberFormat nf= NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        String  userpaneltaxvalue=nf.format(calucaltedvalue);
     
        System.out.println(TaxAmmount);
        System.out.println(userpaneltaxvalue);     
        Common.assertionCheckwithReport(userpaneltaxvalue.equals(TaxAmmount), "verifying tax calculation", "tax rate is matches to given shipping address tax ","successfully tax rate is matches to given shipping address tax", "tax rate is not matches to given shipping address tax");
       
       
       
        }catch(Exception |Error e) {
        
            ExtenantReportUtils.addFailedLog("verifying the loginpage","navigate the loginpage", "user successfully navigate the loginpage", Common.getscreenShotPathforReport("failedtologinpage"));
            Assert.fail();
        }
   
   
   
   
}
public void verifyingMagentoLoginPage(){
	try{
		Sync.waitElementPresent("xpath", "//img[@class='logo-img']");
	String HomepageTitle=Common.getPageTitle();
	Common.assertionCheckwithReport(HomepageTitle.equals("Magento Admin | Hot Tools"), "verifying the loginpage", "navigate the loginpage", "user successfully navigate the loginpage", "Failed to navigate to loginpage");
}
	catch(Exception |Error e) {
	     
			ExtenantReportUtils.addFailedLog("verifying the loginpage","navigate the loginpage", "user successfully navigate the loginpage", Common.getscreenShotPathforReport("failedtologinpage"));
			Assert.fail();
		}
}
public void Admin_Login(String dataset) {
	
	try {
		Sync.waitElementPresent("id", "username");
		Common.textBoxInput("id","username",data.get(dataset).get("UserName"));
		Sync.waitElementPresent("id", "login");
		Common.textBoxInput("id","login",data.get(dataset).get("Password"));
		ExtenantReportUtils.addPassLog("verifying Whether login details entered or not", "Lgin details should enter successfully", "Successfully entered login details", Common.getscreenShotPathforReport("Failed to enter login details"));
		
	}
	 catch(Exception |Error e) {
	        ExtenantReportUtils.addFailedLog("verifying product review functionality", "A confirmation message should present", "user faield to get confirmation message", Common.getscreenShotPathforReport("confirmation messge faield"));
			Assert.fail();
	 }	
	try {
		Sync.waitElementPresent("xpath", "(//span[contains(text(),'Sign in')])");
		Common.clickElement("xpath", "(//span[contains(text(),'Sign in')])");
	}catch(Exception |Error e) {
		Assert.fail();
	}
}
	
public void verifyingMagentoHomepage(){
	try{
		Sync.waitElementPresent("xpath", "(//h1[@class='page-title'])");
	String HomepageTitle=Common.getPageTitle();
	Thread.sleep(2000);
	Common.assertionCheckwithReport(HomepageTitle.equals("Dashboard / Magento Admin | Hot Tools"), "verifying the homepage", "navigate the home page", "user successfully navigate the home page", "Failed to navigate to home page");
    Thread.sleep(2000);
	}
	catch(Exception |Error e) {
	     
			ExtenantReportUtils.addFailedLog("verifying the homepage","navigate the home page", "user successfully navigate the home page", Common.getscreenShotPathforReport("failedtohomepage"));
			Assert.fail();
		}
}

public void Salesmenu_navigations(String dataSet) throws Exception{
	Thread.sleep(3000);
	Sync.waitPageLoad();
	Common.clickElement("xpath", "(//span[contains(text(),'Sales')])[1]");
	
	String Hederlinks2=data.get(dataSet).get("Megamenu");
	String[] hedrs=Hederlinks2.split(",");
	int i=0;
	
	try{
	for(i=0;i<hedrs.length;i++){
		System.out.println(hedrs[i]);
		Sync.waitElementClickable("xpath", "(//span[contains(text(),'"+hedrs[i]+"')])[1]");
		Common.clickElement("xpath", "(//span[contains(text(),'"+hedrs[i]+"')])[1]");
		Thread.sleep(3000);
		System.out.println(Common.getPageTitle());
		Common.assertionCheckwithReport(Common.getPageTitle().contains(hedrs[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
		Common.clickElement("xpath", "(//span[contains(text(),'Sales')])[1]");	
	}
	}
	catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("validating Header Links " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the header link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the headerlink"));
	
		Assert.fail();

	}
}

public void Catalogmenu_navigations(String dataSet) throws Exception{
	Thread.sleep(3000);
	Sync.waitPageLoad();
	Common.clickElement("xpath", "(//span[contains(text(),'Catalog')])[1]");
	
	String Hederlinks2=data.get(dataSet).get("Megamenu");
	String[] hedrs=Hederlinks2.split(",");
	int i=0;
	
	try{
	for(i=0;i<hedrs.length;i++){
		System.out.println(hedrs[i]);
		Sync.waitElementClickable("xpath", "(//span[contains(text(),'"+hedrs[i]+"')])[1]");
		Common.clickElement("xpath", "(//span[contains(text(),'"+hedrs[i]+"')])[1]");
		Thread.sleep(3000);
		System.out.println(Common.getPageTitle());
		Common.assertionCheckwithReport(Common.getPageTitle().contains(hedrs[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
		Common.clickElement("xpath", "(//span[contains(text(),'Catalog')])[1]");	
	}
	}
	catch (Exception | Error e) {
		e.printStackTrace();

		ExtenantReportUtils.addFailedLog("validating Header Links " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the header link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the headerlink"));
	
		Assert.fail();

	}
}

public void CMSpage() throws Exception{
	
	
	try{
		Sync.waitElementPresent("xpath", "(//span[contains(text(),'Stores')])");
		Common.clickElement("xpath", "(//span[contains(text(),'Stores')])");
		ExtenantReportUtils.addPassLog("To display stores sub-category list", "Should display stores sub-category list", "Successfully display stores sub-category list", Common.getscreenShotPathforReport("Failed to display stores sub-category list "));
      	//
	}
	
	catch (Exception | Error e) {
		e.printStackTrace();
	ExtenantReportUtils.addFailedLog("To display stores sub-category list ","Should display stores sub-category list","User unabel to display stores sub-category list ",Common.getscreenShotPathforReport("failed to display stores sub-category list"));
		Assert.fail();

}
	
	try{
		Sync.waitElementPresent("xpath", "(//span[contains(text(),'Configuration')])[18]");
		Common.clickElement("xpath", "(//span[contains(text(),'Configuration')])[18]");
		
		ExtenantReportUtils.addPassLog("To Verify configuration page", "Should land on configuration page", "Successfully landed on Confioguration page", Common.getscreenShotPathforReport("Failed to land on Configuration page"));
      	//
	}
	
	catch (Exception | Error e) {
		e.printStackTrace();
	ExtenantReportUtils.addFailedLog("To verify configuration page","Should land on configuration page","User unable to land on Configuration page",Common.getscreenShotPathforReport("failed to land onn Configuration page"));
		Assert.fail();

}
	try{
		//Common.actionsKeyPress(Keys.PAGE_DOWN);
		Sync.waitElementPresent("xpath", "(//strong[contains(text(),'General')])");
		Common.clickElement("xpath", "(//strong[contains(text(),'General')])");
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		ExtenantReportUtils.addPassLog("To verify expansion of General tab", "Should expand the General tab ", "Successfully Expanded General tab", Common.getscreenShotPathforReport("Failed to click on General tab"));
      	//
	}
	
	catch (Exception | Error e) {
		e.printStackTrace();
	ExtenantReportUtils.addFailedLog("To verify  expansion of General tab","Should expand the General tab","User unable to click the General tab",Common.getscreenShotPathforReport("failed to click on General tab"));
		Assert.fail();
}
	try{
		//Common.actionsKeyPress(Keys.PAGE_DOWN);
		Sync.waitElementPresent("xpath", "(//span[contains(text(),'Web')])");
		Common.clickElement("xpath", "(//span[contains(text(),'Web')])");
		//Common.actionsKeyPress(Keys.PAGE_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		ExtenantReportUtils.addPassLog("To verify expansion of General tab", "Should expand the General tab ", "Successfully Expanded General tab", Common.getscreenShotPathforReport("Failed to click on General tab"));
      	//
	}
	
	catch (Exception | Error e) {
		e.printStackTrace();
	ExtenantReportUtils.addFailedLog("To verify  expansion of General tab","Should expand the General tab","User unable to click the General tab",Common.getscreenShotPathforReport("failed to click on General tab"));
		Assert.fail();
}
	try{
		Sync.waitElementPresent("xpath", "(//input[@id='web_default_cms_home_page_inherit'])");
		Common.clickElement("xpath", "(//input[@id='web_default_cms_home_page_inherit'])");
		ExtenantReportUtils.addPassLog("To verify CMS Homepage checkbox unchecked", "Should uncheck CMS Homepage", "Successfully unchecked CMS Homepage", Common.getscreenShotPathforReport("Failed to uncheck CMS Homepage"));
      	
	}
	
	catch (Exception | Error e) {
		e.printStackTrace();
	ExtenantReportUtils.addFailedLog("To verify CMS Homepage checkbox unchecked","Should uncheck CMS Homepage","User unable to uncheck CMS Homepage",Common.getscreenShotPathforReport("failed to uncheck CMS Homepage"));
		Assert.fail();

}
	/*try{
		Thread.sleep(3000);
	
		Sync.waitElementPresent("id", "web_default_cms_home_page");
		Thread.sleep(5000);
		 Common.textBoxInput("id", "web_default_cms_home_page", data.get(dataSet).get("SelectCMS"));
         
		//Common.clickElement("xpath", "(//input[@id='web_default_cms_home_page_inherit'])");
		ExtenantReportUtils.addPassLog("To verify CMS Homepage checkbox unchecked", "Should uncheck CMS Homepage", "Successfully unchecked CMS Homepage", Common.getscreenShotPathforReport("Failed to uncheck CMS Homepage"));
      	//
	}
	
	catch (Exception | Error e) {
		e.printStackTrace();
	ExtenantReportUtils.addFailedLog("To verify CMS Homepage checkbox unchecked","Should uncheck CMS Homepage","User unable to uncheck CMS Homepage",Common.getscreenShotPathforReport("failed to uncheck CMS Homepage"));
		Assert.fail();

}*/
}
public void CMS_Block() {
	try{
		Sync.waitElementPresent("xpath", "(//span[contains(text(),'Content')])");
		Common.clickElement("xpath", "(//span[contains(text(),'Content')])");
		ExtenantReportUtils.addPassLog("To display Content sub-category list", "Should display Content sub-category list", "Successfully display Content sub-category list", Common.getscreenShotPathforReport("Failed to display Content sub-category list "));
      	//
	}
	
	catch (Exception | Error e) {
		e.printStackTrace();
	ExtenantReportUtils.addFailedLog("To display Content sub-category list ","Should display Content sub-category list","User unabel to display Content sub-category list ",Common.getscreenShotPathforReport("failed to display Content sub-category list"));
		Assert.fail();

}
	try{
		Sync.waitElementPresent("xpath", "(//span[contains(text(),'Blocks')])[1]");
		Common.clickElement("xpath", "(//span[contains(text(),'Blocks')])[1]");
		String Title=Common.findElement("xpath", "(//span[contains(text(),'Add New Block')])").getText();
		Common.assertionCheckwithReport(Title.equals("Add New Block"), "verifying the CMS Block page", "Should land on CMS Block page", "user successfully landed on CMS Block page", "Failed to land on CMS Block page");
	}
	
	catch (Exception | Error e) {
		e.printStackTrace();
	ExtenantReportUtils.addFailedLog("To verify CMS Block page","Should land on CMS Block page","User unable to land on CMS Bloack page",Common.getscreenShotPathforReport("failed to land on CMS Block page"));
		Assert.fail();

}
	
}

public void FilterOptionsPLP(){ 
    try{
        Sync.waitPageLoad();
        Sync.waitElementPresent("xpath", "(//div[contains(text(),'Benefit')])[2]");
        Sync.waitPageLoad();
        Common.clickElement("xpath", "(//span[contains(text(),'Cleanse & Condition')])[2]");
        Sync.waitPageLoad();
        //Thread.sleep(6000);
       // String Cleanse_and_condition_count=Common.findElement("xpath", "(//span[@class='count'])[4]").getText();
        Thread.sleep(4000);
        String Toolbar_number=Common.findElement("xpath", "(//span[@class='toolbar-number'])[1]").getText();
        Thread.sleep(5000);
       /* Common.actionsKeyPress(Keys.ARROW_DOWN);
        Common.actionsKeyPress(Keys.ARROW_DOWN);
        Common.actionsKeyPress(Keys.ARROW_DOWN);*/
        Common.actionsKeyPress(Keys.ARROW_DOWN);
        Common.assertionCheckwithReport(("7").equals(Toolbar_number), "verifying Cleanse and condition filter in Benefits", "Should select Cleanse & Condition", "user successfully selected Cleanse & condition filter", "Failed to select cleanse & condition filter");
    }
    
    catch(Exception |Error e) 
{	     			
ExtenantReportUtils.addFailedLog("verifying cleanse and condition filter in Benefits", "User should  select cleanse & condition filter", "user faield to Select cleanse & condition filter", Common.getscreenShotPathforReport("Cleanse & condition filter"));	
Assert.fail();	
}         
try{		
      Sync.waitPageLoad();	
     // Sync.waitElementPresent("xpath", "(//div[contains(text(),'Price')])");
      Common.clickElement("xpath", "(//div[contains(text(),'Price')])");
      Sync.waitPageLoad();
      Thread.sleep(6000);	
     Common.clickElement("xpath", "(//input[@type='radio'])[2]");
     Thread.sleep(4000);
     String Toolbar_number=Common.findElement("xpath", "(//span[@class='toolbar-number'])[1]").getText();
     Thread.sleep(5000);
     Common.actionsKeyPress(Keys.ARROW_DOWN);
     Common.actionsKeyPress(Keys.ARROW_DOWN);
     Common.actionsKeyPress(Keys.ARROW_DOWN);
     Common.actionsKeyPress(Keys.ARROW_DOWN);
     Common.assertionCheckwithReport(("3").equals(Toolbar_number), "verifying $20.00 - $29.99 Price filter", "Should select $20.00 - $29.99 Price filter", "user successfully selected $20.00 - $29.99 Price filter", "Failed to select $20.00 - $29.99 Price filter");
}
   
catch(Exception |Error e) 
{	     			
ExtenantReportUtils.addFailedLog("verifying $20.00 - $29.99 Price filter", "User should select $20.00 - $29.99 Price filter", "user faield to  Select $20.00 - $29.99 Price filter", Common.getscreenShotPathforReport("Price"));
Assert.fail();	
} 

try{		
    Sync.waitPageLoad();	
   // Sync.waitElementPresent("xpath", "(//div[contains(text(),'Price')])");
    Common.clickElement("xpath", "(//div[contains(text(),'Clean Benefit')])");
    Sync.waitPageLoad();
    Thread.sleep(6000);	
   Common.clickElement("xpath", "(//span[contains(text(),'Phthalate Free')])");
   Thread.sleep(4000);
   Common.actionsKeyPress(Keys.ARROW_UP);
   Common.actionsKeyPress(Keys.ARROW_UP);
   Common.actionsKeyPress(Keys.ARROW_UP);
   String Toolbar_number=Common.findElement("xpath", "(//span[@class='toolbar-number'])[1]").getText();
   Thread.sleep(5000);
   Common.assertionCheckwithReport(("3").equals(Toolbar_number), "verifying Phthalate Free filter in Clean benefit", "user should select Phthalate Free filter in Clean benefit", "user successfully  selected Phthalate Free filter in Clean benefit", "Failed to select Phthalate Free filter in Clean beneft");
}
 
catch(Exception |Error e) 
{	     			
ExtenantReportUtils.addFailedLog("verifying Phthalate Free filter in Clean benefit", "User should  select Phthalate Free filter in Clean benefit", "user faield to Select Phthalate Free filter in Clean benefit", Common.getscreenShotPathforReport("Phthalate Free filter in Clean benefit"));
Assert.fail();	
}   

try{		
    Sync.waitPageLoad();	
   // Sync.waitElementPresent("xpath", "(//div[contains(text(),'Price')])");
   // Common.clickElement("xpath", "(//div[contains(text(),'Clean Benefit')])");
    Sync.waitPageLoad();
    Thread.sleep(6000);	
   Common.clickElement("xpath", "(//span[contains(text(),'Honey Free')])");
   Thread.sleep(4000);
   Common.actionsKeyPress(Keys.PAGE_UP);
   Common.actionsKeyPress(Keys.PAGE_DOWN);
   Common.actionsKeyPress(Keys.ARROW_UP);
   String Toolbar_number=Common.findElement("xpath", "(//span[@class='toolbar-number'])[1]").getText();
   Thread.sleep(5000);
   Common.assertionCheckwithReport(("3").equals(Toolbar_number), "verifying Honey free filter in clean benefit", "Should select Honey free filter in clean benefit", "user successfully selected Honey free filter in clean benefit", "Failed to select Honey free filter in clean benefit");
}
 
catch(Exception |Error e) 
{	     			
ExtenantReportUtils.addFailedLog("verifying Honey free filter in clean benefit", "User should  select Honey free filter in clean benefit", "user faield to Select Honey free filter in clean benefit", Common.getscreenShotPathforReport("Honey free filter in clean benefit"));
Assert.fail();	
} 
try{		
    Sync.waitPageLoad();
    Common.actionsKeyPress(Keys.PAGE_UP);
    Common.actionsKeyPress(Keys.PAGE_UP);
   // Sync.waitElementPresent("xpath", "(//div[contains(text(),'Price')])");
    //Common.clickElement("xpath", "(//div[contains(text(),'Price')])");
    Sync.waitPageLoad();
    Thread.sleep(6000);	
   Common.clickElement("xpath", "(//input[@type='radio'])[1]");
   Thread.sleep(4000);
   String Toolbar_number=Common.findElement("xpath", "(//span[@class='toolbar-number'])[1]").getText();
   Thread.sleep(5000);
   Common.actionsKeyPress(Keys.ARROW_DOWN);
   Common.actionsKeyPress(Keys.ARROW_DOWN);
   Common.actionsKeyPress(Keys.ARROW_DOWN);
   Common.actionsKeyPress(Keys.ARROW_DOWN);
   Common.assertionCheckwithReport(("2").equals(Toolbar_number), "verifying $10.00 - $19.99 Price filter", "User should  select $10.00 - $19.99 Price filter", "user successfully  selected $10.00 - $19.99 Price filter", "Failed to select  $10.00 - $19.99 Price filter");
}
 
catch(Exception |Error e) 
{	     			
ExtenantReportUtils.addFailedLog("verifying $10.00 - $19.99 Price filter", "User should  select $10.00 - $19.99 Price filter", "user failed to select $10.00 - $19.99 Price filter", Common.getscreenShotPathforReport("Price"));
Assert.fail();	
} 

try{
    Sync.waitPageLoad();
    Sync.waitElementPresent("xpath", "(//div[contains(text(),'Benefit')])[2]");
    Sync.waitPageLoad();
    Common.clickElement("xpath", "(//span[contains(text(),'Cleanse & Condition')])[2]");
    Sync.waitPageLoad();
    //Thread.sleep(6000);
   // String Cleanse_and_condition_count=Common.findElement("xpath", "(//span[@class='count'])[4]").getText();
    Thread.sleep(4000);
    String Toolbar_number=Common.findElement("xpath", "(//span[@class='toolbar-number'])[1]").getText();
    Thread.sleep(5000);
    Common.actionsKeyPress(Keys.ARROW_DOWN);
    Common.actionsKeyPress(Keys.ARROW_DOWN);
    Common.actionsKeyPress(Keys.ARROW_DOWN);
    Common.actionsKeyPress(Keys.ARROW_DOWN);
    Common.assertionCheckwithReport(("2").equals(Toolbar_number), "verifying Cleanse & Condition filter in Benefit ", "Should unselect Cleanse & Condition filter in Benefit ", "user successfully unselected Cleanse & Condition filter in Benefit", "Failed to unselect Cleanse & Condition filter in Benefit");
}

catch(Exception |Error e) 
{	     			
ExtenantReportUtils.addFailedLog("verifying Cleanse & Condition filter in Benefit", "User should  unselect Cleanse & Condition filter in Benefit", "user faield to unselect Cleanse & Condition filter in Benefit", Common.getscreenShotPathforReport("Cleanse & Condition filter in Benefit"));	
Assert.fail();	
} 
try{		
    Sync.waitPageLoad();	
   // Sync.waitElementPresent("xpath", "(//div[contains(text(),'Price')])");
   // Common.clickElement("xpath", "(//div[contains(text(),'Clean Benefit')])");
    Sync.waitPageLoad();
    Thread.sleep(6000);	
   Common.clickElement("xpath", "(//span[contains(text(),'Phthalate Free')])");
   Thread.sleep(4000);
   Common.actionsKeyPress(Keys.ARROW_UP);
   Common.actionsKeyPress(Keys.ARROW_UP);
   Common.actionsKeyPress(Keys.ARROW_UP);
   String Toolbar_number=Common.findElement("xpath", "(//span[@class='toolbar-number'])[1]").getText();
   Thread.sleep(5000);
   Common.assertionCheckwithReport(("2").equals(Toolbar_number), "verifying Phthalate free filter in Benefit", "User should  unselect Phthalate free filter in Benefit", "user successfully unselected Phthalate free filter in Benefit", "Failed to unselect Phthalate free filter in Benefit");
}
 
catch(Exception |Error e) 
{	     			
ExtenantReportUtils.addFailedLog("verifying Phthalate free filter in Benefit", "User should  unselect Phthalate free filter in Benefit", "user faield to unselect Phthalate free filter in Benefit", Common.getscreenShotPathforReport("Phthalate free filter in Benefit"));
Assert.fail();	
}  
try{		
    Sync.waitPageLoad();
    Common.actionsKeyPress(Keys.PAGE_UP);
    Common.actionsKeyPress(Keys.PAGE_UP);
    Common.actionsKeyPress(Keys.PAGE_UP);
    Common.actionsKeyPress(Keys.PAGE_UP);
   // Sync.waitElementPresent("xpath", "(//div[contains(text(),'Price')])");
    //Common.clickElement("xpath", "(//div[contains(text(),'Price')])");
    Sync.waitPageLoad();
    Thread.sleep(6000);	
   Common.clickElement("xpath", "(//input[@type='radio'])[3]");
   Thread.sleep(4000);
   String Toolbar_number=Common.findElement("xpath", "(//span[@class='toolbar-number'])[1]").getText();
   Thread.sleep(5000);
   Common.actionsKeyPress(Keys.ARROW_DOWN);
   Common.actionsKeyPress(Keys.ARROW_DOWN);
   Common.actionsKeyPress(Keys.ARROW_DOWN);
   Common.actionsKeyPress(Keys.ARROW_DOWN);
   Common.assertionCheckwithReport(("2").equals(Toolbar_number), "verifying $30.00 - $39.99 price filter", "User should  select $30.00 - $39.99 price filter", "user successfully selected  select $30.00 - $39.99 price filter ", "Failed to select  select $30.00 - $39.99 price filter");
}
 
catch(Exception |Error e) 
{	     			
ExtenantReportUtils.addFailedLog("verifying $30.00 - $39.99 price filter", "User should  select $30.00 - $39.99 price filter", "user faield to Select  select $30.00 - $39.99 price filter", Common.getscreenShotPathforReport("Price"));
Assert.fail();	
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
		
		

		public void writeResultstoXLSx(String Website,String subtotlaValue,String shippingammountvalue,String state,String Zipcode,String Taxammountvalue,String ActualTotalammountvalue, String ExpectedTotalammountvalue,String giventaxvalue,String calucaltedvalue)
		{
			//String fileOut="";
		try{
			
			File file=new File(System.getProperty("user.dir")+"/src/test/resources/Redesign_HottoolsTaxDetails.xlsx");
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
			cell.setCellValue("Beauty");
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
			if(Website.contains("Hottools"))
		     {
				
				Site="Hottools";
				
		}
			else
			{
				Site="";
			}
			cell.setCellValue(Site);
			
			cell = row.createCell(7);
			//cell.setCellValue(OrderId);
			//cell = row.createCell(8);
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
//		return fileOut;
//		return writeResultstoXLSx(String OrderId,String subtotla,String shippingammount,String TaxAmmount,String Totalammount,String giventaxvalue1,String userpaneltaxvalue);

		}
		
		public void guestShippingAddress(String Street,String City,String postcode,String Region) {
			// TODO Auto-generated method stub
			String expectedResult="Should navigate to Shipping address page";
			try {
			
				Thread.sleep(4000);
				//shippingaddress1("Address", Street, City, postcode, Region);
				shippingaddress1("ShippingAddress", Street, City, postcode, Region);
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
		
		public void shippingaddress1(String dataSet,String Street,String City,String postcode,String Region) throws InterruptedException {
			 try {
			    	Common.textBoxInput("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']", data.get(dataSet).get("Email"));
			    	Thread.sleep(3000);
			        Common.textBoxInput("xpath", "//input[@name='firstname']", data.get(dataSet).get("FirstName"));
			        Common.textBoxInput("xpath", "//input[@name='lastname']", data.get(dataSet).get("LastName"));
			        Common.textBoxInput("xpath", "//input[@name='street[0]']", Street);
			        Thread.sleep(3000);
			        Common.actionsKeyPress(Keys.ESCAPE);
			        Common.textBoxInput("xpath", "//input[@name='city']", City);
			        Common.textBoxInput("xpath", "//input[@name='postcode']", postcode);
			        Common.dropdown("xpath", "//select[@name='region_id']",Common.SelectBy.TEXT, Region);
			        Common.textBoxInput("xpath", "//input[@name='telephone']", data.get(dataSet).get("phone"));
			        Thread.sleep(3000);       
			    }catch(Exception |Error e)
			    {
//			        e.printStackTrace();
//			        Assert.fail();
			    }
		}	
		
		 @SuppressWarnings("deprecation")
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
			     // subtotla.replace("", newChar)
			    Float subtotlaValue=Float.valueOf(subtotla);
			    data.put("subtotlaValue",subtotla);
			    
			   // Capabilities cap = (WebDriver).getCapabilities();
			   
			  String shippingammount=Common.getText("xpath", "//span[@data-th='Shipping']").replace("$", "");
			    Float shippingammountvalue=Float.valueOf(shippingammount);
				data.put("shippingammountvalue",shippingammount);
				
				
			     String TaxAmmount=Common.getText("xpath", "//td[@data-th='Tax']//span").replace("$", "");
			    Float Taxammountvalue=Float.valueOf(TaxAmmount);
				data.put("Taxammountvalue",TaxAmmount);
				/*
				 * NumberFormat n_f= NumberFormat.getInstance();
				 * n_f.setMaximumFractionDigits(2); String
				 * Tax_Amount=n_f.format(Taxammountvalue);
				 */
				
			     String ActualTotalAmmount=Common.getText("xpath", "//tr[@class='grand totals']//span").replace("$", "");
			    Float ActualTotalammountvalue=Float.valueOf(ActualTotalAmmount);
			    data.put("ActualTotalammountvalue",ActualTotalAmmount);
			   // Float Total=(subtotlaValue+shippingammountvalue);
			    
			    Float ExpectedTotalAmmount = subtotlaValue+shippingammountvalue+Taxammountvalue;
			    String ExpectedTotalAmount=String.valueOf(ExpectedTotalAmmount);
			    data.put("ExpectedTotalAmmountvalue",ExpectedTotalAmount);
			  
			    if((state.equals("Illinois"))||(state.equals("Florida"))) {
			    Float calucaltedvalue= ((subtotlaValue)*giventaxvalue)/100;
			    String userpaneltaxvalue = new BigDecimal(calucaltedvalue).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
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
			 			report.addFailedLog("verifying tax calculation", "getting price values from shipping page  ", "Faield to get price value from shipping page", Common.getscreenShotPathforReport("TaxRates"));

			 			e.printStackTrace();
			 			Assert.fail();
			 			
			 	}

						
			    return data;
			    
			}
		
		public void addDeliveryAddress(String dataSet,String Street,String City,String postcode,String Region) throws Exception {
    		try {
    			Sync.waitElementVisible("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']");
    			//Common.textBoxInput("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']", "qatest067@gmail.com");
    			 Common.textBoxInput("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']","varaprasad.raikanti@gmail.com");
    		} catch (NoSuchElementException e) {
    			Common.textBoxInput("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']", data.get(dataSet).get("Email"));

    		}
    		String expectedResult = "email field will have email address";
    		try {
    			Common.textBoxInput("xpath", "//input[@name='firstname']",data.get(dataSet).get("FirstName"));
                int size = Common.findElements("xpath", "//fieldset[@id='customer-email-fieldset']//input[@id='customer-email']").size();
                Common.assertionCheckwithReport(size > 0, "validating the email address field", expectedResult,"Filled Email address", "unabel to fill the email address");
                Common.textBoxInput("xpath", "//input[@name='lastname']",data.get(dataSet).get("LastName"));
    			Common.textBoxInput("xpath", "//input[@name='street[0]']",Street);
    			String Text=Common.getText("xpath", "//input[@name='street[0]']");
    			
    			

    		/*	try {
    				Common.clickElement("xpath", "//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
    			} catch (Exception e) {
    				Common.actionsKeyPress(Keys.BACK_SPACE);
    				Thread.sleep(1000);
    				Common.actionsKeyPress(Keys.SPACE);
    				Common.clickElement("xpath", "//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
    			}
    			if (data.get(dataSet).get("StreetLine2") != null) {
    				Common.textBoxInput("name", "street[1]", Street);
    				
    				String text=Common.getText("name","street[1]");
    				System.out.println(text);
    				
    			}
    			if (data.get(dataSet).get("StreetLine3") != null) {
    				Common.textBoxInput("name", "street[2]",Street);
    			}*/
    			Sync.waitPageLoad();
    			Thread.sleep(5000);
    			Common.findElement("xpath", "//input[@name='city']").clear();
    			Common.textBoxInput("xpath", "//input[@name='city']",City);
    			
    			
    			Common.actionsKeyPress(Keys.PAGE_DOWN);
    			Thread.sleep(3000);
    			try {
    				Common.dropdown("xpath", "//select[@name='region_id']", Common.SelectBy.TEXT, Region);
    			} catch (ElementClickInterceptedException e) {
    				Thread.sleep(3000);
    				Common.dropdown("xpath", "//select[@name='region_id']", Common.SelectBy.TEXT, Region);
    			}
    			Thread.sleep(2000);
    			Common.textBoxInputClear("xpath", "//input[@name='postcode']");
    			Common.textBoxInput("xpath", "//input[@name='postcode']",postcode);
    			
    			Thread.sleep(5000);
    			
    			Common.textBoxInput("xpath", "//input[@name='telephone']", data.get(dataSet).get("phone"));

    		}

    		catch (Exception | Error e) {

    			ExtenantReportUtils.addFailedLog("validating shipping address",
    					"shipping address is filled in to the fields", "user faield to fill the shipping address",
    					Common.getscreenShotPathforReport("shipingaddressfaield"));
    			// ExtenantReportUtils.addFailedLog("User click check out button",
    			// "User unabel click the checkout button",
    			// Common.getscreenShotPathforReport("check out miniCart"));
    			Assert.fail();

    		}
    		Thread.sleep(5000);
    		int size=Common.findElements("xpath", "(//td[text()='Express'])").size();
    		if(size>0){
    			Common.clickElement("xpath", "(//td[text()='Express'])");
    		}

    		expectedResult = "shipping address is filled in to the fields";
    		click_Next();
    		//Common.clickElement("xpath", "//button[@data-ac-test='form-shipping-address_action_submit']");

    		int errorsize = Common.findElements("xpath", "//div[contains(@id,'error')]").size();

    		if (errorsize <= 0) {
    			ExtenantReportUtils.addPassLog("validating the shipping address field with valid Data", expectedResult,
    					"Filled the shipping address", Common.getscreenShotPathforReport("shippingaddresspass"));
    		} else {
    			ExtenantReportUtils.addFailedLog("validating the shipping address field with valid Datas", expectedResult,
    					"failed to add a addres in the filled",
    					Common.getscreenShotPathforReport("failed to add a address"));
    			Assert.fail();
    		}

    		
    		Thread.sleep(3000);
    	}	
		
		
		public String  URL() throws InterruptedException {
			String Website="";
			Common.clickElement("xpath", "(//a[@class='logo'])");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			Website=Common.getCurrentURL();
            
			return Website;
			
		}		
		public void addDeliveryAddress_register(String dataSet,String Street,String City,String postcode,String Region) throws Exception {

			String expectedResult = "shipping address is entering in the fields";
		    int size = Common.findElements(By.xpath("//span[contains(text(),'New Address')]")).size();
			if (size > 0) {

				try {
					Common.clickElement("xpath", "//span[contains(text(),'New Address')]");
					Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",
							data.get(dataSet).get("FirstName"));
					Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']",
							data.get(dataSet).get("LastName"));
					//Common.textBoxInput("xpath", "//input[@name='street[0]']",Street);
					Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']",Street);
					Thread.sleep(2000);
					Common.actionsKeyPress(Keys.SPACE);
					Thread.sleep(3000);
					
					if (data.get(dataSet).get("StreetLine2") != null) {
						Common.textBoxInput("name", "street[1]",Street);
					}
				if (data.get(dataSet).get("StreetLine3") != null) {
						Common.textBoxInput("name", "street[2]", data.get(dataSet).get("Street"));
					}
					Common.actionsKeyPress(Keys.PAGE_DOWN);
					Thread.sleep(3000);
					Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='city']",City);
					// Common.mouseOverClick("name", "region_id");
					try {
						Common.dropdown("name", "region_id", Common.SelectBy.TEXT,Region);
					} catch (ElementClickInterceptedException e) {
						// TODO: handle exception
						Thread.sleep(3000);
						Common.dropdown("name", "region_id", Common.SelectBy.TEXT,Region);
					}
					Thread.sleep(2000);
					Common.textBoxInputClear("xpath", "//form[@id='co-shipping-form']//input[@name='postcode']");
					Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='postcode']",postcode);
					Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='telephone']",
							data.get(dataSet).get("phone"));
		            Sync.waitElementPresent("xpath", "(//span[text()='Save in address book'])");
		            Common.clickElement("xpath", "(//span[text()='Save in address book'])");
					ExtenantReportUtils.addPassLog("validating the shipping address", expectedResult,
							"user add the shipping address",
							Common.getscreenShotPathforReport("faield to add shipping address"));


					Common.clickElement("xpath", "//button[contains(@class,'save-address')]");

					int sizeerrormessage = Common.findElements("xpath", "//span[contains(text(),'This is a required field')]").size();

					Common.assertionCheckwithReport(sizeerrormessage <= 0, "verifying shipping addres filling ",
							"user will fill the all the shipping", "user fill the shiping address click save button",
							"faield to add new shipping address");
					
					
					
					Common.actionsKeyPress(Keys.END);
					Sync.waitPageLoad();
					/*select_USPS_StandardGround_shippingMethod();
		            Thread.sleep(5000);
					Common.mouseOverClick("xpath", "//div[@id='shipping-method-buttons-container']/div/button");*/
					 Thread.sleep(5000);
						Common.clickElement("xpath", "(//button[@type='button'])[11]");
						
						Sync.waitElementPresent("xpath", "//span[text()='Next']");
						Common.clickElement("xpath", "//span[text()='Next']");
					
					
				} catch (Exception | Error e) {
					e.printStackTrace();

					ExtenantReportUtils.addFailedLog("validating adding  address", expectedResult,
							"User unabel add shipping address",
							Common.getscreenShotPathforReport("shipping address faield"));
								Assert.fail();

				}
			}

			else

			{
				try {
					Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",data.get(dataSet).get("FirstName"));
					Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']",
							data.get(dataSet).get("LastName"));
					Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']",
							data.get(dataSet).get("Street"));
					Thread.sleep(2000);
					Common.actionsKeyPress(Keys.SPACE);
					Thread.sleep(3000);
					try {
						Common.clickElement("xpath",
								"//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
					} catch (Exception e) {
						Common.actionsKeyPress(Keys.BACK_SPACE);
						Thread.sleep(1000);
						Common.actionsKeyPress(Keys.SPACE);
						Common.clickElement("xpath", "//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div");
						//Common.clickElement("xpath","//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
					}
					if (data.get(dataSet).get("StreetLine2") != null) {
						Common.textBoxInput("name", "street[1]", data.get(dataSet).get("Street"));
					}
					if (data.get(dataSet).get("StreetLine3") != null) {
						Common.textBoxInput("name", "street[2]", data.get(dataSet).get("Street"));
					}
					Common.actionsKeyPress(Keys.PAGE_DOWN);
					Thread.sleep(3000);
					Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='city']",
							data.get(dataSet).get("City"));
					// Common.mouseOverClick("name", "region_id");
					try {
						Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
					} catch (ElementClickInterceptedException e) {
						// TODO: handle exception
						Thread.sleep(3000);
						Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
					}
					Thread.sleep(2000);
					Common.textBoxInputClear("name", "postcode");
					Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
					Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));

					//Sync.waitElementClickable("xpath", "//span[contains(text(),'Continue To Payment')]");
					//Common.clickElement("xpath", "//span[contains(text(),'Continue To Payment')]");

				
					ExtenantReportUtils.addPassLog("verifying shipping addres filling ", expectedResult,
							"user enter the shipping address ",
							Common.getscreenShotPathforReport("fill the shipping address first time"));

					//Common.findElements("xpath", "").size();
					expectedResult = "shipping address is filled in to the fields";
					// report.addPassLog(expectedResult,"Filled the shipping
					// address",Common.getscreenShotPathforReport("fill the shipping
					// address"));
					
					//Common.mouseOverClick("xpath", "//input[@id='label_method_bestway']");
					//Sync.waitElementClickable("xpath","//input[@id='label_method_bestway']");
					//Common.clickElement("xpath", "//input[@id='label_method_bestway']");
					select_USPS_StandardGround_shippingMethod();
		            Thread.sleep(5000);
					Common.clickElement("xpath", "//button[@data-role='opc-continue']");
					
					Thread.sleep(3000);

				} catch (Exception | Error e) {
					e.printStackTrace();

					ExtenantReportUtils.addFailedLog("validating adding  address", expectedResult,
							"User unabel add shipping address",
							Common.getscreenShotPathforReport("shipping address faield"));
					// ExtenantReportUtils.addFailedLog("User click check out
					// button", "User unabel click the checkout button",
					// Common.getscreenShotPathforReport("check out miniCart"));
					Assert.fail();

				}
			}

			// report.addPassLog("clicked on the proceed to payment
			// section",Common.getscreenShotPathforReport("land on the payment
			// section"));

		}
		public void writeResultstoXLSx_(String Website,String OrderId,String subtotlaValue,String shippingammountvalue,String state,String Zipcode,String Taxammountvalue,String ActualTotalammountvalue, String ExpectedTotalammountvalue,String giventaxvalue,String calucaltedvalue)
		{
			//String fileOut="";
		try{
			
			File file=new File(System.getProperty("user.dir")+"/src/test/resources/DryBarTaxDetails_register.xlsx");
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
			cell.setCellValue("Beauty");
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
			if(Website.contains("drybar"))
		     {
				
				Site="Drybar";
				
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
//		return fileOut;
//		return writeResultstoXLSx(String OrderId,String subtotla,String shippingammount,String TaxAmmount,String Totalammount,String giventaxvalue1,String userpaneltaxvalue);

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
        int size=Common.findElements("xpath", "(//a[@class='action print'])").size();
        if(size>0) {

        Orderid=Common.getText("xpath", "(//div[@class='column main'])//div[@class='checkout-success']//a//strong");
        }
        else{
        Orderid=Common.getText("xpath", "((//div[@class='column main'])//span)[1]");
        }
       
    	System.out.println(Orderid);
        System.out.println("Your order number is:"+Orderid);
        Common.assertionCheckwithReport(sucessMessage.equals("THANK YOU FOR YOUR PURCHASE!"),"verifying the product confirmation", expectedResult,"Successfully It redirects to order confirmation page Order Placed","User unabel to go orderconformation page");
           
        }
        catch (Exception | Error e) {
            e.printStackTrace();
            ExtenantReportUtils.addFailedLog("verifying the product confirmation", expectedResult,
                    "User failed to navigate  to order confirmation page", Common.getscreenShotPathforReport("failednavigatepage"));
            Assert.fail();
            
        }
       
	return Orderid;
    }



public void RegisterUser_Shipping_Address() {
	// TODO Auto-generated method stub
	{
		String expectedResult="Navigate to Shipping Address Page";
		try {
		Thread.sleep(3000);
		Sync.waitElementPresent("xpath", "//span[text()='Next']");
		Common.clickElement("xpath", "//span[text()='Next']");

		//Sync.waitElementClickable(30, By.xpath("//span[text()='OK']"));
		//Common.findElement("xpath", "//span[text()='OK']").click();
		
		Thread.sleep(5000);
		

		report.addPassLog(expectedResult, "Should display Shipping Address Page", "Shipping Address Page display successfully", Common.getscreenShotPathforReport("Shipping Address page display success"));
		}catch(Exception |Error e)
		{
			e.printStackTrace();
		report.addFailedLog(expectedResult,"Should display Shipping Address Page", "Shipping Address Page not displayed", Common.getscreenShotPathforReport("Shipping Address Failed"));
		Assert.fail();
		}
		}
}

public void addCardDetiles(String dataSet) throws Exception{
	Thread.sleep(9000);
	Sync.waitElementClickable("xpath", "(//label[@class='label'])[2]");
	Common.clickCheckBox("xpath", "(//label[@class='label'])[2]");
	Sync.waitElementClickable("xpath", "(//label[@class='label'])[2]");
	Common.clickCheckBox("xpath", "(//label[@class='label'])[2]");
	//Common.clickCheckBox("xpath", "//input[@id='ime_paymetrictokenize']");
	Sync.waitElementClickable("xpath", "//input[@id='ime_paymetrictokenize']");
	//	Common.clickCheckBox("xpath", "//input[@id='ime_paymetrictokenize']");
	Common.clickCheckBox("xpath", "//input[@id='ime_paymetrictokenize']");
	report.addPassLog("select the card payement mode",Common.getscreenShotPathforReport(" card payement"));

	Thread.sleep(7000);

	Common.switchFrames("paymetric_xisecure_frame");
	Common.clickElement("xpath", "//select[@id='c-ct']");
	Common.dropdown("xpath", "//select[@id='c-ct']", SelectBy.TEXT, data.get(dataSet).get("cardType"));


	Common.textBoxInput("id", "c-cardnumber", data.get(dataSet).get("cardNumber"));

	Common.clickElement("id", "c-exmth");

	Common.dropdown("id", "c-exmth", SelectBy.VALUE, data.get(dataSet).get("ExpMonth"));
	Common.clickElement("id", "c-exyr");
	Common.dropdown("id", "c-exyr", SelectBy.TEXT, data.get(dataSet).get("ExpYear"));

	Common.textBoxInput("id", "c-cvv",  data.get(dataSet).get("cvv"));
	Common.switchToDefault();

	report.addPassLog("enter the card detiles",Common.getscreenShotPathforReport(" card details"));
	Thread.sleep(6000);
	//	Common.clickElement(By.xpath("//*[@id='iosc-summary']/div[5]/button"));
	//Common.actionsKeyPress(Keys.PAGE_DOWN);

	int sizes=Common.findElements(By.xpath("//div[@id='checkout-step-shipping']/div")).size();     
	if(sizes>2){
		Thread.sleep(6000);

		Sync.waitElementPresent("xpath", "//*[@id='iosc-summary']/div[5]/button");
		Common.clickElement(By.xpath("//*[@id='iosc-summary']/div[5]/button"));

		Thread.sleep(6000);
		int sizs=Common.findElements("xpath", "//a[@class='action primary continue']").size();
		if(sizs>0){

			//Common.clickElement("xpath","//button[contains(@class,'action-accept')]");
		}
		else{

			Common.actionsKeyPress(Keys.PAGE_UP);
			Common.clickElement("xpath", "//div[@class='shipping-address-item selected-item']");
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(2000);
			Sync.waitElementClickable("xpath", "//input[@id='ime_paymetrictokenize']");
			Common.clickElement("xpath", "//input[@id='ime_paymetrictokenize']");
			Common.clickElement(By.id("ime_paymetrictokenize"));
			Thread.sleep(7000);

			Common.switchFrames("paymetric_xisecure_frame");
			Common.clickElement("xpath", "//select[@id='c-ct']");
			Common.dropdown("xpath", "//select[@id='c-ct']", SelectBy.TEXT, data.get(dataSet).get("cardType"));


			Common.textBoxInput("id", "c-cardnumber", data.get(dataSet).get("cardNumber"));

			Common.clickElement("id", "c-exmth");

			Common.dropdown("id", "c-exmth", SelectBy.VALUE, data.get(dataSet).get("ExpMonth"));
			Common.clickElement("id", "c-exyr");
			Common.dropdown("id", "c-exyr", SelectBy.TEXT, data.get(dataSet).get("ExpYear"));

			Common.textBoxInput("id", "c-cvv",  data.get(dataSet).get("cvv"));
			Common.switchToDefault();
			Thread.sleep(6000);
			report.addPassLog("enter the card detiles",Common.getscreenShotPathforReport(" card details"));

			Sync.waitElementPresent("xpath", "//*[@id='iosc-summary']/div[5]/button");
			Common.clickElement(By.xpath("//*[@id='iosc-summary']/div[5]/button"));

			report.addPassLog("submit the ordear",Common.getscreenShotPathforReport(" ordersubmit"));

		}



	}
	else{

		//	Common.actionsKeyPress(Keys.PAGE_DOWN);
		Thread.sleep(6000);
		Sync.waitElementPresent("xpath", "//*[@id='iosc-summary']/div[5]/button");
		Common.clickElement(By.xpath("//*[@id='iosc-summary']/div[5]/button"));
		Thread.sleep(6000);
		Common.clickElement("xpath","//button[contains(@class,'action-accept')]");
		Thread.sleep(4000);
		Common.clickElement("xpath","//button[contains(@class,'action-accept')]");


	}
}

public void searchProducts(String dataSet) throws Exception{

	try {
		Thread.sleep(6000);
		//Sync.waitElementPresent("xpath", "//button[@title='Back To Top']");

		Sync.waitElementPresent("xpath", "//a[@title='Search']");
		Common.clickElement("xpath", "//a[@title='Search']");
		Sync.waitElementPresent("id", "search");
		Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName"));
		Sync.waitElementClickable("xpath", "//button[@title='Search']");
		Common.clickElement(By.xpath("//button[@title='Search']"));
		report.addPassLog("Should display searchProducts", "searchProducts display successfully", Common.getscreenShotPathforReport("searchProducts success"));

	}
	catch(Exception |Error e)
	{
		report.addFailedLog("Should display searchProducts", "searchProducts display successfully", Common.getscreenShotPathforReport("searchProducts Failed"));
		e.printStackTrace();
		Assert.fail();
	}

}




public void couponCode(String dataSet){
	try{
		Sync.waitPageLoad();
		Sync.waitElementPresent("id","block-discount-heading");
		
	Common.clickElement("id","block-discount-heading");
	System.out.println(data.get(dataSet).get("couponCode"));
	Thread.sleep(3000);
    Common.textBoxInput("id", "discount-code",data.get(dataSet).get("couponCode"));
    Thread.sleep(2000);
    Common.clickElement("xpath", "//button[@value='Apply Code']");
    
    Thread.sleep(5000);
	int size=Common.findElements("xpath", "//div[contains(@class,'message-success')]/div").size();
	Common.assertionCheckwithReport(size>0, "validating the offer code", "offer code was added.", "successfully offer code added","Failed to added offer code");
    }
	catch (Exception | Error e) {
		e.printStackTrace();
        ExtenantReportUtils.addFailedLog("validating offer code","offer code is applicable","User faield to add offer code",Common.getscreenShotPathforReport("offercode"));
        Assert.fail();
        }
}
public void increaseProductsQuantity(String Quantity) throws Exception{
	try{
	Sync.waitPageLoad();
	
	int size=Common.findElements("xpath", "//select[@id='qty']").size();
	
	if(size>0){
		Common.dropdown("xpath", "//select[@id='qty']",SelectBy.VALUE, Quantity);
	}
	else{
	for(int i=1;i<Integer.valueOf(Quantity);i++){
		Sync.waitElementPresent("id", "qty");
		Common.clickElement("xpath", "//input[@id='qty']/following::button[1]");
	    }
	
	}

	
	/*
	Common.findElement("id","qty").clear();
	Common.textBoxInput("id", "qty",Quantity);*/
	
	ExtenantReportUtils.addPassLog("verifying product Quntity", "User increaseProductQuantity", "user successfully increaseProductQuantity", Common.getscreenShotPathforReport("productIncr"));
	}
	catch(Exception |Error e) {
	     e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying Hair Product category", "User navigate to hair product page", "user faield to click the Hair Product", Common.getscreenShotPathforReport("productIncr"));
		Assert.fail();
	}
}


public void CreateAccount(String dataSet, String email) {
	// TODO Auto-generated method stub
	try{
		Thread.sleep(5000);
		Common.clickElement("xpath", "(//a[@class='account-link top-link'])[2]");
		Thread.sleep(3000);
		ExtenantReportUtils.addPassLog("verifying login page", "It should lands on login page", "user  lands on Customer login form Page", Common.getscreenShotPathforReport("Login page"));
	Common.clickElement("xpath", "(//span[text()='Create an Account'])[1]");
	
	ExtenantReportUtils.addPassLog("verifying Create Account button", "It should lands on Create New Customer from Account form Page", "user  lands on Customer Account creation form Page", Common.getscreenShotPathforReport("createaccount"));
	}
	catch(Exception |Error e) {
		e.printStackTrace();
	        ExtenantReportUtils.addFailedLog("verifying Create Account button", "It should lands on Create New Customer from Account form Page", "user faield lands on Account form Page", Common.getscreenShotPathforReport("createaccount"));
			Assert.fail();
		}
	
	try{
	Thread.sleep(5000);
	Common.textBoxInput("id", "firstname",data.get(dataSet).get("FirstName"));
    Common.textBoxInput("id", "lastname",data.get(dataSet).get("LastName"));
	Common.clickElement("xpath", "//input[@id='is_subscribed']");
	Common.textBoxInput("id", "email_address",email);
	//String email = Common.genrateRandomEmail(data.get(dataSet).get("Email"));
	Common.textBoxInput("id", "password",data.get(dataSet).get("Password"));
	Common.textBoxInput("id", "password-confirmation", data.get(dataSet).get("Password"));
	Common.clickElement("xpath", "//button[@title='Create an Account']");
	Sync.waitPageLoad();
	Thread.sleep(4000);
	String title=Common.getPageTitle();
	String Head = Common.findElement("xpath", "(//h1[@class='page-title'])").getText();
	System.out.println(title);
	Common.assertionCheckwithReport(title.equals("My Account | Hot Tools"), "verifying Create My Account functionality","User navigate to My Account page","user successfully created new account and landed on My Account page", "user faield to create new account");
	//ExtenantReportUtils.addPassLog("verifying Create My account Page", "It should lands on MyAccount  Page", "user lands on My Account  Page", Common.getscreenShotPathforReport("My Account page"));
	
	}
	 catch(Exception |Error e) {
	        ExtenantReportUtils.addFailedLog("verifying  MyAccount page", "Account should be created successfully navigate to My Account page", "user faield to create account", Common.getscreenShotPathforReport("createaccountfaield"));
			Assert.fail();
		}
	
}

public void MyOrder_Confirmation() {
	
	try {
		
		
		Common.clickElement("xpath", "(//img[@alt='account icon'])[2]");
	Thread.sleep(3000);
		//Sync.waitElementClickable(30, By.xpath("//ul[@class='header links']//li[2]/a"));
		Common.findElement("xpath", "(//a[@class='my-account-link'])[2]").click();
		Thread.sleep(7000);
		Common.clickElement("xpath", "(//a[text()='My Orders'])");
		Sync.waitPageLoad();
		String Title= Common.getPageTitle();
		Thread.sleep(5000);
	    System.out.println(Title);
	    Thread.sleep(3000);
	    String Ordernumber=Common.getText("xpath", "(//td[@data-th='Order #'])[1]");
	    System.out.println(Ordernumber);
		
		
		
		
	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying order number in Myorders ", "Click the My orders","User failed to clcik the Myorder link",Common.getscreenShotPathforReport("My orders"));
		Assert.fail();

	
	}}

public void Share_WishList(String dataSet) {
	// TODO Auto-generated method stub
	try {
	Sync.waitElementPresent("xpath", "(//a[text()='here'])");
	
	Common.clickElement("xpath", "(//a[text()='here'])");
	Common.scrollIntoView("xpath", "(//span[text()='Share Wish List'])");
	Common.clickElement("xpath", "(//span[text()='Share Wish List'])");
	Common.textBoxInput("xpath", "(//textarea[@id='email_address'])",data.get(dataSet).get("Email"));
	Common.textBoxInput("xpath", "(//textarea[@id='message'])",data.get(dataSet).get("Message"));
	Common.clickElement("xpath", "(//span[text()='Share Wish List'])");


	}
	catch(Exception e) {
		Assert.fail();
}	}


public void Shipping_PoBox_Adress(String dataSet) {
	// TODO Auto-generated method stub
	
		String expectedResult = "shipping address is entering in the fields";
	    int size = Common.findElements(By.xpath("//span[contains(text(),'New Address')]")).size();
		if (size > 0) {

			try {
				Thread.sleep(5000);
				Common.clickElement("xpath", "//span[contains(text(),'New Address')]");
				Common.textBoxInput("xpath", "(//input[@name='firstname'])",
						data.get(dataSet).get("FirstName"));
				Common.textBoxInput("xpath", "(//input[@name='lastname'])",
						data.get(dataSet).get("LastName"));
				Thread.sleep(5000);
				Common.textBoxInput("xpath", "(//input[@name='street[0]'])",
						data.get(dataSet).get("Street"));
				Thread.sleep(2000);
				Common.actionsKeyPress(Keys.SPACE);
				Thread.sleep(3000);
				
				if (data.get(dataSet).get("StreetLine2") != null) {
					Common.textBoxInput("name", "street[1]", data.get(dataSet).get("Street"));
				}
			if (data.get(dataSet).get("StreetLine3") != null) {
					Common.textBoxInput("name", "street[2]", data.get(dataSet).get("Street"));
				}
				Common.actionsKeyPress(Keys.PAGE_DOWN);
				Thread.sleep(5000);
				Common.textBoxInput("xpath", "(//input[@name='city'])",
						data.get(dataSet).get("City"));
				// Common.mouseOverClick("name", "region_id");
				try {
					Common.dropdown("xpath", "(//select[@name='region_id'])", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
				} catch (ElementClickInterceptedException e) {
					// TODO: handle exception
					Thread.sleep(3000);
					Common.dropdown("xpath", "(//select[@name='region_id'])", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
				}
				Thread.sleep(4000);
				Common.textBoxInputClear("xpath", "(//input[@name='postcode'])");
				Common.textBoxInput("xpath", "(//input[@name='postcode'])",
						data.get(dataSet).get("postcode"));
				
				Common.textBoxInput("xpath", "(//input[@name='telephone'])",
						data.get(dataSet).get("phone"));
				Thread.sleep(2000);
	            Sync.waitElementPresent("xpath", "(//span[text()='Save in address book'])");
	            Common.clickElement("xpath", "(//span[text()='Save in address book'])");
				ExtenantReportUtils.addPassLog("validating the shipping address", expectedResult,
						"user add the shipping address",
						Common.getscreenShotPathforReport("passed to add shipping address"));


				Common.clickElement("xpath", "//button[contains(@class,'save-address')]");
				int sizeerrormessage = Common.findElements("xpath", "//span[contains(text(),'This is a required field')]").size();

				Common.assertionCheckwithReport(sizeerrormessage <= 0, "verifying shipping addres filling ",
						"user will fill the all the shipping", "user fill the shiping address click save button",
						"passed to add new shipping address");
				  Common.actionsKeyPress(Keys.PAGE_DOWN);
				String A = Common.findElement("xpath", "(//div[@id='checkout-step-shipping_method'])").getText();
				String B = "Oops. Looks like the item in your bag can't be shipped as requested. Please confirm the item is available to ship to your location.";
				Assert.assertEquals(A, B);
}
		catch(Exception e) {
			Assert.fail();
	}	
			
		}
}
		
		
		



public void SortByMostViewed() {
	// TODO Auto-generated method stub
	try {
		Sync.waitElementPresent("xpath", "(//select[@id='sorter'])");
		Common.clickElement("xpath", "(//select[@id='sorter'])");
		Common.dropdown("xpath", "(//select[@id='sorter'])", SelectBy.TEXT,"Most Viewed");
		Thread.sleep(5000);
		Close_popup();
		
		String Url = Common.getCurrentURL();
		System.out.println("The url is" +Url);
		Common.assertionCheckwithReport(Url.contains("product_list_order=most_viewed"), "To validate most viewed sort option","most viewed sort option validated","Failed to validate most viewed sort option");
	}
	catch(Exception e) {
		Assert.fail();
	}
}


public void SortByNew() {
	// TODO Auto-generated method stub
	try {
		Sync.waitElementPresent("xpath", "(//select[@id='sorter'])");
		Common.clickElement("xpath", "(//select[@id='sorter'])");
		Common.dropdown("xpath", "(//select[@id='sorter'])", SelectBy.TEXT,"New");
		Thread.sleep(5000);
		String Url = Common.getCurrentURL();
		System.out.println("The url is" +Url);
		Common.assertionCheckwithReport(Url.contains("product_list_order=new"), "To validate most viewed sort option","most viewed sort option validated","Failed to validate most viewed sort option");
	}
	catch(Exception e) {
		Assert.fail();
	}
}


public void SortBy_Price_Low_To_High() {
	// TODO Auto-generated method stub
	try {
		Sync.waitElementPresent("xpath", "(//select[@id='sorter'])");
		Common.clickElement("xpath", "(//select[@id='sorter'])");
		Common.dropdown("xpath", "(//select[@id='sorter'])", SelectBy.TEXT,"Price: low to high");
		Thread.sleep(5000);
		String Url = Common.getCurrentURL();
		System.out.println("The url is" +Url);
		Common.assertionCheckwithReport(Url.contains("product_list_order=price_asc"), "To validate most viewed sort option","most viewed sort option validated","Failed to validate most viewed sort option");
	}
	catch(Exception e) {
		Assert.fail();
}
}


public void SortBy_Price_High_To_Low() {
	// TODO Auto-generated method stub
	try {
		Sync.waitElementPresent("xpath", "(//select[@id='sorter'])");
		Common.clickElement("xpath", "(//select[@id='sorter'])");
		Common.dropdown("xpath", "(//select[@id='sorter'])", SelectBy.TEXT,"Price: high to low");
		Thread.sleep(5000);
		String Url = Common.getCurrentURL();
		System.out.println("The url is" +Url);
		Common.assertionCheckwithReport(Url.contains("product_list_order=price_desc"), "To validate most viewed sort option","most viewed sort option validated","Failed to validate most viewed sort option");
	}
	catch(Exception e) {
		Assert.fail();
}	
}


public void SortBy_Productname() {
	// TODO Auto-generated method stub
	try {
		Sync.waitElementPresent("xpath", "(//select[@id='sorter'])");
		Common.clickElement("xpath", "(//select[@id='sorter'])");
		Common.dropdown("xpath", "(//select[@id='sorter'])", SelectBy.TEXT,"Product Name");
		Thread.sleep(5000);
		String Url = Common.getCurrentURL();
		System.out.println("The url is" +Url);
		Common.assertionCheckwithReport(Url.contains("product_list_order=name"), "To validate most viewed sort option","most viewed sort option validated","Failed to validate most viewed sort option");
	}
	catch(Exception e) {
		Assert.fail();
}	
}


public void SortBy_Top_Rated() {
	// TODO Auto-generated method stub
	try {
		Sync.waitElementPresent("xpath", "(//select[@id='sorter'])");
		Common.clickElement("xpath", "(//select[@id='sorter'])");
		Common.dropdown("xpath", "(//select[@id='sorter'])", SelectBy.TEXT,"Top Rated");
		Thread.sleep(5000);
		String Url = Common.getCurrentURL();
		System.out.println("The url is" +Url);
		Common.assertionCheckwithReport(Url.contains("product_list_order=rating_summary"), "To validate most viewed sort option","most viewed sort option validated","Failed to validate most viewed sort option");
	}
	catch(Exception e) {
		Assert.fail();
}	
}




}






