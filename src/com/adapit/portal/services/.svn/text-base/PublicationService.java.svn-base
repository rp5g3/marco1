package com.adapit.portal.services;

import java.util.List;
import java.util.Vector;

import com.adapit.portal.entidades.Imagem;
import com.adapit.portal.entidades.Publication;

public interface PublicationService {

	@SuppressWarnings("unchecked")
	public List listLikeDescricao(String descricao);

	@SuppressWarnings("unchecked")
	public List listDescricaoBeginingWith(String descricao);

	@SuppressWarnings("unchecked")
	public List listDescricaoEndingWith(String descricao);

	@SuppressWarnings("unchecked")
	public List listByDescricao(String descricao);

	@SuppressWarnings("unchecked")
	public List listLastPublications(int number);
	
	@SuppressWarnings("unchecked")
	public List listLastPublications(int number, int ano);

	@SuppressWarnings("unchecked")
	public List listAccordingTo(String descricao, StringQueryKind descKind);

	@SuppressWarnings("unchecked")
	public List listAll();

	public Publication saveOrUpdate(Publication pub) throws Exception;

	public Publication deleteById(int id) throws Exception;

	public Publication getPublicationById(int id) throws Exception;

	public Publication getPublicationByDescricao(String descricao) throws Exception;
	
	public List<Imagem> getImagensByPublicationId(int id) throws Exception;
	
	public Imagem removeImagemFromPublication(int pubId, int imgId) throws Exception;
	
	public Publication loadPublicationEagerImagens(Publication pub) throws Exception;
	
	public Publication anexarImagemPublication(Publication pub, List<Imagem> selectedImgs) throws Exception;	
	
	public Imagem createPublicationImage(Publication pub, Imagem img) throws Exception;
	
	@SuppressWarnings("unchecked")
	public Vector listAllPublication() throws Exception;

}