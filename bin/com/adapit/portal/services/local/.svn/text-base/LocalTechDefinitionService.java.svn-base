package com.adapit.portal.services.local;


import java.io.Serializable;
import java.util.Collection;

import org.hibernate.NonUniqueObjectException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.xml.XmlBeanFactory;

import com.adapit.portal.entidades.TechDefinition;
import com.adapit.portal.services.TechDefinitionService;
import com.adapit.portal.services.validation.FieldMsgValidationException;
import com.adapit.portal.services.validation.ValidationException;


public class LocalTechDefinitionService implements TechDefinitionService{
	
	private TechDefinitionService techDefinitionService;
	
	private static LocalTechDefinitionService instance;


	
	private LocalTechDefinitionService(){
		try{
			XmlBeanFactory beanFactory = new XmlBeanFactory(new org.springframework.core.io.ClassPathResource("localServices.xml"));
			techDefinitionService = (TechDefinitionService) beanFactory.getBean("localTechDefinitionServiceDAOHibernate");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public static LocalTechDefinitionService getInstance(){
		if (instance == null){
			instance = new LocalTechDefinitionService();
		}
		return instance;
	}
	/**
 *Filtra definições por palavra chave informada
	*@param arg0 techDefinition.keywords a palavra chave contida em keywords
 **/
	public Collection<TechDefinition> filterTechDefinitionByKeyword(java.lang.String arg0 ) throws Exception{
		try{
			return this.techDefinitionService.filterTechDefinitionByKeyword(arg0);
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}
	
	public TechDefinition saveOrUpdate(TechDefinition techDefinition ) throws FieldMsgValidationException, ValidationException, NonUniqueObjectException, ConstraintViolationException, DataException, Exception{
		try{
			return this.techDefinitionService.saveOrUpdate(techDefinition);
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}
	
	public boolean delete(TechDefinition techDefinition ) throws Exception{
		try{
			return this.techDefinitionService.delete(techDefinition);
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}
	
	public TechDefinition loadTechDefinition(Serializable pk ) throws Exception{
		try{
			return this.techDefinitionService.loadTechDefinition(pk);
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}
	
	public Collection<TechDefinition> listAll(){
		try{
			return this.techDefinitionService.listAll();
		}catch(Exception ex){
			ex.printStackTrace();

		}
		return null;
	}
	
	public boolean deleteMany( Collection<TechDefinition> techDefinitionList ) throws Exception{
		try{
			return this.techDefinitionService.deleteMany(techDefinitionList);
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}



}