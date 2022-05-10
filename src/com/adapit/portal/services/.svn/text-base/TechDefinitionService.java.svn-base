package com.adapit.portal.services;


import java.io.Serializable;
import java.util.Collection;

import org.hibernate.NonUniqueObjectException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;

import com.adapit.portal.entidades.TechDefinition;
import com.adapit.portal.services.validation.FieldMsgValidationException;
import com.adapit.portal.services.validation.ValidationException;


public interface TechDefinitionService{


	/**
 *Filtra definições por palavra chave informada
	*@param arg0 techDefinition.keywords a palavra chave contida em keywords
 **/
	public Collection<TechDefinition> filterTechDefinitionByKeyword(java.lang.String arg0 ) throws Exception;
	
	public TechDefinition saveOrUpdate(TechDefinition techDefinition ) throws FieldMsgValidationException, ValidationException, NonUniqueObjectException, ConstraintViolationException, DataException, Exception;
	
	public boolean delete(TechDefinition techDefinition ) throws Exception;
	
	public TechDefinition loadTechDefinition(Serializable pk ) throws Exception;
	
	public Collection<TechDefinition> listAll();
	
	public boolean deleteMany( Collection<TechDefinition> techDefinitionList ) throws Exception;



}