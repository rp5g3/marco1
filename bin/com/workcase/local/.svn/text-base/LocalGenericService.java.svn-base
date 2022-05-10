package com.workcase.local;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.dao.DataAccessException;

import com.workcase.gui.utils.SwingContext;
import com.workcase.hibernate.GenericDAO;

public class LocalGenericService implements GenericDAO {

	private GenericDAO genericService;

	private static LocalGenericService instance;

	private LocalGenericService() {
		try {
			XmlBeanFactory beanFactory = SwingContext.getInstance().getBeanFactory();
			genericService = (GenericDAO) beanFactory.getBean("genericDAOHibernate");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static LocalGenericService getInstance() {
		if (instance == null) {
			instance = new LocalGenericService();
		}
		return instance;
	}

	public Serializable createOrUpdate(Serializable vo) {
		return genericService.createOrUpdate(vo);
	}
	
	public Serializable update(Serializable vo) {
		return genericService.update(vo);
	}
	
	public Serializable save(Serializable vo) {
		return genericService.save(vo);
	}
	
	public Serializable merge(Serializable vo) {
		return genericService.merge(vo);
	}
	
	public void saveOrUpdate(Serializable vo) {
		genericService.createOrUpdate(vo);
	}

	public void delete(Serializable object) {
		genericService.delete(object);
	}

	@SuppressWarnings("unchecked")
	public void deleteAll(Collection objects) {
		genericService.deleteAll(objects);
	}

	@SuppressWarnings("unchecked")
	public List retrieveAll(Serializable object) throws DataAccessException {
		return genericService.retrieveAll(object);
	}

	public Serializable retrieveByPK(Serializable pk)
			throws DataAccessException {
		return retrieveByPK(pk);
	}

}
