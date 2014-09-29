package com.tcs;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendAttachmentInEmail {
   public static void sendMail() {
      // Recipient's email ID needs to be mentioned.
      String to = "SheetalA.Sharma@pfizer.com,amol.october@gmail.com,amol.y@tcs.com,Hirdesh.Aggrawal@pfizer.com,Anushri.Jain@pfizer.com,Janhavi.Acharya@pfizer.com";

      // Sender's email ID needs to be mentioned
      String from = "amol.y@tcs.com";

      final String username = "722709";//change accordingly
      final String password = "Kata#1547";//change accordingly

      // Assuming you are sending email through smtp.gmail.com
      String host = "mail.tcs.com";

      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
    //  props.put("mail.smtp.port", "587");
      props.put("mail.smtp.port", "80");

      // Get the Session object.
      Session session = Session.getInstance(props,
         new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(username, password);
            }
         });

      try {
         // Create a default MimeMessage object.
         Message message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(to));

         // Set Subject: header field
         message.setSubject("Site Data Status Attached in Email ");

         // Create the message part
         BodyPart messageBodyPart = new MimeBodyPart();

         // Now set the actual message
         messageBodyPart.setText("Hi All,\n" +
         		"Please find the attached Site Data having NFLookup and Ping Status.\n" +
        		 "Please Note - This mail is coming thrgh a Java Program as a part of Testing the Functionality \n" +
         		"Thanks.\n" +
         		"Amol Yadwadkar");

         // Create a multipar message
         Multipart multipart = new MimeMultipart();

         // Set text message part
         multipart.addBodyPart(messageBodyPart);

         // Part t  wo is attachment
         messageBodyPart = new MimeBodyPart();
         String filename = "Site Data Demo.xlsx";
         DataSource source = new FileDataSource(filename);
         messageBodyPart.setDataHandler(new DataHandler(source));
         messageBodyPart.setFileName(filename);
         multipart.addBodyPart(messageBodyPart);

         // Send the complete message parts
         message.setContent(multipart);

         // Send message
        //Transport.send(message);
    
        	 
      	   File file =new File("Site Data Demo.xlsx");
   
      	 if(file.renameTo(new File("E:\\Site Data Status\\" + file.getName()))){
     		//System.out.println("File is moved successful!");
     	   }else{
     		//System.out.println("File is failed to move!");
     	   }

         System.out.println("File Genertated Successfully....File can be found @  "+ " E:\\Site Data Status\\" +file.getName()) ;
  
      } catch (MessagingException e) {
         throw new RuntimeException(e);
      }
      catch (Exception  e){
    	  e.printStackTrace();
      }
   }
}