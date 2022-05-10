package com.adapit.portal.services.local;

import java.util.List;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import com.adapit.portal.entidades.Categoria;
import com.adapit.portal.services.CategoriaService;
import com.adapit.portal.services.dao.hibernate.CategoriaServiceDAOHibernate;

public class LocalCategoriaService implements CategoriaService {

	private CategoriaService categoriaService;

	private static LocalCategoriaService instance;

	private LocalCategoriaService() {
		try {
			XmlBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("localServices.xml"));
			categoriaService = (CategoriaServiceDAOHibernate) beanFactory
					.getBean("categoriaServiceDAOHibernate");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static LocalCategoriaService getInstance() {
		if (instance == null) {
			instance = new LocalCategoriaService();
		}
		/*XmlBeanFactory beanFactory = SwingContext.getInstance()
		.getBeanFactory();
		instance.categoriaService = (CategoriaService) beanFactory.getBean("remoteCategoriaServiceHttpInvokerProxy");*/
		
		return instance;
	}

	@SuppressWarnings("unchecked")
	public List listLikeName(String nome) {
		try {
			return this.categoriaService.listLikeName(nome);
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List listLikeFromParentName(String joinCategoriaNome) {
		try {
			return this.categoriaService
					.listLikeFromParentName(joinCategoriaNome);
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List listSubCategoriasnotNull(Categoria subCategorias) {
		try {
			return this.categoriaService
					.listSubCategoriasnotNull(subCategorias);
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List listAll() {
		try {
			return this.categoriaService.listAll();
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	public Categoria saveOrUpdate(Categoria categoria) throws Exception {
		try {
			return this.categoriaService.saveOrUpdate(categoria);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("RemoteCategoriaService_saveOrUpdateError");
		}
	}
	
	public boolean merge(Categoria categoria) throws Exception {
		try {
			return this.categoriaService.merge(categoria);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("RemoteCategoriaService_saveOrUpdateError");
		}
	}

	public Categoria deleteById(int id) throws Exception {
		try {
			return this.categoriaService.deleteById(id);
		} /*catch(org.springframework.remoting.RemoteAccessException e){
			e.printStackTrace();
			return null;
		}*/catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("RemoteCategoriaService_deleteByIdError");
		}
	}

	public Categoria deleteByParentId(int joinCategoriaId) throws Exception {
		try {
			return this.categoriaService.deleteByParentId(joinCategoriaId);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("RemoteCategoriaService_deleteByParentIdError");
		}
	}

	public Categoria deleteByName(String nome) throws Exception {
		try {
			return this.categoriaService.deleteByName(nome);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("RemoteCategoriaService_deleteByNameError");
		}
	}

	public Categoria getCategoriaById(int id) throws Exception {
		try {
			return this.categoriaService.getCategoriaById(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("RemoteCategoriaService_getCategoriaByIdError");
		}
	}

	public Categoria getCategoriaByBeginingName(String nome) throws Exception {
		try {
			return this.categoriaService.getCategoriaByBeginingName(nome);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(
					"RemoteCategoriaService_getCategoriaByBeginingNameError");
		}
	}

	public Categoria getCategoriaByName(String nome) throws Exception {
		try {
			Categoria c= this.categoriaService.getCategoriaByName(nome);
			//se não LAZY, remover esta linha
			//c.setPai(null);
			return c;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(
					"RemoteCategoriaService_getCategoriaByNameError");
		}
	}

	@SuppressWarnings("unchecked")
	public List listCategoriasByNullParent(boolean eager) {
		try {
			return this.categoriaService.listCategoriasByNullParent(eager);
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List listByParentId(int id) throws Exception {
		try {
			return this.categoriaService.listByParentId(id);
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

}