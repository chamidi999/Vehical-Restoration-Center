
package Mail;
import com.sun.xml.internal.org.jvnet.mimepull.MIMEMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Session;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author Asus
 */
public class SendEmail {

    /**
     * @param args the command line arguments
     */
    public static String sendMail(String recepient, String content, String title) throws Exception {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();  //configure the propertise in email
        
        properties.put("mail.smtp.auth","true");   //Authentication needed for the email server
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        
        //identifying the emil address and password
        final String myAccountEmail = "amigos9998@gmail.com";
        final String password = "996944220v";
        
        //login to the email
        Session session = Session.getInstance(properties,new Authenticator(){

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(myAccountEmail, password);//password authentication
            }
            
        });
        
        Message message  = prepareMessage(session,myAccountEmail,recepient, content, title);
        
        Transport.send(message); //thransport the data
        System.out.println("Message send Successfully");
		return "Message send Successfully";
    }

    private static Message prepareMessage(Session session,String myAccountEmail,String recepient, String content, String title) {
        try {
            Message message=new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject(title);  //message subject
            message.setText(content); //message body
            return message;
        } catch (Exception ex) {
            Logger.getLogger(SendEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null; 
    }
    
}
