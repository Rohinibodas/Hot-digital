package TestExecute.RevlonUS;

import org.testng.annotations.Test;

import TestComponent.RevlonUS.RevelonHelper;
import TestLib.Common;
import TestLib.Login;

import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class RHT_MT_NLS_Newsletter_Subscription {
	String datafile = "revlon//RevlonTestData.xlsx";	
	RevelonHelper revelon=new RevelonHelper(datafile);
	
	@Test(priority=1)
	public void loginApplication() throws Exception {

		try {
			revelon.loginRevlon("AccountDetails");
		}
		catch (Exception e) {
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	@Test(dependsOnMethods="loginApplication")
	  public void NewsletterSubscription(){
		  
		  try{
			  revelon.navigateNewsletterSubscription();
		  }
		  catch (Exception e) {
					
				Assert.fail(e.getMessage(), e);
			}
	  }
	
	@BeforeMethod
	//@Parameters({"browser"})  
	  public void startTest() throws Exception {
		System.setProperty("configFile", "Revelon\\config.properties");
		  Login.signIn("browser");
		  
	  }
	
	@AfterTest
	public void clearBrowser()
	{
		
		Common.closeAll();

	}
}
