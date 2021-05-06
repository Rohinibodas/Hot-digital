package TestComponent.PUR;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import TestLib.Automation_properties;
import TestLib.Common;
import TestLib.Sync;
import TestLib.Common.SelectBy;
import Utilities.ExcelReader;
import Utilities.ExtenantReportUtils;
import Utilities.Utils;

public class PurHelper {
	
	String datafile;
	static Automation_properties automation_properties = Automation_properties.getInstance();
	ExcelReader excelData;
	Map<String, Map<String, String>> data=new HashMap<>();
	static ExtenantReportUtils report;

	public  PurHelper(String datafile)
	{
		excelData=new ExcelReader(datafile);
		data=excelData.getExcelValue();
		this.data=data;

		if(Utilities.TestListener.report==null)
		{
			report=new ExtenantReportUtils("PUR");
			report.createTestcase("PURTestCases");
		}else{
			this.report=Utilities.TestListener.report;
		}
	}
	public void createAccount(String DataSet) throws InterruptedException
	{
		String expectedResult="Naviagating to account Creation page";
		try {
	
			Sync.waitElementPresent("xpath", "//a[@title='Sign In / Sign Up']");
	Common.clickElement("xpath", "//a[@title='Sign In / Sign Up']");
	Sync.waitElementPresent("xpath", "//a[@class='action create primary']");
	
	Common.clickElement("xpath", "//a[@class='action create primary']");
	Thread.sleep(5000);
	report.addPassLog(expectedResult, "Should display Account Creation page", "Account Creation page display successfully", Common.getscreenShotPathforReport("Account Creation"));
	
	Common.textBoxInput("id", "firstname", data.get(DataSet).get("FirstName"));
	Common.textBoxInput("id", "lastname", data.get(DataSet).get("LastName"));
	Common.actionsKeyPress(Keys.ARROW_DOWN);
	
	//Sync.waitElementPresent("xpath", "//input[@title='Sign Up for Newsletter']");
	//Common.clickElement("xpath", "//input[@title='Sign Up for Newsletter']");
	

	Common.textBoxInput("id", "email_address", Utils.getEmailid());
	
	//Common.textBoxInput("id", "email_address",data.get(DataSet).get("Email"));
	Common.textBoxInput("id", "password", data.get(DataSet).get("Password"));
	Common.textBoxInput("id", "password-confirmation", data.get(DataSet).get("Password"));
	Common.actionsKeyPress(Keys.PAGE_DOWN);
	Thread.sleep(4000);
	Common.clickElement("xpath", "//button[@title='Create an Account']");
	Thread.sleep(10000);
	report.addPassLog(expectedResult, "Should display My Account Page", "My Account Page display successfully", Common.getscreenShotPathforReport("Account Creation success"));
	
	String s=Common.getText("xpath", "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");
	System.out.println(s);
	
}catch(Exception |Error e)
{
	report.addFailedLog(expectedResult,"Should display My Account Page", "My Account Page not display", Common.getscreenShotPathforReport("Account Creation Failed"));
	e.printStackTrace();
	Assert.fail();
}
}
	
	public void ClicktheSignbutton() throws Exception{
		Sync.waitElementPresent("xpath", "//a[@title='Sign In / Sign Up']");
		Common.clickElement("xpath", "//a[@title='Sign In / Sign Up']");

	}

	public void singin(String DataSet) throws Exception{
		   
		String expectedResult="Land on login page";

		try {
			
			Sync.waitElementPresent("xpath", "//a[@title='Sign In / Sign Up']");
			Common.clickElement("xpath", "//a[@title='Sign In / Sign Up']");
			Thread.sleep(3000);
			
			report.addPassLog(expectedResult, "Should navigate to Login page", "Login page displayed successfully", Common.getscreenShotPathforReport("Navigate to Login Page "));
			Thread.sleep(2000);
			
			Sync.waitElementPresent("id", "email");
			Thread.sleep(4000);

			Common.textBoxInput("id", "email", data.get(DataSet).get("Email"));
			Common.textBoxInput("id", "pass", data.get(DataSet).get("Password"));
			Thread.sleep(2000);
			Sync.waitElementPresent("xpath", "//*[@id='send2']/span");
			Common.clickElement(By.xpath("//*[@id='send2']/span"));
			
			report.addPassLog(expectedResult,"Should Login to application successfully", "Login to application", Common.getscreenShotPathforReport("Login Page Success"));
		}

		catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should Login to application successfully", "Login to application Failed", Common.getscreenShotPathforReport("Login failed"));
			e.printStackTrace();
			Assert.fail();
	}	
}
	
	public void newslettersubscription()throws Exception{
		   
		String expectedResult="Subscribe for newsletter";

		try {
			
			Thread.sleep(5000);
			

			Sync.waitElementPresent("xpath", "//a[contains(text(), 'Newsletter Subscriptions')]");
			Common.clickElement("xpath", "//a[contains(text(), 'Newsletter Subscriptions')]");

			Thread.sleep(3000);

			Sync.waitElementPresent("xpath", "//input[@name='is_subscribed']");
			Common.clickElement("xpath", "//input[@name='is_subscribed']");
			
			Thread.sleep(3000);

			Sync.waitElementPresent("xpath", "//button[@title='Save']");
			Common.clickElement("xpath", "//button[@title='Save']");

			Thread.sleep(300);

			String s=Common.getText("xpath", "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");
			System.out.println(s);
			Thread.sleep(3000);
			report.addPassLog(expectedResult,"Should subscribe to newsletter successfully", "Subscription page success", Common.getscreenShotPathforReport("subscription Successfully"));
		}

		catch(Exception |Error e)
		{
			report.addPassLog(expectedResult,"Should subscribe to newsletter successfully", "Subscription page Failed", Common.getscreenShotPathforReport("subscription failed"));
			e.printStackTrace();
			Assert.fail();
	}	
}	

	
	public void newsletterUnsubscription()throws Exception{
		   
		String expectedResult="UnSubscribe for newsletter";

		try {
			
			Thread.sleep(5000);
			

			Sync.waitElementPresent("xpath", "//a[contains(text(), 'Newsletter Subscriptions')]");
			Common.clickElement("xpath", "//a[contains(text(), 'Newsletter Subscriptions')]");

			Thread.sleep(3000);

			Sync.waitElementPresent("xpath", "//input[@name='is_subscribed']");
			Common.clickElement("xpath", "//input[@name='is_subscribed']");
			
			Thread.sleep(3000);

			Sync.waitElementPresent("xpath", "//button[@title='Save']");
			Common.clickElement("xpath", "//button[@title='Save']");

			Thread.sleep(300);

			String s=Common.getText("xpath", "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");
			System.out.println(s);
			Thread.sleep(3000);
			report.addPassLog(expectedResult,"Should unsubscribe to newsletter successfully", "UnSubscription page success", Common.getscreenShotPathforReport("Remove subscription Successfully"));
		}

		catch(Exception |Error e)
		{
			report.addPassLog(expectedResult,"Should Unsubscribe to newsletter successfully", "UnSubscription page Failed", Common.getscreenShotPathforReport("Unsubscription failed"));
			e.printStackTrace();
			Assert.fail();
	}	
}	
	public void navigateAccontInformtion() throws Exception
	{
		String expectedResult="Navigation of Account information";
		try {
			Sync.waitElementPresent("xpath", "//a[contains(text(), 'Profile')]");
			Common.clickElement("xpath", "//a[contains(text(), 'Profile')]");
			
			Thread.sleep(3000);
			
			String s= Common.getText("xpath", "//span[contains(text(),'Profile')]");
			
			Thread.sleep(10000);
			
			System.out.println("My Account info page navigated "+s);
			Assert.assertEquals(s, "Profile");
			//report.addPassLog(expectedResult, "Navigation of Account information", "My Account Information Page navigated successfully", Common.getscreenShotPathforReport("Accountinformation page Success"));
		
		
		
		}catch(Exception |Error e)
		{
			//report.addFailedLog(expectedResult,"Navigation of Account information", "My Account Information Page navigated successfully", Common.getscreenShotPathforReport("Accountinformation page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
		
		public void changeAIName(String DataSet) throws Exception
		{
			String expectedResult="Should able to change Name for registered user";
			try {
				navigateAccontInformtion();
				
				Common.textBoxInput("name", "firstname", data.get(DataSet).get("FirstName"));
				Common.textBoxInput("name", "lastname", data.get(DataSet).get("LastName"));

				Thread.sleep(1000);

				Sync.waitElementPresent("xpath", "//button[@title='Save']");
				Common.clickElement("xpath", "//button[@title='Save']");

				Thread.sleep(3000);
				String s=Common.getText("xpath", "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");
				System.out.println(s);
				Assert.assertEquals(s, "You saved the account information.");
				
				Thread.sleep(6000);
				report.addPassLog(expectedResult,"Should able to change Name successfully", "Able to change Account Information successfully", Common.getscreenShotPathforReport("Changed Name Account Information Success"));
			}catch(Exception |Error e)
			{
				report.addFailedLog(expectedResult,"Should able to change Account Information successfully", "Not able to change Account Information successfully", Common.getscreenShotPathforReport("Changed Name Account Information failed"));
				e.printStackTrace();
				Assert.fail();
			}


	}


	public void changeAIEmail(String dataSet) throws Exception
	{

		String expectedResult="Navigating to Account Information for email for registered user";
		try {
			navigateAccontInformtion();
			
			Common.actionsKeyPress(Keys.PAGE_DOWN);
			Sync.waitElementPresent("name", "change_email");	
			Common.clickElement("name", "change_email");
			Thread.sleep(1000);

			Common.textBoxInput("name", "email", data.get(dataSet).get("Email"));
			Common.textBoxInput("name", "current_password", data.get(dataSet).get("Password"));

			Thread.sleep(1000);

			Sync.waitElementPresent("xpath", "//button[@title='Save']");
			Common.clickElement("xpath", "//button[@title='Save']");

			Thread.sleep(3000);
			String s=Common.getText("xpath", "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");
			System.out.println(s);
			Assert.assertEquals(s, "You saved the account information.");
			
			Thread.sleep(6000);
			report.addPassLog(expectedResult,"Should Navigate to Account Information for email successfully", "Navigated to Account Information for email successfully", Common.getscreenShotPathforReport("email Account Information Success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should Navigate to Account Information for email successfully", "Not Navigated to Account Information for email successfully", Common.getscreenShotPathforReport("email Account Information failed"));
			e.printStackTrace();
			Assert.fail();
		}
		
	}

	public void changeAIPassword(String dataSet) throws Exception
	{
		String expectedResult="Navigating to Account Information for password for registered user";
		try {
			
			navigateAccontInformtion();
			
			Common.actionsKeyPress(Keys.PAGE_DOWN);
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
			Assert.assertEquals(s, "You saved the account information.");
			
			Thread.sleep(6000);
			report.addPassLog(expectedResult,"Should Navigate to Account Information for password successfully", "Navigated to Account Information for password successfully", Common.getscreenShotPathforReport("Password Account Information Success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should Navigate to Account Information for password successfully", "Not Navigated to Account Information for password successfully", Common.getscreenShotPathforReport("password Account Information failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	
	public void Addressbook(String dataSet) throws Exception
	{
		String expectedResult="Should able to Add New Address for registered user";
		try {
			Sync.waitElementPresent("xpath", "//li[@class='nav item current']");
			Common.clickElement("xpath", "//li[@class='nav item current']");
			Thread.sleep(5000);
			Common.textBoxInput("name", "firstname", data.get(dataSet).get("FirstName"));
			Common.textBoxInput("name", "lastname", data.get(dataSet).get("LastName"));
			Common.textBoxInput("name" , "street[]" ,data.get(dataSet).get("steret Address"));
			Common.textBoxInput("name" , "city" ,data.get(dataSet).get("city"));			
			

			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "//button[@title='Save']");
			Common.clickElement("xpath", "//button[@title='Save']");
			Thread.sleep(5000);	
			report.addPassLog(expectedResult,"Should able to Add Address successfully", "Able to add address successfully", Common.getscreenShotPathforReport("Address Added Successfully"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should able to Add Address successfully", "Not able to Add Address successfully", Common.getscreenShotPathforReport("Add Address failed"));
			e.printStackTrace();
			Assert.fail();
		}
}

	
	public void navigateAddressbook(String DataSet) {
		String expectedResult="Should able to add New Address for registered user";
		try {
			Thread.sleep(3000);
			Sync.waitElementPresent("xpath", "//a[contains(text(), 'Address Book')]");
			Common.clickElement("xpath", "//a[contains(text(), 'Address Book')]");
			Thread.sleep(3000);
			Sync.waitElementPresent("xpath", "//button[@title='Add New Address']");
			Common.clickElement("xpath", "//button[@title='Add New Address']");
			
			
			Thread.sleep(5000);
			
			Common.textBoxInput("name", "firstname", data.get(DataSet).get("FirstName"));
			Common.textBoxInput("name", "lastname", data.get(DataSet).get("LastName"));
			//Common.textBoxInput("name", "company", data.get(DataSet).get("Company"));
			Common.textBoxInput("name", "telephone", data.get(DataSet).get("phone"));
			Common.textBoxInput("name", "street[]", data.get(DataSet).get("Street"));
			Common.textBoxInput("name", "city", data.get(DataSet).get("City"));

			Thread.sleep(1000);

			Sync.waitElementPresent("xpath", "//select[@title='State/Province']");
			Common.clickElement("xpath", "//select[@title='State/Province']");

			Thread.sleep(1000);

			Sync.waitElementPresent("xpath", "//select[@title='State/Province']");
			
			Common.dropdown("xpath", "//select[@title='State/Province']", SelectBy.TEXT, data.get(DataSet).get("Region"));


			Sync.waitElementPresent("name", "postcode");
			Common.textBoxInput("name", "postcode", data.get(DataSet).get("postcode"));

			Common.dropdown("xpath", "//select[@name='country_id']", SelectBy.TEXT, data.get(DataSet).get("Country"));

			Thread.sleep(500);

			Sync.waitElementPresent("xpath", "//button[@title='Save Address']");
			Common.clickElement("xpath", "//button[@title='Save Address']");
			
			Thread.sleep(3000);
			String s=Common.getText("xpath", "//div[@class='message-success success message']");
			Thread.sleep(3000);
			System.out.println(s);
			Thread.sleep(3000);
			
			report.addPassLog(expectedResult,"Should able to Add Address successfully", "Able to add address successfully", Common.getscreenShotPathforReport("Address Added Successfully"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should able to Add Address successfully", "Not able to Add Address successfully", Common.getscreenShotPathforReport("Add Address failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void Mouseover()throws Exception{
		   
		String expectedResult="navigate to Shop category page";

		try {
			Sync.waitElementPresent("xpath", "//span[contains(text() , 'Shop')]");
			Common.mouseOver("xpath" , "//span[contains(text() , 'Shop')]");
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "//a[contains(text(), 'Pitcher Filtration')]");
			Common.clickElement("xpath", "//a[contains(text(), 'Pitcher Filtration')]");
			Thread.sleep(4000);
			
			report.addPassLog(expectedResult,"Navigate to Shop category page successfully", "Landed on category page", Common.getscreenShotPathforReport("Shop category page Success"));
					}

		catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Navigate to Shop category page successfully", "Not Landed on category page", Common.getscreenShotPathforReport("Shop category page failed"));
			e.printStackTrace();
			Assert.fail();
	}	
}	
	
	public void Mouseoverlearn()throws Exception{
		   
		String expectedResult="Should lands on Learn page";

		try {
			Sync.waitElementPresent("xpath", "//span[contains(text() , 'Learn')]");
			
			Common.mouseOver("xpath" , "//span[contains(text() , 'Learn')]");
			Thread.sleep(4000);
			
			report.addPassLog(expectedResult,"Should lands on  Learn page successfully", "Landed on Learn page", Common.getscreenShotPathforReport("Learn page Success"));
					}

		catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should lands on Learn page successfully", "Not Landed on Learn page", Common.getscreenShotPathforReport("Learn page failed"));
			e.printStackTrace();
			Assert.fail();
	}	
}
	
	
	public void Mouseoversupport()throws Exception{
		   
		String expectedResult="Should lands on Support page";

		try {
			Sync.waitElementPresent("xpath", "//span[contains(text() , 'Support')]");
		
			Common.mouseOver("xpath" , "//span[contains(text() , 'Support')]");
			Thread.sleep(4000);
			
			report.addPassLog(expectedResult,"Should lands on  Support page successfully", "Landed on Support page", Common.getscreenShotPathforReport("Support page Success"));
					}

		catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should lands on Support page successfully", "Not Landed on Support page", Common.getscreenShotPathforReport("Support page failed"));
			e.printStackTrace();
			Assert.fail();
	}	
}
	
	
	
	public void categoryMenuItem()
	{
		String expectedResult="Select from  category" ;
		try {
			Thread.sleep(1000);
			Sync.waitElementPresent("xpath", "//img[@class='pagebuilder-mobile-hidden'][1]");
			Common.clickElement("xpath", "//img[@class='pagebuilder-mobile-hidden'][1]");
	
			Thread.sleep(1000);
			Sync.waitElementPresent("xpath", "(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[1]");
			Common.clickElement("xpath", "(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[1]");
			Thread.sleep(5000);
			report.addPassLog(expectedResult, "Should display item from  menucategory", "product display successfully", Common.getscreenShotPathforReport(" product display success"));

		}catch(Exception e)
		{
			report.addFailedLog(expectedResult,"Should display Search Results Page", "Search results Page not display", Common.getscreenShotPathforReport("Search result Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void Productselection() throws Exception
	{
		String expectedResult="Product Selection from Category";
		try {
			Thread.sleep(4000);		
			Sync.waitElementPresent("xpath", "//a[contains(text() ,'PUR PLUS 7 Cup Pitcher')]");
			Common.clickElement("xpath", "//a[contains(text() ,'PUR PLUS 7 Cup Pitcher')]");
			
			Thread.sleep(3000);
			Common.isElementDisplayed("xpath", "//span[@data-ui-id='page-title-wrapper']");
			report.addPassLog(expectedResult, "Should display item from menucategory", "Product Details Page display successfully", Common.getscreenShotPathforReport("product display successfully"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display item from menucategory", "Product details Page not displayed", Common.getscreenShotPathforReport("product display Failed"));
			Assert.fail();
		}

	}
	
	public void navigateMinicart() throws Exception
	{
		String expectedResult="Product adding to mini cart";
		try {
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "(//span[contains(text(), 'Add to Cart')])[1]");
			Common.clickElement("xpath", "(//span[contains(text(), 'Add to Cart')])[1]");
			Thread.sleep(5000);
			
			Sync.waitElementPresent("xpath", "//a[@class='action showcart']");
			Common.clickElement("xpath", "//a[@class='action showcart']");
			Thread.sleep(3000);

			
			report.addPassLog(expectedResult, "Should display Mini Cart Page", "Mini Cart Page display successfully", Common.getscreenShotPathforReport("Mini Cart Page display successfully"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Mini Cart Page", "Mini Cart Page not displayed", Common.getscreenShotPathforReport("Mini Cart Page display Failed"));
			e.printStackTrace();
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
			
			report.addPassLog(expectedResult, "Should display Checkout Page", "Checkout Page display successfully", Common.getscreenShotPathforReport("Checkout page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Checkout Page", "Checkout Page not displayed", Common.getscreenShotPathforReport("Checkout Failed"));
			Assert.fail();
		}
	}
	
	public void AddAddress() throws Exception
	{
		String expectedResult="Adding Address to shipping";
		try {
			Thread.sleep(4000);
			report.addPassLog(expectedResult, "Should display Shipping AddressPage", "Address Page display successfully", Common.getscreenShotPathforReport("Address page success"));
			Sync.waitElementPresent("xpath", "//span[contains(text(), 'Proceed to Review & Payment')]");
			Common.clickElement("xpath", "//span[contains(text(), 'Proceed to Review & Payment')]");
			//Common.clickElement("xpath", "//span[contains(text(), 'Next')]");
			Thread.sleep(5000);
			
			
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Mini Cart Page", "Mini Cart Page not displayed", Common.getscreenShotPathforReport("Mini Cart Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	
	public void searchProduct(String dataSet) throws Exception
	{
		String expectedResult="Search with Product name :"+data.get(dataSet).get("ProductName");
		try {
			Thread.sleep(1000);
			Common.clickElement("xpath", "//label[@class='label js-search-dropdown']");
			Sync.waitElementPresent("id", "search");
			try {
				Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName"));
				
			}catch(Exception e)
			{
				Common.clickElement("xpath", "//label[@class='label js-search-dropdown']");
				Thread.sleep(3000);
				Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName"));
			}
			Common.actionsKeyPress(Keys.ENTER);
			Thread.sleep(10000);
			Common.actionsKeyPress(Keys.DOWN);
			//Common.scrollIntoView("xpath", "(//div[@class='product-item-info']/div//a[@class='product photo product-item-photo title'])[1]");
			report.addPassLog(expectedResult, "Should display Search Results Page", "Search results Page display successfully", Common.getscreenShotPathforReport("Search results success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Search Results Page", "Search results Page not display", Common.getscreenShotPathforReport("Search result Failed"));
			e.printStackTrace();
			Assert.fail();
		}

	}
	
	public void zerosearchProduct(String dataSet) throws Exception
	{		
		String expectedResult="Search with Product name :"+data.get(dataSet).get("ProductName");
		try {
			Thread.sleep(1000);
			Common.clickElement("xpath", "//label[@class='label js-search-dropdown']");
			Sync.waitElementPresent("id", "search");
			try {
				Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName"));
				
			}catch(Exception e)
			{
				Common.clickElement("xpath", "//label[@class='label js-search-dropdown']");
				Thread.sleep(3000);
				Common.textBoxInput("id", "search", data.get(dataSet).get("ProductName"));
			}
			Common.actionsKeyPress(Keys.ENTER);
			Thread.sleep(10000);
			Common.actionsKeyPress(Keys.DOWN);
			
			report.addPassLog(expectedResult, "Should display Zero search results Page", "Zero search results Page display successfully", Common.getscreenShotPathforReport("Zero results page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Zero search results Page", "Zero search results Page not display", Common.getscreenShotPathforReport("Zearo results Failed"));
			Assert.fail();
		}

	}
	
	public void Addtocart() throws Exception
	{
		String expectedResult="Product adding to mini cart";
		try {
			Thread.sleep(4000);
			Sync.waitElementPresent("xpath", "(//img[@class='product-image-photo'])[1]");
			Common.clickElement("xpath", "(//img[@class='product-image-photo'])[1]");
			Thread.sleep(5000);
			Sync.waitElementPresent("xpath", "(//span[contains(text(), 'Add to Cart')])[1]");
			Common.clickElement("xpath", "(//span[contains(text(), 'Add to Cart')])[1]");
			Thread.sleep(8000);
			Sync.waitElementPresent("xpath", "//a[@class='action showcart']");
			Common.clickElement("xpath", "//a[@class='action showcart']");
			
			Thread.sleep(5000);

			/*Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"us_en/checkout/cart/']"));
			Common.isElementDisplayed("xpath", "//strong[@class='product-item-name']");*/
			report.addPassLog(expectedResult, "Should display Mini Cart Page", "Mini Cart Page display successfully", Common.getscreenShotPathforReport("Mini Cart page success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Mini Cart Page", "Mini Cart Page not displayed", Common.getscreenShotPathforReport("Mini Cart Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public void shipping_Address(String dataSet) throws Exception
	{
		
		String expectedResult="add the shipping Address";
		try {
			Thread.sleep(8000);
			
		//Common.textBoxInput("xpath","//input[@type='email']", data.get(dataSet).get("Email"));
		Common.textBoxInput("xpath","//input[@id='customer-email']", data.get(dataSet).get("Email"));
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

		Thread.sleep(500);

		Common.textBoxInput("name", "telephone",data.get(dataSet).get("phone"));
        Thread.sleep(3000);
        
        Sync.waitElementPresent("xpath", "//button[@class='button action continue primary']");
		Common.clickElement("xpath", "//button[@class='button action continue primary']");
		
		Thread.sleep(3000);
		
		
	report.addPassLog(expectedResult,"Should add the shipping Address", "Payment and review page  displayed", Common.getscreenShotPathforReport("Add Shipping Address Success page"));
       }catch(Exception |Error e)
		{
		report.addFailedLog(expectedResult,"Should add the shipping Address", "Payment and review page not displayed", Common.getscreenShotPathforReport("Payment and review page Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}

	
	public void forgotPasswordEmailtrigger(String dataSet) throws Exception
	{
		String expectedResult="Forgot Password email trigger for Registered User";
		Thread.sleep(2000);
		try {
			String expectedResult1="Landed on Login page";
			Sync.waitElementClickable(30, By.xpath("//a[@title='My Account']"));
			Common.findElement("xpath", "//a[@title='My Account']").click();
			report.addPassLog(expectedResult1, "Should display login page", "Login page displayed successfully", Common.getscreenShotPathforReport("Login page"));
			Sync.waitElementClickable(30, By.xpath("//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"uk_en/customer/account/forgotpassword/']")));
			Common.clickElement("xpath", "//a[@href='"+System.getProperty("url",automation_properties.getInstance().getProperty(automation_properties.BASEURL)+"uk_en/customer/account/forgotpassword/']"));
			int i=0;
			do{  
				Common.textBoxInput("id", "email_address", data.get(dataSet).get("Email"));
				Sync.waitElementClickable(30, By.xpath("//button[contains(text(),'Reset My Password')]"));
				Common.clickElement("xpath", "//button[contains(text(),'Reset My Password')]");
				Thread.sleep(4000);

				i++;
			}while(i<3 && !Common.isElementDisplayed("xpath", "//span[contains(text(),'Customer Login')]")); 
			Thread.sleep(5000);
			if(Common.isElementDisplayed("xpath", "//div[@data-bind='html: message.text']")) {
				String s=Common.getText("xpath", "//div[@data-bind='html: message.text']");
				Thread.sleep(4000);
				Assert.assertEquals(s, "If there is an account associated with "+data.get(dataSet).get("Email")+" you will receive an email with a link to reset your password.");
				Thread.sleep(4000);
			}
			HashMap<String, String> hm = Utilities.GmailAPI.getGmailData("\"Your Password Reset Request\"");
			Assert.assertTrue(hm.get("body").contains("set a new password"));
			Assert.assertTrue(hm.get("link").contains("createPassword"));
			//System.out.println(hm.get("body"));
			report.addPassLog(expectedResult, "Should display Forgot Password email trigger Success message", "Forgot Password email triggered successfully", Common.getscreenShotPathforReport("Forgot Password email trigger Success"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should display Forgot Password email trigger Success message", "Forgot Password email triggered  not successfully", Common.getscreenShotPathforReport("Forgot Password email trigger Failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}
	


	
	
		public void giftcard() throws Exception{
			String expectedResult="Navigating to Giftcard for registered user";
			try {
			
			Thread.sleep(3000);
		Sync.waitElementPresent("xpath", "//*[@id='block-collapsible-nav']/ul/li[10]");
		Common.clickElement("xpath", "//*[@id='block-collapsible-nav']/ul/li[10]");
		
		Thread.sleep(5000);
		
		report.addPassLog(expectedResult,"Should Navigate to Giftcard successfully", "Navigated to Giftcard successfully", Common.getscreenShotPathforReport("Giftcard Page passed"));
		}catch(Exception |Error e)
		{
			report.addFailedLog(expectedResult,"Should Navigate to GiftCard successfully", "Not Navigated to Giftcard page", Common.getscreenShotPathforReport("Giftcard failed"));
			e.printStackTrace();
			Assert.fail();
		}
	}

	

public void addPaymentDetails(String dataSet) throws Exception{
	
	String expectedResult="enter card details";
	try {
		
	Thread.sleep(5000);
	
	Common.switchFrames("xpath", "//iframe[@id='paymetric_xisecure_frame']");
	
	Thread.sleep(3000);
	Sync.waitElementPresent("xpath", "//select[@id='c-ct']");
	Common.dropdown("xpath", "//select[@id='c-ct']", Common.SelectBy.TEXT, data.get(dataSet).get("CardType"));
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
	//report.addPassLog(expectedResult," enter the card detailes","enterd Successfully", Common.getscreenShotPathforReport(" card details"));
	/*Thread.sleep(2000);
	Sync.waitElementPresent("xpath", "//span[contains(text(),'Place Order')]");
	//Common.clickElement("xpath", "//span[contains(text(),'Place Order')]");
	Common.javascriptclickElement("xpath", "//span[contains(text(),'Place Order')]");
	
	Thread.sleep(5000);
	
	String sucessMessage=Common.getText("xpath", "//span[contains(text(),'Thank you for your purchase!')]");

	System.out.println(sucessMessage);
	Assert.assertEquals(sucessMessage, "Thank you for your purchase!");*/
	//report.addPassLog(expectedResult, "Should display Order Success Page", "Order Success Page display successfully", Common.getscreenShotPathforReport("Order success page success"));

}catch(Exception |Error e)
{
	//report.addFailedLog(expectedResult, "enter the card details", "  not place the order successfully", Common.getscreenShotPathforReport("Invalid card details error"));
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

		Sync.waitElementPresent("xpath", "//span[contains(text(),'Place Order')]");
		//Common.clickElement("xpath", "//span[contains(text(),'Place Order')]");
		Common.javascriptclickElement("xpath", "//span[contains(text(),'Place Order')]");
		
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



public void forgotPassword(String dataSet) throws Exception
{
	String expectedResult="Forgot Password for Registered User";
	try
	{
		Sync.waitElementPresent("xpath", "//*[@id=\"login-form\"]/fieldset/div[5]/div[2]/a/span");
		Common.clickElement("xpath", "//*[@id=\"login-form\"]/fieldset/div[5]/div[2]/a/span");
		
		Thread.sleep(3000);
		Common.textBoxInput("id", "email_address", data.get(dataSet).get("Email"));
		Thread.sleep(3000);
		Sync.waitElementPresent("xpath", "//span[contains(text(),'Reset My Password')]");
		Common.clickElement("xpath", "//span[contains(text(),'Reset My Password')]");
		
		report.addPassLog(expectedResult, "Should display Forgot Password Succes message", "Forgot Password page success message displayed successfully", Common.getscreenShotPathforReport("Forgot Password text"));
		

	}catch(Exception |Error e)
	{
		report.addFailedLog(expectedResult,"Should display Forgot Password Succes message", "Forgot Password page success message not displayed", Common.getscreenShotPathforReport("Account Creation Failed"));
		Assert.fail();
	}
}

public void navigateMyOrder() throws Exception
{

String expectedResult="Navigating to My Order for registered user";
try{
	Sync.waitElementPresent("xpath", "//button[@class='action switch']");
	Common.clickElement("xpath", "//button[@class='action switch']");
	
	
	Thread.sleep(5000);
	
Sync.waitElementPresent("xpath", "//a[contains(text(), 'My Orders')]");
Common.clickElement("xpath", "//a[contains(text(), 'My Orders')]");

Thread.sleep(5000);

String heading=Common.getText("xpath", "//span[contains(text(),'My Orders')]");
System.out.println(heading);
Assert.assertEquals(heading, "My Orders");

Thread.sleep(4000);

String s=Common.getText("xpath", "//p[@class='toolbar-amount']");
System.out.println("No of orders :"+s);	
report.addPassLog(expectedResult,"Should Navigate to My order successfully", "Navigated to My Order successfully", Common.getscreenShotPathforReport("My orders passed"));
}catch(Exception |Error e)
{
report.addFailedLog(expectedResult,"Should Navigate to My order successfully", "Not Navigated to My orders page", Common.getscreenShotPathforReport("My Orders failed"));
e.printStackTrace();
Assert.fail();
}

}

public void navigateStoreCredit() throws Exception
{
	
	String expectedResult="Navigating to StoreCredit for registered user";
	try{
		Sync.waitElementPresent("xpath", "//a[@href='https://jetrails-stag.pur.com/storecredit/info/']");
		Common.clickElement("xpath", "//a[@href='https://jetrails-stag.pur.com/storecredit/info/']");
		
		Thread.sleep(6000);
		
		String heading=Common.getText("xpath", "//span[contains(text(),'Store Credit')]");
		System.out.println(heading);
		Thread.sleep(3000);
		Assert.assertEquals(heading, "Store Credit");
		report.addPassLog(expectedResult,"Should Navigate to Storecredit successfully", "Navigated to Storecredit successfully", Common.getscreenShotPathforReport("Storecredit passed"));
	}catch(Exception |Error e)
	{
		report.addFailedLog(expectedResult,"Should Navigate to Storecredit successfully", "Not Navigated to Storecredit page", Common.getscreenShotPathforReport("Storecredit failed"));
		e.printStackTrace();
		Assert.fail();
	}
}


public void myProductReviews() throws Exception
{
	
	String expectedResult="Navigating to ProductReviews for registered user";
	try{
		Sync.waitElementPresent("xpath", "//a[contains(text(), 'My Product Reviews')]");
		Common.clickElement("xpath", "//a[contains(text(), 'My Product Reviews')]");
		
		Thread.sleep(6000);
		
		String heading=Common.getText("xpath", "//span[contains(text(),'My Product Reviews')]");
		System.out.println(heading);
		Thread.sleep(3000);
		Assert.assertEquals(heading, "My Product Reviews");
		report.addPassLog(expectedResult,"Should Navigate to My Product Reviews successfully", "Navigated to My Product Reviews successfully", Common.getscreenShotPathforReport("My Product Reviews page passed"));
	}catch(Exception |Error e)
	{
		report.addFailedLog(expectedResult,"Should Navigate to My Product Reviews successfully", "Not Navigated to My Product Reviews page", Common.getscreenShotPathforReport("My Product Reviews failed"));
		e.printStackTrace();
		Assert.fail();
	}
}

public void orderbySKU(String dataSet) throws Exception
{
	
	String expectedResult="Navigating to Order by SKU for registered user";
	try{
		Sync.waitElementPresent("xpath", "//a[@href='https://jetrails-stag.pur.com/customer_order/sku/']");
		Common.clickElement("xpath", "//a[@href='https://jetrails-stag.pur.com/customer_order/sku/']");
		
		Thread.sleep(6000);
		
		String heading=Common.getText("xpath", "//span[contains(text(),'Order by SKU')]");
		System.out.println(heading);
		Thread.sleep(3000);
		Assert.assertEquals(heading, "Order by SKU");
		Thread.sleep(3000);
		
		Common.textBoxInput("id", "id-items0sku", data.get(dataSet).get("SKU"));
		Common.textBoxInput("id", "id-items0qty", data.get(dataSet).get("Quantity"));
		
		Thread.sleep(5000);
		Sync.waitElementPresent("xpath", "//button[@class='action tocart primary']");
		Common.clickElement("xpath", "//button[@class='action tocart primary']");
		Thread.sleep(3000);
		
		report.addPassLog(expectedResult,"Should Navigate to Order by SKU successfully", "Navigated to Order by SKU successfully", Common.getscreenShotPathforReport("Order by SKU placed Successfully"));
	}catch(Exception |Error e)
	{
		report.addFailedLog(expectedResult,"Should Navigate to Order by SKU successfully", "Not Navigated to Order by SKU page", Common.getscreenShotPathforReport("Order by SKU failed"));
		e.printStackTrace();
		Assert.fail();
	}
}

public void CategoryMenu()throws Exception{
	   
	String expectedResult="navigate to Shop category page";

	try {
		Sync.waitElementPresent("xpath", "//span[contains(text() , 'Shop')]");
		Common.mouseOver("xpath" , "//span[contains(text() , 'Shop')]");
		Thread.sleep(4000);
		Sync.waitElementPresent("xpath", "//a[contains(text(), 'Replacement Filters')]");
		Common.clickElement("xpath", "//a[contains(text(), 'Replacement Filters')]");
		Thread.sleep(4000);
		
		report.addPassLog(expectedResult,"Navigate to Shop category page successfully", "Landed on category page", Common.getscreenShotPathforReport("Shop category page Success"));
				}

	catch(Exception |Error e)
	{
		report.addFailedLog(expectedResult,"Navigate to Shop category page successfully", "Not Landed on category page", Common.getscreenShotPathforReport("Shop category page failed"));
		e.printStackTrace();
		Assert.fail();
}	
}
public void OutofStockProduct() throws Exception
{
	String expectedResult="Product Selection from Category";
	try {
		Thread.sleep(4000);		
		Sync.waitElementPresent("xpath", "//a[contains(text() ,'PUR Faucet Filter')]");
		Common.clickElement("xpath", "//a[contains(text() ,'PUR Faucet Filter')]");
		
		Thread.sleep(3000);
		Common.isElementDisplayed("xpath", "//span[@data-ui-id='page-title-wrapper']");
		report.addPassLog(expectedResult, "Should display item from menucategory", "Product Details Page display successfully", Common.getscreenShotPathforReport("product display successfully"));
	}catch(Exception |Error e)
	{
		report.addFailedLog(expectedResult,"Should display item from menucategory", "Product details Page not displayed", Common.getscreenShotPathforReport("product display Failed"));
		Assert.fail();
	}

}

public void ProductNotifyEmail(String DataSet) {
	String expectedResult="Out of stock Product from Category";
	
  try
  {
	  Thread.sleep(3000);
	  Common.actionsKeyPress(Keys.DOWN);
	  
	  String stock=Common.getText("xpath", "//h4[contains(text(), 'Temporarily out of stock')]");
       Assert.assertEquals(stock, "TEMPORARILY OUT OF STOCK");
	  
	  Thread.sleep(3000);
	  
	  Common.textBoxInput("name", "email", data.get(DataSet).get("Email"));
	  Thread.sleep(3000);
	  Sync.waitElementPresent("xpath", "//span[contains(text() ,'Notify')]");
		Common.clickElement("xpath", "//span[contains(text() ,'Notify')]");
		
		
		report.addPassLog(expectedResult, "Out of stock item from menucategory", "Notify me when product is available display successfully", Common.getscreenShotPathforReport("Email Subscribed successfully"));
	}catch(Exception |Error e)
	{
		report.addFailedLog(expectedResult,"Out of stock item from menucategory", "Product details Page not displayed", Common.getscreenShotPathforReport("Email Subscribtion Failed"));
		Assert.fail();
	}
	  
		  
  }

public void footerNewletterSubcription() throws Exception{
	String expectedResult="Newletter Subcription from footer";
	try {
		
		//Sync.waitElementPresent("xpath", "//div[@class='copyrights']");
		//Common.scrollIntoView("xpath", "//div[@class='copyrights']");
		Thread.sleep(4000);
		Sync.waitElementPresent("xpath", "//input[@id='newsletter']");
		Common.textBoxInput("name", "email", Utils.getEmailid());
		Thread.sleep(5000);
		Common.clickElement("xpath", "//button[@title='Subscribe']");
		Thread.sleep(6000);
		if(Common.isElementDisplayed("xpath", "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")) {
			String s=Common.getText("xpath", "//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']");
			System.out.println(s);
			Assert.assertEquals(s, "Thank you for your subscription.");
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


public void purSignin(String DataSet) throws Exception
{
	String expectedResult="PUR Signin";
	try {
		Thread.sleep(4000);		
		Sync.waitElementPresent("xpath", "//a[@class='header-content__right-link']");
		Common.clickElement("xpath", "//a[@class='header-content__right-link']");
		
		Thread.sleep(3000);
		Common.textBoxInput("name", "login[username]", data.get(DataSet).get("Email"));

		Common.textBoxInput("id", "pass", data.get(DataSet).get("Password"));
		Thread.sleep(3000);
		Sync.waitElementPresent("xpath","//button[@class='action login primary']" );
		Common.clickElement("xpath", "//button[@class='action login primary']");


		
		
		//report.addPassLog(expectedResult, "Should display item from menucategory", "Product Details Page display successfully", Common.getscreenShotPathforReport("product display successfully"));
	}catch(Exception |Error e)
	{
		//report.addFailedLog(expectedResult,"Should display item from menucategory", "Product details Page not displayed", Common.getscreenShotPathforReport("product display Failed"));
		Assert.fail();
		
	}
	

}

public void warrantyRegistration(String DataSet) {

String expectedResult="warrantyregistration";

 

       try {

 int home= Common.findElements("xpath", "//span[@class='icon-search action open']").size();
 System.out.println(home); 
 Common.assertionCheckwithReport(home>0, "verifying home page", "Home page contines Logo of product", "sucessfully lands on home page ", "faield to land on Home page");
 Thread.sleep(4000);
 Common.actionsKeyPress(Keys.END); 
 String footers=Common.getText("xpath", "//span[contains(text(),'Warranty Registration')]");
 System.out.println(footers); 
 Common.assertionCheckwithReport(footers.equals("Warranty Registration"), "Verifying footer page", "It should navigate to Warranty Registration", "successfully lands on Warranty Registration footer links ","Warranty Registration");
 Sync.waitElementPresent("xpath", "//span[contains(text(),'Warranty Registration')]"); 
 Thread.sleep(4000);

  Common.clickElement("xpath", "//span[contains(text(),'Warranty Registration')]");
  Sync.waitPageLoad();
  Thread.sleep(5000);
 

  String Title=Common.getPageTitle();
  System.out.println(Title);
  Common.assertionCheckwithReport(Title.equals("Product Registration"), "Verifying Product Registration page", "It should navigate to Product Registration page", "successfully lands on Product Registration page","Product Registration");
  
  Common.switchFrames("xpath", "//iframe[contains(@src,'custhelp')]");
  
  Common.textBoxInput("xpath", "//input[@name='Contact.Name.First']", data.get(DataSet).get("FirstName"));
  Common.textBoxInput("xpath", "//input[@name='Contact.Name.Last']", data.get(DataSet).get("LastName"));
  Common.textBoxInput("xpath", "//input[@name='Contact.Emails.PRIMARY.Address']", data.get(DataSet).get("Email"));
  Common.textBoxInput("id", "rn_TextInput_11_Contact.Address.Street", data.get(DataSet).get("Street"));
  Common.textBoxInput("xpath", " //input[@name='Contact.Address.City']", data.get(DataSet).get("City"));
  Sync.waitElementPresent("xpath", "//select[@name='Contact.Address.StateOrProvince']");

   Common.dropdown("xpath", "//select[@name='Contact.Address.StateOrProvince']", SelectBy.TEXT, data.get(DataSet).get("Region"));

  Common.textBoxInput("xpath", " (//input[@class='rn_Text'])[5]", data.get(DataSet).get("postcode"));
  Common.textBoxInput("xpath", " //input[@name='Contact.Phones.MOBILE.Number']", data.get(DataSet).get("phone"));
  Common.textBoxInput("xpath", "//input[@id='searchKeyword']", data.get(DataSet).get("ProductName"));
  Sync.waitElementClickable("xpath", "//div[@title='CR1100CAV2 (PUR 11 CUP PITCHER)']");
  Common.javascriptclickElement("xpath", "//div[@title='CR1100CAV2 (PUR 11 CUP PITCHER)']");
 
  Common.dropdown("xpath", "//select[@name='Asset.CustomFields.HOT.store_purchased']", SelectBy.TEXT, data.get(DataSet).get("vendorname"));
  Common.textBoxInput("xpath", "//input[@name='Asset.CustomFields.HOT.date_code']", data.get(DataSet).get("dataCode"));

  Sync.waitElementPresent("xpath", "//input[@placeholder='mm/dd/yyyy']");

  Common.textBoxInput("xpath", "//input[@placeholder='mm/dd/yyyy']", data.get(DataSet).get("date"));
  Common.clickElement("xpath", "//button[@class='rn_DisplayButton']");

  String submitted=Common.getText("xpath", "//h1[@style='font-size:16px;']");
  System.out.println(submitted);

  Common.assertionCheckwithReport(submitted.equals("Thank you for registering your product! Your request has been processed."), "Verifying Warranty Registration submitted page", "It should navigate to Warranty Registration  submitted page", "successfully lands on Warranty Registration  submitted page ","Warranty Registration  submitted page");

  report.addPassLog(expectedResult, "Should display Warrantay Registration page", "Warrantay Registration page display successfully", Common.getscreenShotPathforReport("Warrantay Registration page display successfully"));

}catch(Exception |Error e)

       

{

 

       e.printStackTrace();

 

       report.addFailedLog(expectedResult,"Should display Warranty Registration  submitted  page", "Warranty Registration  submitted Page not displayed", Common.getscreenShotPathforReport("Warranty Registration submitted page display Failed"));

 

Assert.fail();

 

 

}

 

 


}

public void contactUsPage(String DataSet) {

String expectedResult="contactUsPage";

       

       try {

 

 

int home= Common.findElements("xpath", "//span[@class='icon-search action open']").size();

 

System.out.println(home);

 

Common.assertionCheckwithReport(home>0, "verifying home page", "Home page contines Logo of product", "sucessfully lands on home page ", "faield to land on Home page");

 

Thread.sleep(4000);

 

Common.actionsKeyPress(Keys.END);

String footers=Common.getText("xpath", "//span[contains(text(),'Contact Us')]");

 

System.out.println(footers);

 

Common.assertionCheckwithReport(footers.equals("Contact Us"), "Verifying footer page", "It should navigate to footer links", "successfully lands on Contact Us footer links ","footerlinks");

 

 

Sync.waitElementPresent("xpath", "//span[contains(text(),'Contact Us')]");
   Thread.sleep(4000);

 Common.clickElement("xpath", "//span[contains(text(),'Contact Us')]");
   Sync.waitPageLoad();
   Thread.sleep(5000);

 
String Title=Common.getPageTitle();

 System.out.println(Title);
 Common.assertionCheckwithReport(Title.equals("Contact Us"), "Verifying Contact Us page", "It should navigate to Contact Us page", "successfully lands on Contact Us page","Contact Us");
 Common.switchFrames("xpath", "//iframe[contains(@src,'custhelp')]");

 Common.textBoxInput("xpath", "//input[@name='Contact.Name.First']", data.get(DataSet).get("FirstName"));
 Common.textBoxInput("xpath", "//input[@id='rn_TextInput_5_Contact.Name.Last']", data.get(DataSet).get("LastName"));
 Common.textBoxInput("xpath", "//input[@name='Contact.CustomFields.c.company']", data.get(DataSet).get("Company"));
 Common.textBoxInput("xpath", "//input[@name='Contact.Phones.MOBILE.Number']", data.get(DataSet).get("Primary"));
 Common.textBoxInput("xpath", "//input[@name='Contact.Emails.PRIMARY.Address']", data.get(DataSet).get("Email"));
 Common.textBoxInput("xpath", "//input[@name='Contact.Address.PostalCode']", data.get(DataSet).get("postcode"));

 Sync.waitElementPresent("xpath", "//select[@name='Contact.Address.StateOrProvince']");
 Common.dropdown("xpath", "//select[@name='Contact.Address.StateOrProvince']", SelectBy.TEXT, data.get(DataSet).get("Region"));
 Common.textBoxInput("xpath", "//input[@name='Contact.Address.City']", data.get(DataSet).get("City"));
 Common.textBoxInput("xpath", "//input[@name='Contact.Address.Street']", data.get(DataSet).get("Street"));
 Common.textBoxInput("xpath", "//input[@name='Incident.CustomFields.c.ordernumber']", data.get(DataSet).get("OrderNumber"));
 Common.textBoxInput("xpath", "//input[@id='searchKeyword']", data.get(DataSet).get("ProductName"));
 Sync.waitElementClickable("xpath", "//div[@title='CR1100CAV2 (PUR 11 CUP PITCHER)']");
 Common.javascriptclickElement("xpath", "//div[@title='CR1100CAV2 (PUR 11 CUP PITCHER)']");

 Sync.waitElementPresent("xpath", "(//button[@class='rn_DisplayButton'])[1]");
 Common.findElement("xpath", "(//button[@class='rn_DisplayButton'])[1]").click();
 Thread.sleep(5000);
 Common.javascriptclickElement("xpath", "//a[contains(text(), 'Order Issues')]");
 Sync.waitElementPresent("xpath", "//textarea[@id='rn_TextInput_30_Incident.Threads']");
 Common.textBoxInput("xpath", "//textarea[@id='rn_TextInput_30_Incident.Threads']", data.get(DataSet).get("Message"));

 Sync.waitElementPresent("xpath", "//button[contains(text(),'Submit')]");
 Common.javascriptclickElement("xpath", "//button[contains(text(),'Submit')]");

 Thread.sleep(6000);
 Common.actionsKeyPress(Keys.PAGE_UP);

 Common.actionsKeyPress(Keys.PAGE_UP);

 String submitted=Common.getText("xpath", "//h1[@style='font-size:16px;']");

 System.out.println();

 Common.assertionCheckwithReport(submitted.equals("Your question has been submitted!"), "Verifying Contact Us submitted page", "It should navigate to Contact Us submitted page", "successfully lands on Contact Us submitted page ","Contact Us submitted page");

report.addPassLog(expectedResult, "Should display contact Us submitted page", "Contact Us submitted Page display successfully", Common.getscreenShotPathforReport("Contact us submitted page display successfully"));
  }catch(Exception |Error e)


  {

  e.printStackTrace();
  report.addFailedLog(expectedResult,"Should display contact Us submitted  page", "contact us submitted Page not displayed", Common.getscreenShotPathforReport("Contact us submitted page display Failed"));
  Assert.fail();

  }

}

}

       

 








