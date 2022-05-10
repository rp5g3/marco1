package com.adapit.portal.services.dao.hibernate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.adapit.portal.entidades.AgendaTreinamento;
import com.adapit.portal.entidades.ComercialSolution;
import com.adapit.portal.entidades.ComercialSolutionItem;
import com.adapit.portal.entidades.FormacaoTreinamento;
import com.adapit.portal.entidades.Imagem;
import com.adapit.portal.entidades.Participante;
import com.adapit.portal.entidades.ScheduledTrainingStatus;
import com.adapit.portal.entidades.StatusAgenda;
import com.adapit.portal.entidades.TrainingFormationItem;
import com.adapit.portal.entidades.TrainingSolution;
import com.adapit.portal.entidades.Treinamento;
import com.adapit.portal.entidades.TurmaTreinamento;
import com.adapit.portal.services.ScheduledTrainingFilterType;
import com.adapit.portal.services.TreinamentoService;
import com.adapit.portal.services.local.LocalServicesUtility;
import com.adapit.portal.util.global.FilterResultSize;
import com.workcase.hibernate.GenericDAO;
import com.workcase.hibernate.GenericDAOHibernate;
/**
 * @spring.bean id="treinamentoServiceDAOHibernate" singleton="true"
 * @@org.springframework.transaction.interceptor.DefaultTransactionAttribute(propagationBehaviorName="PROPAGATION_REQUIRED")
 */
@SuppressWarnings({"serial","unchecked","unused","static-access"})

public class TreinamentoServiceDAOHibernate extends GenericDAOHibernate implements
		TreinamentoService, GenericDAO {

	@SuppressWarnings("unused")
	private SessionFactory sessionFactory;

	private String entityName = Treinamento.class.getSimpleName();	
	private String agendaTreinamento = AgendaTreinamento.class.getSimpleName();	
	private String comercialSolutionItem = ComercialSolutionItem.class.getSimpleName();	
	private String comercialSolution = ComercialSolution.class.getSimpleName();
	private String trainingFormationItem = TrainingFormationItem.class.getSimpleName();
	private String formacaoTreinamento = FormacaoTreinamento.class.getSimpleName();
	
	private int maxResults=FilterResultSize.treinamentosListMaxSize;

	public TreinamentoServiceDAOHibernate() {

	}

	/**
	 * Retorna todos os treinamentos que tiverem no código do treinamento o valor passado como
	 * argumento
	 */
	@SuppressWarnings("unchecked")
	public List listScheduledTrainingLikeCodigo(String codTreinamento) {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from " + entityName + " treinamento");
			hql.append(" where ");
			hql.append(" treinamento.codigo like ? order by treinamento.codigo");
			paramList.add("%" + codTreinamento + "%");
			return getHibernateTemplate().find(hql.toString(),
					paramList.toArray());

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	/**
	 * Retorna todos os treinamentos em que o código termina com o valor passado como
	 * argumento
	 */
	@SuppressWarnings("unchecked")
	public List listScheduledTrainingByCodigoEndingWith(String codTreinamento) {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from " + entityName + " treinamento ");
			hql.append(" where ");
			hql.append(" treinamento.codigo like ? ");
			paramList.add("%" + codTreinamento);
			return getHibernateTemplate().find(hql.toString(),
					paramList.toArray());

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	/**
	 * Retorna todos os treinamentos em que o código inicia com o valor passado como
	 * argumento
	 */
	@SuppressWarnings("unchecked")
	public List listScheduledTrainingByCodigoBeginingWith(String codTreinamento) {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from " + entityName + " treinamento ");
			hql.append(" where ");
			hql.append(" treinamento.codigo like ? ");
			paramList.add(codTreinamento + "%");
			return getHibernateTemplate().find(hql.toString(),
					paramList.toArray());

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	/**
	 * Retorna todos os treinamentos ou retirados ou não retirados
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public List listByRetirado(boolean retirado) {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from " + entityName + " treinamento ");
			hql.append(" where ");
			hql.append(" treinamento.retirado like ? ");
			paramList.add("%" + retirado + "%");
			return getHibernateTemplate().find(hql.toString(),
					paramList.toArray());

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	/**
	 * Retorna todos os treinamentos em que o status é igual ao valor passado como
	 * argumento
	 */
	@SuppressWarnings("unchecked")
	public List listScheduledTrainingByStatus(ScheduledTrainingStatus status) {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from " + entityName + " treinamento ");
			hql.append(" where ");
			hql.append(" treinamento.status = ? ");
			paramList.add("" + status + "");
			return getHibernateTemplate().find(hql.toString(),
					paramList.toArray());

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	/**
	 * Retorna todos os treinamentos de um comprador que possui no nome um valor igual
	 * ao do argumento passado como parâmetro
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public List listScheduledTrainingLikeNomeAutor(Participante joincompraComprador) {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from " + entityName + " treinamento ");
			hql.append(" where ");
			hql.append(" treinamento.comprador.nome like ? ");
			paramList.add("%" + joincompraComprador.getNome() + "%");
			return getHibernateTemplate().find(hql.toString(),
					paramList.toArray());

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	/**
	 * Retorna todos os treinamentos de um comprador que possui o id igual ao do
	 * argumenta passado como parâmetro
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public List listScheduledTrainingByIdAutor(int joinPessoaId) {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from " + entityName + " treinamento ");
			hql.append(" where ");
			hql.append(" treinamento.comprador.id = ? ");
			paramList.add("" + joinPessoaId);
			return getHibernateTemplate().find(hql.toString(),
					paramList.toArray());

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	/**
	 * Retorna todos os treinamentos de um determinado pessoaEmDivulgacao que possua no nome o
	 * valor passado como argumento
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public List listLikeNomeComitente(String joinPessoaNome) {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from " + entityName + " treinamento ");
			hql.append(" where ");
			hql.append(" treinamento.comitente.nome like ? ");
			paramList.add("%" + joinPessoaNome + "%");
			return getHibernateTemplate().find(hql.toString(),
					paramList.toArray());

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	/**
	 * Retorna todos os treinamentos de um determinado pessoaEmDivulgacao pelo identificador do
	 * pessoaEmDivulgacao
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public List listByIdComitente(int joinPessoaId) {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from " + entityName + " treinamento ");
			hql.append(" where ");
			hql.append(" treinamento.comitente.id = ? ");
			paramList.add("" + joinPessoaId);
			return getHibernateTemplate().find(hql.toString(),
					paramList.toArray());

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List listAllScheduledTraining() {
		try {
			HibernateTemplate template = new HibernateTemplate(
					getSessionFactory());
			return template.loadAll(Treinamento.class);

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<FormacaoTreinamento> listAllTrainingFormations() {
		try {
			HibernateTemplate template = new HibernateTemplate(
					getSessionFactory());
			return template.loadAll(FormacaoTreinamento.class);

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	public boolean saveOrUpdate(Treinamento treinamento) throws Exception {		
		try {
			HibernateTemplate template = new HibernateTemplate(
					getSessionFactory());
			template.saveOrUpdate(treinamento);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	/**
	 * Apaga o treinamento pelo identificador do treinamento
	 */
	@SuppressWarnings("unchecked")
	public Treinamento deleteById(int id) throws Exception {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from " + entityName + " treinamento ");
			hql.append(" where ");
			hql.append(" treinamento.id = " + id + " ");
			Treinamento treinamento = (Treinamento) DataAccessUtils
					.uniqueResult(getHibernateTemplate().find(hql.toString(),
							paramList.toArray()));

			getHibernateTemplate().delete(treinamento);
			return treinamento;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	/**
	 * apaga o treinamento que possui o código igual ao valor passado como argumento
	 */
	@SuppressWarnings("unchecked")
	public Treinamento deleteByCodigo(String codTreinamento) throws Exception {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from " + entityName + " treinamento ");
			hql.append(" where ");
			hql.append(" treinamento.codigo = ? ");
			paramList.add("" + codTreinamento + "");
			Treinamento treinamento = (Treinamento) DataAccessUtils
					.uniqueResult(getHibernateTemplate().find(hql.toString(),
							paramList.toArray()));

			getHibernateTemplate().delete(treinamento);
			return treinamento;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	/**
	 * Apaga todos os treinamentos que forem de um pessoaEmDivulgacao. O pessoaEmDivulgacao é informado de
	 * acordo com o identificador
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public Treinamento deleteByIdComitente(int joinPessoaId) throws Exception {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from " + entityName + " treinamento ");
			hql.append(" where ");
			hql.append(" treinamento.comitente.id = ? ");
			paramList.add("" + joinPessoaId);
			Treinamento treinamento = (Treinamento) DataAccessUtils
					.uniqueResult(getHibernateTemplate().find(hql.toString(),
							paramList.toArray()));

			getHibernateTemplate().delete(treinamento);
			return treinamento;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	/**
	 * apaga todos os treinamentos que foram comprados por uma determinada pessoa. Esta
	 * pessoa é idetificado pelo parametro id.
	 */
	@SuppressWarnings("unchecked")
	@Deprecated
	public Treinamento deleteByIdAutor(Participante joincompraComprador)
			throws Exception {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from " + entityName + " treinamento ");
			hql.append(" where ");
			hql.append(" treinamento.comprador.id = ? ");
			paramList.add(""+joincompraComprador.getId());
			Treinamento treinamento = (Treinamento) DataAccessUtils
					.uniqueResult(getHibernateTemplate().find(hql.toString(),
							paramList.toArray()));

			getHibernateTemplate().delete(treinamento);
			return treinamento;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	@SuppressWarnings("unchecked")
	public Treinamento getScheduledTrainingById(int id) throws Exception {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from " + entityName + " treinamento ");
			hql.append(" where ");
			hql.append(" treinamento.id = " + id + " ");
			return (Treinamento) DataAccessUtils.uniqueResult(getHibernateTemplate()
					.find(hql.toString(), paramList.toArray()));

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	@SuppressWarnings("unchecked")
	public Treinamento getScheduledTrainingByCodigo(String codTreinamento) throws Exception {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from " + entityName + " treinamento ");
			hql.append(" where ");
			hql.append(" treinamento.codigo = ? ");
			paramList.add("" + codTreinamento + "");
			Treinamento l = (Treinamento) DataAccessUtils.uniqueResult(getHibernateTemplate()
					.find(hql.toString(), paramList.toArray()));
			
			//Hibernate.initialize(l.getItensProduto());
			
			return l; 

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	@Override
	public AgendaTreinamento getScheduledTrainingScheduleIdByScheduledTrainingId(int treinamentoId) throws Exception{
		Session s = LocalServicesUtility.getInstance().openSession();		
		try {
			int id = (Integer) s.createQuery("select treinamento.agendaTreinamento.id from "+Treinamento.class.getSimpleName()+" treinamento where treinamento.id="+treinamentoId+"").uniqueResult();
			AgendaTreinamento agenda = new AgendaTreinamento();
			agenda.setId(id);
			//adicionei na transição
			agenda = (AgendaTreinamento) s.load(AgendaTreinamento.class, id);
			return agenda;
		} catch (NullPointerException e) {
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if (s!=null) s.close();
		}	
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Object filterScheduledTrainingBy(ScheduledTrainingFilterType tipoFiltro, int firstResultIndex,	int tabSelectedIndex, 
			boolean filtrarSomenteLeilao, Boolean filtrarRetirados,	Boolean filtrarArrematados, 
			Boolean filtrarComPagamentoQuitado, Integer idLeilao,String statusLote,	String descricaoLote, 
			String numeroProcesso, Integer paiSubLote,	int orderBy, int secBegin) throws Exception{
		
		if (tipoFiltro == null) throw new Exception("O tipo do filtro não pode ser nulo");
		String query = null;
		if (tipoFiltro == ScheduledTrainingFilterType.Lote) query= "select l from "+entityName+" l";
		else if (tipoFiltro == ScheduledTrainingFilterType.LoteCount) query= "select count(l) from "+entityName+" l";
		String comparator1=null;
		
		boolean whereused=false;
		if (filtrarSomenteLeilao){
			if (tipoFiltro == ScheduledTrainingFilterType.Lote || tipoFiltro == ScheduledTrainingFilterType.LoteCount) 
				comparator1=" l.turma.id="+idLeilao;
			
		}
		if (comparator1 != null){
			query += " where " + comparator1;
			whereused=true;
		}
		
		if (tabSelectedIndex == 0){
			String str = (String) statusLote;
			if (str != null && str.equals("Qualquer um dos estados")) ;
			else if (str != null){
				String value = str.replace(" ", "_");
				ScheduledTrainingStatus st = ScheduledTrainingStatus.valueOf(value);
				if (comparator1 != null) query += " and l.status='"+st.name()+"'";
				else{
					query += " where l.status='"+st.name()+"'";
					whereused=true;
				}
			}
		}else if(tabSelectedIndex == 1){
			String str = (String) descricaoLote;
			if (str == null || str.equals("")) ;
			else{
				
				if (comparator1 != null) query += " and l.descricao like '%"+str+"%'";
				else{
					query += " where l.descricao like '%"+str+"%'";
					whereused=true;
				}
			}
		}else if(tabSelectedIndex == 3){
			String str = (String) numeroProcesso;
			if (str == null || str.equals("")) ;
			else{				
				if (comparator1 != null) query += " and l.processo.processo like '%"+str+"%'";
				else{
					query += " where l.processo.processo like '%"+str+"%'";
					whereused=true;
				}
			}
		}
		
		if (filtrarRetirados != null){
			if (filtrarRetirados){
				if (!whereused) {
					query+=" where l.retiradoDeposito=true";							
				}
				else query+=" and l.retiradoDeposito=true";
			}else{
				if (!whereused) query+=" where l.retiradoDeposito=false";
				else query+=" and l.retiradoDeposito=false";
			}
			
			if (filtrarArrematados != null){
				if (filtrarArrematados){
					query+=" and l.arrematado=true";
				}else{
					query+=" and l.arrematado=false";
				}
				
				if (filtrarComPagamentoQuitado != null){
					if (filtrarComPagamentoQuitado){
						query+=" and l.pagtoQuitado=true";
					}else{
						query+=" and l.pagtoQuitado=false";
					}
				}
			}
		} else if (filtrarArrematados != null){			
			if (filtrarArrematados){
				if (!whereused) {
					query+=" where l.arrematado=true";							
				}
				else query+=" and l.arrematado=true";
				
			}else{
				if (!whereused) query+=" where l.arrematado=false";
				else query+=" and l.arrematado=false";				
			}
			
			if (filtrarComPagamentoQuitado != null){
				if (filtrarComPagamentoQuitado){
					query+=" and l.pagtoQuitado=true";
				}else{
					query+=" and l.pagtoQuitado=false";
				}
			}
		} else if (filtrarComPagamentoQuitado != null){			
			if (filtrarComPagamentoQuitado){
				if (!whereused) {
					query+=" where l.pagtoQuitado=true";							
				}
				else query+=" and l.pagtoQuitado=true";
				
			}else{
				if (!whereused) query+=" where l.pagtoQuitado=false";
				else query+=" and l.pagtoQuitado=false";				
			}
		}
		
		
		
		
		Session s = LocalServicesUtility.getInstance().openSession();
		try {
			if (tipoFiltro == ScheduledTrainingFilterType.Lote){
				List<Treinamento> treinamentos = s.createQuery(query).setMaxResults(maxResults).setFirstResult(maxResults*firstResultIndex).list();
				return treinamentos;
			}else if(tipoFiltro == ScheduledTrainingFilterType.LoteCount){
				return (Integer) s.createQuery(query).uniqueResult();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			throw e1;
		}finally{
			s.close();
		}
		return s;
	}
	
	@Override
	public void removeScheduledTraining(Treinamento l, TurmaTreinamento turma) throws Exception{
		Session s = LocalServicesUtility.getInstance().openSession();
		try {
			s.beginTransaction();
			
			l = (Treinamento) s.load(Treinamento.class,l.getId());
			turma.getTreinamentos().remove(l);
			
			Iterator<ComercialSolutionItem> it = l.getComercialSolutionItens().iterator();
			while(it.hasNext()){
				ComercialSolutionItem ip = it.next();
				ip.setTreinamento(null);
				s.save(ip);
			}
	
			s.delete(l);
			s.getTransaction().commit();			
		} catch (Exception e1) {
			e1.printStackTrace();
			s.getTransaction().rollback();
			throw e1;
		}finally{
			if (s!=null) s.close();
		}
	}
	

	
	@Override
	@Deprecated
	public Participante getAuthorByScheduledTrainingId(int idTreinamento) throws Exception{
		Session s = LocalServicesUtility.getInstance().openSession();
		try {
			s = LocalServicesUtility.getInstance().openSession();
			Long id = (Long) s.createQuery(
					"select treinamento.autor.id from "+entityName+" treinamento where treinamento.id="+idTreinamento)
					.uniqueResult();
			if (id == null) return null;
			Participante obj = (Participante) s.load(Participante.class, id);
			if (obj != null){
				
				((Participante)obj).getTipoPessoa();
				((Participante)obj).getUser();
				return (Participante) obj;
			}			
		} catch (Exception e1) {
			e1.printStackTrace();
			s.getTransaction().rollback();
			throw e1;
		}finally{
			if (s!=null) s.close();
		}
		return null;
	}
	
	
	
	//copiados de treinamentoprodutoindividual
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Treinamento> listAllScheduledTrainingByTurmaId(int idLeilao) throws Exception{
		Session s = null;
		 List<Treinamento> arr = new ArrayList<Treinamento>();
		try {
			s = LocalServicesUtility.getInstance().openSession();
			List list = s.createQuery("select treinamento.id, treinamento.codigo from "+entityName+" treinamento where treinamento.turma.id =" + idLeilao+" order by treinamento.codigo").list();
			if (list != null && list.size() > 0){
				Iterator it = list.iterator();
				while (it.hasNext()){
					Object obj[] = (Object[]) it.next();
					Treinamento l = new Treinamento();
					l.setId((Integer)obj[0]);
					l.setCodigo((String)obj[1]);
					arr.add(l);
				}				
			}
		} catch (Exception e2) {
			e2.printStackTrace();
			throw e2;
		}finally{
			if (s != null) s.close();
		}
		return arr;
	}
	
	@Override
	public Treinamento updateScheduledTrainingProperties(Treinamento treinamento) throws Exception{
		Session s = null;
		try{
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();		
			
			Query query = s.createQuery("update "+entityName+" l set l.resumo=:resumo, l.status=:status, l.custo=:custo, l.receita=:receita where l.id=:id");
			query.setParameter("resumo", treinamento.getResumo());
			query.setParameter("status", treinamento.getStatus().name());
			query.setParameter("custo", treinamento.getCusto());
			query.setParameter("receita", treinamento.getReceita());
			query.setParameter("id", treinamento.getId());
			query.executeUpdate();
			s.getTransaction().commit();
		}catch(Exception ex){
			ex.printStackTrace();
			s.getTransaction().rollback();
		}finally{
			if (s != null) s.close();
		}
		return treinamento;
	}

	
	@Override
	public FormacaoTreinamento updateTrainingFormationProperties(FormacaoTreinamento treinamento) throws Exception{
		Session s = null;
		try{
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();		
			
			Query query = s.createQuery("update "+formacaoTreinamento+ " l set l.technology=:tec, l.nome=:nome, l.descricao=:desc, l.avaliacao=:avaliacao, l.cargaHorariaTotal=:ch where l.id=:id");
			query.setParameter("tec", treinamento.getTechnology().ordinal());
			query.setParameter("nome", treinamento.getNome());
			query.setParameter("desc", treinamento.getDescricao());
			query.setParameter("avaliacao", treinamento.getAvaliacao());
			query.setParameter("id", treinamento.getId());
			query.setParameter("ch", treinamento.getCargaHorariaTotal());
			query.executeUpdate();
			s.getTransaction().commit();
		}catch(Exception ex){
			ex.printStackTrace();
			s.getTransaction().rollback();
		}finally{
			if (s != null) s.close();
		}
		return treinamento;
	}
	
	@Override
	public Treinamento loadScheduledTrainingById(int idTreinamento, boolean cascadeAgendas) throws Exception{		
		Session s = LocalServicesUtility.getInstance().openSession();
		
		try {
			Treinamento schedTrain = (Treinamento) s.createQuery("select treinamento from "+entityName+" treinamento where treinamento.id =" + idTreinamento+" ").uniqueResult();
			
			/*if (schedTrain.getAgendaTreinamento()!= null)
				schedTrain.getAgendaTreinamento().getId();*/
			Integer agid = (Integer)s.createQuery("select t.agendaTreinamento.id from "+entityName+" t where t.id =" + idTreinamento+" ").uniqueResult();
			if (agid != null){
				AgendaTreinamento a = new AgendaTreinamento();
				a.setId(agid.intValue());
				schedTrain.setAgendaTreinamento(a);
			}
			
			if (schedTrain.getInteressados() != null && schedTrain.getInteressados().size()>0){
				
			}
			schedTrain.getTurma();
			if (cascadeAgendas && schedTrain.getAgendas() != null)
				schedTrain.getAgendas().size();
			
			Object[] objs = (Object[])s.createQuery("select t.treinamento.id, t.treinamento.nome, t.treinamento.avaliacao from "+entityName+" t where t.id =" + idTreinamento+" ").uniqueResult();
			if (objs != null && objs[0] != null && objs.length>0){
				TrainingSolution t = new TrainingSolution();
				t.setId((Integer)objs[0]);
				t.setNome((String)objs[1]);
				t.setAvaliacao((Float)objs[2]);
				schedTrain.setTreinamento(t);
			}
			
			schedTrain.getCusto();
			schedTrain.getStatus();
			schedTrain.getReceita();
			schedTrain.getResumo();
			
			
			return schedTrain;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if (s!=null) s.close();
		}		
	}
	
	@Override
	public FormacaoTreinamento loadTrainingFormationByFormationId(int idTreinamento) throws Exception{		
		Session s = LocalServicesUtility.getInstance().openSession();
		
		try {
			FormacaoTreinamento objLote = (FormacaoTreinamento) s.createQuery("select treinamento from "+formacaoTreinamento+" treinamento where treinamento.id =" + idTreinamento+" ").uniqueResult();
			
			objLote.getDescricao();
			
			objLote.getAvaliacao();
			objLote.getTechnology();
			if (objLote.getImagem()!= null){
				objLote.getImagem().getId();
			}
			return objLote;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if (s!=null) s.close();
		}		
	}

	
	@Override
	public void delete(Treinamento l) throws Exception{
		Session s = LocalServicesUtility.getInstance().openSession();
		try {
			s.beginTransaction();			
			l = (Treinamento) s.load(Treinamento.class,l.getId());
			s.delete(l);
			s.getTransaction().commit();			
		} catch (Exception e1) {
			e1.printStackTrace();
			s.getTransaction().rollback();
			throw e1;
		}finally{
			if (s!=null) s.close();
		}
	}
	
	@Override
	public void delete(Treinamento l, int idTurma) throws Exception{
		Session s = LocalServicesUtility.getInstance().openSession();
		try {
			s.beginTransaction();
			TurmaTreinamento turma = (TurmaTreinamento) s.load(TurmaTreinamento.class, idTurma);
			l = (Treinamento) s.load(Treinamento.class,l.getId());
			turma.getTreinamentos().remove(l);
			Iterator<ComercialSolutionItem> it = l.getComercialSolutionItens().iterator();
			while(it.hasNext()){
				ComercialSolutionItem ip = it.next();
				ip.setTreinamento(null);
				s.save(ip);
			}
	
			s.delete(l);
			s.getTransaction().commit();			
		} catch (Exception e1) {
			e1.printStackTrace();
			s.getTransaction().rollback();
			throw e1;
		}finally{
			if (s!=null) s.close();
		}
	}
	
	@Override
	public void delete(FormacaoTreinamento l, int idTurma) throws Exception{
		Session s = LocalServicesUtility.getInstance().openSession();
		try {
			s.beginTransaction();
			TurmaTreinamento turma = (TurmaTreinamento) s.load(TurmaTreinamento.class, idTurma);
			l = (FormacaoTreinamento) s.load(FormacaoTreinamento.class,l.getId());
			turma.setFormacao(null);
			Iterator<TrainingFormationItem> it = l.getTrainingFormationItens().iterator();
			while(it.hasNext()){
				TrainingFormationItem ip = it.next();
				ip.setTrainingFormation(null);
				s.save(ip);
			}
	
			s.delete(l);
			s.getTransaction().commit();			
		} catch (Exception e1) {
			e1.printStackTrace();
			s.getTransaction().rollback();
			throw e1;
		}finally{
			if (s!=null) s.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ComercialSolutionItem> getComercialSolutionItensByScheduledTrainingId(int treinamentoId) throws Exception{
		List<ComercialSolutionItem> itens = new ArrayList<ComercialSolutionItem>();		
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			String qItens = "select ip.id, ip.desconto, ip.valorAcertado from "+comercialSolutionItem+" ip where ip.treinamento.id="+ treinamentoId;
			List list =  s.createQuery(qItens).list();
			if (list != null && list.size() > 0){
				Iterator<Object[]> it = list.iterator();
				while (it.hasNext()){
					Object[] objs = (Object[]) it.next();
					Integer id = (Integer) objs[0];
					Integer qtd = (Integer) objs[1];
					Float value = (Float) objs[2];
					ComercialSolutionItem ip = new ComercialSolutionItem();
					ip.setId(id);
					ip.setDesconto(qtd);
					ip.setValorAcertado(value);
					itens.add(ip);
				}
			}			
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if (s!=null) s.close();
		}		
		return itens;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ComercialSolutionItem> getTrainingSolutionItensByFormationId(int formacaoId) throws Exception{
		List<ComercialSolutionItem> itens = new ArrayList<ComercialSolutionItem>();		
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			String qItens = "select ip.id, ip.qtd, ip.valorTotal from "+comercialSolutionItem+" ip where ip.formacao.id="+ formacaoId;
			List list =  s.createQuery(qItens).list();
			if (list != null && list.size() > 0){
				Iterator<Object[]> it = list.iterator();
				while (it.hasNext()){
					Object[] objs = (Object[]) it.next();
					Integer id = (Integer) objs[0];
					Integer qtd = (Integer) objs[1];
					Float value = (Float) objs[2];
					ComercialSolutionItem ip = new ComercialSolutionItem();
					ip.setId(id);
					ip.setDesconto(qtd);
					ip.setValorAcertado(value);
					itens.add(ip);
				}
			}			
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if (s!=null) s.close();
		}		
		return itens;
	}
	
	public FormacaoTreinamento getFormacaoById(int id) throws Exception{
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			FormacaoTreinamento f = (FormacaoTreinamento) s.load(FormacaoTreinamento.class,id);
			f.getNome();
			return f;
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if (s!=null) s.close();
		}	
	}
	
	public List<Treinamento> createTrainemantosByFormacaoIdAndTurmaId(int fid, int tid) throws Exception{
		Session s = null;
		List<Treinamento> treinamentos = new ArrayList<Treinamento>();
		try {
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
			FormacaoTreinamento f = (FormacaoTreinamento) s.load(FormacaoTreinamento.class,fid);			
			TurmaTreinamento t = (TurmaTreinamento) s.load(TurmaTreinamento.class,tid);
			List<TrainingFormationItem> items = s.createQuery("select item from TrainingFormationItem item where item.trainingFormation.id="+fid).list();
			if (items != null){
				for(TrainingFormationItem item: items){
					TrainingSolution ts = item.getTrainingSolution();
					Treinamento treinamento = new Treinamento();
					treinamento.setTreinamento(ts);
					treinamento.setCodigo(ts.getCodigo()+"-"+item.getItemOrder());
					treinamento.setStatus(ScheduledTrainingStatus.Aguardando_confirmação_da_agenda);
					treinamento.setTurma(t);
					s.save(treinamento);
					
					AgendaTreinamento agenda = new AgendaTreinamento();
					agenda.setInicioPrevisto(t.getDataTreinamento());
					agenda.setTerminoPrevisto(t.getDataEncerramento());
					agenda.setTurno(t.getTurno());
					agenda.setRecebendoInteressados(true);
					agenda.setStatus(StatusAgenda.Cadastrada);
					s.save(agenda);
					
					treinamento.setAgendaTreinamento(agenda);
					s.merge(treinamento);
					
					treinamentos.add(treinamento);
				}
			}
						
			s.getTransaction().commit();
			return treinamentos;
		}catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();
			throw e;
		}finally{
			if (s!=null) s.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<TrainingSolution> listAllTrainingSolutions() throws Exception{
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			return s.createQuery("select ts from TrainingSolution").list();
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if (s!=null) s.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<TrainingSolution> listTrainingSolutionsByKeywords(String kw[]) throws Exception{
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			if (kw == null)
				return s.createQuery("select ts from TrainingSolution").list();
			else if (kw.length == 1)
				return s.createQuery("select ts from TrainingSolution where ts.keyWords like '%"+kw[0]+"%'").list();
			else{
				String query = "select ts from TrainingSolution where ";
				for (int i=0; i < kw.length; i++){
					if (i > 0 ) query+=" or ";
					query+= "ts.keyWords like '%"+kw[0]+"%'";
				}
			
				return s.createQuery(query).list();
			}
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if (s!=null) s.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void mergeNewTrainingSolutionOnScheduledTraining(TrainingSolution solution, Treinamento treinamento) throws Exception{
		Session s = LocalServicesUtility.getInstance().openSession();
		try{
			s.beginTransaction();
			
			treinamento.setTreinamento(solution);			
			s.merge(treinamento);
			
			s.getTransaction().commit();
		}catch(Exception ex){
			ex.printStackTrace();
			s.getTransaction().rollback();
			throw ex;
		}finally{
			if (s != null) s.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ComercialSolutionItem mergeParticipanteOnScheduledTraining(Participante participante, Treinamento treinamento) throws Exception{
		Session s = LocalServicesUtility.getInstance().openSession();
		try{
			s.beginTransaction();
			//s.refresh(treinamento);
			//s.refresh(prod);
			try {
				ComercialSolutionItem item = (ComercialSolutionItem) s.createQuery("select item from ComercialSolutionItem item where " +
						" item.treinamento.id="+treinamento.getId()+
						" and item.inscrito.id="+participante.getId()).uniqueResult();
				if (item != null) return item;
			} catch (Exception e) {e.printStackTrace();}
			
			participante = (Participante) s.load(Participante.class,participante.getId());
			treinamento = (Treinamento) s.load(Treinamento.class,treinamento.getId());
			
			ComercialSolutionItem ip = new ComercialSolutionItem();
			ip.setDesconto(0);
			ip.setInscrito(participante);
			ip.setTreinamento(treinamento);
			if (treinamento.getComercialSolutionItens() == null) treinamento.setComercialSolutionItens(new ArrayList());
			treinamento.getComercialSolutionItens().add(ip);
			if(participante.getItensTreinamentos() == null) participante.setItensTreinamentos(new ArrayList());
			participante.getItensTreinamentos().add(ip);
			
			List<Treinamento> meusTreinamentos = (List<Treinamento>) participante.getMeusTreinamentos();
			
			Iterator<Treinamento> ituser = meusTreinamentos.iterator();
			boolean temInscricao=false;
			while (ituser.hasNext()){
				Treinamento l = ituser.next();
				if (l.getId() == treinamento.getId())
					temInscricao = true;
			}		
			
			s.save(ip);		
			
			if (!temInscricao){
				Participante part = (Participante)participante;
				part.getMeusTreinamentos().add(treinamento);
				treinamento.getInteressados().add(part);
				s.merge(treinamento);
				s.merge(part);
			}
			
			s.getTransaction().commit();
			return ip;
		}catch(Exception ex){
			ex.printStackTrace();
			s.getTransaction().rollback();
			throw ex;
		}finally{
			if (s != null) s.close();
		}
		
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public TrainingFormationItem mergeNewTrainingSolutionOnTrainingFormation(TrainingSolution solution, FormacaoTreinamento treinamento) throws Exception{
		Session s = LocalServicesUtility.getInstance().openSession();
		try{
			s.beginTransaction();
			TrainingFormationItem ip = new TrainingFormationItem();
			ip.setItemOrder(10);
			ip.setTrainingSolution(solution);
			ip.setTrainingFormation(treinamento);
			if (treinamento.getTrainingFormationItens() == null) treinamento.setTrainingFormationItens(new ArrayList());
			treinamento.getTrainingFormationItens().add(ip);
			if(solution.getTrainingFormationItens() == null) solution.setTrainingFormationItens(new ArrayList());
			solution.getTrainingFormationItens().add(ip);
			
			s.save(ip);	
			s.getTransaction().commit();
			return ip;
		}catch(Exception ex){
			ex.printStackTrace();
			s.getTransaction().rollback();
			throw ex;
		}finally{
			if (s != null) s.close();
		}
	}
	
	@Override
	public void removeTrainingSolutionFromScheduledTraining(boolean definitivo, int idItemProd) throws Exception{
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			
			ComercialSolutionItem ip = (ComercialSolutionItem) s.load(ComercialSolutionItem.class,idItemProd);							
			 
			s.beginTransaction();
			
			ip.setTreinamento(null);
			s.delete(ip);
			
			s.getTransaction().commit();			
		} catch(org.hibernate.exception.ConstraintViolationException ex){
			ex.printStackTrace();
			s.getTransaction().rollback();
			throw ex;
		} catch (Exception e1) {
			e1.printStackTrace();
			s.getTransaction().rollback();
			throw e1;
		}finally{
			if (s != null && s.isOpen())s.close();
		}
	}
	/*public void removeTrainingSolutionFromScheduledTraining(boolean definitivo, int idItemProd) throws Exception{
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			
			ComercialSolutionItem ip = (ComercialSolutionItem) s.load(ComercialSolutionItem.class,idItemProd);							
			 
			s.beginTransaction();
			
			if (definitivo){								
				Iterator<Imagem> it =ip.getComercialSolution().getImagens().iterator();
				TrainingSolution prod =ip.getComercialSolution(); 
				while(it.hasNext()){
					Imagem im = it.next();
					im.setComercialSolution(null);
					s.delete(im);			
				}
				prod.setTrainingFormationItens(null);
				ip.setComercialSolution(null);
				
				s.delete(ip);
				s.delete(prod);
			}else{
				ip.setTreinamento(null);
				ip.setComercialSolution(null);
				s.delete(ip);
			}
			s.getTransaction().commit();			
		} catch(org.hibernate.exception.ConstraintViolationException ex){
			ex.printStackTrace();
			s.getTransaction().rollback();
			throw ex;
		} catch (Exception e1) {
			e1.printStackTrace();
			s.getTransaction().rollback();
			throw e1;
		}finally{
			if (s != null && s.isOpen())s.close();
		}
	}*/
	
	@Override
	public void removeTrainingSolutionFromTrainingFormation(boolean definitivo, int idFormationId) throws Exception{
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			
			TrainingFormationItem ip = (TrainingFormationItem) s.load(TrainingFormationItem.class,idFormationId);							
			 
			s.beginTransaction();
			
			if (definitivo){								
				Iterator<Imagem> it =ip.getTrainingSolution().getImagens().iterator();
				TrainingSolution prod =ip.getTrainingSolution(); 
				while(it.hasNext()){
					Imagem im = it.next();
					im.setComercialSolution(null);
					s.delete(im);			
				}
				prod.setTrainingFormationItens(null);
				ip.setTrainingSolution(null);
				
				s.delete(ip);
				s.delete(prod);
			}else{
				ip.setTrainingSolution(null);
				s.delete(ip);
			}
			s.getTransaction().commit();			
		} catch(org.hibernate.exception.ConstraintViolationException ex){
			ex.printStackTrace();
			s.getTransaction().rollback();
			throw ex;
		} catch (Exception e1) {
			e1.printStackTrace();
			s.getTransaction().rollback();
			throw e1;
		}finally{
			if (s != null && s.isOpen())s.close();
		}
	}
	
	@Override
	public Object[] getScheduledTrainingItemPropertiesByItemId(int idItem) throws Exception{
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			String queryprod = "select ip.inscrito.id, ip.inscrito.nome, ip.confirmada, ip.quitada, ip.desconto, ip.valorAcertado from "+comercialSolutionItem+" ip where ip.id="+idItem;
			Object[] prods = (Object[]) s.createQuery(queryprod).uniqueResult();
			return prods;
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if (s!=null) s.close();
		}	
		
	}
/*	public Object[] getScheduledTrainingItemPropertiesByItemId(int idItem) throws Exception{
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			String queryprod = "select ip.comercialSolution.id, ip.comercialSolution.dataCriacao, ip.comercialSolution.nome, ip.comercialSolution.avaliacao, ip.comercialSolution.cargaHoraria from "+comercialSolutionItem+" ip where ip.id="+idItem;
			Object[] prods = (Object[]) s.createQuery(queryprod).uniqueResult();
			String catname = (String) s.createQuery("select prod.categoria.nome from ComercialSolution prod where prod.id="+prods[0]).uniqueResult();
			Object objs[] = new Object[prods.length+1];
			for(int i=0; i < prods.length;i++){
				objs[i] = prods[i];
			}
			objs[prods.length] = catname;
			return objs;
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if (s!=null) s.close();
		}	
		
	}*/
	

	
	@Override
	public Object[] getTrainingFormationItemPropertiesByItemId(int idItem) throws Exception{
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			String queryprod = "select ip.trainingSolution.id, ip.trainingSolution.dataCriacao, ip.trainingSolution.nome, ip.trainingSolution.avaliacao, ip.trainingSolution.cargaHoraria from "+trainingFormationItem+" ip where ip.id="+idItem;
			Object[] prods = (Object[]) s.createQuery(queryprod).uniqueResult();
			String catname = (String) s.createQuery("select prod.categoria.nome from "+comercialSolution+" prod where prod.id="+prods[0]).uniqueResult();
			Object objs[] = new Object[prods.length+1];
			for(int i=0; i < prods.length;i++){
				objs[i] = prods[i];
			}
			objs[prods.length] = catname;
			return objs;
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if (s!=null) s.close();
		}	
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TrainingFormationItem> getTrainingFormationItensByFormationId(int formacaoId) throws Exception{
		List<TrainingFormationItem> itens = new ArrayList<TrainingFormationItem>();		
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			String qItens = "select ip.id, ip.itemOrder from "+trainingFormationItem+" ip where ip.trainingFormation.id="+ formacaoId+" order by ip.itemOrder ASC";
			List list =  s.createQuery(qItens).list();
			if (list != null && list.size() > 0){
				Iterator<Object[]> it = list.iterator();
				while (it.hasNext()){
					Object[] objs = (Object[]) it.next();
					Integer id = (Integer) objs[0];
					Integer qtd = (Integer) objs[1];
					TrainingFormationItem ip = new TrainingFormationItem();
					ip.setId(id);
					ip.setItemOrder(qtd);
					itens.add(ip);
				}
			}			
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if (s!=null) s.close();
		}		
		return itens;
	}
	
	@Override
	public void createFormationWithTrainingSolutions(TrainingSolution prods[], FormacaoTreinamento treinamento) throws Exception {
		Session s = LocalServicesUtility.getInstance().openSession();
		try {

			for (int i = 0; i < prods.length; i++) {
				s.beginTransaction();
				TrainingSolution p = (TrainingSolution) prods[i];
				TrainingFormationItem ip = (TrainingFormationItem) s
						.createQuery(
								"select ip from "+trainingFormationItem+" where ip.trainingFormation.id=:idf and ip.trainingSolution.id=:ids")
						.setParameter("idf", treinamento.getId()).setParameter(
								"ids", p.getId()).uniqueResult();

				ip.setTrainingFormation(treinamento);
				treinamento.getTrainingFormationItens().add(ip);

				ip.setTrainingSolution(p);
				p.getTrainingFormationItens().add(ip);

				s.flush();
				s.getTransaction().commit();

			}
		} catch (Exception ex) {
			ex.printStackTrace();
			s.getTransaction().rollback();
			throw ex;
		} finally {
			if (s != null)
				s.close();
		}
	}
	

	@Override
	@Deprecated
	public void createScheduledTrainingWithTrainingSolutions(TrainingSolution prods[], Treinamento treinamento) throws Exception{
		/*Session s = LocalServicesUtility.getInstance().openSession();
		try {

			for (int i = 0; i < prods.length; i++) {
				s.beginTransaction();
				TrainingSolution p = (TrainingSolution) prods[i];
				ComercialSolutionItem ip = (ComercialSolutionItem) s
						.createQuery(
								"select ip from "+comercialSolutionItem+" where ip.treinamento.id=:idt and ip.comercialSolution.id=:ids")
						.setParameter("idt", treinamento.getId()).setParameter(
								"ids", p.getId()).uniqueResult();

				ip.setTreinamento(treinamento);
				treinamento.getComercialSolutionItens().add(ip);

				ip.setComercialSolution(p);
				p.getComercialSolutionItens().add(ip);

				s.flush();
				s.getTransaction().commit();

			}
		} catch (Exception ex) {
			ex.printStackTrace();
			s.getTransaction().rollback();
			throw ex;
		} finally {
			if (s != null)
				s.close();
		}*/
	}
	
/*	@Override
	public void createScheduledTrainingWithTrainingSolutions(TrainingSolution prods[], Treinamento treinamento) throws Exception{
		Session s = LocalServicesUtility.getInstance().openSession();
		try {

			for (int i = 0; i < prods.length; i++) {
				s.beginTransaction();
				TrainingSolution p = (TrainingSolution) prods[i];
				ComercialSolutionItem ip = (ComercialSolutionItem) s
						.createQuery(
								"select ip from "+comercialSolutionItem+" where ip.treinamento.id=:idt and ip.comercialSolution.id=:ids")
						.setParameter("idt", treinamento.getId()).setParameter(
								"ids", p.getId()).uniqueResult();

				ip.setTreinamento(treinamento);
				treinamento.getComercialSolutionItens().add(ip);

				ip.setComercialSolution(p);
				p.getComercialSolutionItens().add(ip);

				s.flush();
				s.getTransaction().commit();

			}
		} catch (Exception ex) {
			ex.printStackTrace();
			s.getTransaction().rollback();
			throw ex;
		} finally {
			if (s != null)
				s.close();
		}
	}*/
	
}