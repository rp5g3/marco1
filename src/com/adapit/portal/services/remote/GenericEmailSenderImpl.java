package com.adapit.portal.services.remote;

import java.util.ArrayList;
import java.util.Iterator;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class GenericEmailSenderImpl  implements GenericEmailSender {
	private MailSender mailSender;
	
	public GenericEmailSenderImpl() {
	}



	public MailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Override
	public void sendEmail(String nome, String email, String assunto, String msg, String to) throws Exception {

		
		JavaMailSenderImpl sender = (JavaMailSenderImpl) mailSender;

		MimeMessage message = sender.createMimeMessage();
		
		
		MimeMessageHelper helper = new MimeMessageHelper(message,true);
		ArrayList<String> emails = new ArrayList<String>();
		if (to.indexOf(";")>0){
			String strs[] = to.split(";");
			if (strs.length>0){
				for(int i=0; i< strs.length; i++)
					emails.add(strs[i].replace(";", "").trim());
			}
			else emails.add(to.replace(";", ""));
			
			String toMessage="";
			if (emails != null && emails.size()>0){
				Iterator<String> it = emails.iterator();
				while(it.hasNext()){
					String str = it.next();
					System.out.println(str);
					if (str != null && !str.equals("")){
						toMessage+=str;
						if (it.hasNext()) toMessage+=", ";		
						helper.addTo(str);
					}				
				}
					
			}
		}else{
			if (to == null)
				helper.setTo("cargneluttileiloes@gmail.com");
			else helper.setTo(to);
		}
		System.out.println("Enviado por : "+nome);
		
		
		helper.setFrom(email);
		helper.setSubject(assunto);

		String text="<B>Contato:</B> "+nome+" <br>";
		text+="<B>Email:</B> "+email+" <br>";
		text+="<B>Assunto:</B> "+assunto+" <br>";
		text+=msg;
		helper.setText(text,true);
		
		try{
			
			sender.send(message);
			System.out.println("Email enviado com sucesso");
		}
		catch(MailException ex) {
			System.err.println(ex.getMessage());
			throw ex;
		}
	}
}
