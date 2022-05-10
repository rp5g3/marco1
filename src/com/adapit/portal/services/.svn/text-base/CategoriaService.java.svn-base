package com.adapit.portal.services;


import java.util.List;

import com.adapit.portal.entidades.Categoria;


public interface CategoriaService{
	
	@SuppressWarnings("unchecked")
	public List listLikeName(String nome );
	
	@SuppressWarnings("unchecked")
	public List listLikeFromParentName(String joinCategoriaNome );
	
	@SuppressWarnings("unchecked")
	public List listSubCategoriasnotNull(Categoria subCategorias );
	
	@SuppressWarnings("unchecked")
	public List listCategoriasByNullParent(boolean eager);
	
	@SuppressWarnings("unchecked")
	public List listAll();
	
	public Categoria saveOrUpdate(Categoria categoria ) throws Exception;
	
	public Categoria deleteById(int id ) throws Exception;
	
	public Categoria deleteByParentId(int joinCategoriaId ) throws Exception;
	
	public Categoria deleteByName(String nome ) throws Exception;
	
	public Categoria getCategoriaById(int id ) throws Exception;
	
	public Categoria getCategoriaByBeginingName(String nome ) throws Exception;
	
	public Categoria getCategoriaByName(String nome ) throws Exception;

	@SuppressWarnings("unchecked")
	public List listByParentId(int id) throws Exception;

	public boolean merge(Categoria categoria) throws Exception;

}