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
			Sync.waitElementClickable("xpath", "//span[contains(text(),'Continue To Payment')]");
			Common.clickElement("xpath", "//span[contains(text(),'Continue To Payment')]");
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
		
		Common.textBoxInput("xpath", "//input[@id='encryptedCardNumber']", data.get(dataSet).get("cardNumber"));

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
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Login • Instagram"), "Verifying Social link  "+socallinksarry[i],"User click the social "+socallinksarry[i], "successfully navigating to social link  "+socallinksarry[i], "Failed to navigate to social link "+socallinksarry[i]);
			Common.closeCurrentWindow();
			Common.switchToFirstTab();
		}
   else	if(socallinksarry[i].equals("Facebook")){
			Common.assertionCheckwithReport(Common.getPageTitle().contains("Log into Facebook"), "Verifying Social link  "+socallinksarry[i],"User click the social "+socallinksarry[i], "successfully navigating to social link  "+socallinksarry[i], "Failed to navigate to social link "+socallinksarry[i]);
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

 Common.assertionCheckwithReport(Common.getPageTitle().equals("We are Hydro Flask"),"Validate the Footer link "+Links, "Click the footer link "+Links+"it will navigate to page"+Links, "successfully navigating to "+Links +"page ","Failed to navigate to"+Links+"page");

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

 Common.assertionCheckwithReport(Common.getPageTitle().equals("Hydro Flask Press Coverage & In the News | Hydro Flask"),"Validate the Footer link "+Links, "Click the footer link "+Links+"it will navigate to page"+Links, "successfully navigating to "+Links +"page ","Failed to navigate to"+Links+"page");

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
}

 