package TestComponent.revlon;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import TestLib.BaseDriver;
import TestLib.Common;
import TestLib.Sync;
import Utilities.ExcelReader;

import com.asprise.ocr.Ocr;


public class RevelonHelper {
	
	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data=new HashMap<>();

	public void navigateAccount() throws InterruptedException
	{
		//System.out.println(data.get("LoginDetails"));
		Sync.waitElementClickable(30, By.xpath("//a[@title='My Account']"));
		Common.findElement("xpath", "//a[@title='My Account']").click();
		Sync.waitElementClickable(30, By.xpath("//button[text()='Create an Account']"));
		Thread.sleep(4000);
	}	
	
	public void CreateNewAccount(String dataSet) throws Exception
	{
		
		navigateAccount();
		Common.clickElement("xpath", "//button[text()='Create an Account']");
		Thread.sleep(3000);
		Common.textBoxInput("id", "firstname", data.get(dataSet).get("FirstName").toString());
		Common.textBoxInput("id", "lastname", data.get(dataSet).get("LastName").toString());
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Common.textBoxInput("id", "email_address", data.get(dataSet).get("Email").toString());
		Common.textBoxInput("id", "password", data.get(dataSet).get("Password").toString());
		Common.textBoxInput("id", "password-confirmation", data.get(dataSet).get("Password").toString());
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Thread.sleep(4000);
		Common.clickElement("id", "captcha_user_create");
		Sync.waitElementVisible("className", "captcha-img");
		Common.clickElement("xpath", "//button[@title='Create an Account']");
		
		
	}	
	
	
	public void searchProduct(String productName) throws Exception
	{
		Common.clickElement("xpath", "//div[@class='block block-search search-visible-md minisearch-v2']/div/i");
		Sync.waitElementPresent("id", "search");
		try {
		Common.textBoxInput("id", "search", productName);
		}catch(Exception e)
		{
			Common.clickElement("xpath", "//div[@class='block block-search search-visible-md minisearch-v2']/div/i");
			Thread.sleep(2000);
			Common.textBoxInput("id", "search", productName);
		}
		Common.actionsKeyPress(Keys.ENTER);
		Thread.sleep(4000);
		Common.actionsKeyPress(Keys.PAGE_DOWN);
		Thread.sleep(2000);
		Common.clickElement("xpath", "//div[@class='slick-track']/div[1]/following::form[1]");
		Thread.sleep(4000);
	}
	
	public void loginRevlon(String dataSet) throws Exception
	{
		
		navigateAccount();
		Common.textBoxInput("id", "email", data.get(dataSet).get("Email"));
		Common.textBoxInput("id", "pass", data.get(dataSet).get("Password"));
		Common.actionsKeyPress(Keys.ARROW_DOWN);
		Thread.sleep(2000);
		//Common.mouseOver("xpath", "//button[text()='Sign In']");
		Common.clickElement("xpath", "//button[text()='Sign In']");
		Thread.sleep(5000);
		
	}
	
	public void proccedToCheckout() throws InterruptedException
	{
		Common.clickElement("xpath", "//a[@class='action viewcart hvr-sweep-to-right rev-checkout-btn rev-cart-view']");
		//Common.clickElement("xpath", "//a[@class='action showcart']");
		Thread.sleep(3000);
		Common.clickElement("xpath", "//button[text()=' Checkout']");
		Thread.sleep(4000);
	}
	
	public void addShippingAddress(String dataSet) throws Exception
	{	
		try {
		Common.textBoxInput("name", "street[0]", data.get(dataSet).get("City"));
		}catch (Exception e) {
			if(Common.findElements("xpath","//div[@class='shipping-address-item selected-item']").size()>0)
			{
				Common.actionsKeyPress(Keys.PAGE_DOWN);
				Common.clickElement("xpath", "//input[@name='shipping_method']");
				Common.clickElement("xpath", "//button[@class='rev-w-btn rev-def-btn next-btn shipping-next']");
				return;
			}
			else {
			proccedToCheckout();
			if(Common.findElements("xpath","//div[@class='shipping-address-item selected-item']").size()>0)
			{
				Common.actionsKeyPress(Keys.PAGE_DOWN);
				Common.clickCheckBox("xpath", "//input[@name='shipping_method']");
				Common.clickElement("xpath", "//button[@class='rev-w-btn rev-def-btn next-btn shipping-next']");
				return;
			}else {
			Common.textBoxInput("name", "street[0]", data.get(dataSet).get("City"));}
			}
		}
		Thread.sleep(2000);
		Common.actionsKeyPress(Keys.SPACE);
		Thread.sleep(3000);
		Common.clickElement("xpath", "//*[@id=\"shipping-new-address-form\"]/fieldset/div/div[1]/div/ul/li[1]/a");
		Common.textBoxInput("xpath", "//input[@name='telephone']", "9898989898");
		Thread.sleep(2000);
		Common.clickElement("xpath", "//button[@class='rev-w-btn rev-def-btn next-btn shipping-next']");
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
				Common.actionsKeyPress(Keys.ARROW_DOWN);
				Common.switchToDefault();
				Thread.sleep(1000);
				Common.clickElement("xpath", "//button[@title='Place Order']");
				
		
		
	}
	
	public void updatePaymentAndSubmitOrder(String dataSet) throws Exception
	{
		addPaymentDetails(dataSet);

		if(Common.findElements("xpath", "//div[@class='message message-error']").size()>0)
		{	
			addPaymentDetails(dataSet);
		}
			String sucessMessage=Common.getText("xpath", "//h1[@class='page-title']").trim();
			Assert.assertEquals(sucessMessage, "THANK YOU FOR YOUR PURCHASE");
			
		
	}

	
	public void acceptPrivecy()
	{
		Common.clickElementStale("xpath", "//button[text()='AGREE & PROCEED']");
	}
	public  RevelonHelper(String datafile)
	{
		excelData=new ExcelReader(datafile);
		data=excelData.getExcelValue();
		this.data=data;
	}

}
