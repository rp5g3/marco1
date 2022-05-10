package com.adapit.portal.services;

import java.util.List;

import com.adapit.portal.entidades.Cliente;
import com.adapit.portal.entidades.Projeto;
import com.adapit.portal.entidades.ScheduledTrainingStatus;
@SuppressWarnings({"serial","unchecked","unused","static-access"})
public interface ProjetoService {

	/**
	 * Retorna todos os lotes que tiverem no código do lote o valor passado como
	 * argumento
	 */
	public List listLikeCodLote(String codLote);

	/**
	 * Retorna todos os lotes em que o cõdigo termina com o valor passado como
	 * argumento
	 */
	public List listByCodLoteEndingWith(String codLote);

	/**
	 * Retorna todos os lotes em que o código inicia com o valor passado como
	 * argumento
	 */
	public List listByCodLoteBeginingWith(String codLote);

	/**
	 * Retorna todos os lotes ou retirados ou não retirados
	 */
	public List listByRetirado(boolean retirado);

	/**
	 * Retorna todos os lotes em que o status é igual ao valor passado como
	 * argumento
	 */
	public List listByStatus(ScheduledTrainingStatus status);

	/**
	 * Retorna todos os lotes de um comprador que possui no nome um valor igual
	 * ao do argumento passado como parâmetro
	 */
	public List listLikeNomeComprador(Cliente joincompraComprador);

	/**
	 * Retorna todos os lotes de um comprador que possui o id igual ao do
	 * argumenta passado como parâmetro
	 */
	public List listByIdComprador(int joinPessoaId);

	/**
	 * Retorna todos os lotes de um determinado pessoaEmDivulgacao que possua no nome o
	 * valor passado como argumento
	 */
	public List listLikeNomeComitente(String joinPessoaNome);

	/**
	 * Retorna todos os lotes de um determinado pessoaEmDivulgacao pelo identificador do
	 * pessoaEmDivulgacao
	 */
	public List listByIdComitente(int joinPessoaId);

	public List listAll();

	public boolean saveOrUpdate(Projeto lote) throws Exception;

	/**
	 * Apaga o lote pelo identificador do lote
	 */
	public Projeto deleteById(int id) throws Exception;

	/**
	 * apga o lote que possui o código igual ao valor passado como argumento
	 */
	public Projeto deleteByCodLote(String codLote) throws Exception;

	/**
	 * Apaga todos os lotes que forem de um pessoaEmDivulgacao. O pessoaEmDivulgacao éinformado de
	 * acordo com o identificador
	 */
	public Projeto deleteByIdComitente(int joinPessoaId) throws Exception;

	/**
	 * apaga todos os lotes que foram comprados por uma determinada pessoa. Esta
	 * pessoa é idetificado pelo parametro id.
	 */
	public Projeto deleteByIdParticipante(Cliente joincompraComprador)
			throws Exception;

	public Projeto getLoteById(int id) throws Exception;

	public Projeto getLoteByCodLote(String codLote) throws Exception;
	
	public boolean commitLance(int agendaid, int participanteid, float valor) throws Exception;
	
	

}