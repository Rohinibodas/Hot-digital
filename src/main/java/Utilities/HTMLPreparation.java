package Utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import org.testng.ITestResult;

import TestLib.Automation_properties;
import TestLib.Driver;



public class HTMLPreparation {

	public static String mailTemplatePath=System.getProperty("user.dir")+"/MailTemplates/";
	public static String htmlContent;
	public static String module;
	public static ITestResult test;

	public static String parseHTMLfile(String filePath)
	{
		StringBuilder contentBuilder = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new FileReader(filePath));
			String str;
			while ((str = in.readLine()) != null) {
				contentBuilder.append(str);
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Driver.getLogger().info("File Parsed");
		return contentBuilder.toString();
	}

	public static String prepmail(String fileName) throws Exception{

		FileWriter fWriter = null;
		BufferedWriter writer = null;
		String strDate = HTML.GetDateTime();
		String sFilename = fileName+ strDate+ ".html";
		String htmlreportpath =System.getProperty("user.dir") + "/test-output/" + File.separator + sFilename;
		try {

			fWriter = new FileWriter(htmlreportpath);
			writer = new BufferedWriter(fWriter);
			writer.write(htmlContent);;
			writer.close();
			System.out.println("MAil Body prepared");
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return htmlreportpath;
	}




	/*public static String generateMail(String mailType) throws Exception {

		String filePath;
		switch(mailType)
		{
		case "start" :
		{
			filePath=mailTemplatePath+"ExecutionStartMail.html";
			mailGeneration.to= Automation_properties.getInstance().getProperty("Exestartmail");
			htmlContent= parseHTMLfile(filePath).replace("#Browser#", Automation_properties.getInstance().getProperty("BROWSER") );

			fillCommonDetails();
			MailTestSuite.generateStartMail();
			return prepmail("Execution_StartMail");
		}
		case "exectionReport":
		{
			filePath=mailTemplatePath+"ExecutionMail.html";
			mailGeneration.to= HA.TestAutomation.HATF_properties.getInstance().getProperty("Exemail");
			htmlContent= parseHTMLfile(filePath).replace("#Browser#", HA.TestAutomation.HATF_properties.getInstance().getProperty("BROWSER") );
			fillCommonDetails();
			System.out.println("Module Name:"+HTMLPreparation.module);
			String module=generateHTMLReport.getTCdetails(0).get(4).split("HA.TestExecute.")[1].split("\\.")[0]+" "+ HA.TestAutomation.HATF_properties.getInstance().getProperty("BROWSER");
			String ModuleName=generateHTMLReport.getTCdetails(0).get(4).split("HA.TestExecute.")[1].split("\\.")[0];
			if(ModuleName.equals("DI"))
			{
				htmlContent=htmlContent.replace( "#HorizonStatus#", "    <tr>"
						+"   <td align='left' valign='middle' style='background:#e9e9e9; border:1px solid #b6b6b6; font:bold 13px 'Segoe UI', Arial, Helvetica, sans-serif;' class='auto-style1'>Horizon Status</td>"
			//			+"  <td align='left' valign='middle' style='border:1px solid #b6b6b6; font:normal 13px 'Segoe UI', Arial, Helvetica, sans-serif; '>"+DLRWebServices.getHorizonStatus()+"</td>"
						+"</tr>");
			}
			else
				htmlContent=htmlContent.replace( "#HorizonStatus#","");
		//	mailGeneration.subject+=": "+module+" Module Results"+MailTestSuite.generateExecutionHTML();;
			HTML.htmlreportpath=prepmail("Execution_report");
			return HTML.htmlreportpath;
		}
		case "failure":
		{
			filePath=mailTemplatePath+"ExecutionFail.html";
		//	mailGeneration.subject="Test case Failed";
		//	mailGeneration.to= HA.TestAutomation.HATF_properties.getInstance().getProperty("FailureMail");
			htmlContent= parseHTMLfile(filePath);
			fillCommonDetails();
			String testDetails = test.getTestClass().toString();
			String testName=testDetails.split("HA.TestExecute.")[1];
			String Module= testName.split("\\.")[0];
			String testScriptName =(testName.split("\\.")[1]).split("]")[0];
			String testDuration = MilliSecondsToMinutes(test.getStartMillis()-test.getEndMillis());
			String Remarks=test.getThrowable().toString();
			Remarks=Remarks.split("\\:")[0];
			htmlContent=htmlContent.replace("#Module#",Module).replace("#Test Script Name#",testScriptName).replace("#Test Duration#",testDuration).replace("#Remarks#",generateHTMLReport.exception(Remarks));
			return prepmail("TC_Failed");
		}
		case "consolidatedReport":
		{
			filePath=mailTemplatePath+"ConsolidatedMail.html";
			mailGeneration.to= Automation_properties.getInstance().getProperty("Exemail");
			htmlContent= parseHTMLfile(filePath);
			htmlContent=htmlContent.replace("#BuildVersion#",DLRWebServices.getVersion());
			MailTestSuite.generateConsolidatedTable(MailTestSuite.generateConsolidatedHTML());

			return prepmail("Consolidated_Report_");
		}
		}
		return null;
	}
*/
	

	public static void setFailedTestcaseDetails(ITestResult testCase) {

		test=testCase;

	}

	public static String MilliSecondsToMinutes(long testexetime)
	{
		String time;
		long minutes = TimeUnit.MILLISECONDS.toMinutes(testexetime);
		if(minutes<1){
			minutes = TimeUnit.MILLISECONDS.toSeconds(testexetime);
			time="1 Mins";
		}
		else{
			time=String.valueOf(minutes)+" Mins";
		}
		return time;
	}
}
