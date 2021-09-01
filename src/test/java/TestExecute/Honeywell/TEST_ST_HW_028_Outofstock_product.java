package TestExecute.Honeywell;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import TestComponent.Honeywell.Honeywellhelper;
import TestComponent.Hydroflask.HydroHelper;
import TestLib.Common;
import TestLib.Login;

 
public class TEST_ST_HW_028_Outofstock_product {
String datafile = "Honeywell\\HoneywellTestData.xlsx";
Honeywellhelper honeyWell=new Honeywellhelper(datafile);
@Test(retryAnalyzer = Utilities.RetryAnalyzer.class)

public void outofstock_product() throws Exception {


honeyWell.verifyingHomePage();
//honeyWell.agree_proceed();
honeyWell.click_Airpurifiers();
honeyWell.outofstockproduct("outofstock");
honeyWell.outofstockinpdp();

 

 


}

 

 

@AfterTest
public void clearBrowser()
{
Common.closeAll();

}

 

@BeforeTest
public void startTest() throws Exception {
//System.setProperty("configFile", "Honeywell\\config.properties");
Login.signIn();

 


}
}

 