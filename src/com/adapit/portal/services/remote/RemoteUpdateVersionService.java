package com.adapit.portal.services.remote;

import java.util.List;

import org.springframework.beans.factory.xml.XmlBeanFactory;

import com.adapit.portal.entidades.ComercialSolution;
import com.adapit.portal.entidades.News;
import com.adapit.portal.entidades.Update;
import com.adapit.portal.entidades.Usuario;
import com.adapit.portal.services.mail.GenericEmailSender;
import com.adapit.portal.services.mail.UpdateVersionMailSender;
import com.workcase.gui.utils.SwingContext;

public class RemoteUpdateVersionService  implements UpdateVersionMailSender {

	private UpdateVersionMailSender mailService;

	private static RemoteUpdateVersionService instance;

	private  RemoteUpdateVersionService() {
		try {
			XmlBeanFactory beanFactory = SwingContext.getInstance()
					.getBeanFactory();
			mailService = (UpdateVersionMailSender) beanFactory
					.getBean("remoteUpdateVersionMailSenderHttpInvokerProxy");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static RemoteUpdateVersionService getInstance() {
		if (instance == null) {
			instance = new RemoteUpdateVersionService();
		}
		return instance;
	}

	@Override
	public void sendMail(List<Usuario> usuario, ComercialSolution sol,
			Update update, String sigla) throws Exception {
		mailService.sendMail(usuario, sol, update, sigla);
	}

	@Override
	public void sendMail(List<Usuario> usuario, List<News> newsList)
			throws Exception {
		mailService.sendMail(usuario, newsList);
	}


}
