package com.adapit.portal.services;

import java.util.Date;
import java.util.List;

import com.adapit.portal.entidades.CondicaoPagamento;
import com.adapit.portal.entidades.Participante;
import com.adapit.portal.entidades.ParticipanteContaPagar;
import com.adapit.portal.entidades.Treinamento;
import com.adapit.portal.services.validation.ValidationException;

public interface ContaService {

	public Object[] loadEncerramentoValuesByAgendaId(int idAgenda) throws Exception, ValidationException;
	
	public List<ParticipanteContaPagar> gerarContasPagar(boolean avista, float valorTotal,
			CondicaoPagamento condicao,
			Participante cliente, int dia, 
			int carencias, int numeroPrest, 
			int entradas) throws Exception;

	public CondicaoPagamento loadCondicaoPagamento(int idAgenda) throws Exception;

	public int countParticipanteContaPagarByAgenda(int idAgenda) throws Exception;
	
	public List<Treinamento> listLotesParaContasPagar(int idLeilao) throws Exception;
	
	public boolean isQuitadoWhenUpdatingContaPagar(ParticipanteContaPagar conta) throws Exception;
	
	public List<Object[]> listContasPagarValues(int idAgenda, boolean todasContas) throws Exception;

	public void updateContaPagarValor(int idConta, float valor) throws Exception;

	public void updateContaPagarDataVencimento(int idConta, Date dt) throws Exception;
	
}
