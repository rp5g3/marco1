package com.adapit.portal.services.mail;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.adapit.portal.entidades.Fisica;
import com.adapit.portal.entidades.Participante;
import com.adapit.portal.entidades.Sexo;
import com.adapit.portal.entidades.Usuario;
import com.adapit.portal.util.global.GlobalVariables;

public class AdapitAutenticateUserMailServiceImpl implements AdapitAutenticateUserMailService {
	private MailSender mailSender;
	private final String userSenderMail="adapitsys@gmail.com";
	//private final String userSenderPwd="aJeC27RM";
	
	private VelocityEngine velocityEngine;

	@SuppressWarnings("unchecked")
	@Override
	public void sendAutenticateUserMsg(Usuario usuario) throws Exception {

		JavaMailSenderImpl sender = (JavaMailSenderImpl) mailSender;

		MimeMessage message = sender.createMimeMessage();
				
		MimeMessageHelper helper = new MimeMessageHelper(message,"ISO-8859-1");
		
		helper.setTo(usuario.getDadosPessoais().getEmail());
		helper.setFrom(userSenderMail);
		helper.setSubject("Autenticação de cadastro no site Adapit Soluções em TI");

		String text="";
		if (usuario.getDadosPessoais().getTipoPessoa() instanceof Fisica){
			Fisica f = (Fisica) usuario.getDadosPessoais().getTipoPessoa();
			String sexo=(f.getSexo() == Sexo.Masculino?"o":"a");
			text+="Car"+sexo+" " + usuario.getDadosPessoais().getNome();
			text+=" "+((Fisica)usuario.getDadosPessoais().getTipoPessoa()).getSobrenome();
			text+=", você foi cadastrad"+sexo+" no sistema da <b>Adapit Soluções em TI</b>.";
		}else{
			text+="Estimada empresa " + usuario.getDadosPessoais().getNome();
			text+=", você foi cadastrada no sistema da <b>Adapit Soluções em TI</b>.";
		}
		
		Map model = new HashMap();
        model.put("usuario", usuario);
        model.put("welcome_message", text);
        model.put("URL", GlobalVariables.URL);
        model.put("adapitsite", GlobalVariables.URL);
        model.put("part_id", usuario.getDadosPessoais().getId());
        model.put("address", GlobalVariables.ADAPIT_ADDRESS);
        model.put("phone", GlobalVariables.ADAPIT_PHONE);
        model.put("copyright", GlobalVariables.ADAPIT_COPYRIGHT);
        //java.io.File f = new java.io.File(".");
        //System.out.println(f.getAbsolutePath());
        String textVelocity = VelocityEngineUtils.mergeTemplateIntoString(
           velocityEngine, "com/adapit/portal/services/velocity/registration_confirmation.vm", model);

		helper.setText(textVelocity,true);
		try{
			sender.send(message);
		}
		catch(MailException ex) {
			System.err.println(ex.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void sendInvitationMsg(Participante part, String emails[], String recText) throws Exception {

		JavaMailSenderImpl sender = (JavaMailSenderImpl) mailSender;

				
		
		String msg="<p>Olá. ";
		
		if (part != null && part.getTipoPessoa() instanceof Fisica){
			//Fisica f = (Fisica) part.getTipoPessoa();
			String amigo="";//"Seu amigo";
			/*try {
				if(((Fisica)part.getTipoPessoa()).getSexo() == Sexo.Feminino)
					amigo="Sua amiga";
			} catch (Exception e) {
				e.printStackTrace();
			}*/
			msg+= amigo+" <i>"+part.getNome();
			msg+=" "+((Fisica)part.getTipoPessoa()).getSobrenome();
			msg+="</i> recomendou você como um contato.</p>";
			
		}
		else msg+=" Um amigo recomendou você como um contato.";
		/*if(recText != null){
			msg+="<div class=\"default_content_pane\">";
			msg+="<p>"+recText+"</p>";
			msg+="</div>";
		}*/
		recText="";
		
		String text="";
		text+="<p>Somos a <b>Adapit Soluções em TI</b>." +
		"</p>";
		text+="<p>Trabalhamos com um sistema de auxílio à produção de aplicativos web e desktop denominado " +
				"Work Case Toolkit (WCT). " +
		" Ele possibilita produzir rapidamente algumas funcionalidades de suas aplicações utilizando a sua arquitetura, " +
		"os seus padrões e as suas práticas de desenvolvimento."; 
		/*"Sendo um sistema aderente com processos ágeis, ele deve ser adaptado para gerar o" +
		" código-fonte nos seus padrões de qualidade. Dessa forma, permite agregar produtividade ao trabalho do programador," +
		" sendo um complemento ao seu trabalho diário." +*/
		text+=" WCT é um prototipador de funcionalidades para CRUDs, filtros e associações, " +
				"que permite gerar código-fonte para Java." +
		" Características técnicas: interfaces de usuário web com Ajax;" +
		" implementação das funcionalidades com base em padrões de projeto como" +
		" MVC, Factory, Abstract Factory, " +
		" Facade e outros.</p>"; 
		text+=
		"<p>Ruby on Rails já fez isso e nós decidimos fazer o mesmo para uma abordagem" +
		" independente de arquitetura. Venha conhecer o <a class=\"red_link\" href=\"http://"
		+GlobalVariables.URL+"/portal.html?sys=wct\" target=\"blank\">WCT</a>, ele está disponível para download" +
		" em nossa página e pode ser testado gratuitamente.";
		
		Map model = new HashMap();
        model.put("welcome_message", text);
        model.put("URL", GlobalVariables.URL);
        model.put("adapitsite", GlobalVariables.URL);
        model.put("recText",msg);
        model.put("address", GlobalVariables.ADAPIT_ADDRESS);
        model.put("phone", GlobalVariables.ADAPIT_PHONE);
        model.put("copyright", GlobalVariables.ADAPIT_COPYRIGHT);
        
        if(emails != null && emails.length>0){
        	for(String email:emails){
	        	MimeMessage message = sender.createMimeMessage();
	    		
	        	MimeMessageHelper helper = new MimeMessageHelper(message,"ISO-8859-1");
			
				helper.setFrom(userSenderMail);
				helper.setSubject("Conheça a Empresa Adapit Soluções em TI");
		
		        helper.setTo(email.replace(" ", "").trim());
				
		        String textVelocity = VelocityEngineUtils.mergeTemplateIntoString(
		           velocityEngine, "com/adapit/portal/services/velocity/invitation.vm", model);
		
				helper.setText(textVelocity,true);
				try{
					sender.send(message);
				}
				catch(MailException ex) {
					System.err.println(ex.getMessage());
				}
        	}
        }
	}

	public MailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}
}
