package com.adapit.portal.services;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.adapit.portal.entidades.AddressType;
import com.adapit.portal.entidades.ComercialSolution;
import com.adapit.portal.entidades.ComercialSolutionItem;
import com.adapit.portal.entidades.Endereco;
import com.adapit.portal.entidades.Imagem;

public interface UtilityService {
	
	public Serializable createOrUpdate(Serializable vo) ;
	
	public void saveOrUpdate(Serializable vo) ;

	public void delete(final Serializable object) ;

	@SuppressWarnings("unchecked")
	public void deleteAll(final Collection object) ;

	public Serializable retrieveByPK(final Serializable pk);

	@SuppressWarnings("unchecked")
	public List retrieveAll(final Serializable object) ;
	
	public ComercialSolution getComercialSolutionByIdItem(int id) throws Exception ;

	public String getDescricaoComercialSolutionByIdItem(int id) throws Exception;

	public String getCategoriaComercialSolutionByIdItem(int id) throws Exception;
	
	public ComercialSolutionItem getComercialSolutionItemByIdComercialSolution(int id);
	
	public List<Imagem> listImagensByComercialSolutionId(int id) throws Exception;
	
	public Object delete(Object obj) throws Exception;
	
	public Object save(Object obj) throws Exception;
	
	public Object update(Object obj) throws Exception;
	
	@SuppressWarnings("unchecked")
	public Object load(Class clazz, Serializable id) throws Exception;
	
	public List<Endereco> listEnderecoByTipo(AddressType tipo)	throws Exception;

	public Object refresh(Object object) throws Exception;
	
	@SuppressWarnings("unchecked")
	public void deleteById(Class clazz, Serializable id) throws Exception;
	
}
