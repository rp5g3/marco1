package com.adapit.portal.services.remote;


public interface GenericEmailSender {

	public void sendEmail(String nome, String email, String assunto, String message, String to) throws Exception;
}
