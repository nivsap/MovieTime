/**
 * 
 */
package il.cshaifasweng.OCSFMediatorExample.server;


//import java.net.PasswordAuthentication;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.*;


//import com.sun.net.httpserver.Authenticator;
import com.sun.net.httpserver.Authenticator.Result;
import com.sun.net.httpserver.HttpExchange;
/**
 * @author Eitan
 *
 */
public class JavaMailUtil {

		
	public static void sendMessage(String toMail, String sub, String msg) {
		String message = msg;
		String subject  = sub;
		String to = toMail;
		String from = "TheSirtiya@gmail.com";
		
			
		sendEmail(message,subject,to,from);
		
		
	}
	
	
	private static void sendEmail(String message, String subject, String to, String from) {
		
		String host ="smtp.gmail.com";
		String password = "team7ftw";
		
		
		//Properties properties = System.getProperties();
		Properties properties = new Properties();
		
		
		properties.put("mail.smtp.auth" , "true");
		properties.put("mail.smtp.starttls.enable" , "true");
		properties.put("mail.smtp.host" , host);
		properties.put("mail.smtp.port" , "587");
		
		
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(from, password);
			}

			
			
		});
		
		session.setDebug(true);
		
		MimeMessage m = new MimeMessage(session);
		
		
		try {
			m.setFrom(new InternetAddress(from));
			
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			
			m.setSubject(subject);
			
			m.setText(message);
			
			Transport.send(m);
			
			
			System.out.println("email sending was successful");
			
			
			
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
}
