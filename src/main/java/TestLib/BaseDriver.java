package TestLib;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.logging.Level;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;


public class BaseDriver 
{

	public static WebDriver driver;
	public static String className = Common.getCLassName();
		public static WebDriver StartBrowser(String browserName,String URL) throws InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
		{
	
			 if(browserName.equalsIgnoreCase("firefox"))
			{
				WebDriverManager.firefoxdriver().setup();
				 /*DesiredCapabilities cap = new DesiredCapabilities();
				 cap.setJavascriptEnabled(true);
				 FirefoxOptions  opt = new FirefoxOptions();*/
				driver=new FirefoxDriver();
				
			}
			else if(browserName.equalsIgnoreCase("safari"))
			{
				DriverManagerType safari = DriverManagerType.SAFARI;
				WebDriverManager.getInstance(safari).setup();
				Class<?> safariClass =  Class.forName(safari.browserClass());
				driver = (WebDriver) safariClass.getDeclaredConstructor().newInstance();
			}

			
			
			else if(browserName.equalsIgnoreCase("chrome"))
			{
				WebDriverManager.chromedriver().setup();
				String 	downloadFilepath = System.getProperty("user.dir")+"//TestLogs//Download//";
				File dlDirectory = new File(downloadFilepath);
				dlDirectory.mkdir();
				if (!dlDirectory.exists()) {
					dlDirectory.mkdir();
				}else{
					File[] files = dlDirectory.listFiles();
					if(files!=null)
					{
						for (File f : files)
						{
							if(f.delete())
								{
									System.out.println(f.getName()+"  file deleted  "+f.getAbsolutePath());
									Driver.getLogger().info(f.getName()+"  file deleted  "+f.getAbsolutePath());
								}
								else
								{
									System.out.println("Unable to delete file.....");
								}
							}
						}
					
				}
			
			// Save Chrome Preferences in Hash Map
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			HashMap<String, Object> contentsetting = new HashMap<String, Object>();
			chromePrefs.put("download.default_directory", downloadFilepath);
			chromePrefs.put("download.default_directory", downloadFilepath);
			contentsetting.put("multiple-automatic-downloads", 1);
			chromePrefs.put("download.prompt_for_download", "false");
			chromePrefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1);
			chromePrefs.put("credentials_enable_service", false);
			chromePrefs.put("password_manager_enabled", false);
			// Save Chrome Opions
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--no-sandbox");
			options.addArguments("-disable-dev-shm-usage");
			options.addArguments("--test-type");
			options.addArguments("--enable-video-player-chromecast-support");
			options.setExperimentalOption("useAutomationExtension", false);
			options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			options.addArguments("disable-infobars");
			options.addArguments("--disable-notifications");
			options.addArguments("--disable-popup-blocking");
			//options.setCapability("chrome.switches", Arrays.asList("--disable-local-storage"));
			//options.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
			// cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			//options.setCapability(ChromeOptions.CAPABILITY, options);
			//options.setCapability("--enable-video-player-chromecast-support", true);
			LoggingPreferences logPrefschrome = new LoggingPreferences();
			logPrefschrome.enable(LogType.BROWSER, Level.ALL);
			//options.setCapability(CapabilityType.LOGGING_PREFS, logPrefschrome);
			options.setExperimentalOption("prefs", chromePrefs);
			driver = new ChromeDriver(options);
			Driver.getLogger().info("Chrome Driver Stared Successfully");
			}
			else{
				WebDriverManager.chromedriver().setup();
				driver=new ChromeDriver();
			}
			Thread.sleep(3000);
			System.out.println("URL is=====?"+URL);
			driver.get(URL);
			driver.manage().window().maximize();
			return driver;
			
		}

		
		public static void closeBrowser()
		{
			driver.quit();
		}
		
		public static WebDriver getDriver()
		{
			return driver;
			
		}
		
		
		
}
