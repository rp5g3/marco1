package com.adapit.portal.services;

import java.util.List;
import java.util.Vector;

import com.adapit.portal.entidades.Imagem;
import com.adapit.portal.entidades.News;

public interface NewsService{

	/**
	 * Retorna todos os produtos em que a descrição contenha o valor passado
	 * como argumento
	 */
	@SuppressWarnings("unchecked")
	public List listLikeDescricao(String descricao);

	/**
	 * Retorna o produto em que a descrição inicia pelo valor passado como
	 * argumento
	 */
	@SuppressWarnings("unchecked")
	public List listDescricaoBeginingWith(String descricao);

	/**
	 * Retorna o produto em que a descrição termina pelo valor passado como
	 * argumento
	 */
	@SuppressWarnings("unchecked")
	public List listDescricaoEndingWith(String descricao);

	/**
	 * Retorna o produto em que a descrição é igual ao valor passado como
	 * argumento
	 */
	@SuppressWarnings("unchecked")
	public List listByDescricao(String descricao);
	
	/**
	 * Retorna o news em que a descrição é igual ao valor passado como
	 * argumento
	 */
	@SuppressWarnings("unchecked")
	public List listLastNews(int number);
	
	/**
	 * 
	 * @param number o número de registros
	 * @param ano o ano das news
	 * @return uma lista de News
	 */
	@SuppressWarnings("unchecked")
	public List listLastNews(int number, int ano);


	/**
	 * Retorna todos os produtos que estejam de acordo com a consulta
	 */
	@SuppressWarnings("unchecked")
	public List listAccordingTo(String descricao, StringQueryKind descKind);

	@SuppressWarnings("unchecked")
	public List listAll();

	public News saveOrUpdate(News news) throws Exception;

	/**
	 * Remove o produto que contém o identificador passado como argumento
	 */
	public News deleteById(int id) throws Exception;


	/**
	 * Retorna um produto pelo identificador
	 */
	public News getNewsById(int id) throws Exception;

	/**
	 * Retorna um produto pela descrição
	 */
	public News getNewsByDescricao(String descricao) throws Exception;
	
	public List<Imagem> getImagensByNewsId(int id) throws Exception;
	
	public Imagem removeImagemFromNews(int newsId, int imgId) throws Exception;
	
	public News loadNewsEagerImagens(News news) throws Exception;
	
	public News anexarImagemNews(News news, List<Imagem> selectedImgs) throws Exception;	
	
	public Imagem createNewsImage(News news, Imagem img) throws Exception;
	
	@SuppressWarnings("unchecked")
	public Vector listAllNews() throws Exception;

}