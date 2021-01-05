package TestExecute.DryBar;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Login;

public class VerifyingMiniCartPage {
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
	
  @Test
  public void f() throws Exception {
	  drybar.clickHairProducts();
	  drybar.selectproduct("ProductName");
	  drybar.increaseProductQuantity("2");
	  drybar.clickAddtoBag();
	  drybar.clickminiCartButton();
	//  drybar.addproductInMiniCartPage();
  }
  
  @AfterTest
 	public void clearBrowser()
 	{
 		//Common.closeAll();

 	}
 	
 	@BeforeTest
 	  public void startTest() throws Exception {
 		 System.setProperty("configFile", "DryBar\\config.properties");
 		  Login.signIn();
 		 
 		  
 	  }

}
