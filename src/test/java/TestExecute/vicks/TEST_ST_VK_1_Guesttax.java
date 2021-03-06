package TestExecute.vicks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import TestComponent.Vicks.VicksHelper;
import TestLib.Common;
import TestLib.Login;
import Utilities.ExcelReader;

 

public class TEST_ST_VK_1_Guesttax {
    String datafile = "Vicks//Vickstestdata.xlsx";    
	VicksHelper vicks = new VicksHelper(datafile);
    @Test(retryAnalyzer = Utilities.RetryAnalyzer.class)
    
    public void gUESTTAX() throws Exception {
    	try {
			String addressFile = "Vicks/flks.xlsx";

	     	vicks.prepareTaxData("vicksTaxDetails_Guest.xlsx");
	     	Map<String, List<Map<String, String>>> addressVal=new HashMap<>();
			ExcelReader excelData=new ExcelReader(addressFile);
			addressVal=excelData.getStateAddressValue();
			Set<String> stateKeys=addressVal.keySet();
			for(String state:stateKeys)
			{	
			for(int i=0;i<addressVal.get(state).size();i++)
			{
//				int i=2;
		
			try {
				
			Map<String, String> add=addressVal.get(state).get(i);
			String streetAddress=addressVal.get(state).get(i).get("StreetAddress");
			String City=addressVal.get(state).get(i).get("City");
			String Zipcode=addressVal.get(state).get(i).get("Zipcode");
			String tax=addressVal.get(state).get(i).get("TaxRate");
			String Website=vicks.URL();
			vicks.Verifyhomepage();
//			vicks.Agreandproceed();
			vicks.Humidifiers_Vaporizers();
			vicks.productselect();
			vicks.addtocart();
			vicks.mincat();
			vicks.checkout();
            vicks.GuestShippingAddress(streetAddress,City,Zipcode,state);
            HashMap<String,String> data=vicks.Taxcalucaltion(tax,state);
            vicks.paymentDetails("PaymentDetails");
           //String OrderId=vicks.PlaceOrder();
            String OrderId="123";
//            vicks.clickminicartButton();
//    		vicks.removeproductinBagPage();
    		
            
         
            
    	 System.out.print(data);

			vicks.writeResultstoXLSx(Website,OrderId,data.get("subtotlaValue"),data.get("shippingammountvalue"),state,Zipcode,data.get("Taxammountvalue"),data.get("ActualTotalammountvalue"),data.get("ExpectedTotalAmmountvalue"),data.get("giventaxvalue"),data.get("calculatedvalue"));
           
			}
			
			
		catch (Exception e) {
			Common.closeAll();
			startTest();
//			Assert.fail(e.getMessage(), e);
				} 
			}}}
			catch (Exception e) {
				
				Assert.fail(e.getMessage(), e);
	}
}
    	
	@BeforeMethod
	public void startTest() throws Exception {
	//	System.setProperty("configFile", "Vicks\\config_Vicks_Production.properties");
		Login.signIn();
        
    }
	@AfterTest
	public void clearBrowser()
	{
		
//		Common.closeAll();

	}

}
