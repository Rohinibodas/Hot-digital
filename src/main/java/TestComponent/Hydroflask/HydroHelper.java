package TestComponent.Hydroflask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import TestLib.Common;
import TestLib.Sync;
import TestLib.Common.SelectBy;
import Utilities.ExcelReader;

public class HydroHelper {

	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data=new HashMap<>();

	public void navigateMyAccount() throws InterruptedException
	{	
		Sync.waitElementClickable(30, By.xpath("//a[@class='social-login']"));
		Common.findElement("xpath", "//a[@class='social-login']").click();
		Thread.sleep(3000);
	}

	public void acceptPrivecy()
	{
		Common.clickElementStale("xpath", "//button[text()='AGREE & PROCEED']");
	}
	public void CreateNewAccount(String dataSet) throws Exception
	{
		navigateMyAccount();
		try {
			Sync.waitElementClickable(30, By.xpath("//div[contains(text(),'Sign Up')]"));
		}catch (Exception e) {
			if(Common.findElement("xpath", "//div[contains(text(),'Sign Up')]")==null)
			{
				Common.clickElement("xpath", "//a[@class='social-login']");
				Thread.sleep(2000);
			}
		}
		String email=Common.genrateRandomEmail(data.get(dataSet).get("Email"));
		Common.clickElement("xpath", "//div[contains(text(),'Sign Up')]");
		Common.textBoxInput("id", "social-login-popup-create-firstname", data.get(dataSet).get("FirstName"));
		Common.textBoxInput("id", "social-login-popup-create-lastname", data.get(dataSet).get("LastName"));
		Common.textBoxInput("id", "social-login-popup-create-email", email);
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Common.textBoxInput("id", "social-login-popup-create-pass", data.get(dataSet).get("Password"));
		Common.textBoxInput("id", "password-confirmation-social", data.get(dataSet).get("Password"));
		Common.clickElement("id", "social-login-popup-create-is-subscribed");
		Common.clickElement("xpath", "//button[@title='Sign Up']");
		//Common.actionsKeyPress(Keys.ESCAPE);
		Thread.sleep(2000);
		Sync.waitElementVisible("xpath", "//span[@data-ui-id='page-title-wrapper']");
		Assert.assertEquals(Common.getText("xpath", "//span[@data-ui-id='page-title-wrapper']"), "My Account");
	}	

	
	public void loginHydroflaskAccount(String dataSet) throws Exception
	{
		Thread.sleep(3000);
		navigateMyAccount();
		Sync.waitElementClickable(30, By.id("social-login-popup-log-in-email"));
		if(Common.findElement("id", "social-login-popup-log-in-email")==null)
		{
			Common.clickElement("xpath", "//a[@class='social-login']");
			Thread.sleep(2000);
		}
		Common.textBoxInput("id", "social-login-popup-log-in-email",data.get(dataSet).get("Email"));
		Common.textBoxInput("id", "social-login-popup-log-in-pass",data.get(dataSet).get("Password"));
		Common.clickElement("id", "bnt-social-login-authentication");
		Thread.sleep(2000);
		Assert.assertEquals(Common.getText("xpath", "//span[@data-ui-id='page-title-wrapper']"), "My Account");
	}
	
	public void orderSubmit(String category) throws Exception
	{
		Thread.sleep(8000);
		//Common.getDriver().switchTo().frame(0);
	//	Thread.sleep(2000);
		Sync.waitElementClickable("xpath", "//ul[@class='megamenu-list']/li[1]/div[1]");
		System.out.println(Common.getText("xpath", "//ul[@class='megamenu-list']/li[1]/div[1]/button"));
		Thread.sleep(4000);
		Common.mouseOverClick("xpath", "//ul[@class='megamenu-list']/li[1]/div[1]/button");
		//Common.clickElement("css", "ul.megamenu-list > li:nth-of-type(1) > div:nth-of-type(1) > button");
		Thread.sleep(3000);
		try {
		Common.mouseOver("xpath", "//a[contains(text(),'"+category+"')]");
		}catch (Exception e) {
			// TODO: handle exception
			Common.clickElement("xpath", "//ul[@class='megamenu-list']/li[1]/div[1]/button");
		}
		Thread.sleep(1000);
		Common.clickElement("xpath", "//a[contains(text(),'"+category+"')]");
		//Sync.waitElementVisible("xpath", "//div[text()='Drink in the adventure.']");
		Thread.sleep(4000);
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Thread.sleep(2000);
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Thread.sleep(4000);
		Sync.waitElementClickable("xpath", "//button[@title='Add to Cart']");
		Common.clickElement("xpath", "//button[@title='Add to Cart']");
		Thread.sleep(5000);
		}

	public void checkOut() throws Exception
	{
		Common.clickElement("xpath", "//a[@aria-label='minicart']");
		Thread.sleep(2000);
		Common.clickElement("id", "top-cart-btn-checkout");
		Sync.waitElementVisible("className", "checkout-step-title");

	}

	public void addDeliveryAddress(String dataSet) throws Exception
	{
		try {	
			Sync.waitElementVisible("id", "customer-email-address");
			Common.textBoxInput("id", "customer-email-address",data.get(dataSet).get("Email"));
			}catch (NoSuchElementException e) {
				checkOut();
				Common.textBoxInput("id", "customer-email-address",data.get(dataSet).get("Email"));
			}
			Thread.sleep(3000);
			Common.textBoxInput("name", "firstname", data.get(dataSet).get("FirstName"));
			Common.textBoxInput("name", "lastname", data.get(dataSet).get("LastName"));
			Common.textBoxInput("name", "street[0]", data.get(dataSet).get("Street"));
			Thread.sleep(2000);
			Common.actionsKeyPress(Keys.SPACE);
			Thread.sleep(3000);
			Common.clickElement("xpath", "//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
			if(data.get(dataSet).get("StreetLine2")!=null)
			{
				Common.textBoxInput("name", "street[1]", data.get(dataSet).get("Street"));
			}
			if(data.get(dataSet).get("StreetLine3")!=null)
			{
				Common.textBoxInput("name", "street[2]", data.get(dataSet).get("Street"));
			}
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(3000);
			//Common.mouseOverClick("name", "region_id");
			try {
			Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));}
			catch (ElementClickInterceptedException e) {
				// TODO: handle exception
				Thread.sleep(3000);
				Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			}
			Thread.sleep(2000);
			Common.textBoxInputClear("name", "postcode");
			Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
			Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
			Common.clickElement("xpath", "//button[@data-ac-test='form-shipping-address_action_submit']");
			Thread.sleep(3000);
	}

	public void addPaymentDetails(String dataSet) throws Exception
	{
		//Common.switchFrames("xpath", "//iframe[@id='paymetric_xisecure_frame']");
		//Common.switchFrames("xpath", "//iframe[@id='paymetric_xisecure_frame']");
				Sync.waitElementClickable("xpath", "//label[@for='ime_paymetrictokenize']");
				Common.clickElement("xpath", "//label[@for='ime_paymetrictokenize']");
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
				Thread.sleep(2000);
				Common.clickElement("xpath", "//button[@title='Place Order']");
				
		
		
	}
	
	public void updatePaymentAndSubmitOrder(String dataSet) throws Exception
	{
		addPaymentDetails(dataSet);
		if(Common.findElement("xpath", "//div[@class='message message-error']")!=null)
		{
			addPaymentDetails(dataSet);
		}
		String sucessMessage=Common.getText("xpath", "//h1[@class='checkout-success-title']").trim();
		Assert.assertEquals(sucessMessage, "Your order has been received","Sucess message validations");
	}

	public void clickWarranty()throws Exception{
		Common.actionsKeyPress(Keys.END);
		Sync.waitElementPresent("xpath", "//a[text()='warranty']");
		Common.clickElement("xpath", "//a[text()='warranty']");
	}
	
	public void submitWarranty(String dataSet) throws Exception{
		Common.actionsKeyPress(Keys.END);
		clickWarranty();
		Sync.waitElementPresent("xpath", "//div[@class='wararnty-cta']/a");
		Common.clickElement("xpath", "//div[@class='wararnty-cta']/a");
		
		
		
		Sync.waitElementPresent("id", "email");
		Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
		
		
		Sync.waitElementPresent("id", "pass");
		Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
		
		
		
		
		
		Sync.waitElementPresent("xpath", "//button[@class='login-page-submit-action']");
		Common.clickElement("xpath", "//button[@class='login-page-submit-action']");
		
		
		
		//Submit a Warranty Claim form
		
		Sync.waitElementPresent("xpath", "//iframe[contains(@src,'warranty')]");
		Common.switchFrames("xpath", "//iframe[contains(@src,'warranty')]");
		
		
		Sync.waitElementPresent("xpath", "//input[contains(@id,'First')]");
		Common.textBoxInput("xpath", "//input[contains(@id,'First')]", data.get(dataSet).get("FirstName"));
		
		String s=data.get(dataSet).get("LastName");
		System.out.println(s);
		Sync.waitElementPresent("xpath", "//input[contains(@id,'Last')]");
		Common.textBoxInput("xpath", "//input[contains(@id,'Last')]", data.get(dataSet).get("LastName"));
		
		Sync.waitElementPresent("xpath", "//input[contains(@id,'Emails')]");
		Common.textBoxInput("xpath", "//input[contains(@id,'Emails')]", data.get(dataSet).get("Email"));
		
		Sync.waitElementPresent("xpath", "//input[contains(@id,'Street')]");
		Common.textBoxInput("xpath", "//input[contains(@id,'Street')]", data.get(dataSet).get("Street"));
		
		
		Sync.waitElementPresent("xpath", "//input[contains(@id,'City')]");
		Common.textBoxInput("xpath", "//input[contains(@id,'City')]", data.get(dataSet).get("City"));
		
		Common.clickElement(By.xpath("//select[contains(@id,'Country')]"));

		Sync.waitElementPresent("xpath", "//select[contains(@id,'Country')]");
		Common.dropdown("xpath", "//select[contains(@id,'Country')]", SelectBy.TEXT, data.get(dataSet).get("Country"));
		
		
		Sync.waitElementPresent("xpath", "//select[contains(@id,'StateOrProvince')]");
		Common.clickElement(By.xpath("//select[contains(@id,'StateOrProvince')]"));
		Thread.sleep(5000);
		Common.dropdown("xpath", "//select[contains(@id,'StateOrProvince')]", SelectBy.TEXT,data.get(dataSet).get("State"));
		
		Sync.waitElementPresent("xpath", "//input[contains(@id,'PostalCode')]");
		Common.textBoxInput("xpath", "//input[contains(@id,'PostalCode')]", data.get(dataSet).get("postcode"));
		
		
		Sync.waitElementPresent("xpath", "//input[contains(@id,'MOBILE')]");
		Common.textBoxInput("xpath", "//input[contains(@id,'MOBILE')]", data.get(dataSet).get("phone"));
		
		Sync.waitElementPresent("xpath", "//span[text()='View All']");
		Common.clickElement(By.xpath("//span[text()='View All']"));
		
		Thread.sleep(5000);
	    List<WebElement> Productselemts=	Common.findElements("xpath", "//div[contains(@class,'nameset')]");
		
	       for(int i=0;i<Productselemts.size();i++){
	    	   
	    	    if(Productselemts.get(i).getAttribute("title").equals( data.get(dataSet).get("Products"))){
	    	    	Productselemts.get(i).click();
	    	    	break;
	    	    }
	    	   
	       }
	
	     //input[contains(@class,'product_quantity')]
		
		
		Sync.waitElementPresent("xpath", "//input[contains(@class,'product_quantity')]");
		Common.textBoxInput("xpath", "//input[contains(@class,'product_quantity')]", data.get(dataSet).get("ProductQuantity"));
		
		
		Sync.waitElementPresent("xpath", "//input[contains(@class,'problem_description')]");
		Common.textBoxInput("xpath", "//input[contains(@class,'problem_description')]", data.get(dataSet).get("ProblemDescription"));
		
		Sync.waitElementPresent("xpath", "//input[contains(@id,'FileInput')]");
		String path=System.getProperty("user.dir")+("/src//test//resources//TestData//Hydroflask//hyderoflask.jpg");
		Common.textBoxInput("xpath", "//input[contains(@id,'FileInput')]", path);
		
		Thread.sleep(10000);
		Sync.waitElementPresent("xpath", "//button[contains(@id,'CustomFormSubmit')]");
		Common.clickElement("xpath", "//button[contains(@id,'CustomFormSubmit')]");
		
		}

	
	public void clickContact()throws Exception{
		Common.actionsKeyPress(Keys.END);
		Sync.waitElementPresent("xpath", "//a[text()='Contact']");
		Common.clickElement("xpath", "//a[text()='Contact']");
	}
	
	public void contactUsPage(String dataSet) throws Exception{
		clickContact();
	Common.implicitWait();
		Sync.waitElementPresent("xpath", "//li[@data-tab-name='E-mail Us']");
		Common.clickElement("xpath","//li[@data-tab-name='E-mail Us']");
		
		
		Sync.waitElementPresent("xpath", "//input[contains(@id,'Emails')]");
		Common.textBoxInput("xpath", "//input[contains(@id,'Emails')]", data.get(dataSet).get("Email"));
		
		Sync.waitElementPresent("xpath", "//input[contains(@id,'First')]");
		Common.textBoxInput("xpath", "//input[contains(@id,'First')]", data.get(dataSet).get("FirstName"));
		
		
		Sync.waitElementPresent("xpath", "//input[contains(@id,'Last')]");
		Common.textBoxInput("xpath", "//input[contains(@id,'Last')]", data.get(dataSet).get("LastName"));
		
		Sync.waitElementPresent("xpath", "//input[contains(@id,'company')]");
		Common.textBoxInput("xpath", "//input[contains(@id,'company')]", data.get(dataSet).get("CompanyName"));
		
		Sync.waitElementPresent("xpath", "//input[contains(@id,'MOBILE')]");
		Common.textBoxInput("xpath", "//input[contains(@id,'MOBILE')]", data.get(dataSet).get("Primary "));
		
		Sync.waitElementPresent("xpath", "//input[contains(@id,'Street')]");
		Common.textBoxInput("xpath", "//input[contains(@id,'Street')]", data.get(dataSet).get("Street "));
		
		Sync.waitElementPresent("xpath", "//input[contains(@id,'City')]");
		Common.textBoxInput("xpath", "//input[contains(@id,'City')]", data.get(dataSet).get("City "));
		
		
	}
	
	public void clickProDeal()throws Exception{
		Common.actionsKeyPress(Keys.END);
		Sync.waitElementPresent("xpath", "//a[text()='Pro Deal']");
		Common.clickElement("xpath", "//a[text()='Pro Deal']");
	}
	public void ProdelerPage(String dataSet) throws Exception{
		clickProDeal();
		Sync.waitElementPresent("xpath", "//a[@title='Sign in or register']");
		Common.clickElement("xpath", "//a[@title='Sign in or register']");
		
		
		
	
		
		
		
		Sync.waitElementPresent("id", "email");
		Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
		Sync.waitElementPresent("id", "pass");
		Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
		Sync.waitElementPresent("xpath", "//button[@class='login-page-submit-action']");
		Common.clickElement("xpath", "//button[@class='login-page-submit-action']");
		
		
		
		Sync.waitElementPresent("xpath", "//a[contains(@class,'pro-deal-action-primary')]");
		Common.clickElement("xpath", "//a[contains(@class,'pro-deal-action-primary')]");
		
		Common.switchWindows();
		
		
		
	    
	   
		
		Thread.sleep(10000);
		Sync.waitElementPresent("id", "first_name");
		Common.textBoxInput("id", "first_name", data.get(dataSet).get("FirstName"));
		
	
		Sync.waitElementPresent("id", "last_name");
		Common.textBoxInput("id", "last_name", data.get(dataSet).get("LastName"));
		
		
		Sync.waitElementPresent("id", "association");
		Common.textBoxInput("id", "association", data.get(dataSet).get("Company"));
		
		Sync.waitElementPresent("id", "association_email");
		Common.textBoxInput("id", "association_email", data.get(dataSet).get("AssociationEmailAddress"));
		
		
		 Common.actionsKeyPress(Keys.PAGE_DOWN);
		    Thread.sleep(6000);
		     String path=System.getProperty("user.dir")+("/src//test//resources//TestData//Hydroflask//hyderoflask.jpg");
			//Sync.waitElementInvisible("xpath", "//input[@id='supporting_document']");
			Common.textBoxInput("xpath", "//input[@id='supporting_document']", path);
		
		Sync.waitElementPresent("id", "group_id");
		
		System.out.println(data.get(dataSet).get("GropName"));
		Common.dropdown("xpath", "//select[@id='group_id']", SelectBy.TEXT, data.get(dataSet).get("GropName"));
		
		Sync.waitElementPresent("id", "comment");
		Common.textBoxInput("id", "comment",  data.get(dataSet).get("CommetsHydroflask"));
		
		
		Sync.waitElementPresent("xpath", "//button[@title='Submit']");
		Common.clickElement("xpath", "//button[@title='Submit']");
		
		
	}
	public void stayIntouch(String dataSet) throws Exception{
		Common.actionsKeyPress(Keys.END);
		Thread.sleep(5000);
		
		Sync.waitElementPresent("id", "newsletter");
		Common.clickElement("id", "newsletter");
		Common.textBoxInput("id", "newsletter", data.get(dataSet).get("Email"));
		Thread.sleep(5000);
		Common.actionsKeyPress(Keys.ENTER);
		
		//Common.clickElement("xpath", "//div[contains(@class,'sign-button')]");
		//Thread.sleep(5000);
	   // System.out.println(Common.getText("class", "mage-success"));
		
		
	}
	
	

	public  HydroHelper(String datafile)
	{
		excelData=new ExcelReader(datafile);
		data=excelData.getExcelValue();
		this.data=data;
	}
}
