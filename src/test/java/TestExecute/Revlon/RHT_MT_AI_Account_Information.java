package TestExecute.Revlon;

import org.testng.annotations.Test;

import TestComponent.revlon.RevelonHelper;
import TestLib.Common;
import TestLib.Login;

import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class RHT_MT_AI_Account_Information {
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
	  public void Changeusername(){
		  
		  try{
			  revelon.navigateAccountInformation();
			  revelon.changeAIName("AccountDetails");
		  }
		  catch (Exception e) {
					
				Assert.fail(e.getMessage(), e);
			}
	  }
	
	@Test(dependsOnMethods="Changeusername")
	  public void Changeuseremail(){
		  
		  try{
			  revelon.navigateAccountInformation();
			  revelon.changeAIEmail("AccountDetails");;
		  }
		  catch (Exception e) {
					
				Assert.fail(e.getMessage(), e);
			}
	  }
	
	@Test(dependsOnMethods="Changeuseremail")
	  public void Changeuserpassword(){
		  
		  try{
			  revelon.navigateAccountInformation();
			  revelon.changeAIPassword("AccountDetails");;
		  }
		  catch (Exception e) {
					
				Assert.fail(e.getMessage(), e);
			}
	  }
	
	@BeforeMethod
	//@Parameters({"browser"})  
	  public void startTest() throws Exception {
		System.setProperty("configFile", "Revelon\\config.properties");
		  Login.signIn("firefox");
		  
	  }
	
	/*@AfterTest
	public void clearBrowser()
	{
		
		Common.closeAll();

	}*/

}
