package com.adapit.portal.services.remote;

import org.springframework.beans.factory.xml.XmlBeanFactory;

import com.adapit.portal.services.mail.GenericEmailSender;
import com.workcase.gui.utils.SwingContext;

public class RemoteGenericMailService implements GenericEmailSender {

	private GenericEmailSender mailService;

	private static RemoteGenericMailService instance;

	private  RemoteGenericMailService() {
		try {
			XmlBeanFactory beanFactory = SwingContext.getInstance()
					.getBeanFactory();
			mailService = (GenericEmailSender) beanFactory
					.getBean("remoteGenericEmailServiceHttpInvokerProxy");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static RemoteGenericMailService getInstance() {
		if (instance == null) {
			instance = new RemoteGenericMailService();
		}
		return instance;
	}

	@Override
	public void sendEmail(String nome, String email, String assunto,
			String message, String to) throws Exception {
		mailService.sendEmail(nome, email, assunto, message, to);
	}

}
