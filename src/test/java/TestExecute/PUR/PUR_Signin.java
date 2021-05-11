package TestExecute.PUR;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.PUR.PurHelper;
import TestLib.Common;
import TestLib.Login;

public class PUR_Signin {
	String datafile = "PUR//PUR_TestData.xlsx";	
	PurHelper PUR=new PurHelper(datafile);
	
	@Test(priority=1)
	public void CreateAccount(){

		try{
			
			
			PUR.purSignin("CustomerAccountdetails");
			PUR.searchProduct("productName");
			PUR.Addtocart();
			
			
			
			
		}
		catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}
	
	
	/*@BeforeMethod
	  public void startTest() throws Exception {
		System.setProperty("configFile", "PUR\\config.properties");
		  Login.signIn("chrome");
		   }*/
	
	/*@BeforeMethod
	@Parameters({"browser"}) 
	  public void startTest(String browser) throws Exception {
		System.setProperty("configFile", "PUR\\config.properties");
		  Login.signIn(browser);
	}*/
	@BeforeMethod
	  public void startTest() throws Exception {
		System.setProperty("configFile", "PUR//config.properties");
		  Login.signIn("firefox");
		   }
	
	  

	@AfterTest
	public void clearBrowser()
	{
		//Common.closeAll();

	}

}

