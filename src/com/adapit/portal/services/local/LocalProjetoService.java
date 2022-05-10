package com.adapit.portal.services.local;

import java.util.List;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import com.adapit.portal.entidades.Cliente;
import com.adapit.portal.entidades.Projeto;
import com.adapit.portal.entidades.ScheduledTrainingStatus;
import com.adapit.portal.services.ProjetoService;
@SuppressWarnings({"serial","unchecked","unused","static-access"})
public class LocalProjetoService implements ProjetoService {

	private ProjetoService loteService;

	private static LocalProjetoService instance;

	private LocalProjetoService() {
		try {
			XmlBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("localServices.xml"));
			loteService = (ProjetoService) beanFactory.getBean("loteServiceDAOHibernate");//new LoteServiceDAOHibernate();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static LocalProjetoService getInstance() {
		if (instance == null) {
			instance = new LocalProjetoService();
		}
		return instance;
	}

	/**
	 * Retorna todos os lotes que tiverem no código do lote o valor passado como
	 * argumento
	 */
	public List listLikeCodLote(String codLote) {
		try {
			return this.loteService.listLikeCodLote(codLote);
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
			return this.loteService.listByCodLoteEndingWith(codLote);
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
			return this.loteService.listByCodLoteBeginingWith(codLote);
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
			return this.loteService.listByRetirado(retirado);
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
			return this.loteService.listByStatus(status);
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
			return this.loteService.listLikeNomeComprador(joincompraComprador);
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
			return this.loteService.listByIdComprador(joinPessoaId);
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
			return this.loteService.listLikeNomeComitente(joinPessoaNome);
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
			return this.loteService.listByIdComitente(joinPessoaId);
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	public List listAll() {
		try {
			return this.loteService.listAll();
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	public boolean saveOrUpdate(Projeto lote) throws Exception {
		try {
			return this.loteService.saveOrUpdate(lote);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("RemoteLoteService_saveOrUpdateError");
		}
	}

	/**
	 * Apaga o lote pelo identificador do lote
	 */
	public Projeto deleteById(int id) throws Exception {
		try {
			return this.loteService.deleteById(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("RemoteLoteService_deleteByIdError");
		}
	}

	/**
	 * apga o lote que possui o código igual ao valor passado como argumento
	 */
	public Projeto deleteByCodLote(String codLote) throws Exception {
		try {
			return this.loteService.deleteByCodLote(codLote);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("RemoteLoteService_deleteByCodLoteError");
		}
	}

	/**
	 * Apaga todos os lotes que forem de um pessoaEmDivulgacao. O pessoaEmDivulgacao éinformado de
	 * acordo com o identificador
	 */
	public Projeto deleteByIdComitente(int joinPessoaId) throws Exception {
		try {
			return this.loteService.deleteByIdComitente(joinPessoaId);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("RemoteLoteService_deleteByIdComitenteError");
		}
	}

	/**
	 * apaga todos os lotes que foram comprados por uma determinada pessoa. Esta
	 * pessoa é idetificado pelo parametro id.
	 */
	public Projeto deleteByIdParticipante(Cliente joincompraComprador)
			throws Exception {
		try {
			return this.loteService.deleteByIdParticipante(joincompraComprador);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("RemoteLoteService_deleteByIdParticipanteError");
		}
	}

	public Projeto getLoteById(int id) throws Exception {
		try {
			return this.loteService.getLoteById(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("RemoteLoteService_getLoteByIdError");
		}
	}

	public Projeto getLoteByCodLote(String codLote) throws Exception {
		try {
			return this.loteService.getLoteByCodLote(codLote);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("RemoteLoteService_getLoteByCodLoteError");
		}
	}
	
	@Override
	public boolean commitLance(int agendaid, int participanteid, float valor)
			throws Exception {
		try {
			return this.loteService.commitLance(agendaid,participanteid,valor);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}
	


}