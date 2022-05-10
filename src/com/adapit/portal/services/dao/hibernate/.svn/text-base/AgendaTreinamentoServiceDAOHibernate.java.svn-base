package com.adapit.portal.services.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.adapit.portal.entidades.AgendaTreinamento;
import com.adapit.portal.entidades.Encerramento;
import com.adapit.portal.entidades.EncerramentoCondicionado;
import com.adapit.portal.entidades.ScheduledTrainingStatus;
import com.adapit.portal.entidades.StatusAgenda;
import com.adapit.portal.entidades.Treinamento;
import com.adapit.portal.entidades.TurnoTreinamento;
import com.adapit.portal.services.AgendaTreinamentoService;
import com.adapit.portal.services.local.LocalServicesUtility;
import com.adapit.portal.services.validation.ValidationException;
import com.workcase.hibernate.GenericDAO;
import com.workcase.hibernate.GenericDAOHibernate;
/**
 * @spring.bean id="agendaTreinamentoServiceDAOHibernate" singleton="true"
 * @@org.springframework.transaction.interceptor.DefaultTransactionAttribute(propagationBehaviorName="PROPAGATION_REQUIRED")
 */
@SuppressWarnings({"serial","unchecked","unused","static-access"})
public class AgendaTreinamentoServiceDAOHibernate extends GenericDAOHibernate implements 
AgendaTreinamentoService, GenericDAO{

	@SuppressWarnings("unused")
	private SessionFactory sessionFactory;
	
	private String agendaTreinamento = AgendaTreinamento.class.getSimpleName();	
	private String treinamentoEntityName = Treinamento.class.getSimpleName();
	private String encerramentoCondicionadoName = EncerramentoCondicionado.class.getSimpleName();
	
	@Override
	public float updateIncremento(int idAgenda, float valor) throws Exception{
		Session s = null;
		try{
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
			
			Query query = s.createQuery("update "+agendaTreinamento+" al set al.incremento=:inc where al.id=:id");
			query.setParameter("inc", valor);			
			query.setParameter("id", idAgenda);
			query.executeUpdate();
			
			s.getTransaction().commit();
			
			float val = (Float) s.createQuery("select al.incremento from "+agendaTreinamento+" al where al.id=:id").setParameter("id", idAgenda).uniqueResult();
			return val;
		}catch(Exception ex){
			ex.printStackTrace();
			s.getTransaction().rollback();
			throw ex;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}
	

	
	@Override
	public boolean isAgendaEncerrada(int idAgenda) throws Exception{
		Session s = null;		
		try {						
			s = LocalServicesUtility.getInstance().openSession();
			String q = "select al.encerrada from "+agendaTreinamento+" al where al.id="+idAgenda;
			return (Boolean) s.createQuery(q).uniqueResult();						
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();
			throw e;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}

	
	@Override
	public TurnoTreinamento getScheduledTrainingPeriod(int idAgenda) throws Exception{
		Session s = null;		
		try {						
			s = LocalServicesUtility.getInstance().openSession();
			TurnoTreinamento regra = (TurnoTreinamento) 
				s.createQuery(
						"select a.treinamento.turma.turno from "+agendaTreinamento+" a where a.id="+idAgenda)
						.uniqueResult();
			return regra;
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();
			throw e;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}

	
	@Override
	public int getScheduledTrainingScheduleNumber(int idAgenda) throws Exception{
		Session s = null;		
		try {						
			s = LocalServicesUtility.getInstance().openSession();
			int size = (Integer) s.createQuery("select size(t.agendas) from "+treinamentoEntityName+" t where t.agendaTreinamento.id="+idAgenda).uniqueResult();
			return size;
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
	@Deprecated
	public List<Treinamento> listScheduledTrainingToPrepareAccount(int idLeilao) throws Exception{
		Session s = null;		
		try {
			s = LocalServicesUtility.getInstance().openSession();
			List list = s.createQuery("select lote.id, lote.codigo from "+treinamentoEntityName+" lote" +
					" where lote.arrematado=true" +
					" and lote.status<>'"+ScheduledTrainingStatus.Não_homologado.name()+"'" +
					" and lote.turma.id =" + 
					idLeilao+
					" order by lote.codigo").list();			
			return list;
		} catch (Exception e2) {
			e2.printStackTrace();
			throw e2;
		}finally{
			if (s != null) s.close();
		}
	}

	
	@Override
	public EncerramentoCondicionado aprovarHomologacao(int idLote, EncerramentoCondicionado encCond, float valorAprovado) throws Exception{
		Session s = null;		
		try {						
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();			
			//s.update("status",lote);
			s.createQuery("update "+treinamentoEntityName+" l set l.status='"+ScheduledTrainingStatus.Realizado.name()+"' where l.id="+idLote).executeUpdate();
			encCond.setAprovado(true);
			encCond.setValorAprovado(valorAprovado);
			//s.update("aprovado,valorAprovado",encerramento.getEncerrCond());
			s.createQuery("update "+encerramentoCondicionadoName+" e set e.aprovado=true, e.valorAprovado="+encCond.getValorAprovado()+" where e.id="+encCond.getId()).executeUpdate();
			s.getTransaction().commit();	
			return encCond;
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();
			throw e;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}
	
	@Override
	public AgendaTreinamento saveOrUpdateMergeScheduledTrainingId(AgendaTreinamento a, int idLote) throws Exception{
		Session s = null;		
		try {						
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
			Treinamento lote = (Treinamento) s.load(Treinamento.class,idLote);
			s.beginTransaction();
			if (a.getId() == 0){
				a.setStatus(StatusAgenda.Cadastrada);
				s.save(a);
			}
			else s.update(a);
			lote.setAgendaTreinamento(a);
			a.getId();
			s.update(lote);
			s.getTransaction().commit();	
			return a;
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();
			throw e;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}
	
	@Override
	public void confirmTrainingSchedule(AgendaTreinamento agenda, Treinamento treinamento, TurnoTreinamento turno) throws Exception{
		Session s = null;		
		try {						
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
			agenda.setTreinamento(treinamento);
			agenda.setConfirmada(true);
			agenda.setStatus(StatusAgenda.Confirmada);
			agenda.setTurno(turno);			
			//s.update("confirmada,status",agenda);
			s.createQuery("update "+AgendaTreinamento.class.getSimpleName()+
					" agenda set agenda.status=:status," +
					" agenda.confirmada=:conf," +
					" agenda.turno=:turno where agenda.id=:id")
					.setParameter("status",agenda.getStatus().ordinal())
					.setParameter("conf",agenda.isConfirmada())
					.setParameter("turno",agenda.getTurno().ordinal())
					.setParameter("id",agenda.getId())
					.executeUpdate();
			
			s.createQuery("update "+Treinamento.class.getSimpleName()+" t " +
					" set t.status=:status where t.id=:id")
					.setParameter("id",treinamento.getId())
					.setParameter("status",treinamento.getStatus().name())
					.executeUpdate();
			
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
	public void cancelTrainingSchedule(AgendaTreinamento agenda) throws Exception{
		Session s = null;		
		try {						
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
			agenda.setConfirmada(false);
			agenda.setStatus(StatusAgenda.Cancelada);
			//agenda.setObs("Esta agenda foi cancelada ");
			s.update("confirmada,status",agenda);
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
	public void generateScheduleHistoryByScheduledTrainingId(int idLote) throws Exception{
		Session s = null;		
		try {						
			s = LocalServicesUtility.getInstance().openSession();			
			Treinamento lote = (Treinamento) s.load(Treinamento.class,idLote);
			if (lote == null) throw new ValidationException("Treinamento não encontrado! Passe um identificador válido");
			if (lote.getAgendaTreinamento() != null){
				lote.getAgendas().add(lote.getAgendaTreinamento());
				s.beginTransaction();
				s.update(lote);
				//s.update(lote.getAgendas());
				s.getTransaction().commit();				
			}				
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();
			throw e;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}
	
	@Override
	public Encerramento loadEncerramento(int idEncerr) throws Exception{
		Session s = null;		
		try {						
			s = LocalServicesUtility.getInstance().openSession();
			Encerramento encerramento = (Encerramento) s.load(Encerramento.class, idEncerr);
			
			return encerramento;			
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
