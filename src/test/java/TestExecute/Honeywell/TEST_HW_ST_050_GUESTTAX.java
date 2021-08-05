package TestExecute.Honeywell;

import org.testng.annotations.Test;

import TestComponent.Honeywell.Honeywellhelper;
import TestLib.Common;
import TestLib.Login;
import Utilities.ExcelReader;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

 

public class TEST_HW_ST_050_GUESTTAX {
    String datafile = "Honeywell\\HoneywellTestData.xlsx";    
    Honeywellhelper honeyWell=new Honeywellhelper(datafile);
    @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
    
    public void gUESTTAX() throws Exception {
    	try {
//			String addressFile = "Honeywell//StreetAddresswithZipcode.xlsx";
			String addressFile = "Honeywell//guestaxad.xlsx";
	     	honeyWell.prepareTaxData("HoneywellTaxDetails_Guest.xlsx");
	     	Map<String, List<Map<String, String>>> addressVal=new HashMap<>();
			ExcelReader excelData=new ExcelReader(addressFile);
			addressVal=excelData.getStateAddressValue();
			Set<String> stateKeys=addressVal.keySet();
			for(String state:stateKeys)
			{	
			for(int i=0;i<addressVal.get(state).size();i++)
			{
		
			try {
			Map<String, String> add=addressVal.get(state).get(i);
			String streetAddress=addressVal.get(state).get(i).get("StreetAddress");
			String City=addressVal.get(state).get(i).get("City");
			String Zipcode=addressVal.get(state).get(i).get("Zipcode");
			String tax=addressVal.get(state).get(i).get("TaxRate");	    
            honeyWell.verifyingHomePage();
          //  honeyWell.accept();
            honeyWell.click_fans();
            honeyWell.adding_product_toCart("productnameRegester1");
            honeyWell.clickminicartButton();
            honeyWell.clickminicartcheckout();
            honeyWell.GuestShippingAddress(streetAddress,City,Zipcode,state);
          HashMap<String,String> data=honeyWell.Taxcalucaltion(tax);
            honeyWell.creditCard_payment("CCVisa");
           String OrderId=honeyWell.order_Verifying();
         
            
    	 System.out.print(data);

			honeyWell.writeResultstoXLSx(OrderId,data.get("subtotlaValue"),data.get("shippingammountvalue"),data.get("Taxammountvalue"),data.get("Totalammountvalue"),data.get("giventaxvalue"),data.get("calculatedvalue"));
            //honeyWell.writeResultstoXLSx("OrderId");
			}
			
			
		catch (Exception e) {
			Common.closeAll();
			startTest();
			//Assert.fail(e.getMessage(), e);
		} 
			}
			}}
			catch (Exception e) {
				
				Assert.fail(e.getMessage(), e);
	}}
	
    @BeforeTest
    public void startTest() throws Exception {
       System.setProperty("configFile", "Honeywell\\config.properties");
        Login.signIn();
       
        
    }
	@AfterTest
	public void clearBrowser()
	{
		
		Common.closeAll();

	}

}
