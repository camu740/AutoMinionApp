package autominion.utils;

import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class SendEmail {
	/**
	 * Metodo que envia un email al correo que nosotros queramos, con el texto que nosotros queramos
	 * @param email Email de la persona que le vamos a enviar el correo
	 * @param pin Pin de autentificacion para registrarse o cambiar contraseña
	 */
	
	public static void email(String email, int pin) {
		final String username = CredentialsHelper.email();
		final String password = CredentialsHelper.passwordEmail();
		
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.trust", "*");
		prop.put("mail.smtp.starttls.required", "true");
		prop.put("mail.smtp.ssl.protocols", "TLSv1.2");

		Session session = Session.getInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("from@gmail.com")); // Aqui se puede mandar el email a los que queramos
																	// demas
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			message.setSubject("Pin de autentificación");
			message.setText("Hola aqui tiene el codigo de autentificación para poder registrarte,\n\n" + pin + ".");
			// mailchimp, sendinblue, mailjet Paguinas web para enviar correos con html
			
			Transport.send(message);

		} catch (MessagingException e) {
			JOptionPane.showMessageDialog(null, "Correo eléctronico no válido, pruebe a insertarlo otra vez");
			e.printStackTrace();
		}
	}
}
