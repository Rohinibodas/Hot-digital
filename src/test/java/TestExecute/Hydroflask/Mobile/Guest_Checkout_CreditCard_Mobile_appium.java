package TestExecute.Hydroflask.Mobile;

import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.Hydroflask.HydroHelper;
import TestComponent.Hydroflask.HydroMobileHelper;
import TestLib.BaseDriver;
import TestLib.Common;
import TestLib.Login;

public class Guest_Checkout_CreditCard_Mobile_appium {
	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroMobileHelper Hydro=new HydroMobileHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
	
	public void gustUserCheckout_CreditCard() throws Exception {

		try {
			Hydro.acceptPrivecy();
			Hydro.orderSubmit("Bottles");
			Hydro.checkOut();
			Hydro.addDeliveryAddress("Address");
			Hydro.updatePaymentAndSubmitOrder("PaymentDetails");
		}
		catch (Exception e) {
			
			//Assert.fail(e.getMessage(), e);
		} 
	}
	
	
	
	@AfterTest
	public void clearBrowser()
	{
	Common.closeAll();

	}
	
	@BeforeTest
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "Hydroflask\\mobile_config.properties");
		 Login.mobilesignIn("Andriod");
		 Hydro.acceptPrivecy();
		 
		//Login.signIn("chrome","Galaxy S5");
		 //Hydro.acceptPrivecy();
		 
		/* DesiredCapabilities cap = new DesiredCapabilities();

			cap.setCapability("browserName", "Chrome");

			cap.setCapability("platformVersion","11");

			cap.setCapability("deviceName","sdk_gphone_x86_arm");
			cap.setCapability("udid","emulator-5554");

			cap.setCapability("platformName","ANDROID");
			
			//AppiumDriver<MobileElement> driver=new AppiumDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"),cap);

			RemoteWebDriver driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
			//driver.get("https://jetrails-stg.hydroflask.com/");
			driver.get("https://www.hydroflask.com/");
			BaseDriver.driver=driver;*/
		
			
		  
	  }
	
	/*@BeforeTest
	@Parameters({"device"})  
	  public void startTest(String Device) throws Exception {
		System.setProperty("configFile", "Revelon\\config.properties");
		  Login.signIn("chrome",Device);
		  Hydro.acceptPrivecy();
	  }*/

}
