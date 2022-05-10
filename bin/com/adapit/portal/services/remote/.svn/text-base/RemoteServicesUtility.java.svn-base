package com.adapit.portal.services.remote;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.xml.XmlBeanFactory;

import com.adapit.portal.entidades.AddressType;
import com.adapit.portal.entidades.ComercialSolution;
import com.adapit.portal.entidades.ComercialSolutionItem;
import com.adapit.portal.entidades.Endereco;
import com.adapit.portal.entidades.Imagem;
import com.adapit.portal.services.UtilityService;
import com.workcase.gui.utils.SwingContext;

public class RemoteServicesUtility implements UtilityService{
	private UtilityService utilityService;

	private static RemoteServicesUtility instance;

	private RemoteServicesUtility() {
		try {
			XmlBeanFactory beanFactory = SwingContext.getInstance()
					.getBeanFactory();
			utilityService = (UtilityService) beanFactory
					.getBean("remoteUtilityServiceHttpInvokerProxy");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static RemoteServicesUtility getInstance() {
		if (instance == null)
			instance = new RemoteServicesUtility();
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

	@Override
	public Object delete(Object obj) throws Exception {
		return utilityService.delete(obj);
	}

	@Override
	public String getCategoriaComercialSolutionByIdItem(int id) throws Exception {		
		return utilityService.getCategoriaComercialSolutionByIdItem(id);
	}

	@Override
	public String getDescricaoComercialSolutionByIdItem(int id) throws Exception {
		return utilityService.getDescricaoComercialSolutionByIdItem(id);
	}

	@Override
	public ComercialSolutionItem getComercialSolutionItemByIdComercialSolution(int id) {
		return utilityService.getComercialSolutionItemByIdComercialSolution(id);
	}

	@Override
	public ComercialSolution getComercialSolutionByIdItem(int id) throws Exception {
		return utilityService.getComercialSolutionByIdItem(id);
	}

	@Override
	public List<Imagem> listImagensByComercialSolutionId(int id) throws Exception {
		return utilityService.listImagensByComercialSolutionId(id);
	}

	@Override
	public Object save(Object obj) throws Exception {
		return utilityService.save(obj);
	}

	@Override
	public Object update(Object obj) throws Exception {
		return utilityService.update(obj);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object load(Class clazz, Serializable id) throws Exception {
		return utilityService.load(clazz, id);
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