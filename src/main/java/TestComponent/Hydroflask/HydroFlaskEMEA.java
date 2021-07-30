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
import Utilities.ExtenantReportUtils;

public class HydroFlaskEMEA {
	
	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data = new HashMap<>();
	static ExtenantReportUtils report;
public HydroFlaskEMEA(String datafile) {
		
		
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
public void navigateMyAccount() throws InterruptedException {
	Thread.sleep(2000);
	Sync.waitPageLoad();
	String expectedResult = "User should land on the home page";
	int size =Common.findElements("xpath", "//a[@class='logo']").size();
	Common.assertionCheckwithReport(size > 0, " verifying the home page", expectedResult,"Successfully landed on the home page", "User unabel to land on home page");
	Common.assertionCheckwithReport(size>0, "Successfully landed on th home page", expectedResult,"User unabel to land on home page");
	try {
		Sync.waitPageLoad();
		
		Sync.waitElementClickable(30, By.xpath("//a[@class='social-login']"));
		Common.findElement("xpath", "//a[@class='social-login']").click();
		
	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying my account option", "clcik the my account button",
				"User failed to clcik the my account button",
				Common.getscreenShotPathforReport("my account button"));
		Assert.fail();

	}
}

public void loginHydroflaskAccount(String dataSet) throws Exception {
	
	navigateMyAccount();
	String expectedResult = "Opens login pop_up";

	Sync.waitElementClickable(30, By.id("social-login-popup-log-in-email"));
	if (Common.findElement("id", "social-login-popup-log-in-email") == null) {
		Common.clickElement("xpath", "//a[@class='social-login']");
		//Thread.sleep(2000);
	}
	int size = Common.findElements("id", "social-login-popup-log-in-email").size();

	Common.assertionCheckwithReport(size > 0, "verifying  login pop up", expectedResult,
			"Successfully opeans Login pop up page", "Faild to load the Login pop up");


	try {
		Common.textBoxInput("id", "social-login-popup-log-in-email", data.get(dataSet).get("Email"));
		Common.textBoxInput("id", "social-login-popup-log-in-pass", data.get(dataSet).get("Password"));

		expectedResult = "see the fields populated with the data";
		int errormessagetextSize = Common.findElements("xpath", "//div[contains (@id,'error')]").size();
		Common.assertionCheckwithReport(errormessagetextSize <= 0, "verifying login credentials", expectedResult,
				"Successfully Enter in the login data", "Required Field Data Missing");
		
		Common.clickElement("id", "bnt-social-login-authentication");
		Thread.sleep(4000);
		Sync.waitElementPresent("xpath", "//span[@data-ui-id='page-title-wrapper']");
		int Elementsize = Common.findElements("xpath", "//span[@data-ui-id='page-title-wrapper']").size();

		Common.assertionCheckwithReport(Elementsize>0, "verifying login account",
				"customer is redirected to My Account page",
				"Logged in the application and customer is redirected to My Account page",
				"Unabel to login Account");
		

	}

	catch (Exception | Error e) {
		ExtenantReportUtils.addFailedLog("verifying login page with credentials", expectedResult,
				"User failed to login in account  ", Common.getscreenShotPathforReport("login faield"));
		Assert.fail();

	}
}
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
		Common.mouseOver("xpath", "//ul[@class='megamenu-list-ancestor']/li[2]");
	} catch (Exception e) {
		Common.clickElement("xpath", "//ul[@class='megamenu-list-ancestor']/li[2]");
	}
	Thread.sleep(1000);
	Common.clickElement("xpath", "//ul[@class='megamenu-list-ancestor']/li[2]");
	Thread.sleep(4000);
	expectedResult = "User should select the  bottle category";
	int sizebotteles = Common.findElements("xpath", "//ul[@class='megamenu-list-ancestor']/li[2]").size();
	Common.assertionCheckwithReport(sizebotteles > 0,"validating the product category as bottle from navigation menu ", expectedResult,
			"Selected the  bottle category", "User unabel to click bottle");
	// Common.assertionCheckwithReport(sizebotteles>0, "Selected the
	// "+category+" category", expectedResult,"User unabel to
	// click"+category+"");

	try {
		// Common.actionsKeyPress(Keys.PAGE_DOWN);
		// Thread.sleep(2000);
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Common.actionsKeyPress(Keys.PAGE_DOWN);
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
		//Sync.waitElementClickable("xpath", "//button[@title='Add to Cart']");
		Thread.sleep(4000);
		List<WebElement> element = Common.findElements("xpath", "//button[contains(@class,'actions-primary-add-to-cart')]");

		
		element.get(2).click();
        Thread.sleep(5000);

		//String s = Common.getText("xpath", "//a[@aria-label='minicart']/following::span[3]");
		//System.out.println();

		expectedResult = "Product should add to Cart";
		//WebElement elements=element.get(2);
		 
		//int cartbuttonsize = Common.findElements("xpath", "(//button[@title='Add to Cart'])[2]").size();
		ExtenantReportUtils.addPassLog("validating the add product to cart", expectedResult,
				"Added Product to Cart",Common.getscreenShotPathforReport("failed to add product"));
		
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
public void checkOut() throws Exception {
	String expectedResult = "it should land on the checkout intermediate page";

	try {

		Common.clickElement("xpath", "//a[contains(@class,'action showcart')]");
		Thread.sleep(3000);

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
public void addDeliveryAddress_registerUser(String dataSet) throws Exception {

	
	String expectedResult = "shipping address is entering in the fields";
    int size = Common.findElements(By.xpath("//a[contains(@class,'action-show-popup')]/span")).size();
	if (size > 0) {
    	try {
			Common.clickElement("xpath", "//a[contains(@class,'action-show-popup')]/span");
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='firstname']",data.get(dataSet).get("FirstName"));
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='lastname']",	data.get(dataSet).get("LastName"));
			Common.textBoxInput("xpath", "//form[@id='co-shipping-form']//input[@name='street[0]']",data.get(dataSet).get("Street"));
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
			// Common.mouseOverClick("name", "region_id");
			/*try {
				Common.dropdown("xpath", "//form[@id='co-shipping-form']//select[@name='region_id']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			} catch (ElementClickInterceptedException e) {
				// TODO: handle exception
				Thread.sleep(3000);
				Common.dropdown("xpath", "//form[@id='co-shipping-form']//select[@name='region_id']", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			}*/
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
			
			/*try {
				Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			} catch (ElementClickInterceptedException e) {
				// TODO: handle exception
				Thread.sleep(3000);
				Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
			}*/
			Thread.sleep(2000);
			Common.textBoxInputClear("name", "postcode");
			Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));
			Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));
			Thread.sleep(5000);
			Sync.waitElementClickable("xpath", "//div[@class='checkout-shipping-methods-tr row']/div/input[@id='label_method_bestway']");
			Common.clickElement("xpath", "//div[@class='checkout-shipping-methods-tr row']/div/input[@id='label_method_bestway']");
            int errorsize=Common.findElements("xpath", "//div[@class='field-error']").size();
			Common.assertionCheckwithReport(errorsize>0, "verifying shipping addres filling ", expectedResult, "user enter the shipping address", "mandatory data");			
			
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

public void addDeliveryAddress(String dataSet) throws Exception {

try {

Sync.waitElementVisible("id", "customer-email-address");

Common.textBoxInput("id", "customer-email-address", data.get(dataSet).get("Email"));

}
catch (NoSuchElementException e) {

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

System.out.println(data.get(dataSet).get("City"));


Common.actionsKeyPress(Keys.PAGE_DOWN);

Thread.sleep(3000);

/*try {

  Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));

} catch (ElementClickInterceptedException e) {

Thread.sleep(3000);

Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));

}*/

Thread.sleep(2000);

Common.textBoxInputClear("name", "postcode");

Common.textBoxInput("name", "postcode", data.get(dataSet).get("postcode"));

Thread.sleep(5000);


Common.textBoxInput("name", "telephone", data.get(dataSet).get("phone"));



}



catch (Exception | Error e) {

e.printStackTrace();

ExtenantReportUtils.addFailedLog("validating shipping address","shipping address is filled in to the fields", "user faield to fill the shipping address",Common.getscreenShotPathforReport("shipingaddressfaield"));

//ExtenantReportUtils.addFailedLog("User click check out button",

//"User unabel click the checkout button",

//Common.getscreenShotPathforReport("check out miniCart"));

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



//Common.assertionCheckwithReport(errorsize<=0,"enter the shipping

//address in to the fields without skipping any mandatory fields",

//expectedResult, "Filled the shipping address", "failed to add a

//address");

//Common.assertionCheckwithReport(errorsize<=0, "Filled the shipping

//address", expectedResult, "Missing the shipping address");

Thread.sleep(3000);

}
public void addPaymentDetails(String dataSet) throws Exception {
	

	Thread.sleep(4000);
	String expectedResult = "land on the payment section";
	//Common.refreshpage();

	try {
		//Sync.waitElementClickable("xpath", "//label[@for='paymetric']");
		Sync.waitElementClickable("xpath", "//label[@for='adyen_cc']");
		int sizes=Common.findElements("xpath", "//label[@for='adyen_cc']").size();

	 Common.assertionCheckwithReport(sizes>0, "Successfully land on the payment section", expectedResult,"User unabel to land opaymentpage");
		Common.clickElement("xpath", "//label[@for='adyen_cc']");
		
		//Common.clickElement("xpath","//label[@for='paymetric']");
		
		Thread.sleep(2000);
		int elementnumber=Common.findElements("xpath", "//iframe[@title='Iframe for secured card data input field']").size();
	System.out.println(elementnumber);	
		
	Common.switchFrames("xpath", "//iframe[@title='Iframe for secured card data input field']");
	
				//Common.dropdown("xpath", "//select[@id='c-ct']", Common.SelectBy.TEXT, data.get(dataSet).get("cardType"));
		
		Common.textBoxInput("xpath", "//span[@data-cse='encryptedCardNumber']", data.get(dataSet).get("cardNumber"));

		Common.switchToDefault();
		Common.switchFrames("xpath", "//span[contains(@class,'adyen-checkout__card__exp-dat')]/iframe");

		Common.textBoxInput("xpath", "//input[@id='encryptedExpiryDate']", data.get(dataSet).get("ExpYearMonth"));
		
		//Common.textBoxInput("xpath", "//input[@id='encryptedExpiryDate']",data.get(dataSet).get("ExpYear"));
		Common.switchToDefault();
		Common.switchFrames("xpath","//span[contains(@class,'adyen-checkout__card__cvc__input')]/iframe");
		Common.textBoxInput("xpath", "//input[@id='encryptedSecurityCode']", data.get(dataSet).get("cvv"));
		Common.switchToDefault();
		Common.textBoxInput("xpath", "//input[contains(@class,'adyen-checkout__card__holderName__input')]",data.get(dataSet).get("UserName"));
		Thread.sleep(2000);

		Common.actionsKeyPress(Keys.ARROW_DOWN);
		
		Thread.sleep(1000);
		Common.clickElement("xpath", "//label[@for='agreement_adyen_cc_1']");
		Common.clickElement("xpath", "//button[@class='action primary checkout']");
		
		

	}

	catch (Exception | Error e) {
		e.printStackTrace();
		

		ExtenantReportUtils.addFailedLog("validating the Credit Card infromation", expectedResult,
				"faield  to fill the Credit Card infromation",
				Common.getscreenShotPathforReport("Cardinfromationfail"));
                Assert.fail();
      }

	
	
	
	}


public void updatePaymentAndSubmitOrder(String dataSet) throws Exception {
	
	addPaymentDetails(dataSet);
	String expectedResult = "It redirects to order confirmation page";

	if (Common.findElements("xpath", "//span[contains(@class,'error-text')]").size() > 0) {
		addPaymentDetails(dataSet);
	}
	
	Thread.sleep(3000);
	int placeordercount = Common.findElements("xpath", "//button[@class='action primary checkout']").size();
	
	if (placeordercount > 1) {
		Common.clickElement("xpath", "//button[@class='action primary checkout']");
	}

	
	
		try{
		String urlName=	Common.getCurrentURL();
    	Common.assertionCheckwithReport(urlName.contains("success"),
			"verifying the product confirmation", expectedResult,
			"Successfully It redirects to order confirmation page Order Placed",
			"User unabel to go orderconformation page");
		}
		catch (Exception | Error e) {
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying the product confirmation", expectedResult,
					"User failed to navigate  to order confirmation page", Common.getscreenShotPathforReport("failednavigatepage"));
			Assert.fail();
		}

}
public void acceptPrivecy() {		
	Common.clickElementStale("id", "truste-consent-required");	}




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
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Hydro Flask (@HydroFlask) / Twitter"), "Verifying Social link  "+socallinksarry[i],"User click the social "+socallinksarry[i], "successfully navigating to social link  "+socallinksarry[i], "Failed to navigate to social link "+socallinksarry[i]);
			Common.closeCurrentWindow();
			Common.switchToFirstTab();
		}
   else if(socallinksarry[i].equals("Instagram")){
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Hydro Flask (@hydroflask) • Instagram photos and videos"), "Verifying Social link  "+socallinksarry[i],"User click the social "+socallinksarry[i], "successfully navigating to social link  "+socallinksarry[i], "Failed to navigate to social link "+socallinksarry[i]);
			Common.closeCurrentWindow();
			Common.switchToFirstTab();
		}
   else	if(socallinksarry[i].equals("Facebook")){
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Facebook"), "Verifying Social link  "+socallinksarry[i],"User click the social "+socallinksarry[i], "successfully navigating to social link  "+socallinksarry[i], "Failed to navigate to social link "+socallinksarry[i]);
			Common.closeCurrentWindow();
			Common.switchToFirstTab();
		}
   else	if(socallinksarry[i].equals("Pinterest")){
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Pinterest"), "Verifying Social link  "+socallinksarry[i],"User click the social "+socallinksarry[i], "successfully navigating to social link  "+socallinksarry[i], "Failed to navigate to social link "+socallinksarry[i]);
			Common.closeCurrentWindow();
			Common.switchToFirstTab();
		}
   else	if(socallinksarry[i].equals("Youtube")){
		Common.assertionCheckwithReport(Common.getCurrentURL().contains("hydroflask"), "Verifying Social link  "+socallinksarry[i],"User click the social "+socallinksarry[i], "successfully navigating to social link  "+socallinksarry[i], "Failed to navigate to social link "+socallinksarry[i]);
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

public void footerLinks_About_Validation(){

String Links= "AboutUs";

 try{

 Common.actionsKeyPress(Keys.END);

 Thread.sleep(3000);

 

 Common.clickElement("xpath","(//a[contains(@href,'our-story')])[2]");

 Sync.waitPageLoad();

 Common.assertionCheckwithReport(Common.getPageTitle().contains(" Hydro Flask"),"Validate the Footer link "+Links, "Click the footer link "+Links+"it will navigate to page"+Links, "successfully navigating to "+Links +"page ","Failed to navigate to"+Links+"page");

 }

 catch (Exception |Error e) {

e.printStackTrace();

   ExtenantReportUtils.addFailedLog("Validate the Footer link "+Links,"Click the footer link "+Links+"it will navigate to page"+Links, "Failed to navigate to"+Links+"page", Common.getscreenShotPathforReport("failed to land on "+Links));

   Assert.fail();

 

 }

 }



 public void footerLinks_careers_Validation(){

String Links= "Careers";

 try{

 

// Sync.waitElementInvisible("xpath", "//a[text()='Personalize']");

 Common.actionsKeyPress(Keys.END);

 Thread.sleep(3000);

 Common.clickElement("xpath","//a[contains(@href,'myworkdayjobs')]");

 Sync.waitPageLoad();
 
 Common.assertionCheckwithReport(Common.getPageTitle().equals("Search for Jobs"),"Validate the Footer link "+Links, "Click the footer link "+Links+"it will navigate to page"+Links, "successfully navigating to "+Links +"page ","Failed to navigate to"+Links+"page");

 }

 catch (Exception |Error e) {

e.printStackTrace();

   ExtenantReportUtils.addFailedLog("Validate the Footer link "+Links,"Click the footer link "+Links+"it will navigate to page"+Links, "Failed to navigate to"+Links+"page", Common.getscreenShotPathforReport("failed to land on "+Links));

   Assert.fail();

 

 }

 }

 public void footerLinks_Press_Validation(){

String Links= "press";

 try{

 Common.actionsKeyPress(Keys.END);

 Thread.sleep(3000);

 Common.clickElement("xpath","(//a[contains(@href,'press')])[2]");

 Sync.waitPageLoad();

 Common.assertionCheckwithReport(Common.getCurrentURL().contains("press"),"Validate the Footer link "+Links, "Click the footer link "+Links+"it will navigate to page"+Links, "successfully navigating to "+Links +"page ","Failed to navigate to"+Links+"page");

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

 Common.clickElement("xpath","(//a[contains(@href,'frequently-asked-questions')])[1]");

 Sync.waitPageLoad();

 Common.assertionCheckwithReport(Common.getCurrentURL().contains("frequently-asked-questions"),"Validate the Footer link "+Links, "Click the footer link "+Links+"it will navigate to page"+Links, "successfully navigating to "+Links +"page ","Failed to navigate to"+Links+"page");

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

 Common.clickElement("xpath","(//a[contains(@href,'contact')])[2]");

 Sync.waitPageLoad();

 Common.assertionCheckwithReport(Common.getCurrentURL().contains("contact"),"Validate the Footer link "+Links, "Click the footer link "+Links+"it will navigate to page"+Links, "successfully navigating to "+Links +"page ","Failed to navigate to"+Links+"page");

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

 Common.clickElement("xpath","//a[contains(@href,'shipping')]");

 Sync.waitPageLoad();

 Common.assertionCheckwithReport(Common.getCurrentURL().contains("frequently-asked-questions"),"Validate the Footer link "+Links, "Click the footer link "+Links+"it will navigate to page"+Links, "successfully navigating to "+Links +"page ","Failed to navigate to"+Links+"page");

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

 Common.clickElement("xpath","//a[contains(@href,'returns')]");

 Sync.waitPageLoad();

 Common.assertionCheckwithReport(Common.getCurrentURL().contains("frequently-asked-questions"),"Validate the Footer link "+Links, "Click the footer link "+Links+"it will navigate to page"+Links, "successfully navigating to "+Links +"page ","Failed to navigate to"+Links+"page");

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

 Common.clickElement("xpath","//a[contains(@href,'warranty')]");

 Sync.waitPageLoad();

 Common.assertionCheckwithReport(Common.getCurrentURL().contains("warranty"),"Validate the Footer link "+Links, "Click the footer link "+Links+"it will navigate to page"+Links, "successfully navigating to "+Links +"page ","Failed to navigate to"+Links+"page");

 }

 catch (Exception |Error e) {

e.printStackTrace();

   ExtenantReportUtils.addFailedLog("Validate the Footer link "+Links,"Click the footer link "+Links+"it will navigate to page"+Links, "Failed to navigate to"+Links+"page", Common.getscreenShotPathforReport("failed to land on "+Links));

   Assert.fail();

 

 }

 }

 public void footerLinks_Privacy_policy(){

String Links= "privacy";

 try{

 Common.actionsKeyPress(Keys.END);

 Thread.sleep(3000);

 Common.clickElement("xpath","(//a[contains(@href,'privacy')])[2]");

 Sync.waitPageLoad();

 Common.assertionCheckwithReport(Common.getCurrentURL().contains("privacy-policy"),"Validate the Footer link "+Links, "Click the footer link "+Links+"it will navigate to page"+Links, "successfully navigating to "+Links +"page ","Failed to navigate to"+Links+"page");

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
public void CreateNewAccount(String dataSet) throws Exception {

		navigateMyAccount();
		String expectedResult = "opens Sign up pop up";

		try {
			Sync.waitElementClickable(30, By.xpath("//div[@class='social-login-popup-subheading subheading-register action create']"));
			// Common.assertionCheckwithReport("", expectedResult, "");

			// report.addPassLog(expectedResult,"Successfully opeans Sign up
			// pop_up page",Common.getscreenShotPathforReport("Successfully
			// opeans Sign up pop_up page"));

		} catch (Exception e) {
			if (Common.findElement("xpath", "//div[@class='social-login-popup-subheading subheading-register action create']") == null) {

				Common.clickElement("xpath", "//a[@class='social-login']");
				Thread.sleep(2000);
			}
		}

		String email = Common.genrateRandomEmail(data.get(dataSet).get("Email"));
		Common.clickElement("xpath", "//div[@class='social-login-popup-subheading subheading-register action create']");

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
			Common.clickElement("xpath", "//button[@class='action create primary social-login-popup-button']");
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
			int account= Common.isElementPresent("xpath", "//h1[@class='page-title']").size();
			

			Common.assertionCheckwithReport(account>0, "verifying new account creation confirmation ",
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

public void myAccountInformation() {
	try {
		Sync.waitElementClickable("xpath", "//ul[@class='nav items']/li[2]");
		Common.mouseOverClick("xpath", "//ul[@class='nav items']/li[2]");
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
		Thread.sleep(2000);
		Common.clickElement("xpath", "//div[@class='account-information-links']/button");
		Common.textBoxInput("id", "email", change_Email);
	} catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying the change password & email from",
				"User enter valid Email and password", "User failed to proceed to change email and passowrd ",
				Common.getscreenShotPathforReport("emailpasswordnew"));
		Assert.fail();

	}

	
	try {

		Common.textBoxInput("id", "pass", data.get(dataSet).get("Newpassword"));
		Common.clickElement("xpath", "//button[@class='login-page-submit-action']");
		Thread.sleep(4000);
		Sync.waitElementPresent("xpath", "//span[@data-ui-id='page-title-wrapper']");
		int account=Common.isElementPresent("xpath", "//h1[@class='page-title']").size();
		Common.assertionCheckwithReport(
				account>0,
				"verifying new credentials", "user login with new login data", "User login with new logines",
				"unabel to login new user logines");
		// (Common.getText("xpath",
		// "//span[@data-ui-id='page-title-wrapper']").equals("My Account"),
		// "user login with new login data", "User login with new logines",
		// Common.getscreenShotPathforReport("unabel to login new user
		// logines"));
	}

	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying new credentials", "User enter valid Email and password",
				"User failed to proceedchange email and passowrd ",
				Common.getscreenShotPathforReport("emailpassword"));
		Assert.fail();

	}

	// logOut();

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
			Thread.sleep(3000);
			String Text = Common.getText("xpath", "//input[@id='newsletter']//following::div[1]");
			expectedResult = "User gets confirmation message that it was submitted";
			ExtenantReportUtils.addPassLog("verifying newsletter subscription",
					"User get confirmation message if new email if it used mail it showing error message ", Text,
					Common.getscreenShotPathforReport("NewsLetter Subscrptionsuccess"));

		} catch (Exception | Error e) {
			
			e.printStackTrace();
			ExtenantReportUtils.addFailedLog("verifying newsletter subscription", "NewsLetter Subscrption success",
					"User failed to subscrption for newLetter  ",
					Common.getscreenShotPathforReport("NewsLetter Subscrptionsuccess"));
			Assert.fail();
     	}
}

public void contactUsPage_DR(String dataSet) throws Exception {
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

		Common.switchFrames("xpath", "//iframe[contains(@src,'quicksupport')]");

		// input[contains(@id,'Emails')]

		Sync.waitElementPresent("xpath", "//span[@id='lbl_Details']");

		int emailsize = Common.findElements("xpath", "//span[@id='lbl_Details']").size();
		Common.assertionCheckwithReport(emailsize > 0, "Email us form is visible in tab", expectedResult,
				"unabel to load the  contacts form");
		
	
			Common.dropdown("xpath", "//select[@id='ddlTitleMenu']", SelectBy.TEXT,
					data.get(dataSet).get("salutation_DE"));
		
		Sync.waitElementPresent("xpath", "//input[@id='txt_FirstName']");
		Common.textBoxInput("xpath", "//input[@id='txt_FirstName']", data.get(dataSet).get("FirstName"));
		
		Sync.waitElementPresent("xpath", "//input[@id='txt_LastName']");
		Common.textBoxInput("xpath", "//input[@id='txt_LastName']", data.get(dataSet).get("LastName"));

		Common.textBoxInput("xpath", "//input[@id='txt_Eamil']", data.get(dataSet).get("Email"));
		
		Common.textBoxInput("xpath", "//input[@id='txt_ConfirmEmail']", data.get(dataSet).get("Email"));

		

		

		Sync.waitElementPresent("xpath", "//input[@id='txt_Company']");
		Common.textBoxInput("xpath", "//input[@id='txt_Company']", data.get(dataSet).get("Company"));
		
		Sync.waitElementPresent("xpath", "//input[@id='txt_Address']");
		Common.textBoxInput("xpath", "//input[@id='txt_Address']", data.get(dataSet).get("Street"));
		
		Sync.waitElementPresent("xpath", "//input[@id='txt_ZipCode']");
		Common.textBoxInput("xpath", "//input[@id='txt_ZipCode']", data.get(dataSet).get("postcode"));
		
		Sync.waitElementPresent("xpath", "//input[@id='txt_City']");
		Common.textBoxInput("xpath", "//input[@id='txt_City']", data.get(dataSet).get("City"));

		Sync.waitElementPresent("xpath", "//input[@id='txt_phone']");
		Common.textBoxInput("xpath", "//input[@id='txt_phone']", data.get(dataSet).get("phone"));

		Common.textBoxInput("xpath", "//input[@id='txt_SearchModelNumber']", data.get(dataSet).get("Searchmodel"));
		Thread.sleep(10000);
		
		Common.javascriptclickElement("xpath", "//input[@id='txt_SearchModelNumber']//following::ul/li/div[text()='24 oz Standard Mouth Black S24SX001']");
		

		
		
 
		Sync.waitElementPresent("xpath", "//div[@class='lbl_container']/select[@name='dll_Inquirycategory']");
		Thread.sleep(5000);
		
		Common.dropdown("xpath", "//select[@name='dll_Inquirycategory']",SelectBy.TEXT,
		data.get(dataSet).get("category_Demand_DE"));
 		
		Common.dropdown("xpath", "//select[@id='ddl_RequestType']",SelectBy.TEXT,
				data.get(dataSet).get("Type_DE"));
		
		Sync.waitElementPresent("xpath", "//input[contains(@id,'txtpurchasedate') and @type='text']");
		Common.textBoxInput("xpath", "//input[contains(@id,'txtpurchasedate') and @type='text']",
				data.get(dataSet).get("OrderDate"));
        
		Common.textBoxInput("xpath", "//textarea[contains(@id,'txt_MessageBox')]",
				data.get(dataSet).get("Question"));
		Common.clickElement("xpath", "//input[@id='subLinkBut']");
		
	}
	

	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying email us from",
				"contact us form data enter without any error message", "Contact us page getting error ",
				Common.getscreenShotPathforReport("Contact us page"));
		Assert.fail();

	}
     
	Thread.sleep(10000);
	Common.actionsKeyPress(Keys.PAGE_DOWN);
	int Text = Common.isElementPresent("xpath", "//span[@id='lbl_thanku']").size();
	expectedResult = "User gets confirmation under the same tab. It includes a reference number and email is sent to email provided. No validation errors.";
	Common.assertionCheckwithReport(Text>0,
			"verifying contact us conformation message", expectedResult,
			"User gets confirmation under the same tab", "unabel to load the confirmation form");
	}

public void contactUsPage_FR(String dataSet) throws Exception {
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

		Common.switchFrames("xpath", "//iframe[contains(@src,'quicksupport')]");

		// input[contains(@id,'Emails')]

		Sync.waitElementPresent("xpath", "//span[@id='lbl_Details']");

		int emailsize = Common.findElements("xpath", "//span[@id='lbl_Details']").size();
		Common.assertionCheckwithReport(emailsize > 0, "Email us form is visible in tab", expectedResult,
				"unabel to load the  contacts form");
		
	
			Common.dropdown("xpath", "//select[@id='ddlTitleMenu']", SelectBy.TEXT,
					data.get(dataSet).get("salutation_FR"));
		
		Sync.waitElementPresent("xpath", "//input[@id='txt_FirstName']");
		Common.textBoxInput("xpath", "//input[@id='txt_FirstName']", data.get(dataSet).get("FirstName"));
		
		Sync.waitElementPresent("xpath", "//input[@id='txt_LastName']");
		Common.textBoxInput("xpath", "//input[@id='txt_LastName']", data.get(dataSet).get("LastName"));

		Common.textBoxInput("xpath", "//input[@id='txt_Eamil']", data.get(dataSet).get("Email"));
		
		Common.textBoxInput("xpath", "//input[@id='txt_ConfirmEmail']", data.get(dataSet).get("Email"));

		

		

		Sync.waitElementPresent("xpath", "//input[@id='txt_Company']");
		Common.textBoxInput("xpath", "//input[@id='txt_Company']", data.get(dataSet).get("Company"));
		
		Sync.waitElementPresent("xpath", "//input[@id='txt_Address']");
		Common.textBoxInput("xpath", "//input[@id='txt_Address']", data.get(dataSet).get("Street"));
		
		Sync.waitElementPresent("xpath", "//input[@id='txt_ZipCode']");
		Common.textBoxInput("xpath", "//input[@id='txt_ZipCode']", data.get(dataSet).get("postcode"));
		
		Sync.waitElementPresent("xpath", "//input[@id='txt_City']");
		Common.textBoxInput("xpath", "//input[@id='txt_City']", data.get(dataSet).get("City"));

		Sync.waitElementPresent("xpath", "//input[@id='txt_phone']");
		Common.textBoxInput("xpath", "//input[@id='txt_phone']", data.get(dataSet).get("phone"));

		Common.textBoxInput("xpath", "//input[@id='txt_SearchModelNumber']", data.get(dataSet).get("Searchmodel"));
		Thread.sleep(10000);
		
		Common.javascriptclickElement("xpath", "//input[@id='txt_SearchModelNumber']//following::ul/li/div[text()='24 oz Standard Mouth Black S24SX001']");
		

		
		
 
		Sync.waitElementPresent("xpath", "//div[@class='lbl_container']/select[@name='dll_Inquirycategory']");
		Thread.sleep(5000);
		
		Common.dropdown("xpath", "//select[@name='dll_Inquirycategory']",SelectBy.TEXT,
		data.get(dataSet).get("category_Demand_FR"));
 		
		Common.dropdown("xpath", "//select[@id='ddl_RequestType']",SelectBy.TEXT,
				data.get(dataSet).get("Type_FR"));
		
		Sync.waitElementPresent("xpath", "//input[contains(@id,'txtpurchasedate') and @type='text']");
		Common.textBoxInput("xpath", "//input[contains(@id,'txtpurchasedate') and @type='text']",
				data.get(dataSet).get("OrderDate"));
        
		Common.textBoxInput("xpath", "//textarea[contains(@id,'txt_MessageBox')]",
				data.get(dataSet).get("Question"));
		Common.clickElement("xpath", "//input[@id='subLinkBut']");
		
	}
	

	catch (Exception | Error e) {
		e.printStackTrace();
		ExtenantReportUtils.addFailedLog("verifying email us from",
				"contact us form data enter without any error message", "Contact us page getting error ",
				Common.getscreenShotPathforReport("Contact us page"));
		Assert.fail();

	}
     
	Thread.sleep(10000);
	Common.actionsKeyPress(Keys.PAGE_DOWN);
	int Text = Common.isElementPresent("xpath", "//span[@id='lbl_thanku']").size();
	expectedResult = "User gets confirmation under the same tab. It includes a reference number and email is sent to email provided. No validation errors.";
	Common.assertionCheckwithReport(Text>0,
			"verifying contact us conformation message", expectedResult,
			"User gets confirmation under the same tab", "unabel to load the confirmation form");
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
			Sync.waitElementPresent("xpath", "(//a[contains(@href,'contact')])[2]");
			Common.clickElement("xpath", "(//a[contains(@href,'contact')])[2]");
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
public void verifying_letsGo(){
	try{
	Thread.sleep(5000);
	Sync.waitPageLoad();
	Common.clickElement("xpath", "//ul[@class='megamenu-list']/li[2]");
	Common.clickElement("xpath", "//span[text()='Let’s Go!']");
	//Common.clickElement("xpath", "//ul[@class='megamenu-list']/li[3]//ul/li[1]/a");
	Common.assertionCheckwithReport(Common.getPageTitle().contains("Let’s Go!"), "verifying Header link of Lets go","user open the Lets go page", "user successfully open the header link lets go","Failed open the header link lets go");
	
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
	
	
    Common.clickElement("xpath", "//ul[@class='learn-menu']//li[2]");
    Common.clickElement("xpath", "//span[text()='Parks For All']");
	//Common.clickElement("xpath", "//ul[@class='megamenu-list']/li[3]//ul/li[2]/a");
    int parks=Common.isElementPresent("xpath", "//a[@class='logo']").size();

	Common.assertionCheckwithReport(parks>0, "verifying Header link of Parks For All","user open the Parks For All page", "user successfully open the header link Parks For All","Failed open the header link Parks For All");
	
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
	
	Common.clickElement("xpath", "//ul[@class='learn-menu']//li[3]");
	//Common.clickElement("xpath", "//ul[@class='megamenu-list']/li[3]//ul/li[3]/a");
	Common.clickElement("xpath", "(//a[contains(@href,'our-story')])[1]");
	Common.assertionCheckwithReport(Common.getPageTitle().contains(" Hydro Flask"), "verifying Header link of Our Story","user open the Our Story page", "user successfully open the header link Our Story","Failed open the header link Our Story");
	
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
	Common.clickElement("xpath", "//ul[@class='learn-menu']//li[4]");
	
	Common.clickElement("xpath", "(//a[contains(@href,'world-surf-league')])[1]");
	//Common.clickElement("xpath", "//ul[@class='megamenu-list']/li[3]//ul/li[4]/a");
	int partnership=Common.isElementPresent("xpath", "(//a[contains(@href,'world-surf-league')])[2]").size();
	Common.assertionCheckwithReport(partnership>0, "verifying Header link WSL Partnership","user open the WSL Partnership", "user successfully open the header link WSL Partnership","Failed open the WSL Partnership");
	
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
	Common.clickElement("xpath", "//ul[@class='learn-menu']//li[5]");
	Common.clickElement("xpath", "(//a[contains(@href,'contact')])[1]");
	//Common.clickElement("xpath", "//ul[@class='megamenu-list']/li[5]//ul/li[5]/a");
	int contact=Common.isElementPresent("xpath", "//a[@class='logo']").size();
	Common.assertionCheckwithReport(contact > 0, "verifying Header link contact","user open the contact header link", "user successfully open the header link contact","Failed open the contact");
	
	}
	catch (Exception | Error e) {
		e.printStackTrace();
        ExtenantReportUtils.addFailedLog("validating Header Links contact link","user open the contact headerlink","User unabel open the contact link",Common.getscreenShotPathforReport("contact"));
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
               Sync.waitElementClickable("xpath", "//label[@for='adyen_cc']");
int sizes=Common.findElements("xpath", "//label[@for='adyen_cc']").size();
   Common.assertionCheckwithReport(sizes>0, "Successfully land on the payment section", expectedResult,"User unabel to land on paymentpage");
Common.clickElement("xpath", "//label[@for='adyen_cc']");
               //Common.clickElement("xpath", "//label[@for='paymetric']");
Thread.sleep(2000);
Common.switchFrames("xpath", "//iframe[@title='Iframe for secured card data input field']");

Common.textBoxInput("xpath", "//input[@id='encryptedCardNumber']", data.get(dataSet).get("cardNumber"));
Common.switchToDefault();
Common.switchFrames("xpath", "//span[contains(@class,'adyen-checkout__card__exp-dat')]/iframe");

Common.textBoxInput("xpath", "//input[@id='encryptedExpiryDate']", data.get(dataSet).get("ExpYearMonth"));

Common.switchToDefault();
Common.switchFrames("xpath","//span[contains(@class,'adyen-checkout__card__cvc__input')]/iframe");
Common.textBoxInput("xpath", "//input[@id='encryptedSecurityCode']", data.get(dataSet).get("cvv"));
Common.switchToDefault();
Common.textBoxInput("xpath", "//input[contains(@class,'adyen-checkout__card__holderName__input')]",data.get(dataSet).get("UserName"));
Thread.sleep(2000);

Common.actionsKeyPress(Keys.ARROW_DOWN);

Thread.sleep(1000);
Common.clickElement("xpath", "//label[@for='agreement_adyen_cc_1']");
Common.clickElement("xpath", "//button[@class='action primary checkout']");
//stage working //Common.clickElement("xpath", "//button[@title='Place Order']");
           }
           catch(Exception |Error e) {
        	   e.printStackTrace();
   ExtenantReportUtils.addFailedLog("validating the Credit Card infromation", expectedResult, "faield to fill the Credit Card infromation", Common.getscreenShotPathforReport("Cardinfromationfail"));
    //ExtenantReportUtils.addFailedLog("User click check out button", "User unabel click the checkout button", Common.getscreenShotPathforReport("check out miniCart"));
   Assert.fail();
           }
   }

public void submitWarranty_FR(String dataSet) throws Exception {
		String expectedResult = "It should land  warranty page ";

		Common.actionsKeyPress(Keys.END);
		clickWarranty();
		Thread.sleep(5000);
		try {
			Sync.waitElementPresent("xpath", "(//a[contains(@href,'contact')])[2]");
			Common.clickElement("xpath", "(//a[contains(@href,'contact')])[2]");
			
			expectedResult = "User is landed on contact_US page ";
			String current_url = Common.getCurrentURL();
			Common.assertionCheckwithReport(current_url.contains("contact"), "verifying contact us page", "user redirected to contact us page",
					"Successfully User is redirected to contact us page", "User unabel redirected to contact us page");
			
		}

		catch (Exception | Error e) {
			ExtenantReportUtils.addFailedLog("verifying contact us page", "user redirected to contact us page",
					"User failed to redirected to contact page", Common.getscreenShotPathforReport("warranty Page"));
			Assert.fail();
		}

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
		Sync.waitElementPresent("xpath", "//a[contains(@href,'warranty')]");
		Common.clickElement("xpath", "//a[contains(@href,'warranty')]");
		String currenturl = Common.getCurrentURL();
		Common.assertionCheckwithReport(currenturl.contains("warranty"), "verifying warranty page",
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




public void submitWarranty_DE(String dataSet) throws Exception {
	String expectedResult = "It should land  warranty page ";

	Common.actionsKeyPress(Keys.END);
	clickWarranty_DE();
	Thread.sleep(5000);
	try {
		Sync.waitElementPresent("xpath", "(//a[contains(@href,'contact')])[2]");
		Common.clickElement("xpath", "(//a[contains(@href,'contact')])[2]");
		
		expectedResult = "User is landed on contact_US page ";
		String current_url = Common.getCurrentURL();
		Common.assertionCheckwithReport(current_url.contains("contact"), "verifying contact us page", "user redirected to contact us page",
				"Successfully User is redirected to contact us page", "User unabel redirected to contact us page");
		
	}

	catch (Exception | Error e) {
		ExtenantReportUtils.addFailedLog("verifying contact us page", "user redirected to contact us page",
				"User failed to redirected to contact page", Common.getscreenShotPathforReport("warranty Page"));
		Assert.fail();
	}

}



public void clickWarranty_DE() throws Exception {

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
		Sync.waitElementPresent("xpath", "//a[contains(@href,'warranty')]");
		Common.clickElement("xpath", "//a[contains(@href,'warranty')]");
		String currenturl = Common.getCurrentURL();
		Common.assertionCheckwithReport(currenturl.contains("warranty"), "verifying warranty page",
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

public void minicart(String dataSet) throws Exception {
	Thread.sleep(2000);
	Common.clickElement("xpath", "(//span[@class='minicart-trigger-num'])[1]");
	
	Sync.waitElementClickable("xpath", "//a[contains(@class,'viewcart')]");
	Common.clickElement("xpath", "//a[contains(@class,'viewcart')]");

	Thread.sleep(10000);
	// div[contains(@class,'no-edit')]/a[2]
	// Sync.waitElementVisible("className", "checkout-step-title");
	// report.addPassLog("Clicked the checkout
	// button",Common.getscreenShotPathforReport("checked out page"));

	// div[contains(@class,'no-remove')]

	List<WebElement> elemtddds = Common.findElements("xpath", "//select[@class='cart-item-qty-dropdown']");

	//elemtddds.get(elemtddds.size() - 1).click();
	Common.dropdown("xpath", "//select[@class='cart-item-qty-dropdown']", Common.SelectBy.VALUE, data.get(dataSet).get("ProductQuantity"));
}

public void minicart_Validation() throws Exception {
	String expectedResult="it should land on shipping page ";
	try
	{
		
	Thread.sleep(2000);
	Common.clickElement("xpath", "(//span[@class='minicart-trigger-num'])[1]");
	
	Sync.waitElementClickable("xpath", "(//a[contains(@href,'cart')])[4]");
	Common.clickElement("xpath", "(//a[contains(@href,'cart')])[4]");

	Thread.sleep(10000);
	Common.clickElement("xpath", "(//span[@class='minicart-trigger-num'])[1]");
	Common.clickElement("xpath", "//a[contains(@data-bind,'Remove item')]");
	Common.clickElement("xpath", "//button[@class='action-primary action-accept']");
	
	int shopppingcartEmpty=Common.findElements("xpath", "//div[@class='empty-cart-page-text']").size();
	if(shopppingcartEmpty>0) {
		orderSubmit("Bottles");
		Sync.waitElementClickable("xpath", "//a[contains(@class,'viewcart')]");
		Common.clickElement("xpath", "//a[contains(@class,'viewcart')]");
		
		
		Sync.waitElementClickable("xpath", "(//button[@class='action primary checkout'])[3]");
		Common.clickElement("xpath", "(//button[@class='action primary checkout'])[3]");
		
		int Checkout =Common.findElements("xpath", "(//div[@class='checkout-step-title'])[1]").size();
		System.out.println(Checkout);
		Common.assertionCheckwithReport(Checkout>0, "verifying checkout page",
				"It should land  checkout page", "Successfully land on checkout page",
				"User unabel on vheckout page");
		
		
	}
	}	
	
	catch (Exception |Error e) {

		e.printStackTrace();

		report.addFailedLog(expectedResult,"Should Land on Shipping page", "  Shipping page not displayed", Common.getscreenShotPathforReport(" Land on Shipping page Failed"));

		Assert.fail();
		
		 }    
}

	
	
	
	

	


public void Shop_header_Validation( ) throws Exception {
	
	
	String expectedResult = "User should land on the home page";
	int size = Common.findElements("xpath", "//a[@class='logo']").size();
	Common.assertionCheckwithReport(size > 0, "validating the home page ", expectedResult,"Successfully landed on the home page", "User unabel to land on home page");
	
	 try{  

	 Common.clickElement("xpath","//ul[@class='megamenu-list']/li[1]/div[1]/button");
	 Common.clickElement("xpath","(//a[contains(@href,'bottles')])[1]");
     int bottles=Common.isElementPresent("xpath", "//div[@class='description-banner-title']").size();
   
	 Common.assertionCheckwithReport(bottles>0, "Verifying bottles page", "It should navigate tobottles page", "successfully lands on bottles page ","bottles page");
	
	 }
	 

	 catch (Exception |Error e) {

	e.printStackTrace();

	report.addFailedLog(expectedResult,"Should display   bottles  page", "bottles Page not displayed", Common.getscreenShotPathforReport("bottles page display Failed"));

	Assert.fail();
	
	 }    
}


public void accessories_Validation( ) throws Exception {
	String expectedResult= "It should navigate to accessories";
	try{  

	 Common.clickElement("xpath","//ul[@class='megamenu-list']/li[1]/div[1]/button");
	 Common.clickElement("xpath","(//a[contains(@href,'accessories')])[1]");
	 int accessories=Common.isElementPresent("xpath", "(//a[contains(@href,'accessories')])[7]").size();
	Common.assertionCheckwithReport(accessories>0, "Verifying accessories page", "It should navigate to accessories page", "successfully lands on accessories page ","accessories page");
	 }

	 catch (Exception |Error e) {

	e.printStackTrace();

	report.addFailedLog(expectedResult,"Should display accessories page", "accessories Page not displayed", Common.getscreenShotPathforReport("accessories page display Failed"));

	Assert.fail();
	
	 }    
}


public void tumblers_Validation() throws Exception {
	String expectedResult= "It should navigate to tumblers";
	try{  

	 Common.clickElement("xpath","//ul[@class='megamenu-list']/li[1]/div[1]/button");
	 Common.clickElement("xpath","(//a[contains(@href,'tumblers')])[1]");
	 int tumbler=Common.isElementPresent("xpath", "//div[@class='category-sibling-current-category has-siblings']").size();
	Common.assertionCheckwithReport(tumbler>0, "Verifying tumbler page", "It should navigate to tumbler page", "successfully lands on tumbler page ","tumbler page");
	 }

	 catch (Exception |Error e) {

	e.printStackTrace();

	report.addFailedLog(expectedResult,"Should display tumbler   page", "tumbler Page not displayed", Common.getscreenShotPathforReport("  tumbler  page display Failed"));

	Assert.fail();
	
	 }    
}



public void coffee_Validation( ) throws Exception {
	String expectedResult= "It should navigate to coffee";
	try{  

	 Common.clickElement("xpath","//ul[@class='megamenu-list']/li[1]/div[1]/button");
	 Common.clickElement("xpath","(//a[contains(@href,'coffee')])[1]");
	 int coffee=Common.isElementPresent("xpath", "//div[@class='description-banner-title']").size();

	Common.assertionCheckwithReport(coffee>0, "Verifying coffee page", "It should navigate to coffee page", "successfully lands on coffee page ","coffee page");
	 }

	 catch (Exception |Error e) {

	e.printStackTrace();

	report.addFailedLog(expectedResult,"Should display coffee page"," coffee Page not displayed", Common.getscreenShotPathforReport("coffee page display Failed"));

	Assert.fail();
	
	 }    
}


public void beer_Validation( ) throws Exception {
	String expectedResult= "It should navigate to coffee";
	try{  

	 Common.clickElement("xpath","//ul[@class='megamenu-list']/li[1]/div[1]/button");
	 Common.clickElement("xpath","(//a[contains(@href,'beer')])[1]");
	 int beer=Common.isElementPresent("xpath", "//div[@class='description-banner-title']").size();

	Common.assertionCheckwithReport(beer>0, "Verifying beer page", "It should navigate to beer page", "successfully lands on beer page ","beer page");
	 }

	 catch (Exception |Error e) {

	e.printStackTrace();

	report.addFailedLog(expectedResult,"Should display beer page", "beer Page not displayed", Common.getscreenShotPathforReport("beer page display Failed"));

	Assert.fail();
	
	 }    
}



public void Wine_Validation( ) throws Exception {
	String expectedResult= "It should navigate to coffee";
	try{  

	 Common.clickElement("xpath","//ul[@class='megamenu-list']/li[1]/div[1]/button");
	 Common.clickElement("xpath","(//a[contains(@href,'wine')])[1]");
	 int wine=Common.isElementPresent("xpath", "//div[@class='description-banner-title']").size();

	Common.assertionCheckwithReport(wine>0, "Verifying wine page", "It should navigate to beer page", "successfully lands on beer page ","beer page");
	 }

	 catch (Exception |Error e) {

	e.printStackTrace();

	report.addFailedLog(expectedResult,"Should display wine  page", "wine Page not displayed", Common.getscreenShotPathforReport("wine page display Failed"));

	Assert.fail();
	
	 }    
}


public void coolers_Validation( ) throws Exception {
	String expectedResult= "It should navigate to coffee";
	try{  

	 Common.clickElement("xpath","//ul[@class='megamenu-list']/li[1]/div[1]/button");
	 Common.clickElement("xpath","(//a[contains(@href,'coolers')])[1]");
	 int coolers=Common.isElementPresent("xpath", "//div[@class='description-banner-title']").size();

	Common.assertionCheckwithReport(coolers>0, "Verifying coolers page", "It should navigate to coolers page", "successfully lands on coolers page ","beer page");
	 }

	 catch (Exception |Error e) {

	e.printStackTrace();

	report.addFailedLog(expectedResult,"Should display coolers  page", "coolers Page not displayed", Common.getscreenShotPathforReport("coolers page display Failed"));

	Assert.fail();
	
	 }    
}


public void Food_Validation( ) throws Exception {
	String expectedResult= "It should navigate to coffee";
	try{  

	 Common.clickElement("xpath","//ul[@class='megamenu-list']/li[1]/div[1]/button");
	 Common.clickElement("xpath","(//a[contains(@href,'food')])[1]");
	 int food=Common.isElementPresent("xpath", "//div[@class='description-banner-title']").size();

	Common.assertionCheckwithReport(food>0, "Verifying food page", "It should navigate to food page", "successfully lands on food page ","beer page");
	 }

	 catch (Exception |Error e) {

	e.printStackTrace();

	report.addFailedLog(expectedResult,"Should display food  page", "food Page not displayed", Common.getscreenShotPathforReport("food page display Failed"));

	Assert.fail();
	
	 }    
}

public void More_Validation( ) throws Exception {
	String expectedResult= "It should navigate to coffee";
	try{  

	 Common.clickElement("xpath","//ul[@class='megamenu-list']/li[1]/div[1]/button");
	 Common.clickElement("xpath","(//button[@data-ac-test='link-to_navigation-category_level2'])[2]");
	 int more=Common.isElementPresent("xpath", "//div[@class='description-banner-title']").size();

	Common.assertionCheckwithReport(more>0, "Verifying food page", "It should navigate to food page", "successfully lands on food page ","beer page");
	 }

	 catch (Exception |Error e) {

	e.printStackTrace();

	report.addFailedLog(expectedResult,"Should display food  page", "food Page not displayed", Common.getscreenShotPathforReport("food page display Failed"));

	Assert.fail();
	
	 }    
}


public void Bottle()throws Exception {
   String expectedResult="It should navigate to write review page";
	try {
		
		Common.clickElement("xpath","//ul[@class='megamenu-list']/li[1]/div[1]/button");
		 Common.clickElement("xpath","(//a[contains(@href,'bottles')])[1]");
	     int bottles=Common.isElementPresent("xpath", "//div[@class='description-banner-title']").size();
	if(bottles>0){
		
		Common.scrollIntoView("xpath", "(//a[contains(@href,'mouth')])[28]");
		 Common.clickElement("xpath","(//a[contains(@href,'mouth')])[28]");
	}else {
		Common.scrollIntoView("xpath", "(//a[contains(@href,'mouth')])[26]");
	 Common.clickElement("xpath","(//a[contains(@href,'mouth')])[26]");
	}
	
	Sync.waitElementClickable("xpath", "//button[@id='tab-title-reviews']");
	 Common.clickElement("xpath","//button[@id='tab-title-reviews']");
	 Thread.sleep(5000);
	 Sync.waitElementClickable("xpath", "//div[@class='bv-write-review-container bv-write-container']/button");
	 Common.clickElement("xpath","//div[@class='bv-write-review-container bv-write-container']/button");
	
	 
	 int Review=Common.isElementPresent("xpath", "(//span[@class='bv-fieldset-label'])[1]").size();
	 Common.assertionCheckwithReport(Review>0, "Verifying Review page", "It should navigate to Review page", "successfully lands on Review page ","Review page");
	 
	}
	
catch (Exception |Error e) {
e.printStackTrace();
	report.addFailedLog(expectedResult,"Should display food  page", "food Page not displayed", Common.getscreenShotPathforReport("food page display Failed"));

	Assert.fail();
	
	 }    
}
public void ReviewForm(String dataSet) throws Exception {
	String expectedResult="It should fill the review page or form ";
	try {
	Common.clickElement("xpath","(//span[@class='bv-fieldset-rating-group bv-radio-group notranslate']//span[@role='radio'])[3]");
	
	Common.textBoxInput("id", "bv-text-field-title", data.get(dataSet).get("Reviewtitle"));
	Common.textBoxInput("id", "bv-textarea-field-reviewtext", data.get(dataSet).get("ReviewDescription"));
	Sync.waitElementClickable("id", "bv-radio-isrecommended-true-label");
	Common.clickElement("id","bv-radio-isrecommended-true-label");
	Common.textBoxInput("id", "bv-text-field-usernickname", data.get(dataSet).get("Nickname"));
	Common.textBoxInput("id", "bv-text-field-userlocation", data.get(dataSet).get("Location"));
	Common.textBoxInput("id", "bv-email-field-hostedauthentication_authenticationemail", data.get(dataSet).get("Email"));
	
	
	
	Common.dropdown("id", "bv-select-field-contextdatavalue_Age", Common.SelectBy.TEXT, data.get(dataSet).get("Age"));
	Common.dropdown("id", "bv-select-field-contextdatavalue_Gender", Common.SelectBy.TEXT, data.get(dataSet).get("gender"));
	Common.clickElement("xpath","(//span[@class='bv-submission-star-rating-control']//span[@aria-hidden='true'])[7]");
	
	Common.clickElement("xpath","(//span[@class='bv-submission-star-rating-control']//span[@aria-hidden='true'])[12]");
	Common.clickElement("xpath","//span[@class='bv-fieldset-netpromoterscore-group bv-radio-group notranslate']//li[4]");
	Common.clickElement("xpath","//button[@name='bv-submit-button']");

	 int Review=Common.isElementPresent("id", "bv-mbox-label-text").size();
	 Common.assertionCheckwithReport(Review>0, "Verifying Review submitt page", "It should navigate to Review submitt page", "successfully lands on Review submitt page ","Review submitt page");
	
	}
	
	catch (Exception |Error e) {
		e.printStackTrace();
			report.addFailedLog(expectedResult,"Should display Review submitt  page", "Review submitt Page not displayed", Common.getscreenShotPathforReport("Review submitt page display Failed"));

			Assert.fail();
			
			 }    
		}
}


