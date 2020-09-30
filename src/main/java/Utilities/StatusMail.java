package Utilities;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import TestLib.Driver;

	public class StatusMail {
		
		public final static String PROPERTYFILENAME = "config/mail.properties";
		private static Properties mailProperties = new Properties();
		final public static Logger logger = Logger.getLogger(StatusMail.class);
		public static int totalTCcount = 0;
		public static int passTCcount = 0;
		public static int failTCcount = 0;
		public static String testPassPercentage = null;
		public static String testPassRate = null;
		public static String DBUName = null, DBPwd = null, DBURL = null, DBDriver = null;
		public static int SUMMARYFLAG;

		public static String to="mchiruvella@gmail.com,mahendra.koppanadham@gmail.com",cc="mchiruvella@gmail.com",subject="HoT Test Automation Results",attachmentPath="",attachmentPath1="";
		
		
		
		public static void sendMail() throws Exception
		{
			subject="HoT Test Automation Results";
			attachmentPath=System.getProperty("user.dir")+"/src/test/resources/MailTemplates/ExecutionMailReport.html" ;
			//attachmentPath1=;
			attachmentPath=HTMLPreparation.generateMail("exectionReport");
			triggerSendMail();
		}

		
		public static void triggerSendMail()
		{
			String userName="manoj@lotuswave.net";
			String passWord= "Mankoo@17";
			String host="mail.privateemail.com";
			String port="465";
			String starttls="true";
			String auth="true";
			boolean debug=true;
			String socketFactoryClass="javax.net.ssl.SSLSocketFactory";
			String fallback="false";


			//Object Instantiation of a properties file.
			Properties props = new Properties();
			props.put("mail.smtp.user", userName);
			props.put("mail.smtp.host", host);

			if(!"".equals(port)){
				props.put("mail.smtp.port", port);
			}

			if(!"".equals(starttls)){
				props.put("mail.smtp.starttls.enable",starttls);
				props.put("mail.smtp.auth", auth);
			}

			if(debug){
				props.put("mail.smtp.debug", "true");
			}
			else{
				props.put("mail.smtp.debug", "false");
			}

			if(!"".equals(port)){
				props.put("mail.smtp.socketFactory.port", port);
			}

			if(!"".equals(socketFactoryClass)){
				props.put("mail.smtp.socketFactory.class",socketFactoryClass);
			}

			if(!"".equals(fallback)){
				props.put("mail.smtp.socketFactory.fallback", fallback);
			}

			try{

				Session session = Session.getDefaultInstance(props, null);
				MimeMessage msg = new MimeMessage(session);
				msg.setSubject(subject);

				Multipart multipart = new MimeMultipart();
				MimeBodyPart messageBodyPart = new MimeBodyPart();
				
				DataSource source = new FileDataSource(attachmentPath);
				messageBodyPart.setDataHandler(new DataHandler(source));
				multipart.addBodyPart(messageBodyPart);
				
				/*MimeBodyPart messageBodyPart1 = new MimeBodyPart();
				DataSource source1 = new FileDataSource(attachmentPath1);
				messageBodyPart1.setDataHandler(new DataHandler(source1));
				messageBodyPart1.setFileName("ADCTasksDetailStatusReport.xlsx");
				multipart.addBodyPart(messageBodyPart1);*/

				msg.setContent(multipart);
				msg.setFrom(new InternetAddress(userName));

				msg.addRecipients(Message.RecipientType.TO, to);
				msg.addRecipients(Message.RecipientType.CC, cc);
				msg.saveChanges();

				Transport transport = session.getTransport("smtp");
				transport.connect(host, userName, passWord);
				
				System.out.println("connected mail ");
				
				transport.sendMessage(msg, msg.getAllRecipients());
				
				transport.close();
				System.out.println("Mail Sent");
			} 

			catch (Exception e){
				e.printStackTrace();				
			}
		}

	}



