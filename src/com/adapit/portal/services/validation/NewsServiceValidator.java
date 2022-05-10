package com.adapit.portal.services.validation;

import java.util.List;
import java.util.Vector;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindException;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.adapit.portal.entidades.Imagem;
import com.adapit.portal.entidades.News;
import com.adapit.portal.services.NewsService;
import com.adapit.portal.services.StringQueryKind;

/**
 * @spring.bean id="newsService" singleton="true"
 */
public class NewsServiceValidator extends AbstractValidator implements
		NewsService {

	private DefaultBeanValidator validator;

	private NewsService newsService;

	public void setValidator(DefaultBeanValidator validator) {
		this.validator = validator;
	}

	public DefaultBeanValidator getValidator() {
		return this.validator;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	/**
	 * @spring.property ref="newsServiceDAOHibernate" singleton="true"
	 */
	public NewsService getNewsService() {
		return this.newsService;
	}

	public NewsServiceValidator() {
		super();
		setName("newsServiceValidator");
	}

	/**
	 * Retorna todos os newss em que a descrição contenha o valor passado como
	 * argumento
	 */
	@SuppressWarnings("unchecked")
	public List listLikeDescricao(String descricao) {
		try {
			return this.newsService.listLikeDescricao(descricao);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Retorna o news em que a descrição inicia pelo valor passado como
	 * argumento
	 */
	@SuppressWarnings("unchecked")
	public List listDescricaoBeginingWith(String descricao) {
		try {
			return this.newsService.listDescricaoBeginingWith(descricao);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Retorna o news em que a descrição termina pelo valor passado como
	 * argumento
	 */
	@SuppressWarnings("unchecked")
	public List listDescricaoEndingWith(String descricao) {
		try {
			return this.newsService.listDescricaoEndingWith(descricao);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Retorna o news em que a descrição é igual ao valor passado como argumento
	 */
	@SuppressWarnings("unchecked")
	public List listByDescricao(String descricao) {
		try {
			return this.newsService.listByDescricao(descricao);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Retorna todos os newss que estejam de acordo com a consulta
	 */
	@SuppressWarnings("unchecked")
	public List listAccordingTo(String descricao, StringQueryKind descKind) {
		try {
			return this.newsService.listAccordingTo(descricao, descKind);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List listAll() {
		try {
			return this.newsService.listAll();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public News saveOrUpdate(News news) throws Exception {
		BindException errors = new BindException(news, "news");
		validator.validate(news, errors);
		if (errors.hasErrors()) {
			throw new Exception("exceptions.invalidFields");
		} else {
			try {
				return this.newsService.saveOrUpdate(news);
			} catch (DataIntegrityViolationException dive) {
				dive.printStackTrace();
				throw new Exception("news.crudError");
			} catch (DataAccessException dae) {
				dae.printStackTrace();
				throw new Exception("exceptions.saveOrUpdate");
			}
		}
	}

	/**
	 * Remove o news que contém o identificador passado como argumento
	 */
	public News deleteById(int id) throws Exception {
		try {
			return this.newsService.deleteById(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("deleteByIdError");
		}
	}

	/**
	 * Retorna um news pelo identificador
	 */
	public News getNewsById(int id) throws Exception {
		try {
			return this.newsService.getNewsById(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("getNewsByIdError");
		}
	}
	
	public List<Imagem> getImagensByNewsId(int id) throws Exception {
		try {
			return this.newsService.getImagensByNewsId(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("RemoteNewsService_getNewsByIdError");
		}
	}

	/**
	 * Retorna um news pela descrição
	 */
	public News getNewsByDescricao(String descricao) throws Exception {
		try {
			return this.newsService.getNewsByDescricao(descricao);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("getNewsByDescricaoError");
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List listLastNews(int number) {
		return this.newsService.listLastNews(number);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List listLastNews(int number,int ano) {
		return this.newsService.listLastNews(number, ano);
	}

	@Override
	public News anexarImagemNews(News news, List<Imagem> selectedImgs)
			throws Exception {
		return this.newsService.anexarImagemNews(news, selectedImgs);
	}

	@Override
	public Imagem createNewsImage(News news, Imagem img) throws Exception {
		return this.newsService.createNewsImage(news, img);
	}

	@Override
	public News loadNewsEagerImagens(News news) throws Exception {
		return this.newsService.loadNewsEagerImagens(news);
	}

	@Override
	public Imagem removeImagemFromNews(int newsId, int imgId) throws Exception {
		return this.newsService.removeImagemFromNews(newsId, imgId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Vector listAllNews() throws Exception {
		return newsService.listAllNews();
	}

}
