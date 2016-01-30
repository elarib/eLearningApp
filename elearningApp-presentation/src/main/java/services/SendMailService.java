
package services;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

import entities.User;

public class SendMailService {

	@Autowired
	private ServletContext servletContext;
	private JavaMailSender mailSender;

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void sendMailAction(final User user,HttpServletRequest request) {

		System.out.println("From Service : " + user);
		String lienDeConfirmation = returnAppUrl(request)+"confirm/" + "?email=" + user.getEmail() + "&token="+ user.getToken();
		
		String content = ("Bonjour, " + "Merci d'avoir s'inscrire dans notre plateforme e-Learning."
				+ "Pour finaliser votre inscription, vous devez confirmer votre inscription en cliquant sur ce lien : "
				+ lienDeConfirmation);
	
		sendMail(user.getEmail(), content);

	}

	public void EnvoyerEmailRecuperationPassword(final String email,HttpServletRequest request) {
		System.out.println("email envoyé de récupération ");
		
		String content = "Email de récupération "+request.getRequestURL().toString();
		sendMail(email, content);
	}

	public void sendMail(String email, String content) {
		String host = "gator3060.hostgator.com";
		final String user = "sophia@elarib.com";
		final String password = "GLMSI2015";// change accordingly

		String to = email;// change accordingly

		// Get the session object
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		// Compose the message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("javatpoint");
			message.setText(content);

			// send the message
			Transport.send(message);

			System.out.println("message sent successfully...");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	
	public String returnAppUrl(HttpServletRequest req)
	{
		StringBuffer url = req.getRequestURL();
		String uri = req.getRequestURI();
		String ctx = req.getContextPath();
		String base = url.substring(0, url.length() - uri.length() + ctx.length()) + "/";
		
		return base;
	}


}