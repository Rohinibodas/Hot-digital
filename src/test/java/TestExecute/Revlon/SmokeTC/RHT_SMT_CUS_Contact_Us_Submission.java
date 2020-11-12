package TestExecute.Revlon.SmokeTC;

import org.testng.annotations.Test;

import TestComponent.revlon.RevelonHelper;
import TestLib.Common;
import TestLib.Login;

import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class RHT_SMT_CUS_Contact_Us_Submission {
	String datafile = "revlon//RevlonTestData.xlsx";	
	RevelonHelper revelon=new RevelonHelper(datafile);
	
	@Test(priority=1)
	public void NavigationContactUs() throws Exception {

		try {
			revelon.acceptPrivecy();
			revelon.navigateContactUs();
		}
		catch (Exception e) {
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	@Test(dependsOnMethods="NavigationContactUs")
	  public void Contactuspage(){
		  
		  try{
			  revelon.contactus();
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
	
	@AfterTest
	public void clearBrowser()
	{
		
		Common.closeAll();

	}

}
