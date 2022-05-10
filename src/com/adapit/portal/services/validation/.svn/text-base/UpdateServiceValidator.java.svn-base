package com.adapit.portal.services.validation;

import java.util.List;
import java.util.Vector;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindException;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.adapit.portal.entidades.Arquivo;
import com.adapit.portal.entidades.ComercialSolution;
import com.adapit.portal.entidades.Imagem;
import com.adapit.portal.entidades.Participante;
import com.adapit.portal.entidades.Update;
import com.adapit.portal.entidades.UpdateFile;
import com.adapit.portal.entidades.Usuario;
import com.adapit.portal.services.UpdateService;
import com.adapit.portal.services.StringQueryKind;

/**
 * @spring.bean id="updateService" singleton="true"
 */
public class UpdateServiceValidator extends AbstractValidator implements
		UpdateService {

	private DefaultBeanValidator validator;

	private UpdateService updateService;

	public void setValidator(DefaultBeanValidator validator) {
		this.validator = validator;
	}

	public DefaultBeanValidator getValidator() {
		return this.validator;
	}

	public void setUpdateService(UpdateService updateService) {
		this.updateService = updateService;
	}

	/**
	 * @spring.property ref="updateServiceDAOHibernate" singleton="true"
	 */
	public UpdateService getUpdateService() {
		return this.updateService;
	}

	public UpdateServiceValidator() {
		super();
		setName("updateServiceValidator");
	}

	/**
	 * Retorna todos os updates em que a descrição contenha o valor passado como
	 * argumento
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List listLikeDescricao(String descricao, ComercialSolution c) {
		try {
			return this.updateService.listLikeDescricao(descricao,c);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Retorna o update em que a descrição inicia pelo valor passado como
	 * argumento
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List listDescricaoBeginingWith(String descricao, ComercialSolution c) {
		try {
			return this.updateService.listDescricaoBeginingWith(descricao,c);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Retorna o update em que a descrição termina pelo valor passado como
	 * argumento
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List listDescricaoEndingWith(String descricao, ComercialSolution c) {
		try {
			return this.updateService.listDescricaoEndingWith(descricao,c);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Retorna o update em que a descrição é igual ao valor passado como argumento
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List listByDescricao(String descricao, ComercialSolution c) {
		try {
			return this.updateService.listByDescricao(descricao,c);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Retorna todos os updates que estejam de acordo com a consulta
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List listAccordingTo(String descricao, StringQueryKind descKind, ComercialSolution c) {
		try {
			return this.updateService.listAccordingTo(descricao, descKind, c);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List listAll(ComercialSolution c) {
		try {
			return this.updateService.listAll(c);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public Update saveOrUpdate(Update update) throws Exception {
		BindException errors = new BindException(update, "update");
		validator.validate(update, errors);
		if (errors.hasErrors()) {
			throw new Exception("exceptions.invalidFields");
		} else {
			try {
				return this.updateService.saveOrUpdate(update);
			} catch (DataIntegrityViolationException dive) {
				dive.printStackTrace();
				throw new Exception("update.crudError");
			} catch (DataAccessException dae) {
				dae.printStackTrace();
				throw new Exception("exceptions.saveOrUpdate");
			}
		}
	}

	/**
	 * Remove o update que contém o identificador passado como argumento
	 */
	@Override
	public Update deleteById(int id) throws Exception {
		try {
			return this.updateService.deleteById(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	/**
	 * Retorna um update pelo identificador
	 */
	@Override
	public Update getUpdateById(int id) throws Exception {
		try {
			return this.updateService.getUpdateById(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}
	
	@Override
	public List<Imagem> getImagensByUpdateId(int id) throws Exception {
		try {
			return this.updateService.getImagensByUpdateId(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	/**
	 * Retorna um update pela descrição
	 */
	@Override
	public Update getUpdateByDescricao(String descricao, ComercialSolution c) throws Exception {
		try {
			return this.updateService.getUpdateByDescricao(descricao,c);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List listLastUpdate(int number, ComercialSolution c) {
		return this.updateService.listLastUpdate(number,c);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List listLastUpdate(int number,int ano, ComercialSolution c) {
		return this.updateService.listLastUpdate(number, ano,c);
	}

	@Override
	public Update anexarImagemUpdate(Update update, List<Imagem> selectedImgs)
			throws Exception {
		return this.updateService.anexarImagemUpdate(update, selectedImgs);
	}

	@Override
	public Imagem createUpdateImage(Update update, Imagem img) throws Exception {
		return this.updateService.createUpdateImage(update, img);
	}

	@Override
	public Update loadUpdateEagerImagens(Update update) throws Exception {
		return this.updateService.loadUpdateEagerImagens(update);
	}

	@Override
	public Imagem removeImagemFromUpdate(int updateId, int imgId) throws Exception {
		return this.updateService.removeImagemFromUpdate(updateId, imgId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Vector listAllUpdate(ComercialSolution c) throws Exception {
		return updateService.listAllUpdate(c);
	}
	
	@Override
	public Vector<UpdateFile> listAllUpdateFiles(Update up, Boolean published)
			throws Exception {
		return updateService.listAllUpdateFiles(up, published);
	}

	@Override
	public UpdateFile saveOrUpdate(UpdateFile uf, Update updateBean,
			Arquivo arq, Arquivo inst, UpdateFile oldUpdate) throws Exception {
		return updateService.saveOrUpdate(uf, updateBean, arq, inst, oldUpdate);
	}

	@Override
	public UpdateFile load(UpdateFile uf) throws Exception{
		return updateService.load(uf);
	}
	
	@Override
	public void delete(UpdateFile uf) throws Exception{
		updateService.delete(uf);
	}
	
	@Override
	public List<UpdateFile> listAllUpdateFiles(UpdateFile file, Boolean published) throws Exception{
		return updateService.listAllUpdateFiles(file,published);		
	}
	
	@Override
	public Update newInstanceOf(Update update) throws Exception{		
		return updateService.newInstanceOf(update);
	}
	
	@Override
	public void undoVersion(UpdateFile uf, Update file) throws Exception{
		updateService.undoVersion(uf,file);
	}
	
	@Override
	public void autorizeUsers(List<Participante> list, boolean b, UpdateFile uf)
			throws Exception {
		updateService.autorizeUsers(list, b, uf);
	}

	@Override
	public List<Usuario> listAutorizedUsers(UpdateFile uf) throws Exception {
		return updateService.listAutorizedUsers(uf);
	}
	
	@Override
	public int countUpdates(ComercialSolution cs) throws Exception {
		return updateService.countUpdates(cs);
	}
}

