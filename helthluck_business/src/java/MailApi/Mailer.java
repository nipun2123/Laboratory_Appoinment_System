/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MailApi;

import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author nipun
 */
public class Mailer {

    public static String sendEmail(String receiver, String subject, String content) {

        try {
            final String emailSender = "........";
            final String password = "...........";
            
            
            Properties pr = new Properties();
            
            pr.put("mail.smtp.auth", "true");
            pr.put("mail.smtp.starttls.enable", "true");
            pr.put("mail.smtp.host", "smtp.gmail.com");
            pr.put("mail.smtp.port", "587");
            pr.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            
            Session gs = Session.getInstance(pr, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(emailSender, password);
                    
                }
            });
            
            Message ms = messageContent(gs, emailSender, receiver,subject,content);
            
           
            
        } catch (Exception ex) {
            Logger.getLogger(Mailer.class.getName()).log(Level.SEVERE, null, ex);
        }
 return "Email sent! ";
    }

    private static Message messageContent(Session gs, String emailId, String reciever,String subject,String content) throws Exception {
        try {

            Message msg = new MimeMessage(gs);
            msg.setFrom(new InternetAddress(emailId));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(reciever));
            msg.setSubject(subject);
            msg.setContent(content,"text/html" );  
            Transport.send(msg);
            return msg;
        } catch (MessagingException e) {
            System.out.println(e);
        }

        return null;

    }


}
