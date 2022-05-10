package com.adapit.portal.services.mail;

import javax.activation.FileDataSource;
import javax.mail.internet.MimeMessage;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.adapit.portal.entidades.Participante;
import com.adapit.portal.entidades.ParticipanteContaPagar;
import com.adapit.portal.services.local.LocalServicesUtility;
import com.adapit.portal.util.global.GlobalVariables;
import com.workcase.gui.utils.WebResourceMessage;

public class BoletoContaMailServiceImpl  implements BoletoContaMailService {
	private MailSender mailSender;
	private final String userSenderMail="fabiopbasso@gmail.com";
	private final String userSenderPwd="48gT2C3n";
	
	public BoletoContaMailServiceImpl() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public void sendBoleto(Participante arrematante, ParticipanteContaPagar conta) throws Exception {		
		Session s = null;		
		String email = null;
		String serverDir = "."+WebResourceMessage.getInstance().getMessage("boletosurl");//GlobalVariables.BoletoServerFileDir;
		try{
			s = LocalServicesUtility.getInstance().openSession();			
			
			try {
				Query query = s.createQuery("select u.dadosPessoais.email from Usuario u where u.dadosPessoais.id ="+arrematante.getId());
				email = (String) query.uniqueResult();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
		
		JavaMailSenderImpl sender = (JavaMailSenderImpl) mailSender;

		MimeMessage message = sender.createMimeMessage();
		
		
		MimeMessageHelper helper = new MimeMessageHelper(message,true);
		String toMessage=email;
		
		System.out.println("Para: "+toMessage);
		helper.setTo(toMessage);
		
		helper.setFrom(userSenderMail);
		helper.setSubject("Boleto N:"+conta.getId());

		String text="";
		text+="<br><br>";
		text+='<'+"html"+'>'+""+'<'+"body"+'>'+"";
		
		text+='\n'+""+'<'+"br"+'>'+"O boleto para pagemento segue em anexo ";
		
		text+="<p>Para mais informações consulte <a href=\"http://www.cargneluttileiloes.com.br\" target=\"blanck\" >"+"<b>www.cargneluttileiloes.com.br</b></a>.</p>";
		text+='<'+"/body"+'>'+""+'<'+"/html"+'>'+"";
		helper.setText(text,true);
		try{
			String fileName="boleto_Conta_"+conta.getId()+"_Arrem_"+arrematante.getId()+".pdf";
			String fileStr = serverDir+fileName;
	    	//ContaBoletoGenerator.generateBoleto(arrematante,conta, lote, fileStr,false);
			helper.addAttachment(fileName, new FileDataSource(fileStr));			
			
			sender.send(message);
			System.out.println("Email enviado com sucesso");
		}
		catch(MailException ex) {
			System.err.println(ex.getMessage());
			throw ex;
		}
	}

	public MailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

}
