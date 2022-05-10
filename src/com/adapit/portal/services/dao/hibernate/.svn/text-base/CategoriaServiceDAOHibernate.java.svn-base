package com.adapit.portal.services.dao.hibernate;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.adapit.portal.entidades.Categoria;
import com.adapit.portal.services.CategoriaService;
import com.workcase.hibernate.GenericDAO;
import com.workcase.hibernate.GenericDAOHibernate;


/**
* @spring.bean id="categoriaServiceDAOHibernate" singleton="true"
* @@org.springframework.transaction.interceptor.DefaultTransactionAttribute(propagationBehaviorName="PROPAGATION_REQUIRED")
*/

public class CategoriaServiceDAOHibernate extends GenericDAOHibernate implements CategoriaService, GenericDAO{
	
	@SuppressWarnings("unused")
	private SessionFactory sessionFactory;


	
	public CategoriaServiceDAOHibernate(){

	}
	

	@SuppressWarnings("unchecked")
	public List listLikeName(String nome ){
		try{
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from Categoria categoria ");
			hql.append(" where ");
			hql.append(" lower(categoria.nome) like ? order by categoria.nome ASC");
			paramList.add("%" + nome.toLowerCase() + "%");
			return getHibernateTemplate().find(hql.toString(), paramList.toArray());

		}catch(Exception ex){
			ex.printStackTrace();

		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List listLikeFromParentName(String joinCategoriaNome ){
		try{
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from Categoria categoria ");
			hql.append(" where ");
			hql.append(" lower(categoria.pai.nome) like ? order by categoria.nome ASC");
			paramList.add("%" + joinCategoriaNome.toLowerCase() + "%");
			return getHibernateTemplate().find(hql.toString(), paramList.toArray());

		}catch(Exception ex){
			ex.printStackTrace();

		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List listSubCategoriasnotNull(Categoria subCategorias ){
		try{
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from Categoria categoria ");
			hql.append(" where ");
			hql.append(" categoria.subCategorias is not null order by categoria.nome ASC");
			return getHibernateTemplate().find(hql.toString(), paramList.toArray());

		}catch(Exception ex){
			ex.printStackTrace();

		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List listAll(){
		try{
			HibernateTemplate template = new HibernateTemplate(getSessionFactory());
			return template.loadAll(Categoria.class);

		}catch(Exception ex){
			ex.printStackTrace();

		}
		return null;
	}
	
	public Categoria saveOrUpdate(Categoria categoria ) throws Exception{
		try{
			HibernateTemplate template = new HibernateTemplate(getSessionFactory());			
			template.saveOrUpdate(categoria);			
			return categoria;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception("CategoriaServiceDAOHibernate_saveOrUpdateError");
		}
	}
	
	public boolean merge(Categoria categoria ) throws Exception{
		try{
			HibernateTemplate template = new HibernateTemplate(getSessionFactory());
			template.merge(categoria);
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception("CategoriaServiceDAOHibernate_saveOrUpdateError");
		}
	}
	
	@SuppressWarnings("unchecked")
	public Categoria deleteById(int id ) throws Exception{
		try{
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from Categoria categoria ");
			hql.append(" where ");
			hql.append(" categoria.id = " + id + " ");
			Categoria categoria= (Categoria) DataAccessUtils.uniqueResult(getHibernateTemplate().find(hql.toString(), paramList.toArray()));

			getHibernateTemplate().delete(categoria);
			//categoria.setPai(null);
			return categoria;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception("CategoriaServiceDAOHibernate_deleteByIdError");
		}
	}
	
	@SuppressWarnings("unchecked")
	public Categoria deleteByParentId(int joinCategoriaId ) throws Exception{
		try{
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from Categoria categoria ");
			hql.append(" where ");
			hql.append(" categoria.pai.id = ? ");
			paramList.add("" + joinCategoriaId);
			Categoria categoria= (Categoria) DataAccessUtils.uniqueResult(getHibernateTemplate().find(hql.toString(), paramList.toArray()));

			getHibernateTemplate().delete(categoria);
			
			//categoria.setPai(null);
			
			return categoria;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception("CategoriaServiceDAOHibernate_deleteByParentIdError");
		}
	}
	
	@SuppressWarnings("unchecked")
	public Categoria deleteByName(String nome ) throws Exception{
		try{
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from Categoria categoria ");
			hql.append(" where ");
			hql.append(" categoria.nome = ? ");
			paramList.add("" + nome + "");
			Categoria categoria= (Categoria) DataAccessUtils.uniqueResult(getHibernateTemplate().find(hql.toString(), paramList.toArray()));

			getHibernateTemplate().delete(categoria);
			
			//categoria.setPai(null);
			return categoria;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception("CategoriaServiceDAOHibernate_deleteByNameError");
		}
	}
	
	@SuppressWarnings("unchecked")
	public Categoria getCategoriaById(int id ) throws Exception{
		try{
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from Categoria categoria ");
			hql.append(" where ");
			hql.append(" categoria.id = " + id + " order by categoria.nome ASC");
			Categoria c = (Categoria) DataAccessUtils.uniqueResult(getHibernateTemplate().find(hql.toString(), paramList.toArray()));

			//Hibernate.initialize(c.getPai());
			return c;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception("CategoriaServiceDAOHibernate_getCategoriaByIdError");
		}
	}
	
	@SuppressWarnings("unchecked")
	public Categoria getCategoriaByBeginingName(String nome ) throws Exception{
		try{
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from Categoria categoria ");
			hql.append(" where ");
			hql.append(" categoria.nome like ? order by categoria.nome ASC");
			paramList.add(nome + "%");
			Categoria c =  (Categoria) DataAccessUtils.uniqueResult(getHibernateTemplate().find(hql.toString(), paramList.toArray()));

			//Hibernate.initialize(c.getPai());
			return c;
			
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception("CategoriaServiceDAOHibernate_getCategoriaByBeginingNameError");
		}
	}
	
	@SuppressWarnings("unchecked")
	public Categoria getCategoriaByName(String nome ) throws Exception{
		try{
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from Categoria categoria ");
			hql.append(" where ");
			hql.append(" categoria.nome = ? order by categoria.nome ASC");
			paramList.add("" + nome + "");
			Categoria c =  (Categoria) DataAccessUtils.uniqueResult(getHibernateTemplate().find(hql.toString(), paramList.toArray()));
			//Hibernate.initialize(c.getPai());
			return c;
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception("CategoriaServiceDAOHibernate_getCategoriaByNameError");
		}
	}

	@SuppressWarnings("unchecked")
	public List listCategoriasByNullParent(boolean eager) {
		try{
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from Categoria categoria ");
			hql.append(" where ");
			hql.append(" categoria.pai is null order by categoria.nome ASC");
			List l = getHibernateTemplate().find(hql.toString(), paramList.toArray());
			/*if (eager && l != null && l.size() > 0){
				Iterator<Categoria> it = l.iterator();
				while(it.hasNext()){
					Categoria cat = it.next();
					eagerInitialize(cat);
				}
			}*/
			return l;

		}catch(Exception ex){
			ex.printStackTrace();

		}
		return null;
	}
	

	@SuppressWarnings("unused")
	private void eagerInitialize(Categoria c){
		/*Hibernate.initialize(c.getSubCategorias());
		Set s = c.getSubCategorias();
		if (s != null && s.size() > 0){
			Iterator<Categoria> it = s.iterator();
			while(it.hasNext()){
				Categoria cat = it.next();
				eagerInitialize(cat);
			}
		}*/
	}

	@SuppressWarnings("unchecked")
	public List listByParentId(int id) throws Exception{
		try{
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from Categoria categoria ");
			hql.append(" where ");
			hql.append(" categoria.pai.id = "+id+" order by categoria.nome ASC ");
			return getHibernateTemplate().find(hql.toString(), paramList.toArray());

		}catch(Exception ex){
			ex.printStackTrace();

		}
		return null;
	}


}