/*
 * Created on Feb 11, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.workcase.hibernate;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

/**
 * @author claiton
 *  
 */
public interface GenericDAO {

	Serializable createOrUpdate(Serializable vo);

	void delete(final Serializable object);

	@SuppressWarnings("unchecked")
	void deleteAll(final Collection objects);

	Serializable retrieveByPK(final Serializable pk) throws DataAccessException;

	@SuppressWarnings("unchecked")
	List retrieveAll(final Serializable object) throws DataAccessException;
	
	public void saveOrUpdate(Serializable obj);
	
	public Serializable save(Serializable vo);
	
	public Serializable update(Serializable vo);
	
	public Serializable merge(Serializable vo);
}