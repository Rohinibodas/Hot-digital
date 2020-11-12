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

public class HT_ST_MC_005_Mini_Cart {
	String datafile = "Hottools//HottoolsTestData.xlsx";	
	HottoolsHelpr Hottools=new HottoolsHelpr(datafile);

	@Test(priority=1)
	public void SigIn(){

		try{
			Hottools.singin("RetailCustomerAccountDetails");
		}
		catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}

	@Test(dependsOnMethods="SigIn")
	public void SerchProduct(){

		try{
			Hottools.searchingProducts("productName");
		}
		catch (Exception e) {

			Assert.fail(e.getMessage(), e);
		}
	}

	@Test(dependsOnMethods="SerchProduct")
	public void MinicartProduct(){

		try{
			Hottools.minicartProduct("productName");
		}
		catch (Exception e) {



			Assert.fail(e.getMessage(), e);
		}
	}

	@Test(dependsOnMethods="MinicartProduct")
	public void ValidateMiniCart(){

		try{
			Hottools.miniCart("productName");
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
