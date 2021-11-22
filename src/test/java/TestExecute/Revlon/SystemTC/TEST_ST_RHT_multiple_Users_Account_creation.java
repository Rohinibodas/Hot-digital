package TestExecute.Revlon.SystemTC;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import TestComponent.revlon.RevelonHelper;
import TestLib.Common;
import TestLib.Login;



public class TEST_ST_RHT_multiple_Users_Account_creation {

	String datafile = "revlon//RevlonTestData.xlsx";	
		RevelonHelper revelon=new RevelonHelper(datafile);	
		
		@Parameters({"start","end"})
		
		@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
		
	  public void createaccount(int start,int end) throws Exception {
			
			 
			try{			
				
				System.out.println(start);
				System.out.println(end);
				revelon.Newslettersignup();
				revelon.acceptPrivecy();
				for(int i=start;i<=end;i++)
					{
					
					String Email="perftesting"+i+"@example.com";
					System.out.println(Email);
					
				try {
				
					
					revelon.CreateAccount("AccountCreation");
					revelon.Logout();
	  }
				catch (Exception e) {
					//Common.closeAll();
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
				//Common.closeAll();

			}
			
			@BeforeTest
			  public void startTest() throws Exception {
				//System.setProperty("configFile", "RevlonUS\\Config_RevlonUS_Staging.properties");
				  Login.signIn();
				 
				  
			  }
		}
		
