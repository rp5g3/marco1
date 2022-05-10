package com.adapit.portal.services.validation;


import java.io.Serializable;
import java.util.Collection;

import org.hibernate.NonUniqueObjectException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindException;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.adapit.portal.entidades.TechDefinition;
import com.adapit.portal.services.TechDefinitionService;

/**
* @spring.bean id="techDefinitionService" singleton="true"
*/
public class TechDefinitionServiceValidator implements TechDefinitionService{
	
	private DefaultBeanValidator validator;
	
	private TechDefinitionService techDefinitionService;


	
	public void setValidator(DefaultBeanValidator validator ){
		this.validator = validator;
	}
	
	public DefaultBeanValidator getValidator(){
		return this.validator;
	}
	
	public void setTechDefinitionService(TechDefinitionService techDefinitionService ){
		this.techDefinitionService = techDefinitionService;
	}
	/**
	* @spring.property ref="techDefinitionServiceDAOHibernate" singleton="true"
	*/
	public TechDefinitionService getTechDefinitionService(){
		return this.techDefinitionService;
	}
	
	public TechDefinitionServiceValidator(){
		super();

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

		BindException errors1 = new BindException(techDefinition, "techDefinition");
		validator.validate(techDefinition, errors1);
		if (errors1.hasErrors()) {
			throw new FieldMsgValidationException(errors1);
		}
		else {
			try {
				return this.techDefinitionService.saveOrUpdate(techDefinition);
			} catch (DataIntegrityViolationException dive) {
				dive.printStackTrace();
				throw dive;
			} catch (DataAccessException dae) {
				dae.printStackTrace();
				throw dae;
			} catch (Exception ex) {
				ex.printStackTrace();
				throw ex;
			}
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