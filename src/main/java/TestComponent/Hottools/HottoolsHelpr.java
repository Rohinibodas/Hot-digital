package TestComponent.Hottools;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.record.PageBreakRecord.Break;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.gargoylesoftware.htmlunit.Page;

import TestLib.Automation_properties;
import TestLib.Common;
import TestLib.Sync;
import TestLib.Common.SelectBy;
import Utilities.ExcelReader;
import Utilities.ExtenantReportUtils;
import Utilities.Utils;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

public class HottoolsHelpr {
	String datafile;
	static Automation_properties automation_properties = Automation_properties.getInstance();
	ExcelReader excelData;
	Map<String, Map<String, String>> data=new HashMap<>();
	static ExtenantReportUtils report;

	public  HottoolsHelpr(String datafile)
	{
		excelData=new ExcelReader(datafile);
		data=excelData.getExcelValue();
		this.data=data;

		if(Utilities.TestListener.report==null)
		{
			report=new ExtenantReportUtils("Hydro");
			report.createTestcase("HottoolsTestCases");
		}else{
			this.report=Utilities.TestListener.report;
		}
	}



	public void ClicktheSignbutton() throws Exception{
		Sync.waitElementPresent("xpath", "//span[@class='authorization-link']");
		Common.clickElement("xpath", "//span[@class='authorization-link']");

	}

	public void closeCookiesbanner() throws Exception{
		Sync.waitElementPresent("id", "truste-consent-close");

		Common.clickElement("id", "truste-consent-close");
	}

	public void agreeCookiesbanner() throws Exception{
		Sync.waitElementPresent("id", "truste-consent-button");

		Common.clickElement("id", "truste-consent-button");
	}



	public void receive_updates_on(String dataSet){
		String updates=data.get(dataSet).get("receiveUpdatesOn");
		String[] updatesarry=updates.split(",");

		for(int i=0;i<updatesarry.length;i++){

			if(updatesarry[i].equals("ExclusiveOffers")){

				Common.clickElement("id", "exclusiveoff");

			}
			else if(updatesarry[i].equals("EventInvitations")){
				Common.clickElement("id", "eventoff");
			}
			else if(updatesarry[i].equals("Promotions")){
				Common.clickElement("id", "promotionoff");
			}
		}


	}

	public void AddressType(String dataSet){
		String adress=data.get(dataSet).get("AddressType");

		if(adress.equals("HomeAddress")){
			Common.clickElement("id", "promotionoff");
		}
		else if(adress.equals("SalonAddress")){
			Common.clickElement("id", "saloonaddress");
		}

	}


	public void clickCreateNewCustomerAccountButton()throws Exception{
		Sync.waitElementPresent("xpath", "//a[contains(text(),'Create')]");
		Common.clickElement("xpath", "//a[contains(text(),'Create')]");

	}

	public void createNewCustomerAccount_Stylist_SalonOwner(String dataSet) throws Exception{
		ClicktheSignbutton();
		clickCreateNewCustomerAccountButton();
		String expectedResult="Stylist saloon user Account Creation";
		try {

			Sync.waitElementPresent("div-toggle");
			Common.dropdown("id", "div-toggle", SelectBy.TEXT, "Stylist / Salon Owner");  
			Thread.sleep(10000);
			Common.textBoxInput("id", "firstname", data.get(dataSet).get("FirstName"));
			Common.textBoxInput("id", "lastname", data.get(dataSet).get("LastName"));
			//Common.textBoxInput("id", "date_field", data.get(dataSet).get("License Expiration Date"));
			Common.clickElement("id", "date_field");
			Thread.sleep(5000);
			if(Common.isElementDisplayed("xpath", "//span[@class='ui-datepicker-month']")) {

				Common.clickElement("xpath", "//*[@id='ui-datepicker-div']/table/tbody/tr[5]/td[2]/a");
			}else {
				Common.clickElement("id", "date_field");
				Thread.sleep(5000);
				Common.clickElement("xpath", "//*[@id='ui-datepicker-div']/table/tbody/tr[5]/td[2]/a");
			}

			Common.textBoxInput("id", "license_number", data.get(dataSet).get("License Number"));

			Common.clickElement("id", "state_ass_license");
			Common.dropdown("id", "state_ass_license", SelectBy.TEXT, data.get(dataSet).get("State Associated"));

			//Common.textBoxInput("id", "upload_license", data.get(dataSet).get("Upload License"));

			Common.fileUpLoad("id", "upload_license", System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\Hottools\\testimage.jpg" );

			Common.dropdown("xpath", "//select[@id='estimated_no_cus']", SelectBy.TEXT, data.get(dataSet).get("Estimated Number"));

			Common.textBoxInput("id", "phone_number", data.get(dataSet).get("Telephone"));

			Common.textBoxInput("id", "doob", data.get(dataSet).get("Date of Birth"));

			Thread.sleep(4000);

			Common.clickElement("xpath", "//input[@name='exclusive']");

			Common.clickElement("id", "saloonaddress");

			Thread.sleep(4000);

			Common.textBoxInput("id", "telephone", data.get(dataSet).get("phone"));

			Common.textBoxInput("id", "street_1", data.get(dataSet).get("Street"));

			Common.textBoxInput("id", "city", data.get(dataSet).get("City"));

			Common.dropdown("id", "region_id", SelectBy.TEXT, data.get(dataSet).get("Region"));

			Common.textBoxInput("id", "zip", data.get(dataSet).get("postcode"));

			Common.textBoxInput("id", "email_address", Utils.getEmailid());
			Common.textBoxInput("id", "password", data.get(dataSet).get("Password"));
			Common.textBoxInput("id", "password-confirmation", data.get(dataSet).get("Password"));

			Thread.sleep(5000);

			Common.clickElement("xpath", "//button[@title='Create an Account']");

			Thread.sleep(10000);

			String Success=	Common.getText("xpath", "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");
			System.out.println(Success+" Account created");
			Assert.assertEquals(Success, "Thank you for registering with Hot Tools Professional.");
			report.addPassLog(expectedResult,"Should Create Stylist Account successfully", "Stylist User account created successfully", Common.getscreenShotPathforReport("Stylist Account Creation success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should Create Stylist Account successfully", "Stylist User not account created", Common.getscreenShotPathforReport("Stylist Account Creation Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}


	public void createNewCustomerAccount_RetailCustomer(String dataSet) throws Exception{

		String expectedResult="Account creation for Retail user";
		try {
			Sync.waitElementPresent("xpath", "//span[@class='authorization-link']");
			Common.clickElement("xpath", "//span[@class='authorization-link']");

			Sync.waitElementPresent("xpath", "//a[contains(text(),'Create')]");
			Common.clickElement("xpath", "//a[contains(text(),'Create')]");

			Sync.waitElementPresent("div-toggle");
			Common.dropdown("id", "div-toggle", SelectBy.TEXT, "Retail Customer");  

			Common.textBoxInput("id", "firstname", data.get(dataSet).get("FirstName"));
			//	Common.textBoxInput("id", "middlename", data.get(dataSet).get("MiddleName"));
			Common.textBoxInput("id", "lastname", data.get(dataSet).get("LastName"));

			Common.textBoxInput("id", "email_address", Utils.getEmailid());
			Common.textBoxInput("id", "password", data.get(dataSet).get("Password"));
			Common.textBoxInput("id", "password-confirmation", data.get(dataSet).get("Password"));

			Thread.sleep(5000);

			Common.clickElement("xpath", "//button[@title='Create an Account']");

			Common.actionsKeyPress(Keys.PAGE_DOWN);

			/*String cap=	Common.findElement(By.xpath("//img[@class='captcha-img']")).getAttribute("value");
			System.out.println(cap+"cap with class");

			String capid=Common.findElement(By.xpath("//div[@id='captcha-container-user_create']")).getAttribute("value");
			System.out.println(capid+"cap with id");*/

			//Sync.waitPageLoad();
			Thread.sleep(15000);

			String Success=	Common.getText("xpath", "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");
			System.out.println(Success+" Account created");

			Assert.assertEquals(Success, "Thank you for registering with Hot Tools Professional.");
			report.addPassLog(expectedResult,"Should Retail Account create successfully", "Retailer User account created successfully", Common.getscreenShotPathforReport("Retailer Account Creation success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should Retail Account create successfully", "Retailer User account not created", Common.getscreenShotPathforReport("Retailer Account Creation Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}




	public void singin(String dataSet) throws Exception{

		String expectedResult="Land on login page";
		try {

			if(Common.isElementDisplayed("id", "truste-consent-button")) {
				agreeCookiesbanner();
			}
			Sync.waitElementPresent("xpath", "//span[@class='authorization-link']");
			Common.clickElement("xpath", "//span[@class='authorization-link']");

			Sync.waitElementPresent("xpath", "//a[text()='sign in']");
			Common.clickElement("xpath", "//a[text()='sign in']");
			int i=0;
			do{ 
				Common.refreshpage();

				Sync.waitElementPresent("id", "email");

				Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
				Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));

				Sync.waitElementPresent("xapth","//fieldset[@class='fieldset login']//div[3]");
				Thread.sleep(4000);
				Common.scrollIntoView("id", "send2");
				Sync.waitElementPresent("id", "send2");
				Common.clickElement(By.id("send2"));

				Thread.sleep(4000);
				i++;
			}while(i<5 && !Common.isElementDisplayed("xpath", "//span[@class='logged-in']")); 

			report.addPassLog(expectedResult,"Should Login to application successfully", "Login to application successfully", Common.getscreenShotPathforReport("Login success"));
		}catch(Exception |Error e)
		{
			report.addPassLog(expectedResult,"Should Login to application successfully", "Login to application Failed", Common.getscreenShotPathforReport("Login failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}


	public void distributorsignin(String dataSet) throws Exception{

		String expectedResult="Validating Distributor login page with valid Credentials";
		try {
			if(Common.isElementDisplayed("id", "truste-consent-button")) {
				agreeCookiesbanner();
			}
			Sync.waitElementPresent("xpath", "//span[@class='authorization-link']");
			Common.clickElement("xpath", "//span[@class='authorization-link']");

			Sync.waitElementPresent("xpath", "//a[text()='sign in']");
			Common.clickElement("xpath", "//a[text()='sign in as distributor']");

			int i=0;
			do{ 
				Common.refreshpage();

				Sync.waitElementPresent("id", "email");

				Common.textBoxInput("id", "email", data.get(dataSet).get("UserName"));
				Common.textBoxInput("id", "password", data.get(dataSet).get("Password"));

				Sync.waitElementPresent("xapth","//fieldset[@class='fieldset login']//div[3]");
				Thread.sleep(4000);

				report.addPassLog("log in with user name and password ",Common.getscreenShotPathforReport("Sign IN with user email and password"));
				Sync.waitElementPresent("id", "send2");
				Common.clickElement(By.id("send2"));

				Thread.sleep(4000);
				i++;
			}while(i<5 && !Common.isElementDisplayed("xpath", "//span[contains(text(),'My Account')]")); 

			report.addPassLog(expectedResult,"Should Login to application successfully", "Login to application successfully", Common.getscreenShotPathforReport("Login success"));
		}catch(Exception |Error e)
		{
			report.addPassLog(expectedResult,"Should Login to application successfully", "Login to application Failed", Common.getscreenShotPathforReport("Login failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	public void searchingProducts(String dataSet) throws Exception{

		String expectedResult="Search with Product name :"+data.get(dataSet).get("ProductName");
		try {
			if(Common.isElementDisplayed("id", "truste-consent-button")) {
				agreeCookiesbanner();
			}else {
				System.out.println("Cookies popup not displayed");
			}

			Thread.sleep(6000);
			Sync.waitElementPresent("xpath", "//button[@title='Back To Top']");

			Sync.waitElementPresent("xpath", "//a[@href='#search-mod']");
			Common.clickElement("xpath", "//a[@href='#search-mod']");

			Thread.sleep(3000);

			if(Common.isElementDisplayed("id", "search")) {

				Sync.waitElementPresent("id", "search");
				Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName"));

			}else {

				Common.refreshpage();

				Thread.sleep(3000);

				Sync.waitElementPresent("xpath", "//a[@href='#search-mod']");
				Common.clickElement("xpath", "//a[@href='#search-mod']");

				Sync.waitElementPresent("id", "search");
				Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName"));
			}

			Sync.waitElementClickable("xpath", "//button[@title='Search']");
			Common.clickElement(By.xpath("//button[@title='Search']"));

			report.addPassLog(expectedResult,"Should search product successfully", "Searched product successfully", Common.getscreenShotPathforReport("Search product success"));
		}catch(Exception |Error e)
		{
			report.addPassLog(expectedResult,"Should search product successfully", "Searched product successfully", Common.getscreenShotPathforReport("Search product failed"));
			e.printStackTrace();
			Assert.fail();
		}


	}


	public void AddToCartProduct(String dataSet) throws Exception{

		Common.actionsKeyPress(Keys.PAGE_DOWN);
		//	Sync.waitElementPresent("xpath", "//a[@title='"+data.get(dataSet).get("ProductName")+"']");

		Thread.sleep(6000);
		Common.clickElement("xpath", "//a[@title='"+data.get(dataSet).get("ProductName")+"']");
		report.addPassLog("select the searched product ",Common.getscreenShotPathforReport("produact selection"));

		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Thread.sleep(6000);
		Sync.waitElementPresent("id", "product-addtocart-button");
		Common.clickElement(By.id("product-addtocart-button"));

		report.addPassLog("add producat to Cart ",Common.getscreenShotPathforReport("produact to cart"));
		Thread.sleep(7000);

		int size= Common.findElements("id", "top-cart-btn-checkout").size();
		if(size<0){
			Common.clickElement(By.id("product-addtocart-button"));
			report.addPassLog("add producat to Cart ",Common.getscreenShotPathforReport("produact to cart"));
			Sync.waitElementPresent("id", "top-cart-btn-checkout");
			Common.clickElement(By.id("top-cart-btn-checkout"));
			report.addPassLog(" producat  Cart to check out",Common.getscreenShotPathforReport("click the check out button"));
		}
		else{

			Sync.waitElementPresent("id", "top-cart-btn-checkout");
			Common.clickElement(By.id("top-cart-btn-checkout"));
			report.addPassLog(" producat  Cart to check out",Common.getscreenShotPathforReport("click the check out button"));
		}


		//a[@title='Mini Travel Dryer in Mint']//following::button[1]
	}


	public void sp(String dataSet)throws Exception{
		Thread.sleep(7000);
		Common.implicitWait();
		List<WebElement> shippinglabels=	Common.findElements("xpath", "//div[contains(@name,'shippingAddress')]//input[1]");

		for(int i=0;i<shippinglabels.size();i++){

			String attribute=shippinglabels.get(i).getAttribute("name");
			switch (attribute) {

			case "firstname":
				shippinglabels.get(i).sendKeys(data.get(dataSet).get("FirstName"));
				break;
			case "lastname":
				shippinglabels.get(i).sendKeys(data.get(dataSet).get("LastName"));
				break;
			case "street[0]":
				shippinglabels.get(i).sendKeys(data.get(dataSet).get("Street"));
				break;
			case "city":
				shippinglabels.get(i).sendKeys(data.get(dataSet).get("City"));
				break;


			}

		}
		Sync.waitElementPresent("xpath", "//select[@name='region_id']");
		Common.dropdown("xpath", "//select[@name='region_id']", SelectBy.TEXT, data.get(dataSet).get("Region"));

		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Sync.waitElementPresent("name", "postcode");
		Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
		Common.dropdown("xpath", "//select[@name='country_id']", SelectBy.TEXT, data.get(dataSet).get("Country"));


		Common.textBoxInput("name", "company", data.get(dataSet).get("Company"));

		Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));

	}

	public void shippingAddress(String dataSet) throws Exception{
		Thread.sleep(9000);

		int sizes=Common.findElements(By.xpath("//div[@id='checkout-step-shipping']/div")).size();     



		if(sizes>2){

			Common.doubleClick("xpath", "//div[@class='shipping-address-item selected-item']");

			report.addPassLog("selecting the producat shipping address",Common.getscreenShotPathforReport("select the shipping address"));
			Thread.sleep(9000);
		}
		else{

			Sync.waitElementPresent("name", "firstname");
			Common.textBoxInput("name", "firstname", data.get(dataSet).get("FirstName"));
			Thread.sleep(7000);
			Sync.waitElementPresent("name", "lastname");
			Common.textBoxInput("name", "lastname", data.get(dataSet).get("LastName"));
			Thread.sleep(8000);
			Sync.waitElementPresent("name", "street[0]");
			Common.textBoxInput("name", "street[0]", data.get(dataSet).get("Street"));
			Thread.sleep(7000);
			Sync.waitElementPresent("name", "city");
			Common.textBoxInput("name", "city", data.get(dataSet).get("City"));
			Sync.waitElementPresent("xpath", "//select[@name='region_id']");
			Thread.sleep(7000);
			Common.dropdown("xpath", "//select[@name='region_id']", SelectBy.TEXT, data.get(dataSet).get("Region"));

			Common.actionsKeyPress(Keys.ARROW_DOWN);
			Sync.waitElementPresent("name", "postcode");
			Thread.sleep(7000);
			Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
			Thread.sleep(7000);
			Common.dropdown("xpath", "//select[@name='country_id']", SelectBy.TEXT, data.get(dataSet).get("Country"));

			Thread.sleep(7000);
			Common.textBoxInput("name", "company", data.get(dataSet).get("Company"));
			Thread.sleep(5000);
			Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));

			report.addPassLog("Enter the shipping  address",Common.getscreenShotPathforReport("enter the  shipping address"));
			Thread.sleep(5000);
			Common.clickElement("xpath", "//input[@id='billing-address-same-as-shipping-']");


			//fieldset[@id="billing-new-address-form"]//input[@name='lastname']

			Sync.waitElementPresent("xpath", "//fieldset[@id='billing-new-address-form']//input[@name='firstname']");
			Common.textBoxInput("xpath", "//fieldset[@id='billing-new-address-form']//input[@name='firstname']", data.get(dataSet).get("FirstName"));
			Thread.sleep(7000);
			Sync.waitElementPresent("xpath", "//fieldset[@id='billing-new-address-form']//input[@name='lastname']");
			Common.textBoxInput("xpath", "//fieldset[@id='billing-new-address-form']//input[@name='lastname']", data.get(dataSet).get("LastName"));
			Thread.sleep(8000);
			Sync.waitElementPresent("xpath", "//fieldset[@id='billing-new-address-form']//input[@name='street[0]']");
			Common.textBoxInput("xpath", "//fieldset[@id='billing-new-address-form']//input[@name='street[0]']", data.get(dataSet).get("Street"));
			Thread.sleep(7000);
			Sync.waitElementPresent("xpath", "//fieldset[@id='billing-new-address-form']//input[@name='city']");
			Common.textBoxInput("xpath", "//fieldset[@id='billing-new-address-form']//input[@name='city']", data.get(dataSet).get("City"));
			//Sync.waitElementPresent("xpath", "//select[@name='region_id']")

			Sync.waitElementPresent("xpath", "//fieldset[@id='billing-new-address-form']//select[@name='region_id']");
			Thread.sleep(7000);
			Common.dropdown("xpath", "//fieldset[@id='billing-new-address-form']//select[@name='region_id']", SelectBy.TEXT, data.get(dataSet).get("Region"));


			Common.actionsKeyPress(Keys.ARROW_DOWN);
			Sync.waitElementPresent("xpath", "//fieldset[@id='billing-new-address-form']//input[@name='postcode']");
			Thread.sleep(7000);
			Common.textBoxInput("xpath", "//fieldset[@id='billing-new-address-form']//input[@name='postcode']", data.get(dataSet).get("postcode"));



			Thread.sleep(5000);
			Common.dropdown("xpath", "//fieldset[@id='billing-new-address-form']//select[@name='country_id']", SelectBy.TEXT, data.get(dataSet).get("Country"));

			Thread.sleep(7000);
			Common.textBoxInput("xpath", "//fieldset[@id='billing-new-address-form']//input[@name='company']", data.get(dataSet).get("Company"));
			Thread.sleep(5000);
			Common.textBoxInput("xpath", "//fieldset[@id='billing-new-address-form']//input[@name='telephone']", data.get(dataSet).get("phone"));
			Thread.sleep(5000);
			report.addPassLog("Enter shipping address",Common.getscreenShotPathforReport(" shipping address"));
			Common.actionsKeyPress(Keys.PAGE_UP);


		}

	}

	public void addCardDetiles(String dataSet) throws Exception{
		Thread.sleep(9000);
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
	public void LogOut() throws Exception{

		Thread.sleep(8000);

		Common.clickElement(By.xpath("//div[@class='panel header']//ul[3]/li[2]"));


		Thread.sleep(3000);
		Common.clickElement(By.xpath("//div[@class='panel header']//ul[3]/li[2]/div/ul/li[3]"));
		report.addPassLog("log out the application ",Common.getscreenShotPathforReport(" log out the application"));
	}

	/*harish chiruvella*/

	public void signoutarrow() throws Exception
	{
		Sync.waitElementPresent("xpath", "//span[@class='customer-name']");
		Common.clickElement("xpath", "//span[@class='customer-name']");

	}

	public void myAccount() throws Exception
	{
		automation_properties = Automation_properties.getInstance();
		Sync.waitElementPresent("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"pro/index.php/customer/account/']"));
		Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"pro/index.php/customer/account/']"));

	}

	public void navigateMyAccount(String dataSet) throws Exception
	{
		Sync.waitElementPresent("xpath", "//span[@class='customer-name']");
		Common.clickElement("xpath", "//span[@class='customer-name']");

		Sync.waitElementPresent("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"customer/account/']"));
		Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"customer/account/']"));

		Sync.waitElementPresent("xpath", "//div[@class='box-content']");
		String s=Common.getText("xpath", "//div[@class='box-content']");
		System.out.println(s);
		//assertEquals(s, data.get(dataSet).get("FirstName")+ data.get(dataSet).get("LastName")+  data.get(dataSet).get("Email"));
	}

	public void navigateDistributorMyAccount(String dataSet) throws Exception
	{
		Sync.waitElementPresent("xpath", "//span[@class='customer-name']");
		Common.clickElement("xpath", "//span[@class='customer-name']");

		Sync.waitElementPresent("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"pro/index.php/customer/account/']"));
		Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"pro/index.php/customer/account/']"));

		Sync.waitElementPresent("xpath", "//div[@class='box-content']");
		String s=Common.getText("xpath", "//div[@class='box-content']");
		System.out.println(s);
		//assertEquals(s, data.get(dataSet).get("FirstName")+ data.get(dataSet).get("LastName")+  data.get(dataSet).get("Email"));
	}

	public void navigateMyOrder() throws Exception
	{
		signoutarrow();
		myAccount();

		Sync.waitElementPresent("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"sales/order/history/']"));
		Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"sales/order/history/']"));

		Thread.sleep(2000);

		String s=Common.getText("xpath", "//p[@class='toolbar-amount']");
		System.out.println(s);	
	}

	public void navigateMyWishList() throws Exception
	{
		signoutarrow();
		myAccount();

		Sync.waitElementPresent("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"wishlist/']"));
		Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"wishlist/']"));

	}

	public void navigateAddressBook() throws Exception
	{
		signoutarrow();
		myAccount();

		Sync.waitElementPresent("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"customer/address/']"));
		Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"customer/address/']"));

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


	}

	public void navigateDistributorAddressBook() throws Exception
	{
		signoutarrow();
		myAccount();

		Sync.waitElementPresent("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"pro/index.php/customer/address/']"));
		Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"pro/index.php/customer/address/']"));

		Thread.sleep(2000);

		String s=Common.getText("xpath", "//div[@class='box-content']");
		System.out.println("Default Address is : "+s);

		Thread.sleep(3000);

		String s1=Common.getText("xpath", "//p[@class='toolbar-amount']");
		System.out.println("Additional Address entries : "+s1);	
	}

	public void addNewAddress(String dataSet) throws Exception
	{

		Common.textBoxInput("name", "firstname", data.get(dataSet).get("FirstName"));
		Common.textBoxInput("name", "lastname", data.get(dataSet).get("LastName"));
		Common.textBoxInput("name", "company", data.get(dataSet).get("Company"));
		Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
		Common.textBoxInput("name", "street[]", data.get(dataSet).get("Street"));
		Common.textBoxInput("name", "city", data.get(dataSet).get("City"));

		Thread.sleep(500);

		Sync.waitElementPresent("xpath", "//select[@title='State/Province']");
		Common.scrollToElementAndClick("xpath", "//select[@title='State/Province']");

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

	public void navigateAccountInformation() throws Exception
	{
		signoutarrow();
		myAccount();

		Sync.waitElementPresent("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"customer/account/edit/']"));
		Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"customer/account/edit/']"));

	}


	public void distributornavigateAccountInformation() throws Exception
	{
		signoutarrow();
		myAccount();

		Sync.waitElementPresent("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"pro/index.php/customer/account/edit/']"));
		Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"pro/index.php/customer/account/edit/']"));

	}

	public void changeAIName(String dataSet) throws Exception
	{

		Common.textBoxInput("name", "firstname", data.get(dataSet).get("FirstName"));
		Common.textBoxInput("name", "lastname", data.get(dataSet).get("LastName"));

		Thread.sleep(500);

		Sync.waitElementPresent("xpath", "//button[@title='Save']");
		Common.clickElement("xpath", "//button[@title='Save']");

	}

	public void changeAIEmail(String dataSet) throws Exception
	{

		Sync.waitElementPresent("name", "change_email");	
		Common.clickElement("name", "change_email");
		Thread.sleep(500);

		Common.textBoxInput("name", "email", data.get(dataSet).get("Email"));
		Common.textBoxInput("name", "current_password", data.get(dataSet).get("Password"));

		Thread.sleep(500);

		Sync.waitElementPresent("xpath", "//button[@title='Save']");
		Common.clickElement("xpath", "//button[@title='Save']");

	}

	public void changeAIPassword(String dataSet) throws Exception
	{

		Sync.waitElementPresent("name", "change_password");	
		Common.clickElement("name", "change_password");
		Thread.sleep(500);

		Common.textBoxInput("xpath", "//input[@data-input='current-password']", data.get(dataSet).get("Password"));
		Common.textBoxInput("xpath", "//input[@data-input='new-password']", data.get(dataSet).get("Password"));
		Common.textBoxInput("name", "password_confirmation", data.get(dataSet).get("Password"));


		Thread.sleep(500);

		Sync.waitElementPresent("xpath", "//button[@title='Save']");
		Common.clickElement("xpath", "//button[@title='Save']");

		String s=Common.getText("xpath", "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");
		System.out.println(s);

	}

	public void navigateProductReview(String dataSet) throws Exception
	{
		signoutarrow();
		myAccount();

		Sync.waitElementPresent("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"review/customer/']"));
		Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"review/customer/']"));

		int i=Common.getElementCount("xpath", "//*[@id='my-reviews-table']/tbody");
		System.out.println(i);
		for(int j=i;j<=i;j++) {
			String s=Common.getText("xpath", "//*[@id='my-reviews-table']/tbody/tr["+j+"]/td[4]");
			System.out.println(s);
			if(s.equals(data.get(dataSet).get("review"))) {
				System.out.println("Product Review updated sucessfully");
			}

		}

	}


	public void Productselection(String dataSet) throws Exception
	{
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		//	Sync.waitElementPresent("xpath", "//a[@title='"+data.get(dataSet).get("ProductName")+"']");

		Thread.sleep(3000);
		Sync.waitElementPresent("xpath", "//a[@title='"+data.get(dataSet).get("ProductName")+"']");
		Common.clickElement("xpath", "//a[@title='"+data.get(dataSet).get("ProductName")+"']");

		Common.actionsKeyPress(Keys.ARROW_DOWN);

		Thread.sleep(3000);

		Sync.waitElementPresent("xpath", "//a[@href='#reviews']");
		Common.clickElement("xpath", "//a[@href='#reviews']");
		Thread.sleep(3000);

	}

	public void ProductReview(String dataSet) throws Exception
	{


		if(Common.isElementDisplayed("xpath", "//label[@id='Quality_5_label']")) {

			System.out.println("Review page is displayed");

			Sync.waitElementPresent("xpath", "//label[@id='Quality_5_label']");
			Common.clickElement("xpath", "//label[@id='Quality_5_label']");
		}else {

			System.out.println("Review page is not displayed");

			Sync.waitElementPresent("xpath", "//a[@href='#reviews']");
			Common.clickElement("xpath", "//a[@href='#reviews']");

			Sync.waitElementPresent("xpath", "//label[@id='Quality_5_label']");
			Common.clickElement("xpath", "//label[@id='Quality_5_label']");
		}

		Sync.waitElementPresent("xpath", "//label[@id='Price_5_label']");
		Common.clickElement("xpath", "//label[@id='Price_5_label']");

		Sync.waitElementPresent("xpath", "//label[@id='Value_5_label']");
		Common.clickElement("xpath", "//label[@id='Value_5_label']");

		Common.textBoxInput("name", "nickname", data.get(dataSet).get("name"));

		Common.textBoxInput("name", "title", data.get(dataSet).get("summary"));

		Common.textBoxInput("name", "detail", data.get(dataSet).get("review"));

		Sync.waitElementPresent("xpath", "//span[contains(text(),'Submit Review')]");
		Common.clickElement("xpath", "//span[contains(text(),'Submit Review')]");

	}

	public void navigateProductReviewDetails(String dataSet) throws Exception
	{
		signoutarrow();
		myAccount();

		Sync.waitElementPresent("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"review/customer/']"));
		Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"review/customer/']"));

		int i=Common.getElementCount("xpath", "//*[@id='my-reviews-table']/tbody");
		System.out.println(i);
		for(int j=i;j<=i;j++) {
			String s=Common.getText("xpath", "//*[@id='my-reviews-table']/tbody/tr["+j+"]/td[2]");
			System.out.println(s);
			if(s.equals(data.get(dataSet).get("ProductName"))) {

				Sync.waitElementPresent("xpath", "//*[@id='my-reviews-table']/tbody/tr["+j+"]/td[5]");
				Common.clickElement("xpath", "//*[@id='my-reviews-table']/tbody/tr["+j+"]/td[5]");

				String t=Common.getText("xpath", "//div[@class='review-title']");
				System.out.println(s);
				Assert.assertEquals(t, data.get(dataSet).get("ProductName"));
				System.out.println("Test case passed successfully");
			}

		}

	}

	public void navigateNewsLetterSubscription() throws Exception
	{
		signoutarrow();
		myAccount();

		Sync.waitElementPresent("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"newsletter/manage/']"));
		Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"newsletter/manage/']"));

		Thread.sleep(300);

		Sync.waitElementPresent("xpath", "//input[@name='is_subscribed']");
		Common.clickElement("xpath", "//input[@name='is_subscribed']");

		Sync.waitElementPresent("xpath", "//button[@title='Save']");
		Common.clickElement("xpath", "//button[@title='Save']");

		Thread.sleep(300);

		String s=Common.getText("xpath", "//div[@class='box box-newsletter']//div[@class='box-content']");
		System.out.println(s);


	}

	public void searchresults(String dataSet) throws Exception
	{

		Thread.sleep(300);

		String s=Common.getText("xpath", "//span[@data-ui-id='page-title-wrapper']");
		System.out.println(s);

		if(s.contains(data.get(dataSet).get("ProductName"))) {
			System.out.println("Test cases passed Successfully");
		}else {
			System.out.println("Test cases Failed");
		}

	}

	public void homePage(String dataSet) throws Exception
	{

		String s=Common.getText("xpath", "//h2[contains(text(),'Featured Products')]");
		System.out.println(s);

		if(s.contains(data.get(dataSet).get("HomePage"))) {
			System.out.println("Test case passed successfully");
		}else {
			System.out.println("Test case failed");
		}

	}

	public void forgotPassword(String dataSet) throws Exception
	{
		String expectedResult="Forgot Password for Registered Retailer/Stylist User";
		try {
			if(Common.isElementDisplayed("id", "truste-consent-button"))
			{
				agreeCookiesbanner();
			}

			Sync.waitElementPresent("xpath", "//span[@class='authorization-link']");
			Common.clickElement("xpath", "//span[@class='authorization-link']");

			Sync.waitElementPresent("xpath", "//a[text()='sign in']");
			Common.clickElement("xpath", "//a[text()='sign in']");

			Sync.waitElementPresent("id", "email");

			Sync.waitElementPresent("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"customer/account/forgotpassword/']"));
			Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"customer/account/forgotpassword/']"));

			Common.textBoxInput("name", "email", data.get(dataSet).get("Email"));

			Sync.waitElementPresent("xpath", "//button[@title='Reset My Password']");
			Common.clickElement("xpath", "//button[@title='Reset My Password']");
			Thread.sleep(7000);

			if(Common.isElementDisplayedonPage(10, "xpath", "//div[contains(text(),'Invalid Form Key. Please refresh the page.')]")){

				Common.refreshpage();

				Common.textBoxInput("name", "email", data.get(dataSet).get("Email"));

				Sync.waitElementPresent("xpath", "//button[@title='Reset My Password']");
				Common.clickElement("xpath", "//button[@title='Reset My Password']");
				Thread.sleep(7000);

			}else {
				System.out.println("Forgot Password Link send to registered email");
			}

			if(Common.isElementDisplayed("xpath", "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")) {
				Sync.waitElementPresent("xpath", "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");
				Thread.sleep(5000);
				String s=Common.getText("xpath", "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");
				System.out.println(s);
				Thread.sleep(5000);
				Assert.assertEquals(s, "If there is an account associated with "+data.get(dataSet).get("Email")+" you will receive an email with a link to reset your password.");

			}else {
				System.out.println("Forgot alert not displayed");
			}

			report.addPassLog(expectedResult,"Should display Forgot password success message", "Forgot password success message display successfully", Common.getscreenShotPathforReport("forgot password success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Forgot password success message", "Forgot password success message display successfully", Common.getscreenShotPathforReport("forgot password Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}


	public void minicartProduct(String dataSet) throws Exception{

		String expectedResult="Product adding to mini cart";
		try {
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Sync.waitElementPresent("xpath", "//a[@title='"+data.get(dataSet).get("ProductName")+"']");

			Thread.sleep(5000);
			Common.clickElement("xpath", "//a[@title='"+data.get(dataSet).get("ProductName")+"']");

			//Common.actionsKeyPress(Keys.ARROW_DOWN);
			Thread.sleep(7000);
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Sync.waitElementPresent("id", "product-addtocart-button");
			Common.clickElement("id", "product-addtocart-button");
			//Common.clickElement(By.id("product-addtocart-button"));
			Thread.sleep(7000);
			if(Common.isElementDisplayedonPage(10, "xpath", "//div[contains(text(),'Invalid Form Key. Please refresh the page.')]")){

				Common.refreshpage();

				Sync.waitElementPresent("id", "product-addtocart-button");
				Common.clickElement(By.id("product-addtocart-button"));

				Thread.sleep(4000);

				Sync.waitElementPresent("xpath", "//div[@data-bind='html: message.text']//a[contains(text(),'shopping cart')]");
				Common.clickElement("xpath", "//div[@data-bind='html: message.text']//a[contains(text(),'shopping cart')]");
			}else {

				System.out.println("You added "+data.get(dataSet).get("ProductName")+" to your shopping cart.");
				/*Sync.waitElementPresent("xpath", "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']//a[contains(text(),'shopping cart')]");
				Common.clickElement("xpath", "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']//a[contains(text(),'shopping cart')]");*/
			}

			if(Common.isElementDisplayed("xpath", "//div[@class='actions action-viewcart bottom-aligned']/div//a[@href='https://stage.hottools.com/checkout/cart/']")) {

				Sync.waitElementPresent("xpath", "//div[@class='actions action-viewcart bottom-aligned']/div//a[@href='https://stage.hottools.com/checkout/cart/']");
				Common.clickElement("xpath", "//div[@class='actions action-viewcart bottom-aligned']/div//a[@href='https://stage.hottools.com/checkout/cart/']");
				System.out.println("Mini cark in Popup clicked");
			}else {

				Sync.waitElementPresent("xpath", "//a[@title='Cart']");
				Common.clickElement("xpath", "//a[@title='Cart']");
				System.out.println("Mini cark in Header clicked");

				Thread.sleep(1000);

				Sync.waitElementPresent("xpath", "//div[@class='actions action-viewcart bottom-aligned']/div//a[@class='action viewcart']");
				Common.clickElement("xpath", "//div[@class='actions action-viewcart bottom-aligned']/div//a[@class='action viewcart']");

			}
			report.addPassLog(expectedResult,"Should Show MiniCart successfully", "Mini cart showed successfully", Common.getscreenShotPathforReport("Mini cart success"));
		}catch(Exception |Error e)
		{
			report.addPassLog(expectedResult,"Should Show MiniCart successfully", "Mini cart showed successfully", Common.getscreenShotPathforReport("Mini cart failed"));
			e.printStackTrace();
			Assert.fail();
		}


	}


	public void miniCart(String dataSet) throws Exception
	{
		String expectedResult="Product adding to Cart page";
		try {
			Sync.waitElementPresent("xpath", "//strong[@class='product-item-name']");
			Thread.sleep(5000);
			String s=Common.getText("xpath", "//strong[@class='product-item-name']");
			System.out.println(s);
			Assert.assertEquals(s, data.get(dataSet).get("ProductName"));
			System.out.println("Mini cart Test cases passed successfully");
			Thread.sleep(5000);
			report.addPassLog(expectedResult,"Should Mini cart page successfully", "Mini cart display successfully", Common.getscreenShotPathforReport("cart success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should Mini cart page successfully", "Mini cart display successfully", Common.getscreenShotPathforReport("cart Failed"));
			e.printStackTrace();
			Assert.fail();
		}


	}

	public void DistributorminiCart(String dataSet) throws Exception
	{
		String expectedResult="Product adding to Cart page";
		try {
			Sync.waitElementPresent("xpath", "//*[@id='shopping-cart-table']/tbody/tr/td[2]/a");
			Thread.sleep(5000);
			String s=Common.getText("xpath", "//*[@id='shopping-cart-table']/tbody/tr/td[2]/a");
			System.out.println(s);
			//Assert.assertEquals(s, data.get(dataSet).get("ProductName"));
			System.out.println("Mini cart Test cases passed successfully");
			Thread.sleep(5000);
			report.addPassLog(expectedResult,"Should Mini cart page successfully", "Mini cart display successfully", Common.getscreenShotPathforReport("cart success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should Mini cart page successfully", "Mini cart display successfully", Common.getscreenShotPathforReport("cart Failed"));
			e.printStackTrace();
			Assert.fail();
		}


	}

	public void checkoutpage(String dataSet) throws Exception
	{
		String expectedResult="Proceeding to checkout page";
		try {


			Thread.sleep(2000);
			Sync.waitElementPresent("xpath", "//button[@title='Proceed to Checkout']");
			Common.clickElement("xpath", "//button[@title='Proceed to Checkout']");
			//Common.clickElementStale("xpath", "//button[@title='Proceed to Checkout']");
			//Common.clickElement("xpath", "//button[@class='action primary checkout']");

			if(Common.isElementDisplayed("xpath", "//*[@id='checkout-step-shipping']/div[1]/div/div/div[1]//span[contains(text(),'Ship here:')]")) {
				System.out.println("Shipping address is not selected");
				Common.clickElement("xpath", "//*[@id='checkout-step-shipping']/div[1]/div/div/div[1]//span[contains(text(),'Ship here:')]");
			}else {
				System.out.println("Shipping address is selected");
				Thread.sleep(5000);
				Sync.waitElementPresent("xpath", "//div[@class='items payment-methods']/div/div[4]/div//input[@name='payment[method]']");
				Common.clickElement("xpath", "//div[@class='items payment-methods']/div/div[4]/div//input[@name='payment[method]']");		
			}
			Thread.sleep(2000);
			if(Common.checkBoxIsSelected("xpath", "//td[@class='col col-method']")) {

				System.out.println("Shipping method is selected");

			}else {

				Common.clickElement("xpath", "//td[@class='col col-method']");
			}

			//Common.clickElement("xpath", "//td[@class='col col-method']");

			Thread.sleep(8000);

			if(Common.isElementDisplayed("xpath", "//div[@id='checkout-loader']")) {
				Thread.sleep(8000);
			}else {
				Thread.sleep(6000);
				Sync.waitElementPresent("id", "ime_paymetrictokenize");
				Common.clickElement(By.id("ime_paymetrictokenize"));
				Thread.sleep(8000);
			}	

			//Common.textBoxInput("xpath", "//input[@title='Purchase Order Number']", data.get(dataSet).get("PurchaseOrder"));
			Common.switchFrames("paymetric_xisecure_frame");
			Sync.waitElementPresent("xpath", "//select[@id='c-ct']");
			Common.dropdown("xpath", "//select[@id='c-ct']", SelectBy.TEXT, data.get(dataSet).get("cardType"));
			Common.textBoxInput("id", "c-cardnumber", data.get(dataSet).get("cardNumber"));
			Common.dropdown("id", "c-exmth", SelectBy.VALUE, data.get(dataSet).get("ExpMonth"));
			Common.dropdown("id", "c-exyr", SelectBy.TEXT, data.get(dataSet).get("ExpYear"));
			Common.textBoxInput("id", "c-cvv",  data.get(dataSet).get("cvv"));
			Common.switchToDefault();

			Sync.waitElementPresent("xpath", "//div[@class='iosc-place-order-container']//button[@title='Place Order']");
			Common.clickElement(By.xpath("//div[@class='iosc-place-order-container']//button[@title='Place Order']"));
			report.addPassLog(expectedResult,"Should display checkout page successfully", "Checkout Page display successfully", Common.getscreenShotPathforReport("Checkout success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display checkout page successfully", "Checkout Page display successfully", Common.getscreenShotPathforReport("Checkout Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void orderSuccesspage() throws Exception
	{
		String expectedResult="Order submition success page";
		try {

			Thread.sleep(7000);
			String s=Common.getText("xpath", "//div[@class='checkout-page-title-wrapper thank-you-page']//h1[@class='page-title']");
			System.out.println(s);
			Assert.assertEquals(s, "THANK YOU FOR YOUR PURCHASE");
			System.out.println("Order success page Test cases passed successfully");
			String s1=Common.getText("xpath", "//div[@class='checkout-success']/p/a/strong");
			System.out.println("Your order number is "+ s1);
			Thread.sleep(5000);
			report.addPassLog(expectedResult,"Should display Order success page", "Order success page display successfully", Common.getscreenShotPathforReport("Order success page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Order success page", "Order success page display successfully", Common.getscreenShotPathforReport("Order success page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void ProductRegistration() throws Exception
	{
		String expectedResult="Validating CMS Links Product Registration";
		try {
			Sync.waitElementPresent("xpath", "//div[@class='copyrights']");
			Common.scrollIntoView("xpath", "//div[@class='copyrights']");

			Sync.waitElementPresent("xpath", "//a[@href='https://stage.hottools.com/product-registration/']");
			Common.clickElement("xpath", "//a[@href='https://stage.hottools.com/product-registration/']");

			Thread.sleep(300);

			report.addPassLog(expectedResult,"Should navigate Product Registration", "Product registration page navigated successfully", Common.getscreenShotPathforReport("Product registration page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Order success page", "Order success page display successfully", Common.getscreenShotPathforReport("Product registration page Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}


	public void  productRegistration(String dataSet) throws Exception
	{

		String expectedResult="Product Registration page submission";
		try {	
			//Common.switchFrames("xpath", "//iframe[@src='https://helenoftroy--tst2.custhelp.com/app/ask/themes/revlon']");
			Thread.sleep(10000);
			Common.switchWindows();
			//Common.switchmainWindowsCons();

			Common.textBoxInput("name", "textinput-39", data.get(dataSet).get("FirstName"));

			Common.textBoxInput("name", "textinput-40", data.get(dataSet).get("LastName"));

			Sync.waitElementPresent("name", "dropdown-1565372469299");
			Common.dropdown("name", "dropdown-1565372469299", SelectBy.TEXT, data.get(dataSet).get("Customer Type"));

			Common.textBoxInput("name", "textinput-41", data.get(dataSet).get("Street"));

			Common.textBoxInput("name","textinput-42", data.get(dataSet).get("City"));

			Common.textBoxInput("name","textinput-43", data.get(dataSet).get("Region"));

			Common.textBoxInput("name", "textinput-44", data.get(dataSet).get("postcode"));

			Common.textBoxInput("name", "textinput-1542803230733", data.get(dataSet).get("phone"));

			Common.textBoxInput("name", "textinput-50", data.get(dataSet).get("Email"));

			Common.textBoxInput("name", "textinput-51", data.get(dataSet).get("Storewherepurchased"));

			//Common.scrollIntoView("xpath", "//input[@class='amform-date _has-datepicker']");
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Common.actionsKeyPress(Keys.TAB);

			Common.javascriptclickElement("xpath", "//input[@class='amform-date _has-datepicker']");
			//Common.clickElement("xpath", "//input[@class='amform-date _has-datepicker']");
			Thread.sleep(4000);
			Common.clickElement("xpath", "//*[@id='ui-datepicker-div']/table/tbody/tr[5]/td[2]/a");

			Common.textBoxInput("name", "textinput-53", data.get(dataSet).get("Producttype"));

			Common.clickElement("id", "checkbox-opt-80-0");

			Common.clickElement("id", "checkbox-opt-1566832969483-0");

			Common.scrollIntoView("xpath", "//button[contains(text(),'Submit')]");
			Sync.waitElementPresent("xpath", "//button[contains(text(),'Submit')]");
			Common.clickElement("xpath", "//button[contains(text(),'Submit')]");

			Thread.sleep(5000);

			Common.scrollIntoView("xpath", "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");
			String Success=	Common.getText("xpath", "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");
			System.out.println(Success+" Product Registration done");
			Assert.assertEquals(Success, "Thanks for registering your product. Your product registration was saved successfully.");
			report.addPassLog(expectedResult,"Should Submit Product Registration", "Product Registration submitted successfully", Common.getscreenShotPathforReport("Product Registration success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should Submit Product Registration", "Product Registration not submitted", Common.getscreenShotPathforReport("Product Registration Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}

	public void DistributorforgotPassword(String dataSet) throws Exception
	{
		String expectedResult="Forgot Password for Registered distributor User";
		try {
			if(Common.isElementDisplayed("id", "truste-consent-button"))
			{
				agreeCookiesbanner();
			} 

			Sync.waitElementPresent("xpath", "//span[@class='authorization-link']");
			Common.clickElement("xpath", "//span[@class='authorization-link']");

			Sync.waitElementPresent("xpath", "//a[text()='sign in as distributor']");
			Common.clickElement("xpath", "//a[text()='sign in as distributor']");

			Sync.waitElementPresent("id", "email");

			Sync.waitElementPresent("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"pro/index.php/customer/account/forgotpassword/']"));
			Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"pro/index.php/customer/account/forgotpassword/']"));

			Common.textBoxInput("name", "email", data.get(dataSet).get("Email"));

			Sync.waitElementPresent("xpath", "//button[@title='Reset My Password']");
			Common.clickElement("xpath", "//button[@title='Reset My Password']");
			Thread.sleep(15000);
			if(Common.isElementDisplayedonPage(10, "xpath", "//div[contains(text(),'Invalid Form Key. Please refresh the page.')]")){

				Common.refreshpage();

				Common.textBoxInput("name", "email", data.get(dataSet).get("Email"));

				Sync.waitElementPresent("xpath", "//button[@title='Reset My Password']");
				Common.clickElement("xpath", "//button[@title='Reset My Password']");
				Thread.sleep(7000);
				Sync.waitElementPresent("xpath", "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");
			}else {
				System.out.println("Forgot Password Link send to registered email");
			}

			if(Common.isElementDisplayed("xpath", "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")) {

				Thread.sleep(7000);
				String s=Common.getText("xpath", "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");
				System.out.println(s);
				Thread.sleep(7000);
				Assert.assertEquals(s, "If there is an account associated with "+data.get(dataSet).get("Email")+" you will receive an email with a link to reset your password.");

			}else {
				System.out.println("Forgot alert not displayed");
			}
			report.addPassLog(expectedResult,"Should display forgot password success message for distributor user", "Forgot password success message for distributor user display successfully", Common.getscreenShotPathforReport("Distributor forgotPassword success"));

		}
		catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display forgot password success message for distributor user", "Forgot password success message for distributor user not display", Common.getscreenShotPathforReport("Distributor forgotPassword Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void closeCurrentTabandSwitchtoHome() throws Exception
	{
		Common.closeCurrentWindow();
		Common.switchToFirstTab();
		Thread.sleep(3000);
	}

	public void validateNavigateGiveUsFeedback(String dataSet) throws Exception {
		String expectedResult="Validating CMS Links Give Us Feedback";
		try {
			Sync.waitElementPresent("xpath", "//div[@class='copyrights']");
			Common.scrollIntoView("xpath", "//div[@class='copyrights']");
			Sync.waitElementPresent("xpath", "//a[@href='" + System.getProperty("url",
					automation_properties.getInstance().getProperty(automation_properties.BASEURL) + "feedback/']"));
			Common.clickElement("xpath", "//a[@href='" + System.getProperty("url",
					automation_properties.getInstance().getProperty(automation_properties.BASEURL) + "feedback/']"));
			Thread.sleep(10000);
			Common.switchWindows();
			Common.textBoxInput("name", "-1", data.get(dataSet).get("FirstName"));
			Common.textBoxInput("name", "-2", data.get(dataSet).get("Email"));
			Sync.waitElementPresent("name", "-66");
			Common.dropdown("name", "-66", SelectBy.TEXT, data.get(dataSet).get("ProductName"));
			Common.clickElement("xpath", "//label[@for='-37-1']");
			Common.clickElement("xpath", "//label[@for='-6-2']");
			Common.textBoxInput("name", "-7", data.get(dataSet).get("review"));
			Common.scrollToElementAndClick("xpath",
					"//div[@class='amcform-toolbar']/button[@class='amcform-submit action submit primary ']");
			Thread.sleep(10000);
			if (Common.isElementDisplayed("xpath",
					"//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")) {

				Thread.sleep(7000);
				String s = Common.getText("xpath", "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");
				System.out.println(s);
				Thread.sleep(7000);
				Assert.assertEquals(s, "Thanks for your feedback. Your feedback form was saved successfully.");

			} else {
				System.out.println("Alert not displayed");
			}

			report.addPassLog(expectedResult,"Should display GiveUsFeedback", "GiveUsFeedback display successfully", Common.getscreenShotPathforReport("GiveUsFeedback success"));

		}
		catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display GiveUsFeedback", "GiveUsFeedback display successfully", Common.getscreenShotPathforReport("GiveUsFeedback Failed"));
			e.printStackTrace();
			Assert.fail();
		}
		this.closeCurrentTabandSwitchtoHome();

	}

	public void validateAboutUsLink() throws Exception
	{
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

	public void validateNavigateFAQ() throws Exception {
		String expectedResult="Validating CMS links FAQ";
		try
		{
			Sync.waitElementPresent("xpath", "//div[@class='copyrights']");
			Common.scrollIntoView("xpath", "//div[@class='copyrights']");
			Sync.waitElementPresent("xpath", "//a[@href='" + System.getProperty("url",
					automation_properties.getInstance().getProperty(automation_properties.BASEURL) + "faq/']"));
			Common.clickElement("xpath", "//a[@href='" + System.getProperty("url",
					automation_properties.getInstance().getProperty(automation_properties.BASEURL) + "faq/']"));
			Thread.sleep(5000);
			Common.switchWindows();
			String s = Common.getText("xpath",
					"//div[@class='page-title-wrapper']/h1[@class='page-title']/span[@class='base']");
			System.out.println(s);
			Assert.assertEquals(s, "HOT TOOLS FREQUENTLY ASKED QUESTIONS");
			report.addPassLog(expectedResult,"Should display FAQ page", "FAQ page display successfully", Common.getscreenShotPathforReport("FAQ page success"));

		}
		catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display FAQ page", "FAQ display successfully", Common.getscreenShotPathforReport("FAQ page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
		this.closeCurrentTabandSwitchtoHome();
	}

	public void  validateNavigateContactUs(String dataSet) throws Exception {

		String expectedResult="Validating CMS Links Contact us with form submission";
		try {
			Sync.waitElementPresent("xpath", "//div[@class='copyrights']");
			Common.scrollIntoView("xpath", "//div[@class='copyrights']");

			Sync.waitElementPresent("xpath", "//a[@href='" + System.getProperty("url",
					automation_properties.getInstance().getProperty(automation_properties.BASEURL) + "contact-us/']"));
			Common.clickElement("xpath", "//a[@href='" + System.getProperty("url",
					automation_properties.getInstance().getProperty(automation_properties.BASEURL) + "contact-us/']"));
			Thread.sleep(10000);
			Common.switchWindows();
			Sync.waitElementPresent("name", "dropdown-20");
			Common.dropdown("name", "dropdown-20", SelectBy.TEXT, data.get(dataSet).get("Department"));
			Common.textBoxInput("name", "-1", data.get(dataSet).get("FirstName"));
			Common.textBoxInput("name", "-2", data.get(dataSet).get("LastName"));
			Common.textBoxInput("name", "textinput-33", data.get(dataSet).get("Street"));
			Common.textBoxInput("name", "textinput-34", data.get(dataSet).get("City"));
			Sync.waitElementPresent("name", "dropdown-73");
			Common.dropdown("name", "dropdown-73", SelectBy.TEXT, data.get(dataSet).get("Region"));
			Common.textBoxInput("name", "textinput-36", data.get(dataSet).get("postcode"));
			Common.textBoxInput("name", "number-65", data.get(dataSet).get("phone"));
			Common.textBoxInput("name", "-3", data.get(dataSet).get("Email"));
			Common.textBoxInput("name", "textinput-26", data.get(dataSet).get("Model"));
			Common.textBoxInput("name", "textinput-27", data.get(dataSet).get("DataCode"));
			Common.textBoxInput("name", "-6", data.get(dataSet).get("review"));
			Common.scrollToElementAndClick("xpath", "//div[@class='amcform-toolbar']/button[@class='amcform-submit action submit primary ']");
			Thread.sleep(5000);
			if (Common.isElementDisplayed("xpath",
					"//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")) {

				Thread.sleep(7000);
				String s = Common.getText("xpath", "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");
				System.out.println(s);
				Thread.sleep(7000);
				Assert.assertEquals(s, "Thanks for contacting us. Your request was saved successfully.");

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
		this.closeCurrentTabandSwitchtoHome();
	}


	public void validateNavigatePrivacyPolicy() throws Exception {
		String expectedResult="Validating CMS link Privacy Policy";
		try {
			Sync.waitElementPresent("xpath", "//div[@class='copyrights']");
			Common.scrollIntoView("xpath", "//div[@class='copyrights']");
			Sync.waitElementPresent("xpath", "//a[@href='" + System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL) + "privacy-policy/']"));
			Common.clickElement("xpath", "//a[@href='" + System.getProperty("url",
					automation_properties.getInstance().getProperty(automation_properties.BASEURL) + "privacy-policy/']"));
			Thread.sleep(5000);
			Common.switchWindows();
			String s = Common.getText("xpath", "//div[@class='pagebuilder-content-type']/h2/span[@class='main_title']");
			System.out.println(s);
			Assert.assertEquals(s, "PRIVACY & COOKIES POLICY");
			report.addPassLog(expectedResult,"Should display PrivancyPolicy", "PrivancyPolicy display successfully", Common.getscreenShotPathforReport("PrivancyPolicy success"));

		}
		catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display PrivancyPolicy", "PrivancyPolicy display successfully", Common.getscreenShotPathforReport("PrivancyPolicy Failed"));
			e.printStackTrace();
			Assert.fail();
		}
		this.closeCurrentTabandSwitchtoHome();
	}


	public void validateNavigateTermsAndConditions() throws Exception {
		String expectedResult="Validating CMS links Terms & Conditions";
		try {
			Sync.waitElementPresent("xpath", "//div[@class='copyrights']");
			Common.scrollIntoView("xpath", "//div[@class='copyrights']");
			Sync.waitElementPresent("xpath",
					"//a[@href='" + System.getProperty("url",
							automation_properties.getInstance().getProperty(automation_properties.BASEURL)
							+ "terms-and-conditions/']"));
			Common.clickElement("xpath",
					"//a[@href='" + System.getProperty("url",
							automation_properties.getInstance().getProperty(automation_properties.BASEURL)
							+ "terms-and-conditions/']"));
			Thread.sleep(5000);
			Common.switchWindows();
			String s = Common.getText("xpath",
					"//div[@class='page-title-wrapper']/h1[@class='page-title']/span[@class='base']");
			System.out.println(s);
			Assert.assertEquals(s, "TERMS AND CONDITIONS");
			report.addPassLog(expectedResult,"Should display TermsAndConditions", "TermsAndConditions display successfully", Common.getscreenShotPathforReport("TermsAndConditions success"));

		}
		catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display TermsAndConditions", "TermsAndConditions display successfully", Common.getscreenShotPathforReport("TermsAndConditions Failed"));
			e.printStackTrace();
			Assert.fail();
		}
		this.closeCurrentTabandSwitchtoHome();
	}

	public void validateNavigatehottoolsUniversity() throws Exception {
		String expectedResult="Validating CMS links Hottools University";
		try {
			Sync.waitElementPresent("xpath", "//div[@class='copyrights']");
			Common.scrollIntoView("xpath", "//div[@class='copyrights']");
			Sync.waitElementPresent("xpath", "//a[@href='/hot-tools-university']");
			Common.clickElement("xpath", "//a[@href='/hot-tools-university']");
			Thread.sleep(5000);
			Common.switchWindows();
			String s = Common.getText("xpath",
					"//div[@class='breadcrumbs']/ul[@class='items']/li[@class='item cms_page']/strong");
			System.out.println(s);
			Assert.assertEquals(s, "HOT TOOLS UNIVERSITY");

			report.addPassLog(expectedResult,"Should display hottoolsUniversity", "hottoolsUniversity display successfully", Common.getscreenShotPathforReport("hottoolsUniversity success"));
		}
		catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display hottoolsUniversity", "hottoolsUniversity display successfully", Common.getscreenShotPathforReport("hottoolsUniversity Failed"));
			e.printStackTrace();
			Assert.fail();
		}
		this.closeCurrentTabandSwitchtoHome();
	}

	//Distributor login
	public void distributorSingin(String dataSet) throws Exception{

		//agreeCookiesbanner();
		String expectedResult="Validating Distributor Login with valid credentials";
		try
		{
			Sync.waitElementPresent("xpath", "//span[@class='authorization-link']");
			Common.clickElement("xpath", "//span[@class='authorization-link']");

			Sync.waitElementPresent("xpath", "//a[text()='sign in as distributor']");
			Common.clickElement("xpath", "//a[text()='sign in as distributor']");

			Sync.waitElementPresent("id", "email");

			Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
			Common.textBoxInput("id", "password", data.get(dataSet).get("Password"));

			Sync.waitElementPresent("xapth","//fieldset[@class='fieldset login']//div[3]");
			Thread.sleep(4000);
			Sync.waitElementPresent("id", "send2");
			Common.clickElement(By.id("send2"));

			report.addPassLog(expectedResult,"Should display distributorSingin", "distributorSingin display successfully", Common.getscreenShotPathforReport("distributorSingin success"));

		}
		catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display distributorSingin", "distributorSingin not display", Common.getscreenShotPathforReport("distributorSingin Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void distributorQuickOrderUnits(String dataSet) throws Exception{

		String expectedResult="Validating Distributor Quick Order";
		try
		{
			Sync.waitElementPresent("xpath", "//a[@title='Quick Order']");
			Common.clickElement("xpath", "//a[@title='Quick Order']");

			Thread.sleep(5000);

			Common.textBoxInput("id", "item-sku-units", data.get(dataSet).get("ProductName"));
			Common.textBoxInput("id", "item-qty-units", data.get(dataSet).get("QTY"));
			Common.clickElement("xpath", "//button[@title='Submit']");
			report.addPassLog(expectedResult,"Should display selected product", "Selected product display successfully", Common.getscreenShotPathforReport("QuickOrder selection success"));

		}
		catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display selected product", "Selected product not display", Common.getscreenShotPathforReport("QuickOrder selection Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}

	public void QuickOrderAddtoCart() throws Exception{

		String expectedResult="Validating Distributor Quick Order Add to Cart";
		try
		{
			Sync.waitElementPresent("xpath", "//button[@title='Add to Cart']");
			Common.clickElement("xpath", "//button[@title='Add to Cart']");

			report.addPassLog(expectedResult,"Should display Checkout page", "Checkout page display successfully", Common.getscreenShotPathforReport("QuickOrder Add to cart success"));

		}
		catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Checkout page", "Checkout page not display", Common.getscreenShotPathforReport("QuickOrder Add to cart Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}
}
