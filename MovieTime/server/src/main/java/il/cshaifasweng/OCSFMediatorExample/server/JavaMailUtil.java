package il.cshaifasweng.OCSFMediatorExample.server;

import java.util.Properties;
import javax.mail.Session;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.*;

/**
 * @author Eitan
 *
 */
public class JavaMailUtil {

	
	private static String from = "TheSirtiya@gmail.com";
	public static void sendMessage(String toMail, String subject, String msg) {
			
		sendEmail(msg,subject,toMail,from);
		
		
	}
	
	public static void sendSuccesfulTicketPurchaseMessage(String toMail) {
		
		String subject = "The Sirtiya purcahse details";
		String message = "Dear %s, Thank you for your purchase.\n"
				+ "the details of your order are:\n"
				//for loop over tickets
				+"%cinemaName Cinema, hall %hallNumber, Seat %seatNumber\n"
				+ "Total price: %price";
		sendEmail(message,subject,toMail,from);
		
		
	}
	
	public static void sendPurchaseCancellationMessage(String toMail, String customerName, String orderNumber, double refund) {
		Boolean isRefunded = refund > 0? true:false;
		String subject = "We are sad to see you go";
		String message = 
				"Dear " +customerName + ",\n"
				+ "Your order, number: " + orderNumber + ", has been cancelled to your request.";
				if(isRefunded)
					message+="\nA refund of " + refund + "₪ was issued to the credit card you paid with.";
				message+="\n\nWe hope you will come back to the Sirtiya!\nCheers.";
				
		sendEmail(message, subject, toMail, from);
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
