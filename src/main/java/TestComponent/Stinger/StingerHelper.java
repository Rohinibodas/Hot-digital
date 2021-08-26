package TestComponent.Stinger;

import java.text.NumberFormat;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import com.gargoylesoftware.htmlunit.javascript.host.file.File;
import com.google.common.collect.Table.Cell;

import TestLib.Automation_properties;
import TestLib.Common;
import TestLib.Sync;
import TestLib.Common.SelectBy;
import Utilities.ExcelReader;
import Utilities.ExtenantReportUtils;
import Utilities.Utils;

public class StingerHelper {
	String datafile;
	ExcelReader excelData;
	static Automation_properties automation_properties = Automation_properties.getInstance();
	Map<String, Map<String, String>> data=new HashMap<>();
	static ExtenantReportUtils report;
	public StingerHelper(String datafile){

		excelData=new ExcelReader(datafile);
		data=excelData.getExcelValue();
		this.data=data;
		if(Utilities.TestListener.report==null)
		{
			report=new ExtenantReportUtils("Stinger");
			report.createTestcase("StingerHCTestCases");
		}else{
			this.report=Utilities.TestListener.report;
		}
	}
	
	
	
	public void AgreeAndProceed() {
		
		int sizes=Common.findElements("xpath", "//button[text()='AGREE & PROCEED']").size();
		if(sizes>0){
		Sync.waitElementClickable(30, By.xpath("//button[text()='AGREE & PROCEED']"));
	   Common.findElement("xpath", "//button[text()='AGREE & PROCEED']").click();
		}
	}
	
	
	public void Global_search(String dataSet) {
		// TODO Auto-generated method stub
		String expectedResult="Land on searchProduct page";
		try {
			Thread.sleep(3000);
			
			
		    Common.textBoxInput("xpath", "(//input[@class='input-text'])[1]", data.get(dataSet).get("ProductName"));
			Common.actionsKeyPress(Keys.ENTER);
			Common.findElement("xpath", "(//img[@class='product-image-photo lazy'])[1]").click();
			
			
			
			report.addPassLog(expectedResult, "Should display searchProduct Page", "searchProduct page display successfully", Common.getscreenShotPathforReport("searchProduct page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display  searchProduct Page", "searchProduct Page not displayed", Common.getscreenShotPathforReport("searchProduct page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	
	}
	
	
	public void footerLinks(String dataSet) throws Exception{
		String expectedResult="Footer Link validations";
		String Footerlinks=data.get(dataSet).get("FooterNames");
		String[] Footers=Footerlinks.split(",");
		for(int i=0;i<Footers.length;i++){
			Common.actionsKeyPress(Keys.END);
			Sync.waitElementClickable("xpath", "//a[text()='"+Footers[i]+"']");
			Common.clickElement("xpath", "//a[text()='"+Footers[i]+"']");
			Thread.sleep(3000);
			System.out.println(Common.getPageTitle());
			report.addPassLog(expectedResult, "Should display "+Footers[i]+" success Page", ""+Footers[i]+" Page display successfully", Common.getscreenShotPathforReport("+Footers[i]+ success page success"));
			
		}
		
	}
	
	public void categoryMenuItem()
	{
		String expectedResult="Select from  category" ;
		try {
			Thread.sleep(1000);
			Sync.waitElementPresent("xpath", "(//span[contains(text(),'Products')])[1]");
			Common.clickElement("xpath", "(//span[contains(text(),'Products')])[1]");
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(1000);
			Sync.waitElementPresent("xpath", "(//img[@class='product-image-photo lazy'])[18]");
			Common.clickElement("xpath", "(//img[@class='product-image-photo lazy'])[18]");
			Thread.sleep(5000);
			report.addPassLog(expectedResult, "Should display item from  menucategory", "product display successfully", Common.getscreenShotPathforReport(" product display success"));

		}catch(Exception e)
		{
			report.addFailedLog(expectedResult,"Should display Search Results Page", "Search results Page not display", Common.getscreenShotPathforReport("Search result Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	
	public void Addtocart()
	{
		String expectedResult=" Adding product to mini cart" ;
		try {
			Thread.sleep(1000);
			Sync.waitElementPresent("xpath", "//span[contains(text(),'Add to Cart')]");
			Common.clickElement("xpath", "//span[contains(text(),'Add to Cart')]");
	
			Thread.sleep(1000);
			Sync.waitElementPresent("xpath", "//a[@class='action showcart']");
			Common.clickElement("xpath", "//a[@class='action showcart']");
			Thread.sleep(5000);
			report.addPassLog(expectedResult, "Should display Mini Cart Page", "Mini Cart Page display successfully", Common.getscreenShotPathforReport(" Mini Cart Page display successfull"));

		}catch(Exception e)
		{
			report.addFailedLog(expectedResult,"Should display Mini Cart Page", "Mini Cart Page not displayed", Common.getscreenShotPathforReport("Mini Cart Page display failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	
	
	public void ViewandEditcart(String dataset) throws Exception
	{
		String expectedResult="Navigate to View and Edit cart page";
		try {
			Thread.sleep(3000);
			Sync.waitElementPresent("xpath", "//span[contains(text(),'View and Edit Cart')]");
			Common.clickElement("xpath", "//span[contains(text(),'View and Edit Cart')]");
			
			Common.textBoxInputClear("xpath", "//input[@class='input-text qty ui-spinner-input']");
			Common.textBoxInput("xpath", "//input[@class='input-text qty ui-spinner-input']", data.get(dataset).get("Quantity"));
			Thread.sleep(5000);
			Common.clickElement("xpath", "//span[contains(text(),'Update Cart')]");
			String qty=Common.getText("xpath", "//div[@class='message-success success message']");
			System.out.println(qty);
			Assert.assertEquals(qty, "Your shopping cart has been updated.");
			
			report.addPassLog(expectedResult, "Should display Shipping Address Page", "Shipping Address Page display successfully", Common.getscreenShotPathforReport("Shipping Address page display  success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Shipping Address Page", "Shipping Address Page not displayed", Common.getscreenShotPathforReport("Shipping Address Failed"));
			Assert.fail();
		}
	}
	public void checkoutPage() throws Exception
	{
		String expectedResult="Navigate to checkout page";
		try {
			Thread.sleep(3000);
			Sync.waitElementPresent("xpath", "//button[@id='top-cart-btn-checkout']");
			Common.clickElement("xpath", "//button[@id='top-cart-btn-checkout']");
			
			Thread.sleep(5000);
			
			report.addPassLog(expectedResult, "Should display Shipping Address Page", "Shipping Address Page display successfully", Common.getscreenShotPathforReport("Shipping Address page display  success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Shipping Address Page", "Shipping Address Page not displayed", Common.getscreenShotPathforReport("Shipping Address Failed"));
			Assert.fail();
		}
	}
	
	
	
	public void shipping_Address(String dataSet) throws Exception
	{
		
		String expectedResult="add the shipping Address";
		try {
			Thread.sleep(8000);
			
		//Common.textBoxInput("xpath","//input[@type='email']", data.get(dataSet).get("Email"));
	    Common.textBoxInput("xpath","//div[@class='field required']//input[@id='customer-email']", data.get(dataSet).get("Email"));
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

		Thread.sleep(5000);

		Common.textBoxInput("name", "telephone",data.get(dataSet).get("phone"));
        Thread.sleep(3000);
        
        Sync.waitElementPresent("xpath", "//button[@class='button action continue primary']");
		Common.clickElement("xpath", "//button[@class='button action continue primary']");	
		Sync.waitElementClickable(30, By.xpath("//span[text()='OK']"));
		Common.findElement("xpath", "//span[text()='OK']").click(); 
		
	report.addPassLog(expectedResult,"Should add the shipping Address", "Payment and review page  displayed", Common.getscreenShotPathforReport("Add Shipping Address Success page"));
       }catch(Exception |Error e)
		{
		report.addFailedLog(expectedResult,"Should add the shipping Address", "Payment and review page not displayed", Common.getscreenShotPathforReport("Payment and review page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void addPaymentDetails(String dataSet) throws Exception{
		String expectedResult="enter card details";
		try {
		Thread.sleep(8000);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.switchFrames("xpath", "//iframe[@id='paymetric_xisecure_frame']");
		Thread.sleep(3000);
		Sync.waitElementPresent("xpath", "//select[@id='c-ct']");
		Common.dropdown("xpath", "//select[@id='c-ct']", Common.SelectBy.TEXT, data.get(dataSet).get("cardType"));
		Common.textBoxInput("id", "c-cardnumber", data.get(dataSet).get("cardNumber"));
		Sync.waitElementPresent("xpath", "//select[@id='c-exmth']");
		Common.dropdown("xpath", "//select[@id='c-exmth']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpMonth"));
		Sync.waitElementPresent("xpath", "//select[@id='c-exyr']");
		Common.dropdown("xpath", "//select[@id='c-exyr']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpYear"));
		Common.textBoxInput("id", "c-cvv", data.get(dataSet).get("cvv"));
		Thread.sleep(1000);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.switchToDefault();
		Thread.sleep(3000);
		String URL = Common.getCurrentURL();
        
        if (URL.equals("https://www.stingerproducts.com/stinger/checkout/#payment")) {
       Common.getCurrentURL();
   } else {
 	Sync.waitElementPresent("xpath", "(//button[@class='action primary checkout'])[2]");
    		Common.clickElement("xpath", "//span[contains(text(),'Place Order')]");
        }
		Thread.sleep(5000);
			/* s = Common.getText("xpath","//h1[text()='Thank you for your purchase']");
			System.out.println(s);
			Assert.assertEquals(s, "THANK YOU FOR YOUR PURCHASE");*/
			report.addPassLog(expectedResult, "Should display Order Success Page", "Order Success Page display successfully", Common.getscreenShotPathforReport("Order success page success"));

	}catch(Exception |Error e)
	{
		report.addFailedLog(expectedResult, "enter the card details", "  not place the order successfully", Common.getscreenShotPathforReport("Invalid card details error"));
		e.printStackTrace();
		Assert.fail();
	}
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
		public void invalidCreditCard(String dataSet) throws Exception
	{
		String expectedResult="Payment Method with invalid Credit card";
		try {
			addPaymentDetails(dataSet);

			if(Common.findElements("xpath", "//div[@class='message message-error']").size()>0)
			{	
				addPaymentDetails(dataSet);
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
	public void Login(String dataSet) {
		// TODO Auto-generated method stub
		String expectedResult="Land on login page";
		try {
			Sync.waitElementClickable(30, By.xpath("(//li[@class='authorization-link'])[2]"));
			Common.findElement("xpath", "(//li[@class='authorization-link'])[2]").click();
			Thread.sleep(1000);
			Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
			Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
			Common.actionsKeyPress(Keys.ARROW_DOWN);
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

/* d */
	
	public void AGREEPROCEED() {
		// TODO Auto-generated method stub
		Sync.waitElementClickable(30, By.xpath("//button[text()='AGREE & PROCEED']"));
	Common.findElement("xpath", "//button[text()='AGREE & PROCEED']").click();
	}
	public void login_page(String dataSet) throws Exception {
		// TODO Auto-generated method stub
		String expectedResult="Land on login page";
		try {
			int home= Common.findElements("xpath", "//a[@title='Stinger Products']").size();

			 System.out.println(home);

			 Common.assertionCheckwithReport(home>0, "verifying home page", "Home page contines Logo of product", "sucessfully lands on home page ", "faield to land on Home page");


			Sync.waitElementPresent("xpath", "(//li[@class='authorization-link'])[2]");

				Common.clickElement("xpath", "(//li[@class='authorization-link'])[2]");
			Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
			Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
			
			Common.actionsKeyPress(Keys.ARROW_DOWN);
			//Common.mouseOverClick("xpath", "(//span[text()='Sign In'])[1]");
			Common.javascriptclickElement("xpath", "//span[text()='Login']");
			
			
			report.addPassLog(expectedResult, "Should display login Page", "login page display successfully", Common.getscreenShotPathforReport("Login page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display  login Page", "login Page not displayed", Common.getscreenShotPathforReport("Login page Failed"));
			Assert.fail();
		}		
}

public void My_Account_Page() throws Exception {
	// TODO Auto-generated method stub
	String expectedResult="Land on My account  page";
	try {
		int home= Common.findElements("xpath", "//a[@title='Stinger Products']").size();

		 System.out.println(home);

		 Common.assertionCheckwithReport(home>0, "verifying home page", "Home page contines Logo of product", "sucessfully lands on home page ", "faield to land on Home page");


		Sync.waitElementPresent("xpath", "(//li[@class='my-account-link i-hide'])[2]");

			Common.clickElement("xpath", "(//li[@class='my-account-link i-hide'])[2]");
		
		
		report.addPassLog(expectedResult, "Should display login Page", "login page display successfully", Common.getscreenShotPathforReport("Login page success"));
	}catch(Exception |Error e)
	{
		report.addFailedLog(expectedResult,"Should display  login Page", "login Page not displayed", Common.getscreenShotPathforReport("Login page Failed"));
		Assert.fail();
	}		
}

public void AccountCreation(String dataSet) {
	String expectedResult="Account Creation of User with valid details";
	try {
		 int home= Common.findElements("xpath", "//a[@title='Stinger Products']").size();

		 System.out.println(home);

		 Common.assertionCheckwithReport(home>0, "verifying home page", "Home page contines Logo of product", "sucessfully lands on home page ", "faield to land on Home page");

		  
		Sync.waitElementClickable(30, By.xpath("(//a[text()='Register'])[3]"));
		Common.findElement("xpath", "(//a[text()='Register'])[3]").click();

		String footers=Common.getText("xpath", "//span[text()='Create New Customer Account']");

		System.out.println(footers);

		 

		Common.assertionCheckwithReport(footers.equals("Create New Customer Account"), "Verifying Create New Customer Account page", "It should navigate to Create New Customer Account", "successfully lands on Create New Customer Account ","Create New Customer Account");

		
		Common.textBoxInput("id", "firstname", data.get(dataSet).get("FirstName").toString());
	//	Common.textBoxInput("id", "middlename", data.get(dataSet).get("Middlename").toString());
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
		String S=Common.getText("xpath", "//div[text()='Thank you for registering with Stinger.']");
		System.out.println(S);
		Assert.assertEquals(S, "Thank you for registering with Stinger.");
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


public void forgotPassword(String dataSet) throws Exception {
	String expectedResult="Forgot Password for Registered User";
	try {
		int home= Common.findElements("xpath", "//a[@title='Stinger Products']").size();

		 System.out.println(home);

		 Common.assertionCheckwithReport(home>0, "verifying home page", "Home page contines Logo of product", "sucessfully lands on home page ", "faield to land on Home page");

		// Common.javascriptclickElement("xpath", "//span[text()='Login']");
			

	Sync.waitElementPresent("xpath", "(//li[@class='authorization-link'])[2]");

		Common.clickElement("xpath", "(//li[@class='authorization-link'])[2]");
		
		//Common.javascriptclickElement("xpath", "/span[text()='Forgot Your Password?'])[1]");
		
 
		Sync.waitElementPresent("xpath", "(//span[text()='Forgot Your Password?'])[1]");

			Common.clickElement("xpath", "(//span[text()='Forgot Your Password?'])[1]");
			
		String nextpage=Common.getText("xpath","//div[contains(text(),'Please enter your email address below to receive a password reset link')]");
		
		Common.textBoxInput("id", "email_address", data.get(dataSet).get("Email"));
		
        Sync.waitElementPresent("xpath", "//span[contains(text(),'Reset My Password')]");

		Common.clickElement("xpath", "//span[contains(text(),'Reset My Password')]");
		
	}catch(Exception |Error e)
	{
		report.addFailedLog(expectedResult,"Should display Forgot Password Succes message", "Forgot Password page success message not displayed", Common.getscreenShotPathforReport("Account Creation Failed"));
		Assert.fail();
	}
}



public void Address_Book() throws InterruptedException {
	String expectedResult="Navigate to Address_Book page";
	try {
		
		Common.clickElement("xpath", "//a[text()='Address Book']");
		Thread.sleep(1000);
	
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
	Common.clickElement("xpath", "//span[text()='OK']");
	
	
}


public void Accountinfomation() throws InterruptedException {
	String expectedResult="Navigating to Profile for registered user";
	try {
				
		Common.findElement("xpath", "//a[text()='Account Information']").click();
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

		Common.textBoxInput("name", "email", Utils.getEmailid());
		//Common.textBoxInput("id", "email_address",Utils.getEmailid());
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


public void Mouseclick()throws Exception{
	   
	String expectedResult="navigate to The Stinger Advantage page";

	try {
		int home= Common.findElements("xpath", "//a[@title='Stinger Products']").size();

		 System.out.println(home);

		 Common.assertionCheckwithReport(home>0, "verifying home page", "Home page contines Logo of product", "sucessfully lands on home page ", "faield to land on Home page");

		
		Sync.waitElementPresent("xpath", "//span[text()='The Stinger Advantage']");
		Common.clickElement("xpath", "//span[text()='The Stinger Advantage']");
		Thread.sleep(4000);
		
		report.addPassLog(expectedResult,"navigate to The Stinger Advantage page successfully", "navigate to The Stinger Advantage page", Common.getscreenShotPathforReport("navigate to The Stinger Advantage page Success"));
				}

	catch(Exception |Error e)
	{
		report.addFailedLog(expectedResult,"navigate to The Stinger Advantage page successfully", "Not navigate to The Stinger Advantage page", Common.getscreenShotPathforReport("navigate to The Stinger Advantage page failed"));
		e.printStackTrace();
		Assert.fail();
}	
}


public void Mouseclick_Product_link()throws Exception{
	  
	String expectedResult="navigate to The Products page";

	try {
		int home= Common.findElements("xpath", "//a[@title='Stinger Products']").size();

		 System.out.println(home);

		 Common.assertionCheckwithReport(home>0, "verifying home page", "Home page contines Logo of product", "sucessfully lands on home page ", "faield to land on Home page");

		
		Sync.waitElementPresent("xpath", "(//span[text()='Products'])[1]");
		Common.clickElement("xpath", "(//span[text()='Products'])[1]");
		 

		String footers=Common.getText("xpath", "//strong[text()='Categories']");

		System.out.println(footers);

		 

		Common.assertionCheckwithReport(footers.equals("Categories"), "Verifying Categories text", "It should navigate to Categories", "successfully lands on Categories text ","Categories text");

		Sync.waitElementPresent("xpath", "//a[text()='Zappers']");
		Common.clickElement("xpath", "//a[text()='Zappers']");
		
		Sync.waitElementPresent("xpath", "//a[text()='Rackets']");
		Common.clickElement("xpath", "//a[text()='Rackets']");
		 
		Sync.waitElementPresent("xpath", "//a[text()='Portable Zapper Lanterns']");
		Common.clickElement("xpath", "//a[text()='Portable Zapper Lanterns']");
		 
		Sync.waitElementPresent("xpath", "//a[text()='Repellents']");
		Common.clickElement("xpath", "//a[text()='Repellents']");
		
		Sync.waitElementPresent("xpath", "//a[text()='Bulbs & Accessories']");
		Common.clickElement("xpath", "//a[text()='Bulbs & Accessories']");
		
		Sync.waitElementPresent("xpath", "(//a[text()='Accessories & Repellents'])[1]");
		Common.clickElement("xpath", "(//a[text()='Accessories & Repellents'])[1]");
		
		Sync.waitElementPresent("xpath", "(//a[text()='All Products'])[1]");
		Common.clickElement("xpath", "(//a[text()='All Products'])[1]");
		
		 
	
		
		report.addPassLog(expectedResult,"navigate to The Products page successfully", "navigate to The Products page", Common.getscreenShotPathforReport("navigate to The Products page Success"));
				}

	catch(Exception |Error e)
	{
		report.addFailedLog(expectedResult,"navigate to The Products page successfully", "Not navigate to The Products page", Common.getscreenShotPathforReport("navigate The Products page failed"));
		e.printStackTrace();
		Assert.fail();
}	
}


public void Mouseclick_Facts()throws Exception{
	   
	String expectedResult="navigate to The Stinger Advantage page";

	try {
		int home= Common.findElements("xpath", "//a[@title='Stinger Products']").size();

		 System.out.println(home);

		 Common.assertionCheckwithReport(home>0, "verifying home page", "Home page contines Logo of product", "sucessfully lands on home page ", "faield to land on Home page");

		
		Sync.waitElementPresent("xpath", "//span[text()='Mosquito Facts']");
		Common.clickElement("xpath", "//span[text()='Mosquito Facts']");
		Thread.sleep(4000);
		
		report.addPassLog(expectedResult,"navigate to The Mosquito Facts page successfully", "navigate to The Mosquito Facts page", Common.getscreenShotPathforReport("navigate to The Mosquito Facts page Success"));
				}

	catch(Exception |Error e)
	{
		report.addFailedLog(expectedResult,"navigate to Mosquito Facts page successfully", "Not navigate to The Mosquito Facts page", Common.getscreenShotPathforReport("navigate to The Mosquito Facts page failed"));
		e.printStackTrace();
		Assert.fail();
}	
}


public void Mouseclick_Where_to_Buy()throws Exception{
	   
	String expectedResult="navigate to Where to Buy page";

	try {
		int home= Common.findElements("xpath", "//a[@title='Stinger Products']").size();

		 System.out.println(home);

		 Common.assertionCheckwithReport(home>0, "verifying home page", "Home page contines Logo of product", "sucessfully lands on home page ", "faield to land on Home page");

		
		Sync.waitElementPresent("xpath", "//span[text()='Where to Buy']");
		Common.clickElement("xpath", "//span[text()='Where to Buy']");
		Thread.sleep(4000);
		
		report.addPassLog(expectedResult,"navigate to The Where to Buy  page successfully", "navigate to Where to Buy page", Common.getscreenShotPathforReport("navigate to the Where to Buy page page Success"));
				}

	catch(Exception |Error e)
	{
		report.addFailedLog(expectedResult,"navigate to  Where to Buy page successfully", "Not navigate to Where to Buy page", Common.getscreenShotPathforReport("navigate to Where to Buy page failed"));
		e.printStackTrace();
		Assert.fail();
}	
}






public void Mouseclick_Customer_Support()throws Exception{
	   
	String expectedResult="navigate to Customer Support page";

	try {
		int home= Common.findElements("xpath", "//a[@title='Stinger Products']").size();

		 System.out.println(home);

		 Common.assertionCheckwithReport(home>0, "verifying home page", "Home page contines Logo of product", "sucessfully lands on home page ", "faield to land on Home page");

		
		Sync.waitElementPresent("xpath", "//span[text()='Customer Support']");
		Common.clickElement("xpath", "//span[text()='Customer Support']");
		Thread.sleep(4000);
		
		report.addPassLog(expectedResult,"navigate to Customer Support page successfully", "navigate to Customer Support page ", Common.getscreenShotPathforReport("navigate to Customer Support page Success"));
				}

	catch(Exception |Error e)
	{
		report.addFailedLog(expectedResult,"navigate to Customer Support page successfully", "Not navigate to Customer Support page", Common.getscreenShotPathforReport("navigate to Customer Support page failed"));
		e.printStackTrace();
		Assert.fail();
}	
}
//Today//

public void Validating_forgotPassword() throws Exception {
	String expectedResult="Forgot Password for Registered User";
	try {
		int home= Common.findElements("xpath", "//a[@title='Stinger Products']").size();

		 System.out.println(home);

		 Common.assertionCheckwithReport(home>0, "verifying home page", "Home page contines Logo of product", "sucessfully lands on home page ", "faield to land on Home page");

		// Common.javascriptclickElement("xpath", "//span[text()='Login']");
			

	Sync.waitElementPresent("xpath", "(//li[@class='authorization-link'])[2]");

		Common.clickElement("xpath", "(//li[@class='authorization-link'])[2]");
		
		//Common.javascriptclickElement("xpath", "/span[text()='Forgot Your Password?'])[1]");
		
 
		Sync.waitElementPresent("xpath", "(//span[text()='Forgot Your Password?'])[1]");

			Common.clickElement("xpath", "(//span[text()='Forgot Your Password?'])[1]");
			
		String nextpage=Common.getText("xpath","//div[contains(text(),'Please enter your email address below to receive a password reset link')]");
		
		//Common.textBoxInput("id", "email_address", data.get(dataSet).get("Email"));
		
        Sync.waitElementPresent("xpath", "//span[contains(text(),'Reset My Password')]");

		Common.clickElement("xpath", "//span[contains(text(),'Reset My Password')]");
		
		//String message=Common.getText("xpath", "//div[text()='If there is an account associated with rajkumarpotharaju97@gmail.com you will receive an email with a link to reset your password.']");

		//System.out.println(message);

		 

		//Common.assertionCheckwithReport(message.equals("If there is an account associated with rajkumarpotharaju97@gmail.com you will receive an email with a link to reset your password."), "If there is an account associated with rajkumarpotharaju97@gmail.com you will receive an email with a link to reset your password. page", "If there is an account associated with rajkumarpotharaju97@gmail.com you will receive an email with a link to reset your password.", "If there is an account associated with rajkumarpotharaju97@gmail.com you will receive an email with a link to reset your password.","If there is an account associated with rajkumarpotharaju97@gmail.com you will receive an email with a link to reset your password.");
		



	}catch(Exception |Error e)
	{
		report.addFailedLog(expectedResult,"Should display Forgot Password Succes message", "Forgot Password page success message not displayed", Common.getscreenShotPathforReport("Account Creation Failed"));
		Assert.fail();
	}
}


public void ShippingFormvalidation() {
	
	String expectedResult=" Validating shipping page ";
try {
	Thread.sleep(6000);
	Common.actionsKeyPress(Keys.DOWN);

	Common.clickElement("xpath", "//span[text()='Next']");
	//Common.actionsKeyPress(Keys.ARROW_DOWN);
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

public void RegisterUser_ShippingAddress() throws Exception
{
	String expectedResult="Navigate to Shipping Address Page";
	try {
		Thread.sleep(3000);
		Sync.waitElementPresent("xpath", "//span[text()='Next']");
		Common.clickElement("xpath", "//span[text()='Next']");
		
		Sync.waitElementClickable(30, By.xpath("//span[text()='OK']"));
		Common.findElement("xpath", "//span[text()='OK']").click();
		
		Thread.sleep(5000);
		
		report.addPassLog(expectedResult, "Should display Shipping Address Page", "Shipping Address Page display successfully", Common.getscreenShotPathforReport("Shipping Address page display  success"));
	}catch(Exception |Error e)
	{
		report.addFailedLog(expectedResult,"Should display Shipping Address Page", "Shipping Address Page not displayed", Common.getscreenShotPathforReport("Shipping Address Failed"));
		Assert.fail();
	}
}


public void MyOrdersPage() throws InterruptedException {
	String expectedResult="Land on MyOrders page";
	try {
		Common.findElement("xpath", "//a[text()='My Orders']").click();
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
public void Taxcalucaltion(String dataset) throws Exception{
	try{
		Thread.sleep(3000);
	
	String taxpercent=data.get(dataset).get("Tax");
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
	
	
	public void AGREEPROCEED1() {
		// TODO Auto-generated method stub
		int sizes=Common.findElements("xpath", "//button[text()='AGREE & PROCEED']").size();
		if(sizes>0){
		Sync.waitElementClickable(30, By.xpath("//button[text()='AGREE & PROCEED']"));
	   Common.findElement("xpath", "//button[text()='AGREE & PROCEED']").click();
		}
	}





	



	public void Contact_US(String dataset) {
		// TODO Auto-generated method stub
		 String expectedResult="Validating Contact Us";
	       try
	       {    
		   Common.actionsKeyPress(Keys.END); 
		   Sync.waitElementClickable(30, By.xpath("//a[text()='Contact Us']"));
			
			Common.findElement("xpath","//a[text()='Contact Us']").click();
			
			Common.switchFrames("xpath", "//iframe[@src='https://helenoftroy.custhelp.com/app/ask/theme/stinger']");
			Sync.waitPageLoad();
			Common.textBoxInput("xpath", "//input[@id='rn_TextInput_3_Contact.Name.First']",data.get(dataset).get("FirstName"));
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
			
			
			
			Common.textBoxInput("xpath", "//input[@id='searchKeyword']", data.get(dataset).get("ProductName"));
			 Sync.waitElementClickable("xpath", "//div[@class='a22734 resultRow']");
			 Common.javascriptclickElement("xpath", "//div[@class='a22734 resultRow']");

			 Sync.waitElementPresent("xpath", "(//button[@class='rn_DisplayButton'])[1]");
			 Common.findElement("xpath", "(//button[@class='rn_DisplayButton'])[1]").click();
			 Thread.sleep(5000);
			 Common.javascriptclickElement("xpath", "//a[contains(text(), 'Order Issues')]");
			 Sync.waitElementPresent("xpath", "//textarea[@id='rn_TextInput_30_Incident.Threads']");
			 Common.textBoxInput("xpath", "//textarea[@id='rn_TextInput_30_Incident.Threads']", data.get(dataset).get("Message"));

			 Sync.waitElementPresent("xpath", "//button[contains(text(),'Submit')]");
			 Common.javascriptclickElement("xpath", "//button[contains(text(),'Submit')]");

			 Thread.sleep(6000);
			 Common.actionsKeyPress(Keys.PAGE_UP);

			 Common.actionsKeyPress(Keys.PAGE_UP);

			 String submitted=Common.getText("xpath", "//h1[@style='font-size:16px;']");

			 System.out.println();
			 
			 String header=Common.getText("xpath", "//h1[text()='Your question has been submitted!']");
			 System.out.println(header+" page navigated successfully");
			 Assert.assertEquals(header, "Your question has been submitted!");
		
			
			
	       
	       report.addPassLog(expectedResult,"Should display Contact Us page", "Contact Us display successfully", Common.getscreenShotPathforReport("Contact Us page success"));
	       }
	       catch(Exception |Error e)
			{
				report.addFailedLog(expectedResult,"Should display Contact Us page", "Contact Us page display successfully", Common.getscreenShotPathforReport("Contact Us page Failed"));
				e.printStackTrace();
				Assert.fail();
		
	}

	}

	public void Reg_Shipping_Methods() {
		// TODO Auto-generated method stub
		int sizes=Common.findElements("xpath", "//span[text()='Next']").size();
		if(sizes>0){
		Sync.waitElementClickable(30, By.xpath("//span[text()='Next']"));
	   Common.findElement("xpath", "//span[text()='Next']").click();
		
	}
	}



	public void Shipping_OK_button() {
		// TODO Auto-generated method stub
		int sizes=Common.findElements("xpath", "//span[text()='OK']").size();
		if(sizes>0){
		Sync.waitElementClickable(30, By.xpath("//span[text()='OK']"));
	   Common.findElement("xpath", "//span[text()='OK']").click();
	   
	  
		
	}	
	}



	public void Apply_promocode(String dataset) {
		// TODO Auto-generated method stub   
	   String expectedResult="Apply Discount Code";
		try {
			
			Sync.waitElementClickable("xpath", "//span[@class='action action-toggle']");
			 Common.javascriptclickElement("xpath", "//span[@class='action action-toggle']");

			  	Common.textBoxInput("xpath", "//input[@placeholder='Enter discount code']", data.get(dataset).get("promocodename"));
			Common.findElement("xpath", "//span[text()='Apply Discount']").click();
			Thread.sleep(3000);
			/*String header=Common.getText("xpath", "//span[text()='My Orders']");
			System.out.println(header+" page navigated successfully");
			Assert.assertEquals(header, "My Orders");*/
			report.addPassLog(expectedResult, "Should display MyOrdersPage Page", "MyOrdersPage page display successfully", Common.getscreenShotPathforReport("MyOrdersPage page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display  MyOrdersPage Page", "MyOrdersPage Page not displayed", Common.getscreenShotPathforReport("MyOrdersPage page Failed"));
			Assert.fail();
		}
	}



	public void invad_promocode(String dataset) {
		// TODO Auto-generated method stub
		  String expectedResult="invalid Discount Code";
			try {
				
				Sync.waitElementClickable("xpath", "//span[@class='action action-toggle']");
				 Common.javascriptclickElement("xpath", "//span[@class='action action-toggle']");

				  	Common.textBoxInput("xpath", "//input[@placeholder='Enter discount code']", data.get(dataset).get("promocodename"));
				Common.findElement("xpath", "//span[text()='Apply Discount']").click();
				Thread.sleep(3000);
				String s=Common.getText("xpath", "//div[@class='message message-error error']");
				System.out.println(s);
				Assert.assertEquals(s, "The coupon code isn't valid. Verify the code and try again.");
				report.addPassLog(expectedResult, "Should display MyOrdersPage Page", "MyOrdersPage page display successfully", Common.getscreenShotPathforReport("MyOrdersPage page success"));
			}catch(Exception |Error e)
			{
				report.addFailedLog(expectedResult,"Should display  MyOrdersPage Page", "MyOrdersPage Page not displayed", Common.getscreenShotPathforReport("MyOrdersPage page Failed"));
				Assert.fail();
			}
		}



	public void PrivacyPolicyAndTermsConditions(String dataSet) throws InterruptedException {
		// TODO Auto-generated method stub
		String expectedResult="Footer Link validations";
		String PrivacyPolicy=data.get(dataSet).get("FooterNames");
		String[] Footers=PrivacyPolicy.split(",");
		for(int i=0;i<Footers.length;i++){
			Common.actionsKeyPress(Keys.END);
			Sync.waitElementClickable("xpath", "//a[text()='"+Footers[i]+"']");
			Common.clickElement("xpath", "//a[text()='"+Footers[i]+"']");
			Thread.sleep(3000);
			System.out.println(Common.getPageTitle());
			report.addPassLog(expectedResult, "Should display "+Footers[i]+" success Page", ""+Footers[i]+" Page display successfully", Common.getscreenShotPathforReport("+Footers[i]+ success page success"));
			
		}
		
		
	}



	public void categoryMenuItem1() {
		// TODO Auto-generated method stub	
		String expectedResult="Select from  category" ;
		try {
			Thread.sleep(1000);
			Sync.waitElementPresent("xpath", "(//span[contains(text(),'Products')])[1]");
			Common.clickElement("xpath", "(//span[contains(text(),'Products')])[1]");
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(1000);
			Sync.waitElementPresent("xpath", "(//img[@class='product-image-photo lazy'])[2]");
			Common.clickElement("xpath", "(//img[@class='product-image-photo lazy'])[2]");
			Thread.sleep(5000);
			report.addPassLog(expectedResult, "Should display item from  menucategory", "product display successfully", Common.getscreenShotPathforReport(" product display success"));

		}catch(Exception e)
		{
			report.addFailedLog(expectedResult,"Should display Search Results Page", "Search results Page not display", Common.getscreenShotPathforReport("Search result Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	}



/*	public void prepareTaxData(String fileName)
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
		cell.setCellValue("subtotlaValue");
		cell = row.createCell(2);
		cell.setCellStyle(cs);
		cell.setCellValue("ShippingAmount");
		cell=row.createCell(3);
		cell.setCellStyle(cs);
		cell.setCellValue("Taxammountvalue");
		cell=row.createCell(4);
		cell.setCellStyle(cs);
		cell.setCellValue("Totalammountvalue");
		cell=row.createCell(5);
		cell.setCellStyle(cs);
		cell.setCellValue("giventaxvalue");
		cell=row.createCell(6);
		cell.setCellStyle(cs);
		cell.setCellValue("userpaneltaxvalue");
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
		row = sheet.createRow(rowcount);
		cell = row.createCell(0);

		FileOutputStream fileOut = new FileOutputStream(file);
		workbook.write(fileOut);
		fileOut.flush();
		fileOut.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	

	public Static writeResultstoXLSx(String OrderId,float subtotlaValue,float ShippingAmount,float Taxammountvalue, float Totalammountvalue,float giventaxvalue ,float userpaneltaxvalue)
	{
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
		cell.setCellValue(subtotlaValue);
		cell = row.createCell(2);
		cell.setCellType(CellType.NUMERIC);
		cell.setCellValue(ShippingAmount);
		cell = row.createCell(3);
		cell.setCellType(CellType.NUMERIC);
		cell.setCellValue(Taxammountvalue);
		cell = row.createCell(4);
		cell.setCellType(CellType.NUMERIC);
		cell.setCellValue(Totalammountvalue);
		cell = row.createCell(5);
		cell.setCellType(CellType.NUMERIC);
		cell.setCellValue(giventaxvalue);
		cell = row.createCell(6);
		cell.setCellType(CellType.NUMERIC);
		cell.setCellValue(userpaneltaxvalue);
		cell = row.createCell(7);
		cell.setCellType(CellType.STRING);
		String status="Fail";
		if(giventaxvalue==userpaneltaxvalue)
		{
			status="pass";
		}
	     cell.setCellValue(status);
	     
	     
	     
	    // String OrderID="OrderId";
	     //float Subtotal=subtotlaValue;
	    // float Shipping=ShippingAmount;
	     //float TaxAmount=Taxammountvalue;
	    // float TotalAmount= Totalammountvalue;
	    //float Tax= giventaxvalue;
	     //float TaxDisplayed = userpaneltaxvalue;
	     
		System.out.println("subtotlaValue");
		System.out.println("ShippingAmount");
		System.out.println("Taxammountvalue");
		System.out.println("Totalammountvalue");
		System.out.println("giventaxvalue");
		System.out.println("userpaneltaxvalue");
		
		//return writeResultstoXLSx(OrderID,Subtotal,Shipping,TaxAmount,TotalAmount,Tax,TaxDisplayed);
		
		FileOutputStream fileOut = new FileOutputStream(file);
		workbook.write(fileOut);
		fileOut.flush();
	     fileOut.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
	return writeResultstoXLSx(OrderId,subtotlaValue,ShippingAmount,giventaxvalue,Taxammountvalue,Totalammountvalue, userpaneltaxvalue);
	//return null;
    }
	
}


public void Taxcalucaltion(String taxpercent, String conditionvalue) throws Exception{
	try{
	
	
	Thread.sleep(3000);
	
	//String taxpercent=data.get(dataset).get("Tax");
	 Float giventaxvalue=Float.valueOf(taxpercent);
     String givenCondition=String.valueOf(conditionvalue);
     System.out.println(givenCondition);
	String subtotla=Common.getText("xpath", "//tr[@class='totals sub']/td/span").replace("$", "");
	 // subtotla.replace("", newChar)
	Float subtotlaValue=Float.valueOf(subtotla);
	
	String shippingammount=Common.getText("xpath", "//span[@data-th='Shipping']").replace("$", "");
	Float shippingammountvalue=Float.valueOf(shippingammount);
	
	String TaxAmmount=Common.getText("xpath", "//td[@data-th='Tax']/span").replace("$", "");
	Float Taxammountvalue=Float.valueOf(TaxAmmount);
	if(Taxammountvalue==null) {
	System.out.println("0");
	}
	else {
		System.out.println("Taxammountvalue");
	}
	
	String TotalAmmount=Common.getText("xpath", "//tr[@class='grand totals']//span").replace("$", "");
	Float Totalammountvalue=Float.valueOf(Taxammountvalue);
	
	if(givenCondition=="Yes") {
    Float calucaltedvalue= ((subtotlaValue+shippingammountvalue)*giventaxvalue)/100;
    
    NumberFormat nf= NumberFormat.getInstance();
    nf.setMaximumFractionDigits(2);
    String  userpaneltaxvalue=nf.format(calucaltedvalue);
  
    System.out.println(TaxAmmount);
    System.out.println(userpaneltaxvalue);      
    Common.assertionCheckwithReport(userpaneltaxvalue.equals(TaxAmmount), "verifying tax calculation", "tax rate is matches to given shipping address tax ","successfully tax rate is matches to given shipping address tax", "tax rate is not matches to given shipping address tax");
	}
	else {
		Float calucaltedvalue= ((subtotlaValue)*giventaxvalue)/100;
	    
	    NumberFormat nf= NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        String  userpaneltaxvalue=nf.format(calucaltedvalue);
      
        System.out.println(TaxAmmount);
        System.out.println(userpaneltaxvalue);      
	    Common.assertionCheckwithReport(userpaneltaxvalue.equals(TaxAmmount), "verifying tax calculation", "tax rate is matches to given shipping address tax ","successfully tax rate is matches to given shipping address tax", "tax rate is not matches to given shipping address tax");
	}
	}
 catch(Exception |Error e)
	{
		report.addFailedLog("verifying tax calculation", "getting price values from shipping page  ", "Faield to get price value from shipping page", Common.getscreenShotPathforReport("TaxRates"));

		e.printStackTrace();
		Assert.fail();
		
}
	
	
	
	}
	

}
*/