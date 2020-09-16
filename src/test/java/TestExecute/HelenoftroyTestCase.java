package TestExecute;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import TestComponent.HelenoftroyBrands;
import TestLib.Common;
import TestLib.Login;
import Utilities.Listener;


public class HelenoftroyTestCase {

	@Test
	public void ftest() throws Exception {
		String datafile = "Helenoftroy.xlsx";	
		try {
			HelenoftroyBrands brand=new HelenoftroyBrands();
			Thread.sleep(30000);
			brand.navigateBrands();
		}
		catch (Exception e) {
			
			Assert.fail(e.getMessage(), e);
		} 
		finally 
		{ 
			//Common.closeAll();

		}
	}

	
	@BeforeMethod
	  public void beforeMethod() throws Exception {
		  Login.signIn();
		  
	  }
}
