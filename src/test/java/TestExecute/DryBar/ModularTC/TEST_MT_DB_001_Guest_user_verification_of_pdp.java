package TestExecute.DryBar.ModularTC;
	import org.testng.Assert;
	import org.testng.annotations.AfterTest;
	import org.testng.annotations.BeforeTest;
	import org.testng.annotations.Test;

	import TestComponent.DryBar.DryBarHelper;
	import TestLib.Common;
	import TestLib.Login;

	public class TEST_MT_DB_001_Guest_user_verification_of_pdp {
		
			String datafile = "DryBar//DryBarTestData.xlsx";	
			DryBarHelper drybar=new DryBarHelper(datafile);
			@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
		  
		  public void Guest_user_verification_of_pdp() {
				try {
					  drybar.Accept();
					  drybar.verifyingHomePage();
					  drybar.Search_productname("ProductName");
					  drybar.Verify_PDP();
					  	
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
	 //System.setProperty("configFile", "DryBar\\config.properties");
	 Login.signIn();
				 

	}
		}


