package com.adapit.portal.services.dao.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.adapit.portal.entidades.Imagem;
import com.adapit.portal.entidades.News;
import com.adapit.portal.services.NewsService;
import com.adapit.portal.services.StringQueryKind;
import com.adapit.portal.services.local.LocalServicesUtility;
import com.workcase.hibernate.GenericDAO;
import com.workcase.hibernate.GenericDAOHibernate;


/**
 * @spring.bean id="newsServiceDAOHibernate" singleton="true"
 * @@org.springframework.transaction.interceptor.DefaultTransactionAttribute(propagationBehaviorName="PROPAGATION_REQUIRED")
 */

public class NewsServiceDAOHibernate extends GenericDAOHibernate implements
		NewsService, GenericDAO {

	@SuppressWarnings("unused")
	private SessionFactory sessionFactory;

	public NewsServiceDAOHibernate() {

	}

	/**
	 * Retorna todos os newss em que a descrição contenha o valor passado
	 * como argumento
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List listLikeDescricao(String descricao) {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from News news ");
			hql.append(" where ");
			hql.append(" news.descricao like ? ");
			paramList.add("%" + descricao + "%");
			return getHibernateTemplate().find(hql.toString(),
					paramList.toArray());

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	/**
	 * Retorna o news em que a descrição inicia pelo valor passado como
	 * argumento
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List listDescricaoBeginingWith(String descricao) {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from News news ");
			hql.append(" where ");
			hql.append(" news.descricao like ? ");
			paramList.add(descricao + "%");
			return getHibernateTemplate().find(hql.toString(),
					paramList.toArray());

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	/**
	 * Retorna o news em que a descrição termina pelo valor passado como
	 * argumento
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List listDescricaoEndingWith(String descricao) {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from News news ");
			hql.append(" where ");
			hql.append(" news.descricao like ? ");
			paramList.add("%" + descricao);
			return getHibernateTemplate().find(hql.toString(),
					paramList.toArray());

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	/**
	 * Retorna o news em que a descrição é igual ao valor passado como
	 * argumento
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List listByDescricao(String descricao) {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from News news ");
			hql.append(" where ");
			hql.append(" news.descricao = ? ");
			paramList.add("" + descricao + "");
			return getHibernateTemplate().find(hql.toString(),
					paramList.toArray());

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	
	@Override
	@SuppressWarnings("unchecked")
	public List listAccordingTo(String descricao, StringQueryKind descKind) {
		try {
			List paramList = new ArrayList();
			
			StringBuffer fields = new StringBuffer();
			StringBuffer hql = new StringBuffer();
			hql.append(" from News news");
			
			fields.append(" news.id, news.descricao, news.resumo, news.titulo, news.dataPublicacao");
			
						
			if (descricao != null )
				hql.append(" where ");

			
			else {
				hql.append(" order by news.dataPublicacao DESC");
				String query = "select " + fields.toString() + hql.toString();
				System.out.println("EXECUTING QUERY " + query);
				
				Session s = LocalServicesUtility.getInstance().openSession();
				
				ArrayList newss = new ArrayList();
				List<Object[]> list=null;
				try{
					list= s.createQuery(query).list();
					System.out.println("EXECUTED QUERY " + query);
				
				
					if (list != null) for(Object[] objs: list){
						News p = new News();
						p.setId((Integer)objs[0]);
						p.setDescricao((String)objs[1]);
						p.setResumo((String)objs[2]);
						p.setTitulo((String)objs[3]);
						p.setDataPublicacao((Date) objs[4]);
						newss.add(p);
					}
				}catch(Exception ex){
					ex.printStackTrace();
				}finally{
					if (s != null) s.close();
				}
				System.out.println("retornando a lista de news");
				return newss;
			}

			
			if (descricao != null) {
				if (descKind == StringQueryKind.LIKE
						|| descKind == StringQueryKind.BEGINS_WITH
						|| descKind == StringQueryKind.ENDS_WITH)
					hql.append(" upper(news.descricao) like upper(?) ");
				else if (descKind == StringQueryKind.EQUALS)
					hql.append(" upper(news.descricao) = upper(?) ");

				if (descKind == StringQueryKind.LIKE)
					paramList.add("%" + descricao + "%");
				else if (descKind == StringQueryKind.EQUALS)
					paramList.add(descricao);
				else if (descKind == StringQueryKind.BEGINS_WITH)
					paramList.add(descricao + "%");
				else if (descKind == StringQueryKind.ENDS_WITH)
					paramList.add("%" + descricao);				
			}
			
			hql.append(" order by news.dataPublicacao DESC");
			String query = "select " + fields.toString() + hql.toString();
			System.out.println("EXECUTING QUERY " + query);
			
			
			Session s = LocalServicesUtility.getInstance().openSession();
			
			//return list;
			ArrayList newss = new ArrayList();
			List<Object[]> list=null;
			try{
				//list= s.createQuery(query).list();
				list= getHibernateTemplate().find(query.toString(),
						paramList.toArray());
				System.out.println("EXECUTED QUERY " + query);
			
			
				if (list != null) for(Object[] objs: list){
					News p = new News();
					p.setId((Integer)objs[0]);
					p.setDescricao((String)objs[1]);
					p.setResumo((String)objs[2]);
					p.setTitulo((String)objs[3]);
					p.setDataPublicacao((Date) objs[4]);
					newss.add(p);
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}finally{
				if (s != null) s.close();
			}
			System.out.println("retornando a lista de newss");
			return newss;
			
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}
	

	@Override
	@SuppressWarnings("unchecked")
	public List listAll() {
		try {
			HibernateTemplate template = new HibernateTemplate(
					getSessionFactory());
			return /*initializeCategoria(*/template.loadAll(News.class)/*)*/;

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	@Override
	public News saveOrUpdate(News news) throws Exception {
		Session s = LocalServicesUtility.getInstance().openSession();
		try {
			s.beginTransaction();
			if (news.getId() == 0) s.save(news);
			else s.update(news);
			s.getTransaction().commit();
			return news;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}

	/**
	 * Remove o news que contém o identificador passado como argumento
	 */
	@Override
	@SuppressWarnings("unchecked")
	public News deleteById(int id) throws Exception {
/*		Session s = LocalServicesUtility.getInstance().openSession();
		try{
			s.beginTransaction();
													
			s.createQuery("delete from News p where p.id="+id).executeUpdate();
			
			s.getTransaction().commit();									
			return null;
		}catch(Exception e){
			s.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}finally{
			if (s != null) s.close();
		}*/
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from News news ");
			hql.append(" where ");
			hql.append(" news.id = " + id + " ");
			News news = (News) DataAccessUtils
					.uniqueResult(getHibernateTemplate().find(hql.toString(),
							paramList.toArray()));

			getHibernateTemplate().delete(news);
			return news;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("NewsServiceDAOHibernate_deleteByIdError");
		}
	}


	/**
	 * Retorna um news pelo identificador
	 */
	@Override
	@SuppressWarnings("unchecked")
	public News getNewsById(int id) throws Exception {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("select news from News news");
			hql.append(" where ");
			hql.append(" news.id = " + id + " ");
			return (News) DataAccessUtils
					.uniqueResult(getHibernateTemplate().find(hql.toString(),
							paramList.toArray()));

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(
					"NewsServiceDAOHibernate_getNewsByIdError");
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Imagem> getImagensByNewsId(int id) throws Exception {
		try {
			//List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			String sql="select nm.image_id from news_image nm where nm.news_id = " + id;
			
			/*hql.append("select news.imagens from News news ");
			hql.append(" where ");
			hql.append(" news.id = " + id + " ");*/
			
			System.out.println(hql.toString());
			//return getHibernateTemplate().find(hql.toString(),	paramList.toArray());*/
			org.hibernate.Session s = LocalServicesUtility.getInstance().openSession();	
			ArrayList<Imagem> arr = new ArrayList();
			try {
				List ids = s.createSQLQuery(sql).list();
				if (ids != null && ids.iterator() != null){
					Iterator<Integer> it = ids.iterator();
					while(it.hasNext()){
						Integer idImg = it.next();
						//System.out.println(idImg);
						Imagem img = (Imagem) s.load(Imagem.class, idImg.intValue());
						img.getPath();
						img.getAltText();
						img.getDescription();
						img.getFormat();
						img.getFullImageBytes();
						img.getHeight();
						img.getImageFormat();
						img.getIndice();
						img.getPath();
						img.getWidth();
						img.getRotulo();
						//System.out.println(img.getRotulo());
						arr.add(img);
					}
				}//else System.out.println("ids é nulo");
				//return s.createQuery(hql.toString()).list();
				return arr;
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if (s != null && s.isOpen()) s.close();				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(
					"NewsServiceDAOHibernate_getNewsByIdError");
		}
		return null;
	}

	/**
	 * Retorna um news pela descrição
	 */
	@Override
	@SuppressWarnings("unchecked")
	public News getNewsByDescricao(String descricao) throws Exception {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from News news ");
			hql.append(" where ");
			hql.append(" news.descricao = ? ");
			paramList.add("" + descricao + "");
			return (News) DataAccessUtils
					.uniqueResult(getHibernateTemplate().find(hql.toString(),
							paramList.toArray()));

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(
					"NewsServiceDAOHibernate_getNewsByDescricaoError");
		}
	}
	
	/**
	 * Retorna um news e também as propriedades em cascata
	 */
	@SuppressWarnings("unchecked")
	public News getNewsByIdCascadingProperties(java.io.Serializable id, boolean images) throws Exception {
		try {
			org.hibernate.Session s = getSessionFactory().openSession();			
			News news = null;
			/*news = (News) s.load(News.class, id);
			org.hibernate.Hibernate.initialize(news);
			if (images) org.hibernate.Hibernate.initialize(news.getImagens());
			if (categoria) org.hibernate.Hibernate.initialize(news.getCategoria());
			if (itemNews) org.hibernate.Hibernate.initialize(news.getItemNews());	*/
			StringBuffer fields = new StringBuffer();
			StringBuffer hql = new StringBuffer();
			/*if (images) hql.append(" from News news join fetch news.imagens im where news.id="+id);
			else*/ hql.append(" from News news where news.id="+id);
			
			fields.append(" news.id, news.descricao");
			
			/*if (images) fields.append(", im");*/
			String query = "select " + fields.toString() + hql.toString();
			System.out.println("EXECUTING QUERY " + query);			
			
			Object[] objs=null;
			try{
				objs= (Object[]) s.createQuery(query).uniqueResult();
				System.out.println("EXECUTED QUERY " + query);
			
			
				if (objs != null && objs.length > 0){
					News p = new News();
					p.setId((Integer)objs[0]);
					p.setDescricao((String)objs[1]);
					
					if(images){
						try {
							System.out.println("buscando imagens");
							String query3="select prod.imagens from News prod where prod.id="+id;
							List<Imagem> imgs = (List) s.createQuery(query3).list();
							p.setImagens(imgs);
							System.out.println("Imagens retornadas " + imgs.size());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
					return p;
					
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}finally{
				if (s != null) s.close();
			}
			
			
			return news;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("News Cascading Load Exception");
		}
	}


	public News mergeCascadingProperties(News news, boolean images) throws Exception {
		try {
			org.hibernate.Session s = getSessionFactory().openSession();			
			try {
				s.beginTransaction();
				s.merge(news);
				if (images) s.merge(news.getImagens());
				s.getTransaction().commit();
				s.close();
			} catch (HibernateException e) {
				if (s.getTransaction() != null) s.getTransaction().rollback();				
				throw e;
			}finally{
				s.close();
			}
			return news;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("News Cascading Merge Exception");
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List listLastNews(int number) {
		String hql = "select news.id, news.titulo, news.resumo, news.dataPublicacao from News news order by news.dataPublicacao DESC";
		org.hibernate.Session s = LocalServicesUtility.getInstance()
				.openSession();
		ArrayList<News> list = new ArrayList();
		try {
			List<Object[]> results = s.createQuery(hql).setMaxResults(number).list();
			for(Object[] objs: results){
				int id = (Integer) objs[0];
				String titulo = (String) objs[1];
				String resumo = (String) objs[2];
				Date dt = (Date) objs[3];
				News n = new News();
				n.setId(id);
				n.setTitulo(titulo);
				n.setResumo(resumo);
				n.setDataPublicacao(dt);
				list.add(n);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			s.close();

		}

	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List listLastNews(int number, int ano) {
		String hql = "select news.id, news.titulo, news.resumo, news.dataPublicacao from News news where year(news.dataPublicacao) = "+ano+" order by news.dataPublicacao DESC";
		org.hibernate.Session s = LocalServicesUtility.getInstance()
				.openSession();
		ArrayList<News> list = new ArrayList();
		try {
			List<Object[]> results = s.createQuery(hql).setMaxResults(number).list();
			for(Object[] objs: results){
				int id = (Integer) objs[0];
				String titulo = (String) objs[1];
				String resumo = (String) objs[2];
				Date dt = (Date) objs[3];
				News n = new News();
				n.setId(id);
				n.setTitulo(titulo);
				n.setResumo(resumo);
				n.setDataPublicacao(dt);
				list.add(n);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			s.close();

		}

	}
	
	@Override
	public Imagem removeImagemFromNews(int newsId, int imgId) throws Exception{
		Session s = null;
		try {
			Imagem imgRemove=null;
			s=LocalServicesUtility.getInstance().openSession();
			News p = (News) s.get(News.class,newsId);
			Iterator<Imagem> it = p.getImagens().iterator();
			while(it.hasNext()){
				Imagem im = it.next();
				if (im.getId() == imgId) {
					imgRemove = im;
					break;
				}
			}
			if (imgRemove != null){
				p.getImagens().remove(imgRemove);
				s.beginTransaction();
				s.merge(p);
				s.getTransaction().commit();
				return imgRemove;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			if (s != null) s.getTransaction().rollback();
			throw e;
		}finally{
			if (s != null) s.close();
		}
		return null;
	}
	
	@Override
	public News loadNewsEagerImagens(News news) throws Exception{
		Session s = LocalServicesUtility.getInstance().openSession();
		try{
			s.load(news,news.getId());
			news.getImagens().iterator().hasNext();
			return news;
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}finally{
			s.close();
		}
	}
	
	@Override
	public News anexarImagemNews(News news, List<Imagem> selectedImgs) throws Exception{
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			s.load(news,news.getId());
			
			s.beginTransaction();
			for(Imagem im : selectedImgs){
				news.getImagens().add(im);
			}
			s.flush();
			s.getTransaction().commit();
			return news;
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();
			throw e;
		}finally{
			if (s != null) s.close();
		}
	}
	
	@Override
	public Imagem createNewsImage(News news, Imagem img) throws Exception{
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
									
			s.persist(img);
			
			s.load(news,news.getId());
			news.getImagens().add(img);				
			
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
	
	@SuppressWarnings("unchecked")
	@Override
	public Vector listAllNews() throws Exception{
		Vector v = new Vector();
		Session s = LocalServicesUtility.getInstance().openSession();
		try{
			String query = "select news.id, news.descricao, news.resumo, news.title " +
					
					" from News news" +					
					"    order by news.resumo";
			List l =  s.createSQLQuery(query).list();
			
			
			if (l != null && l.size()>0){
				Iterator it = l.iterator();
				while (it.hasNext()){
					Object objs[] = (Object[]) it.next();
					if (objs.length > 0){
						News p = new News();
						p.setId(((Integer) objs[0]).intValue());
						p.setDescricao((String) objs[1]);
						p.setResumo((String) objs[2]);
						p.setTitulo((String) objs[3]);
						v.add(p);						
					}
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}finally{
			if (s != null) s.close();
		}
		return v;
	}
}