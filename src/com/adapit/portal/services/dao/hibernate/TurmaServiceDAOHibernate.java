package com.adapit.portal.services.dao.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.adapit.portal.entidades.AddressType;
import com.adapit.portal.entidades.ClassificacaoTreinamento;
import com.adapit.portal.entidades.ComercialSolutionItem;
import com.adapit.portal.entidades.CondicaoParticipacaoTreinamento;
import com.adapit.portal.entidades.Endereco;
import com.adapit.portal.entidades.FormacaoTreinamento;
import com.adapit.portal.entidades.Imagem;
import com.adapit.portal.entidades.Instrutor;
import com.adapit.portal.entidades.TipoExecucaoTreinamento;
import com.adapit.portal.entidades.TipoPacoteTreinamento;
import com.adapit.portal.entidades.Treinamento;
import com.adapit.portal.entidades.TurmaTreinamento;
import com.adapit.portal.entidades.TurnoTreinamento;
import com.adapit.portal.services.TrainingClassFilterType;
import com.adapit.portal.services.TurmaService;
import com.adapit.portal.services.local.LocalServicesUtility;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.adapit.portal.util.global.FilterResultSize;
import com.workcase.hibernate.GenericDAO;
import com.workcase.hibernate.GenericDAOHibernate;

/**
 * @spring.bean id="turmaServiceDAOHibernate" singleton="true"
 * @@org.springframework.transaction.interceptor.DefaultTransactionAttribute(propagationBehaviorName="PROPAGATION_REQUIRED")
 */
public class TurmaServiceDAOHibernate extends GenericDAOHibernate implements
		TurmaService, GenericDAO {

	@SuppressWarnings("unused")
	private SessionFactory sessionFactory;
	

	private int max = FilterResultSize.turmasListMaxSize;
	
	private String turmaEntityName = TurmaTreinamento.class.getSimpleName();
	
	private String treinamentoEntityName = Treinamento.class.getSimpleName();
	
	private String condTreiEntityName = CondicaoParticipacaoTreinamento.class.getSimpleName();
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> listTrainingClassValues() throws Exception{
		Session s = null;		
		try {						
			s = LocalServicesUtility.getInstance().openSession();
			return s.createQuery("select turma.id, turma.dataTreinamento, turma.dataEncerramento from "+turmaEntityName+" turma order by turma.dataTreinamento ASC").list();					
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();
			throw e;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public TurmaTreinamento loadTrainingClassByTrainingClassId(int idTurmaTreinamento) throws Exception{
		Session s = null;		
		try {						
			s = LocalServicesUtility.getInstance().openSession();
			return (TurmaTreinamento) s.load(TurmaTreinamento.class, idTurmaTreinamento);					
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TurmaTreinamento> listTrainingClassesOnValidInterval() throws Exception{
		Session s = LocalServicesUtility.getInstance().openSession();
		try{
			return s.createQuery("from "+turmaEntityName+" l where l.dataTreinamento > current_time or l.dataEncerramento > current_time").list();					
		}catch(Exception ex){
			ex.printStackTrace();	
			throw ex;
		}finally{
			if (s != null) s.close();
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Object listTrainingClassAccordingTo(TrainingClassFilterType filtro, int left,
			boolean usarEndereco, int idEndereco, boolean usarData,
			Date dataTreinamento, Date dataEncerramento,
			boolean usarRegras, ClassificacaoTreinamento classificacaoTurmaTreinamento,
			TipoPacoteTreinamento subClassificacaoTurmaTreinamento,
			TurnoTreinamento turno,
			TipoExecucaoTreinamento tipoExecucaoTurmaTreinamento, int orderBy)
			throws Exception {
		if (filtro == null)
			throw new Exception("O tipo do filtro não pode ser nulo");
		String query = null;
		if (filtro == TrainingClassFilterType.Turma)
			query = "select l from "+turmaEntityName+" l";
		else if (filtro == TrainingClassFilterType.TurmaCount)
			query = "select count(l) from "+turmaEntityName+" l";

		String comparator1 = null;

		boolean whereused = false;
		if (usarEndereco) {
			comparator1 = " l.enderecoPresencial.id=" + idEndereco;
		}
		if (comparator1 != null) {
			query += " where " + comparator1;
			whereused = true;
		}

		if (usarData) {
			System.err.println("Filtro por Data");
			if (!whereused) {
				query += " where ";
			} else
				query += " and ";

			boolean and = false;
			if (dataTreinamento != null) {
				query += " date(l.dataTreinamento)='"
						+ AdapitVirtualFrame.formatDateTime(
								dataTreinamento, "EN-US") + "'";
				and = true;
			}
			if (dataEncerramento != null) {
				if (and)
					query += " and ";
				query += " date(l.dataEncerramento)='"
						+ AdapitVirtualFrame.formatDateTime(
								dataEncerramento, "EN-US") + "'";
				and = true;
			}			
		}

		if (usarRegras) {
			System.err.println("Filtro por Regras");
			if (!whereused)
				query += " where l.classificacao="
						+ classificacaoTurmaTreinamento.ordinal() + " and "
						+ "l.subClassificacao="
						+ subClassificacaoTurmaTreinamento.ordinal() + " and "
						+ "l.turno="
						+ turno.ordinal() + " and "
						+ "l.tipoExecucao=" + tipoExecucaoTurmaTreinamento.ordinal();
			else
				query += " and l.classificacao="
						+ classificacaoTurmaTreinamento.ordinal() + " and "
						+ "l.subClassificacao="
						+ subClassificacaoTurmaTreinamento.ordinal() + " and "
						+ "l.turno="
						+ turno.ordinal() + " and "
						+ "l.tipoExecucao=" + tipoExecucaoTurmaTreinamento.ordinal();
		}
		if (filtro == TrainingClassFilterType.Turma) {
			if (orderBy == 0)
				query += " order by l.classificacao, l.subClassificacao, l.turno, l.tipoExecucao ASC";
			else if (orderBy == 1)
				query += " order by l.subClassificacao, l.classificacao, l.turno, l.tipoExecucao ASC";
			else if (orderBy == 2)
				query += " order by l.turno, l.subClassificacao, l.classificacao, l.tipoExecucao ASC";
			else if (orderBy == 3)
				query += " order by l.tipoExecucao, l.turno, l.subClassificacao, l.classificacao  ASC";
		}

		Session s = LocalServicesUtility.getInstance().openSession();
		try {
			if (filtro == TrainingClassFilterType.Turma) {
				List<Treinamento> leiloes = s.createQuery(query).setMaxResults(max)
						.setFirstResult(max * left).list();
				return leiloes;
			} else if (filtro == TrainingClassFilterType.TurmaCount) {
				return (Integer) s.createQuery(query).uniqueResult();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			if (s != null && s.isOpen())
				s.close();
		}
		return null;

	}
	
	@Override
	public Endereco loadAddress(int id) throws Exception{
		Session s = null;		
		try {						
			s = LocalServicesUtility.getInstance().openSession();
			return (Endereco) s.load(Endereco.class, id);					
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}
	
	@Override
	public CondicaoParticipacaoTreinamento loadTrainingClassParticipationConditionByTrainingClassId(int id) throws Exception{
		Session s = null;		
		try {						
			s = LocalServicesUtility.getInstance().openSession();
			return (CondicaoParticipacaoTreinamento) s.load(CondicaoParticipacaoTreinamento.class, id);					
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}
	

	
	@Override
	public TurmaTreinamento saveOrUpdate(TurmaTreinamento l) throws Exception{
		Session s = null;		
		try {						
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
			s.saveOrUpdate(l);	
			s.getTransaction().commit();
			return l;
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();
			throw e;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}
	
	@Override
	public TurmaTreinamento loadTrainingClassCascading(TurmaTreinamento objTurmaTreinamento) throws Exception{
		Session s = LocalServicesUtility.getInstance().openSession();
		try {
			s.beginTransaction();
			objTurmaTreinamento = (TurmaTreinamento) s.load(TurmaTreinamento.class,objTurmaTreinamento.getId());
			objTurmaTreinamento.getInstrutor();
			objTurmaTreinamento.getEnderecoPresencial();
			try {
				objTurmaTreinamento.getFormacao().getNome();
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
			objTurmaTreinamento.getCondicaoParticipacao();
			if (objTurmaTreinamento.getProcesso() != null) objTurmaTreinamento.getProcesso().getContatoTreinamento();
			objTurmaTreinamento.getInstrutor().getNome();
			//objTurmaTreinamento.getComitente().getNome();
			objTurmaTreinamento.getEnderecoPresencial().getCidade();
			objTurmaTreinamento.getCondicaoParticipacao().getDescricao();
			if (objTurmaTreinamento.getImagem() != null) objTurmaTreinamento.getImagem().getId();
			return objTurmaTreinamento;
		} catch (Exception e1) {
			e1.printStackTrace();
			throw e1;
		}finally{			
			if (s != null && s.isOpen()) s.close();
		}
	}
	
	@Override
	public CondicaoParticipacaoTreinamento saveTrainingClassParticipationCondition(CondicaoParticipacaoTreinamento condPartTurmaTreinamento) throws Exception{
		Session s = null;		
		try {						
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
			if (condPartTurmaTreinamento.getId()==0) s.save(condPartTurmaTreinamento);
			else s.update(condPartTurmaTreinamento);
			s.getTransaction().commit();						
			return condPartTurmaTreinamento;
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();
			throw e;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}
	
	@Override
	public void removeScheduledTrainingFromTrainingClass(Treinamento l, TurmaTreinamento turma) throws Exception{
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
			if (s != null && s.isOpen()) s.close();
		}
	}
	
	/*@SuppressWarnings("unchecked")
	@Override
	public List<PessoaEmDivulgacao> listComitentesByTipo(PersonExpositionType tipo) throws Exception {
		Session s = null;
		List<PessoaEmDivulgacao> pessoaEmDivulgacaos = new ArrayList<PessoaEmDivulgacao>();
		try {
			s = LocalServicesUtility.getInstance().openSession();
			Iterator it = s
					.createQuery(
							"select pessoaEmDivulgacao.id, pessoaEmDivulgacao.nome from PessoaEmDivulgacao pessoaEmDivulgacao where pessoaEmDivulgacao.tipoComitente != "
									+ tipo.ordinal()
									+ " order by pessoaEmDivulgacao.nome ASC").list()
					.iterator();
			while (it.hasNext()) {
				Object obj[] = (Object[]) it.next();

				PessoaEmDivulgacao l = new PessoaEmDivulgacao();
				l.setId(((Long) obj[0]).intValue());
				l.setNome((String) obj[1]);
				pessoaEmDivulgacaos.add(l);
			}

			return pessoaEmDivulgacaos;
		} catch (Exception e1) {
			e1.printStackTrace();
			throw e1;
		} finally {
			s.close();
		}
	}*/
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Instrutor> listAllInstructors() throws Exception {
		Session s = null;
		List<Instrutor> instrutors = new ArrayList<Instrutor>();
		try {
			s = LocalServicesUtility.getInstance().openSession();
			Iterator it = s.createQuery("select instrutor.id, instrutor.nome from Instrutor instrutor order by instrutor.nome ASC").list().iterator();
			{
				while(it.hasNext()){
					Object obj[] = (Object[])it.next();
					
					Instrutor l = new Instrutor();
					l.setId(((Long)obj[0]).intValue());
					l.setNome((String)obj[1]);	
					instrutors.add(l);
				}
			}
			return instrutors;
		} catch (Exception e1) {
			e1.printStackTrace();
			throw e1;
		} finally {
			s.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CondicaoParticipacaoTreinamento> listTrainingClassParticipationConditions() throws Exception {
		Session s = null;
		List<CondicaoParticipacaoTreinamento> condicoes = new ArrayList<CondicaoParticipacaoTreinamento>();
		try {
			s = LocalServicesUtility.getInstance().openSession();
			Iterator it = s.createQuery("select cond.id, cond.descricao from "+condTreiEntityName+" cond order by cond.descricao ASC").list().iterator();
			{
				while(it.hasNext()){
					Object obj[] = (Object[])it.next();
					
					CondicaoParticipacaoTreinamento l = new CondicaoParticipacaoTreinamento();
					l.setId(((Integer)obj[0]).intValue());
					l.setDescricao((String)obj[1]);	
					condicoes.add(l);
				}
			}
			return condicoes;
		} catch (Exception e1) {
			e1.printStackTrace();
			throw e1;
		} finally {
			s.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Endereco> listAddressesByAddressType(AddressType tipo) throws Exception{
		Session s = null;
		List<Endereco> enderecos = new ArrayList<Endereco>();
		try {
			s = LocalServicesUtility.getInstance().openSession();
			Iterator it = s.createQuery("select endereco.id, endereco.cidade, endereco.rua from" +
					" Endereco endereco where endereco.tipo="+tipo.ordinal()+
					" order by endereco.cidade ASC").list().iterator();
			{
				while(it.hasNext()){
					Object obj[] = (Object[])it.next();
					
					Endereco l = new Endereco();
					l.setId(((Integer)obj[0]).intValue());
					l.setCidade((String)obj[1]);
					l.setRua((String)obj[2]);
					enderecos.add(l);
				}
			}
			return enderecos;
		} catch (Exception e1) {
			e1.printStackTrace();
			throw e1;
		} finally {
			s.close();
		}
	}
	
	@Override
	public CondicaoParticipacaoTreinamento loadTrainingClassParticipationCondition(int id) throws Exception{
		Session s = null;		
		try {						
			s = LocalServicesUtility.getInstance().openSession();
			return (CondicaoParticipacaoTreinamento) s.load(CondicaoParticipacaoTreinamento.class, id);					
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Treinamento> listScheduledTrainingsByTrainingClassId(int idTurmaTreinamento) throws Exception{
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			List list = s.createQuery("from "+treinamentoEntityName+" treinamento where treinamento.turma.id =" + idTurmaTreinamento).list();
			list.size();
			return list;
		} catch (Exception e2) {
			e2.printStackTrace();
			throw e2;
		}finally{
			if (s != null) s.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Treinamento> listAllScheduledTrainingsWithoutTrainingClass() throws Exception{
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			List list = s.createQuery("from "+treinamentoEntityName+" treinamento where treinamento.turma is null").list();
			return list;
		} catch (Exception e2) {
			e2.printStackTrace();
			throw e2;
		}finally{
			if (s != null) s.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Treinamento> listAllScheduledTrainingsWithoutDefinedSchedule() throws Exception{
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			List list = s.createQuery("from "+treinamentoEntityName+" treinamento where treinamento.agendaTreinamento is null or treinamento.agendaTreinamento.confirmada = false ").list();
			return list;
		} catch (Exception e2) {
			e2.printStackTrace();
			throw e2;
		}finally{
			if (s != null) s.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Treinamento> listAllScheduledTrainingsWithPendingApproval() throws Exception{
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			List list = s.createQuery("from "+treinamentoEntityName+" treinamento where treinamento.agendaTreinamento.encerramento.condicionado=true").list();
			return list;
		} catch (Exception e2) {
			e2.printStackTrace();
			throw e2;
		}finally{
			if (s != null) s.close();
		}
	}
	
	@Override
	public void mergeImageTrainingClass(int imageid, int turmaid) throws Exception{
		Session s = null;		
		try {						
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
			
			TurmaTreinamento t = new TurmaTreinamento();
			t.setId(turmaid);
			t = (TurmaTreinamento) s.load(TurmaTreinamento.class, turmaid);
			
			Imagem im = new Imagem();
			im.setId(imageid);
			
			im = (Imagem) s.load(Imagem.class, imageid);
			
			t.setImagem(im);
			s.merge(t);
			
			s.getTransaction().commit();						
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();
			throw e;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}
	
	@Override
	public void mergeImageTrainingFormation(int imageid, int formacaoid) throws Exception{
		Session s = null;		
		try {						
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
			
			FormacaoTreinamento t = new FormacaoTreinamento();
			t.setId(formacaoid);
			t = (FormacaoTreinamento) s.load(FormacaoTreinamento.class, formacaoid);
			
			Imagem im = new Imagem();
			im.setId(imageid);
			
			im = (Imagem) s.load(Imagem.class, imageid);
			
			t.setImagem(im);
			s.merge(t);
			
			s.getTransaction().commit();						
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();
			throw e;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}

	@Override
	public Object[] getExtraInformationFromTurma(int turmaid) throws Exception{
		Session s = null;		
		try {						
			s = LocalServicesUtility.getInstance().openSession();
			
			Object[] objs = (Object[]) s.createQuery("select turma.instrutor.nome, turma.formacao.nome from TurmaTreinamento turma where turma.id="+turmaid).uniqueResult();	
			Object[] ends = (Object[]) s.createQuery("select turma.enderecoPresencial.cidade,turma.enderecoPresencial.rua from TurmaTreinamento turma where turma.id="+turmaid).uniqueResult();
			Object o[] = new Object[4];
			if (objs.length > 0){
				o[0] = objs[0];
				o[1] = objs[1];
			}
			if (ends.length > 0){
				o[2] = ends[0];
				o[3] = ends[1];
			}
			return 	o;				
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}
	
/*	@Deprecated
	public void op() throws Exception{
		Session s = null;		
		try {						
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
			
			s.getTransaction().commit();						
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();
			throw e;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}*/
}
