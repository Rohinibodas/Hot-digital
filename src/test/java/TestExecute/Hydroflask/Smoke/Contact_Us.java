package TestExecute.Hydroflask.Smoke;



import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;
//@Listeners(Utilities.TestListener.class)
public class Contact_Us {
	
	String datafile = "Hydroflask//HydroTestData.xlsx";	
	HydroHelper Hydro=new HydroHelper(datafile);
	
  @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void contactUS() {

		try {
	      Hydro.contactUsPage("contactusEmail");
	        
	        
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
	
	@BeforeTest
	  public void startTest() throws Exception {
		 System.setProperty("configFile", "Hydroflask\\config.properties");
		  Login.signIn();
		  Hydro.acceptPrivecy();
		  
	  }
}
