package com.adapit.portal.services;

import java.util.List;
import java.util.Vector;

import com.adapit.portal.entidades.Arquivo;
import com.adapit.portal.entidades.ComercialSolution;
import com.adapit.portal.entidades.Imagem;
import com.adapit.portal.entidades.Participante;
import com.adapit.portal.entidades.Update;
import com.adapit.portal.entidades.UpdateFile;
import com.adapit.portal.entidades.Usuario;

public interface UpdateService {

	/**
	 * Retorna todos os produtos em que a descrição contenha o valor passado
	 * como argumento
	 */
	@SuppressWarnings("unchecked")
	public List listLikeDescricao(String descricao, ComercialSolution c);

	/**
	 * Retorna o produto em que a descrição inicia pelo valor passado como
	 * argumento
	 */
	@SuppressWarnings("unchecked")
	public List listDescricaoBeginingWith(String descricao, ComercialSolution c);

	/**
	 * Retorna o produto em que a descrição termina pelo valor passado como
	 * argumento
	 */
	@SuppressWarnings("unchecked")
	public List listDescricaoEndingWith(String descricao, ComercialSolution c);

	/**
	 * Retorna o produto em que a descrição é igual ao valor passado como
	 * argumento
	 */
	@SuppressWarnings("unchecked")
	public List listByDescricao(String descricao, ComercialSolution c);
	
	/**
	 * Retorna o news em que a descrição é igual ao valor passado como
	 * argumento
	 */
	@SuppressWarnings("unchecked")
	public List listLastUpdate(int number, ComercialSolution c);
	
	/**
	 * 
	 * @param number o número de registros
	 * @param ano o ano das news
	 * @return uma lista de Update
	 */
	@SuppressWarnings("unchecked")
	public List listLastUpdate(int number, int ano, ComercialSolution c);


	/**
	 * Retorna todos os produtos que estejam de acordo com a consulta
	 */
	@SuppressWarnings("unchecked")
	public List listAccordingTo(String descricao, StringQueryKind descKind, ComercialSolution c);

	@SuppressWarnings("unchecked")
	public List listAll(ComercialSolution c);

	public Update saveOrUpdate(Update news) throws Exception;

	/**
	 * Remove o produto que contém o identificador passado como argumento
	 */
	public Update deleteById(int id) throws Exception;


	/**
	 * Retorna um produto pelo identificador
	 */
	public Update getUpdateById(int id) throws Exception;

	/**
	 * Retorna um produto pela descrição
	 */
	public Update getUpdateByDescricao(String descricao, ComercialSolution c) throws Exception;
	
	public List<Imagem> getImagensByUpdateId(int id) throws Exception;
	
	public Imagem removeImagemFromUpdate(int newsId, int imgId) throws Exception;
	
	public Update loadUpdateEagerImagens(Update news) throws Exception;
	
	public Update anexarImagemUpdate(Update news, List<Imagem> selectedImgs) throws Exception;	
	
	public Imagem createUpdateImage(Update news, Imagem img) throws Exception;
	
	@SuppressWarnings("unchecked")
	public Vector listAllUpdate( ComercialSolution c) throws Exception;

	public Vector<UpdateFile> listAllUpdateFiles(Update up, Boolean published)
			throws Exception;

	public UpdateFile saveOrUpdate(UpdateFile uf, Update updateBean, Arquivo arq,
			Arquivo inst, UpdateFile oldUpdate) throws Exception;

	public UpdateFile load(UpdateFile uf) throws Exception;

	public void delete(UpdateFile uf) throws Exception;

	public List<UpdateFile> listAllUpdateFiles(UpdateFile file, Boolean published)
			throws Exception;

	public Update newInstanceOf(Update update) throws Exception;

	public void undoVersion(UpdateFile uf, Update file) throws Exception;

	public List<Usuario> listAutorizedUsers(UpdateFile uf) throws Exception;

	public void autorizeUsers(List<Participante> list, boolean b, UpdateFile uf)
			throws Exception;
	
	public int countUpdates(ComercialSolution cs) throws Exception;

}
