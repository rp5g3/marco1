package com.adapit.portal.services.remote;

import org.springframework.beans.factory.xml.XmlBeanFactory;

import com.adapit.portal.entidades.Participante;
import com.adapit.portal.entidades.Usuario;
import com.adapit.portal.services.mail.AdapitAutenticateUserMailService;
import com.workcase.gui.utils.SwingContext;

public class RemoteAdapitAutenticateUserService implements AdapitAutenticateUserMailService {

	private AdapitAutenticateUserMailService mailService;

	private static RemoteAdapitAutenticateUserService instance;

	private RemoteAdapitAutenticateUserService() {
		try {
			XmlBeanFactory beanFactory = SwingContext.getInstance()
					.getBeanFactory();
			mailService = (AdapitAutenticateUserMailService) beanFactory
					.getBean("remoteLeilaoMailServiceHttpInvokerProxy");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static RemoteAdapitAutenticateUserService getInstance() {
		if (instance == null) {
			instance = new RemoteAdapitAutenticateUserService();
		}
		return instance;
	}

	public void sendAutenticateUserMsg(Usuario usuario) throws Exception {
		try {
			mailService.sendAutenticateUserMsg(usuario);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public AdapitAutenticateUserMailService getMailService() {
		return mailService;
	}

	public void setMailService(AdapitAutenticateUserMailService mailService) {
		this.mailService = mailService;
	}

	@Override
	public void sendInvitationMsg(Participante part, String[] emails, String msg)
			throws Exception {
		mailService.sendInvitationMsg(part, emails,msg);
	}

}
