package com.workcase.hibernate;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;



@SuppressWarnings({"serial","unchecked","unused","static-access"})
/**
 * 
 * @@org.springframework.transaction.interceptor.DefaultTransactionAttribute(propagationBehaviorName="PROPAGATION_REQUIRED")
 */
public class GenericDAOHibernate extends HibernateDaoSupport implements
		GenericDAO {

	@SuppressWarnings("unchecked")
	private Class objectClass;

	@SuppressWarnings("unused")
	private SessionFactory sessionFactory;

	/**
	 * @@org.springframework.transaction.interceptor.DefaultTransactionAttribute(propagationBehaviorName="PROPAGATION_REQUIRES_NEW")
	 */
	public Serializable createOrUpdate(Serializable vo) {
		HibernateTemplate template = new HibernateTemplate(getSessionFactory());
		template.saveOrUpdate(vo);
		return (Serializable) vo;
	}
	
	/**
	 * @@org.springframework.transaction.interceptor.DefaultTransactionAttribute(propagationBehaviorName="PROPAGATION_REQUIRES_NEW")
	 */
	public void saveOrUpdate(Serializable vo) {
		HibernateTemplate template = new HibernateTemplate(getSessionFactory());
		template.saveOrUpdate(vo);		
	}
	
	/**
	 * @@org.springframework.transaction.interceptor.DefaultTransactionAttribute(propagationBehaviorName="PROPAGATION_REQUIRES_NEW")
	 */
	public Serializable save(Serializable vo) {
		HibernateTemplate template = new HibernateTemplate(getSessionFactory());
		
		template.save(vo);		
		
		return vo;
	}
	
	/**
	 * @@org.springframework.transaction.interceptor.DefaultTransactionAttribute(propagationBehaviorName="PROPAGATION_REQUIRES_NEW")
	 */
	public Serializable update(Serializable vo) {
		HibernateTemplate template = new HibernateTemplate(getSessionFactory());
		
		template.update(vo);		
		
		return vo;
	}
	
	/**
	 * @@org.springframework.transaction.interceptor.DefaultTransactionAttribute(propagationBehaviorName="PROPAGATION_REQUIRES_NEW")
	 */
	public Serializable merge(Serializable vo) {
		HibernateTemplate template = new HibernateTemplate(getSessionFactory());
		
		template.merge(vo);		
		
		return vo;
	}

	/**
	 * @@org.springframework.transaction.interceptor.DefaultTransactionAttribute(propagationBehaviorName="PROPAGATION_REQUIRES_NEW")
	 */
	public void delete(final Serializable object) {
		HibernateTemplate template = new HibernateTemplate(getSessionFactory());
		template.delete(object);
	}

	/**
	 * @@org.springframework.transaction.interceptor.DefaultTransactionAttribute(propagationBehaviorName="PROPAGATION_REQUIRES_NEW")
	 */
	@SuppressWarnings("unchecked")
	public void deleteAll(final Collection object) {
		HibernateTemplate template = new HibernateTemplate(getSessionFactory());
		template.deleteAll(object);
	}

	/**
	 *  
	 */
	public Serializable retrieveByPK(final Serializable pk) {
		HibernateTemplate template = new HibernateTemplate(getSessionFactory());
		return (Serializable) template.load(objectClass, pk);
	}

	/**
	 *  
	 */
	@SuppressWarnings("unchecked")
	public List retrieveAll(final Serializable object) {
		HibernateTemplate template = new HibernateTemplate(getSessionFactory());
		return template.loadAll(object.getClass());
	}

	/**
	 * @param objectClass
	 *            The objectClass to set.
	 */
	@SuppressWarnings("unchecked")
	public void setObjectClass(final Class objectClass) {
		this.objectClass = objectClass;
	}



}