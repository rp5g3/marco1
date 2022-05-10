package com.adapit.portal.services.validation;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindException;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.adapit.portal.entidades.Cliente;
import com.adapit.portal.entidades.Projeto;
import com.adapit.portal.entidades.ScheduledTrainingStatus;
import com.adapit.portal.services.ProjetoService;
/**
 * @spring.bean id="projetoService" singleton="true"
 */
@SuppressWarnings({"serial","unchecked","unused","static-access"})

public class ProjetoServiceValidator extends AbstractValidator implements
		ProjetoService {

	private DefaultBeanValidator validator;

	private ProjetoService projetoService;

	public void setValidator(DefaultBeanValidator validator) {
		this.validator = validator;
	}

	public DefaultBeanValidator getValidator() {
		return this.validator;
	}

	public void setProjetoService(ProjetoService loteService) {
		this.projetoService = loteService;
	}

	/**
	 * @spring.property ref="projetoServiceDAOHibernate" singleton="true"
	 */
	public ProjetoService getProjetoService() {
		return this.projetoService;
	}

	public ProjetoServiceValidator() {
		super();
		setName("projetoServiceValidator");
	}

	/**
	 * Retorna todos os lotes que tiverem no código do lote o valor passado como
	 * argumento
	 */
	public List listLikeCodLote(String codLote) {
		try {
			return this.projetoService.listLikeCodLote(codLote);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Retorna todos os lotes em que o cõdigo termina com o valor passado como
	 * argumento
	 */
	public List listByCodLoteEndingWith(String codLote) {
		try {
			return this.projetoService.listByCodLoteEndingWith(codLote);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Retorna todos os lotes em que o código inicia com o valor passado como
	 * argumento
	 */
	public List listByCodLoteBeginingWith(String codLote) {
		try {
			return this.projetoService.listByCodLoteBeginingWith(codLote);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Retorna todos os lotes ou retirados ou não retirados
	 */
	public List listByRetirado(boolean retirado) {
		try {
			return this.projetoService.listByRetirado(retirado);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Retorna todos os lotes em que o status é igual ao valor passado como
	 * argumento
	 */
	public List listByStatus(ScheduledTrainingStatus status) {
		try {
			return this.projetoService.listByStatus(status);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Retorna todos os lotes de um comprador que possui no nome um valor igual
	 * ao do argumento passado como parâmetro
	 */
	public List listLikeNomeComprador(Cliente joincompraComprador) {
		try {
			return this.projetoService.listLikeNomeComprador(joincompraComprador);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Retorna todos os lotes de um comprador que possui o id igual ao do
	 * argumenta passado como parâmetro
	 */
	public List listByIdComprador(int joinPessoaId) {
		try {
			return this.projetoService.listByIdComprador(joinPessoaId);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Retorna todos os lotes de um determinado pessoaEmDivulgacao que possua no nome o
	 * valor passado como argumento
	 */
	public List listLikeNomeComitente(String joinPessoaNome) {
		try {
			return this.projetoService.listLikeNomeComitente(joinPessoaNome);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Retorna todos os lotes de um determinado pessoaEmDivulgacao pelo identificador do
	 * pessoaEmDivulgacao
	 */
	public List listByIdComitente(int joinPessoaId) {
		try {
			return this.projetoService.listByIdComitente(joinPessoaId);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public List listAll() {
		try {
			return this.projetoService.listAll();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public boolean saveOrUpdate(Projeto lote) throws Exception {
		System.out.println("saveOrUpdate VALIDACAO");
		try {
			BindException errors = new BindException(lote, "lote");
			validator.validate(lote, errors);
			if (errors.hasErrors()) {
				throw new Exception("exceptions.invalidFields");
			} else {
				try {
					return this.projetoService.saveOrUpdate(lote);
				} catch (DataIntegrityViolationException dive) {
					dive.printStackTrace();
					throw new Exception("lote.crudError");
				} catch (DataAccessException dae) {
					dae.printStackTrace();
					throw new Exception("exceptions.saveOrUpdate");
				}
			}
		} catch (Exception e) {
			// 
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Apaga o lote pelo identificador do lote
	 */
	public Projeto deleteById(int id) throws Exception {
		try {
			return this.projetoService.deleteById(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("deleteByIdError");
		}
	}

	/**
	 * apga o lote que possui o código igual ao valor passado como argumento
	 */
	public Projeto deleteByCodLote(String codLote) throws Exception {
		try {
			return this.projetoService.deleteByCodLote(codLote);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("deleteByCodLoteError");
		}
	}

	/**
	 * Apaga todos os lotes que forem de um pessoaEmDivulgacao. O pessoaEmDivulgacao éinformado de
	 * acordo com o identificador
	 */
	public Projeto deleteByIdComitente(int joinPessoaId) throws Exception {
		try {
			return this.projetoService.deleteByIdComitente(joinPessoaId);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("deleteByIdComitenteError");
		}
	}

	/**
	 * apaga todos os lotes que foram comprados por uma determinada pessoa. Esta
	 * pessoa é idetificado pelo parametro id.
	 */
	public Projeto deleteByIdParticipante(Cliente joincompraComprador)
			throws Exception {
		try {
			return this.projetoService.deleteByIdParticipante(joincompraComprador);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("deleteByIdParticipanteError");
		}
	}

	public Projeto getLoteById(int id) throws Exception {
		try {
			return this.projetoService.getLoteById(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("getLoteByIdError");
		}
	}

	public Projeto getLoteByCodLote(String codLote) throws Exception {
		try {
			return this.projetoService.getLoteByCodLote(codLote);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("getLoteByCodLoteError");
		}
	}
	
	@Override
	public boolean commitLance(int agendaid, int participanteid, float valor)
			throws Exception {
		try {
			return this.projetoService.commitLance(agendaid,participanteid,valor);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}
	


}