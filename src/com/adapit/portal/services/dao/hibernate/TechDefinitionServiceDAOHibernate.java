package com.adapit.portal.services.dao.hibernate;


import java.io.Serializable;
import java.util.Collection;

import org.hibernate.NonUniqueObjectException;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.adapit.portal.entidades.TechDefinition;
import com.adapit.portal.services.TechDefinitionService;
import com.adapit.portal.services.validation.FieldMsgValidationException;
import com.adapit.portal.services.validation.ValidationException;
import com.workcase.hibernate.GenericDAO;
import com.workcase.hibernate.GenericDAOHibernate;


/**
* @spring.bean id="techDefinitionServiceDAOHibernate" singleton="true"
* @@org.springframework.transaction.interceptor.DefaultTransactionAttribute(propagationBehaviorName="PROPAGATION_REQUIRED")
*/
public class TechDefinitionServiceDAOHibernate extends GenericDAOHibernate implements TechDefinitionService, GenericDAO{
	
	private SessionFactory sessionFactory;
	
	private String entityName = TechDefinition.class.getSimpleName();;


	
	public TechDefinitionServiceDAOHibernate(){

	}
	/**
 *Filtra definições por palavra chave informada
	*@param arg0 techDefinition.keywords a palavra chave contida em keywords
 **/
	public Collection<TechDefinition> filterTechDefinitionByKeyword(java.lang.String arg0 ) throws Exception{
		org.hibernate.Session session = null;
		try{
			session = getHibernateTemplate().getSessionFactory().openSession();
			boolean whereUsed=false;
			String query="from TechDefinition techDefinition ";
			if(arg0!= null){
				if(!whereUsed){
					query+=" where ";
					whereUsed=true;
				}else query+=" and";
				query+=" lower(techDefinition.keywords) like  :arg0  ";
			}
			org.hibernate.Query q = session.createQuery(query);
			if(arg0!= null) q.setParameter("arg0", "%"+arg0.toLowerCase()+"%");

			return q.list();
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}finally{
			if(session != null && session.isOpen())
				session.close();
		}

	}
	
	public TechDefinition saveOrUpdate(TechDefinition techDefinition ) throws FieldMsgValidationException, ValidationException, NonUniqueObjectException, ConstraintViolationException, DataException, Exception{
		try{
			HibernateTemplate template = new HibernateTemplate(getSessionFactory());
			template.saveOrUpdate(techDefinition);
			return techDefinition;
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}

	}
	
	public boolean delete(TechDefinition techDefinition ) throws Exception{
		HibernateTemplate template = new HibernateTemplate(getSessionFactory());
		try{
			template.delete(techDefinition);
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}
	
	public TechDefinition loadTechDefinition(Serializable pk ) throws Exception{
		org.hibernate.Session session = getHibernateTemplate().getSessionFactory().openSession();
		try{
			TechDefinition techDefinition = (TechDefinition) session.load(TechDefinition.class, pk);
			org.hibernate.Hibernate.initialize(techDefinition);
			return techDefinition;
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
		finally{
			if(session != null && session.isOpen()) session.close();
		}

	}
	
	public Collection<TechDefinition> listAll(){
		try{
			HibernateTemplate template = new HibernateTemplate(getSessionFactory());
			return template.loadAll(TechDefinition.class);
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}

	}
	
	public boolean deleteMany( Collection<TechDefinition> techDefinitionList ) throws Exception{
		org.hibernate.Session session = getHibernateTemplate().getSessionFactory().openSession();
		try{
			session.beginTransaction();
			for(TechDefinition techDefinition: techDefinitionList){
				session.refresh(techDefinition);

				session.delete(techDefinition);
			}
			session.getTransaction().commit();

			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			session.getTransaction().rollback();
			throw ex;
		}
		finally{
			if(session != null && session.isOpen()) session.close();
		}

	}



}