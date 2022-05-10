package com.adapit.portal.services.dao.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.adapit.portal.entidades.Arquivo;
import com.adapit.portal.entidades.ComercialSolution;
import com.adapit.portal.entidades.Imagem;
import com.adapit.portal.entidades.Participante;
import com.adapit.portal.entidades.Update;
import com.adapit.portal.entidades.UpdateFile;
import com.adapit.portal.entidades.UpdateFileKind;
import com.adapit.portal.entidades.Usuario;
import com.adapit.portal.services.StringQueryKind;
import com.adapit.portal.services.UpdateService;
import com.adapit.portal.services.local.LocalServicesUtility;
import com.workcase.hibernate.GenericDAO;
import com.workcase.hibernate.GenericDAOHibernate;


/**
 * @spring.bean id="updateServiceDAOHibernate" singleton="true"
 * @@org.springframework.transaction.interceptor.DefaultTransactionAttribute(propagationBehaviorName="PROPAGATION_REQUIRED")
 */

public class UpdateServiceDAOHibernate extends GenericDAOHibernate implements
		UpdateService, GenericDAO {

	@SuppressWarnings("unused")
	private SessionFactory sessionFactory;

	public UpdateServiceDAOHibernate() {

	}

	/**
	 * Retorna todos os updateBeans em que a descrição contenha o valor passado
	 * como argumento
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List listLikeDescricao(String descricao, ComercialSolution c) {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from Update updateBean ");
			hql.append(" where ");
			hql.append(" updateBean.descricao like ? and updateBean.commercialSolution.id = ? order by updateBean.dataPublicacao DESC");
			paramList.add("%" + descricao + "%");
			paramList.add(c.getId());
			return getHibernateTemplate().find(hql.toString(),
					paramList.toArray());

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	/**
	 * Retorna o updateBean em que a descrição inicia pelo valor passado como
	 * argumento
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List listDescricaoBeginingWith(String descricao,ComercialSolution c) {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from Update updateBean ");
			hql.append(" where ");
			hql.append(" updateBean.descricao like ?  and updateBean.commercialSolution.id = ?  order by updateBean.dataPublicacao DESC");
			paramList.add(descricao + "%");
			paramList.add(c.getId());
			return getHibernateTemplate().find(hql.toString(),
					paramList.toArray());

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	/**
	 * Retorna o updateBean em que a descrição termina pelo valor passado como
	 * argumento
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List listDescricaoEndingWith(String descricao, ComercialSolution c) {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from Update updateBean ");
			hql.append(" where ");
			hql.append(" updateBean.descricao like ?  and updateBean.commercialSolution.id = ?" +
					"  order by updateBean.dataPublicacao DESC");
			paramList.add("%" + descricao);
			paramList.add(c.getId());
			return getHibernateTemplate().find(hql.toString(),
					paramList.toArray());

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	/**
	 * Retorna o updateBean em que a descrição é igual ao valor passado como
	 * argumento
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List listByDescricao(String descricao,ComercialSolution c) {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from Update updateBean ");
			hql.append(" where ");
			hql.append(" updateBean.descricao = ?  and updateBean.commercialSolution.id = ?" +
					" order by updateBean.dataPublicacao DESC");
			paramList.add("" + descricao + "");
			paramList.add(c.getId());
			return getHibernateTemplate().find(hql.toString(),
					paramList.toArray());

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	
	@Override
	@SuppressWarnings("unchecked")
	public List listAccordingTo(String descricao, StringQueryKind descKind, ComercialSolution c) {
		try {
			List paramList = new ArrayList();
			
			StringBuffer fields = new StringBuffer();
			StringBuffer hql = new StringBuffer();
			hql.append(" from Update updateBean");
			
			fields.append(" updateBean.id, updateBean.descricao, updateBean.resumo, updateBean.titulo, updateBean.dataPublicacao");
			
						
			if (descricao != null || c != null)
				hql.append(" where ");

			else {
				
				String query = "select " + fields.toString() + hql.toString();
				//System.out.println("EXECUTING QUERY " + query);
				
				Session s = LocalServicesUtility.getInstance().openSession();
				
				ArrayList updateBeans = new ArrayList();
				List<Object[]> list=null;
				try{
					list= s.createQuery(query).list();
					//System.out.println("EXECUTED QUERY " + query);
				
				
					if (list != null) for(Object[] objs: list){
						Update p = new Update();
						p.setId((Integer)objs[0]);
						p.setDescricao((String)objs[1]);
						p.setResumo((String)objs[2]);
						p.setTitulo((String)objs[3]);
						p.setDataPublicacao((Date)objs[4]);
						updateBeans.add(p);
					}
				}catch(Exception ex){
					ex.printStackTrace();
				}finally{
					if (s != null) s.close();
				}
				//System.out.println("retornando a lista de updateBeans");
				return updateBeans;
			}

			
			if (descricao != null) {
				if (descKind == StringQueryKind.LIKE
						|| descKind == StringQueryKind.BEGINS_WITH
						|| descKind == StringQueryKind.ENDS_WITH)
					hql.append(" upper(updateBean.descricao) like upper(?) ");
				else if (descKind == StringQueryKind.EQUALS)
					hql.append(" upper(updateBean.descricao) = upper(?) ");

				if (descKind == StringQueryKind.LIKE)
					paramList.add("%" + descricao + "%");
				else if (descKind == StringQueryKind.EQUALS)
					paramList.add(descricao);
				else if (descKind == StringQueryKind.BEGINS_WITH)
					paramList.add(descricao + "%");
				else if (descKind == StringQueryKind.ENDS_WITH)
					paramList.add("%" + descricao);				
			}
			if(c != null){
				if(descricao != null)
					hql.append(" and ");
				hql.append(" updateBean.commercialSolution.id = ? ");
				paramList.add(c.getId());
			}
			
			String query = "select " + fields.toString() + hql.toString() + "  order by updateBean.dataPublicacao DESC, updateBean.dataCriacao DESC";
			//System.out.println("EXECUTING QUERY " + query);
			
			
			Session s = LocalServicesUtility.getInstance().openSession();
			
			//return list;
			ArrayList updateBeans = new ArrayList();
			List<Object[]> list=null;
			try{
				//list= s.createQuery(query).list();
				list= getHibernateTemplate().find(query.toString(),
						paramList.toArray());
				//System.out.println("EXECUTED QUERY " + query);
			
			
				if (list != null) for(Object[] objs: list){
					Update p = new Update();
					p.setId((Integer)objs[0]);
					p.setDescricao((String)objs[1]);
					p.setResumo((String)objs[2]);
					p.setTitulo((String)objs[3]);
					p.setDataPublicacao((Date)objs[4]);
					updateBeans.add(p);
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}finally{
				if (s != null) s.close();
			}
			//System.out.println("retornando a lista de updateBeans");
			return updateBeans;
			
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}
	

	@Override
	@SuppressWarnings("unchecked")
	public List listAll(ComercialSolution c) {
		try {
			HibernateTemplate template = new HibernateTemplate(
					getSessionFactory());
			return /*initializeCategoria(*/template.loadAll(Update.class)/*)*/;

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	@Override
	public Update saveOrUpdate(Update updateBean) throws Exception {
		Session s = LocalServicesUtility.getInstance().openSession();
		try {
			s.beginTransaction();
			if (updateBean.getId() == 0) s.save(updateBean);
			else s.update(updateBean);
			s.getTransaction().commit();
			return updateBean;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}

	/**
	 * Remove o updateBean que contém o identificador passado como argumento
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Update deleteById(int id) throws Exception {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from Update updateBean ");
			hql.append(" where ");
			hql.append(" updateBean.id = " + id + " ");
			Update updateBean = (Update) DataAccessUtils
					.uniqueResult(getHibernateTemplate().find(hql.toString(),
							paramList.toArray()));

			getHibernateTemplate().delete(updateBean);
			return updateBean;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;//throw new Exception("UpdateServiceDAOHibernate_deleteByIdError");
		}
	}


	/**
	 * Retorna um updateBean pelo identificador
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Update getUpdateById(int id) throws Exception {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("select updateBean from Update updateBean");
			hql.append(" where ");
			hql.append(" updateBean.id = " + id + " ");
			Update up = (Update) DataAccessUtils
					.uniqueResult(getHibernateTemplate().find(hql.toString(),
							paramList.toArray()));
			if(up.getCommercialSolution()!=null)
				up.getCommercialSolution().getId();
			return up;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Imagem> getImagensByUpdateId(int id) throws Exception {
		try {
			//List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			String sql="select nm.image_id from update_image nm where nm.update_id = " + id;
			
			/*hql.append("select updateBean.imagens from Update updateBean ");
			hql.append(" where ");
			hql.append(" updateBean.id = " + id + " ");*/
			
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
			throw ex;/*new Exception(
					"UpdateServiceDAOHibernate_getUpdateByIdError");*/
		}
		return null;
	}

	/**
	 * Retorna um updateBean pela descrição
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Update getUpdateByDescricao(String descricao, ComercialSolution c) throws Exception {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from Update updateBean ");
			hql.append(" where ");
			hql.append(" updateBean.descricao = ?  and updateBean.commercialSolution.id = ?");
			paramList.add("" + descricao + "");
			paramList.add(c.getId());
			return (Update) DataAccessUtils
					.uniqueResult(getHibernateTemplate().find(hql.toString(),
							paramList.toArray()));

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;/*new Exception(
					"UpdateServiceDAOHibernate_getUpdateByDescricaoError");*/
		}
	}
	
	/**
	 * Retorna um updateBean e também as propriedades em cascata
	 */
	@SuppressWarnings("unchecked")
	public Update getUpdateByIdCascadingProperties(java.io.Serializable id, boolean images) throws Exception {
		try {
			org.hibernate.Session s = getSessionFactory().openSession();			
			Update updateBean = null;
			/*updateBean = (Update) s.load(Update.class, id);
			org.hibernate.Hibernate.initialize(updateBean);
			if (images) org.hibernate.Hibernate.initialize(updateBean.getImagens());
			if (categoria) org.hibernate.Hibernate.initialize(updateBean.getCategoria());
			if (itemUpdate) org.hibernate.Hibernate.initialize(updateBean.getItemUpdate());	*/
			StringBuffer fields = new StringBuffer();
			StringBuffer hql = new StringBuffer();
			/*if (images) hql.append(" from Update updateBean join fetch updateBean.imagens im where updateBean.id="+id);
			else*/ hql.append(" from Update updateBean where updateBean.id="+id);
			
			fields.append(" updateBean.id, updateBean.descricao, updateBean.dataPublicacao");
			
			/*if (images) fields.append(", im");*/
			String query = "select " + fields.toString() + hql.toString();
			//System.out.println("EXECUTING QUERY " + query);			
			
			Object[] objs=null;
			try{
				objs= (Object[]) s.createQuery(query).uniqueResult();
				//System.out.println("EXECUTED QUERY " + query);
			
			
				if (objs != null && objs.length > 0){
					Update p = new Update();
					p.setId((Integer)objs[0]);
					p.setDescricao((String)objs[1]);
					p.setDataPublicacao((Date)objs[2]);
					if(images){
						try {
							//System.out.println("buscando imagens");
							String query3="select prod.imagens from Update prod where prod.id="+id;
							List<Imagem> imgs = (List) s.createQuery(query3).list();
							p.setImagens(imgs);
							//System.out.println("Imagens retornadas " + imgs.size());
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
			
			
			return updateBean;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	
	public Update mergeCascadingProperties(Update updateBean, boolean images) throws Exception {
		try {
			org.hibernate.Session s = getSessionFactory().openSession();			
			try {
				s.beginTransaction();
				s.merge(updateBean);
				if (images) s.merge(updateBean.getImagens());
				s.getTransaction().commit();
				s.close();
			} catch (HibernateException e) {
				if (s.getTransaction() != null) s.getTransaction().rollback();				
				throw e;
			}finally{
				s.close();
			}
			return updateBean;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List listLastUpdate(int number, ComercialSolution c) {
		String hql = "select updateBean.id, updateBean.titulo, updateBean.resumo, updateBean.dataPublicacao" +
				" from Update updateBean" +
				" where updateBean.commercialSolution.id = :id" +
				" order by updateBean.dataPublicacao DESC";
		org.hibernate.Session s = LocalServicesUtility.getInstance()
				.openSession();
		ArrayList<Update> list = new ArrayList();
		try {
			List<Object[]> results = s.createQuery(hql)
			.setParameter("id", c.getId())
			.setMaxResults(number).list();
			for(Object[] objs: results){
				int id = (Integer) objs[0];
				String titulo = (String) objs[1];
				String resumo = (String) objs[2];
				Date dt = (Date) objs[3];
				Update n = new Update();
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
	public List listLastUpdate(int number, int ano, ComercialSolution c) {
		String hql = "select updateBean.id, updateBean.titulo, updateBean.resumo, updateBean.dataPublicacao" +
				" from Update updateBean" +
				" where year(updateBean.dataPublicacao) = "+ano+
				" and updateBean.commercialSolution.id = :id" +
				" order by updateBean.dataPublicacao DESC";
		org.hibernate.Session s = LocalServicesUtility.getInstance()
				.openSession();
		ArrayList<Update> list = new ArrayList();
		try {
			List<Object[]> results = s.createQuery(hql)
			.setParameter("id", c.getId())
			.setMaxResults(number).list();
			for(Object[] objs: results){
				int id = (Integer) objs[0];
				String titulo = (String) objs[1];
				String resumo = (String) objs[2];
				Date dt = (Date) objs[3];
				Update n = new Update();
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
	public Imagem removeImagemFromUpdate(int updateBeanId, int imgId) throws Exception{
		Session s = null;
		try {
			Imagem imgRemove=null;
			s=LocalServicesUtility.getInstance().openSession();
			Update p = (Update) s.get(Update.class,updateBeanId);
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
	public Update loadUpdateEagerImagens(Update updateBean) throws Exception{
		Session s = LocalServicesUtility.getInstance().openSession();
		try{
			s.load(updateBean,updateBean.getId());
			updateBean.getImagens().iterator().hasNext();
			return updateBean;
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}finally{
			s.close();
		}
	}
	
	@Override
	public Update anexarImagemUpdate(Update updateBean, List<Imagem> selectedImgs) throws Exception{
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			s.load(updateBean,updateBean.getId());
			
			s.beginTransaction();
			for(Imagem im : selectedImgs){
				updateBean.getImagens().add(im);
			}
			s.flush();
			s.getTransaction().commit();
			return updateBean;
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();
			throw e;
		}finally{
			if (s != null) s.close();
		}
	}
	
	@Override
	public Imagem createUpdateImage(Update updateBean, Imagem img) throws Exception{
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
									
			s.persist(img);
			
			s.load(updateBean,updateBean.getId());
			updateBean.getImagens().add(img);				
			
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
	public Vector listAllUpdate(ComercialSolution c) throws Exception{
		Vector v = new Vector();
		Session s = LocalServicesUtility.getInstance().openSession();
		try{
			String query = "select updateBean.id, updateBean.descricao, updateBean.resumo, updateBean.title, updateBean.dataPublicacao " +
					
					" from Update updateBean" +					
					" where updateBean.commercialSolution.id = :id" +
					" order by updateBean.dataPublicacao, updateBean.id DESC";
			List l =  s.createSQLQuery(query)
			.setParameter("id", c.getId()).list();
			
			
			if (l != null && l.size()>0){
				Iterator it = l.iterator();
				while (it.hasNext()){
					Object objs[] = (Object[]) it.next();
					if (objs.length > 0){
						Update p = new Update();
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
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Vector<UpdateFile> listAllUpdateFiles(Update up, Boolean published) throws Exception{
		Vector<UpdateFile> v = new Vector<UpdateFile>();
		Session s = LocalServicesUtility.getInstance().openSession();
		try{
			String query = "select updateFileBean.id," +
					" updateFileBean.version," +
					" updateFileBean.published," +
					" updateFileBean.updateFileKind," +
					" updateFileBean.obs," +
					" updateFileBean.date,"+
					" updateFileBean.restrict"+
					" from UpdateFile updateFileBean" +					
					" where updateFileBean.updateBean.id = :id";
			if(published != null){
				query+=" and updateFileBean.published=:pub";
			}
			query+=	" order by updateFileBean.date DESC";
			Query q =  s.createQuery(query).setParameter("id", up.getId());
			if(published != null){
				q.setParameter("pub", published);
			}
			List l = q.list();
			
			
			if (l != null && l.size()>0){
				Iterator it = l.iterator();
				while (it.hasNext()){
					Object objs[] = (Object[]) it.next();
					if (objs.length > 0){
						UpdateFile p = new UpdateFile();
						p.setId(((Integer) objs[0]).intValue());
						p.setVersion((String) objs[1]);
						p.setPublished((Boolean) objs[2]);
						p.setUpdateFileKind((UpdateFileKind) objs[3]);
						p.setObs((String) objs[4]);
						p.setDate((Date) objs[5]);
						p.setRestrict((Boolean) objs[6]);
						try {
							Object os[] = (Object[]) s.createQuery("select updateFileBean.currentFile.id," +
									" updateFileBean.currentFile.name," +
									" updateFileBean.currentFile.format," +
									" updateFileBean.updateBean.dataPublicacao " +
									" from UpdateFile updateFileBean where updateFileBean.id="+p.getId() ).uniqueResult();
							
							if(os!=null && os.length>0){
								if(os[0] != null && os[1] != null && os[2] != null){
									Arquivo arq = new Arquivo();
									arq.setId((Integer)os[0]);
									p.setCurrentFile(arq);
									arq.setName((String) os[1]);
									arq.setFormat((String)os[2]);
								}
								if(os[3] != null)
									up.setDataPublicacao((Date) os[3]);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						try {
							Object os[] = (Object[]) s.createQuery(
									"select updateFileBean.installationFile.id," +
									" updateFileBean.installationFile.name," +
									" updateFileBean.installationFile.format " +
									" from UpdateFile updateFileBean where updateFileBean.id="+p.getId() ).uniqueResult();
							
							if(os!=null && os.length>0){
								if(os[0] != null && os[1] != null && os[2] != null){
									Arquivo arq = new Arquivo();
									arq.setId((Integer)os[0]);
									p.setInstallationFile(arq);
									arq.setName((String) os[1]);
									arq.setFormat((String)os[2]);
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						p.setUpdateBean(up);
						
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
	
	@Override
	public UpdateFile saveOrUpdate(UpdateFile ufile, Update updateBean, 
			Arquivo arq, Arquivo inst, UpdateFile oldUpdate) throws Exception {
		Session s = LocalServicesUtility.getInstance().openSession();
		try {
			s.beginTransaction();
			UpdateFile uf = null;
			if(ufile.getId()>0)
				uf=(UpdateFile) s.load(UpdateFile.class, ufile.getId());
			else uf = ufile;
			//if(oldUpdate != null){
				uf.setUpdateBean(updateBean);
				if(arq != null)
					uf.setCurrentFile(arq);
				else uf.setCurrentFile(ufile.getCurrentFile());
				if(inst != null)
					uf.setInstallationFile(inst);
				else uf.setInstallationFile(ufile.getInstallationFile());
				uf.setDate(ufile.getDate());
				//uf.setId(uf.getId());
				uf.setObs(ufile.getObs());
				uf.setPublished(ufile.isPublished());
				uf.setUpdateFileKind(ufile.getUpdateFileKind());
				uf.setVersion(ufile.getVersion());
				
				uf.setRestrict(ufile.isRestrict());
				uf.setWhatChanged(ufile.getWhatChanged());
				uf.setWhoChanged(ufile.getWhoChanged());
				uf.setWhyChanged(ufile.getWhyChanged());
			//}
			if (uf.getId() == 0)
				s.save(uf);
			else{				
				s.update(uf);
			}
			
			if(oldUpdate != null){
				//s.refresh(oldUpdate);
				oldUpdate.setUpdateBean(null);
				s.save(oldUpdate);
				if(uf.getPreviousUpdates() == null)
					uf.setPreviousUpdates(new ArrayList<UpdateFile>());
				uf.getPreviousUpdates().add(oldUpdate);
				s.merge(uf);
			}
			if(uf.getPreviousUpdates() != null){
				for(UpdateFile f: uf.getPreviousUpdates()){
					System.out.println(f.getVersion());
				}
			}
			s.getTransaction().commit();
			return uf;
		} catch (Exception ex) {
			ex.printStackTrace();
			s.getTransaction().rollback();
			throw ex;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}

	@Override
	public void delete(UpdateFile uf) throws Exception {
		Session s = LocalServicesUtility.getInstance().openSession();
		
		try {
			List<UpdateFile> prev = new ArrayList();
			
			/*java.sql.PreparedStatement pstm = s.connection().prepareStatement("select UPDATEFILEVERSIONS.VERSIONEDFILE_ID from UPDATEFILEVERSIONS where UPDATEFILEVERSIONS.VERSIONEDFILE_ID=?");
			pstm.setInt(1, uf.getId());
			pstm.execute();
			*/
			//Apaga referência do pai para ele
			try {
				s.beginTransaction();
				java.sql.PreparedStatement pstm = s.connection().prepareStatement("delete from UPDATEFILEVERSIONS where UPDATEFILEVERSIONS.VERSIONEDFILE_ID=?");
				pstm.setInt(1, uf.getId());
				pstm.execute();
				s.getTransaction().commit();
			}catch(org.hibernate.ObjectNotFoundException e){
				
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			
			s.beginTransaction();
			uf = (UpdateFile) s.load(UpdateFile.class, uf.getId());
			/*if(uf.getCurrentFile() != null){
				Arquivo arq = uf.getCurrentFile();
				uf.setCurrentFile(null);
				s.merge(uf);
				s.delete(arq);				
			}*/
			
			if(uf.getPreviousUpdates() != null){
				uf.getPreviousUpdates().iterator();
				for(UpdateFile ufile: uf.getPreviousUpdates()){
					prev.add(ufile);
				}
				
				uf.setPreviousUpdates(null);
				s.merge(uf);
				
			}
			s.delete(uf);
			s.getTransaction().commit();
			if(prev != null && prev.size()>0){
				for(UpdateFile uf1:prev){
					delete(uf1);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			s.getTransaction().rollback();
			throw ex;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}
	
	@Override
	public void undoVersion(UpdateFile uf, Update file) throws Exception{
		Session s = LocalServicesUtility.getInstance().openSession();
		
		try {
			//Apaga referência do pai para ele
			try {
				s.beginTransaction();
				java.sql.PreparedStatement pstm = s.connection().prepareStatement("delete from UPDATEFILEVERSIONS where UPDATEFILEVERSIONS.VERSIONEDFILE_ID=?");
				pstm.setInt(1, uf.getId());
				pstm.execute();
				s.getTransaction().commit();
			}catch(org.hibernate.ObjectNotFoundException e){
				
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			
			s.beginTransaction();
			uf = (UpdateFile) s.load(UpdateFile.class, uf.getId());
			uf.setUpdateBean(file);
			s.merge(uf);
			s.getTransaction().commit();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			s.getTransaction().rollback();
			throw ex;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}

	@Override
	public UpdateFile load(UpdateFile uf) throws Exception {
		Session s = LocalServicesUtility.getInstance().openSession();
		try {
			uf = (UpdateFile) s.load(UpdateFile.class, uf.getId());
			uf.getDate();
			uf.getObs();
			uf.isPublished();
			uf.isRestrict();
			if(uf.getCurrentFile() != null){
				uf.getCurrentFile().getName();
			}
			if(uf.getInstallationFile() != null)
				uf.getInstallationFile().getId();
			if(uf.getPreviousUpdates() != null && uf.getPreviousUpdates().size()>0){
				uf.getPreviousUpdates().iterator();
			}
			return uf;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}
	
	@Override
	public List<Usuario> listAutorizedUsers(UpdateFile uf) throws Exception {
		Session s = LocalServicesUtility.getInstance().openSession();
		ArrayList<Usuario> arr =new ArrayList<Usuario>();
		try {
			uf = (UpdateFile) s.load(UpdateFile.class, uf.getId());
			if(uf.getAutorizedUsers() != null && uf.getAutorizedUsers().size()>0){
				for(Participante p: uf.getAutorizedUsers()){
					if(p.getUser() != null){
						p.getUser().getLogin();
						p.getUser().setDadosPessoais(p);
						arr.add(p.getUser());
					}
					if(p.getTipoPessoa() != null)
						p.getTipoPessoa().getId();
					if(p.getEndereco() != null)
						p.getEndereco().getId();
				}
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
		return arr;
	}
	
	@Override
	public void autorizeUsers(List<Participante> list, boolean b, UpdateFile uf) throws Exception {
		Session s = LocalServicesUtility.getInstance().openSession();
		try {
			uf = (UpdateFile) s.load(UpdateFile.class, uf.getId());
			s.beginTransaction();
			if(uf.getAutorizedUsers() == null)
				uf.setAutorizedUsers(new ArrayList<Participante>());
			if(list != null && list.size()>0){
				for(Participante p: list){
					if(b)
						uf.getAutorizedUsers().add(p);
					else{
						java.sql.PreparedStatement pstm = 
							s.connection().prepareStatement("delete from updatefileautorities where" +
									" updatefileautorities.UPDATEFILE_ID=? and " +
									" updatefileautorities.PARTICIPANT_ID=?");
						pstm.setInt(1, uf.getId());
						pstm.setLong(2, p.getId());
						pstm.execute();
						//uf.getAutorizedUsers().remove(p);	
					}
						
				}
			}
			s.merge(uf);
			s.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			s.getTransaction().rollback();
			throw ex;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}
	

	@Override
	public List<UpdateFile> listAllUpdateFiles(UpdateFile file, Boolean published) throws Exception{
		Vector<UpdateFile> v = new Vector<UpdateFile>();
		Session s = LocalServicesUtility.getInstance().openSession();
		try{
			//System.out.println("Searching from file " + file.getId());
			String query = "select updateFileBean.previousUpdates from UpdateFile updateFileBean where updateFileBean.id = :id";
			Query q =  s.createQuery(query).setParameter("id", file.getId());
			
			List l = q.list();
			//s.refresh(file);
			//List<UpdateFile> l = file.getPreviousUpdates();
			
			if (l != null && l.size()>0){
				Iterator it = l.iterator();
				while (it.hasNext()){
					UpdateFile up= (UpdateFile) it.next();
					if(published != null){
						if(published && up.isPublished())
							v.add(up);
						else if(!published && !up.isPublished())
							v.add(up);
					}else v.add(up);
					up.getDate();
					if(up.getCurrentFile() != null)
						up.getCurrentFile().getId();
					
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
	
	@Override
	public Update newInstanceOf(Update update) throws Exception{		
		Session s = LocalServicesUtility.getInstance().openSession();
		
		try {
			s.beginTransaction();
			Update up = new Update();
			update = (Update) s.load(Update.class, update.getId());
			up.setCommercialSolution(update.getCommercialSolution());
			up.setDataCriacao(new Date());
			up.setDataPublicacao(new Date());
			up.setDescricao(update.getDescricao());
			up.setPublicar(update.isPublicar());
			up.setResumo(update.getResumo());
			up.setTitulo(update.getTitulo());
			up.setUnpublished("[Atualização anterior ID=" + update.getId() + " DATA PUBLICAÇÃO=" + update.getDataPublicacao()+"]"+'\n'+
					"O que mudou?"+'\n'+"Por que mudou?"+'\n'+"Quem fez a modificação?"
					+'\n'+update.getUnpublished());
			s.save(up);
						
			
			s.getTransaction().commit();
			
			if(update.getImagens() != null && update.getImagens().size()>0){
				anexarImagemUpdate(up, update.getImagens());
			}
			
			Vector<UpdateFile> updates = listAllUpdateFiles(update, null);
			if(updates != null && updates.size()>0){
				s.beginTransaction();
				for(UpdateFile u: updates){
					
					UpdateFile uf = u.newVersion();
					uf.setUpdateBean(up);
					s.save(uf);					
				}
				s.getTransaction().commit();
			}
			
			return up;
		} catch (Exception ex) {
			ex.printStackTrace();
			s.getTransaction().rollback();
			throw ex;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}

	}
	
	@Override
	public int countUpdates(ComercialSolution cs) throws Exception {
		String hql = "select count(updateBean.id) " +
		" from Update updateBean" +
		" where updateBean.commercialSolution.id = :id";
		org.hibernate.Session s = LocalServicesUtility.getInstance()
				.openSession();
		try {
			return ((Integer) s.createQuery(hql).setParameter("id", cs.getId()).uniqueResult()).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			s.close();
		
		}
	}
}