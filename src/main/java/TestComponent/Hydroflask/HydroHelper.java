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

import com.aventstack.extentreports.TestListener;

import TestLib.Common;
import TestLib.Sync;
import TestLib.Common.SelectBy;
import Utilities.ExcelReader;
import Utilities.ExtenantReportUtils;

public class HydroHelper {

	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data=new HashMap<>();
	 static ExtenantReportUtils report;
	

	public void navigateMyAccount() throws InterruptedException
	{	
		Sync.waitElementClickable(30, By.xpath("//a[@class='social-login']"));
		Common.findElement("xpath", "//a[@class='social-login']").click();
		Thread.sleep(3000);
		report.addPassLog("click the my account ",Common.getscreenShotPathforReport("my account"));
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
		report.addPassLog("Opened login, register pop up",Common.getscreenShotPathforReport("loginPop"));
		String email=Common.genrateRandomEmail(data.get(dataSet).get("Email"));
		Common.clickElement("xpath", "//div[contains(text(),'Sign Up')]");
		report.addPassLog("opens registration pop up",Common.getscreenShotPathforReport("register"));
		Common.textBoxInput("id", "social-login-popup-create-firstname", data.get(dataSet).get("FirstName"));
		Common.textBoxInput("id", "social-login-popup-create-lastname", data.get(dataSet).get("LastName"));
		Common.textBoxInput("id", "social-login-popup-create-email", email);
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Common.textBoxInput("id", "social-login-popup-create-pass", data.get(dataSet).get("Password"));
		Common.textBoxInput("id", "password-confirmation-social", data.get(dataSet).get("Password"));
		Common.clickElement("id", "social-login-popup-create-is-subscribed");
		report.addPassLog("Fields populated with the data",Common.getscreenShotPathforReport("fields"));
		Common.clickElement("xpath", "//button[@title='Sign Up']");
		//Common.actionsKeyPress(Keys.ESCAPE);
		Thread.sleep(2000);
		Sync.waitElementVisible("xpath", "//span[@data-ui-id='page-title-wrapper']");
		Assert.assertEquals(Common.getText("xpath", "//span[@data-ui-id='page-title-wrapper']"), "My Account");
		report.addPassLog("Created an account and logged in the application",Common.getscreenShotPathforReport("Myaccount"));
	}	

	
	public void loginHydroflaskAccount(String dataSet) throws Exception
	{
		Thread.sleep(3000);
		navigateMyAccount();
		report.addPassLog("Opened login, register pop up",Common.getscreenShotPathforReport("register"));
		Sync.waitElementClickable(30, By.id("social-login-popup-log-in-email"));
		if(Common.findElement("id", "social-login-popup-log-in-email")==null)
		{
			Common.clickElement("xpath", "//a[@class='social-login']");
			Thread.sleep(2000);
		}
		Common.textBoxInput("id", "social-login-popup-log-in-email",data.get(dataSet).get("Email"));
		Common.textBoxInput("id", "social-login-popup-log-in-pass",data.get(dataSet).get("Password"));
		report.addPassLog("Fields populated with the data",Common.getscreenShotPathforReport("fields"));
		Common.clickElement("id", "bnt-social-login-authentication");
		Thread.sleep(2000);
		Assert.assertEquals(Common.getText("xpath", "//span[@data-ui-id='page-title-wrapper']"), "My Account");
		report.addPassLog("Logged in the application and customer is redirected to 'My Account' page",Common.getscreenShotPathforReport("Myaccount"));
	}
	
	public void orderSubmit(String category) throws Exception
	{	
		report.addPassLog("Successfully landed on the home page",Common.getscreenShotPathforReport("Successfully landed on the home page"));
		Thread.sleep(5000);
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
		
		report.addPassLog("click the"+category,Common.getscreenShotPathforReport("click the category as shop option as  "+category));
		}catch (Exception e) {
			// TODO: handle exception
			Common.clickElement("xpath", "//ul[@class='megamenu-list']/li[1]/div[1]/button");
			//report.addPassLog("click the option as shop in category",Common.getscreenShotPathforReport("click the category as shop "));		
			}
		report.addPassLog("Clicked shop option in category",Common.getscreenShotPathforReport("clicked shop option in category"));
		Thread.sleep(1000);
		Common.clickElement("xpath", "//a[contains(text(),'"+category+"')]");
		//Sync.waitElementVisible("xpath", "//div[text()='Drink in the adventure.']");
		Thread.sleep(4000);
		report.addPassLog("Selected the "+category+" category	",Common.getscreenShotPathforReport("click the category as shop option as  "+category));
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Thread.sleep(2000);
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Thread.sleep(4000);
		Sync.waitElementClickable("xpath", "//button[@title='Add to Cart']");
		Common.clickElement("xpath", "//button[@title='Add to Cart']");
		Thread.sleep(5000);
		report.addPassLog("Added Product to Cart",Common.getscreenShotPathforReport("Added Product to Cart"));
		}

	public void checkOut() throws Exception
	{
		Common.clickElement("xpath", "//a[@aria-label='minicart']");
		Thread.sleep(2000);
		Common.clickElement("id", "top-cart-btn-checkout");
		Sync.waitElementVisible("className", "checkout-step-title");
		report.addPassLog("Clicked the checkout button",Common.getscreenShotPathforReport("checked out page"));
	}
	public void checkOutCart() throws Exception
	{
		try {
			checkOut();
		}
		catch (Exception e) {
			Common.refreshpage();
			Thread.sleep(6000);
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(2000);
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(4000);
			Sync.waitElementClickable("xpath", "//button[@title='Add to Cart']");
			Common.clickElement("xpath", "//button[@title='Add to Cart']");
			Thread.sleep(5000);
			checkOut();
		}
	}

	public void addDeliveryAddress(String dataSet) throws Exception
	{
		try {	
			Sync.waitElementVisible("id", "customer-email-address");
			Common.textBoxInput("id", "customer-email-address",data.get(dataSet).get("Email"));
			}catch (NoSuchElementException e) {
				checkOut();
				Common.textBoxInput("id", "customer-email-address",data.get(dataSet).get("Email"));
				//report.addPassLog("enter the email address",Common.getscreenShotPathforReport("fill in the email id"));
			}
			Thread.sleep(3000);
			report.addPassLog("Filled Email address",Common.getscreenShotPathforReport("fill in the email id"));
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']", data.get(dataSet).get("FirstName"));
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']", data.get(dataSet).get("LastName"));
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']", data.get(dataSet).get("Street"));
			Thread.sleep(2000);
			Common.actionsKeyPress(Keys.SPACE);
			Thread.sleep(3000);
			try {
			Common.clickElement("xpath", "//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
			}catch(Exception e)
			{
				Common.actionsKeyPress(Keys.BACK_SPACE);
				Thread.sleep(1000);
				Common.actionsKeyPress(Keys.SPACE);
				Common.clickElement("xpath", "//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");		
			}
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
			report.addPassLog("Filled the shipping address for check out page",Common.getscreenShotPathforReport("fill the shipping address"));
            Common.clickElement("xpath", "//button[@data-ac-test='form-shipping-address_action_submit']");
			Thread.sleep(3000);
			report.addPassLog("clicked on the proceed to payment section",Common.getscreenShotPathforReport("land on the payment section"));
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
				report.addPassLog("Filled the Card details ",Common.getscreenShotPathforReport("filling the Card details"));
				Common.actionsKeyPress(Keys.ARROW_DOWN);
				Common.switchToDefault();
				Thread.sleep(1000);
				Common.clickElement("xpath", "//button[@title='Place Order']");
				//report.addPassLog(" navigating to order confirmation page ",Common.getscreenShotPathforReport("place the order "));
				
		
		
	}
	
	public void updatePaymentAndSubmitOrder(String dataSet) throws Exception
	{
		addPaymentDetails(dataSet);

		if(Common.findElements("xpath", "//div[@class='message message-error']").size()>0)
		{	
			addPaymentDetails(dataSet);
		}
			String sucessMessage=Common.getText("xpath", "//h1[@class='checkout-success-title']").trim();
			Assert.assertEquals(sucessMessage, "Your order has been received","Sucess message validations");
			report.addPassLog("Order Placed successfull",Common.getscreenShotPathforReport("order  confromation "));
			
		
	}

	public void clickWarranty()throws Exception{
		Common.actionsKeyPress(Keys.END);
		Sync.waitElementPresent("xpath", "//a[text()='warranty']");
		Common.clickElement("xpath", "//a[text()='warranty']");
		report.addPassLog("Clicking the Warranty button",Common.getscreenShotPathforReport("click the Warranty button "));

		
	}
	
	public void submitWarranty(String dataSet) throws Exception{
		Common.actionsKeyPress(Keys.END);
		clickWarranty();
		Sync.waitElementPresent("xpath", "//div[@class='wararnty-cta']/a");
		Common.clickElement("xpath", "//div[@class='wararnty-cta']/a");
		report.addPassLog("Clicking Submit a Warranty clim button it navigating  to  ",Common.getscreenShotPathforReport("clcik the Submit a Warranty clim button"));
		
		
		Sync.waitElementPresent("id", "email");
		Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
		
		
		Sync.waitElementPresent("id", "pass");
		Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
		
		
		//report.addPassLog("Enter the login Infromation ",Common.getscreenShotPathforReport("Loginto application"));
		
		
		Sync.waitElementPresent("xpath", "//button[@class='login-page-submit-action']");
		Common.clickElement("xpath", "//button[@class='login-page-submit-action']");
		
		report.addPassLog("Enter the login Infromation ",Common.getscreenShotPathforReport("Loginto application"));
		Thread.sleep(6000);
		//Submit a Warranty Claim form
		
		Sync.waitElementPresent("xpath", "//iframe[contains(@src,'warranty')]");
		Common.switchFrames("xpath", "//iframe[contains(@src,'warranty')]");
		
		try {
		Sync.waitElementPresent("xpath", "//input[contains(@name,'Contact.Name.First')]");
		Common.textBoxInput("xpath", "//input[contains(@name,'Contact.Name.First')]", data.get(dataSet).get("FirstName"));
		}catch(Exception e)
		{
			Common.textBoxInput("xpath", "//input[contains(@name,'Contact.Name.First')]", data.get(dataSet).get("FirstName"));
		}
		
		String s=data.get(dataSet).get("LastName");
		System.out.println(s);
		Sync.waitElementPresent("xpath", "//input[@name='Contact.Name.Last']");
		Common.textBoxInput("xpath", "//input[@name='Contact.Name.Last']", data.get(dataSet).get("LastName"));
		
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
		String path=System.getProperty("user.dir")+("\\src\\test\\resources\\TestData\\Hydroflask\\TestScreen.jpg");
		try {
		Common.fileUpLoad("xpath", "//input[contains(@id,'FileInput')]", path);
		}
		catch(Exception e)
		{
			
		}
		
		report.addPassLog("Enter the warrenty from infromation  ",Common.getscreenShotPathforReport("Filling the Warranty from "));
		
		
		Thread.sleep(5000);
		Sync.waitElementPresent("xpath", "//button[contains(@id,'CustomFormSubmit')]");
		Common.clickElement("xpath", "//button[contains(@id,'CustomFormSubmit')]");
		Common.actionsKeyPress(Keys.HOME);
		
		
		report.addPassLog(" redirected to confirmation pages in warranty page ",Common.getscreenShotPathforReport("click submit button "));
		}

	
	public void clickContact()throws Exception{
		Common.actionsKeyPress(Keys.END);
		Sync.waitElementPresent("xpath", "//a[text()='Contact']");
		Common.clickElement("xpath", "//a[text()='Contact']");
		
		report.addPassLog(" navigate to contact page ",Common.getscreenShotPathforReport("click contact Us button "));
	}
	
	public void contactUsPage(String dataSet) throws Exception{
		clickContact();
     	Common.implicitWait();
     	
     	Thread.sleep(9000);
     	
     	
     int size=	Common.findElements("xpath","//li[@data-tab-name='E-mail Us']").size();
    		Sync.waitElementPresent("xpath", "//li[@data-tab-name='E-mail Us']");
    		Sync.waitElementInvisible(60, "xpath", "//li[@data-tab-name='E-mail Us']");
    		Common.clickElement("xpath","//li[@data-tab-name='E-mail Us']");
    		
    		//*[@id="HNNEN6W"]//ul/li[3]
		
//		Common.clickElement("xpath","//li[@data-tab-name='E-mail Us']");
		
		report.addPassLog(" navigate to contact page ",Common.getscreenShotPathforReport("click email Us button "));
		/*//Common.switchFrames("xpath", "//iframe[contains(@src,'custhelp')]");
		1
		
		try{
			Common.switchFrames("xpath", "//iframe[contains(@src,'custhelp')]");
             Sync.waitElementPresent("xpath", "//input[contains(@id,'Emails')]");
			Common.textBoxInput("xpath", "//input[contains(@id,'Emails')]", data.get(dataSet).get("Email"));
		}
			catch(Exception e){
				Common.switchToDefault();
				Common.clickElement("xpath","//li[@data-tab-name='E-mail Us']");
				report.addPassLog(" navigate to contact page ",Common.getscreenShotPathforReport("click email Us button "));
				Common.switchFrames("xpath", "//iframe[contains(@src,'custhelp')]");
			}
		
		
		
		Sync.waitElementPresent("xpath", "//input[contains(@id,'Emails')]");
		Common.textBoxInput("xpath", "//input[contains(@id,'Emails')]", data.get(dataSet).get("Email"));
		//Common.switchFrames("xpath", "//iframe[contains(@src,'custhelp')]");
		
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
		
		report.addPassLog(" filling user infromation in conatct us page ",Common.getscreenShotPathforReport("enter user infromation"));
		
	*/	
	}
	
	public void clickProDeal()throws Exception{
		Common.actionsKeyPress(Keys.END);
		Sync.waitElementPresent("xpath", "//a[text()='Pro Deal']");
		Common.clickElement("xpath", "//a[text()='Pro Deal']");
		report.addPassLog(" navigate prodeal page",Common.getscreenShotPathforReport("click the prodeal button"));
	}
	public void ProdelerPage(String dataSet) throws Exception{
		clickProDeal();
		try {
		Sync.waitElementPresent("xpath", "//a[@title='Sign in or register']");
		report.addPassLog("sogn up page",Common.getscreenShotPathforReport("click the Sign in or register button"));
		}catch(Exception e)
		{
			clickProDeal();
			Thread.sleep(3000);
		}
		Common.clickElement("xpath", "//a[@title='Sign in or register']");
		Sync.waitElementPresent("id", "email");
		Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
		Sync.waitElementPresent("id", "pass");
		Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
		Sync.waitElementPresent("xpath", "//button[@class='login-page-submit-action']");
		Common.clickElement("xpath", "//button[@class='login-page-submit-action']");
		report.addPassLog("login as user",Common.getscreenShotPathforReport("enter the user logins"));
		Sync.waitElementPresent("xpath", "//a[contains(@class,'pro-deal-action-primary')]");
		Common.clickElement("xpath", "//a[contains(@class,'pro-deal-action-primary')]");
		
		report.addPassLog("User is redirected to Pro Deal application page",Common.getscreenShotPathforReport("Apply to Pro Deal button"));
		Thread.sleep(3000);
		Common.switchWindows(false);
		
		Thread.sleep(3000);
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
		     String path=System.getProperty("user.dir")+("\\src\\test\\resources\\TestData\\Hydroflask\\TestScreen.jpg");
			//Sync.waitElementInvisible("xpath", "//input[@id='supporting_document']");
			Common.fileUpLoad("xpath", "//input[@id='supporting_document']", path);
		
		Sync.waitElementPresent("id", "group_id");
		
		System.out.println(data.get(dataSet).get("GropName"));
		Common.dropdown("xpath", "//select[@id='group_id']", SelectBy.TEXT, data.get(dataSet).get("GropName"));
		
		Sync.waitElementPresent("id", "comment");
		Common.textBoxInput("id", "comment",  data.get(dataSet).get("CommetsHydroflask"));
		
		
		Sync.waitElementPresent("xpath", "//button[@title='Submit']");
		Common.clickElement("xpath", "//button[@title='Submit']");
		report.addPassLog("User fill the Pro Deal application",Common.getscreenShotPathforReport("Pro Deal application submition"));
		
	}
	public void stayIntouch(String dataSet) throws Exception{
		Common.actionsKeyPress(Keys.END);
		Thread.sleep(5000);
		
		Sync.waitElementPresent("id", "newsletter");
		Common.clickElement("id", "newsletter");
		
		
		Common.textBoxInput("id", "newsletter", data.get(dataSet).get("Email"));
		Thread.sleep(5000);
		Common.actionsKeyPress(Keys.ENTER);
		report.addPassLog("enter email in  Stay in touch ",Common.getscreenShotPathforReport("submit email address in stay in touch"));
		
		//Common.clickElement("xpath", "//div[contains(@class,'sign-button')]");
		//Thread.sleep(5000);
	   // System.out.println(Common.getText("class", "mage-success"));
		
		
	}
	public void forgetpassword(String dataSet) throws Exception{
		navigateMyAccount();
		Sync.waitElementPresent("xpath", "//a[contains(@class,'forgot-password')]");
		Common.clickElement("xpath","//a[contains(@class,'forgot-password')]");
		report.addPassLog("click the forget password",Common.getscreenShotPathforReport("foget password "));
		
		
		Sync.waitElementPresent("xpath", "//input[contains(@id,'forgot-password-email')]");
		Common.textBoxInput("xpath", "//input[contains(@id,'forgot-password-email')]",data.get(dataSet).get("Email"));
		
		report.addPassLog("enter the email",Common.getscreenShotPathforReport("foget password page enater email"));
		Common.clickElement("xpath", "//button[contains(text(),'Reset my Password')]");
		
		Common.actionsKeyPress(Keys.ESCAPE);
	}
	
    public void Customize_Bottle() throws Exception{
        
		Thread.sleep(8000);

    	Sync.waitElementPresent("xpath","//ul[@class='megamenu-list']/li[2]/div[1]/button");
        Thread.sleep(4000);

    	Common.mouseOverClick("xpath", "//ul[@class='megamenu-list']/li[2]/div[1]/button");
    	report.addPassLog("select the custom option",Common.getscreenShotPathforReport("customize option"));
    	
    	Sync.waitElementPresent("xpath", "//span[contains(text(),'Create Yours Now')]");
        Thread.sleep(4000);
        Common.clickElement("xpath", "//span[contains(text(),'Create Yours Now')]");
    	
    	report.addPassLog("opean the customize order page",Common.getscreenShotPathforReport("customize order page"));
    	
        Thread.sleep(8000);

		Common.actionsKeyPress(Keys.PAGE_DOWN);


		Sync.waitElementPresent("xpath", "//div[text()='Standard Mouth Bottle']/following::a[1]");
        Thread.sleep(5000);

    	Common.mouseOverClick("xpath", "//div[text()='Standard Mouth Bottle']/following::a[1]");
    
    	report.addPassLog("opean the  my hydro configurator page",Common.getscreenShotPathforReport("customize bottle page"));
    	
    	
    	Thread.sleep(6000);
    	Common.actionsKeyPress(Keys.PAGE_DOWN);
    	Sync.waitElementPresent("xpath", "//span[text()='Add To Cart']");
    	Common.clickElement("xpath", "//span[text()='Add To Cart']");
    	
    	report.addPassLog("add the product the cart",Common.getscreenShotPathforReport("click to add to Card"));
    	
    	Sync.waitElementPresent("xpath", "//div[@id='fc-nav-flyout-header-80251']");
    	Common.clickElement("xpath", "//div[@id='fc-nav-flyout-header-80251']");
    	
    	
    	Sync.waitElementPresent("xpath", "//div[contains(@aria-label,'24 oz Our')]");
    	Common.clickElement("xpath", "//div[contains(@aria-label,'24 oz Our')]");
    	
    	
    	Sync.waitElementPresent("xpath", "//div[@id='fc-nav-flyout-header-80262']");
    	Common.clickElement("xpath", "//div[@id='fc-nav-flyout-header-80262']");
    	
    	Sync.waitElementPresent("xpath", "//fieldset[@id='fc-ca-swatch-80262-fieldset']//div[5]/span");
    	Common.clickElement("xpath", "//fieldset[@id='fc-ca-swatch-80262-fieldset']//div[5]/span");
    	
    	
    	Sync.waitElementPresent("xpath", "//div[@id='fc-nav-flyout-header-80263']");
    	Common.clickElement("xpath", "//div[@id='fc-nav-flyout-header-80263']");
    	
    	Sync.waitElementPresent("xpath", "//fieldset[@id='fc-ca-swatch-80263-fieldset']//div[5]/span");
    	Common.clickElement("xpath", "//fieldset[@id='fc-ca-swatch-80263-fieldset']//div[5]/span");
    	
    	
      	Sync.waitElementPresent("xpath", "//div[@id='fc-nav-flyout-header-80268']");
    	Common.clickElement("xpath", "//div[@id='fc-nav-flyout-header-80268']");
    	
    	Sync.waitElementPresent("xpath", "//fieldset[@id='fc-ca-swatch-80268-fieldset']//div[5]/span");
    	Common.clickElement("xpath", "//fieldset[@id='fc-ca-swatch-80268-fieldset']//div[5]/span");
    	
    
    	
      	Sync.waitElementPresent("xpath", "//div[@id='fc-nav-flyout-header-80270']");
    	Common.clickElement("xpath", "//div[@id='fc-nav-flyout-header-80270']");
    	
    	Sync.waitElementPresent("xpath", "//fieldset[@id='fc-ca-swatch-80270-fieldset']//div[5]/span");
    	Common.clickElement("xpath", "//fieldset[@id='fc-ca-swatch-80270-fieldset']//div[5]/span");
    	report.addPassLog("chenage the bottle configuration size and color",Common.getscreenShotPathforReport("changing Configurations"));
    	
    	Sync.waitElementPresent("xpath", "//span[text()='Add To Cart']");
    	Common.clickElement("xpath", "//span[text()='Add To Cart']");
    	report.addPassLog("add the product the cart",Common.getscreenShotPathforReport("click to add to Card"));
    	Thread.sleep(6000);
    	
    	checkOut();
    	
    	/*Sync.waitElementPresent("xpath", "//button[@id='top-cart-btn-checkout']");
    	Common.clickElement("xpath", "//button[@id='top-cart-btn-checkout']");
    	report.addPassLog("navigating  to check out page ",Common.getscreenShotPathforReport("click to check out"));*/
    	}
    
    
    public void shop_bottles() throws Exception{
    	Sync.waitElementPresent("xpath", "//ul[@class='megamenu-list']/li[ 1]/div[1]/button");
    	Common.clickElement("xpath", "//ul[@class='megamenu-list']/li[ 1]/div[1]/button");
    	
    	Sync.waitElementPresent("xpath", "//ul[@class='megamenu-list-ancestor']//a[contains(text(),'Bottles')]");
    	Common.clickElement("xpath","//ul[@class='megamenu-list-ancestor']//a[contains(text(),'Bottles')]");
    	 }
	
	

	public  HydroHelper(String datafile)
	{
		excelData=new ExcelReader(datafile);
		data=excelData.getExcelValue();
		this.data=data;
		if(Utilities.TestListener.report==null)
		{
			report=new ExtenantReportUtils("Hydro");
			report.createTestcase("HydroTestCases");
		}else{
			this.report=Utilities.TestListener.report;
		}
	}
}
