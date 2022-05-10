package com.adapit.portal.services.validation;

import java.util.Date;
import java.util.List;

import org.springmodules.validation.commons.DefaultBeanValidator;

import com.adapit.portal.entidades.CondicaoPagamento;
import com.adapit.portal.entidades.Participante;
import com.adapit.portal.entidades.ParticipanteContaPagar;
import com.adapit.portal.entidades.Treinamento;
import com.adapit.portal.services.ContaService;

/**
 * @spring.bean id="contaService" singleton="true"
 */
public class ContaServiceValidator extends AbstractValidator implements ContaService{
	private DefaultBeanValidator validator;

	private ContaService contaService;

	public void setValidator(DefaultBeanValidator validator) {
		this.validator = validator;
	}

	public DefaultBeanValidator getValidator() {
		return this.validator;
	}

	public void setContaService(ContaService service) {
		this.contaService = service;
	}

	/**
	 * @spring.property ref="contaServiceDAOHibernate" singleton="true"
	 */
	public ContaService getContaService() {
		return this.contaService;
	}

	public ContaServiceValidator() {
		super();
		setName("contaServiceValidator");
	}
	
	@Override
	public Object[] loadEncerramentoValuesByAgendaId(int idAgenda)
			throws Exception, ValidationException {
		return contaService.loadEncerramentoValuesByAgendaId(idAgenda);
	}
	
	@Override
	public List<ParticipanteContaPagar> gerarContasPagar(boolean avista,
			float valorTotal, CondicaoPagamento condicao, Participante cliente,
			int dia, int carencias, int numeroPrest, int entradas)
			throws Exception {
		return contaService.gerarContasPagar(avista, valorTotal, condicao, cliente, dia, carencias, numeroPrest, entradas);
	}

	@Override
	public int countParticipanteContaPagarByAgenda(int idAgenda)
			throws Exception {
		return contaService.countParticipanteContaPagarByAgenda(idAgenda);
	}

	@Override
	public CondicaoPagamento loadCondicaoPagamento(int idAgenda)
			throws Exception {
		return contaService.loadCondicaoPagamento(idAgenda);
	}
	
	@Override
	public List<Treinamento> listLotesParaContasPagar(int idLeilao) throws Exception {
		return contaService.listLotesParaContasPagar(idLeilao);
	}

	@Override
	public boolean isQuitadoWhenUpdatingContaPagar(ParticipanteContaPagar conta)
			throws Exception {
		return contaService.isQuitadoWhenUpdatingContaPagar(conta);
	}

	@Override
	public List<Object[]> listContasPagarValues(int idAgenda,
			boolean todasContas) throws Exception {
		return contaService.listContasPagarValues(idAgenda, todasContas);
	}

	@Override
	public void updateContaPagarDataVencimento(int idConta, Date dt)
			throws Exception {
		contaService.updateContaPagarDataVencimento(idConta, dt);
	}

	@Override
	public void updateContaPagarValor(int idConta, float valor)
			throws Exception {
		contaService.updateContaPagarValor(idConta, valor);
	}
}
