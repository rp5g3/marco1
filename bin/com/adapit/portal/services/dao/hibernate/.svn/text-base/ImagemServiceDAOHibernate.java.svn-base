package com.adapit.portal.services.dao.hibernate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.adapit.portal.entidades.Categoria;
import com.adapit.portal.entidades.ComercialSolution;
import com.adapit.portal.entidades.Imagem;
import com.adapit.portal.entidades.News;
import com.adapit.portal.services.ImagemService;
import com.adapit.portal.services.local.LocalServicesUtility;
import com.adapit.portal.util.global.FilterResultSize;
import com.workcase.hibernate.GenericDAO;
import com.workcase.hibernate.GenericDAOHibernate;

/**
 * @spring.bean id="imagemServiceDAOHibernate" singleton="true"
 * @@org.springframework.transaction.interceptor.DefaultTransactionAttribute(propagationBehaviorName="PROPAGATION_REQUIRED")
 */
public class ImagemServiceDAOHibernate extends GenericDAOHibernate implements
		ImagemService, GenericDAO {

	@SuppressWarnings("unused")
	private SessionFactory sessionFactory;
	
	private String solutionEntityName= ComercialSolution.class.getSimpleName();

	@Override
	public byte[] getFullImageBytesFromImage(int id) throws Exception{
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			String query = "select im.fullImageBytes from Imagem im where im.id="+id;
			return (byte[]) s.createQuery(query).uniqueResult();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (s != null && s.isOpen())
				s.close();
		}
	}
	
	@Override
	public byte[] getSmallImageBytesFromImage(int id) throws Exception{
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			String query = "select im.smallImageBytes from Imagem im where im.id="+id;
			Object obj = s.createQuery(query).uniqueResult();
			//System.out.println(obj.getClass().getSimpleName());
			return (byte[]) obj;
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (s != null && s.isOpen())
				s.close();
		}
	}
	

	/**
	 * @param id o identificador da imagem
	 * @return a imagem no estado attached
	 */
	/*public Imagem removerReferenciaImagemComercialSolution(int id) throws Exception{
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			s.getTransaction().begin();
			Imagem imagem = (Imagem) s.get(Imagem.class, id);
			String query="select prod.id from "+solutionEntityName+" prod join prod.imagens im where im.id="+id;
			List prodids= s.createQuery(query).list();
			Iterator it = prodids.iterator();
			
			s.getTransaction().begin();
			while(it.hasNext()){
				int pid = (Integer) it.next();			
				ComercialSolution p = (ComercialSolution) s.get(ComercialSolution.class,pid);
				p.getImagens().remove(imagem);
				s.merge(p);						
			}
			s.delete(imagem);
			
			
			s.getTransaction().commit();
			return imagem;
		} catch (HibernateException e) {
			e.printStackTrace();
			if (s != null) s.getTransaction().rollback();		
			throw e;
		}finally{
			if (s != null) s.close();
		}		
	}*/
	
	@Override
	@SuppressWarnings("unchecked")
	public Imagem removerReferenciaImagemComercialSolution(int id) throws Exception{
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			s.getTransaction().begin();
			Imagem imagem = (Imagem) s.get(Imagem.class, id);
			String query="select prod.id from "+solutionEntityName+" prod join prod.imagens im where im.id="+id;
			List prodids= s.createQuery(query).list();
			Iterator it = prodids.iterator();
			
			s.getTransaction().begin();
			while(it.hasNext()){
				int pid = (Integer) it.next();			
				ComercialSolution p = (ComercialSolution) s.get(ComercialSolution.class,pid);
				p.getImagens().remove(imagem);
				s.merge(p);						
			}
			s.delete(imagem);
			
			
			s.getTransaction().commit();
			return imagem;
		} catch (HibernateException e) {
			e.printStackTrace();
			if (s != null) s.getTransaction().rollback();		
			throw e;
		}finally{
			if (s != null) s.close();
		}		
	}
	
		
/*	@Override
	public Imagem mergeImagemComercialSolution(Imagem img, ComercialSolution prod) throws Exception{
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();	
			
			s.refresh(img);
			s.refresh(prod);
			if (prod.getImagens() == null)
				prod.setImagens(new ArrayList<Imagem>());
			prod.getImagens().add(img);
			
			s.flush();
			
			s.getTransaction().commit();
			
			return img;
		} catch (Exception e1) {
			if (s != null) s.getTransaction().rollback();
			e1.printStackTrace();
			throw e1;
		}finally{
			if (s != null) s.close();
		}
	}*/
	
	@Override
	public Imagem mergeImagemComercialSolution(Imagem img, ComercialSolution prod) throws Exception{
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();	
			
			s.refresh(img);
			s.refresh(prod);
			if (prod.getImagens() == null)
				prod.setImagens(new ArrayList<Imagem>());
			prod.getImagens().add(img);
			
			s.flush();
			
			s.getTransaction().commit();
			
			return img;
		} catch (Exception e1) {
			if (s != null) s.getTransaction().rollback();
			e1.printStackTrace();
			throw e1;
		}finally{
			if (s != null) s.close();
		}
	}
	
		
	public Imagem mergeCategoriaImagem(Imagem img, Categoria cat) throws Exception{
		Session s = null;
		try {
			if (img != null && img.getId() != 0) {
				s = LocalServicesUtility.getInstance().openSession();
				s.getTransaction().begin();
				img.setCategoria(cat);
				s.merge(img);							
				s.getTransaction().commit();
				return img;
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			s.getTransaction().rollback();
			throw ex;
		} finally {
			if (s != null)
				s.close();
		}
	}

	

	
	@Override
	@SuppressWarnings("unchecked")
	public List<Imagem> listByCategoriaId(int id, int firstResult) throws Exception{		
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			String query = "select im.id, im.rotulo, im.description, im.indice from Imagem im where im.categoria.id="+id;
			List<Imagem> imgList = new ArrayList<Imagem>();
			List<Object[]> objList = s.createQuery(query)
			.setMaxResults(FilterResultSize.imagemMaxSize)
					.setFirstResult(firstResult).list();
			for(Object[] objs : objList){
				Imagem im = new Imagem();
				im.setId((Integer) objs[0]);
				im.setRotulo((String)objs[1]);
				im.setDescription((String)objs[2]);
				im.setIndice((Integer)objs[3]);
				imgList.add(im);
				String q = "select im.categoria.id, im.categoria.nome from Imagem im where im.id="+im.getId();
				Object[] cats = (Object[]) s.createQuery(q).uniqueResult();
				if (cats != null && cats.length > 0){
					Categoria c = new Categoria();
					c.setId((Integer)cats[0]);
					c.setNome((String)cats[1]);
					im.setCategoria(c);
				}
			}
			return imgList;
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (s != null && s.isOpen())
				s.close();
		}

	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Imagem> listLikeDescricao(String desc, int firstResult) throws Exception{		
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			String query = "select im.id, im.rotulo, im.description, im.indice from Imagem im where lower(im.description) like :desc";
			
			List<Imagem> imgList = new ArrayList<Imagem>();
			List<Object[]> objList = s.createQuery(query)
				.setMaxResults(FilterResultSize.imagemMaxSize)
					.setFirstResult(firstResult).setParameter("desc", "%"+desc.toLowerCase()+"%").list();
			for(Object[] objs : objList){
				Imagem im = new Imagem();
				im.setId((Integer) objs[0]);
				im.setRotulo((String)objs[1]);
				im.setDescription((String)objs[2]);
				im.setIndice((Integer)objs[3]);
				imgList.add(im);
				String q = "select im.categoria.id, im.categoria.nome from Imagem im where im.id="+im.getId();
				Object[] cats = (Object[]) s.createQuery(q).uniqueResult();
				if (cats != null && cats.length > 0){
					Categoria c = new Categoria();
					c.setId((Integer)cats[0]);
					c.setNome((String)cats[1]);
					im.setCategoria(c);
				}
			}
			return imgList;
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (s != null && s.isOpen())
				s.close();
		}

	}
	
	@Override
	@SuppressWarnings("unchecked")	
	public List<Imagem> listLikeRotulo(String rot, int firstResult) throws Exception{		
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			String query = "select im.id, im.rotulo, im.description, im.indice from Imagem im where lower(im.rotulo) like :rot";
			
			List<Imagem> imgList = new ArrayList<Imagem>();
			List<Object[]> objList = s.createQuery(query)
			.setMaxResults(FilterResultSize.imagemMaxSize)
			.setFirstResult(firstResult).setParameter("rot", "%"+rot.toLowerCase()+"%").list();
			for(Object[] objs : objList){
				Imagem im = new Imagem();
				im.setId((Integer) objs[0]);
				im.setRotulo((String)objs[1]);
				im.setDescription((String)objs[2]);
				im.setIndice((Integer)objs[3]);
				imgList.add(im);
				String q = "select im.categoria.id, im.categoria.nome from Imagem im where im.id="+im.getId();
				Object[] cats = (Object[]) s.createQuery(q).uniqueResult();
				if (cats != null && cats.length > 0){
					Categoria c = new Categoria();
					c.setId((Integer)cats[0]);
					c.setNome((String)cats[1]);
					im.setCategoria(c);
				}
			}
			return imgList;
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (s != null && s.isOpen())
				s.close();
		}

	}
	
	@Override
	public Integer countLikeRotulo(String rot) throws Exception{		
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			String query = "select count(im) from Imagem im where im.rotulo like :rot";			
			return (Integer) s.createQuery(query).setParameter("rot", "%"+rot+"%").uniqueResult();			
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (s != null && s.isOpen())
				s.close();
		}

	}
	
	@Override
	public Integer countLikeDescricao(String desc) throws Exception{		
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			String query = "select count(im) from Imagem im where im.description like :desc";			
			return (Integer) s.createQuery(query).setParameter("desc", "%"+desc+"%").uniqueResult();			
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (s != null && s.isOpen())
				s.close();
		}

	}
	
	@Override
	public int countByCategoriaId(int id) throws Exception{		
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			String query = "select count(im) from Imagem im where im.categoria.id="+id;
			return (Integer) s.createQuery(query).uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (s != null && s.isOpen())
				s.close();
		}

	}
	
	@Override
	public int countAll() throws Exception{		
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			String query = "select count(im) from Imagem im";
			return (Integer) s.createQuery(query).uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (s != null && s.isOpen())
				s.close();
		}

	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Imagem> listAll(int firstResult) throws Exception{
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			List<Imagem> imgList = new ArrayList<Imagem>();
			String query = "select im.id, im.rotulo, im.description, im.indice from Imagem im";
			query+=" order by im.id DESC";
			List<Object[]> objList = s.createQuery(query)
			.setMaxResults(FilterResultSize.imagemMaxSize)
			.setFirstResult(firstResult).list();
			for(Object[] objs : objList){
				Imagem im = new Imagem();
				im.setId((Integer) objs[0]);
				im.setRotulo((String)objs[1]);
				im.setDescription((String)objs[2]);
				im.setIndice((Integer)objs[3]);
				imgList.add(im);
				String q = "select im.categoria.id, im.categoria.nome from Imagem im where im.id="+im.getId();
				Object[] cats = (Object[]) s.createQuery(q).uniqueResult();
				if (cats != null && cats.length > 0){
					Categoria c = new Categoria();
					c.setId((Integer)cats[0]);
					c.setNome((String)cats[1]);
					im.setCategoria(c);
				}
			}
			return imgList;
			//List<Imagem> prodList = s.createQuery("select im from Imagem im").list();
			/*for(Imagem im : prodList){
				//System.out.println("Imagem: " + im.getRotulo());
				im.getFullImageBytes();
				im.getImageIcon();
			}*/
			//return prodList;
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (s != null && s.isOpen()) s.close();
		}

	}
	
	
	
	@Override
	@SuppressWarnings("unchecked")
	/**
	 * @param id o identificador da imagem
	 * @return a imagem no estado attached
	 */
	public Imagem removerReferenciaImagemNews(int id) throws Exception{
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			s.getTransaction().begin();
			Imagem imagem = (Imagem) s.get(Imagem.class, id);
			String query="select n.id from "+News.class.getSimpleName()+" n join n.imagens im where im.id="+id;
			List prodids= s.createQuery(query).list();
			Iterator it = prodids.iterator();
			
			s.getTransaction().begin();
			while(it.hasNext()){
				int pid = (Integer) it.next();			
				News n = (News) s.get(News.class,pid);
				n.getImagens().remove(imagem);
				s.merge(n);						
			}
			s.delete(imagem);
			
			
			s.getTransaction().commit();
			return imagem;
		} catch (HibernateException e) {
			e.printStackTrace();
			if (s != null) s.getTransaction().rollback();		
			throw e;
		}finally{
			if (s != null) s.close();
		}		
	}
	
	@Override
	public Imagem saveImagemMergeCategoria(Imagem img, Categoria cat) throws Exception{
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();	
			s.persist(img);
			s.getTransaction().commit();
			
			s.beginTransaction();			
			img.setCategoria(cat);
			s.merge(img);			
			s.getTransaction().commit();
			
			return img;
		} catch (Exception e1) {
			if (s != null) s.getTransaction().rollback();
			e1.printStackTrace();
			throw e1;
		}finally{
			if (s != null) s.close();
		}
	}
	
	
	
	public void updateImageDescriptionByImageId(int id, String desc) throws Exception{
		Session s = null;
		try {
			if (id != 0) {
				s = LocalServicesUtility.getInstance().openSession();
				s.getTransaction().begin();
				
				s.createQuery("update Imagem im set im.description='"+desc+"' where im.id="+id).executeUpdate();
				
				s.getTransaction().commit();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			s.getTransaction().rollback();
			throw ex;
		} finally {
			if (s != null)
				s.close();
		}
	}
	
	public void updateImageRotuloByImageId(int id, String rot) throws Exception{
		Session s = null;
		try {
			if (id != 0) {
				s = LocalServicesUtility.getInstance().openSession();
				s.getTransaction().begin();
				
				s.createQuery("update Imagem im set im.rotulo='"+rot+"' where im.id="+id).executeUpdate();
				
				s.getTransaction().commit();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			s.getTransaction().rollback();
		} finally {
			if (s != null)
				s.close();
		}
	}
	
	
	@Override
	public Imagem loadImagem(Integer id) throws Exception{
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			Imagem img = (Imagem) s.load(Imagem.class, id);
			img.getAltText();
			img.getFormat();
			img.getSmallImageBytes();
			img.getFullImageBytes();
			return img;
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (s != null && s.isOpen())
				s.close();
		}
	}

}