package TestComponent.Hydroflask;
  
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.bag.SynchronizedSortedBag;
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
import org.openqa.selenium.WebElement;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextFontAlignType;
import org.testng.Assert;

import TestLib.Automation_properties;
import TestLib.Common;
import TestLib.Sync;
import TestLib.Common.SelectBy;
import Utilities.ExcelReader;
import Utilities.ExtenantReportUtils;
import Utilities.xmlReader;

public class HydroHelper {
  
	
	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data = new HashMap<>();
	static ExtenantReportUtils report;
	
	static Automation_properties automation_properties = Automation_properties.getInstance();
	
	
	public int getpageresponce(String url) throws MalformedURLException, IOException{
		 HttpURLConnection c=(HttpURLConnection)new URL(url).openConnection();
		   c.setRequestMethod("HEAD");
		   c.connect();
		   int r = c.getResponseCode();
		   
		   
		   return r;
	}
	
	
	public void navigateMyAccount() throws InterruptedException {
		Thread.sleep(2000);
		Sync.waitPageLoad();
		String expectedResult = "User should land on the home page";
		int size =Common.findElements("xpath", "//a[@class='logo']").size();
		Common.assertionCheckwithReport(size > 0, " verifying the home page", expectedResult,"Successfully landed on the home page", "User unabel to land on home page");
		//Common.assertionCheckwithReport(size>0, "Successfully landed on th home page", expectedResult,"User unabel to land on home page");
		try {
			Sync.waitPageLoad();
			//Thread.sleep(5000);
			try {
			Sync.waitElementClickable(30, By.xpath("//a[@class='social-login']"));
			Common.findElement("xpath", "//a[@class='social-login']").click();
			}
			catch(Exception e){
				Common.clickElement("xpath", "//li[@class='header-links-login']/a");
			}
			//Thread.sleep(3000);
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying my account option", "clcik the my account button",
					"User failed to clcik the my account button",
					Common.getscreenShotPathforReport("my account button"));
			Assert.fail();

		}
		/*
		 * String expectedResult="Open login, register pop up";
		 * report.addPassLog(expectedResult,"click on the 'My Acount' link"
		 * ,Common.getscreenShotPathforReport("my account"));
		 */
	}

	public void acceptPrivecy() {
		
		Common.clickElementStale("id", "truste-consent-required");
	}
public void Rigisteraccount(String dataSet) {
		
		try {
			
			
		String email = Common.genrateRandomEmail(data.get(dataSet).get("Email"));
		
		Common.findElement("xpath", "//div[@class='login-page-register-wrapper']/a").click();
		
		
		Common.textBoxInput("id", "firstname", data.get(dataSet).get("FirstName"));
		
		Common.textBoxInput("id", "lastname", data.get(dataSet).get("LastName"));
		
		Common.textBoxInput("id", "email_address", email);
		
		Common.textBoxInput("id", "password", data.get(dataSet).get("Password"));
		Common.textBoxInput("id", "password-confirmation", data.get(dataSet).get("Password"));
		
		Common.clickElement("xpath","//button[@class='register-page-action-submit']");
		ExtenantReportUtils.addPassLog("verifying  page to Create new account",
				"Crate account from with valid data", "User sucssfully creat account",
				Common.getscreenShotPathforReport("account creation issue"));
			

		
		}
		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying  page to Create new account",
					"Crate account from with valid data", "User failed to proceed creat account form ",
					Common.getscreenShotPathforReport("account creation issue"));
			Assert.fail();
		
	}
	}
	
	public void CreateNewAccount(String dataSet) throws Exception {

		navigateMyAccount();
		String expectedResult = "opens Sign up pop up";

		if(Common.findElements("xpath", "//div[@class='login-page-register-wrapper']/a").size()>0) {
			
			Rigisteraccount(dataSet);
			
		}
		else {
		
		try {
			Sync.waitElementClickable(30, By.xpath("//div[contains(text(),'Sign Up')]"));
			// Common.assertionCheckwithReport("", expectedResult, "");

			// report.addPassLog(expectedResult,"Successfully opeans Sign up
			// pop_up page",Common.getscreenShotPathforReport("Successfully
			// opeans Sign up pop_up page"));

		} catch (Exception e) {
			if (Common.findElement("xpath", "//div[contains(text(),'Sign Up')]") == null) {

				Common.clickElement("xpath", "//a[@class='social-login']");
				Thread.sleep(2000);
			}
		}

		String email = Common.genrateRandomEmail(data.get(dataSet).get("Email"));
		Common.clickElement("xpath", "//div[contains(text(),'Sign Up')]");

		// report.addPassLog(expectedResult,"Successfully opeans Sign up pop_up
		// page",Common.getscreenShotPathforReport("Successfully opeans Sign up
		// pop_up page"));
		// report.addPassLog("opens registration pop
		// up",Common.getscreenShotPathforReport("register"));
		try {
			Common.textBoxInput("id", "social-login-popup-create-firstname", data.get(dataSet).get("FirstName"));
			expectedResult = "opens Sign up pop up";
			int size = Common.findElements("id", "social-login-popup-create-firstname").size();

			Common.assertionCheckwithReport(size > 0, "verifying sign up pageÂ ", expectedResult,
					"Successfully opeans Sign up pop up", "Faild to load the Sign popup");
			// Common.assertionCheckwithReport(size>0, "Successfully opeans Sign
			// up pop_up page", expectedResult, "Faild to load the Sign popup");

			Common.textBoxInput("id", "social-login-popup-create-lastname", data.get(dataSet).get("LastName"));
			Common.textBoxInput("id", "social-login-popup-create-email", email);
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Common.textBoxInput("id", "social-login-popup-create-pass", data.get(dataSet).get("Password"));
			Common.textBoxInput("id", "password-confirmation-social", data.get(dataSet).get("Password"));
			Common.clickElement("id", "social-login-popup-create-is-subscribed");
			expectedResult = "see the fields populated with the data";
			ExtenantReportUtils.addPassLog("verifying sign up page with field data", expectedResult,
					"successfully fill the data in username email password",
					Common.getscreenShotPathforReport("signup page issue"));
			Common.clickElement("xpath", "//button[@title='Sign Up']");
			int errormessagetextSize = Common.findElements("xpath", "//div[contains (text(),'required')]").size();
			if (errormessagetextSize <= 0) {
			} else {

				ExtenantReportUtils.addFailedLog("verifying sign up page with valid field data", expectedResult,
						"User failed to proceed signUp form", Common.getscreenShotPathforReport("signup issue"));
				// ExtenantReportUtils.addFailedLog("Sign usp popup with valid
				// Data", "User failed to proceed signUp form ",
				// Common.getscreenShotPathforReport("signup issue"));
			}

		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("verifying sign up page to Create new account",
					"Sign up popup with valid Data", "User failed to proceed signUp form ",
					Common.getscreenShotPathforReport("signup issue"));
			Assert.fail();

		}

		Thread.sleep(2000);

		try {
			Sync.waitElementVisible("xpath", "//span[@data-ui-id='page-title-wrapper']");
			// Assert.assertEquals(Common.getText("xpath",
			// "//span[@data-ui-id='page-title-wrapper']"), "My Account");
			expectedResult = "it creates an account and logs in the user";
			String text = Common.getText("xpath", "//span[@data-ui-id='page-title-wrapper']");
			// Common.assertionCheckwithReport(text.equals("My Account"),
			// "Successfully Created an account and logged in the
			// application",expectedResult, "Unabel to create Account");

			Common.assertionCheckwithReport(text.equals("My Account"), "verifying new account creation confirmation ",
					expectedResult, "Successfully Created an account and logged in the application",
					"faield to Create New Account");

			// report.addPassLog(expectedResult,"Successfully Created an account
			// and logged in the
			// application",Common.getscreenShotPathforReport("Successfully
			// Created an account and logged in the application"));
		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("verifying new account creation confirmation", expectedResult,
					"User failed to faield to Create New Account ", Common.getscreenShotPathforReport("signup faield"));
			Assert.fail();

		}
		}
	}
	public void login(String dataSet){
		try {

		Thread.sleep(5000);
		Common.assertionCheckwithReport(Common.getPageTitle().equals("Customer Login"), "validating login page", "after clicking my account button it will navigate to login page", "sucessfully navigate to login page", "fail to navigate login page");
		Common.textBoxInput("id", "email",data.get(dataSet).get("Email"));
		//Thread.sleep(4000);
		Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
		//Thread.sleep(5000);
	    Common.clickElement("xpath", "//button[@class='login-page-submit-action']");
	    
	    
	    Thread.sleep(4000);
	    
		String text = Common.getText("xpath", "//span[@data-ui-id='page-title-wrapper']");
		
		Common.assertionCheckwithReport(text.equals("My Account"), "verifying login account",
				"customer is redirected to My Account page",
				"Logged in the application and customer is redirected to My Account page",
				"Unabel to login Account");
		}
		
		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying login page with credentials", "Open login page",
					"User failed to login in account  ", Common.getscreenShotPathforReport("login faield"));
			Assert.fail();

		}
	}
	public void loginHydroflaskAccount(String dataSet) throws Exception {
		//Thread.sleep(3000);
		navigateMyAccount();
		String expectedResult = "Opens login pop_up";

	if(Common.findElements("id","email").size()>0) {
		
		login(dataSet);
	}
	else {
		
		Sync.waitElementClickable(30, By.id("social-login-popup-log-in-email"));
		if (Common.findElement("id", "social-login-popup-log-in-email") == null) {
			Common.clickElement("xpath", "//a[@class='social-login']");
			//Thread.sleep(2000);
		}
		int size = Common.findElements("id", "social-login-popup-log-in-email").size();

		Common.assertionCheckwithReport(size > 0, "verifying  login pop up", expectedResult,
				"Successfully opeans Login pop up page", "Faild to load the Login pop up");
		// Common.assertionCheckwithReport(size>0, "Successfully opeans Login
		// pop up page", expectedResult, "Faild to load the Login pop up");

		try {
			Common.textBoxInput("id", "social-login-popup-log-in-email", data.get(dataSet).get("Email"));
			Common.textBoxInput("id", "social-login-popup-log-in-pass", data.get(dataSet).get("Password"));

			expectedResult = "see the fields populated with the data";
			int errormessagetextSize = Common.findElements("xpath", "//div[contains (@id,'error')]").size();
			Common.assertionCheckwithReport(errormessagetextSize <= 0, "verifying login credentials", expectedResult,
					"Successfully Enter in the login data", "Required Field Data Missing");
			// (errormessagetextSize<=0, "Successfully Enter in the login data,
			// email address and password", expectedResult,"Required Field Data
			// Missing");

			Common.clickElement("id", "bnt-social-login-authentication");
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//span[@data-ui-id='page-title-wrapper']");
			// Assert.assertEquals(Common.getText("xpath",
			// "//span[@data-ui-id='page-title-wrapper']"), "My Account");
			// expectedResult="it will successfully logs in and will see the
			// customer name on the header and customer is redirected to 'My
			// Account' page";
			String text = Common.getText("xpath", "//span[@data-ui-id='page-title-wrapper']");
			// ExtenantReportUtils.addPassLog(expectedResult, "",
			// Common.getscreenShotPathforReport(expectedResult));
			Common.assertionCheckwithReport(text.equals("My Account"), "verifying login account",
					"customer is redirected to My Account page",
					"Logged in the application and customer is redirected to My Account page",
					"Unabel to login Account");
			// (text.equals("My Account"), "Logged in the application and
			// customer is redirected to My Account page",expectedResult,
			// "Unabel to login Account");

		}

		catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("verifying login page with credentials", expectedResult,
					"User failed to login in account  ", Common.getscreenShotPathforReport("login faield"));
			Assert.fail();

		}
	}
	}
	/*
	 * public void orderSubmit(String category) throws Exception {
	 * report.addPassLog("Successfully landed on the home page",Common.
	 * getscreenShotPathforReport("Successfully landed on the home page"));
	 * Thread.sleep(5000); //Common.getDriver().switchTo().frame(0); //
	 * Thread.sleep(2000); Sync.waitElementClickable("xpath",
	 * "//ul[@class='megamenu-list']/li[1]/div[1]");
	 * System.out.println(Common.getText("xpath",
	 * "//ul[@class='megamenu-list']/li[1]/div[1]/button")); Thread.sleep(4000);
	 * Common.mouseOverClick("xpath",
	 * "//ul[@class='megamenu-list']/li[1]/div[1]/button");
	 * //Common.clickElement("css",
	 * "ul.megamenu-list > li:nth-of-type(1) > div:nth-of-type(1) > button");
	 * Thread.sleep(3000); try { Common.mouseOver("xpath",
	 * "//a[contains(text(),'"+category+"')]");
	 * 
	 * report.addPassLog("click the"+category,Common.
	 * getscreenShotPathforReport("click the category as shop option as  "
	 * +category)); }catch (Exception e) { // TODO: handle exception
	 * Common.clickElement("xpath",
	 * "//ul[@class='megamenu-list']/li[1]/div[1]/button");
	 * //report.addPassLog("click the option as shop in category",Common.
	 * getscreenShotPathforReport("click the category as shop ")); }
	 * report.addPassLog("Clicked shop option in category",Common.
	 * getscreenShotPathforReport("clicked shop option in category"));
	 * Thread.sleep(1000); Common.clickElement("xpath",
	 * "//a[contains(text(),'"+category+"')]");
	 * //Sync.waitElementVisible("xpath",
	 * "//div[text()='Drink in the adventure.']"); Thread.sleep(4000);
	 * report.addPassLog("Selected the "+category+" category	",Common.
	 * getscreenShotPathforReport("click the category as shop option as  "
	 * +category)); Common.actionsKeyPress(Keys.PAGE_DOWN); Thread.sleep(2000);
	 * Common.actionsKeyPress(Keys.PAGE_DOWN); Thread.sleep(4000);
	 * Sync.waitElementClickable("xpath", "//button[@title='Add to Cart']");
	 * Common.clickElement("xpath", "//button[@title='Add to Cart']");
	 * Thread.sleep(5000); report.addPassLog("Added Product to Cart",Common.
	 * getscreenShotPathforReport("Added Product to Cart")); }
	 */


	

	public void orderSubmit(String category) throws Exception {
		
		
		
		
		Thread.sleep(5000);
		String expectedResult = "User should land on the home page";
		int size = Common.findElements("xpath", "//a[@class='logo']").size();
		Common.assertionCheckwithReport(size > 0, "validating the home page ", expectedResult,"Successfully landed on the home page", "User unabel to land on home page");
		// Common.assertionCheckwithReport(size>0, "Successfully landed on the
		// home page", expectedResult,"User unabel to land on home page");

		Sync.waitElementClickable("xpath", "//ul[@class='megamenu-list']/li[1]/div[1]");
		Thread.sleep(3000);
		Common.mouseOverClick("xpath", "//ul[@class='megamenu-list']/li[1]/div[1]/button");
		Thread.sleep(3000);
		expectedResult = "User should click the" + category;
		try {
			Common.mouseOver("xpath", "//a[contains(text(),'" + category + "')]");
		} catch (Exception e) {
			Common.clickElement("xpath", "//ul[@class='megamenu-list']/li[1]/div[1]/button");
		}
		Thread.sleep(1000);
		Common.clickElement("xpath", "//a[contains(text(),'" + category + "')]");
		Thread.sleep(4000);
		expectedResult = "User should select the " + category + "category";
		int sizebotteles = Common.findElements("xpath", "//a[contains(text(),'" + category + "')]").size();
		Common.assertionCheckwithReport(sizebotteles > 0,
				"validating the product category as" + category + "from navigation menu ", expectedResult,
				"Selected the " + category + " category", "User unabel to click" + category + "");
		// Common.assertionCheckwithReport(sizebotteles>0, "Selected the
		// "+category+" category", expectedResult,"User unabel to
		// click"+category+"");

		try {
			// Common.actionsKeyPress(Keys.PAGE_DOWN);
			// Thread.sleep(2000);
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			//Common.actionsKeyPress(Keys.PAGE_DOWN);
			//Thread.sleep(8000);
			for (int i = 0; i <= 10; i++) {
				Thread.sleep(2000);
				List<WebElement> webelementslist = Common.findElements("xpath",
						"//a[contains(@class,'product-colors-total-link')]");
				String s = webelementslist.get(i).getAttribute("href");
				if (s.isEmpty()) {

				} else {
					break;
				}
			}
			//ClosADD();
			Sync.waitElementClickable("xpath", "//button[@title='Add to Cart']");
			Thread.sleep(4000);
			List<WebElement> element = Common.findElements("xpath", "//button[@title='Add to Cart']");

			
			element.get(6).click();
            Thread.sleep(5000);

			//String s = Common.getText("xpath", "//a[@aria-label='minicart']/following::span[3]");
			//System.out.println();

			expectedResult = "Product should add to Cart";

			int cartbuttonsize = Common.findElements("xpath", "(//button[@title='Add to Cart'])[2]").size();
			Common.assertionCheckwithReport(cartbuttonsize > 0, "validating the add product to cart", expectedResult,
					"Added Product to Cart", "User unabel add product to cart");
			
		} catch (Exception | Error e) {

			ExtenantReportUtils.addFailedLog("validating the product add to cart", expectedResult,
					"User unabel to add product to cart", Common.getscreenShotPathforReport("failed to add product"));
			// ExtenantReportUtils.addFailedLog("User click check out button",
			// "User unabel click the checkout button",
			// Common.getscreenShotPathforReport("check out miniCart"));
			e.printStackTrace();
			Assert.fail();

		}
	}

	public void CheckOutPaypal(String dataSet) {
		String expectedResult = "it should land on the checkout intermediate page";
		String url=automation_properties.getInstance().getProperty(automation_properties.BASEURL);
		
		try {
			Thread.sleep(3000);
			Common.clickElement("xpath", "//a[@aria-label='minicart']");
			Thread.sleep(3000);
			Common.scrollIntoView("id", "top-cart-btn-checkout");
			ExtenantReportUtils.addPassLog("validating the product miniCart", expectedResult,
					"User click the minicart button", Common.getscreenShotPathforReport("clickminiCart"));
		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("validating the product miniCart", expectedResult,
					"User unabel click the minicart button", Common.getscreenShotPathforReport("clickminiCart"));
			// ExtenantReportUtils.addFailedLog("User click check out button",
			// "User unabel click the checkout button",
			// Common.getscreenShotPathforReport("check out miniCart"));
			Assert.fail();
		}
		try {
//			Common.switchFrames("xpath", "//iframe[contains(@class,'zoid-component-frame')]");
			Common.switchFrames("xpath", "//iframe[contains(@class,'component-frame visible')]");
			Sync.scrollDownToView("xpath", "//div[@class='paypal-button-label-container']");
			Sync.waitElementClickable(30, By.xpath("//div[@class='paypal-button-label-container']"));
			Common.mouseOverClick("xpath", "//div[@class='paypal-button-label-container']");
			Thread.sleep(8000);
			Common.switchToDefault();
			Thread.sleep(5000);
			Common.switchWindows();
			int size = Common.findElements("id", "acceptAllButton").size();
			if (size > 0) {
				Common.clickElement("id", "acceptAllButton");
			}
	
			if(!url.contains("stg")& !url.contains("dev")){
				
				int sizeofelement=Common.findElements("id", "email").size();
				Common.assertionCheckwithReport(sizeofelement > 0, "verifying the paypal payment ", expectedResult,"open paypal site window", "faild to open paypal account");
			}
			else{
			Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
			int sizeofbutton = Common.findElements("xpath", "//button[@id='btnNext']").size();
			if (sizeofbutton > 0) {
				Common.clickElement("xpath", "//button[@id='btnNext']");
			}
			Common.textBoxInput("id", "password", data.get(dataSet).get("Password"));
			int sizeemail = Common.findElements("id", "email").size();
			Common.assertionCheckwithReport(sizeemail > 0, "verifying the paypal payment ", expectedResult,
					"open paypal site window", "faild to open paypal account");
			Common.clickElement("id", "btnLogin");
			Thread.sleep(5000);
			Common.actionsKeyPress(Keys.END);
			Thread.sleep(8000);
			
			int buttonsize=Common.findElements("id", "payment-submit-btn").size();
			
			if(buttonsize>0){
				Common.clickElement("id", "payment-submit-btn");
				//Common.clickElement("xpath", "//div[@class='paypal-button-label-container']");
			}
			else{
			Common.clickElement("id", "confirmButtonTop");
			Thread.sleep(8000);
			}
			Common.switchToFirstTab();
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the paypal payment ", expectedResult,
					"User failed to proceed with paypal payment", Common.getscreenShotPathforReport(expectedResult));
			Assert.fail();
		}
		if(!url.contains("stg")& !url.contains("dev")){
			
			int sizeofelement=Common.findElements("id", "email").size();
			Common.assertionCheckwithReport(sizeofelement > 0, "verifying the paypal payment ", expectedResult,"open paypal site window", "faild to open paypal account");
		}
		else{
		try {
			Thread.sleep(8000);
			expectedResult = "select the shipping metho";
			//Common.scrollIntoView("xpath", "//select[@id='shipping-method']");
			//Thread.sleep(4000);
			// Common.clickElementStale("xpath","//select[@id='shipping-method']");
			Common.dropdown("xpath", "//select[@id='shipping-method']", Common.SelectBy.VALUE, "tablerate_bestway");
			ExtenantReportUtils.addPassLog("validating shipping methoad", expectedResult,
					"user select the shipping method", Common.getscreenShotPathforReport("shippingmethodselect"));
		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("validating shipping methoad", expectedResult,
					"User unabel to select shipping methoad",
					Common.getscreenShotPathforReport("faieldtoselectShippingmethod"));
			Assert.fail();
		}
		try {
			Thread.sleep(4000);
			expectedResult = "click the place order";
			Sync.waitPageLoad();
			Sync.waitElementClickable("id", "review-button");
			Common.scrollIntoView("id", "review-button");
			Common.clickElement("id", "review-button");
		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("validating place order", expectedResult,
					"User click the place order button", Common.getscreenShotPathforReport("placeorderbutton"));
			Assert.fail();
		}
		try {
			Sync.waitPageLoad();
			Sync.waitPresenceOfElementLocated("xpath", "//h1[@class='checkout-success-title']");
			String sucessMessage = Common.getText("xpath", "//h1[@class='checkout-success-title']").trim();
			Assert.assertEquals(sucessMessage, "Your order has been received", "Sucess message validations");
			expectedResult = "Verify order confirmation number which was dynamically generated";
			Common.assertionCheckwithReport(sucessMessage.equals("Your order has been received"),
					"Order Placed successfull", expectedResult, "faild to place order");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the product confirmation", expectedResult,
					"User failed to proceed paypal detiles", Common.getscreenShotPathforReport("faieldmessagepaypal"));
			Assert.fail();
		}
		}
	}

	public void checkOut() throws Exception {
		String expectedResult = "it should land on the checkout intermediate page";

		try {
            
			Thread.sleep(6000);
			Common.clickElement("xpath", "//a[@aria-label='minicart']");
			Thread.sleep(6000);

			int size = Common.findElements("id", "top-cart-btn-checkout").size();

			ExtenantReportUtils.addPassLog("validating the product checkout", expectedResult,
					"User land Check out paga and click checkout button",
					Common.getscreenShotPathforReport("check out miniCart"));
			Common.clickElement("id", "top-cart-btn-checkout");

			
		} catch (Exception | Error e) {

			ExtenantReportUtils.addFailedLog("validating the product checkout", expectedResult,
					"User unabel click the checkout button", Common.getscreenShotPathforReport("check out miniCart"));
			
			Assert.fail();

		}

	}

	public void checkOutCart() throws Exception {
		try {
			checkOut();
		} catch (Exception e) {
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

	public void closeFreeGift() throws Exception {
		for (int i = 0; i < 10; i++) {
			List<WebElement> webelemts = Common.findElements("xpath", "//div[@id='checkout-loader']");
			Thread.sleep(1000);
			int siz = webelemts.size();
			if (siz == 0) {
				int size = Common.findElements("xpath", "//button[contains(@class,'ampromo-close')]").size();
				if (size != 0) {
					Common.clickElement("xpath", "//button[contains(@class,'ampromo-close')]");
					break;
				}

				break;
			}
		}
	}

	public HashMap<String, String> addDeliveryAddress_registerUser(String dataSet) throws Exception {
		
		HashMap<String,String> Shippingaddress=new HashMap<String,String>();

		
		String expectedResult = "shipping address is entering in the fields";
        int size = Common.findElements(By.xpath("//span[contains(text(),'Add New Address')]")).size();
		if (size > 0) {
        	try { 
				Common.clickElement("xpath", "//span[contains(text(),'Add New Address')]");
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",data.get(dataSet).get("FirstName"));
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']",	data.get(dataSet).get("LastName"));
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']",data.get(dataSet).get("Street"));
				Thread.sleep(2000);
				Common.actionsKeyPress(Keys.SPACE);
				Thread.sleep(3000);
			/*	try {
					Common.clickElement("xpath",
							"//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
				} catch (Exception e) {
					Common.actionsKeyPress(Keys.BACK_SPACE);
					Thread.sleep(1000);
					Common.actionsKeyPress(Keys.SPACE);
					Common.clickElement("xpath",
							"//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
				}
				if (data.get(dataSet).get("StreetLine2") != null) {
					Common.textBoxInput("name", "street[1]", data.get(dataSet).get("Street"));
				}
				if (data.get(dataSet).get("StreetLine3") != null) {
					Common.textBoxInput("name", "street[2]", data.get(dataSet).get("Street"));
				}*/
				
				
				Common.dropdown("xpath", "//select[@name='region_id']", Common.SelectBy.TEXT,data.get(dataSet).get("Region"));
				Thread.sleep(5000);
				String Shippingvalue = Common.findElement("xpath", "//select[@name='region_id']").getAttribute("value");
				String Shippingstate = Common.findElement("xpath", "//select[@name='region_id']//option[@value='" + Shippingvalue + "']").getText();

				Shippingaddress.put("Shippingstate", Shippingstate);
				System.out.println(Shippingstate);

				Common.actionsKeyPress(Keys.PAGE_DOWN);
				Thread.sleep(3000);
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='city']",
						data.get(dataSet).get("City"));
				// Common.mouseOverClick("name", "region_id");
				try {
					Common.dropdown("xpath", "//form[@id='co-shipping-form']//select[@name='region_id']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
				} catch (ElementClickInterceptedException e) {
					// TODO: handle exception
					Thread.sleep(3000);
					Common.dropdown("xpath", "//form[@id='co-shipping-form']//select[@name='region_id']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
				}
				Thread.sleep(2000);
				Common.textBoxInputClear("xpath", "//form[@id='co-shipping-form']//input[@name='postcode']");
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='postcode']",
						data.get(dataSet).get("postcode"));
				String ShippingZip = Common.findElement("name", "postcode").getAttribute("value");
				System.out.println("*****" + ShippingZip + "*******");
				Shippingaddress.put("ShippingZip", ShippingZip);
				
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='telephone']",
						data.get(dataSet).get("phone"));

				ExtenantReportUtils.addPassLog("validating the shipping address", expectedResult,
						"user add the shipping address",
						Common.getscreenShotPathforReport("faield to add shipping address"));

			
                Common.clickElement("xpath", "//div[@id='opc-new-shipping-address']//following::button[1]");

				int sizeerrormessage = Common.findElements("xpath", "//span[contains(text(),'This is a required field')]").size();

				Common.assertionCheckwithReport(sizeerrormessage <= 0, "verifying shipping addres filling ",
						"user will fill the all the shipping", "user fill the shiping address click save button",
						"faield to add new shipping address");
				

				Common.clickElement("xpath", "//input[@id='label_method_bestway']");
				Common.clickElement("xpath", "//button[@data-ac-test='form-shipping-address_action_submit']");
				
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
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",
						data.get(dataSet).get("FirstName"));
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
					Common.clickElement("xpath",
							"//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
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
				
				String ShippingZip = Common.findElement("name", "postcode").getAttribute("value");
				System.out.println("*****" + ShippingZip + "*******");
				Shippingaddress.put("ShippingZip", ShippingZip);
				
				Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));

				Sync.waitElementClickable("xpath", "//span[contains(text(),'Continue To Payment')]");
				Common.clickElement("xpath", "//span[contains(text(),'Continue To Payment')]");
                int errorsize=Common.findElements("xpath", "//div[@class='field-error']").size();
				Common.assertionCheckwithReport(errorsize>0, "verifying shipping addres filling ", expectedResult, "user enter the shipping address", "mandatory data");			
				//ExtenantReportUtils.addPassLog("verifying shipping addres filling ", expectedResult,"user enter the shipping address ",
				//Common.getscreenShotPathforReport("fill the shipping address first time"));

				//Common.findElements("xpath", "").size();
				expectedResult = "shipping address is filled in to the fields";
				Common.clickElement("xpath", "//button[@data-ac-test='form-shipping-address_action_submit']");
				Thread.sleep(3000);

			} catch (Exception | Error e) {
				e.printStackTrace();

				ExtenantReportUtils.addFailedLog("validating adding  address", expectedResult,
						"User unabel add shipping address",
						Common.getscreenShotPathforReport("shipping address faield"));
				
				Assert.fail();

			}
		}
		return Shippingaddress;

		
	}
	

	public void addDeliveryAddress(String dataSet) throws Exception {
		try {
			Sync.waitElementVisible("id", "customer-email");
			Common.textBoxInput("id", "customer-email", data.get(dataSet).get("Email"));
		} catch (NoSuchElementException e) {
			checkOut();
			Common.textBoxInput("id", "customer-email-address", data.get(dataSet).get("Email"));

		}
		String expectedResult = "email field will have email address";
		try {
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",data.get(dataSet).get("FirstName"));
            int size = Common.findElements("id", "customer-email").size();
            Common.assertionCheckwithReport(size > 0, "validating the email address field", expectedResult,"Filled Email address", "unabel to fill the email address");
            Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']",data.get(dataSet).get("LastName"));
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']",data.get(dataSet).get("Street"));
			String Text=Common.getText("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']");
			
			

		/*	try {
				Common.clickElement("xpath", "//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
			} catch (Exception e) {
				Common.actionsKeyPress(Keys.BACK_SPACE);
				Thread.sleep(1000);
				Common.actionsKeyPress(Keys.SPACE);
				Common.clickElement("xpath", "//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
			}
			if (data.get(dataSet).get("StreetLine2") != null) {
				Common.textBoxInput("name", "street[1]", data.get(dataSet).get("Street"));
				
				String text=Common.getText("name","street[1]");
				System.out.println(text);
				
			}
			if (data.get(dataSet).get("StreetLine3") != null) {
				Common.textBoxInput("name", "street[2]", data.get(dataSet).get("Street"));
			}*/
			Sync.waitPageLoad();
			Thread.sleep(5000);
			Common.findElement("xpath", "//form[@id='co-shipping-form']//input[@name='city']").clear();
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='city']",data.get(dataSet).get("City"));
			System.out.println(data.get(dataSet).get("City"));
			
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(3000);
			try {
				Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			} catch (ElementClickInterceptedException e) {
				Thread.sleep(3000);
				Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			}
			Thread.sleep(2000);
			Common.textBoxInputClear("name", "postcode");
			Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
			Thread.sleep(5000);
			
			Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));

		}

		catch (Exception | Error e) {
     e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating shipping address",
					"shipping address is filled in to the fields", "user faield to fill the shipping address",
					Common.getscreenShotPathforReport("shipingaddressfaield"));
			// ExtenantReportUtils.addFailedLog("User click check out button",
			// "User unabel click the checkout button",
			// Common.getscreenShotPathforReport("check out miniCart"));
			Assert.fail();

		}
		Thread.sleep(5000);
		int size=Common.findElements("xpath", "//input[@id='label_method_bestway']").size();
		if(size>0){
			Common.clickElement("xpath", "//input[@id='label_method_bestway']");
		}

		expectedResult = "shipping address is filled in to the fields";
		Common.clickElement("xpath", "//button[@data-ac-test='form-shipping-address_action_submit']");

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

		// Common.assertionCheckwithReport(errorsize<=0,"enter the shipping
		// address in to the fields without skipping any mandatory fields",
		// expectedResult, "Filled the shipping address", "failed to add a
		// address");
		// Common.assertionCheckwithReport(errorsize<=0, "Filled the shipping
		// address", expectedResult, "Missing the shipping address");
		Thread.sleep(3000);
	}

	public void updtePayementcrditcard_WithInvalidData(String dataSet) throws Exception {
		addPaymentDetails_Invalid(dataSet);
		int placeordercount = Common.findElements("xpath", "//span[text()='Place Order']").size();
		if (placeordercount > 1) {
			
			  Common.clickElement("xpath", "//span[text()='Place Order']");
			// stage its working Common.clickElement("xpath", "//button[@title='Place Order']");
			  ////span[text()='Place Order']
		}
		ExtenantReportUtils.addPassLog("verifying the product confirmation",
				"It redirects to order confirmation page", "User failed to proceed CreditCard information",
				Common.getscreenShotPathforReport("faieldmessage"));
	}
	
	
	public void addPaymentDetails_Invalid(String dataSet) throws Exception
	{
	//Common.switchFrames("xpath", "//iframe[@id='paymetric_xisecure_frame']");
	//Common.switchFrames("xpath", "//iframe[@id='paymetric_xisecure_frame']");
	    Thread.sleep(4000);
	               String expectedResult="land on the payment section";
	           try{  
	        	   //label[@for='paymetric']
	        	 //label[@for='ime_paymetrictokenize']
	               Sync.waitElementClickable("xpath", "//label[@for='ime_paymetrictokenize']");
	int sizes=Common.findElements("xpath", "//label[@for='ime_paymetrictokenize']").size();
	   Common.assertionCheckwithReport(sizes>0, "Successfully land on the payment section", expectedResult,"User unabel to land on paymentpage");
	Common.clickElement("xpath", "//label[@for='ime_paymetrictokenize']");
	               //Common.clickElement("xpath", "//label[@for='paymetric']");
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
    Common.clickElement("xpath", "//span[text()='Place Order']");
    		//+ "//button[@title='Place Order']");

    		//+ "//span[text()='Place Order']");
	
    //stage working //Common.clickElement("xpath", "//button[@title='Place Order']");
	           }
	           catch(Exception |Error e) {
	        	   e.printStackTrace();
	   ExtenantReportUtils.addFailedLog("validating the Credit Card infromation", expectedResult, "faield to fill the Credit Card infromation", Common.getscreenShotPathforReport("Cardinfromationfail"));
	    //ExtenantReportUtils.addFailedLog("User click check out button", "User unabel click the checkout button", Common.getscreenShotPathforReport("check out miniCart"));
	   Assert.fail();
	           }
	   }

	

	public void addNewAddress_ShipPage(String dataSet) throws Exception {

		Common.clickElement("xpath", "//span[text()='Add New Address']");
		Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",
				data.get(dataSet).get("FirstName"));
		Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']",
				data.get(dataSet).get("LastName"));
		Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']",
				data.get(dataSet).get("Street"));
		if (data.get(dataSet).get("StreetLine2") != null) {
			Common.textBoxInput("name", "street[1]", data.get(dataSet).get("Street"));
		}
		if (data.get(dataSet).get("StreetLine3") != null) {
			Common.textBoxInput("name", "street[2]", data.get(dataSet).get("Street"));
		}
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Thread.sleep(3000);
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

		Common.clickElement("xpath", "//span[text()='Save Address']");
		Thread.sleep(3000);
	}

	public void addPaymentDetails(String dataSet) throws Exception {
		HashMap<String,String> Paymentmethod=new HashMap<String,String>();

		Thread.sleep(4000);
		String expectedResult = "land on the payment section";
		//Common.refreshpage();
	
		try {
			//Sync.waitElementClickable("xpath", "//label[@for='paymetric']");
			Sync.waitElementClickable("xpath", "//label[@for='ime_paymetrictokenize']");
			int sizes=Common.findElements("xpath", "//label[@for='ime_paymetrictokenize']").size();

		 Common.assertionCheckwithReport(sizes>0, "Successfully land on the payment section", expectedResult,"User unabel to land opaymentpage");
			Common.clickElement("xpath", "//label[@for='ime_paymetrictokenize']");
			
			//Common.clickElement("xpath","//label[@for='paymetric']");  .getAttribute("value");
			
			Thread.sleep(2000);
			Common.switchFrames("id", "paymetric_xisecure_frame");
			Common.dropdown("xpath", "//select[@id='c-ct']", Common.SelectBy.TEXT, data.get(dataSet).get("cardType"));
			
					
			Common.textBoxInput("id", "c-cardnumber", data.get(dataSet).get("cardNumber"));
			Common.dropdown("xpath", "//select[@id='c-exmth']", Common.SelectBy.TEXT,
					data.get(dataSet).get("ExpMonth"));
			Common.dropdown("xpath", "//select[@id='c-exyr']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpYear"));
			Common.textBoxInput("id", "c-cvv", data.get(dataSet).get("cvv"));
			Thread.sleep(2000);

			Common.actionsKeyPress(Keys.ARROW_DOWN);
			Common.switchToDefault();
			Thread.sleep(1000);
			Common.clickElement("xpath", "//span[text()='Place Order']");
			
			//Common.clickElement("xpath", "//button[@title='Place Order']");

		}

		catch (Exception | Error e) {
			e.printStackTrace();
			

			ExtenantReportUtils.addFailedLog("validating the Credit Card infromation", expectedResult,
					"faield  to fill the Credit Card infromation",
					Common.getscreenShotPathforReport("Cardinfromationfail"));
			// ExtenantReportUtils.addFailedLog("User click check out button",
			// "User unabel click the checkout button",
			// Common.getscreenShotPathforReport("check out miniCart"));
			Assert.fail();
          }

		expectedResult = "credit card fields are filled with the data";
		String errorTexts = Common.findElement("xpath", "//div[contains(@id,'error')]").getText();
		
	//int errormessage=Common.findElements("xpath", "//div[contains(@id,'error')]").size();
		
		
	/*	Common.assertionCheckwithReport(errormessage<=0, "validating the credit card information with valid data",
			expectedResult, "Filled the Card detiles", "missing field data it showinng error");
*/
		
		Common.assertionCheckwithReport(errorTexts.isEmpty(), "validating the credit card information with valid data",
			expectedResult, "Filled the Card detiles", "missing field data it showinng error");
	}

	public String updatePaymentAndSubmitOrder(String dataSet) throws Exception {
		
		String order="";
		addPaymentDetails(dataSet);
		String expectedResult = "It redirects to order confirmation page";

		if (Common.findElements("xpath", "//div[@class='message message-error']").size() > 0) {
			addPaymentDetails(dataSet);
		}
		
		Thread.sleep(3000);
		int placeordercount = Common.findElements("xpath", "//span[text()='Place Order']").size();
		//Juttriles code //("xpath", "//span[text()='Place Order']")
		////button[@title='Place Order']   stage
		if (placeordercount > 1) {
			Common.clickElement("xpath", "//span[text()='Place Order']");
		}

		String url=automation_properties.getInstance().getProperty(automation_properties.BASEURL);
		
		if(!url.contains("stg")){
			//ExtenantReportUtils.addPassLog(description, expectedResult, actualResult, Common.getscreenShotPathforReport(expectedResult));
			/*int sizeofelement=Common.findElements("id", "email").size();
			Common.assertionCheckwithReport(sizeofelement > 0, "verifying the paypal payment ", expectedResult,"open paypal site window", "faild to open paypal account");*/
		}
		
		else{
			try{
		String sucessMessage = Common.getText("xpath", "//h1[@class='checkout-success-title']").trim();
		// Assert.assertEquals(sucessMessage, "Your order has been
		// received","Sucess message validations");
		int sizes = Common.findElements("xpath", "//h1[@class='checkout-success-title']").size();
		Common.assertionCheckwithReport(sucessMessage.equals("Your order has been received"),
				"verifying the product confirmation", expectedResult,
				"Successfully It redirects to order confirmation page Order Placed",
				"User unabel to go orderconformation page");
		
		if(Common.findElements("xpath", "//div[@class='checkout-success']/p/span").size()>0) {
			order=Common.getText("xpath", "//div[@class='checkout-success']/p/span");
			System.out.println(order);
		}
		
		
		
		if(Common.findElements("xpath","//a[@class='order-number']/strong").size()>0) {
			order=	Common.getText("xpath", "//a[@class='order-number']/strong");
			System.out.println(order);
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
	public String payPal_Payment(String dataSet) throws Exception {

		String order="";

		String expectedResult = "It should open paypal site window.";
		try {
			Thread.sleep(3000);
			Sync.waitElementPresent("xpath", "//input[@id='paypal_express']");
			Thread.sleep(2000);
			Common.clickElement("xpath", "//input[@id='paypal_express']");
			Thread.sleep(5000);
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Common.switchFrames("xpath", "//iframe[contains(@class,'component-frame visible')]");
			//Thread.sleep(5000);
			//Common.refreshpage();
			Thread.sleep(8000);
			Sync.waitElementClickable("xpath", "//div[@class='paypal-button-label-container']");
			Common.clickElement("xpath", "//div[@class='paypal-button-label-container']");
			Common.switchToDefault();
			Thread.sleep(5000);
			Common.switchWindows();
			int size = Common.findElements("id", "acceptAllButton").size();
			if (size > 0) {

				Common.clickElement("id", "acceptAllButton");

			}
		}
			catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying the paypal payment ", expectedResult,
						"User failed to proceed with paypal payment", Common.getscreenShotPathforReport(expectedResult));
				Assert.fail();
			}
			String url=automation_properties.getInstance().getProperty(automation_properties.BASEURL);
			
			if(!url.contains("stg") &!url.contains("dev")){
				
				int sizeofelement=Common.findElements("id", "email").size();
				Common.assertionCheckwithReport(sizeofelement > 0, "verifying the paypal payment ", expectedResult,"open paypal site window", "faild to open paypal account");
			}
			else
			{
			Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
			Common.textBoxInput("id", "password", data.get(dataSet).get("Password"));
			int sizeemail = Common.findElements("id", "email").size();

			Common.assertionCheckwithReport(sizeemail > 0, "verifying the paypal payment ", expectedResult,"open paypal site window", "faild to open paypal account");
			
			try{
			Common.clickElement("id", "btnLogin");
			Thread.sleep(5000);
			Common.actionsKeyPress(Keys.END);
			Thread.sleep(5000);
			Common.clickElement("id", "payment-submit-btn");
			Thread.sleep(8000);
			Common.switchToFirstTab();
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the paypal payment ", expectedResult,
					"User failed to proceed with paypal payment", Common.getscreenShotPathforReport(expectedResult));
			Assert.fail();
		}

			
			int n=Common.findElements("xpath", "//div[@class='checkout-success']/p[1]/span").size();
			if(n>0) {
				 order=Common.getText("xpath", "//div[@class='checkout-success']/p[1]/span");
				 System.out.println(order);
			}
			else {
				order=Common.getText("xpath", "//a[@class='order-number']/strong");	
				 System.out.println(order);
			}
			
		String sucessMessage = Common.getText("xpath", "//h1[@class='checkout-success-title']").trim();
		Assert.assertEquals(sucessMessage, "Your order has been received", "Sucess message validations");
		expectedResult = "Verify order confirmation number which was dynamically generated";
		Common.assertionCheckwithReport(sucessMessage.equals("Your order has been received"),"Order Placed successfull", expectedResult, "faild to place order");

	}
			return order;
	}

	public void clickWarranty() throws Exception {

		// report.addPassLog(expectedResult,"Successfully landed on the home
		// page",Common.getscreenShotPathforReport("Successfully landed on the
		// home page"));

		String expectedResult = "User should land on the home page";
	     Thread.sleep(5000);
		int size = Common.findElements("xpath", "//a[@class='logo']").size();
		Common.assertionCheckwithReport(size > 0, "Successfully landed on the home page", expectedResult,
				"User unabel to land on home page");
		try {
			Common.actionsKeyPress(Keys.END);
			Sync.waitElementPresent("xpath", "//a[text()='Warranty']");
			Common.clickElement("xpath", "//a[text()='Warranty']");
			String currenturl = Common.getCurrentURL();
			Common.assertionCheckwithReport(currenturl.contains("product_warranty"), "verifying warranty page",
					"It should land  warranty page", "Successfully land on warranty page",
					"User unabel on warranty page");
			// (currenturl.contains("product_warranty"), "Successfully land on
			// warranty page", expectedResult,"User unabel on warranty page");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying warranty page", "It should land  warranty page",
					"User failed to click warrenty option", Common.getscreenShotPathforReport("warrenty button"));
			Assert.fail();
		}

	}

	public void submitWarranty(String dataSet) throws Exception {
		String expectedResult = "It should land  warranty page ";

		Common.actionsKeyPress(Keys.END);
		clickWarranty();
		Thread.sleep(5000);
		try {
			Sync.waitElementPresent("xpath", "//div[@class='wararnty-cta']/a");
			Common.clickElement("xpath", "//div[@class='wararnty-cta']/a");
			Sync.waitElementPresent("id", "email");
			expectedResult = "User is redirected to login page";
			int sizeemail = Common.findElements("id", "email").size();
			Common.assertionCheckwithReport(sizeemail > 0, "verifying login page", "user redirected to login page",
					"Successfully User is redirected to login page", "User unabel redirected to login page");
			// (sizeemail>0, "Successfully User is redirected to login page",
			// expectedResult,"User unabel redirected to login page");
		}

		catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("verifying login page", "user redirected to login page",
					"User failed to redirected to login pag", Common.getscreenShotPathforReport("warrenty LoginPage"));
			Assert.fail();
		}

		try {

			Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
			Sync.waitElementPresent("id", "pass");
			Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
			// report.addPassLog("Enter the login Infromation
			// ",Common.getscreenShotPathforReport("Loginto application"));
			Sync.waitElementPresent("xpath", "//button[@class='login-page-submit-action']");
			Common.clickElement("xpath", "//button[@class='login-page-submit-action']");
			ExtenantReportUtils.addPassLog("verifying login page", "User is login and able to view warranty form",
					"user Successfully login", Common.getscreenShotPathforReport("faield tologin warranty"));
		}

		catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("verifying login page with credentials ",
					"User is logged in and able to view warranty form", "User failed to login or view warranty form",
					Common.getscreenShotPathforReport("warranty form"));
			Assert.fail();
		}

		try {
			Thread.sleep(6000);
			Sync.waitElementPresent("xpath", "//iframe[contains(@src,'warranty')]");
			Common.switchFrames("xpath", "//iframe[contains(@src,'warranty')]");
			int warrantyfistname = Common.findElements("xpath", "//input[contains(@name,'Contact.Name.First')]").size();
			expectedResult = "User is logged in and able to view warranty form";
			Common.assertionCheckwithReport(warrantyfistname > 0, "verifying warrenty from", expectedResult,
					"Successfully login user and able to view warranty form", "User failed open the warranty form");
			// (warrantyfistname>0, "Successfully login user and able to view
			// warranty form ", expectedResult,"User unabel see the warranty
			// form");
			// ExtenantReportUtils.addPassLog("verifying login page with
			// credentials", "User is logged in and able to view warranty form",
			// "User successfully open the warranty form", "User failed open the
			// warranty form");
		}

		catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("verifying warrenty from",
					"User is logged in and able to view warranty form", "User failed to view  warranty form",
					Common.getscreenShotPathforReport("warranty form view"));
			Assert.fail();
		}

		Thread.sleep(6000);
		// Submit a Warranty Claim form

		try {
			Sync.waitElementPresent("xpath", "//input[contains(@name,'Contact.Name.First')]");
			Common.textBoxInput("xpath", "//input[contains(@name,'Contact.Name.First')]",
					data.get(dataSet).get("FirstName"));
		} catch (Exception e) {
			Common.textBoxInput("xpath", "//input[contains(@name,'Contact.Name.First')]",
					data.get(dataSet).get("FirstName"));
		}
		try {
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
			Common.dropdown("xpath", "//select[contains(@id,'Country')]", SelectBy.TEXT,
					data.get(dataSet).get("Country"));

			Sync.waitElementPresent("xpath", "//select[contains(@id,'StateOrProvince')]");
			Common.clickElement(By.xpath("//select[contains(@id,'StateOrProvince')]"));
			Thread.sleep(5000);
			Common.dropdown("xpath", "//select[contains(@id,'StateOrProvince')]", SelectBy.TEXT,
					data.get(dataSet).get("State"));

			Sync.waitElementPresent("xpath", "//input[contains(@id,'PostalCode')]");
			Common.textBoxInput("xpath", "//input[contains(@id,'PostalCode')]", data.get(dataSet).get("postcode"));

			Sync.waitElementPresent("xpath", "//input[contains(@id,'MOBILE')]");
			Common.textBoxInput("xpath", "//input[contains(@id,'MOBILE')]", data.get(dataSet).get("phone"));

			Thread.sleep(3000);
			// Common.actionsKeyPress(Keys.PAGE_DOWN);

			Sync.waitElementPresent("xpath", "//i[@class='all_button_arrow fa fa-arrow-down']");
			// Common.clickElement(By.xpath("//span[text()='View All']"));
			// Common.clickElement(By.xpath("//i[@class='all_button_arrow fa
			// fa-arrow-down']"));
			Common.javascriptclickElement("xpath", "//i[@class='all_button_arrow fa fa-arrow-down']");
			// Common.mouseOverClick("xpath", "//i[@class='all_button_arrow fa
			// fa-arrow-down']");
			// Common.clickElement("xpath","//i[@class='all_button_arrow fa
			// fa-arrow-down']");
			// Common.mouseOverClick("xpath","//span[text()='View All']");
			Thread.sleep(5000);
			List<WebElement> Productselemts = Common.findElements("xpath", "//div[contains(@class,'nameset')]");

			for (int i = 0; i < Productselemts.size(); i++) {

				if (Productselemts.get(i).getAttribute("title").equals(data.get(dataSet).get("Products"))) {
					Productselemts.get(i).click();
					break;
				}

			}

			// input[contains(@class,'product_quantity')]

			Sync.waitElementPresent("xpath", "//input[contains(@class,'product_quantity')]");
			Common.textBoxInput("xpath", "//input[contains(@class,'product_quantity')]",
					data.get(dataSet).get("ProductQuantity"));

			Sync.waitElementPresent("xpath", "//input[contains(@class,'problem_description')]");
			Common.textBoxInput("xpath", "//input[contains(@class,'problem_description')]",
					data.get(dataSet).get("ProblemDescription"));

			Sync.waitElementPresent("xpath", "//input[contains(@id,'FileInput')]");
			String path = System.getProperty("user.dir")
					+ ("\\src\\test\\resources\\TestData\\Hydroflask\\TestScreen.jpg");
			try {
				Common.fileUpLoad("xpath", "//input[contains(@id,'FileInput')]", path);
			} catch (Exception e) {
				// ExtenantReportUtils.addFailedLog("warrenty from file upload
				// ", "warrenty from unabel to uppload file",
				// Common.getscreenShotPathforReport("warrenty from
				// uploadfile"));
				// Assert.fail();
			}
			// expectedResult="No validation errors";
			// report.addPassLog(expectedResult,"Enter the warrenty from
			// infromation with out any validation
			// ",Common.getscreenShotPathforReport("Filling the Warranty from
			// "));
			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "//button[contains(@id,'CustomFormSubmit')]");
			Common.javascriptclickElement("xpath", "//button[contains(@id,'CustomFormSubmit')]");
			// Common.clickElement("xpath",
			// "//button[contains(@id,'CustomFormSubmit')]");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying warranty form field data",
					"warrenty from infromation with out any validation", "warrenty from with invalid data",
					Common.getscreenShotPathforReport("warrenty from invalid data"));
			e.printStackTrace();
			Assert.fail();

		}

		int sizeerrormessage = Common.findElements("xpath", "//div[contains(@id,'ErrorLocation')]").size();
		Common.assertionCheckwithReport(sizeerrormessage > 0, "verifying warranty form field data",
				"Enter the warrenty from infromation with out any validation", "mandatory data missing in the from",
				Common.getscreenShotPathforReport("mandatory data missing in the from"));
		// (sizeerrormessage>0, "Enter the warrenty from infromation with out
		// any validation ", expectedResult, "mandatory data missing in the
		// from");
		Common.actionsKeyPress(Keys.HOME);
		Thread.sleep(6000);

		String sucessMessage = Common.getText("xpath", "//body[@id='rn_BlankBody']//h1").trim();
		// Assert.assertEquals(sucessMessage, "Your warranty request has been
		// submitted!");
		expectedResult = "User gets redirected to confirmation page, it includes a reference number and email is sent to email provided. No validation errors.";
		Common.assertionCheckwithReport(sucessMessage.equals("Your warranty request has been submitted!"),
				"warranty applied  successfull,and redirected to confirmation page", expectedResult,
				"submit the warranty but confirmation page  message missing");
		// report.addPassLog(expectedResult,"warranty applied successfull,and
		// redirected to confirmation
		// page",Common.getscreenShotPathforReport("warranty submitted "));

	}

	public void clickContact() throws Exception {
		String expectedResult = "User should land on the home page";
		Thread.sleep(5000);
		int size = Common.findElements("xpath", "//a[@class='logo']").size();
		Common.assertionCheckwithReport(size > 0, "verifying home page", expectedResult,
				"Successfully landed on the home page", "User unabel to land on home page");
		// (size>0, "Successfully landed on the home page", expectedResult,"User
		// unabel to land on home page");
		Common.actionsKeyPress(Keys.END);
		try {
			Sync.waitElementPresent("xpath", "//a[text()='Contact']");
			Common.clickElement("xpath", "//a[text()='Contact']");
			Thread.sleep(9000);
			String contactpageurl = Common.getCurrentURL();
			expectedResult = "It should land successfully on the explore/contact page";
			Common.assertionCheckwithReport(contactpageurl.contains("contact"), "successfully land to contact page",
					expectedResult, "unabel to load the  contact page");
		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("validating contact us page", expectedResult,
					"unabel to load the contact page", Common.getscreenShotPathforReport("Contact us page link"));
			Assert.fail();

		}
	}

	public void contactUsPage(String dataSet) throws Exception {
		clickContact();
		String expectedResult = "Email us form is visible in tab";

		try {
			/*
			 * String contactpageurl=Common.getCurrentURL(); String
			 * expectedResult="It should land successfully on the explore/contact page"
			 * ;
			 * Common.assertionCheckwithReport(contactpageurl.contains("contact"
			 * ),"successfully land to contact page"
			 * ,expectedResult,"unabel to load the  contact page");
			 */

			for (int i = 0; i < 10; i++) {
				Thread.sleep(5000);

				WebElement activeelemet = Common.findElement("xpath", "//*[@id='HNNEN6W']/div[1]");
				String className = activeelemet.getAttribute("class");
				if (className.contains("active")) {

					ExtenantReportUtils.addPassLog("validating emil us button", "Email us button displayed",
							"Dispalying EmailUs button", Common.getscreenShotPathforReport("EmailUsbutton"));
					// Common.assertionCheckwithReport(contactpageurl.contains("contact"),"successfully
					// land to contact page",expectedResult,"unabel to load the
					// contact page");

					Common.clickElement("xpath", "//*[@id='HNNEN6W']/div[2]/div[2]/div[1]/ul/li[3]/img[2]");
					Common.actionsKeyPress(Keys.PAGE_DOWN);
					break;
				}
			}

			Common.switchFrames("xpath", "//iframe[contains(@src,'custhelp')]");

			// input[contains(@id,'Emails')]

			Sync.waitElementPresent("xpath", "//input[contains(@id,'Emails')]");

			int emailsize = Common.findElements("xpath", "//input[contains(@id,'Emails')]").size();
			Common.assertionCheckwithReport(emailsize > 0, "Email us form is visible in tab", expectedResult,
					"unabel to load the  contacts form");

			Common.textBoxInput("xpath", "//input[contains(@id,'Emails')]", data.get(dataSet).get("Email"));

			Sync.waitElementPresent("xpath", "//input[contains(@id,'First')]");
			Common.textBoxInput("xpath", "//input[contains(@id,'First')]", data.get(dataSet).get("FirstName"));

			Sync.waitElementPresent("xpath", "//input[contains(@id,'Last')]");
			Common.textBoxInput("xpath", "//input[contains(@id,'Last')]", data.get(dataSet).get("LastName"));

			Sync.waitElementPresent("xpath", "//input[contains(@id,'company')]");
			Common.textBoxInput("xpath", "//input[contains(@id,'company')]", data.get(dataSet).get("Company"));

			Sync.waitElementPresent("xpath", "//input[contains(@id,'MOBILE')]");
			Common.textBoxInput("xpath", "//input[contains(@id,'MOBILE')]", data.get(dataSet).get("phone"));

			Sync.waitElementPresent("xpath", "//input[contains(@id,'Street')]");
			Common.textBoxInput("xpath", "//input[contains(@id,'Street')]", data.get(dataSet).get("Street"));

			Sync.waitElementPresent("xpath", "//input[contains(@id,'City')]");
			Common.textBoxInput("xpath", "//input[contains(@id,'City')]", data.get(dataSet).get("City"));

			Sync.waitElementPresent("xpath", "//select[contains(@id,'Country')]");
			Common.dropdown("xpath", "//select[contains(@id,'Country')]", SelectBy.TEXT,
					data.get(dataSet).get("Country"));

			Sync.waitElementPresent("xpath", "//select[contains(@id,'State')]");
			Common.dropdown("xpath", "//select[contains(@id,'State')]", SelectBy.TEXT, data.get(dataSet).get("State"));

			Sync.waitElementPresent("xpath", "//input[contains(@id,'PostalCode')]");
			Common.textBoxInput("xpath", "//input[contains(@id,'PostalCode')]", data.get(dataSet).get("postcode"));

			Sync.waitElementPresent("xpath", "//select[contains(@id,'SelectObject_lvl1')]");
			Common.dropdown("xpath", "//select[contains(@id,'SelectObject_lvl1')]", SelectBy.TEXT,
					data.get(dataSet).get("Howcanwehelp"));

			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "//select[contains(@id,'SelectObject_lvl2')]");
			Common.dropdown("xpath", "//select[contains(@id,'SelectObject_lvl2')]", SelectBy.TEXT,
					data.get(dataSet).get("category"));

			Sync.waitElementPresent("xpath", "//input[contains(@id,'ordernumber')]");
			Common.textBoxInput("xpath", "//input[contains(@id,'ordernumber')]", data.get(dataSet).get("OrderNumber"));

			Sync.waitElementPresent("xpath", "//input[contains(@id,'DateTimeUI') and @type='text']");
			Common.textBoxInput("xpath", "//input[contains(@id,'DateTimeUI') and @type='text']", data.get(dataSet).get("OrderDate"));

			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "//input[contains(@id,'billing_name')]");
			Common.clickElement("xpath", "//input[contains(@id,'billing_name')]");
			Common.textBoxInput("xpath", "//input[contains(@id,'billing_name')]", data.get(dataSet).get("BillName"));
            
			//Sync.waitElementPresent("xpath", "//textarea[contains(@id,'TextInputPlaceholder_94')]");
			Sync.waitElementPresent("xpath" , "//textarea[contains(@id, 'rn_TextInputPlaceholder')]");
			Common.clickElement("xpath" , "//textarea[contains(@id, 'rn_TextInputPlaceholder')]");
			//Common.clickElement("xpath", "//textarea[contains(@id,'TextInputPlaceholder_94')]");
			Common.textBoxInput("xpath", "//textarea[contains(@id, 'rn_TextInputPlaceholder')]",
					data.get(dataSet).get("Question*"));
			Common.clickElement("xpath", "//button[contains(@id,'rn_FormSubmit')]");
			Thread.sleep(7000);
		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying email us from",
					"contact us form data enter without any error message", "Contact us page getting error ",
					Common.getscreenShotPathforReport("Contact us page"));
			Assert.fail();

		}

		Common.actionsKeyPress(Keys.PAGE_UP);
		String Text = Common.getText("xpath", "//div[@class='rn_Container']/h1");
		Assert.assertEquals(Text, "Your question has been submitted!", "Conatct message sucess");
		expectedResult = "User gets confirmation under the same tab. It includes a reference number and email is sent to email provided. No validation errors.";
		Common.assertionCheckwithReport(Text.equals("Your question has been submitted!"),
				"verifying contact us conformation message", expectedResult,
				"User gets confirmation under the same tab", "unabel to load the confirmation form");
		// (Text.equals("Your question has been submitted!"),"User gets
		// confirmation under the same tab",expectedResult,"unabel to load the
		// confirmation form");
	}

	/*
	 * int size= Common.findElements("xpath",
	 * "//*[@id='HNNEN6W']/div[2]/div[2]/div[1]/ul/li[3]/img[2]").size();
	 * System.out.println(size); Sync.waitElementPresent("xpath",
	 * "//li[@data-tab-name='E-mail Us']");
	 * 
	 * Common.clickElement("xpath","//li[@data-tab-name='E-mail Us']");
	 * Common.clickElement("xpath",
	 * "//*[@id='HNNEN6W']/div[2]/div[2]/div[1]/ul/li[3]/img[2]");
	 * 
	 * //*[@id="HNNEN6W"]//ul/li[3]
	 * 
	 * // Common.clickElement("xpath","//li[@data-tab-name='E-mail Us']");
	 * 
	 * report.addPassLog(" navigate to contact page ",Common.
	 * getscreenShotPathforReport("click email Us button "));
	 * //Common.switchFrames("xpath", "//iframe[contains(@src,'custhelp')]"); 1
	 * 
	 * try{ Common.switchFrames("xpath", "//iframe[contains(@src,'custhelp')]");
	 * Sync.waitElementPresent("xpath", "//input[contains(@id,'Emails')]");
	 * Common.textBoxInput("xpath", "//input[contains(@id,'Emails')]",
	 * data.get(dataSet).get("Email")); } catch(Exception e){
	 * Common.switchToDefault();
	 * Common.clickElement("xpath","//li[@data-tab-name='E-mail Us']");
	 * report.addPassLog(" navigate to contact page ",Common.
	 * getscreenShotPathforReport("click email Us button "));
	 * Common.switchFrames("xpath", "//iframe[contains(@src,'custhelp')]"); }
	 * 
	 * 
	 * 
	 * Sync.waitElementPresent("xpath", "//input[contains(@id,'Emails')]");
	 * Common.textBoxInput("xpath", "//input[contains(@id,'Emails')]",
	 * data.get(dataSet).get("Email")); //
	 */

	public void clickProDeal() throws Exception {
		String expectedResult = "User should land on the home page";
		Thread.sleep(5000);
		int size = Common.findElements("xpath", "//a[@class='logo']").size();
		Common.assertionCheckwithReport(size > 0, "verifying home page", expectedResult,
				"Successfully landed on the home page", "User unabel to land on home page");
		// (size>0, "Successfully landed on the home page", expectedResult,"User
		// unabel to land on home page");
		try {
			Common.actionsKeyPress(Keys.END);
			Sync.waitElementPresent("xpath", "//a[text()='Pro Deal']");
			Common.clickElement("xpath", "//a[text()='Pro Deal']");
			try {
				Sync.waitElementPresent("xpath", "//a[@title='Sign in or register']");

			} catch (Exception e) {
				clickProDeal();
				Thread.sleep(3000);
			}
			String prodealname = Common.getCurrentURL();

			expectedResult = "User is redirected to the Apply For Pro Deal page";
			Common.assertionCheckwithReport(prodealname.contains("prodeal"), "verifying Pro Deal page ", expectedResult,
					"Successfully redirected prodeal page", "User unabel to land on prodeal page");

		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("verifying Pro Deal page", "user click prodeal option",
					"user faield to click prodeal option",
					Common.getscreenShotPathforReport("prodeal page click faield"));
			Assert.fail();

		}
	}

	public void ProdelerPage(String dataSet) throws Exception {
		clickProDeal();

		String prodealname = Common.getCurrentURL();
		{
			/*
			 * String
			 * expectedResult="User is redirected to the Apply For Pro Deal page"
			 * ;
			 * Common.assertionCheckwithReport(prodealname.contains("prodeal"),
			 * "Successfully redirected prodeal page",
			 * expectedResult,"User unabel to land on prodeal page");
			 */
			String expectedResult = "User is redirected to login page";

			Common.clickElement("xpath", "//a[@title='Sign in or register']");
			Sync.waitElementPresent("id", "email");
			int sizeemeil = Common.findElements("id", "email").size();
			Common.assertionCheckwithReport(sizeemeil > 0, "Successfully redirected to login page", expectedResult,
					"User unabel to land on login page");

			try {

				Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
				Sync.waitElementPresent("id", "pass");
				Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
				Sync.waitElementPresent("xpath", "//button[@class='login-page-submit-action']");
				Common.clickElement("xpath", "//button[@class='login-page-submit-action']");

				Sync.waitElementPresent("xpath", "//a[contains(@class,'pro-deal-action-primary')]");
				Common.clickElement("xpath", "//a[contains(@class,'pro-deal-action-primary')]");

			} catch (Exception | Error e) {
				ExtenantReportUtils.addFailedLog("ProDeal User login ", "User faield login aplication ",
						Common.getscreenShotPathforReport("prodeal aplication "));
				Assert.fail();

			}

			Thread.sleep(3000);
			Common.switchWindows();
			Thread.sleep(3000);
			expectedResult = "User is redirected to Pro Deal application page";
			try {

				Sync.waitElementPresent("id", "first_name");
				int fistnamesize = Common.findElements("id", "first_name").size();
				Common.assertionCheckwithReport(fistnamesize > 0,
						"Successfully User is redirected to Pro Deal application page", expectedResult,
						"User unabel to redirected to Pro Deal application page");
				Thread.sleep(3000);
				Common.textBoxInput("id", "first_name", data.get(dataSet).get("FirstName"));
				Sync.waitElementPresent("id", "last_name");
				Common.textBoxInput("id", "last_name", data.get(dataSet).get("LastName"));
				Sync.waitElementPresent("id", "association");
				Common.textBoxInput("id", "association", data.get(dataSet).get("Company"));
				Sync.waitElementPresent("id", "association_email");
				Common.textBoxInput("id", "association_email", data.get(dataSet).get("AssociationEmailAddress"));
				Common.actionsKeyPress(Keys.PAGE_DOWN);
				Thread.sleep(6000);
				String path = System.getProperty("user.dir")
						+ ("\\src\\test\\resources\\TestData\\Hydroflask\\TestScreen.jpg");
				// Sync.waitElementInvisible("xpath",
				// "//input[@id='supporting_document']");
				Common.fileUpLoad("xpath", "//input[@id='supporting_document']", path);

				Sync.waitElementPresent("id", "group_id");

				System.out.println(data.get(dataSet).get("GropName"));
				Common.dropdown("xpath", "//select[@id='group_id']", SelectBy.TEXT, data.get(dataSet).get("GropName"));

				Sync.waitElementPresent("id", "comment");
				Common.textBoxInput("id", "comment", data.get(dataSet).get("CommetsHydroflask"));

				Sync.waitElementPresent("xpath", "//button[@title='Submit']");
				Common.clickElement("xpath", "//button[@title='Submit']");

			} catch (Exception | Error e) {
				ExtenantReportUtils.addFailedLog("ProDeal application from filling",
						"User faield to fill the prodeal aplication ",
						Common.getscreenShotPathforReport("prodeal aplication "));
				Assert.fail();

			}

			// String text=Common.getText("xpath",
			// "//div[@class='pro-deal-header']/h4");

			String text = Common.getText("xpath", "//span[@class='base']");
			// Assert.assertEquals(text, "Pro Deal Application Complete", "pro
			// Deal application completed");

			expectedResult = "User gets redirected to confirmation page and email is sent to email provided.";

			Common.assertionCheckwithReport(text.equals("Pro Deal Application Complete"),
					"verifying Pro Deal conformation", expectedResult, "User redirected to confirmation page",
					"User unabel to redirected to confirmation page");

		}

	}

	public void stayIntouch(String dataSet) throws Exception {

		String expectedResult = "User should land on the home page";
		try {
			Thread.sleep(5000);
			int size = Common.findElements("xpath", "//a[@class='logo']").size();
			Common.assertionCheckwithReport(size > 0, "Successfully landed on the home page", expectedResult,
					"User unabel to land on home page");

			Common.actionsKeyPress(Keys.END);
			Thread.sleep(5000);

			Sync.waitElementPresent("id", "newsletter");
			Common.clickElement("id", "newsletter");

			String email = Common.genrateRandomEmail(data.get(dataSet).get("Email"));

			Common.textBoxInput("id", "newsletter", email);
			Thread.sleep(5000);
			Common.clickElement("xpath", "//button[contains(@class,'action subscribe primary')]");
			Thread.sleep(5000);
			String Text = Common.getText("xpath", "//input[@id='newsletter']//following::div[1]");
			expectedResult = "User gets confirmation message that it was submitted";
			ExtenantReportUtils.addPassLog("verifying newsletter subscription",
					"User get confirmation message if new email if it used mail it showing error message ", Text,
					Common.getscreenShotPathforReport("NewsLetter Subscrptionsuccess"));

		} catch (Exception | Error e) {
			
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying newsletter subscription", "NewsLetter Subscrption success",
					"User faield to subscrption for newLetter  ",
					Common.getscreenShotPathforReport("NewsLetter Subscrptionsuccess"));
			Assert.fail();
     	}
		// Text=Common.getTextBoxInput("xpath",
		// "//input[@id='newsletter']//following::div[1]");

		// Common.assertionCheckwithReport(size>0, "confirmation message that it
		// was submitted", expectedResult,Text);
		// .addPassLog(expectedResult,"confirmation message that it was
		// submitted",Common.getscreenShotPathforReport(Text));
	}
	// Common.clickElement("xpath", "//div[contains(@class,'sign-button')]");
	// Thread.sleep(5000);
	// System.out.println(Common.getText("class", "mage-success"));

	public void forgetpassword(String dataSet) throws Exception {
		navigateMyAccount();
		String expectedResult = "Forgot password pop up is displayed to customer";
		try {
			Sync.waitElementPresent("xpath", "//a[contains(@class,'forgot-password')]");
			Common.clickElement("xpath", "//a[contains(@class,'forgot-password')]");
			Sync.waitElementPresent("xpath", "//input[contains(@id,'forgot-password-email')]");
			int size = Common.findElements("xpath", "//input[contains(@id,'forgot-password-email')]").size();
			Common.assertionCheckwithReport(size > 0, "verifying forgetpassword option", expectedResult,
					"Successfully Forgot password pop up is displayed to customer",
					"User faield to get  Forgot password pop");
			// (size>0, "Successfully Forgot password pop up is displayed to
			// customer", expectedResult,"User faield to get Forgot password
			// pop");

		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("verifying forgetpassword option", "clcik the forget password option",
					"User failed to clcik the forget password button",
					Common.getscreenShotPathforReport("forget password buttonfaield"));
			Assert.fail();

		}

		try {

			Common.textBoxInput("xpath", "//input[contains(@id,'forgot-password-email')]",
					data.get(dataSet).get("Email"));
			// Successfully Forgot password pop up is displayed to customer

			Common.clickElement("xpath", "//button[contains(text(),'Reset my Password')]");
			Thread.sleep(3000);
			expectedResult = "Confirmation message is presented to customer saying if there is an associated account they would get email with instructions. Email is sent to customer.";
			report.addPassLog("verifying forgetpassword conformation message ", expectedResult,
					"Successfully Forgot password email is field",
					Common.getscreenShotPathforReport("foget password button"));
		}

		catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("verifying forgetpassword conformation message", expectedResult,
					"User failed to enter forget password",
					Common.getscreenShotPathforReport("forget password enterfaield"));
			Assert.fail();

		}

		// report.addPassLog(expectedResult,"user get confirmatin
		// message",Common.getscreenShotPathforReport("reset my passowrd
		// button"));
		// Common.actionsKeyPress(Keys.ESCAPE);
	}

	public void Customize_Bottle() throws Exception {

		String expectedResult = "User should land on the home page";
		Thread.sleep(8000);
		int size = Common.findElements("xpath", "//a[@class='logo']").size();
		Common.assertionCheckwithReport(size > 0, " verifying the home page", expectedResult,
				"Successfully landed on the home page", "User unabel to land on home page");
		// (size>0, "validating the home page", expectedResult, "Successfully
		// landed on the home page", "custombottlehomepage");

		// Common.assertionCheckwithReport(size>0, "Successfully landed on the
		// home page", expectedResult,"User unabel to land on home page");
		// report.addPassLog(expectedResult,"Successfully landed on the home
		// page",Common.getscreenShotPathforReport("Successfully landed on the
		// home page"));
		try {
			Thread.sleep(8000);

			Sync.waitElementPresent("xpath", "//ul[@class='megamenu-list']/li[2]/div[1]/button");
			Thread.sleep(4000);
            Common.mouseOverClick("xpath", "//ul[@class='megamenu-list']/li[2]/div[1]/button");
            //This code will be commited in production. for jetrails need to uncomment this.
            
            
			Sync.waitElementPresent("xpath", "//span[contains(text(),'Create Yours Now')]");
			Thread.sleep(4000);
			Common.clickElement("xpath", "//span[contains(text(),'Create Yours Now')]");
			Thread.sleep(5000);

			expectedResult = "It should land successfully on my-hydro-landing page";
			int sizes = Common.findElements("xpath", "//span[contains(text(),'Create Yours Now')]").size();

			Common.assertionCheckwithReport(sizes > 0, "validating My hydro-Landing page", expectedResult,
					"successfully land  on my-hydro-landing page", "User unabel to land on my hydro landing page");

			
			// above  code 1736 to 1740 is not working in stage need to commit
			
			
			// Common.assertionCheckwithReport(sizes>0, "successfully land on
			// my-hydro-landing page", expectedResult,"User unabel to land on
			// my-hydro-landing page ");
			report.addPassLog("validating My hydro-Landing page", expectedResult,
					"User successfully to select My Hydro option", Common.getscreenShotPathforReport("Myhydropage"));
			
		} 
		catch (Exception | Error e) {
             e.printStackTrace();
			report.addFailedLog("validating My hydro-Landing page", expectedResult,
					"User Faield to select My Hydro option", Common.getscreenShotPathforReport("Myhydropage"));
			// ExtenantReportUtils.addFailedLog("click the my hydro button for
			// customized productst", "User Faield to select My Hydro option ",
			// Common.getscreenShotPathforReport("My hydro"));
			Assert.fail();

		}

		try {
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Sync.waitElementPresent("xpath", "//div[contains(text(),'Standard')]/following::a[1]");
			WebElement linkEnabel = Common.findElementBy("xpath","//div[contains(text(),'Standard')]/following::a[1]");
			for (int i = 1; i < 10; i++) {
				Thread.sleep(2000);
				String attribute = linkEnabel.getAttribute("href");
				attribute.length();
				if (attribute.contains("hydro")) {
					expectedResult = "It should land successfully on the my hydro configurator";
					Sync.waitElementPresent("xpath", "//div[contains(text(),'Standard')]/following::a[1]");
					// int buttonsize=Common.findElements("xpath",
					// "//div[text()='Standard Mouth
					// Bottle']/following::a[1]").size();
					report.addPassLog("validating my hydro configuration page", expectedResult,
							"successfully opean the  my hydro configurator page", " my-hydro-configurator page");
					// Common.assertionCheckwithReport(buttonsize>0, "validating
					// my hydro configuration page", expectedResult,
					// "successfully opean the my hydro configurator page",
					// screenShotPath);
					// Common.assertionCheckwithReport(buttonsize>0,
					// "successfully opean the my hydro configurator page",
					// expectedResult,"User unabel to land on
					// my-hydro-configurator page ");
					Common.clickElement("xpath", "//div[contains(text(),'Standard')]/following::a[1]");
					break;
				}

			}

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating my hydro configuration page",
					"It should land successfully on the my hydro configurator and select Standard Mouth Bottle ",
					"User Faield to select My Hydro configurator or botttle option",
					Common.getscreenShotPathforReport("My hydro options"));
			Assert.fail();

		}

		Thread.sleep(6000);

		/*
		 * Common.actionsKeyPress(Keys.PAGE_DOWN);
		 * Sync.waitElementPresent("xpath", "//span[text()='Add To Cart']");
		 * Common.clickElement("xpath", "//span[text()='Add To Cart']");
		 * 
		 * report.addPassLog("add the product the cart",Common.
		 * getscreenShotPathforReport("click to add to Card"));
		 */
		try {
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

			Sync.waitElementPresent("xpath", "//fieldset[@id='fc-ca-swatch-90031-fieldset']//div[5]/span");
			Common.clickElement("xpath", "//fieldset[@id='fc-ca-swatch-90031-fieldset']//div[5]/span");

			Sync.waitElementPresent("xpath", "//div[@id='fc-nav-flyout-header-80268']");
			Common.clickElement("xpath", "//div[@id='fc-nav-flyout-header-80268']");

			Sync.waitElementPresent("xpath", "//fieldset[@id='fc-ca-swatch-80268-fieldset']//div[5]/span");
			Common.clickElement("xpath", "//fieldset[@id='fc-ca-swatch-80268-fieldset']//div[5]/span");

			Sync.waitElementPresent("xpath", "//div[@id='fc-nav-flyout-header-80270']");
			Common.clickElement("xpath", "//div[@id='fc-nav-flyout-header-80270']");

			Sync.waitElementPresent("xpath", "//fieldset[@id='fc-ca-swatch-80270-fieldset']//div[2]/span");
			Common.clickElement("xpath", "//fieldset[@id='fc-ca-swatch-80270-fieldset']//div[2]/span");
			expectedResult = "chenage the bottle configuration size and colo";

			int colorofbottle = Common.findElements("xpath", "//div[@id='fc-nav-flyout-header-80270']").size();
			Assert.assertTrue(colorofbottle > 0);

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying myhydro configuration page", expectedResult,
					"User unabel to change bottele color ",
					Common.getscreenShotPathforReport("faield change the color myhydro"));
			Assert.fail();

		}
		// report.addPassLog(expectedResult, "Successfully chenage the bottle
		// configuration size and
		// color",Common.getscreenShotPathforReport("changing Configurations"));
		try {
			Sync.waitElementPresent("xpath", "//span[text()='Add To Cart']");
			Common.clickElement("xpath", "//span[text()='Add To Cart']");
			ExtenantReportUtils.addPassLog("verifying myhydro configuration page", "user click add to cart button",
					"user click the add to cart button",
					Common.getscreenShotPathforReport("faield to click add to cart button"));
		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("verifying myhydro configuration page", "click add to cart",
					"User faield to click add to cart ", Common.getscreenShotPathforReport("add to cart button"));
			Assert.fail();

		}
		Thread.sleep(6000);
		 
		checkOut();
	}

	/*
	 * Sync.waitElementPresent("xpath",
	 * "//button[@id='top-cart-btn-checkout']"); Common.clickElement("xpath",
	 * "//button[@id='top-cart-btn-checkout']");
	 * report.addPassLog("navigating  to check out page ",Common.
	 * getscreenShotPathforReport("click to check out"));
	 */

	public void shop_bottles() throws Exception {
		Sync.waitElementPresent("xpath", "//ul[@class='megamenu-list']/li[ 1]/div[1]/button");
		Common.clickElement("xpath", "//ul[@class='megamenu-list']/li[ 1]/div[1]/button");

		Sync.waitElementPresent("xpath", "//ul[@class='megamenu-list-ancestor']//a[contains(text(),'Bottles')]");
		Common.clickElement("xpath", "//ul[@class='megamenu-list-ancestor']//a[contains(text(),'Bottles')]");
	}

	public void review( String dataSet) {
		
		String expectedResult="";
		try {
			
			String Serachproduct ="18 oz Standard Mouth";
		Thread.sleep(5000);
		Sync.waitElementVisible("xpath", "//form[@id='search_mini_form']//label");
		Thread.sleep(8000);
		Common.clickElement("xpath", "//form[@id='search_mini_form']//label");
		Common.textBoxInput("xpath", "//input[@id='search']", Serachproduct);
		Common.actionsKeyPress(Keys.ENTER);
		
		Common.clickElement("xpath", "//a[text()='"+Serachproduct+"']");
		Thread.sleep(4000);
		ExtenantReportUtils.addPassLog("validating Search box", "enter product name will display in search box",
				"user enter the product name in  search box", Common.getscreenShotPathforReport("searchproduct1"));
		
		
		
		
	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating Search box", "enter product name will display in search box",
				"User failed to enter product name", Common.getscreenShotPathforReport("searchproduct1"));
		Assert.fail();

	}
		
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		
		

		try {
			Thread.sleep(4000);

			Sync.waitElementPresent("xpath", "//button[contains(@class,'write-review')]");
			Common.clickElement("xpath", "//button[contains(@class,'write-review')]");
		}

		catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("verifying the review button", "select the write review option",
					"User failed to click write review option  ", Common.getscreenShotPathforReport("write riew"));
			Assert.fail();

		}

		try {
		
			Thread.sleep(4000);
			expectedResult = "It should shows My Review Pop-up";
			
			overallRating(data.get(dataSet).get("OverallRating"));

			Sync.waitElementPresent("id", "bv-text-field-title");

			int reviewpagelview = Common.findElements("id", "bv-text-field-title").size();
			Common.assertionCheckwithReport(reviewpagelview > 0, "verifying review page", "Review pop-up open",
					expectedResult, "User unabel to redirect Review pop-up");
			// (reviewpagelview>0, "Review pop-up open", expectedResult,"User
			// unabel to redirect Review pop-up ");

			Common.textBoxInput("id", "bv-text-field-title", data.get(dataSet).get("Reviewtitle"));

			Common.textBoxInput("xpath", "//textarea[contains(@id,'reviewtext')]",
					data.get(dataSet).get("ReviewDescription"));

			Wouldyourecommendthiproduct(data.get(dataSet).get("recommendthiproduct"));

			// nicName
			Sync.waitElementPresent("xpath", "//input[contains(@id,'usernickname')]");
			Common.textBoxInput("xpath", "//input[contains(@id,'usernickname')]", data.get(dataSet).get("Nickname"));

			// User location
			Sync.waitElementPresent("xpath", "//input[contains(@id,'userlocation')]");
			Common.textBoxInput("xpath", "//input[contains(@id,'userlocation')]", data.get(dataSet).get("Location"));

			// user email

			Sync.waitElementPresent("xpath", "//input[contains(@id,'hostedauthentication')]");
			Common.textBoxInput("xpath", "//input[contains(@id,'hostedauthentication')]",
					data.get(dataSet).get("Email"));
			selectAgeforReview(Integer.valueOf(data.get(dataSet).get("Age")));
			selectGenderforReview(data.get(dataSet).get("gender"));
			qualityof_ProductRating(data.get(dataSet).get("ProductRating"));
			valueof_productRating(data.get(dataSet).get("valueofProductRating"));
			recommendHydroFlask(Integer.valueOf(data.get(dataSet).get("HydroFlaskOverallRating")));

			Common.textBoxInput("xpath", "//textarea[contains(@id,'netpromotercomment')]",
					data.get(dataSet).get("Pleasetelluswhy"));

			Common.clickElement("xpath", "//button[contains(@class,'form-actions-submit')]");

		}

		catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("verifying product review",
					"User fill the all the infromation in reivew page",
					"User failed to fill the all the infromation in review page",
					Common.getscreenShotPathforReport("reviwPage"));
			Assert.fail();
		}

		try {
			Thread.sleep(4000);
			String sucesstext = Common.getText("xpath", "//*[contains(@class,'bv-submission-text')]");
			System.out.println(sucesstext+"********************************");
			
			expectedResult = "Click on post review button, it shouldshows Pop-up with text Your review was Submitted!";
			Common.assertionCheckwithReport(sucesstext.contains("Your review was submitted!"),
					"verifying review success message", expectedResult, "Your review was submitted",
					"User missing filed valied data in review page");
		} catch (Exception | Error e) {
			e.printStackTrace();
		
			ExtenantReportUtils.addFailedLog("verifying review success message", "User submit the review",
					"User failed to fill the all the infromation in review page",
					Common.getscreenShotPathforReport("reviwPagesubmit"));
			Assert.fail();
		}
	}

	
	
	
	
	public void review_bottles(String dataSet) throws Exception {

		String expectedResult = "It Shoud lands on the Product Listing Page";
		try {

			Thread.sleep(8000);
			Sync.waitElementClickable("xpath", "//ul[@class='megamenu-list']/li[1]/div[1]");
			Thread.sleep(4000);
			 Common.clickElement("xpath", "//ul[@class='megamenu-list']/li[1]/div[1]/button");

			// Common.clickElement("css", "ul.megamenu-list > li:nth-of-type(1)
			// > div:nth-of-type(1) > button");
			Thread.sleep(3000);
			expectedResult = "It Shoud lands on the Product Listing Page";
			try {
				Common.mouseOverClick("xpath", "//a[contains(text(),'Bottles')]");
			} catch (Exception e) {

			//	Common.clickElement("xpath", "//ul[@class='megamenu-list']/li[1]/div[1]/button");
				Thread.sleep(1000);
				Common.clickElement("xpath", "//a[contains(text(),'Bottles')]");

			}

			int sizeofbottle = Common.findElements("xpath", "//a[contains(text(),'Bottles')]").size();

		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("validating Bottles option ", "user selecct the bottle option",
					"user failed to click the bottle option",
					Common.getscreenShotPathforReport("bottleselectionmissing"));

			Assert.fail();

		}

		Thread.sleep(4000);
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		try {

			//Sync.waitElementPresent("xpath", "//img[contains(@alt,'18 oz Standard Mouth - Pineapple')]");
			//Common.clickElement("xpath", "//img[contains(@alt,'18 oz Standard Mouth - Pineapple')]");
			
			Sync.waitElementPresent("xpath", "//img[contains(@alt,'18 oz Standard Mouth - hibiscus')]");
			Common.clickElement("xpath", "//img[contains(@alt,'18 oz Standard Mouth - hibiscus')]");
			
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Common.actionsKeyPress(Keys.PAGE_DOWN);

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Click on  product image/ product name",
					"User failed to to select the bottle product for review ",
					Common.getscreenShotPathforReport("bottleselectionmissing"));
			Assert.fail();

		}
		Thread.sleep(8000);
		try {
			expectedResult = "Click on  product image/ product name, it should  be redirect to the product details page";
			Sync.waitElementPresent("id", "tab-title-reviews");
			int tabelview = Common.findElements("id", "tab-title-reviews").size();
			Common.assertionCheckwithReport(tabelview > 0, "redirect to the product details page", expectedResult,
					"User unabel to redirect the product details page");
			Common.clickElement("id", "tab-title-reviews");
		}

		catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("verifying bottele product", expectedResult,
					"User failed to select review option in product list page ",
					Common.getscreenShotPathforReport("bottle image"));
			Assert.fail();

		}
		Thread.sleep(4000);

		try {

			Sync.waitElementPresent("xpath", "//button[contains(@class,'write-review')]");
			Common.clickElement("xpath", "//button[contains(@class,'write-review')]");
		}

		catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("verifying the review button", "select the write review option",
					"User failed to click write review option  ", Common.getscreenShotPathforReport("write riew"));
			Assert.fail();

		}

		try {
		
			Thread.sleep(4000);
			expectedResult = "It should shows My Review Pop-up";
			
			overallRating(data.get(dataSet).get("OverallRating"));

			Sync.waitElementPresent("id", "bv-text-field-title");

			int reviewpagelview = Common.findElements("id", "bv-text-field-title").size();
			Common.assertionCheckwithReport(reviewpagelview > 0, "verifying review page", "Review pop-up open",
					expectedResult, "User unabel to redirect Review pop-up");
			// (reviewpagelview>0, "Review pop-up open", expectedResult,"User
			// unabel to redirect Review pop-up ");

			Common.textBoxInput("id", "bv-text-field-title", data.get(dataSet).get("Reviewtitle"));

			Common.textBoxInput("xpath", "//textarea[contains(@id,'reviewtext')]",
					data.get(dataSet).get("ReviewDescription"));

			Wouldyourecommendthiproduct(data.get(dataSet).get("recommendthiproduct"));

			// nicName
			Sync.waitElementPresent("xpath", "//input[contains(@id,'usernickname')]");
			Common.textBoxInput("xpath", "//input[contains(@id,'usernickname')]", data.get(dataSet).get("Nickname"));

			// User location
			Sync.waitElementPresent("xpath", "//input[contains(@id,'userlocation')]");
			Common.textBoxInput("xpath", "//input[contains(@id,'userlocation')]", data.get(dataSet).get("Location"));

			// user email

			Sync.waitElementPresent("xpath", "//input[contains(@id,'hostedauthentication')]");
			Common.textBoxInput("xpath", "//input[contains(@id,'hostedauthentication')]",
					data.get(dataSet).get("Email"));
			selectAgeforReview(Integer.valueOf(data.get(dataSet).get("Age")));
			selectGenderforReview(data.get(dataSet).get("gender"));
			qualityof_ProductRating(data.get(dataSet).get("ProductRating"));
			valueof_productRating(data.get(dataSet).get("valueofProductRating"));
			recommendHydroFlask(Integer.valueOf(data.get(dataSet).get("HydroFlaskOverallRating")));

			Common.textBoxInput("xpath", "//textarea[contains(@id,'netpromotercomment')]",
					data.get(dataSet).get("Pleasetelluswhy"));

			Common.clickElement("xpath", "//button[contains(@class,'form-actions-submit')]");

		}

		catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("verifying product review",
					"User fill the all the infromation in reivew page",
					"User failed to fill the all the infromation in review page",
					Common.getscreenShotPathforReport("reviwPage"));
			Assert.fail();
		}

		try {
			Thread.sleep(4000);
			String sucesstext = Common.getText("xpath", "//*[contains(@class,'bv-submission-text')]");
			System.out.println(sucesstext+"********************************");
			
			expectedResult = "Click on post review button, it shouldshows Pop-up with text Your review was Submitted!";
			Common.assertionCheckwithReport(sucesstext.contains("Your review was submitted!"),
					"verifying review success message", expectedResult, "Your review was submitted",
					"User missing filed valied data in review page");
		} catch (Exception | Error e) {
			e.printStackTrace();
		
			ExtenantReportUtils.addFailedLog("verifying review success message", "User submit the review",
					"User failed to fill the all the infromation in review page",
					Common.getscreenShotPathforReport("reviwPagesubmit"));
			Assert.fail();
		}
	}

	/*
	 * catch(Exception |Error e) {
	 * ExtenantReportUtils.addFailedLog("verifying product review"
	 * ,"User fill the all the infromation in reivew page",
	 * "User failed to fill the all the infromation in review page",
	 * Common.getscreenShotPathforReport("reviwPage")); Assert.fail();
	 * 
	 * }
	 * 
	 * 
	 * try{ String sucesstext= Common.getText("xpath",
	 * "//span[contains(@class,'bv-submission-text')]");
	 * 
	 * // Assert.assertEquals(sucesstext, "Your review was submitted!",
	 * "submit the Review");
	 * 
	 * expectedResult="Click on post review button, it shouldshows Pop-up with text Your review was Submitted!"
	 * ;
	 * 
	 * // int tabelview=Common.findElements("id", "tab-title-reviews").size();
	 * Common.assertionCheckwithReport(sucesstext.
	 * equals("Your review was submitted!"), "verifying review success message",
	 * expectedResult, "Your review was submitted",
	 * "User missing filed valied data in review page");
	 * //(sucesstext.equals("Your review was submitted!"),
	 * "Your review was submitted",
	 * expectedResult,"User missing filed data in review page");
	 * 
	 * //report.addPassLog(expectedResult,"Your review was Submitted",Common.
	 * getscreenShotPathforReport("Review")); } catch(Exception |Error e) {
	 * ExtenantReportUtils.addFailedLog("verifying review success message"
	 * ,"User submit the review",
	 * "User failed to fill the all the infromation in review page",
	 * Common.getscreenShotPathforReport("reviwPagesubmit")); Assert.fail();
	 */

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void recommendHydroFlask(int number) {

		int numbers = number + 1;
		Common.clickElement("xpath", "//span[contains(@class,'netpromoterscore-wrapper')]//li[" + numbers + "]");
	}

	/*public void overallRating(String OverallRating) throws Exception {
		Thread.sleep(3000);
		System.out.println(OverallRating);
		switch (OverallRating) {

		case "poor":
			Sync.waitElementPresent("xpath", "//a[contains(@id,'rating-1')]");
			Common.clickElement("xpath", "//a[contains(@id,'rating-1')]");
			break;
		case "Fair":
			Sync.waitElementPresent("xpath", "//a[contains(@id,'rating-2')]");
			Common.clickElement("xpath", "//a[contains(@id,'rating-2')]");
			break;
		case "Average":
			Sync.waitElementPresent("xpath", "//a[contains(@id,'rating-3')]");
			Common.clickElement("xpath", "//a[contains(@id,'rating-3')]");
			;
			System.out.println("clicked");

			break;
		case "Good":
			Sync.waitElementPresent("xpath", "//a[contains(@id,'rating-4')]");
			Common.clickElement("xpath", "//a[contains(@id,'rating-4')]");
			break;

		case "Excellent":
			Sync.waitElementPresent("xpath", "//a[contains(@id,'rating-5')]");
			Common.clickElement("xpath", "//a[contains(@id,'rating-5')]");
			break;

		}
	}

	public void qualityof_ProductRating(String ProductRating) throws Exception {

		switch (ProductRating) {

		case "poor":
			Sync.waitElementPresent("xpath", "//a[contains(@id,'Quality-1')]");
			Common.clickElement("xpath", "//a[contains(@id,'Quality-1')]");
			break;
		case "Fair":
			Sync.waitElementPresent("xpath", "//a[contains(@id,'Quality-2')]");
			Common.clickElement("xpath", "//a[contains(@id,'rating-2')]");
			break;
		case "Average":
			Sync.waitElementPresent("xpath", "//a[contains(@id,'Quality-3')]");
			Common.clickElement("xpath", "//a[contains(@id,'Quality-3')]");
			;
			break;
		case "Good":
			Sync.waitElementPresent("xpath", "//a[contains(@id,'Quality-4')]");
			Common.clickElement("xpath", "//a[contains(@id,'Quality-4')]");
			break;

		case "Excellent":
			Sync.waitElementPresent("xpath", "//a[contains(@id,'Quality-5')]");
			Common.clickElement("xpath", "//a[contains(@id,'Quality-5')]");
			break;
		}

	}

	public void valueof_productRating(String valueofProductRating) throws Exception {

		switch (valueofProductRating) {

		case "poor":
			Sync.waitElementPresent("xpath", "//a[contains(@id,'rating_Value-1')]");
			Common.clickElement("xpath", "//a[contains(@id,'rating_Value-1')]");
			break;
		case "Fair":
			Sync.waitElementPresent("xpath", "//a[contains(@id,'rating_Value-2')]");
			Common.clickElement("xpath", "//a[contains(@id,'rating_Value-2')]");
			break;
		case "Average":
			Sync.waitElementPresent("xpath", "//a[contains(@id,'rating_Value-3')]");
			Common.clickElement("xpath", "//a[contains(@id,'rating_Value-3')]");
			;
			break;
		case "Good":
			Sync.waitElementPresent("xpath", "//a[contains(@id,'rating_Value-4')]");
			Common.clickElement("xpath", "//a[contains(@id,'rating_Value-4')]");
			break;

		case "Excellent":
			Sync.waitElementPresent("xpath", "//a[contains(@id,'rating_Value-5')]");
			Common.clickElement("xpath", "//a[contains(@id,'rating_Value-5')]");
			break;

		}

	}*/
	

	public void overallRating(String OverallRating) throws Exception{
	Thread.sleep(3000);
	/*if(Common.getCurrentURL().contains("stg")){
	 switch (OverallRating) {
	case "poor":
	Sync.waitElementPresent("xpath", "//a[contains(@id,'rating-1')]");
	Common.clickElement("xpath", "//a[contains(@id,'rating-1')]");
	break;
	case "Fair":
	Sync.waitElementPresent("xpath", "//a[contains(@id,'rating-2')]");
	Common.clickElement("xpath", "//a[contains(@id,'rating-2')]");
	break;
	case "Average":
	Sync.waitElementPresent("xpath", "//a[contains(@id,'rating-3')]");
	Common.clickElement("xpath", "//a[contains(@id,'rating-3')]");;
	System.out.println("clicked");
	break;
	case "Good":
	Sync.waitElementPresent("xpath", "//a[contains(@id,'rating-4')]");
	Common.clickElement("xpath", "//a[contains(@id,'rating-4')]");
	break;
	case "Excellent":
	Sync.waitElementPresent("xpath", "//a[contains(@id,'rating-5')]");
	Common.clickElement("xpath", "//a[contains(@id,'rating-5')]");
	break;
	}
	}*/
	//else{
	switch (OverallRating) {
	case "poor":
	Sync.waitElementPresent("xpath", "//span[contains(@id,'rating-1')]");
	Common.clickElement("xpath", "//span[contains(@id,'rating-1')]");
	break;
	case "Fair":
	Sync.waitElementPresent("xpath", "//span[contains(@id,'rating-2')]");
	Common.clickElement("xpath", "//span[contains(@id,'rating-2')]");
	break;
	case "Average":
	Sync.waitElementPresent("xpath", "//span[contains(@id,'rating-3')]");
	Common.clickElement("xpath", "//span[contains(@id,'rating-3')]");;
	System.out.println("clicked");
	break;
	case "Good":
	Sync.waitElementPresent("xpath", "//span[contains(@id,'rating-4')]");
	Common.clickElement("xpath", "//span[contains(@id,'rating-4')]");
	break;
	case "Excellent":
	Sync.waitElementPresent("xpath", "//span[contains(@id,'rating-5')]");
	Common.clickElement("xpath", "//span[contains(@id,'rating-5')]");
	break;
	}
	}
	//}
	    public void qualityof_ProductRating(String ProductRating) throws Exception{
	/*//*/
	switch (ProductRating) {
	case "poor":
	Sync.waitElementPresent("xpath", "//span[contains(@id,'Quality-1')]");
	Common.clickElement("xpath", "//span[contains(@id,'Quality-1')]");
	break;
	case "Fair":
	Sync.waitElementPresent("xpath", "//span[contains(@id,'Quality-2')]");
	Common.clickElement("xpath", "//span[contains(@id,'rating-2')]");
	break;
	case "Average":
	Sync.waitElementPresent("xpath", "//span[contains(@id,'Quality-3')]");
	Common.clickElement("xpath", "//span[contains(@id,'Quality-3')]");;
	break;
	case "Good":
	Sync.waitElementPresent("xpath", "//span[contains(@id,'Quality-4')]");
	Common.clickElement("xpath", "//span[contains(@id,'Quality-4')]");
	break;
	case "Excellent":
	Sync.waitElementPresent("xpath", "//span[contains(@id,'Quality-5')]");
	Common.clickElement("xpath", "//span[contains(@id,'Quality-5')]");
	break;
	}
	}
	//}
	public void valueof_productRating(String valueofProductRating) throws Exception{
	/*if(Common.getCurrentURL().contains("stg")){
	switch (valueofProductRating) {
	case "poor":
	Sync.waitElementPresent("xpath", "//a[contains(@id,'rating_Value-1')]");
	Common.clickElement("xpath", "//a[contains(@id,'rating_Value-1')]");
	break;
	case "Fair":
	Sync.waitElementPresent("xpath", "//a[contains(@id,'rating_Value-2')]");
	Common.clickElement("xpath", "//a[contains(@id,'rating_Value-2')]");
	break;
	case "Average":
	Sync.waitElementPresent("xpath", "//a[contains(@id,'rating_Value-3')]");
	Common.clickElement("xpath", "//a[contains(@id,'rating_Value-3')]");;
	break;
	case "Good":
	Sync.waitElementPresent("xpath", "//a[contains(@id,'rating_Value-4')]");
	Common.clickElement("xpath", "//a[contains(@id,'rating_Value-4')]");
	break;
	case "Excellent":
	Sync.waitElementPresent("xpath", "//a[contains(@id,'rating_Value-5')]");
	Common.clickElement("xpath", "//a[contains(@id,'rating_Value-5')]");
	break;
	}
	}
	else{*/
	switch (valueofProductRating) {
	case "poor":
	Sync.waitElementPresent("xpath", "//span[contains(@id,'rating_Value-1')]");
	Common.clickElement("xpath", "//span[contains(@id,'rating_Value-1')]");
	break;
	case "Fair":
	Sync.waitElementPresent("xpath", "//span[contains(@id,'rating_Value-2')]");
	Common.clickElement("xpath", "//span[contains(@id,'rating_Value-2')]");
	break;
	case "Average":
	Sync.waitElementPresent("xpath", "//span[contains(@id,'rating_Value-3')]");
	Common.clickElement("xpath", "//span[contains(@id,'rating_Value-3')]");;
	break;
	case "Good":
	Sync.waitElementPresent("xpath", "//span[contains(@id,'rating_Value-4')]");
	Common.clickElement("xpath", "//span[contains(@id,'rating_Value-4')]");
	break;
	case "Excellent":
	Sync.waitElementPresent("xpath", "//span[contains(@id,'rating_Value-5')]");
	Common.clickElement("xpath", "//span[contains(@id,'rating_Value-5')]");
	break;
	}
	}
	//}
	
	
	
	
	
	
	
	
	
	

	public void selectAgeforReview(int Age) throws Exception {

		if (Age <= 17) {
			Sync.waitElementPresent("id", "bv-select-field-contextdatavalue_Age");

			Common.dropdown("id", "bv-select-field-contextdatavalue_Age", SelectBy.VALUE, "17orUnder");
		}

		else if (Age <= 24 && Age > 17) {
			Sync.waitElementPresent("id", "bv-select-field-contextdatavalue_Age");
			Common.dropdown("id", "bv-select-field-contextdatavalue_Age", SelectBy.VALUE, "18to24");
		} else if (Age >= 25 && Age <= 34) {
			Sync.waitElementPresent("id", "bv-select-field-contextdatavalue_Age");
			Common.dropdown("id", "bv-select-field-contextdatavalue_Age", SelectBy.VALUE, "25to34");
		}

		else if (Age >= 35 && Age <= 44) {
			Sync.waitElementPresent("id", "bv-select-field-contextdatavalue_Age");
			Common.dropdown("id", "bv-select-field-contextdatavalue_Age", SelectBy.VALUE, "35to44");
		}

		else if (Age >= 45 && Age <= 54) {
			Sync.waitElementPresent("id", "bv-select-field-contextdatavalue_Age");
			Common.dropdown("id", "bv-select-field-contextdatavalue_Age", SelectBy.VALUE, "45to54");
		} else if (Age >= 55 && Age <= 64) {
			Sync.waitElementPresent("id", "bv-select-field-contextdatavalue_Age");
			Common.dropdown("id", "bv-select-field-contextdatavalue_Age", SelectBy.VALUE, "55to64");
		} else if (Age <= 65) {
			Sync.waitElementPresent("id", "bv-select-field-contextdatavalue_Age");
			Common.dropdown("id", "bv-select-field-contextdatavalue_Age", SelectBy.VALUE, "65orOver");
		}
	}

	public void selectGenderforReview(String gender) throws Exception {

		if (gender.equals("Male")) {
			Sync.waitElementPresent("id", "bv-select-field-contextdatavalue_Gender");
			Common.dropdown("id", "bv-select-field-contextdatavalue_Gender", SelectBy.VALUE, "Male");
		} else if (gender.equals("Female")) {
			Sync.waitElementPresent("id", "bv-select-field-contextdatavalue_Gender");
			Common.dropdown("id", "bv-select-field-contextdatavalue_Gender", SelectBy.VALUE, "Female");
		}

	}

	public void Wouldyourecommendthiproduct(String recommendthiproduct) throws Exception {

		if (recommendthiproduct.equals("Yes")) {
			Sync.waitElementPresent("xpath", "//label[contains(@id,'isrecommended-true')]");
			Common.clickElement("xpath", "//label[contains(@id,'isrecommended-true')]");

		} else if (recommendthiproduct.equals("No")) {

			Sync.waitElementPresent("xpath", "//label[contains(@id,'isrecommended-false')]");
			Common.clickElement("xpath", "//label[contains(@id,'isrecommended-false')]");
		}
	}

	public void logOut() throws Exception {

		Thread.sleep(5000);
		try {
			String expectedResult = "It should land on the signout page and redireted to homepage after 5 seconds.";

			Sync.waitElementPresent("xpath", "//li[contains(@class,'account-component')]/a");
			Common.mouseOverClick("xpath", "//li[contains(@class,'account-component')]/a");

			Sync.waitElementPresent("xpath", "//ul[contains(@class,'component-content')]/li[2]/a");
			Common.mouseOverClick("xpath", "//ul[contains(@class,'component-content')]/li[2]/a");
			ExtenantReportUtils.addPassLog("verifying logoout", "Log out from aplication",
					"User log out from aplication", Common.getscreenShotPathforReport("logout"));

		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("verifying logoout", "user log from application",
					"User failed to log out from aplication", Common.getscreenShotPathforReport("logoutfailed"));
			Assert.fail();

		}
		/*
		 * Thread.sleep(3000); int sizemessage=Common.findElements("xpath",
		 * "//div[@class='customer-logout-success-message']").size();
		 * 
		 * Common.assertionCheckwithReport(sizemessage>0,
		 * "Successfully Log out from aplication",
		 * expectedResult,"User unabel logoutappliaction");
		 */

	}

	public void promationCode(String dataSet) throws Exception {

		String expectedResult = "It should opens textbox input.";

		try {

			Sync.waitElementClickable("id", "discount-accordion");
			Common.clickElement("id", "discount-accordion");

			Sync.waitElementPresent("id", "discount-code");

			Common.textBoxInput("id", "discount-code", data.get(dataSet).get("Promationcode"));

			int size = Common.findElements("id", "discount-code").size();

			Common.assertionCheckwithReport(size > 0, "verifying the Promo Code label", expectedResult,
					"Successfully open the discount input box", "User unabel enter promationCode");
			// Common.assertionCheckwithReport(size>0, "Successfully open the
			// discount input box", expectedResult,"User unabel enter
			// promationCode");
			Sync.waitElementClickable("xpath", "//button[@class='action action-apply']");
			Common.clickElement("xpath", "//button[@class='action action-apply']");
			Sync.waitPageLoad();
			Thread.sleep(4000);
			expectedResult = "It should apply discount on your price.If user enters invalid promocode it should display coupon code is not valid message.";
			String codetext = Common.getText("xpath", "//span[@class='rule-coupon-code']");
			Common.assertionCheckwithReport(codetext.equals(data.get(dataSet).get("Promationcode")),
					"verifying pomocode", expectedResult, "promotion code working as expected",
					"Promation code is not applied");

			// Common.assertionCheckwithReport(codetext.equals(data.get(dataSet).get("Promationcode")),
			// "promotion code working as expected", expectedResult,"Promation
			// code is not applied ");
			// Assert.assertEquals( data.get(dataSet).get("Promationcode"),
			// codetext,"Promation code is not applied ");
		}

		catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("validating promo code", expectedResult,
					"User failed to proceed with promocode", Common.getscreenShotPathforReport("promocodefaield"));
			// (expectedResult, "User failed to proceed with promocode ",
			// Common.getscreenShotPathforReport(expectedResult));
			Assert.fail();

		}

		// report.addPassLog(expectedResult,"promotion code working as
		// expected",Common.getscreenShotPathforReport("pomotion code"));
	}

	public void changeAddressIn_AddressBook(String dataSet) throws Exception {
		try {
			Sync.waitElementClickable("xpath", "//a[contains(text(),'Address Book')]");
			Common.clickElementStale("xpath", "//a[contains(text(),'Address Book')]");
			// report.addPassLog("click the my address book page
			// ",Common.getscreenShotPathforReport("my address book option"));

			ExtenantReportUtils.addPassLog("validating Address Book option", "navigate to my address book page",
					"successfully navigate to address book page", Common.getscreenShotPathforReport("addressbook pag"));

		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("validating Address Book option", "navigate to my address book page",
					"User failed to navigate my address book", Common.getscreenShotPathforReport("addressbook paget"));
			// (expectedResult, "User failed to proceed with promocode ",
			// Common.getscreenShotPathforReport(expectedResult));
			Assert.fail();

		}
		String PageTitle = Common.getText("xpath", "//h1[@class='page-title']/span");
		if (PageTitle.contains("New")) {
			try {
				Common.textBoxInput("xpath", "//input[@id='firstname']", data.get(dataSet).get("FirstName"));
				Common.textBoxInput("xpath", "//input[@id='lastname']", data.get(dataSet).get("LastName"));
				Common.textBoxInput("xpath", "//input[@id='street_1']", data.get(dataSet).get("Street"));
				Common.textBoxInput("xpath", "//input[@id='city']", data.get(dataSet).get("City"));
				try {
					Common.dropdown("id", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
				} catch (ElementClickInterceptedException e) {
					Thread.sleep(3000);
					Common.dropdown("id", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
				}
				Thread.sleep(2000);
                Common.textBoxInputClear("xpath", "//input[@id='zip']");
				//Common.textBoxInputClear("id", "postcode");
				Common.textBoxInput("id", "zip", data.get(dataSet).get("postcode"));
				Common.textBoxInput("id", "telephone", data.get(dataSet).get("phone"));
				Common.clickElement("xpath", "//button[@title='Save Address']");
				ExtenantReportUtils.addPassLog("validating Address Book from",
						"Filled the shipping address for myaccount page",
						Common.getscreenShotPathforReport("fill the shipping address myaccount"));
			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validating Address Book from",
						"enter the valid address without any validation",
						"User failed to enter data in my address book",
						Common.getscreenShotPathforReport("faield to addressbook"));
				// (expectedResult, "User failed to proceed with promocode ",
				// Common.getscreenShotPathforReport(expectedResult));
				Assert.fail();

			}
		}

		else {
			Common.clickElementStale("xpath", "//a[contains(text(),'Change Billing Address')]");
			// report.addPassLog("click update button myaccount
			// page",Common.getscreenShotPathforReport("edit the shipping
			// address myaccount"));

			try {
				Common.textBoxInput("xpath", "//input[@id='firstname']", data.get(dataSet).get("FirstName"));

				// ExtenantReportUtils.addPassLog("validating Address Book
				// from","Filled the shipping address for myaccount
				// page",Common.getscreenShotPathforReport("fill the shipping
				// address myaccount"));

				Common.textBoxInput("xpath", "//input[@id='lastname']", data.get(dataSet).get("LastName"));
				Common.textBoxInput("xpath", "//input[@id='street_1']", data.get(dataSet).get("Street"));
				try {
					Common.dropdown("id", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
				} catch (ElementClickInterceptedException e) {
					// TODO: handle exception
					Thread.sleep(3000);
					Common.dropdown("id", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
				}
				Thread.sleep(2000);
				// Common.textBoxInputClear("id", "postcode");
				Common.textBoxInput("id", "zip", data.get(dataSet).get("postcode"));
				Common.textBoxInput("id", "telephone", data.get(dataSet).get("phone"));
				Common.clickElement("xpath", "//button[@title='Save Address']");
				Thread.sleep(6000);

			
				String Sucess = Common.getText("xpath", "//div[contains(@class,'message-success')]/div");
				// Assert.assertEquals(Sucess, "You saved the address.", "Adress
				// is saved");
				/*Common.assertionCheckwithReport(Sucess.equals("You saved the address."),
						"validating my address book with data", "enter the valid address without any validation",
						"successfully user enter the address", "User failed to enter data in my address book");*/
			}

			catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validating my address book with data",
						"enter the valid address without any validation",
						"User failed to enter data in my address book",
						Common.getscreenShotPathforReport("faield to addressbookt"));
				Assert.fail();

			}
		}
	}
public void serachproduct_addtocart(String dataSet){
	
	try {
		Thread.sleep(5000);
		Sync.waitElementVisible("xpath", "//form[@id='search_mini_form']//label");
		Thread.sleep(8000);
		Common.clickElement("xpath", "//form[@id='search_mini_form']//label");
		Common.textBoxInput("xpath", "//input[@id='search']", dataSet);
		Common.actionsKeyPress(Keys.ENTER);
		
		Common.clickElement("xpath", "//a[text()='"+dataSet+"']");
		Thread.sleep(4000);
		validating_BundlePrdocuts();
		Common.clickElement("xpath", "//button[@title='Add to Cart']");
	//	Common.clickElement("xpath", "//a[text()='Adventure Bundle']//following::form[1]//button");
		
		ExtenantReportUtils.addPassLog("validating Search box", "enter product name will display in search box",
				"user enter the product name in  search box", Common.getscreenShotPathforReport("searchproduct"));
		
		
		
		
	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating Search box", "enter product name will display in search box",
				"User failed to enter product name", Common.getscreenShotPathforReport("searchproduct"));
		Assert.fail();

	}

	
}

	public void SampleSearchProduct(String dataSet) throws Exception {
		Thread.sleep(8000);
		try {
			Sync.waitElementVisible("xpath", "//form[@id='search_mini_form']//label");
			Thread.sleep(8000);
			Common.clickElement("xpath", "//form[@id='search_mini_form']//label");
			Common.textBoxInput("xpath", "//input[@id='search']", dataSet);
			ExtenantReportUtils.addPassLog("validating Search box", "enter product name will display in search box",
					"user enter the product name in  search box", Common.getscreenShotPathforReport("searchproduct"));
		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("validating Search box", "enter product name will display in search box",
					"User failed to enter product name", Common.getscreenShotPathforReport("searchproduct"));
			Assert.fail();

		}
		try {
			Thread.sleep(8000);
			Common.actionsKeyPress(Keys.ENTER);
			Thread.sleep(8000);
			List<WebElement> productsizes = Common.findElements("xpath", "//li[contains(@class,'product-item')]");
			for (int i = 0; i < productsizes.size(); i++) {
				String attribute = productsizes.get(i).getAttribute("data-product-id");
				List<WebElement> productColors = Common.findElements("xpath", "//li[contains(@data-product-id,'"
						+ attribute + "')]//div[contains(@class,'swatch-attribute-options clearfix')]/div");
				if (productColors.size() <= 1 && productColors.size() > 0) {
					Common.clickElement("xpath", "//li[contains(@data-product-id,'" + attribute + "')]");
					ExtenantReportUtils.addPassLog("validating sample product", "user find sample product in search",
							"user successfully find sample product in search",
							Common.getscreenShotPathforReport("searchproductsample"));

					break;
				}
			}

		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("validating sample product", "user find sample product in search",
					"User failed find sample product in search",
					Common.getscreenShotPathforReport("searchproductsample"));
			Assert.fail();

		}
	}

	public void myAccountInformation() {
		try {
			Sync.waitElementClickable("xpath", "//a[contains(text(),'Account Information')]");
			Common.mouseOverClick("xpath", "//a[contains(text(),'Account Information')]");
			ExtenantReportUtils.addPassLog("verifying my account buttonÂ ", "User click the my account button",
					"successfullyÂ click the my account buttonÂ ",
					Common.getscreenShotPathforReport("my account button"));

		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("verifying my account button", "User click the my account button",
					"faield to click the my account", Common.getscreenShotPathforReport("my account button"));
			Assert.fail();

		}

	}

	public void edit_Accountinfo(String dataSet) throws Exception {

		CreateNewAccount(dataSet);
		myAccountInformation();
		Thread.sleep(5000);

		try {

			Sync.waitElementClickable("xpath", "//button[@data-role='change-email']");
			Thread.sleep(4000);

			Common.clickElement("xpath", "//button[@data-role='change-email']");
			Thread.sleep(4000);
			Sync.waitElementClickable("xpath", "//button[@data-role='change-password']");
			Common.clickElement("xpath", "//button[@data-role='change-password']");
			Thread.sleep(4000);
			String change_Email = Common.genrateRandomEmail(data.get(dataSet).get("NewEmail"));
			Common.textBoxInput("xpath", "//input[@id='email']", change_Email);
			Common.textBoxInput("xpath", "//input[@id='current-password']", data.get(dataSet).get("Password"));
			Common.textBoxInput("xpath", "//input[@id='password']", data.get(dataSet).get("Newpassword"));
			Common.textBoxInput("xpath", "//input[@id='password-confirmation']", data.get(dataSet).get("Newpassword"));
			ExtenantReportUtils.addPassLog("verifying the change password & email from", "user enter the New logins",
					"Enter the new login infromation", Common.getscreenShotPathforReport("user enterchange password"));
			Common.clickElement("xpath", "//button[contains(text(),'Save')]");
			Common.textBoxInput("id", "email", change_Email);
		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("verifying the change password & email from",
					"User enter valid Email and password", "User failed to proceed to change email and passowrd ",
					Common.getscreenShotPathforReport("emailpasswordnew"));
			Assert.fail();

		}

		/*
		 * int size= Common.findElements("xpath",
		 * "//input[@id='password-confirmation'").size();
		 * 
		 * Common.assertionCheckwithReport(size>0,
		 * "Enter new email and password", "User enter new email and password",
		 * "failed to enter data");
		 * 
		 * 
		 */
		try {

			Common.textBoxInput("id", "pass", data.get(dataSet).get("Newpassword"));
			Common.clickElement("xpath", "//button[@class='login-page-submit-action']");
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//span[@data-ui-id='page-title-wrapper']");
			Assert.assertEquals(Common.getText("xpath", "//span[@data-ui-id='page-title-wrapper']"), "My Account");
			Common.assertionCheckwithReport(
					Common.getText("xpath", "//span[@data-ui-id='page-title-wrapper']").equals("My Account"),
					"verifying new credentials", "user login with new login data", "User login with new logines",
					"unabel to login new user logines");
			// (Common.getText("xpath",
			// "//span[@data-ui-id='page-title-wrapper']").equals("My Account"),
			// "user login with new login data", "User login with new logines",
			// Common.getscreenShotPathforReport("unabel to login new user
			// logines"));
		}

		catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("verifying new credentials", "User enter valid Email and password",
					"User failed to proceedchange email and passowrd ",
					Common.getscreenShotPathforReport("emailpassword"));
			Assert.fail();

		}

		// logOut();

	}

	public void minicart_Validation() throws Exception {
		String UpdataedQuntityinminicart="3";
		
		
		
	try{	

		Common.clickElement("xpath", "//a[@aria-label='minicart']");
		Thread.sleep(2000);
		Common.clickElement("xpath", "//a[contains(@class,'viewcart')]");
		
		Thread.sleep(10000);
		
		Common.dropdown("name", "qty-mobile", SelectBy.TEXT, UpdataedQuntityinminicart);
		
		Sync.waitPageLoad();
		Thread.sleep(10000);
		Common.clickElement("xpath", "//a[@aria-label='minicart']");
		
		String getQuntity=Common.getText("xpath", "//span[@class='minicart-item-option-value']");
		
		Common.assertionCheckwithReport(UpdataedQuntityinminicart.equals(getQuntity), "Validating updated Qty in cart page", "Updated Qunty must be add to cart ", "sucessfully cart QTY is updated", "failed to UpdatedQunty");
		
	}
	
	catch (Exception | Error e) {
		ExtenantReportUtils.addFailedLog("Validating updated Qty in cart page", "Updated Qunty must be add to cart ",
				"User failed to updated QTY ",
				Common.getscreenShotPathforReport("QTY"));
		Assert.fail();

	}

		
		// div[contains(@class,'no-edit')]/a[2]
		// Sync.waitElementVisible("className", "checkout-step-title");
		// report.addPassLog("Clicked the checkout
		// button",Common.getscreenShotPathforReport("checked out page"));

		// div[contains(@class,'no-remove')]

		
		/*
		 * Common.clickElement("id", "block-discount-heading");
		 * Common.textBoxInput("id", "coupon_code", "h20");
		 * Common.clickElement("xpath",
		 * "//button[contains(@class,'cart-table-discount-apply')]");
		 */
	}

	public void EditProduct() {
		try {
			List<WebElement> elemtddds = Common.findElements("xpath", "//div[contains(@class,'no-remove')]/a[1]");

			elemtddds.get(elemtddds.size() - 1).click();
			/*
			 * Thread.sleep(10000); Common.dropdown("xpath",
			 * "//select[@name='qty-mobile']", Common.SelectBy.VALUE, "2");
			 * ExtenantReportUtils.addPassLog("verifying Editproduct",
			 * "select the product quantity", "user select the Quntity",
			 * Common.getscreenShotPathforReport("passQuntitly"));
			 * 
			 * Common.clickElement("xpath", "//button[@title='Update Cart']");
			 */

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying Editproduct",
					"User clickeditbutton navigate to product details page",
					"User failed to edit product or navigate to product detiles page",
					Common.getscreenShotPathforReport("editproduct"));
			Assert.fail();

		}
	}

	public void new_arrivals(String dataSet) {

		Common.oppenURL(data.get(dataSet).get("newarrivals"));
		String bannerText = Common.getText("xpath", "//div[@class='description-banner-title']");
		Assert.assertTrue(Common.getCurrentURL().contains("https"));

		Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "this url contains https ",
				"give url contains https", "give url missing  https");

		Assert.assertTrue(bannerText.equals("New Arrivals"));

		Common.assertionCheckwithReport(bannerText.equals("New Arrivals"), "Give URL Contains Expected Templat ",
				"give url Navigating to new Arrivals link", "give url failed lo load");

		Assert.assertTrue(Common.getPageTitle().contains(bannerText));

		Common.assertionCheckwithReport(Common.getPageTitle().contains(bannerText), "Give URL Contains Expected title ",
				"Give URL Contains valid title ", "title checking");

	}

	public void trail_Series(String dataSet) {
		Common.oppenURL(data.get(dataSet).get("trailseries"));
		String bannerText = Common.getText("xpath", "//div[@class='description-banner-title']");
		Assert.assertTrue(Common.getCurrentURL().contains("https"));
		Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "this url contains https ",
				"give url contains https", "give url missing  https");
		;
		Assert.assertTrue(bannerText.equals("Less weight. More miles."));

		Common.assertionCheckwithReport(bannerText.equals("Less weight. More miles."),
				"Give URL Contains Expected Templat ", "give url Navigating to new trail Series link",
				"give url failed lo load");

		// System.out.println(Common.getPageTitle());

		// Common.assertionCheckwithReport(Common.getPageTitle().contains("Hydro
		// Flask | Trail Series"), "Give URL Contains Expected title ", "Give
		// URL Contains valid title ", "title checking");
		// report.addPassLog("Give URL Contains Expected
		// title",Common.getscreenShotPathforReport("title checking"));
	}

	public void limited_edition(String dataSet) {
		Common.oppenURL(data.get(dataSet).get("limitededition"));
		String bannerText = Common.getText("xpath", "//div[@class='description-banner-title']");
		Assert.assertTrue(Common.getCurrentURL().contains("https"));
		// report.addPassLog("this url contains
		// https",Common.getscreenShotPathforReport("https"));

		Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "this url contains https ",
				"give url contains https", "give url missing  https");
		;

		// Assert.assertTrue(bannerText.equals("Limited Edition collections."));
		// report.addPassLog("Give URL Contains Expected
		// Template",Common.getscreenShotPathforReport("Template checking"));

		Common.assertionCheckwithReport(bannerText.equals("Limited Edition collections."),
				"Give URL Contains Expected Templat ", "give url Navigating to new trail Series link",
				"give url failed lo load");

		System.out.println(Common.getPageTitle());
		// Assert.assertTrue(Common.getPageTitle().contains("Limited Edition
		// Product | Hydro Flask"));

		Assert.assertTrue(Common.getPageTitle().contains("Limited Edition Product | Hydro Flask"));
		Common.assertionCheckwithReport(Common.getPageTitle().contains("Limited Edition Product | Hydro Flask"),
				"Give URL Contains Expected title ", "Give URL Contains valid title ", "title checking");
		// report.addPassLog("Give URL Contains Expected
		// title",Common.getscreenShotPathforReport("title checking"));
	}

	public void order_ppt(String category) throws Exception {
		Thread.sleep(5000);
		String expectedResult = "User should land on the home page";
		int size = Common.findElements("xpath", "//a[@class='logo']").size();
		Common.assertionCheckwithReport(size > 0, "validating the home page ", expectedResult,
				"Successfully landed on the home page", "User unabel to land on home page");
		Sync.waitElementClickable("xpath", "//ul[@class='megamenu-list']/li[1]/div[1]");
		Thread.sleep(4000);
		Common.mouseOverClick("xpath", "//ul[@class='megamenu-list']/li[1]/div[1]/button");
		Thread.sleep(3000);
		expectedResult = "User should click the" + category;
	}
	
	
	public void apparel(String dataSet) {
		try {
			 Common.oppenURL(data.get(dataSet).get("apparel"));
			 String bannerText=Common.getText("xpath", "//li[@class='color-category-1 active']");  
			 Common.assertionCheckwithReport(bannerText.equals("apparel"), "Give URL Contains Expected Template ", "given url Navigating to new Arrivals link", "give url failed to load");
		    if (bannerText.contains("apparel")){
		        Common.assertionCheckwithReport(bannerText.equals("apparel"), "Give URL Contains Expected Template ", "given url Navigating to new Arrivals link", "give url failed to load");
		    }else {
		    	ExtenantReportUtils.addFailedLog("Validating banner text of apparel Page", "Expected text should not be obtained","Expected text is not obtained", "LinkValidation apparel");
		        Assert.fail(); 
		    }
		    }catch(Exception |Error e){
		    	ExtenantReportUtils.addFailedLog("Validating banner text of apparel Page", "Expected text should not be obtained","Expected text is not obtained", "LinkValidation apparel");
		        Assert.fail();
		}
		Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "Validating apparel URL contains https", "apparel URL should Contains https", "apparel URL contains https", "apparel URL missing https");

		Common.assertionCheckwithReport(Common.getCurrentURL().equals("PageURL")&&Common.getPageTitle().equals("PageTitle"),"Validating Page Title and URL", "Actual and Current URL&Page Title Should be Same", "Actual and Current URL&Page Title are Same", "Actual and Current URL&Page Title are different");
		
	}

	public void urls(String URL) {
		Common.oppenURL(URL);
		String current = Common.getCurrentURL();
		String pagetitle = Common.getPageTitle();
		System.out.println(current + "---- present url");
		System.out.println(pagetitle + "--------page title");
	}
	
	public void ClosADD() throws InterruptedException{
		Thread.sleep(50000);
		int sizesframe=Common.findElements("id", "attentive_creative").size();
		if(sizesframe>0){
		Common.actionsKeyPress(Keys.PAGE_UP);
		Thread.sleep(6000);
		Common.switchFrames("id", "attentive_creative");
		Sync.waitElementClickable("id" , "closeIconContainer");
		Common.clickElement("id", "closeIconContainer");
		
		}
		}
	
	
	public void food_transport(String dataSet){
		
		try{
 			Common.oppenURL(data.get(dataSet).get("food-transport"));
			Sync.waitPageLoad();
			String bannertext=Common.getText("xpath", "//div[@class='description-banner-title']");
		
			Common.assertionCheckwithReport(bannertext.equals("Food transport has never been easier."),"validating the  banner text","it shoud open the  ","user sucessfully open the given url", "user faield to open the expected url");
			
		}
		catch(Exception |Error e){
	    	ExtenantReportUtils.addFailedLog("validating the  banner text", "it shoud open the  "+data.get(dataSet).get("food-transport"),"faield to get banner text",Common.getscreenShot("failedfoodmessage"));
	        Assert.fail();
	}
		
		Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"),"valdiating https in the given url", "it shoud contines https for given url", "it contines https ", "given url not contines https");
		
		System.out.println(Common.getPageTitle());
		Common.assertionCheckwithReport(Common.getCurrentURL().contains(data.get(dataSet).get("pageTitle"))&& Common.getPageTitle().contains(data.get(dataSet).get("pageTitle")), "validating the page url and page title", "given url contines expected page title", "sucessfully open the given page and it contines page title as"+Common.getPageTitle(), "failed to pet page title");
		
	}
	
	public void verifytheHomepage(){
		String expectedResult = "User should land on the home page";
		
	try{
		Thread.sleep(4000);
		
		int size = Common.findElements("xpath", "//a[@class='logo']").size();
		Common.assertionCheckwithReport(size > 0, "validating the home page ", expectedResult,"Successfully landed on the home page", "User unabel to land on home page");
	}
		catch(Exception |Error e){
	    	ExtenantReportUtils.addFailedLog("validating the home page", expectedResult,"User unabel to land on home page",Common.getscreenShot("homepage"));
	        Assert.fail();
	}
	}
public void order(String category) throws Exception {
		
	    verifytheHomepage();
		Sync.waitElementClickable("xpath", "//ul[@class='megamenu-list']/li[1]/div[1]");
		Thread.sleep(3000);
		Common.mouseOverClick("xpath", "//ul[@class='megamenu-list']/li[1]/div[1]/button");
		Thread.sleep(3000);
		String expectedResult = "User should click the" + category;
		try {
			Common.mouseOver("xpath", "//a[contains(text(),'" + category + "')]");
		} catch (Exception e) {
			Common.clickElement("xpath", "//ul[@class='megamenu-list']/li[1]/div[1]/button");
		}
		
		Common.clickElement("xpath", "//a[contains(text(),'" + category + "')]");
		expectedResult = "User should select the " + category + "category";
		int sizebotteles = Common.findElements("xpath", "//a[contains(text(),'" + category + "')]").size();
		Common.assertionCheckwithReport(sizebotteles > 0,
				"validating the product category as" + category + "from navigation menu ", expectedResult,
				"Selected the " + category + " category", "User unabel to click" + category + "");


		try {
			
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			
			Sync.waitElementClickable("xpath", "//button[@title='Add to Cart']");
			List<WebElement> element = Common.findElements("xpath", "//button[@title='Add to Cart']");
            element.get(2).click();
        	expectedResult = "Product should add to Cart";

			int cartbuttonsize = Common.findElements("xpath", "(//button[@title='Add to Cart'])[2]").size();
			Common.assertionCheckwithReport(cartbuttonsize > 0, "validating the add product to cart", expectedResult,
					"Added Product to Cart", "User unabel add product to cart");
		
		} catch (Exception | Error e) {

			ExtenantReportUtils.addFailedLog("validating the product add to cart", expectedResult,
					"User unabel to add product to cart", Common.getscreenShotPathforReport("failed to add product"));
			
			e.printStackTrace();
			Assert.fail();

		}
	}


//div[@class='mh-customization-title-top' and text()='24 oz']//following::a[1]


public void Customize_Bottle_Standed(String bottlesize) throws Exception {

	String expectedResult = "User should land on the home page";
	Thread.sleep(8000);
	int size = Common.findElements("xpath", "//a[@class='logo']").size();
	Common.assertionCheckwithReport(size > 0, " verifying the home page", expectedResult,"Successfully landed on the home page", "User unabel to land on home page");
	
	try {
		Thread.sleep(5000);
         Sync.waitElementPresent("xpath", "//ul[@class='megamenu-list']/li[2]//button");
		 Common.mouseOverClick("xpath", "//ul[@class='megamenu-list']/li[2]//button");
    
      /*  int customeButton=Common.findElements("xpath", "//span[contains(text(),'Create Yours Now')]").size();
        if(customeButton>0) {
        	Sync.waitElementPresent("xpath", "//span[contains(text(),'Create Yours Now')]");
			Common.clickElement("xpath", "//span[contains(text(),'Create Yours Now')]");
        }*/
		 Thread.sleep(3000);
        expectedResult = "It should land successfully on my-hydro-landing page";
		Common.assertionCheckwithReport(Common.getPageTitle().contains("My Hydro™ by Hydro Flask | Customized & Personalized Hydro Flasks"), "validating My hydro-Landing page", expectedResult,
				"successfully land  on my-hydro-landing page", "User unabel to land on my hydro landing page");

		
		
		
	} 
	catch (Exception | Error e) {
         e.printStackTrace();
		report.addFailedLog("validating My hydro-Landing page", expectedResult,
				"User Faield to select My Hydro option", Common.getscreenShotPathforReport("Myhydropage"));
		
		Assert.fail();

	}

	try {
		
		Thread.sleep(5000);
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Sync.waitElementClickable("xpath", "//div[@class='mh-customization-title-top' and text()='"+bottlesize+"']//following::a[1]");
		Common.clickElement("xpath", "//div[@class='mh-customization-title-top' and text()='"+bottlesize+"']//following::a[1]");
		
		
		Thread.sleep(8000);
		System.out.println(Common.getPageTitle());
		Common.assertionCheckwithReport(Common.getPageTitle().equals("MyHydro STANDARD MOUTH"), "It should land on the my hydro standard mouth configurator", "successfully opean the  my hydro standard configurator page", "my-hydro-configurator");
		

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating my hydro configuration page",
				"It should land successfully on the my hydro configurator and select Standard Mouth Bottle ",
				"User Faield to select My Hydro configurator or botttle option",
				Common.getscreenShotPathforReport("My hydro options"));
		Assert.fail();

	}

	

	
	try {
		Thread.sleep(18000);
		
		Common.actionsKeyPress(Keys.ESCAPE);
		
//		selectSide_standard_mouthbottle("24oz");
		select_Capcolor_standardMouthBottle("Black");
		select_Strapcolor_standardMouthBottle("Black");
		select_Bottlecolor_standardMouthBottle("Black");
		select_Bootcolor_standardMouthBottle("Stone");

	
		
	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying myhydro configuration page", expectedResult,"User unabel to change bottele color ",Common.getscreenShotPathforReport("faield change the color myhydro"));
		Assert.fail();

	}
	
	try {
		Sync.waitElementPresent("xpath", "//span[text()='Add To Cart']");
		Common.clickElement("xpath", "//span[text()='Add To Cart']");
		ExtenantReportUtils.addPassLog("verifying myhydro configuration page", "user click add to cart button",
				"user click the add to cart button",
				Common.getscreenShotPathforReport("faield to click add to cart button"));
	} catch (Exception | Error e) {
		ExtenantReportUtils.addFailedLog("verifying myhydro configuration page", "click add to cart",
				"User faield to click add to cart ", Common.getscreenShotPathforReport("add to cart button"));
		Assert.fail();

	}
	
	
}















public void Customize_Bottle_Standed() throws Exception {

	String expectedResult = "User should land on the home page";
	Thread.sleep(8000);
	int size = Common.findElements("xpath", "//a[@class='logo']").size();
	Common.assertionCheckwithReport(size > 0, " verifying the home page", expectedResult,"Successfully landed on the home page", "User unabel to land on home page");
	
	try {
		
         Sync.waitElementPresent("xpath", "//ul[@class='megamenu-list']/li[2]//button");
		 Common.mouseOverClick("xpath", "//ul[@class='megamenu-list']/li[2]//button");
    
      /*  int customeButton=Common.findElements("xpath", "//span[contains(text(),'Create Yours Now')]").size();
        if(customeButton>0) {
        	Sync.waitElementPresent("xpath", "//span[contains(text(),'Create Yours Now')]");
			Common.clickElement("xpath", "//span[contains(text(),'Create Yours Now')]");
        }
		*/ 
        expectedResult = "It should land successfully on my-hydro-landing page";
		Common.assertionCheckwithReport(Common.getPageTitle().equals("My Hydro™ by Hydro Flask | Customized & Personalized Hydro Flasks"), "validating My hydro-Landing page", expectedResult,
				"successfully land  on my-hydro-landing page", "User unabel to land on my hydro landing page");

		
		
		
	} 
	catch (Exception | Error e) {
         e.printStackTrace();
		report.addFailedLog("validating My hydro-Landing page", expectedResult,
				"User Faield to select My Hydro option", Common.getscreenShotPathforReport("Myhydropage"));
		
		Assert.fail();

	}

	try {
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Sync.waitElementClickable("xpath", "//div[contains(text(),'Standard')]/following::a[1]");
		Common.clickElement("xpath", "//div[contains(text(),'Standard')]/following::a[1]");
		
		
		Thread.sleep(8000);
		System.out.println(Common.getPageTitle());
		Common.assertionCheckwithReport(Common.getPageTitle().equals("MyHydro STANDARD MOUTH"), "It should land on the my hydro standard mouth configurator", "successfully opean the  my hydro standard configurator page", "my-hydro-configurator");
		

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating my hydro configuration page",
				"It should land successfully on the my hydro configurator and select Standard Mouth Bottle ",
				"User Faield to select My Hydro configurator or botttle option",
				Common.getscreenShotPathforReport("My hydro options"));
		Assert.fail();

	}

	

	
	try {
		Thread.sleep(18000);
		
		Common.actionsKeyPress(Keys.ESCAPE);
		
		selectSide_standard_mouthbottle("24oz");
		select_Capcolor_standardMouthBottle("Black");
		select_Strapcolor_standardMouthBottle("Black");
		select_Bottlecolor_standardMouthBottle("Black");
		select_Bootcolor_standardMouthBottle("Stone");

	
		
	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying myhydro configuration page", expectedResult,"User unabel to change bottele color ",Common.getscreenShotPathforReport("faield change the color myhydro"));
		Assert.fail();

	}
	
	try {
		Sync.waitElementPresent("xpath", "//span[text()='Add To Cart']");
		Common.clickElement("xpath", "//span[text()='Add To Cart']");
		ExtenantReportUtils.addPassLog("verifying myhydro configuration page", "user click add to cart button",
				"user click the add to cart button",
				Common.getscreenShotPathforReport("faield to click add to cart button"));
	} catch (Exception | Error e) {
		ExtenantReportUtils.addFailedLog("verifying myhydro configuration page", "click add to cart",
				"User faield to click add to cart ", Common.getscreenShotPathforReport("add to cart button"));
		Assert.fail();

	}
	
	
}

public void Customize_Bottle_Wide(String bottlesize) throws Exception {

	String expectedResult = "User should land on the home page";
	Thread.sleep(8000);
	int size = Common.findElements("xpath", "//a[@class='logo']").size();
	Common.assertionCheckwithReport(size > 0, " verifying the home page", expectedResult,"Successfully landed on the home page", "User unabel to land on home page");
	
	try {
		
         Sync.waitElementPresent("xpath", "//ul[@class='megamenu-list']/li[2]//button");
		 Common.mouseOverClick("xpath", "//ul[@class='megamenu-list']/li[2]//button");
    
        int customeButton=Common.findElements("xpath", "//span[contains(text(),'Create Yours Now')]").size();
        if(customeButton>0) {
        	Sync.waitElementPresent("xpath", "//span[contains(text(),'Create Yours Now')]");
			Common.clickElement("xpath", "//span[contains(text(),'Create Yours Now')]");
        }
		 
        expectedResult = "It should land successfully on my-hydro-landing page";
		Common.assertionCheckwithReport(Common.getPageTitle().equals("My Hydro™ by Hydro Flask | Customized & Personalized Hydro Flasks"), "validating My hydro-Landing page", expectedResult,
				"successfully land  on my-hydro-landing page", "User unabel to land on my hydro landing page");

		
		
		
	} 
	catch (Exception | Error e) {
         e.printStackTrace();
		report.addFailedLog("validating My hydro-Landing page", expectedResult,
				"User Faield to select My Hydro option", Common.getscreenShotPathforReport("Myhydropage"));
		
		Assert.fail();

	}

	try {
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Sync.waitElementClickable("xpath", "//div[@class='mh-customization-title-top' and text()='"+bottlesize+"']//following::a[1]");
		Common.clickElement("xpath", "//div[@class='mh-customization-title-top' and text()='"+bottlesize+"']//following::a[1]");
		
		
		Thread.sleep(8000);
		System.out.println(Common.getPageTitle());
		Common.assertionCheckwithReport(Common.getPageTitle().equals("MyHydro"), "It should land on the my hydro wide mouth configurator", "successfully opean the  my hydro wide bootle configurator page", "my-hydro-configurator");
		

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating my hydro configuration page",
				"It should land successfully on the my hydro configurator and select Wide Mouth Bottle ",
				"User Faield to select My Hydro configurator or botttle option",
				Common.getscreenShotPathforReport("My hydro options wide"));
		Assert.fail();

	}

	Thread.sleep(18000);

	
	try {
		Thread.sleep(18000);
		
		Common.actionsKeyPress(Keys.ESCAPE);
		
		
		selectSide_wide_mouthbottle("32oz");
		select_Capcolor_WideMouthBottle("Black");
		select_Strapcolor_wideMouthBottle("Black");
		select_Bottlecolor_wideMouthBottle("Black");
		select_Bootcolor_wideMouthBottle("Stone");

	
		
	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying myhydro configuration page", expectedResult,"User unabel to change bottele color ",Common.getscreenShotPathforReport("faield change the color myhydro"));
		Assert.fail();

	}
	
	try {
		Sync.waitElementPresent("xpath", "//span[text()='Add To Cart']");
		Common.clickElement("xpath", "//span[text()='Add To Cart']");
		ExtenantReportUtils.addPassLog("verifying myhydro configuration page", "user click add to cart button",
				"user click the add to cart button",
				Common.getscreenShotPathforReport("faield to click add to cart button"));
	} catch (Exception | Error e) {
		ExtenantReportUtils.addFailedLog("verifying myhydro configuration page", "click add to cart",
				"User faield to click add to cart ", Common.getscreenShotPathforReport("add to cart button"));
		Assert.fail();

	}
	
	
}



public void Customize_Bottle_Wide() throws Exception {

	String expectedResult = "User should land on the home page";
	Thread.sleep(8000);
	int size = Common.findElements("xpath", "//a[@class='logo']").size();
	Common.assertionCheckwithReport(size > 0, " verifying the home page", expectedResult,"Successfully landed on the home page", "User unabel to land on home page");
	
	try {
		
         Sync.waitElementPresent("xpath", "//ul[@class='megamenu-list']/li[2]//button");
		 Common.mouseOverClick("xpath", "//ul[@class='megamenu-list']/li[2]//button");
    
        int customeButton=Common.findElements("xpath", "//span[contains(text(),'Create Yours Now')]").size();
        if(customeButton>0) {
        	Sync.waitElementPresent("xpath", "//span[contains(text(),'Create Yours Now')]");
			Common.clickElement("xpath", "//span[contains(text(),'Create Yours Now')]");
        }
		 
        expectedResult = "It should land successfully on my-hydro-landing page";
		Common.assertionCheckwithReport(Common.getPageTitle().equals("My Hydro™ by Hydro Flask | Customized & Personalized Hydro Flasks"), "validating My hydro-Landing page", expectedResult,
				"successfully land  on my-hydro-landing page", "User unabel to land on my hydro landing page");

		
		
		
	} 
	catch (Exception | Error e) {
         e.printStackTrace();
		report.addFailedLog("validating My hydro-Landing page", expectedResult,
				"User Faield to select My Hydro option", Common.getscreenShotPathforReport("Myhydropage"));
		
		Assert.fail();

	}

	try {
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Sync.waitElementClickable("xpath", "//div[contains(text(),'Wide')]/following::a[1]");
		Common.clickElement("xpath", "//div[contains(text(),'Wide')]/following::a[1]");
		
		
		Thread.sleep(8000);
		System.out.println(Common.getPageTitle());
		Common.assertionCheckwithReport(Common.getPageTitle().equals("MyHydro"), "It should land on the my hydro wide mouth configurator", "successfully opean the  my hydro wide bootle configurator page", "my-hydro-configurator");
		

	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating my hydro configuration page",
				"It should land successfully on the my hydro configurator and select Wide Mouth Bottle ",
				"User Faield to select My Hydro configurator or botttle option",
				Common.getscreenShotPathforReport("My hydro options wide"));
		Assert.fail();

	}

	Thread.sleep(18000);

	
	try {
		Thread.sleep(18000);
		
		Common.actionsKeyPress(Keys.ESCAPE);
		
		
		selectSide_wide_mouthbottle("32oz");
		select_Capcolor_WideMouthBottle("Black");
		select_Strapcolor_wideMouthBottle("Black");
		select_Bottlecolor_wideMouthBottle("Black");
		select_Bootcolor_wideMouthBottle("Stone");

	
		
	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying myhydro configuration page", expectedResult,"User unabel to change bottele color ",Common.getscreenShotPathforReport("faield change the color myhydro"));
		Assert.fail();

	}
	
	try {
		Sync.waitElementPresent("xpath", "//span[text()='Add To Cart']");
		Common.clickElement("xpath", "//span[text()='Add To Cart']");
		ExtenantReportUtils.addPassLog("verifying myhydro configuration page", "user click add to cart button",
				"user click the add to cart button",
				Common.getscreenShotPathforReport("faield to click add to cart button"));
	} catch (Exception | Error e) {
		ExtenantReportUtils.addFailedLog("verifying myhydro configuration page", "click add to cart",
				"User faield to click add to cart ", Common.getscreenShotPathforReport("add to cart button"));
		Assert.fail();

	}
	
	
}


public void selectSide_standard_mouthbottle(String Bottlesize) {
	try {
	Sync.waitElementPresent("xpath", "//div[@id='fc-nav-flyout-header-80251']");
	Common.clickElement("xpath", "//div[@id='fc-nav-flyout-header-80251']");

	if(Bottlesize.equals("21oz")) {
		Sync.waitElementPresent("xpath", "//div[contains(@aria-label,'21 oz')]");
		Common.clickElement("xpath", "//div[contains(@aria-label,'21 oz')]");
		ExtenantReportUtils.addPassLog("verifying myhydro standard mouth bottle size", "selecting the standad moth bottle size is "+Bottlesize,
				"User successfully click add to cartstandard mouth bottle size is "+Bottlesize, Common.getscreenShotPathforReport("BottleSize"));
	}
	else if(Bottlesize.equals("24oz")) {
		Sync.waitElementPresent("xpath", "//div[contains(@aria-label,'24 oz')]");
		Common.clickElement("xpath", "//div[contains(@aria-label,'24 oz')]");
		ExtenantReportUtils.addPassLog("verifying myhydro standard mouth bottle size", "selecting the standad moth bottle size is "+Bottlesize,
				"User successfully click add to cartstandard mouth bottle size is "+Bottlesize, Common.getscreenShotPathforReport("BottleSize"));
	}
}
	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying myhydro standard mouth bottle size", "selecting the standad moth bottle size is "+Bottlesize,
				"User faield to click add to cartstandard mouth bottle size is "+Bottlesize, Common.getscreenShotPathforReport("BottleSize"));
		Assert.fail();

	}
}




public void selectSide_wide_mouthbottle(String Bottlesize) {
	try {
	Sync.waitElementPresent("xpath", "//div[@id='fc-nav-flyout-header-80249']");
	Common.clickElement("xpath", "//div[@id='fc-nav-flyout-header-80249']");

	if(Bottlesize.equals("20oz")) {
		Sync.waitElementPresent("xpath", "//div[contains(@aria-label,'20 oz')]");
		Common.clickElement("xpath", "//div[contains(@aria-label,'20 oz')]");
		ExtenantReportUtils.addPassLog("verifying myhydro standard mouth bottle size", "selecting the standad moth bottle size is "+Bottlesize,
				"User successfully click add to cartstandard mouth bottle size is "+Bottlesize, Common.getscreenShotPathforReport("BottleSize"));
	}
	else if(Bottlesize.equals("32oz")) {
		Sync.waitElementPresent("xpath", "//div[contains(@aria-label,'32 oz')]");
		Common.clickElement("xpath", "//div[contains(@aria-label,'32 oz')]");
		ExtenantReportUtils.addPassLog("verifying myhydro standard mouth bottle size", "selecting the standad moth bottle size is "+Bottlesize,
				"User successfully click add to cartstandard mouth bottle size is "+Bottlesize, Common.getscreenShotPathforReport("BottleSize"));
	}
	else if(Bottlesize.equals("40oz")) {
		Sync.waitElementPresent("xpath", "//div[contains(@aria-label,'40 oz')]");
		Common.clickElement("xpath", "//div[contains(@aria-label,'40 oz')]");
		ExtenantReportUtils.addPassLog("verifying myhydro standard mouth bottle size", "selecting the standad moth bottle size is "+Bottlesize,
				"User successfully click add to cartstandard mouth bottle size is "+Bottlesize, Common.getscreenShotPathforReport("BottleSize"));
	}
}
	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying myhydro standard mouth bottle size", "selecting the standad moth bottle size is "+Bottlesize,
				"User faield to click add to cartstandard mouth bottle size is "+Bottlesize, Common.getscreenShotPathforReport("BottleSize"));
		Assert.fail();

	}
}




public void select_Capcolor_standardMouthBottle(String Color) {
try {
	Sync.waitElementClickable("xpath", "//div[@id='fc-nav-flyout-header-80263']"); 
	
	Common.clickElement("xpath", "//div[@id='fc-nav-flyout-header-80263']");  


	Common.clickElement("xpath", "//div[@aria-describedby='fc-ca-90031-fieldset-description' and contains(@aria-label,'"+Color+"')]/span");
    ExtenantReportUtils.addPassLog("verifying myhydro standard moutH CAP COLOR", "selecting the standad moth bottle cap color is "+Color,"User successfully click standard mouth bottle cap color "+Color, Common.getscreenShotPathforReport("capcolor"));


}

catch (Exception | Error e) {
	ExtenantReportUtils.addFailedLog("verifying myhydro standard moutH CAP COLOR", "selecting the standad moth bottle cap color is "+Color,
			"User faield to click standard mouth bottle cap color is "+Color, Common.getscreenShotPathforReport("capcolor"));
	Assert.fail();

}
}
public void select_Capcolor_WideMouthBottle(String Color) {
try {
	Sync.waitElementClickable("xpath", "//div[@id='fc-nav-flyout-header-80254']");  
	Common.clickElement("xpath", "//div[@id='fc-nav-flyout-header-80254']");  
	Common.clickElement("xpath", "//div[@aria-describedby='fc-ca-90164-fieldset-description' and contains(@aria-label,'"+Color+"')]/span");
    ExtenantReportUtils.addPassLog("verifying myhydro wide moutH CAP COLOR", "selecting the wide moth bottle cap color is "+Color,"User successfully click wide mouth bottle cap color "+Color, Common.getscreenShotPathforReport("capcolor"));


}

catch (Exception | Error e) {
	ExtenantReportUtils.addFailedLog("verifying myhydro wide moutH CAP COLOR", "selecting the wide moth bottle cap color is "+Color,
			"User faield to click wide mouth bottle cap color is "+Color, Common.getscreenShotPathforReport("capcolor"));
	Assert.fail();

}
}
public void select_Strapcolor_standardMouthBottle(String Color) {
	try {
		Sync.waitElementClickable("xpath", "//div[@id='fc-nav-flyout-header-80262']");  
		Common.clickElement("xpath", "//div[@id='fc-nav-flyout-header-80262']"); 
        Common.clickElement("xpath", "//div[@aria-describedby='fc-ca-80262-fieldset-description' and contains(@aria-label,'"+Color+"')]/span");
	    ExtenantReportUtils.addPassLog("verifying myhydro standard moutH strap COLOR", "selecting the standad  bottle strap cap color is "+Color,"User successfully click standard mouth bottle strap color "+Color, Common.getscreenShotPathforReport("strap"));
	
	}

	catch (Exception | Error e) {
		ExtenantReportUtils.addFailedLog("verifying myhydro standard moutH CAP COLOR", "selecting the standad moth bottle cap color is "+Color,
				"User faield to click standard mouth bottle cap color is "+Color, Common.getscreenShotPathforReport("capcolor"));
		Assert.fail();

	}
	}
	
public void select_Strapcolor_wideMouthBottle(String Color) {
	try {
		Sync.waitElementClickable("xpath", "//div[@id='fc-nav-flyout-header-80253']");  
		Common.clickElement("xpath", "//div[@id='fc-nav-flyout-header-80253']");
        Common.clickElement("xpath", "//div[@aria-describedby='fc-ca-80253-fieldset-description' and contains(@aria-label,'"+Color+"')]/span");
	    ExtenantReportUtils.addPassLog("verifying myhydro Wide moutH strap COLOR", "selecting the wide  bottle strap cap color is "+Color,"User successfully click wide mouth bottle strap color "+Color, Common.getscreenShotPathforReport("strap"));
	
	}

	catch (Exception | Error e) {
		ExtenantReportUtils.addFailedLog("verifying myhydro wide moutH CAP COLOR", "selecting the wide moth bottle cap color is "+Color,
				"User faield to click wide mouth bottle cap color is "+Color, Common.getscreenShotPathforReport("capcolor"));
		Assert.fail();

	}
	}

public void select_Bottlecolor_standardMouthBottle(String Color) {
	try {
		Sync.waitElementClickable("xpath", "//div[@id='fc-nav-flyout-header-80268']");  
		Common.clickElement("xpath", "//div[@id='fc-nav-flyout-header-80268']");  
        Common.clickElement("xpath", "//div[@aria-describedby='fc-ca-80268-fieldset-description' and contains(@aria-label,'"+Color+"')]/span");
	    ExtenantReportUtils.addPassLog("verifying myhydro standard moutH bottle COLOR", "selecting the standad  bottle  color is "+Color,"User successfully click standard mouth bottle  color "+Color, Common.getscreenShotPathforReport("bottle"));
	
	}

	catch (Exception | Error e) {
		ExtenantReportUtils.addFailedLog("verifying myhydro standard moutH bottle COLOR", "selecting the standad moth bottle bottle color is "+Color,
				"User faield to click standard mouth bottle  color is "+Color, Common.getscreenShotPathforReport("bottle"));
		Assert.fail();

	}
	}

public void select_Bottlecolor_wideMouthBottle(String Color) {
	try {
		Sync.waitElementClickable("xpath", "//div[@id='fc-nav-flyout-header-80266']");  
        Common.clickElement("xpath", "//div[@aria-describedby='fc-ca-80266-fieldset-description' and contains(@aria-label,'"+Color+"')]/span");
	    ExtenantReportUtils.addPassLog("verifying myhydro wide mouth bottle COLOR", "selecting the wide bottle  color is "+Color,"User successfully click wide mouth bottle  color "+Color, Common.getscreenShotPathforReport("bottle"));
	
	}

	catch (Exception | Error e) {
		ExtenantReportUtils.addFailedLog("verifying myhydro wide mouth bottle COLOR", "selecting the wide moth bottle bottle color is "+Color,
				"User faield to click wide mouth bottle  color is "+Color, Common.getscreenShotPathforReport("bottle"));
		Assert.fail();

	}
	}

public void select_Bootcolor_standardMouthBottle(String Color) {
	try {
		Sync.waitElementClickable("xpath", "//div[@id='fc-nav-flyout-header-80270']");  
        Common.clickElement("xpath", "//div[@id='fc-nav-flyout-header-80270']");
	    ExtenantReportUtils.addPassLog("verifying myhydro standard moutH bottle COLOR", "selecting the standad  bottle boot  color is "+Color,"User successfully click standard mouth bottle boot color "+Color, Common.getscreenShotPathforReport("boot"));
	
	}

	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying myhydro standard moutH bottle boot COLOR", "selecting the standad moth bottle boot color is "+Color,
				"User faield to click standard mouth boot color is "+Color, Common.getscreenShotPathforReport("boot"));
		Assert.fail();

	}
	}

public void select_Bootcolor_wideMouthBottle(String Color) {
	try {
		Sync.waitElementClickable("xpath", "//div[@id='fc-nav-flyout-header-80269']");  
        Common.clickElement("xpath", "//div[@id='fc-nav-flyout-header-80269']");
	    ExtenantReportUtils.addPassLog("verifying myhydro standard moutH bottle COLOR", "selecting the standad  bottle boot  color is "+Color,"User successfully click standard mouth bottle boot color "+Color, Common.getscreenShotPathforReport("boot"));
	
	}

	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying myhydro standard moutH bottle boot COLOR", "selecting the standad moth bottle boot color is "+Color,
				"User faield to click standard mouth boot color is "+Color, Common.getscreenShotPathforReport("boot"));
		Assert.fail();

	}
	}

public void searchproduct_addtocart(String dataSet) throws Exception {
	Thread.sleep(5000);

	try {
		Sync.waitElementVisible("xpath", "//form[@id='search_mini_form']//label");
	
		Common.clickElement("xpath", "//form[@id='search_mini_form']//label");
		Common.textBoxInput("xpath", "//input[@id='search']", dataSet);
		ExtenantReportUtils.addPassLog("validating Search box", "enter product name will display in search box",
				"user enter the product name in  search box", Common.getscreenShotPathforReport("searchproduct"));
	} catch (Exception | Error e) {
		ExtenantReportUtils.addFailedLog("validating Search box", "enter product name will display in search box",
				"User failed to enter product name", Common.getscreenShotPathforReport("searchproduct"));
		Assert.fail();

	}
	try {
		
		Common.actionsKeyPress(Keys.ENTER);
		Sync.waitPageLoad();
		Sync.waitElementClickable("xpath","//a[text()='"+dataSet+"']");
		Common.scrollIntoView("xpath","//a[text()='"+dataSet+"']");
		Common.clickElement("xpath","//a[text()='"+dataSet+"']");
		
		
		Thread.sleep(3000);
		int sizeaddtocart=Common.findElements("xpath", "(//button[@title='Add to Cart']//span)[1]").size();
		Common.clickElement("xpath", "(//button[@title='Add to Cart']//span)[1]");
		Common.assertionCheckwithReport(sizeaddtocart>0, "Verifying the add to cart button in PDP page", "user click the add to cart button", "user successfully click the add to cart button", "user failed click the add to cart button");
		
		}

	 catch (Exception | Error e)
{
		 e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating search product", "user find  product in search",
				"User failed find  product in search",
				Common.getscreenShotPathforReport("searchproduct"));
		Assert.fail();

	}
}

public void searchproduct_2(String dataSet) throws Exception {
	Thread.sleep(5000);

	try {
		Sync.waitElementVisible("xpath", "//form[@id='search_mini_form']//label");
	
		Common.clickElement("xpath", "//form[@id='search_mini_form']//label");
		Common.textBoxInput("xpath", "//input[@id='search']", dataSet);
		ExtenantReportUtils.addPassLog("validating Search box", "enter product name will display in search box",
				"user enter the product name in  search box", Common.getscreenShotPathforReport("searchproduct"));
	} catch (Exception | Error e) {
		ExtenantReportUtils.addFailedLog("validating Search box", "enter product name will display in search box",
				"User failed to enter product name", Common.getscreenShotPathforReport("searchproduct"));
		Assert.fail();

	}
	try {
		
		Common.actionsKeyPress(Keys.ENTER);
		Sync.waitElementClickable("xpath","//a[text()='20 oz Wide Mouth']");
		Common.scrollIntoView("xpath","//a[text()='20 oz Wide Mouth']");
		Common.clickElement("xpath","//a[text()='20 oz Wide Mouth']");
		
		int Add=Common.findElements("xpath", "//button[@class='hf-94-close']").size();
		if(Add>0)
		{
			Common.clickElement("xpath", "//button[@class='hf-94-close']");
		}
		
		Thread.sleep(10000);
		int sizeaddtocart=Common.findElements("xpath","(//button[@title='Add to Cart']//span)[1]").size();
		Common.clickElement("xpath", "(//button[@title='Add to Cart']//span)[1]");
		Common.assertionCheckwithReport(sizeaddtocart>0, "Verifying the add to cart button in PDP page", "user click the add to cart button", "user successfully click the add to cart button", "user failed click the add to cart button");
		
		}

	 catch (Exception | Error e)
{
		ExtenantReportUtils.addFailedLog("validating search product", "user find  product in search",
				"User failed find  product in search",
				Common.getscreenShotPathforReport("searchproduct"));
		Assert.fail();

	}
}


public void verifying_letsGo(){
	try{
	Thread.sleep(5000);
	Sync.waitPageLoad();
	Common.clickElement("xpath", "//ul[@class='megamenu-list']/li[3]/div[1]");
	Common.clickElement("xpath", "//span[text()='Let’s Go!']");
	//Common.clickElement("xpath", "//ul[@class='megamenu-list']/li[3]//ul/li[1]/a");
	Common.assertionCheckwithReport(Common.getPageTitle().equals("Let’s Go! | Hydro Flask - One lifetime. Fill Often"), "verifying Header link of Lets go","user open the Lets go page", "user successfully open the header link lets go","Failed open the header link lets go");
	
	}
	catch (Exception | Error e) {
		e.printStackTrace();
        ExtenantReportUtils.addFailedLog("validating Header Links Lets go ","user open the lets go option","User unabel open the header link lets go",Common.getscreenShotPathforReport("letsgo"));
	    Assert.fail();

	}
}
public void verifying_Parks_For_All(){
	try{
	Thread.sleep(5000);
	Sync.waitPageLoad();
	
	
    Common.clickElement("xpath", "//ul[@class='megamenu-list']/li[3]/div[1]");
    Common.clickElement("xpath", "//span[text()='Parks For All']");
	//Common.clickElement("xpath", "//ul[@class='megamenu-list']/li[3]//ul/li[2]/a");
	Common.assertionCheckwithReport(Common.getPageTitle().equals("Parks For All | Hydro Flask"), "verifying Header link of Parks For All","user open the Parks For All page", "user successfully open the header link Parks For All","Failed open the header link Parks For All");
	
	}
	catch (Exception | Error e) {
		e.printStackTrace();
        ExtenantReportUtils.addFailedLog("validating Header Links Parks For All","user open the Parks For All option","User unabel open the header link Parks For All",Common.getscreenShotPathforReport("Parks For All"));
	    Assert.fail();

	}
}

public void verifying_OurStory(){
	try{
	Thread.sleep(5000);
	Sync.waitPageLoad();
	
	Common.clickElement("xpath", "//ul[@class='megamenu-list']/li[3]/div[1]");
	//Common.clickElement("xpath", "//ul[@class='megamenu-list']/li[3]//ul/li[3]/a");
	Common.clickElement("xpath", "//span[text()='Our Story']");
	Common.assertionCheckwithReport(Common.getPageTitle().equals("We are Hydro Flask"), "verifying Header link of Our Story","user open the Our Story page", "user successfully open the header link Our Story","Failed open the header link Our Story");
	
	}
	catch (Exception | Error e) {
		e.printStackTrace();
        ExtenantReportUtils.addFailedLog("validating Header Links Our Story","user open the Our Story option","User unabel open the header Our Story",Common.getscreenShotPathforReport("Our Story"));
	    Assert.fail();

	}
}

public void verifying_WSL_Partnership(){
	try{
	Thread.sleep(5000);
	Sync.waitPageLoad();
	Common.clickElement("xpath", "//ul[@class='megamenu-list']/li[3]/div[1]");
	
	Common.clickElement("xpath", "//span[text()='WSL Partnership']");
	//Common.clickElement("xpath", "//ul[@class='megamenu-list']/li[3]//ul/li[4]/a");
	Common.assertionCheckwithReport(Common.getPageTitle().equals("World Surf League Partnership | Hydro Flask®"), "verifying Header link WSL Partnership","user open the WSL Partnership", "user successfully open the header link WSL Partnership","Failed open the WSL Partnership");
	
	}
	catch (Exception | Error e) {
		e.printStackTrace();
        ExtenantReportUtils.addFailedLog("validating Header Links WSL Partnership","user open the WSL Partnership","User unabel open the WSL Partnership",Common.getscreenShotPathforReport("WSL Partnership"));
	    Assert.fail();

	}
}

public void verifying_Contact(){
	try{
	Thread.sleep(5000);
	Sync.waitPageLoad();
	Common.clickElement("xpath", "//ul[@class='megamenu-list']/li[3]/div[1]");
	Common.clickElement("xpath", "//span[text()='Contact']");
	//Common.clickElement("xpath", "//ul[@class='megamenu-list']/li[5]//ul/li[5]/a");
	Common.assertionCheckwithReport(Common.getPageTitle().equals("Contact Hydro Flask"), "verifying Header link contact","user open the contact header link", "user successfully open the header link contact","Failed open the contact");
	
	}
	catch (Exception | Error e) {
		e.printStackTrace();
        ExtenantReportUtils.addFailedLog("validating Header Links contact link","user open the contact headerlink","User unabel open the contact link",Common.getscreenShotPathforReport("contact"));
	    Assert.fail();

	}
}

public void click_trackorder(){
	try{
	Common.actionsKeyPress(Keys.END);
	Common.clickElement("xpath", "//a[text()='Track Your Order']");
	
	Common.assertionCheckwithReport(Common.getPageTitle().equals("Track Order"), "Verifying trackorder page", "after click the tracker order button it will navigate ttack order page", "successfully open the track order page", "Failed to opean the track order page");
}
catch (Exception | Error e) {
	e.printStackTrace();
    ExtenantReportUtils.addFailedLog("validating trackorder","user open the trackorder page","User unabel open the trackorder",Common.getscreenShotPathforReport("trackorder"));
    Assert.fail();

}
}

public void Tack_orderPage_form(String dataSet){

try{
  Sync.waitElementPresent("id", "order_id");
  Common.textBoxInput("id", "order_id",data.get(dataSet).get("oderId"));
  
  Sync.waitElementPresent("id", "billing_last_name");
  Common.textBoxInput("id", "billing_last_name",data.get(dataSet).get("Billinglastname"));
  
  Sync.waitElementPresent("id", "email");
  Common.textBoxInput("id", "email",data.get(dataSet).get("billingemail"));
  
  Sync.waitElementPresent("xpath", "//button[@title='See Status']");
  Common.clickElement("xpath", "//button[@title='See Status']");
  Thread.sleep(6000);
  int sizeelement= Common.findElements("xpath", "//a[text()='Order Details']").size();
  Common.assertionCheckwithReport(sizeelement>0, "verifying order status form", "order tracking information page navigation","successfully order tracking information page ", "Failed to navigate tracking order page infromation");
	
  
  
}
	catch (Exception | Error e) {
		e.printStackTrace();
	    ExtenantReportUtils.addFailedLog("verifying order status form","order tracking information page navigation","User unabel to navigate track ordepage",Common.getscreenShotPathforReport("trackorderpag"));
	    Assert.fail();

	}
}

public void gustuserorderStatus(){
	click_trackorder();
	Tack_orderPage_form("GustUserOrderdetiles");
	
	try{
		
	Common.clickElement("xpath", "//a[text()='Order Details']");
	Common.assertionCheckwithReport(Common.getPageTitle().equals("Customer Login"), "validating gust user view order option", "after view order it will navigate to login page", "sucessfully navigate login page", "Failed to nivigate login page");
    
	}
	catch (Exception | Error e) {
		e.printStackTrace();
	    ExtenantReportUtils.addFailedLog("validating gust user view order option","order tracking information page navigation","User unabel to navigate login page",Common.getscreenShotPathforReport("loginpage"));
	    Assert.fail();

	}
}
public void loginorderfrom(String dataSet){
	try{
		Thread.sleep(5000);
	Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
	Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
	Common.clickElement("id", "send2");
	Thread.sleep(5000);
	Common.assertionCheckwithReport(Common.getPageTitle().equals("My Orders"), "validating order status", "it will navigate to my order page", "successfully navigate to my orders page", "Failed navigate to my orders page");
	
	}
	catch (Exception | Error e) {
		e.printStackTrace();
	    ExtenantReportUtils.addFailedLog("validating order status page","it will navigate to my order page","User unabel to navigate my orders page",Common.getscreenShotPathforReport("myorderpageS"));
	    Assert.fail();

	}
}

public void registereduserStatus(String Logininfromatiom){
	click_trackorder();
	Tack_orderPage_form("registereduserOrderdetiles");
	
	try{
		
	Common.clickElement("xpath", "//a[text()='Order Details']");
	Common.assertionCheckwithReport(Common.getPageTitle().equals("Customer Login"), "validating registered user view order option", "after view order it will navigate to login page", "sucessfully navigate login page", "Failed to nivigate login page");
	loginorderfrom(Logininfromatiom);
	
	}
	catch (Exception | Error e) {
		e.printStackTrace();
	    ExtenantReportUtils.addFailedLog("validating gegistered user view order option","order tracking information page navigation","User unabel to navigate login page",Common.getscreenShotPathforReport("loginpage"));
	    Assert.fail();

	}

}

public void socialLinkValidation(String dataSet){
	
	String socalLinks =data.get(dataSet).get("Links");
	String [] socallinksarry=socalLinks.split(",");
	int i=0;
	try{
	for(i=0;i<socallinksarry.length;i++){
		Common.actionsKeyPress(Keys.END);
		Common.clickElement("xpath", "//a[text()='"+socallinksarry[i]+"']");
		Common.switchWindows();
		System.out.println(Common.getCurrentURL());
		
		if(socallinksarry[i].equals("Twitter")){
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("twitter"), "Verifying Social link  "+socallinksarry[i],"User click the social "+socallinksarry[i], "successfully navigating to social link  "+socallinksarry[i], "Failed to navigate to social link "+socallinksarry[i]);
			Common.closeCurrentWindow();
			Common.switchToFirstTab();
		}
		
		  else if(socallinksarry[i].equals("Instagram")){
		  Common.assertionCheckwithReport(Common.getCurrentURL().contains("instagram"),"Verifying Social link  "+socallinksarry[i],"User click the social "+socallinksarry[i],"successfully navigating to social link  "+socallinksarry[i],"Failed to navigate to social link "+socallinksarry[i]);
		  Common.closeCurrentWindow(); Common.switchToFirstTab(); }
		 
   else	if(socallinksarry[i].equals("Facebook")){
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("www.facebook.com"), "Verifying Social link  "+socallinksarry[i],"User click the social "+socallinksarry[i], "successfully navigating to social link  "+socallinksarry[i], "Failed to navigate to social link "+socallinksarry[i]);
			Common.closeCurrentWindow();
			Common.switchToFirstTab();
		}
   else	if(socallinksarry[i].equals("Pinterest")){
			Common.assertionCheckwithReport(Common.getCurrentURL().contains("pinterest"), "Verifying Social link  "+socallinksarry[i],"User click the social "+socallinksarry[i], "successfully navigating to social link  "+socallinksarry[i], "Failed to navigate to social link "+socallinksarry[i]);
			Common.closeCurrentWindow();
			Common.switchToFirstTab();
		}
   else	if(socallinksarry[i].equals("Youtube")){
		Common.assertionCheckwithReport(Common.getCurrentURL().contains("youtube"), "Verifying Social link  "+socallinksarry[i],"User click the social "+socallinksarry[i], "successfully navigating to social link  "+socallinksarry[i], "Failed to navigate to social link "+socallinksarry[i]);
		Common.closeCurrentWindow();
		Common.switchToFirstTab();
	}
		
		//Common.switchToDefault();
	}
	}
	catch(Exception | Error e){
		e.printStackTrace();
	    ExtenantReportUtils.addFailedLog("Verifying Social link ","click the socal links it will navigating to particular page","User unabel to navigate Social page",Common.getscreenShotPathforReport("socialpage"));
	    Assert.fail();
	}
	
	
}

public void verifying_account_dashboard(){
	
		Sync.waitPageLoad();
		
		try {
			Thread.sleep(4000);
			Common.mouseOverClick("xpath", "//div[@id='account-menu']//li[1]");
			Thread.sleep(2000);
			Common.assertionCheckwithReport(Common.getPageTitle().contains("My Account"), "Validate accountdashboard", "user should land on account dashboard", "successfully navigate to accountdashborad page", "Failed to navigate accountdashboard page");
			
			
		} catch (Exception |Error e){
			e.printStackTrace();
		ExtenantReportUtils.addFailedLog("Validate accountdashboard",
				"User should land on accountdashboard", "User unable to landed on accountdashboard",
				Common.getscreenShotPathforReport("Failed to land on My Account page"));
		Assert.fail();
		} 
	}

public void Account_information() {
	
	Sync.waitPageLoad();
	  try {
		  Thread.sleep(4000);
		Sync.waitElementClickable("xpath", "(//a[contains(text(),'Account Information')])");
		Common.clickElement("xpath", "(//a[contains(text(),'Account Information')])");
		Common.assertionCheckwithReport(Common.getPageTitle().contains("Account Information"), "Validate my account information page", "user should land on my account information page", "successfully navigate to myaccount  page", "Failed to navigate myaccount page");
			
		} catch (Exception |Error e){
		
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("TO validating my account information page",
		"user should land on my account information page", "user unable to landed on my account information page ",
		Common.getscreenShotPathforReport("failed to land on my account_information page"));
		Assert.fail();
			
			}
			}
public void Adress_Book(){
	try {
		 Sync.waitElementClickable("xpath", "(//a[contains(text(),'Address Book')])");
    	 Common.clickElement("xpath", "(//a[contains(text(),'Address Book')])");
    	
 		Common.assertionCheckwithReport(Common.getPageTitle().contains("Address Book"), "Validate my adress book page", "user should land on Adress book page", "successfully navigate to adress  page", "Failed to navigate address page");

    	
    			
    	
    	} catch (Exception |Error e){
    		
    	e.printStackTrace();
    	ExtenantReportUtils.addFailedLog("To validating my adress book page",
    	"user should land on my adress book page", "user unable to landed the adress book page ",
    	Common.getscreenShotPathforReport("failed to land on my adress book page"));
    	Assert.fail();
    		}
    	
        }
public void my_orders() {
	try {
	 Sync.waitElementClickable("xpath", "(//a[contains(text(),'My Orders')])");
     Common.clickElement("xpath", "(//a[contains(text(),'My Orders')])");
	 Common.assertionCheckwithReport(Common.getPageTitle().contains("My Orders"), "Validate my order page", "user should land on my order page", "successfully navigate to my order page", "Failed to navigate my order page");

     }
	catch (Exception |Error e) {
				
    		
    e.printStackTrace();
    ExtenantReportUtils.addFailedLog("To validating my order page",
    "user should land on my order page ", " user unable to landed on my order page",
    Common.getscreenShotPathforReport("failed to land on my orders page"));
    Assert.fail();
    }
			}
		

public void Newsletter_subscription() {
	try {
	Sync.waitElementClickable("xpath", "(//a[contains(text(),'Newsletter Subscription')])");
    Common.clickElement("xpath", "(//a[contains(text(),'Newsletter Subscription')])");
    
	 Common.assertionCheckwithReport(Common.getPageTitle().contains("Newsletter Subscription"), "Validate my newsletter subscription page", "user should land on my newsletter subscription page", "successfully navigate to my newsletter subscription page", "Failed to navigate my newsletter subscription page");

     }
    catch (Exception |Error e) {
		e.printStackTrace();
    ExtenantReportUtils.addFailedLog("To validating my newsletter subscription page ",
    "user should land on my newsletter subscription page", "user unable to land the newsletter subscription ",
    Common.getscreenShotPathforReport("failed to land on my newsletter subscription"));
    Assert.fail();
                 }
                 }

  public void footerLinks_AboutUs_Validation(){
	 String Links= "AboutUs";
	  try{
	  Common.actionsKeyPress(Keys.END);
	  Thread.sleep(3000);
	  
	  Common.clickElement("xpath","//a[text()='About Us']");
	  Sync.waitPageLoad();
	  Common.assertionCheckwithReport(Common.getPageTitle().equals("We are Hydro Flask"),"Validate the Footer link "+Links, "Click the footer link "+Links+"it will navigate to page"+Links, "successfully navigating to "+Links +"page ","Failed to navigate to"+Links+"page");
	  }
	  catch (Exception |Error e) {
			e.printStackTrace();
	    ExtenantReportUtils.addFailedLog("Validate the Footer link "+Links,"Click the footer link "+Links+"it will navigate to page"+Links, "Failed to navigate to"+Links+"page", Common.getscreenShotPathforReport("failed to land on "+Links));
	    Assert.fail();
	  
  }
  }
  public void footerLinks_DealerCentral_Validation(){
		 String Links= "Dealer Central";
		  try{
			  Common.actionsKeyPress(Keys.END);
			  Thread.sleep(3000);
		  Common.clickElement("xpath","//a[text()='Dealer Central']");
		  Sync.waitPageLoad();
		  Common.assertionCheckwithReport(Common.getPageTitle().equals("Hydro Flask Dealer Central | Hydro Flask"),"Validate the Footer link "+Links, "Click the footer link "+Links+"it will navigate to page"+Links, "successfully navigating to "+Links +"page ","Failed to navigate to"+Links+"page");
		  }
		  catch (Exception |Error e) {
				e.printStackTrace();
		    ExtenantReportUtils.addFailedLog("Validate the Footer link "+Links,"Click the footer link "+Links+"it will navigate to page"+Links, "Failed to navigate to"+Links+"page", Common.getscreenShotPathforReport("failed to land on "+Links));
		    Assert.fail();
		  
	  }
	  }

  public void footerLinks_Personalize_Validation(){
		 String Links= "Personalize";
		  try{
		  
		 // Sync.waitElementInvisible("xpath", "//a[text()='Personalize']");
			  Common.actionsKeyPress(Keys.END);
			  Thread.sleep(3000);
		  Common.clickElement("xpath","//a[text()='Personalize']");
		  Sync.waitPageLoad();
		  Common.assertionCheckwithReport(Common.getPageTitle().equals("Hydro Flask Customization"),"Validate the Footer link "+Links, "Click the footer link "+Links+"it will navigate to page"+Links, "successfully navigating to "+Links +"page ","Failed to navigate to"+Links+"page");
		  }
		  catch (Exception |Error e) {
				e.printStackTrace();
		    ExtenantReportUtils.addFailedLog("Validate the Footer link "+Links,"Click the footer link "+Links+"it will navigate to page"+Links, "Failed to navigate to"+Links+"page", Common.getscreenShotPathforReport("failed to land on "+Links));
		    Assert.fail();
		  
	  }
	  }
  public void footerLinks_Affiliates_Validation(){
		 String Links= "Affiliates";
		  try{
			  Common.actionsKeyPress(Keys.END);
			  Thread.sleep(3000);
		  Common.clickElement("xpath","//a[text()='Affiliates']");
		  Sync.waitPageLoad();
		  Common.assertionCheckwithReport(Common.getPageTitle().equals("Hydro Flask Affiliate Program"),"Validate the Footer link "+Links, "Click the footer link "+Links+"it will navigate to page"+Links, "successfully navigating to "+Links +"page ","Failed to navigate to"+Links+"page");
		  }
		  catch (Exception |Error e) {
				e.printStackTrace();
		    ExtenantReportUtils.addFailedLog("Validate the Footer link "+Links,"Click the footer link "+Links+"it will navigate to page"+Links, "Failed to navigate to"+Links+"page", Common.getscreenShotPathforReport("failed to land on "+Links));
		    Assert.fail();
		  
	  }
	  }
  public void footerLinks_ProDeal_Validation(){
		 String Links= "ProDeal";
		  try{
			  Common.actionsKeyPress(Keys.END);
			  Thread.sleep(3000);
		  Common.clickElement("xpath","//a[text()='Pro Deal']");
		  Sync.waitPageLoad();
		  Common.assertionCheckwithReport(Common.getPageTitle().equals("Pro Deal"),"Validate the Footer link "+Links, "Click the footer link "+Links+"it will navigate to page"+Links, "successfully navigating to "+Links +"page ","Failed to navigate to"+Links+"page");
		  }
		  catch (Exception |Error e) {
				e.printStackTrace();
		    ExtenantReportUtils.addFailedLog("Validate the Footer link "+Links,"Click the footer link "+Links+"it will navigate to page"+Links, "Failed to navigate to"+Links+"page", Common.getscreenShotPathforReport("failed to land on "+Links));
		    Assert.fail();
		  
	  }
	  }
  public void footerLinks_Careers_Validation(){
		 String Links= "Careers";
		  try{
			  Common.actionsKeyPress(Keys.END);
			  Thread.sleep(3000);;
		  Common.clickElement("xpath","//a[text()='Careers']");
		  Sync.waitPageLoad();
		  Common.assertionCheckwithReport(Common.getPageTitle().equals("Careers | Helen Of Troy"),"Validate the Footer link "+Links, "Click the footer link "+Links+"it will navigate to page"+Links, "successfully navigating to "+Links +"page ","Failed to navigate to"+Links+"page");
		  }
		  catch (Exception |Error e) {
				e.printStackTrace();
		    ExtenantReportUtils.addFailedLog("Validate the Footer link "+Links,"Click the footer link "+Links+"it will navigate to page"+Links, "Failed to navigate to"+Links+"page", Common.getscreenShotPathforReport("failed to land on "+Links));
		    Assert.fail();
		  
	  }
	  }
  public void footerLinks_FAQ_Validation(){
		 String Links= "FAQ";
		  try{
			  Common.actionsKeyPress(Keys.END);
			  Thread.sleep(3000);;
		  Common.clickElement("xpath","//a[text()='FAQ']");
		  Sync.waitPageLoad();
		  Common.assertionCheckwithReport(Common.getPageTitle().equals("Frequently Asked Questions | Hydro Flask"),"Validate the Footer link "+Links, "Click the footer link "+Links+"it will navigate to page"+Links, "successfully navigating to "+Links +"page ","Failed to navigate to"+Links+"page");
		  }
		  catch (Exception |Error e) {
				e.printStackTrace();
		    ExtenantReportUtils.addFailedLog("Validate the Footer link "+Links,"Click the footer link "+Links+"it will navigate to page"+Links, "Failed to navigate to"+Links+"page", Common.getscreenShotPathforReport("failed to land on "+Links));
		    Assert.fail();
		  
	  }
	  }
  
  public void footerLinks_Contact_Validation(){
		 String Links= "Contact";
		  try{
			  Common.actionsKeyPress(Keys.END);
			  Thread.sleep(3000);
		  Common.clickElement("xpath","//a[text()='Contact']");
		  Sync.waitPageLoad();
		  Common.assertionCheckwithReport(Common.getPageTitle().equals("Contact Hydro Flask"),"Validate the Footer link "+Links, "Click the footer link "+Links+"it will navigate to page"+Links, "successfully navigating to "+Links +"page ","Failed to navigate to"+Links+"page");
		  }
		  catch (Exception |Error e) {
				e.printStackTrace();
		    ExtenantReportUtils.addFailedLog("Validate the Footer link "+Links,"Click the footer link "+Links+"it will navigate to page"+Links, "Failed to navigate to"+Links+"page", Common.getscreenShotPathforReport("failed to land on "+Links));
		    Assert.fail();
		  
	  }
	  }
  public void footerLinks_Shipping_Validation(){
		 String Links= "Shipping";
		  try{
			  Common.actionsKeyPress(Keys.END);
			  Thread.sleep(3000);
		  Common.clickElement("xpath","//a[text()='Shipping']");
		  Sync.waitPageLoad();
		  Common.assertionCheckwithReport(Common.getPageTitle().equals("Frequently Asked Questions | Hydro Flask"),"Validate the Footer link "+Links, "Click the footer link "+Links+"it will navigate to page"+Links, "successfully navigating to "+Links +"page ","Failed to navigate to"+Links+"page");
		  }
		  catch (Exception |Error e) {
				e.printStackTrace();
		    ExtenantReportUtils.addFailedLog("Validate the Footer link "+Links,"Click the footer link "+Links+"it will navigate to page"+Links, "Failed to navigate to"+Links+"page", Common.getscreenShotPathforReport("failed to land on "+Links));
		    Assert.fail();
		  
	  }
	  }
  public void footerLinks_Returns_Validation(){
		 String Links= "Returns";
		  try{
			  Common.actionsKeyPress(Keys.END);
			  Thread.sleep(3000);
		  Common.clickElement("xpath","//a[text()='Returns']");
		  Sync.waitPageLoad();
		  Common.assertionCheckwithReport(Common.getPageTitle().equals("Frequently Asked Questions | Hydro Flask"),"Validate the Footer link "+Links, "Click the footer link "+Links+"it will navigate to page"+Links, "successfully navigating to "+Links +"page ","Failed to navigate to"+Links+"page");
		  }
		  catch (Exception |Error e) {
				e.printStackTrace();
		    ExtenantReportUtils.addFailedLog("Validate the Footer link "+Links,"Click the footer link "+Links+"it will navigate to page"+Links, "Failed to navigate to"+Links+"page", Common.getscreenShotPathforReport("failed to land on "+Links));
		    Assert.fail();
		  
	  }
	  }
  
  public void footerLinks_Warranty_Validation(){
		 String Links= "Warranty";
		  try{
			  Common.actionsKeyPress(Keys.END);
			  Thread.sleep(3000);
		  Common.clickElement("xpath","//a[text()='Warranty']");
		  Sync.waitPageLoad();
		  Common.assertionCheckwithReport(Common.getPageTitle().equals("Frequently Asked Questions | Hydro Flask"),"Validate the Footer link "+Links, "Click the footer link "+Links+"it will navigate to page"+Links, "successfully navigating to "+Links +"page ","Failed to navigate to"+Links+"page");
		  }
		  catch (Exception |Error e) {
				e.printStackTrace();
		    ExtenantReportUtils.addFailedLog("Validate the Footer link "+Links,"Click the footer link "+Links+"it will navigate to page"+Links, "Failed to navigate to"+Links+"page", Common.getscreenShotPathforReport("failed to land on "+Links));
		    Assert.fail();
		  
	  }
	  }
  public void footerLinks_Track_Your_Order_Validation(){
		 String Links= "Track Your Order";
		  try{
			  Common.actionsKeyPress(Keys.END);
			  Thread.sleep(3000);
		  Common.clickElement("xpath","//a[text()='Track Your Order']");
		  Sync.waitPageLoad();
		  Common.assertionCheckwithReport(Common.getPageTitle().equals("Track Order"),"Validate the Footer link "+Links, "Click the footer link "+Links+"it will navigate to page"+Links, "successfully navigating to "+Links +"page ","Failed to navigate to"+Links+"page");
		  }
		  catch (Exception |Error e) {
				e.printStackTrace();
		    ExtenantReportUtils.addFailedLog("Validate the Footer link "+Links,"Click the footer link "+Links+"it will navigate to page"+Links, "Failed to navigate to"+Links+"page", Common.getscreenShotPathforReport("failed to land on "+Links));
		    Assert.fail();
		  
	  }
	  }

  public void footerLinks_Refer_aFriend_Validation(){
		 String Links= "Refer a Friend";
		  try{
			  Common.actionsKeyPress(Keys.END);
			  Thread.sleep(3000);
		  Common.clickElement("xpath","//a[text()='Refer a Friend']");
		  Sync.waitPageLoad();
		  Common.assertionCheckwithReport(Common.getPageTitle().equals("Refer-A-Friend"),"Validate the Footer link "+Links, "Click the footer link "+Links+"it will navigate to page"+Links, "successfully navigating to "+Links +"page ","Failed to navigate to"+Links+"page");
		  }
		  catch (Exception |Error e) {
				e.printStackTrace();
		    ExtenantReportUtils.addFailedLog("Validate the Footer link "+Links,"Click the footer link "+Links+"it will navigate to page"+Links, "Failed to navigate to"+Links+"page", Common.getscreenShotPathforReport("failed to land on "+Links));
		    Assert.fail();
		  
	  }
	  }
  public void footerLinks_Refill_For_Good_Validation(){
		 String Links= "Refill For Good";
		  try{
			  Common.actionsKeyPress(Keys.END);
			  Thread.sleep(3000);
		  Common.clickElement("xpath","//a[text()='Refill For Good']");
		  Sync.waitPageLoad();
		  Common.assertionCheckwithReport(Common.getPageTitle().equals("Refill for Good | Hydro Flask®"),"Validate the Footer link "+Links, "Click the footer link "+Links+"it will navigate to page"+Links, "successfully navigating to "+Links +"page ","Failed to navigate to"+Links+"page");
		  }
		  catch (Exception |Error e) {
				e.printStackTrace();
		    ExtenantReportUtils.addFailedLog("Validate the Footer link "+Links,"Click the footer link "+Links+"it will navigate to page"+Links, "Failed to navigate to"+Links+"page", Common.getscreenShotPathforReport("failed to land on "+Links));
		    Assert.fail();
		  
	  }
	  }
  public void footerLinks_Parks_For_All_Validation(){
		 String Links= "Parks For All";
		  try{
			  Common.actionsKeyPress(Keys.END);
			  Thread.sleep(3000);
		  Common.clickElement("xpath","//a[text()='Parks For All']");
		  Sync.waitPageLoad();
		  Common.assertionCheckwithReport(Common.getPageTitle().equals("Parks For All | Hydro Flask"),"Validate the Footer link "+Links, "Click the footer link "+Links+"it will navigate to page"+Links, "successfully navigating to "+Links +"page ","Failed to navigate to"+Links+"page");
		  }
		  catch (Exception |Error e) {
				e.printStackTrace();
		    ExtenantReportUtils.addFailedLog("Validate the Footer link "+Links,"Click the footer link "+Links+"it will navigate to page"+Links, "Failed to navigate to"+Links+"page", Common.getscreenShotPathforReport("failed to land on "+Links));
		    Assert.fail();
		  
	  }
	  }
  
  public void footerLinks_New_Arrivals_Validation(){
		 String Links= "New Arrivals";
		  try{
			  Common.actionsKeyPress(Keys.END);
			  Thread.sleep(3000);
		  Common.clickElement("xpath","//a[text()='New Arrivals']");
		  Sync.waitPageLoad();
		  Common.assertionCheckwithReport(Common.getPageTitle().equals("Hydro Flask New Arrivals  | Hydro Flask"),"Validate the Footer link "+Links, "Click the footer link "+Links+"it will navigate to page"+Links, "successfully navigating to "+Links +"page ","Failed to navigate to"+Links+"page");
		  }
		  catch (Exception |Error e) {
				e.printStackTrace();
		    ExtenantReportUtils.addFailedLog("Validate the Footer link "+Links,"Click the footer link "+Links+"it will navigate to page"+Links, "Failed to navigate to"+Links+"page", Common.getscreenShotPathforReport("failed to land on "+Links));
		    Assert.fail();
		  
	  }
	  }
  public void footerLinks_Bottles_Validation(){
		 String Links= "Bottles";
		  try{
			  Common.actionsKeyPress(Keys.END);
			  Thread.sleep(3000);
		  Common.clickElement("xpath","//a[text()='Bottles']");
		  Sync.waitPageLoad();
		  Common.assertionCheckwithReport(Common.getPageTitle().equals("Stainless Steel Water Bottle: Insulated Water Bottles | Hydro Flask"),"Validate the Footer link "+Links, "Click the footer link "+Links+"it will navigate to page"+Links, "successfully navigating to "+Links +"page ","Failed to navigate to"+Links+"page");
		  }
		  catch (Exception |Error e) {
				e.printStackTrace();
		    ExtenantReportUtils.addFailedLog("Validate the Footer link "+Links,"Click the footer link "+Links+"it will navigate to page"+Links, "Failed to navigate to"+Links+"page", Common.getscreenShotPathforReport("failed to land on "+Links));
		    Assert.fail();
		  
	  }
	  }
  
  public void footerLinks_Customize_Validation(){
		 String Links= "Customize";
		  try{
			  Common.actionsKeyPress(Keys.END);
			  Thread.sleep(3000);
		  Common.clickElement("xpath","//a[text()='Customize']");
		  Sync.waitPageLoad();
		  Common.assertionCheckwithReport(Common.getPageTitle().equals("My Hydro™ by Hydro Flask | Customized & Personalized Hydro Flasks"),"Validate the Footer link "+Links, "Click the footer link "+Links+"it will navigate to page"+Links, "successfully navigating to "+Links +"page ","Failed to navigate to"+Links+"page");
		  }
		  catch (Exception |Error e) {
				e.printStackTrace();
		    ExtenantReportUtils.addFailedLog("Validate the Footer link "+Links,"Click the footer link "+Links+"it will navigate to page"+Links, "Failed to navigate to"+Links+"page", Common.getscreenShotPathforReport("failed to land on "+Links));
		    Assert.fail();
		  
	  }
	  }
  public void footerLinks_OutdoorKitchen_Validation(){
		 String Links= "Outdoor Kitchen";
		  try{
			  Common.actionsKeyPress(Keys.END);
			  Thread.sleep(3000);
		  Common.clickElement("xpath","//a[text()='Outdoor Kitchen']");
		  Sync.waitPageLoad();
		  Common.assertionCheckwithReport(Common.getPageTitle().equals("Outdoor Kitchen Collection"),"Validate the Footer link "+Links, "Click the footer link "+Links+"it will navigate to page"+Links, "successfully navigating to "+Links +"page ","Failed to navigate to"+Links+"page");
		  }
		  catch (Exception |Error e) {
				e.printStackTrace();
		    ExtenantReportUtils.addFailedLog("Validate the Footer link "+Links,"Click the footer link "+Links+"it will navigate to page"+Links, "Failed to navigate to"+Links+"page", Common.getscreenShotPathforReport("failed to land on "+Links));
		    Assert.fail();
		  
	  }
	  }
  

  public void HydroflaskURLValidation(String dataSet) throws Exception, IOException {
	 String urls=data.get(dataSet).get("Links");
	 int j=0;
	 
	 String[] strArray = urls.split("\\r?\\n");
    for (int i=0; i<strArray.length; i++) {
       System.out.println(strArray[i]);
       
       if (Common.getCurrentURL().contains("stg")) {
    	   
    	   Common.oppenURL((strArray[i]));
    	   int  responcecode=getpageresponce(Common.getCurrentURL());
	       System.out.println(responcecode);
	   
	    if(responcecode==200) {
	    	ExtenantReportUtils.addPassLog("Validating Page URL ", "page configured with products ", "successfully page configured with products", Common.getscreenShotPathforReport("link"+i));
	    }
	    else {
	    	
	    	 j++;
	    	 
	    	 ExtenantReportUtils.addFailedLog("Validating Page URL  "+Common.getCurrentURL(), "page configured with products ", "unable to find page it showing 40 error",Common.getscreenShotPathforReport("link"+i));
	    
	    }
   
    	   
       }
       else if(Common.getCurrentURL().contains("https://www.hydroflask.com/")) {
    	   
    	     Common.oppenURL(strArray[i].replace("jetrails-stg-upgrade", "www"));
    	   
    	    int  responcecode=getpageresponce(Common.getCurrentURL());
    	       System.out.println(responcecode);
    	   
    	    if(responcecode==200) {
    	    	ExtenantReportUtils.addPassLog("Validating Page URL ", "page configured with products ", "successfully page configured with products", Common.getscreenShotPathforReport("link"+i));
    	    }
    	    else {
    	    	
    	    	 j++;
    	    	 
    	    	 ExtenantReportUtils.addFailedLog("Validating Page URL  "+Common.getCurrentURL(), "page configured with products ", "unable to find page it showing 40 error",Common.getscreenShotPathforReport("link"+i));
    	    
    	    }
       }
    }
    
	  if(j>1) {
		  Assert.fail();
	  }  
  }
  
  
  
  public void testing() {
	  
	  
	  
		
	  
		int bundleproducts=Common.findElements("xpath","//div[@class='bundle-product-options-container']/div").size();

		
		
		//div[@class='bundle-product-options-container']/div[1]/following::input[1]/following::label[1]
		
		for(int i=0;i<bundleproducts;i++) {
			
			int value=i+1;
			
			
			System.out.println(value+"***********************");
		 List<WebElement> Listsubcolorproducts=Common.findElements("xpath", "//div[@class='bundle-product-options-container']/div["+value+"]//input/following::label[1]");
			
			
			
		    List<WebElement> Listofcolorinput =Common.findElements("xpath","//div[@class='bundle-product-options-container']/div["+value+"]//input[1]");
			
		    
              for(int j=0;j<Listsubcolorproducts.size();j++) {
              
              	
              	
              	String labelid=Listsubcolorproducts.get(j).getAttribute("for");
              	
              	
              	
				
              	if(Common.findElement("xpath", "//input[@id='"+labelid+"']").getAttribute("disabled")==null) {
      		    	try {
      		    		
      		    		
              		Listsubcolorproducts.get(j).click();
              		
              		System.out.println(Common.findElement("xpath","//input[@id='"+labelid+"']").getAttribute("data-color-label"));
              		
              		
      		    	}
      		    	catch(Exception e) {
      		    		
                             break;
      		    		
      		    	}
              		
      		    }
              }
		}
		    
		}
			
			
			
				
	
	  
  
  
        public void validating_BundlePrdocuts() {
        	
        	
        	//List<WebElement> ListofsubProducts=Common.findElements("xpath", "//div[@class='bundle-product-options-container']/div");
        	

        	int subproductList=Common.findElements("xpath","//div[@class='bundle-product-options-container']/div").size();
        
        	for(int i=0;i<subproductList;i++) {
        		int value=i+1;
        		
        		
        		List<WebElement> ListOfSubproducts=Common.findElements("xpath", "//div[@class='bundle-product-options-container']/div["+value+"]//input/following::label[1]");
        		
        		
        		
        		WebElement Colornames=Common.findElement("xpath", "//div[@class='bundle-product-options-container']/div["+value+"]//strong");
        		WebElement imagecolor=Common.findElement("xpath", "//div[@class='bundle-product-options-container']/div["+value+"]//img");
        		for(int j=0;j<ListOfSubproducts.size();j++) {
        			
        		String attributevalue=	ListOfSubproducts.get(j).getAttribute("disabled");
        			
        		System.out.println(attributevalue);
        			if(attributevalue!=null){
        				
        			}
        			else {
        				
        				if(ListOfSubproducts.get(j).getAttribute("class").contains("js-bundle-label")) {
        				ListOfSubproducts.get(j).click();
        				
        				Common.assertionCheckwithReport(imagecolor.getAttribute("alt").contains(Colornames.getText() )||imagecolor.getAttribute("alt").trim().equals(""), "Vrifying  swatch color button "+Colornames.getText(), "after click color swatch button"+Colornames.getText()+"it must dispaly swatch color image", "successfully color swatch image is dispalying", "Failed load color swatch image");
        				}
        				else {
        					
        				    break;
        				}
        			}
        			
        			        			
        		}
        	}
        	
        }
        
        public void verifyingTax_field() {
        	
        	
        	 try {
    	    	 Sync.waitPageLoad();
    	         
    	        String verifyTax=Common.findElement("xpath", "(//th[text()='Tax*'])").getText();
    	      
    	
    	  		Common.assertionCheckwithReport(verifyTax.contains("Tax"),"Verifying tax in Payment page","Should display tax at right of the Payment details page", "successfully  displayed tax at right of the Payment details page", "Payment Page");
    	   }catch(Exception |Error e) {
    			ExtenantReportUtils.addFailedLog("Verifying tax in Payment page","Should display tax at right of the  Payment details page", "user unable to land on Payment page", Common.getscreenShotPathforReport("failed to land on Payment page"));			
    			Assert.fail();	
    			}
    		
        }
        
        
        
        public void edit_BillingAddress_newuser() {
        	
        	 Common.clickElement("xpath", "//input[@id='billing-address-same-as-shipping-shared']");
 		    
  		   int sizename= Common.findElements("xpath", "//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='firstname']").size();
  	    	
  		   if(sizename>0) {
  			   /*
  			    * Address
  			    */
  			}
        	
        	
        }
        
        public void edit_billingAddress_intermiddeduser() {
        	
        	Common.clickElement("xpath", "//input[@id='billing-address-same-as-shipping-shared']");
        	
        }
        
        
        public void edit_BillingAddress_RegisterUser(String dataSet) {
        	int newaddressbutton=Common.findElements("xpath","//button[contains(@class,'new-billing-address')]").size();
        	if(newaddressbutton>0) {
        		
        		Common.clickElement("xpath","//button[contains(@class,'new-billing-address')]");
        		/*  Oldexisting user
        		 * Add new billing address
        		 */
        		  try {
        		Sync.waitElementPresent("xpath", "//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='street[0]']");
    			Common.textBoxInput("xpath", "//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='street[0]']", data.get(dataSet).get("Street"));
    		
    			Common.textBoxInput("xpath", "//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='city']", data.get(dataSet).get("City"));
    			
    			
    			Common.dropdown("xpath", "//input[@id='billing-address-same-as-shipping-shared']//following::select[@name='region_id']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
    			Thread.sleep(4000);
    		
    			//Common.dropdown("xpath", "//div[contains(@name,'billingAddressime_paymetrictokenize')]//select[@name='region_id']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
    			//Thread.sleep(4000);
    			Common.textBoxInput("xpath", "//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='postcode']", data.get(dataSet).get("postcode"));
    			Common.textBoxInput("xpath", "//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='telephone']", data.get(dataSet).get("phone"));
    			Common.actionsKeyPress(Keys.PAGE_DOWN);
    			Thread.sleep(2000);
    			Common.clickElement("xpath", "//span[text()='Save Address']");
        		  }
    			catch(Exception |Error e) {
       				e.printStackTrace();
       				ExtenantReportUtils.addFailedLog("verifying Billing addres filling", "user will fill the all the Billing address", "faield to add new billing address",Common.getscreenShotPathforReport("faieldssbillingpagefilling"));
       				Assert.fail();
       				
       			}  
        		
        		
        		
        	}
        	else 
        	            {
        		
        		        Common.clickElement("xpath", "//input[@id='billing-address-same-as-shipping-shared']");
        		    
        		      int sizename= Common.findElements("xpath", "//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='firstname']").size();
        	    	
        		    if(sizename>0) {
        			   /* new user 
        			    * Address
        			   else */
        			   try {
        			   Sync.waitElementPresent("xpath", "//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='street[0]']");
           			Common.textBoxInput("xpath", "//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='street[0]']", data.get(dataSet).get("Street"));
           		
           			Common.textBoxInput("xpath", "//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='city']", data.get(dataSet).get("City"));
           			
           			
           			Common.dropdown("xpath", "//input[@id='billing-address-same-as-shipping-shared']//following::select[@name='region_id']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
           			Thread.sleep(4000);
           		
           			//Common.dropdown("xpath", "//div[contains(@name,'billingAddressime_paymetrictokenize')]//select[@name='region_id']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
           			//Thread.sleep(4000);
           			Common.textBoxInput("xpath", "//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='postcode']", data.get(dataSet).get("postcode"));
           			Common.textBoxInput("xpath", "(//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='telephone']", data.get(dataSet).get("phone"));
           			Common.actionsKeyPress(Keys.PAGE_DOWN);
           			Thread.sleep(2000);
           			Common.clickElement("xpath", "//button[@class='action action-update']");
           			
           			Thread.sleep(5000);
           			int sizeerrormessage=Common.findElements("xpath", "//span[contains(text(),'This is a required field')]").size();
           		    System.out.println("error messagess    "+sizeerrormessage);
           			Common.assertionCheckwithReport(sizeerrormessage<=0, "verifying Billing addres filling ", "user will fill the all the billing address", "user fill the shipping address click save button", "faield to add new billing address");
        			   }
        			   catch(Exception |Error e) {
           				e.printStackTrace();
           				ExtenantReportUtils.addFailedLog("verifying Billing addres filling", "user will fill the all the Billing address", "faield to add new billing address",Common.getscreenShotPathforReport("faieldssbillingpagefilling"));
           				Assert.fail();
           				
           			}  
        			   
        			}
        		   
        		   
        		   
        		   int dropdownewAdress=Common.findElements("xpath", "//select[@id='billing_address_id']").size();
        	    	
        	    	
        		
        		    if (dropdownewAdress>0) {
        		    	
        			
        			  Common.dropdown("xpath", "//select[@id='billing_address_id']",SelectBy.VALUE,"New Address");
        			  /*
        			   * new address 
        			   */
        			  try {
        			  Sync.waitElementPresent("xpath", "//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='street[0]']");
             			Common.textBoxInput("xpath", "//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='street[0]']", data.get(dataSet).get("Street"));
             		
             			Common.textBoxInput("xpath", "//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='city']", data.get(dataSet).get("City"));
             			
             			
             			Common.dropdown("xpath", "//input[@id='billing-address-same-as-shipping-shared']//following::select[@name='region_id']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
             			Thread.sleep(4000);
             		
             			//Common.dropdown("xpath", "//div[contains(@name,'billingAddressime_paymetrictokenize')]//select[@name='region_id']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
             			//Thread.sleep(4000);
             			Common.textBoxInput("xpath", "//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='postcode']", data.get(dataSet).get("postcode"));
             			Common.textBoxInput("xpath", "(//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='telephone']", data.get(dataSet).get("phone"));
             			Common.actionsKeyPress(Keys.PAGE_DOWN);
             			Thread.sleep(2000);
             			Common.clickElement("xpath", "//button[@class='action action-update']");
             			
             			Thread.sleep(5000);
             			int sizeerrormessage=Common.findElements("xpath", "//span[contains(text(),'This is a required field')]").size();
             		    System.out.println("error messagess    "+sizeerrormessage);
             			Common.assertionCheckwithReport(sizeerrormessage<=0, "verifying Billing addres filling ", "user will fill the all the billing address", "user fill the shipping address click save button", "faield to add new billing address");
        			  }
        			  catch(Exception |Error e) {
          				e.printStackTrace();
          				ExtenantReportUtils.addFailedLog("verifying Billing addres filling", "user will fill the all the Billing address", "faield to add new billing address",Common.getscreenShotPathforReport("faieldssbillingpagefilling"));
          				Assert.fail();
          				
          			  }  
        			  
        			  }
        		    
        		    else {
        		    	
        		    	Common.clickElement("xpath", "//input[@id='billing-address-same-as-shipping-shared']");		  
        		    	 Common.dropdown("xpath", "//select[@id='billing_address_id']",SelectBy.VALUE,"New Address");
           			  /*
           			   * new address 
           			   */
           			  try {
           			  Sync.waitElementPresent("xpath", "//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='street[0]']");
                			Common.textBoxInput("xpath", "//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='street[0]']", data.get(dataSet).get("Street"));
                		
                			Common.textBoxInput("xpath", "//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='city']", data.get(dataSet).get("City"));
                			
                			
                			Common.dropdown("xpath", "//input[@id='billing-address-same-as-shipping-shared']//following::select[@name='region_id']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
                			Thread.sleep(4000);
                		
                			//Common.dropdown("xpath", "//div[contains(@name,'billingAddressime_paymetrictokenize')]//select[@name='region_id']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
                			//Thread.sleep(4000);
                			Common.textBoxInput("xpath", "//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='postcode']", data.get(dataSet).get("postcode"));
                			Common.textBoxInput("xpath", "(//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='telephone']", data.get(dataSet).get("phone"));
                			Common.actionsKeyPress(Keys.PAGE_DOWN);
                			Thread.sleep(2000);
                			Common.clickElement("xpath", "//button[@class='action action-update']");
                			
                			Thread.sleep(5000);
                			int sizeerrormessage=Common.findElements("xpath", "//span[contains(text(),'This is a required field')]").size();
                		    System.out.println("error messagess    "+sizeerrormessage);
                			Common.assertionCheckwithReport(sizeerrormessage<=0, "verifying Billing addres filling ", "user will fill the all the billing address", "user fill the shipping address click save button", "faield to add new billing address");
           			  }
           			  catch(Exception |Error e) {
             				e.printStackTrace();
             				ExtenantReportUtils.addFailedLog("verifying Billing addres filling", "user will fill the all the Billing address", "faield to add new billing address",Common.getscreenShotPathforReport("faieldssbillingpagefilling"));
             				Assert.fail();
             				
             			  }  
           			  
        			  
        		  }
        		  
        		  
        		
        	}
        	
          }
        
        public void edit_BillingAddress_gustuser(String dataSet) {
        	
        	
        	
        	
        
        	
        	try{
        		    Common.clickElement("xpath", "//input[@id='billing-address-same-as-shipping-shared']");
        		    
        		    Sync.waitElementPresent("xpath", "//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='firstname']");
        		    
        		   int billingaddressform= Common.findElements("xpath", "//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='firstname']").size();
        		    
        		   Common.assertionCheckwithReport(billingaddressform>0, "Filling the Billing address ", "user editing  the billing address", "user sucessfully open the billing address from ", "faield open the bulling address from");
        		    
        			Common.textBoxInput("xpath", "//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='firstname']",data.get(dataSet).get("FirstName"));
        			Sync.waitElementPresent("xpath", "//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='lastname']");
        			Common.textBoxInput("xpath", "//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='lastname']",data.get(dataSet).get("LastName"));
        			
        		
        			Sync.waitElementPresent("xpath", "//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='street[0]']");
        			Common.textBoxInput("xpath", "//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='street[0]']", data.get(dataSet).get("Street"));
        		
        			Common.textBoxInput("xpath", "//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='city']", data.get(dataSet).get("City"));
        			
        			
        			Common.dropdown("xpath", "//input[@id='billing-address-same-as-shipping-shared']//following::select[@name='region_id']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
        			Thread.sleep(4000);
        		
        			//Common.dropdown("xpath", "//div[contains(@name,'billingAddressime_paymetrictokenize')]//select[@name='region_id']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
        			//Thread.sleep(4000);
        			Common.textBoxInput("xpath", "//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='postcode']", data.get(dataSet).get("postcode"));
        			Common.textBoxInput("xpath", "(//input[@id='billing-address-same-as-shipping-shared']//following::input[@name='telephone']", data.get(dataSet).get("phone"));
        			Common.actionsKeyPress(Keys.PAGE_DOWN);
        			Thread.sleep(2000);
        			Common.clickElement("xpath", "//button[@class='action action-update']");
        			
        			Thread.sleep(5000);
        			int sizeerrormessage=Common.findElements("xpath", "//span[contains(text(),'This is a required field')]").size();
        		    System.out.println("error messagess    "+sizeerrormessage);
        			Common.assertionCheckwithReport(sizeerrormessage<=0, "verifying Billing addres filling ", "user will fill the all the billing address", "user fill the shipping address click save button", "faield to add new billing address");
        		}
        			
        			catch(Exception |Error e) {
        				e.printStackTrace();
        				ExtenantReportUtils.addFailedLog("verifying Billing addres filling", "user will fill the all the Billing address", "faield to add new billing address",Common.getscreenShotPathforReport("faieldssbillingpagefilling"));
        				Assert.fail();
        				
        			}  
        		}
        
        
        
      
        
        public void validatingShoppbutton() throws Exception {
        	Thread.sleep(3000);
        	String productname;
        	try {
        	List<WebElement> shshopcategoryOptiont=Common.findElements("xpath", "//button[contains(text(),' Shop')]//following::ul[1]/li/a");
        	
        	System.out.println(shshopcategoryOptiont.size());
        	for(int i=0;i<shshopcategoryOptiont.size();i++) {
        		
        		
        		
        		Common.mouseOverClick("xpath", "//ul[@class='megamenu-list']/li[1]/div[1]/button");
        		Thread.sleep(5000);
        		List<WebElement> shshopcategoryOption=Common.findElements("xpath", "//button[contains(text(),' Shop')]//following::ul[1]/li/a");
        		productname=shshopcategoryOption.get(i).getText();
        	    shshopcategoryOption.get(i).click();
        	    
        	    int  responcecode=getpageresponce(Common.getCurrentURL());
     	       System.out.println(responcecode);
     	   
     	    if(responcecode==200) {
     	    	ExtenantReportUtils.addPassLog("Validating"+ productname +"Page  ", "click the shop linka navigating to "+productname +"Page", "successfully page navigating to "+productname +"PAGE", Common.getscreenShotPathforReport(productname));
     	    }
     	    else {
     	    	
     	    	 
     	    	 
     	    	 ExtenantReportUtils.addFailedLog("Validating Page URL "+ productname +"page", "click the shop linka navigating to "+productname +"Page ", "unable to find page it showing 40 error",Common.getscreenShotPathforReport(productname));
     	          Assert.fail();
     	    }
        	
        	}
        	}
        	catch(Exception |Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying Shop links ", "user validating shop link ad sub links", "faield to load the shop links ",Common.getscreenShotPathforReport("shoplinks"));
				Assert.fail();
				
			}  
        	
        }
       
        //**************************
       
    		
    		
        
        public void addDeliveryAddress(String dataSet,String Street,String City,String postcode,String Region) throws Exception {
    		try {
    			Sync.waitElementVisible("id", "customer-email");
    			Common.textBoxInput("id", "customer-email", data.get(dataSet).get("Email"));
    		} catch (NoSuchElementException e) {
    			checkOut();
    			Common.textBoxInput("id", "customer-email", data.get(dataSet).get("Email"));

    		}
    		String expectedResult = "email field will have email address";
    		try {
    			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",data.get(dataSet).get("FirstName"));
                int size = Common.findElements("id", "customer-email").size();
                Common.assertionCheckwithReport(size > 0, "validating the email address field", expectedResult,"Filled Email address", "unabel to fill the email address");
                Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']",data.get(dataSet).get("LastName"));
    			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']",Street);
    			String Text=Common.getText("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']");
    			
    			

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
    			Common.findElement("xpath", "//form[@id='co-shipping-form']//input[@name='city']").clear();
    			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='city']",City);
    			
    			
    			Common.actionsKeyPress(Keys.PAGE_DOWN);
    			Thread.sleep(3000);
    			try {
    				Common.dropdown("name", "region_id", Common.SelectBy.TEXT, Region);
    			} catch (ElementClickInterceptedException e) {
    				Thread.sleep(3000);
    				Common.dropdown("name", "region_id", Common.SelectBy.TEXT, Region);
    			}
    			Thread.sleep(2000);
    			Common.textBoxInputClear("name", "postcode");
    			Common.textBoxInput("name", "postcode",postcode);
    			Thread.sleep(5000);
    			
    			Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));

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
    		int size=Common.findElements("xpath", "//input[@id='label_method_bestway']").size();
    		if(size>0){
    			Common.clickElement("xpath", "//input[@id='label_method_bestway']");
    		}

    		expectedResult = "shipping address is filled in to the fields";
    		Common.clickElement("xpath", "//button[@data-ac-test='form-shipping-address_action_submit']");

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
        
        
        public String persentageclaluater(double toalvalue,double persentagevalue) {
        	
        	
        	double percentagevalue=(toalvalue*persentagevalue)/100;
        	       NumberFormat nf= NumberFormat.getInstance();
        	       nf.setMaximumFractionDigits(2);
            String caluatedvalue=nf.format(percentagevalue);
        	   
                   return caluatedvalue;
        }
        
        

        public void validating_Employ_Discount_forInlineProducts(int empalydisccount) throws Exception {
        	
        	try {
        	Thread.sleep(4000);
        	
        	Common.scrollIntoView("xpath", "//span[@class='old-price']");
        	
        	String orginalprice=Common.getText("xpath", "//span[@class='old-price']").replace("$", "");
        	
           String subtototal=	Common.getText("xpath", "//tr[@class='totals sub']//span").replace("$", "");
       
        
        
        	double orginalprice_converting=Double.valueOf(orginalprice);
        	
            String disccountammount =persentageclaluater(orginalprice_converting,Double.valueOf(empalydisccount));
            
            double ammount=orginalprice_converting-Double.valueOf(disccountammount);
            
            
    
            System.out.println(String.valueOf(ammount).contains(subtototal));
            
            
            Common.assertionCheckwithReport(String.valueOf(ammount).contains(subtototal), "validating emply discount", "product cost will get discount", "sucessfully we get employdiscount for product", "fail to get emply discount");
       
        	}
        	 catch(Exception |Error e)
	 		{
	 			report.addFailedLog("validating product cost with empaly discount", "empaly discount price must be dispakyed in cart summary", "Faield to get empaly discount", Common.getscreenShotPathforReport("emplaydiscount"));

	 			e.printStackTrace();
	 			Assert.fail();
	 			
	 		}        	}
       
        public void validatingPDPPage_URLS() {
        	
        	try {
    			
    			String Serachproduct ="20 oz Wide Mouth";
    		Thread.sleep(5000);
    		Sync.waitElementVisible("xpath", "//form[@id='search_mini_form']//label");
    		Thread.sleep(8000);
    		Common.clickElement("xpath", "//form[@id='search_mini_form']//label");
    		Common.textBoxInput("xpath", "//input[@id='search']", Serachproduct);
    		Common.actionsKeyPress(Keys.ENTER);
    		
    		Common.clickElement("xpath", "//a[text()='"+Serachproduct+"']");
    		Thread.sleep(4000);
    		ExtenantReportUtils.addPassLog("validating Search box", "enter product name will display in search box",
    				"user enter the product name in  search box", Common.getscreenShotPathforReport("searchproduct1"));
    		
    		
    		
    		
    	} catch (Exception | Error e) {
    		e.printStackTrace();
    		ExtenantReportUtils.addFailedLog("validating Search box", "enter product name will display in search box",
    				"User failed to enter product name", Common.getscreenShotPathforReport("searchproduct1"));
    		Assert.fail();

    	}
        	
        	
        	
        	
        
        try {
        	List<WebElement> pdpcolors=Common.findElements("xpath", "//div[@id='product-options-wrapper']//div[contains(@id,'option-label-color')]");
        	for(int i=0;i<pdpcolors.size();i++) {
        		
        		pdpcolors.get(i).click();
        		Thread.sleep(4000);
        		
        		String clicked_Color=pdpcolors.get(i).getAttribute("data-name");
        		System.out.println(clicked_Color+"selected clocor");
        		
        		System.out.println(Common.getCurrentURL());
        	    Common.assertionCheckwithReport(Common.getCurrentURL().contains(clicked_Color),"Validating PDP page url Color name is passing to url", "select the color of product is "+clicked_Color+" it must pass throw url", " Selected color "+clicked_Color+"passing throw url", "Failed to clicked colr is passing throw URL"+clicked_Color);   		
        		
        	}
        }
        catch(Exception |Error e)
 		{
 			report.addFailedLog("verifying PDP color switch passing throw url", "Color Switch must pass throw url peramater as color ", "Faield to pass color parameter in url or faield to swith color", Common.getscreenShotPathforReport("colorswith"));

 			e.printStackTrace();
 			Assert.fail();
 			
 	}
        	
        }
        
        public void check_box() {
        	  try {
        		  Sync.waitPageLoad();
        		  Thread.sleep(8000);
        		  Common.clickElement("xpath", "(//span[contains(text(),'My billing and shipping address are the same')])");
        		  Thread.sleep(6000);
        	  }
        		  catch (Exception |Error e) {
        		    Assert.fail();
        		  
        	  }
        	  }
        public void Edit_BillingAddress(String dataSet)throws Exception{
    		
      	  try{
      	  Sync.waitElementPresent("xpath", "(//input[@placeholder='First Name*'])[3]");
      	  	Common.textBoxInput("xpath", "(//input[@placeholder='First Name*'])[3]",data.get(dataSet).get("FirstName"));
      	  	Sync.waitElementPresent("xpath", "(//input[@placeholder='Last Name*'])[3]");
      	  	Common.textBoxInput("xpath", "(//input[@placeholder='Last Name*'])[3]",data.get(dataSet).get("LastName"));
      	  	//Sync.waitElementPresent("xpath", "//div[contains(@name,'billingAddressime_paymetrictokenize')]//input[@name='firstname']");
      	  	//Common.textBoxInput("xpath", "//div[contains(@name,'billingAddressime_paymetrictokenize')]//input[@name='firstname']",data.get(dataSet).get("FirstName"));
      	  	//Sync.waitElementPresent("xpath", "//div[contains(@name,'billingAddressime_paymetrictokenize')]//input[@name='company']");
      	  	//Common.textBoxInput("xpath", "//div[contains(@name,'billingAddressime_paymetrictokenize')]//input[@name='company']",data.get(dataSet).get("CompanyName"));
      	  	Thread.sleep(5000);
      	  	Sync.waitElementPresent("xpath", "(//input[@placeholder='Street Address: Line 1*'])[2]");
      	  	Common.textBoxInput("xpath", "(//input[@placeholder='Street Address: Line 1*'])[2]", data.get(dataSet).get("Street"));
      	  	Common.dropdown("xpath", "(//select[@name='region_id'])[2]", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
      	  	Thread.sleep(4000);
      	  	Common.textBoxInput("xpath", "(//input[@placeholder='City*'])[2]", data.get(dataSet).get("City"));
      	  	//Common.dropdown("xpath", "//div[contains(@name,'billingAddressime_paymetrictokenize')]//select[@name='region_id']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
      	  	//Thread.sleep(4000);
      	  	Common.textBoxInput("xpath", "(//input[@placeholder='Zip/Postal Code*'])[2]", data.get(dataSet).get("postcode"));
      	  	Common.textBoxInput("xpath", "(//input[@placeholder='Phone Number*'])[2]", data.get(dataSet).get("phone"));
      	  	Common.actionsKeyPress(Keys.PAGE_DOWN);
      	  	Thread.sleep(2000);
      	  	Common.clickElement("xpath", "//button[@class='action action-update']");
      	  	
      	  	Thread.sleep(5000);
      	  	Common.actionsKeyPress(Keys.PAGE_UP);
      	  	Common.actionsKeyPress(Keys.PAGE_UP);
      	  	Common.actionsKeyPress(Keys.PAGE_UP);
      	  	//Common.actionsKeyPress(Keys.PAGE_UP);
      	  	//Common.actionsKeyPress(Keys.PAGE_UP);
      	  	//Common.actionsKeyPress(Keys.PAGE_UP);

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
        
        public HashMap<String, String> addfrieightDeliveryAddress_registerUser(String dataSet) {
     		// TODO Auto-generated method stub
     		
        	HashMap<String,String> Shippingaddress=new HashMap<String,String>();
     			
     			String expectedResult = "shipping address is entering in the fields";
     	        int size = Common.findElements(By.xpath("//span[contains(text(),'Add New Address')]")).size();
     			if (size > 0) {
     	        	try {
     					Common.clickElement("xpath", "//span[contains(text(),'Add New Address')]");
     					Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",data.get(dataSet).get("FirstName"));
     					Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']",	data.get(dataSet).get("LastName"));
     					Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']",data.get(dataSet).get("Street"));
     					Thread.sleep(2000);
     					Common.actionsKeyPress(Keys.SPACE);
     					Thread.sleep(3000);
     				/*	try {
     						Common.clickElement("xpath",
     								"//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
     					} catch (Exception e) {
     						Common.actionsKeyPress(Keys.BACK_SPACE);
     						Thread.sleep(1000);
     						Common.actionsKeyPress(Keys.SPACE);
     						Common.clickElement("xpath",
     								"//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
     					}
     					if (data.get(dataSet).get("StreetLine2") != null) {
     						Common.textBoxInput("name", "street[1]", data.get(dataSet).get("Street"));
     					}
     					if (data.get(dataSet).get("StreetLine3") != null) {
     						Common.textBoxInput("name", "street[2]", data.get(dataSet).get("Street"));
     					}*/
     					
     					Common.dropdown("xpath", "//select[@name='region_id']", Common.SelectBy.TEXT,data.get(dataSet).get("Region"));
     					Thread.sleep(5000);
     					String Shippingvalue = Common.findElement("xpath", "//select[@name='region_id']").getAttribute("value");
     					String Shippingstate = Common.findElement("xpath", "//select[@name='region_id']//option[@value='" + Shippingvalue + "']").getText();

     					Shippingaddress.put("Shippingstate", Shippingstate);
     					System.out.println(Shippingstate);
     					
     					Common.actionsKeyPress(Keys.PAGE_DOWN);
     					Thread.sleep(3000);
     					Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='city']",
     							data.get(dataSet).get("City"));
     					// Common.mouseOverClick("name", "region_id");
     					try {
     						Common.dropdown("xpath", "//form[@id='co-shipping-form']//select[@name='region_id']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
     					} catch (ElementClickInterceptedException e) {
     						// TODO: handle exception
     						Thread.sleep(3000);
     						Common.dropdown("xpath", "//form[@id='co-shipping-form']//select[@name='region_id']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
     					}
     					Thread.sleep(2000);
     					Common.textBoxInputClear("xpath", "//form[@id='co-shipping-form']//input[@name='postcode']");
     					Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='postcode']",
     							data.get(dataSet).get("postcode"));
     					String ShippingZip = Common.findElement("name", "postcode").getAttribute("value");
     					System.out.println("*****" + ShippingZip + "*******");
     					Shippingaddress.put("ShippingZip", ShippingZip);
     					
     					Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='telephone']",
     							data.get(dataSet).get("phone"));

     					ExtenantReportUtils.addPassLog("validating the shipping address", expectedResult,
     							"user add the shipping address",
     							Common.getscreenShotPathforReport("faield to add shipping address"));

     				
     	                Common.clickElement("xpath", "//div[@id='opc-new-shipping-address']//following::button[1]");

     					int sizeerrormessage = Common.findElements("xpath", "//span[contains(text(),'This is a required field')]").size();

     					Common.assertionCheckwithReport(sizeerrormessage <= 0, "verifying shipping addres filling ",
     							"user will fill the all the shipping", "user fill the shiping address click save button",
     							"faield to add new shipping address");
     					
     					Common.clickElement("xpath","(//label[@class='checkout-shipping-methods-label'])[2]");
     					//Common.clickElement("xpath", "//label[text()='2 Day Federal Express-FR']");//span[text()='Continue To Payment']
     					Common.clickElement("xpath", "//span[text()='Continue To Payment']");
     					
     				} catch (Exception | Error e) {
     					e.printStackTrace();

     					ExtenantReportUtils.addFailedLog("validating adding  address", expectedResult,
     							"User unabel add shipping address",
     							Common.getscreenShotPathforReport("shipping address faield"));
     				
     					Assert.fail();

     				}
     	        	
     			}
				return Shippingaddress;
	
     		}

      	
        
        public void addfrieightDeliveryAddress_guestuser(String dataSet) throws Exception {
     		// TODO Auto-generated method stub
     	
     			try {
     				Sync.waitElementVisible("id", "customer-email");
     				Common.textBoxInput("id", "customer-email", data.get(dataSet).get("Email"));
     			} catch (NoSuchElementException e) {
     				checkOut();
     				Common.textBoxInput("id", "customer-email", data.get(dataSet).get("Email"));

     			}
     			String expectedResult = "email field will have email address";
     			try {
     				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",data.get(dataSet).get("FirstName"));
     	            int size = Common.findElements("id", "customer-email").size();
     	            Common.assertionCheckwithReport(size > 0, "validating the email address field", expectedResult,"Filled Email address", "unabel to fill the email address");
     	            Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']",data.get(dataSet).get("LastName"));
     				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']",data.get(dataSet).get("Street"));
     				String Text=Common.getText("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']");
     				
     				

     				try {
     					Common.clickElement("xpath", "(//input[@name='city'])[1]");
     				} catch (Exception e) {
     					Common.actionsKeyPress(Keys.BACK_SPACE);
     					Thread.sleep(1000);
     					Common.actionsKeyPress(Keys.SPACE);
     					Thread.sleep(4000);
     					Common.clickElement("xpath", "(//input[@name='city'])[1]");
     				}
     				if (data.get(dataSet).get("StreetLine2") != null) {
     					Common.textBoxInput("name", "street[1]", data.get(dataSet).get("Street"));
     					
     					String text=Common.getText("name","street[1]");
     					System.out.println(text);
     					
     				}
     				if (data.get(dataSet).get("StreetLine3") != null) {
     					Common.textBoxInput("name", "street[2]", data.get(dataSet).get("Street"));
     				}
     				Sync.waitPageLoad();
     				//Thread.sleep(5000);
     				Common.findElement("xpath", "//form[@id='co-shipping-form']//input[@name='city']").clear();
     				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='city']",data.get(dataSet).get("City"));
     				System.out.println(data.get(dataSet).get("City"));
     				
     				Common.actionsKeyPress(Keys.PAGE_DOWN);
     				Thread.sleep(3000);
     				try {
     					Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
     				} catch (ElementClickInterceptedException e) {
     					Thread.sleep(3000);
     					Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
     				}
     				Thread.sleep(2000);
     				Common.textBoxInputClear("name", "postcode");
     				Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
     				Thread.sleep(5000);
     				
     				Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
     				Common.clickElement("xpath", "//input[@id='label_method_bestway']");
     				Common.clickElement("xpath", "//span[text()='Continue To Payment']");
                         
     			}

     			catch (Exception | Error e) {
                     e.printStackTrace();
     				ExtenantReportUtils.addFailedLog("validating shipping address",
     						"shipping address is filled in to the fields", "user faield to fill the shipping address",
     						Common.getscreenShotPathforReport("shipingaddressfaield"));
     				// ExtenantReportUtils.addFailedLog("User click check out button",
     				// "User unabel click the checkout button",
     				// Common.getscreenShotPathforReport("check out miniCart"));
     				
     				Assert.fail();

     			}
     	}
        
        
        
        
        public void addDeliveryAddress_Outside_US(String dataSet) throws Exception {
    		try {
    			Sync.waitElementVisible("id", "customer-email-address");
    			Common.textBoxInput("id", "customer-email-address", data.get(dataSet).get("Email"));
    		} catch (NoSuchElementException e) {
    			checkOut();
    			Common.textBoxInput("id", "customer-email-address", data.get(dataSet).get("Email"));

    		}
    		String expectedResult = "email field will have email address";
    		try {
    			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",data.get(dataSet).get("FirstName"));
                int size = Common.findElements("id", "customer-email-address").size();
                Common.assertionCheckwithReport(size > 0, "validating the email address field", expectedResult,"Filled Email address", "unabel to fill the email address");
                Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']",data.get(dataSet).get("LastName"));
    			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']",data.get(dataSet).get("Street"));
    			String Text=Common.getText("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']");
    			
    			

    			try {
    				Common.clickElement("xpath", "//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
    			} catch (Exception e) {
    				Common.actionsKeyPress(Keys.BACK_SPACE);
    				Thread.sleep(1000);
    				Common.actionsKeyPress(Keys.SPACE);
    				//Common.clickElement("xpath", "//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
    			}
    			if (data.get(dataSet).get("StreetLine2") != null) {
    				Common.textBoxInput("name", "street[1]", data.get(dataSet).get("Street"));
    				
    				String text=Common.getText("name","street[1]");
    				System.out.println(text);
    				
    			}
    			if (data.get(dataSet).get("StreetLine3") != null) {
    				Common.textBoxInput("name", "street[2]", data.get(dataSet).get("Street"));
    			}
    			Sync.waitPageLoad();
    			Thread.sleep(6000);
    			Common.scrollIntoView("xpath", "//form[@id='co-shipping-form']//input[@name='city']");
    			Common.findElement("xpath", "//form[@id='co-shipping-form']//input[@name='city']").clear();
    			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='city']",data.get(dataSet).get("City"));
    			System.out.println(data.get(dataSet).get("City"));
    			
    			Common.actionsKeyPress(Keys.PAGE_DOWN);
    			Thread.sleep(3000);
    			try {
    				Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
    			} catch (ElementClickInterceptedException e) {
    				Thread.sleep(3000);
    				Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
    			}
    			Thread.sleep(2000);
    			Common.textBoxInputClear("name", "postcode");
    			Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
    			Thread.sleep(5000);
    			Common.clickElement("name", "country_id");
    			
    			
    			

    		}

    		catch (Exception | Error e) {
             e.printStackTrace();
    			ExtenantReportUtils.addFailedLog("validating shipping address",
    					"shipping address is filled in to the fields", "user faield to fill the shipping address",
    					Common.getscreenShotPathforReport("shipingaddressfaield"));
    			// ExtenantReportUtils.addFailedLog("User click check out button",
    			// "User unabel click the checkout button",
    			// Common.getscreenShotPathforReport("check out miniCart"));
    			Assert.fail();

    		}
    		Thread.sleep(5000);
    		int US = Common.findElements("name", "country_id").size();

    		if (US >0) {
    			ExtenantReportUtils.addPassLog("validating the Shipping Out-Side US  address field with valid Data", expectedResult,
    					"Filled the shipping Out-Side US address", Common.getscreenShotPathforReport("Out-Side US"));
    		} else {
    			ExtenantReportUtils.addFailedLog("validating the shipping Out-Side US address field with valid Datas", expectedResult,
    					"failed to add Out-Side US addres in the filled",
    					Common.getscreenShotPathforReport("failed to add Out-Side US address"));
    			Assert.fail();
    		}

    		// Common.assertionCheckwithReport(errorsize<=0,"enter the shipping
    		// address in to the fields without skipping any mandatory fields",
    		// expectedResult, "Filled the shipping address", "failed to add a
    		// address");
    		// Common.assertionCheckwithReport(errorsize<=0, "Filled the shipping
    		// address", expectedResult, "Missing the shipping address");
    		//Thread.sleep(3000);
    	}
    	
    	
        
        public void addDeliveryAddress_registerUser_Outside_US(String dataSet) throws Exception {

    		
    		String expectedResult = "shipping address is entering in the fields";
            int size = Common.findElements(By.xpath("//span[contains(text(),'Add New Address')]")).size();
    		if (size > 0) {
            	try {
    				Common.clickElement("xpath", "//span[contains(text(),'Add New Address')]");
    				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",data.get(dataSet).get("FirstName"));
    				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']",	data.get(dataSet).get("LastName"));
    				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']",data.get(dataSet).get("Street"));
    				Thread.sleep(2000);
    				Common.actionsKeyPress(Keys.SPACE);
    				Thread.sleep(3000);
    			/*	try {
    					Common.clickElement("xpath",
    							"//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
    				} catch (Exception e) {
    					Common.actionsKeyPress(Keys.BACK_SPACE);
    					Thread.sleep(1000);
    					Common.actionsKeyPress(Keys.SPACE);
    					Common.clickElement("xpath",
    							"//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
    				}*/
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
    					Common.dropdown("xpath", "//form[@id='co-shipping-form']//select[@name='region_id']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
    				} catch (ElementClickInterceptedException e) {
    					// TODO: handle exception
    					Thread.sleep(3000);
    					Common.dropdown("xpath", "//form[@id='co-shipping-form']//select[@name='region_id']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
    				}
    				Thread.sleep(2000);
    				Common.textBoxInputClear("xpath", "//form[@id='co-shipping-form']//input[@name='postcode']");
    				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='postcode']",
    						data.get(dataSet).get("postcode"));
    				
    				Common.clickElement("name", "country_id");

    				ExtenantReportUtils.addPassLog("validating the shipping address", expectedResult,
    						"user add the shipping address",
    						Common.getscreenShotPathforReport("faield to add shipping address"));


    				int US = Common.findElements("name", "country_id").size();

    				Common.assertionCheckwithReport(US>0, "verifying shipping Out-side US addres filling ",
    						"user will fill the all the shipping", "user fill the shiping Out-side US address ",
    						"faield to add new Out-side US shipping address");
    				
    				
    				
    			} catch (Exception | Error e) {
    				e.printStackTrace();

    				ExtenantReportUtils.addFailedLog("validating Out-side US adding  address", expectedResult,
    						"User unabel add Out-side US shipping address",
    						Common.getscreenShotPathforReport("shipping Out-side US address faield"));
    			
    				Assert.fail();

    			}
    		}

    		else

    		{
    			try {
    				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",
    						data.get(dataSet).get("FirstName"));
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
    					Common.clickElement("xpath",
    							"//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
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
    				
    				
                    int US=Common.findElements("name", "country_id").size();
    				Common.assertionCheckwithReport(US>0, "verifying shipping Outside US addres filling ", expectedResult, "user enter the shipping Outside US address", "Outside US data");			
    			

    			} catch (Exception | Error e) {
    				e.printStackTrace();

    				ExtenantReportUtils.addFailedLog("validating adding Out-side US address", expectedResult,
    						"User unabel add shipping Out-side US address",
    						Common.getscreenShotPathforReport("shipping Out-side US address faield"));
    				
    				Assert.fail();

    			}
    		}

    		
    	}

        
        
        public void addDeliveryAddress_registerUser_With_Federal_Express_Shipping(String dataSet) throws Exception {

    		
    		String expectedResult = "shipping address is entering in the fields";
            int size = Common.findElements(By.xpath("//span[contains(text(),'Add New Address')]")).size();
    		if (size > 0) {
            	try {
    				Common.clickElement("xpath", "//span[contains(text(),'Add New Address')]");
    				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",data.get(dataSet).get("FirstName"));
    				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']",	data.get(dataSet).get("LastName"));
    				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']",data.get(dataSet).get("Street"));
    				Thread.sleep(2000);
    				Common.actionsKeyPress(Keys.SPACE);
    				Thread.sleep(3000);
    			/*	try {
    					Common.clickElement("xpath",
    							"//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
    				} catch (Exception e) {
    					Common.actionsKeyPress(Keys.BACK_SPACE);
    					Thread.sleep(1000);
    					Common.actionsKeyPress(Keys.SPACE);
    					Common.clickElement("xpath",
    							"//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
    				}*/
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
    					Common.dropdown("xpath", "//form[@id='co-shipping-form']//select[@name='region_id']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
    				} catch (ElementClickInterceptedException e) {
    					// TODO: handle exception
    					Thread.sleep(3000);
    					Common.dropdown("xpath", "//form[@id='co-shipping-form']//select[@name='region_id']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
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

    			
                    Common.clickElement("xpath", "//div[@id='opc-new-shipping-address']//following::button[1]");

    				int sizeerrormessage = Common.findElements("xpath", "//span[contains(text(),'This is a required field')]").size();

    				Common.assertionCheckwithReport(sizeerrormessage <= 0, "verifying shipping addres filling ",
    						"user will fill the all the shipping", "user fill the shiping address click save button",
    						"faield to add new shipping address");
    				
    				Common.clickElement("xpath", "//input[@id='label_method_flatrate']");
    				Common.clickElement("xpath", "//button[@data-ac-test='form-shipping-address_action_submit']");
    				
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
    				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",
    						data.get(dataSet).get("FirstName"));
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
    					Common.clickElement("xpath",
    							"//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
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
    				Sync.waitElementClickable("xpath", "//span[contains(text(),'Continue To Payment')]");
    				Common.clickElement("xpath", "//span[contains(text(),'Continue To Payment')]");
                    int errorsize=Common.findElements("xpath", "//div[@class='field-error']").size();
    				Common.assertionCheckwithReport(errorsize>0, "verifying shipping addres filling ", expectedResult, "user enter the shipping address", "mandatory data");			
    				//ExtenantReportUtils.addPassLog("verifying shipping addres filling ", expectedResult,"user enter the shipping address ",
    				//Common.getscreenShotPathforReport("fill the shipping address first time"));

    				//Common.findElements("xpath", "").size();
    				expectedResult = "shipping address is filled in to the fields";
    				Common.clickElement("xpath", "//button[@data-ac-test='form-shipping-address_action_submit']");
    				Thread.sleep(3000);

    			} catch (Exception | Error e) {
    				e.printStackTrace();

    				ExtenantReportUtils.addFailedLog("validating adding  address", expectedResult,
    						"User unabel add shipping address",
    						Common.getscreenShotPathforReport("shipping address faield"));
    				
    				Assert.fail();

    			}
    		}

    		
    	}
        
        
        
        
        public void CheckOutPaypal_Promocode(String dataSet) {
    		String expectedResult = "it should land on the checkout intermediate page";
    		String url=automation_properties.getInstance().getProperty(automation_properties.BASEURL);
    		
    		try {
    			Thread.sleep(3000);
    			Common.clickElement("xpath", "//a[@aria-label='minicart']");
    			Thread.sleep(3000);
    			Common.scrollIntoView("id", "top-cart-btn-checkout");
    			ExtenantReportUtils.addPassLog("validating the product miniCart", expectedResult,
    					"User click the minicart button", Common.getscreenShotPathforReport("clickminiCart"));
    		} catch (Exception | Error e) {
    			ExtenantReportUtils.addFailedLog("validating the product miniCart", expectedResult,
    					"User unabel click the minicart button", Common.getscreenShotPathforReport("clickminiCart"));
    			// ExtenantReportUtils.addFailedLog("User click check out button",
    			// "User unabel click the checkout button",
    			// Common.getscreenShotPathforReport("check out miniCart"));
    			Assert.fail();
    		}
    		try {
    			Common.switchFrames("xpath", "//iframe[contains(@class,'component-frame visible')]");
    			Sync.scrollDownToView("xpath", "//div[@class='paypal-button-label-container']");
    			Sync.waitElementClickable(30, By.xpath("//div[@class='paypal-button-label-container']"));
    			Common.mouseOverClick("xpath", "//div[@class='paypal-button-label-container']");
    			Thread.sleep(4000);
    			Common.switchToDefault();
    			Thread.sleep(5000);
    			Common.switchWindows();
    			int size = Common.findElements("id", "acceptAllButton").size();
    			if (size > 0) {
    				Common.clickElement("id", "acceptAllButton");
    			}
    	
    			if(!url.contains("stg")& !url.contains("dev")){
    				
    				int sizeofelement=Common.findElements("id", "email").size();
    				Common.assertionCheckwithReport(sizeofelement > 0, "verifying the paypal payment ", expectedResult,"open paypal site window", "faild to open paypal account");
    			}
    			else{
    			Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
    			int sizeofbutton = Common.findElements("xpath", "//button[@id='btnNext']").size();
    			if (sizeofbutton > 0) {
    				Common.clickElement("xpath", "//button[@id='btnNext']");
    			}
    			Common.textBoxInput("id", "password", data.get(dataSet).get("Password"));
    			int sizeemail = Common.findElements("id", "email").size();
    			Common.assertionCheckwithReport(sizeemail > 0, "verifying the paypal payment ", expectedResult,
    					"open paypal site window", "faild to open paypal account");
    			Common.clickElement("id", "btnLogin");
    			Thread.sleep(5000);
    			Common.actionsKeyPress(Keys.END);
    			Thread.sleep(8000);
    			
    			int buttonsize=Common.findElements("id", "payment-submit-btn").size();
    			
    			if(buttonsize>0){
    				Common.clickElement("id", "payment-submit-btn");
    				//Common.clickElement("xpath", "//div[@class='paypal-button-label-container']");
    			}
    			else{
    			Common.clickElement("id", "confirmButtonTop");
    			Thread.sleep(8000);
    			}
    			Common.switchToFirstTab();
    			}
    		} catch (Exception | Error e) {
    			e.printStackTrace();
    			ExtenantReportUtils.addFailedLog("verifying the paypal payment ", expectedResult,
    					"User failed to proceed with paypal payment", Common.getscreenShotPathforReport(expectedResult));
    			Assert.fail();
    		}
    		if(!url.contains("stg")& !url.contains("dev")){
    			
    			int sizeofelement=Common.findElements("id", "email").size();
    			Common.assertionCheckwithReport(sizeofelement > 0, "verifying the paypal payment ", expectedResult,"open paypal site window", "faild to open paypal account");
    		}
    		else{
    		try {
    			Thread.sleep(8000);
    			expectedResult = "select the shipping metho";
    			//Common.scrollIntoView("xpath", "//select[@id='shipping-method']");
    			//Thread.sleep(4000);
    			// Common.clickElementStale("xpath","//select[@id='shipping-method']");
    			Common.dropdown("xpath", "//select[@id='shipping-method']", Common.SelectBy.VALUE, "tablerate_bestway");
    			
    			
    			
    			
    			ExtenantReportUtils.addPassLog("validating shipping methoad", expectedResult,
    					"user select the shipping method", Common.getscreenShotPathforReport("shippingmethodselect"));
    		} catch (Exception | Error e) {
    			e.printStackTrace();
    			ExtenantReportUtils.addFailedLog("validating shipping methoad", expectedResult,
    					"User unabel to select shipping methoad",
    					Common.getscreenShotPathforReport("faieldtoselectShippingmethod"));
    			Assert.fail();
    		}
    		try {
    			
    			verifyingTax_field_for_ExpressPaypal();
    			promationCode_ExpressPaypal("Promationcode");
    			Thread.sleep(5000);
    			expectedResult = "click the place order";
    			Sync.waitPageLoad();
    			Sync.waitElementClickable("id", "review-button");
    			Common.scrollIntoView("xpath", "//button[@id='review-button']");
    			Common.clickElement("xpath", "//button[@id='review-button']");
    		} catch (Exception | Error e) {
    			e.printStackTrace();			ExtenantReportUtils.addFailedLog("validating place order", expectedResult,
    					"User click the place order button", Common.getscreenShotPathforReport("placeorderbutton"));
    			Assert.fail();
    		}
    		try {
    			Sync.waitPageLoad();
    			Sync.waitPresenceOfElementLocated("xpath", "//h1[@class='checkout-success-title']");
    			String sucessMessage = Common.getText("xpath", "//h1[@class='checkout-success-title']").trim();
    			Assert.assertEquals(sucessMessage, "Your order has been received", "Sucess message validations");
    			expectedResult = "Verify order confirmation number which was dynamically generated";
    			Common.assertionCheckwithReport(sucessMessage.equals("Your order has been received"),
    					"Order Placed successfull", expectedResult, "faild to place order");
    		} catch (Exception | Error e) {
    			e.printStackTrace();
    			ExtenantReportUtils.addFailedLog("verifying the product confirmation", expectedResult,
    					"User failed to proceed paypal detiles", Common.getscreenShotPathforReport("faieldmessagepaypal"));
    			Assert.fail();
    		}
    		}
    	}
        
        public void verifyingTax_field_for_ExpressPaypal() {
            
            
            try {
                Sync.waitPageLoad();
               
               String verifyTax=Common.findElement("xpath", "//span[@class='detailed']").getText();
                System.out.println(verifyTax);
            

                 Common.assertionCheckwithReport(verifyTax.contains("Tax*"),"Verifying tax in Payment page","Should display tax at right of the Payment details page", "successfully  displayed tax at right of the Payment details page", "Payment Page");
          }catch(Exception |Error e) {
               ExtenantReportUtils.addFailedLog("Verifying tax in Payment page","Should display tax at right of the  Payment details page", "user unable to land on Payment page", Common.getscreenShotPathforReport("failed to land on Payment page"));           
               Assert.fail();   
               }
          
       }
        
        
        public void promationCode_ExpressPaypal(String dataSet) throws Exception {

    		String expectedResult = "It should opens textbox input.";

    		try {

    			Sync.waitElementClickable("xpath", "//div[@class='cart-table-discount-trigger']");
    			Common.clickElement("xpath", "//div[@class='cart-table-discount-trigger']");

    			Sync.waitElementPresent("xpath", "//input[@class='cart-table-discount-input']");

    			Common.textBoxInput("xpath", "//input[@class='cart-table-discount-input']", data.get(dataSet).get("Promationcode"));

    			int size = Common.findElements("xpath", "//input[@class='cart-table-discount-input']").size();

    			Common.assertionCheckwithReport(size > 0, "verifying the Promo Code label", expectedResult,
    					"Successfully open the discount input box", "User unabel enter promationCode");
    			// Common.assertionCheckwithReport(size>0, "Successfully open the
    			// discount input box", expectedResult,"User unabel enter
    			// promationCode");
    			Sync.waitElementClickable("xpath", "//button[@class='cart-table-discount-apply action apply primary']");
    			Common.clickElement("xpath", "//button[@class='cart-table-discount-apply action apply primary']");
    			Sync.waitPageLoad();
    			Thread.sleep(4000);
    			expectedResult = "It should apply discount on your price.If user enters invalid promocode it should display coupon code is not valid message.";
    			int codetext =  Common.findElements("xpath", "//tr[@class='totals']//th").size();
    			Common.assertionCheckwithReport(codetext>0,
    					"verifying pomocode", expectedResult, "promotion code working as expected",
    					"Promation code is not applied");

    			// Common.assertionCheckwithReport(codetext.equals(data.get(dataSet).get("Promationcode")),
    			// "promotion code working as expected", expectedResult,"Promation
    			// code is not applied ");
    			// Assert.assertEquals( data.get(dataSet).get("Promationcode"),
    			// codetext,"Promation code is not applied ");
    		}

    		catch (Exception | Error e) {
    			e.printStackTrace();
    			ExtenantReportUtils.addFailedLog("validating promo code", expectedResult,
    					"User failed to proceed with promocode", Common.getscreenShotPathforReport("promocodefaield"));
    			// (expectedResult, "User failed to proceed with promocode ",
    			// Common.getscreenShotPathforReport(expectedResult));
    			Assert.fail();

    		}

    		// report.addPassLog(expectedResult,"promotion code working as
    		// expected",Common.getscreenShotPathforReport("pomotion code"));
    	}


        
        
        public void VerificationXMLData() throws Exception {
        	
        	
        	String fileName="C:\\Users\\admin\\Downloads\\Export_4000420945A.xml";
        	Map<String, Object> jsonInMap=xmlReader.fetchvalues(fileName);
        	
                String CardName=(String) jsonInMap.get("CardName");
                String BillingFirstName= (String) jsonInMap.get("BillingFirstName");
                String BillingLastName= (String) jsonInMap.get("BillingLastName");
                String BillingAddress1= (String) jsonInMap.get("BillingAddress1");
                String BillingCity= (String) jsonInMap.get("BillingCity");
                String BillingState= (String) jsonInMap.get("BillingState");
                String BillingZip=(String) jsonInMap.get("BillingZip");
                String BillingCountry=(String) jsonInMap.get("BillingCountry");
                
                
                System.out.println(jsonInMap.get("BillingCountry"));
                
                
             //  System.out.println(jsonInMap.get("OrderItems1"));
                
                
                
                
          //  System.out.println(xmlReader.stringToMap(jsonInMap.get("OrderItems1").toString()));
              
            Map<String,Object> tts= xmlReader.stringToMapTest(jsonInMap.get("OrderItems1").toString());
               
                
           System.out.println(tts);
            
          System.out.println(tts.get("OrderedProductPrice"));
            
                
        }
        
        
        
public void Adminlogins() throws Exception {
        	
        	
        	
        	Common.textBoxInput("xpath", "//input[contains(@name,'username')]", "manojk");
        	Common.textBoxInput("xpath", "//input[contains(@name,'password')]","b{?e\\Gm=c8qDH8p@");
        	Common.clickElement("xpath", "//button[contains(@class,'action-primary')]");
        	
        	
         	Common.clickElement("xpath", "//li[@data-ui-id='menu-magento-sales-sales-order']");
	
        	//select profile 
         	
         	Common.dropdown("id", "profile_id", Common.SelectBy.TEXT, "Alchemy Import Profile (ID: 1)");
        	
        }
 public void Adminlogins(String orderid) throws Exception {
        	
        	
        	Common.oppenURL("https://jetrails-stg.hydroflask.com/nsnfCNSxxsSxrcCa4vnBn5wg");
        	Common.textBoxInput("xpath", "//input[contains(@name,'username')]", "manojk");
        	Common.textBoxInput("xpath", "//input[contains(@name,'password')]","b{?e\\Gm=c8qDH8p@");
        	Common.clickElement("xpath", "//button[contains(@class,'action-primary')]");
        	Thread.sleep(2000);  
        	Common.actionsKeyPress(Keys.ESCAPE);
        	Thread.sleep(2000);        	
        	Common.findElement("xpath","//li[@id='menu-magento-sales-sales']").click();
               	
        //	Common.clickElement("xpath", "//li[@data-ui-id='menu-magento-sales-sales-order']");
        	
        	
        	//import click 
        	//Common.clickElement("xpath", "//li[@data-ui-id='menu-xtento-orderexport-manual']");
        	
        	
        	
        	Thread.sleep(5000);
        	
        	//export click
        	Common.clickElement("xpath","//li[@data-ui-id='menu-xtento-orderexport-manual']");
        	
        	//select profile 
        	
        	Thread.sleep(5000);
        	
        	

         	
    //     	Common.dropdown("id", "profile_id", Common.SelectBy.TEXT, "Alchemy Import Profile (ID: 1)");
        
         	//starting ordernumber
         	Common.textBoxInput("xpath", "//input[@id='increment_from']",orderid);
        
         	//starting ordernumber
         	Common.textBoxInput("xpath", "//input[@id='increment_to']",orderid);
         	
         	
         	//select the orderstatusinexpoert
         	Common.dropdown("id", "force_status",Common.SelectBy.TEXT, "Processing");
         	
         	
            Common.clickElement("xpath", "//input[@id='filter_new_only']");
            
            Common.clickCheckBox("xpath", "//input[@id='start_download']");
            
            Common.clickElement("xpath", "//button[@id='export_button']");
            
            
            
         	
        }   
 
 
 
    	 public void HydroAdminlogin(String dataSet) throws Exception {
         	
         	try {
         	Common.oppenURL("https://jetrails-stg.hydroflask.com/nsnfCNSxxsSxrcCa4vnBn5wg");
         	Common.textBoxInput("xpath", "//input[contains(@name,'username')]", data.get(dataSet).get("UserName"));
         	Common.textBoxInput("xpath", "//input[contains(@name,'password')]",data.get(dataSet).get("Password"));
         	
         int username=	Common.findElements("xpath", "//input[contains(@name,'username')]").size();
         	
         	
            Common.assertionCheckwithReport(username>=1, "verifying Admin panel login page", "User name and password field data is populating", "Sucessfully enter username and password", "Faield to enter username and password"); 	
         	Common.clickElement("xpath", "//button[contains(@class,'action-primary')]");
         	Thread.sleep(2000);  
         	Common.actionsKeyPress(Keys.ESCAPE);
         	}
         	catch(Exception |Error e)
	 		{
	 			report.addFailedLog("verifying Admin panel login page", "User name and password field data is populating", "Faield to enter username and password",Common.getscreenShotPathforReport("adminlogin")); 	

	 			e.printStackTrace();
	 			Assert.fail();
	 			
	 	}
     }
  
    	 
    	 
    	 public String seleOrderoprtion(String ordernumber) {
    		String trantionid="";
    		 try {
    			 Thread.sleep(5000);
     			Common.findElement("xpath","//li[@id='menu-magento-sales-sales']").click();
     			
     			Thread.sleep(5000);
             	
              	Common.clickElement("xpath","//li[@data-ui-id='menu-magento-sales-sales-order']");
             	
                 Thread.sleep(5000);
                 
             	Common.assertionCheckwithReport(Common.getPageTitle().equals("Orders / Operations / Sales / Magento Admin"), "Validating  order detiles option in admin", "User must land on order page in admin", "user sucessfully navigating to orders page ", "fail to navigate orders page");
                Thread.sleep(5000);

             	
             	Common.textBoxInput("xpath", "//input[@id='fulltext']", ordernumber);
             	Common.actionsKeyPress(Keys.ENTER);
                Thread.sleep(5000);
               Common.clickElement("xpath", "//a[text()='View']");
               Thread.sleep(3000);
               
               Common.clickElement("xpath", "//a[@id='sales_order_view_tabs_order_transactions']");
               Thread.sleep(3000);
               trantionid=   Common.getText("xpath", "//tr[@data-role='row']//td[@data-column='txn_id']").trim();
               
               report.addPassLog("validating order TransactionID"," getting TransactionID from admin order view page","User sucessfully get TransactionID from order page in admin",Common.getscreenShotPathforReport("TransactionID"));
    		 }
                 catch(Exception |Error e)
      	 		{
      	 			report.addFailedLog("validating the orders pagein admin	", "select the order view buton", "Faield to select orer view",Common.getscreenShotPathforReport("orderview")); 	

      	 			e.printStackTrace();
      	 			Assert.fail();
      	 			
      	 	}
    		 System.out.println(trantionid);
    		 
    		 return trantionid;

    	 }
    	 
    	 public void selectManulExport(String orderid) {
    		 
    		 try {
    			Common.findElement("xpath","//li[@id='menu-magento-sales-sales']").click();
    			
    			Thread.sleep(5000);
            	
             	Common.clickElement("xpath","//li[@data-ui-id='menu-xtento-orderexport-manual']");
            	
                Thread.sleep(5000);
            	
            	
            	Common.assertionCheckwithReport(Common.getPageTitle().equals("Sales Export - Manual Export / Sales Export / Sales / Magento Admin"), "Validating manual export option in admin", "User must land on Manual Export page in admin", "user sucessfully navigating to Manual Export page ", "fail to navigate Manual Export page");
            	
            	

             	
        //     	Common.dropdown("id", "profile_id", Common.SelectBy.TEXT, "Alchemy Import Profile (ID: 1)");
            
             	//starting ordernumber
             	Common.textBoxInput("xpath", "//input[@id='increment_from']",orderid);
            
             	//starting ordernumber
             	Common.textBoxInput("xpath", "//input[@id='increment_to']",orderid);
             	
             	
             	//select the orderstatusinexpoert
             	Common.dropdown("id", "force_status",Common.SelectBy.TEXT, "Processing");
             	
             	
                Common.clickElement("xpath", "//input[@id='filter_new_only']");
                
                Common.clickCheckBox("xpath", "//input[@id='start_download']");
                
                 Common.clickElement("xpath", "//button[@id='export_button']");
                 report.addPassLog("validating the Manual Export order files"," enter all the field infromation manual export field","User sucessfully enter all the manual export field data",Common.getscreenShotPathforReport("downloading"));
    		 
                Common.actionsKeyPress(Keys.ESCAPE);
             	
            }   
     
    		 catch(Exception |Error e)
 	 		{
 	 			report.addFailedLog("validating the Manual Export order files", "enter all the field infromation manual export field", "Faield to enter manual export field data",Common.getscreenShotPathforReport("faielddownload")); 	

 	 			e.printStackTrace();
 	 			Assert.fail();
 	 			
 	 	}
    			
    			
    			
    			
    			
    			
    	 }
    	 
    	 
    	 public  Map<String,String>  addDeliveryAddress_Validation (String dataSet) throws Exception  {
    		
    		 HashMap <String,String> gustUseraddress=new HashMap<String,String>();
    		 
    		 try {
    				Sync.waitElementVisible("id", "customer-email-address");
    				Common.textBoxInput("id", "customer-email-address", data.get(dataSet).get("Email"));
    				
    				gustUseraddress.put("Email", data.get(dataSet).get("Email"));
    				
    			} catch (NoSuchElementException e) {
    				checkOut();
    				Common.textBoxInput("id", "customer-email-address", data.get(dataSet).get("Email"));

    			}
    			String expectedResult = "email field will have email address";
    			try {
    				
    				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",data.get(dataSet).get("FirstName"));
    				gustUseraddress.put("FirstName", data.get(dataSet).get("FirstName"));
    	            int size = Common.findElements("id", "customer-email-address").size();
    	            Common.assertionCheckwithReport(size > 0, "validating the email address field", expectedResult,"Filled Email address", "unabel to fill the email address");
    	            Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']",data.get(dataSet).get("LastName"));
    	            gustUseraddress.put("LastName", data.get(dataSet).get("LastName"));
    	            Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']",data.get(dataSet).get("Street"));
    				gustUseraddress.put("Street1", data.get(dataSet).get("Street"));
    				String Text=Common.getText("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']");
    				
    				

    				try {
    					Common.clickElement("xpath", "//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
    				} catch (Exception e) {
    					Common.actionsKeyPress(Keys.BACK_SPACE);
    					Thread.sleep(1000);
    					Common.actionsKeyPress(Keys.SPACE);
    					Common.clickElement("xpath", "//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
    				}
    				if (data.get(dataSet).get("StreetLine2") != null) {
    					Common.textBoxInput("name", "street[1]", data.get(dataSet).get("Street"));
    					
    					String text=Common.getText("name","street[1]");
    					System.out.println(text);
    					
    				}
    				if (data.get(dataSet).get("StreetLine3") != null) {
    					Common.textBoxInput("name", "street[2]", data.get(dataSet).get("Street"));
    				}
    				Sync.waitPageLoad();
    				Thread.sleep(5000);
    				Common.findElement("xpath", "//form[@id='co-shipping-form']//input[@name='city']").clear();
    				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='city']",data.get(dataSet).get("City"));
    				
    				 gustUseraddress.put("City", data.get(dataSet).get("City"));
    				
    				System.out.println(data.get(dataSet).get("City"));
    				
    				Common.actionsKeyPress(Keys.PAGE_DOWN);
    				Thread.sleep(3000);
    				try {
    					Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
    					
    					 gustUseraddress.put("Region", data.get(dataSet).get("Region"));
    				} catch (ElementClickInterceptedException e) {
    					Thread.sleep(3000);
    					Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
    				}
    				Thread.sleep(2000);
    				Common.textBoxInputClear("name", "postcode");
    				Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
    				
    				gustUseraddress.put("postcode", data.get(dataSet).get("postcode"));
    				Thread.sleep(5000);
    				
    				Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
    				
    				
    				

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
    			int size=Common.findElements("xpath", "//input[@id='label_method_bestway']").size();
    			if(size>0){
    				Common.clickElement("xpath", "//input[@id='label_method_bestway']");
    			}

    			expectedResult = "shipping address is filled in to the fields";
    			Common.clickElement("xpath", "//button[@data-ac-test='form-shipping-address_action_submit']");

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

    			// Common.assertionCheckwithReport(errorsize<=0,"enter the shipping
    			// address in to the fields without skipping any mandatory fields",
    			// expectedResult, "Filled the shipping address", "failed to add a
    			// address");
    			// Common.assertionCheckwithReport(errorsize<=0, "Filled the shipping
    			// address", expectedResult, "Missing the shipping address");
    			Thread.sleep(3000);
    			
    			return gustUseraddress;
    		}

    	 public void shippingvalidaingXML(String dataSet,String Ordernumber) throws IOException, InterruptedException {
         	
    		 Thread.sleep(5000);
    		 String fileName=System.getProperty("user.dir")+"\\TestLogs\\Download\\Export_"+Ordernumber+".xml";
    		 
        	 //String fileName="C:\\Users\\admin\\Downloads\\Export_4000420945A.xml";
             Map<String, Object> jsonInMap=xmlReader.fetchvalues(fileName);
        	 String BillingAddress1= (String) jsonInMap.get("BillingAddress1");
             String BillingCity= (String) jsonInMap.get("BillingCity");
             String BillingState= (String) jsonInMap.get("BillingState");
             String BillingZip=(String) jsonInMap.get("BillingZip");
             String BillingCountry=(String) jsonInMap.get("BillingCountry");
             
             
           String SfUSername=  data.get(dataSet).get("FirstName");
           String SfLastname = data.get(dataSet).get("LastName");
           String SfStreet =data.get(dataSet).get("Street");
           String Sfcity =data.get(dataSet).get("City");
           String SfRegion =data.get(dataSet).get("Region");
           String Sfpostcode =data.get(dataSet).get("postcode");

           
           System.out.println(BillingAddress1);
           
           System.out.println(BillingCity);
            
           System.out.println(BillingState);
           
           System.out.println(BillingCity);
           
           System.out.println(SfStreet);
           
           System.out.println(Sfcity);
           System.out.println(SfRegion);
           
           
           
             
           Assert.assertTrue(BillingAddress1.equals(SfStreet)&& BillingCity.equals(Sfcity) && BillingZip.equals(Sfpostcode)); 
             
             
        	
        }
    	 
    	 
 public void productinfromationvalidation(HashMap<String,HashMap<String,String>>  SFproductinfromation,String Ordernumber) throws Throwable {
    		 
	 Thread.sleep(5000);
	 String fileName=System.getProperty("user.dir")+"\\TestLogs\\Download\\Export_"+Ordernumber+".xml";
    		 //String fileName="C:\\Users\\admin\\Downloads\\Export_4000420945A.xml";
	 
	 Map<String, Object> jsonInMap=xmlReader.fetchvalues(fileName);
	 Map<String,Object> xml= xmlReader.stringToMapTest(jsonInMap.get("OrderItems1").toString());
  	
	 String OrderNumberxml=(String) jsonInMap.get("OrderNumber");     
	 
  	String orderpricexml=xml.get("OrderedProductPrice").toString();
  	StringBuffer sb= new StringBuffer(orderpricexml);  
 	String xmlproductprice=sb.deleteCharAt(sb.length()-1).toString();
  	String orderedProductSKUXML =xml.get("OrderedProductSKU").toString();
  	String OrderedProductNameXML=xml.get("OrderedProductName").toString();
  	String OrderedProductQTYXML =xml.get("Quantity").toString();
		 
  	
  	
  	HashMap<String, String>  order=SFproductinfromation.get("order1");
  	System.out.println(order);
  	String productPrice=order.get("productPrice").replace("$", "");
  	String productSKU=order.get("productSKU");
  	String productname=order.get("productname");
 	String productQTY=order.get("productQTY");
 	//System.out.println(productPrice.contains(xmlproductprice)&&productSKU.equals(orderedProductSKUXML)&& productname.contains(OrderedProductNameXML)&&productQTY.contains(OrderedProductQTYXML));
    
 	//System.out.println(productPrice.contains(xmlproductprice));
	////
 	
 	//System.out.println(		productSKU.equals(orderedProductSKUXML));
	//System.out.println( productname.contains(OrderedProductNameXML));
 	
 	System.out.println(OrderedProductQTYXML.trim()+" this from xml");
 	
 	System.out.println(productQTY+" this from aplication");
 	
	System.out.println(OrderedProductQTYXML.contains(productQTY));
	Common.oppenURL(fileName);
	orderxmlvalidations("ordernumber", OrderNumberxml, Ordernumber);
	 orderxmlvalidations("productname",OrderedProductNameXML , productname);
	 orderxmlvalidations("product SKU",orderedProductSKUXML, productSKU);
	 orderxmlvalidations("productprice", xmlproductprice, productPrice);
	 orderxmlvalidations("product QTY", OrderedProductQTYXML, productQTY);
	 
	 Common.assertionCheckwithReport(xmlproductprice.contains(productPrice)&&productSKU.contains(orderedProductSKUXML)&& productname.contains(OrderedProductNameXML)&&OrderedProductQTYXML.contains(productQTY),"validating xml product infromation","order product inframtion matches to order xml product info","sucessfully matches product infromation"+order+"is Equal to xml infromation"+xml,"fail to match product infromatio with order xml iformation  product infromation="+order+"xmal infromation =="+xml);  //   System.out.println(productPrice  +"   " + xmlproductprice +"**" +productSKU+orderedProductSKUXML+productname+OrderedProductNameXML+productQTY+OrderedProductQTYXML);
	 
	 
  //  Common.assertionCheckwithReport(productSKU.equals(orderedProductSKUXML)&& productname.contains(OrderedProductNameXML)&&productQTY.contains(s),"validating xml product infromation","order product inframtion matches to order xml product info","sucessfully matches product infromation"+order+"is Equal to xml infromation"+xml,"fail to match product infromatio with order xml iformation  product infromation="+order+"xmal infromation =="+xml);
  	try {
    // Assert.assertTrue(productSKU.contains(orderedProductSKUXML)&& productname.contains(OrderedProductNameXML)&&productQTY.contains(OrderedProductQTYXML)); 
  	}
  	catch(Exception e) {
  		e.printStackTrace();
  	}
    	}

    	 public void viewcart() throws Exception {
    		 
    		 try {
    		 Thread.sleep(4000);
    			Common.clickElement("xpath", "//a[@aria-label='minicart']");
    			Thread.sleep(2000);
    			Common.clickElement("xpath", "//a[contains(@class,'viewcart')]");
    			
    			Thread.sleep(10000); 
    		 Common.assertionCheckwithReport(Common.getPageTitle().equals("Your Cart"), "validating view cart button", "after click view cart button user will nivating to cart page", "User successfully navigtaing to cart page","fail to navigate cartpage");	
    		 }
    		 catch (Exception | Error e) {
    				ExtenantReportUtils.addFailedLog("Validating view cart button", "if we click view cart button it will navgating to cart page ",
    						"User failed to navigate cartpage",
    						Common.getscreenShotPathforReport("QTY"));
    				Assert.fail();
    		 }
    		 
    	 }
    	 
    	 public HashMap<String,HashMap<String,String>> productinfromation() {
    		 
    		 
    		 
    		 
    		 int value=0;
    		List<WebElement> cartproducts= Common.findElements("xpath", "//div[@id='shopping-cart-table']//div[contains (@class,'cart-table-body')]");
    		HashMap<String,HashMap<String,String>> productinfromation=new HashMap<String,HashMap<String,String>>();
    		HashMap<String,String> singleproductinfromation;
    		
    		try {
    		
    		
    		for(int i=0;i<cartproducts.size();i++) {
    			 String productname="";
    		       value=i+1;
    		       singleproductinfromation= new HashMap<String,String>();
    			String productclass=cartproducts.get(i).getAttribute("class").replaceAll("cart-table-body table-item js-cart-item-", "");
    			
    		try {
    		     productname=	Common.findElement("xpath", "//div[contains (@class,'"+productclass+"')]//img").getAttribute("alt");
    		     }
    		catch(Exception e) {
    			  productname=Common.findElement("xpath", "//div[contains (@class,'"+productclass+"')]//h4").getText();
    			}
    		
    			singleproductinfromation.put("productname", productname);
    			String productSKU= Common.findElement("xpath", "//div[contains(@class,'"+productclass+"')]//input").getAttribute("data-cart-item-id");
    			singleproductinfromation.put("productSKU", productSKU);
    		    String productPrice= Common.findElement("xpath", "//div[contains (@class,'"+productclass+"')]//span[@class='price']").getText();
    		    singleproductinfromation.put("productPrice", productPrice);
    		    String productQTY= Common.findElement("xpath", "//div[contains (@class,'"+productclass+"')]//input").getAttribute("value");
    		    singleproductinfromation.put("productQTY", productQTY);
    		    productinfromation.put("order"+value ,singleproductinfromation);
    		    
    		    ExtenantReportUtils.addPassLog("Validating product infromation", "User get product name SKQ , QTY infroamtion   ", "User sucessfully get product infromation "+productinfromation,Common.getscreenShotPathforReport("productinfopass"));		  
    		   
    	}
    		
    		
    		}
    		 catch (Exception | Error e) {
 				ExtenantReportUtils.addFailedLog("Validating product infromation", "User get product name SKQ , QTY infroamtion",
 						"User failed to get product name SKQ , QTY infroamtion",
 						Common.getscreenShotPathforReport("productinfail"));
 				Assert.fail();
 		 }
    		
            System.out.println(productinfromation);
    		 
    		 return productinfromation;
    	 }
    	  
    	 public HashMap<String,String> addDeliveryAddress_validation(String dataSet) throws Exception {
 			HashMap<String,String> Shippingaddress=new HashMap<String,String>();
 			
 			try {
 				Sync.waitElementVisible("id", "customer-email-address");
 				Common.textBoxInput("id", "customer-email-address", data.get(dataSet).get("Email"));
 				Thread.sleep(5000);
 				String emailid=Common.findElement("id" ,"customer-email-address").getAttribute("value");
 				System.out.println("*****"+emailid+"*******");
 				Shippingaddress.put("emailid", emailid);
 				
 			} catch (NoSuchElementException e) {
 				checkOut();
 				Common.textBoxInput("id", "customer-email-address", data.get(dataSet).get("Email"));

 			}
 			
 		
 			String expectedResult = "email field will have email address";
 			try {
 				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",data.get(dataSet).get("FirstName"));
 				Thread.sleep(3000);
 				String ShippingFirstName=Common.findElement("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']").getAttribute("value");
 				System.out.println("*****"+ShippingFirstName+"*******");
 				Shippingaddress.put("ShippingFisrtName", ShippingFirstName);
 	            int size = Common.findElements("id", "customer-email-address").size();
 	            Common.assertionCheckwithReport(size > 0, "validating the email address field", expectedResult,"Filled Email address", "unabel to fill the email address");
 	            Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']",data.get(dataSet).get("LastName"));
 	            Thread.sleep(2000);
 	            String ShippingLastName=Common.findElement("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']").getAttribute("value");
 	            System.out.println("*****"+ShippingLastName+"*******");
 	            Shippingaddress.put("ShippingLastName", ShippingLastName);
 	            
 				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']",data.get(dataSet).get("Street"));
 				Thread.sleep(2000);
 				String ShippingAddress1=Common.findElement("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']").getAttribute("value");
 	            System.out.println("*****"+ShippingAddress1+"*******");
 	            Shippingaddress.put("ShippingAddress1", ShippingAddress1);
 				//String Text=Common.getText("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']");
 				
 				

 				try {
 					Common.clickElement("xpath", "//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
 				} catch (Exception e) {
 					Common.actionsKeyPress(Keys.BACK_SPACE);
 					Thread.sleep(1000);
 					Common.actionsKeyPress(Keys.SPACE);
 					Common.clickElement("xpath", "//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
 				}
 				if (data.get(dataSet).get("StreetLine2") != null) {
 					Common.textBoxInput("name", "street[1]", data.get(dataSet).get("Street"));
 					Thread.sleep(2000);
 					String street1=Common.findElement("name", "street[1]").getAttribute("value");
 		            System.out.println("*****"+street1+"*******");
 					
 					String text=Common.getText("name","street[1]");
 					System.out.println(text);
 					
 				}
 				if (data.get(dataSet).get("StreetLine3") != null) {
 					Common.textBoxInput("name", "street[2]", data.get(dataSet).get("Street"));
 					Thread.sleep(2000);
 					String street2=Common.findElement("name", "street[2]").getAttribute("value");
 		            System.out.println("*****"+street2+"*******");
 				}
 				Sync.waitPageLoad();
 				Thread.sleep(5000);
 				Common.findElement("xpath", "//form[@id='co-shipping-form']//input[@name='city']").clear();
 				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='city']",data.get(dataSet).get("City"));
 				Thread.sleep(2000);
 				String ShippingCity=Common.findElement("xpath", "//form[@id='co-shipping-form']//input[@name='city']").getAttribute("value");
 	            System.out.println("*****"+ShippingCity+"*******");
 	            Shippingaddress.put("ShippingCity", ShippingCity);
// 				System.out.println(data.get(dataSet).get("City"));
 				
 				Common.actionsKeyPress(Keys.PAGE_DOWN);
 				Thread.sleep(3000);
 				try {
 					Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
 					Thread.sleep(2000);
 					String Shippingvalue=Common.findElement("name", "region_id").getAttribute("value");
 					String Shippingstate=Common.findElement("xpath","//select[@name='region_id']//option[@value='"+Shippingvalue+"']").getText();
 					 
 		            Shippingaddress.put("ShippingState", Shippingstate);
 				} catch (ElementClickInterceptedException e) {
 					Thread.sleep(3000);
 					Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
 				}
 				
 				Thread.sleep(2000);
 				Common.textBoxInputClear("name", "postcode");
 				Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
 				Thread.sleep(2000);
 				String ShippingZip=Common.findElement("name", "postcode").getAttribute("value");
 	            System.out.println("*****"+ShippingZip+"*******");
 	            Shippingaddress.put("ShippingZip", ShippingZip);
 				Thread.sleep(5000);
 				
 				Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
 				Thread.sleep(2000);
 				String ShippingPhone=Common.findElement("name", "telephone").getAttribute("value");
 	            System.out.println("*****"+ShippingPhone+"*******");
 	            Shippingaddress.put("ShippingPhone", ShippingPhone);
 				
 	            

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
 			int size=Common.findElements("xpath", "//input[@id='label_method_bestway']").size();
 			if(size>0){
 				Common.clickElement("xpath", "//input[@id='label_method_bestway']");
 			}

 			expectedResult = "shipping address is filled in to the fields";
 			Common.clickElement("xpath", "//button[@data-ac-test='form-shipping-address_action_submit']");

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
 			System.out.println(Shippingaddress);
 			return Shippingaddress;
 	 }
 		
 	 
     	 public void shippingvalidaing_GustUserXML(HashMap<String,String> shippingaddress,String ordernumber) throws IOException, Exception {
     		 ArrayList<String> orderxmlinfromation=new ArrayList<String>();
     	try {	 
          Thread.sleep(5000);
     	 String fileName=System.getProperty("user.dir")+"\\TestLogs\\Download\\Export_"+ordernumber+".xml";
     	 //String fileName="C:\\Users\\admin\\Downloads\\Export_4000420945A.xml";
     	 Map<String, Object> jsonInMap=xmlReader.fetchvalues(fileName);
     	 
     	 
     	 
     	                             			
     	 String ShippingFirstName= (String) jsonInMap.get("ShippingFirstName");
     	 String ShippingLastName= (String) jsonInMap.get("ShippingLastName");
     	 String ShippingAddress1= (String) jsonInMap.get("ShippingAddress1");
     	 String ShippingCity= (String) jsonInMap.get("ShippingCity");
     	 String ShippingState= (String) jsonInMap.get("ShippingState");
     	 String ShippingZip=(String) jsonInMap.get("ShippingZip");
     	 String customermail=(String) jsonInMap.get("cust_to_email");
     	 
     	//ArrayList<String> orderxmlinfromation=new ArrayList<String>();
     	
     	orderxmlinfromation.add("ShippingFirstName="+ShippingFirstName);
     	orderxmlinfromation.add("ShippingLastName="+ShippingLastName);
     	orderxmlinfromation.add("ShippingAddress1="+ShippingAddress1);
     	orderxmlinfromation.add("ShippingCity="+ShippingCity);
     	orderxmlinfromation.add("ShippingState="+ShippingState);
     	orderxmlinfromation.add("ShippingZip="+ShippingZip);

     	  String SfCustomerEmail=shippingaddress.get("emailid");
     	 String Sffirstname=shippingaddress.get("ShippingFisrtName");
     	 String SfLastname =shippingaddress.get("ShippingLastName");
     	 String SfStreet =shippingaddress.get("ShippingAddress1");
     	 String Sfcity =shippingaddress.get("ShippingCity");
     	 String SfRegion =shippingaddress.get("ShippingState");
     	 String Sfpostcode =shippingaddress.get("ShippingZip");
     	 
     	orderxmlvalidations("customerEmail", customermail, SfCustomerEmail);
		 orderxmlvalidations("Shiiping first name",ShippingFirstName , Sffirstname);
		 orderxmlvalidations("Shiiping last name",ShippingLastName, SfLastname);
		 orderxmlvalidations("Shiiping Street address", ShippingAddress1, SfStreet);
		 orderxmlvalidations("Shiiping City ", ShippingCity, Sfcity);
		 
		 orderxmlvalidations("Shiiping shipping zip code", ShippingZip, Sfpostcode);
     	
     	 Common.assertionCheckwithReport(SfCustomerEmail.contains(customermail)&&ShippingFirstName.contains(Sffirstname)&&SfLastname.contains(ShippingLastName)&&SfStreet.contains(ShippingAddress1)&&Sfcity.contains(ShippingCity)&&ShippingZip.contains(Sfpostcode), "Validating orderxml infromation with order shipping address","Order shipping address shoud match to order export xml adress","sucessfully order xml infromation matches to export xml adress"," un match the storefront shipping address"+shippingaddress+"    with  "+"   Orderxml infromation"+orderxmlinfromation);
     	}
     	
     	catch (Exception | Error e) {

     		e.printStackTrace();
    			ExtenantReportUtils.addFailedLog("Validating orderxml infromation with order shipping address","Order shipping address shoud match to order export xml adress","un match the storefront shipping address"+shippingaddress+"    with  "+"   Orderxml infromation"+orderxmlinfromation,
    					Common.getscreenShotPathforReport("shipingaddressfaieldxml"));
    			// ExtenantReportUtils.addFailedLog("User click check out button",
    			// "User unabel click the checkout button",
    			// Common.getscreenShotPathforReport("check out miniCart"));
    			Assert.fail();
    			
    		}
     	
     	 }
     	public HashMap<String,String> orderamountinfromation() {
  			// TODO Auto-generated method stub
  			HashMap<String,String> data=new HashMap<String,String>();
  			try{			    
  				Thread.sleep(5000);
  	            String subtotla=Common.getText("xpath", "//tr[@class='totals sub']/td/span").replace("$", "");
  		       // subtotla.replace("", newChar)
  		        data.put("subtotlaValue",subtotla);
  		        String shippingammount=Common.getText("xpath", "//span[@data-th='Shipping']").replace("$", "");
  		        data.put("shippingammountvalue",shippingammount);
  			    String TaxAmmount=Common.getText("xpath", "//td[@data-th='Tax*']//span").replace("$", "");
  		        data.put("Taxammountvalue",TaxAmmount);
  			    String TotalAmmount=Common.getText("xpath", "//tr[@class='grand totals incl']//span").replace("$", "");
  		        data.put("TotalAmmount", TotalAmmount);
  		        
  		        String shippingmetho=Common.getText("xpath", "//div[@class='checkout-summary-shipping-method']").replace("(", "").replace(")", "");
  		      data.put("Shippingmethod", shippingmetho);
  		 Common.assertionCheckwithReport(!subtotla.equals(null),"verifying order amout detiles", "getting all the Billing ammount infromation","successfully get the total billing amount infromation ", "faiel to get billing ammount");
	 		}
 	 catch(Exception |Error e)
 		{
 			report.addFailedLog("verifying tax billing amount", "getting price values from billing  page  ", "Faield to get price value from billing page", Common.getscreenShotPathforReport("TaxRatesbilling"));

 			e.printStackTrace();
 			Assert.fail();
 			
 	}
  			System.out.println(data);
  			return  data;
  			
  			}
    	     	  
     	  public void TotalvalidationXML(HashMap<String,String> data,String Order) throws IOException, Exception {
  		    Thread.sleep(5000);

  		String fileName=System.getProperty("user.dir")+"\\TestLogs\\Download\\Export_"+Order+".xml";
  		//String fileName="C:\\Users\\admin\\Downloads\\Export_4000420945A.xml";
  		Map<String, Object> jsonInMap=xmlReader.fetchvalues(fileName);
  	
  		String OrderShippingCosts= (String) jsonInMap.get("OrderShippingCosts");
  		String tax_amt= (String) jsonInMap.get("tax_amt");
  		String order_total= (String) jsonInMap.get("order_total");
  		String shippingmethodxml= (String) jsonInMap.get("shippingmethod");
                        

  		 
  		 String Sfshipping =data.get("shippingammountvalue");
  		 String SfTaxamount =data.get("Taxammountvalue");
  		 String Sforder =data.get("TotalAmmount");
  		 String SfShippingmethod=data.get("Shippingmethod");

  		 if(SfShippingmethod.contains("Standard")) {
  			 
  			 SfShippingmethod="Standard - Fedex Ground Home Deliver";
  		 }
  		 
  		 
  		 
  			System.out.println(order_total.contains(Sforder));
      		System.out.println(tax_amt.contains(SfTaxamount));
      		System.out.println( Sfshipping.contains(OrderShippingCosts));

      		
      		 orderxmlvalidations("gross_total",order_total , Sforder);
      		 orderxmlvalidations("tax_amount",tax_amt, SfTaxamount);
      		 orderxmlvalidations("OrderShippingCosts", OrderShippingCosts, Sfshipping);
      		 orderxmlvalidations("Shipping method", shippingmethodxml, SfShippingmethod);
      		 
      		
      		
      		//System.out.println(OrderShippingCosts.contains(Sfshipping));
      		
  		Common.assertionCheckwithReport(order_total.contains(Sforder) && tax_amt.contains(SfTaxamount)&&OrderShippingCosts.contains(Sfshipping), 
  				"verify Tax and total ammount shipping cost with on order xml",
  				"Address must  matching to orderxml ",
  				 "product shiping tax and order total "+ data+ " Matches to xml infromation "+ "OrderShippingCosts="+OrderShippingCosts+" tax_amt="+tax_amt+" prder_total "+order_total,
  				
  				 "product shiping tax and order total "+ data+ " is not Matches to xml infromation "+ "OrderShippingCosts="+OrderShippingCosts+" tax_amt="+tax_amt+" prder_total "+order_total);
  		} 
     	  public void orderxmlvalidations(String Details,String XML,String Web)
    		{
    			//String fileOut="";
    		try{
    			
    			File file=new File(System.getProperty("user.dir")+"/src/test/resources/OrderValidation.xlsx");
    			XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
    			XSSFSheet sheet;
    			Row row;
    			Cell cell;
    			int rowcount;
    			sheet = workbook.getSheet("TaxDetails");
    			
    			if((workbook.getSheet("xmlvalidation"))==null)
    			{
    			sheet = workbook.createSheet("xmlvalidation");
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
    			cell.setCellValue("XMLvalidation");
    			
    			row = sheet.createRow(1);
    			cell = row.createCell(0);
    			cell.setCellStyle(cs);
    			cell.setCellValue("Details");
    			cell = row.createCell(1);
    			cell.setCellStyle(cs);
    			cell.setCellValue("Web");
    			cell = row.createCell(2);
    			cell.setCellStyle(cs);
    			cell.setCellValue("XML");
    			cell=row.createCell(3);
    			cell.setCellStyle(cs);
    			cell.setCellValue("Status");
    			rowcount=2;
    			
    			}
    			
    			else
    			{
    			
    			sheet=workbook.getSheet("xmlvalidation");	
    			rowcount=sheet.getLastRowNum()+1;
    			}
    			row = sheet.createRow(rowcount);
    			cell = row.createCell(0);
  			cell.setCellValue(Details);
  			cell = row.createCell(1);
  			cell.setCellType(CellType.NUMERIC);
  			cell.setCellValue(Web);
  			cell = row.createCell(2);
  			cell.setCellType(CellType.NUMERIC);
  			cell.setCellValue(XML);
  			cell = row.createCell(3);
  			cell.setCellType(CellType.STRING);
  			
  			String status;
    	
    			if(XML.contains(Web))
    			{
    				Thread.sleep(4000);
    				status="pass";
    			}
    			else
    			{
    				status="Fail";
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
    	  public String verifyingSucesspage() throws Exception {
    			String order="";
    			String expectedResult = "It redirects to order confirmation page";

    					
    			Thread.sleep(3000);
    			int placeordercount = Common.findElements("xpath", "//span[text()='Place Order']").size();
    			
    			if (placeordercount > 1) {
    				Common.clickElement("xpath", "//span[text()='Place Order']");
    			}

    			String url=automation_properties.getInstance().getProperty(automation_properties.BASEURL);
    			
    			if(!url.contains("stg")){
    				
    			}
    			
    			else{
    				try{
    			String sucessMessage = Common.getText("xpath", "//h1[@class='checkout-success-title']").trim();
    			
    			int sizes = Common.findElements("xpath", "//h1[@class='checkout-success-title']").size();
    			Common.assertionCheckwithReport(sucessMessage.equals("Your order has been received"),
    					"verifying the product confirmation", expectedResult,
    					"Successfully It redirects to order confirmation page Order Placed",
    					"User unabel to go orderconformation page");
    			//order=Common.getText("xpath", "//a[@class='order-number']/strong");

    			order=Common.getText("xpath", "//div[@class='checkout-success']/p/span");
    			
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
    	  public HashMap<String,String>  add_PaymentDetails(String dataSet) throws Exception {
  			
  			HashMap<String,String> Payment=new HashMap<String,String>();
  			Thread.sleep(4000);
  			String expectedResult = "land on the payment section";
  			
  		
  			try {
  				
  				Sync.waitElementClickable("xpath", "//label[@for='ime_paymetrictokenize']");
  				int sizes=Common.findElements("xpath", "//label[@for='ime_paymetrictokenize']").size();
                  Common.assertionCheckwithReport(sizes>0, "Successfully land on the payment section", expectedResult,"User unabel to land opaymentpage");
  				Common.clickElement("xpath", "//label[@for='ime_paymetrictokenize']");
  				Thread.sleep(2000);
  				Common.switchFrames("id", "paymetric_xisecure_frame");
  				Common.dropdown("xpath", "//select[@id='c-ct']", Common.SelectBy.TEXT, data.get(dataSet).get("cardType"));
  				String Cardtype=Common.findElement("xpath", "//select[@id='c-ct']").getAttribute("value");
  				String Card=Common.findElement("xpath","//select[@id='c-ct']//option[@value='"+Cardtype+"']").getText();
  			    Payment.put("Card", Card);
  				Common.textBoxInput("id", "c-cardnumber", data.get(dataSet).get("cardNumber"));
  				String Cardnumber=Common.findElement("id", "c-cardnumber").getAttribute("value");
  				System.out.println("******"+Cardnumber+"*****");
  				Payment.put("Cardnumber", Cardnumber);
  				Common.dropdown("xpath", "//select[@id='c-exmth']", Common.SelectBy.TEXT,data.get(dataSet).get("ExpMonth"));
  				String ExpMonth=Common.findElement("xpath", "//select[@id='c-exmth']").getAttribute("value");
  				System.out.println("*******"+ExpMonth+"****");
  				Payment.put("ExpMonth", ExpMonth);
  				Common.dropdown("xpath", "//select[@id='c-exyr']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpYear"));
  				String ExpYear=Common.findElement("xpath", "//select[@id='c-exyr']").getAttribute("value");
  				System.out.println("*******"+ExpYear+"****");
  				Payment.put("ExpYear", ExpYear);
  				Common.textBoxInput("id", "c-cvv", data.get(dataSet).get("cvv"));
  				String cvv=Common.findElement("id","c-cvv").getAttribute("value");
  				System.out.println("*******"+cvv+"****");
  				Payment.put("cvv", cvv);
  				Thread.sleep(2000);
  				Common.actionsKeyPress(Keys.ARROW_DOWN);
  				Common.switchToDefault();
  				Thread.sleep(1000);
  				Common.clickElement("xpath", "//span[text()='Place Order']");
  				
  				
  				//Common.clickElement("xpath", "//button[@title='Place Order']");

  			}

  			catch (Exception | Error e) {
  				e.printStackTrace();
  				 
  				ExtenantReportUtils.addFailedLog("validating the Credit Card infromation", expectedResult,
  						"faield  to fill the Credit Card infromation",
  						Common.getscreenShotPathforReport("Cardinfromationfail"));
  				
  				Assert.fail();
  	          }

  			expectedResult = "credit card fields are filled with the data";
  			String errorTexts = Common.findElement("xpath", "//div[contains(@id,'error')]").getText();
  			Common.assertionCheckwithReport(errorTexts.isEmpty(), "validating the credit card information with valid data",
  			expectedResult, "Filled the Card detiles", "missing field data it showinng error");
  			return Payment;
  		}

  	  
  	  public void card_details_validationXML(HashMap<String,String> Payment,String Order) throws IOException, Exception {
            Thread.sleep(5000);

        String fileName=System.getProperty("user.dir")+"\\TestLogs\\Download\\Export_"+Order+".xml";
       Map<String, Object> jsonInMap=xmlReader.fetchvalues(fileName);
       String payment_meth= (String) jsonInMap.get("payment_meth");
       String CardType= (String) jsonInMap.get("CardType");
       String CardExpirationMonth= (String) jsonInMap.get("CardExpirationMonth");
       String CardExpirationYear= (String) jsonInMap.get("CardExpirationYear");
      //  String cvv= (String) jsonInMap.get("cvv");

         String Sfcardtype=Payment.get("Card");
         String Sfcardnumber =Payment.get("Cardnumber");
         String Sfexpmonth =Payment.get("ExpMonth");
         String Sfexpyear =Payment.get("ExpYear");
       // String Sfcvv =Payment.get("cvv");

         orderxmlvalidations("customer cart type", CardType, Sfcardtype);   
         orderxmlvalidations("card expir month", CardExpirationMonth, Sfexpmonth); 
         orderxmlvalidations("card expir year", CardExpirationYear, Sfexpyear); 
Common.assertionCheckwithReport(CardType.contains(Sfcardtype)&&CardExpirationMonth.contains(Sfexpmonth)&&CardExpirationYear.contains(Sfexpyear), "verify address should match on order xml", "store front application data matches to order xml infromatio","sucessfully matchs the web infromaation with order xml","faield to match web order infromation with order xml");
}
  	 public void Billingaddress_GustUserXML_Validation(HashMap<String,String> Billingaddress,String ordernumber) throws IOException, Exception {
 		 ArrayList<String> orderxmlinfromation=new ArrayList<String>();
 	try {	 
      Thread.sleep(5000);
 	 String fileName=System.getProperty("user.dir")+"\\TestLogs\\Download\\Export_"+ordernumber+".xml";
 	
 	 Map<String, Object> jsonInMap=xmlReader.fetchvalues(fileName);
 	 
 	 
 	 
 	                             			
 	 String BillingFirstName= (String) jsonInMap.get("BillingFirstName");
 	 String BillingLastName= (String) jsonInMap.get("BillingLastName");
 	 String BillingAddress1= (String) jsonInMap.get("BillingAddress1");
 	 String BillingCity= (String) jsonInMap.get("BillingCity");
 	 String BillingState= (String) jsonInMap.get("BillingState");
 	 String BillingZip=(String) jsonInMap.get("BillingZip");
 	
 	 
 	//ArrayList<String> orderxmlinfromation=new ArrayList<String>();
 	
 	orderxmlinfromation.add("ShippingFirstName="+BillingFirstName);
 	orderxmlinfromation.add("ShippingLastName="+BillingLastName);
 	orderxmlinfromation.add("ShippingAddress1="+BillingAddress1);
 	orderxmlinfromation.add("ShippingCity="+BillingCity);
 	orderxmlinfromation.add("ShippingState="+BillingState);
 	orderxmlinfromation.add("ShippingZip="+BillingZip);

 	 String Sffirstname=Billingaddress.get("ShippingFisrtName");
 	 String SfLastname =Billingaddress.get("ShippingLastName");
 	 String SfStreet =Billingaddress.get("ShippingAddress1");
 	 String Sfcity =Billingaddress.get("ShippingCity");
 	 String SfRegion =Billingaddress.get("ShippingState");
 	 String Sfpostcode =Billingaddress.get("ShippingZip");
 	 
 	
	 orderxmlvalidations("Billing first name",BillingFirstName , Sffirstname);
	 orderxmlvalidations("Billing last name",BillingLastName, SfLastname);
	 orderxmlvalidations("Billing Street address", BillingAddress1, SfStreet);
	 orderxmlvalidations("Billing City ", BillingCity, Sfcity);
	 
	 orderxmlvalidations("Billing  zip code", BillingZip, Sfpostcode);
 	
 	 Common.assertionCheckwithReport(BillingFirstName.contains(Sffirstname)&&SfLastname.contains(BillingLastName)&&SfStreet.contains(BillingAddress1)&&Sfcity.contains(BillingCity)&&BillingZip.contains(Sfpostcode), "Validating orderxml infromation with order Billing address","Order Billing address shoud match to order export xml adress","sucessfully order xml infromation matches to export xml  Billing adress"," un match the storefront Billing address"+Billingaddress+"    with  "+"   Orderxml infromation"+orderxmlinfromation);
 	}
 	
 	catch (Exception | Error e) {

 		e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating orderxml infromation with order shipping address","Order shipping address shoud match to order export xml adress","un match the storefront shipping address"+Billingaddress+"    with  "+"   Orderxml infromation"+orderxmlinfromation,
					Common.getscreenShotPathforReport("shipingaddressfaieldxml"));
			
			Assert.fail();
			
		}
 	
 	 }
  	public void ValidatingTranctionIDInXML(String Ordernumber,String TrantionID) {
		 try {
		 Thread.sleep(5000);
		 String fileName=System.getProperty("user.dir")+"\\TestLogs\\Download\\Export_"+Ordernumber+".xml";
		 Map<String, Object> jsonInMap=xmlReader.fetchvalues(fileName);
		 String XMLTrantionID=jsonInMap.get("TransactionID").toString();
		
		 orderxmlvalidations("TrantionID", XMLTrantionID, TrantionID);
		 Common.assertionCheckwithReport(XMLTrantionID.contains(TrantionID), "Validating  Weborder Trantion id with Order xml trantion id", "Admin web trantion is must match to xml trantion id", "success fully matches admin web order trantionid with xml trantion id", "fail to match web trantion id with orderxml trantion id");	 
		 }
		 catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("Validating  Weborder Trantion id with Order xml trantion id", "Admin web trantion is must match to xml trantion id ",
					"fail to match web trantion id with orderxml trantion id",
					Common.getscreenShotPathforReport("TrantionIDXMLValidation"));
			Assert.fail();
	 }
		 
		 }
  	
  	
  	
  	
  	public void verifying_NoTax_field() {
  	    
  	    
  	    try {
  	        Sync.waitPageLoad();
  	       
  	       int verify_NoTax=Common.findElements("xpath", "(//th[text()='Tax*'])").size();
  	        System.out.println(verify_NoTax);
  	    

  	         Common.assertionCheckwithReport(verify_NoTax<1,"Verifying  No tax in Payment page","Should Not display tax at right of the Payment details page", "successfully  tax  Not displayed at right of the Payment details page", "Order summary No tax payment Page");
  	  }catch(Exception |Error e) {
  	       ExtenantReportUtils.addFailedLog("Verifying No tax in Payment page","Should Not display tax at right of the  Payment details page", "user unable to find not tax on Payment page", Common.getscreenShotPathforReport("failed to find No tax on payment Page"));           
  	       Assert.fail();   
  	       }
  	  
  	}


  	public void Back_In_Stock_SearchProduct(String dataSet) throws Exception {
		Thread.sleep(8000);
		try {
			Sync.waitElementVisible("xpath", "//form[@id='search_mini_form']//label");
			Thread.sleep(8000);
			Common.clickElement("xpath", "//form[@id='search_mini_form']//label");
			Common.textBoxInput("xpath", "//input[@id='search']", dataSet);
			ExtenantReportUtils.addPassLog("validating Search box", "enter product name will display in search box",
					"user enter the product name in  search box", Common.getscreenShotPathforReport("searchproduct"));
		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("validating Search box", "enter product name will display in search box",
					"User failed to enter product name", Common.getscreenShotPathforReport("searchproduct"));
			Assert.fail();

		}
		try {
			Thread.sleep(8000);
			Common.actionsKeyPress(Keys.ENTER);
			Thread.sleep(8000);
			List<WebElement> productsizes = Common.findElements("xpath", "//li[contains(@class,'product-item')]");
			for (int i = 0; i < productsizes.size(); i++) {
				String attribute = productsizes.get(i).getAttribute("data-product-id");
				List<WebElement> productColors = Common.findElements("xpath", "//li[contains(@data-product-id,'"
						+ attribute + "')]//div[contains(@class,'swatch-attribute-options clearfix')]/div");
				if (productColors.size() <= 1 && productColors.size() > 0) {
					Common.clickElement("xpath", "//img[contains(@alt,'Large Closeable Press-In Lid - Black')]");
					ExtenantReportUtils.addPassLog("validating sample product", "user find sample product in search",
							"user successfully find sample product in search",
							Common.getscreenShotPathforReport("searchproductsample"));

					break;
				}
			}

		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("validating sample product", "user find sample product in search",
					"User failed find sample product in search",
					Common.getscreenShotPathforReport("searchproductsample"));
			Assert.fail();
		}
		}
	public void Verify_OutofStockPDP() {

	    	 try {
	    	        
	    	
	    			 Thread.sleep(2000);
	    			 Common.clickElement("xpath", "(//a[@title='Notify me when available'])");
	    			 Thread.sleep(3000);
	    			 //Common.actionsKeyPress(Keys.PAGE_DOWN);
	    			 String OSS=Common.findElement("xpath","//a[@title='Notify me when available']").getText();
	    			 
	    			 Common.assertionCheckwithReport(OSS.equals("NOTIFY ME WHEN AVAILABLE"), "To verify the PDP with out of stock", "Should land on out of stock PDP page","User unable to land on Out of Stock PDP", "faield to land on out of stock PDP page");
	    			 //ExtenantReportUtils.addPassLog("To verify the Product description page with out of stock", "Should land on out of stock Product description page", "user successfully landed on out of stock product description page", Common.getscreenShotPathforReport("Successfully landed on  out of stock PDP page"));

	    			 	//Common.assertionCheckwithReport(Addtobag.equals("Add to Bag"), "To verify the PDP Page", "Should land on  PDP page", "user successfully landed on PDP page", Common.getscreenShotPathforReport(""));
	    			 }catch(Exception |Error e) {
	    			 		ExtenantReportUtils.addFailedLog("To verify the  the PDP Page with out of stock","Should land on out of stock PDP page", "user unable to land on  Out of Stock PDP", Common.getscreenShotPathforReport("failed to land on out of stock PDP page"));			
	    			 		Assert.fail();	
	    			 		}
	    			 	}
	public void Out_Of_Stock_Subscription(String DataSet){

	    try {
	          Sync.waitPageLoad();
	          Thread.sleep(5000);
	          Sync.waitElementPresent("id", "stock-notification-email");
	          Common.textBoxInput("id", "stock-notification-email",data.get(DataSet).get("Email"));
	          Thread.sleep(3000);
	         ExtenantReportUtils.addPassLog("To verify the Email text field", "Should enter the email address in the email text field", "user successfully entered the Email address", Common.getscreenShotPathforReport("Successfully Entered Email address"));

		//Common.assertionCheckwithReport(Addtobag.equals("Add to Bag"), "To verify the PDP Page", "Should land on  PDP page", "user successfully landed on PDP page", Common.getscreenShotPathforReport(""));
	}catch(Exception |Error e) {
			ExtenantReportUtils.addFailedLog("To verify the Email text field","Should enter the email address in the email text field ", "user unable to Enter Email address", Common.getscreenShotPathforReport("failed to enter Email address"));			
			Assert.fail();	
			}
	try {
	    Common.clickElement("xpath", "//button[@class='action subscribe primary']"); 
	    Thread.sleep(4000);
	    int message=Common.findElements("xpath", "(//div[@class='notification-title'])[3]").size();

		 Common.assertionCheckwithReport(message>0, "To verify Back to Stock Success message","Should add Product to Back to stock alert Subscription", "Product successfully added to Back to Stock Alert Subscription","user failed to add the product to back in stock subscription");

	    }catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("To verify back to stock success message","Should add product to back to stock alert subscription", "user successfully added to back to stock alert subscripton", Common.getscreenShotPathforReport("failed to product add the back to stock alert subscription"));			
		Assert.fail();	
		}
	}
	
	
public void PLP_Validation(String DataSet) throws Exception {
        
        String expectedResult="User should land on the home page";
        
        try{
        Thread.sleep(5000);
        int size = Common.findElements("xpath", "//a[@class='logo']").size();
        Common.assertionCheckwithReport(size > 0, "validating the home page ", expectedResult,"Successfully landed on the home page", "User unabel to land on home page");
        
        Sync.waitElementClickable("xpath", "//ul[@class='megamenu-list']/li[1]/div[1]");
        Thread.sleep(3000);
        Common.mouseOverClick("xpath", "//ul[@class='megamenu-list']/li[1]/div[1]/button");
        Thread.sleep(3000);
        expectedResult = "User should click the" + DataSet;
        try {
            Common.mouseOver("xpath", "//a[contains(text(),'" + DataSet + "')]");
        } catch (Exception e) {
            Common.clickElement("xpath", "//ul[@class='megamenu-list']/li[1]/div[1]/button");
        }
        Thread.sleep(1000);
        Common.clickElement("xpath", "//a[contains(text(),'" + DataSet + "')]");
        Thread.sleep(4000);
        expectedResult = "User should select the " + DataSet + "category";
        int sizebotteles = Common.findElements("xpath", "//a[contains(text(),'" + DataSet + "')]").size();
        Common.assertionCheckwithReport(sizebotteles > 0,
                "validating the product category as" + DataSet + "from navigation menu ", expectedResult,
                "Selected the " + DataSet + " category", "User unabel to click" + DataSet + "");
} catch (Exception | Error e) {

 
          e.printStackTrace();
            ExtenantReportUtils.addFailedLog("validating the plp page", expectedResult,
                    "unabel to validate plp page", Common.getscreenShotPathforReport("failed to validate plp paga"));
            
            e.printStackTrace();
            Assert.fail();
        }
}
    	   
public void clorevliadtion(String colorname) {
	  try {
	 Common.waitAndClick("xpath","(//button[@class='filters-options-trigger js-filters-options-trigger'])[1]");
     
     Common.clickElement("xpath","//a[@aria-label='Black']");
     Thread.sleep(5000);
    Common.actionsKeyPress(Keys.PAGE_DOWN);
     
    String color=Common.getText("xpath", "//ol[@class='products list items product-items']");
    System.out.println(color);
    Thread.sleep(3000);
ArrayList<String> productlinks=new ArrayList<String>();
    	
	  	
	  int j=0;
	List<WebElement> listofproducts=  Common.findElements("xpath", "//ol[contains(@class,'product-items')]//li");
	for(int i=0;i<listofproducts.size();i++) {
		Thread.sleep(5000);
		String id=listofproducts.get(i).getAttribute("data-product-id");
		Thread.sleep(5000);
		String productlink=Common.findElement("xpath", "//a[contains(@class,'product-item-photo js-pdp-link-"+id+"')]").getAttribute("href");
		
	if(	productlink.contains("color="+colorname+"") ||productlink.contains("bundle")) {
		
		//ExtenantReportUtils.addPassLog ("","plp page selected  "+ colorname +"must matched to following product color","this is passed to following products links are matched to selected color"+"Common.getscreenShotPathforReport("passed the color validation"));	
	ExtenantReportUtils.addPassLog("Verification of "+ colorname +" Color filter in PLP","Should display only "+ colorname +" color products in PLP","Successfully displaying"+ colorname +" products in PLP",Common.getscreenShotPathforReport("passed the color validation"));
	
	}
	else {
		
		j++;
		productlinks.add(productlink);
		
		if(j>1) {
			ExtenantReportUtils.addFailedLog("products should land on plp page","plp page selected  "+ colorname +"must mached to following product clor","this is failed to to following products links not matched to selected color"+"  "+colorname ,Common.getscreenShotPathforReport("failedcolorvalidation"));
}
	}
	}
	  }

	catch (Exception | Error e) {
 
       e.printStackTrace();
         ExtenantReportUtils.addFailedLog("validating the plp page", colorname,
                 "unabel to validate plp page", Common.getscreenShotPathforReport("failed to validate plp paga"));
         
         e.printStackTrace();
         Assert.fail();
 }

	}
	public void color_validation(String dataset) throws Exception {
	
	try {
	String products=data.get(dataset).get("product");
    String[] productnames=products.split(",");
   
    Sync.waitPageLoad();
    for(int i=0;i<productnames.length;i++) {
 	   System.out.println(productnames[i]); 
 	  //int j=1+i;
 	    Thread.sleep(3000);

      String product1=Common.findElement("xpath", "//img[@alt='"+productnames[i]+"']").getAttribute("alt");
      System.out.println(product1); 
       Thread.sleep(5000);
     Common.assertionCheckwithReport(product1.equals(productnames[i]),"verifying Black color products plp page ","verify and select the Black color products plp page","user will see Black color products plp page",Common.getscreenShotPathforReport("productnames")); 
    }
     
   Thread.sleep(3000);
    
    
     } catch (Exception | Error e) {


       e.printStackTrace();
         ExtenantReportUtils.addFailedLog("validating the plp page", dataset,
                 "unabel to validate plp page", Common.getscreenShotPathforReport("failed to validate plp paga"));
         
         e.printStackTrace();
         Assert.fail();
     }
	
}

	public void Validation_ofVolume(String dataset) throws Exception {
	try{
        Common.waitAndClick("xpath","(//button[@class='filters-options-trigger js-filters-options-trigger'])[2]");
        Thread.sleep(5000);
        Common.clickElement("xpath","(//span[@class='filters-options-label'])[1]");
        Common.actionsKeyPress(Keys.PAGE_DOWN);
        Thread.sleep(8000);
       // Common.actionsKeyPress(Keys.PAGE_UP);

        Common.findElement("xpath","(//span[@class='filters-current-value'])[2]");

        String volume=Common.getText("xpath", "(//span[@class='filters-current-value'])[2]");
        //System.out.println(volume);
        Common.assertionCheckwithReport(volume.equals("18 Oz"),"verifying 18 oz products plp page ", "verify and select the filter in plp page","user will see 18 oz products plp page",Common.getscreenShotPathforReport("18 oz plp page"));
	
	

} catch (Exception | Error e) {


  e.printStackTrace();
    ExtenantReportUtils.addFailedLog("validating the plp page", dataset,
            "unabel to validate plp page", Common.getscreenShotPathforReport("failed to validate plp paga"));
    
    e.printStackTrace();
    Assert.fail();
}
        
}     

  
  	  
  	  
public void New_Account_Enquiry(String dataSet)throws Exception{
		
		
		String expectedResult = "User should land on the home page";
	     Thread.sleep(5000);
		int size = Common.findElements("xpath", "//a[@class='logo']").size();
		Common.assertionCheckwithReport(size > 0, "Verifying the Homepage", "User Successfully landed on the home page", expectedResult,
				"User unable to land on home page");
		
		Common.actionsKeyPress(Keys.END);
		Thread.sleep(5000);

	
		try {
			Sync.scrollDownToView("xpath", "//a[contains(text(),'Dealer Central')]");
			Sync.waitElementPresent("xpath", "//a[contains(text(),'Dealer Central')]");
			Common.clickElement("xpath", "//a[contains(text(),'Dealer Central')]");
			expectedResult = "User is redirected to Dealer Central page";
			int sizedeal = Common.findElements("xpath", "//a[contains(text(),'Dealer Central')]").size();
			Common.assertionCheckwithReport(sizedeal > 0, "verifying Dealer Central page", expectedResult,
					"Successfully User is redirected to Dealer Central page", "User is unable to redirected to Dealer Central page");
		}

		catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("verifying Dealer Central page", "user redirected to Dealer Central page",
					"User failed to redirected to Dealer Central page", Common.getscreenShotPathforReport("Dealer CentralPage"));
			Assert.fail();
		}

		
		try {
			
			Sync.waitElementPresent("xpath", "//a[contains(text(),'New Account Inquiry')]");
			Common.clickElement("xpath", "//a[contains(text(),'New Account Inquiry')]");
			expectedResult = "User is redirected to New Account Inquiry page";
			Thread.sleep(4000);
			String Title= Common.getPageTitle();
			Common.assertionCheckwithReport(Title.equals("New Account Inquiry"), "verifying New Account Inquiry page", expectedResult,
					"Successfully User is redirected to New Account Inquiry page", "User is unable to redirected to New Account Inquiry page");
		}

		catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("verifying Account Inquiry page", "user redirected to Account Inquiry page",
					"User failed to redirected to Account Inquiry page", Common.getscreenShotPathforReport("Account Inquiry"));
			Assert.fail();
		}
		
         //Common.clickElement("xpath", "//a[contains(text(),'New Account Inquiry')]");
	//String title=Common.getText("xpath", "//span[contains(text(),'New Account Inquiry')]");
	 // Common.assertionCheckwithReport(title.equals("New Account Inquiry"), "verifying Account Inquiry page", "user will fill the all the Account Inquiry", "user fill the Account Inquiry click send button", "faield to Validation Account Inquiry page","Common.getscreenShotPathforReport("Account Inquiry")");

      try{
         Sync.waitElementPresent("id", "companyname");
            Common.textBoxInput("id", "companyname",data.get(dataSet).get("Company"));
            
            Sync.waitElementPresent("xpath", "//select[@id='channel']");
            Common.dropdown("xpath", "//select[@id='channel']", Common.SelectBy.TEXT, data.get(dataSet).get("Channel"));
            Thread.sleep(5000);
            
            //Sync.waitElementPresent("xpath", "//select[@id='custentity_businesstype']");
          
            Sync.waitElementPresent("xpath", "//select[@id='custentity_businesstype']");   
      Common.dropdown("xpath", "//select[@id='custentity_businesstype']", Common.SelectBy.TEXT, data.get(dataSet).get("business"));
      
      Sync.waitElementPresent("xpath", "//input[@id='url']");
      Common.textBoxInput("xpath", "//input[@id='url']",data.get(dataSet).get("webAddress"));
            
      Sync.waitElementPresent("xpath", "//select[@id='is_asi_ppai_industry_member']");
            Common.dropdown("xpath", "//select[@id='is_asi_ppai_industry_member']", Common.SelectBy.TEXT, data.get(dataSet).get("member"));
            
           //Common.textBoxInput("xpath", "//select[@id='is_placing_custom_order']",data.get(dataSet).get("Order"));
            Sync.waitElementPresent("xpath", "//select[@id='is_placing_custom_order']");
            Common.dropdown("xpath", "//select[@id='is_placing_custom_order']", Common.SelectBy.TEXT,data.get(dataSet).get("Order"));
            
            Sync.waitElementPresent("id", "sell_your_products");
            Common.textBoxInput("id", "sell_your_products",data.get(dataSet).get("yourwebsite"));
            
            Sync.waitElementPresent("xpath", "//input[@id='sales_come_through']");
            Common.textBoxInput("xpath", "//input[@id='sales_come_through']",data.get(dataSet).get("stores"));
            
           Sync.waitElementPresent("xpath", "//input[@id='custentity_numberofstores']");
           Common.textBoxInput("xpath", "//input[@id='custentity_numberofstores']", data.get(dataSet).get("numberofstores"));
           
            Sync.waitElementPresent("xpath", "//select[@id='custentity_storesqftsize']");
           // Common.textBoxInput("xpath", "//select[@id='custentity_storesqftsize']", data.get(dataSet).get("Storesize"));
            Common.dropdown("xpath", "//select[@id='custentity_storesqftsize']", Common.SelectBy.TEXT, data.get(dataSet).get("Storesize"));
            
            Sync.waitElementPresent("xpath", "//div[@class='dealer-central-field field custentity_annualsales required']//input");
            Common.textBoxInput("xpath", "//div[@class='dealer-central-field field custentity_annualsales required']//input",data.get(dataSet).get("Revenu"));
            
            Sync.waitElementPresent("xpath", "//div[@class='dealer-central-field field number_of_units required']//input");
            Common.textBoxInput("xpath", "//div[@class='dealer-central-field field number_of_units required']//input",data.get(dataSet).get("estimated number"));
            
            Sync.waitElementPresent("xpath", "//div[@class='dealer-central-field field custentity_yearsinbusiness required']//input");
            Common.textBoxInput("xpath", "//div[@class='dealer-central-field field custentity_yearsinbusiness required']//input",data.get(dataSet).get("YearBusiness"));
            
            Sync.waitElementPresent("xpath", "//div[@class='dealer-central-field field address1 required']//input");
            Common.textBoxInput("xpath", "//div[@class='dealer-central-field field address1 required']//input",data.get(dataSet).get("StoreAddress"));
            
            Sync.waitElementPresent("xpath", "//div[@class='dealer-central-field field address2 required']//input");
            Common.textBoxInput("xpath", "//div[@class='dealer-central-field field address2 required']//input",data.get(dataSet).get("unit"));
            
            Sync.waitElementPresent("xpath", "//div[@class='dealer-central-field field city required']//input");
            Common.textBoxInput("xpath", "//div[@class='dealer-central-field field city required']//input",data.get(dataSet).get("City"));
            
            Sync.waitElementPresent("xpath", "//div[@class='dealer-central-field field state required']//select");
            Common.dropdown("xpath", "//div[@class='dealer-central-field field state required']//select", Common.SelectBy.TEXT, data.get(dataSet).get("State"));
            
            Sync.waitElementPresent("xpath", "//input[@id='zipcode']");
            Common.textBoxInput("xpath", "//input[@id='zipcode']",data.get(dataSet).get("Zipcode"));
            
            //Sync.waitElementPresent("xpath", "//select[@id='country']");
            //Common.dropdown("xpath", "//select[@id='country']", Common.SelectBy.TEXT, data.get(dataSet).get("Country"));
            
            Sync.waitElementPresent("xpath", "//textarea[@id='custentity_businessdescription']");
            Common.textBoxInput("xpath", "//textarea[@id='custentity_businessdescription']",data.get(dataSet).get("business"));
            
            Sync.waitElementPresent("xpath", "//textarea[@id='custentity_reasonforinterest']");
            Common.textBoxInput("xpath", "//textarea[@id='custentity_reasonforinterest']",data.get(dataSet).get("interest"));
            
            Sync.waitElementPresent("xpath", "//textarea[@id='custentity_otherbrandscarried']");
            Common.textBoxInput("xpath", "//textarea[@id='custentity_otherbrandscarried']",data.get(dataSet).get("carried"));
            
            Sync.waitElementPresent("xpath", "//textarea[@id='custentity_marketdisplayplan']");
            Common.textBoxInput("xpath", "//textarea[@id='custentity_marketdisplayplan']",data.get(dataSet).get("Products"));
            
            Sync.waitElementPresent("xpath", "//input[@id='custentity_inquiryleadsource']");
            Common.textBoxInput("xpath", "//input[@id='custentity_inquiryleadsource']",data.get(dataSet).get("aboutUs"));
            
            Sync.waitElementPresent("xpath", "//input[@id='firstname']");
            Common.textBoxInput("xpath", "//input[@id='firstname']",data.get(dataSet).get("FirstName"));
            
            Sync.waitElementPresent("xpath", "//input[@id='lastname']");
            Common.textBoxInput("xpath", "//input[@id='lastname']",data.get(dataSet).get("LastName"));
            
            Sync.waitElementPresent("xpath", "//input[@id='title']");
            Common.textBoxInput("xpath", "//input[@id='title']",data.get(dataSet).get("Title"));
            
            Sync.waitElementPresent("xpath", "//input[@id='phone']");
            Common.textBoxInput("xpath", "//input[@id='phone']",data.get(dataSet).get("phone"));
            
            Sync.waitElementPresent("xpath", "//input[@id='useremail']");
           Common.textBoxInput("xpath", "//input[@id='useremail']",data.get(dataSet).get("Email"));
	
           
           //Common.clickElement("xpath", "//span[contains(text(),'SEND')]");
 

      }
          
          catch(Exception |Error e) {
              e.printStackTrace();
              ExtenantReportUtils.addFailedLog("verifying Account Inquiry form filling", "user fill the all the Account Inquiry form", "User failed to add new Account Inquiry",Common.getscreenShotPathforReport("Account Inquiry Form"));
              Assert.fail();
              
          }  

      try {

    	  Sync.waitElementPresent("xpath", "//input[@id='custentity_inquirysubmittedby']");
          Common.textBoxInput("xpath", "//input[@id='custentity_inquirysubmittedby']",data.get(dataSet).get("inquirysubmittedby"));
			expectedResult = "User entered the form Data";
			int sizeform = Common.findElements("xpath", "//a[contains(text(),'Dealer Central')]").size();
			Common.assertionCheckwithReport(sizeform > 0, "verifying the Data Filled in form", expectedResult,
					"User Successfully entered the Inquiryform Data", "User is unable to entered the form Data");
		}

		catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("verifying the Data Filled in form", "user entered the form Data",
					"User failed to enter the Inquiry form Data", Common.getscreenShotPathforReport("Dealer CentralPage"));
			Assert.fail();
		}
      
      
    //Common.clickElement("xpath", "//span[contains(text(),'SEND')]");
      
      try {
    	 
    	  Sync.waitElementPresent("xpath", "//span[contains(text(),'SEND')]");
          Common.clickElement("xpath", "//span[contains(text(),'SEND')]");
          Thread.sleep(3000);
      	//String title=Common.getText("xpath", "");
      	 
          int message=Common.findElements("xpath", "//h4[contains(text(),'Thank you for applying')]").size();

      	expectedResult = "User gets redirected to confirmation page, Account Enquiry - Thank you for applying";
    	Common.assertionCheckwithReport(message>0, "Verifying submitted Inquiry Confirmation page","New Account Enquiry submitted successfully,and redirected to confirmation page", expectedResult,
    			"submited the New Account Enquiry but confirmation message missing");
      }
     
	catch (Exception | Error e) {
		ExtenantReportUtils.addFailedLog("verifying Confirmation page", "user redirected to Account Inquiry Confirmation page",
				"User failed to redirected to Account Inquiry confirmation page", Common.getscreenShotPathforReport("Account Inquiry confirmation"));
		Assert.fail();
	}
	}	  
  	  
  	  
  	  
	  
public void Partial_order_verification() throws Exception {
	try {
	Sync.waitElementPresent("xpath", "//div[contains(text(),'Order has been partially placed.')]");

int message=Common.findElements("xpath", "//div[contains(text(),'Order has been partially placed.')]").size();
String expectedResult = "User gets redirected to confirmation page, Order has been partially placed.";

	Common.assertionCheckwithReport(message>0,"Verifying My order page","The Order is placed successfully", expectedResult, " submited the order but confirmation message missing");
}catch(Exception |Error e) {
		ExtenantReportUtils.addFailedLog("verifying My order page","The Order is placed successfully", "submited the order but confirmation message missing", Common.getscreenShotPathforReport("failed order id"));			
		Assert.fail();	
		}
	}





public void CheckOutPaypalminicart(String dataSet) {
	String expectedResult = "it should land on the checkout intermediate page";
	String url=automation_properties.getInstance().getProperty(automation_properties.BASEURL);
	
	try {
		Thread.sleep(3000);
		Common.clickElement("xpath", "//a[@aria-label='minicart']");
		Thread.sleep(3000);
		Common.scrollIntoView("id", "top-cart-btn-checkout");
		ExtenantReportUtils.addPassLog("validating the product miniCart", expectedResult,
				"User click the minicart button", Common.getscreenShotPathforReport("clickminiCart"));
	} catch (Exception | Error e) {
		ExtenantReportUtils.addFailedLog("validating the product miniCart", expectedResult,
				"User unabel click the minicart button", Common.getscreenShotPathforReport("clickminiCart"));
		// ExtenantReportUtils.addFailedLog("User click check out button",
		// "User unabel click the checkout button",
		// Common.getscreenShotPathforReport("check out miniCart"));
		Assert.fail();
	}
	try {
		Common.switchFrames("xpath", "//iframe[contains(@class,'zoid-component-frame')]");
		Sync.scrollDownToView("xpath", "//div[@class='paypal-button-label-container']");
		Sync.waitElementClickable(30, By.xpath("//div[@class='paypal-button-label-container']"));
		Common.mouseOverClick("xpath", "//div[@class='paypal-button-label-container']");
		Thread.sleep(4000);
		Common.switchToDefault();
		Thread.sleep(5000);
		Common.switchWindows();
		int size = Common.findElements("id", "acceptAllButton").size();
		if (size > 0) {
			Common.clickElement("id", "acceptAllButton");
		}

		if(!url.contains("stg")& !url.contains("dev")){
			
			int sizeofelement=Common.findElements("id", "email").size();
			Common.assertionCheckwithReport(sizeofelement > 0, "verifying the paypal payment ", expectedResult,"open paypal site window", "faild to open paypal account");
		}
		else{
		Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
		int sizeofbutton = Common.findElements("xpath", "//button[@id='btnNext']").size();
		if (sizeofbutton > 0) {
			Common.clickElement("xpath", "//button[@id='btnNext']");
		}
		Common.textBoxInput("id", "password", data.get(dataSet).get("Password"));
		int sizeemail = Common.findElements("id", "email").size();
		Common.assertionCheckwithReport(sizeemail > 0, "verifying the paypal payment ", expectedResult,
				"open paypal site window", "failed to open paypal account");
		Common.clickElement("id", "btnLogin");
		Thread.sleep(5000);
		Common.actionsKeyPress(Keys.END);
		Thread.sleep(8000);
		}
	}
		catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("validating the paypal payment", expectedResult,
					"User unable  to open the window", Common.getscreenShotPathforReport("Paypalwindow"));
			// ExtenantReportUtils.addFailedLog("User click check out button",
			// "User unabel click the checkout button",
			// Common.getscreenShotPathforReport("check out miniCart"));
			Assert.fail();
		
		/////
		
		//Sync.waitElementPresent("xpath", "//div[@id='shippingAddress']/a");
		//Common.clickElement("xpath", "//div[@id='shippingAddress']/a");
		
		
		
		/////
		}	
}

public void CheckOutPaypaladdress(String dataSet) throws Exception {
	String expectedResult = "User Should successfully land on the Addresses page";
	String url=automation_properties.getInstance().getProperty(automation_properties.BASEURL);

try {
	 
	  Sync.waitElementPresent("xpath", "//div[@id='shippingAddress']/a");
      Common.clickElement("xpath", "//div[@id='shippingAddress']/a");
      Thread.sleep(3000);
  	//String title=Common.getText("xpath", "");
  	 
      int message=Common.findElements("xpath", "//h1[contains(text(),'Addresses')]").size();

  	expectedResult = "User Should successfully land on the Addresses page";
	Common.assertionCheckwithReport(message>0, "Verifying the Addresses ","User Successfully selects the Address", expectedResult,
			"User unable to select the Address");
  }
 
catch (Exception | Error e) {
	ExtenantReportUtils.addFailedLog("Verifying the Saved Address", "User Successfully selects the Address",
			"User failed to select the address", Common.getscreenShotPathforReport("SaveAddress"));
	Assert.fail();
}



//Sync.waitElementPresent("xpath", "//span[@class='leftText']");
//Common.clickElement("xpath", "//span[@class='leftText']");


try {
	 
	  Sync.waitElementPresent("xpath", "//span[@class='leftText']");
      Common.clickElement("xpath", "//span[@class='leftText']");
      Thread.sleep(3000);
  	//String title=Common.getText("xpath", "");
  	 
      int message=Common.findElements("xpath", "//h1[contains(text(),'Add shipping address')]").size();

  	expectedResult = "User Should successfully land on the Address filling page";
	Common.assertionCheckwithReport(message>0, "Verifying the filling Address  ","User Successfully Enters the Address", expectedResult,
			"User unable to Enter the Address");
  }
 
catch (Exception | Error e) {
	ExtenantReportUtils.addFailedLog("Verifying the Filling Address", "User Successfully Enters the Address",
			"User failed to Enter the address", Common.getscreenShotPathforReport("EntertheAddress"));
	Assert.fail();
}

Sync.waitElementPresent("xpath", "//input[@id='shippingFirstName']");
Common.textBoxInput("xpath", "//input[@id='shippingFirstName']",data.get(dataSet).get("FirstName"));	

Sync.waitElementPresent("xpath", "//input[@id='shippingLastName']");
Common.textBoxInput("xpath", "//input[@id='shippingLastName']",data.get(dataSet).get("LastName"));	

Sync.waitElementPresent("xpath", "//input[@id='shippingLine1']");
Common.textBoxInput("xpath", "//input[@id='shippingLine1']",data.get(dataSet).get("Street"));

Sync.waitElementPresent("xpath", "//input[@id='shippingCity']");
Common.textBoxInput("xpath", "//input[@id='shippingCity']",data.get(dataSet).get("City"));

Sync.waitElementPresent("xpath", "//select[@id='shippingState']");
Common.dropdown("xpath", "//select[@id='shippingState']", Common.SelectBy.TEXT, data.get(dataSet).get("State"));

Sync.waitElementPresent("xpath", "//input[@id='shippingPostalCode']");
Common.textBoxInput("xpath", "//input[@id='shippingPostalCode']",data.get(dataSet).get("postcode"));


/*
 * Common.clickElement("xpath", "//input[@value='Save']"); //String
 * title=Common.getText("xpath", "//input[@value='Save']"); //
 * Common.assertionCheckwithReport(title.equals("paypal"),
 * "verifying Address validation", "user will fill the all the Address",
 * "user filled the Address  and click save button",
 * "failed to Validate address page page"
 * ,"Common.getscreenShotPathforReport("SavedAddress")");
 * 
 * String expectedResultt = "User Should successfully save the Address";
 * Thread.sleep(3000); int sizeaddr = Common.findElements("xpath",
 * "//div[@id='paypalLogo']").size(); Common.assertionCheckwithReport(sizeaddr >
 * 0, "Verifying the Saved Address", "User Successfully Saves the Address",
 * expectedResultt, "User unable to Save the Address");
 */

try {
	 
	  Sync.waitElementPresent("xpath", "//input[@value='Save']");
      Common.clickElement("xpath", "//input[@value='Save']");
      Thread.sleep(3000);
  	//String title=Common.getText("xpath", "");
  	 
      int message=Common.findElements("xpath", "//div[@id='paypalLogo']").size();

  	expectedResult = "User Should successfully save the Address";
	Common.assertionCheckwithReport(message>0, "Verifying the Saved Address","User Successfully Saves the Address", expectedResult,
			"User unable to Save the Address");
  }
 
catch (Exception | Error e) {
	ExtenantReportUtils.addFailedLog("Verifying the Saved Address", "User Successfully Saves the Address",
			"User failed to save the address", Common.getscreenShotPathforReport("SaveAddress"));
	Assert.fail();
}

//paypal continue with error message at review page
		try {
	   	 
	  	  Sync.waitElementPresent("id", "confirmButtonTop");
	        Common.clickElement("id", "confirmButtonTop");
	        Sync.waitPageLoad();
	       Thread.sleep(5000);
	       Common.switchToFirstTab();
	       Sync.waitElementPresent("xpath","//span[contains(text(),'Review Order')]"); 
	        Sync.waitElementPresent("xpath", "//div[contains(text(),'Your shipping address is not verified. Your may edit it on PayPal.')]");
	    	 
	       int message=Common.findElements("xpath", "//div[contains(text(),'Your shipping address is not verified. Your may edit it on PayPal.')]").size();

	    	 expectedResult = "User gets redirected to Review page Display Error Message";
	  	Common.assertionCheckwithReport(message>0, "Verifying Paypal Address at Review page","Entered Paypal payment Address and redirected to Review page", expectedResult,	"User failed to validate paypal payment Address - Your shipping address is not verified. Your may edit it on PayPal.");
	    	
		
		}
		
	   
		catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("Verifying Paypal Address at Review page", "Entered Paypal payment Address and redirected to Review page",
					"User failed to validate paypal payment Address - Your shipping address is not verified. Your may edit it on PayPal.", Common.getscreenShotPathforReport("PaypalShipping"));
			Assert.fail();
		}


}


public void addDeliveryAddressValidate(String dataSet) throws Exception {
	try {
		Sync.waitElementVisible("id", "customer-email-address");
		Common.textBoxInput("id", "customer-email-address", data.get(dataSet).get("Email"));
		String emailid=Common.findElement("id" ,"customer-email-address").getAttribute("value");
		System.out.println("*****"+emailid+"*******");
	} catch (NoSuchElementException e) {
		checkOut();
		Common.textBoxInput("id", "customer-email-address", data.get(dataSet).get("Email"));

	}
	String expectedResult = "email field will have email address";
	try {
		Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",data.get(dataSet).get("FirstName"));
		String ShippingFirstName=Common.findElement("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']").getAttribute("value");
		System.out.println("*****"+ShippingFirstName+"*******");
		
		int size = Common.findElements("id", "customer-email-address").size();
        Common.assertionCheckwithReport(size > 0, "validating the email address field", expectedResult,"Filled Email address", "unable to fill the email address");
        Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']",data.get(dataSet).get("LastName"));
		
        String ShippingLastName=Common.findElement("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']").getAttribute("value");
        System.out.println("*****"+ShippingLastName+"*******");
        Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']",data.get(dataSet).get("Street"));
        String ShippingAddress1=Common.findElement("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']").getAttribute("value");
        System.out.println("*****"+ShippingAddress1+"*******");
        
        String Text=Common.getText("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']");
		
		

		try {
			Common.clickElement("xpath", "//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
		} catch (Exception e) {
			Common.actionsKeyPress(Keys.BACK_SPACE);
			Thread.sleep(1000);
			Common.actionsKeyPress(Keys.SPACE);
			Common.clickElement("xpath", "//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
		}
		if (data.get(dataSet).get("StreetLine2") != null) {
			Common.textBoxInput("name", "street[1]", data.get(dataSet).get("Street"));
			
			String street1=Common.findElement("name", "street[1]").getAttribute("value");
			System.out.println("*****"+street1+"*******");
			
			String text=Common.getText("name","street[1]");
			System.out.println(text);
			
		}
		if (data.get(dataSet).get("StreetLine3") != null) {
			Common.textBoxInput("name", "street[2]", data.get(dataSet).get("Street"));
			
			String street2=Common.findElement("name", "street[2]").getAttribute("value");
			System.out.println("*****"+street2+"*******");
		}
		Sync.waitPageLoad();
		Thread.sleep(5000);
		Common.findElement("xpath", "//form[@id='co-shipping-form']//input[@name='city']").clear();
		Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='city']",data.get(dataSet).get("City"));
		//System.out.println(data.get(dataSet).get("City"));
		
		String ShippingCity=Common.findElement("xpath", "//form[@id='co-shipping-form']//input[@name='city']").getAttribute("value");
		System.out.println("*****"+ShippingCity+"*******");
		
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Thread.sleep(3000);
		try {
			Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			
			String Shippingvalue=Common.findElement("name", "region_id").getAttribute("value");
			String Shippingstate=Common.findElement("xpath","//select[@name='region_id']//option[@value='"+Shippingvalue+"']").getText();
			System.out.println("*****"+Shippingvalue+"*******");
		
		} catch (ElementClickInterceptedException e) {
			Thread.sleep(3000);
			Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
		}
		Thread.sleep(2000);
		Common.textBoxInputClear("name", "postcode");
		Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
		String ShippingZip=Common.findElement("name", "postcode").getAttribute("value");
		System.out.println("*****"+ShippingZip+"*******");
		Thread.sleep(5000);
		
		Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
		String ShippingPhone=Common.findElement("name", "telephone").getAttribute("value");
		System.out.println("*****"+ShippingPhone+"*******");

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
	int size=Common.findElements("xpath", "//input[@id='label_method_bestway']").size();
	if(size>0){
		Common.clickElement("xpath", "//input[@id='label_method_bestway']");
	}

	expectedResult = "shipping address is filled in to the fields";
	Common.clickElement("xpath", "//button[@data-ac-test='form-shipping-address_action_submit']");

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

	// Common.assertionCheckwithReport(errorsize<=0,"enter the shipping
	// address in to the fields without skipping any mandatory fields",
	// expectedResult, "Filled the shipping address", "failed to add a
	// address");
	// Common.assertionCheckwithReport(errorsize<=0, "Filled the shipping
	// address", expectedResult, "Missing the shipping address");
	Thread.sleep(3000);
}



public void payPal_Payment_CheckoutPage(String dataSet) throws Exception {

	String expectedResult = "It should open paypal site window.";
	try {
		Thread.sleep(3000);
		Sync.waitElementPresent("xpath", "//input[@id='paypal_express']");
		Thread.sleep(2000);
		Common.clickElement("xpath", "//input[@id='paypal_express']");
		Thread.sleep(5000);
		//Common.actionsKeyPress(Keys.PAGE_DOWN);
		Common.switchFrames("xpath", "//iframe[contains(@class,'zoid-component-frame')]");
		//Thread.sleep(5000);
		//Common.refreshpage();
		Thread.sleep(8000);
		Sync.waitElementClickable("xpath", "//div[@class='paypal-button-label-container']");
		Common.clickElement("xpath", "//div[@class='paypal-button-label-container']");
		Common.switchToDefault();
		Thread.sleep(5000);
		Common.switchWindows();
		int size = Common.findElements("id", "acceptAllButton").size();
		if (size > 0) {

			Common.clickElement("id", "acceptAllButton");

			
		}
		
		Common.switchToFirstTab();
		Common.assertionCheckwithReport(size > 0, "verifying the paypal payment ", expectedResult,"open paypal site window", "User failed to Validate address for paypal checkout");
		
			
			
	
	}
		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the paypal payment ", expectedResult,
					"User failed to validate address for paypal payment", Common.getscreenShotPathforReport(expectedResult));
			Assert.fail();
		}
		String url=automation_properties.getInstance().getProperty(automation_properties.BASEURL);
		
		if(!url.contains("stg") &!url.contains("dev")){
			
			int sizeofelement=Common.findElements("id", "email").size();
			Common.assertionCheckwithReport(sizeofelement > 0, "verifying the paypal payment ", expectedResult,"open paypal site window", "faild to open paypal account");
		}
		else
		{
		Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
		Common.textBoxInput("id", "password", data.get(dataSet).get("Password"));
		int sizeemail = Common.findElements("id", "email").size();

		Common.assertionCheckwithReport(sizeemail > 0, "verifying the paypal payment ", expectedResult,"open paypal site window", "faild to open paypal account");
		
		try{
		Common.clickElement("id", "btnLogin");
		Thread.sleep(5000);
		Common.actionsKeyPress(Keys.END);
		Thread.sleep(5000);
		Common.clickElement("id", "payment-submit-btn");
		Thread.sleep(8000);
		Common.switchToFirstTab();
	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying the paypal payment ", expectedResult,
				"User failed to proceed with paypal payment", Common.getscreenShotPathforReport(expectedResult));
		Assert.fail();
	}

	String sucessMessage = Common.getText("xpath", "//h1[@class='checkout-success-title']").trim();
	Assert.assertEquals(sucessMessage, "Your order has been received", "Sucess message validations");
	expectedResult = "Verify order confirmation number which was dynamically generated";
	Common.assertionCheckwithReport(sucessMessage.equals("Your order has been received"),"Order Placed successfull", expectedResult, "faild to place order");

}
}


public void Verify_AVS_addDeliveryAddress1(String dataSet) throws Exception {
	   
    
    try {
        Sync.waitElementVisible("id", "customer-email-address");
        Common.textBoxInput("id", "customer-email-address", data.get(dataSet).get("Email"));
        Thread.sleep(5000);
        String emailid=Common.findElement("id" ,"customer-email-address").getAttribute("value");
        System.out.println("*****"+emailid+"*******");
       
        
    } catch (NoSuchElementException e) {
        checkOut();
        Common.textBoxInput("id", "customer-email-address", data.get(dataSet).get("Email"));



    }
    

    String expectedResult = "email field will have email address";
    try {
        Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",data.get(dataSet).get("FirstName"));
        Thread.sleep(2000);
        String ShippingFirstName=Common.findElement("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']").getAttribute("value");
        System.out.println("*****"+ShippingFirstName+"*******");
     
        int size = Common.findElements("id", "customer-email-address").size();
        Common.assertionCheckwithReport(size > 0, "validating the email address field", expectedResult,"Filled Email address", "unabel to fill the email address");
        Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']",data.get(dataSet).get("LastName"));
        Thread.sleep(2000);
        String ShippingLastName=Common.findElement("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']").getAttribute("value");
        System.out.println("*****"+ShippingLastName+"*******");
     
        
        Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']",data.get(dataSet).get("Street"));
        Thread.sleep(2000);
        String ShippingAddress1=Common.findElement("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']").getAttribute("value");
        System.out.println("*****"+ShippingAddress1+"*******");
     
        String Text=Common.getText("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']");
        Common.assertionCheckwithReport(ShippingAddress1.equals("2 dream valley"), "validating the suggested address ", "It should suggest address ","Suggested  address", "unabel to suggeste address");
        
       // Common.clickElement("xpath", "//ul[contains(@class,'checkout-shipping-address')]/li[2]");



        try {
            Common.clickElement("xpath", "//ul[contains(@class,'checkout-shipping-address')]/li[2]");
        } catch (Exception e) {
            Common.actionsKeyPress(Keys.BACK_SPACE);
            Thread.sleep(1000);
            Common.actionsKeyPress(Keys.SPACE);
            Common.clickElement("xpath", "//ul[contains(@class,'checkout-shipping-address')]/li[2]");
        }
        if (data.get(dataSet).get("StreetLine2") != null) {
            Common.textBoxInput("name", "street[1]", data.get(dataSet).get("Street"));
            Thread.sleep(2000);
            String street1=Common.findElement("name", "street[1]").getAttribute("value");
            System.out.println("*****"+street1+"*******");
            
            String text=Common.getText("name","street[1]");
            System.out.println(text);
            
        }
        if (data.get(dataSet).get("StreetLine3") != null) {
            Common.textBoxInput("name", "street[2]", data.get(dataSet).get("Street"));
            Thread.sleep(2000);
            String street2=Common.findElement("name", "street[2]").getAttribute("value");
            System.out.println("*****"+street2+"*******");
        }
        Sync.waitPageLoad();
        Thread.sleep(5000);
        Common.findElement("xpath", "//form[@id='co-shipping-form']//input[@name='city']").clear();
        Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='city']",data.get(dataSet).get("City"));
        Thread.sleep(2000);
        String ShippingCity=Common.findElement("xpath", "//form[@id='co-shipping-form']//input[@name='city']").getAttribute("value");
        System.out.println("*****"+ShippingCity+"*******");
     
        Common.assertionCheckwithReport(ShippingCity.equals("Sylva"), "validating the suggested address ", "It should suggest address ","Suggested  address", "unabel to suggeste address");
        
        Common.actionsKeyPress(Keys.PAGE_DOWN);
        Thread.sleep(3000);
        try {
            Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
            Thread.sleep(2000);
            String Shippingvalue=Common.findElement("name", "region_id").getAttribute("value");
            String Shippingstate=Common.findElement("xpath","(//select[@name='region_id']//option[@value='"+Shippingvalue+"'])[1]").getText();
             System.out.println("*****"+Shippingstate+"*******");
          
             Common.assertionCheckwithReport(Shippingstate.equals("North Carolina"), "validating the suggested address ", "It should suggest address ","Suggested  address", "unabel to suggeste address");
             
        } catch (ElementClickInterceptedException e) {
            Thread.sleep(3000);
            Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
        }
        
        Thread.sleep(2000);
        Common.textBoxInputClear("name", "postcode");
        Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
        Thread.sleep(2000);
        String ShippingZip=Common.findElement("name", "postcode").getAttribute("value");
        System.out.println("*****"+ShippingZip+"*******");
   
        Common.assertionCheckwithReport(ShippingZip.equals("28779"), "validating the suggested address ", "It should suggest address ","Suggested  address", "unabel to suggeste address");
        
        Thread.sleep(5000);
        
        Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
        Thread.sleep(2000);
        String ShippingPhone=Common.findElement("name", "telephone").getAttribute("value");
        System.out.println("*****"+ShippingPhone+"*******");
    
        
        Common.clickElement("xpath", "//input[@id='label_method_bestway']");



        Common.clickElement("xpath", "//button[@data-ac-test='form-shipping-address_action_submit']");
			
		} catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validating adding  address", expectedResult,
					"User unabel add shipping address",
					Common.getscreenShotPathforReport("shipping address faield"));
		
			Assert.fail();

		}
	}


  	
  	 public void get(String dataSet){
  		 
  		 System.out.println(data.get(dataSet).get("Email"));
  		 
  	 }
  	 
  	 
  	 
  	 
  	 
  	 
  	 
  	public void writeResultstoXLSx(String Website,String OrderId,String subtotlaValue,String shippingammountvalue,String state,String Zipcode,String Taxammountvalue,String ActualTotalammountvalue, String ExpectedTotalammountvalue,String giventaxvalue,String calucaltedvalue)
	{
		//String fileOut="";
	try{
		
		File file=new File(System.getProperty("user.dir")+"/src/test/resources/HydroflaskTaxDetails_Guest.xlsx");
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
		cell.setCellValue("HOUSEWARE");
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
		if(Website.contains("hydroflask"))
		{

		Site="hydroflask";

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
		System.out.println(subtotlaValue);
		System.out.println(shippingammountvalue);
		System.out.println(Taxammountvalue);
		System.out.println(ActualTotalammountvalue);
		System.out.println(ExpectedTotalammountvalue);
		System.out.println(giventaxvalue);
		System.out.println(calucaltedvalue);

		FileOutputStream fileOut = new FileOutputStream(file);

		workbook.write(fileOut);

		fileOut.flush();
		fileOut.close();
		} catch (Exception e) {
		e.printStackTrace();
		}



		}
  	 
  	
  	public String URL() throws InterruptedException {
		String Website="";
		Common.clickElement("xpath", "(//a[@class='logo'])");
		Sync.waitPageLoad();
		Thread.sleep(4000);
		Website=Common.getCurrentURL();

		return Website;

		}



  	 
	public HashMap<String,String> taxValidation(String taxpercent,String state) {
		// TODO Auto-generated method stub
		HashMap<String,String> data=new HashMap<String,String>();
		try{			    
			Thread.sleep(3000);
 
	     Float giventaxvalue=Float.valueOf(taxpercent);
	     String giventaxvalue1=Float.toString(giventaxvalue);
	     data.put("giventaxvalue",giventaxvalue1);
	     

	     String subtotla=Common.getText("xpath", "//tr[@class='checkout-sidebar-totals-row totals']/td/span").replace("$", "");
	     // subtotla.replace("", newChar)
	    Float subtotlaValue=Float.valueOf(subtotla);
	    data.put("subtotlaValue",subtotla);
	    System.out.println(subtotla);
	   
	  String shippingammount=Common.getText("xpath", "//span[@data-th='Shipping']").replace("$", "");
	    Float shippingammountvalue=Float.valueOf(shippingammount);
		data.put("shippingammountvalue",shippingammount);
		 System.out.println(shippingammount);
		   
		
	     String TaxAmmount=Common.getText("xpath", "//td[@data-th='Tax*']//span").replace("$", "");
	    Float Taxammountvalue=Float.valueOf(TaxAmmount);
		data.put("Taxammountvalue",TaxAmmount);
		 System.out.println(TaxAmmount);
		   
		
		String ActualTotalAmmount=Common.getText("xpath", "//tr[@class='grand totals incl']//span").replace("$", "");
		Float ActualTotalammountvalue=Float.valueOf(ActualTotalAmmount);
		data.put("ActualTotalammountvalue",ActualTotalAmmount);
		 System.out.println(ActualTotalAmmount);
		   
		// Float Total=(subtotlaValue+shippingammountvalue);

		Float ExpectedTotalAmmount = subtotlaValue+shippingammountvalue+Taxammountvalue;
		//String ExpectedTotalAmount=String.valueOf(ExpectedTotalAmmount);
		String ExpectedTotalAmmount2 = new BigDecimal(ExpectedTotalAmmount).setScale(2, BigDecimal.ROUND_HALF_UP).toString();

        String ExpectedTotalAmount=String.valueOf(ExpectedTotalAmmount2);
        data.put("ExpectedTotalAmmountvalue",ExpectedTotalAmount);
		//data.put("ExpectedTotalAmmountvalue",ExpectedTotalAmount);

		if((state.equals("Illinois"))||(state.equals("Florida"))) {
		Float calucaltedvalue= ((subtotlaValue)*giventaxvalue)/100;
		//String userpaneltaxvalue = new BigDecimal(calucaltedvalue).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
		NumberFormat nf= NumberFormat.getInstance();
	    nf.setMaximumFractionDigits(2);
	    String userpaneltaxvalue=nf.format(calucaltedvalue);
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
			e.printStackTrace();
		report.addFailedLog("verifying tax calculation", "getting price values from shipping page ", "Faield to get price value from shipping page", Common.getscreenShotPathforReport("TaxRates"));



		
		Assert.fail();

		}
		return data;
		}	
  	
  	
  	 
  	 

  	public void regaddDeliveryAddress(String dataSet,String Street,String City,String postcode,String Region) {
		// TODO Auto-generated method stub
		
		String expectedResult = "shipping address is entering in the fields";
        int size = Common.findElements(By.xpath("//span[contains(text(),'Add New Address')]")).size();
		if (size > 0) {
        	try {
				Common.clickElement("xpath", "//span[contains(text(),'Add New Address')]");
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",data.get(dataSet).get("FirstName"));
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']",	data.get(dataSet).get("LastName"));
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']",data.get(dataSet).get("Street"));
				Thread.sleep(2000);
				Common.actionsKeyPress(Keys.SPACE);
				Thread.sleep(3000);
			/*	try {
					Common.clickElement("xpath",
							"//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
				} catch (Exception e) {
					Common.actionsKeyPress(Keys.BACK_SPACE);
					Thread.sleep(1000);
					Common.actionsKeyPress(Keys.SPACE);
					Common.clickElement("xpath",
							"//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
				}
				if (data.get(dataSet).get("StreetLine2") != null) {
					Common.textBoxInput("name", "street[1]", data.get(dataSet).get("Street"));
				}
				if (data.get(dataSet).get("StreetLine3") != null) {
					Common.textBoxInput("name", "street[2]", data.get(dataSet).get("Street"));
				}*/
				Common.actionsKeyPress(Keys.PAGE_DOWN);
				Thread.sleep(3000);
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='city']",
						data.get(dataSet).get("City"));
				// Common.mouseOverClick("name", "region_id");
				try {
					Common.dropdown("xpath", "//form[@id='co-shipping-form']//select[@name='region_id']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
				} catch (ElementClickInterceptedException e) {
					// TODO: handle exception
					Thread.sleep(3000);
					Common.dropdown("xpath", "//form[@id='co-shipping-form']//select[@name='region_id']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
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

			Common.clickElement("xpath", "//label[@for='shipping-save-in-address-book']");
                Common.clickElement("xpath", "//div[@id='opc-new-shipping-address']//following::button[1]");

				int sizeerrormessage = Common.findElements("xpath", "//span[contains(text(),'This is a required field')]").size();

				Common.assertionCheckwithReport(sizeerrormessage <= 0, "verifying shipping addres filling ",
						"user will fill the all the shipping", "user fill the shiping address click save button",
						"faield to add new shipping address");
				
				Common.clickElement("xpath", "//input[@id='label_method_bestway']");
				Common.clickElement("xpath", "//button[@data-ac-test='form-shipping-address_action_submit']");
				
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
				Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",
						data.get(dataSet).get("FirstName"));
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
					Common.clickElement("xpath",
							"//*[@id='co-shipping-form']/div/fieldset/div/div[1]/div/div/ul/li[1]/a");
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
				Sync.waitElementClickable("xpath", "//span[contains(text(),'Continue To Payment')]");
				Common.clickElement("xpath", "//span[contains(text(),'Continue To Payment')]");
                int errorsize=Common.findElements("xpath", "//div[@class='field-error']").size();
				Common.assertionCheckwithReport(errorsize>0, "verifying shipping addres filling ", expectedResult, "user enter the shipping address", "mandatory data");			
				//ExtenantReportUtils.addPassLog("verifying shipping addres filling ", expectedResult,"user enter the shipping address ",
				//Common.getscreenShotPathforReport("fill the shipping address first time"));

				//Common.findElements("xpath", "").size();
				expectedResult = "shipping address is filled in to the fields";
				Common.clickElement("xpath", "//button[@data-ac-test='form-shipping-address_action_submit']");
				Thread.sleep(3000);

			} catch (Exception | Error e) {
				e.printStackTrace();

				ExtenantReportUtils.addFailedLog("validating adding  address", expectedResult,
						"User unabel add shipping address",
						Common.getscreenShotPathforReport("shipping address faield"));
				
				Assert.fail();

			}
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
      	
  	public void regprepareTaxData(String fileName) {
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
  	
  	
  	
  	public void regwriteResultstoXLSx(String Website,String OrderId,String subtotlaValue,String shippingammountvalue,String state,String Zipcode,String Taxammountvalue,String ActualTotalammountvalue, String ExpectedTotalammountvalue,String giventaxvalue,String calucaltedvalue) {
		// TODO Auto-generated method stub
try{
			
			File file=new File(System.getProperty("user.dir")+"/src/test/resources/HydroflaskTaxDetails_Reg.xlsx");
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
			cell.setCellValue("Houseware");
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
			if(Website.contains("hydroflask"))
		     {
				
				Site="hydroflask";
				
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
			System.out.println(subtotlaValue);
			System.out.println(shippingammountvalue);
			System.out.println(Taxammountvalue);
			System.out.println(ActualTotalammountvalue);
			System.out.println(ExpectedTotalammountvalue);
			System.out.println(giventaxvalue);
			System.out.println(calucaltedvalue);
			
				FileOutputStream fileOut = new FileOutputStream(file);
			
			workbook.write(fileOut);
		
			fileOut.flush();
			fileOut.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

		}

  	 
  	
  	public void prepare_E2E_Data(String fileName) {
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
    			sheet = workbook.createSheet("Hydroflask_E2E_Details");
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
    			cell.setCellValue("Hydroflask-US_E2E_Stage_Upgraded_Orders_for_E2E_Testing");


    			row = sheet.createRow(1);
    			cell = row.createCell(0);
    			cell.setCellStyle(cs);
    			cell.setCellValue("S.No");
    			cell = row.createCell(1);
    			cell.setCellStyle(cs);
    			cell.setCellValue("Website");
    			cell = row.createCell(2);
    			cell.setCellStyle(cs);
    			cell.setCellValue("Tester");
    			cell = row.createCell(3);
    			cell.setCellStyle(cs);
    			cell.setCellValue("Test Scenario description");
    			cell = row.createCell(4);
    			cell.setCellStyle(cs);
    			cell.setCellValue(" WebOredrNumber");
    			cell = row.createCell(5);
    			cell.setCellStyle(cs);
    			cell.setCellValue("OrderConfirmation_Message");
    			cell = row.createCell(6);
    			cell.setCellStyle(cs);
    			cell.setCellValue("OrderStatusMagento");


    			cell = row.createCell(7);
    			cell.setCellStyle(cs);
    			cell.setCellValue("SubTotal");
    			cell = row.createCell(8);
    			cell.setCellStyle(cs);
    			cell.setCellValue("Shipping");
    			cell = row.createCell(9);
    			cell.setCellStyle(cs);
    			cell.setCellValue("State");
    			cell = row.createCell(10);
    			cell.setCellStyle(cs);
    			cell.setCellValue("Zipcode");
    			cell = row.createCell(11);
    			cell.setCellStyle(cs);
    			cell.setCellValue("Tax");
    			cell = row.createCell(12);
    			cell.setCellStyle(cs);
    			cell.setCellValue("EstimateTotal");
    			cell=row.createCell(13);
    			cell.setCellStyle(cs);
    			cell.setCellValue("Discount");
    			cell=row.createCell(14);
    			cell.setCellStyle(cs);
    			cell.setCellValue("Order Total");
    			cell=row.createCell(15);
    			cell.setCellStyle(cs);
    			cell.setCellValue("PaymentMethod");
    			cell=row.createCell(16);
    			cell.setCellStyle(cs);
    			
    			rowcount=2;
    			}

    			else
    			{
    			workbook = new XSSFWorkbook(new FileInputStream(file));
    			sheet=workbook.getSheet("E2E_Details");
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
  	 
  	
  	 
  	public void E2E_writeResultstoXLSx(String Website,String Description,String Paymentmethod,String OrderId,String subtotlaValue,String ShippingZip,String Shippingstate,String shippingammountvalue,String Taxammountvalue,String ActualTotalammountvalue,String ExpectedTotalAmmountvalue,String Discountammountvalue) throws FileNotFoundException 
  	{
  	//String fileOut="";
  	try{

  	File file=new File(System.getProperty("user.dir")+"/src/test/resources/Hydroflask_E2E_Details.xlsx");
  	XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
  	XSSFSheet sheet;
  	Row row;
  	Cell cell;
  	int rowcount;
  	sheet = workbook.getSheet("Hydroflask_E2E_Details");

  	if((workbook.getSheet("Hydroflask_E2E_Details"))==null)
  	{
  	sheet = workbook.createSheet("Hydroflask_E2E_Details");
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
  	cell.setCellValue("Hydroflask-US_E2E_Stage_Upgraded_Orders_for_E2E_Testing");

  	row = sheet.createRow(1);
  	cell = row.createCell(0);
  	cell.setCellStyle(cs);
  	cell.setCellValue("Web Order Number");
  	rowcount=2;

  	}

  	else
  	{

  	sheet=workbook.getSheet("Hydroflask_E2E_Details");
  	rowcount=sheet.getLastRowNum()+1;
  	}
  	row = sheet.createRow(rowcount);
	cell = row.createCell(0);
	cell.setCellType(CellType.NUMERIC);
	int SNo=rowcount-1;
	cell.setCellValue(SNo);
  	
  

  	
  	cell = row.createCell(1);
	cell.setCellType(CellType.STRING);



	String Site;
	if(Website.contains("hydroflask"))
	{

	Site="hydroflask";

	}
	else
	{
	Site="";
	}
	cell.setCellValue(Site);
  	
	
	cell = row.createCell(2);
  	cell.setCellType(CellType.STRING);
  	cell.setCellValue("LOTUSWAVE");
  	
  	cell = row.createCell(3);
  	cell.setCellType(CellType.STRING);
  	cell.setCellValue(Description);
  	
  	
  	cell = row.createCell(4);
  	cell.setCellType(CellType.NUMERIC);
  	cell.setCellValue(Paymentmethod);
  	
  	
  	cell = row.createCell(5);
  	cell.setCellType(CellType.STRING);
  	cell.setCellValue("SUCCESS");
  	
  	cell = row.createCell(6);
  	cell.setCellType(CellType.STRING);
  	cell.setCellValue("Processing");
  	
	cell = row.createCell(7);
  	cell.setCellType(CellType.NUMERIC);
  	cell.setCellValue(subtotlaValue);
  	
  	cell = row.createCell(8);
  	cell.setCellType(CellType.NUMERIC);
  	cell.setCellValue(shippingammountvalue);
  	
  	
  	cell = row.createCell(9);
  	cell.setCellType(CellType.STRING);
  	cell.setCellValue(Shippingstate);
  	
  	cell = row.createCell(10);
  	cell.setCellType(CellType.NUMERIC);
  	cell.setCellValue(ShippingZip);
  	
  	cell = row.createCell(11);
  	cell.setCellType(CellType.NUMERIC);
  	cell.setCellValue(Taxammountvalue);
  	
  	
  	cell = row.createCell(12);
  	cell.setCellType(CellType.NUMERIC);
  	cell.setCellValue(ExpectedTotalAmmountvalue);
  	
  	cell = row.createCell(13);
  	cell.setCellType(CellType.NUMERIC);
  	cell.setCellValue(Discountammountvalue);
  	
  	cell = row.createCell(14);
  	cell.setCellType(CellType.NUMERIC);
  	cell.setCellValue(ActualTotalammountvalue);
  	
  	
  	
 	cell = row.createCell(15);
  	cell.setCellType(CellType.STRING);
  	cell.setCellValue(OrderId);
  	

//  	System.out.println(OrderId);

  	FileOutputStream fileOut = new FileOutputStream(file);

  	workbook.write(fileOut);

  	fileOut.flush();
  	fileOut.close();
  	} catch (Exception e) {
  	e.printStackTrace();
  	}
  	}
  	 
  	
  	public HashMap<String,String> E2E_Validation() {
		// TODO Auto-generated method stub
		HashMap<String,String> data=new HashMap<String,String>();
		try{			    
			Thread.sleep(3000);
 

	     String subtotal=Common.getText("xpath", "//tr[@class='checkout-sidebar-totals-row totals']/td/span").replace("$", "");
	     // subtotla.replace("", newChar)
	     Float subtotlaValue=Float.valueOf(subtotal);
	     data.put("subtotlaValue",subtotal);
	    System.out.println("Subtotal"+ subtotal);
	   
	      String shippingammount=Common.getText("xpath", "//span[@data-th='Shipping']").replace("$", "");
      Float shippingammountvalue=Float.valueOf(shippingammount);
			data.put("shippingammountvalue",shippingammount);
		      System.out.println("shipping"+ shippingammount);
		      
		      
		      
		      
		
			    
		      String ActualTotalAmmount=Common.getText("xpath", "//tr[@class='grand totals incl']//span").replace("$", "");
			    Float ActualTotalammount=Float.valueOf(ActualTotalAmmount);
			    data.put("ActualTotalammountvalue",ActualTotalAmmount);
			    System.out.println("TotalAmmount"+ ActualTotalAmmount);
		 
			 
		
			 int SizesofDis= Common.findElements("xpath", "//tr[@class='totals discount']//span").size();
			    if(SizesofDis>0) {
			    String Discount=Common.getText("xpath", "//tr[@class='totals discount']//span").replace("-$", "");
			    Float Discountammountvalue=Float.valueOf(Discount);
				data.put("Discountammountvalue",Discount);
				
				
			    
			    }
			    else {
			    	
			    	String Discountammountvalue= "0.00";
					data.put("Discountammountvalue",Discountammountvalue);
			    	System.out.println("No Discount"+Discountammountvalue);
			    }
		
			    
			    
			    
		 int Sizes= Common.findElements("xpath", "//td[@data-th='Tax*']//span").size();
		 if(Sizes>0) {
		 String TaxAmmount=Common.getText("xpath", "//td[@data-th='Tax*']//span").replace("$", "");
		 Float Taxammountvalue=Float.valueOf(TaxAmmount);
		 data.put("Taxammountvalue",TaxAmmount);
		 System.out.println("Tax"+ TaxAmmount);
		 
		 int SizesofDis1= Common.findElements("xpath", "//tr[@class='totals discount']//span").size();
		    if(SizesofDis1>0) {
		 
		 String Discount1=Common.getText("xpath", "//tr[@class='totals discount']//span").replace("-$", "");
		    Float Discountammountvalue1=Float.valueOf(Discount1);
			data.put("Discountammountvalue1",Discount1);
			System.out.println("Discount"+Discountammountvalue1);
		    
//		 int Size=Common.findElements("xpath", "//tr[@class='totals discount']//span").size();
		 
		// if(SizesofDis1>0)
	//	 {
			 Float ExpectedTotalAmmount = subtotlaValue+shippingammountvalue+Taxammountvalue-(Discountammountvalue1);
			  
			    String userpaneltaxvalue=String.valueOf(ExpectedTotalAmmount);
			    String ExpectedTotalAmount = new BigDecimal(userpaneltaxvalue).setScale(2, BigDecimal.ROUND_UP).toString();
			    data.put("ExpectedTotalAmmountvalue",ExpectedTotalAmount);
			    System.out.println("EstimateTotal"+ ExpectedTotalAmount);
		 }
		 else {
			 
		 Float ExpectedTotalAmmount = subtotlaValue+shippingammountvalue+Taxammountvalue;
		    String userpaneltaxvalue=String.valueOf(ExpectedTotalAmmount);
		    String ExpectedTotalAmount = new BigDecimal(userpaneltaxvalue).setScale(2, BigDecimal.ROUND_UP).toString();
		    data.put("ExpectedTotalAmmountvalue",ExpectedTotalAmount);
		    System.out.println("EstimateTotal"+ ExpectedTotalAmount);
		    
		    
		 }
		
//		 else {
//			 String Taxammountvalue= "0.00";
//				data.put("Taxammountvalue",Taxammountvalue);
				
//			  Float ExpectedTotalAmmount = subtotlaValue+shippingammountvalue;
//			    String ExpectedTotalAmount=String.valueOf(ExpectedTotalAmmount);
//			    data.put("ExpectedTotalAmmountvalue",ExpectedTotalAmount);
		}
	
		}
		
		catch(Exception |Error e)
		{
			e.printStackTrace();
		report.addFailedLog("verifying tax calculation", "getting price values from shipping page ", "Faield to get price value from shipping page", Common.getscreenShotPathforReport("TaxRates"));



		
		Assert.fail();

		}
		return data;
		}	
  	
  	
  	
  	
  	public  HashMap<String,String> E2E_addDeliveryAddress(String dataSet) throws Exception {
  		
  		HashMap<String,String> Shippingaddress=new HashMap<String,String>();
		try {
			Sync.waitElementVisible("id", "customer-email");
			Common.textBoxInput("id", "customer-email", data.get(dataSet).get("Email"));
		} catch (NoSuchElementException e) {
			checkOut();
			Common.textBoxInput("id", "customer-email-address", data.get(dataSet).get("Email"));

		}
		String expectedResult = "email field will have email address";
		try {
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",data.get(dataSet).get("FirstName"));
            int size = Common.findElements("id", "customer-email").size();
            Common.assertionCheckwithReport(size > 0, "validating the email address field", expectedResult,"Filled Email address", "unabel to fill the email address");
            Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']",data.get(dataSet).get("LastName"));
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']",data.get(dataSet).get("Street"));
			String Text=Common.getText("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']");
			
			
			
			Common.dropdown("xpath", "//select[@name='region_id']", Common.SelectBy.TEXT,data.get(dataSet).get("Region"));
			Thread.sleep(5000);
			String Shippingvalue = Common.findElement("xpath", "//select[@name='region_id']").getAttribute("value");
			String Shippingstate = Common.findElement("xpath", "//select[@name='region_id']//option[@value='" + Shippingvalue + "']").getText();

			Shippingaddress.put("Shippingstate", Shippingstate);
			System.out.println(Shippingstate);

		
			Sync.waitPageLoad();
			Thread.sleep(5000);
			Common.findElement("xpath", "//form[@id='co-shipping-form']//input[@name='city']").clear();
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='city']",data.get(dataSet).get("City"));
			System.out.println(data.get(dataSet).get("City"));
			
			/*Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(3000);
			try {
				Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
				
			} catch (ElementClickInterceptedException e) {
				Thread.sleep(3000);
				Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
				String State= Common.getText("xpath", "region_id");*/
				
			
			Thread.sleep(2000);
			Common.textBoxInputClear("name", "postcode");
			Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
			
		String ShippingZip = Common.findElement("name", "postcode").getAttribute("value");
		System.out.println("*****" + ShippingZip + "*******");
		Shippingaddress.put("ShippingZip", ShippingZip);
		
			
	
			Thread.sleep(5000);
			
			Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));

		}

		catch (Exception | Error e) {
     e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating shipping address",
					"shipping address is filled in to the fields", "user faield to fill the shipping address",
					Common.getscreenShotPathforReport("shipingaddressfaield"));
			// ExtenantReportUtils.addFailedLog("User click check out button",
			// "User unabel click the checkout button",
			// Common.getscreenShotPathforReport("check out miniCart"));
			Assert.fail();

		}
		Thread.sleep(5000);
		int size=Common.findElements("xpath", "//input[@id='label_method_bestway']").size();
		if(size>0){
			Common.clickElement("xpath", "//input[@id='label_method_bestway']");
		}

		expectedResult = "shipping address is filled in to the fields";
		Common.clickElement("xpath", "//button[@data-ac-test='form-shipping-address_action_submit']");

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

		// Common.assertionCheckwithReport(errorsize<=0,"enter the shipping
		// address in to the fields without skipping any mandatory fields",
		// expectedResult, "Filled the shipping address", "failed to add a
		// address");
		// Common.assertionCheckwithReport(errorsize<=0, "Filled the shipping
		// address", expectedResult, "Missing the shipping address");
		Thread.sleep(3000);
		return Shippingaddress ;
	}
  	
  	//////////////////////////////////////////////////////////////////////////////////
  	
  	public void writeOrder_Number(String Website, String OrderIdNumber,String Description, String subtotlaValue, String shippingammountvalue, String Taxammountvalue,String ActualTotalammountvalue,String ExpectedTotalammountvalue,String Discountammountvalue) throws FileNotFoundException, IOException
  	{
  	//String fileOut="";
  	try{
  	File file=new File(System.getProperty("user.dir")+"/src/test/resources/E2E_Details.xlsx");
  	XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
  	XSSFSheet sheet;
  	Row row;
  	Cell cell;
  	int rowcount;
  	sheet = workbook.getSheet("OrderDetails");
  	if((workbook.getSheet("OrderDetails"))==null)
  	{
  	sheet = workbook.createSheet("OrderDetails");
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
  	rowcount=2;
  	}
  	else
  	{
  	sheet=workbook.getSheet("OrderDetails");
  	rowcount=sheet.getLastRowNum()+1;
  	}
  	row = sheet.createRow(rowcount);
  	cell = row.createCell(0);
  	cell.setCellType(CellType.NUMERIC);
  	int SNo=rowcount-1;
  	cell.setCellValue(SNo);
  	cell = row.createCell(1);
  	cell.setCellType(CellType.STRING);
  	String websitename;
  	if(Website.contains("hydroflask"))
  	{
  	websitename="hydroflask";
  	}
  	else
  	{
  	websitename="";
  	}
  	cell.setCellValue(websitename);
  	cell = row.createCell(2);
  	cell.setCellType(CellType.STRING);
  	cell.setCellValue("Lotuswave");
  	cell = row.createCell(3);
  	cell.setCellType(CellType.STRING);
  	cell.setCellValue(Description);
  	cell = row.createCell(4);
  	cell.setCellType(CellType.NUMERIC);
  	cell.setCellValue(OrderIdNumber);
  	cell = row.createCell(5);
  	cell.setCellType(CellType.STRING);
  	cell.setCellValue("YES");
  	cell = row.createCell(6);
  	cell.setCellType(CellType.STRING);
  	cell.setCellValue("Processing"); cell = row.createCell(7);
  	cell.setCellType(CellType.NUMERIC);
  	cell.setCellValue(subtotlaValue);
  	cell = row.createCell(8);
  	cell.setCellType(CellType.NUMERIC);
  	cell.setCellValue(shippingammountvalue);
  	cell = row.createCell(9);
  	cell.setCellType(CellType.STRING);
  	//cell.setCellValue(ShippingState);
  	cell = row.createCell(10);
  	cell.setCellType(CellType.NUMERIC);
  //	cell.setCellValue(ShippingZip);
  	cell = row.createCell(11);
  	cell.setCellType(CellType.STRING);
  	cell.setCellValue(Taxammountvalue);
  	cell = row.createCell(12);
  	cell.setCellType(CellType.NUMERIC);
  	cell.setCellValue(ExpectedTotalammountvalue);
  	cell = row.createCell(13);
  	cell.setCellType(CellType.NUMERIC);
  	cell.setCellValue(Discountammountvalue);
  	cell = row.createCell(14);
  	cell.setCellType(CellType.NUMERIC);
  	cell.setCellValue(ActualTotalammountvalue);
  	//cell = row.createCell(15);
  	//cell.setCellType(CellType.NUMERIC);
  	//cell.setCellValue(Card);
  	System.out.println(OrderIdNumber);
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
  	//System.out.println(giventaxvalue);
  	//String userpaneltax = Float.toString(userpaneltaxvalue);
  	//System.out.println("String is: "+userpaneltax);
  	//System.out.println(calucaltedvalue);
  	FileOutputStream fileOut = new FileOutputStream(file);
  	workbook.write(fileOut);
  	fileOut.flush();
  	fileOut.close();
  	//return writeResultstoXLSx(String OrderId,String subtotla,String shippingammount,String TaxAmmount,String Totalammount,String giventaxvalue1,String userpaneltaxvalue);
  	} catch (Exception e) {
  	e.printStackTrace();
  	}
  	// return fileOut;
  	// return writeResultstoXLSx(String OrderId,String subtotla,String shippingammount,String TaxAmmount,String Totalammount,String giventaxvalue1,String userpaneltaxvalue); }
  	}


  	
  	
  	

  	
  	
	public HydroHelper(String datafile) {
		
		
		excelData = new ExcelReader(datafile);
		data = excelData.getExcelValue();
		this.data = data;
		if (Utilities.TestListener.report == null) {
			report = new ExtenantReportUtils("Hydro");
			report.createTestcase("HydroTestCases");
		} else {
			this.report = Utilities.TestListener.report;
		}
	}
}

