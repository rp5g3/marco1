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
import com.adapit.portal.entidades.Publication;
import com.adapit.portal.services.PublicationService;
import com.adapit.portal.services.StringQueryKind;
import com.adapit.portal.services.local.LocalServicesUtility;
import com.workcase.hibernate.GenericDAO;
import com.workcase.hibernate.GenericDAOHibernate;



/**
 * @spring.bean id="publicationServiceDAOHibernate" singleton="true"
 * @@org.springframework.transaction.interceptor.DefaultTransactionAttribute(propagationBehaviorName="PROPAGATION_REQUIRED")
 */

public class PublicationServiceDAOHibernate extends GenericDAOHibernate implements
		PublicationService, GenericDAO {

	@SuppressWarnings("unused")
	private SessionFactory sessionFactory;

	public PublicationServiceDAOHibernate() {

	}

	/**
	 * Retorna todos os publications em que a descrição contenha o valor passado
	 * como argumento
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List listLikeDescricao(String descricao) {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from Publication publication ");
			hql.append(" where ");
			hql.append(" publication.descricao like ? ");
			paramList.add("%" + descricao + "%");
			return getHibernateTemplate().find(hql.toString(),
					paramList.toArray());

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	/**
	 * Retorna o publication em que a descrição inicia pelo valor passado como
	 * argumento
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List listDescricaoBeginingWith(String descricao) {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from Publication publication ");
			hql.append(" where ");
			hql.append(" publication.descricao like ? ");
			paramList.add(descricao + "%");
			return getHibernateTemplate().find(hql.toString(),
					paramList.toArray());

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	/**
	 * Retorna o publication em que a descrição termina pelo valor passado como
	 * argumento
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List listDescricaoEndingWith(String descricao) {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from Publication publication ");
			hql.append(" where ");
			hql.append(" publication.descricao like ? ");
			paramList.add("%" + descricao);
			return getHibernateTemplate().find(hql.toString(),
					paramList.toArray());

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	/**
	 * Retorna o publication em que a descrição é igual ao valor passado como
	 * argumento
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List listByDescricao(String descricao) {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from Publication publication ");
			hql.append(" where ");
			hql.append(" publication.descricao = ? ");
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
			hql.append(" from Publication publication");
			
			fields.append(" publication.id, publication.descricao, publication.resumo, publication.titulo");
			
						
			if (descricao != null )
				hql.append(" where ");

			else {
				
				String query = "select " + fields.toString() + hql.toString();
				System.out.println("EXECUTING QUERY " + query);
				
				Session s = LocalServicesUtility.getInstance().openSession();
				
				ArrayList publications = new ArrayList();
				List<Object[]> list=null;
				try{
					list= s.createQuery(query).list();
					System.out.println("EXECUTED QUERY " + query);
				
				
					if (list != null) for(Object[] objs: list){
						Publication p = new Publication();
						p.setId((Integer)objs[0]);
						p.setDescricao((String)objs[1]);
						p.setResumo((String)objs[2]);
						p.setTitulo((String)objs[3]);
						publications.add(p);
					}
				}catch(Exception ex){
					ex.printStackTrace();
				}finally{
					if (s != null) s.close();
				}
				System.out.println("retornando a lista de publications");
				return publications;
			}

			
			if (descricao != null) {
				if (descKind == StringQueryKind.LIKE
						|| descKind == StringQueryKind.BEGINS_WITH
						|| descKind == StringQueryKind.ENDS_WITH)
					hql.append(" upper(publication.descricao) like upper(?) ");
				else if (descKind == StringQueryKind.EQUALS)
					hql.append(" upper(publication.descricao) = upper(?) ");

				if (descKind == StringQueryKind.LIKE)
					paramList.add("%" + descricao + "%");
				else if (descKind == StringQueryKind.EQUALS)
					paramList.add(descricao);
				else if (descKind == StringQueryKind.BEGINS_WITH)
					paramList.add(descricao + "%");
				else if (descKind == StringQueryKind.ENDS_WITH)
					paramList.add("%" + descricao);				
			}
			
			
			String query = "select " + fields.toString() + hql.toString();
			System.out.println("EXECUTING QUERY " + query);
			
			
			Session s = LocalServicesUtility.getInstance().openSession();
			
			//return list;
			ArrayList publications = new ArrayList();
			List<Object[]> list=null;
			try{
				//list= s.createQuery(query).list();
				list= getHibernateTemplate().find(query.toString(),
						paramList.toArray());
				System.out.println("EXECUTED QUERY " + query);
			
			
				if (list != null) for(Object[] objs: list){
					Publication p = new Publication();
					p.setId((Integer)objs[0]);
					p.setDescricao((String)objs[1]);
					p.setResumo((String)objs[2]);
					p.setTitulo((String)objs[3]);
					publications.add(p);
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}finally{
				if (s != null) s.close();
			}
			System.out.println("retornando a lista de publications");
			return publications;
			
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
			return /*initializeCategoria(*/template.loadAll(Publication.class)/*)*/;

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	@Override
	public Publication saveOrUpdate(Publication publication) throws Exception {
		Session s = LocalServicesUtility.getInstance().openSession();
		try {
			s.beginTransaction();
			if (publication.getId() == 0) s.save(publication);
			else s.update(publication);
			s.getTransaction().commit();
			return publication;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}

	/**
	 * Remove o publication que contém o identificador passado como argumento
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Publication deleteById(int id) throws Exception {
/*		Session s = LocalServicesUtility.getInstance().openSession();
		try{
			s.beginTransaction();
													
			s.createQuery("delete from Publication p where p.id="+id).executeUpdate();
			
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
			hql.append("from Publication publication ");
			hql.append(" where ");
			hql.append(" publication.id = " + id + " ");
			Publication publication = (Publication) DataAccessUtils
					.uniqueResult(getHibernateTemplate().find(hql.toString(),
							paramList.toArray()));

			getHibernateTemplate().delete(publication);
			return publication;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("PublicationServiceDAOHibernate_deleteByIdError");
		}
	}


	/**
	 * Retorna um publication pelo identificador
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Publication getPublicationById(int id) throws Exception {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("select publication from Publication publication");
			hql.append(" where ");
			hql.append(" publication.id = " + id + " ");
			return (Publication) DataAccessUtils
					.uniqueResult(getHibernateTemplate().find(hql.toString(),
							paramList.toArray()));

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(
					"PublicationServiceDAOHibernate_getPublicationByIdError");
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Imagem> getImagensByPublicationId(int id) throws Exception {
		try {
			//List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			String sql="select nm.image_id from paper_image nm where nm.paper_id = " + id;
			
			/*hql.append("select publication.imagens from Publication publication ");
			hql.append(" where ");
			hql.append(" publication.id = " + id + " ");*/
			
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
					"PublicationServiceDAOHibernate_getPublicationByIdError");
		}
		return null;
	}

	/**
	 * Retorna um publication pela descrição
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Publication getPublicationByDescricao(String descricao) throws Exception {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from Publication publication ");
			hql.append(" where ");
			hql.append(" publication.descricao = ? ");
			paramList.add("" + descricao + "");
			return (Publication) DataAccessUtils
					.uniqueResult(getHibernateTemplate().find(hql.toString(),
							paramList.toArray()));

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(
					"PublicationServiceDAOHibernate_getPublicationByDescricaoError");
		}
	}
	
	/**
	 * Retorna um publication e também as propriedades em cascata
	 */
	@SuppressWarnings("unchecked")
	public Publication getPublicationByIdCascadingProperties(java.io.Serializable id, boolean images) throws Exception {
		try {
			org.hibernate.Session s = getSessionFactory().openSession();			
			Publication publication = null;
			/*publication = (Publication) s.load(Publication.class, id);
			org.hibernate.Hibernate.initialize(publication);
			if (images) org.hibernate.Hibernate.initialize(publication.getImagens());
			if (categoria) org.hibernate.Hibernate.initialize(publication.getCategoria());
			if (itemPublication) org.hibernate.Hibernate.initialize(publication.getItemPublication());	*/
			StringBuffer fields = new StringBuffer();
			StringBuffer hql = new StringBuffer();
			/*if (images) hql.append(" from Publication publication join fetch publication.imagens im where publication.id="+id);
			else*/ hql.append(" from Publication publication where publication.id="+id);
			
			fields.append(" publication.id, publication.descricao");
			
			/*if (images) fields.append(", im");*/
			String query = "select " + fields.toString() + hql.toString();
			System.out.println("EXECUTING QUERY " + query);			
			
			Object[] objs=null;
			try{
				objs= (Object[]) s.createQuery(query).uniqueResult();
				System.out.println("EXECUTED QUERY " + query);
			
			
				if (objs != null && objs.length > 0){
					Publication p = new Publication();
					p.setId((Integer)objs[0]);
					p.setDescricao((String)objs[1]);
					
					if(images){
						try {
							System.out.println("buscando imagens");
							String query3="select prod.imagens from Publication prod where prod.id="+id;
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
			
			
			return publication;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("Publication Cascading Load Exception");
		}
	}


	public Publication mergeCascadingProperties(Publication publication, boolean images) throws Exception {
		try {
			org.hibernate.Session s = getSessionFactory().openSession();			
			try {
				s.beginTransaction();
				s.merge(publication);
				if (images) s.merge(publication.getImagens());
				s.getTransaction().commit();
				s.close();
			} catch (HibernateException e) {
				if (s.getTransaction() != null) s.getTransaction().rollback();				
				throw e;
			}finally{
				s.close();
			}
			return publication;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("Publication Cascading Merge Exception");
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List listLastPublications(int number) {
		String hql = "select publication.id, publication.titulo, publication.resumo, publication.dataPublicacao from Publication publication order by publication.dataPublicacao DESC";
		org.hibernate.Session s = LocalServicesUtility.getInstance()
				.openSession();
		ArrayList<Publication> list = new ArrayList();
		try {
			List<Object[]> results = s.createQuery(hql).setMaxResults(number).list();
			for(Object[] objs: results){
				int id = (Integer) objs[0];
				String titulo = (String) objs[1];
				String resumo = (String) objs[2];
				Date dt = (Date) objs[3];
				Publication n = new Publication();
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
	public List listLastPublications(int number, int ano) {
		String hql = "select publication.id, publication.titulo, publication.resumo, publication.dataPublicacao from Publication publication where year(publication.dataPublicacao) = "+ano+" order by publication.dataPublicacao DESC";
		org.hibernate.Session s = LocalServicesUtility.getInstance()
				.openSession();
		ArrayList<Publication> list = new ArrayList();
		try {
			List<Object[]> results = s.createQuery(hql).setMaxResults(number).list();
			for(Object[] objs: results){
				int id = (Integer) objs[0];
				String titulo = (String) objs[1];
				String resumo = (String) objs[2];
				Date dt = (Date) objs[3];
				Publication n = new Publication();
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
	public Imagem removeImagemFromPublication(int publicationId, int imgId) throws Exception{
		Session s = null;
		try {
			Imagem imgRemove=null;
			s=LocalServicesUtility.getInstance().openSession();
			Publication p = (Publication) s.get(Publication.class,publicationId);
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
	public Publication loadPublicationEagerImagens(Publication publication) throws Exception{
		Session s = LocalServicesUtility.getInstance().openSession();
		try{
			s.load(publication,publication.getId());
			publication.getImagens().iterator().hasNext();
			return publication;
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}finally{
			s.close();
		}
	}
	
	@Override
	public Publication anexarImagemPublication(Publication publication, List<Imagem> selectedImgs) throws Exception{
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			s.load(publication,publication.getId());
			
			s.beginTransaction();
			for(Imagem im : selectedImgs){
				publication.getImagens().add(im);
			}
			s.flush();
			s.getTransaction().commit();
			return publication;
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();
			throw e;
		}finally{
			if (s != null) s.close();
		}
	}
	
	@Override
	public Imagem createPublicationImage(Publication publication, Imagem img) throws Exception{
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
									
			s.persist(img);
			
			s.load(publication,publication.getId());
			publication.getImagens().add(img);				
			
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
	public Vector listAllPublication() throws Exception{
		Vector v = new Vector();
		Session s = LocalServicesUtility.getInstance().openSession();
		try{
			String query = "select publication.id, publication.descricao, publication.resumo, publication.title " +
					
					" from Publication publication" +					
					"    order by publication.resumo";
			List l =  s.createSQLQuery(query).list();
			
			
			if (l != null && l.size()>0){
				Iterator it = l.iterator();
				while (it.hasNext()){
					Object objs[] = (Object[]) it.next();
					if (objs.length > 0){
						Publication p = new Publication();
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