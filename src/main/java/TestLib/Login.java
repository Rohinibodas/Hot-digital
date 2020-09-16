package TestLib;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Login {
	static String browserName = "GC";
	static Automation_properties automation_properties = Automation_properties.getInstance();
	static WebDriver driver;

	
	public static void signIn(String browser) throws Exception
	{
			browserName = browser;
			driver=BaseDriver.StartBrowser(browser, automation_properties.BASEURL);
			Driver.getLogger().info("Logged in with User: ");
		
	}
	
	public static void signIn() throws Exception
	{
		
			driver=BaseDriver.StartBrowser(automation_properties.getInstance().getProperty(automation_properties.BROWSER),automation_properties.getInstance().getProperty(automation_properties.BASEURL));
			Driver.getLogger().info("Logged in with User: ");
		
	}
	
	

	public static void logginIn(String username,String password) throws Exception { 
		Common.textBoxInput("id","strUserLogin",username);
		Common.textBoxInput("id","strUserPwd",password);
		Common.clickElement("id", "btnLogin");
	}

	
	}
