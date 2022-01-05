package TestExecute.vicks;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.Vicks.VicksHelper;
import TestLib.Common;
import TestLib.Login;

public class TEST_ST_VK_043_Magento_Products_Categories_Menu {
	String datafile = "Vicks//Vickstestdata.xlsx";	
	VicksHelper vicks=new VicksHelper(datafile);
	@Test(priority=1)
	public void Magento_Products_Categories_Menu() throws Exception {

		try {
			vicks.verifyingMagentoLoginPage();
			vicks.vicksAdminlogin("AdminLogins");
			vicks.verifyingMagentoHomepage();
//			vicks.Salesmenu_navigations("SalesMenu");
			vicks.Catalogmenu_navigations("CatalogMenu");
			
		}
		catch (Exception e) {
			Assert.fail(e.getMessage(), e);
		
		
		} 
	}

	
	@AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}
		
		@BeforeMethod
		  public void startTest() throws Exception {
			System.setProperty("configFile", "Vickshumdifier\\config.properties");
			  Login.signIn();
			 
			  
		  }

}
