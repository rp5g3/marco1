package com.adapit.portal.services.remote;

import java.util.List;

import org.springframework.beans.factory.xml.XmlBeanFactory;

import com.adapit.portal.entidades.Cliente;
import com.adapit.portal.entidades.Projeto;
import com.adapit.portal.entidades.ScheduledTrainingStatus;
import com.adapit.portal.services.ProjetoService;
import com.workcase.gui.utils.SwingContext;
@SuppressWarnings({"serial","unchecked","unused","static-access"})
public class RemoteProjetoService implements ProjetoService {

	private ProjetoService projetoService;

	private static RemoteProjetoService instance;

	private RemoteProjetoService() {
		try {
			XmlBeanFactory beanFactory = SwingContext.getInstance()
					.getBeanFactory();
			projetoService = (ProjetoService) beanFactory
					.getBean("remoteProjetoServiceHttpInvokerProxy");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static RemoteProjetoService getInstance() {
		if (instance == null) {
			instance = new RemoteProjetoService();
		}
		return instance;
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
		try {
			return this.projetoService.saveOrUpdate(lote);
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
			return this.projetoService.deleteById(id);
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
			return this.projetoService.deleteByCodLote(codLote);
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
			return this.projetoService.deleteByIdComitente(joinPessoaId);
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
			return this.projetoService.deleteByIdParticipante(joincompraComprador);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("RemoteLoteService_deleteByIdParticipanteError");
		}
	}

	public Projeto getLoteById(int id) throws Exception {
		try {
			return this.projetoService.getLoteById(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("RemoteLoteService_getLoteByIdError");
		}
	}

	public Projeto getLoteByCodLote(String codLote) throws Exception {
		try {
			return this.projetoService.getLoteByCodLote(codLote);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("RemoteLoteService_getLoteByCodLoteError");
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