package com.adapit.portal.services.local;

import java.util.List;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import com.adapit.portal.entidades.Categoria;
import com.adapit.portal.entidades.ComercialSolution;
import com.adapit.portal.entidades.Imagem;
import com.adapit.portal.services.ImagemService;

public class LocalImagemService implements ImagemService{
	
	private ImagemService imagemService;

	private static LocalImagemService instance;

	private LocalImagemService() {
		try {
			XmlBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("localServices.xml"));
			imagemService = (ImagemService) beanFactory
					.getBean("imagemServiceDAOHibernate");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static LocalImagemService getInstance() {
		if (instance == null) {
			instance = new LocalImagemService();
		}
		return instance;
	}
	
	@Override
	public List<Imagem> listAll(int firstResult) throws Exception {
		try{
			return imagemService.listAll(firstResult);
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<Imagem> listByCategoriaId(int id, int firstResult) throws Exception {
		try{
			return imagemService.listByCategoriaId(id, firstResult);
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public Imagem removerReferenciaImagemComercialSolution(int id) throws Exception {
		try{
			return imagemService.removerReferenciaImagemComercialSolution(id);
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public byte[] getFullImageBytesFromImage(int id) throws Exception {
		return imagemService.getFullImageBytesFromImage(id);
	}

	@Override
	public byte[] getSmallImageBytesFromImage(int id) throws Exception {
		return imagemService.getSmallImageBytesFromImage(id);
	}

	@Override
	public int countAll() throws Exception {
		return imagemService.countAll();
	}

	@Override
	public int countByCategoriaId(int id) throws Exception {
		return imagemService.countByCategoriaId(id);
	}

	@Override
	public Integer countLikeDescricao(String desc) throws Exception {
		return imagemService.countLikeDescricao(desc);
	}

	@Override
	public Integer countLikeRotulo(String rot) throws Exception {
		return imagemService.countLikeRotulo(rot);
	}

	@Override
	public List<Imagem> listLikeDescricao(String desc, int firstResult)
			throws Exception {
		return imagemService.listLikeDescricao(desc, firstResult);
	}

	@Override
	public List<Imagem> listLikeRotulo(String rot, int firstResult)
			throws Exception {
		return imagemService.listLikeRotulo(rot, firstResult);
	}

	@Override
	public Imagem saveImagemMergeCategoria(Imagem img, Categoria cat)
			throws Exception {
		return imagemService.saveImagemMergeCategoria(img, cat);
	}
	
	@Override
	public void updateImageDescriptionByImageId(int id, String desc)
			throws Exception {
		imagemService.updateImageDescriptionByImageId(id, desc);
	}

	@Override
	public void updateImageRotuloByImageId(int id, String rot) throws Exception {
		imagemService.updateImageRotuloByImageId(id, rot);
	}

	@Override
	public Imagem mergeCategoriaImagem(Imagem img, Categoria cat)
			throws Exception {
		return imagemService.mergeCategoriaImagem(img, cat);
	}

	@Override
	public Imagem removerReferenciaImagemNews(int id) throws Exception {
		return imagemService.removerReferenciaImagemNews(id);
	}

	@Override
	public Imagem mergeImagemComercialSolution(Imagem img, ComercialSolution prod) throws Exception {
		return imagemService.mergeImagemComercialSolution(img, prod);
	}

	@Override
	public Imagem loadImagem(Integer id) throws Exception{
		return imagemService.loadImagem(id);
	}
	
	
}
