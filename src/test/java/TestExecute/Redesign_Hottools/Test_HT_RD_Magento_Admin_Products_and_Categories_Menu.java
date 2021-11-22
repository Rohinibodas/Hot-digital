package TestExecute.Redesign_Hottools;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestComponent.Redesign_Hottools.Redesign_HottoolsHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_HT_RD_Magento_Admin_Products_and_Categories_Menu {
	
	String datafile = "Redesign_Hottools//Redesign_HottoolsTestData.xlsx";	
	Redesign_HottoolsHelper Redesign_Hottools=new Redesign_HottoolsHelper(datafile);
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  
  public void Admin_products_and_catrgories_menu() {
		try {
			
			Redesign_Hottools.verifyingMagentoLoginPage();
			Redesign_Hottools.Admin_Login("MagentoAccountDetails");
			Redesign_Hottools.verifyingMagentoHomepage();
			Redesign_Hottools.Catalogmenu_navigations("CatalogMenu");
			 
			  
		}
		catch (Exception e) {
			e.printStackTrace();
			
			Assert.fail(e.getMessage(), e);
		} 
	}
	
	
	
	@AfterTest
	public void clearBrowser()
	{
	Common.closeAll();

	}
	

	@BeforeTest
	  public void startTest() throws Exception {
		System.setProperty("configFile", "Redesign_Hottools\\Config_Redesign_Hottools_Production.properties");
		  Login.signIn();
		 
		  
	  }


}
