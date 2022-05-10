package com.adapit.portal.services.remote;

import org.springframework.beans.factory.xml.XmlBeanFactory;

import com.adapit.portal.services.mail.NovaSenhaMailService;
import com.workcase.gui.utils.SwingContext;

public class RemoteChangePasswordMailService implements NovaSenhaMailService {

	private NovaSenhaMailService mailService;

	private static RemoteChangePasswordMailService instance;

	private RemoteChangePasswordMailService() {
		try {
			XmlBeanFactory beanFactory = SwingContext.getInstance()
					.getBeanFactory();
			mailService = (NovaSenhaMailService) beanFactory
					.getBean("remoteNovaSenhaMailServiceHttpInvokerProxy");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static RemoteChangePasswordMailService getInstance() {
		if (instance == null) {
			instance = new RemoteChangePasswordMailService();
		}
		return instance;
	}

	public boolean changePasswordByEmail(String email) throws Exception {
		try {
			return mailService.changePasswordByEmail(email);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public NovaSenhaMailService getMailService() {
		return mailService;
	}

	public void setMailService(NovaSenhaMailService mailService) {
		this.mailService = mailService;
	}



}
