package TestExecute.Redesign_Hottools;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import TestComponent.Redesign_Hottools.Redesign_HottoolsHelper;
import TestLib.Common;
import TestLib.Login;

public class Test_RD_HT_1000_Account_Creation {
	String datafile = "Redesign_Hottools//Redesign_HottoolsTestData.xlsx";	
	Redesign_HottoolsHelper Resign_Hottools=new Redesign_HottoolsHelper(datafile);
	
	@Parameters({"start","end"})
	@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
  public void createaccount(int start,int end) throws Exception {
		
		 
		try{
			
			//int i=start;
			
			System.out.println(start);
			System.out.println(end);
			for(int i=start;i<=end;i++)
				{
				//System.setProperty("configFile", "Redesign_Hottools\\config.properties");
				  //Login.signIn();
				String Email="perftest"+i+"@example.com";
			try {
			
			Resign_Hottools.Accept();
			Resign_Hottools.Close_popup();
		//	Resign_Hottools.verifyingHomePage();
			Resign_Hottools.CreateAccount("AccountCreationDetails", Email);
			Resign_Hottools.Signout();
			
		
	 
	  
  }

			
			catch (Exception e) {
				Common.closeAll();
				//startTest();
				//Assert.fail(e.getMessage(), e);
			} 
				}
				}
				catch (Exception e) {
					
 
		}
	
	}

		
			
			
			
		
			

  
  
  @AfterTest
	public void clearBrowser()
	{
		Common.closeAll();

	}
	
	@BeforeTest
	  public void startTest() throws Exception {
		System.setProperty("configFile", "Redesign_Hottools\\config.properties");
		  Login.signIn();
		 
		  
	  }
}