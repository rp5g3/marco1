package com.adapit.portal.services.remote;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.xml.XmlBeanFactory;

import com.adapit.portal.entidades.CondicaoPagamento;
import com.adapit.portal.entidades.Participante;
import com.adapit.portal.entidades.ParticipanteContaPagar;
import com.adapit.portal.entidades.Treinamento;
import com.adapit.portal.services.ContaService;
import com.adapit.portal.services.validation.ValidationException;
import com.workcase.gui.utils.SwingContext;

public class RemoteContaService implements ContaService{

	private ContaService contaService;

	private static RemoteContaService instance;

	private RemoteContaService() {
		try {
			XmlBeanFactory beanFactory = SwingContext.getInstance()
					.getBeanFactory();
			contaService = (ContaService) beanFactory
					.getBean("remoteContaServiceHttpInvokerProxy");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static RemoteContaService getInstance() {
		if (instance == null) {
			instance = new RemoteContaService();
		}
		return instance;
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
