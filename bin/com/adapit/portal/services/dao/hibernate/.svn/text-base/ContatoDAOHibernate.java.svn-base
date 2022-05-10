package com.adapit.portal.services.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.adapit.portal.entidades.AddressType;
import com.adapit.portal.entidades.ContatoProcessoTreinamento;
import com.adapit.portal.entidades.ContatoTreinamento;
import com.adapit.portal.entidades.Endereco;
import com.adapit.portal.entidades.TurmaTreinamento;
import com.adapit.portal.services.ContatoTreinamentoService;
import com.adapit.portal.services.local.LocalServicesUtility;
import com.workcase.hibernate.GenericDAO;
import com.workcase.hibernate.GenericDAOHibernate;
/**
 * @spring.bean id="contatoServiceDAOHibernate" singleton="true"
 * @@org.springframework.transaction.interceptor.DefaultTransactionAttribute(propagationBehaviorName="PROPAGATION_REQUIRED")
 */
@SuppressWarnings({"serial","unchecked","unused","static-access"})
public class ContatoDAOHibernate extends GenericDAOHibernate implements
ContatoTreinamentoService, GenericDAO {
	
	@SuppressWarnings("unused")
	private SessionFactory sessionFactory;

	@Override
	public int countAllProcessos() throws Exception {
		return 0;
	}

	@Override
	public int countProcessosByNomeContato(String nome) throws Exception {
		// 
		return 0;
	}

	@Override
	public void delete(ContatoTreinamento com) throws Exception {
		if (com == null) return;
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();			
			s.delete(com);
			if (com.getEndereco() != null) s.delete(com.getEndereco());
			s.getTransaction().commit();			
		} catch (Exception ex) {
			ex.printStackTrace();			
			s.getTransaction().rollback();
			throw ex;
		} finally {
			if (s != null && s.isOpen()) s.close();
		}
	}

	@Override
	public void delete(ContatoProcessoTreinamento proc) throws Exception {
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
			s.delete(proc);
			s.getTransaction().commit();	
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}finally {
			if (s != null && s.isOpen()) s.close();
		}
	}

	@Override
	public ContatoTreinamento getContatoByNomeCompleto(String nome) throws Exception {
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			String sql = "from ContatoTreinamento c where c.nome='" + nome + "'";
			ContatoTreinamento com = (ContatoTreinamento) s.createQuery(sql).uniqueResult();			
			com.getEndereco().getId();
			
			if (com.getProcessos() != null){
				com.getProcessos().size();
			}
			return com;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (s != null && s.isOpen()) s.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ContatoTreinamento> listAllContatos() throws Exception {
		Session s = null;		

		try {
			s = LocalServicesUtility.getInstance().openSession();
			List list = s.createQuery("from ContatoTreinamento c order by c.nome ASC")
					.list();
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (s != null)
				s.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ContatoProcessoTreinamento> listAllProcessos(int firstResult)
			throws Exception {
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			String query = "from ContatoProcessoTreinamento p";
			List list = s.createQuery(query).list();
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (s != null && s.isOpen())
				s.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ContatoProcessoTreinamento> listProcessosByNomeContato(String nome)
			throws Exception {
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			String sql = "from ContatoProcessoTreinamento p where p.contatoTreinamento.nome like '%" + nome + "%'";
			
			List list = s.createQuery(sql).list();	
			return list;			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (s != null && s.isOpen())
				s.close();
		}
	}

	@Override
	public ContatoTreinamento loadContato(int id) throws Exception {
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
		
			ContatoTreinamento com = (ContatoTreinamento) s.load(ContatoTreinamento.class, id);
			if (com.getProcessos() != null){
				com.getProcessos().size();
			}
			return com;		
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}finally {
			if (s != null && s.isOpen()) s.close();
		}
	}

	@Override
	public ContatoProcessoTreinamento loadProcessoTreinamento(int id) throws Exception {
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();		
			return (ContatoProcessoTreinamento) s.load(ContatoProcessoTreinamento.class, id);		
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}finally {
			if (s != null && s.isOpen()) s.close();
		}
	}

	@Override
	public Object[] merge(ContatoTreinamento com, ContatoProcessoTreinamento p) throws Exception {
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
			
			com = (ContatoTreinamento) s.load(ContatoTreinamento.class, com.getId());
									
			com.getProcessos().add(p); 
			p.setContatoTreinamento(com);
			
			s.update(p);
			
			s.getTransaction().commit();
			
			Object objs[] = new Object[2];
			objs[0] = com;
			objs[1] = p;
			return objs;
		}catch (Exception ex){
			ex.printStackTrace();
			s.getTransaction().rollback();
			throw ex;
		}finally {
			if (s != null && s.isOpen()) s.close();
		}
	}

	@Override
	public ContatoTreinamento save(ContatoTreinamento com, Endereco end) throws Exception {
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();			
			end.setTipo(AddressType.Presencial);
			s.save(end);
			s.persist(com);

			com.setEndereco(end);
			s.merge(com);

			s.getTransaction().commit();
			
			return com;
		} catch (Exception ex) {
			ex.printStackTrace();			
			s.getTransaction().rollback();
			throw ex;
		} finally {
			if (s != null && s.isOpen()) s.close();
		}
	}

	@Override
	public ContatoTreinamento update(ContatoTreinamento contatoTreinamento, Endereco end) throws Exception {
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
			
			s.update(end);
			contatoTreinamento.setEndereco(end);
			s.update(contatoTreinamento);

			s.getTransaction().commit();
			
			return contatoTreinamento;
		} catch (Exception ex) {
			ex.printStackTrace();
			s.getTransaction().rollback();
			throw ex;
		} finally {
			if (s != null && s.isOpen()) s.close();
		}
	}
	
	/*@Override
	public Object[] merge(ContatoTreinamento com, ContatoProcessoTreinamento processoTreinamento, Treinamento lote) throws Exception {
		Session s = null;		
		try {
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
			com = (ContatoTreinamento) s.load(ContatoTreinamento.class, com.getId());
			processoTreinamento.setContatoTreinamento(com);			
			
			com.getProcessos().add(processoTreinamento);
			if (processoTreinamento.getId() == 0) s.persist(processoTreinamento);
			else {
				s.update(processoTreinamento);				
			}
			s.merge(com);
			lote.setProcesso(processoTreinamento);
			s.merge(lote);
			
			s.getTransaction().commit();
			
			Object objs[] = new Object[3];
			objs[0]=com;
			objs[1]=processoTreinamento;
			objs[2]=lote;
			
			return objs;			
		}catch (Exception ex){
			ex.printStackTrace();
			s.getTransaction().rollback();
			throw ex;
		}finally {
			if (s != null && s.isOpen()) s.close();
		}
	}*/
	
	
	@Override
	public Object[] merge(ContatoTreinamento com, ContatoProcessoTreinamento processoTreinamento, TurmaTreinamento leilao) throws Exception {
		Session s = null;		
		try {
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();			
			com = (ContatoTreinamento) s.load(ContatoTreinamento.class, com.getId());
			processoTreinamento.setContatoTreinamento(com);
			
			com.getProcessos().add(processoTreinamento);
			if (processoTreinamento.getId() == 0) s.persist(processoTreinamento);
			else {
				s.update(processoTreinamento);						
			}
			s.merge(com);
			leilao.setProcesso(processoTreinamento);
			s.merge(leilao);
			
			s.getTransaction().commit();
			
			
			Object objs[] = new Object[3];
			objs[0]=com;
			objs[1]=processoTreinamento;
			objs[2]=leilao;
			
			return objs;			
		}catch (Exception ex){
			ex.printStackTrace();
			s.getTransaction().rollback();
			throw ex;
		}finally {
			if (s != null && s.isOpen()) s.close();
		}
	}

}
