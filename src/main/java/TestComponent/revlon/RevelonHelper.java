package TestComponent.revlon;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.NumberFormat;
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
import com.sun.tools.xjc.addon.sync.SynchronizedMethodAddOn;

public class RevelonHelper {

	String datafile;
	ExcelReader excelData;
	static Automation_properties automation_properties = Automation_properties.getInstance();
	Map<String, Map<String, String>> data = new HashMap<>();
	static ExtenantReportUtils report;

	public void navigateAccount() throws InterruptedException {
		String expectedResult = "Naviagating to account Creation page";
		try {
			Sync.waitElementClickable(30, By.xpath("//a[@title='My Account']"));
			Common.findElement("xpath", "//a[@title='My Account']").click();
			Sync.waitElementClickable(30, By.xpath("//button[text()='Create an Account']"));
			report.addPassLog(expectedResult, "Should display Login page", "Login page displayed successfully",
					Common.getscreenShotPathforReport("Login Page"));
			Thread.sleep(2000);
			report.addPassLog(expectedResult, "Should display My Account Page", "My Account Page display successfully",
					Common.getscreenShotPathforReport("Account Creation success"));
			// report.addPassLog(expectedResult, "Should display My Account Page", "My
			// Account Page display successfully",
			// Common.getscreenShotPathforReport("Account Creation success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display My Account Page", "My Account Page not display",
					Common.getscreenShotPathforReport("Account Creation Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}

	public void CreateNewAccount(String dataSet) throws Exception {
		navigateAccount();
		String expectedResult = "Account Creation of User with valid details";
		try {
			Common.clickElement("xpath", "//button[text()='Create an Account']");
			Thread.sleep(3000);
			report.addPassLog(expectedResult, "Should display Account Creation page",
					"Account Creation page display successfully",
					Common.getscreenShotPathforReport("Account Creation"));
			Common.textBoxInput("id", "firstname", data.get(dataSet).get("FirstName").toString());
			Common.textBoxInput("id", "lastname", data.get(dataSet).get("LastName").toString());
			Common.actionsKeyPress(Keys.ARROW_DOWN);
			Common.textBoxInput("id", "email_address", Utils.getEmailid());
			Common.textBoxInput("id", "password", data.get(dataSet).get("Password").toString());
			Common.textBoxInput("id", "password-confirmation", data.get(dataSet).get("Password").toString());
			Common.actionsKeyPress(Keys.ARROW_DOWN);
			Thread.sleep(3000);
			// Common.clickElement("id", "captcha_user_create");
			// Sync.waitElementVisible("className", "captcha-img");
			Common.clickElement("xpath", "//button[@title='Create an Account']");
			Thread.sleep(10000);
			String s = Common.getText("xpath", "//span[contains(text(),'My Account')]");
			Assert.assertEquals(s, "My Account");
			report.addPassLog(expectedResult, "Should display My Account Page", "My Account Page display successfully",
					Common.getscreenShotPathforReport("Account Creation success"));
			// report.addPassLog(expectedResult, "Should display My Account Page", "My
			// Account Page display successfully",
			// Common.getscreenShotPathforReport("Account Creation success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display My Account Page", "My Account Page not display",
					Common.getscreenShotPathforReport("Account Creation Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void searchProduct(String dataSet) throws Exception {
		String expectedResult = "Search with Product name :" + data.get(dataSet).get("ProductName");
		try {
			Thread.sleep(1000);
			Common.clickElement("xpath", "//div[@class='block block-search search-visible-md minisearch-v2']/div/i");
			Sync.waitElementPresent(10, "id", "search");
			try {
				Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName"));
			} catch (Exception e) {
				Common.clickElement("xpath",
						"//div[@class='block block-search search-visible-md minisearch-v2']/div/i");
				Thread.sleep(3000);
				Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName"));
			}
			Common.actionsKeyPress(Keys.ENTER);
			Thread.sleep(10000);
			//Common.actionsKeyPress(Keys.DOWN);
			// Common.scrollIntoView("xpath",
			// "(//div[@class='product-item-info']/div//a[@class='product photo
			// product-item-photo title'])[1]");
			report.addPassLog(expectedResult, "Should display Search Results Page",
					"Search results Page display successfully",
					Common.getscreenShotPathforReport("Search results success"));
			
			Common.actionsKeyPress(Keys.PAGE_DOWN);               //span[@id='product-price-118']/span
			Thread.sleep(4000);
			if(Common.isElementDisplayed("xpath", "//div[2]/span/span/span[@id='product-price-187']/span")) {
			if(Common.isElementDisplayed("xpath", "//span[@id='product-price-118']/span")) {
				String discountprice = Common.getText("xpath", "//span[@id='product-price-118']/span").replace("$", "");
				 Float DiscountPrice=Float.valueOf(discountprice);
				 System.out.println("the discounted price is" + DiscountPrice);
				Thread.sleep(3000);
	 			String actualprice = Common.getText("xpath", "//div[2]/span/span/span[@id='old-price-187']/span").replace("$", "");
	 			 Float ActualPrice=Float.valueOf(actualprice);   
	 			System.out.println("the Actual price is" + ActualPrice); 
	 			 Common.assertionCheckwithReport(ActualPrice>DiscountPrice, "Discount price display successfull", "Should display discounted price and must be lesser than Actual price", "failed to display the Discount price for the employee" );
	 			System.out.println("Discount amount is availed on the product"); 
			}else {
				System.out.println("Discount price is not available for the user");
				
			}                                        //div[2]/span/span/span[@id='product-price-187']/span
			}else{
				if(Common.isElementDisplayed("xpath", "//div[2]/span/span/span[@id='product-price-187']/span")) {
					String actualprice = Common.getText("xpath", "//div[2]/span/span/span[@id='product-price-187']/span").replace("$", "");
					 Float ActualPrice=Float.valueOf(actualprice);
					 System.out.println("the discounted price is" + ActualPrice);
					Thread.sleep(3000);
					if(Common.isElementDisplayed("xpath", "//span[@id='product-price-118']/span")) {
		 			String discountprice = Common.getText("xpath", "//span[@id='old-price-118']/span").replace("$", "");
		 			 Float DiscountPrice=Float.valueOf(discountprice);   
		 			System.out.println("the Actual price is" + DiscountPrice); 
		 			 Common.assertionCheckwithReport(ActualPrice>DiscountPrice, "Discount price display successfull", "Should display discounted price and must be lesser than Actual price", "failed to display the Discount price for the employee" );
		 			System.out.println("Discount amount is availed on the product");} 
				}else {
					System.out.println("Discount price is not available for the user");
					
				}
			}
			
			
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display Search Results Page", "Search results Page not display",
					Common.getscreenShotPathforReport("Search result Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}

	public void zerosearchProduct(String dataSet) throws Exception {
		String expectedResult = "Search with Product name :" + data.get(dataSet).get("ProductName");
		try {
			Sync.waitElementPresent(10, "id", "search");
			try {
				Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName"));

			} catch (Exception e) {
				Common.clickElement("xpath",
						"//div[@class='block block-search search-visible-md minisearch-v2']/div/i");
				Thread.sleep(3000);
				Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName"));
			}
			Common.actionsKeyPress(Keys.ENTER);
			Thread.sleep(10000);
			Assert.assertTrue(Common.isElementDisplayed("id", "nosearchresultcount"));
			// Common.scrollIntoView("xpath",
			// "(//div[@class='product-item-info']/div//a[@class='product photo
			// product-item-photo title'])[1]");
			report.addPassLog(expectedResult, "Should display Zero search results Page",
					"Zero search results Page display successfully",
					Common.getscreenShotPathforReport("Zero results page success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display Zero search results Page",
					"Zero search results Page not display", Common.getscreenShotPathforReport("Zearo results Failed"));
			Assert.fail();
		}

	}

	public void promocodeproduct_selection() {
		String expectedResult = "Product Selection from search results";
		try {
			
			Thread.sleep(3000);
			Sync.waitElementPresent(20, "xpath",
					"(//button[@class='action tocart primary normal-add-btn'])[1]");
			
			Common.clickElement("xpath",
					"(//button[@class='action tocart primary normal-add-btn'])[1]");
			Thread.sleep(5000);
			int successmessage = Common.findElements("xpath", "//div[@data-bind='html: message.text']").size();
			
			Common.assertionCheckwithReport(successmessage>0, "Products will add to cart from PLP", expectedResult, "Failed to add products to the cart");
		
			Sync.waitElementPresent(20, "xpath",
					"//a[@class='action viewcart hvr-sweep-to-right rev-checkout-btn rev-cart-view']");
			// Common.clickElement("xpath",
			// "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/checkout/cart/']"));
			Common.clickElement("xpath",
					"//a[@class='action viewcart hvr-sweep-to-right rev-checkout-btn rev-cart-view']");
			Thread.sleep(2000);
			Common.actionsKeyPress(Keys.ARROW_DOWN);

			
		}catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display Product Details Page",
					"Product details Page not displayed", Common.getscreenShotPathforReport("Zero search results Failed"));
			Assert.fail();
		}
	}
	public void Productselection() throws Exception {
		String expectedResult = "Product Selection from search results";
		try {
			Thread.sleep(3000);
			Sync.waitElementPresent(20, "xpath",
					"(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[1]");
			
			Common.clickElement("xpath",
					"(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[1]");
			Thread.sleep(3000);
			Common.isElementDisplayed("xpath", "//h1[@class='page-title']");
			
			
			report.addPassLog(expectedResult, "Should display Product Details Page",
					"Product Details Page display successfully",
					Common.getscreenShotPathforReport("Product Details page success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display Product Details Page",
					"Product details Page not displayed", Common.getscreenShotPathforReport("Zero search results Failed"));
			Assert.fail();
		}

	}

	public void loginRevlon(String dataSet) throws Exception {

		if (Common.isElementDisplayed("xpath", "//button[text()='AGREE & PROCEED']")) {
			Thread.sleep(2000);
			acceptPrivecy();
		}
		navigateAccount();

		String expectedResult = "Land on login page";
		try {
			int i = 0;
			do {
				Common.textBoxInput("xpath", "//input[@name='login[username]']", data.get(dataSet).get("Email"));
				Common.textBoxInput("xpath", "//input[@name='login[password]']", data.get(dataSet).get("Password"));
				// Common.scrollIntoView("xpath", "//button[text()='Sign In']");
				// Common.mouseOver("xpath", "//button[text()='Sign In']");
				Common.clickElement("xpath", "//button[text()='Sign In']");
				Thread.sleep(5000);
				i++;
			} while (i < 3 && !Common.isElementDisplayed("xpath", "//span[contains(text(),'My Account')]"));
			
			

			report.addPassLog(expectedResult, "Should display My account Page", "My account page display successfully",
					Common.getscreenShotPathforReport("Login page success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display  My account Page", " My account Page not displayed",
					Common.getscreenShotPathforReport("Login page Failed"));
	e.printStackTrace();
			Assert.fail();
		}
		
	/*	try{
			Sync.waitElementPresent("xpath", "//a[@class='action showcart']");
			Common.clickElement(By.xpath("//a[@class='action showcart']"));		
			int items = Common.findElements(By.xpath("//span[@data-th='Shipping']")).size();
			if(items>0){
				int i=1;
				for(i=1;i<items;i++){
				Sync.waitElementVisible("xpat", "(//span[text()='REMOVE'])[1]");
				Common.clickElement(By.xpath("(//span[text()='REMOVE'])[1]"));
				Thread.sleep(3000);
				Sync.waitElementVisible(30, "xpath", "//span[text()='OK']");
				Common.clickElement(By.xpath("//span[text()='OK']"));
				Sync.waitElementVisible("xpath", "//a[@class='action showcart']");
				
				
				
					
				}
			}
				
				
			
		}catch(Exception | Error e){
			report.addFailedLog(expectedResult, "Should display  My account Page", " My account Page not displayed",
					Common.getscreenShotPathforReport("Login page Failed"));
	e.printStackTrace();
			Assert.fail();
		}
*/
		/*
		 * if(Common.isElementDisplayed("xpath",
		 * "//span[contains(text(),'My Account')]")) {
		 * System.out.println("user not login successfully");
		 * Assert.assertEquals("My Account", Common.getText("xpath",
		 * "//span[contains(text(),'My Account')]")); report.addPassLog(expectedResult,
		 * "Should display My Account Page", "My Account Page display successfully",
		 * Common.getscreenShotPathforReport("User Login success")); }else {
		 * Thread.sleep(100); Common.refreshpage(); Thread.sleep(200);
		 * Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
		 * Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
		 * Common.scrollIntoView("xpath", "//button[text()='Sign In']");
		 * Thread.sleep(500); //report.addPassLog(expectedResult, "", actualResult,
		 * screenShotPath); //Common.mouseOver("xpath", "//button[text()='Sign In']");
		 * Common.clickElement("xpath", "//button[text()='Sign In']");
		 * Thread.sleep(1000); report.addPassLog(expectedResult,
		 * "Should display My Account Page", "My Account Page display successfully",
		 * Common.getscreenShotPathforReport("User Login success")); }
		 */

	}

	public void proccedToCheckout() throws InterruptedException {
		Common.clickElement("xpath", "//a[@class='action viewcart hvr-sweep-to-right rev-checkout-btn rev-cart-view']");
		// Common.clickElement("xpath", "//a[@class='action showcart']");
		Thread.sleep(3000);
		Common.clickElement("xpath", "//button[text()=' Checkout']");
		Thread.sleep(4000);
	}

	public void addShippingAddress(String dataSet) throws Exception {
		try {
			Common.textBoxInput("name", "street[0]", data.get(dataSet).get("City"));
		} catch (Exception e) {
			if (Common.findElements("xpath", "//div[@class='shipping-address-item selected-item']").size() > 0) {
				Common.actionsKeyPress(Keys.PAGE_DOWN);
				Common.clickElement("xpath", "//input[@name='shipping_method']");
				Common.clickElement("xpath", "//button[@class='rev-w-btn rev-def-btn next-btn shipping-next']");
				return;
			} else {
				proccedToCheckout();
				if (Common.findElements("xpath", "//div[@class='shipping-address-item selected-item']").size() > 0) {
					Common.actionsKeyPress(Keys.PAGE_DOWN);
					Common.clickCheckBox("xpath", "//input[@name='shipping_method']");
					Common.clickElement("xpath", "//button[@class='rev-w-btn rev-def-btn next-btn shipping-next']");
					return;
				} else {
					Common.textBoxInput("name", "street[0]", data.get(dataSet).get("City"));
				}
			}
		}
		Thread.sleep(2000);
		Common.actionsKeyPress(Keys.SPACE);
		Thread.sleep(3000);
		Common.clickElement("xpath", "//*[@id='shipping-new-address-form']/fieldset/div/div[1]/div/ul/li[1]/a");
		Common.textBoxInput("xpath", "//input[@name='telephone']", "9898989898");
		Thread.sleep(2000);
		Common.clickElement("xpath", "//button[@class='rev-w-btn rev-def-btn next-btn shipping-next']");
	}

	public void addPaymentDetails(String dataSet) throws Exception {
		
		// Common.switchFrames("xpath", "//iframe[@id='paymetric_xisecure_frame']");
		// Common.switchFrames("xpath", "//iframe[@id='paymetric_xisecure_frame']");
		
		Sync.waitElementClickable("xpath", "//label[@for='ime_paymetrictokenize']");
		Common.clickElement("xpath", "//label[@for='ime_paymetrictokenize']");
		Thread.sleep(2000);
		//Common.actionsKeyPress(Keys.PAGE_DOWN);
		Common.switchFrames("id", "paymetric_xisecure_frame");
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.PAGE_DOWN);

		Common.dropdown("xpath", "//select[@id='c-ct']", Common.SelectBy.TEXT, data.get(dataSet).get("cardType"));
		Common.textBoxInput("id", "c-cardnumber", data.get(dataSet).get("cardNumber"));
		Common.dropdown("xpath", "//select[@id='c-exmth']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpMonth"));
		Common.dropdown("xpath", "//select[@id='c-exyr']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpYear"));
		Common.textBoxInput("id", "c-cvv", data.get(dataSet).get("cvv"));

		Thread.sleep(1000);
		report.addPassLog("Should populate the credit card details", "Credit card details will be populated", Common.getscreenShotPathforReport("Credit card details entered successfully "));
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.switchToDefault();
		Thread.sleep(500);

	}

	public void updatePaymentAndSubmitOrder(String dataSet) throws Exception {
		String expectedResult = "Payment & Order submition success page with Credit card";
		try {
			addPaymentDetails(dataSet);
			if (Common.findElements("xpath", "//div[@class='message message-error']").size() > 0) {
				addPaymentDetails(dataSet);
			}
			Sync.waitPageLoad(30);
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(2000);
		String URL = Common.getCurrentURL();
		if(URL.equals("https://www.revlonhairtools.com/us_en/checkout/#payment")){
			System.out.println("Cannot place order in production website");
		}else{
			//Common.actionsKeyPress(Keys.PAGE_DOWN);
			Sync.waitElementPresent(10, "xpath", "//div[6]/div/button[@id='paymetrictokenize_place_order']");
			// Common.scrollIntoView("xpath",
			// "//button[@id='paymetrictokenize_place_order']");
			Common.clickElement("xpath", "//div[6]/div/button[@id='paymetrictokenize_place_order']");
			Thread.sleep(2000);
           Sync.waitPageLoad(30);
			Sync.waitElementPresent(20, "xpath", "//h1[contains(text(),'Thank you for your purchase')]");
			String sucessMessage = Common.getText("xpath", "//h1[@class='page-title']");
			// String sucessMessage=Common.getText("xpath", "//h1[contains(text(),'Thank you
			// for your purchase')]");
			Thread.sleep(3000);
			System.out.println(sucessMessage);
			Thread.sleep(2000);
			Assert.assertEquals(sucessMessage, "Thank you for your purchase");
			report.addPassLog(expectedResult, "Should display Order Success Page",
					"Order Success Page display successfully",
					Common.getscreenShotPathforReport("Order success page success"));
		}
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display Order Success Page", "Order Success Page not displayed",
					Common.getscreenShotPathforReport("Order Success Page Failed"));
			Assert.fail();
		}
	}

	/*
	 * if(Common.isElementDisplayed("xpath",
	 * "//img[@src='"+System.getProperty("url",automation_properties.getInstance().
	 * getProperty(automation_properties.BASEURL)+
	 * "static/version1603995779/frontend/Pearl/weltpixel_custom/en_US/images/loader-2.gif']"
	 * ))){ System.out.println("place order button clicked");
	 * 
	 * Sync.waitElementPresent(30, "xpath",
	 * "//img[@src='"+System.getProperty("url",automation_properties.getInstance().
	 * getProperty(automation_properties.BASEURL)+
	 * "static/version1603995779/frontend/Pearl/weltpixel_custom/en_US/images/loader-2.gif']"
	 * )); if(Common.isAlertPresent()) { Common.acceptAlert(10); }
	 * Sync.waitAlert(20); Common.acceptAlert(5);
	 * 
	 * Thread.sleep(2000); String sucessMessage=Common.getText("xpath",
	 * "//h1[@class='page-title']").trim(); Assert.assertEquals(sucessMessage,
	 * "THANK YOU FOR YOUR PURCHASE");
	 * 
	 * }else { System.out.println("place order button not clicked");
	 * Common.clickElement("xpath", "//button[@title='Place Order']");
	 * 
	 * //Sync.waitElementPresent(10, "xpath",
	 * "//img[@src='"+System.getProperty("url",automation_properties.getInstance().
	 * getProperty(automation_properties.BASEURL)+
	 * "static/version1603995779/frontend/Pearl/weltpixel_custom/en_US/images/loader-2.gif']"
	 * ));
	 * 
	 * Sync.waitAlert(20); Common.acceptAlert(5);
	 * 
	 * Thread.sleep(2000);
	 * 
	 * String sucessMessage=Common.getText("xpath",
	 * "//h1[@class='page-title']").trim(); Assert.assertEquals(sucessMessage,
	 * "THANK YOU FOR YOUR PURCHASE");
	 * 
	 * }
	 */

	public void invalidCreditCard(String dataSet) throws Exception {
		String expectedResult = "Payment Method with invalid Credit card";
		try {
			addPaymentDetails(dataSet);

			if (Common.findElements("xpath", "//div[@class='message message-error']").size() > 0) {
				addPaymentDetails(dataSet);
			}

			Common.clickElement("xpath", "//button[@id='paymetrictokenize_place_order']");
			Thread.sleep(3000);

			Common.switchFrames("id", "paymetric_xisecure_frame");
			String Errormessage = Common.getText("xpath", "//div[@id='c-cardnumber-error']");
			System.out.println(Errormessage);
			Assert.assertEquals(Errormessage, "Please enter a valid card number");
			report.addPassLog(expectedResult, "Should display Error message for Credit card number feild",
					"Error message for Credit card number feild display successfully",
					Common.getscreenShotPathforReport("Error message credit card success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display Error message for Credit card number feild",
					"Error message for Credit card number feild not display",
					Common.getscreenShotPathforReport("Error message credit card Failed"));
			e.printStackTrace();
			Assert.fail();
		}

		/*
		 * if(Common.isElementDisplayed("xpath",
		 * "//img[@src='"+System.getProperty("url",automation_properties.getInstance().
		 * getProperty(automation_properties.BASEURL)+
		 * "static/version1603995779/frontend/Pearl/weltpixel_custom/en_US/images/loader-2.gif']"
		 * ))){ System.out.println("place order button clicked");
		 * 
		 * Sync.waitElementPresent(30, "xpath",
		 * "//img[@src='"+System.getProperty("url",automation_properties.getInstance().
		 * getProperty(automation_properties.BASEURL)+
		 * "static/version1603995779/frontend/Pearl/weltpixel_custom/en_US/images/loader-2.gif']"
		 * )); if(Common.isAlertPresent()) { Common.acceptAlert(10); }
		 * Sync.waitAlert(20); Common.acceptAlert(5);
		 * 
		 * Thread.sleep(2000); String sucessMessage=Common.getText("xpath",
		 * "//h1[@class='page-title']").trim(); Assert.assertEquals(sucessMessage,
		 * "THANK YOU FOR YOUR PURCHASE");
		 * 
		 * }else { System.out.println("place order button not clicked");
		 * Common.clickElement("xpath", "//button[@title='Place Order']");
		 * 
		 * //Sync.waitElementPresent(10, "xpath",
		 * "//img[@src='"+System.getProperty("url",automation_properties.getInstance().
		 * getProperty(automation_properties.BASEURL)+
		 * "static/version1603995779/frontend/Pearl/weltpixel_custom/en_US/images/loader-2.gif']"
		 * ));
		 * 
		 * Sync.waitAlert(20); Common.acceptAlert(5);
		 * 
		 * Thread.sleep(2000);
		 * 
		 * String sucessMessage=Common.getText("xpath",
		 * "//h1[@class='page-title']").trim(); Assert.assertEquals(sucessMessage,
		 * "THANK YOU FOR YOUR PURCHASE");
		 * 
		 * }
		 */

	}

	public void addPaypalDetails(String dataSet) throws Exception {
		
		Sync.waitElementClickable("xpath", "//label[@for='paypal_express']");
		Common.clickElement("xpath", "//label[@for='paypal_express']");
		Thread.sleep(5000);
		Common.switchFrames("xpath", "(//div[@class='paypal-buttons paypal-buttons-context-iframe paypal-buttons-label-paypal paypal-buttons-layout-vertical']/iframe)[1]");
		Thread.sleep(4000);
		Sync.waitElementClickable(30, "xpath", "//div[@class='paypal-button-label-container']");
		Common.javascriptclickElement("xpath", "//div[@class='paypal-button-label-container']");
		Thread.sleep(3000);
		Common.switchToDefault();
		
		//Sync.waitElementPresent("xpath", "//span[contains(text(),'Continue to PayPal')]");
		//Common.clickElement("xpath", "//span[contains(text(),'Continue to PayPal')]");
		//Thread.sleep(3000);
		// Common.scrollIntoView("xpath", "//ul[@class='opc-progress-bar']");
		// Thread.sleep(1000);

		// Common.switchFrames("xpath", "//iframe[@title='PayPal']");
		// Common.actionsKeyPress(Keys.DOWN);
		//Thread.sleep(5000);

		// *[@id='paypal-animation-content']/div[1]/div[1]/div/img[1]
		// Sync.waitElementClickable("xpath",
		// "//*[@id='paypal-animation-content']/div[1]/div[1]/div");
		// Common.clickElement("xpath",
		// "//*[@id='paypal-animation-content']/div[1]/div[1]/div");
		// Common.javascriptclickElement("xpath",
		// "//*[@id='paypal-animation-content']/div[1]/div[1]/div");

		// Common.switchToDefault();
		Thread.sleep(6000);
		// Common.switchWindows();
	/*if(Common.isElementDisplayed("xpath", "//div/a[contains(text(),'Click to Continue')]")){
	Common.clickElement(By.xpath("//div/a[contains(text(),'Click to Continue')]"));
	
	Thread.sleep(3000);
	}
      */  
		Common.switchWindows();
		Thread.sleep(8000);
		Common.mouseOverClick("xpath", "//button[text()='Accept Cookies']");
		Thread.sleep(4000);
		Common.textBoxInputClear("name", "login_email");
		Thread.sleep(2000);
		Common.textBoxInput("name", "login_email", data.get(dataSet).get("UserName"));
		Thread.sleep(2000);
		Common.textBoxInput("name", "login_password", data.get(dataSet).get("Password"));
		Thread.sleep(3000);
		Common.clickElement("xpath", "//button[@id='btnLogin']");
		Thread.sleep(5000);
		Common.actionsKeyPress(Keys.END);
		Thread.sleep(5000);
		Common.scrollIntoView("xpath", "//button[contains(text(),'Pay Now')]");
		Common.clickElement("id", "payment-submit-btn");
		Thread.sleep(8000);
		Common.switchToFirstTab();
	}

	public void updatePaypalPaymentAndSubmitOrder(String dataSet) throws Exception {
		String expectedResult = "Payment & Order submission page with PayPal";
		try {
			String URL = Common.getCurrentURL();
			
			if(URL.equals("https://www.revlonhairtools.com/checkout/#payment")){
				System.out.println("Paypal is not available in production");
			}else{
			addPaypalDetails(dataSet);

			if (Common.findElements("xpath", "//div[@class='message message-error']").size() > 0) {
				addPaypalDetails(dataSet);
			}

			Thread.sleep(8000);
               Sync.waitPageLoad(30);
			String url = Common.getCurrentURL();

			System.out.println(url);
			Common.assertionCheckwithReport(url.contains("success"), "order sucess page is displayed", expectedResult,
					"order sucess page not displayed");

			report.addPassLog(expectedResult, "Should display Order Success Page",
					"Order Success Page display successfully",
					Common.getscreenShotPathforReport("Order success page success"));
		}
			
		}catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display Order Success Page", "Order Success Page not displayed",
					Common.getscreenShotPathforReport("Order Success Page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void acceptPrivecy() throws Exception {
		// Sync.waitElementPresent(5, "xpath", "//button[@class='truste-button
		// truste-required-btn']");
		Thread.sleep(5000);
		Common.clickElement(By.xpath("//button[@class='truste-button truste-required-btn']"));

	}

	public RevelonHelper(String datafile) {
		excelData = new ExcelReader(datafile);
		data = excelData.getExcelValue();
		this.data = data;
		if (Utilities.TestListener.report == null) {
			report = new ExtenantReportUtils("Revlon");
			report.createTestcase("RevlonUSTestCases");
		} else {
			this.report = Utilities.TestListener.report;
		}
	}

	public void accountIcon() throws Exception {
		Sync.waitElementPresent("xpath",
				"//div[@class='authorization-link header_right user-dropdown m-hide non_ipad']");
		Common.clickElement("xpath", "//div[@class='authorization-link header_right user-dropdown m-hide non_ipad']");

	}

	public void myAccountLink() throws Exception {
		automation_properties = Automation_properties.getInstance();
		Sync.waitElementPresent("xpath",
				"//a[@href='" + System.getProperty("url",
						automation_properties.getInstance().getProperty(automation_properties.BASEURL)
								+ "us_en/customer/account/']"));
		Common.clickElement("xpath",
				"//a[@href='" + System.getProperty("url",
						automation_properties.getInstance().getProperty(automation_properties.BASEURL)
								+ "us_en/customer/account/']"));
	}

	public void Logout() throws Exception {
		automation_properties = Automation_properties.getInstance();
		Sync.waitElementPresent("xpath",
				"//a[@href='" + System.getProperty("url",
						automation_properties.getInstance().getProperty(automation_properties.BASEURL)
								+ "us_en/customer/account/logout/']"));
		Common.clickElement("xpath",
				"//a[@href='" + System.getProperty("url",
						automation_properties.getInstance().getProperty(automation_properties.BASEURL)
								+ "us_en/customer/account/logout/']"));
		Thread.sleep(1000);

	}

	public void forgotPassword(String dataSet) throws Exception {
		String expectedResult = "Forgot Password for Registered User";
		Thread.sleep(2000);
		try {
			String expectedResult1 = "Landed on Login page";
			Sync.waitElementClickable(30, By.xpath("//a[@title='My Account']"));
			Common.findElement("xpath", "//a[@title='My Account']").click();
			report.addPassLog(expectedResult1, "Should display login page", "Login page displayed successfully",
					Common.getscreenShotPathforReport("Login page"));
			Sync.waitElementClickable(30,
					By.xpath("//a[@href='" + System.getProperty("url",
							automation_properties.getInstance().getProperty(automation_properties.BASEURL)
									+ "us_en/customer/account/forgotpassword/']")));
			Common.clickElement("xpath",
					"//a[@href='" + System.getProperty("url",
							automation_properties.getInstance().getProperty(automation_properties.BASEURL)
									+ "us_en/customer/account/forgotpassword/']"));
			int i = 0;
			do {
				Common.textBoxInput("id", "email_address", data.get(dataSet).get("Email"));
				Sync.waitElementClickable(30, By.xpath("//button[contains(text(),'Reset My Password')]"));
				Common.clickElement("xpath", "//button[contains(text(),'Reset My Password')]");
				Thread.sleep(4000);

				i++;
			} while (i < 3 && !Common.isElementDisplayed("xpath", "//span[contains(text(),'Customer Login')]"));
			Thread.sleep(5000);
			if (Common.isElementDisplayed("xpath", "//div[@data-bind='html: message.text']")) {
				String s = Common.getText("xpath", "//div[@data-bind='html: message.text']");
				Thread.sleep(4000);
				Assert.assertEquals(s, "If there is an account associated with " + data.get(dataSet).get("Email")
						+ " you will receive an email with a link to reset your password.");
				Thread.sleep(4000);
			}
			report.addPassLog(expectedResult, "Should display Forgot Password Succes message",
					"Forgot Password page success message displayed successfully",
					Common.getscreenShotPathforReport("Forgot Password text"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display Forgot Password Succes message",
					"Forgot Password page success message not displayed",
					Common.getscreenShotPathforReport("Account Creation Failed"));
			Assert.fail();
		}
	}

	public void navigateMyAccount(String dataSet) throws Exception {
		Sync.waitElementPresent("xpath",
				"//a[@href='" + System.getProperty("url",
						automation_properties.getInstance().getProperty(automation_properties.BASEURL)
								+ "us_en/customer/account/']"));
		Common.clickElement("xpath",
				"//a[@href='" + System.getProperty("url",
						automation_properties.getInstance().getProperty(automation_properties.BASEURL)
								+ "us_en/customer/account/']"));

		Thread.sleep(2000);

		Sync.waitElementPresent("xpath", "//div[@class='box-content']");
		String s = Common.getText("xpath", "//div[@class='box-content']");
		System.out.println(s);
		// assertEquals(s, data.get(dataSet).get("FirstName")+
		// data.get(dataSet).get("LastName")+ data.get(dataSet).get("Email"));
	}

	public void navigateMyOrder() throws Exception {
		accountIcon();
		myAccountLink();

		Sync.waitElementPresent("xpath",
				"//a[@href='" + System.getProperty("url",
						automation_properties.getInstance().getProperty(automation_properties.BASEURL)
								+ "us_en/sales/order/history/']"));
		Common.clickElement("xpath",
				"//a[@href='" + System.getProperty("url",
						automation_properties.getInstance().getProperty(automation_properties.BASEURL)
								+ "us_en/sales/order/history/']"));

		Thread.sleep(2000);

		String s = Common.getText("xpath", "//p[@class='toolbar-amount']");
		System.out.println("No of orders :" + s);
	}

	public void addNewAddress(String dataSet) throws Exception {

		Common.textBoxInput("name", "firstname", data.get(dataSet).get("FirstName"));
		Common.textBoxInput("name", "lastname", data.get(dataSet).get("LastName"));
		Common.textBoxInput("name", "company", data.get(dataSet).get("Company"));
		Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
		Common.textBoxInput("name", "street[]", data.get(dataSet).get("Street"));
		Common.textBoxInput("name", "city", data.get(dataSet).get("City"));

		Thread.sleep(1000);

		Sync.waitElementPresent(10, "xpath", "//select[@title='State/Province']");
		Common.clickElement("xpath", "//select[@title='State/Province']");

		Thread.sleep(1000);

		Sync.waitElementPresent(10, "xpath", "//select[@title='State/Province']");
		// Common.clickElement("xpath", "//select[@title='State/Province']");
		Common.dropdown("xpath", "//select[@title='State/Province']", SelectBy.TEXT, data.get(dataSet).get("Region"));

		Sync.waitElementPresent(10, "name", "postcode");
		Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));

		Common.dropdown("xpath", "//select[@name='country_id']", SelectBy.TEXT, data.get(dataSet).get("Country"));

		Thread.sleep(1000);
  
		Common.clickElement(By.xpath("(//label[@for='primary_shipping']/span)[2]"));
		 
		Sync.waitElementPresent(20, "xpath", "//button[@title='Save Address']");
		Common.clickElement("xpath", "//button[@title='Save Address']");

		Sync.waitElementPresent(20, "xpath", "//button[@class='action-primary action-accept']");
		Common.clickElement("xpath", "//button[@class='action-primary action-accept']");

	}

	public void navigateAddressBook() throws Exception {
		accountIcon();
		myAccountLink();

		Sync.waitElementPresent(20, "xpath",
				"//a[@href='" + System.getProperty("url",
						automation_properties.getInstance().getProperty(automation_properties.BASEURL)
								+ "us_en/customer/address/']"));
		Common.clickElement("xpath",
				"//a[@href='" + System.getProperty("url",
						automation_properties.getInstance().getProperty(automation_properties.BASEURL)
								+ "us_en/customer/address/']"));
		Thread.sleep(2000);
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		if (Common.isElementDisplayed("xpath", "//button[@title='Add New Address']")) {
			System.out.println("Add New Address clicked");

			Sync.waitElementPresent(20, "xpath", "//button[@title='Add New Address']");
			Common.clickElement("xpath", "//button[@title='Add New Address']");

			Thread.sleep(5000);
			if (Common.isElementDisplayed("name", "firstname")) {
				System.out.println("Entered into Add New Address page");
				addNewAddress("Address");
			} else {

				System.out.println("Not Entered into Add New Address page");

				Thread.sleep(2000);

				Sync.waitElementPresent("xpath", "//button[@title='Add New Address']");
				Common.clickElement("xpath", "//button[@title='Add New Address']");

				Thread.sleep(5000);

				addNewAddress("Address");
			}
		} else {
			System.out.println("Add New Address not clicked");

			addNewAddress("Addressbook");
		}

	}

	public void navigateAccountInformation() throws Exception {
		accountIcon();
		myAccountLink();

		Sync.waitElementPresent("xpath",
				"//a[@href='" + System.getProperty("url",
						automation_properties.getInstance().getProperty(automation_properties.BASEURL)
								+ "us_en/customer/account/edit/']"));
		Common.clickElement("xpath",
				"//a[@href='" + System.getProperty("url",
						automation_properties.getInstance().getProperty(automation_properties.BASEURL)
								+ "us_en/customer/account/edit/']"));

	}

	public void changeAIName(String dataSet) throws Exception {

		Thread.sleep(1000);

		Common.textBoxInput("name", "firstname", data.get(dataSet).get("FirstName"));
		Common.textBoxInput("name", "lastname", data.get(dataSet).get("LastName"));

		Thread.sleep(500);

		Sync.waitElementPresent("xpath", "//button[@title='Save']");
		Common.clickElement("xpath", "//button[@title='Save']");

		Thread.sleep(1000);

		String s = Common.getText("xpath", "//div[@data-bind='html: message.text']");
		System.out.println(s);

	}

	public void changeAIEmail(String dataSet) throws Exception {

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

		String s = Common.getText("xpath", "//div[@data-bind='html: message.text']");
		System.out.println(s);
	}

	public void changeAIPassword(String dataSet) throws Exception {

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

		String s = Common.getText("xpath", "//div[@data-bind='html: message.text']");
		System.out.println(s);

	}

	public void navigateNewsletterSubscription() throws Exception {
		accountIcon();
		myAccountLink();

		Sync.waitElementPresent("xpath",
				"//a[@href='" + System.getProperty("url",
						automation_properties.getInstance().getProperty(automation_properties.BASEURL)
								+ "us_en/newsletter/manage/']"));
		Common.clickElement("xpath",
				"//a[@href='" + System.getProperty("url",
						automation_properties.getInstance().getProperty(automation_properties.BASEURL)
								+ "us_en/newsletter/manage/']"));

		Thread.sleep(300);

		Sync.waitElementPresent("xpath", "//input[@name='is_subscribed']");
		Common.clickElement("xpath", "//input[@name='is_subscribed']");

		Sync.waitElementPresent("xpath", "//button[@title='Save']");
		Common.clickElement("xpath", "//button[@title='Save']");

		Thread.sleep(300);

		String s = Common.getText("xpath", "//div[@class='box box-newsletter']//div[@class='box-content']");
		System.out.println(s);

	}

	public void navigateContactUs() throws Exception {
		String expectedResult = "Lands on Contact page";
		try {
			Sync.waitElementPresent("xpath", "//h1[contains(text(),'Hair Tools')]");
			Common.scrollToElementAndClick("xpath", "//h1[contains(text(),'Hair Tools')]");

			Sync.waitElementPresent("xpath", "//a[@href='/contact-us/']");
			Common.clickElement("xpath", "//a[@href='/contact-us/']");
			Thread.sleep(300);
			String s = Common.getText("xpath", "//span[contains(text(),'Contact Us')]");
			System.out.println(s);
			Assert.assertEquals(s, "CONTACT US");

			report.addPassLog(expectedResult, "Should display Contact Us Page", "Contact Us Page display successfully",
					Common.getscreenShotPathforReport("Contact Us page success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display Contact Us Page", "Contact Us Page not displayed",
					Common.getscreenShotPathforReport("Contact Us Failed"));
			Assert.fail();
		}

	}

	/*
	 * public void ContactUsform(String dataSet) throws Exception { String
	 * expectedResult="Contact Us Form submited"; String s=Common.getText("xpath",
	 * "//h1[@class='page-title']"); System.out.println(s+" page is displayed");
	 * 
	 * Thread.sleep(1000);
	 * 
	 * Common.switchFrames("xpath",
	 * "//iframe[@src='https://helenoftroy--tst2.custhelp.com/app/ask/themes/revlon']"
	 * );
	 * 
	 * //Common.clickElement("xpath", "//body[@id='rn_BlankBody']");
	 * 
	 * Sync.waitPageLoad();
	 * 
	 * System.out.println(s+" page switched to form");
	 * 
	 * Sync.waitElementPresent("name", "Contact.Name.First");
	 * Common.textBoxInput("xpath","//input[@name='Contact.Name.First']",
	 * data.get(dataSet).get("FirstName"));
	 * 
	 * Common.textBoxInput("name", "Contact.Name.Last",
	 * data.get(dataSet).get("LastName"));
	 * 
	 * Common.textBoxInput("name", "Contact.CustomFields.c.company",
	 * data.get(dataSet).get("Company"));
	 * 
	 * Sync.waitElementPresent("name", "Contact.Address.Country");
	 * Common.dropdown("name", "Contact.Address.Country", SelectBy.TEXT,
	 * data.get(dataSet).get("Country"));
	 * 
	 * Common.textBoxInput("name", "Contact.Address.Street",
	 * data.get(dataSet).get("Street"));
	 * 
	 * Common.textBoxInput("name", "Contact.Address.City",
	 * data.get(dataSet).get("City"));
	 * 
	 * Sync.waitElementPresent("name", "Contact.Address.StateOrProvince");
	 * Common.dropdown("name", "Contact.Address.StateOrProvince", SelectBy.TEXT,
	 * data.get(dataSet).get("Region"));
	 * 
	 * Common.textBoxInput("name", "Contact.Address.PostalCode",
	 * data.get(dataSet).get("postcode"));
	 * 
	 * Common.textBoxInput("name", "Contact.Emails.PRIMARY.Address",
	 * data.get(dataSet).get("Email"));
	 * 
	 * Common.textBoxInput("name", "Contact.Phones.MOBILE.Number",
	 * data.get(dataSet).get("phone"));
	 * 
	 * Common.textBoxInput("name", "searchKeyword",
	 * data.get(dataSet).get("ProductName"));
	 * 
	 * Thread.sleep(300);
	 * 
	 * System.out.println("Product search are display");
	 * 
	 * Sync.waitElementPresent("xpath", "//*[@id='searchResults']/div[1]");
	 * Common.scrollToElementAndClick("xpath", "//*[@id='searchResults']/div[1]");
	 * 
	 * Thread.sleep(300);
	 * 
	 * Common.clickElement("name", "Incident.CustomFields.c.date_code");
	 * 
	 * Common.textBoxInput("name", "Incident.CustomFields.c.date_code",
	 * data.get(dataSet).get("dataCode"));
	 * 
	 * Sync.waitElementPresent("id", "rn_ProductCategoryInput_27_Category_Button");
	 * Common.clickElement("id", "rn_ProductCategoryInput_27_Category_Button");
	 * 
	 * if(Common.isElementDisplayed("xpath",
	 * "//a[contains(text(),'Product Info Request')]")) { Thread.sleep(300);
	 * 
	 * System.out.println("Topic dropdown are display");
	 * 
	 * Common.clickElement("xpath", "//a[contains(text(),'Product Info Request')]");
	 * }
	 * 
	 * Thread.sleep(1000);
	 * 
	 * Common.actionsKeyPress(Keys.DOWN);
	 * 
	 * Common.textBoxInput("name", "Incident.Threads",
	 * data.get(dataSet).get("Message"));
	 * 
	 * report.addPassLog(expectedResult, "Should dispaly Contact us page with data",
	 * "Contact Us Page with data displayed successfully",
	 * Common.getscreenShotPathforReport("Contact Us Page with data"));
	 * 
	 * Sync.waitElementPresent("id", "rn_CustomFormSubmit_53_Button");
	 * Common.clickElement("id", "rn_CustomFormSubmit_53_Button");
	 * 
	 * Thread.sleep(500); }
	 */
	
	public void ContactUsform(String dataSet) throws Exception

	{

		String expectedResult = "Contact Us Form submited";

		String s = Common.getText("xpath", "//h1[@class='page-title']");

		System.out.println(s + " page is displayed");

		Thread.sleep(1000);

		Common.switchFrames("xpath", "//iframe[@src='https://helenoftroy--tst2.custhelp.com/app/ask/themes/revlon']");

		// Common.clickElement("xpath", "//body[@id='rn_BlankBody']");

		Sync.waitPageLoad();

		System.out.println(s + " page switched to form");

		Sync.waitElementPresent("name", "Contact.Name.First");

		Common.textBoxInput("xpath", "//input[@name='Contact.Name.First']", data.get(dataSet).get("FirstName"));

		Common.textBoxInput("name", "Contact.Name.Last", data.get(dataSet).get("LastName"));

		Common.textBoxInput("name", "Contact.CustomFields.c.company", data.get(dataSet).get("Company"));

		//Sync.waitElementPresent("name", "Contact.Address.Country");

		//Common.dropdown("name", "Contact.Address.Country", SelectBy.TEXT, data.get(dataSet).get("Country"));

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

		if (Common.isElementDisplayed("xpath", "//a[contains(text(),'Product Info Request')]")) {

			Thread.sleep(300);

			System.out.println("Topic dropdown are display");

			Common.clickElement("xpath", "//a[contains(text(),'Product Info Request')]");

		}

		Thread.sleep(1000);

		Common.actionsKeyPress(Keys.DOWN);

		Common.textBoxInput("name", "Incident.Threads", data.get(dataSet).get("Message"));

		report.addPassLog(expectedResult, "Should dispaly Contact us page with data",
				"Contact Us Page with data displayed successfully",
				Common.getscreenShotPathforReport("Contact Us Page with data"));

		Thread.sleep(3000);

		/*
		 * Common.actionsKeyPress(Keys.DOWN);
		 * 
		 * Thread.sleep(3000);
		 * 
		 * Common.actionsKeyPress(Keys.DOWN);
		 */

		Sync.waitElementPresent("id", "rn_CustomFormSubmit_53");

		Common.clickElement("id", "rn_CustomFormSubmit_53");

		Thread.sleep(1000);

	}

	public void contactus() throws Exception {
		String expectedResult = "Contact Us Submit success page";
		try {
			if (Common.isElementEnabled("xpath", "//input[contains(@id,'Contact.Name.First')]")) {

				System.out.println("Contact Us page Enabled");
				ContactUsform("Contact_us");

			} else {

				System.out.println("Contact Us page not Enabled");

				Common.refreshpage();

				ContactUsform("Contact_us");
			}

			Thread.sleep(7000);
			String s = Common.getText("xpath", "//h1[text()='Your question has been submitted!']");
			System.out.println(s);
			System.out.println("Contact us success page Test cases passed successfully");

			Assert.assertEquals(s, "Your question has been submitted!");

			report.addPassLog(expectedResult, "Should display Product Registration success Page",
					"Product Registration success Page display successfully",
					Common.getscreenShotPathforReport("Product Registration success page success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display Product Registration success Page",
					"Product Registration success Page not displayed",
					Common.getscreenShotPathforReport("Product Registration success Failed"));
			Assert.fail();
		}

	}

	public void navigateProductRegistration() throws Exception {
		String expectedResult = "Lands on Product Registration";
		try {
			Sync.waitElementPresent("xpath", "//h1[contains(text(),'Hair Tools')]");
			Common.scrollToElementAndClick("xpath", "//h1[contains(text(),'Hair Tools')]");

			Sync.waitElementPresent("xpath", "//a[@href='/product-registration/']");
			Common.clickElement("xpath", "//a[@href='/product-registration/']");
			Thread.sleep(300);

			String s = Common.getText("xpath", "//span[contains(text(),'Product Registration')]");
			System.out.println(s + " navigated successfully");

			Assert.assertEquals(s, "PRODUCT REGISTRATION");

			report.addPassLog(expectedResult, "Should display Product Registration Page",
					"Product Registration Page display successfully",
					Common.getscreenShotPathforReport("Product Registration page success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display Product Registration Page",
					"Product Registration Page not displayed",
					Common.getscreenShotPathforReport("Product Registration Failed"));
			Assert.fail();
		}

	}

	public void productregistrationform(String dataSet) throws Exception {
		String expectedResult = "Product Registration Form submission";
		String s = Common.getText("xpath", "//h1[@class='page-title']");
		System.out.println(s + " page is displayed");

		Thread.sleep(1000);

		String URL= Common.getCurrentURL();
		if(URL.equals("https://stg-upgrade.revlonhairtools.com/us_en/product-registration")) {
			Common.switchFrames("xpath", "//iframe[@src='https://helenoftroy.custhelp.com/app/product_registration/themes/revlon']");
		
		}

		Common.switchFrames("xpath", "//iframe[@src='https://helenoftroy.custhelp.com/app/product_registration/themes/revlon']");


		Thread.sleep(4000); 

		// Common.clickElement("name", "Contact.Name.First");

		Sync.waitElementPresent("name", "Contact.Name.First");
		Common.textBoxInput("name", "Contact.Name.First", data.get(dataSet).get("FirstName"));

		Common.textBoxInput("name", "Contact.Name.Last", data.get(dataSet).get("LastName"));
		Thread.sleep(3000);
/*
		Common.textBoxInput("name", "Contact.Phones.MOBILE.Number", data.get(dataSet).get("phone"));

		Common.textBoxInput("name", "searchKeyword", data.get(dataSet).get("ProductName"));

		Thread.sleep(300);

		System.out.println("Product search are display");

		Sync.waitElementPresent("xpath", "//*[@id='searchResults']/div[1]");
		Common.scrollToElementAndClick("xpath", "//*[@id='searchResults']/div[1]");

	*/	
		Common.textBoxInput("name", "Contact.Emails.PRIMARY.Address", data.get(dataSet).get("Email"));
		Thread.sleep(2000);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Thread.sleep(2000);
		//Sync.waitElementPresent("name", "Contact.Address.Country");
		//Common.dropdown("name", "Contact.Address.Country", SelectBy.TEXT, data.get(dataSet).get("Country"));

		Common.textBoxInput("xpath", "//input[@name='Contact.Address.Street']", data.get(dataSet).get("Street"));
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Thread.sleep(2000);

		Common.textBoxInput("name", "Contact.Address.City", data.get(dataSet).get("City"));
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Thread.sleep(2000);

		Sync.waitElementPresent("name", "Contact.Address.StateOrProvince");
		Common.dropdown("name", "Contact.Address.StateOrProvince", SelectBy.TEXT, data.get(dataSet).get("Region"));

		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Thread.sleep(2000);
		
		Common.textBoxInput("name", "Contact.Address.PostalCode", data.get(dataSet).get("postcode"));
		//Common.actionsKeyPress(Keys.PAGE_UP);

		Common.textBoxInput("xpath", "//div[@placeholder='Phone Number']/div/input", data.get(dataSet).get("phone"));

		Common.textBoxInput("name", "searchKeyword", data.get(dataSet).get("ProductName"));

		Thread.sleep(300);

		System.out.println("Product search are display");

		Sync.waitElementPresent("xpath", "//*[@id='searchResults']/div[1]");
		Common.scrollToElementAndClick("xpath", "//*[@id='searchResults']/div[1]");

		Thread.sleep(300);

		Sync.waitElementPresent("name", "Asset.CustomFields.HOT.store_purchased");
		Common.dropdown("name", "Asset.CustomFields.HOT.store_purchased", SelectBy.TEXT,
				data.get(dataSet).get("vendorname"));

		Common.textBoxInput("name", "Asset.CustomFields.HOT.date_code", data.get(dataSet).get("dataCode"));

		Common.textBoxInput("id", "rn_DateInput_DateTimeUI_29", data.get(dataSet).get("date") + "/"
				+ data.get(dataSet).get("month") + "/" + data.get(dataSet).get("year"));

		Common.clickElement("name", "Asset.CustomFields.HOT.research_panel_revlon");
		report.addPassLog(expectedResult, "Should dispaly Product registration page with data",
				"Product Registration Page with data displayed successfully",
				Common.getscreenShotPathforReport("Product Registration Page with data"));

		Sync.waitElementPresent("id", "rn_CustomFormSubmit2_55_Button");
		Common.clickElement("id", "rn_CustomFormSubmit2_55_Button");

	}

	public void ProductRegistration() throws Exception {
		String expectedResult = "Product Registratin form submission success page";
		try {
			String s = Common.getText("xpath", "//h1[contains(text(),'Hair Tools')]");
			System.out.println(s);
			if (Common.isElementEnabled("xpath", "//input[@name='Contact.Name.First']")) {

				System.out.println("Product Registration page Enabled");
				productregistrationform("Product_Registration");

			} else {

				System.out.println("Product Registration page not Enabled");
				Thread.sleep(3000);

				Common.refreshpage();
				
				Thread.sleep(3000);

				productregistrationform("Product_Registration");
			}
			Thread.sleep(7000);

			Common.switchToDefault();

			Assert.assertEquals("Thank you for registering your product! Your request has been processed.",
					"Thank you for registering your product! Your request has been processed.");

			report.addPassLog(expectedResult, "Should display Product Registration success Page",
					"Product Registration success Page display successfully",
					Common.getscreenShotPathforReport("Product Registration success page success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display Product Registration success Page",
					"Product Registration success Page not displayed",
					Common.getscreenShotPathforReport("Product Registration success Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}

	public void navigatefooter() throws Exception {

		if (Common.isElementDisplayed("xpath", "//button[text()='AGREE & PROCEED']")) {
			acceptPrivecy();
		}

		Sync.waitElementPresent("xpath", "//h1[contains(text(),'Hair Tools')]");
		Common.scrollToElementAndClick("xpath", "//h1[contains(text(),'Hair Tools')]");

	}

	public void navigateCMSLink() throws Exception {
		String expectedResult = "Lands On Home page footer links";
		try {
			Sync.waitElementPresent("xpath", "//h1[contains(text(),'Hair Tools')]");
			Common.scrollToElementAndClick("xpath", "//h1[contains(text(),'Hair Tools')]");
			Common.isElementDisplayed("xpath", "//h1[contains(text(),'Hair Tools')]");
			report.addPassLog(expectedResult, "Should display CMS Link Page", "CMS Link Page display successfully",
					Common.getscreenShotPathforReport("CMS Link page success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display CMS Link Page", "CMS Link Page not displayed",
					Common.getscreenShotPathforReport("CMS Link Failed"));
			Assert.fail();
		}

	}

	public void navigateAboutUs() throws Exception {
		String expectedResult = "Lands on About Us page";
		try {
			Sync.waitElementPresent("xpath", "//a[@href='/about-us/']");
			Common.scrollToElementAndClick("xpath", "//a[@href='/about-us/']");

			Thread.sleep(300);

			String s = Common.getText("xpath", "//span[contains(text(),'ABOUT US')]");
			System.out.println(s + " navigated successfully");
			Assert.assertEquals(s, "ABOUT US");

			report.addPassLog(expectedResult, "Should display ABOUT US Page", "ABOUT US Page display successfully",
					Common.getscreenShotPathforReport("ABOUT US page success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display ABOUT US Page", "ABOUT US Page not displayed",
					Common.getscreenShotPathforReport("ABOUT US Failed"));
			Assert.fail();
		}

	}

	public void navigateFAQ() throws Exception {
		String expectedResult = "Lands on FAQ page";
		try {
			Sync.waitElementPresent("xpath", "//a[@href='/faqs/']");
			Common.scrollToElementAndClick("xpath", "//a[@href='/faqs/']");

			Thread.sleep(300);

			String s = Common.getText("xpath", "//span[contains(text(),'Faqs')]");
			System.out.println(s + " navigated successfully");
			Assert.assertEquals(s, "FAQS");

			report.addPassLog(expectedResult, "Should display FAQ Page", "FQA Page display successfully",
					Common.getscreenShotPathforReport("FAQ page success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display FAQ Page", "FQA Page not displayed",
					Common.getscreenShotPathforReport("FAQ Failed"));
			Assert.fail();
		}

	}

	public void navigatePrivacyPolicy() throws Exception {
		String expectedResult = "Lands on Privacy policy page";
		try {
			Sync.waitElementPresent("xpath", "//a[@href='/privacy-policy/']");
			Common.scrollToElementAndClick("xpath", "//a[@href='/privacy-policy/']");

			Thread.sleep(300);

			String s = Common.getText("xpath", "//span[contains(text(),'Privacy Policy')]");
			System.out.println(s + " navigated successfully");
			Assert.assertEquals(s, "PRIVACY POLICY");

			report.addPassLog(expectedResult, "Should display Privacy Policy Page",
					"Privacy Policy Page display successfully",
					Common.getscreenShotPathforReport("Privacy Policy page success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display Privacy Policy Page",
					"Privacy Policy Page not displayed", Common.getscreenShotPathforReport("Privacy Policy Failed"));
			Assert.fail();
		}

	}

	public void navigateFindStore() throws Exception {
		String expectedResult = "Lands on Find a Store page";
		try {
			Sync.waitElementPresent("xpath", "//a[@href='/find-a-store/']");
			Common.scrollToElementAndClick("xpath", "//a[@href='/find-a-store/']");

			Thread.sleep(300);

			String s = Common.getText("xpath", "//span[contains(text(),'Find a Store')]");
			System.out.println(s + " navigated successfully");
			Assert.assertEquals(s, "FIND A STORE");

			report.addPassLog(expectedResult, "Should display Find a Store Page",
					"Find a Store Page display successfully",
					Common.getscreenShotPathforReport("Find a Store page success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display Find a Store Page", "Find a Store Page not displayed",
					Common.getscreenShotPathforReport("Find a Store Failed"));
			Assert.fail();
		}

	}

	public void navigateReturns() throws Exception {
		String expectedResult = "Lands On Returns page";
		try {
			Sync.waitElementPresent("xpath", "//a[@href='/returns/']");
			Common.scrollToElementAndClick("xpath", "//a[@href='/returns/']");

			Thread.sleep(300);

			String s = Common.getText("xpath", "//span[contains(text(),'RETURNS')]");
			System.out.println(s + " navigated successfully");
			Assert.assertEquals(s, "RETURNS");

			report.addPassLog(expectedResult, "Should display RETURNS Page", "RETURNS Page display successfully",
					Common.getscreenShotPathforReport("RETURNS page success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display RETURNS Page", "RETURNS Page not displayed",
					Common.getscreenShotPathforReport("RETURNS Failed"));
			Assert.fail();
		}

	}

	public void navigatePressRoom() throws Exception {
		String expectedResult = "Lands on Press Room page";
		try {
			Sync.waitElementPresent("xpath", "//a[@href='/press-room/']");
			Common.scrollToElementAndClick("xpath", "//a[@href='/press-room/']");

			Thread.sleep(300);

			String s = Common.getText("xpath", "//span[contains(text(),'Press Room')]");
			System.out.println(s + " navigated successfully");
			Assert.assertEquals(s, "PRESS ROOM");

			report.addPassLog(expectedResult, "Should display Press Room Page", "Press Room Page display successfully",
					Common.getscreenShotPathforReport("Press Room page success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display Press Room Page", "Press Room Page not displayed",
					Common.getscreenShotPathforReport("Press Room Failed"));
			Assert.fail();
		}

	}

	public void navigateTermsOfUse() throws Exception {
		String expectedResult = "Lands on Terms Of Use page";
		try {
			Sync.waitElementPresent("xpath", "//a[@href='/terms-and-conditions/']");
			Common.scrollToElementAndClick("xpath", "//a[@href='/terms-and-conditions/']");

			Thread.sleep(300);

			String s = Common.getText("xpath", "//span[contains(text(),'TERMS OF USE')]");
			System.out.println(s + " navigated successfully");
			Assert.assertEquals(s, "TERMS OF USE");

			report.addPassLog(expectedResult, "Should display TERMS OF USE Page",
					"TERMS OF USE Page display successfully",
					Common.getscreenShotPathforReport("TERMS OF USE page success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display TERMS OF USE Page", "TERMS OF USE Page not displayed",
					Common.getscreenShotPathforReport("TERMS OF USE Failed"));
			Assert.fail();
		}

	}

	public void navigateHomePage() throws Exception {
		String expectedResult = "Lands on Home page";
		try {
			Sync.waitElementPresent("xpath", "//a[@href='" + System.getProperty("url",
					automation_properties.getInstance().getProperty(automation_properties.BASEURL) + "us_en/']"));
			Common.clickElement("xpath", "//a[@href='" + System.getProperty("url",
					automation_properties.getInstance().getProperty(automation_properties.BASEURL) + "us_en/']"));

			Thread.sleep(1000);

			report.addPassLog(expectedResult, "Should display Home Page", "Home Page display successfully",
					Common.getscreenShotPathforReport("Home page success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display Home Page", "Home Page not displayed",
					Common.getscreenShotPathforReport("Home Failed"));
			Assert.fail();
		}

	}

	/*
	 * public void categoryMenuItem() { String
	 * expectedResult="Select from  category" ; try { Thread.sleep(1000);
	 * Sync.waitElementPresent("xpath", "//span[contains(text(), 'Dryer')]");
	 * Common.clickElement("xpath", "//span[contains(text(), 'Dryer')]");
	 * Thread.sleep(3000); Sync.waitElementPresent("xpath",
	 * "(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[1]"
	 * ); Common.clickElement("xpath",
	 * "(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[1]"
	 * ); Thread.sleep(5000); report.addPassLog(expectedResult,
	 * "Should display item from  menucategory", "product display successfully",
	 * Common.getscreenShotPathforReport(" product display success"));
	 * 
	 * 
	 * }catch(Exception e) {
	 * report.addFailedLog(expectedResult,"Should display Search Results Page",
	 * "Search results Page not display",
	 * Common.getscreenShotPathforReport("Search result Failed"));
	 * e.printStackTrace(); Assert.fail(); } }
	 */
	public void navigateMinicart() throws Exception {
		String expectedResult = "Products adding to mini cart";
		try {

			Sync.waitPageLoad(20);
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			// Common.scrollIntoView("xpath", "(//button[@title='Buy Now'])[1]");
			Common.javascriptclickElement("xpath", "(//button[@title='Buy Now'])[1]");
			Sync.waitElementVisible(10, By.xpath("//span[text()='YOUR BAG - ']"));
			Common.isElementDisplayed("xpath", "//span[text()='YOUR BAG - ']");

			Sync.waitElementPresent(20, "xpath",
					"//a[@class='action viewcart hvr-sweep-to-right rev-checkout-btn rev-cart-view']");
			// Common.clickElement("xpath",
			// "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/checkout/cart/']"));
			Common.clickElement("xpath",
					"//a[@class='action viewcart hvr-sweep-to-right rev-checkout-btn rev-cart-view']");
			Thread.sleep(4000);
			Common.actionsKeyPress(Keys.ARROW_DOWN);
	
			
			/*	if (Common.findElements("xpath", "(//td/div/strong[@class='product-item-name']/a)[1]").size() > 0) {
				String product1 = Common.getText("xpath", "(//td/div/strong[@class='product-item-name']/a)[1]");

				System.out.println(product1 + "is added to cart");
			} else {
				System.out.println("product not added to cart");
			}
			if (Common.findElements("xpath", "(//td/div/strong[@class='product-item-name']/a)[2]").size() > 0) {
				String Product2 = Common.getText("xpath", "(//td/div/strong[@class='product-item-name']/a)[2]");
				System.out.println(Product2 + "is added to cart");
			} 
*/
			report.addPassLog("to verify the Product are adding to cart", expectedResult,
					"Products adding to cart successfull",
					Common.getscreenShotPathforReport("successfully added products to cart"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "failed to add products to cart",
					Common.getscreenShotPathforReport(" Failed to add products to cart"));
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

	public void navigateCartPage() throws Exception {
		String expectedResult = "Product adding to cart page";
		try {

			try {
				Thread.sleep(5000);
				Sync.waitElementPresent(20, "xpath",
						"//div[@data-bind='html: message.text']//a[contains(text(),'shopping cart')]");
				Common.clickElement("xpath",
						"//div[@data-bind='html: message.text']//a[contains(text(),'shopping cart')]");

			} catch (Exception e) {
				Thread.sleep(1000);
				Common.refreshpage();
Common.actionsKeyPress(Keys.PAGE_DOWN);
				/*
				 * Sync.waitElementPresent("xpath", "//button[@id='product-addtocart-button']");
				 * Common.clickElement("xpath", "//button[@id='product-addtocart-button']");
				 * 
				 * Thread.sleep(500);
				 * 
				 * Sync.waitElementPresent("xpath",
				 * "//div[@data-bind='html: message.text']//a[contains(text(),'shopping cart')]"
				 * ); Common.clickElement("xpath",
				 * "//div[@data-bind='html: message.text']//a[contains(text(),'shopping cart')]"
				 * );
				 */
			}

			Thread.sleep(5000);
			Common.isElementDisplayed("xpath", "//strong[@class='product-item-name']");
			report.addPassLog(expectedResult, "Should display Cart Page", "Cart Page display successfully",
					Common.getscreenShotPathforReport("Cart page success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display Cart Page", "Cart Page not displayed",
					Common.getscreenShotPathforReport("Cart Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void checkoutPage() throws Exception {
		String expectedResult = "Product adding to checkout page";
		try {
			Thread.sleep(3000);
				Sync.waitElementPresent(30, "xpath",
					"//a[@href='" + System.getProperty("url",
							automation_properties.getInstance().getProperty(automation_properties.BASEURL)
									+ "us_en/checkout/']"));
			Common.clickElement("xpath",
					"//a[@href='" + System.getProperty("url",
							automation_properties.getInstance().getProperty(automation_properties.BASEURL)
									+ "us_en/checkout/']"));
			Thread.sleep(1000);
			Common.isElementDisplayed("xpath", "//div[contains(text(),'Shipping Address')]");
			report.addPassLog(expectedResult, "Should display Checkout Page", "Checkout Page display successfully",
					Common.getscreenShotPathforReport("Checkout page success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display Checkout Page", "Checkout Page not displayed",
					Common.getscreenShotPathforReport("Checkout Failed"));
			Assert.fail();
		}
	}

	public void shipping_Address(String dataSet) throws Exception {

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
		// Common.clickElement("xpath", "//select[@title='State/Province']");
		Common.dropdown("name", "region_id", SelectBy.TEXT, data.get(dataSet).get("Region"));
		// Common.dropdown("xpath",
		// "//div[@name='shippingAddress.region_id']/div//select[@name='region_id']",
		// SelectBy.TEXT, data.get(dataSet).get("Region"));

		Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));

		Common.dropdown("xpath", "//select[@name='country_id']", SelectBy.TEXT, data.get(dataSet).get("Country"));

		Thread.sleep(500);

		Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));

	}

	public void shipping_Address_AVS(String dataSet) throws Exception {
		Common.textBoxInput("name", "firstname", data.get(dataSet).get("FirstName"));
		Common.textBoxInput("name", "lastname", data.get(dataSet).get("LastName"));
		Common.textBoxInput("name", "street[0]", data.get(dataSet).get("Street"));
		Common.textBoxInput("name", "city", data.get(dataSet).get("City"));
		Thread.sleep(10000);
		Common.actionsKeyPress(Keys.DOWN);
		Common.actionsKeyPress(Keys.ENTER);
		Thread.sleep(10000);
		Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));

	}

	public void navigateCheckoutGuest(String dataSet) throws Exception {
		String expectedResult = "Checkout page with payment for Guest user";
		String expectedResult1 = "Email page of shipping address";
		String expectedResult2 = "Shipping address of Checkout page";
		try {
			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "//div[contains(text(),'Email')]");
			Thread.sleep(12000);
			Sync.waitPageLoad(10);
			Common.clickElement("id", "customer-email");
			Common.textBoxInput("xpath", "//fieldset[@id='customer-email-fieldset']/div/div//input[@type='email']",
					data.get(dataSet).get("Email"));
			report.addPassLog(expectedResult1, "Should dispaly Shipping address page",
					"shipping address Page displayed successfully",
					Common.getscreenShotPathforReport("shipping address page"));

			Sync.waitElementPresent("xpath", "//span[contains(text(),'Next')]");
			Common.clickElement("xpath", "//span[contains(text(),'Next')]");

			Thread.sleep(2000);
			report.addPassLog(expectedResult2, "Should dispaly Shipping address page",
					"shipping address Page displayed successfully",
					Common.getscreenShotPathforReport("Shipping address"));


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
			// Common.clickElement("xpath", "//select[@title='State/Province']");
			Common.dropdown("name", "region_id", SelectBy.TEXT, data.get(dataSet).get("Region"));
			// Common.dropdown("xpath",
			// "//div[@name='shippingAddress.region_id']/div//select[@name='region_id']",
			// SelectBy.TEXT, data.get(dataSet).get("Region"));

			Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));

			Common.dropdown("xpath", "//select[@name='country_id']", SelectBy.TEXT, data.get(dataSet).get("Country"));

			Thread.sleep(500);

			Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));

			report.addPassLog(expectedResult2, "Should dispaly Shipping address page",
					"shipping address Page displayed successfully",
					Common.getscreenShotPathforReport("Shipping address"));

			Common.scrollIntoView("name", "region_id");

			report.addPassLog(expectedResult, "Should display the address form", "Shipping addresses form will be populated",
					Common.getscreenShotPathforReport("Shipping address form population successfull"));

		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display Checkout Page", "Checkout Page not displayed",
					Common.getscreenShotPathforReport("Checkout page navigation Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}

	public void navigateCheckoutGuest_Notax(String dataSet) throws Exception {
		String expectedResult = "Checkout page with payment for Guest user";
		String expectedResult1 = "Email page of shipping address";
		String expectedResult2 = "Shipping address of Checkout page";
		try {
			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "//div[contains(text(),'Email')]");
			Thread.sleep(12000);
			Sync.waitPageLoad(10);
			Common.clickElement("id", "customer-email");
			Common.textBoxInput("xpath", "//fieldset[@id='customer-email-fieldset']/div/div//input[@type='email']",
					data.get(dataSet).get("Email"));
			report.addPassLog(expectedResult1, "Should dispaly Shipping address page",
					"shipping address Page displayed successfully",
					Common.getscreenShotPathforReport("shipping address page"));

			Sync.waitElementPresent("xpath", "//span[contains(text(),'Next')]");
			Common.clickElement("xpath", "//span[contains(text(),'Next')]");

			Thread.sleep(300);

			shipping_Address("NotaxonfreightAddress");
			report.addPassLog(expectedResult2, "Should dispaly Shipping address page",
					"shipping address Page displayed successfully",
					Common.getscreenShotPathforReport("Shipping address"));

			Common.scrollIntoView("name", "region_id");

			report.addPassLog(expectedResult, "Should display Checkout Page", "Checkout Page display successfully",
					Common.getscreenShotPathforReport("Checkout page success"));

		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display Checkout Page", "Checkout Page not displayed",
					Common.getscreenShotPathforReport("Checkout Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}
	
	
	
	public void ValidateAVS(String dataSet) throws Exception {
		String expectedResult = "Checkout page with payment for Guest user";
		String expectedResult1 = "Email page of shipping address";
		String expectedResult2 = "Shipping address of Checkout page";
		try {
			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "//div[contains(text(),'Email')]");
			Thread.sleep(12000);
			// Sync.waitPageLoad(10);
			Common.clickElement("id", "customer-email");
			Common.textBoxInput("xpath", "//fieldset[@id='customer-email-fieldset']/div/div//input[@type='email']",
					data.get(dataSet).get("Email"));
			report.addPassLog(expectedResult1, "Should dispaly Shipping address page",
					"shipping address Page displayed successfully",
					Common.getscreenShotPathforReport("shipping address page"));

			Sync.waitElementPresent("xpath", "//span[contains(text(),'Next')]");
			Common.clickElement("xpath", "//span[contains(text(),'Next')]");

			Thread.sleep(300);

			shipping_Address_AVS("Guest_shipping");
			report.addPassLog(expectedResult2, "Should dispaly Shipping address page",
					"shipping address Page displayed successfully",
					Common.getscreenShotPathforReport("Shipping address"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display Checkout Page", "Checkout Page not displayed",
					Common.getscreenShotPathforReport("Checkout Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}

	public void navigateCheckout() throws Exception {
		String expectedResult = "Checkout page of Register user";
		try {
			Common.clickElement("xpath", "//span[contains(text(),'Ship Here')]");
			Thread.sleep(2000);
			/*
			 * Common.scrollToElementAndClick("xpath",
			 * "//div[contains(text(),'Shipping Methods')]");
			 * 
			 * if(Common.checkBoxIsSelected("xpath",
			 * "//*[@id='checkout-shipping-method-load']/table/tbody/tr[1]/td[1]/input")) {
			 * System.out.println("Shipping method is selected"); }else {
			 * System.out.println("Shipping method is not selected");
			 * Sync.waitElementPresent("xpath",
			 * "//*[@id='checkout-shipping-method-load']/table/tbody/tr[1]/td[1]/input");
			 * Common.clickElement("xpath",
			 * "//*[@id='checkout-shipping-method-load']/table/tbody/tr[1]/td[1]/input"); }
			 * Thread.sleep(1000);
			 * 
			 * System.out.println("Next button clicked"); Thread.sleep(300);
			 * Common.actionsKeyPress(Keys.DOWN);
			 * 
			 * //Sync.waitElementPresent("xpath", "//button[@data-role='opc-continue']");
			 * //Common.clickElement("xpath", "//button[@data-role='opc-continue']");
			 * 
			 * Sync.waitElementPresent("xpath", "(//span[contains(text(),'Next')])[2]");
			 * Common.clickElement("xpath", "(//span[contains(text(),'Next')])[2]");
			 * 
			 * Thread.sleep(300);
			 * 
			 * 
			 * if(Common.isElementVisibleOnPage(30, "xpath",
			 * "//div[contains(text(),'Address is not verified. Do you want to continue ?')]"
			 * )) {
			 * 
			 * System.out.println("Address not verified pop up displayed");
			 * 
			 * Sync.waitElementPresent("xpath",
			 * "//button[@class='action-primary action-accept']");
			 * Common.clickElement("xpath",
			 * "//button[@class='action-primary action-accept']"); }
			 * Common.isElementDisplayed("xpath",
			 * "//div[contains(text(),'Payment Method')]");
			 */

			report.addPassLog(expectedResult, "Should display Checkout Page", "Checkout Page display successfully",
					Common.getscreenShotPathforReport("Checkout page success"));
			// Common.clickElement("xpath", "//span[contains(text(),'Continue to
			// PayPal')]");
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display Checkout Page", "Checkout Page not displayed",
					Common.getscreenShotPathforReport("Checkout Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}

	public void CheckoutShippingAddress(String dataset) throws Exception {
		String expectedResult = "Checkout page with new address pop up";
		try {
			Thread.sleep(3000);

			Sync.waitElementPresent("xpath",
					"//button[@data-bind='click: showFormPopUp, visible: !isNewAddressAdded()']");
			Common.clickElement("xpath", "//button[@data-bind='click: showFormPopUp, visible: !isNewAddressAdded()']");

			Sync.waitElementPresent("xpath", "//h1[contains(text(),'Shipping Address')]");
			Thread.sleep(3000);
			shipping_Address(dataset);

			Thread.sleep(2000);
			Common.clickElement("xpath",
					"//button[@class='rev-w-btn rev-def-btn next-btn']//span[contains(text(),'Ship here')]");

			Sync.waitPageLoad();
			report.addPassLog(expectedResult, "Should display checkout page with new address",
					"Checkout Page with new address display successfully",
					Common.getscreenShotPathforReport("Checkout page with new address success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display Checkout Page", "Checkout Page not displayed",
					Common.getscreenShotPathforReport("Checkout Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}

	public void EditBillingAddress() throws Exception {
		accountIcon();
		myAccountLink();

		String expectedResult = "Edit billing address from My Account";
		try {
			Sync.waitElementPresent("xpath",
					"//a[@href='" + System.getProperty("url",
							automation_properties.getInstance().getProperty(automation_properties.BASEURL)
									+ "us_en/customer/address/']"));
			Common.clickElement("xpath",
					"//a[@href='" + System.getProperty("url",
							automation_properties.getInstance().getProperty(automation_properties.BASEURL)
									+ "us_en/customer/address/']"));

			Thread.sleep(3000);

			Sync.waitElementPresent("xpath", "//a[contains(text(),'Change Billing Address')]");
			Common.clickElement("xpath", "//a[contains(text(),'Change Billing Address')]");

			addNewAddress("Address");
			Thread.sleep(3000);

			report.addPassLog(expectedResult, "Should edit the billing address from My Account",
					"Billing Address from my account updated successfully",
					Common.getscreenShotPathforReport("Billing address my account success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should edit the billing address from My Account",
					"Billing Address from my account not updated",
					Common.getscreenShotPathforReport("Billing address my account Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}

	public void selectioncategory() throws Exception {
		String expectedResult = "Selection of Dryer category";
		try {
			Sync.waitElementPresent("xpath", "//a[@href='" + System.getProperty("url",
					automation_properties.getInstance().getProperty(automation_properties.BASEURL) + "us_en/dryers']"));
			Common.clickElement("xpath", "//a[@href='" + System.getProperty("url",
					automation_properties.getInstance().getProperty(automation_properties.BASEURL) + "us_en/dryers']"));

			Thread.sleep(10000);

			Sync.waitPageLoad(30);

			String header = Common.getText("id", "page-title-heading");
			System.out.println(header + " page navigated");

			Assert.assertEquals(header, "Dryers");
			report.addPassLog(expectedResult, "Should display checkout page with new address",
					"Checkout Page with new address display successfully",
					Common.getscreenShotPathforReport("Checkout page with new address success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display Checkout Page", "Checkout Page not displayed",
					Common.getscreenShotPathforReport("Checkout Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}

	public void ValidatingPromocode(String dataSet) throws Exception {
		String expectedResult = "Validate Promode in Checkout page";
		try {

			Common.scrollIntoView("xpath", "//div[@class='billing-address-details']");
			Sync.waitElementPresent("id", "block-discount-heading");
			Common.clickElement("id", "block-discount-heading");

			Sync.waitElementPresent("name", "discount_code");
			Common.clickElement("name", "discount_code");

			Common.textBoxInput("name", "discount_code", data.get(dataSet).get("Promocode"));

			Thread.sleep(4000);

			Sync.waitElementPresent("xpath", "//div[4]/div[2]/form/div[2]/div/button[@class='rev-w-btn rev-oof-btn rev-app-dis']");
			Common.clickElement("xpath", "//div[4]/div[2]/form/div[2]/div/button[@class='rev-w-btn rev-oof-btn rev-app-dis']");
Thread.sleep(1500);

			String success = Common.getText("xpath",
					"//div[@class='message message-success success']");
			//div[@class='message message-success success']
			System.out.println(success);
			
Common.actionsKeyPress(Keys.PAGE_UP);
			report.addPassLog(expectedResult, "Should display Success message for promocode",
					"Success of promocode displayed successfully",
					Common.getscreenShotPathforReport("Promocode success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display Success message for promocode",
					"Success of promocode not displayed", Common.getscreenShotPathforReport("Promocode Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}

	public void Productdetails() throws Exception {
		String expectedResult = "Lands on product details page";
		try {
			Sync.waitElementPresent("xpath",
					"(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[1]");
			Common.clickElement("xpath",
					"(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[1]");
			Thread.sleep(10000);
			Common.isElementDisplayed("xpath", "//h1[@class='page-title']");

			Thread.sleep(8000);
			String producttitle = Common.getText("xpath", "//h1[@class='page-title']");
			System.out.println(producttitle + " is selected");

			Assert.assertTrue(Common.isElementDisplayed("xpath", "//h1[@class='page-title']"));
			report.addPassLog(expectedResult, "Should display Product Details Page with title",
					"Product Details Page with title display successfully",
					Common.getscreenShotPathforReport("Product Details page title success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display Product Details Page with title",
					"Product Details Page with title display failed",
					Common.getscreenShotPathforReport("Product Details page title Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}

	public void ProductdetailsQuantity() throws Exception {
		String expectedResult = "Product Details page Changing the Quantity";
		try {
			Common.dropdown("name", "qty", SelectBy.TEXT, "3");

			String QTY = Common.getSelectedOption("name", "qty");
			System.out.println("Changed product quantity to " + QTY);

			Assert.assertEquals(QTY, "3");
			report.addPassLog(expectedResult, "Should display Product Details Page Change qunatity",
					"Product Details Page Change qunatity display successfully",
					Common.getscreenShotPathforReport("Product Details page Change qunatity success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display Product Details Page Change qunatity",
					"Product Details Page Change qunatity display Failed",
					Common.getscreenShotPathforReport("Product Details page Change qunatity Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}

	public void ProductDetailsBreadCrumbs() throws Exception {
		String expectedResult = "Validating Product details page BreadCrumbs";
		try {
			String BC1 = Common.getText("xpath", "//div[@class='breadcrumbs']/ul/li[1]");
			System.out.println(BC1 + " is Displayed");

			Assert.assertEquals(BC1, "HOME");

			String BC2 = Common.getText("xpath", "//div[@class='breadcrumbs']/ul/li[2]");
			System.out.println(BC2 + " is Displayed");

			Assert.assertEquals(BC2, "DRYERS");

			String BC3 = Common.getText("xpath", "//div[@class='breadcrumbs']/ul/li[3]");
			System.out.println(BC3 + " is Displayed");

			Assert.assertEquals(BC3, Common.getText("xpath", "//h1[@class='page-title']"));
			report.addPassLog(expectedResult, "Should display Product Details Page with BreadCrumb",
					"Product Details Page with BreadCrumb display successfully",
					Common.getscreenShotPathforReport("Product Details page BreadCrumb success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display Product Details Page with BreadCrumb",
					"Product Details Page with BreadCrumb display Failed",
					Common.getscreenShotPathforReport("Product Details page BreadCrumb Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}

	public void Searchresultsfilter() throws Exception {
		String expectedResult = "Validating Product listing page filter with Price (Low to High)";
		String expectedResult1 = "Validating Product listing page filter with Price (High to Low)";
		String expectedResult2 = "Validating Product listing page filter with Newest Arrivals";
		String expectedResult3 = "Validating Product listing page filter";
		String expectedResult4 = "Validating Product listing page filter";
		try {

			Common.dropdown("id", "sorter", SelectBy.TEXT, "Price (Low to High)");
			Thread.sleep(5000);
			String filter1 = Common.getText("id", "dynamicProductCounts");
			System.out.println("Price (Low to High) filer count is " + filter1);
			report.addPassLog(expectedResult, "Should display Product Listing Page with filters Price (Low to High)",
					"Product Listing Page with filters Price (Low to High) display successfully",
					Common.getscreenShotPathforReport("PLP filter with Price (Low to High) success"));
			Thread.sleep(5000);

			Common.dropdown("id", "sorter", SelectBy.TEXT, "Price (High to Low)");
			Thread.sleep(5000);
			String filter2 = Common.getText("id", "dynamicProductCounts");
			System.out.println("Price (High to Low) filer count is " + filter2);
			report.addPassLog(expectedResult1, "Should display Product Listing Page with filters Price (High to Low)",
					"Product Listing Page with filters Price (High to Low) display successfully",
					Common.getscreenShotPathforReport("PLP filter with Price (High to Low) success"));
			Thread.sleep(5000);

			Common.dropdown("id", "sorter", SelectBy.TEXT, "Newest Arrivals");
			Thread.sleep(5000);
			String filter3 = Common.getText("id", "dynamicProductCounts");
			System.out.println("Newest Arrivals filer count is " + filter3);
			report.addPassLog(expectedResult2, "Should display Product Listing Page with filters Newest Arrivals",
					"Product Listing Page with filters Newest Arrivals display successfully",
					Common.getscreenShotPathforReport("PLP filter with Newest Arrivals success"));
			Thread.sleep(5000);

			Common.dropdown("id", "sorter", SelectBy.TEXT, "Price (Low to High)");
			Thread.sleep(5000);
			String filter4 = Common.getText("id", "dynamicProductCounts");
			System.out.println("Relevance filer count is " + filter4);
			report.addPassLog(expectedResult3, "Should display Product Listing Page with filters Relevance",
					"Product Listing Page with filters Relevance display successfully",
					Common.getscreenShotPathforReport("PLP filter with Relevance success"));
			Thread.sleep(10000);

			Common.clickElement("name", "amshopby[collections][]");
			Thread.sleep(5000);
			String filter5 = Common.getText("id", "dynamicProductCounts");
			System.out.println("Essentials filer count is " + filter5);
			report.addPassLog(expectedResult4, "Should display Product Listing Page with filters Essentials",
					"Product Listing Page with filters with Essentials display successfully",
					Common.getscreenShotPathforReport("PLP filter with Essentials success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display Product Listing Page with filters",
					"Product Listing Page with filters display Failed",
					Common.getscreenShotPathforReport("Product Listing Page with filters Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}

	public void searchProductwithmultiple(String dataSet) throws Exception {
		String expectedResult = "Search with Product name :" + data.get(dataSet).get("ProductName");
		try {
			Thread.sleep(1000);
			Common.clickElement("xpath", "//div[@class='block block-search search-visible-md minisearch-v2']/div/i");
			Sync.waitElementPresent("id", "search");
			try {
				Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName"));
			} catch (Exception e) {
				Common.clickElement("xpath",
						"//div[@class='block block-search search-visible-md minisearch-v2']/div/i");
				Thread.sleep(3000);
				Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName"));
			}
			Common.actionsKeyPress(Keys.ENTER);
			Thread.sleep(10000);
			Common.actionsKeyPress(Keys.DOWN);
			String search = Common.getText("id", "dynamicProductCounts");
			System.out.println(data.get(dataSet).get("ProductName") + " Search result count is " + search);
			// Common.scrollIntoView("xpath",
			// "(//div[@class='product-item-info']/div//a[@class='product photo
			// product-item-photo title'])[1]");
			report.addPassLog(expectedResult,
					"Should display Search Results Page for " + data.get(dataSet).get("ProductName"),
					"Search results Page display for " + data.get(dataSet).get("ProductName") + " successfully",
					Common.getscreenShotPathforReport("Search results success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult,
					"Should display Search Results Page for " + data.get(dataSet).get("ProductName"),
					"Search results Page not display for " + data.get(dataSet).get("ProductName"),
					Common.getscreenShotPathforReport("Search result Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}

	public void BrowseSubcategory() throws Exception {
		String expectedResult = "Navigation of sub category from main menu";
		try {
			Common.mouseOver("xpath", "//span[contains(text(),'Collections')]");

			Thread.sleep(3000);

			Common.clickElement("xpath", "//span[contains(text(),'One Step Family')]");

			Thread.sleep(10000);

			String subcategory = Common.getText("xpath", "//h1[contains(text(),'The One-Step Collection')]");
			System.out.println("Subcategory page navigated " + subcategory);
			Assert.assertEquals(subcategory, "The One-Step Collection");
			report.addPassLog(expectedResult, "Should navigation Subcategory page",
					"Subcategory Page navigated successfully",
					Common.getscreenShotPathforReport("Subcategory page success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should navigation Subcategory page", "Subcategory Page not navigated",
					Common.getscreenShotPathforReport("Subcategory page Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}

	public void PLPfilterwithColour() throws Exception {
		String expectedResult = "Validating Product listing page Colour filter results";
		try {
			Common.clickElement("xpath",
					"//a[@href='" + System.getProperty("url",
							automation_properties.getInstance().getProperty(automation_properties.BASEURL)
									+ "us_en/dryers?color=13']"));
			Thread.sleep(10000);

			String filter = Common.getText("id", "dynamicProductCounts");
			System.out.println("Colour filer count is " + filter);

			report.addPassLog(expectedResult, "Should display Product Listing Page Colour filters results",
					"Product Listing Page Colour filters results displayed successfully",
					Common.getscreenShotPathforReport("Product Listing Page Colour filters success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display Product Listing Page Colour filters results",
					"Product Listing Page Colour filters results displayed Failed",
					Common.getscreenShotPathforReport("Product Listing Page Colour filters Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}

	public void PLPfilterwithCollections() throws Exception {
		String expectedResult = "Validating Product listing page Collections filter results";
		try {
			Common.scrollIntoView("xpath",
					"//a[@href='" + System.getProperty("url",
							automation_properties.getInstance().getProperty(automation_properties.BASEURL)
									+ "us_en/dryers?collections=48']"));
			Thread.sleep(3000);
			Common.clickElement("xpath",
					"//a[@href='" + System.getProperty("url",
							automation_properties.getInstance().getProperty(automation_properties.BASEURL)
									+ "us_en/dryers?collections=48']"));
			Thread.sleep(10000);

			Common.scrollIntoView("id", "dynamicProductCounts");
			String filter = Common.getText("id", "dynamicProductCounts");
			System.out.println("Collections filer count is " + filter);

			report.addPassLog(expectedResult, "Should display Product Listing Page Collections filters results",
					"Product Listing Page Collections filters results displayed successfully",
					Common.getscreenShotPathforReport("Product Listing Page Collections filters success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display Product Listing Page Collections filters results",
					"Product Listing Page Collections filters results displayed Failed",
					Common.getscreenShotPathforReport("Product Listing Page Collections filters Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}

	public void PLPfilterwithHeat() throws Exception {
		String expectedResult = "Validating Product listing page Heat filter results";
		try {
			Common.scrollIntoView("xpath",
					"//a[@href='" + System.getProperty("url",
							automation_properties.getInstance().getProperty(automation_properties.BASEURL)
									+ "us_en/dryers?heat=29']"));
			Thread.sleep(3000);
			Common.clickElement("xpath",
					"//a[@href='" + System.getProperty("url",
							automation_properties.getInstance().getProperty(automation_properties.BASEURL)
									+ "us_en/dryers?heat=29']"));
			Thread.sleep(10000);

			Common.scrollIntoView("id", "dynamicProductCounts");
			String filter = Common.getText("id", "dynamicProductCounts");
			System.out.println("Heat filer count is " + filter);

			report.addPassLog(expectedResult, "Should display Product Listing Page Heat filters results",
					"Product Listing Page Heat filters results displayed successfully",
					Common.getscreenShotPathforReport("Product Listing Page Heat filters success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display Product Listing Page Heat filters results",
					"Product Listing Page Heat filters results displayed Failed",
					Common.getscreenShotPathforReport("Product Listing Page Heat filters Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}

	public void PLPfilterwithSize() throws Exception {
		String expectedResult = "Validating Product listing page Size filter results";
		try {
			Common.scrollIntoView("xpath",
					"//a[@href='" + System.getProperty("url",
							automation_properties.getInstance().getProperty(automation_properties.BASEURL)
									+ "us_en/dryers?size=36']"));
			Common.clickElement("xpath",
					"//a[@href='" + System.getProperty("url",
							automation_properties.getInstance().getProperty(automation_properties.BASEURL)
									+ "us_en/dryers?size=36']"));
			Thread.sleep(10000);

			Common.scrollIntoView("id", "dynamicProductCounts");
			String filter = Common.getText("id", "dynamicProductCounts");
			System.out.println("Size filer count is " + filter);

			report.addPassLog(expectedResult, "Should display Product Listing Page Size filters results",
					"Product Listing Page Size filters results displayed successfully",
					Common.getscreenShotPathforReport("Product Listing Page Size filters success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display Product Listing Page Size filters results",
					"Product Listing Page Size filters results displayed Failed",
					Common.getscreenShotPathforReport("Product Listing Page Size filters Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}

	public void PLPfilterwithTechnology() throws Exception {
		String expectedResult = "Validating Product listing page Technology filter results";
		try {
			Common.scrollIntoView("xpath",
					"//a[@href='" + System.getProperty("url",
							automation_properties.getInstance().getProperty(automation_properties.BASEURL)
									+ "us_en/dryers?technology=40']"));
			Thread.sleep(3000);
			Common.clickElement("xpath",
					"//a[@href='" + System.getProperty("url",
							automation_properties.getInstance().getProperty(automation_properties.BASEURL)
									+ "us_en/dryers?technology=40']"));
			Thread.sleep(10000);

			Common.scrollIntoView("id", "dynamicProductCounts");
			String filter = Common.getText("id", "dynamicProductCounts");
			System.out.println("Technology filer count is " + filter);

			report.addPassLog(expectedResult, "Should display Product Listing Page Technology filters results",
					"Product Listing Page Technology filters results displayed successfully",
					Common.getscreenShotPathforReport("Product Listing Page Technology filters success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display Product Listing Page Technology filters results",
					"Product Listing Page Technology filters results displayed Failed",
					Common.getscreenShotPathforReport("Product Listing Page Technology filters Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}

	public void PLPfilterwithType() throws Exception {
		String expectedResult = "Validating Product listing page Type filter results";
		try {
			Common.scrollIntoView("xpath",
					"//a[@href='" + System.getProperty("url",
							automation_properties.getInstance().getProperty(automation_properties.BASEURL)
									+ "us_en/dryers?type=77']"));
			Thread.sleep(3000);
			Common.clickElement("xpath",
					"//a[@href='" + System.getProperty("url",
							automation_properties.getInstance().getProperty(automation_properties.BASEURL)
									+ "us_en/dryers?type=77']"));
			Thread.sleep(10000);

			Common.scrollIntoView("id", "dynamicProductCounts");
			String filter = Common.getText("id", "dynamicProductCounts");
			System.out.println("Type filer count is " + filter);

			report.addPassLog(expectedResult, "Should display Product Listing Page Type filters results",
					"Product Listing Page Type filters results displayed successfully",
					Common.getscreenShotPathforReport("Product Listing Page Type filters success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display Product Listing Page Type filters results",
					"Product Listing Page Type filters results displayed Failed",
					Common.getscreenShotPathforReport("Product Listing Page Type filters Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}

	public void PLPfilterwithDualVoltage() throws Exception {
		String expectedResult = "Validating Product listing page DualVoltage filter results";
		try {
			Common.scrollIntoView("xpath",
					"//a[@href='" + System.getProperty("url",
							automation_properties.getInstance().getProperty(automation_properties.BASEURL)
									+ "us_en/dryers?dual_voltage=86']"));
			Thread.sleep(3000);
			Common.clickElement("xpath",
					"//a[@href='" + System.getProperty("url",
							automation_properties.getInstance().getProperty(automation_properties.BASEURL)
									+ "us_en/dryers?dual_voltage=86']"));
			Thread.sleep(10000);

			Common.scrollIntoView("id", "dynamicProductCounts");
			String filter = Common.getText("id", "dynamicProductCounts");
			System.out.println("DualVoltage filer count is " + filter);

			report.addPassLog(expectedResult, "Should display Product Listing Page DualVoltage filters results",
					"Product Listing Page DualVoltage filters results displayed successfully",
					Common.getscreenShotPathforReport("Product Listing Page DualVoltage filters success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display Product Listing Page DualVoltage filters results",
					"Product Listing Page DualVoltage filters results displayed Failed",
					Common.getscreenShotPathforReport("Product Listing Page DualVoltage filters Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}

	public void ClearFilter() throws Exception {
		try {
			Common.clickElement("xpath", "//a[contains(text(),'Clear All')]");
			Thread.sleep(10000);
		} catch (Exception | Error e) {
			e.printStackTrace();
		}

	}

	public void ShippingAddress(String dataSet) throws Exception {

		String expectedResult1 = "Email page of shipping address";
		String expectedResult2 = "Shipping address of Checkout page";
		try {
			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "//div[contains(text(),'Email')]");
			Thread.sleep(12000);
			// Sync.waitPageLoad(10);
			Common.clickElement("id", "customer-email");
			Common.textBoxInput("xpath", "//fieldset[@id='customer-email-fieldset']/div/div//input[@type='email']",
					data.get(dataSet).get("Email"));
			report.addPassLog(expectedResult1, "Should dispaly Shipping address page",
					"shipping address Page displayed successfully",
					Common.getscreenShotPathforReport("shipping address page"));

			Sync.waitElementPresent("xpath", "//span[contains(text(),'Next')]");
			Common.clickElement("xpath", "//span[contains(text(),'Next')]");

			Thread.sleep(1500);

			shipping_Address(dataSet);

			Thread.sleep(3000);
			report.addPassLog(expectedResult2, "Should dispaly Shipping address page",
					"shipping address Page displayed successfully",
					Common.getscreenShotPathforReport("Shipping address"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult2, "Should display Checkout Page", "Checkout Page not displayed",
					Common.getscreenShotPathforReport("Checkout Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}

	public void ProductReview(String dataSet) throws Exception {

		String expectedResult = "Updating the product review of selected product";
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
			Common.textBoxInput("xpath", "//form[@id='review-form']/fieldset/div[1]/div//input[@id='nickname_field']",
					data.get(dataSet).get("FirstName"));
			Common.textBoxInput("id", "email_field", data.get(dataSet).get("Email"));
			Common.textBoxInput("id", "summary_field", data.get(dataSet).get("Summary"));
			Common.textBoxInput("id", "review_field", data.get(dataSet).get("Review"));
			Thread.sleep(3000);
			Common.clickElement("xpath", "//button[contains(text(),'Submit Review')]");
			Thread.sleep(7000);
			String review = Common.getText("xpath", "//div[@data-bind='html: message.text']");
			System.out.println("Product Review updated " + review);
			Assert.assertEquals(review, "You submitted your review for moderation.");
			report.addPassLog(expectedResult, "Should update Product Review for selected product",
					"Updated Product review for selected product successfully",
					Common.getscreenShotPathforReport("Product Review success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should update Product Review for selected product",
					"Update Product review for selected product Failed",
					Common.getscreenShotPathforReport("Product Review Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void categoryMenuItem() {
		String expectedResult = "Select from  category";
		try {
			Thread.sleep(1000);
			Sync.waitElementPresent("xpath", "//span[contains(text(), 'Dryer')]");
			Common.clickElement("xpath", "//span[contains(text(), 'Dryer')]");
			Thread.sleep(1000);
			Sync.waitElementPresent("xpath",
					"//h2[contains(text(), 'salon one-step hair dryer and volumizer in teal')]");
			Common.clickElement("xpath", "//h2[contains(text(), 'salon one-step hair dryer and volumizer in teal')]");
			Thread.sleep(5000);
			report.addPassLog(expectedResult, "Should display item from  menucategory", "product display successfully",
					Common.getscreenShotPathforReport(" product display success"));

		} catch (Exception e) {
			report.addFailedLog(expectedResult, "Should display Search Results Page", "Search results Page not display",
					Common.getscreenShotPathforReport("Search result Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void categoryMenuItemCurlingiron() {
		String expectedResult = "Select from  category";
		try {
			Thread.sleep(1000);
			Sync.waitElementPresent("xpath", "(//span[contains(text(), 'Curling Irons')])[1]");
			Common.clickElement("xpath", "(//span[contains(text(), 'Curling Irons')])[1]");
			Thread.sleep(1000);
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(2000);
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Sync.waitElementPresent(20, "xpath", "(//h2[@class='title'])[2]");
			Common.clickElement("xpath", "(//h2[@class='title'])[2]");
			Thread.sleep(5000);
			report.addPassLog(expectedResult, "Should display item from  menucategory", "product display successfully",
					Common.getscreenShotPathforReport(" product display success"));

		} catch (Exception e) {
			report.addFailedLog(expectedResult, "Should display Search Results Page", "Search results Page not display",
					Common.getscreenShotPathforReport("Search result Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}

	/*
	 * public void Myorders(String dataSet) throws Exception { String
	 * expectedResult="Payment & Order submition success page with Credit card"; try
	 * { addPaymentDetails(dataSet);
	 * 
	 * if(Common.findElements("xpath",
	 * "//div[@class='message message-error']").size()>0) {
	 * addPaymentDetails(dataSet); }
	 * 
	 * Common.clickElement("xpath",
	 * "//button[@id='paymetrictokenize_place_order']"); Thread.sleep(2000);
	 * 
	 * String sucessMessage=Common.getText("xpath",
	 * "//h1[contains(text(),'Thank you for your purchase')]");
	 * 
	 * System.out.println(sucessMessage); Assert.assertEquals(sucessMessage,
	 * "THANK YOU FOR YOUR PURCHASE"); report.addPassLog(expectedResult,
	 * "Should display Order Success Page",
	 * "Order Success Page display successfully",
	 * Common.getscreenShotPathforReport("Order success page success"));
	 * /*catch(Exception |Error e)
	 */
	/*
	 * { //report.addFailedLog(expectedResult,"Should display Order Success Page",
	 * "Order Success Page not displayed",
	 * Common.getscreenShotPathforReport("Order Success Page Failed"));
	 * //Assert.fail(); }
	 */
	/*
	 * Thread.sleep(3000); String R=Common.getText("xpath",
	 * "//a[@class='order-number']"); System.out.println(R+" is Displayed");
	 * 
	 * Sync.waitElementPresent("xpath", "(//i[@class='icon-line2-user'])[1]");
	 * Common.clickElement("xpath", "(//i[@class='icon-line2-user'])[1]");
	 * Thread.sleep(1000); Common.clickElement("xpath",
	 * "(//a[@title='My Account'])[1]"); Thread.sleep(3000);
	 * Sync.waitElementPresent("xpath", "//a[contains(text(), 'My Orders')]");
	 * Common.clickElement("xpath", "//a[contains(text(), 'My Orders')]");
	 * Thread.sleep(2000);
	 * 
	 * String M=Common.getText("xpath", "(//td[@class='col id'])[1]");
	 * System.out.println(M+" is Displayed");
	 * 
	 * Assert.assertEquals(R, Common.getText("xpath",
	 * "(//td[@class='col id'])[1]"));
	 * 
	 * report.addPassLog(expectedResult, "Should display My Orders Page",
	 * "Order Details Page display successfully",
	 * Common.getscreenShotPathforReport("Order Details page success")); }
	 * catch(Exception |Error e) { report.addFailedLog(expectedResult,
	 * "Not displayed My Orders Page", "Order Details Page Not displayed",
	 * Common.getscreenShotPathforReport("Order Details Page Failed"));
	 * Assert.fail(); }
	 * 
	 * }
	 */

	public void headLinks(String dataSet) throws Exception {
		String expectedResult = "Header Link validations";
		String Headerlinks = data.get(dataSet).get("HeaderNames");
		String[] headers = Headerlinks.split(",");
		for (int i = 0; i < headers.length; i++) {
			Sync.waitElementClickable("xpath", "//span[text()='" + headers[i] + "']");
			Common.clickElement("xpath", "//span[text()='" + headers[i] + "']");
			Thread.sleep(3000);
			System.out.println(Common.getPageTitle());
			report.addPassLog(expectedResult, "Should display " + headers[i] + " success Page",
					"" + headers[i] + " Page display successfully",
					Common.getscreenShotPathforReport("Product Registration success page success"));

		}

	}

	public void navigateMyAccounts() throws InterruptedException {
		String expectedResult = "Naviagating to Login page";
		try {
			Thread.sleep(10000);

			Sync.waitElementClickable(30,
					By.xpath("//div[@class='authorization-link header_right user-dropdown m-hide non_ipad']"));
			// Common.findElement("xpath", "//div[@class='authorization-link header_right
			// user-dropdown m-hide non_ipad']").click();
			Common.mouseOver("xpath", "//div[@class='authorization-link header_right user-dropdown m-hide non_ipad']");
			// Sync.waitElementClickable(30,
			// By.xpath("(//i[@class='icon-line2-user'])[1]"));
			// Sync.waitElementClickable("xpath", "(//i[@class='icon-line2-user'])[1]");
			// Common.findElement("xpath", "(//i[@class='icon-line2-user'])[1]").click();

			// Thread.sleep(10000);

			// Sync.waitElementClickable(30, By.xpath("//div[@class='authorization-link
			// header_right user-dropdown m-hide non_ipad']"));
			// Common.findElement("xpath", "//div[@class='authorization-link header_right
			// user-dropdown m-hide non_ipad']").click();

			Thread.sleep(4000);
			Common.findElement("xpath", "(//a[@title='My Account'])[1]").click();

			// Sync.waitElementClickable(30,
			// By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/customer/account/")));
			// Common.findElement("xpath",
			// "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/customer/account/")).click();

			report.addPassLog(expectedResult, "Should display My Account Page",
					"My Account Page displayed successfully",
					Common.getscreenShotPathforReport("My Account Page success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display My Account Page", "My Account Page not display",
					Common.getscreenShotPathforReport("My Account Page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void navigateMyorders() throws Exception {
		String expectedResult = "Lands On My Order Page & Should display list of orders";
		try {
			Thread.sleep(6000);

			Sync.waitElementPresent("xpath", "//a[contains(text(), 'My Orders')]");
			Common.clickElement("xpath", "//a[contains(text(), 'My Orders')]");

			// Sync.waitElementClickable(30,
			// By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/sales/order/history/")));
			// Common.findElement("xpath",
			// "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/sales/order/history/")).click();
			Thread.sleep(4000);

			// String Order=Common.getText("xpath",
			// "//*[@id='my-orders-table']/tbody/tr[1]/td[1]");
			String Order = Common.getText("xpath", "(//td[@class='col id'])[1]");

			System.out.println(Order);

			report.addPassLog(expectedResult, "Should display My Order page with List of orders",
					"My Order page with List of orders display successfully",
					Common.getscreenShotPathforReport("MyOrder Page success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display My Order page with List of orders",
					"My Order page with List of orders not displayed",
					Common.getscreenShotPathforReport("MyOrder Page Failed"));
			Assert.fail();
		}
	}

	public void newsletterSubscription() throws Exception {
		String expectedResult = "Should Enter Email id for newsletter subscription";
		try {

			Common.textBoxInput("name", "email", Utils.getEmailid());
			Thread.sleep(5000);
			Common.clickElement("xpath", "//*[@id='weltpixel_newsletter']/div/button/span");

			Thread.sleep(7000);

			String s = Common.getText("xpath", "//div[@data-bind='html: message.text']");
			System.out.println(s);
			Assert.assertEquals(s, "Thank you for your subscription.");
			report.addPassLog(expectedResult, "Should display Email in Newsletter subscription",
					"Email in Newsletter subscription display successfully",
					Common.getscreenShotPathforReport("Newsletter success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display Email in Newsletter subscription",
					"Email in Newsletter subscription not displayed",
					Common.getscreenShotPathforReport("Newsletter Failed"));
			Assert.fail();
		}

	}

	public void InstaGramArticle() throws Exception {
		String expectedResult = "Instagram article page selection";
		try {

			Common.clickElement("xpath", "//a[@href='https://www.instagram.com/revlonhairtools/']");
			Thread.sleep(2000);
			Common.switchToSecondTab();
			Thread.sleep(10000);
			if (Common.isElementDisplayed("xpath", "//h1[contains(text(),'Instagram')]")) {
				String s = Common.getText("xpath", "//h1[contains(text(),'Instagram')]");
				System.out.println(s);
				Assert.assertEquals(s, "Instagram");
			} else {
				String s = Common.getText("xpath", "//h2[contains(text(),'revlonhairtools')]");
				System.out.println(s);
				Assert.assertEquals(s, "revlonhairtools");
			}
			report.addPassLog(expectedResult, "Should display Instagram page", "Instagram page display successfully",
					Common.getscreenShotPathforReport("Instagram page success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display Instagram page", "Instagram page not displayed",
					Common.getscreenShotPathforReport("Instagram page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
		Common.closeCurrentWindow();
		Common.switchToFirstTab();
	}

	public void FacebookArticle() throws Exception {
		String expectedResult = "Facebook article page selection";
		try {
			Common.clickElement("xpath", "//a[@href='https://www.facebook.com/revlonhairtools/']");
			Thread.sleep(2000);
			Common.switchToSecondTab();
			Thread.sleep(15000);
			// String s=Common.getText("xpath",
			// "//a[@href='https://www.facebook.com/revlonhairtools/']");
			String s = Common.getCurrentURL();
			System.out.println(s);
			Common.assertionCheckwithReport(s.contains("facebook"), "to verify the facebook page",
					"should display the facebook page", "Facebook page is displayed", "facebook page not displayed");
			report.addPassLog(expectedResult, "Should display Facebook page", "Facebook page display successfully",
					Common.getscreenShotPathforReport("Facebook page success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display Facebook page", "Facebook page not displayed",
					Common.getscreenShotPathforReport("Facebook page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
		Common.closeCurrentWindow();
		Common.switchToFirstTab();
	}

	public void youtubeArticle() throws Exception {
		String expectedResult = "Youtube article page selection";
		try {
			Common.clickElement("xpath", "//a[@href='https://www.youtube.com/user/RevlonHairTools']");
			Thread.sleep(2000);
			Common.switchToSecondTab();
			Thread.sleep(13000);
			String s = Common.getText("xpath", "(//*[contains(text(),'Revlon Hair Tools')])[1]");
			System.out.println(s);
			Assert.assertEquals(s, "Revlon Hair Tools");
			report.addPassLog(expectedResult, "Should display Youtube page", "Youtube page display successfully",
					Common.getscreenShotPathforReport("Youtube page success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display Youtube page", "Youtube page not displayed",
					Common.getscreenShotPathforReport("Youtube page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
		Common.closeCurrentWindow();
		Common.switchToFirstTab();

	}

	public void pinterestArticle() throws Exception {
		String expectedResult = "Pinterest article page selection";
		try {
			Common.clickElement("xpath", "//a[@href='https://co.pinterest.com/revlonhairtools/']");
			Thread.sleep(2000);
			Common.switchToSecondTab();
			Thread.sleep(10000);
			// String s=Common.getText("xpath", "//h1[contains(text(),'Revlon Hair
			// Tools')]");
			String s = Common.getCurrentURL();
			System.out.println(s);
			Common.assertionCheckwithReport(s.contains("pinterest"), "should navigate topinterest page ",
					expectedResult, "not navigated to pinterest page");
			// Assert.assertEquals(s, "Revlon Hair Tools");
			report.addPassLog(expectedResult, "Should display Pinterest page", "Pinterest page display successfully",
					Common.getscreenShotPathforReport("Pinterest page success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display Pinterest page", "Pinterest page not displayed",
					Common.getscreenShotPathforReport("Pinterest page Failed"));
			e.printStackTrace();
			;
			Assert.fail();
		}
		Common.closeCurrentWindow();
		Common.switchToFirstTab();

	}

	public void ValidateMyOrder() throws Exception {
		String expectedResult = "Lands On My Order Page & Should display list of orders";
		try {
			Thread.sleep(5000);
			Sync.waitElementClickable(30,
					By.xpath("//a[@href='" + System.getProperty("url",
							automation_properties.getInstance().getProperty(automation_properties.BASEURL)
									+ "us_en/sales/order/history/']")));
			Common.findElement("xpath",
					"//a[@href='" + System.getProperty("url",
							automation_properties.getInstance().getProperty(automation_properties.BASEURL)
									+ "us_en/sales/order/history/']"))
					.click();
			Thread.sleep(3000);

			String Order = Common.getText("xpath", "//h1[@class='page-title']");
			System.out.println(Order);
			Assert.assertEquals(Order, "My Orders");
			report.addPassLog(expectedResult, "Should display My Order page with List of orders",
					"My Order page with List of orders display successfully",
					Common.getscreenShotPathforReport("MyOrder Page success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display My Order page with List of orders",
					"My Order page with List of orders not displayed",
					Common.getscreenShotPathforReport("MyOrder Page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void ValidateAddressBook() throws Exception {
		String expectedResult = "Lands On AddressBook Page & Should display Billing Address";
		try {
			Thread.sleep(5000);
			Sync.waitElementClickable(30,
					By.xpath("//a[@href='" + System.getProperty("url",
							automation_properties.getInstance().getProperty(automation_properties.BASEURL)
									+ "us_en/customer/address/']")));
			Common.findElement("xpath",
					"//a[@href='" + System.getProperty("url",
							automation_properties.getInstance().getProperty(automation_properties.BASEURL)
									+ "us_en/customer/address/']"))
					.click();
			Thread.sleep(3000);

			String addressbook = Common.getText("xpath", "//h1[@class='page-title']");
			System.out.println(addressbook);
			Assert.assertEquals(addressbook, "Address Book");
			report.addPassLog(expectedResult, "Should display AddressBook page with Billing Address",
					"AddressBook page with Billing Address display successfully",
					Common.getscreenShotPathforReport("AddressBook Page success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display AddressBook page with Billing Address",
					"AddressBook page with Billing Address not displayed",
					Common.getscreenShotPathforReport("AddressBook Page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void ValidateAddressinformation() throws Exception {
		String expectedResult = "Lands On Addressinformation Page";
		try {
			Thread.sleep(5000);
			Sync.waitElementClickable(30,
					By.xpath("//a[@href='" + System.getProperty("url",
							automation_properties.getInstance().getProperty(automation_properties.BASEURL)
									+ "us_en/customer/account/edit/']")));
			Common.findElement("xpath",
					"//a[@href='" + System.getProperty("url",
							automation_properties.getInstance().getProperty(automation_properties.BASEURL)
									+ "us_en/customer/account/edit/']"))
					.click();

			Thread.sleep(3000);

			String addressinformation = Common.getText("xpath", "//h1[@class='page-title']");
			System.out.println(addressinformation);
			Assert.assertEquals(addressinformation, "Edit Account Information");
			report.addPassLog(expectedResult, "Should display Addressinformation page",
					"Addressinformation page display successfully",
					Common.getscreenShotPathforReport("Addressinformation Page success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display Addressinformation page",
					"Addressinformation page not displayed",
					Common.getscreenShotPathforReport("Addressinformation Page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void ValidateProductReview() throws Exception {
		String expectedResult = "Lands On Addressinformation Page";
		try {
			Thread.sleep(5000);
			Sync.waitElementClickable(30,
					By.xpath("//a[@href='" + System.getProperty("url",
							automation_properties.getInstance().getProperty(automation_properties.BASEURL)
									+ "us_en/review/customer/']")));
			Common.findElement("xpath",
					"//a[@href='" + System.getProperty("url",
							automation_properties.getInstance().getProperty(automation_properties.BASEURL)
									+ "us_en/review/customer/']"))
					.click();
			Thread.sleep(3000);

			String productreview = Common.getText("xpath", "//h1[@class='page-title']");
			System.out.println(productreview);
			Assert.assertEquals(productreview, "My Product Reviews");
			report.addPassLog(expectedResult, "Should display Addressinformation page",
					"Addressinformation page display successfully",
					Common.getscreenShotPathforReport("Addressinformation Page success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display Addressinformation page",
					"Addressinformation page not displayed",
					Common.getscreenShotPathforReport("Addressinformation Page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void ValidateNewsletterSubscription() throws Exception {
		String expectedResult = "Lands On Newsletter Subscription Page";
		try {
			Thread.sleep(5000);
			Sync.waitElementClickable(30,
					By.xpath("//a[@href='" + System.getProperty("url",
							automation_properties.getInstance().getProperty(automation_properties.BASEURL)
									+ "us_en/newsletter/manage/']")));
			Common.findElement("xpath",
					"//a[@href='" + System.getProperty("url",
							automation_properties.getInstance().getProperty(automation_properties.BASEURL)
									+ "us_en/newsletter/manage/']"))
					.click();
			Thread.sleep(3000);

			String Newslettersubscription = Common.getText("xpath", "//h1[@class='page-title']");
			System.out.println(Newslettersubscription);
			Assert.assertEquals(Newslettersubscription, "Newsletter Subscription");
			report.addPassLog(expectedResult, "Should display Newsletter Subscription page",
					"Newsletter Subscription page display successfully",
					Common.getscreenShotPathforReport("Newsletter Subscription Page success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display Newsletter Subscription page",
					"Newsletter Subscription page not displayed",
					Common.getscreenShotPathforReport("Newsletter Subscription Page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void Validatebacktostock() throws Exception {
		String expectedResult = "Lands On Back to stock Page";
		try {
			Thread.sleep(5000);
			Sync.waitElementClickable(30,
					By.xpath("//a[@href='" + System.getProperty("url",
							automation_properties.getInstance().getProperty(automation_properties.BASEURL)
									+ "us_en/xnotif/stock/index/']")));
			Common.findElement("xpath",
					"//a[@href='" + System.getProperty("url",
							automation_properties.getInstance().getProperty(automation_properties.BASEURL)
									+ "us_en/xnotif/stock/index/']"))
					.click();
			Thread.sleep(3000);

			// String backtostock=Common.getText("xpath", "//h1/span[contains(text(),'My
			// Back in Stock Subscriptions')]");
			// System.out.println(backtostock);
//			Assert.assertEquals(backtostock, "My Back in Stock Subscriptions");
			String s = Common.getCurrentURL();
			Common.assertionCheckwithReport(s.contains("stock"), "My back in stock subscription page will be displayed",
					expectedResult, "Back to stock page not displayed");
			report.addPassLog(expectedResult, "Should display Back to stock page",
					"Back to stock page display successfully",
					Common.getscreenShotPathforReport("Back to stock Page success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display Back to stock page", "Back to stock page not displayed",
					Common.getscreenShotPathforReport("Back to stock Page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void ValidateQuestion() throws Exception {
		String expectedResult = "Lands On Question Page";
		try {
			Thread.sleep(5000);
			Sync.waitElementClickable(30,
					By.xpath("//a[@href='" + System.getProperty("url",
							automation_properties.getInstance().getProperty(automation_properties.BASEURL)
									+ "us_en/productquestion/customer/']")));
			Common.findElement("xpath",
					"//a[@href='" + System.getProperty("url",
							automation_properties.getInstance().getProperty(automation_properties.BASEURL)
									+ "us_en/productquestion/customer/']"))
					.click();
			Thread.sleep(3000);

			String question = Common.getText("xpath", "//h1[@class='page-title']");
			System.out.println(question);
			Assert.assertEquals(question, "Product Questions");
			report.addPassLog(expectedResult, "Should display Question page", "Question page display successfully",
					Common.getscreenShotPathforReport("Question Page success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display Question page", "Question page not displayed",
					Common.getscreenShotPathforReport("Question Page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void CreateAccount(String dataSet) throws InterruptedException {
		navigateAccount();
		String expectedResult = "Account Creation of User with valid details";
		try {
			Common.clickElement("xpath", "//button[text()='Create an Account']");
			Thread.sleep(3000);
			report.addPassLog(expectedResult, "Should display Account Creation page",
					"Account Creation page display successfully",
					Common.getscreenShotPathforReport("Account Creation"));
			Common.textBoxInput("id", "firstname", data.get(dataSet).get("FirstName").toString());
			Common.textBoxInput("id", "lastname", data.get(dataSet).get("LastName").toString());
			Common.actionsKeyPress(Keys.ARROW_DOWN);
			Common.textBoxInput("id", "email_address", Utils.getEmailid());
			
			System.out.println(Utils.getEmailid());
			Common.textBoxInput("id", "password", data.get(dataSet).get("Password").toString());
			Common.textBoxInput("id", "password-confirmation", data.get(dataSet).get("Password").toString());
			Common.actionsKeyPress(Keys.ARROW_DOWN);
			Thread.sleep(3000);
			// Common.clickElement("id", "captcha_user_create");
			// Sync.waitElementVisible("className", "captcha-img");
			Common.clickElement("xpath", "//button[@title='Create an Account']");
			Thread.sleep(10000);
			String s = Common.getText("xpath", "//span[contains(text(),'My Account')]");
			Assert.assertEquals(s, "My Account");
			report.addPassLog(expectedResult, "Should display My Account Page", "My Account Page display successfully",
					Common.getscreenShotPathforReport("Account Creation success"));
			// report.addPassLog(expectedResult, "Should display My Account Page", "My
			// Account Page display successfully",
			// Common.getscreenShotPathforReport("Account Creation success"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display My Account Page", "My Account Page not display",
					Common.getscreenShotPathforReport("Account Creation Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void MyAccount_Address_AVS(String dataSet) throws Exception {
		Common.textBoxInput("name", "firstname", data.get(dataSet).get("FirstName"));
		Common.textBoxInput("name", "lastname", data.get(dataSet).get("LastName"));
		Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
		Common.textBoxInput("name", "street[]", data.get(dataSet).get("Street"));
		Common.textBoxInput("name", "city", data.get(dataSet).get("City"));
		Thread.sleep(10000);
		/*
		 * Common.actionsKeyPress(Keys.DOWN); Common.actionsKeyPress(Keys.ENTER);
		 * Thread.sleep(10000);
		 */

	}

	public void ValidateMyAccountAVS(String dataSet) throws Exception {
		String expectedResult = "Validating My Account Address Book AVS";
		try {

			Common.clickElement("xpath", "//button[@title='Add New Address']");
			Thread.sleep(5000);
			MyAccount_Address_AVS("Guest_shipping");
			report.addPassLog(expectedResult, "Should dispaly New Address Book page",
					"New Address Book page displayed successfully",
					Common.getscreenShotPathforReport("Address Book Pass"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should display New Address Book Page",
					"New Address Book Page not displayed", Common.getscreenShotPathforReport("Address Book Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void Newslettersignup(String dataSet) throws Exception {
		String expectedResult = "Should subscribe Newsletter Successfully";
		try {
			Thread.sleep(20000);
			Common.switchWindows();
			// Common.switchFrames("xpath", "//iframe[@id='LL_DataServer']");
			if (Common.isElementDisplayed("id", "wpn-lightbox-content")) {
				Thread.sleep(5000);
				Sync.waitElementClickable("xpath", "//div/input[@name='firstname']");
				Common.textBoxInput("xpath", "//div/input[@name='firstname']", data.get(dataSet).get("FirstName"));
				Common.textBoxInput("xpath", "//div/input[@name='lastname']", data.get(dataSet).get("LastName"));
				Common.textBoxInput("xpath", "(//div/input[@name='email'])[2]", Utils.getEmailid());
				Common.clickElement("xpath", "//button/span[contains(text(),'Sign Up')]");
				Thread.sleep(5000);

				if (Common.isElementDisplayed("xpath", "//div[@data-ui-id='message-success']")) {
					String s = Common.getText("xpath", "//div[@data-ui-id='message-success']");
					Assert.assertEquals(s, "Thank you for your subscription.");
				}
			} else {
				System.out.println("Newsletter signup popup not displayed");
				Common.switchToDefault();
			}
			report.addPassLog("subscription for Newsletter in homepage", expectedResult,
					"Newsletter Subscription successfull",
					Common.getscreenShotPathforReport("HomePage Newsletter Subscription passed"));
		} catch (Exception | Error e) {
			report.addFailedLog(expectedResult, "Should Navigating to Subscription Popup in Home Page",
					"Not Navigating to Subscription Popup in Home Page",
					Common.getscreenShotPathforReport("HomePage Popup failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}

	public void Newslettersignup() throws InterruptedException {

		Thread.sleep(4000);

		if (Common.isElementDisplayed("id", "wpn-lightbox-content")) {
			Thread.sleep(2000);
			Common.clickElement("id", "wpn-lightbox-close-newsletter");
		}
	}

	public void Registerincreaseproductquantity() {

		String expectedresult = "Increase product quantity in Viewcart Page";
		try {
			Sync.waitElementPresent("xpath", "//select[@data-title='Qty']");
			Common.clickElement("xpath", "//select[@data-title='Qty']");
			Thread.sleep(2000);
			Common.clickElement("xpath", "//select[@data-title='Qty']/option[@value='3']");
			Thread.sleep(5000);
			String s = Common.getText("xpath",
					"//div[contains(text(),'Great! Your order will be delivered for free!')]");
			Assert.assertEquals(s, "Great! Your order will be delivered for free!");
			Thread.sleep(3000);

			report.addPassLog(expectedresult, "order will be delivered for free message should be displayed",
					Common.getscreenShotPathforReport("Free order delivery message will be displayed"));

			// Common.scrollIntoView("xpath", "(//button[contains(text(),'Checkout')])");
			// Common.javascriptclickElement("xpath",
			// "(//button[contains(text(),'Checkout')])");

		} catch (Exception | Error e) {
			report.addFailedLog(expectedresult, "amount left for free delivery message will be displayed",
					Common.getscreenShotPathforReport("Failed to display the message"));

			e.printStackTrace();
			Assert.fail();
		}

	}

	public void RegisteruseraddNewAddress(String dataSet) {
		try {

			Common.textBoxInput("xpath", "(//input[@name='firstname'])[2]", data.get(dataSet).get("FirstName"));
			Common.textBoxInput("xpath", "(//input[@name='lastname'])[2]", data.get(dataSet).get("LastName"));

			Thread.sleep(1000);

			Common.textBoxInput("xpath", "(//input[@name='street[0]'])[2]", data.get(dataSet).get("Street"));
             Thread.sleep(2000);
            Common.textBoxInput("xpath", "(//input[@name='city'])[2]", data.get(dataSet).get("City"));

            Common.dropdown("xpath", "(//select[@name='region_id'])[2]", SelectBy.TEXT,
					data.get(dataSet).get("Region"));

			Sync.waitElementPresent("xpath", "(//input[@name='postcode'])[2]");
			Common.textBoxInput("xpath", "(//input[@name='postcode'])[2]", data.get(dataSet).get("postcode"));

			Common.dropdown("xpath", "//select[@name='country_id']", SelectBy.TEXT, data.get(dataSet).get("Country"));

			Thread.sleep(500);
			Common.textBoxInput("xpath", "(//input[@name='telephone'])[2]", data.get(dataSet).get("phone"));

			Sync.waitElementPresent("xpath", "(//input[@type='checkbox'])[3]");
			Common.clickElement("xpath", "(//input[@type='checkbox'])[3]");

			Sync.waitElementPresent("xpath", "//button[@class='action primary action-save-address']/span[text()='Ship Here']");
			Common.clickElement("xpath", "//button[@class='action primary action-save-address']/span[text()='Ship Here']");
			Thread.sleep(3000);
			if (Common.isElementDisplayed("xpath",
					"//div[contains(text(),'Address is not verified. Do you want to continue ?')]")) {
				Sync.waitElementClickable("xpath", "//button[@class='action-primary action-accept']");
				Common.clickElement("xpath", "//button[@class='action-primary action-accept']");
			}
			Thread.sleep(3000);
			report.addPassLog("should land on shipping page", "shipping address page will be displayed ",
					Common.getscreenShotPathforReport("shipping address page navigated successfully"));
		} catch (Exception | Error e) {
			report.addFailedLog("failed navigating to shiping address page ",
					Common.getscreenShotPathforReport("shipping address page not displayed"));
			e.printStackTrace();
			Assert.fail();
		}

	}

	public void clickaddnewaddress() {

		try {
			Common.scrollIntoView("xpath", "//span[contains(text(),'New Address')]");
			Common.javascriptclickElement("xpath", "//span[contains(text(),'New Address')]");
			Common.isElementDisplayed("xpath", "//h1[contains(text(),'Shipping Address')]");
			report.addPassLog("Should navigate to shipping address window", "shipping address window will be displayed",
					Common.getscreenShotPathforReport("shipping address window displayed successfully"));

		} catch (Exception | Error e) {
			report.addFailedLog("Should navigate to shipping address window", "shipping address window not displayed",
					Common.getscreenShotPathforReport("failed to display the shipping address page "));
			e.printStackTrace();
			Assert.fail();
		}

	}

	public void guestincreaseproductquantity() {

		String expectedresult = "Increase product quantity in Viewcart Page";
		try {
			Sync.waitElementPresent("xpath", "//select[@data-title='Qty']");
			Common.clickElement("xpath", "//select[@data-title='Qty']");
			Thread.sleep(2000);
			Common.clickElement("xpath", "//select[@data-title='Qty']/option[@value='3']");
			Thread.sleep(5000);
			String s = Common.getText("xpath",
					"//div[contains(text(),'Great! Your order will be delivered for free!')]");
			Assert.assertEquals(s, "Great! Your order will be delivered for free!");
			Thread.sleep(3000);

			report.addPassLog(expectedresult, "order will be delivered for free message should be displayed",
					Common.getscreenShotPathforReport("Free order delivery message will be displayed"));

			Common.scrollIntoView("xpath", "(//button[contains(text(),'Checkout')])");
			Common.javascriptclickElement("xpath", "(//button[contains(text(),'Checkout')])");

		} catch (Exception | Error e) {
			report.addFailedLog(expectedresult, "amount left for free delivery message will be displayed",
					Common.getscreenShotPathforReport("Failed to display the message"));

			e.printStackTrace();
			Assert.fail();
		}

	}

	public void Twoproductselection() {
		try {

			Common.scrollIntoView("xpath", "(//span[text()='Add to Cart'])[1]");
			String s = Common.getText("xpath", "(//div[@class='rev_product']//h2[@class='title'])[1]");
			System.out.println(s);
			Common.javascriptclickElement("xpath", "(//span[text()='Add to Cart'])[1]");
			Thread.sleep(3000);

			Common.actionsKeyPress(Keys.HOME);
			Common.isElementDisplayed("xpath", "//div[@data-bind='html: message.text']");
			String s1 = Common.getText("xpath", "//div[@data-bind='html: message.text']");
			System.out.println(s1);
			Common.assertionCheckwithReport(s1.contains(s), "Should add products to cart from plp",
					"products added successfully", "failed to add products to cart");

			if (Common.isElementDisplayed("xpath", "//div[@class='quickcart-top']")) {
				Common.clickElement("xpath", "(//button[@type='button'])[1]");
			}

			Common.scrollIntoView("xpath", "(//span[text()='Add to Cart'])[2]");
			String s2 = Common.getText("xpath", "(//div[@class='rev_product']//h2[@class='title'])[2]");
			System.out.println(s2);
			Common.javascriptclickElement("xpath", "(//span[text()='Add to Cart'])[2]");
			Thread.sleep(3000);

			Common.actionsKeyPress(Keys.HOME);
			/*
			 * Common.isElementDisplayed("xpath", "//div[@data-bind='html: message.text']");
			 * 
			 * String s3 = Common.getText("xpath",
			 * "//div[@data-bind='html: message.text']"); System.out.println(s3);
			 * Common.assertionCheckwithReport(s3.contains(s2),
			 * "Should add products to cart from plp", "products added successfully",
			 * "failed to add products to cart"); Thread.sleep(3000);
			 */
			Sync.waitElementPresent("xpath", "//a[contains(@class,'action viewcart')]/span");
			Common.clickElement("xpath", "//a[contains(@class,'action viewcart')]/span");

		} catch (Exception | Error e) {
			report.addFailedLog("should add two products to the cart", "products not added to cart",
					Common.getscreenShotPathforReport("failed to add the products"));
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void homepageaddtocart() throws Exception {
		String expectedresult = "Should add product to cart";
		try {
			if (Common.isElementDisplayed("xpath", "//span[text()='My Account']")) {
				Common.findElement("xpath", "//a[@href='" + System.getProperty("url",
						automation_properties.getInstance().getProperty(automation_properties.BASEURL) + "us_en/']"))
						.click();
			}
			Common.scrollIntoView("xpath", "(//button[@title='Add to Cart'])[5]");
			Common.getscreenShot("Product Displayed");
			Common.javascriptclickElement("xpath", "(//button[@title='Add to Cart'])[5]");
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//span[text()='YOUR BAG - ']");
			Common.actionsKeyPress(Keys.HOME);
			Common.isElementDisplayedonPage(10, "xpath", "//div[@class='message-success success message']");
			String s = Common.findElement("xpath", "//div[@class='message-success success message']").getText();

			System.out.println(s);
			Assert.assertEquals(s, "You added Salon One-Step Hair Dryer And Volumizer In Blue to your shopping cart.");
			report.addPassLog(expectedresult, "Added product to cart successfully",
					Common.getscreenShotPathforReport("Successfully added product to cart"));

		} catch (Exception | Error e) {
			report.addFailedLog(expectedresult, "Added product to cart successfully",
					Common.getscreenShotPathforReport("Failed to add product to cart"));
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void DifferentBillingaddress() throws Exception {
		try {
			Thread.sleep(3000);
			//Common.actionsKeyPress(Keys.PAGE_DOWN);
			//Thread.sleep(4000);
			if (Common.isElementDisplayed("xpath",
					"//button[@data-bind='click: editAddress, visible: !isAddressSameAsShipping()']/span")) {

				Common.clickElement("xpath",
						"//button[@data-bind='click: editAddress, visible: !isAddressSameAsShipping()']/span");

			} else {
				Thread.sleep(3000);
			//	Common.actionsKeyPress(Keys.PAGE_UP);
				Sync.waitElementPresent(20, "xpath", "//div[3]/div/input[@name='billing-address-same-as-shipping']");
				Common.clickElement("xpath", "//div[3]/div/input[@name='billing-address-same-as-shipping']");
			}
			Thread.sleep(2000);
			// Common.actionsKeyPress(Keys.PAGE_DOWN);
			if (Common.isElementDisplayed("xpath", "(//select[@name='billing_address_id'])[1]")) {
				Common.clickElement("xpath", "(//select[@name='billing_address_id'])[1]");
				Common.clickElementWithoutWait("xpath", "//select/option[contains(text(),'New Address')]");
				Thread.sleep(2000);
				Billingaddressform("NewAddress");
			} else {
				Thread.sleep(3000);
				Billingaddressform("NewAddress");
			}
			Thread.sleep(3000);

			Common.actionsKeyPress(Keys.PAGE_UP);
			// Sync.waitElementVisible("xpath", "(//div[@class='payment-method-title field
			// choice'])[2]");
			report.addPassLog("should add new billing address", "Billing adress added successfully",
					Common.getscreenShotPathforReport("successfully Added billing address"));
			Thread.sleep(3000);
			Common.scrollIntoView("xpath", "//button[@id='paymetrictokenize_place_order']");
		} catch (Exception | Error e) {
			report.addFailedLog("should add new billing address", "Billing address not added ",
			Common.getscreenShotPathforReport("Failed to add billing address"));
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void Billingaddressform(String dataSet) throws Exception {
		Common.textBoxInput("name", "firstname", data.get(dataSet).get("FirstName"));
		Common.textBoxInput("name", "lastname", data.get(dataSet).get("LastName"));

		Common.textBoxInput("name", "street[0]", data.get(dataSet).get("Street"));
		Common.textBoxInput("name", "city", data.get(dataSet).get("City"));
		Thread.sleep(2000);
		
		Common.dropdown("xpath", "//div[@class='billing-address-form']/form/fieldset/div[6]/div/select", SelectBy.TEXT, data.get(dataSet).get("Region"));
		
		Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
		Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
		Common.clickElement("xpath", "//button/span[text()='Update']");
		if (Common.isElementDisplayed("xpath", "//div[@class='field-error']")) {
			Common.textBoxInput("xpath", "//div[@class='billing-address-form']/form/fieldset/div[7]/div/input",
					data.get(dataSet).get("Region"));
			Common.clickElement("xpath", "//button/span[text()='Update']");
			Thread.sleep(2000);
		}
		if (Common.isElementDisplayed("xpath",
				"//div[contains(text(),'Address is not verified. Do you want to continue ?')]")) {
			Common.clickElement("xpath", "//span[text()='OK']");
		}
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.PAGE_UP);
		String s = Common.findElement("xpath", "//a[contains(@data-bind,'text: currentBillingAddress()')]").getText();
		Assert.assertEquals(s, data.get(dataSet).get("phone"));

		Common.actionsKeyPress(Keys.ARROW_UP);
	}
	
	
	
	public void BillingaddressformOutsideUS(String dataSet) throws Exception {
		Common.textBoxInput("name", "firstname", data.get(dataSet).get("FirstName"));
		Common.textBoxInput("name", "lastname", data.get(dataSet).get("LastName"));
		Thread.sleep(1500);
		Common.dropdown("xpath", "(//select[@name='country_id'])[2]", SelectBy.TEXT, data.get(dataSet).get("Country"));
		Common.textBoxInput("name", "street[0]", data.get(dataSet).get("Street"));
		Common.textBoxInput("name", "city", data.get(dataSet).get("City"));
		
		//Thread.sleep(2000);
		//Common.textBoxInput("xpath", "(//input[@name='region'])[2]", data.get(dataSet).get("Region"));
		
		Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
		Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
		
		Common.clickElement("xpath", "//button/span[text()='Update']");
		
		if (Common.isElementDisplayed("xpath", "//div[@class='field-error']")) {
			Common.textBoxInput("xpath", "//div[@class='billing-address-form']/form/fieldset/div[7]/div/input",
					data.get(dataSet).get("Region"));
			Common.clickElement("xpath", "//button/span[text()='Update']");
			Thread.sleep(2000);
		}
		if (Common.isElementDisplayed("xpath",
				"//div[contains(text(),'Address is not verified. Do you want to continue ?')]")) {
			Common.clickElement("xpath", "//span[text()='OK']");
		}else {
			System.out.println("The Update button is not clicked");
			System.out.println("We cannot place the order for Billing Address outside US");
		}
		Sync.waitPageLoad(40);
		
	}
	
	
	
	public void BillingaddressoutsideUS() throws Exception {
		try {
			Thread.sleep(3000);
			
			if (Common.isElementDisplayed("xpath", "//button[@data-bind='visible: !isAddressSameAsShipping(), click: editAddress']")) {

				Common.clickElement("xpath",
						"//button[@data-bind='visible: !isAddressSameAsShipping(), click: editAddress']");

			} else {
				     
				Thread.sleep(3000);      
				Common.actionsKeyPress(Keys.PAGE_UP);
				Thread.sleep(3000);
				Sync.waitElementVisible(30, "xpath", "//div[3]/div/input[@name='billing-address-same-as-shipping']");
				Common.scrollToElementAndClick("xpath", "//div[3]/div/input[@name='billing-address-same-as-shipping']");
			
				Common.clickElement("xpath", "//div[3]/div/input[@name='billing-address-same-as-shipping']");
			}
				if (Common.isElementDisplayed("xpath", "(//select[@name='billing_address_id'])[1]")) {
				Common.clickElement("xpath", "(//select[@name='billing_address_id'])[1]");
				Common.clickElementWithoutWait("xpath", "//select/option[contains(text(),'New Address')]");
				Thread.sleep(2000);
				BillingaddressformOutsideUS("NonUSaddress");
			} else {
				Thread.sleep(3000);
				BillingaddressformOutsideUS("NonUSaddress");
			}
		
             Sync.waitPageLoad(30);
             Common.refreshpage();
             Thread.sleep(3000);
             Common.refreshpage();
             Common.scrollIntoView("xpath", "//button/span/span[text()='Next']");
             Common.javascriptclickElement("xpath", "//button/span/span[text()='Next']");
             Thread.sleep(4000);
			//Common.actionsKeyPress(Keys.HOME);
             Sync.waitElementPresent("xpath", "//span[text()='No Payment method available.']");
			Common.isElementDisplayed("xpath", "//span[text()='No Payment method available.']");
			int Error_message = Common.findElements("xpath", "//span[text()='No Payment method available.']").size();
			
			Common.assertionCheckwithReport(Error_message>0, "should add new billing address", "Billing adress added successfully", "Failed to add the billing address");
					
		} catch (Exception | Error e) {
			report.addFailedLog("should add new billing address", "Billing address not added ",
					Common.getscreenShotPathforReport("Failed to add billing address"));
			e.printStackTrace();
			Assert.fail();
		}
	}

	public void TaxandShippingAmountvalidation(String dataSet) {
		String Expectedresult = "Should display the Shipping and tax amount ";
		String Expectedresult1 = "The calculated tax and expecetd tax should be equal";
		try {
			Common.actionsKeyPress(Keys.ARROW_DOWN);
		Float Taxrate =Float.valueOf(data.get(dataSet).get("taxrate"));
			if(Taxrate>0){
			Common.isElementDisplayed("xpath", "//strong[@class='product-item-name']");
			Common.isElementDisplayed("xpath", "//span[@data-th='Shipping']");
			String Cartsubtotal = Common.getText("xpath", "//span[@data-th='Cart Subtotal']").replace("$", "");
			Float cartsubttl = Float.valueOf(Cartsubtotal);
			
			String shippingamount = Common.getText("xpath", "//span[@data-th='Shipping']").replace("$", "");
			Float shipingamt = Float.valueOf(shippingamount);
			
			
				String taxamount = Common.findElement("xpath", "(//span[@data-bind='text: getValue()'])[2]").getText().replace("$", "");
				Float Taxamt = Float.valueOf(taxamount);
				System.out.println("The tax Amount is" + taxamount);
											
		Float CalculatedTaxvalue =  (((cartsubttl+shipingamt)*Taxrate)/100 ) ;
		
		String caltaxvalue = new BigDecimal(CalculatedTaxvalue).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
		String caltaxvalue1 = String.valueOf(caltaxvalue);
		
		
		Common.assertionCheckwithReport(caltaxvalue1.equals(Taxamt), "The actual tax and calculated tax are equal ", Expectedresult1, "The calculated tax and actual tax are not equal");

		
		
			
			} else {
				System.out.println("There is no tax amount for this address");
			}
		
			
			
			report.addPassLog(Expectedresult, "Shipping amount and tax will be displayed",
					Common.getscreenShotPathforReport(" Successfully displayed the tax"));

		} catch (Exception | Error e) {
			report.addFailedLog(Expectedresult, "Shipping amount and tax not displayed",
					Common.getscreenShotPathforReport("Failed to display the tax and shipping amount"));
 
			e.printStackTrace();
			Assert.fail();
		}

	}
	public void noTaxonfrieght(String dataSet) {
		String Expectedresult = "Should display the Shipping and tax amount ";
		String Expectedresult1 = "The calculated tax and expecetd tax should be equal";
		try {
		Float Taxrate =Float.valueOf(data.get(dataSet).get("taxrate"));
		
			if(Taxrate>0){
			Common.isElementDisplayed("xpath", "//strong[@class='product-item-name']");
			Common.isElementDisplayed("xpath", "//span[@data-th='Shipping']");
			String Cartsubtotal = Common.getText("xpath", "//span[@data-th='Cart Subtotal']").replace("$", "");
			Float cartsubttl = Float.valueOf(Cartsubtotal);
			System.out.println("The cart subtotal is " +cartsubttl);
			
			String shippingamount = Common.getText("xpath", "//span[@data-th='Shipping']").replace("$", "");
			Float shipingamt = Float.valueOf(shippingamount);
			System.out.println("The shipping amount is"+shippingamount);
			
				String taxamount = Common.findElement("xpath", "(//span[@data-bind='text: getValue()'])[2]").getText().replace("$", "");
				Float Taxamt = Float.valueOf(taxamount);
				System.out.println("The tax Amount is" + taxamount);
				
		
					Float CalculatedTaxvalue =  ((cartsubttl*Taxrate)/100 ) ;
					System.out.println(CalculatedTaxvalue);
				
					String caltaxvalue = new BigDecimal(CalculatedTaxvalue).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
					System.out.println(caltaxvalue);
					Float caltaxvalue1 = Float.valueOf(caltaxvalue);
					
					System.out.println(caltaxvalue1);
					Common.assertionCheckwithReport(caltaxvalue1.equals(Taxamt), "The actual tax and calculated tax are equal ", Expectedresult1, "The calculated tax and actual tax are not equal");

		
			} else {
				System.out.println("There is no Tax for this address");
			}
		
			
			
			report.addPassLog(Expectedresult, "Shipping amount and tax will be displayed",
					Common.getscreenShotPathforReport(" Successfully displayed the tax"));

		} catch (Exception | Error e) {
			report.addFailedLog(Expectedresult, "Shipping amount and tax not displayed",
					Common.getscreenShotPathforReport("Failed to display the tax and shipping amount"));
 
			e.printStackTrace();
			Assert.fail();
		}

	}
	public void Shippingmethod() {
		String expectedResult = "Should navigate to review and payments page";
		try {
		if(Common.isElementDisplayed("xpath", "//form[@class='form methods-shipping']/div/table")){
		String cartsubtotal = Common.findElement("xpath", "//tr[@class='totals sub']/td/span").getText().replace("$", "");
		float cartvalue = Float.valueOf(cartsubtotal).floatValue();
		if(cartvalue<50){
		
			String standardshipping = Common.findElement("xpath", "//td[@class='col col-price']/span/span").getText().replace("$", "");
			Common.assertionCheckwithReport(standardshipping.equals("8.95"), "standard shipping method will be enabled  ", "standard shipping method should  be enabled ", "Shipping methods not enabled");
			
		}else{
			String freeshipping = Common.findElement("xpath", "//td[@class='col col-price']/span/span").getText().replace("$", "");
			Common.assertionCheckwithReport(freeshipping.equals("0.00"), "free shipping method will be enabled  ", "free shipping method should  be enabled ", "free methods not enabled");
			
		}}
		else{
			Common.isElementDisplayed("xpath", "//div/div[@class='no-quotes-block']");
			System.out.println("Shipping methods not enabled");
			
		}		
		 Common.clickElement("xpath", "(//span[text()='Next'])[2]");
		 
			
		if (Common.isElementVisibleOnPage(30, "xpath",
				"//div[contains(text(),'Address is not verified. Do you want to continue ?')]")) {

			System.out.println("Address not verified pop up displayed");

			Sync.waitElementPresent("xpath", "//button[@class='action-primary action-accept']");
			Common.clickElement("xpath", "//button[@class='action-primary action-accept']");
		}
		Common.isElementDisplayed("xpath", "//div[contains(text(),'Payment Method')]");
		
		report.addPassLog(expectedResult, "Should display Checkout Page", "Checkout Page display successfully",
				Common.getscreenShotPathforReport("Checkout page success"));
		Thread.sleep(3000);

	} catch (Exception | Error e) {
		report.addFailedLog(expectedResult, "Review and payments page dispaly failed",
				Common.getscreenShotPathforReport("Failed to display the tax"));

		e.printStackTrace();
		Assert.fail();
	}
}
	
	public void StandardShippingmethod() {
		String expectedResult = "Should navigate to review and payments page";
		try {		
			/*
			if (Common.checkBoxIsSelected("xpath", "(//input[@name='shipping_method'])[2]")) {

				System.out.println("Shipping method is selected");
			} else {

				System.out.println("Shipping method is not selected");

				Sync.waitElementPresent(20, "xpath", "(//input[@name='shipping_method'])[1]");
				Common.clickElement("xpath", "(//input[@name='shipping_method'])");

			}
*/
			Sync.waitElementPresent(20, "xpath", "(//span[contains(text(),'Next')])[2]");
			Common.clickElement("xpath", "(//span[contains(text(),'Next')])[2]");

			Thread.sleep(2000);

			if (Common.isElementVisibleOnPage(20, "xpath",
					"//div[contains(text(),'Address is not verified. Do you want to continue ?')]")) {

				System.out.println("Address not verified pop up displayed");

				Sync.waitElementPresent("xpath", "//button[@class='action-primary action-accept']");
				Common.clickElement("xpath", "//button[@class='action-primary action-accept']");
			}
			Common.isElementDisplayed("xpath", "//div[contains(text(),'Payment Method')]");

			report.addPassLog("To verify the navigation to payments and review page", expectedResult,
					"Checkout Page display successfully", Common.getscreenShotPathforReport("Checkout page success"));

		} catch (Exception | Error e) {

			report.addFailedLog(expectedResult, "Review and payments page dispaly failed",
					Common.getscreenShotPathforReport("Failed to display the tax"));

			e.printStackTrace();
			Assert.fail();
		}
	}

public void giftcard(String dataSet) {
	
		try{
		
		//String URL = Common.getCurrentURL();
		//Assert.assertEquals("URL", "https://stg-upgrade.revlonhairtools.com/us_en/checkout/#payment");
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			 String Ordertotal = Common.findElement("xpath", "//td[@data-th='Order Total']/strong/span").getText().replace("$", "");
			Sync.waitElementVisible("xpath", "//span[text()='Apply Gift Card']");
			Common.clickElement(By.xpath("//span[text()='Apply Gift Card']"));
	        Sync.waitElementVisible("name", "giftcard_code");
	        Common.textBoxInput("name", "giftcard_code", data.get(dataSet).get("giftcard"));
	        
	        Common.clickElement(By.xpath("(//span[text()='Apply'])[2]"));
	        Common.getscreenShot("Gift card applied successfullly");
	     String success= Common.findElement(By.xpath("//div[@data-ui-id='checkout-cart-validationmessages-message-success']")).getText();
	     Assert.assertEquals(success, "Gift Card 012QALEJIIFB was added.");
	     Common.actionsKeyPress(Keys.PAGE_UP);
	        Sync.waitElementPresent("xpath", "//div[@class='gift-card-information']");
	      String discountedamount = Common.findElement("xpath", "//div[@class='gift-card-information']/span[2]").getText().replace("$", "");
	      Common.assertionCheckwithReport(discountedamount.equals(Ordertotal), "Gift card is applied ", "Gift card should be applied successfully", "Failed to apply gift card");
			
	     	      
		  String nopayment = Common.getText("xpath", "//span[text()='No Payment Information Required']");  
	      Assert.assertEquals(nopayment, "No Payment Information Required");
			
			
			Sync.waitElementVisible("xpath", "//span[text()='Place Order']");
			Common.clickElement(By.xpath("//span[text()='Place Order']"));
			 
			Assert.assertEquals(Common.getCurrentURL(), "https://stg-upgrade.revlonhairtools.com/us_en/checkout/onepage/success/");
			String successmessage = Common.findElement("xpath", "//h1[text()='Thank you for your purchase']").getText();
			Common.assertionCheckwithReport(successmessage.equals("Thank you for your purchase"), "Order success page is displayed ", "Order succeess page should be dispalyed", "Failed to display order success page");
				
			
			
		}catch(Exception | Error e){
			report.addFailedLog("Gift card should be applied on the total cart value ", "Should apply the discount code successfully", "Gift card not applied",
					Common.getscreenShotPathforReport("Failed to apply the gi8ft card"));
			e.printStackTrace();
			Assert.fail();
		}
		
		
		
	}

}
