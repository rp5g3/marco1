package com.adapit.portal.services.dao.hibernate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.adapit.portal.entidades.AgendaTreinamento;
import com.adapit.portal.entidades.CondicaoPagamento;
import com.adapit.portal.entidades.Encerramento;
import com.adapit.portal.entidades.Participante;
import com.adapit.portal.entidades.ParticipanteContaPagar;
import com.adapit.portal.entidades.ScheduledTrainingStatus;
import com.adapit.portal.entidades.Treinamento;
import com.adapit.portal.services.ContaService;
import com.adapit.portal.services.local.LocalServicesUtility;
import com.adapit.portal.services.validation.ValidationException;
import com.workcase.hibernate.GenericDAO;
import com.workcase.hibernate.GenericDAOHibernate;

/**
 * @spring.bean id="contaServiceDAOHibernate" singleton="true"
 * @@org.springframework.transaction.interceptor.DefaultTransactionAttribute(propagationBehaviorName="PROPAGATION_REQUIRED")
 */
public class ContaServiceDAOHibernate extends GenericDAOHibernate implements 
ContaService, GenericDAO{

	@SuppressWarnings("unused")
	private SessionFactory sessionFactory;
	
	private String contaEntityName = ParticipanteContaPagar.class.getSimpleName();
	
	private String treinamentoEntityName = Treinamento.class.getSimpleName();
	
	private String agendaEntityName = AgendaTreinamento.class.getSimpleName();
	
	
	
	@Override
	public Object[] loadEncerramentoValuesByAgendaId(int idAgenda) throws Exception, ValidationException{
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			
			Object objs[] = (Object[]) s.createQuery("select al.id, al.lanceInicial, al.chamadaLeilao, al.lote.leilao.regraExecucaoLeilao from "+agendaEntityName+" al where al.id="+idAgenda).uniqueResult();
			if (objs == null || objs.length == 0){
				//throw new ValidationException("Sem dados retornados na consulta 1");
				objs = (Object[]) s.createQuery("select al.id, al.lanceInicial, al.chamadaLeilao from "+agendaEntityName+" al where al.id="+idAgenda).uniqueResult();
				if (objs == null || objs.length == 0){
					throw new ValidationException("Sem dados retornados na consulta 2");					
				}
			}
			
			Encerramento encerramento = (Encerramento) s.createQuery("select al.encerramento from "+agendaEntityName+" al where al.id="+idAgenda).uniqueResult();			
			
			//inicializa o encerramento
			if (encerramento != null){
				
			}
			
			Object[] values = new Object[4];
			values[0] = encerramento;
			values[1] = objs[1];
			values[2] = objs[2];
			if (objs.length == 4) values[3] = objs[3];
			else values[3] = null;
			
			return values;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}
	
	
	private ParticipanteContaPagar gerarConta(Calendar cal, float value, 
			CondicaoPagamento condicao,
			Participante cliente, int dia){
		try {
			ParticipanteContaPagar c = new ParticipanteContaPagar();
			c.setAgenda(condicao.getAgendaTreinamento());
			c.setCliente(cliente);
			c.setCondicaoPagamento(condicao);

			//cal.getTime().setDate(dia);
			cal.set(Calendar.DAY_OF_MONTH, dia);
			
			Calendar today = Calendar.getInstance();
			if (cal.before(today))
				cal.add(Calendar.DAY_OF_MONTH, today.get(Calendar.DAY_OF_MONTH)+5);
			
			if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
				//cal.getTime().setDate(dia+1);
				cal.add(Calendar.DAY_OF_MONTH, 1);
			}
			else if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){
				//cal.getTime().setDate(dia+2);
				cal.add(Calendar.DAY_OF_MONTH, 2);
			}
			
			c.setDataVencimento(cal.getTime());
			c.setValor(value);		
				
			
			return c;
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return null;
	}
	
	private List<ParticipanteContaPagar> gerarVencimentoContas(boolean avista, float valorTotal,
			CondicaoPagamento condicao,
			Participante cliente, int dia, int carencias, int numeroPrest, int entradas){
		List<ParticipanteContaPagar>  list = new ArrayList<ParticipanteContaPagar> ();
		if (avista){
			//Date dt = new Date();
			//int mesAtual = dt.getMonth();
			
			if (carencias > 0){
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.MONTH, carencias);
				list.add(gerarConta(cal, valorTotal,condicao,cliente,dia));
			}
			else{
				Calendar cal = Calendar.getInstance();
				//cal.add(Calendar.WEEK_OF_MONTH, 1);
				//cal.add(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
				list.add(gerarConta(cal, valorTotal,condicao,cliente,dia));				
			}
		}else{
			
			//int mesInicio=0;
			//for (int i=0; i < numeroPrest; i++){
							
				if (carencias > 0){
					for (int i=0; i < numeroPrest; i++){
						Calendar cal = Calendar.getInstance();
						cal.add(Calendar.MONTH, carencias+i);
						list.add(
								gerarConta(cal, valorTotal/numeroPrest,
								condicao,cliente,dia)
						);
					}
				}
				else{					
					if (entradas > 0){
						for(int j=0; j < entradas; j++){
							Calendar cal = Calendar.getInstance();
							cal.add(Calendar.WEEK_OF_MONTH, j);
							list.add(gerarConta(cal, valorTotal/numeroPrest,
									condicao,cliente,dia));
						}
						for (int i=1; i <= (numeroPrest-entradas); i++){
							Calendar cal = Calendar.getInstance();
							
							cal.add(Calendar.MONTH, i);
							list.add(gerarConta(cal, valorTotal/numeroPrest,
									condicao,cliente,dia));
						}
					}else{
						for (int i=0; i < numeroPrest; i++){
							Calendar cal = Calendar.getInstance();
							cal.add(Calendar.MONTH, i);
							list.add(gerarConta(cal, valorTotal/numeroPrest,
									condicao,cliente,dia));
						}
					}
				}
			//}
		}
		return list;
	}
	
	@Override
	public List<ParticipanteContaPagar> gerarContasPagar(boolean avista, float valorTotal,
			CondicaoPagamento condicao,
			Participante cliente, int dia, 
			int carencias, int numeroPrest, 
			int entradas) throws Exception{
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
			List<ParticipanteContaPagar> list = gerarVencimentoContas(avista, valorTotal,
					condicao,
					cliente, dia, 
					carencias, numeroPrest, 
					entradas);
			for(ParticipanteContaPagar p : list){
				s.save(p);
			}
			s.getTransaction().commit();
			return list;			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}

	@Override
	public int countParticipanteContaPagarByAgenda(int idAgenda) throws Exception{
		Session s = null;		
		try {						
			s = LocalServicesUtility.getInstance().openSession();
			int size = (Integer) s.createQuery("select count(conta) from "+contaEntityName+" conta where conta.agenda.id="+idAgenda).uniqueResult();
			return size;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}

	@Override
	public CondicaoPagamento loadCondicaoPagamento(int idAgenda) throws Exception{
		Session s = null;		
		try {						
			s = LocalServicesUtility.getInstance().openSession();
			CondicaoPagamento cond = 
				(CondicaoPagamento)
				s.createQuery("select al.condicaoPagamento from "+agendaEntityName+" al where al.id="+idAgenda)
				.uniqueResult();
			return cond;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Treinamento> listLotesParaContasPagar(int idLeilao) throws Exception{
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			List list = s.createQuery("select lote.id, lote.codigo from "+treinamentoEntityName+" lote" +
					" where lote.arrematado=true" +
					" and lote.status='"+ScheduledTrainingStatus.Realizado.name()+"'" +
					" and lote.leilao.id =" + idLeilao+
					" order by lote.codigo").list();
						
			return list;			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}
	
	@Override
	public boolean isQuitadoWhenUpdatingContaPagar(ParticipanteContaPagar conta) throws Exception{
		Session s = null;		
		try {						
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
			s.createQuery("update "+contaEntityName+" conta" +
					" set conta.valor=:valor, " +
					"conta.dataVencimento=:dtv, " +
					"conta.paga=:paga, " +
					"conta.formaPagamento=:forma, " +
					"conta.dataPagto=:dtp " +
					" where conta.id=:id")
					.setParameter("id", conta.getId())
					.setParameter("valor", conta.getValor())
					.setParameter("dtv", conta.getDataVencimento())
					.setParameter("paga", conta.isPaga())
					.setParameter("dtp", conta.getDataPagto())
					.setParameter("forma", conta.getFormaPagamento().ordinal())
					.executeUpdate();
			String contasPagas="select count(conta) from "+contaEntityName+" conta where conta.agenda.id=:id and conta.paga=true";
			String numContas="select count(conta) from "+contaEntityName+" conta where conta.agenda.id=:id";
			int ncontas = (Integer) s.createQuery(numContas).setParameter("id", conta.getAgenda().getId()).uniqueResult();
			int ncontaspagas = (Integer) s.createQuery(contasPagas).setParameter("id", conta.getAgenda().getId()).uniqueResult();
			if (ncontas == ncontaspagas){
				s.beginTransaction();
				String updateLote="update "+treinamentoEntityName+" lote set lote.pagtoQuitado=true where lote.agendaTreinamento.id=:id";
				s.createQuery(updateLote).setParameter("id",conta.getAgenda().getId()).executeUpdate();
				s.getTransaction().commit();
				return true;
			}else{
				s.getTransaction().commit();
				return false;
			}									
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
	public List<Object[]> listContasPagarValues(int idAgenda, boolean todasContas) throws Exception{
		Session s = null;
		try {
			String somenteNPagas="select contaPagar.id, contaPagar.dataVencimento, contaPagar.valor, " +
					"contaPagar.paga, contaPagar.desitenciaArrematante, " +
					"contaPagar.dataPagto, contaPagar.formaPagamento from " +
					""+contaEntityName+" contaPagar where contaPagar.paga=false " +
					"and contaPagar.agenda.id="+idAgenda+"" +
							" order by contaPagar.dataVencimento ASC";
			String todas="select contaPagar.id, contaPagar.dataVencimento," +
					" contaPagar.valor, contaPagar.paga, contaPagar.desitenciaArrematante," +
					" contaPagar.dataPagto, contaPagar.formaPagamento" +
					" from "+contaEntityName+" contaPagar where " +
					"contaPagar.agenda.id="+idAgenda+" " +
							"order by contaPagar.dataVencimento ASC";				
			String query = null;
			if (todasContas)
				query=todas;
			else query=somenteNPagas;
			
			List list = s.createQuery(query).list();				
			
			return list;			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}
	
	@Override
	public void updateContaPagarDataVencimento(int idConta, Date dt) throws Exception{
		Session s = null;		
		try {						
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
			s.createQuery("update "+contaEntityName+" conta set conta.dataVencimento=:dt where conta.id=:id")
			.setParameter("dt", dt)
			.setParameter("id", idConta).executeUpdate();
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
	public void updateContaPagarValor(int idConta, float valor) throws Exception{
		Session s = null;		
		try {						
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
			s.createQuery("update "+contaEntityName+" conta set conta.valor=:value where conta.id=:id")
			.setParameter("value", valor)
			.setParameter("id", idConta).executeUpdate();
			s.getTransaction().commit();						
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();
			throw e;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}
	
	@Deprecated
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
	}
	
}
