package TestExecute.hottools;

import org.testng.annotations.Test;

import TestComponent.Hottools.HottoolsHelpr;
import TestLib.Common;
import TestLib.Login;

import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class HT_MT_DAB_003_Distributor_Address_Book {
	
	String datafile = "Hottools//HottoolsTestData.xlsx";	
	HottoolsHelpr Hottools=new HottoolsHelpr(datafile);


	@Test(priority=1)
	public void DistributorSignIn(){

		try{
			Hottools.distributorsignin("DistributorAccountDetails");
		}
		catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}
	
	@Test(dependsOnMethods="DistributorSignIn")
	  public void DIstributorAddressBook(){
		  
		  try{
			  Hottools.navigateDistributorAddressBook();
		  }
		  catch (Exception e) {
					
				Assert.fail(e.getMessage(), e);
			}
	  }
  @BeforeMethod
	//@Parameters({"browser"})  

	public void startTest() throws Exception {
		System.setProperty("configFile", "Hottools\\config.properties");
		Login.signIn("firefox");

	}

	@AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}

}
