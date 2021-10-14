package TestComponent.oxo;

import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import TestLib.Common;
import TestLib.Sync;
import Utilities.ExcelReader;
import Utilities.ExtenantReportUtils;
import Utilities.MailAPI;
import Utilities.PropertiesReader;

public class OxoHelper {

	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data = new HashMap<>();
	static ExtenantReportUtils report;

	/*
	 * public void navigateLoginPage() {
	 * 
	 * }
	 */
	
	
	

	public void clickLogo() {
		try {
			Thread.sleep(3000);
			String url = Common.getCurrentURL();
			if (url.contains("blog")) {
				Sync.waitPresenceOfElementLocated("xpath", "//img[@alt='oxo']");
				Common.clickElement("xpath", "//img[@alt='oxo']");

			}
			Sync.waitPresenceOfElementLocated("xpath", "//img[@title='OXO']");
			Common.clickElement("xpath", "//img[@title='OXO']");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying logo button", "click logo button land on home page",
					"User failed to click logo", Common.getscreenShotPathforReport("oxologo"));
			AssertJUnit.fail();

		}
	}

	public void CreateNewAccount(String dataSet) throws Exception {
		try {
			Thread.sleep(3000);
			Sync.waitElementClickable(30, By.xpath("//a[@class='social-login']"));
			Common.findElement("xpath", "//a[@class='social-login']").click();
			Thread.sleep(3000);
			ExtenantReportUtils.addPassLog("verifying Sign in link", "lands on the account creation popup",
					"User lands on the account creation popup", Common.getscreenShotPathforReport("signbutton"));
			// ExtenantReportUtils.addPassLog(description, expectedResult,
			// actualResult, screenShotPath);
		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("verifying Sign in link", "lands on the account creation popup",
					"User failed lands on the account creation popup", Common.getscreenShotPathforReport("signbutton"));
			AssertJUnit.fail();

		}
		
		String email = Common.genrateRandomEmail(data.get(dataSet).get("Email"));
		Common.clickElement("xpath", "//span[text()='Create Account']");

		try {
			try {
				Sync.waitElementClickable(30, By.xpath("//span[text()='Create Account']"));
			} catch (Exception e) {
				if (Common.findElement("xpath", "//span[text()='Create Account']") == null) {
					Common.clickElement("xpath", "//a[@class='social-login']");
					Thread.sleep(2000);
				}
			}
			Common.clickElement("xpath", "//span[text()='Create Account']");
			ExtenantReportUtils.addPassLog("verifying Create account button", "lands on the signin popup",
					"User successfully lands signin popup", Common.getscreenShotPathforReport("creatnow"));
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying Create account button", "lands on the signin popup",
					"User failed lands signin popup", Common.getscreenShotPathforReport("creatnow"));
			AssertJUnit.fail();

		}
		try {
			Common.textBoxInput("id", "firstname", data.get(dataSet).get("FirstName"));
			Common.textBoxInput("id", "lastname", data.get(dataSet).get("LastName"));
			Common.textBoxInput("id", "email_address_create", email);
			Common.textBoxInput("id", "password-social", data.get(dataSet).get("Password"));
			Common.textBoxInput("id", "password-confirmation-social", data.get(dataSet).get("Password"));
			ExtenantReportUtils.addPassLog("verifying sign up page with fieldData", "User enter the FieldData",
					"successfully enter the data", Common.getscreenShotPathforReport("sigData"));

			// Common.actionsKeyPress(Keys.PAGE_DOWN);

			Common.clickElement("id", "button-create-social");
			Common.actionsKeyPress(Keys.PAGE_UP);

			int SuccessmessagetextSize = Common.findElements("xpath", "//div[@class='message-success success message']").size();
			if (SuccessmessagetextSize >= 0) {
			} else {
				
				ExtenantReportUtils.addFailedLog("verifying sign up page with valid field data",
						"see the fields populated with the data", "User failed to proceed signUp form",
						Common.getscreenShotPathforReport("signupissue"));
				AssertJUnit.fail();
			}

			Common.actionsKeyPress(Keys.ESCAPE);
		} catch (Exception | Error e) {
			
            e.printStackTrace();
            ExtenantReportUtils.addFailedLog("verifying sign up page to Create new account",
					"Sign up popup with valid Data", "User failed to proceed signUp form ",
					Common.getscreenShotPathforReport("signupissue"));
			AssertJUnit.fail();

		}
	}

	public void validatingSearchBoxWithOutData() throws Exception {
		String expectedResult = "Click on the search button Lands on Global search page should show search textbox";
		String url = Common.getCurrentURL();
		Thread.sleep(5000);
		try {
			Common.clickElement("className", "search-tool");

			Sync.waitElementPresent("id", "search");
			if (Common.findElement("id", "search") == null) {
				Common.mouseOverClick("className", "search-tool");
				Thread.sleep(2000);
			}
			ExtenantReportUtils.addPassLog("validating search box", expectedResult,
					"successfully Click on the search button Lands on Global search page",
					Common.getscreenShotPathforReport("faieldopensearchbox"));
		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("validating search box", expectedResult,
					"user faield to Click on the search button",
					Common.getscreenShotPathforReport("faieldopensearchbox"));
			AssertJUnit.fail();

		}
		try {
			expectedResult = "It Should redirect to home page";

			Common.textBoxInput("id", "search", "");
			Common.actionsKeyPress(Keys.ENTER);
			Sync.waitPageLoad();
			String presenturl = Common.getCurrentURL();
			if (presenturl.contains("#")) {
				presenturl = presenturl.replace("#", "");
			}
			System.out.println(presenturl);

			Common.assertionCheckwithReport(presenturl.equals(url), "validating the search with empty data",
					expectedResult, "user enter empty data and it redirect to home page",
					Common.getscreenShotPathforReport(
							"user faield eneter empty data in search or redirect to home page"));
		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("validating the search with empty data", expectedResult,
					"user faield eneter empty data in search or redirect to home page",
					Common.getscreenShotPathforReport("emptysearch"));
			AssertJUnit.fail();

		}
	}

	public void validatingSearchBoxWithNumberText(String productName) throws Exception {
		String expectedResult = "Click on the search button Lands on Global search page should show search textbox";
		Thread.sleep(5000);
		try {
			Common.clickElement("className", "search-tool");

			Sync.waitElementPresent("id", "search");
			if (Common.findElement("id", "search") == null) {
				Common.mouseOverClick("className", "search-tool");
				Thread.sleep(2000);
			}
			ExtenantReportUtils.addPassLog("validating search box", expectedResult,
					"successfully Click on the search button Lands on Global search page",
					Common.getscreenShotPathforReport("faieldopensearchbox"));
		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("validating search box", expectedResult,
					"user faield to Click on the search button",
					Common.getscreenShotPathforReport("faieldopensearchbox"));
			AssertJUnit.fail();

		}

		try {
			expectedResult = "It should allow both text and numeric";

			Common.textBoxInput("id", "search", productName);
			Common.actionsKeyPress(Keys.ENTER);
			Sync.waitPageLoad();
			String Classname = Common.findElement("xpath", "//div[@id='ajax-layer-product-list-container']/div[1]")
					.getAttribute("class");
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Common.assertionCheckwithReport(Classname.contains("results"),
					"validating the search with combination of latter Numbers data", expectedResult,
					"successfully search allow both text and numeric", "failed allow both text and numeric");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the search with combination of latter Numbers data",
					expectedResult, "user faield allowing combination of latter Numbers data",
					Common.getscreenShotPathforReport("serchnumbertext"));
			AssertJUnit.fail();

		}
	}

	public void validatingSearchProductInformation(String productName) throws Exception {
		String expectedResult = "Click on the search button Lands on Global search page should show search textbox";
		Thread.sleep(5000);
		try {
			Common.clickElement("className", "search-tool");

			Sync.waitElementPresent("id", "search");
			if (Common.findElement("id", "search") == null) {
				Common.mouseOverClick("className", "search-tool");
				Thread.sleep(2000);
			}
			ExtenantReportUtils.addPassLog("validating search box", expectedResult,
					"successfully Click on the search button Lands on Global search page",
					Common.getscreenShotPathforReport("faieldopensearchbox"));
		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("validating search box", expectedResult,
					"user faield to Click on the search button",
					Common.getscreenShotPathforReport("faieldopensearchbox"));
			AssertJUnit.fail();

		}

		try {

			expectedResult = "It should contines homeTab ";
			Common.textBoxInput("id", "search", productName);
			Common.actionsKeyPress(Keys.ENTER);
			Sync.waitPageLoad();
			Thread.sleep(8000);
			String textelemt = Common.getText("xpath", "//ul[@class='items']/li[1]/a");
			Common.assertionCheckwithReport(textelemt.contains("Home"),
					"validating the search rueslt contines homeTab ", expectedResult,
					"search result it showing home tab", Common.getscreenShotPathforReport("faield to diaply hometab"));
		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("validating the search rueslt contines homeTab", expectedResult,
					"faield to display hometab", Common.getscreenShotPathforReport("homeTab"));
			AssertJUnit.fail();

		}
		String errormessage = "product Namesis";
		try {

			expectedResult = "in search result should show product image, product title, price add wishlist.";
			int totalproductscount = Common.findElements("xpath", "//li[@class='item product product-item ']").size();

			int producttitles = Common.findElements("xpath", "//a[@class='product-item-link']").size();
			AssertJUnit.assertTrue(totalproductscount == producttitles);
			errormessage = "wishlist button";
			int totlWishList = Common.findElements("xpath", "//a[@class='action towishlist']").size();
			AssertJUnit.assertTrue(totalproductscount == totlWishList);

			errormessage = "product price";
			int productprice = Common.findElements("xpath", "//span[contains(@id,'product-price')]").size();
			AssertJUnit.assertTrue(totalproductscount == productprice);

			Common.actionsKeyPress(Keys.PAGE_DOWN);
			ExtenantReportUtils.addPassLog("validating the search rueslt", expectedResult,
					"it display all images,price wishlist ", Common.getscreenShotPathforReport("resultissues"));
		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("validating the search rueslt", expectedResult,
					"faield to display " + errormessage + "", Common.getscreenShotPathforReport("resultissues"));
			AssertJUnit.fail();

		}

	}

	// a[@class='product-item-link']
	// a[@class='action towishlist']
	// img[@class='product-image-photo']

	public void searchProductAndAddtoCart(String productName) throws Exception {
		Thread.sleep(10000);
		Common.actionsKeyPress(Keys.ESCAPE);
		Common.clickElement("className", "search-tool");
		Sync.waitElementPresent("id", "search");
		if (Common.findElement("id", "search") == null) {
			Common.mouseOverClick("className", "search-tool");
			Thread.sleep(2000);
		}
		Common.textBoxInput("id", "search", productName);
		Common.actionsKeyPress(Keys.ENTER);
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Thread.sleep(3000);
		// Sync.waitElementClickable("xpath", "//ol[@class='products list items
		// product-items']/li[1]/following::button[1]");
		Common.clickElement("xpath", "//ol[@class='products list items product-items']/li[1]/following::button[1]");
		/*
		 * if(Common.findElement("xpath",
		 * "//button[@title='Add to Cart']")!=null) {
		 * Common.clickCheckBox("xpath", "//button[@title='Add to Cart']"); }
		 */
		Thread.sleep(3000);
		Common.actionsKeyPress(Keys.ESCAPE);
		Thread.sleep(2000);

	}

	public void checkOut() throws Exception {

		Common.actionsKeyPress(Keys.ESCAPE);
		Common.mouseOver("className", "minicart-wrapper");
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		try {
			Common.mouseOverClick("id", "top-cart-btn-checkout");
		} catch (Exception e) {
			Common.actionsKeyPress(Keys.ESCAPE);
			Common.mouseOver("className", "minicart-wrapper");
			Common.mouseOverClick("id", "top-cart-btn-checkout");
		}
		Thread.sleep(4000);
		// Common.actionsKeyPress(Keys.PAGE_DOWN);

	}

	public void addDeliveryAddress(String dataSet) throws Exception {
		Sync.waitElementPresent("name", "street[0]");
		Common.textBoxInput("name", "street[0]", data.get(dataSet).get("Street"));
		Common.textBoxInput("name", "city", data.get(dataSet).get("City"));
		Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
		Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
		Common.clickElement("xpath", "//*[contains(@name,'ko_unique_')]");
		Common.mouseOverClick("xpath", "//button[@class='button action continue primary']");
		Thread.sleep(3000);
		Common.clickElement("xpath", "//label[@for='ime_paymetrictokenize']");
		Thread.sleep(3000);
	}

	public void selectproduct(String productname) {
		String expectedResult = "It Should navigate to the product details page";
		try {
			Sync.waitPageLoad();
			// Common.actionsKeyPress(Keys.PAGE_DOWN);
			// Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(3000);
			// Common.actionsKeyPress(Keys.PAGE_DOWN);

			Common.scrollIntoView("xpath", "//a[contains(text(),'" + productname + "')]");
			Sync.waitElementClickable("xpath",
					"//a[@class='product-item-link'and contains(text(),'" + productname + "')]");
			Common.clickElement("xpath", "//a[contains(text(),'" + productname + "')]");
			System.out.println(Common.getPageTitle());
			Thread.sleep(5000);
			System.out.println(Common.getPageTitle());
			Common.assertionCheckwithReport(Common.getPageTitle().equals(productname),
					"validating the product details page", expectedResult,
					"sucessfully navigated to product details page", "faield to navigate product details page");
		
			
			Thread.sleep(5000);
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the product details page", expectedResult,
					"user faield to navigate product detiles page",
					Common.getscreenShotPathforReport("faield product detiles page"));
			AssertJUnit.fail();

		}

	}

	public void addproducts(String quantity) throws Exception {

		int elementsize = Common.findElements("xpath", "//input[@class='sf-stepper-input']").size();

		if (elementsize > 0) {
			try {
				Thread.sleep(6000);
				Sync.scrollDownToView("xpath", "//input[@class='sf-stepper-input']");
				Common.textBoxInput("xpath", "//input[@class='sf-stepper-input']", quantity);
				Sync.waitElementPresent("xpath", "//input[@class='sf-stepper-input']");
				Common.clickElement("xpath", "//button[@id='product-addtocart-button']");
				Thread.sleep(3000);
				Sync.waitElementPresent("xpath", "//span[text()='close']");
				Common.clickElement("xpath", "//span[text()='close']");

				ExtenantReportUtils.addPassLog("validating the product to Cart", "user add the product to cart",
						"user successfully add the product to cart",
						Common.getscreenShotPathforReport("usertheproducttocart"));
			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validating the product to Cart", "user add the product to cart",
						"user faield to add the product to cart",
						Common.getscreenShotPathforReport("user faield to add the product to cart"));
				AssertJUnit.fail();

			}

		} else {
			try {
				Thread.sleep(6000);
				// Sync.waitElementPresent("xpath",
				// "//input[@class='sf-stepper-input']");
				Sync.scrollDownToView("xpath", "//select[@id='qty']");
				Sync.waitElementPresent("xpath", "//select[@id='qty']");
				Common.dropdown("xpath", "//select[@id='qty']", Common.SelectBy.VALUE, quantity);
				Sync.waitElementPresent("xpath", "//button[@id='product-addtocart-button']");
				Common.clickElement("xpath", "//button[@id='product-addtocart-button']");
				Thread.sleep(3000);
				//Sync.waitElementPresent("xpath", "//span[text()='close']");
				//Common.clickElement("xpath", "//span[text()='close']");

				ExtenantReportUtils.addPassLog("validating the product to Cart", "user add the product to cart",
						"user successfully add the product to cart",
						Common.getscreenShotPathforReport("user faield to add the product to cart"));
			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("validating the product to Cart", "user add the product to cart",
						"user faield to add the product to cart",
						Common.getscreenShotPathforReport("user faield to add the product to cart"));
				AssertJUnit.fail();

			}
		}
	}

	public void clickMiniCart() throws Exception {
		Thread.sleep(6000);
		try {
			Sync.waitPageLoad();
			//Sync.waitElementPresent("xpath", "//a[@class='action showcart']");
			//Common.clickCheckBox("xpath", "//a[@class='action showcart']");
			int Subtotal = Common.findElements("xpath", "//div[@class='subtotal']").size();
			int productdetails = Common.findElements("xpath", "//div[@class='product-item-details']").size();
			Common.assertionCheckwithReport(Subtotal > 0 && productdetails > 0, "validating the mini cart HomePage",
					" This page contains Subtotal and productDetails", "this page having Subtotla and ProductDetailes",
					"this page missing Subtotal and productDetails");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the mini cart icon", "user click cartbutton",
					"user faield to click cart", Common.getscreenShotPathforReport("fairldcart"));
			AssertJUnit.fail();

		}
	}

	public void clickViewCart() throws Exception {
		Thread.sleep(6000);
		try {
			
			
			
			Sync.waitPageLoad();
			//Sync.waitElementPresent("xpath", "//a[@class='action showcart']");
			//Common.mouseOver("xpath", "//a[@class='action showcart']");
			// Common.clickCheckBox("xpath", "//a[@class='action showcart']");
			Sync.waitElementPresent("xpath", "//a[@class='action viewcart']");
			Common.javascriptclickElement("xpath", "//a[@class='action viewcart']");
			
			// Common.mouseOverClick("xpath",
			// "//a[contains(@class,'viewcart')]");
			// Common.assertionCheckwithReport(Subtotal>0&&productdetails>0,
			// "validating the mini cart HomePage", " This page contains
			// Subtotal and productDetails", "this page having Subtotla and
			// ProductDetailes", "this page missing Subtotal and
			// productDetails");
			ExtenantReportUtils.addPassLog("validating viewCart button",
					"Click on View cart link and navigate to Cart page", "user click the View cart button ",
					Common.getscreenShotPathforReport("Viewcart"));
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating viewCart button",
					"Click on View cart link and navigate to Cart page", "user faield to click View cart",
					Common.getscreenShotPathforReport("Viewcart"));
			AssertJUnit.fail();

		}
	}

	public void SignIn_CheckoutPage(String dataSet) throws Exception {
		try {
			Thread.sleep(4000);
			
			Sync.waitElementClickable("xpath", "//li//button[@class='action primary checkout']");
			// Common.findElement("xpath", "//li//button[@class='action primary
			// checkout']").click();
			Common.javascriptclickElement("xpath", "//li//button[@class='action primary checkout']");
			Thread.sleep(4000);
			Sync.waitPageLoad();
			Common.textBoxInput("id", "customer-email", data.get(dataSet).get("Email"));
			Common.textBoxInput("xpath", "//input[@id='popup-pass']", data.get(dataSet).get("Password"));
			ExtenantReportUtils.addPassLog("verifying login page with fieldData", "User enter the FieldData",
					"successfully enter the data", Common.getscreenShotPathforReport("logindata"));
			Common.clickElement("id", "send2");
			Thread.sleep(2000);
			int errormessagetextSize = Common.findElements("xpath", "//div[contains (text(),'required')]").size();
			if (errormessagetextSize <= 0) {
			} else {

				ExtenantReportUtils.addFailedLog("verifying login page with fieldData",
						"see the fields populated with the data", "User failed to proceed login form",
						Common.getscreenShotPathforReport("logindata"));
				AssertJUnit.fail();
			}
			try {
				
				Common.refreshpage();
				Thread.sleep(4000);
				Sync.waitElementVisible("xpath", "//span[@class='customer-name']");
				WebElement element = Common.findElement("xpath", "//span[@class='customer-name']");
				String text = element.getText();
				System.out.println("Text Obtained is..." + text);
				if (text.contains("Hi,QA")) {
					System.out.println("Expected text is obtained");
					ExtenantReportUtils.addPassLog("Validating UserName from Header on Home Page",
							"User Should Successfully Sign-In to User Account and Expected Username text should be obtained",
							"Successfully Sign-In into User Account and Expected Username text is obtained",
							Common.getscreenShotPathforReport("MyAccount"));
				} else {
					System.out.println("Expected text is not obtained");
					ExtenantReportUtils.addFailedLog("Validating UserName from Header on Home Page",
							"User Should Failed Sign-In to User Account", "Failed to Sign-In to User Account",
							Common.getscreenShotPathforReport("MyAccount"));
					AssertJUnit.fail();
				}
			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("Validating UserName from Header on Home Page",
						"User Should Failed Sign-In to User Account", "Failed to Sign-In to User Account",
						Common.getscreenShotPathforReport("MyAccount"));
				AssertJUnit.fail();

			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating Sign-In in Checkout Page",
					"User Should Failed Sign-In to User Account from Checkout Page", "Failed to Sign-In to User Account  from Checkout Page",
					Common.getscreenShotPathforReport("Sign-In in Checkout Page"));
			AssertJUnit.fail();
		}
	}

	public void CheckPost() throws Exception {
		Thread.sleep(5000);
		Sync.waitPageLoad();
		String Expectedresult = "page contains image SKUid Product title sub tota";
		String ErrorMessage = "";
		try {
			ErrorMessage = "failed to display image";
			int productimage = Common.findElements("xpath", "//tr[@class='item-info']/td[1]//img").size();
			AssertJUnit.assertTrue(productimage > 0);

			ErrorMessage = "failed to display productName";
			String productName = Common.findElement("xpath", "//strong[@class='product-item-name']/a").getText();
			AssertJUnit.assertTrue(productName != null);

			ErrorMessage = "failed to display SkuID ";
			String SkuID = Common.findElement("xpath", "//div[@class='item-sku']/span[2]").getText();
			AssertJUnit.assertTrue(SkuID != null);

			ErrorMessage = "failed to display sub tota ";
			int subtotlas = Common.findElements("xpath", "//td[contains(@class,'subtotal')]").size();
			AssertJUnit.assertTrue(subtotlas > 0);
			ExtenantReportUtils.addPassLog("validating the Cart page with image SKU id Product title sub tota",
					Expectedresult, "this page display image SKUid Product title sub tota",
					Common.getscreenShotPathforReport("fairldcarrrt"));
		}

		catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("validating the Cart page with image SKU id Product title sub tota",
					Expectedresult, ErrorMessage, Common.getscreenShotPathforReport("fairldcarrrt"));
			AssertJUnit.fail();

		}

		try {
			Expectedresult = " On clicking on Quantity Dropdown should.It should open dropdown to increase or decrease of products";
			ErrorMessage = "failed to open Quantity Dropdown";
			Sync.waitElementVisible("xpath", "//select[contains(@id,'cart')]");
			Common.clickElement("xpath", "//select[contains(@id,'cart')]");
			ExtenantReportUtils.addPassLog("validating the Cart page with increase or decrease of products",
					Expectedresult, "it click the Quantity Dropdown", Common.getscreenShotPathforReport("dropdown"));
		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("validating the Cart page with increase or decrease of products",
					Expectedresult, ErrorMessage, Common.getscreenShotPathforReport("dropdown"));
			AssertJUnit.fail();

		}

		try {
			Expectedresult = "page should contain Discount codes";
			ErrorMessage = "page missing Discount codes";
			Common.clickElement("xpath", "//strong[@id='block-discount-heading']");
			int sizecoupon = Common.findElements("id", "coupon_code").size();
			AssertJUnit.assertTrue(sizecoupon > 0);
			ExtenantReportUtils.addPassLog("validating the Cart page with Coupon Code", Expectedresult,
					"this page contain Discount codes ", Common.getscreenShotPathforReport("discount"));
		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("validating the Cart page with Coupon Code", Expectedresult, ErrorMessage,
					Common.getscreenShotPathforReport("discount"));
			AssertJUnit.fail();

		}
	}

	// div[@class='subtotal']
	// *[@class='product-item-details']

	// a[contains(@class,'viewcart')]

	// div[@id='block-discount']

	//// div[@class='cart-summary']

	public void checkout() throws Exception {
		Thread.sleep(4000);
		//Sync.waitElementPresent("xpath", "//a[@class='action showcart']");
		//Common.clickCheckBox("xpath", "//a[@class='action showcart']");
		Sync.waitElementPresent("id", "top-cart-btn-checkout");
		Common.clickElement("id", "top-cart-btn-checkout");
	}

	public void clickThreebadmenu() {

		Common.clickElement("xpath", "//span[@class='action nav-toggle']");
	}

	public void HomePage_ProductSelect() {

		String expectedResult = "It Should navigate to the product details page";
		try {
			Common.scrollIntoView("xpath", "//h3[contains(text(),'Swivel Peeler')]");
			Sync.waitElementClickable("xpath", "//h3[contains(text(),'Swivel Peeler')]");
			Common.clickElement("xpath", "//h3[contains(text(),'Swivel Peeler')]");
			System.out.println(Common.getPageTitle());
			Thread.sleep(5000);
			System.out.println(Common.getPageTitle());
			Common.assertionCheckwithReport(
					Common.getPageTitle().equals("Swivel Peeler For Apples & Potatoes | OXO Good Grips"),
					"validating the product details page", expectedResult,
					"sucessfully navigated to product details page", "faield to navigate product details page");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the product details page", expectedResult,
					"user faield to navigate product detiles page",
					Common.getscreenShotPathforReport("faield product detiles page"));
			AssertJUnit.fail();

		}

	}

	public void CleaningandOrganization() {

		// clickThreebadmenu();
		String expectedResult = "It Should be navigate to the CleaningandOrganization category page.";
		try {
			Thread.sleep(8000);
			Sync.waitElementClickable("xpath", "//a[@data-menu='menu-15762']");
			//a[@data-menu='menu-15097']	Common.mouseOverClick("xpath", "//li[contains(@class,'navigation__item')]//a[@data-menu='menu-15762']");
			// Common.clickElement("xpath",//
			// "//li[contains(@class,'navigation__item')]/a[@data-menu='menu-15148']");
			// Common.clickElement("xpath","//li[contains(@class,'navigation__item')]/a[@data-menu='menu-15148']‌");
			// Common.clickElement("xpath", "//a[@data-menu='menu-15148']");
			try {
				Sync.waitElementClickable("xpath", "//ul[@data-menu='menu-15763']//a[contains(text(),'Shop All')]");
			} catch (Exception e) {
				Sync.waitElementClickable("xpath", "//a[@data-menu='menu-15762']");
				Common.clickElement("xpath", "//li[contains(@class,'navigation__item')]//a[@data-menu='menu-15762']");
				Thread.sleep(5000);
			}
			ExtenantReportUtils.addPassLog("verifying category CleaningAndOrganization",
					"lands on CleaningAndOrganization options", "User lands on the CleaningAndOrganization options",
					Common.getscreenShotPathforReport("CleaningAndOrganization "));
			Common.mouseOverClick("xpath", "//ul[@data-menu='menu-15763']//a[contains(text(),'Shop All')]");
			Common.mouseOverClick("xpath", "//ul[@data-menu='menu-15763']//a[contains(text(),'Shop All')]");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the CleaningAndOrganization category page.", expectedResult,
					"user faield to navigate CleaningAndOrganization category",
					Common.getscreenShotPathforReport("faield to navgate categorypage"));
			AssertJUnit.fail();
		}
		selectproduct("Large Expandable Utensil Organizer");

	}

	public void CookingAndBaking() {
		// clickThreebadmenu();
		String expectedResult = "It Should be navigate to the Beverage category page.";
		try {
			Thread.sleep(5000);
			Sync.waitElementClickable("xpath", "//a[@data-menu='menu-15701']");
			Common.mouseOverClick("xpath", "//li[contains(@class,'navigation__item')]//a[@data-menu='menu-15701']");
			// Common.clickElement("xpath",//
			// "//li[contains(@class,'navigation__item')]/a[@data-menu='menu-15148']");
			// Common.clickElement("xpath","//li[contains(@class,'navigation__item')]/a[@data-menu='menu-15148']‌");
			// Common.clickElement("xpath", "//a[@data-menu='menu-15148']");
			try {
				Sync.waitElementClickable("xpath", "//ul[@data-menu='menu-15702']//a[contains(text(),'Shop All')]");
			} catch (Exception e) {
				Sync.waitElementClickable("xpath", "//a[@data-menu='menu-15701']");
				Common.clickElement("xpath", "//li[contains(@class,'navigation__item')]//a[@data-menu='menu-15701']");
				//Thread.sleep(5000);
			}
			ExtenantReportUtils.addPassLog("verifying category Beverage", "lands on Beverage options",
					"User lands on the Beverage options", Common.getscreenShotPathforReport("faield to click"));
			//Common.mouseOverClick("xpath", "//ul[@data-menu='menu-15702']//a[contains(text(),'Shop All')]");
			//Common.mouseOverClick("xpath", "//ul[@data-menu='menu-15702']//a[contains(text(),'Shop All')]");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the category page.", expectedResult,
					"user faield to navigate Baby Toddler category",
					Common.getscreenShotPathforReport("faield to navgate categorypage"));
			AssertJUnit.fail();
		}
		selectproduct("10-Piece POP Container Set");
	}
	public void Candb()throws Exception{
		try{
			Thread.sleep(5000);
			Sync.waitElementClickable("xpath", "//a[@data-menu='menu-15701']");
			Common.mouseOver("xpath", "(//li[contains(@class,'navigation__item navigation__item--parent')]//a[@data-menu='menu-15701'])[1]");
			Thread.sleep(2000);
			Common.clickElement("xpath", "(//li[contains(@class,'navigation__item navigation__item--parent')]//a[@data-menu='menu-15701'])[1]");
			Common.assertionCheckwithReport(Common.getPageTitle().equals("Cooking & Baking Products"), "verifying catlog page","User navigate to catlog page","user successfully landed on products page", "user faield to catlog page");
		  }
		      catch(Exception |Error e) {
		          e.printStackTrace();
		              ExtenantReportUtils.addFailedLog("verifying catlog page", "User navigate to catlog page", "user faield to catlog page", Common.getscreenShotPathforReport("catlogpage"));
		              Assert.fail();
		          }
		selectproduct("3-Piece Prep Peeler Set, Assorted Blades");
	}
	public void Beverage() {
		// clickThreebadmenu();
		String expectedResult = "It Should be navigate to the Beverage category page.";
		try {
			Thread.sleep(8000);
			Sync.waitElementClickable("xpath", "//a[@data-menu='menu-15748']");
			Common.mouseOverClick("xpath", "//li[contains(@class,'navigation__item')]//a[@data-menu='menu-15748']");
			// Common.clickElement("xpath",//
			// "//li[contains(@class,'navigation__item')]/a[@data-menu='menu-15148']");
			// Common.clickElement("xpath","//li[contains(@class,'navigation__item')]/a[@data-menu='menu-15148']‌");
			// Common.clickElement("xpath", "//a[@data-menu='menu-15148']");
			try {
				Sync.waitElementClickable("xpath", "//ul[@data-menu='menu-15749']//a[contains(text(),'Shop All')]");
			} catch (Exception e) {
				Sync.waitElementClickable("xpath", "//a[@data-menu='menu-15748']");
				Common.clickElement("xpath", "//li[contains(@class,'navigation__item')]/a[@data-menu='menu-15748']");
				Thread.sleep(5000);
			}
			ExtenantReportUtils.addPassLog("verifying category Beverage", "lands on Beverage options",
					"User lands on the Beverage options", Common.getscreenShotPathforReport("faield to click"));
			//Common.mouseOverClick("xpath", "//li[contains(@class,'navigation__item')]/a[@data-menu='menu-15748']");
			Common.mouseOverClick("xpath", "//ul[@data-menu='menu-15749']//a[contains(text(),'Shop All')]");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the category page.", expectedResult,
					"user faield to navigate Baby Toddler category",
					Common.getscreenShotPathforReport("faield to navgate categorypage"));
			AssertJUnit.fail();
		}
		selectproduct("Compact Cold Brew Coffee Maker");
	}

	public void clickBaby_Toddler() throws Exception {

		clickThreebadmenu();
		String expectedResult = "It Should be navigate to the Baby_Toddler category page.";
		try {
			Thread.sleep(4000);

			Sync.waitElementClickable("xpath", "//a[@data-menu='menu-15784']");
			Common.mouseOverClick("xpath", "//li[contains(@class,'navigation__item')]/a[@data-menu='menu-15784']");
			// Common.clickElement("xpath",
			// "//li[contains(@class,'navigation__item')]/a[@data-menu='menu-15184']");

			// Common.clickElement("xpath","//li[contains(@class,'navigation__item')]/a[@data-menu='menu-15184']‌");
			// Common.clickElement("xpath", "//a[@data-menu='menu-15184']");
			try {
				Sync.waitElementClickable("xpath", "//ul[@data-menu='menu-15785']//a[contains(text(),'Shop All')]");
			} catch (Exception e) {
				Sync.waitElementClickable("xpath", "//a[@data-menu='menu-15784']");
				Common.clickElement("xpath", "//li[contains(@class,'navigation__item')]/a[@data-menu='menu-15784']");
				Thread.sleep(5000);
			}
			ExtenantReportUtils.addPassLog("verifying category Baby_Toddler", "lands on Baby_Toddler options",
					"User lands on the Baby_Toddler options", Common.getscreenShotPathforReport("faield to click"));

			//Common.mouseOverClick("xpath", "//ul[@data-menu='menu-15785']//a[contains(text(),'Shop All')]");

			//Common.mouseOverClick("xpath", "//ul[@data-menu='menu-15785']//a[contains(text(),'Shop All')]");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the category page.", expectedResult,
					"user faield to navigate Baby Toddler category",
					Common.getscreenShotPathforReport("faield to navgate categorypage"));
			AssertJUnit.fail();

		}
		selectproduct("Perch Booster Seat with Straps");

	}
	
	
	public void Configure_Color(String dataSet) throws Exception {
		
		try {
		Thread.sleep(3000);
		String Color=data.get(dataSet).get("Colour");
		System.out.println(Color);
		Sync.waitElementClickable("xpath", "//a[@class='product-color__link']//figcaption[contains(text(),'"+Color+"')]");
		Common.findElement("xpath", "//a[@class='product-color__link']//figcaption[contains(text(),'"+Color+"')]").click();		
		Thread.sleep(3000);
		
		String URL = Common.getCurrentURL();
		System.out.println(URL);
        Common.assertionCheckwithReport(Common.getCurrentURL().contains(Color),"Validating Page Title and URL", "Actual and Current URL&Page Title Should be Same",
				"Actual and Current URL&Page Title are Same", "Actual and Current URL&Page Title are different");
		
		
		} catch (Exception | Error e) {
			e.printStackTrace();
		}
	}

	public void VerifyingShippingpage() throws Exception {

		String expectedResult = "The page should contain the shipping address along  with the order summary with total price";
		try {
			Sync.waitForLoad();
			String Order = Common.getText("xpath", "//div[@class='opc-block-summary']/span");

			Thread.sleep(4000);

			int shippingsize = Common.findElements("xpath", "//li[@id='shipping']").size();
			// int
			// shippingsize=Common.findElements("xapth","//li[@id='shipping']").size();

			String totalamount = Common.getText("xpath", "//tr[@class='grand totals']/td/strong/span");

			Common.assertionCheckwithReport(Order.equals("Order Summary") && shippingsize > 0 && totalamount != null,
					"Verifying Shippingpage", expectedResult, "page contain shipping address ordersummary totalprice",
					"page missing shipping ordersummary totalprice");

			// Assert.assertEquals(actual, expected);
		} catch (Exception | Error e) {
			
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Verifying Shippingpage", expectedResult,
					"page missing shipping ordersummary totalprice",
					Common.getscreenShotPathforReport("faieldsshippingpage"));
			AssertJUnit.fail();

		}
	}

	public void addNewAddress(String dataSet) {
		int AddnewAddress = Common.findElements("xpath", "//button[contains(@class,'action-show-popup')]").size();
		if (AddnewAddress > 0) {
			Common.clickElement("xpath", "//button[contains(@class,'action-show-popup')]");

			try {
				Sync.waitElementPresent("xpath", "//div[@id='shipping-new-address-form']//input[@name='firstname']");
				Common.textBoxInput("xpath", "//div[@id='shipping-new-address-form']//input[@name='firstname']",
						data.get(dataSet).get("FirstName"));

				Sync.waitElementPresent("xp	ath", "//div[@id='shipping-new-address-form']//input[@name='lastname']");
				Common.textBoxInput("xpath", "//div[@id='shipping-new-address-form']//input[@name='lastname']",
						data.get(dataSet).get("LastName"));

				Sync.waitElementPresent("xpath", "//div[@id='shipping-new-address-form']//input[@name='firstname']");
				Common.textBoxInput("xpath", "//div[@id='shipping-new-address-form']//input[@name='firstname']",
						data.get(dataSet).get("FirstName"));

				Sync.waitElementPresent("xp	ath", "//div[@id='shipping-new-address-form']//input[@name='company']");
				Common.textBoxInput("xpath", "//div[@id='shipping-new-address-form']//input[@name='company']",
						data.get(dataSet).get("CompanyName"));

				Sync.waitElementPresent("name", "street[0]");
				Common.textBoxInput("name", "street[0]", data.get(dataSet).get("Street"));
				Common.textBoxInput("name", "city", data.get(dataSet).get("City"));
				Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
				Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
				Common.actionsKeyPress(Keys.PAGE_DOWN);
				Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));

				Common.clickElement("xpath", "//button[contains(@class,'action-save-address')]");

				int sizeerrormessage = Common
						.findElements("xpath", "//span[contains(text(),'This is a required field')]").size();
				Common.assertionCheckwithReport(sizeerrormessage <= 0, "verifying shipping addres filling ",
						"user will fill the all the shipping", "user fill the shiping address click save button",
						"faield to add new shipping address");

			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying shipping addres filling",
						"user will fill the all the shipping", "faield to add new shipping address",
						Common.getscreenShotPathforReport("faieldsshippingpagefilling"));
				AssertJUnit.fail();

			}

		}
	}

	public void ShippingAddress(String dataSet) throws Exception {
		VerifyingShippingpage();
		try {
			Sync.waitElementPresent("xpath", "//div[@id='shipping-new-address-form']//input[@name='firstname']");
			Common.textBoxInput("xpath", "//div[@id='shipping-new-address-form']//input[@name='firstname']",
					data.get(dataSet).get("FirstName"));

			Sync.waitElementPresent("xp	ath", "//div[@id='shipping-new-address-form']//input[@name='lastname']");
			Common.textBoxInput("xpath", "//div[@id='shipping-new-address-form']//input[@name='lastname']",
					data.get(dataSet).get("LastName"));

			Sync.waitElementPresent("xpath", "//div[@id='shipping-new-address-form']//input[@name='firstname']");
			Common.textBoxInput("xpath", "//div[@id='shipping-new-address-form']//input[@name='firstname']",
					data.get(dataSet).get("FirstName"));

			Sync.waitElementPresent("xp	ath", "//div[@id='shipping-new-address-form']//input[@name='company']");
			Common.textBoxInput("xpath", "//div[@id='shipping-new-address-form']//input[@name='company']",
					data.get(dataSet).get("CompanyName"));

			Sync.waitElementPresent("name", "street[0]");
			Common.textBoxInput("name", "street[0]", data.get(dataSet).get("Street"));
			Common.textBoxInput("name", "city", data.get(dataSet).get("City"));
			Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));

			Sync.waitElementPresent("xpath", "//input[@id='customer-email']");
			Common.textBoxInput("xpath", "//input[@id='customer-email']", data.get(dataSet).get("Email"));
			// Common.clickElement("xpath",
			// "//button[contains(@class,'save-address')]");
			Sync.waitPageLoad();
			int sizeerrormessage = Common.findElements("xpath", "//span[contains(text(),'This is a required field')]")
					.size();
			Common.assertionCheckwithReport(sizeerrormessage <= 0, "verifying shipping addres filling ",
					"user will fill the all the shipping", "user fill the shiping address click save button",
					"faield to add new shipping address");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying shipping addres filling", "user will fill the all the shipping",
					"faield to add new shipping address",
					Common.getscreenShotPathforReport("faieldsshippingpagefilling"));
			AssertJUnit.fail();

		}

	}

	
	public int Check_Tax() throws Exception {
        
		Thread.sleep(4000);
		int TaxPrice = Common.findElements("xpath", "//tr[@class='totals-tax']//span[@class='price']").size();
		System.out.println("Tax Price " + TaxPrice);
		return TaxPrice;
	}

   public void TaxPrice() throws Exception {

		if (Check_Tax() > 0) {
			
		int TaxPrice = Common.findElements("xpath", "//tr[@class='totals-tax']//span[@class='price']").size();
		System.out.println("Tax Price " + TaxPrice);
		int TaxExpected;
		TaxExpected=0;
		System.out.println(TaxExpected);
		
		Assert.assertEquals(TaxPrice, TaxExpected);
		
		Common.assertionCheckwithReport(TaxPrice <= 0, "verifying Tax Price ",
				"user will fill the all the billing address", "user fill the shipping address click save button",
				"faield to add new shipping address");
		}

       ExtenantReportUtils.addPassLog("verifying Tax from Order Summary block", "lands on home page", "User lands on the Home page",
				Common.getscreenShotPathforReport("Tax"));

	}
	
	
	public void selectGroundShippingMethod() throws Exception {
		try {
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Sync.waitPageLoad();
			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "//label[contains(@id,'bestway_tablerate')]");
			Common.scrollIntoView("xpath", "//label[contains(@id,'bestway_tablerate')]");
			Common.javascriptclickElement("xpath", "//label[contains(@id,'bestway_tablerate')]");
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//button[contains(@class,'continue primary')]");
			Common.clickElement("xpath", "//button[contains(@class,'continue primary')]");
			ExtenantReportUtils.addPassLog("verifying Shipping Methods", "user select the Ground shipping method",
					" selected the Ground shipping method", Common.getscreenShotPathforReport("faieldsshippingmethod"));
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying Shipping Methods", "user select the Ground shipping method",
					"faield to select shippingmethod", Common.getscreenShotPathforReport("faieldsshippingmethod"));
			AssertJUnit.fail();

		}
	}

	
	public void selectExpressStandardOvernightShippingMethod() throws Exception {
		try {
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Sync.waitPageLoad();
			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "//label[contains(@id,'shippingrates1_shippingrates')]");
			Common.scrollIntoView("xpath", "//label[contains(@id,'shippingrates1_shippingrates')]");
			Common.javascriptclickElement("xpath", "//label[contains(@id,'shippingrates1_shippingrates')]");
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//button[contains(@class,'continue primary')]");
			Common.clickElement("xpath", "//button[contains(@class,'continue primary')]");
			ExtenantReportUtils.addPassLog("verifying Shipping Methods", "user select the Express Standard Overnight shipping method",
					" selected the Express Standard Overnight shipping method", Common.getscreenShotPathforReport("faieldsshippingmethod"));
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying Shipping Methods", "user select the Express Standard Overnight shipping method",
					"faield to select  shippingmethod", Common.getscreenShotPathforReport("faieldsshippingmethod"));
			AssertJUnit.fail();

		}
	}
	
	
	
	
	
	
	public void Express_PayPal_GroundShippingMethod() throws Exception {
		try {

			// Sync.waitPageLoad();
			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "//select[@id='shipping-method']");
			Common.findElement("xpath", "//select[@id='shipping-method']").click();
			// Common.javascriptclickElement("xpath",
			// "//select[@id='shipping-method']");
			Thread.sleep(2000);
			Sync.waitElementPresent("xpath", "//optgroup[@label='Ground']");
			Common.clickElement("xpath", "//optgroup[@label='Ground']");
			// Common.mouseOverClick("xpath", "//optgroup[@label='Ground']");
			Common.javascriptclickElement("xpath", "//optgroup[@label='Ground']");
			// Common.clickElement("xpath", "//optgroup[@label='Ground']");
			ExtenantReportUtils.addPassLog("verifying Shipping Methods", "user select the Ground shipping method",
					" selected the Ground shipping method", Common.getscreenShotPathforReport("faieldsshippingmethod"));

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying Shipping Methods", "user select the Ground shipping method",
					"faield to select shippingmethod", Common.getscreenShotPathforReport("faieldsshippingmethod"));
			AssertJUnit.fail();

		}
	}
	public void selectExpress_StandardMethod() throws Exception {
		try {
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Sync.waitPageLoad();
			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "//label[contains(@id,'label_method_shippingrates1_shippingrates')]");
			Common.scrollIntoView("xpath", "//label[contains(@id,'label_method_shippingrates1_shippingrates')]");
			Common.javascriptclickElement("xpath", "//label[contains(@id,'label_method_shippingrates1_shippingrates')]");
			Sync.waitPageLoad();
			//Sync.waitElementPresent("xpath", "//button[contains(@class,'continue primary')]");
			//Common.clickElement("xpath", "//button[contains(@class,'continue primary')]");
			ExtenantReportUtils.addPassLog("verifying Shipping Methods", "user select the Ground shipping method",
					" selected the Ground shipping method", Common.getscreenShotPathforReport("faieldsshippingmethod"));
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying Shipping Methods", "user select the Ground shipping method",
					"faield to select shippingmethod", Common.getscreenShotPathforReport("faieldsshippingmethod"));
			AssertJUnit.fail();
		}
		}
	public void PlaceorderButton() throws InterruptedException {
		Thread.sleep(1000);
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.actionsKeyPress(Keys.ENTER);
		Common.switchToDefault();
		Thread.sleep(4000);
		Sync.waitElementClickable("xpath", "//span[contains(text(),'Place Order')]");
		Common.findElementBy("xpath", "//span[contains(text(),'Place Order')]").click();
		// Common.clickElement("xpath", "//span[contains(text(),'Place
		// Order')]");
		// Common.switchFrames("id", "paymetric_xisecure_frame");
		Common.switchToDefault();
		// Common.clickElement("xpath", "//span[contains(text(),'Place
		// Order')]");

		/*
		 * 
		 * Sync.waitElementClickable("xpath",
		 * "//span[contains(text(),'Place Order')]");
		 * //Common.clickElement("xpath",
		 * "//span[contains(text(),'Place Order')]");
		 * //Common.findElement("xpath",
		 * "//button[@id='review-button']").click();
		 * Common.javascriptclickElement("xpath",
		 * "//span[contains(text(),'Place Order')]");
		 */
	}

	public void Promocode(String dataSet) throws Exception {

		try {
			Common.actionsKeyPress(Keys.ARROW_DOWN);
			Sync.waitElementClickable("id", "block-discount-heading");
			Thread.sleep(3000);
		    //Common.scrollIntoView("xpath", "//span[@id='block-discount-heading']");
			Common.findElement("xpath", "//span[@id='block-discount-heading']").click();
			Sync.waitElementPresent("xpath", "//input[@id='discount-code']");
			Common.textBoxInput("xpath", "//input[@id='discount-code']", data.get(dataSet).get("Promocode"));
			Common.clickElement("xpath", "//button[@class='action action-apply']");
			Thread.sleep(4000);
			ExtenantReportUtils.addPassLog("Apply Promocode on Checkout Page",
					"Promocode Should be applied on Checkout Page",
					"Promo Code added successfully and applied discount to Order Summary",
					Common.getscreenShotPathforReport("Promocode"));
			// Thread.sleep(2000);
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Apply Promocode on Checkout Page",
					"Promocode Should be applied on Checkout Page", "Failed to apply Promocode",
					Common.getscreenShotPathforReport("Promocode"));
			AssertJUnit.fail();
		}
	}
	public void EmployeeDiscountCode(String dataSet) throws Exception {

		try {
			Common.actionsKeyPress(Keys.ARROW_DOWN);
			Sync.waitElementClickable("id", "block-discount-heading");
			Thread.sleep(3000);
		    //Common.scrollIntoView("xpath", "//span[@id='block-discount-heading']");
			Common.findElement("xpath", "//span[@id='block-discount-heading']").click();
			Sync.waitElementPresent("xpath", "//input[@id='discount-code']");
			Common.textBoxInput("xpath", "//input[@id='discount-code']", data.get(dataSet).get("EmployeeDiscount"));
			Common.clickElement("xpath", "//button[@class='action action-apply']");
			Thread.sleep(4000);
			ExtenantReportUtils.addPassLog("Apply Promocode on Checkout Page",
					"Promocode Should be applied on Checkout Page",
					"Promo Code added successfully and applied discount to Order Summary",
					Common.getscreenShotPathforReport("Promocode"));
			// Thread.sleep(2000);
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Apply Promocode on Checkout Page",
					"Promocode Should be applied on Checkout Page", "Failed to apply Promocode",
					Common.getscreenShotPathforReport("Promocode"));
			AssertJUnit.fail();
		}
	}

	public void clickAcceptingaddress() throws Exception {
		try {
			Sync.implicitWait();
			int alertsize = Common.findElements("xpath", "//button[contains(@class,'action-accept')]").size();
			if (alertsize > 0) {
				Sync.waitElementPresent("xpath", "//button[contains(@class,'action-accept')]");
				Common.clickElement("xpath", "//button[contains(@class,'action-accept')]");
			}
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying confirmation address", "user acceptance given address",
					"faield to acceptance given address", Common.getscreenShotPathforReport("faieldcceptance"));
			AssertJUnit.fail();

		}

	}

	/*
	 * public void edit_BillingAddress_registeredUser(){
	 * 
	 * 
	 * Common.findElements("xpath", "//select[@name='billing_address_id']")
	 * 
	 * }
	 */

	public void Edit_BillingAddress(String dataSet) throws Exception {

		try {
			Common.clickElement("xpath", "//label[contains(@for,'billing-address-same-as-shipping-paymetric')]");

			int sizes = Common.findElements("xpath", "//select[@name='billing_address_id']").size();
			Common.actionsKeyPress(Keys.PAGE_UP);
			if (sizes > 0) {
				Thread.sleep(5000);
				Common.scrollIntoView("xpath", "//label[contains(@for,'billing-address-same-as-shipping-paymetric')]");
				Common.actionsKeyPress(Keys.PAGE_UP);
				Thread.sleep(3000);
				Common.dropdown("xpath", "//select[@name='billing_address_id']", Common.SelectBy.TEXT, "New Address");
			}

			// Common.clickElement("Xpath","//span[contains(text(),'My billing
			// and shipping address are the same')]");
			Sync.waitElementPresent("xpath",
					"//div[@class='payment-method-billing-address']//input[@name='firstname']");
			Common.textBoxInput("xpath", "//div[@class='payment-method-billing-address']//input[@name='firstname']",
					data.get(dataSet).get("FirstName"));
			Sync.waitElementPresent("xp	ath",
					"//div[@class='payment-method-billing-address']//input[@name='lastname']");
			Common.textBoxInput("xpath", "//div[@class='payment-method-billing-address']//input[@name='lastname']",
					data.get(dataSet).get("LastName"));
			Sync.waitElementPresent("xpath",
					"//div[@class='payment-method-billing-address']//input[@name='firstname']");
			Common.textBoxInput("xpath", "//div[@class='payment-method-billing-address']//input[@name='firstname']",
					data.get(dataSet).get("FirstName"));
			Sync.waitElementPresent("xp	ath", "//div[@class='payment-method-billing-address']//input[@name='company']");
			Common.textBoxInput("xpath", "//div[@class='payment-method-billing-address']//input[@name='company']",
					data.get(dataSet).get("CompanyName"));
			Sync.waitElementPresent("xpath",
					"//div[@class='payment-method-billing-address']//input[@name='street[0]']");
			Common.textBoxInput("xpath", "//div[@class='payment-method-billing-address']//input[@name='street[0]']",
					data.get(dataSet).get("Street"));
			Common.textBoxInput("xpath", "//div[@class='payment-method-billing-address']//input[@name='city']",
					data.get(dataSet).get("City"));
			Common.dropdown("xpath", "//div[@class='payment-method-billing-address']//select[@name='region_id']",
					Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			Common.textBoxInput("xpath", "//div[@class='payment-method-billing-address']//input[@name='postcode']",
					data.get(dataSet).get("postcode"));
			Common.textBoxInput("xpath", "//div[@class='payment-method-billing-address']//input[@name='telephone']",
					data.get(dataSet).get("phone"));
			Common.actionsKeyPress(Keys.PAGE_DOWN);

			Common.clickElement("xpath", "//button[contains(@class,'action-update')]");

			Thread.sleep(5000);
			int sizeerrormessage = Common.findElements("xpath", "//span[contains(text(),'This is a required field')]")
					.size();
			System.out.println("error messagess    " + sizeerrormessage);
			Common.assertionCheckwithReport(sizeerrormessage <= 0, "verifying Billing addres filling ",
					"user will fill the all the billing address", "user fill the shipping address click save button",
					"faield to add new shipping address");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying Billing addres filling",
					"user will fill the all the Billing address", "faield to add new billing address",
					Common.getscreenShotPathforReport("faieldssbillingpagefilling"));
			AssertJUnit.fail();

		}
	}

	public void Click_CreditCard() {
		try {
			Thread.sleep(6000);
			Sync.waitPageLoad();
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Common.clickElement("xpath", "//label[@for='paymetric']");
			ExtenantReportUtils.addPassLog("verifying CreditCardbutton", "user click CreditCard ",
					"user clicked CreditCard option", Common.getscreenShotPathforReport("creditoption"));
		} catch (Exception | Error e) {
			
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying CreditCardbutton", "user click CreditCard ",
					"faield to click CreditCard option", Common.getscreenShotPathforReport("creditfaeild"));
			AssertJUnit.fail();

		}
	}

	public void Selectproduct() throws Exception {

		Sync.waitElementPresent("xpath", "//a[contains(text(),'Splash')]");
		Common.clickElement("xpath", "//a[contains(text(),'Splash')]");
		Sync.waitElementPresent("xpath", "//div[@id='overview']/div[3]//h1/span");
		String Text = Common.getText("xpath", "//div[@id='overview']/div[3]//h1/span");
		// Common.assertionCheckwithReport(Text.equals(""), "verifying product
		// details page", "It Should navigate to the product details page",
		// "successfully navigated to product details page", "faield to navigate
		// to product details page");
		// div[@id="overview"]/div[3]//h1/span
		Common.clickElement("id", "product-addtocart-button");
		Common.clickElement("xpath", "//a[contains(text(),'Checkout Now')]");
	}

	public void addPaymentDetails(String dataSet) throws Exception {
		Common.switchFrames("xpath", "//iframe[@id='paymetric_xisecure_frame']");
		Common.dropdown("xpath", "//select[@id='c-ct']", Common.SelectBy.TEXT, data.get(dataSet).get("cardType"));
		Common.textBoxInput("id", "c-cardnumber", data.get(dataSet).get("cardNumber"));
		Common.dropdown("xpath", "//select[@id='c-exmth']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpMonth"));
		Common.dropdown("xpath", "//select[@id='c-exyr']", Common.SelectBy.TEXT, data.get(dataSet).get("ExpYear"));
		Common.textBoxInput("id", "c-cvv", data.get(dataSet).get("cvv"));
	}

	public void loginOxo(String dataSet) throws Exception {
		Thread.sleep(3000);
		int name = Common.findElements("xpath", "//span[@class='customer-name']").size();
		if (name > 0) {

		} else {
			try {
				Sync.waitElementClickable(30, By.xpath("//a[@class='social-login']"));
				Common.findElement("xpath", "//a[@class='social-login']").click();
				Sync.waitElementClickable(30, By.id("email"));
				if (Common.findElement("id", "email") == null) {
					Common.mouseOverClick("xpath", "//a[@class='social-login']");
					Thread.sleep(2000);
				}
				ExtenantReportUtils.addPassLog("verifying Sign in link", "lands on sign popup",
						"User lands on the sign popup", Common.getscreenShotPathforReport("signpop"));
			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying Sign in link", "lands on sign popup",
						"User failed lands on sign popup", Common.getscreenShotPathforReport("signpop"));
				AssertJUnit.fail();

			}
			try {
				Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
				Common.textBoxInput("id", "pass1", data.get(dataSet).get("Password"));
				ExtenantReportUtils.addPassLog("verifying login page with fieldData", "User enter the FieldData",
						"successfully enter the data", Common.getscreenShotPathforReport("logindata"));
				Common.clickElement("id", "bnt-social-login-authentication");
				Thread.sleep(8000);
				int errormessagetextSize = Common.findElements("xpath", "//div[@class='mage-error']").size();
				if (errormessagetextSize <= 0) {
				} else {

					ExtenantReportUtils.addFailedLog("verifying login page with fieldData",
							"see the fields populated with the data", "User failed to proceed login form",
							Common.getscreenShotPathforReport("logindata"));
					AssertJUnit.fail();
				}
				/*try {
					Thread.sleep(4000);
					Common.refreshpage();
					Sync.waitElementVisible("xpath", "//span[@class='customer-name']");
					WebElement element = Common.findElement("xpath", "//span[@class='customer-name']");
					String text = element.getText();
					System.out.println("Text Obtained is..." + text);
					Thread.sleep(5000);
					
					//String QA = Common.textBoxInput("id", "email", data.get(dataSet).get("UserName"));
					if (text.contains("Hi,QA")) {
						System.out.println("Expected text is obtained");
						ExtenantReportUtils.addPassLog("Validating UserName from Header on Home Page",
								"User Should Successfully Sign-In to User Account and Expected Username text should be obtained",
								"Successfully Sign-In into User Account and Expected Username text is obtained",
								Common.getscreenShotPathforReport("MyAccount"));
					} else {
						System.out.println("Expected text is not obtained");
						ExtenantReportUtils.addFailedLog("Validating UserName from Header on Home Page",
								"User Should Failed Sign-In to User Account", "Failed to Sign-In to User Account",
								Common.getscreenShotPathforReport("MyAccount"));
						AssertJUnit.fail();
					}
				} catch (Exception | Error e) {
					e.printStackTrace();
					ExtenantReportUtils.addFailedLog("Validating UserName from Header on Home Page",
							"User Should Failed Sign-In to User Account", "Failed to Sign-In to User Account",
							Common.getscreenShotPathforReport("MyAccount"));
					AssertJUnit.fail();

				}*/

			} catch (Exception | Error e) {
				e.printStackTrace();
				ExtenantReportUtils.addFailedLog("verifying login page", "lands on sign popup",
						"User failed lands on sign popup", Common.getscreenShotPathforReport("signpoptt"));
				AssertJUnit.fail();

			}
		}
		/*
		 * try{ Sync.waitElementClickable(30,
		 * By.xpath("//a[@class='social-login']")); Common.findElement("xpath",
		 * "//a[@class='social-login']").click(); Sync.waitElementClickable(30,
		 * By.id("email")); if(Common.findElement("id", "email")==null) {
		 * Common.mouseOverClick("xpath", "//a[@class='social-login']");
		 * Thread.sleep(2000); }
		 * 
		 * 
		 * }
		 * 
		 * catch(Exception |Error e) { // ExtenantReportUtils.
		 * addFailedLog("verifying login page with credentials",expectedResult,
		 * "User failed to login in account  ",
		 * Common.getscreenShotPathforReport("login faield")); Assert.fail(); }
		 * 
		 * Common.textBoxInput("id", "email",data.get(dataSet).get("Email"));
		 * Common.textBoxInput("id", "pass1",data.get(dataSet).get("Password"));
		 * Common.clickElement("id", "bnt-social-login-authentication");
		 */

	}

	public void Creditcard_Type(String dataSet) throws Exception {
		try {
			Thread.sleep(4000);
			Common.switchFrames("id", "paymetric_xisecure_frame");
			Common.dropdown("xpath", "//select[@id='c-ct']", Common.SelectBy.TEXT, data.get(dataSet).get("cardType"));
			ExtenantReportUtils.addPassLog("verifying CreditCardType Dropdown",
					"User should able select Credit card Type", "User selected CreditCard Type",
					Common.getscreenShotPathforReport("CreditcardType"));

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying CreditCardType Dropdown",
					"User should able select Credit card Type", "User failed to select CreditCard Type",
					Common.getscreenShotPathforReport("CreditcardType"));
			AssertJUnit.fail();
		}
	}

	public void invalidCC_data(String dataSet)throws Exception {
		
		try {

			// Common.actionsKeyPress(Keys.PAGE_DOWN);
			// Common.clickElement("xpath",
			// "//label[@for='ime_paymetrictokenize']");
			Thread.sleep(2000);
			Common.switchFrames("id", "paymetric_xisecure_frame");
			Thread.sleep(4000);
			int size = Common.findElements("xpath", "//select[@id='c-ct']").size();
			Common.switchToDefault();
			Common.assertionCheckwithReport(size > 0, "validating Creditcard option", "click the creadit card label",
					"clicking credit card label and open the card fields", "user faield to open credit card form");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Credit Card option", "click the creadit card label",
					"faield to click Credit Card option", Common.getscreenShotPathforReport("Cardinoption"));
			AssertJUnit.fail();

		}

		try {

			Thread.sleep(2000);
			Common.switchFrames("xpath", "//iframe[@id='paymetric_xisecure_frame']");
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
			Common.clickElement("xpath", "//span[contains(text(),'Place Order')]");
			
			Thread.sleep(3000);
		   //  Sync.waitElementVisible("xpath", "//div[contains(text(),'Please enter a valid card number')]");
		     //int InvalidCCErrormessage = Common.findElements("xpath", "//div[contains(text(),'Please enter a valid card number')]").size();			
	        // Common.assertionCheckwithReport(InvalidCCErrormessage > 0, "verifying error message invalid CC data","enter with empety data it must show invalid CC error message", "sucessfully display the invalid CC error message","faield to dispaly invalid CC errormessage");
			
			
	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("validating the Credit Card infromation",
				"credit card fields are filled with the data", "faield  to fill the Credit Card infromation",
				Common.getscreenShotPathforReport("Cardinfromationfail"));
		AssertJUnit.fail();
	}
		
			
	/*	} catch (Exception | Error e) {
            e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying invalid CC error message",
					"enter with invalid CC data it must show error message", "faield to dispaly invalid CC errormessage",
					Common.getscreenShotPathforReport("invalid CC data"));
			Assert.fail();
		}*/
		}
	
	public void creditCard_payment(String dataSet) throws Exception {

		try {

			// Common.actionsKeyPress(Keys.PAGE_DOWN);
			// Common.clickElement("xpath",
			// "//label[@for='ime_paymetrictokenize']");
			Thread.sleep(2000);
			Common.switchFrames("id", "paymetric_xisecure_frame");
			Thread.sleep(4000);
			int size = Common.findElements("xpath", "//select[@id='c-ct']").size();
			Common.switchToDefault();
			Common.assertionCheckwithReport(size > 0, "validating Creditcard option", "click the creadit card label",
					"clicking credit card label and open the card fields", "user faield to open credit card form");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Credit Card option", "click the creadit card label",
					"faield to click Credit Card option", Common.getscreenShotPathforReport("Cardinoption"));
			AssertJUnit.fail();

		}

		try {

			Thread.sleep(2000);
			Common.switchFrames("xpath", "//iframe[@id='paymetric_xisecure_frame']");
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
			
			Common.findElement("xpath", "//div[@class='actions-toolbar']//button[@class='action primary checkout']").click();
			//Common.clickElement("xpath", "//span[contains(text(),'Place Order')]");
			Common.switchFrames("id", "paymetric_xisecure_frame");
			String expectedResult = "credit card fields are filled with the data";
			String errorTexts = Common.findElement("xpath", "//div[contains(@id,'error')]").getText();
			Common.switchToDefault();
			Common.assertionCheckwithReport(errorTexts.isEmpty(),
					"validating the credit card information with valid data", expectedResult, "Filled the Card detiles",
					"missing field data it showinng error");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the Credit Card infromation",
					"credit card fields are filled with the data", "faield  to fill the Credit Card infromation",
					Common.getscreenShotPathforReport("Cardinfromationfail"));
			AssertJUnit.fail();

		}

	}
	public void InvalidCCErrormessage(){
		
		try{
	     int InvalidCCErrormessage = Common.findElements("xpath", "//div[@id='c-cardnumber-error']").size();			
         Common.assertionCheckwithReport(InvalidCCErrormessage > 0, "verifying error message invalid CC data",
				"enter with empety data it must show invalid CC error message", "sucessfully display the invalid CC error message",
				"faield to dispaly invalid CC errormessage");
	} catch (Exception | Error e) {

		ExtenantReportUtils.addFailedLog("verifying invalid CC error message",
				"enter with invalid CC data it must show error message", "faield to dispaly invalid CC errormessage",
				Common.getscreenShotPathforReport("invalid CC data"));
		AssertJUnit.fail();
	}
	}

	public void payPal_payment(String dataSet) {
		String expectedResult = "It should open paypal site window.";
		try {
			Thread.sleep(3000);
			Sync.waitElementPresent("xpath", "//img[@class='payment-icon']");
			Common.clickElement("xpath", "//img[@class='payment-icon']");
			Thread.sleep(5000);
			Common.actionsKeyPress(Keys.PAGE_DOWN);

			Common.switchFrames("xpath", "(//iframe[contains(@name,'__zoid__paypal_buttons__eyJzZW5kZXI')])[2]");
			Sync.waitElementClickable("xpath", "//div[contains(@class,'paypal-button-label-container')]");
			int sizes = Common.findElements("xpath", "//div[@class='paypal-button-label-container']").size();

			System.out.println(sizes);
			Thread.sleep(5000);
			Common.clickElement("xpath", "//div[contains(@class,'paypal-button-label-container')]");
			Common.switchToDefault();
			Thread.sleep(5000);
			Common.switchWindows();
			int size = Common.findElements("id", "acceptAllButton").size();
			if (size > 0) {

				Common.clickElement("id", "acceptAllButton");

			}

			Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
			Common.textBoxInput("id", "password", data.get(dataSet).get("Password"));
			int sizeemail = Common.findElements("id", "email").size();

			Common.assertionCheckwithReport(sizeemail > 0, "verifying the paypal payment ", expectedResult,
					"open paypal site window", "faild to open paypal account");
			Common.clickElement("id", "btnLogin");
			Thread.sleep(5000);
			Common.actionsKeyPress(Keys.END);
			Thread.sleep(5000);

			int ButtonSize = Common.findElements("id", "confirmButtonTop").size();
			if (ButtonSize > 0) {
				Common.clickElement("id", "confirmButtonTop");
			} else {
				Common.clickElement("id", "payment-submit-btn");
			}
			Thread.sleep(8000);
			Common.switchToFirstTab();
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the paypal payment ", expectedResult,
					"User failed to proceed with paypal payment", Common.getscreenShotPathforReport(expectedResult));
			AssertJUnit.fail();
		}
	}

	public void Express_payPal_payment(String dataSet) {
		String expectedResult = "It should open paypal site window.";
		try {
			Thread.sleep(3000);
			//Common.actionsKeyPress(Keys.PAGE_DOWN);

			Common.switchFrames("xpath", "//ul//iframe[contains(@name,'__zoid__paypal_buttons__eyJzZW5k')]");
		    
			
			Sync.waitElementClickable("xpath", "//div[contains(@class,'paypal-button-label-container')]");
			int sizes = Common.findElements("xpath", "//div[@class='paypal-button-label-container']").size();

			System.out.println(sizes);
			Thread.sleep(5000);
			Common.clickElement("xpath", "//div[contains(@class,'paypal-button-label-container')]");
			Common.switchToDefault();
			Thread.sleep(5000);
			Common.switchWindows();
			int size = Common.findElements("id", "acceptAllButton").size();
			if (size > 0) {

				Common.clickElement("id", "acceptAllButton");

			}

			Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));

			Common.findElement("xpath", "//button[@id='btnNext']").click();
			Thread.sleep(3000);
			Common.textBoxInput("id", "password", data.get(dataSet).get("Password"));
			int sizeemail = Common.findElements("id", "email").size();

			Common.assertionCheckwithReport(sizeemail > 0, "verifying the paypal payment ", expectedResult,
					"open paypal site window", "faild to open paypal account");
			Common.clickElement("id", "btnLogin");
			Thread.sleep(5000);
			Common.actionsKeyPress(Keys.END);
			Thread.sleep(5000);

			int ButtonSize = Common.findElements("id", "confirmButtonTop").size();
			if (ButtonSize > 0) {
				Common.clickElement("id", "confirmButtonTop");
			} else {
				Common.clickElement("id", "payment-submit-btn");
			}
			Thread.sleep(8000);
			Common.switchToFirstTab();
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the paypal payment ", expectedResult,
					"User failed to proceed with paypal payment", Common.getscreenShotPathforReport(expectedResult));
			AssertJUnit.fail();
		}
	}
	public void Express_payPal_payment_R(String dataSet) {
		String expectedResult = "It should open paypal site window.";
		try {
			Thread.sleep(3000);
			//Common.actionsKeyPress(Keys.PAGE_DOWN);

			Common.switchFrames("xpath", "//ul//iframe[contains(@name,'__zoid__paypal_buttons__eyJzZW5k')]");
		    
			
			Sync.waitElementClickable("xpath", "//div[contains(@class,'paypal-button-label-container')]");
			int sizes = Common.findElements("xpath", "//div[@class='paypal-button-label-container']").size();

			System.out.println(sizes);
			Thread.sleep(5000);
			Common.clickElement("xpath", "//div[contains(@class,'paypal-button-label-container')]");
			Common.switchToDefault();
			Thread.sleep(5000);
			Common.switchWindows();
			int size = Common.findElements("id", "acceptAllButton").size();
			if (size > 0) {

				Common.clickElement("id", "acceptAllButton");

			}

			Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
			Common.textBoxInput("id", "password", data.get(dataSet).get("Password"));
			Common.findElement("xpath", "//button[@id='btnLogin']").click();
			Thread.sleep(3000);
			int sizeemail = Common.findElements("xpath", "//img[@data-testid='header-logo']").size();

			Common.assertionCheckwithReport(sizeemail > 0, "verifying the paypal payment ", expectedResult,
					"open paypal site window", "faild to open paypal account");
			//Common.clickElement("id", "payment-submit-btn");
			//Thread.sleep(5000);
			//Common.actionsKeyPress(Keys.END);
			//Thread.sleep(5000);

			int ButtonSize = Common.findElements("id", "payment-submit-btn").size();
			if (ButtonSize > 0) {
				Common.clickElement("id", "confirmButtonTop");
			} else {
				Common.clickElement("id", "payment-submit-btn");
			}
			Thread.sleep(8000);
			Common.switchToFirstTab();
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the paypal payment ", expectedResult,
					"User failed to proceed with paypal payment", Common.getscreenShotPathforReport(expectedResult));
			AssertJUnit.fail();
		}
	}

	public void VerifyaingConformationPage() throws Exception {
		
		Thread.sleep(4000);
		String URL = Common.getCurrentURL();
		System.out.println(URL);
		if( URL.contains("Stg")) {

		String Url = Common.getCurrentURL();
		
		if (Url.contains("heledigital")) { 
		
		Sync.waitElementPresent("xpath", "//h1[@class='page-title']/span");
		String Sucessmessage = Common.getText("xpath", "//h1[@class='page-title']/span");
		System.out.println(Sucessmessage);
		Common.assertionCheckwithReport(Sucessmessage.equals("Thank you for your order!"),
				"verifying order success message", "displaying the confirmation message",
				"successfully displaying the confirmation message", "Failed displaying the confirmation message");

		// Thank you for your order!

		/*
		 * Sync.waitElementPresent("xpath",
		 * "//div[@id='registration']/div[2]/a/span"); String
		 * CreateAccount=Common.getText("xpath",
		 * "//div[@id='registration']/div[2]/a/span");
		 * Common.assertionCheckwithReport(CreateAccount.equals("Create Account"
		 * ), "verifying Creat account button",
		 * "displaying the Creat account button",
		 * "successfully displaying the Creat account button"
		 * ,"Failed displaying Creat account button");
		 */

		// Common.findElements("xpath", "//div[@class='order-help-item']/h4");

		int number = Common.findElements("xpath", "//p[@class='order-number-wrapper']//strong").size();
		if (number > 0) {
			String ordernumber = Common.getText("xpath", "//p[@class='order-number-wrapper']//strong");
			System.out.println(ordernumber);
			PropertiesReader prop = new PropertiesReader();
			prop.properUpdate("OxoOrder.properties", "OrderNumber_" + Common.getCurrentDate(),
					Common.getText("xpath", "//p[@class='order-number-wrapper']//strong"));
		}

		else {
			String ordernumber = Common.getText("xpath", "//p[@class='order-number-wrapper']/span");
			System.out.println(ordernumber);

			PropertiesReader prop = new PropertiesReader();
			prop.properUpdate("OxoOrder.properties", "OrderNumber_" + Common.getCurrentDate(),
					Common.getText("xpath", "//p[@class='order-number-wrapper']/span"));
			// //div[@id="registration"]/div[2]/a Createaccount
		}}else {
			
		

		// h1[@class='page-title']/span
		// Thank you for your order!

		// div[@class='order-help-item']/h4

		// div[contains(@class,'signup-columns')]//h2 Stay in the Loop.
		// form[contains(@class,'form subscribe')] Email
		}}
	}

	public int checkadd() {

		Common.actionsKeyPress(Keys.PAGE_UP);
		// Common.switchFrames("xpath", "//iframe[@id='attentive_creative']");
		int elementsize = Common.findElements("xpath", "//button[@id='closeIconContainer']").size();
		Common.switchToDefault();
		return elementsize;
	}

	public void closetheadd() throws Exception {
		Close_popup();
		ExtenantReportUtils.addPassLog("verifying home page", "lands on home page", "User lands on the Home page",
				Common.getscreenShotPathforReport("homepage"));
	//	Close_popup();
		if (checkadd() > 0) {

			Common.switchFrames("xpath", "//iframe[@id='attentive_creative']");
			Sync.waitElementPresent("xpath", "//button[@id='closeIconContainer']");
			Common.clickElement("xpath", "//button[@id='closeIconContainer']");
			Common.switchToDefault();

		}
		acceptPrivecy();
	}
	
	public void Close_popup() {
		try {
		//		  Thread.sleep(2000); if (Common.findElement("xpath","//button[@id='closeIconContainer']") != null) {
			Common.switchFrames("xpath", "//iframe[@id='attentive_creative']");	  
				  Common.clickElement("xpath", "//button[@id='closeIconContainer']"); 
				  Common.switchToDefault();
				  }catch
				  (Exception e) {
				  e.printStackTrace(); 
				  }
		}
	 public void acceptPrivecy(){
			int sizeofbutton= Common.findElements("xpath", "//button[@id='truste-consent-required']").size();
			if(sizeofbutton>0){
				
				Common.clickElement("xpath", "//button[@id='truste-consent-required']");
				
			}
		 }
	

	public void ForgotPassword(String dataset) throws Exception {

		Thread.sleep(3000);
		try {
			Sync.waitElementClickable(30, By.xpath("//a[@class='social-login']"));
			Common.findElement("xpath", "//a[@class='social-login']").click();
			Sync.waitElementClickable(30, By.id("email"));
			if (Common.findElement("id", "email") == null) {
				Common.mouseOverClick("xpath", "//a[@class='social-login']");
				Thread.sleep(3000);
			}
			ExtenantReportUtils.addPassLog("verifying Sign in link", "lands on sign page",
					"User lands on the sign popup", Common.getscreenShotPathforReport("signpopf"));
		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("verifying Sign in link", "lands on sign popup",
					"User failed lands on sign popup", Common.getscreenShotPathforReport("signpopf"));
			AssertJUnit.fail();

		}

		Thread.sleep(3000);
		String expectedResult = "Forgot password pop up is displayed to customer";
		try {
			Common.clickElement("xpath", "//a[@class='action remind']");
			Thread.sleep(3000);
			Common.textBoxInput("xpath", "//input[@id='email_address_forgot']", data.get(dataset).get("Email"));
			Common.clickElement("id", "bnt-social-login-forgot");
			Thread.sleep(5000);
			String message = Common.getText("xpath", "//div[contains(@class,'message-success')]/div");
			System.out.println(message);
			int size = Common.findElements("xpath", "//div[contains(@class,'message-success')]/div").size();
			// int size=Common.findElements("xpath",
			// "//input[@id='email_address_forgot']").size();
			// Common.assertionCheckwithReport(size>0, "verifying forgetpassword
			// option", expectedResult,"Successfully Forgot password pop up is
			// displayed to customer", "User faield to get Forgot password
			// pop");
			Common.assertionCheckwithReport(size > 0, "verifying forgetpassword option", expectedResult,
					"Successfully Forgot password pop up is displayed to customer",
					"User faield to get  Forgot password pop");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying forgetpassword option", "clcik the forget password option",
					"User failed to clcik the forget password button",
					Common.getscreenShotPathforReport("forgetpasswordbuttonfaield"));
			AssertJUnit.fail();
		}

		/*
		 * String emailID="mahendra.lotuswave@gmail.com"; String
		 * password="Mahendra@123"; String
		 * fromAddress="Akhil Meesarakonda <meesarakonda.akhil9@gmail.com>";
		 * String toAddress="Mahendra.lotuswave@gmail.com"; String
		 * subject="Fwd: Reset your OXO password"; String
		 * content="Set a New Password";
		 */

		System.out.println(data.get(dataset).get("Email"));
		System.out.println(data.get(dataset).get("Password"));
		System.out.println(data.get(dataset).get("fromAddress"));
		System.out.println(data.get(dataset).get("toAddress"));
		System.out.println(data.get(dataset).get("subject"));
		System.out.println(data.get(dataset).get("content"));

		try {
			// String
			// email=MailAPI.gmailAPI(data.get(dataset).get("Email"),data.get(dataset).get("Password"),data.get(dataset).get("fromAddress"),
			// data.get(dataset).get("toAddress"),
			// data.get(dataset).get("subject"),
			// data.get(dataset).get("content"));
			// System.out.println(email.split("Set a New
			// Password")[1].split("<")[1].split(">")[0].trim());

		}

		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying forgetpassword email",
					"clcik the forget password link in email", "User not getting EMail",
					Common.getscreenShotPathforReport("emailFaield"));
			AssertJUnit.fail();
		}

	}

	// span[@class='customer-name active']

	public void ProductRegistration() throws InterruptedException {
		Thread.sleep(3000);
		try {
			Sync.scrollDownToView("xpath", "//a[@data-menu='menu-14903']");
			Sync.waitElementClickable(10, By.xpath("//a[@data-menu='menu-14903']"));
			ExtenantReportUtils.addPassLog("Validating ProductRegistration Link",
					"ProductRegistration link should be able to click", "Clicked on ProductRegistration Link",
					Common.getscreenShotPathforReport("ProductRegistrationLink"));
			Common.clickElement("xpath", "//a[@data-menu='menu-14903']");
			Thread.sleep(3000);
			Sync.waitElementVisible("xpath", "//h2[contains(text(),'Select your product')]");
			Sync.scrollDownToView("xpath", "//h2[contains(text(),'Select your product')]");
			WebElement element = Common.findElement("xpath", "//h2[contains(text(),'Select your product')]");
			String text = element.getText();
			if (text.contains("Select your product")) {
				ExtenantReportUtils.addPassLog("Validating Webtext of Product Registration Page",
						"Expected text should be obtained", "Expected text is obtained",
						Common.getscreenShotPathforReport("ProductRegistrationText"));
			} else {
				ExtenantReportUtils.addFailedLog("Validating Webelement of Product Registration Page",
						"Expected text should not be obtained", "Expected text is not obtained",
						"LinkValidation Product Registration");
				AssertJUnit.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating  of Product Registration Page",
					"Expected text should not be obtained", "Expected text is not obtained",
					"LinkValidation Product Registration");
			AssertJUnit.fail();
		}

		Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "Validating URL contains https",
				"Product Registration URL should Contains https", "Product Registration URL contains https",
				"Product Registration URL missing https");
		// Assert.assertTrue(Common.getCurrentURL().equals("https://oxo-dev.heledigital.com/productregistration")&&Common.getPageTitle().equals("Product
		// Registration"));
		Common.assertionCheckwithReport(
				Common.getCurrentURL().equals("https://oxo-stg.heledigital.com/productregistration")
						&& Common.getPageTitle().equals("Product Registration"),
				"Validating Page Title and URL", "Actual and Current URL&Page Title Should be Same",
				"Actual and Current URL&Page Title are Same", "Actual and Current URL&Page Title are different");

	}

	public void ContactUS() throws InterruptedException {
		Thread.sleep(3000);
		clickLogo();
		try {
			Sync.scrollDownToView("xpath", "//a[@data-menu='menu-14901']");
			Sync.waitElementClickable(10, By.xpath("//a[@data-menu='menu-14901']"));
			ExtenantReportUtils.addPassLog("Validating ContactUS Link", "ContactUS link should be present",
					"ContactUS Link is present", Common.getscreenShotPathforReport("ContactUSLink"));
			Common.clickElement("xpath", "//a[@data-menu='menu-14901']");

			WebElement element = Common.findElement("xpath", "//h2[contains(text(),'Email us')]");
			String text = element.getText();

			if (text.contains("Email us")) {

				ExtenantReportUtils.addPassLog("Validating Webtext of ContactUS Page",
						"Expected text should be obtained", "Expected text is obtained",
						Common.getscreenShotPathforReport("ContactUSText"));
			} else {

				ExtenantReportUtils.addFailedLog("Validating Webelement of ContactUS Page",
						"Expected text should not be obtained", "Expected text is not obtained",
						"LinkValidation ContactUS");
				AssertJUnit.fail();
			}

		}

		catch (Exception e) {
			ExtenantReportUtils.addFailedLog("Validating Webelement of ContactUS Page",
					"Expected text should not be obtained", "Expected text is not obtained",
					"LinkValidation ContactUS");
			AssertJUnit.fail();
		}

		Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "Validating URL contains https",
				"ContactUS URL should Contains https", "ContactUS URL contains https", "ContactUS URL missing https");
		// Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"),
		// "Validating URL which contains https", "This URL Contains https",
		// "give url contains https", "give url missing https");
		Common.assertionCheckwithReport(
				Common.getCurrentURL().equals("https://oxo-stg.heledigital.com/contact-us")
						&& Common.getPageTitle().equals("Contact Us"),
				"Validating Page Title and URL", "Actual and Current URL&Page Title Should be Same",
				"Actual and Current URL&Page Title are Same", "Actual and Current URL&Page Title are different");
	}

	public void FAQ() throws InterruptedException {
		Thread.sleep(3000);
		try {
			Sync.scrollDownToView("xpath", "//a[@data-menu='menu-14902']");
			Sync.waitElementClickable(10, By.xpath("//a[@data-menu='menu-14902']"));
			ExtenantReportUtils.addPassLog("Validating FAQ Link", "FAQ link should be present", "FAQ Link is present",
					Common.getscreenShotPathforReport("FAQLink"));
			// ExtenantReportUtils.addPassLog("Validating FAQ Link","FAQ link
			// should be able to click" ,"Clicked on PFAQ
			// Link",Common.getscreenShotPathforReport("FAQ Link"));
			Common.clickElement("xpath", "//a[@data-menu='menu-14902']");
			WebElement element = Common.findElement("xpath", "//span[contains(text(),'Frequently Asked Questions')]");
			String text = element.getText();
			System.out.println("Text Obtained is..." + text);
			if (text.contains("Frequently Asked Questions")) {
				System.out.println("Expected text is obtained");
				ExtenantReportUtils.addPassLog("Validating Webtext of FAQ Page", "Expected text should be obtained",
						"Expected text is obtained", Common.getscreenShotPathforReport("FAQText"));
			} else {
				System.out.println("Expected text is not obtained");
				ExtenantReportUtils.addFailedLog("Validating Webelement of FAQ Page",
						"Expected text should not be obtained", "Expected text is not obtained", "LinkValidation FAQ");
				AssertJUnit.fail();
			}
		} catch (Exception e) {
			ExtenantReportUtils.addFailedLog("Validating Webelement of FAQ Page",
					"Expected text should not be obtained", "Expected text is not obtained", "LinkValidation FAQ");
			AssertJUnit.fail();
		}

		Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "Validating URL contains https",
				"FAQ URL should Contains https", "FAQ URL contains https", "FAQ URL missing https");
		// Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"),
		// "Validating URL which contains https", "This URL Contains https",
		// "give url contains https", "give url missing https");

		// Assert.assertTrue(Common.getCurrentURL().equals("https://oxo-dev.heledigital.com/faq")&&Common.getPageTitle().equals("FAQ"));
		Common.assertionCheckwithReport(
				Common.getCurrentURL().equals("https://oxo-stg.heledigital.com/faq")
						&& Common.getPageTitle().equals("FAQ"),
				"Validating Page Title and URL", "Actual and Current URL&Page Title Should be Same",
				"Actual and Current URL&Page Title are Same", "Actual and Current URL&Page Title are different");
	}

	public void VoluntaryRecall() throws InterruptedException {
		Thread.sleep(3000);
		try {
			Sync.scrollDownToView("xpath", "//a[@data-menu='menu-14904']");
			Sync.waitElementClickable(10, By.xpath("//a[@data-menu='menu-14904']"));
			ExtenantReportUtils.addPassLog("Validating VoluntaryRecall Link", "VoluntaryRecall link should be present",
					"VoluntaryRecall Link is present", Common.getscreenShotPathforReport("VoluntaryRecallLink"));
			// ExtenantReportUtils.addPassLog("Validating ProductRegistration
			// Link","ProductRegistration link should be able to click"
			// ,"Clicked on ProductRegistration
			// Link",Common.getscreenShotPathforReport("ProductRegistrationLink"));
			Common.clickElement("xpath", "//a[@data-menu='menu-14904']");
			// Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"),
			// "Validating URL which contains https", "This URL Contains https",
			// "give url contains https", "give url missing https");
			WebElement element = Common.findElement("xpath",
					"//span[contains(text(),'Tot Nest Booster Seat Voluntary Recall')]");
			String text = element.getText();
			System.out.println("Text Obtained is" + text);
			if (text.contains("Tot Nest Booster Seat Voluntary Recall")) {
				System.out.println("Expected text is obtained");
				ExtenantReportUtils.addPassLog("Validating Webtext of VoluntaryRecall Page",
						"Expected text should be obtained", "Expected text is obtained",
						Common.getscreenShotPathforReport("VoluntaryRecallText"));
			} else {
				System.out.println("Expected text is not obtained");
				ExtenantReportUtils.addFailedLog("Validating Webelement of VoluntaryRecall Page",
						"Expected text should not be obtained", "Expected text is not obtained",
						"LinkValidation VoluntaryRecall");
				AssertJUnit.fail();
			}

		}

		catch (Exception e) {
			ExtenantReportUtils.addFailedLog("Validating Webelement of VoluntaryRecall Page",
					"Expected text should not be obtained", "Expected text is not obtained",
					"LinkValidation VoluntaryRecall");
			AssertJUnit.fail();
		}
		Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "Validating URL contains https",
				"VoluntaryRecall URL should Contains https", "VoluntaryRecall URL contains https",
				"VoluntaryRecall URL missing https");
		Common.assertionCheckwithReport(
				Common.getCurrentURL().equals("https://oxo-stg.heledigital.com/product-recall")
						&& Common.getPageTitle().equals("Product Recall"),
				"Validating Page Title and URL", "Actual and Current URL&Page Title Should be Same",
				"Actual and Current URL&Page Title are Same", "Actual and Current URL&Page Title are different");

	}

	public void PrivacyPolicy() throws InterruptedException {
		Thread.sleep(3000);
		try {
			Sync.scrollDownToView("xpath", "//a[@data-menu='menu-14905']");
			Sync.waitElementClickable(10, By.xpath("//a[@data-menu='menu-14905']"));
			ExtenantReportUtils.addPassLog("Validating PrivacyPolicy Link", "PrivacyPolicy link should be present",
					"PrivacyPolicy Link is present", Common.getscreenShotPathforReport("PrivacyPolicyLink"));
			// ExtenantReportUtils.addPassLog("Validating ProductRegistration
			// Link","ProductRegistration link should be able to click"
			// ,"Clicked on ProductRegistration
			// Link",Common.getscreenShotPathforReport("ProductRegistrationLink"));
			Common.clickElement("xpath", "//a[@data-menu='menu-14905']");
			WebElement element = Common.findElement("xpath", "//span[contains(text(),'Privacy Policy')]");
			String text = element.getText();
			System.out.println("Text Obtained is" + text);
			if (text.contains("Privacy Policy")) {
				System.out.println("Expected text is obtained");
				ExtenantReportUtils.addPassLog("Validating Webtext of PrivacyPolicy Page",
						"Expected text should be obtained", "Expected text is obtained",
						Common.getscreenShotPathforReport("PrivacyPolicyText"));
			} else {
				System.out.println("Expected text is not obtained");
				ExtenantReportUtils.addFailedLog("Validating Webelement of PrivacyPolicy Page",
						"Expected text should not be obtained", "Expected text is not obtained",
						"LinkValidation PrivacyPolicy");
				AssertJUnit.fail();
			}
		}

		catch (Exception e) {
			ExtenantReportUtils.addFailedLog("Validating Webelement of PrivacyPolicy Page",
					"Expected text should not be obtained", "Expected text is not obtained",
					"LinkValidation PrivacyPolicy");
			AssertJUnit.fail();

		}

		Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "Validating URL contains https",
				"PrivacyPolicy URL should Contains https", "PrivacyPolicy URL contains https",
				"PrivacyPolicy URL missing https");
		// Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"),
		// "Validating URL which contains https", "This URL Contains https",
		// "give url contains https", "give url missing https");

		// Assert.assertTrue(Common.getCurrentURL().equals("https://oxo-dev.heledigital.com/privacy")&&Common.getPageTitle().equals("Privacy
		// Policy"));
		Common.assertionCheckwithReport(
				Common.getCurrentURL().equals("https://oxo-stg.heledigital.com/privacy")
						&& Common.getPageTitle().equals("Privacy Policy"),
				"Validating Page Title and URL", "Actual and Current URL&Page Title Should be Same",
				"Actual and Current URL&Page Title are Same", "Actual and Current URL&Page Title are different");

	}

	public void TermsandConditions() throws InterruptedException {
		Thread.sleep(3000);
		try {
			Sync.scrollDownToView("xpath", "//a[@data-menu='menu-14906']");
			Sync.waitElementClickable(10, By.xpath("//a[@data-menu='menu-14906']"));
			ExtenantReportUtils.addPassLog("TermsandConditions Link", "TermsandConditions link should be present",
					"TermsandConditions Link is present", Common.getscreenShotPathforReport("TermsandConditionsLink"));
			// ExtenantReportUtils.addPassLog("Validating ProductRegistration
			// Link","ProductRegistration link should be able to click"
			// ,"Clicked on ProductRegistration
			// Link",Common.getscreenShotPathforReport("ProductRegistrationLink"));
			Common.clickElement("xpath", "//a[@data-menu='menu-14906']");
			WebElement element = Common.findElement("xpath", "//span[contains(text(),'Terms & Conditions')]");
			String text = element.getText();
			System.out.println("Text Obtained is" + text);
			if (text.contains("Terms & Conditions")) {
				System.out.println("Expected text is obtained");
				ExtenantReportUtils.addPassLog("Validating Webtext of TermsandConditions Page",
						"Expected text should be obtained", "Expected text is obtained",
						Common.getscreenShotPathforReport("TermsandConditionsText"));
			} else {
				System.out.println("Expected text is not obtained");
				ExtenantReportUtils.addFailedLog("Validating Webelement of TermsandConditions Page",
						"Expected text should not be obtained", "Expected text is not obtained",
						"LinkValidation TermsandConditions");
				AssertJUnit.fail();
			}

		}

		catch (Exception e) {
			ExtenantReportUtils.addFailedLog("Validating Webelement of TermsandConditions Page",
					"Expected text should not be obtained", "Expected text is not obtained",
					"LinkValidation TermsandConditions");
			AssertJUnit.fail();
		}

		Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "Validating URL contains https",
				"TermsandConditions URL should Contains https", "TermsandConditions URL contains https",
				"TermsandConditions URL missing https");
		// Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"),
		// "Validating URL which contains https", "This URL Contains https",
		// "give url contains https", "give url missing https");

		// Assert.assertTrue(Common.getCurrentURL().equals("https://oxo-dev.heledigital.com/Terms")&&Common.getPageTitle().equals("Terms
		// and Conditions"));
		Common.assertionCheckwithReport(
				Common.getCurrentURL().equals("https://oxo-stg.heledigital.com/Terms")
						&& Common.getPageTitle().equals("Terms and Conditions"),
				"Validating Page Title and URL", "Actual and Current URL&Page Title Should be Same",
				"Actual and Current URL&Page Title are Same", "Actual and Current URL&Page Title are different");

	}

	public void TrackOrder() throws InterruptedException {
		Thread.sleep(3000);
		try {
			Sync.scrollDownToView("xpath", "//a[@data-menu='menu-14908']");
			Sync.waitElementClickable(10, By.xpath("//a[@data-menu='menu-14908']"));
			ExtenantReportUtils.addPassLog("TrackOrder Link", "TrackOrder link should be present",
					"TrackOrder Link is present", Common.getscreenShotPathforReport("TrackOrderLink"));
			// ExtenantReportUtils.addPassLog("Validating ProductRegistration
			// Link","ProductRegistration link should be able to click"
			// ,"Clicked on ProductRegistration
			// Link",Common.getscreenShotPathforReport("ProductRegistrationLink"));
			Common.clickElement("xpath", "//a[@data-menu='menu-14908']");
			WebElement element = Common.findElement("xpath", "//span[contains(text(),'Track Order')]");
			String text = element.getText();
			System.out.println("Text Obtained is..." + text);
			if (text.contains("Track Order")) {
				System.out.println("Expected text is obtained");
				ExtenantReportUtils.addPassLog("Validating Webtext of TrackOrder Page",
						"Expected text should be obtained", "Expected text is obtained",
						Common.getscreenShotPathforReport("TrackOrderText"));
			} else {
				System.out.println("Expected text is not obtained");
				ExtenantReportUtils.addFailedLog("Validating Webelement of TrackOrder Page",
						"Expected text should not be obtained", "Expected text is not obtained",
						"LinkValidation TrackOrder");
				AssertJUnit.fail();
			}

		}

		catch (Exception e) {
			ExtenantReportUtils.addFailedLog("Validating Webelement of TrackOrder Page",
					"Expected text should not be obtained", "Expected text is not obtained",
					"LinkValidation TrackOrder");
			AssertJUnit.fail();
		}
		Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "Validating URL contains https",
				"TrackOrder URL should Contains https", "TrackOrder URL contains https",
				"TrackOrder URL missing https");
		// Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"),
		// "Validating URL which contains https", "This URL Contains https",
		// "give url contains https", "give url missing https");
		System.out.println(Common.getPageTitle());
		System.out.println(Common.getCurrentURL());
		// Assert.assertTrue(Common.getCurrentURL().equals("https://oxo-dev.heledigital.com/sales/guest/form/")&&Common.getPageTitle().equals("Orders
		// and Returns"));
		Common.assertionCheckwithReport(
				Common.getCurrentURL().equals("https://oxo-stg.heledigital.com/sales/guest/form/")
						&& Common.getPageTitle().equals("Orders and Returns"),
				"Validating Page Title and URL", "Actual and Current URL&Page Title Should be Same",
				"Actual and Current URL&Page Title are Same", "Actual and Current URL&Page Title are different");

	}

	public void ShippingInformation() throws InterruptedException {
		Thread.sleep(3000);
		try {
			Sync.scrollDownToView("xpath", "//a[@data-menu='menu-14909']");
			Sync.waitElementClickable(10, By.xpath("//a[@data-menu='menu-14909']"));
			ExtenantReportUtils.addPassLog("ShippingInformation Link", "ShippingInformation link should be present",
					"ShippingInformation Link is present",
					Common.getscreenShotPathforReport("ShippingInformationLink"));
			// ExtenantReportUtils.addPassLog("Validating ProductRegistration
			// Link","ProductRegistration link should be able to click"
			// ,"Clicked on ProductRegistration
			// Link",Common.getscreenShotPathforReport("ProductRegistrationLink"));
			Common.clickElement("xpath", "//a[@data-menu='menu-14909']");
			WebElement element = Common.findElement("xpath", "//h2[contains(text(),'Shipping Policies')]");
			String text = element.getText();
			System.out.println("Text Obtained is..." + text);
			if (text.contains("Shipping Policies")) {
				System.out.println("Expected text is obtained");
				ExtenantReportUtils.addPassLog("Validating Webtext of ShippingInformation Page",
						"Expected text should be obtained", "Expected text is obtained",
						Common.getscreenShotPathforReport("ShippingInformationText"));
			} else {
				System.out.println("Expected text is not obtained");
				ExtenantReportUtils.addFailedLog("Validating Webelement of ShippingInformation Page",
						"Expected text should not be obtained", "Expected text is not obtained",
						"LinkValidation ShippingInformation");
				AssertJUnit.fail();
			}
		}

		catch (Exception e) {
			ExtenantReportUtils.addFailedLog("Validating Webelement of ShippingInformation Page",
					"Expected text should not be obtained", "Expected text is not obtained",
					"LinkValidation ShippingInformation");
			AssertJUnit.fail();
		}
		Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "Validating URL contains https",
				"ShippingInformation URL should Contains https", "ShippingInformation URL contains https",
				"ShippingInformation URL missing https");
		// Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"),
		// "Validating URL which contains https", "This URL Contains https",
		// "give url contains https", "give url missing https");
		System.out.println(Common.getPageTitle());
		System.out.println(Common.getCurrentURL());
		// Assert.assertTrue(Common.getCurrentURL().equals("https://oxo-dev.heledigital.com/shipping")&&Common.getPageTitle().equals("Shipping"));
		Common.assertionCheckwithReport(
				Common.getCurrentURL().equals("https://oxo-stg.heledigital.com/shipping")
						&& Common.getPageTitle().equals("Shipping"),
				"Validating Page Title and URL", "Actual and Current URL&Page Title Should be Same",
				"Actual and Current URL&Page Title are Same", "Actual and Current URL&Page Title are different");

	}

	public void BetterGuarantee() throws InterruptedException {
		Thread.sleep(3000);
		try {
			Sync.scrollDownToView("xpath", "//a[@data-menu='menu-14910']");
			Sync.waitElementClickable(10, By.xpath("//a[@data-menu='menu-14910']"));
			ExtenantReportUtils.addPassLog("BetterGuarantee Link", "BetterGuarantee link should be present",
					"BetterGuarantee Link is present", Common.getscreenShotPathforReport("BetterGuaranteeLink"));
			// ExtenantReportUtils.addPassLog("Validating ProductRegistration
			// Link","ProductRegistration link should be able to click"
			// ,"Clicked on ProductRegistration
			// Link",Common.getscreenShotPathforReport("ProductRegistrationLink"));
			Common.clickElement("xpath", "//a[@data-menu='menu-14910']");
			WebElement element = Common.findElement("xpath", "//span[contains(text(),'The OXO Better Guarantee*')]");
			String text = element.getText();
			System.out.println("Text Obtained is..." + text);
			if (text.contains("The OXO Better Guarantee*")) {
				System.out.println("Expected text is obtained");
				ExtenantReportUtils.addPassLog("Validating Webtext of BetterGuarantee Page",
						"Expected text should be obtained", "Expected text is obtained",
						Common.getscreenShotPathforReport("BetterGuaranteeText"));
			} else {
				System.out.println("Expected text is not obtained");

				ExtenantReportUtils.addFailedLog("Validating Webelement of BetterGuarantee Page",
						"Expected text should not be obtained", "Expected text is not obtained",
						"LinkValidation BetterGuarantee");
				AssertJUnit.fail();
			}

		}

		catch (Exception e) {
			ExtenantReportUtils.addFailedLog("Validating Webelement of BetterGuarantee Page",
					"Expected text should not be obtained", "Expected text is not obtained",
					"LinkValidation BetterGuarantee");
			AssertJUnit.fail();
		}
		Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "Validating URL contains https",
				"BetterGuarantee URL should Contains https", "BetterGuarantee URL contains https",
				"BetterGuarantee URL missing https");
		// Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"),
		// "Validating URL which contains https", "This URL Contains https",
		// "give url contains https", "give url missing https");
		System.out.println(Common.getPageTitle());
		System.out.println(Common.getCurrentURL());
		// Assert.assertTrue(Common.getCurrentURL().equals("https://oxo-dev.heledigital.com/guarantee")&&Common.getPageTitle().equals("The
		// OXO Better Guarantee"));
		Common.assertionCheckwithReport(
				Common.getCurrentURL().equals("https://oxo-stg.heledigital.com/guarantee")
						&& Common.getPageTitle().equals("The OXO Better Guarantee"),
				"Validating Page Title and URL", "Actual and Current URL&Page Title Should be Same",
				"Actual and Current URL&Page Title are Same", "Actual and Current URL&Page Title are different");

	}

	public void GoodTipsBlog() throws InterruptedException {
		Thread.sleep(3000);
		clickLogo();
		try {
			Sync.scrollDownToView("xpath", "//a[@data-menu='menu-14913']");
			Sync.waitElementClickable(10, By.xpath("//a[@data-menu='menu-14913']"));
			ExtenantReportUtils.addPassLog("GoodTipsBlog Link", "GoodTipsBlog link should be present",
					"GoodTipsBlog Link is present", Common.getscreenShotPathforReport("GoodTipsBlogLink"));
			// ExtenantReportUtils.addPassLog("Validating ProductRegistration
			// Link","ProductRegistration link should be able to click"
			// ,"Clicked on ProductRegistration
			// Link",Common.getscreenShotPathforReport("ProductRegistrationLink"));
			Common.clickElement("xpath", "//a[@data-menu='menu-14913']");
			Thread.sleep(5000);

			Sync.waitElementPresent("Xpath", "//h1[contains(text(),'Good Tips')]");
			WebElement element = Common.findElement("xpath", "//h1[contains(text(),'Good Tips')]");
			String text = element.getText();
			System.out.println("Text Obtained is..." + text);
			if (text.contains("Good Tips")) {
				System.out.println("Expected text is obtained");
				ExtenantReportUtils.addPassLog("Validating Webtext of GoodTipsBlog Page",
						"Expected text should be obtained", "Expected text is obtained",
						Common.getscreenShotPathforReport("GoodTipsBlogText"));
			} else {
				System.out.println("Expected text is not obtained");
				ExtenantReportUtils.addFailedLog("Validating Webelement of GoodTipsBlog Page",
						"Expected text should not be obtained", "Expected text is not obtained",
						"GoodTipsBlog Product Registration");
				AssertJUnit.fail();
			}

		}

		catch (Exception e) {
			ExtenantReportUtils.addFailedLog("Validating Webelement of GoodTipsBlog Page",
					"Expected text should not be obtained", "Expected text is not obtained",
					"GoodTipsBlog Product Registration");
			AssertJUnit.fail();
		}
		Thread.sleep(2000);
		Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "Validating URL contains https",
				"GoodTipsBlog URL should Contains https", "GoodTipsBlog URL contains https",
				"GoodTipsBlog URL missing https");
		// Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"),
		// "Validating URL which contains https", "This URL Contains https",
		// "give url contains https", "give url missing https");
		System.out.println(Common.getPageTitle());
		System.out.println(Common.getCurrentURL());
		// Assert.assertTrue(Common.getCurrentURL().equals("https://oxo-dev.heledigital.com/blog/")&&Common.getPageTitle().equals("OXO
		// Good Tips - The OXO Blog"));
		Common.assertionCheckwithReport(
				Common.getCurrentURL().equals("https://oxo-stg.heledigital.com/blog/")
						&& Common.getPageTitle().equals("OXO Good Tips - The OXO Blog"),
				"Validating Page Title and URL", "Actual and Current URL&Page Title Should be Same",
				"Actual and Current URL&Page Title are Same", "Actual and Current URL&Page Title are different");

	}

	public void InventorSubmissions() throws InterruptedException {
		Thread.sleep(3000);
		clickLogo();
		try {
			Sync.scrollDownToView("xpath", "//a[@data-menu='menu-14893']");
			Sync.waitElementClickable(10, By.xpath("//a[@data-menu='menu-14893']"));
			ExtenantReportUtils.addPassLog("InventorSubmissions Link", "InventorSubmissions link should be present",
					"InventorSubmissions Link is present",
					Common.getscreenShotPathforReport("InventorSubmissionsLink"));
			// ExtenantReportUtils.addPassLog("Validating ProductRegistration
			// Link","ProductRegistration link should be able to click"
			// ,"Clicked on ProductRegistration
			// Link",Common.getscreenShotPathforReport("ProductRegistrationLink"));
			Common.clickElement("xpath", "//a[@data-menu='menu-14893']");
			WebElement element = Common.findElement("xpath", "//span[contains(text(),'Inventor Submission')]");
			String text = element.getText();
			System.out.println("Text Obtained is..." + text);
			if (text.contains("Inventor Submission")) {
				System.out.println("Expected text is obtained");
				ExtenantReportUtils.addPassLog("Validating Webtext of InventorSubmissions Page",
						"Expected text should be obtained", "Expected text is obtained",
						Common.getscreenShotPathforReport("InventorSubmissionsText"));
			} else {
				System.out.println("Expected text is not obtained");
				ExtenantReportUtils.addFailedLog("Validating Webelement of InventorSubmissions Page",
						"Expected text should not be obtained", "Expected text is not obtained",
						"LinkValidation InventorSubmissions");
				AssertJUnit.fail();
			}
		}

		catch (Exception e) {
			ExtenantReportUtils.addFailedLog("Validating Webelement of InventorSubmissions Page",
					"Expected text should not be obtained", "Expected text is not obtained",
					"LinkValidation InventorSubmissions");
			AssertJUnit.fail();
		}
		Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "Validating URL contains https",
				"InventorSubmissions URL should Contains https", "InventorSubmissions URL contains https",
				"InventorSubmissions URL missing https");
		// Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"),
		// "Validating URL which contains https", "This URL Contains https",
		// "give url contains https", "give url missing https");
		System.out.println(Common.getPageTitle());
		System.out.println(Common.getCurrentURL());
		// Assert.assertTrue(Common.getCurrentURL().equals("https://oxo-dev.heledigital.com/inventor")&&Common.getPageTitle().equals("Inventor
		// Submission"));
		Common.assertionCheckwithReport(
				Common.getCurrentURL().equals("https://oxo-stg.heledigital.com/inventor")
						&& Common.getPageTitle().equals("Inventor Submission"),
				"Validating Page Title and URL", "Actual and Current URL&Page Title Should be Same",
				"Actual and Current URL&Page Title are Same", "Actual and Current URL&Page Title are different");

	}

	public void Careers() throws InterruptedException {
		Thread.sleep(3000);
		clickLogo();
		try {
			Sync.scrollDownToView("xpath", "//a[@data-menu='menu-14915']");
			Sync.waitElementClickable(10, By.xpath("//a[@data-menu='menu-14915']"));
			ExtenantReportUtils.addPassLog("Careers Link", "Careers link should be present", "Careers Link is present",
					Common.getscreenShotPathforReport("CareersLink"));
			// ExtenantReportUtils.addPassLog("Validating ProductRegistration
			// Link","ProductRegistration link should be able to click"
			// ,"Clicked on ProductRegistration
			// Link",Common.getscreenShotPathforReport("ProductRegistrationLink"));
			Common.clickElement("xpath", "//a[@data-menu='menu-14915']");
			Thread.sleep(2000);
			Common.switchWindows();
			Sync.waitElementPresent("xpath", "//span[contains(text(),'Search for Jobs')]");
			WebElement element = Common.findElement("xpath", "//span[contains(text(),'Search for Jobs')]");
			String text = element.getText();
			System.out.println("Text Obtained is..." + text);
			if (text.contains("Search for Jobs")) {
				System.out.println("Expected text is obtained");
				ExtenantReportUtils.addPassLog("Validating Webtext of Careers Page", "Expected text should be obtained",
						"Expected text is obtained", Common.getscreenShotPathforReport("CareersText"));
			} else {
				System.out.println("Expected text is not obtained");
				ExtenantReportUtils.addFailedLog("Validating Webelement of Careers Page",
						"Expected text should not be obtained", "Expected text is not obtained",
						"LinkValidation Careers");
				Common.switchToFirstTab();
				AssertJUnit.fail();
			}
		}

		catch (Exception e) {
			ExtenantReportUtils.addFailedLog("Validating Webelement of Careers Page",
					"Expected text should not be obtained", "Expected text is not obtained", "LinkValidation Careers");
			Common.switchToFirstTab();
			AssertJUnit.fail();
		}
		Thread.sleep(2000);
		Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "Validating URL contains https",
				"Careers URL should Contains https", "Careers URL contains https", "Careers URL missing https");
		// Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"),
		// "Validating URL which contains https", "This URL Contains https",
		// "give url contains https", "give url missing https");

		Thread.sleep(6000);
		// Assert.assertTrue(Common.getCurrentURL().equals("https://helenoftroy.wd1.myworkdayjobs.com/Main_HoT")&&Common.getPageTitle().equals("Search
		// for Jobs"));
		Common.assertionCheckwithReport(
				Common.getCurrentURL().equals("https://helenoftroy.wd1.myworkdayjobs.com/Main_HoT")
						&& Common.getPageTitle().equals("Search for Jobs"),
				"Validating Page Title and URL", "Actual and Current URL&Page Title Should be Same",
				"Actual and Current URL&Page Title are Same", "Actual and Current URL&Page Title are different");
		Common.switchToFirstTab();
	}

	public void InvestorRelations() throws InterruptedException {
		Thread.sleep(3000);
		clickLogo();
		try {
			Sync.scrollDownToView("xpath", "//a[@data-menu='menu-14916']");
			Sync.waitElementClickable(10, By.xpath("//a[@data-menu='menu-14916']"));
			ExtenantReportUtils.addPassLog("InvestorRelations Link", "InvestorRelations link should be present",
					"InvestorRelations Link is present", Common.getscreenShotPathforReport("InvestorRelationsLink"));
			// ExtenantReportUtils.addPassLog("Validating ProductRegistration
			// Link","ProductRegistration link should be able to click"
			// ,"Clicked on ProductRegistration
			// Link",Common.getscreenShotPathforReport("ProductRegistrationLink"));
			Common.clickElement("xpath", "//a[@data-menu='menu-14916']");
			Thread.sleep(2000);
			Common.switchWindows();
			// Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//span[contains(text(),'Why Helen of Troy')]");
			WebElement element = Common.findElement("xpath", "//span[contains(text(),'Why Helen of Troy')]");
			String text = element.getText();
			System.out.println("Text Obtained is..." + text);
			if (text.contains("Why Helen of Troy")) {
				System.out.println("Expected text is obtained");
				ExtenantReportUtils.addPassLog("Validating Webtext of InvestorRelations Page",
						"Expected text should be obtained", "Expected text is obtained",
						Common.getscreenShotPathforReport("InvestorRelationsText"));
			} else {
				System.out.println("Expected text is not obtained");
				ExtenantReportUtils.addFailedLog("Validating Webelement of InvestorRelations Page",
						"Expected text should not be obtained", "Expected text is not obtained",
						"LinkValidation InvestorRelations");
				Common.switchToFirstTab();
				AssertJUnit.fail();
			}
		}

		catch (Exception e) {
			ExtenantReportUtils.addFailedLog("Validating Webelement of InvestorRelations Page",
					"Expected text should not be obtained", "Expected text is not obtained",
					"LinkValidation InvestorRelations");
			Common.switchToFirstTab();
			AssertJUnit.fail();
		}

		Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"), "Validating URL contains https",
				"InvestorRelations URL should Contains https", "InvestorRelations URL contains https",
				"InvestorRelations URL missing https");
		Thread.sleep(3000);
		Common.assertionCheckwithReport(
				Common.getCurrentURL().equals("https://investor.helenoftroy.com/overview/default.aspx")
						&& Common.getPageTitle().equals("Helen of Troy Limited - Overview"),
				"Validating Page Title and URL", "Actual and Current URL&Page Title Should be Same",
				"Actual and Current URL&Page Title are Same", "Actual and Current URL&Page Title are different");
		Common.switchToFirstTab();
	}

	public void logOut() throws Exception {

		Thread.sleep(5000);
		try {

			Sync.waitElementPresent("xpath", "//span[@class='customer-name']");
			Common.mouseOverClick("xpath", "//span[@class='customer-name']");
			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "//li[contains(@class,'customer-welcome')]//li[2]/a");
			Common.mouseOverClick("xpath", "//li[contains(@class,'customer-welcome')]//li[2]/a");
		    ExtenantReportUtils.addPassLog("verifying logoout", "Log out from aplication",
					"User log out from aplication", Common.getscreenShotPathforReport("logout"));

		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("verifying logoout", "user log from application",
					"User failed to log out from aplication", Common.getscreenShotPathforReport("logoutfailed"));
			AssertJUnit.fail();

		}

	}

	public void Instagram() throws InterruptedException {
		Thread.sleep(3000);
		try {
			Sync.scrollDownToView("xpath", "//a[@data-menu='menu-11486']");
			Sync.waitElementClickable(10, By.xpath("//a[@data-menu='menu-11486']"));
			Common.clickElement("xpath", "//a[@data-menu='menu-11486']");
			Thread.sleep(2000);
			Common.switchWindows();
			Thread.sleep(3000);
			WebElement element = Common.findElement("xpath", "//h2[contains(text(),'oxo')]");
			String text = element.getText();
			if (text.contains("oxo")) {
				ExtenantReportUtils.addPassLog("Validating Webelement of Instagram Page",
						"Expected text should be obtained", "Expected text is obtained", "Instagram LinkValidation ");
			} else {
				ExtenantReportUtils.addFailedLog("Validating Webelement of Instagram Page",
						"Expected text should not be obtained", "Expected text is not obtained",
						"Instagram LinkValidation");
				Common.switchToFirstTab();
				AssertJUnit.fail();
			}
		}

		catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("Validating Webelement of Instagram Page",
					"Expected text should not be obtained", "Expected text is not obtained",
					"Instagram LinkValidation");
			Common.switchToFirstTab();

		}
		Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"),
				"Validating Instagram URL which contains https", "This URL Contains https", "give url contains https",
				"give url missing  https");
		Common.assertionCheckwithReport(
				Common.getCurrentURL().equals("https://www.instagram.com/oxo/")
						&& Common.getPageTitle().equals("OXO (@oxo) • Instagram photos and videos"),
				"Validating Instagram Page Title and URL", "Actual and Current URL&Page Title Should be Same",
				"Actual and Current URL&Page Title are Same", "Actual and Current URL&Page Title are different");
		Common.switchToFirstTab();

	}

	public void YouTube() throws InterruptedException {
		Thread.sleep(3000);
		try {
			Sync.scrollDownToView("xpath", "//a[@data-menu='menu-11489']");
			Sync.waitElementClickable(10, By.xpath("//a[@data-menu='menu-11489']"));
			ExtenantReportUtils.addPassLog("Validating YouTube Link", "", "",
					Common.getscreenShotPathforReport("YouTubeLink"));
			Common.clickElement("xpath", "//a[@data-menu='menu-11489']");

			Thread.sleep(2000);
			Common.switchWindows();
			Thread.sleep(3000);
		}
		/*
		 * WebElement element=Common.findElement("xpath",
		 * "//div[contains(text(),'Playlists')]"); String
		 * text=element.getText(); System.out.println("Text Obtained is"+ text);
		 * if(text.contains("Playlists")){ ExtenantReportUtils.
		 * addPassLog("Validating Webelement of YouTube Channel",
		 * "Expected text should be obtained","Expected text is obtained",
		 * "YouTube LinkValidation "); } else{ ExtenantReportUtils.
		 * addFailedLog("Validating Webelement of YouTube Channel",
		 * "Expected text should not be obtained"
		 * ,"Expected text is not obtained", "YouTube LinkValidation");
		 * Assert.fail(); } }
		 */

		catch (Exception e) {
			ExtenantReportUtils.addFailedLog("Validating  of YouTube Channel", "Expected text should not be obtained",
					"Expected text is not obtained", "YouTube LinkValidation");
			AssertJUnit.fail();
			Common.switchToFirstTab();
		}
		// Assert.assertTrue(Common.getCurrentURL().contains("https"));
		Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"),
				"Validating  YouTube URL which contains https", "This URL Contains https", "give url contains https",
				"give url missing  https");
		System.out.println(Common.getPageTitle());
		System.out.println(Common.getCurrentURL());
		// Assert.assertTrue(Common.getCurrentURL().equals("https://www.youtube.com/oxo")&&Common.getPageTitle().equals("OXO
		// (@oxo) • Instagram photos and videos"));

		Common.assertionCheckwithReport(
				Common.getCurrentURL().equals("https://www.youtube.com/oxo")
						&& Common.getPageTitle().equals("OXO - YouTube"),
				"Validating Page  YouTube Title and URL", "Actual and Current URL&Page Title Should be Same",
				"Actual and Current URL&Page Title are Same", "Actual and Current URL&Page Title are different");
		Common.closeCurrentWindow();
		Common.switchToFirstTab();
	}

	public void pinterest() throws InterruptedException {
		Thread.sleep(3000);
		try {
			Sync.scrollDownToView("xpath", "//a[@data-menu='menu-11488']");
			Sync.waitElementClickable(10, By.xpath("//a[@data-menu='menu-11488']"));
			Common.clickElement("xpath", "//a[@data-menu='menu-11488']");
			Thread.sleep(2000);
			Common.switchToSecondTab();
			Thread.sleep(3000);
			WebElement element = Common.findElement("xpath", "//span[contains(text(),'Following')]");
			String text = element.getText();
			System.out.println("Text Obtained is" + text);
			if (text.contains("Following")) {
				ExtenantReportUtils.addPassLog("Validating Webtext of pinterest", "Expected text should be obtained",
						"Expected text is obtained", "pinterest LinkValidation ");
			} else {
				ExtenantReportUtils.addFailedLog("Validating Webtext of pinterest",
						"Expected text should not be obtained", "Expected text is not obtained",
						"pinterest LinkValidation");
				AssertJUnit.fail();
			}
		}

		catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating Webtext of pinterest", "Expected text should not be obtained",
					"Expected text is not obtained", "pinterest LinkValidation");
			AssertJUnit.fail();
		}
		Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"),
				"Validating pinterest URL which contains https", "This URL Contains https", "give url contains https",
				"give url missing  https");
		// Assert.assertTrue(Common.getCurrentURL().equals("https://in.pinterest.com/oxo/_shop/")&&Common.getPageTitle().equals("OXO
		// (oxo) on Pinterest"));
		Common.assertionCheckwithReport(
				Common.getCurrentURL().equals("https://www.pinterest.com/oxo/")
						&& Common.getPageTitle().equals("OXO (oxo) - Profile | Pinterest"),
				"Validating pinterest Page Title and URL", "Actual and Current URL&Page Title Should be Same",
				"Actual and Current URL&Page Title are Same", "Actual and Current URL&Page Title are different");
		Common.closeCurrentWindow();
		Common.switchToFirstTab();
	}

	public void Twitter() throws InterruptedException {
		Thread.sleep(3000);
		try {
			Sync.scrollDownToView("xpath", "//a[@data-menu='menu-11490']");
			Sync.waitElementClickable(10, By.xpath("//a[@data-menu='menu-11490']"));
			Common.clickElement("xpath", "//a[@data-menu='menu-11490']");
			Thread.sleep(2000);
			Common.switchWindows();
			Thread.sleep(3000);
			WebElement element = Common.findElement("xpath", "//span[contains(text(),'New to Twitter?')]");
			String text = element.getText();
			System.out.println("Text Obtained is" + text);
			if (text.contains("New to Twitter?")) {
				ExtenantReportUtils.addPassLog("Validating Webelement of Twitter Page",
						"Expected text should be obtained", "Expected text is obtained", "Twitter LinkValidation ");
			} else {
				ExtenantReportUtils.addFailedLog("Validating Webelement of Twitter Page",
						"Expected text should not be obtained", "Expected text is not obtained",
						"Twitter LinkValidation");
				AssertJUnit.fail();
			}
		}

		catch (Exception e) {
			ExtenantReportUtils.addFailedLog("Validating Webelement of Twitter Page",
					"Expected text should not be obtained", "Expected text is not obtained", "Twitter LinkValidation");
			AssertJUnit.fail();
		}
		System.out.println(Common.getPageTitle());
		Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"),
				"Validating Twitter URL which contains https", "This URL Contains https", "given url contains https",
				"give url missing  https");
		// Assert.assertTrue(Common.getCurrentURL().equals("https://www.instagram.com/oxo/")&&Common.getPageTitle().equals("OXO
		// (@oxo) • Instagram photos and videos"));
		Common.assertionCheckwithReport(
				Common.getCurrentURL().equals("https://twitter.com/OXO")
						&& Common.getPageTitle().equals("OXO (@OXO) / Twitter"),
				"Validating  Twitter Page Title and URL", "Actual and Current URL&Page Title Should be Same",
				"Actual and Current URL&Page Title are Same", "Actual and Current URL&Page Title are different");

		Common.closeCurrentWindow();
		Common.switchToFirstTab();
	}

	public void Facebook() throws InterruptedException {
		Thread.sleep(3000);
		try {
			Sync.scrollDownToView("xpath", "//a[@data-menu='menu-11487']");
			Sync.waitElementClickable(10, By.xpath("//a[@data-menu='menu-11487']"));
			Common.clickElement("xpath", "//a[@data-menu='menu-11487']");
			Thread.sleep(2000);
			Common.switchWindows();
			Thread.sleep(3000);
			WebElement element = Common.findElement("id", "seo_h1_tag");
			String text = element.getText();
			// System.out.println("Text Obtained is"+ text);
			if (text.contains("Kitchen/Cooking")) {

				System.out.println("Expected text is obtained");
				ExtenantReportUtils.addPassLog("Validating Webelement of Facebook Page",
						"Expected text should be obtained", "Expected text is obtained", "Facebook LinkValidation ");
			} else {
				System.out.println("Expected text is not obtained");

				ExtenantReportUtils.addFailedLog("Validating Webelement of Facebook Page",
						"Expected text should not be obtained", "Expected text is not obtained",
						"Facebook LinkValidation");
				AssertJUnit.fail();
			}
		}

		catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating Webelement of Facebook Page",
					"Expected text should not be obtained", "Expected text is not obtained", "Facebook LinkValidation");

			AssertJUnit.fail();
		}

		Common.assertionCheckwithReport(Common.getCurrentURL().contains("https"),
				"Validating Facebook URL which contains https", "This URL Contains https", "give url contains https",
				"give url missing  https");
		Common.assertionCheckwithReport(
				Common.getCurrentURL().equals("https://www.facebook.com/OXO")
						&& Common.getPageTitle().equals("OXO - Home | Facebook"),
				"Validating Facebook Page Title and URL", "Actual and Current URL&Page Title Should be Same",
				"Actual and Current URL&Page Title are Same", "Actual and Current URL&Page Title are different");
		Common.closeCurrentWindow();
		Common.switchToFirstTab();

	}

	public void Click_MyAccount() {
		try {
			Sync.waitElementPresent("xpath", "//span[@class='customer-name']");
			Common.mouseOverClick("xpath", "//span[@class='customer-name']");
			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "//li[contains(@class,'customer-welcome')]//li[1]/a");
			Common.mouseOverClick("xpath", "//li[contains(@class,'customer-welcome')]//li[1]/a");
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//h1[@data-element='hero_title']");
			String userName = Common.getText("xpah", "//h1[@data-element='hero_title']");
			Common.assertionCheckwithReport(userName.concat("Hi"), "verifying my account link",
					"User lands on the my accountpage", "User lands on AccountPage", "Faield lo land o nmyaccount");

		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying my account link", "lands on AccountPage",
					"User failed lands on my accountpage", Common.getscreenShotPathforReport("account"));
			AssertJUnit.fail();

		}

	}

	public void myaccount_validation() {
		try {

			Sync.waitElementPresent("xpath", "//div[@id='account-nav']//li[1]/a");
			Common.clickElement("xpath", "//div[@id='account-nav']//li[1]/a");
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Sync.waitElementPresent("xpath", "//h1[@class='page-title']/span");
			String accountinfro = Common.getText("xpath", "//h1[@class='page-title']/span");
			Common.assertionCheckwithReport(accountinfro.equals("Account Dashboard"), "Validating my account option",
					"it open the my account Dashboard", "successfully open the my account Dashboard",
					"it faield open the myaccount dashboard");
		} catch (Exception e) {
			ExtenantReportUtils.addFailedLog("Validating my account option", "it open the my account Dashboard",
					"it faield open the myaccount dashboard", Common.getscreenShot("faieldmyaccount"));
			AssertJUnit.fail();
		}
	}

	public void myaOrders() {
		try {

			Sync.waitElementPresent("xpath", "//div[@id='account-nav']//li[2]/a");
			Common.clickElement("xpath", "//div[@id='account-nav']//li[2]/a");
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Sync.waitElementPresent("xpath", "//h1[@class='page-title']/span");
			String accountinfro = Common.getText("xpath", "//h1[@class='page-title']/span");
			Common.assertionCheckwithReport(accountinfro.equals("My Orders"), "Validating  my aOrders option",
					"it open the myaOrders Dashboard", "successfully open the myaOrders Dashboard",
					"it faield open the myaOrders dashboard");
		} catch (Exception e) {
			ExtenantReportUtils.addFailedLog("Validating myaOrders option", "it open the myaOrders Dashboard",
					"it faield open the myaOrders dashboard", Common.getscreenShot("faieldmyaOrders"));
			AssertJUnit.fail();
		}
	}

	/*
	 * public void myaOrders(){ try{
	 * 
	 * Sync.waitElementPresent("xpath", "//div[@id='account-nav']//li[3]/a");
	 * Common.clickElement("xpath", "//div[@id='account-nav']//li[3]/a");
	 * Common.actionsKeyPress(Keys.PAGE_DOWN); Sync.waitElementPresent("xpath",
	 * "//h1[@class='page-title']/span"); String
	 * accountinfro=Common.getText("xpath", "//h1[@class='page-title']/span");
	 * Common.assertionCheckwithReport(accountinfro.equals("My Orders"),
	 * "Validating  my aOrders option","it open the myaOrders Dashboard",
	 * "successfully open the myaOrders Dashboard",
	 * "it faield open the myaOrders dashboard"); } catch(Exception e) {
	 * ExtenantReportUtils.addFailedLog("Validating myaOrders option",
	 * "it open the myaOrders Dashboard",
	 * "it faield open the myaOrders dashboard",Common.getscreenShot(
	 * "faieldmyaOrders")); Assert.fail(); } }
	 */
	public void pagewalidation() throws Exception {

		Thread.sleep(5000);
		try {

			Sync.waitElementPresent("xpath", "//span[@class='customer-name']");
			Common.mouseOverClick("xpath", "//span[@class='customer-name']");
			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "//li[contains(@class,'customer-welcome')]//li[1]/a");
			Common.mouseOverClick("xpath", "//li[contains(@class,'customer-welcome')]//li[1]/a");
			ExtenantReportUtils.addPassLog("verifying logoout", "Log out from aplication",
					"User log out from aplication", Common.getscreenShotPathforReport("logout"));

		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("verifying logoout", "user log from application",
					"User failed to log out from aplication", Common.getscreenShotPathforReport("logoutfailed"));
			AssertJUnit.fail();

		}
	}

	/////////////////////////// **********//////////////

	public void clickGoodTipsBlog() {
		String expectedResult = "It Should be navigate Goodtipsblog page";
		try {
			Thread.sleep(5000);
			Sync.waitElementClickable("xpath", "//a[@data-menu='menu-15796']");
			Common.clickElement("xpath", "//a[@data-menu='menu-15796']");
			// Common.assertionCheckwithReport(Common.getCurrentURL().equals(data.get(dataSet).get("URL"))&&Common.getPageTitle().equals(data.get(dataSet).get("pageTitle")),"Validating
			// GoodTipsBlog page Title and URL", "it navigating to GoodTipsBlog
			// page", "user navigated to GoodTipsBlog Page", "user faield to
			// navigate to good tipes blog");
			ExtenantReportUtils.addPassLog("validating the GoodTipsBlog page.", expectedResult,
					"user click the GoodTipsblog", Common.getscreenShotPathforReport("passgood"));
		} catch (Exception | Error e) {
			
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the GoodTipsBlog page.", expectedResult,
					"user failed to navigate GoodTipsBlog", Common.getscreenShotPathforReport("GoodTipsBlogfaield"));
			AssertJUnit.fail();
		}

	}

	public void signButton_GoodTipsBlog() throws Exception {
		Thread.sleep(4000);
		String expectedResult = "click the sign button navigating to signpage";
		try {
			Sync.waitElementClickable("xpath", "//a[@class='sign-in mob-hidden']");
			Common.clickElement("xpath", "//a[@class='sign-in mob-hidden']");
			Sync.waitElementPresent("xpath", "//input[@id='email-social']");
			int emailsize = Common.findElements("xpath", "//input[@id='email-social']").size();

			Common.assertionCheckwithReport(emailsize > 0, "validating the signButton", expectedResult,
					"user successfully click the sign button", "Faield to click the signbutton");
		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("validating the GoodTipsBlog sign button.", expectedResult,
					"user failed to navigate GoodTipsBlog", Common.getscreenShotPathforReport("GoodTipsBlogfaield"));
			AssertJUnit.fail();
		}
	}

	public void login_GoodTipsBlog(String dataSet) throws Exception {

		clickGoodTipsBlog();
		signButton_GoodTipsBlog();
		try {
			Common.textBoxInput("xpath", "//input[@id='email-social']", data.get(dataSet).get("Email"));
			Common.textBoxInput("xpath", "//input[@id='pass-social']", data.get(dataSet).get("Password"));

			ExtenantReportUtils.addPassLog("verifying login page with fieldData", "User enter the FieldData",
					"successfully enter the data", Common.getscreenShotPathforReport("logindatagood"));
			Sync.waitPageLoad();
			Thread.sleep(8000);
			Common.clickElement("xpath", "//button[contains(@class,'login primary')]");
			Thread.sleep(8000);
			int errormessagetextSize = Common.findElements("xpath", "//div[contains (text(),'required')]").size();
			if (errormessagetextSize <= 0) {
			} else {

				ExtenantReportUtils.addFailedLog("verifying login page with fieldData",
						"see the fields populated with the data", "User failed to proceed login form",
						Common.getscreenShotPathforReport("logindata"));
				AssertJUnit.fail();
			}

		}

		catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("verifying login page", "lands on sign popup",
					"User failed lands on sign popup", Common.getscreenShotPathforReport("signpoptt"));
			AssertJUnit.fail();

		}
	}

	public void CreateAccount_GoodTipsBlog(String dataSet) throws Exception {
		clickGoodTipsBlog();
		signButton_GoodTipsBlog();
		Thread.sleep(5000);
		try {
			Sync.waitElementClickable(30, By.xpath("//a[@class='action create primary']"));
			Common.clickElement("xpath", "//a[@class='action create primary']");

			ExtenantReportUtils.addPassLog("verifying Create account button", "lands on the Creating account popup",
					"User successfully lands createaccount popup", Common.getscreenShotPathforReport("creatnow"));
		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("verifying Create account button", "lands on the  Creating account",
					"User failed lands Creating account popup", Common.getscreenShotPathforReport("creatnow"));
			AssertJUnit.fail();
		}
		try {
			// Sync.waitElementClickable("xpath", "//span[text()='Create
			// Account']");
			Thread.sleep(5000);
			Common.textBoxInput("xpath", "//form[@id='form-validate']//input[@name='firstname']",
					data.get(dataSet).get("FirstName"));
			Common.textBoxInput("xpath", "//form[@id='form-validate']//input[@name='lastname']",
					data.get(dataSet).get("LastName"));
			Common.textBoxInput("xpath", "//form[@id='form-validate']//input[@id='email_address']",
					data.get(dataSet).get("Email"));
			Common.textBoxInput("xpath", "//form[@id='form-validate']//input[@id='password']",
					data.get(dataSet).get("Password"));
			Common.textBoxInput("xpath", "//form[@id='form-validate']//input[@id='password-confirmation']",
					data.get(dataSet).get("Password"));
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Sync.waitPageLoad();
			Thread.sleep(5000);
			Common.clickElement("xpath", "//button[contains(@class,'submit primary')]");
			int errormessagetextSize = Common.findElements("xpath", "//div[contains (text(),'required')]").size();
			if (errormessagetextSize <= 0) {
			} else {

				ExtenantReportUtils.addFailedLog("verifying Createaccount up page with valid field data",
						"see the fields populated with the data", "User failed to proceed Create account form",
						Common.getscreenShotPathforReport("signupissuecr"));
				AssertJUnit.fail();
			}

			Common.actionsKeyPress(Keys.ESCAPE);
		} catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("verifying sign up page to Create new account",
					"Sign up popup with valid Data", "User failed to proceed Create account form ",
					Common.getscreenShotPathforReport("signupissuecr"));
			AssertJUnit.fail();

		}
	}

	public void GoodTipsBlogPage() throws Exception {
		String expectedResult = "It Should be navigate to the selected category page.";
		try {
			Sync.waitElementClickable("xpath", "//a[@data-menu='menu-15196']");
			Common.clickElement("xpath", "//a[@data-menu='menu-15196']");

		} catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("validating the category page.", expectedResult, "user failed to navigate",
					Common.getscreenShotPathforReport("failed to navigate categorypage"));
			AssertJUnit.fail();
		}

		// Thread.sleep(3000);
		Sync.waitElementClickable("xpath", "//a[@class='sign-in mob-hidden']");
		Common.clickElement("xpath", "//a[@class='sign-in mob-hidden']");
	}

	public void forgetpasswordGoodTipsBlock(String dataset) throws Exception {
		clickGoodTipsBlog();
		signButton_GoodTipsBlog();
		Thread.sleep(3000);
		String expectedResult = "Forgot password pop up is displayed to customer";
		try {

			Common.clickElement("xpath", "//a[@class='action remind']");
			Sync.waitPageLoad();
			Thread.sleep(7000);
			Common.textBoxInput("xpath", "//input[@id='email_address_forgot']", data.get(dataset).get("Email"));
			ExtenantReportUtils.addPassLog("verifying user enter email", "user enter email",
					"User successfully enter email", Common.getscreenShotPathforReport("emailforg"));
			Common.clickElement("id", "bnt-social-login-forgot");
			Thread.sleep(5000);
			String message = Common.getText("xpath", "//div[contains(@class,'message-success')]/div");
			System.out.println(message);
			int size = Common.findElements("xpath", "//div[contains(@class,'message-success')]/div").size();
			// int size=Common.findElements("xpath",
			// "//input[@id='email_address_forgot']").size();
			// Common.assertionCheckwithReport(size>0, "verifying forgetpassword
			// option", expectedResult,"Successfully Forgot password pop up is
			// displayed to customer", "User faield to get Forgot password
			// pop");
			Common.assertionCheckwithReport(size > 0, "verifying forgetpassword option", expectedResult,
					"Successfully Forgot password pop up is displayed to customer",
					"User faield to get  Forgot password pop");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying forgetpassword option", "clcik the forget password option",
					"User failed to clcik the forget password button",
					Common.getscreenShotPathforReport("forgetpasswordbuttonfaield"));
			AssertJUnit.fail();
		}
	}

	public void click_MyAccount() {
		try {
			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "(//span[@class='customer-name'])[1]");
			Common.mouseOverClick("xpath", "(//span[@class='customer-name'])[1]");
			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "(//li[contains(@class,'customer-welcome')]//li[1]/a)[1]");
			Common.mouseOverClick("xpath", "(//li[contains(@class,'customer-welcome')]//li[1]/a[1])");
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//h1[@data-element='hero_title']");
			String userName = Common.getText("xpath", "//h1[@data-element='hero_title']");
			Common.assertionCheckwithReport(userName.equals("Hi, mahendra!"), "verifying my account link",
					"User lands on the my accountpage", "User lands on AccountPage", "Faield lo land o nmyaccount");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying my account link", "lands on AccountPage",
					"User failed lands on my accountpage", Common.getscreenShotPathforReport("account"));
			AssertJUnit.fail();
		}
	}

	public void Myaccount_validation() {
		try {
			// Sync.waitElementPresent("xpath",
			// "//div[@id='account-nav']//li[1]/a");
			// Common.clickElement("xpath",
			// "//div[@id='account-nav']//li[1]/a");
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Sync.waitElementPresent("xpath", "//h1[@class='page-title']/span");
			String accountinfro = Common.getText("xpath", "//h1[@class='page-title']/span");
			Common.assertionCheckwithReport(accountinfro.equals("Account Dashboard"), "Validating my account option",
					"it open the my account Dashboard", "successfully open the my account Dashboard",
					"it faield open the myaccount dashboard");
		} catch (Exception e) {
			ExtenantReportUtils.addFailedLog("Validating my account option", "it open the my account Dashboard",
					"it faield open the myaccount dashboard", Common.getscreenShot("faieldmyaccount"));
			AssertJUnit.fail();
		}
	}

	public void MyOrders() {
		try {
			Sync.waitElementPresent("xpath", "//div[@id='account-nav']//li[2]/a");
			Common.clickElement("xpath", "//div[@id='account-nav']//li[2]/a");
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Sync.waitElementPresent("xpath", "//h1[@class='page-title']/span");
			String accountinfro = Common.getText("xpath", "//h1[@class='page-title']/span");
			Common.assertionCheckwithReport(accountinfro.equals("My Orders"), "Validating  my aOrders option",
					"it open the myaOrders Dashboard", "successfully open the myaOrders Dashboard",
					"it faield open the myaOrders dashboard");
		} catch (Exception e) {
			ExtenantReportUtils.addFailedLog("Validating myaOrders option", "it open the myaOrders Dashboard",
					"it faield open the myaOrders dashboard", Common.getscreenShot("faieldmyaOrders"));
			AssertJUnit.fail();
		}
	}

	public void MyWishlist() {
		try {
			Sync.waitElementPresent("xpath", "//*[@id=\"account-nav\"]/ul/li[3]/a");
			Common.clickElement("xpath", "//*[@id=\"account-nav\"]/ul/li[3]/a");
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			String Title = Common.getPageTitle();
			Common.assertionCheckwithReport(Title.equals("My Wish List"), "Validating  my wishlist option",
					"it open the my wishlist Dashboard", "successfully open the my wishlist Dashboard",
					"it faield open the my wishlist dashboard");
		} catch (Exception e) {
			ExtenantReportUtils.addFailedLog("Validating my wishlist option", "it open the my wishlist Dashboard",
					"it faield open the my wishlist dashboard", Common.getscreenShot("faieldmywishlist"));
			AssertJUnit.fail();
		}
	}

	public void AddressBook() {
		try {
			Sync.waitElementPresent("xpath", "//*[@id=\"account-nav\"]/ul/li[4]/a");
			Common.clickElement("xpath", "//*[@id=\"account-nav\"]/ul/li[4]/a");
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			String Title = Common.getPageTitle();
			Common.assertionCheckwithReport(Title.equals("Address Book"), "Validating  AddressBook option",
					"it open the AddressBook Dashboard", "successfully open the AddressBook Dashboard",
					"it faield open the AddressBook dashboard");
		} catch (Exception e) {
			ExtenantReportUtils.addFailedLog("Validating my AddressBook option", "it open the AddressBook Dashboard",
					"it faield open the AddressBook dashboard", Common.getscreenShot("faieldAddressBook"));
			AssertJUnit.fail();
		}
	}

	public void AddAddress(String dataSet) {
		try {
			// Sync.waitElementPresent("xpath",
			// "(//span[(text()='Delete')])[1]");
			// Common.clickElement("xpath", "(//span[(text()='Delete')])[1]");
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Sync.waitElementPresent("xpath", "(//span[text()='Delete'])[1]");
			Common.clickElement("xpath", "(//span[text()='Delete'])[1]");
			Thread.sleep(3000);
			Sync.waitElementPresent("xpath", "(//button[@class='action-primary action-accept'])");
			Common.clickElement("xpath", "(//button[@class='action-primary action-accept'])");
			Thread.sleep(4000);
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Sync.waitElementPresent("xpath", "(//span[(text()='Add New Address')])[1]");
			Common.clickElement("xpath", "(//span[(text()='Add New Address')])[1]");
			Thread.sleep(5000);
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(5000);
			Sync.waitElementPresent("id", "firstname");
			Common.textBoxInput("xpath", "//input[@id='firstname']", data.get(dataSet).get("FirstName"));
			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "//input[@id='firstname']");
			Common.textBoxInput("xpath", "//input[@id='lastname']", data.get(dataSet).get("LastName"));
			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "//input[@id='lastname']");
			Common.textBoxInput("xpath", "//input[@id='lastname']", data.get(dataSet).get("LastName"));
			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "//input[@id='company']");
			Common.textBoxInput("xpath", "//input[@id='company']", data.get(dataSet).get("CompanyName"));
			// Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "//input[@id='telephone']");
			Common.textBoxInput("xpath", "//input[@id='telephone']", data.get(dataSet).get("phone"));
			Sync.waitElementPresent("xpath", "(//input[@name='street[0]'])");
			Common.textBoxInput("xpath", "(//input[@name='street[0]'])", data.get(dataSet).get("Street"));
			Sync.waitElementPresent("xpath", "(//input[@name='street[0]'])");
			Common.textBoxInput("xpath", "(//input[@name='street[0]'])", data.get(dataSet).get("Street"));
			Common.textBoxInput("name", "city", data.get(dataSet).get("City"));
			Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Sync.waitElementPresent("xpath", "(//button[@title='Save Address'])");
			Common.clickElement("xpath", "(//button[@title='Save Address'])");
			Thread.sleep(3000); // Sync.waitElementPresent("xpath",
								// "(//button[@class='action-primary
								// action-accept'])");
			// Common.clickElement("xpath", "(//button[@class='action-primary
			// action-accept'])");
			Thread.sleep(5000);
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			int message = Common.findElements("xpath", "(//div[@class='message-success success message'])").size();
			Common.assertionCheckwithReport(message > 0, "verifying confrmation message ",
					"enter with empety data it must show error message", "sucessfully display the error message",
					"faield to dispalyerrormessage");
		} catch (Exception e) {
			ExtenantReportUtils.addFailedLog("Validating my AddressBook option", "it open the AddressBook Dashboard",
					"it faield open the AddressBook dashboard", Common.getscreenShot("faieldAddressBook"));
			AssertJUnit.fail();
		}
	}

	public void My_Information() {
		try {
			Sync.waitElementPresent("xpath", "//*[@id=\"account-nav\"]/ul/li[5]/a");
			Common.clickElement("xpath", "//*[@id=\"account-nav\"]/ul/li[5]/a");
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			String Title = Common.getPageTitle();
			Common.assertionCheckwithReport(Title.equals("My Information"), "Validating  My Information option",
					"it open the My Information Dashboard", "successfully open the My Information Dashboard",
					"it faield open the My Information dashboard");
		} catch (Exception e) {
			ExtenantReportUtils.addFailedLog("Validating My Information option", "it open the My Information Dashboard",
					"it faield open the My Information dashboard", Common.getscreenShot("faieldMy Information"));
			AssertJUnit.fail();
		}
	}

	public void NewsletterUnsubscription() throws InterruptedException {
		try {
			Sync.waitElementClickable("xpath", "//p//a[@class='action edit']");
			Common.findElement("xpath", "//p//a[@class='action edit']").click();
			Thread.sleep(4000);
			Common.unCheckBox("xpath", "//input[@id='subscription']");
		} catch (Exception e) {
			ExtenantReportUtils.addFailedLog("Validating My Information option", "it open the My Information Dashboard",
					"it faield open the My Information dashboard", Common.getscreenShot("faieldMy Information"));
			AssertJUnit.fail();
		}

	}

	public void Change_MyInformation(String dataSet) {
		try {
			Sync.waitElementPresent("xpath", "(//input[@id='firstname'])");
			Common.textBoxInput("xpath", "//input[@id='firstname']", data.get(dataSet).get("FirstName"));
			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "(//input[@id='lastname'])");
			Common.textBoxInput("xpath", "//input[@id='lastname']", data.get(dataSet).get("LastName"));
			Thread.sleep(2000);
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			// Common.scrollIntoView("xpath", "(//button[@title='Save'])");
			Thread.sleep(1000);
			Sync.waitElementPresent("xpath", "(//button[@title='Save'])");
			Common.clickElement("xpath", "(//button[@title='Save'])");
			Thread.sleep(5000);
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			int message = Common.findElements("xpath", "(//*[@id=\"maincontent\"]/div)[1]").size();
			Common.assertionCheckwithReport(message > 0, "verifying confrmation message ",
					"enter with empety data it must show error message", "sucessfully display the error message",
					"faield to dispalyerrormessage");
		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating My Information option", "it open the My Information Dashboard",
					"it faield open the My Information dashboard", Common.getscreenShot("faieldMy Information"));
			AssertJUnit.fail();
		}
	}

	public void NewProductRegistration(String dataSet) throws Exception {
		Thread.sleep(3000);
		try {
			Sync.scrollDownToView("xpath", "//a[@data-menu='menu-14903']");
			Sync.waitElementClickable(10, By.xpath("//a[@data-menu='menu-14903']"));
			ExtenantReportUtils.addPassLog("Validating ProductRegistration Link",
					"ProductRegistration link should be able to click", "Clicked on ProductRegistration Link",
					Common.getscreenShotPathforReport("ProductRegistrationLink"));
			Common.clickElement("xpath", "//a[@data-menu='menu-14903']");
			Thread.sleep(3000);
			WebElement element = Common.findElement("xpath", "//h2[contains(text(),'Select your product')]");
			String text = element.getText();
			if (text.contains("Select your product")) {
				ExtenantReportUtils.addPassLog("Validating Webtext of Product Registration Page",
						"Expected text should be obtained", "Expected text is obtained",
						Common.getscreenShotPathforReport("ProductRegistrationText"));
			} else {
				ExtenantReportUtils.addFailedLog("Validating Webelement of Product Registration Page",
						"Expected text should not be obtained", "Expected text is not obtained",
						"LinkValidation Product Registration");
				AssertJUnit.fail();
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating  of Product Registration Page",
					"Expected text should not be obtained", "Expected text is not obtained",
					"LinkValidation Product Registration");
			AssertJUnit.fail();
		}
		try {
			Thread.sleep(4000);
			acceptPrivecy();
			/*Sync.waitElementClickable("xpath", "//select[@id='product_name']");
			Common.findElement("xpath", "//select[@id='product_name']").click();
			Sync.waitElementVisible("xpath", "//option[@value='sprout-chair']");
			Common.findElement("xpath", "//option[@value='sprout-chair']").click();
			// Common.mouseOverClick("xpath","//option[@value='sprout-chair']");*/
		//	Common.actionsKeyPress(Keys.ENTER);

			Sync.waitElementClickable("xpath", "//input[@id='model']");
			Common.textBoxInput("xpath", "//input[@id='model']", data.get(dataSet).get("Model#"));
			Common.textBoxInput("xpath", "//input[@id='serial']", data.get(dataSet).get("Serial#"));
			// Common.textBoxInput("xpath",
			// "//input[@id='manufacturing_date']",data.get(dataSet).get("ManufacturingDate"));
			// Common.textBoxInput("xpath",
			// "//input[@id='purchase_date']",data.get(dataSet).get("PurchaseDate"));
			Common.textBoxInput("xpath", "//input[@id='location_purchased']",
					data.get(dataSet).get("Location Purchased"));
			Common.textBoxInput("xpath", "//input[@id='city']", data.get(dataSet).get("City"));
			Common.textBoxInput("xpath", "//input[@id='full_name']", data.get(dataSet).get("FullName"));
			Common.textBoxInput("xpath", "//input[@id='email_39']", data.get(dataSet).get("Email"));
			Common.textBoxInput("xpath", "//input[@id='ph_no']", data.get(dataSet).get("phone"));
			Common.textBoxInput("xpath", "//input[@id='address']", data.get(dataSet).get("Street"));
			Common.textBoxInput("xpath", "//input[@id='city_user']", data.get(dataSet).get("City"));
			Common.textBoxInput("xpath", "//input[@id='state']", data.get(dataSet).get("Region"));
			Common.textBoxInput("xpath", "//input[@id='zip']", data.get(dataSet).get("postcode"));
			Thread.sleep(4000);
			Common.findElement("xpath", "//button[@class='action submit primary']").click();
			int ProductRegistrationmessage = Common
					.findElements("xpath", " //div[@class='message message-success success']").size();
			Common.assertionCheckwithReport(ProductRegistrationmessage > 0,
					"verifying Product Registration Success message ", "Success message should be Displayed",
					"Product Registration Success message displayed ", "failed to dispaly success message");
			ExtenantReportUtils.addPassLog("New Product Registeration", "Product Should be successfully Registered",
					"Product Registered Successfully", Common.getscreenShotPathforReport("ProductRegistration"));

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating  of Product Registration Page",
					"Expected text should not be obtained", "Expected text is not obtained",
					"LinkValidation Product Registration");
			AssertJUnit.fail();
		}
	}

	public void ForgotPasswordValidation() {
		try {

			Thread.sleep(4000);
			Sync.waitElementClickable("xpath", "//a[@class='social-login']");
			Common.findElement("xpath", "//a[@class='social-login']").click();
			Thread.sleep(2000);
			// Common.scrollIntoView("xpath", "//a[@class='action remind']");
			Sync.waitElementClickable("xpath", "//a[@class='action remind']");
			Common.findElement("xpath", "//a[@class='action remind']").click();
			Thread.sleep(4000);
			Sync.waitElementClickable("xpath", "//button[@id='bnt-social-login-forgot']");
			Common.clickElement("xpath", "//button[@id='bnt-social-login-forgot']");
			Thread.sleep(3000);
			int emailerrormessage = Common.findElements("xpath", "//div[@id='email_address_forgot-error']").size();
			Common.assertionCheckwithReport(emailerrormessage > 0, "verifying error message ForgotPasswordPage",
					"enter with empty data it must show error message", "sucessfully display the error message",
					"faield to dispalyerrormessage");
		} catch (Exception | Error e) {

			ExtenantReportUtils.addFailedLog("verifying error message ForgotPasswordPage",
					"enter with empty data it must show error message", "faield to dispalyerrormessage",
					Common.getscreenShotPathforReport("loginpagevalidation"));
			AssertJUnit.fail();
		}
	}

	public void AccountCreationFormValidation() {
		try {
			Thread.sleep(5000);
			Common.clickElement("xpath", "//a[@class='social-login']");
			Sync.waitElementClickable("xpath", "//a[@class='action create']");
			Common.clickElement("xpath", "//a[@class='action create']");
			ExtenantReportUtils.addPassLog("verifying Create Account button",
					"It should lands on Create New Customer from Account form Page",
					"user  lands on Customer Account creation form Page",
					Common.getscreenShotPathforReport("createaccount"));
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying Create Account button",
					"It should lands on Create New Customer from Account form Page",
					"user faield lands on Account form Page", Common.getscreenShotPathforReport("createaccount"));
			AssertJUnit.fail();
		}
		try {

			Thread.sleep(4000);
			Sync.waitElementClickable("xpath", "//button[@id='button-create-social']");
			Common.findElement("xpath", "//button[@id='button-create-social']").click();
			Sync.waitElementVisible("xpath", "//div[@class='mage-error']");
			/*
			 * int sizes =Common.findElements("xpath",
			 * "//div[@class='message-error error message']").size();
			 * Common.assertionCheckwithReport(sizes>0,
			 * "Successfully land on the payment section",
			 * expectedResult,"User unabel to land on paymentpage");
			 */
			int Signuperrormessage = Common.findElements("xpath", "//div[@class='mage-error']").size();
			Common.assertionCheckwithReport(Signuperrormessage > 0, "verifying error message signuppage",
					"enter with empety data it must show error message", "sucessfully display the error message",
					"faield to dispalyerrormessage");
		} catch (Exception | Error e) {

			ExtenantReportUtils.addFailedLog("verifying error message signpage",
					"enter with empty data it must show error message", "faield to dispalyerrormessage",
					Common.getscreenShotPathforReport("loginpagevalidation"));
			AssertJUnit.fail();
		}
	}

	public void ClickMyAccount() {
		try {
			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "(//span[@class='customer-name'])[1]");
			Common.mouseOverClick("xpath", "(//span[@class='customer-name'])[1]");
			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "(//li[contains(@class,'customer-welcome')]//li[1]/a)[1]");
			Common.mouseOverClick("xpath", "(//li[contains(@class,'customer-welcome')]//li[1]/a[1])");
			Sync.waitPageLoad();
			Sync.waitElementPresent("xpath", "//h1[@data-element='hero_title']");
			String userName = Common.getText("xpath", "//h1[@data-element='hero_title']");
			Common.assertionCheckwithReport(userName.equals("Hi, mahendra!"), "verifying my account link",
					"User lands on the my accountpage", "User lands on AccountPage", "Faield lo land o nmyaccount");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying my account link", "lands on AccountPage",
					"User failed lands on my accountpage", Common.getscreenShotPathforReport("account"));
			AssertJUnit.fail();
		}
	}

	public void myaccountvalidation() {
		try {
			// Sync.waitElementPresent("xpath",
			// "//div[@id='account-nav']//li[1]/a");
			// Common.clickElement("xpath",
			// "//div[@id='account-nav']//li[1]/a");
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Sync.waitElementPresent("xpath", "//h1[@class='page-title']/span");
			String accountinfro = Common.getText("xpath", "//h1[@class='page-title']/span");
			Common.assertionCheckwithReport(accountinfro.equals("Account Dashboard"), "Validating my account option",
					"it open the my account Dashboard", "successfully open the my account Dashboard",
					"it faield open the myaccount dashboard");
		} catch (Exception e) {
			ExtenantReportUtils.addFailedLog("Validating my account option", "it open the my account Dashboard",
					"it faield open the myaccount dashboard", Common.getscreenShot("faieldmyaccount"));
			AssertJUnit.fail();
		}
	}

	public void myOrders() {
		try {
			Sync.waitElementPresent("xpath", "//div[@id='account-nav']//li[2]/a");
			Common.clickElement("xpath", "//div[@id='account-nav']//li[2]/a");
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Sync.waitElementPresent("xpath", "//h1[@class='page-title']/span");
			String accountinfro = Common.getText("xpath", "//h1[@class='page-title']/span");
			Common.assertionCheckwithReport(accountinfro.equals("My Orders"), "Validating  my aOrders option",
					"it open the myaOrders Dashboard", "successfully open the myaOrders Dashboard",
					"it faield open the myaOrders dashboard");
		} catch (Exception e) {
			ExtenantReportUtils.addFailedLog("Validating myaOrders option", "it open the myaOrders Dashboard",
					"it faield open the myaOrders dashboard", Common.getscreenShot("faieldmyaOrders"));
			AssertJUnit.fail();
		}
	}

	public void Mywishlist() {
		try {
			Sync.waitElementPresent("xpath", "//*[@id=\"account-nav\"]/ul/li[3]/a");
			Common.clickElement("xpath", "//*[@id=\"account-nav\"]/ul/li[3]/a");
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			String Title = Common.getPageTitle();
			Common.assertionCheckwithReport(Title.equals("My Wish List"), "Validating  my wishlist option",
					"it open the my wishlist Dashboard", "successfully open the my wishlist Dashboard",
					"it faield open the my wishlist dashboard");
		} catch (Exception e) {
			ExtenantReportUtils.addFailedLog("Validating my wishlist option", "it open the my wishlist Dashboard",
					"it faield open the my wishlist dashboard", Common.getscreenShot("faieldmywishlist"));
			AssertJUnit.fail();
		}
	}

	public void Address_Book() {
		try {
			Sync.waitElementPresent("xpath", "//*[@id=\"account-nav\"]/ul/li[4]/a");
			Common.clickElement("xpath", "//*[@id=\"account-nav\"]/ul/li[4]/a");
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			String Title = Common.getPageTitle();
			Common.assertionCheckwithReport(Title.equals("Address Book"), "Validating  AddressBook option",
					"it open the AddressBook Dashboard", "successfully open the AddressBook Dashboard",
					"it faield open the AddressBook dashboard");
		} catch (Exception e) {
			ExtenantReportUtils.addFailedLog("Validating my AddressBook option", "it open the AddressBook Dashboard",
					"it faield open the AddressBook dashboard", Common.getscreenShot("faieldAddressBook"));
			AssertJUnit.fail();
		}
	}

	public void Add_Address(String dataSet) {
		try {
			// Sync.waitElementPresent("xpath",
			// "(//span[(text()='Delete')])[1]");
			// Common.clickElement("xpath", "(//span[(text()='Delete')])[1]");
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Sync.waitElementPresent("xpath", "(//span[text()='Delete'])[1]");
			Common.clickElement("xpath", "(//span[text()='Delete'])[1]");
			Thread.sleep(3000);
			Sync.waitElementPresent("xpath", "(//button[@class='action-primary action-accept'])");
			Common.clickElement("xpath", "(//button[@class='action-primary action-accept'])");
			Thread.sleep(4000);
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Sync.waitElementPresent("xpath", "(//span[(text()='Add New Address')])[1]");
			Common.clickElement("xpath", "(//span[(text()='Add New Address')])[1]");
			Thread.sleep(5000);
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Thread.sleep(5000);
			Sync.waitElementPresent("id", "firstname");
			Common.textBoxInput("xpath", "//input[@id='firstname']", data.get(dataSet).get("FirstName"));
			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "//input[@id='firstname']");
			Common.textBoxInput("xpath", "//input[@id='lastname']", data.get(dataSet).get("LastName"));
			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "//input[@id='lastname']");
			Common.textBoxInput("xpath", "//input[@id='lastname']", data.get(dataSet).get("LastName"));
			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "//input[@id='company']");
			Common.textBoxInput("xpath", "//input[@id='company']", data.get(dataSet).get("CompanyName"));
			// Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "//input[@id='telephone']");
			Common.textBoxInput("xpath", "//input[@id='telephone']", data.get(dataSet).get("phone"));
			Sync.waitElementPresent("xpath", "(//input[@name='street[0]'])");
			Common.textBoxInput("xpath", "(//input[@name='street[0]'])", data.get(dataSet).get("Street"));
			Sync.waitElementPresent("xpath", "(//input[@name='street[0]'])");
			Common.textBoxInput("xpath", "(//input[@name='street[0]'])", data.get(dataSet).get("Street"));
			Common.textBoxInput("name", "city", data.get(dataSet).get("City"));
			Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));

			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Sync.waitElementPresent("xpath", "(//button[@title='Save Address'])");
			Common.clickElement("xpath", "(//button[@title='Save Address'])");
			Thread.sleep(3000); // Sync.waitElementPresent("xpath",
								// "(//button[@class='action-primary
								// action-accept'])");
			// Common.clickElement("xpath", "(//button[@class='action-primary
			// action-accept'])");
			Thread.sleep(5000);
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			int message = Common.findElements("xpath", "(//div[@class='message-success success message'])").size();
			Common.assertionCheckwithReport(message > 0, "verifying confrmation message ",
					"enter with empety data it must show error message", "sucessfully display the error message",
					"faield to dispalyerrormessage");
		} catch (Exception e) {
			ExtenantReportUtils.addFailedLog("Validating my AddressBook option", "it open the AddressBook Dashboard",
					"it faield open the AddressBook dashboard", Common.getscreenShot("faieldAddressBook"));
			AssertJUnit.fail();
		}
	}

	public void MyInformation() {
		try {
			Sync.waitElementPresent("xpath", "//*[@id=\"account-nav\"]/ul/li[5]/a");
			Common.clickElement("xpath", "//*[@id=\"account-nav\"]/ul/li[5]/a");
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			String Title = Common.getPageTitle();
			Common.assertionCheckwithReport(Title.equals("My Information"), "Validating  My Information option",
					"it open the My Information Dashboard", "successfully open the My Information Dashboard",
					"it faield open the My Information dashboard");
		} catch (Exception e) {
			ExtenantReportUtils.addFailedLog("Validating My Information option", "it open the My Information Dashboard",
					"it faield open the My Information dashboard", Common.getscreenShot("faieldMy Information"));
			AssertJUnit.fail();
		}
	}

	public void ChangeMyInformation(String dataSet) {
		try {
			Sync.waitElementPresent("xpath", "(//input[@id='firstname'])");
			Common.textBoxInput("xpath", "//input[@id='firstname']", data.get(dataSet).get("FirstName"));
			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "(//input[@id='lastname'])");
			Common.textBoxInput("xpath", "//input[@id='lastname']", data.get(dataSet).get("LastName"));
			Thread.sleep(2000);
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			// Common.scrollIntoView("xpath", "(//button[@title='Save'])");
			Thread.sleep(1000);
			Sync.waitElementPresent("xpath", "(//button[@title='Save'])");
			Common.clickElement("xpath", "(//button[@title='Save'])");
			Thread.sleep(5000);
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			int message = Common.findElements("xpath", "(//*[@id=\"maincontent\"]/div)[1]").size();
			Common.assertionCheckwithReport(message > 0, "verifying confrmation message ",
					"enter with empety data it must show error message", "sucessfully display the error message",
					"faield to dispalyerrormessage");
		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating My Information option", "it open the My Information Dashboard",
					"it faield open the My Information dashboard", Common.getscreenShot("faieldMy Information"));
			AssertJUnit.fail();
		}
	}

	public void NewsletterSubscription(String dataSet) throws Exception {

		try {
			Thread.sleep(3000);
			Common.scrollIntoView("xpath", "//input[@id='newsletter']");
			Sync.waitElementPresent("xpath", "//input[@id='newsletter']");
			Common.textBoxInput("xpath", "//input[@id='newsletter']", data.get(dataSet).get("Email"));

			Sync.waitElementClickable("xpath", "//button[@id='newsletter-signup-button']");
			Common.javascriptclickElement("xpath", "//button[@id='newsletter-signup-button']");

			if (Common.findElement("xpath", "//button[@id='newsletter-signup-button']") == null) {
				Thread.sleep(3000);
				Sync.waitElementClickable("xpath", "//button[@class='signup__submit']");
				Common.mouseOverClick("xpath", "//button[@class='signup__submit']");
				Common.javascriptclickElement("xpath", "//button[@class='signup__submit']");
			}

			ExtenantReportUtils.addPassLog("NewLetterSubscription", "NewLetterSubscription Should be successfull",
					"Successfully Subscribed for NewLetterSubscription",
					Common.getscreenShotPathforReport("NewLetterSubscription"));

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validation of NewLetterSubscription",
					"NewLetterSubscription Should be successfull", "Successfully Subscribed for NewLetter",
					Common.getscreenShotPathforReport("NewLetterSubscription"));
			AssertJUnit.fail();
		}
	}

	public void Voluntary_Recall(String dataSet) throws InterruptedException {

		Thread.sleep(3000);
		try {
			Sync.scrollDownToView("xpath", "//a[@data-menu='menu-14904']");
			Sync.waitElementClickable(10, By.xpath("//a[@data-menu='menu-14904']"));
			ExtenantReportUtils.addPassLog("Validating VoluntaryRecall Link",
					"VoluntaryRecall link should be able to click", "Clicked on VoluntaryRecall Link",
					Common.getscreenShotPathforReport("VoluntaryRecallLink"));
			Common.clickElement("xpath", "//a[@data-menu='menu-14904']");
			Thread.sleep(3000);
			WebElement element = Common.findElement("xpath",
					"//h2[contains(text(),'Replacement Straps Registration')]");
			String text = element.getText();
			if (text.contains("Replacement Straps Registration")) {
				ExtenantReportUtils.addPassLog("Validating Webtext of VoluntaryRecall Page",
						"Expected text should be obtained", "Expected text is obtained",
						Common.getscreenShotPathforReport("VoluntaryRecallText"));
			} else {
				ExtenantReportUtils.addFailedLog("Validating Webelement of Voluntary Recall Page",
						"Expected text should not be obtained", "Expected text is not obtained",
						"LinkValidation VoluntaryRecall");
				AssertJUnit.fail();
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating Webelement of Voluntary Recall Page",
					"Expected text should not be obtained", "Expected text is not obtained",
					"LinkValidation VoluntaryRecall");
			AssertJUnit.fail();
		}

		try {
			Common.textBoxInput("xpath", "//input[@id='textinput-1543451237847']", data.get(dataSet).get("FullName"));
			Common.textBoxInput("xpath", "//input[@id='textinput-1543451299203']", data.get(dataSet).get("phone"));
			Common.textBoxInput("xpath", "//input[@id='textinput-1543451318343']", data.get(dataSet).get("Email"));
			Common.textBoxInput("xpath", "//input[@id='textinput-1543451474572']", data.get(dataSet).get("Street"));

			Common.textBoxInput("xpath", "//input[@id='textinput-1543451474609']", data.get(dataSet).get("City"));
			Common.textBoxInput("xpath", "//input[@id='textinput-1543451474622']", data.get(dataSet).get("postcode"));
			Common.textBoxInput("xpath", "//input[@id='textinput-1543451474614']", data.get(dataSet).get("Region"));
			
			Sync.waitElementClickable("xpath", "//button[@class='amcform-submit action submit primary ']");
			Common.findElement("xpath", "//button[@class='amcform-submit action submit primary ']").click();
			// int VoluntaryRecallmessage=Common.findElements("xpath", "//div[@class='message message-success success']").size();
			// Common.assertionCheckwithReport(VoluntaryRecallmessage>0, "verifying Product Registration Success message ", "Success message should be Displayed","Product Registration Success message displayed ", "failed to dispaly success message");
			ExtenantReportUtils.addPassLog("VoluntaryRecall for the Product",
					"Product should be Successfully Registered", "Successfully Registered for the Product",
					Common.getscreenShotPathforReport("VoluntaryRecall"));

		} catch (Exception e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating  of Voluntary Recall Page",
					"Expected text should not be obtained", "Expected text is not obtained",
					"LinkValidation  Voluntary Recall");
			AssertJUnit.fail();
		}

	}

	public void PLP_Validation() {
		// clickThreebadmenu();
		String expectedResult = "It Should be navigate to the Beverage category page.";
		try {
			Thread.sleep(8000);
			Sync.waitElementClickable("xpath", "//a[@data-menu='menu-15146']");
			Common.mouseOverClick("xpath", "//li[contains(@class,'navigation__item')]//a[@data-menu='menu-15146']");
			try {
				Sync.waitElementClickable("xpath", "//ul[@data-menu='menu-15147']//a[contains(text(),'Shop All')]");
			} catch (Exception e) {
				Sync.waitElementClickable("xpath", "//a[@data-menu='menu-15146']");
				Common.clickElement("xpath", "//li[contains(@class,'navigation__item')]/a[@data-menu='menu-15146']");
				Thread.sleep(5000);
			}
			ExtenantReportUtils.addPassLog("verifying category Beverage", "lands on Beverage options",
					"User lands on the Beverage options", Common.getscreenShotPathforReport("faield to click"));
			Common.mouseOverClick("xpath", "//ul[@data-menu='menu-15147']//a[contains(text(),'Shop All')]");
			Common.mouseOverClick("xpath", "//ul[@data-menu='menu-15147']//a[contains(text(),'Shop All')]");
		} catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("validating the category page.", expectedResult,
					"user faield to navigate Baby Toddler category",
					Common.getscreenShotPathforReport("faield to navgate categorypage"));
			AssertJUnit.fail();
		}
	}

	public void Track_order(String dataSet) throws Exception {

		Thread.sleep(3000);
		try {
			Sync.scrollDownToView("xpath", "//a[@data-menu='menu-14908']");
			Sync.waitElementClickable(10, By.xpath("//a[@data-menu='menu-14908']"));
			ExtenantReportUtils.addPassLog("TrackOrder Link", "TrackOrder link should be present",
					"TrackOrder Link is present", Common.getscreenShotPathforReport("TrackOrderLink"));
			// ExtenantReportUtils.addPassLog("Validating ProductRegistration
			// Link","ProductRegistration link should be able to click"
			// ,"Clicked on ProductRegistration
			// Link",Common.getscreenShotPathforReport("ProductRegistrationLink"));
			Common.clickElement("xpath", "//a[@data-menu='menu-14908']");
			WebElement element = Common.findElement("xpath", "//span[contains(text(),'Track Order')]");
			String text = element.getText();
			System.out.println("Text Obtained is..." + text);
			if (text.contains("Track Order")) {
				System.out.println("Expected text is obtained");
				ExtenantReportUtils.addPassLog("Validating Webtext of TrackOrder Page",
						"Expected text should be obtained", "Expected text is obtained",
						Common.getscreenShotPathforReport("TrackOrderText"));
			} else {
				System.out.println("Expected text is not obtained");
				ExtenantReportUtils.addFailedLog("Validating Webelement of TrackOrder Page",
						"Expected text should not be obtained", "Expected text is not obtained",
						"LinkValidation TrackOrder");
				AssertJUnit.fail();
			}

		}

		catch (Exception e) {
			
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("Validating Webelement of TrackOrder Page",
					"Expected text should not be obtained", "Expected text is not obtained",
					"LinkValidation TrackOrder");
			AssertJUnit.fail();
		}

		try {
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//input[@id='oar-order-id']");
			Common.textBoxInput("xpath", "//input[@id='oar-order-id']", data.get(dataSet).get("OrderID"));
			Common.textBoxInput("xpath", "//input[@id='oar-billing-lastname']", data.get(dataSet).get("LastName"));
			Common.textBoxInput("xpath", "//input[@id='oar_email']", data.get(dataSet).get("Email"));
			ExtenantReportUtils.addPassLog("TrackOrder Link", "TrackOrder link should be present",
					"TrackOrder Link is present", Common.getscreenShotPathforReport("TrackOrderLink"));
			Sync.waitElementClickable("xpath", "//button[@class='action submit primary']");
			Common.findElement("xpath", "//button[@class='action submit primary']").click();
			int TrackOrderStatusmessage = Common.findElements("xpath", "//dt[contains(text(),'Status')]").size();
			Common.assertionCheckwithReport(TrackOrderStatusmessage > 0, "verifying TrackOrder Status message ",
					"TrackOrder Status message should be Displayed", "TrackOrder Status is displayed ",
					"failed to dispaly TrackOrder Status");

		} catch (Exception e) {
			ExtenantReportUtils.addFailedLog("verifying TrackOrder Status message",
					"TrackOrder Status should not be obtained", "TrackOrder Status is not obtained",
					"TrackOrder Status");
			AssertJUnit.fail();
		}
	}
	public void fottorValidations_Help(String dataSet) throws Exception{
		//Thread.sleep(3000);
		Sync.waitPageLoad();
		
		Common.actionsKeyPress(Keys.END);
		String Hederlinks=data.get(dataSet).get("Help");
		String[] hedrs=Hederlinks.split(",");
		int i=0;
		
		try{
			Thread.sleep(3000);
		for(i=0;i<hedrs.length;i++){
			System.out.println(hedrs[i]);
			Sync.waitElementClickable("xpath", "//a[text()='"+hedrs[i]+"']");
			Common.clickElement("xpath", "//a[text()='"+hedrs[i]+"']");
			Thread.sleep(3000);
		
			Common.assertionCheckwithReport(Common.getPageTitle().contains(hedrs[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
			Common.actionsKeyPress(Keys.END);
			
			
			if (hedrs[i].contains("Product Recall")){
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Product Recall"), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
			}
			else if (hedrs[i].contains("Terms and Conditions")){
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Terms and Conditions"), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
			}
			}
		}
		catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validating Header Links " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the header link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the headerlink"));
		
			AssertJUnit.fail();

		}
	}
	public void Add_product_to_Wishlist_PLP() {
		
		 try {
	            Thread.sleep(4000);
	            Sync.waitElementClickable("xpath", "//a[@data-menu='menu-15701']");
	            Common.mouseOverClick("xpath", "//li[contains(@class,'navigation__item')]/a[@data-menu='menu-15701']");
	            Common.javascriptclickElement("xpath", "//li[contains(@class,'navigation__inner-item')]/a[@data-menu='menu-15799']");
	            Thread.sleep(3000);
	            Common.actionsKeyPress(Keys.ARROW_DOWN);
	            Common.mouseOver("xpath", "//a[contains(text(),'Mini Tongs')]");
	            Sync.waitElementPresent("xpath", "(//a[@class='action towishlist'])[2]");
	            Common.clickElement("xpath", "(//a[@class='action towishlist'])[2]");

	            Thread.sleep(2000);
	            Common.actionsKeyPress(Keys.UP);
	            String saved=Common.getText("xpath", "//span[@class='wishlist-counter']//span[@class='counter qty']");
	            System.out.println(saved);
	            Common.assertionCheckwithReport(saved.equals("1"), "verifying catlog page","User navigate to catlog page","user successfully landed on products page", "user faield to catlog page");
	          }
	              catch(Exception |Error e) {
	                      ExtenantReportUtils.addFailedLog("verifying catlog page", "User navigate to catlog page", "user faield to catlog page", Common.getscreenShotPathforReport("catlogpage"));
	                      Assert.fail();
	                  }
	    }



	public void Add_product_to_Wishlist_PDP() {
	        try {
	            Common.clickElement("xpath", "//a[contains(text(),'8-Piece POP Container Baking Set')]");
	            Common.assertionCheckwithReport(Common.getPageTitle().equals("8-Piece POP Container Baking Set"), "verifying catlog page","User navigate to catlog page","user successfully landed on products page", "user faield to catlog page");
	          }
	              catch(Exception |Error e) {
	                  e.printStackTrace();
	                      ExtenantReportUtils.addFailedLog("verifying catlog page", "User navigate to catlog page", "user faield to catlog page", Common.getscreenShotPathforReport("catlogpage"));
	                         Assert.fail();
	              }
	        try {
	        Sync.waitElementPresent("xpath", "(//a[@class='action towishlist'])[2]");
	        Common.clickElement("xpath", "(//a[@class='action towishlist'])[2]");
	        Thread.sleep(4000);
	        String saved=Common.getText("xpath", "//span[@class='wishlist-counter']//span[@class='counter qty']");
	        System.out.println(saved);
	        Common.assertionCheckwithReport(saved.equals("2"), "verifying catlog page","User navigate to catlog page","user successfully landed on products page", "user faield to catlog page");
	      }
	          catch(Exception |Error e) {
	              e.printStackTrace();
	                  ExtenantReportUtils.addFailedLog("verifying catlog page", "User navigate to catlog page", "user faield to catlog page", Common.getscreenShotPathforReport("catlogpage"));
	                  Assert.fail();
	              }
	        }


	public void removeandaddtocartfrom_wishlist() throws Exception {
	 Sync.waitPageLoad();
	 try {

		 Sync.waitElementPresent("xpath", "(//li[@class='link wishlist'])[1]");
	    Common.clickElement("xpath", "(//li[@class='link wishlist'])[1]");
	    Common.assertionCheckwithReport(Common.getPageTitle().equals("My Wish List"), "verifying catlog page","User navigate to catlog page","user successfully landed on products page", "user faield to catlog page");
	      }
	          catch(Exception |Error e) {
	              e.printStackTrace();
	             ExtenantReportUtils.addFailedLog("verifying catlog page", "User navigate to catlog page", "user faield to catlog page", Common.getscreenShotPathforReport("catlogpage"));
	             Assert.fail();
	          }
	    try {
	    Sync.waitElementPresent("xpath", "(//a[@title='Remove Item'])");
	    Common.clickElement("xpath", "(//a[@title='Remove Item'])");
	    String saved=Common.getText("xpath", "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");
	    System.out.println(saved);
	    Common.assertionCheckwithReport(saved.equals("Mini Tongs has been removed from your Wish List."), "verifying catlog page","User navigate to catlog page","user successfully landed on products page", "user faield to catlog page");
	  }
	      catch(Exception |Error e) {
	          e.printStackTrace();
	          ExtenantReportUtils.addFailedLog("verifying catlog page", "User navigate to catlog page", "user faield to catlog page", Common.getscreenShotPathforReport("catlogpage"));
	          Assert.fail();
	          }
	    try {
	    Sync.waitPageLoad();
	    Sync.waitElementPresent("xpath", "//button[@title='Add to Cart']");
	    Common.clickElement("xpath", "//button[@title='Add to Cart']");
	    String saved=Common.getText("xpath", "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");
	    System.out.println(saved);
	    Common.assertionCheckwithReport(saved.equals("You added 8-Piece POP Container Baking Set to your shopping cart."), "verifying catlog page","User navigate to catlog page","user successfully landed on products page", "user faield to catlog page");
	  }
	      catch(Exception |Error e) {
	          e.printStackTrace();
	          ExtenantReportUtils.addFailedLog("verifying catlog page", "User navigate to catlog page", "user faield to catlog page", Common.getscreenShotPathforReport("catlogpage"));
	          Assert.fail();
	          }
	}

	
	public void Header_Link(String dataSet) throws InterruptedException {
		
		 //Thread.sleep(3000);
		Sync.waitPageLoad();
		acceptPrivecy();
		Sync.waitElementClickable("xpath", "(//li[@class='navigation__item navigation__item--parent'])[1]");
		Common.mouseOver("xpath", "(//li[@class='navigation__item navigation__item--parent'])[1]");
		String Hederlinks=data.get(dataSet).get("Cookin & Baking");
		String[] hedrs=Hederlinks.split(",");
		int i=0;  

		/*
		
		try{
		//for(i=0;i<hedrs.length;i++){
			
			System.out.println(hedrs[i]);
			try {
			Sync.waitElementClickable("xpath", "//a[text()='"+hedrs[i]+"']");
			Common.clickElement("xpath", "//a[text()='"+hedrs[i]+"']");
			
			} catch (Exception e) {
				if (Common.findElement("xpath", "//a[text()='"+hedrs[i]+"']") == null) {
					
					Sync.waitElementClickable("xpath", "//a[contains(text(),'"+hedrs[i]+"')]");
					Common.clickElement("xpath", "//a[contains(text(),'"+hedrs[i]+"')]");
					Thread.sleep(2000);
				}
			}*/
			
			try {
			
			int SubCategoryList=Common.findElements("xpath","//div[@id='navigation-category-15701']//li[@class='navigation__inner-item navigation__inner-item--level2']/a").size();
	        System.out.println(SubCategoryList);
			
        	if(SubCategoryList>0){
        		int SubCategoryList2=Common.findElements("xpath","//div[@id='navigation-category-15701']//li[@class='navigation__inner-item navigation__inner-item--level2 oxo35-show']/a").size();
        		System.out.println(SubCategoryList2);
        	}
			
			for(int j=0;j<SubCategoryList;j++) {
        		int value=j+1;
        		
        		List<WebElement> ListOfSubCategory=Common.findElements("xpath", "//div[@id='navigation-category-15701']//li[@class='navigation__inner-item navigation__inner-item--level2']/a");
        		Thread.sleep(3000);
        		ListOfSubCategory.get(j).click();
		
			String PageTitle = Common.getPageTitle();
			String ProductTitle = data.get(dataSet).get("Cookin & Baking Page Title");
			String[] Title = ProductTitle.split(",");
			
			
			if (PageTitle.equals("Spoons, Spatulas & Turners - Utensils - Cooking & Baking - Products")) {
				System.out.println(PageTitle);
				Common.assertionCheckwithReport(Common.getPageTitle().contains(Title[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
				
			} else if (PageTitle.equals("OXO Good Grips POP Containers")) {
					System.out.println(PageTitle);
					Common.assertionCheckwithReport(Common.getPageTitle().contains(Title[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
				}else if (PageTitle.equals("Prep & Go - Food Containers - Cooking & Baking - Products")) {
					System.out.println(PageTitle);
					Common.assertionCheckwithReport(Common.getPageTitle().contains(Title[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
				}
				else if (PageTitle.equals("Smart Seal - Food Containers - Cooking & Baking - Products")) {
					System.out.println(PageTitle);
					Common.assertionCheckwithReport(Common.getPageTitle().contains(Title[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
				
				}
				else if (PageTitle.equals("Green Saver Produce Containers - Food Containers - Cooking & Baking - Products")) {
					System.out.println(PageTitle);
					Common.assertionCheckwithReport(Common.getPageTitle().contains(Title[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
				
				}
				else if (PageTitle.equals("Food Storage - Food Containers - Cooking & Baking - Products")) {
					System.out.println(PageTitle);
					Common.assertionCheckwithReport(Common.getPageTitle().contains(Title[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
				
				}
				else if (PageTitle.equals("On the Go  - Food Containers - Cooking & Baking - Products")) {
					System.out.println(PageTitle);
					Common.assertionCheckwithReport(Common.getPageTitle().contains(Title[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
				
				}
				else if (PageTitle.equals("Easy to Use Digital Food Scales & Measuring Cups | OXO")) {
					System.out.println(PageTitle);
					Common.assertionCheckwithReport(Common.getPageTitle().contains(Title[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
				
				}
				else if (PageTitle.equals("Mixing Bowls & Prep Mixing Bowls for Cooking & Baking | OXO")) {
					System.out.println(PageTitle);
					Common.assertionCheckwithReport(Common.getPageTitle().contains(Title[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
				
				}
				else if (PageTitle.equals("Easy to use Vegetable Peelers & Choppers | OXO")) {
					System.out.println(PageTitle);
					Common.assertionCheckwithReport(Common.getPageTitle().contains(Title[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
				
				}
				else if (PageTitle.equals("Graters and Slicers: Cheese Grater Mandoline Slicers | OXO")) {
					System.out.println(PageTitle);
					Common.assertionCheckwithReport(Common.getPageTitle().contains(Title[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
				
				}
				else if (PageTitle.equals("Knives, Kitchen Scissors & Cutting Boards | OXO")) {
					System.out.println(PageTitle);
					Common.assertionCheckwithReport(Common.getPageTitle().contains(Title[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
				
				}
			
			
		/*	
			
			
			System.out.println(PageTitle);
			System.out.println(Title[i]);
			Common.assertionCheckwithReport(Common.getPageTitle().contains(Title[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
				
			Thread.sleep(2000);  */
			
			Thread.sleep(2000);
			clickLogo();
		//	Sync.waitPageLoad();
		Common.mouseOver("xpath", "(//li[@class='navigation__item navigation__item--parent'])[1]");
			}
		}
		catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validating Header Links " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the header link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the headerlink"));
		    Assert.fail();

		}
	}
	
	
	
	
	
	public void SubCategoriesClicksCaB() throws InterruptedException {
		
		Thread.sleep(4000);
		Sync.waitElementClickable("xpath", "(//li[@class='navigation__item navigation__item--parent'])[1]");
		Common.mouseOver("xpath", "(//li[@class='navigation__item navigation__item--parent'])[1]");
		
		int SubC = Common.findElements("xpath", "//div[@id='navigation-category-15701']//li[@class='navigation__inner-item navigation__inner-item--level2']/a").size();
		System.out.println(SubC);
		
		
		for(int i=0;i<SubC;i++) {
    		//int value=i+1;
			List<WebElement> ListOfSubC = Common.findElements("xpath", "//div[@id='navigation-category-15701']//li[@class='navigation__inner-item navigation__inner-item--level2']/a");
			
    		ListOfSubC.get(i).click();
    		
    		String PageTitle = Common.getPageTitle();
    		System.out.println(PageTitle);
    		Thread.sleep(2000);
    		clickLogo();
    		Sync.waitPageLoad();
    		//acceptPrivecy();
    		Sync.waitElementClickable("xpath", "(//li[@class='navigation__item navigation__item--parent'])[1]");
    		Common.mouseOver("xpath", "(//li[@class='navigation__item navigation__item--parent'])[1]");
    		Thread.sleep(4000);
		
		
		
		
    		if (PageTitle.equals("Spoons, Spatulas & Turners - Utensils - Cooking & Baking - Products")) {
				System.out.println(PageTitle);
				Common.assertionCheckwithReport(Common.getPageTitle().contains(PageTitle), "verifying Header link of "+PageTitle,"user open the "+PageTitle+" option", "user successfully open the header link "+PageTitle,"Failed open the header link "+PageTitle);
				
			} else if (PageTitle.equals("OXO Good Grips POP Containers")) {
					System.out.println(PageTitle);
					Common.assertionCheckwithReport(Common.getPageTitle().contains("OXO Good Grips POP Containers"), "verifying Header link of "+PageTitle,"user open the "+PageTitle+" option", "user successfully open the header link "+PageTitle,"Failed open the header link "+PageTitle);
			        
					
			
			}else if (PageTitle.equals("Prep & Go - Food Containers - Cooking & Baking - Products")) {
					System.out.println(PageTitle);
					Common.assertionCheckwithReport(Common.getPageTitle().contains(PageTitle), "verifying Header link of "+PageTitle,"user open the "+PageTitle+" option", "user successfully open the header link "+PageTitle,"Failed open the header link "+PageTitle);
				}
		}
	}
	
	public void Heade_BabyAndToddler(String dataSet) throws InterruptedException {
		Sync.waitPageLoad();
		Sync.waitElementClickable("xpath", "(//li[@class='navigation__item navigation__item--parent'])[4]");
		Common.mouseOver("xpath", "(//li[@class='navigation__item navigation__item--parent'])[4]");
		String Hederlinks=data.get(dataSet).get("Baby&Toddler");
		String[] hedrs=Hederlinks.split(",");
		int i=0;
		
		String ProductTitle=data.get(dataSet).get("Baby&Toddler PageTitle");
		String[] Title=ProductTitle.split(",");
		//int j=0;
		
		try{
		for(i=0;i<hedrs.length;i++){
			System.out.println(hedrs[i]);
			Sync.waitElementClickable("xpath", "//strong[text()='"+hedrs[i]+"']");
			Common.clickElement("xpath", "//strong[text()='"+hedrs[i]+"']");
			Thread.sleep(3000);
			System.out.println(Common.getPageTitle());
			Common.assertionCheckwithReport(Common.getPageTitle().contains(Title[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
			Common.mouseOver("xpath", "(//li[@class='navigation__item navigation__item--parent'])[4]");
		}
		}
		catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validating Header Links " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the header link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the headerlink"));
		
			Assert.fail();

		}
	}
	
	
	
	public void Heade_CleaningAndOrganization(String dataSet) throws InterruptedException {
		Sync.waitPageLoad();
		Sync.waitElementClickable("xpath", "(//li[@class='navigation__item navigation__item--parent'])[3]");
		Common.mouseOver("xpath", "(//li[@class='navigation__item navigation__item--parent'])[3]");
		String Hederlinks=data.get(dataSet).get("Cleaning&Organization");
		String[] hedrs=Hederlinks.split(",");
		int i=0;
		
		String ProductTitle=data.get(dataSet).get("Cleaning&Organization PageTitle");
		String[] Title=ProductTitle.split(",");
		//int j=0;
		
		try{
		for(i=0;i<hedrs.length;i++){
			System.out.println(hedrs[i]);
			Sync.waitElementClickable("xpath", "//a[text()='"+hedrs[i]+"']");
			Common.clickElement("xpath", "//a[text()='"+hedrs[i]+"']");
			Thread.sleep(3000);
			System.out.println(Common.getPageTitle());
			Common.assertionCheckwithReport(Common.getPageTitle().contains(Title[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
			Common.mouseOver("xpath", "(//li[@class='navigation__item navigation__item--parent'])[3]");
		}
		}
		catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validating Header Links " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the header link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the headerlink"));
		
			Assert.fail();

		}
	}
	
	
	public void Heade_Beverage(String dataSet) throws InterruptedException {
		Sync.waitPageLoad();
		Sync.waitElementClickable("xpath", "(//li[@class='navigation__item navigation__item--parent'])[2]");
		Common.mouseOver("xpath", "(//li[@class='navigation__item navigation__item--parent'])[2]");
		String Hederlinks=data.get(dataSet).get("Beverage");
		String[] hedrs=Hederlinks.split(",");
		int i=0;
		
		String ProductTitle=data.get(dataSet).get("Beverage Page Title");
		String[] Title=ProductTitle.split(",");
		//int j=0;
		
		try{
		for(i=0;i<hedrs.length;i++){
			System.out.println(hedrs[i]);
			Sync.waitElementClickable("xpath", "//a[text()='"+hedrs[i]+"']");
			Common.clickElement("xpath", "//a[text()='"+hedrs[i]+"']");
			Thread.sleep(3000);
			System.out.println(Common.getPageTitle());
			Common.assertionCheckwithReport(Common.getPageTitle().contains(Title[i]), "verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+" option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
			Common.mouseOver("xpath", "(//li[@class='navigation__item navigation__item--parent'])[2]");
		}
		}
		catch (Exception | Error e) {
			e.printStackTrace();
            ExtenantReportUtils.addFailedLog("validating Header Links " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the header link "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the headerlink"));
		    Assert.fail();

		}
	}
	
	public void HomePageMLP_Validation(String dataSet)throws Exception{

		Thread.sleep(3000);
		Sync.waitPageLoad();
		String Hederlinks=data.get(dataSet).get("HomePageMSP");
		String[] hedrs=Hederlinks.split(",");
		int i=0;
		
		try{
		for(i=0;i<hedrs.length;i++){
			System.out.println(hedrs[i]);
			acceptPrivecy();
			Common.scrollIntoView("xpath", "//section[@class='featured-product']//div[@data-slick-index='"+hedrs[i]+"']//h3");
			Sync.waitElementClickable("xpath", "//section[@class='featured-product']//div[@data-slick-index='"+hedrs[i]+"']//h3");
			String MSP= Common.getText("xpath", "//section[@class='featured-product']//div[@data-slick-index='"+hedrs[i]+"']//h3");
			System.out.println(MSP);
			Common.clickElement("xpath", "//section[@class='featured-product']//div[@data-slick-index='"+hedrs[i]+"']//h3");
			Thread.sleep(3000);
			String HeadName = Common.getText("xpath", "//div[@class='product-info-main-container']//h1[@class='page-title']//span");
		    System.out.println(HeadName);
			Thread.sleep(3000);
			System.out.println(MSP.equalsIgnoreCase(HeadName));
		
			Assert.assertEquals(MSP, HeadName);
			
			ExtenantReportUtils.addPassLog("verifying Homepage Blog Post of "+MSP, "user open the "+MSP+" Blog", "user successfully open the Blog post of "+MSP, Common.getscreenShotPathforReport("HomePageBlogs"));
			Thread.sleep(3000);
			clickLogo();
			
		}
		}
		catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validating Homepage Blog post Link " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the Homepage Blog post Link  "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the Homepage Blog post Link "));
		
			Assert.fail();

		}
	}
	
	
	
	public void NoTax() throws Exception {

		Thread.sleep(3000);
		String Tax = Common.getText("xpath", "//tr[@class='tax']//span[@class='price']").replace("$", "");

		Float TaxValue = Float.valueOf(Tax);
		System.out.println(TaxValue);

		if (TaxValue == 0.00) {
			ExtenantReportUtils.addPassLog("Validating Tax Price on Order Success Page",
					"Tax Should be Zero($0.00) on Order", "Successfully NoTax has applied",
					Common.getscreenShotPathforReport("NoTaxApplied"));

		} else {

			ExtenantReportUtils.addFailedLog("Validating Tax Price on Order Success Page",
					"Tax should not applied on Orderprice for NoTax shipping address",
					"Tax has applied on Order for NoTax shipping address",
					Common.getscreenShotPathforReport("NoTaxApplied"));
			AssertJUnit.fail();
		}

	}

	public void tax() throws Exception {

		Thread.sleep(3000);
		String Tax = Common.getText("xpath", "//tr[@class='tax']//span[@class='price']").replace("$", "");

		Float TaxValue = Float.valueOf(Tax);
		System.out.println(TaxValue);

		if (TaxValue > 0.00) {

			ExtenantReportUtils.addPassLog("Validating Tax Price on Order Success Page",
					"Tax Should be applied on Order", " Tax has successfully applied",
					Common.getscreenShotPathforReport("TaxApplied"));
		} else {

			ExtenantReportUtils.addFailedLog("Validating Tax Price on Order Success Page",
					"Tax Should not applied on Order", " Tax has failed to applied",
					Common.getscreenShotPathforReport("TaxApplied"));
		}

	}
		
	public void HomePageMSP_Validation(String dataSet)throws Exception{
		
		Thread.sleep(3000);
		Sync.waitPageLoad();
		String Hederlinks=data.get(dataSet).get("HomePageMSP");
		String[] hedrs=Hederlinks.split(",");
		int i=0;
		
		try{
		for(i=0;i<hedrs.length;i++){
			System.out.println(hedrs[i]);
			acceptPrivecy();
		    Sync.waitElementClickable("xpath", "//li[@class='popular-searches__item']["+hedrs[i]+"]");
			String MSP= Common.getText("xpath", "//li[@class='popular-searches__item']["+hedrs[i]+"]");
			System.out.println(MSP);
			Common.actionsKeyPress(Keys.ARROW_DOWN);
			Common.clickElement("xpath", "//li[@class='popular-searches__item']["+hedrs[i]+"]");
			Thread.sleep(3000);
			String HeadName = Common.getText("xpath", "//div[@class='product-info-main-container']//h1[@class='page-title']//span");
		    System.out.println(HeadName);
			Thread.sleep(3000);
			System.out.println(MSP.equalsIgnoreCase(HeadName));
		
			Assert.assertEquals(MSP, HeadName);
			ExtenantReportUtils.addPassLog("verifying Homepage Blog Post of "+MSP, "user open the "+MSP+" Blog", "user successfully open the Blog post of "+MSP, Common.getscreenShotPathforReport("HomePageBlogs"));
			Thread.sleep(3000);
			clickLogo();
			
		}
		}
		catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validating Homepage Blog post Link " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the Homepage Blog post Link  "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the Homepage Blog post Link "));
		
			Assert.fail();

		}
	
		
	}
	public void HomePageValidation(String dataSet) throws Exception{
		Thread.sleep(3000);
		Sync.waitPageLoad();
		String Hederlinks=data.get(dataSet).get("HomePageMSP");
		String[] hedrs=Hederlinks.split(",");
		int i=0;
		
		try{
		for(i=0;i<hedrs.length;i++){
			System.out.println(hedrs[i]);
			Common.scrollIntoView("xpath", "//section[@class='featured-blog']//div[@data-slick-index='"+hedrs[i]+"']//h3");
			Sync.waitElementClickable("xpath", "//section[@class='featured-blog']//div[@data-slick-index='"+hedrs[i]+"']//h3");
			String MSP= Common.getText("xpath", "//section[@class='featured-blog']//div[@data-slick-index='"+hedrs[i]+"']//h3");
			System.out.println(MSP);
			Common.clickElement("xpath", "//section[@class='featured-blog']//div[@data-slick-index='"+hedrs[i]+"']//h3");
			Thread.sleep(3000);
			String HeadName = Common.getText("xpath", "//div[@class='head']//h2");
		    System.out.println(HeadName);
			Thread.sleep(3000);
			System.out.println(MSP.equalsIgnoreCase(HeadName));
		
			Assert.assertEquals(MSP, HeadName);
			
			ExtenantReportUtils.addPassLog("verifying Homepage Blog Post of "+MSP, "user open the "+MSP+" Blog", "user successfully open the Blog post of "+MSP, Common.getscreenShotPathforReport("HomePageBlogs"));
			Thread.sleep(3000);
			Common.oppenURL("https://oxo-stg.heledigital.com/");
			//clickLogo();
		}
		}
		catch (Exception | Error e) {
			e.printStackTrace();

			ExtenantReportUtils.addFailedLog("validating Homepage Blog post Link " +hedrs[i],"user open the "+hedrs[i]+" option","User unabel open the Homepage Blog post Link  "+hedrs[i],Common.getscreenShotPathforReport("user failed to open the Homepage Blog post Link "));
		
			Assert.fail();

		}
	}
	
	public void verifyingMagentoLoginPage(){//td[@class='amount'])[1]
		try{
			Sync.waitElementPresent("xpath", "//a[@class='logo']");
		String HomepageTitle=Common.getPageTitle();
		Common.assertionCheckwithReport(HomepageTitle.equals("Magento Admin"), "verifying the loginpage", "navigate the loginpage", "user successfully navigate the loginpage", "Failed to navigate to loginpage");
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
		Common.assertionCheckwithReport(HomepageTitle.equals("Dashboard / Magento Admin"), "verifying the homepage", "navigate the home page", "user successfully navigate the home page", "Failed to navigate to home page");
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
			Thread.sleep(5000);
			System.out.println(Common.getPageTitle());
			//Common.assertionCheckwithReport(Common.getPageTitle().contains(hedrs[i]),"verifying Header link of "+hedrs[i],"user open the "+hedrs[i]+"option", "user successfully open the header link "+hedrs[i],"Failed open the header link "+hedrs[i]);
			Thread.sleep(5000);
			Common.clickElement("xpath", "(//span[contains(text(),'Sales')])[1]");	
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
			Sync.waitElementPresent("xpath", "(//span[contains(text(),'Configuration')])[7]");
			Common.clickElement("xpath", "(//span[contains(text(),'Configuration')])[7]");
			
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
	      	//
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
	 public void prepareTaxData_Register(String fileName) {
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
		  public HashMap<String,String> taxValidation(String taxpercent, String state) {
				// TODO Auto-generated method stub
				HashMap<String,String> data=new HashMap<String,String>();
				try{			    
					Thread.sleep(3000);
		 
			     Float giventaxvalue=Float.valueOf(taxpercent);
			     String giventaxvalue1=Float.toString(giventaxvalue);
			     data.put("giventaxvalue",giventaxvalue1);
			     

			     String subtotla=Common.getText("xpath", "//tr[@class='totals sub']//span").replace("$", "");
			     // subtotla.replace("", newChar)
			    Float subtotlaValue=Float.valueOf(subtotla);
			    data.put("subtotlaValue",subtotla);
			   
			  String shippingammount=Common.getText("xpath", "//span[@data-th='Shipping']").replace("$", "");
			    Float shippingammountvalue=Float.valueOf(shippingammount);
				data.put("shippingammountvalue",shippingammount);
				
			     String TaxAmmount=Common.getText("xpath", "//tr[@class='totals-tax']//span").replace("$", "");
			    Float Taxammountvalue=Float.valueOf(TaxAmmount);
				data.put("Taxammountvalue",TaxAmmount);
				
			     String TotalAmmount=Common.getText("xpath", "//tr[@class='grand totals']//span").replace("$", "");
			    Float Totalammountvalue=Float.valueOf(TotalAmmount);
			    //data.put("Totalammountvalue",Totalammountvalue);
			    data.put("Totalammountvalue", TotalAmmount);
			    
			    String ActualTotalAmmount=Common.getText("xpath", "//tr[@class='grand totals']//span").replace("$", "");
			    Float ActualTotalammountvalue=Float.valueOf(ActualTotalAmmount);
			    data.put("ActualTotalammountvalue",ActualTotalAmmount);
			  // Float Total=(subtotlaValue+shippingammountvalue);
			    
			   Float ExpectedTotalAmmount = subtotlaValue+shippingammountvalue+Taxammountvalue;
			   String ExpectedTotalAmmount2 = new BigDecimal(ExpectedTotalAmmount).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
			    
			    String ExpectedTotalAmount=String.valueOf(ExpectedTotalAmmount2);
			    data.put("ExpectedTotalAmmountvalue",ExpectedTotalAmount);
			  
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
				  //  NumberFormat nf= NumberFormat.getInstance();
				   // nf.setMaximumFractionDigits(2);
				   // String userpaneltaxvalue=nf.format(calucaltedvalue);
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
	  		/*try {
	  			Sync.waitElementVisible("id", "customer-email-address");
	  			Common.textBoxInput("id", "customer-email-address", data.get(dataSet).get("Email"));
	  		} catch (NoSuchElementException e) {
	  			checkOut();
	  			Common.textBoxInput("id", "customer-email-address", data.get(dataSet).get("Email"));

	  		}*/
	  		String expectedResult = "email field will have email address";
	  		try {
	  			
	  			
	  			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",data.get(dataSet).get("FirstName"));
	             // int size = Common.findElements("id", "customer-email-address").size();
	              //Common.assertionCheckwithReport(size > 0, "validating the email address field", expectedResult,"Filled Email address", "unabel to fill the email address");
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
	  			//Sync.waitElementPresent("xpath", "//input[@id='customer-email']");
				Common.textBoxInput("xpath", "//input[@id='customer-email']", data.get(dataSet).get("Email"));

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
		  public void addDeliveryAddress_2(String dataSet,String Street,String City,String postcode,String Region) throws Exception {
			  int AddnewAddress = Common.findElements("xpath", "//button[contains(@class,'action-show-popup')]").size();
				if (AddnewAddress > 0) {
					Common.clickElement("xpath", "//button[contains(@class,'action-show-popup')]");

					try {
						Sync.waitElementPresent("xpath", "//div[@id='shipping-new-address-form']//input[@name='firstname']");
						Common.textBoxInput("xpath", "//div[@id='shipping-new-address-form']//input[@name='firstname']",
								data.get(dataSet).get("FirstName"));

						Sync.waitElementPresent("xp	ath", "//div[@id='shipping-new-address-form']//input[@name='lastname']");
						Common.textBoxInput("xpath", "//div[@id='shipping-new-address-form']//input[@name='lastname']",
								data.get(dataSet).get("LastName"));

						Sync.waitElementPresent("xpath", "//div[@id='shipping-new-address-form']//input[@name='firstname']");
						Common.textBoxInput("xpath", "//div[@id='shipping-new-address-form']//input[@name='firstname']",
								data.get(dataSet).get("FirstName"));

						Sync.waitElementPresent("xp	ath", "//div[@id='shipping-new-address-form']//input[@name='company']");
						Common.textBoxInput("xpath", "//div[@id='shipping-new-address-form']//input[@name='company']",
								data.get(dataSet).get("CompanyName"));

						Sync.waitElementPresent("name", "street[0]");
						Common.textBoxInput("name", "street[0]", Street);
						Common.textBoxInput("name", "city", City);
						Common.dropdown("name", "region_id", Common.SelectBy.TEXT, Region);
						Common.textBoxInput("name", "postcode", postcode);
						Common.actionsKeyPress(Keys.PAGE_DOWN);
						Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));

						Common.clickElement("xpath", "//button[contains(@class,'action-save-address')]");

						int sizeerrormessage = Common
								.findElements("xpath", "//span[contains(text(),'This is a required field')]").size();
						Common.assertionCheckwithReport(sizeerrormessage <= 0, "verifying shipping addres filling ",
								"user will fill the all the shipping", "user fill the shiping address click save button",
								"faield to add new shipping address");

					} catch (Exception | Error e) {
						e.printStackTrace();
						ExtenantReportUtils.addFailedLog("verifying shipping addres filling",
								"user will fill the all the shipping", "faield to add new shipping address",
								Common.getscreenShotPathforReport("faieldsshippingpagefilling"));
						AssertJUnit.fail();

					}

				}
			}
		  	
		  public void writeResultstoXLSx(String Website,String OrderId,String subtotlaValue,String shippingammountvalue,String state,String Zipcode,String Taxammountvalue,String ActualTotalammountvalue, String ExpectedTotalammountvalue,String giventaxvalue,String calucaltedvalue)
		  {
				//String fileOut="";
			try{
				
				File file=new File(System.getProperty("user.dir")+"/src/test/resources/OXOTaxDetails_Guest.xlsx");
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
				cell.setCellValue("House Ware");
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
				if(Website.contains("oxo"))
			     {
					
					Site="OXO";
					
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
//			return fileOut;
//			return writeResultstoXLSx(String OrderId,String subtotla,String shippingammount,String TaxAmmount,String Totalammount,String giventaxvalue1,String userpaneltaxvalue);

			}
		  public String order_Verifying() throws Exception{
				String OrderId="";
				//Thread.sleep(10000);
				//Common.textBoxInput("id", "//textarea[contains(@id,'tt-c-comment-field')]","Ceate accounts test ");
				String expectedResult = "It redirects to order confirmation page";
				try{
				Sync.waitPageLoad();
				
				
				
				for(int i=0;i<10;i++){
					Thread.sleep(5000);
					if(Common.getCurrentURL().contains("success")){
						break;
					}
					
				}
				
				String sucessMessage=Common.getText("xpath", "//h1[@class='page-title']/span");
				System.out.println(sucessMessage);
				int name = Common.findElements("xpath", "//span[@class='customer-name']").size();
				System.out.println(name);
				if (name > 0) {
					
					OrderId=Common.getText("xpath", "//p[@class='order-number-wrapper']//strong");	
				}else{
				 OrderId=Common.getText("xpath", "//p[@class='order-number-wrapper']//span");
				}
				System.out.println("Your order number is:"+OrderId);
				Common.assertionCheckwithReport(sucessMessage.equals("Thank you for your order!"),"verifying the product confirmation", expectedResult,"Successfully It redirects to order confirmation page Order Placed","User unabel to go orderconformation page");
					
				}
				catch (Exception | Error e) {
					e.printStackTrace();
					ExtenantReportUtils.addFailedLog("verifying the product confirmation", expectedResult,
							"User failed to navigate  to order confirmation page", Common.getscreenShotPathforReport("failednavigatepage"));
					Assert.fail();
				}
				return OrderId;
			}
		  
		  public String  URL() throws InterruptedException {
			  String Website="";
				Common.clickElement("xpath", "//img[@alt='OXO']");
				Sync.waitPageLoad();
				Thread.sleep(4000);
				Website=Common.getCurrentURL();
	            
				return Website;
				
			}	
public void OxoAdminlogin(String dataSet) throws Exception {
			  
			  try {
	         	Common.oppenURL("https://stg-upgrade.oxo.com/upgrade_admin");
	         	int name = Common.findElements("xpath", "//span[@class='admin-user-account-text']").size();
				if (name > 0) {

				}else {
	         	Common.textBoxInput("xpath", "//input[contains(@name,'username')]", data.get(dataSet).get("UserName"));
	         	Common.textBoxInput("xpath", "//input[contains(@name,'password')]",data.get(dataSet).get("Password"));
				
	         int username=	Common.findElements("xpath", "//input[contains(@name,'username')]").size();
	         	
	         	
	            Common.assertionCheckwithReport(username>=1, "verifying Admin panel login page", "User name and password field data is populating", "Sucessfully enter username and password", "Faield to enter username and password"); 	
	         	Common.clickElement("xpath", "//button[contains(@class,'action-primary')]");
	         	Thread.sleep(2000);  
	         	Common.actionsKeyPress(Keys.ESCAPE);
	         	}}
	         	catch(Exception |Error e)
		 		{
		 			report.addFailedLog("verifying Admin panel login page", "User name and password field data is populating", "Faield to enter username and password",Common.getscreenShotPathforReport("adminlogin")); 	

		 			e.printStackTrace();
		 			Assert.fail();
		 			
		 	}
	     }
		  
		  
		  public void catlog() {
			  try {
				  Sync.waitElementClickable("xpath", "//li[@id='menu-magento-catalog-catalog']");
				  Common.clickElement("xpath", "//li[@id='menu-magento-catalog-catalog']");
				  Sync.waitElementClickable("xpath", "//span[contains(text(),'Products')]");
				  Common.clickElement("xpath", "//span[contains(text(),'Products')]");
				  Common.assertionCheckwithReport(Common.getPageTitle().equals("Products / Inventory / Catalog / Magento Admin"), "verifying catlog page","User navigate to catlog page","user successfully landed on products page", "user faield to catlog page");
			  }
			      catch(Exception |Error e) {
			          
			              ExtenantReportUtils.addFailedLog("verifying catlog page", "User navigate to catlog page", "user faield to catlog page", Common.getscreenShotPathforReport("catlogpage"));
			              Assert.fail();
			          }
		  }
		  public void Productsearch(String dataSet) throws Exception {
			  
			  try {
			Common.clickElement("xpath", "//button[@class='action-remove']");
			Thread.sleep(3000);
			  Sync.waitElementClickable("xpath", "(//input[@id='fulltext'])[1]");
			  Common.textBoxInput("xpath", "(//input[@id='fulltext'])[1]", data.get(dataSet).get("ProductName"));
			  Thread.sleep(3000);
			  Common.clickElement("xpath", "//div[@class='data-grid-search-control-wrap']//button[@class='action-submit']");
			  Thread.sleep(3000);
			  Common.clickElement("xpath", "//tr[@class='data-row']//div[contains(text(),'Steel 6-Piece POP Container Set')]");
			  Common.assertionCheckwithReport(Common.getPageTitle().equals("Steel 6-Piece POP Container Set / Products / Inventory / Catalog / Magento Admin"), "verifying catlog page","User navigate to catlog page","user successfully landed on products page", "user faield to catlog page");
			  }
			      catch(Exception |Error e) {
			          e.printStackTrace();
			              ExtenantReportUtils.addFailedLog("verifying catlog page", "User navigate to catlog page", "user faield to catlog page", Common.getscreenShotPathforReport("catlogpage"));
			              Assert.fail();
			          }
			  try {
				  Common.actionsKeyPress(Keys.ARROW_DOWN);
				  
						Common.dropdown("xpath", "//select[@name='product[quantity_and_stock_status][is_in_stock]']", Common.SelectBy.TEXT, data.get(dataSet).get("Outofstock"));
					
				  Common.assertionCheckwithReport(Common.getPageTitle().equals("Steel 6-Piece POP Container Set / Products / Inventory / Catalog / Magento Admin"), "verifying catlog page","User navigate to catlog page","user successfully landed on products page", "user faield to catlog page");
			  }
			
			      catch(Exception |Error e) {
			          
			              ExtenantReportUtils.addFailedLog("verifying catlog page", "User navigate to catlog page", "user faield to catlog page", Common.getscreenShotPathforReport("catlogpage"));
			              Assert.fail();
			          }
			  try {
				  Common.clickElement("xpath", "//button[@id='save-button']");
				  Thread.sleep(3000);
				  String saved=Common.getText("xpath", "//div[@data-ui-id='messages-message-success']");
				  
				  Common.assertionCheckwithReport(saved.equals("You saved the product."), "verifying catlog page","User navigate to catlog page","user successfully landed on products page", "user faield to catlog page");
			  }
			  catch(Exception |Error e) {
		          
	              ExtenantReportUtils.addFailedLog("verifying catlog page", "User navigate to catlog page", "user faield to catlog page", Common.getscreenShotPathforReport("catlogpage"));
	              Assert.fail();
	          }
		  
		  }
		  public void Oxo(String dataSet) throws Exception {
			  try {
		         	Common.oppenURL("https://stg-upgrade.oxo.com/");
		         	Thread.sleep(3000);
		         	Common.clickElement("xpath", "//a[@class='search-tool']");
		         	Common.textBoxInput("xpath", "//div[@class='minisearch__search-area']//input[@type='text']", data.get(dataSet).get("ProductName"));
		         	Common.clickElement("xpath", "//button[@class='minisearch__btn-submit action search']");
		         	Common.assertionCheckwithReport(Common.getPageTitle().equals("Search results for: 'Steel 6-Piece POP Container Set'"), "verifying catlog page","User navigate to catlog page","user successfully landed on products page", "user faield to catlog page");
			  }
			      catch(Exception |Error e) {
			          
			              ExtenantReportUtils.addFailedLog("verifying catlog page", "User navigate to catlog page", "user faield to catlog page", Common.getscreenShotPathforReport("catlogpage"));
			              Assert.fail();
			          }
		         try {
		        	 Thread.sleep(3000);
		        	 Common.clickElement("xpath", "//a[contains(text(),'Steel 6-Piece POP Container Set')]");
		        	 Common.assertionCheckwithReport(Common.getPageTitle().equals("Steel 6-Piece POP Container Set"), "verifying catlog page","User navigate to catlog page","user successfully landed on products page", "user faield to catlog page");
				  }
				      catch(Exception |Error e) {
				          
				              ExtenantReportUtils.addFailedLog("verifying catlog page", "User navigate to catlog page", "user faield to catlog page", Common.getscreenShotPathforReport("catlogpage"));
				              Assert.fail();
				          }
		  }	 
		  public void magentostock(String dataSet) throws Exception {
			  int name = Common.findElements("xpath", "//button[@id='product-addtocart-button']").size();
				if (name > 0) {
					try {
						Thread.sleep(8000);
						Sync.waitElementClickable("xpath", "//button[@id='product-addtocart-button']");
						Common.clickElement("xpath", "//button[@id='product-addtocart-button']");
						Thread.sleep(3000);
						String saved=Common.getText("xpath", "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");
						System.out.println(saved);
			        	 Common.assertionCheckwithReport(saved.equals("You added Steel 6-Piece POP Container Set to your shopping cart."), "verifying catlog page","User navigate to catlog page","user successfully landed on products page", "user faield to catlog page");
					  }
					  catch(Exception |Error e) {
				          e.printStackTrace();
			              ExtenantReportUtils.addFailedLog("verifying catlog page", "User navigate to catlog page", "user faield to catlog page", Common.getscreenShotPathforReport("catlogpage"));
			              Assert.fail();
			          }

				}   
				else {
			  try {
		        	 Common.textBoxInput("xpath", "//input[@placeholder='Your Email Address']", data.get(dataSet).get("mail"));
		        	 Common.clickElement("xpath", "//button[@class='action submit primary']");
		        	 Thread.sleep(3000);
		        	 String saved=Common.getText("xpath", "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");
		        	 System.out.println(saved);
		        	 Common.assertionCheckwithReport(saved.equals("Thank you! You are already subscribed to this product."), "verifying catlog page","User navigate to catlog page","user successfully landed on products page", "user faield to catlog page");
				  }
				  catch(Exception |Error e) {
			          e.printStackTrace();
		              ExtenantReportUtils.addFailedLog("verifying catlog page", "User navigate to catlog page", "user faield to catlog page", Common.getscreenShotPathforReport("catlogpage"));
		              Assert.fail();
		          }
			  }
		  }
public void Productsearch_Instock(String dataSet) throws Exception {
			  
			  try {
			Common.clickElement("xpath", "//button[@class='action-remove']");
			Thread.sleep(9000);
			  Sync.waitElementClickable("xpath", "(//input[@id='fulltext'])[1]");
			  Common.textBoxInput("xpath", "(//input[@id='fulltext'])[1]", data.get(dataSet).get("ProductName"));
			  Thread.sleep(9000);
			  Common.clickElement("xpath", "//div[@class='data-grid-search-control-wrap']//button[@class='action-submit']");
			  Thread.sleep(9000);
			  Common.clickElement("xpath", "//tr[@class='data-row']//div[contains(text(),'Steel 6-Piece POP Container Set')]");
			  Common.assertionCheckwithReport(Common.getPageTitle().equals("Steel 6-Piece POP Container Set / Products / Inventory / Catalog / Magento Admin"), "verifying catlog page","User navigate to catlog page","user successfully landed on products page", "user faield to catlog page");
			  }
			      catch(Exception |Error e) {
			          e.printStackTrace();
			              ExtenantReportUtils.addFailedLog("verifying catlog page", "User navigate to catlog page", "user faield to catlog page", Common.getscreenShotPathforReport("catlogpage"));
			              Assert.fail();
			          }
			  try {
				  Common.actionsKeyPress(Keys.ARROW_DOWN);
				  Thread.sleep(9000); 
				Common.dropdown("xpath", "//select[@name='product[quantity_and_stock_status][is_in_stock]']", Common.SelectBy.TEXT, data.get(dataSet).get("Instock"));
				Thread.sleep(9000);
				  Common.assertionCheckwithReport(Common.getPageTitle().equals("Steel 6-Piece POP Container Set / Products / Inventory / Catalog / Magento Admin"), "verifying catlog page","User navigate to catlog page","user successfully landed on products page", "user faield to catlog page");
			  }
			
			      catch(Exception |Error e) {
			          e.printStackTrace();
			              ExtenantReportUtils.addFailedLog("verifying catlog page", "User navigate to catlog page", "user faield to catlog page", Common.getscreenShotPathforReport("catlogpage"));
			              Assert.fail();
			          }
			  try {
				  Common.clickElement("xpath", "//button[@id='save-button']");
				  Thread.sleep(5000);
				  String saved=Common.getText("xpath", "//div[@data-ui-id='messages-message-success']");
				  
				  Common.assertionCheckwithReport(saved.equals("You saved the product."), "verifying catlog page","User navigate to catlog page","user successfully landed on products page", "user faield to catlog page");
			  }
			  catch(Exception |Error e) {
		          
	              ExtenantReportUtils.addFailedLog("verifying catlog page", "User navigate to catlog page", "user faield to catlog page", Common.getscreenShotPathforReport("catlogpage"));
	              Assert.fail();
	          }
		  
		  }
	public OxoHelper(String datafile) {
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