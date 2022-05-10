package com.adapit.portal.services.validation;

import java.util.List;
import java.util.Vector;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindException;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.adapit.portal.entidades.Imagem;
import com.adapit.portal.entidades.Publication;
import com.adapit.portal.services.PublicationService;
import com.adapit.portal.services.StringQueryKind;


/**
 * @spring.bean id="publicationService" singleton="true"
 */
public class PublicationServiceValidator extends AbstractValidator implements
		PublicationService {

	private DefaultBeanValidator validator;

	private PublicationService publicationService;

	public void setValidator(DefaultBeanValidator validator) {
		this.validator = validator;
	}

	public DefaultBeanValidator getValidator() {
		return this.validator;
	}

	public void setPublicationService(PublicationService publicationService) {
		this.publicationService = publicationService;
	}

	/**
	 * @spring.property ref="publicationServiceDAOHibernate" singleton="true"
	 */
	public PublicationService getPublicationService() {
		return this.publicationService;
	}

	public PublicationServiceValidator() {
		super();
		setName("publicationServiceValidator");
	}

	/**
	 * Retorna todos os publications em que a descrição contenha o valor passado como
	 * argumento
	 */
	@SuppressWarnings("unchecked")
	public List listLikeDescricao(String descricao) {
		try {
			return this.publicationService.listLikeDescricao(descricao);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Retorna o publication em que a descrição inicia pelo valor passado como
	 * argumento
	 */
	@SuppressWarnings("unchecked")
	public List listDescricaoBeginingWith(String descricao) {
		try {
			return this.publicationService.listDescricaoBeginingWith(descricao);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Retorna o publication em que a descrição termina pelo valor passado como
	 * argumento
	 */
	@SuppressWarnings("unchecked")
	public List listDescricaoEndingWith(String descricao) {
		try {
			return this.publicationService.listDescricaoEndingWith(descricao);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Retorna o publication em que a descrição é igual ao valor passado como argumento
	 */
	@SuppressWarnings("unchecked")
	public List listByDescricao(String descricao) {
		try {
			return this.publicationService.listByDescricao(descricao);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Retorna todos os publications que estejam de acordo com a consulta
	 */
	@SuppressWarnings("unchecked")
	public List listAccordingTo(String descricao, StringQueryKind descKind) {
		try {
			return this.publicationService.listAccordingTo(descricao, descKind);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List listAll() {
		try {
			return this.publicationService.listAll();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public Publication saveOrUpdate(Publication publication) throws Exception {
		BindException errors = new BindException(publication, "publication");
		validator.validate(publication, errors);
		if (errors.hasErrors()) {
			throw new Exception("exceptions.invalidFields");
		} else {
			try {
				return this.publicationService.saveOrUpdate(publication);
			} catch (DataIntegrityViolationException dive) {
				dive.printStackTrace();
				throw new Exception("publication.crudError");
			} catch (DataAccessException dae) {
				dae.printStackTrace();
				throw new Exception("exceptions.saveOrUpdate");
			}
		}
	}

	/**
	 * Remove o publication que contém o identificador passado como argumento
	 */
	public Publication deleteById(int id) throws Exception {
		try {
			return this.publicationService.deleteById(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	/**
	 * Retorna um publication pelo identificador
	 */
	public Publication getPublicationById(int id) throws Exception {
		try {
			return this.publicationService.getPublicationById(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}
	
	public List<Imagem> getImagensByPublicationId(int id) throws Exception {
		try {
			return this.publicationService.getImagensByPublicationId(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	/**
	 * Retorna um publication pela descrição
	 */
	public Publication getPublicationByDescricao(String descricao) throws Exception {
		try {
			return this.publicationService.getPublicationByDescricao(descricao);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List listLastPublications(int number) {
		return this.publicationService.listLastPublications(number);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List listLastPublications(int number,int ano) {
		return this.publicationService.listLastPublications(number, ano);
	}

	@Override
	public Publication anexarImagemPublication(Publication publication, List<Imagem> selectedImgs)
			throws Exception {
		return this.publicationService.anexarImagemPublication(publication, selectedImgs);
	}

	@Override
	public Imagem createPublicationImage(Publication publication, Imagem img) throws Exception {
		return this.publicationService.createPublicationImage(publication, img);
	}

	@Override
	public Publication loadPublicationEagerImagens(Publication publication) throws Exception {
		return this.publicationService.loadPublicationEagerImagens(publication);
	}

	@Override
	public Imagem removeImagemFromPublication(int publicationId, int imgId) throws Exception {
		return this.publicationService.removeImagemFromPublication(publicationId, imgId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Vector listAllPublication() throws Exception {
		return publicationService.listAllPublication();
	}

}
