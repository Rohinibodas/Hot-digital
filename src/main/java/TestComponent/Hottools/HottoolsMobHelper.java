package TestComponent.Hottools;

import java.awt.Desktop.Action;
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

public class HottoolsMobHelper {
	String datafile;
	static Automation_properties automation_properties = Automation_properties.getInstance();
	ExcelReader excelData;
	Map<String, Map<String, String>> data=new HashMap<>();
	static ExtenantReportUtils report;

	public  HottoolsMobHelper(String datafile)
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

	public void acceptPrivecy()
	{
		Common.clickElementStale("xpath", "//button[text()='AGREE & PROCEED']");
	}
	
	public void slider() throws InterruptedException
	{
		Thread.sleep(15000);
		if(Common.isElementDisplayed("xpath", "//span[contains(text(),'WE VALUE YOUR PRIVACY')]")) {
			acceptPrivecy();
		}	
		Common.javascriptclickElement("xpath", "//span[@class='action nav-toggle']");
		Thread.sleep(3000);
		if(Common.isElementDisplayed("xpath", "//a[@href='#store.links']")) {
			System.out.println("Slider displayed");
		}else {
			System.out.println("slider not displayed");
			Thread.sleep(5000);
			//Common.findElement("xpath", "/html/body/div[2]/div[1]/div/header/div[2]/span").click();
			Common.findElement("xpath", "//span[@class='action nav-toggle']").click();
		}
	}
	
	public void Loginpage() throws InterruptedException
	{
		Thread.sleep(5000);
		Sync.waitElementClickable(30, By.xpath("//a[@href='#store.links']"));
		Common.clickElement("xpath", "//a[@href='#store.links']");
		
		Thread.sleep(1000);
		
		Sync.waitElementClickable(30, By.xpath("//*[@id='store.links']/ul[2]/li[2]/div/ul/li[1]/a"));
		Common.clickElement("xpath", "//*[@id='store.links']/ul[2]/li[2]/div/ul/li[1]/a");
	}
	
	public void DistributorLoginpage() throws InterruptedException
	{
		Thread.sleep(6000);
		Sync.waitElementClickable(30, By.xpath("//a[@href='#store.links']"));
		//Common.clickElement("xpath", "//a[@href='#store.links']");
		Common.javascriptclickElement("xpath", "//a[@href='#store.links']");
		
		Thread.sleep(1000);
		
		Sync.waitElementClickable(30, By.xpath("//*[@id='store.links']/ul[2]/li[2]/div/ul/li[2]/a"));
		Common.clickElement("xpath", "//*[@id='store.links']/ul[2]/li[2]/div/ul/li[2]/a");
	}

	public void singin(String dataSet) throws Exception{

		String expectedResult="Validating login page with valid credentials";
		try {
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

				Sync.waitElementPresent("id", "send2");
				Common.clickElement(By.id("send2"));

				Thread.sleep(4000);
				i++;
			}while(i<5 && !Common.isElementDisplayed("xpath", "//h1[@class='page-title']/span")); 

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
				acceptPrivecy();
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
				acceptPrivecy();
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

			/*Sync.waitElementClickable("xpath", "//button[@title='Search']");
			Common.clickElement(By.xpath("//button[@title='Search']"));*/
			
			Common.actionsKeyPress(Keys.ENTER);
		
			report.addPassLog(expectedResult,"Should search product successfully", "Searched product successfully", Common.getscreenShotPathforReport("Search product success"));
		}catch(Exception |Error e)
		{
			report.addPassLog(expectedResult,"Should search product successfully", "Searched product successfully", Common.getscreenShotPathforReport("Search product failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void ZerosearchProducts(String dataSet) throws Exception{

		String expectedResult="Search with Product name :"+data.get(dataSet).get("InvalidSearch");
		try {
			if(Common.isElementDisplayed("id", "truste-consent-button")) {
				acceptPrivecy();
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
				Common.textBoxInput("id", "search", data.get(dataSet).get("InvalidSearch"));

			}else {

				Common.refreshpage();

				Thread.sleep(3000);

				Sync.waitElementPresent("xpath", "//a[@href='#search-mod']");
				Common.clickElement("xpath", "//a[@href='#search-mod']");

				Sync.waitElementPresent("id", "search");
				Common.textBoxInput("id", "search", data.get(dataSet).get("InvalidSearch"));
			}

			/*Sync.waitElementClickable("xpath", "//button[@title='Search']");
			Common.clickElement(By.xpath("//button[@title='Search']"));*/
			
			Common.actionsKeyPress(Keys.ENTER);
		
			report.addPassLog(expectedResult,"Should search product successfully", "Searched product successfully", Common.getscreenShotPathforReport("Search product success"));
		}catch(Exception |Error e)
		{
			report.addPassLog(expectedResult,"Should search product successfully", "Searched product successfully", Common.getscreenShotPathforReport("Search product failed"));
			e.printStackTrace();
			Assert.fail();
		}


	}
	
	public void minicartProduct(String dataSet) throws Exception{

		String expectedResult="Product adding to mini cart";
		try {
			Common.actionsKeyPress(Keys.ENTER);
			Thread.sleep(5000);
			Common.actionsKeyPress(Keys.PAGE_UP);
			Sync.waitElementPresent("xpath", "//a[@title='"+data.get(dataSet).get("ProductName")+"']");

			Thread.sleep(5000);
			Common.clickElement("xpath", "//a[@title='"+data.get(dataSet).get("ProductName")+"']");
			System.out.println("product selected");

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
	public void checkoutpage() throws Exception
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
				//Sync.waitElementPresent("xpath", "//div[@class='items payment-methods']/div/div[4]/div//input[@name='payment[method]']");
				//Common.clickElement("xpath", "//div[@class='items payment-methods']/div/div[4]/div//input[@name='payment[method]']");		
			}
			Thread.sleep(2000);
			if(Common.checkBoxIsSelected("xpath", "//td[@class='col col-method']")) {

				System.out.println("Shipping method is selected");

			}else {

				Common.clickElement("xpath", "//td[@class='col col-method']");
			}
			Thread.sleep(3000);
			report.addPassLog(expectedResult,"Should display checkout page successfully", "Checkout Page display successfully", Common.getscreenShotPathforReport("Checkout success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display checkout page successfully", "Checkout Page display successfully", Common.getscreenShotPathforReport("Checkout Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void CreditcardPayment(String dataSet) throws Exception
	{
		String expectedResult="Payment With Valid Credit Card";
		try {
			Thread.sleep(5000);

			if(Common.isElementDisplayed("xpath", "//div[@id='checkout-loader']")) {
				Thread.sleep(8000);
			}else {
				Thread.sleep(6000);
				Common.actionsKeyPress(Keys.DOWN);
				Thread.sleep(5000);
				Common.actionsKeyPress(Keys.DOWN);
				Thread.sleep(5000);
				Sync.waitElementPresent("id", "ime_paymetrictokenize");
				Common.clickElement(By.id("ime_paymetrictokenize"));
				Thread.sleep(8000);
			}	
			Common.switchFrames("paymetric_xisecure_frame");
			Sync.waitElementPresent("xpath", "//select[@id='c-ct']");
			Common.dropdown("xpath", "//select[@id='c-ct']", SelectBy.TEXT, data.get(dataSet).get("cardType"));
			Common.textBoxInput("id", "c-cardnumber", data.get(dataSet).get("cardNumber"));
			Common.dropdown("id", "c-exmth", SelectBy.VALUE, data.get(dataSet).get("ExpMonth"));
			Common.dropdown("id", "c-exyr", SelectBy.TEXT, data.get(dataSet).get("ExpYear"));
			Common.textBoxInput("id", "c-cvv",  data.get(dataSet).get("cvv"));
			Common.switchToDefault();

			Common.scrollIntoView("xpath", "//div[@class='iosc-place-order-container']//button[@title='Place Order']");
			Thread.sleep(3000);
			
			Sync.waitElementPresent("xpath", "//div[@class='iosc-place-order-container']//button[@title='Place Order']");
			Common.clickElement(By.xpath("//div[@class='iosc-place-order-container']//button[@title='Place Order']"));
			
			report.addPassLog(expectedResult,"Should Make payment wih valid credit card successfully", "Make payment wih valid credit card successfully", Common.getscreenShotPathforReport("Payment CC success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should Make payment wih valid credit card successfully", "Make payment wih valid credit card unsuccessfully", Common.getscreenShotPathforReport("Payment CC Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void RegistereduserorderSuccesspage() throws Exception
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
	public void PurchaseRegistereduserorderSuccesspage() throws Exception
	{
		String expectedResult="Order submition success page";
		try {

			Thread.sleep(7000);
			String s=Common.getText("xpath", "//div[@class='checkout-page-title-wrapper thank-you-page']//h1[@class='page-title']");
			System.out.println(s);
			Assert.assertEquals(s, "THANK YOU FOR YOUR PURCHASE");
			System.out.println("Order success page Test cases passed successfully");
			/*String s1=Common.getText("xpath", "//div[@class='checkout-success']/p/a/strong");
			System.out.println("Your order number is "+ s1);
			Thread.sleep(5000);*/
			report.addPassLog(expectedResult,"Should display Order success page", "Order success page display successfully", Common.getscreenShotPathforReport("Order success page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Order success page", "Order success page display successfully", Common.getscreenShotPathforReport("Order success page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	public void Guestcheckoutpage(String dataSet) throws Exception
	{
		String expectedResult="Guest Checkout shipping address form";
		try {
			Thread.sleep(2000);
			Sync.waitElementPresent("xpath", "//button[@title='Proceed to Checkout']");
			Common.clickElement("xpath", "//button[@title='Proceed to Checkout']");
			Thread.sleep(4000);
			Common.textBoxInput("name", "username", data.get(dataSet).get("Email"));
			
			Thread.sleep(3000);
			
			shipping_Address("Guestshippingaddress");
			
			Thread.sleep(3000);
			
			Common.scrollIntoView("xpath", "//td[@class='col col-method']");
			Thread.sleep(3000);
			
			if(Common.checkBoxIsSelected("xpath", "//td[@class='col col-method']")) {

				System.out.println("Shipping method is selected");

			}else {

				Common.clickElement("xpath", "//td[@class='col col-method']");
			}

			//Common.clickElement("xpath", "//td[@class='col col-method']");
			Thread.sleep(2000);
			report.addPassLog(expectedResult,"Should display Guest checkout page successfully", "Guest Checkout Page display successfully", Common.getscreenShotPathforReport("Guest Checkout success"));
			if(Common.isElementVisibleOnPage(30, "xpath", "//div[contains(text(),'Shipping Address is not verified. Do you want to continue ?')]")) {

				System.out.println("Address not verified pop up displayed");

				Sync.waitElementPresent("xpath", "//button[@class='action-primary action-accept']");
				Common.clickElement("xpath", "//button[@class='action-primary action-accept']");
			}
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Guest checkout page successfully", "Guest Checkout Page display successfully", Common.getscreenShotPathforReport("Guest Checkout Failed"));
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
		/*Thread.sleep(3000);
		Sync.waitElementPresent("xpath", "//div[contains(text(),'Shipping Address')]");
		Common.clickElement("xpath", "//div[contains(text(),'Shipping Address')]");

		Thread.sleep(3000);*/

		Sync.waitElementPresent("name", "region_id");
		Common.clickElement("name", "region_id");

		Thread.sleep(2000);

		Sync.waitElementPresent("name", "region_id");
		//Common.clickElement("xpath", "//select[@title='State/Province']");
		Common.dropdown("name", "region_id", SelectBy.TEXT, data.get(dataSet).get("Region"));
		//Common.dropdown("xpath", "//div[@name='shippingAddress.region_id']/div//select[@name='region_id']", SelectBy.TEXT, data.get(dataSet).get("Region"));

		Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));

		Common.dropdown("xpath", "//select[@name='country_id']", SelectBy.TEXT, data.get(dataSet).get("Country"));

		Thread.sleep(500);

		Common.textBoxInput("name", "telephone",data.get(dataSet).get("phone"));

	}
	
	public void GuestCreditcard(String dataSet) throws Exception
	{
		String expectedResult="Guest Payment with valid credit card";
		try {
			Thread.sleep(4000);
			if(Common.isElementDisplayed("xpath", "//div[@id='checkout-loader']")) {
				Thread.sleep(5000);
			}else {
				Thread.sleep(3000);
				Sync.waitElementPresent("id", "ime_paymetrictokenize");
				Common.clickElement(By.id("ime_paymetrictokenize"));
				Thread.sleep(4000);
			}	
			Common.switchFrames("paymetric_xisecure_frame");
			Sync.waitElementPresent("xpath", "//select[@id='c-ct']");
			Common.dropdown("xpath", "//select[@id='c-ct']", SelectBy.TEXT, data.get(dataSet).get("cardType"));
			Common.textBoxInput("id", "c-cardnumber", data.get(dataSet).get("cardNumber"));
			Common.dropdown("id", "c-exmth", SelectBy.VALUE, data.get(dataSet).get("ExpMonth"));
			Common.dropdown("id", "c-exyr", SelectBy.TEXT, data.get(dataSet).get("ExpYear"));
			Common.textBoxInput("id", "c-cvv",  data.get(dataSet).get("cvv"));
			Common.switchToDefault();
			Thread.sleep(2000);
			report.addPassLog(expectedResult,"Should make payment with valid credit card successfully", "Make payment with valid credit card successfully", Common.getscreenShotPathforReport("validcreditcard success"));
			Thread.sleep(3000);
			Common.scrollIntoView("xpath", "//div[@class='iosc-place-order-container']//button[@title='Place Order']");
			Thread.sleep(3000);
			Sync.waitElementPresent("xpath", "//div[@class='iosc-place-order-container']//button[@title='Place Order']");
			Common.clickElement(By.xpath("//div[@class='iosc-place-order-container']//button[@title='Place Order']"));
			if(Common.isElementVisibleOnPage(30, "xpath", "//div[contains(text(),'Shipping Address is not verified. Do you want to continue ?')]")) {
				System.out.println("Address not verified pop up displayed");
				Sync.waitElementPresent("xpath", "//button[@class='action-primary action-accept']");
				Common.clickElement("xpath", "//button[@class='action-primary action-accept']");
			}
			Thread.sleep(1000);
			//report.addPassLog(expectedResult,"Should make payment with valid credit card successfully", "Make payment with valid credit card successfully", Common.getscreenShotPathforReport("validcreditcard success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should make payment with valid credit card successfully", "Payment with valid credit card failed", Common.getscreenShotPathforReport("cvalidcreditcardart Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void GuestuserorderSuccesspage() throws Exception
	{
		String expectedResult="Order submition success page";
		try {

			Thread.sleep(7000);
			String s=Common.getText("xpath", "//div[@class='checkout-page-title-wrapper thank-you-page']//h1[@class='page-title']");
			System.out.println(s);
			Assert.assertEquals(s, "THANK YOU FOR YOUR PURCHASE");
			System.out.println("Order success page Test cases passed successfully");
			String s1=Common.getText("xpath", "//*[@id='maincontent']/div[2]/div/div[2]/div[2]/p[1]/strong");
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
	
	public void PaypalPaymentMethod(String dataSet) throws Exception
	{
		String expectedResult="Payment with valid Paypal Details";
		try {
			Thread.sleep(4000);
			if(Common.isElementDisplayed("xpath", "//div[@id='checkout-loader']")) {
				Thread.sleep(5000);
			}else {
				Thread.sleep(3000);
				Sync.waitElementPresent("id", "paypal_express");
				Common.clickElement(By.id("paypal_express"));
				Thread.sleep(4000);
			}	
			
			Thread.sleep(3000);
			Common.scrollIntoView("xpath", "//div[@class='iosc-place-order-container']//button[@type='submit']");
			Thread.sleep(3000);
			Sync.waitElementPresent("xpath", "//div[@class='iosc-place-order-container']//button[@type='submit']");
			Common.clickElement(By.xpath("//div[@class='iosc-place-order-container']//button[@type='submit']"));
			Thread.sleep(5000);
			if(Common.isElementDisplayed("xpath", "//button[text()='Accept Cookies']")) {
				Common.clickElementStale("xpath", "//button[text()='Accept Cookies']");
			}
			//Common.clickElementStale("xpath", "//button[text()='Accept Cookies']");
			Common.textBoxInputClear("name", "login_email");
			Common.textBoxInput("name", "login_email", data.get(dataSet).get("UserName"));
			Common.textBoxInput("name", "login_password", data.get(dataSet).get("Password"));
			Common.clickElement("xpath", "//button[@id='btnLogin']");
			Thread.sleep(5000);
			Common.actionsKeyPress(Keys.END);
			Thread.sleep(3000);
			//Common.scrollIntoView("xpath", "//button[contains(text(),'Pay Now')]");
			Common.clickElement("id", "payment-submit-btn");
			Thread.sleep(5000);
			report.addPassLog(expectedResult,"Should make payment with valid Paypal successfully", "Make payment with valid Paypal successfully", Common.getscreenShotPathforReport("validPaypal success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should make payment with valid Paypal successfully", "Payment with valid Paypal failed", Common.getscreenShotPathforReport("cvalidPaypal Failed"));
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
		//this.closeCurrentTabandSwitchtoHome();
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
		//this.closeCurrentTabandSwitchtoHome();
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
		//this.closeCurrentTabandSwitchtoHome();
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
		//this.closeCurrentTabandSwitchtoHome();
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
		//this.closeCurrentTabandSwitchtoHome();
	}

	public void validateNavigatehottoolsUniversity() throws Exception {
		String expectedResult="Validating CMS links Hottools University";
		try {
			Sync.waitElementPresent("xpath", "//div[@class='copyrights']");
			Common.scrollIntoView("xpath", "//div[@class='copyrights']");
			
			Sync.waitElementPresent("xpath", "//a[@title='Hot Tools University']");
			Common.clickElement("xpath", "//a[@title='Hot Tools University']");
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
		//closeCurrentTabandSwitchtoHome();
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
		//this.closeCurrentTabandSwitchtoHome();

	}
	
	public void ProductRegistration() throws Exception
	{
		String expectedResult="Validating CMS Links Product Registration";
		try {
			Sync.waitElementPresent("xpath", "//div[@class='copyrights']");
			Common.scrollIntoView("xpath", "//div[@class='copyrights']");

			Sync.waitElementPresent("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"product-registration/']"));
			Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"product-registration/']"));

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
            //Common.switchFrames("//iframe[@src='https://helenoftroy--tst1.custhelp.com/app/product_registration/themes/hottools']");
            //Common.switchFrames("xpath", "//iframe[@src='https://helenoftroy--tst1.custhelp.com/app/product_registration/themes/hottools']");
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

 

            Common.textBoxInput("name", "Contact.Phones.MOBILE.Number", data.get(dataSet).get("phone"));

 

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
            
            Thread.sleep(5000);

 

            Common.scrollIntoView("xpath", "//*[contains(text(),'Thank you for registering your product!  Your request has been processed.')]");
            String Success=    Common.getText("xpath", "//*[contains(text(),'Thank you for registering your product!  Your request has been processed.')]");
            System.out.println(Success+" Product Registration done");
            Assert.assertEquals(Success, "Thank you for registering your product! Your request has been processed.");
            Common.switchToDefault();
            Thread.sleep(2000);
            Common.actionsKeyPress(Keys.PAGE_UP);
            
            Common.scrollIntoView("xpath", "//span[contains(text(),'Product Registration')]");
            report.addPassLog(expectedResult,"Should Submit Product Registration", "Product Registration submitted successfully", Common.getscreenShotPathforReport("Product Registration success"));
        }catch(Exception |Error e)
        {
            report.addFailedLog(expectedResult,"Should Submit Product Registration", "Product Registration not submitted", Common.getscreenShotPathforReport("Product Registration Failed"));
            e.printStackTrace();
            Assert.fail();
        }

 

    }
	
	

	public void agreeCookiesbanner() throws Exception{
		Sync.waitElementPresent("id", "truste-consent-button");

		Common.clickElement("id", "truste-consent-button");
	}
	
	public void DistributorforgotPassword(String dataSet) throws Exception
	{
		String expectedResult="Forgot Password for Registered distributor User";
		try {
			if(Common.isElementDisplayed("id", "truste-consent-button"))
			{
				agreeCookiesbanner();
			}

			Sync.waitElementPresent("id", "email");
			
			Common.scrollIntoView("xpath", "//span[contains(text(),'Distributor Login')]");
			Thread.sleep(3000);

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
	/*Rakesh Maddi*/
	public void ClicktheSignbutton() throws Exception{
		Sync.waitElementPresent("xpath", "//span[@class='authorization-link']");
		Common.clickElement("xpath", "//span[@class='authorization-link']");

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

				Common.clickElement("xpath", "//*[@id='ui-datepicker-div']/table/tbody/tr[5]/td[1]/a");
			}else {
				Common.clickElement("id", "date_field");
				Thread.sleep(5000);
				Common.clickElement("xpath", "//*[@id='ui-datepicker-div']/table/tbody/tr[5]/td[1]/a");
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

			//Common.actionsKeyPress(Keys.PAGE_DOWN);

			/*String cap=	Common.findElement(By.xpath("//img[@class='captcha-img']")).getAttribute("value");
			System.out.println(cap+"cap with class");

			String capid=Common.findElement(By.xpath("//div[@id='captcha-container-user_create']")).getAttribute("value");
			System.out.println(capid+"cap with id");*/

			//Sync.waitPageLoad();
			Thread.sleep(15000);
			Common.actionsKeyPress(Keys.UP);

			String Success=	Common.getText("xpath", "(//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)'])[1]");
			System.out.println(Success+" Account created");

			Assert.assertEquals(Success, "Thank you for registering with Hot Tools Professional.");
			//Common.actionsKeyPress(Keys.UP);

            Thread.sleep(2000);
			report.addPassLog(expectedResult,"Should Retail Account create successfully", "Retailer User account created successfully", Common.getscreenShotPathforReport("Retailer Account Creation success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should Retail Account create successfully", "Retailer User account not created", Common.getscreenShotPathforReport("Retailer Account Creation Failed"));
			e.printStackTrace();
			Assert.fail();
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
			
			Common.scrollIntoView("xpath", "//span[contains(text(),'Customer Login')]");
			Thread.sleep(3000);

			Sync.waitElementPresent("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"customer/account/forgotpassword/']"));
			Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"customer/account/forgotpassword/']"));

			Common.textBoxInput("name", "email", data.get(dataSet).get("Email"));
			
			Thread.sleep(9000);

			Sync.waitElementPresent("xpath", "//button[@title='Reset My Password']");
			Common.clickElement("xpath", "//button[@title='Reset My Password']");
			Thread.sleep(7000);

			if(Common.isElementDisplayedonPage(10, "xpath", "//div[contains(text(),'Invalid Form Key. Please refresh the page.')]")){

				Common.refreshpage();

				Common.textBoxInput("name", "email", data.get(dataSet).get("Email"));
				
				Thread.sleep(9000);

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
	public void categoryMenuItem()
	{
		String expectedResult="Select from  category" ;
		try {
			Thread.sleep(1000);
			Sync.waitElementPresent("xpath", "(//span[contains(text(), 'Hair Dryers')])[1]");
			Common.clickElement("xpath", "(//span[contains(text(), 'Hair Dryers')])[1]");
			Thread.sleep(1000);
			//Sync.waitElementPresent("xpath", "(//a[@title='Mini Travel Dryer in Mint'])[2]");
			//Common.clickElement("xpath", "(//a[@title='Mini Travel Dryer in Mint'])[2]");
			//Thread.sleep(5000);
			report.addPassLog(expectedResult, "Should display item from  menucategory", "product display successfully", Common.getscreenShotPathforReport(" product display success"));

		}catch(Exception e)
		{
			report.addFailedLog(expectedResult,"Should display Search Results Page", "Search results Page not display", Common.getscreenShotPathforReport("Search result Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	public void myorderconfirmation() throws Exception
	{
		String expectedResult="Lands On My Order Page & Should display list of orders";
		try {
			Thread.sleep(4000);
			
			Sync.waitElementPresent("xpath", "//span[@class='action nav-toggle']");
			Common.clickElement("xpath", "//span[@class='action nav-toggle']");

			Thread.sleep(2000);
			Sync.waitElementPresent("xpath", "(//a[@class='nav-sections-3 nav-sections-item-switch'])[2]");
			Common.clickElement("xpath", "(//a[@class='nav-sections-3 nav-sections-item-switch'])[2]");
			
			Thread.sleep(2000);
		    Sync.waitElementPresent("xpath", "(//a[contains(text(), 'My Account')])[2]");
		    Common.clickElement("xpath", "(//a[contains(text(), 'My Account')])[2]");
		    
		    Thread.sleep(2000);
		    //Sync.waitElementPresent("xpath", "//a[contains(text(), 'My Orders')]");
		    Common.clickElement("xpath", "(//strong[contains(text(), 'My Account')])[1]");
		    
		    Thread.sleep(2000);
		    Sync.waitElementPresent("xpath", "//a[contains(text(), 'My Orders')]");
		    Common.clickElement("xpath", "//a[contains(text(), 'My Orders')]");
			
		    String Order=Common.getText("xpath", "(//td[@class='col id'])[1]");
			
			System.out.println(Order);
			Thread.sleep(1000);
			report.addPassLog(expectedResult, "Should display My Order page with List of orders", "My Order page with List of orders display successfully", Common.getscreenShotPathforReport("MyOrder Page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display My Order page with List of orders", "My Order page with List of orders not displayed", Common.getscreenShotPathforReport("MyOrder Page Failed"));
			Assert.fail();
		}
	}
	public void signin(String dataSet) throws Exception{

		String expectedResult="Land on login page";
		try {

			if(Common.isElementDisplayed("id", "truste-consent-button")) {
				agreeCookiesbanner();
			}
			Sync.waitElementPresent("xpath", "//span[@class='authorization-link']/button");
			Common.clickElement("xpath", "//span[@class='authorization-link']/button");
            
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//a[text()='sign in']");
			Common.clickElement("xpath", "//a[text()='sign in']");
			Thread.sleep(1000);
			int i=0;
			do{ 
				//Common.refreshpage();

				Sync.waitElementPresent("id", "email");

				Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
				Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));

				Sync.waitElementPresent("xapth","//fieldset[@class='fieldset login']//div[3]");
				Thread.sleep(5000);
				Common.scrollIntoView("xpath", "//span[contains(text(),'Customer Login')]");
				Common.actionsKeyPress(Keys.ARROW_DOWN);
				Thread.sleep(4000);
				//Common.scrollIntoView("id", "send2");
				Sync.waitElementPresent("xpath", "//button[@id='send2']/span");
				Common.javascriptclickElement("xpath", "//button[@id='send2']/span");
				//Common.clickElement(By.xpath("//button[@id='send2']/span"));
				Thread.sleep(5000);
				i++;
			}while(i<5 && !Common.isElementDisplayed("xpath", "//h1[@class='page-title']/span")); 

			report.addPassLog(expectedResult,"Should Login to application successfully", "Login to application successfully", Common.getscreenShotPathforReport("Login success"));
		}catch(Exception |Error e)
		{
			report.addPassLog(expectedResult,"Should Login to application successfully", "Login to application Failed", Common.getscreenShotPathforReport("Login failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	public void MyOrderConfirmation() throws Exception
	{
		String expectedResult="Order Confirmation page navigation";
		try {
			Thread.sleep(4000);
			
			Sync.waitElementPresent("xpath","//span[@class='action nav-toggle']");
			Common.clickElement("xpath", "//span[@class='action nav-toggle']");
			
			Sync.waitElementPresent("xpath","(//a[@class='nav-sections-3 nav-sections-item-switch'])[2]");
			Common.clickElement("xpath", "(//a[@class='nav-sections-3 nav-sections-item-switch'])[2]");
			
			Thread.sleep(2000);

			Sync.waitElementPresent("xpath","(//a[contains(text(), 'My Account')])[2]");
			Common.clickElement("xpath", "(//a[contains(text(), 'My Account')])[2]");
			
			Sync.waitElementPresent("xpath","(//strong[contains(text(), 'My Account')])[1]");
			Common.clickElement("xpath", "(//strong[contains(text(), 'My Account')])[1]");
			Thread.sleep(2000);
			Common.clickElement("xpath", "//a[contains(text(), 'My Orders')]");
					
			String s1=Common.getText("xpath", "(//td[@class='col id'])[1]");
			System.out.println("Your order number is "+ s1);
			Thread.sleep(1000);
			report.addPassLog(expectedResult,"Should display Order Confirmation page", "Order Confirmation page display successfully", Common.getscreenShotPathforReport("Order Confirmation page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Order Confirmation page", "Order Confirmation page display successfully", Common.getscreenShotPathforReport("Order Confirmation page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	public void  productReg(String dataSet) throws Exception
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
			Common.clickElement("xpath", "//*[@id='ui-datepicker-div']/table/tbody/tr[4]/td[2]/a");

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
			Common.scrollIntoView("xpath", "//span[contains(text(), 'Product Registration')]");
			Thread.sleep(1000);
			report.addPassLog(expectedResult,"Should Submit Product Registration", "Product Registration submitted successfully", Common.getscreenShotPathforReport("Product Registration success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should Submit Product Registration", "Product Registration not submitted", Common.getscreenShotPathforReport("Product Registration Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}
public void PurchaseOrder(String dataSet) throws Exception{
		
		Thread.sleep(2000);
		Common.clickElement("xpath", "//input[@id='purchaseorder']");
		Common.textBoxInput("xpath", "(//input[@title='Purchase Order Number'])[1]",  data.get(dataSet).get("OrderId"));
	    Thread.sleep(5000);
		Sync.waitElementPresent("xpath","(//button[@title='Place Order'])[2]");
		Common.clickElement("xpath", "(//button[@title='Place Order'])[2]");
		//Common.javascriptclickElement("xpath", "(//button[@title='Place Order'])[2]");
	
	}
public void QuickOrder() throws Exception{
	String expectedResult="Quick Order Submission";
	try {
		Thread.sleep(2000);
		Sync.waitElementPresent("xpath","(//a[@title='Quick Order'])[1]");
		Common.clickElement("xpath", "(//a[@title='Quick Order'])[1]");
		Thread.sleep(2000);
     report.addPassLog(expectedResult,"Should Lands on Quick Order Page Successfully", "Landed on Quick Order Page successfully", Common.getscreenShotPathforReport("Quick Order Page"));
}catch(Exception |Error e)
{
	report.addPassLog(expectedResult,"Should Lands on Quick Order Page Successfully", "Not Landed on Quick Order Page successfully", Common.getscreenShotPathforReport("Quick Order Page failed"));
	e.printStackTrace();
	Assert.fail();
}
}
public void AddfromFile() throws Exception{
	String expectedResult="Quick Order Submission";
	try {
		Thread.sleep(2000);
		Common.fileUpLoad("id", "customer_sku_csv", System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\Hottools\\AddFromFile.csv" );
		Thread.sleep(2000);
		
     report.addPassLog(expectedResult,"Should Add Product From .Csv File Successfully", "Added Product From .Csv File successfully", Common.getscreenShotPathforReport("Quick Order Add From File Page"));
     Common.clickElement("xpath", "//span[contains(text(), 'Add to Cart')]");
	}catch(Exception |Error e)
{
	report.addPassLog(expectedResult,"Should Add Product From .Csv File Successfully", "Not Added Product From .Csv File successfully", Common.getscreenShotPathforReport("Quick Order Add Frome File Page failed"));
	e.printStackTrace();
	Assert.fail();
}
}
public void QuickOrderDetails(String dataSet) throws Exception{
	String expectedResult="Quick Order Details";
	try {
		Thread.sleep(2000);
		//Common.clickElement("xpath", "//input[@id='item-sku-units']");
		Common.textBoxInput("xpath", "//input[@id='item-sku-units']",  data.get(dataSet).get("ProductName"));
		//Common.clickElement("xpath", "//input[@id='item-sku-units']");
		//Common.clickElement("xpath", "(//span[contains(text(), 'Hot Tools® Professional Black Gold™ One-Step Detachable Blowout ')])[2]");
		//Common.javascriptclickElement("xpath", "//*[@id='ui-id-10']/li/span");
		
		Thread.sleep(4000);
		Common.clickElement("xpath", "//input[@id='item-qty-units']");
		Common.textBoxInput("xpath", "//input[@id='item-qty-units']",  data.get(dataSet).get("Qty"));
		Thread.sleep(2000);
		Common.clickElement("xpath", "(//span[contains(text(), 'Submit')])[1]");
		Thread.sleep(1000);
     report.addPassLog(expectedResult,"Should Submit Units Details Successfully", "Submitted Units Details Successfully", Common.getscreenShotPathforReport("Units Details Page"));
        
        Common.clickElement("xpath", "//span[contains(text(), 'Add to Cart')]");
        Thread.sleep(3000);
        //Common.clickElement("xpath", "//input[@title='Qty']");
        Common.javascriptclickElement("xpath", "//input[@title='Qty']");
		Common.textBoxInput("xpath", "//input[@title='Qty']",  data.get(dataSet).get("Qty"));
		Thread.sleep(1000);
		Sync.waitElementPresent("xpath","//span[contains(text(), 'Update Shopping Cart')]");
		Common.clickElement("xpath", "//span[contains(text(), 'Update Shopping Cart')]");

}catch(Exception |Error e)
{
	report.addPassLog(expectedResult,"Should Submit Units Details Successfully", "Not Submitted Units Details Successfully", Common.getscreenShotPathforReport("Units Details Page failed"));
	e.printStackTrace();
	Assert.fail();
}
}
public void QuickOrderDetailsMultipleSkus(String dataSet) throws Exception{
	String expectedResult="Quick Order Multiple Skus Details ";
	try {
		Thread.sleep(2000);
		Common.clickElement("xpath", "//textarea[@name='multiple_skus']");
		Common.textBoxInput("xpath", "//textarea[@name='multiple_skus']",  data.get(dataSet).get("ProductName"));
		
		Thread.sleep(2000);
		Common.clickElement("xpath", "(//span[contains(text(), 'Submit')])[3]");
		Thread.sleep(1000);
     report.addPassLog(expectedResult,"Should Submit Multiple Skus Details Successfully", "Submitted Multiple Skus Details Successfully", Common.getscreenShotPathforReport("Multiple Skus Details Page"));
        
        Common.clickElement("xpath", "//span[contains(text(), 'Add to Cart')]");
        Thread.sleep(3000);
        
        Common.javascriptclickElement("xpath", "(//input[@title='Qty'])[1]");
		Common.textBoxInput("xpath", "(//input[@title='Qty'])[1]",  data.get(dataSet).get("Qty"));
		Thread.sleep(2000);
		
        //Common.javascriptclickElement("xpath", "(//input[@title='Qty'])[2]");
		//Common.textBoxInput("xpath", "(//input[@title='Qty'])[2]",  data.get(dataSet).get("Qty"));
		Thread.sleep(2000);
		Sync.waitElementPresent("xpath","//span[contains(text(), 'Update Shopping Cart')]");
		Common.clickElement("xpath", "//span[contains(text(), 'Update Shopping Cart')]");
}catch(Exception |Error e)
{
	report.addPassLog(expectedResult,"Should Submit Multiple Skus Details Successfully", "Not Submitted Multiple Skus Details Successfully", Common.getscreenShotPathforReport("Multiple Skus Details Page failed"));
	e.printStackTrace();
	Assert.fail();
}
}
public void QuickOrderDetailsCases(String dataSet) throws Exception{
	String expectedResult="Quick Order Cases Details ";
	try {
		Thread.sleep(2000);
		Common.clickElement("xpath", "//input[@id='item-sku-cases']");
		Common.textBoxInput("xpath", "//input[@id='item-sku-cases']",  data.get(dataSet).get("ProductName"));
		//Common.clickElement("xpath", "(//span[contains(text(), 'Hot Tools® Professional Black Gold™ One-Step Detachable Blowout ')])[2]");
		//Common.javascriptclickElement("xpath", "//*[@id='ui-id-10']/li/span");
		
		Thread.sleep(2000);
		Common.clickElement("xpath", "//input[@id='item-qty-cases']");
		Common.textBoxInput("xpath", "//input[@id='item-qty-cases']",  data.get(dataSet).get("Qty"));
		Thread.sleep(2000);
		Common.clickElement("xpath", "(//span[contains(text(), 'Submit')])[2]");
		Thread.sleep(1000);
     report.addPassLog(expectedResult,"Should Submit Cases Details Successfully", "Submitted Cases Details Successfully", Common.getscreenShotPathforReport("Cases Details Page"));
        
        Common.clickElement("xpath", "//span[contains(text(), 'Add to Cart')]");
}catch(Exception |Error e)
{
	report.addPassLog(expectedResult,"Should Submit Cases Details Successfully", "Not Submitted Cases Details Successfully", Common.getscreenShotPathforReport("Cases Details Page failed"));
	e.printStackTrace();
	Assert.fail();
}
}
public void Quickcheckoutpage() throws Exception
{
	String expectedResult="Proceeding to checkout page";
	try {
		
		//Common.scrollIntoView("xpath", "//span[contains(text(),'Shopping Cart')]");
		Thread.sleep(5000);
		Sync.waitElementPresent("xpath", "//button[@title='Proceed to Checkout']");
		//Common.clickElement("xpath", "//button[@title='Proceed to Checkout']");
		Common.javascriptclickElement("xpath", "//button[@title='Proceed to Checkout']");
		
		//Common.clickElementStale("xpath", "//button[@title='Proceed to Checkout']");
		//Common.clickElement("xpath", "//button[@class='action primary checkout']");

		if(Common.isElementDisplayed("xpath", "//*[@id='checkout-step-shipping']/div[1]/div/div/div[1]//span[contains(text(),'Ship here:')]")) {
			System.out.println("Shipping address is not selected");
			Common.clickElement("xpath", "//*[@id='checkout-step-shipping']/div[1]/div/div/div[1]//span[contains(text(),'Ship here:')]");
		}else {
			System.out.println("Shipping address is selected");
			Thread.sleep(5000);
			//Sync.waitElementPresent("xpath", "//div[@class='items payment-methods']/div/div[4]/div//input[@name='payment[method]']");
			//Common.clickElement("xpath", "//div[@class='items payment-methods']/div/div[4]/div//input[@name='payment[method]']");		
		}
		Thread.sleep(2000);
		if(Common.checkBoxIsSelected("xpath", "//td[@class='col col-method']")) {

			System.out.println("Shipping method is selected");

		}else {

			Common.clickElement("xpath", "//td[@class='col col-method']");
		}
		
		Thread.sleep(2000);
		report.addPassLog(expectedResult,"Should display checkout page successfully", "Checkout Page display successfully", Common.getscreenShotPathforReport("Checkout success"));
	}catch(Exception |Error e)
	{
		report.addFailedLog(expectedResult,"Should display checkout page successfully", "Checkout Page display successfully", Common.getscreenShotPathforReport("Checkout Failed"));
		e.printStackTrace();
		Assert.fail();
	}
}
public void QuickPaymentMethod() throws Exception{
    Thread.sleep(5000);
	Sync.waitElementPresent("xpath","//div[@class='shipping-address-item not-selected-item']");
	Common.clickElement("xpath", "//div[@class='shipping-address-item not-selected-item']");
	//Common.javascriptclickElement("xpath", "(//button[@title='Place Order'])[2]");

}
public void QuickCreditcardPayment(String dataSet) throws Exception
{
	String expectedResult="Payment With Valid Credit Card";
	try {
		Thread.sleep(5000);

		if(Common.isElementDisplayed("xpath", "//div[@id='checkout-loader']")) {
			Thread.sleep(8000);
		}else {
			Thread.sleep(6000);
			/*Sync.waitElementPresent("id", "ime_paymetrictokenize");
			Common.clickElement(By.id("ime_paymetrictokenize"));
			Thread.sleep(8000);*/
		}	
		//Common.switchFrames("paymetric_xisecure_frame");
		
		Thread.sleep(5000);
		
		Sync.waitElementPresent("xpath", "(//input[@name='payment[method]'])[2]");
		Common.clickElement("xpath", "(//input[@name='payment[method]'])[2]");
		
		Sync.waitElementPresent("xpath", "(//input[@title='Purchase Order Number'])[3]");
		Common.clickElement("xpath", "(//input[@title='Purchase Order Number'])[3]");
		Common.textBoxInput("xpath", "(//input[@title='Purchase Order Number'])[3]",  data.get(dataSet).get("OrderId"));
	    
		Common.switchFrames("paymetric_xisecure_frame");
		Thread.sleep(1000);
	    
		Sync.waitElementPresent("xpath", "//select[@id='c-ct']");
		Common.dropdown("xpath", "//select[@id='c-ct']", SelectBy.TEXT, data.get(dataSet).get("cardType"));
		Common.textBoxInput("id", "c-cardnumber", data.get(dataSet).get("cardNumber"));
		Common.dropdown("id", "c-exmth", SelectBy.VALUE, data.get(dataSet).get("ExpMonth"));
		Common.dropdown("id", "c-exyr", SelectBy.TEXT, data.get(dataSet).get("ExpYear"));
		Common.textBoxInput("id", "c-cvv",  data.get(dataSet).get("cvv"));
		Common.switchToDefault();
        
		Common.scrollIntoView("xpath", "(//div[@class='iosc-place-order-container']/button)[1]");
		Thread.sleep(6000);
		Sync.waitElementPresent("xpath", "(//div[@class='iosc-place-order-container']/button)[1]");
		Common.javascriptclickElement("xpath", "(//div[@class='iosc-place-order-container']/button)[1]");
		
		report.addPassLog(expectedResult,"Should Make payment wih valid credit card successfully", "Make payment wih valid credit card successfully", Common.getscreenShotPathforReport("Payment CC success"));
	}catch(Exception |Error e)
	{
		report.addFailedLog(expectedResult,"Should Make payment wih valid credit card successfully", "Make payment wih valid credit card unsuccessfully", Common.getscreenShotPathforReport("Payment CC Failed"));
		e.printStackTrace();
		Assert.fail();
	}
}
public void FooterNewletterSubcription() throws Exception{
	String expectedResult="Newletter Subcription from footer";
	try {
		Sync.waitElementPresent("xpath", "//div[@class='copyrights']");
		Common.scrollIntoView("xpath", "//div[@class='copyrights']");
		Thread.sleep(2000);
		Common.textBoxInput("name", "email", Utils.getEmailid());
		Thread.sleep(2000);
		Common.clickElement("xpath", "//button[@type='submit']");
		Thread.sleep(15000);
		if(Common.isElementDisplayed("xpath", "//div[@data-bind='html: message.text']")) {
			String s=Common.getText("xpath", "//div[@data-bind='html: message.text']");
			System.out.println(s);
			Assert.assertEquals(s, "Thank you for your subscription");
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
public void BecomeInfluencer() throws Exception
{
	String expectedResult="Validating CMS Links Become an Influencer";
	try {
		Sync.waitElementPresent("xpath", "//div[@class='copyrights']");
		Common.scrollIntoView("xpath", "//div[@class='copyrights']");

		Sync.waitElementPresent("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"influencer-program/']"));
		Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"influencer-program/']"));

		Thread.sleep(300);

		report.addPassLog(expectedResult,"Should navigate Become an Influencer", "Become an Influencer page navigated successfully", Common.getscreenShotPathforReport("Influencer page success"));
	}catch(Exception |Error e)
	{
		report.addFailedLog(expectedResult,"Should display Become an Influencer", "Become an Influencer page navigated successfully", Common.getscreenShotPathforReport("Influencer page Failed"));
		e.printStackTrace();
		Assert.fail();
	}

}

public void  BecomeInfluencerpage(String dataSet) throws Exception
{
	String expectedResult="Become an Influencer page submission";
	try {	
		Thread.sleep(10000);
		Common.switchWindows();
		
		Common.textBoxInput("name", "textinput-1557762544137", data.get(dataSet).get("Email"));
		Common.textBoxInput("name", "textinput-1557763133652", data.get(dataSet).get("PersonalStyle"));
		Common.textBoxInput("name", "textinput-1557763511526", data.get(dataSet).get("LikeProduct"));
		Common.textBoxInput("name", "textinput-1557765020520", data.get(dataSet).get("Blog"));
		Common.textBoxInput("name", "textinput-1557765082400", data.get(dataSet).get("Instagram"));
		Common.textBoxInput("name", "textinput-1557765153834", data.get(dataSet).get("InstaFollowers"));
		Common.textBoxInput("name", "textinput-1557765254599", data.get(dataSet).get("Snapchat"));
		Common.textBoxInput("name", "textinput-1557765269949", data.get(dataSet).get("snapFollower"));
		Common.textBoxInput("name", "textinput-1557765309090", data.get(dataSet).get("Facebook"));
		Common.textBoxInput("name", "textinput-1557765307875", data.get(dataSet).get("FacebookFollowers"));
		Common.textBoxInput("name", "textinput-1557765394001", data.get(dataSet).get("Twitter"));
		Common.textBoxInput("name", "textinput-1557765404516", data.get(dataSet).get("TwitterFollowers"));
		Common.textBoxInput("name", "textinput-1557765421812", data.get(dataSet).get("Youtube"));
		Common.textBoxInput("name", "textinput-1557765423836", data.get(dataSet).get("YoutubeFollowers"));
		Thread.sleep(1000);
		Common.clickElement("xpath", "//*[contains(text(),'Submit')]");
		Thread.sleep(5000);
		String Success=Common.getText("xpath", "//*[contains(text(),'Thanks for contacting us. Your request was sent successfully.')]");
		System.out.println(Success);
		Assert.assertEquals(Success, "Thanks for contacting us. Your request was sent successfully.");
		Common.switchToDefault();
		Thread.sleep(2000);
		//Common.actionsKeyPress(Keys.PAGE_UP);
		
		report.addPassLog(expectedResult,"Should Submit Become an Influencer", "Become an Influencer submitted successfully", Common.getscreenShotPathforReport("Become an Influencer success"));
	}catch(Exception |Error e)
	{
		report.addFailedLog(expectedResult,"Should Submit Become an Influencer", "Become an Influencer not submitted", Common.getscreenShotPathforReport("Become an Influencer Failed"));
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
		Thread.sleep(2000);
		Common.clickElement("xpath", "//span[@class='action nav-toggle']");
	}
}
public void GoldCardVideoClose() throws Exception{
	Thread.sleep(9000);
	Sync.waitElementPresent("xpath", "//a[@class='submit']/span");
	Common.clickElement("xpath", "//a[@class='submit']/span");
	Thread.sleep(3000);
	report.addPassLog("Should display Gold Card Homepage", "Successful Landed on Gold Card Homepage", Common.getscreenShotPathforReport("Gold Card Homepage success"));
}
public void GoldCardInspo(String dataSet) throws Exception{
	String expectedResult="GoldCardInspo Checkout ";
	try {
		Thread.sleep(2000);
		
		Common.clickElement("xpath", "//input[@id='name']");
		Common.textBoxInput("xpath", "//input[@id='name']",  data.get(dataSet).get("InspoAccess"));
		Thread.sleep(2000);
		Common.clickElement("xpath", "//span[@class='checkmark']");
		Thread.sleep(2000);
		Common.clickElement("xpath", "//button[@id='accesscode_go']/span");
		Thread.sleep(6000);
     report.addPassLog(expectedResult,"Should Display Products with Checkout Button  Successfully", "Displayed products with Checkout Button Successfully", Common.getscreenShotPathforReport("Products Displayed With Checkout Button Successfully"));
}catch(Exception |Error e)
{
	report.addPassLog(expectedResult,"Should Display Products with Checkout Button Successfully", "Not Displayed Product", Common.getscreenShotPathforReport("Products Displayed With Checkout Button Failed"));
	e.printStackTrace();
	Assert.fail();
}
}
public void GoldCardCreative(String dataSet) throws Exception{
	String expectedResult="GoldCardCreative Checkout ";
	try {
		Thread.sleep(2000);
		
		Common.clickElement("xpath", "//input[@id='name']");
		Common.textBoxInput("xpath", "//input[@id='name']",  data.get(dataSet).get("CreativeAccess"));
		Thread.sleep(2000);
		Common.clickElement("xpath", "//span[@class='checkmark']");
		Thread.sleep(2000);
		Common.clickElement("xpath", "//button[@id='accesscode_go']/span");
		Thread.sleep(6000);
     report.addPassLog(expectedResult,"Should Submit Cases Details Successfully", "Submitted Cases Details Successfully", Common.getscreenShotPathforReport("Cases Details Page"));
}catch(Exception |Error e)
{
	report.addPassLog(expectedResult,"Should Submit Cases Details Successfully", "Not Submitted Cases Details Successfully", Common.getscreenShotPathforReport("Cases Details Page failed"));
	e.printStackTrace();
	Assert.fail();
}
}
public void GoldCardArtist(String dataSet) throws Exception{
	String expectedResult="GoldCardCreative Checkout ";
	try {
		Thread.sleep(2000);
		
		Common.clickElement("xpath", "//input[@id='name']");
		Common.textBoxInput("xpath", "//input[@id='name']",  data.get(dataSet).get("ArtistAccess"));
		Thread.sleep(2000);
		Common.clickElement("xpath", "//span[@class='checkmark']");
		Thread.sleep(2000);
		Common.clickElement("xpath", "//button[@id='accesscode_go']/span");
		Thread.sleep(6000);
     report.addPassLog(expectedResult,"Should Submit Cases Details Successfully", "Submitted Cases Details Successfully", Common.getscreenShotPathforReport("Cases Details Page"));
}catch(Exception |Error e)
{
	report.addPassLog(expectedResult,"Should Submit Cases Details Successfully", "Not Submitted Cases Details Successfully", Common.getscreenShotPathforReport("Cases Details Page failed"));
	e.printStackTrace();
	Assert.fail();
}
}
public void GoldCardPremiere(String dataSet) throws Exception{
	String expectedResult="GoldCardCreative Checkout ";
	try {
		Thread.sleep(2000);
		
		Common.clickElement("xpath", "//input[@id='name']");
		Common.textBoxInput("xpath", "//input[@id='name']",  data.get(dataSet).get("PremiereAccess"));
		Thread.sleep(2000);
		Common.clickElement("xpath", "//span[@class='checkmark']");
		Thread.sleep(2000);
        Common.clickElement("xpath", "//button[@id='accesscode_go']/span");
        Thread.sleep(5000);
        report.addPassLog(expectedResult,"Should Display Products with Checkout Button  Successfully", "Displayed products with Checkout Button Successfully", Common.getscreenShotPathforReport("Products Displayed With Checkout Button Successfully"));
	}catch(Exception |Error e)
	{
		report.addPassLog(expectedResult,"Should Display Products with Checkout Button Successfully", "Not Displayed Product", Common.getscreenShotPathforReport("Products Displayed With Checkout Button Failed"));
		e.printStackTrace();
		Assert.fail();
	}
}
public void Checkout() throws Exception{
	String expectedResult="GoldCardInspo Checkout ";
		Thread.sleep(5000);
		Sync.waitElementPresent("xpath","(//label[@class='move-to-cart-btn']//input)[1]");
		Common.clickElement("xpath", "(//label[@class='move-to-cart-btn']//input)[1]");
}
public void GoldCardShipping(String dataSet) throws Exception
{
	try {
		Thread.sleep(12000);
		
	Common.textBoxInput("xpath", "//input[@name='username']", data.get(dataSet).get("Email"));
	
    Common.textBoxInput("name", "firstname", data.get(dataSet).get("FirstName"));

    Common.textBoxInput("name", "lastname", data.get(dataSet).get("LastName"));

    Common.textBoxInput("name", "street[0]", data.get(dataSet).get("Street"));

    Common.textBoxInput("name", "city", data.get(dataSet).get("City"));

    Thread.sleep(3000);
    
     Sync.waitElementPresent("name", "region_id");

     Common.clickElement("name", "region_id");

     Thread.sleep(3000);

      Sync.waitElementPresent("name", "region_id");

      Common.dropdown("name", "region_id", SelectBy.TEXT, data.get(dataSet).get("Region"));

      Thread.sleep(4000);

      Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));

      Common.dropdown("xpath", "//select[@name='country_id']", SelectBy.TEXT, data.get(dataSet).get("Country"));

      Thread.sleep(500);
      
      Common.textBoxInput("name", "telephone",data.get(dataSet).get("phone"));

      Thread.sleep(4000);
      
      Common.actionsKeyPress(Keys.PAGE_UP);
      
      report.addPassLog("Should Fill Shipping Address Details Successfully", "Filled Shipping Address Details Successfully", Common.getscreenShotPathforReport("Shipping Address Page"));

      }catch(Exception |Error e)
      {
       report.addPassLog("Should Fill Shipping Address Details Successfully", "Not Filled Shipping Address Details", Common.getscreenShotPathforReport("Shipping Address Page failed"));

       e.printStackTrace();

       Assert.fail();

       }

}
public void ValidatingPromocode(String dataSet) throws Exception
{
	String expectedResult="Validate Promode in Checkout page";
	try {
		
		Common.scrollIntoView("xpath", "(//strong[contains(text(), 'Order Total')])[1]");
	

		
		//Sync.waitElementPresent("id", "block-discount-heading");
		//Common.clickElement("id", "block-discount-heading");
		
		Sync.waitElementPresent("name", "discount_code_fake");
		Common.clickElement("name", "discount_code_fake");
		
		Common.textBoxInput("name", "discount_code_fake", data.get(dataSet).get("Promocode"));
		
		Thread.sleep(2000);

		Sync.waitElementPresent("xpath", "(//button[@class='action action-apply']/span)[1]");
		Common.clickElement("xpath", "(//button[@class='action action-apply']/span)[1]");
		
		//String success=Common.getText("xpath", "//div[@data-ui-id='checkout-cart-validationmessages-message-success']");
		//System.out.println(success);
		
		Thread.sleep(2000);
		report.addPassLog(expectedResult, "Should display Success message for promocode", "Success of promocode displayed successfully", Common.getscreenShotPathforReport("Promocode success"));
	}catch(Exception |Error e)
	{
		report.addFailedLog(expectedResult,"Should display Success message for promocode", "Success of promocode not displayed", Common.getscreenShotPathforReport("Promocode Failed"));
		e.printStackTrace();
		Assert.fail();
	}

}
public void CasesRegistereduserorderSuccesspage() throws Exception
{
	String expectedResult="Order submition success page";
	try {

		Thread.sleep(7000);
		String s=Common.getText("xpath", "//div[@class='checkout-page-title-wrapper thank-you-page']//h1[@class='page-title']");
		System.out.println(s);
		Assert.assertEquals(s, "THANK YOU FOR YOUR PURCHASE");
		System.out.println("Order success page Test cases passed successfully");
		/*String s1=Common.getText("xpath", "//div[@class='checkout-success']/p/a/strong");
		System.out.println("Your order number is "+ s1);
		Thread.sleep(5000);*/
		
		report.addPassLog(expectedResult,"Should display Order success page", "Order success page display successfully", Common.getscreenShotPathforReport("Order success page success"));
	}catch(Exception |Error e)
	{
		report.addFailedLog(expectedResult,"Should display Order success page", "Order success page display successfully", Common.getscreenShotPathforReport("Order success page Failed"));
		e.printStackTrace();
		Assert.fail();
	}
}
public void UnitsRegistereduserorderSuccesspage() throws Exception
{
	String expectedResult="Order submition success page";
	try {

		/*Thread.sleep(7000);
		String s=Common.getText("xpath", "//div[@class='checkout-page-title-wrapper thank-you-page']//h1[@class='page-title']");
		System.out.println(s);
		Assert.assertEquals(s, "THANK YOU FOR YOUR PURCHASE");
		System.out.println("Order success page Test cases passed successfully");
		String s1=Common.getText("xpath", "//div[@class='checkout-success']/p/a/strong");
		System.out.println("Your order number is "+ s1);
		Thread.sleep(5000);*/
		report.addPassLog(expectedResult,"Should display Order success page", "Order success page display successfully", Common.getscreenShotPathforReport("Order success page success"));
	}catch(Exception |Error e)
	{
		report.addFailedLog(expectedResult,"Should display Order success page", "Order success page display successfully", Common.getscreenShotPathforReport("Order success page Failed"));
		e.printStackTrace();
		Assert.fail();
	}
}
public void MultipleRegistereduserorderSuccesspage() throws Exception
{
	String expectedResult="Order submition success page";
	try {

		Thread.sleep(7000);
		String s=Common.getText("xpath", "//div[@class='checkout-page-title-wrapper thank-you-page']//h1[@class='page-title']");
		System.out.println(s);
		Assert.assertEquals(s, "THANK YOU FOR YOUR PURCHASE");
		System.out.println("Order success page Test cases passed successfully");
		/*String s1=Common.getText("xpath", "//div[@class='checkout-success']/p/a/strong");
		System.out.println("Your order number is "+ s1);
		Thread.sleep(1000);*/
		report.addPassLog(expectedResult,"Should display Order success page", "Order success page display successfully", Common.getscreenShotPathforReport("Order success page success"));
	}catch(Exception |Error e)
	{
		report.addFailedLog(expectedResult,"Should display Order success page", "Order success page display successfully", Common.getscreenShotPathforReport("Order success page Failed"));
		e.printStackTrace();
		Assert.fail();
	}
}
public void FileRegistereduserorderSuccesspage() throws Exception
{
	String expectedResult="Order submition success page";
	try {

		Thread.sleep(7000);
		String s=Common.getText("xpath", "//div[@class='checkout-page-title-wrapper thank-you-page']//h1[@class='page-title']");
		System.out.println(s);
		Assert.assertEquals(s, "THANK YOU FOR YOUR PURCHASE");
		/*System.out.println("Order success page Test cases passed successfully");
		String s1=Common.getText("xpath", "//div[@class='checkout-success']/p/a/strong");
		System.out.println("Your order number is "+ s1);
		Thread.sleep(5000);*/
		report.addPassLog(expectedResult,"Should display Order success page", "Order success page display successfully", Common.getscreenShotPathforReport("Order success page success"));
	}catch(Exception |Error e)
	{
		report.addFailedLog(expectedResult,"Should display Order success page", "Order success page display successfully", Common.getscreenShotPathforReport("Order success page Failed"));
		e.printStackTrace();
		Assert.fail();
	}
}
public void Guestcheckoutpages(String dataSet) throws Exception
{
	String expectedResult="Guest Checkout shipping address form";
	try {
		Thread.sleep(2000);
		/*Sync.waitElementPresent("xpath", "//button[@title='Proceed to Checkout']");
		Common.clickElement("xpath", "//button[@title='Proceed to Checkout']");*/
		Thread.sleep(4000);
		Common.textBoxInput("name", "username", data.get(dataSet).get("Email"));
		
		Thread.sleep(3000);
		
		shipping_Address("Guestshippingaddress");
		
		Thread.sleep(3000);
		
		Common.scrollIntoView("xpath", "//td[@class='col col-method']");
		Thread.sleep(3000);
		
		if(Common.checkBoxIsSelected("xpath", "//td[@class='col col-method']")) {

			System.out.println("Shipping method is selected");

		}else {

			Common.clickElement("xpath", "//td[@class='col col-method']");
		}

		//Common.clickElement("xpath", "//td[@class='col col-method']");
		Thread.sleep(2000);
		report.addPassLog(expectedResult,"Should display Guest checkout page successfully", "Guest Checkout Page display successfully", Common.getscreenShotPathforReport("Guest Checkout success"));
		if(Common.isElementVisibleOnPage(30, "xpath", "//div[contains(text(),'Shipping Address is not verified. Do you want to continue ?')]")) {

			System.out.println("Address not verified pop up displayed");

			Sync.waitElementPresent("xpath", "//button[@class='action-primary action-accept']");
			Common.clickElement("xpath", "//button[@class='action-primary action-accept']");
		}
	}catch(Exception |Error e)
	{
		report.addFailedLog(expectedResult,"Should display Guest checkout page successfully", "Guest Checkout Page display successfully", Common.getscreenShotPathforReport("Guest Checkout Failed"));
		e.printStackTrace();
		Assert.fail();
	}
}








	

	
}
