package com.adapit.portal.services.dao.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.support.DataAccessUtils;

import com.adapit.portal.entidades.AddressType;
import com.adapit.portal.entidades.ComercialSolution;
import com.adapit.portal.entidades.ComercialSolutionItem;
import com.adapit.portal.entidades.CommercialSolutionDetailTab;
import com.adapit.portal.entidades.CommercialSolutionDetailTabPK;
import com.adapit.portal.entidades.Endereco;
import com.adapit.portal.services.UtilityService;
import com.adapit.portal.services.local.LocalServicesUtility;
import com.workcase.hibernate.GenericDAO;
import com.workcase.hibernate.GenericDAOHibernate;
/**
 * @spring.bean id="utilityDAOHibernate" singleton="true"
 * @@org.springframework.transaction.interceptor.DefaultTransactionAttribute(propagationBehaviorName="PROPAGATION_REQUIRED")
 */
@SuppressWarnings({"serial","unchecked","unused","static-access"})
public class UtilityDAOHibernate extends GenericDAOHibernate implements GenericDAO, UtilityService {

	@SuppressWarnings("unused")
	private SessionFactory sessionFactory;

	private String comSolutionItem = ComercialSolutionItem.class.getSimpleName();
	private String comSolution = ComercialSolution.class.getSimpleName();
	
	public UtilityDAOHibernate() {

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ComercialSolution getComercialSolutionByIdItem(int id) throws Exception {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("select ip.comercialSolution from "+comSolutionItem+" ip ");
			hql.append(" where ");
			hql.append(" ip.id = " + id + " ");
			return (ComercialSolution) DataAccessUtils.uniqueResult(getHibernateTemplate()
					.find(hql.toString(), paramList.toArray()));
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("LoteServiceDAOHibernate_getLoteByIdError");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getDescricaoComercialSolutionByIdItem(int id) throws Exception {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("select p.descricao from "+comSolution+" p ");
			hql.append(" where ");
			hql.append(" p.comercialSolutionItem.id = " + id + " ");
			return  (String) DataAccessUtils.uniqueResult(getHibernateTemplate()
					.find(hql.toString(), paramList.toArray()));
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("LoteServiceDAOHibernate_getLoteByIdError");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getCategoriaComercialSolutionByIdItem(int id) throws Exception{
		try {
			{		
				List paramList = new ArrayList();
				StringBuffer hql = new StringBuffer();
				hql.append("select c.nome from Categoria c, "+comSolution+" p ");
				hql.append(" where ");
				hql.append(" c.id = p.categoria.id and p.comercialSolutionItem.id = " + id );
				return  (String) DataAccessUtils.uniqueResult(getHibernateTemplate()
						.find(hql.toString(), paramList.toArray()));
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("LoteServiceDAOHibernate_getLoteByIdError");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ComercialSolutionItem getComercialSolutionItemByIdComercialSolution(int id) {
		List paramList = new ArrayList();
		StringBuffer hql = new StringBuffer();
		hql.append("select from "+comSolutionItem+" ip, "+comSolution+" p ");
		hql.append(" where ");
		hql.append(" ip.id = p.comercialSolutionItem.id and p.comercialSolutionItem.id = " + id );
		return  (ComercialSolutionItem) DataAccessUtils.uniqueResult(getHibernateTemplate()
				.find(hql.toString(), paramList.toArray()));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List listImagensByComercialSolutionId(int id) throws Exception{
		List paramList = new ArrayList();
		StringBuffer hql = new StringBuffer();
		hql.append("from Image im ");
		hql.append(" where ");
		hql.append(" im.comercialSolution.id = " + id + " ");
		return getHibernateTemplate()
				.find(hql.toString(), paramList.toArray());
				
	}
	@Override
	public Object delete(Object obj) throws Exception{
		try {
			getHibernateTemplate().delete(obj);
			return obj;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("ComercialSolutionServiceDAOHibernate_deleteByIdError");
		}
	}
	
	public org.hibernate.Session openSession(){
		org.hibernate.Session s = getSessionFactory().openSession();
		return s;
	}

	@Override
	public Object save(Object obj) throws Exception {
		Session s = null;
		try{
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
			s.save(obj);			
			s.getTransaction().commit();
			return obj;
		}catch(Exception ex){
			ex.printStackTrace();
			s.getTransaction().rollback();
			throw ex;
		}finally{
			if (s != null && s.isOpen()) s.close();			
		}
	}

	@Override
	public Object update(Object obj) throws Exception {
		Session s = null;
		try{
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
			s.update(obj);			
			s.getTransaction().commit();
			return obj;
		}catch(Exception ex){
			ex.printStackTrace();
			s.getTransaction().rollback();
			throw ex;
		}finally{
			if (s != null && s.isOpen()) s.close();			
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void deleteById(Class clazz, Serializable id) throws Exception {
		Session s = null;
		try{
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
			String simpleName = clazz.getSimpleName();
			if (clazz != CommercialSolutionDetailTab.class){
				s.createQuery("delete "+simpleName+" obj where obj.id=:id")
				.setParameter("id", id).executeUpdate();
			}else{
				CommercialSolutionDetailTabPK pk = (CommercialSolutionDetailTabPK) id;
				/*pk.getCommercial_solution_id();
				pk.getDetail_tab_name();
				pk.getDetail_text_language();*/
				s.createQuery("delete "+simpleName+" obj where obj.commercial_solution_id=:cid and obj.detail_tab_name=:tid and obj.detail_text_language=:xid")
				.setParameter("cid", pk.getCommercial_solution_id())
				.setParameter("tid", pk.getDetail_tab_name())
				.setParameter("xid", pk.getDetail_text_language())
				.executeUpdate();
			}
			s.getTransaction().commit();
			
		}catch(org.hibernate.exception.DataException ex){
			ex.printStackTrace();
			s.getTransaction().rollback();
			throw ex;
		}catch(Exception ex){
			ex.printStackTrace();
			s.getTransaction().rollback();
			throw ex;
		}finally{
			if (s != null && s.isOpen()) s.close();			
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object load(Class clazz, Serializable id) throws Exception {
		Session s=null;
		try {
			s = LocalServicesUtility.getInstance().openSession();		
			s.beginTransaction();
			Object obj = s.load(clazz,id);
			System.out.println(obj.toString());
			return obj;
		} catch (Exception e1) {
			e1.printStackTrace();throw e1;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object refresh(Object object) throws Exception {
		Session s=null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			s.refresh(object);
			return object;
		} catch (Exception e1) {
			e1.printStackTrace();
			throw e1;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Endereco> listEnderecoByTipo(AddressType tipo)
			throws Exception {
		Session s = LocalServicesUtility.getInstance().openSession();
		List<Endereco> arr = new ArrayList<Endereco>();
		try {
			s.beginTransaction();
			Iterator<Object[]> it = s
					.createQuery(
							"select endereco.id, endereco.cidade, endereco.rua from Endereco endereco where endereco.tipo="
									+ tipo.ordinal()
									+ " order by endereco.cidade ASC").list()
					.iterator();
			while (it.hasNext()) {
				Object obj[] = (Object[]) it.next();
				Endereco l = new Endereco();
				l.setId(((Integer) obj[0]).intValue());
				l.setCidade((String) obj[1]);
				l.setRua((String) obj[2]);
				arr.add(l);
			}

			return arr;
		} catch (Exception e1) {
			e1.printStackTrace();
			throw e1;
		} finally {
			s.close();
		}
	}
	


}