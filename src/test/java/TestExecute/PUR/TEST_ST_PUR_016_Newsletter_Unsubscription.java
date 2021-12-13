package TestExecute.PUR;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.PUR.PurHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_PUR_016_Newsletter_Unsubscription {
	
	String datafile = "PUR//PUR_TestData.xlsx";	
	PurHelper PUR=new PurHelper(datafile);
	
	@Test(priority=1)
	public void newsletterUnsubscription(){

		try{
			
			//PUR.ClicktheSignbutton();
			PUR.singin("CustomerAccountdetails");
			PUR.newsletterUnsubscription();
			
		}
		catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}
	
	
	/*@BeforeMethod
	@Parameters({"browser"})  
	  public void startTest() throws Exception {
		System.setProperty("configFile", "PUR\\config.properties");
		  Login.signIn("chrome");
		   }*/
	
	@BeforeMethod
	//@Parameters() 
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "PUR\\Config_PUR_Staging.properties");
		  Login.signIn();
	}


	@AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}

}
