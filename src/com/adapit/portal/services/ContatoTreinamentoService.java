package com.adapit.portal.services;

import java.util.List;

import com.adapit.portal.entidades.ContatoProcessoTreinamento;
import com.adapit.portal.entidades.ContatoTreinamento;
import com.adapit.portal.entidades.Endereco;
import com.adapit.portal.entidades.TurmaTreinamento;
@SuppressWarnings({"serial","unchecked","unused","static-access"})
public interface ContatoTreinamentoService {

	public List<ContatoProcessoTreinamento> listAllProcessos(int firstResult) throws Exception;
	
	public int countAllProcessos() throws Exception;
	
	public List<ContatoProcessoTreinamento> listProcessosByNomeContato(String nome) throws Exception;
	
	public int countProcessosByNomeContato(String nome) throws Exception;
	
	public ContatoTreinamento getContatoByNomeCompleto(String nome) throws Exception;
	
	public void delete(ContatoTreinamento com) throws Exception;
	
	public void delete(ContatoProcessoTreinamento proc) throws Exception;
	
	public ContatoTreinamento save(ContatoTreinamento com, Endereco ender) throws Exception;
	
	public ContatoTreinamento update(ContatoTreinamento com, Endereco ender) throws Exception;
	
	public List<ContatoTreinamento> listAllContatos() throws Exception;
	
	public Object[] merge(ContatoTreinamento com, ContatoProcessoTreinamento proc) throws Exception;
	
	public ContatoTreinamento loadContato(int id) throws Exception;
	
	public ContatoProcessoTreinamento loadProcessoTreinamento(int id) throws Exception;
	
	public Object[] merge(ContatoTreinamento com, ContatoProcessoTreinamento processoJudicial, TurmaTreinamento leilao) throws Exception;
	
}
