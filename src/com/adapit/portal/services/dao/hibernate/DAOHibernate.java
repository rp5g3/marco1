
package com.adapit.portal.services.dao.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author F�bio Paulo Basso
 *
 * */
public class DAOHibernate extends HibernateDaoSupport{
    @SuppressWarnings("unused")
	private SessionFactory sessionFactory;
}
