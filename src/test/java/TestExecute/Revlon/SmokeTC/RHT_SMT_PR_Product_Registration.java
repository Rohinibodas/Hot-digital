package TestExecute.Revlon.SmokeTC;

import org.testng.annotations.Test;

import TestComponent.revlon.RevelonHelper;
import TestLib.Common;
import TestLib.Login;

import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class RHT_SMT_PR_Product_Registration {
	String datafile = "revlon//RevlonTestData.xlsx";	
	RevelonHelper revelon=new RevelonHelper(datafile);
	
	@Test(priority=1)
	public void NavigationProductRegistration() throws Exception {

		try {
			revelon.acceptPrivecy();
			revelon.navigateProductRegistration();
		}
		catch (Exception e) {
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	@Test(dependsOnMethods="NavigationProductRegistration")
	  public void ProductRegistrationForm(){
		  
		  try{
			  revelon.ProductRegistration();
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
