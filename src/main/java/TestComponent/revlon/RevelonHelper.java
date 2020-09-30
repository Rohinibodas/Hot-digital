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

import TestLib.BaseDriver;
import TestLib.Common;
import TestLib.Sync;
import Utilities.ExcelReader;

import com.asprise.ocr.Ocr;


public class RevelonHelper {
	
	String datafile;
	ExcelReader excelData;
	Map<String, Map<String, String>> data=new HashMap<>();

	public void navigateAccount()
	{
		//System.out.println(data.get("LoginDetails"));
		Sync.waitElementClickable(30, By.xpath("//a[@title='My Account']"));
		Common.findElement("xpath", "//a[@title='My Account']").click();
		Sync.waitElementClickable(30, By.xpath("//button[text()='Create an Account']"));
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
		
		
		/* String imageUrl=Common.findElement("className", "captcha-img").getAttribute("src");
		 System.out.println("Image source path : \n"+ imageUrl);
		 Common.openNewTab();
		 Common.getDriver().get(imageUrl);
		 byte[] arrScreen = ((TakesScreenshot) BaseDriver.getDriver()).getScreenshotAs(OutputType.BYTES);
		 BufferedImage imageScreen = ImageIO.read(new ByteArrayInputStream(arrScreen));
		 WebElement cap =Common.findElement("tagName","img");
		 org.openqa.selenium.Dimension capDimension = cap.getSize();
		 org.openqa.selenium.Point capLocation = cap.getLocation();
		 BufferedImage imgCap = imageScreen.getSubimage(capLocation.x, capLocation.y, capDimension.width, capDimension.height);
		 ByteArrayOutputStream os = new ByteArrayOutputStream();
		 ImageIO.write(imgCap, "png", os);
		 SocketClient client = new SocketClient("user", "password");
		 Captcha res = client.decode(new ByteArrayInputStream(os.toByteArray()));*/
		
	}	
	
	
	public void searchProduct(String productName) throws Exception
	{
		Common.clickElement("xpath", "//div[@class='block block-search search-visible-md minisearch-v2']/div/i");
		Sync.waitElementPresent("id", "search");
		Common.textBoxInput("id", "search", productName);
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
		Common.clickElement("xpath", "//button[text()='Sign In']");
		Thread.sleep(3000);
		
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
		Common.textBoxInput("name", "street[0]", data.get(dataSet).get("City"));
		Thread.sleep(2000);
		Common.actionsKeyPress(Keys.SPACE);
		Thread.sleep(3000);
		Common.clickElement("xpath", "//*[@id=\"shipping-new-address-form\"]/fieldset/div/div[1]/div/ul/li[1]/a");
	}
	
	public  RevelonHelper(String datafile)
	{
		excelData=new ExcelReader(datafile);
		data=excelData.getExcelValue();
		this.data=data;
	}

}
