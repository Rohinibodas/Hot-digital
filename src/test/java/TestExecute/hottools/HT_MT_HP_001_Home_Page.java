package TestExecute.hottools;

import org.testng.annotations.Test;

import TestComponent.Hottools.HottoolsHelpr;
import TestLib.Common;
import TestLib.Login;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class HT_MT_HP_001_Home_Page {
	String datafile = "Hottools//HottoolsTestData.xlsx";	
	HottoolsHelpr Hottools=new HottoolsHelpr(datafile);


	@Test(priority=1)
	  public void SignIn(){
		  
		  try{
			  Hottools.singin("RetailCustomerAccountDetails");
		  }
		  catch (Exception e) {
				
				Assert.fail(e.getMessage(), e);
			}
	  }
	
	@Test(dependsOnMethods="SignIn")
	  public void Homepage(){
		  
		  try{
			  Hottools.homePage("homePage");
		  }
		  catch (Exception e) {
					
				Assert.fail(e.getMessage(), e);
			}
	  }
	  



	@BeforeMethod
	@Parameters({"browser"})  

	public void startTest() throws Exception {
		System.setProperty("configFile", "Hottools\\config.properties");
		Login.signIn();

	}
	
	@AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}

}
