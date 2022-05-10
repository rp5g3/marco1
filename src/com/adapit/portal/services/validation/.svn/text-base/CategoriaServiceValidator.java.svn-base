package com.adapit.portal.services.validation;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindException;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.adapit.portal.entidades.Categoria;
import com.adapit.portal.services.CategoriaService;

/**
 * @spring.bean id="categoriaService" singleton="true"
 */
public class CategoriaServiceValidator extends AbstractValidator implements
		CategoriaService {

	private DefaultBeanValidator validator;

	private CategoriaService categoriaService;

	public void setValidator(DefaultBeanValidator validator) {
		this.validator = validator;
	}

	public DefaultBeanValidator getValidator() {
		return this.validator;
	}

	public void setCategoriaService(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}

	/**
	 * @spring.property ref="categoriaServiceDAOHibernate" singleton="true"
	 */
	public CategoriaService getCategoriaService() {
		return this.categoriaService;
	}

	public CategoriaServiceValidator() {
		super();
		setName("categoriaServiceValidator");
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
		BindException errors = new BindException(categoria, "categoria");
		validator.validate(categoria, errors);
		if (errors.hasErrors()) {
			throw new Exception("exceptions.invalidFields");
		} else {
			try {
				return this.categoriaService.saveOrUpdate(categoria);
			} catch (DataIntegrityViolationException dive) {
				dive.printStackTrace();
				throw new Exception("categoria.crudError");
			} catch (DataAccessException dae) {
				dae.printStackTrace();
				throw new Exception("exceptions.saveOrUpdate");
			}
		}
	}

	public boolean merge(Categoria categoria) throws Exception {
		BindException errors = new BindException(categoria, "categoria");
		validator.validate(categoria, errors);
		if (errors.hasErrors()) {
			throw new Exception("exceptions.invalidFields");
		} else {
			try {
				return this.categoriaService.merge(categoria);
			} catch (DataIntegrityViolationException dive) {
				dive.printStackTrace();
				throw new Exception("categoria.crudError");
			} catch (DataAccessException dae) {
				dae.printStackTrace();
				throw new Exception("exceptions.saveOrUpdate");
			}
		}
	}

	public Categoria deleteById(int id) throws Exception {
		try {
			return this.categoriaService.deleteById(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("deleteByIdError");
		}
	}

	public Categoria deleteByParentId(int joinCategoriaId) throws Exception {
		try {
			return this.categoriaService.deleteByParentId(joinCategoriaId);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("deleteByParentIdError");
		}
	}

	public Categoria deleteByName(String nome) throws Exception {
		try {
			return this.categoriaService.deleteByName(nome);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("deleteByNameError");
		}
	}

	public Categoria getCategoriaById(int id) throws Exception {
		try {
			return this.categoriaService.getCategoriaById(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("getCategoriaByIdError");
		}
	}

	public Categoria getCategoriaByBeginingName(String nome) throws Exception {
		try {
			return this.categoriaService.getCategoriaByBeginingName(nome);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("getCategoriaByBeginingNameError");
		}
	}

	public Categoria getCategoriaByName(String nome) throws Exception {
		try {
			return this.categoriaService.getCategoriaByName(nome);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("getCategoriaByNameError");
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