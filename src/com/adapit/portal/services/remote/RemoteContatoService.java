package com.adapit.portal.services.remote;

import java.util.List;

import org.springframework.beans.factory.xml.XmlBeanFactory;

import com.adapit.portal.entidades.ContatoProcessoTreinamento;
import com.adapit.portal.entidades.ContatoTreinamento;
import com.adapit.portal.entidades.Endereco;
import com.adapit.portal.entidades.TurmaTreinamento;
import com.adapit.portal.services.ContatoTreinamentoService;
import com.workcase.gui.utils.SwingContext;
@SuppressWarnings({"serial","unchecked","unused","static-access"})
public class RemoteContatoService implements ContatoTreinamentoService{

	private ContatoTreinamentoService contatoService;

	private static RemoteContatoService instance;

	private RemoteContatoService() {
		try {
			XmlBeanFactory beanFactory = SwingContext.getInstance()
					.getBeanFactory();
			contatoService = (ContatoTreinamentoService) beanFactory
					.getBean("remoteContatoServiceHttpInvokerProxy");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static RemoteContatoService getInstance() {
		if (instance == null) {
			instance = new RemoteContatoService();
		}
		return instance;
	}

	@Override
	public int countAllProcessos() throws Exception {
		return contatoService.countAllProcessos();
	}

	@Override
	public int countProcessosByNomeContato(String nome) throws Exception {
		return contatoService.countProcessosByNomeContato(nome);
	}

	@Override
	public void delete(ContatoTreinamento com) throws Exception {
		contatoService.delete(com);
	}

	@Override
	public void delete(ContatoProcessoTreinamento proc) throws Exception {
		contatoService.delete(proc);
	}

	@Override
	public ContatoTreinamento getContatoByNomeCompleto(String nome) throws Exception {
		return contatoService.getContatoByNomeCompleto(nome);
	}

	@Override
	public List<ContatoTreinamento> listAllContatos() throws Exception {
		return contatoService.listAllContatos();
	}

	@Override
	public List<ContatoProcessoTreinamento> listAllProcessos(int firstResult)
			throws Exception {
		return contatoService.listAllProcessos(firstResult);
	}

	@Override
	public List<ContatoProcessoTreinamento> listProcessosByNomeContato(String nome)
			throws Exception {
		return contatoService.listProcessosByNomeContato(nome);
	}

	@Override
	public ContatoTreinamento loadContato(int id) throws Exception {
		return contatoService.loadContato(id);
	}

	@Override
	public ContatoProcessoTreinamento loadProcessoTreinamento(int id) throws Exception {
		return contatoService.loadProcessoTreinamento(id);
	}

	@Override
	public Object[] merge(ContatoTreinamento com, ContatoProcessoTreinamento proc) throws Exception {
		return contatoService.merge(com, proc);
	}

	@Override
	public ContatoTreinamento save(ContatoTreinamento com, Endereco ender) throws Exception {
		return contatoService.save(com,ender);
	}

	@Override
	public ContatoTreinamento update(ContatoTreinamento com, Endereco ender) throws Exception {
		return contatoService.update(com,ender);
	}
/*
	@Override
	public Object[] merge(ContatoTreinamento com, ContatoProcessoTreinamento processoJudicial,
			Treinamento lote) throws Exception {
		return contatoService.merge(com, processoJudicial, lote);
	}*/

	@Override
	public Object[] merge(ContatoTreinamento com, ContatoProcessoTreinamento processoJudicial,
			TurmaTreinamento leilao) throws Exception {
		return contatoService.merge(com, processoJudicial, leilao);
	}
}
