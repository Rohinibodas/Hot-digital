package TestComponent.Hydroflask;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;

import TestLib.Common;
import TestLib.Sync;
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
		Thread.sleep(3000);
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
		Common.dropdown("name", "region_id", Common.SelectBy.TEXT, data.get(dataSet).get("Region"));
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
		Common.clickElement("xpath", "//button[@title='Place Order']");
		String sucessMessage=Common.getText("xpath", "//h1[@class='checkout-success-title']").trim();
		Assert.assertEquals(sucessMessage, "Your order has been received","Sucess message validations");
		
	}


	public  HydroHelper(String datafile)
	{
		excelData=new ExcelReader(datafile);
		data=excelData.getExcelValue();
		this.data=data;
	}
}
