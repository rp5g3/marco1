package com.adapit.portal.services.mail;

import com.adapit.portal.entidades.Participante;
import com.adapit.portal.entidades.ParticipanteContaPagar;

public interface BoletoContaMailService {

	public void sendBoleto(Participante arrematante,ParticipanteContaPagar conta) throws Exception ;
	
}
