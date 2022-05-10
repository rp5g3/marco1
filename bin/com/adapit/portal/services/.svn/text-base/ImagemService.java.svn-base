package com.adapit.portal.services;

import java.util.List;

import com.adapit.portal.entidades.Categoria;
import com.adapit.portal.entidades.ComercialSolution;
import com.adapit.portal.entidades.Imagem;

public interface ImagemService {


	public List<Imagem> listByCategoriaId(int id, int firstResult) throws Exception;	

	public List<Imagem> listAll(int firstResult) throws Exception;
	
	public Imagem removerReferenciaImagemComercialSolution(int id) throws Exception;
	
	public byte[] getFullImageBytesFromImage(int id) throws Exception;
	
	public byte[] getSmallImageBytesFromImage(int id) throws Exception;
	
	public List<Imagem> listLikeDescricao(String desc, int firstResult) throws Exception;
	
	public List<Imagem> listLikeRotulo(String rot, int firstResult) throws Exception;
	
	public Integer countLikeRotulo(String rot) throws Exception;
	
	public Integer countLikeDescricao(String desc) throws Exception;
	
	public int countByCategoriaId(int id) throws Exception;
	
	public int countAll() throws Exception;
	
	public Imagem saveImagemMergeCategoria(Imagem img, Categoria cat) throws Exception;
	
	public void updateImageDescriptionByImageId(int id, String desc) throws Exception;
	
	public void updateImageRotuloByImageId(int id, String rot) throws Exception;
	
	public Imagem mergeCategoriaImagem(Imagem img, Categoria cat) throws Exception;
	
	public Imagem removerReferenciaImagemNews(int id) throws Exception;
	
	public Imagem mergeImagemComercialSolution(Imagem img, ComercialSolution prod) throws Exception;

	public Imagem loadImagem(Integer id) throws Exception;
	
}
