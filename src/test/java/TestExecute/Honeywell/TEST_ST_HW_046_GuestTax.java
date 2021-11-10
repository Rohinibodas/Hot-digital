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

 

public class TEST_ST_HW_046_GuestTax {
//    String datafile = "Honeywell\\HoneyData.xlsx";    
	String datafile = "Honeywell\\HoneywellTestData.xlsx";	
    Honeywellhelper honeyWell=new Honeywellhelper(datafile);
    @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
    
    public void gUESTTAX() throws Exception {//StreetAddresswithZipcode (1)
    	try {
//    		String addressFile = "Honeywell//DryBarTaxAddress.xlsx";
    		String addressFile = "Honeywell//newAddressForHoneywell1.xlsx";
//			String addressFile = "Honeywell//guestaxad.xlsx";
//    		String addressFile = "Honeywell//HoneywellproTaxAddress.xlsx";
    		//1
			honeyWell.prepareTaxDataN("HoneywellTaxDetails_Guest.xlsx");
	     	
//			honeyWell.prepareTaxData("HoneywellTaxDetails_Guest.xlsx");
	     	Map<String, List<Map<String, String>>> addressVal=new HashMap<>();
			ExcelReader excelData=new ExcelReader(addressFile);
			addressVal=excelData.getStateAddressValue();
			Set<String> stateKeys=addressVal.keySet();
			for(String state:stateKeys)
			{	
			for(int i=0;i<addressVal.get(state).size();i++)
			{
		
			try {
//				int i=1;
			Map<String, String> add=addressVal.get(state).get(i);
			String streetAddress=addressVal.get(state).get(i).get("StreetAddress");
			String City=addressVal.get(state).get(i).get("City");
			String Zipcode=addressVal.get(state).get(i).get("Zipcode");
			String tax=addressVal.get(state).get(i).get("TaxRate");	    
//			 String Website=honeyWell.URL();
		      String Website="Honeywell";
			honeyWell.verifyingHomePage();
                honeyWell.accept();
           honeyWell.Click_Heaters();
           honeyWell.adding_product_toCart("productnameRegester2");
//            honeyWell.click_fans();
//            honeyWell.click_Airpurifiers();
//            honeyWell.adding_product_toCart("productnameRegester1");
            honeyWell.clickminicartButton();
            honeyWell.clickminicartcheckout();
            honeyWell.GuestShippingAddress(streetAddress,City,Zipcode,state);
         //3
//            HashMap<String,String> data=honeyWell.TaxcalucaltionN(tax,state);
            HashMap<String,String> data=honeyWell.taxValidation(tax,state);
            
//             HashMap<String,String> data=honeyWell.Taxcalucaltion(tax);
            honeyWell.creditCard_payment("CCVisa");
           String OrderId=honeyWell.order_Verifying();
//          String OrderId="123456789";
         
            

           System.out.print(data);
           //4
//           honeyWell.writeResultstoXLSxN(Website,OrderId,data.get("subtotlaValue"),data.get("shippingammountvalue"),state,Zipcode,data.get("Taxammountvalue"),data.get("ActualTotalammountvalue"),data.get("ExpectedTotalAmmountvalue"),data.get("giventaxvalue"),data.get("calculatedvalue"));
           honeyWell.writeResultstoXLSxN(Website,OrderId,data.get("subtotlaValue"),data.get("shippingammountvalue"),state,Zipcode,data.get("Taxammountvalue"),data.get("ActualTotalammountvalue"),data.get("ExpectedTotalAmmountvalue"),data.get("giventaxvalue"),data.get("calculatedvalue"));
			           
           
           
//			honeyWell.writeResultstoXLSx(OrderId,data.get("subtotlaValue"),data.get("shippingammountvalue"),data.get("Taxammountvalue"),data.get("Totalammountvalue"),data.get("giventaxvalue"),data.get("calculatedvalue"));

			//honeyWell.writeResultstoXLSx(data.get("subtotlaValue"),data.get("shippingammountvalue"),data.get("Taxammountvalue"),data.get("Totalammountvalue"),data.get("giventaxvalue"),data.get("calculatedvalue"));
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
