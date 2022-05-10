package com.adapit.portal.services.remote;

import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.xml.XmlBeanFactory;


import com.adapit.portal.entidades.Imagem;
import com.adapit.portal.entidades.News;
import com.adapit.portal.services.NewsService;
import com.adapit.portal.services.StringQueryKind;
import com.workcase.gui.utils.SwingContext;

public class RemoteNewsService implements NewsService {

	private NewsService newsService;

	private static RemoteNewsService instance;

	private RemoteNewsService() {
		try {
			XmlBeanFactory beanFactory = SwingContext.getInstance()
					.getBeanFactory();
			newsService = (NewsService) beanFactory
					.getBean("remoteNewsServiceHttpInvokerProxy");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static RemoteNewsService getInstance() {
		if (instance == null) {
			instance = new RemoteNewsService();
		}
		return instance;
	}

	/**
	 * Retorna todos os newss em que a descrição contenha o valor passado
	 * como argumento
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
	 * Retorna o news em que a descrição é igual ao valor passado como
	 * argumento
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
		try {
			return this.newsService.saveOrUpdate(news);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
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
			throw new Exception("RemoteNewsService_deleteByIdError");
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
			throw new Exception("RemoteNewsService_getNewsByIdError");
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
			throw new Exception(
					"RemoteNewsService_getNewsByDescricaoError");
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List listLastNews(int number) {
		return this.newsService.listLastNews(number);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List listLastNews(int number, int ano) {
		return this.newsService.listLastNews(number,ano);
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