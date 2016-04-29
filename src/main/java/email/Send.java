package email;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletContext;

import model.*;

public class Send {

	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;
 
	/*public static void main(String args[]) throws AddressException, MessagingException {
		//generateAndSendEmail();
		//generateAndSendEmail();
		System.out.println("\n\n ===> Your Java Program has just sent an Email successfully. Check your email..");
	}*/
 
	public void Caller(form frm, Properties properties){
		try {
			generateAndSendEmail(frm, properties);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void generateAndSendEmail(form frm, Properties properties) throws AddressException, MessagingException {
 	
		// Step1
		System.out.println("\n 1st ===> setup Mail Server Properties..");
		mailServerProperties = System.getProperties();
		
		
		
		mailServerProperties.put("mail.smtp.user", "joaao.correia"); 			// Criar conta nova e meter aqui
		mailServerProperties.put("mail.smtp.password", "liaryzvazcmgudyr");		// Criar conta nova e meter aqui
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		System.out.println("Mail Server Properties have been setup successfully..");
 
		// Step2
		System.out.println("\n\n 2nd ===> get Mail Session..");
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);	
		//generateMailMessage.setFrom(new InternetAddress("joaao.correia@gmail.com"));		// Criar conta nova e meter aqui
		//generateMailMessage.setFrom(new InternetAddress("non-reply@cgi.com"));
		
		
		String[] mailAddressTo = new String[3];    
		mailAddressTo[0] = "bruno.godinho@cgi.com";    
		mailAddressTo[1] = "joao.carlos.correia@cgi.com";    
		mailAddressTo[2] = "ricardo.morgado@cgi.com";

		InternetAddress[] mailAddress_TO = new InternetAddress[mailAddressTo.length];

		for (int i = 0; i < mailAddressTo.length; i++)
		{
		    mailAddress_TO[i] = new InternetAddress(mailAddressTo[i]);
		}

		generateMailMessage.addRecipients(Message.RecipientType.TO, mailAddress_TO);
		
		//generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("bruno.godinho@cgi.com"));
		
		//generateMailMessage.addRecipients(type, addresses);
		
		//generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("test2@crunchify.com"));
		//generateMailMessage.addRecipient(Message.RecipientType.BCC, new InternetAddress("ricardo.morgado@cgi.com")); 
		generateMailMessage.setSubject("DATOS DE CONTACTO");
		
		String emailBody = "NIF: " + frm.get_NIF() + 
						   "<br>NOMBRE: " + frm.get_NOMBRE() +
						   "<br>PRIMER APELLIDO: " + frm.get_PAP() +
						   "<br>SEGUNDO APELLIDO: " + frm.get_SAP() +
						   "<br>COMUNIDADE AUT&#211;NOMA: " + frm.get_COMAUT() +
						   "<br>PROVINCIA: " + frm.get_PROVINCIA() +
						   "<br>MUNICIPIO: " + frm.get_MUNICIPIO() +
						   "<br>LOCALIDAD: " + frm.get_LOCALIDAD() +
						   "<br>DIRECC&#211;IN: " + frm.get_DIRECCION() +
						   "<br>MOTIVI DE LA SOLICITUD: " + frm.get_MOTIVISOL() +
						   "<br>CORREO ELECTR&#211;NICO: " + frm.get_CORREO() + 
						   "<br>TEL&#201;FONO: " + frm.get_TEL() + 
						   "<br>TEXTO: " + frm.get_TEXTO();
		
		//String emailBody = "Test email by Crunchify.com JavaMail API example. " + "<br><br> Regards, <br>Crunchify Admin";
		generateMailMessage.setContent(emailBody, "text/html");
		System.out.println("Mail Session has been created successfully..");
		
		/*
		// Anexar ficheiro ao e-mail a enviar  
		MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(emailBody, "text/html");
		
        // creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
 
        String[] attachFiles = new String[]{"C:/users/morgadorf/desktop/comandos unix.txt"};
        // adds attachments
        if (attachFiles != null && attachFiles.length > 0) {
            for (String filePath : attachFiles) {
                MimeBodyPart attachPart = new MimeBodyPart();
 
                try {
                    attachPart.attachFile(filePath);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
 
                multipart.addBodyPart(attachPart);
            }
        }
 
        // sets the multi-part as e-mail's content
        generateMailMessage.setContent(multipart);
		
        */
		
  //generateMailMessage.ATTACHMENT("C:/users/morgadorf/desktop/mail-1.4.7.jar");
  
		// Step3
		System.out.println("\n\n 3rd ===> Get Session and Send mail");
		Transport transport = getMailSession.getTransport("smtp");
 
		// Enter your correct gmail UserID and Password
		// if you have 2FA enabled then provide App Specific Password
		transport.connect("smtp.gmail.com", "joaao.correia", "liaryzvazcmgudyr");
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		transport.close();
	}

}
