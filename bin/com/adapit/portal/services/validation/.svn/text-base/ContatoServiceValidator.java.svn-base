package com.adapit.portal.services.validation;

import java.util.List;

import org.springmodules.validation.commons.DefaultBeanValidator;

import com.adapit.portal.entidades.ContatoProcessoTreinamento;
import com.adapit.portal.entidades.ContatoTreinamento;
import com.adapit.portal.entidades.Endereco;
import com.adapit.portal.entidades.TurmaTreinamento;
import com.adapit.portal.services.ContatoTreinamentoService;
/**
 * @spring.bean id="contatoService" singleton="true"
 */
@SuppressWarnings({"serial","unchecked","unused","static-access"})

public class ContatoServiceValidator extends AbstractValidator implements ContatoTreinamentoService{
	private DefaultBeanValidator validator;

	private ContatoTreinamentoService contatoService;

	public void setValidator(DefaultBeanValidator validator) {
		this.validator = validator;
	}

	public DefaultBeanValidator getValidator() {
		return this.validator;
	}

	public void setContatoService(ContatoTreinamentoService imService) {
		this.contatoService = imService;
	}

	/**
	 * @spring.property ref="contatoServiceDAOHibernate" singleton="true"
	 */
	public ContatoTreinamentoService getContatoService() {
		return this.contatoService;
	}

	public ContatoServiceValidator() {
		super();
		setName("contatoServiceValidator");
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
