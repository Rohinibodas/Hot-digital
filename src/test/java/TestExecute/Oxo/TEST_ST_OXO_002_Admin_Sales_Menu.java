package TestExecute.Oxo;


import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.oxo.OxoHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_OXO_002_Admin_Sales_Menu {
	
	String datafile = "oxo//OxoTestData.xlsx";
	OxoHelper oxo = new OxoHelper(datafile);

	@Test(priority = 1)
	
	public void Admin_sales_menu() {
		try {
			
			  oxo.verifyingMagentoLoginPage();
			  oxo.Admin_Login("MagentoAccountDetails");
			  oxo.verifyingMagentoHomepage();
			  oxo.Salesmenu_navigations("SalesMenu");
			  
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
		System.setProperty("configFile", "Oxo\\config.properties");
		Login.signIn();

		 
		  
	  }

  
}
