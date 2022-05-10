package com.adapit.portal.services.mail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.DataHandler;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.adapit.portal.entidades.ComercialSolution;
import com.adapit.portal.entidades.News;
import com.adapit.portal.entidades.Update;
import com.adapit.portal.entidades.Usuario;
import com.adapit.portal.services.validation.UpdateServiceValidator;
import com.adapit.portal.util.global.GlobalVariables;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.WebResourceMessage;
import com.workcase.utils.DatePropertyEditor;

public class UpdateVersionMailSenderImpl implements UpdateVersionMailSender {
	private MailSender mailSender;
	private final String userSenderMail = "adapitsys@gmail.com";

	private VelocityEngine velocityEngine;

	@SuppressWarnings("unused")
	private ResourceMessage messages;

	private UpdateServiceValidator updateService;

	private DatePropertyEditor datePropertyEditor;

	public UpdateVersionMailSenderImpl() {
		messages = WebResourceMessage.getInstance();
		datePropertyEditor = new DatePropertyEditor();
	}

	@SuppressWarnings("unchecked")
	public void sendMail(List<Usuario> usuarios, ComercialSolution sol,
			Update update, String sigla) throws Exception {

		JavaMailSenderImpl sender = (JavaMailSenderImpl) mailSender;

		if (usuarios != null && usuarios.size() > 0) {
			for (Usuario usuario : usuarios) {

				MimeMessage message = sender.createMimeMessage();

				MimeMessageHelper helper = new MimeMessageHelper(message,
						"ISO-8859-1");
				
				
				helper.setTo(usuario.getDadosPessoais().getEmail());
				helper.setFrom(userSenderMail);
				helper.setSubject("Notificação de atualização do sistema "
						+ sol.getNome());

				Map model = new HashMap();
				model.put("adapitsite", GlobalVariables.URL);
				model.put("sigla", sigla);
				model.put("updateBean", update);
				model.put("part_id", usuario.getDadosPessoais().getId());
				datePropertyEditor.setValue(update.getDataPublicacao());
				model.put("dataPublicacao", datePropertyEditor.getAsText());
				model.put("address", GlobalVariables.ADAPIT_ADDRESS);
		        model.put("phone", GlobalVariables.ADAPIT_PHONE);
		        model.put("copyright", GlobalVariables.ADAPIT_COPYRIGHT);

				String textVelocity = VelocityEngineUtils
						.mergeTemplateIntoString(
								velocityEngine,
								"com/adapit/portal/services/velocity/update_version.vm",
								model);
				
				message.setDataHandler(new DataHandler(new HTMLDataSource(
						textVelocity)));

				helper.setText(textVelocity, true);

				try {
					sender.send(message);
					System.out.println("Email enviado para " +
							usuario.getDadosPessoais().getEmail());
				} catch (MailException ex) {
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

	/**
	 * @spring.property ref="updateService" singleton="true"
	 */
	public UpdateServiceValidator getUpdateService() {
		return updateService;
	}

	public void setUpdateService(UpdateServiceValidator updateService) {
		this.updateService = updateService;
	}


	@SuppressWarnings("unchecked")
	@Override
	public void sendMail(List<Usuario> usuarios, List<News> newsList) throws Exception {
		JavaMailSenderImpl sender = (JavaMailSenderImpl) mailSender;

		

		if (usuarios != null && usuarios.size() > 0) {
			for (Usuario usuario : usuarios) {

				MimeMessage message = sender.createMimeMessage();

				MimeMessageHelper helper = new MimeMessageHelper(message,
						"ISO-8859-1");
				
				
				helper.setTo(usuario.getDadosPessoais().getEmail());
				helper.setFrom(userSenderMail);
				helper.setSubject("Notícias Adapit Soluções em TI");
		
				Map model = new HashMap();
				model.put("adapitsite", GlobalVariables.URL);
				model.put("newsList", newsList);
				model.put("part_id", usuario.getDadosPessoais().getId());
				model.put("address", GlobalVariables.ADAPIT_ADDRESS);
		        model.put("phone", GlobalVariables.ADAPIT_PHONE);
		        model.put("copyright", GlobalVariables.ADAPIT_COPYRIGHT);
		        
				String textVelocity = VelocityEngineUtils
						.mergeTemplateIntoString(
								velocityEngine,
								"com/adapit/portal/services/velocity/news.vm",
								model);
				
				message.setDataHandler(new DataHandler(new HTMLDataSource(
						textVelocity)));

				helper.setText(textVelocity, true);

				try {
					sender.send(message);
					System.out.println("Email enviado para " +
							usuario.getDadosPessoais().getEmail());
				} catch (MailException ex) {
					System.err.println(ex.getMessage());
				}
			}
		}
	}
}
