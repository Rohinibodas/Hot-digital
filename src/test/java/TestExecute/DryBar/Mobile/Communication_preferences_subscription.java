package TestExecute.DryBar.Mobile;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestComponent.DryBar.DryBarMobile;
import TestLib.Login;

public class Communication_preferences_subscription {
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarMobile drybar=new DryBarMobile(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void My_Account_Navigations() throws Exception {

	  try{
		  drybar.Aggree_and_proceed();
		  drybar.clickMyaccount();
		  drybar.loginApplication("AccountDetails");
		  drybar.Communication_Preferences(); 
		  drybar.Subscribe_To_CP();
		  drybar.Communication_Preferences();
		  drybar.UnSubscribe_To_CP();
  }
	  catch (Exception e) {
			e.printStackTrace();
			
			Assert.fail(e.getMessage(), e);
		} 
	}
  
	
  
  @AfterTest
	public void clearBrowser()
	{
		//Common.closeAll();

	}
  @BeforeMethod
  public void startTest() throws Exception {
	 System.setProperty("configFile", "DryBar\\config.properties");
	 Login.signIn("chrome","iPad");
 }
}
 
/*@BeforeTest
@Parameters({"device"})  
  public void startTest(String Device) throws Exception {
	System.setProperty("configFile", "DryBar\\config.properties");
	Login.signIn("chrome",Device);
  }
}*/
