package com.adapit.portal.services.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.adapit.portal.entidades.Cliente;
import com.adapit.portal.entidades.Projeto;
import com.adapit.portal.entidades.ScheduledTrainingStatus;
import com.adapit.portal.services.ProjetoService;
import com.workcase.hibernate.GenericDAO;
import com.workcase.hibernate.GenericDAOHibernate;

/**
 * @spring.bean id="projetoServiceDAOHibernate" singleton="true"
 * @@org.springframework.transaction.interceptor.DefaultTransactionAttribute(propagationBehaviorName="PROPAGATION_REQUIRED")
 */

public class ProjetoServiceDAOHibernate extends GenericDAOHibernate implements
		ProjetoService, GenericDAO {

	@SuppressWarnings("unused")
	private SessionFactory sessionFactory;

	private String entityName = Projeto.class.getSimpleName();

	public ProjetoServiceDAOHibernate() {

	}

	/**
	 * Retorna todos os lotes que tiverem no código do lote o valor passado como
	 * argumento
	 */
	@SuppressWarnings("unchecked")
	public List listLikeCodLote(String codLote) {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from " + entityName + " lote");
			hql.append(" where ");
			hql.append(" lote.codLote like ? order by lote.codLote");
			paramList.add("%" + codLote + "%");
			return getHibernateTemplate().find(hql.toString(),
					paramList.toArray());

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	/**
	 * Retorna todos os lotes em que o código termina com o valor passado como
	 * argumento
	 */
	@SuppressWarnings("unchecked")
	public List listByCodLoteEndingWith(String codLote) {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from " + entityName + " lote ");
			hql.append(" where ");
			hql.append(" lote.codLote like ? ");
			paramList.add("%" + codLote);
			return getHibernateTemplate().find(hql.toString(),
					paramList.toArray());

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	/**
	 * Retorna todos os lotes em que o código inicia com o valor passado como
	 * argumento
	 */
	@SuppressWarnings("unchecked")
	public List listByCodLoteBeginingWith(String codLote) {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from " + entityName + " lote ");
			hql.append(" where ");
			hql.append(" lote.codLote like ? ");
			paramList.add(codLote + "%");
			return getHibernateTemplate().find(hql.toString(),
					paramList.toArray());

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	/**
	 * Retorna todos os lotes ou retirados ou não retirados
	 */
	@SuppressWarnings("unchecked")
	public List listByRetirado(boolean retirado) {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from " + entityName + " lote ");
			hql.append(" where ");
			hql.append(" lote.retirado like ? ");
			paramList.add("%" + retirado + "%");
			return getHibernateTemplate().find(hql.toString(),
					paramList.toArray());

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	/**
	 * Retorna todos os lotes em que o status é igual ao valor passado como
	 * argumento
	 */
	@SuppressWarnings("unchecked")
	public List listByStatus(ScheduledTrainingStatus status) {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from " + entityName + " lote ");
			hql.append(" where ");
			hql.append(" lote.status = ? ");
			paramList.add("" + status + "");
			return getHibernateTemplate().find(hql.toString(),
					paramList.toArray());

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	/**
	 * Retorna todos os lotes de um comprador que possui no nome um valor igual
	 * ao do argumento passado como parâmetro
	 */
	@SuppressWarnings("unchecked")
	public List listLikeNomeComprador(Cliente joincompraComprador) {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from " + entityName + " lote ");
			hql.append(" where ");
			hql.append(" lote.comprador.nome like ? ");
			paramList.add("%" + joincompraComprador.getNome() + "%");
			return getHibernateTemplate().find(hql.toString(),
					paramList.toArray());

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	/**
	 * Retorna todos os lotes de um comprador que possui o id igual ao do
	 * argumenta passado como parâmetro
	 */
	@SuppressWarnings("unchecked")
	public List listByIdComprador(int joinPessoaId) {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from " + entityName + " lote ");
			hql.append(" where ");
			hql.append(" lote.comprador.id = ? ");
			paramList.add("" + joinPessoaId);
			return getHibernateTemplate().find(hql.toString(),
					paramList.toArray());

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	/**
	 * Retorna todos os lotes de um determinado pessoaEmDivulgacao que possua no nome o
	 * valor passado como argumento
	 */
	@SuppressWarnings("unchecked")
	public List listLikeNomeComitente(String joinPessoaNome) {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from " + entityName + " lote ");
			hql.append(" where ");
			hql.append(" lote.comitente.nome like ? ");
			paramList.add("%" + joinPessoaNome + "%");
			return getHibernateTemplate().find(hql.toString(),
					paramList.toArray());

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	/**
	 * Retorna todos os lotes de um determinado pessoaEmDivulgacao pelo identificador do
	 * pessoaEmDivulgacao
	 */
	@SuppressWarnings("unchecked")
	public List listByIdComitente(int joinPessoaId) {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from " + entityName + " lote ");
			hql.append(" where ");
			hql.append(" lote.comitente.id = ? ");
			paramList.add("" + joinPessoaId);
			return getHibernateTemplate().find(hql.toString(),
					paramList.toArray());

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List listAll() {
		try {
			HibernateTemplate template = new HibernateTemplate(
					getSessionFactory());
			return template.loadAll(Projeto.class);

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	public boolean saveOrUpdate(Projeto lote) throws Exception {
		System.out.println("saveOrUpdate HIBERNATE");
		try {
			HibernateTemplate template = new HibernateTemplate(
					getSessionFactory());
			template.saveOrUpdate(lote);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("LoteServiceDAOHibernate_saveOrUpdateError");
		}
	}

	/**
	 * Apaga o lote pelo identificador do lote
	 */
	@SuppressWarnings("unchecked")
	public Projeto deleteById(int id) throws Exception {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from " + entityName + " lote ");
			hql.append(" where ");
			hql.append(" lote.id = " + id + " ");
			Projeto lote = (Projeto) DataAccessUtils
					.uniqueResult(getHibernateTemplate().find(hql.toString(),
							paramList.toArray()));

			getHibernateTemplate().delete(lote);
			return lote;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("LoteServiceDAOHibernate_deleteByIdError");
		}
	}

	/**
	 * apaga o lote que possui o código igual ao valor passado como argumento
	 */
	@SuppressWarnings("unchecked")
	public Projeto deleteByCodLote(String codLote) throws Exception {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from " + entityName + " lote ");
			hql.append(" where ");
			hql.append(" lote.codLote = ? ");
			paramList.add("" + codLote + "");
			Projeto lote = (Projeto) DataAccessUtils
					.uniqueResult(getHibernateTemplate().find(hql.toString(),
							paramList.toArray()));

			getHibernateTemplate().delete(lote);
			return lote;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("LoteServiceDAOHibernate_deleteByCodLoteError");
		}
	}

	/**
	 * Apaga todos os lotes que forem de um pessoaEmDivulgacao. O pessoaEmDivulgacao é informado de
	 * acordo com o identificador
	 */
	@SuppressWarnings("unchecked")
	public Projeto deleteByIdComitente(int joinPessoaId) throws Exception {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from " + entityName + " lote ");
			hql.append(" where ");
			hql.append(" lote.comitente.id = ? ");
			paramList.add("" + joinPessoaId);
			Projeto lote = (Projeto) DataAccessUtils
					.uniqueResult(getHibernateTemplate().find(hql.toString(),
							paramList.toArray()));

			getHibernateTemplate().delete(lote);
			return lote;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(
					"LoteServiceDAOHibernate_deleteByIdComitenteError");
		}
	}

	/**
	 * apaga todos os lotes que foram comprados por uma determinada pessoa. Esta
	 * pessoa é idetificado pelo parametro id.
	 */
	@SuppressWarnings("unchecked")
	public Projeto deleteByIdParticipante(Cliente joincompraComprador)
			throws Exception {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from " + entityName + " lote ");
			hql.append(" where ");
			hql.append(" lote.comprador.id = ? ");
			paramList.add(""+joincompraComprador.getId());
			Projeto lote = (Projeto) DataAccessUtils
					.uniqueResult(getHibernateTemplate().find(hql.toString(),
							paramList.toArray()));

			getHibernateTemplate().delete(lote);
			return lote;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(
					"LoteServiceDAOHibernate_deleteByIdParticipanteError");
		}
	}

	@SuppressWarnings("unchecked")
	public Projeto getLoteById(int id) throws Exception {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from " + entityName + " lote ");
			hql.append(" where ");
			hql.append(" lote.id = " + id + " ");
			return (Projeto) DataAccessUtils.uniqueResult(getHibernateTemplate()
					.find(hql.toString(), paramList.toArray()));

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("LoteServiceDAOHibernate_getLoteByIdError");
		}
	}

	@SuppressWarnings("unchecked")
	public Projeto getLoteByCodLote(String codLote) throws Exception {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from " + entityName + " lote ");
			hql.append(" where ");
			hql.append(" lote.codLote = ? ");
			paramList.add("" + codLote + "");
			Projeto l = (Projeto) DataAccessUtils.uniqueResult(getHibernateTemplate()
					.find(hql.toString(), paramList.toArray()));
			
			//Hibernate.initialize(l.getItensProduto());
			
			return l; 

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("LoteServiceDAOHibernate_getLoteByCodLoteError");
		}
	}

	@Override
	public boolean commitLance(int agendaid, int participanteid, float valor)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	

}