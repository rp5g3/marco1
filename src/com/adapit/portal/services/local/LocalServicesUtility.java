package com.adapit.portal.services.local;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import com.adapit.portal.entidades.AddressType;
import com.adapit.portal.entidades.ComercialSolution;
import com.adapit.portal.entidades.ComercialSolutionItem;
import com.adapit.portal.entidades.Endereco;
import com.adapit.portal.services.UtilityService;
import com.adapit.portal.services.dao.hibernate.UtilityDAOHibernate;

public class LocalServicesUtility implements UtilityService{

	private UtilityDAOHibernate utilityService;

	private static LocalServicesUtility instance;

	private LocalServicesUtility() {
		try {
			XmlBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("localServices.xml"));
			utilityService = (UtilityDAOHibernate) beanFactory
					.getBean("utilityDAOHibernate");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	public static LocalServicesUtility getInstance(){
		if (instance == null) instance = new LocalServicesUtility();
		return instance;
	}
	
	@Override
	public Serializable createOrUpdate(Serializable vo) {		
		return utilityService.createOrUpdate(vo);
	}
	
	@Override
	public void saveOrUpdate(Serializable vo) {
		utilityService.saveOrUpdate(vo);		
	}

	@Override
	public void delete(final Serializable object) {
		utilityService.delete(object);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void deleteAll(final Collection object) {
		utilityService.deleteAll(object);
	}

	@Override
	public Serializable retrieveByPK(final Serializable pk) {
		return utilityService.retrieveByPK(pk);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List retrieveAll(final Serializable object) {
		return utilityService.retrieveAll(object);
	}


	@SuppressWarnings("unchecked")
	public void setObjectClass(final Class objectClass) {
		utilityService.setObjectClass(objectClass);
	}
	
	@Override
	public ComercialSolution getComercialSolutionByIdItem(int id) throws Exception {
		try {
			return utilityService.getComercialSolutionByIdItem(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("LoteServiceDAOHibernate_getLoteByIdError");
		}
	}
	
	@Override
	public String getDescricaoComercialSolutionByIdItem(int id) throws Exception {
		try {
			return  utilityService.getDescricaoComercialSolutionByIdItem(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("LoteServiceDAOHibernate_getLoteByIdError");
		}
	}

	@Override
	public String getCategoriaComercialSolutionByIdItem(int id) throws Exception {
		try {
			return  utilityService.getCategoriaComercialSolutionByIdItem(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("LoteServiceDAOHibernate_getLoteByIdError");
		}
	}

	@Override
	public ComercialSolutionItem getComercialSolutionItemByIdComercialSolution(int id) {
		try {
			return  utilityService.getComercialSolutionItemByIdComercialSolution(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			//throw new Exception("LoteServiceDAOHibernate_getLoteByIdError");
		}
		return null;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List listImagensByComercialSolutionId(int id) throws Exception{
		try {
			return  utilityService.listImagensByComercialSolutionId(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public Object delete(Object obj) {
		try {
			return  utilityService.delete(obj);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public org.hibernate.Session openSession(){
		return utilityService.openSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object load(Class clazz, Serializable id) throws Exception {
		return utilityService.load(clazz, id);
	}

	@Override
	public Object save(Object obj) throws Exception {
		return utilityService.save(obj);
	}

	@Override
	public Object update(Object obj) throws Exception {
		return utilityService.update(obj);
	}


	@Override
	public List<Endereco> listEnderecoByTipo(AddressType tipo)
			throws Exception {
		return utilityService.listEnderecoByTipo(tipo);
	}


	@Override
	public Object refresh(Object object) throws Exception {
		return utilityService.refresh(object);
	}


	@SuppressWarnings("unchecked")
	@Override
	public void deleteById(Class clazz, Serializable id) throws Exception {
		utilityService.deleteById(clazz, id);
	}
}