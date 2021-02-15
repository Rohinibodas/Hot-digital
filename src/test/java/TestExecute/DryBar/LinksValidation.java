package TestExecute.DryBar;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.DryBar.DryBarHelper;
import TestLib.Login;

public class LinksValidation {
	String datafile = "DryBar//DryBarTestData.xlsx";	
	DryBarHelper drybar=new DryBarHelper(datafile);
  @Test
  public void f() {
	  drybar.linkValidation();
	  
  }
  @BeforeTest
  public void startTest() throws Exception {
	 System.setProperty("configFile", "DryBar\\config.properties");
	 Login.signIn();
  }
}

