package com.adapit.portal.services.dao.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.adapit.portal.dto.CategoriaPreferidaDTO;
import com.adapit.portal.entidades.Categoria;
import com.adapit.portal.entidades.Destaque;
import com.adapit.portal.entidades.MessageFeedbackCounter;
import com.adapit.portal.entidades.News;
import com.adapit.portal.entidades.PreferenciaCategoria;
import com.adapit.portal.entidades.Publication;
import com.adapit.portal.entidades.SoftwareSolution;
import com.adapit.portal.entidades.Usuario;
import com.adapit.portal.entidades.MessageFeedbackCounter.FeedbackType;
import com.adapit.portal.services.PreferenciaService;
import com.adapit.portal.services.local.LocalServicesUtility;
import com.adapit.portal.services.validation.ValidationException;
import com.workcase.hibernate.GenericDAO;
import com.workcase.hibernate.GenericDAOHibernate;

/**
 * @spring.bean id="preferenciaServiceDAOHibernate" singleton="true"
 * @@org.springframework.transaction.interceptor.DefaultTransactionAttribute(propagationBehaviorName="PROPAGATION_REQUIRED")
 */
@SuppressWarnings({"unchecked","unused"})

public class PreferenciaServiceDAOHibernate extends GenericDAOHibernate implements
		PreferenciaService, GenericDAO {

	private SessionFactory sessionFactory;

	public void updateOutrasCategoriasByPreferenciaId(Integer id, String value) throws Exception{
		Session s = null;
		if (id == null) return;
		try {						
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
			s.getNamedQuery("preferencia.updatePreferencias")
			.setParameter("value",value)
			.setParameter("id",id.intValue()).executeUpdate();
			s.getTransaction().commit();						
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();
			throw e;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}
	
	public PreferenciaCategoria createPreferencia(boolean isNewRegister, Usuario usuario) throws Exception{
		Session s = null;
		if (usuario == null) throw new ValidationException("The user must not be null!");
		try{
			s = LocalServicesUtility.getInstance().openSession();
			PreferenciaCategoria pref = null;
			if (isNewRegister){
				pref = new PreferenciaCategoria();

				s.beginTransaction();
				s.save(pref);
				Usuario user = (Usuario) s.load(Usuario.class,usuario.getLogin());
				pref.setUsuario(user);
				s.merge(pref);
				s.getTransaction().commit();
				
			}else{
				pref = (PreferenciaCategoria) s.getNamedQuery("preferencia.getByLogin")
				.setParameter("userlogin", usuario.getLogin())
				.uniqueResult();
				if (pref == null){
					pref = new PreferenciaCategoria();		
					
					s.beginTransaction();
					s.save(pref);
					Usuario user = (Usuario) s.load(Usuario.class,usuario.getLogin());
					pref.setUsuario(user);
					s.merge(pref);
					s.getTransaction().commit();
				}
			}
			return pref;
		} catch (Exception e) {
			e.printStackTrace();
			if (s.getTransaction().isActive()) s.getTransaction().rollback();
			throw e;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}
	
	@Override
	public PreferenciaCategoria updatePreferenciaByPropertyName(String propName, int id, boolean value) throws Exception{
		Session s = null;
		try {						
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
			System.out.println(propName + " - " + value + " - " + id);
			String name="";
			if(propName.equals("receberEmailAtualizacoesSoftware"))
				name="preferencia.receberEmailAtualizacoesSoftware";
			else if(propName.equals("interesseEmConsultoria"))
				name="preferencia.interesseEmConsultoria";
			else if(propName.equals("receberEmailNovosProdutos"))
				name="preferencia.receberEmailNovosProdutos";
			else if(propName.equals("interesseEmTreinamentos"))
				name="preferencia.interesseEmTreinamentos";
			else if(propName.equals("receberEmailSobreEventos"))
				name="preferencia.receberEmailSobreEventos";
			else if(propName.equals("receberNotificacaoNewsByEmail"))
				name="preferencia.receberNotificacaoNewsByEmail";
			s.getNamedQuery(name)
			.setParameter("value",value)
			.setParameter("id",id).executeUpdate();
			s.getTransaction().commit();	
			PreferenciaCategoria pref = (PreferenciaCategoria) s.load(PreferenciaCategoria.class, id);
			pref.isReceberEmailSobreEventos();
			return pref;
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();
			throw e;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}
	
	public List<Categoria> listCategoriasPreferediasByIdPessoa(long id) throws Exception{
		Session s = null;
		try{
			
			s = LocalServicesUtility.getInstance().openSession();
			List<Categoria> list = s.getNamedQuery("participante.preferencias")
			.setParameter("id", id)
			.list();
			return list;
			
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}finally{
			if (s != null) s.close();
		}
	}
	
	@Override
	public List<CategoriaPreferidaDTO> listCategoriasPreferediasByPessoa(long id) throws Exception{
		Session s = null;
		try{
			List<Categoria> sel = listCategoriasPreferediasByIdPessoa(id);
			List<CategoriaPreferidaDTO> categorias = new ArrayList<CategoriaPreferidaDTO>();
			s = LocalServicesUtility.getInstance().openSession();
			String rootcat = "select c.id,c.nome from Categoria c where c.pai is null";
			List<Object[]> rootcats = (List<Object[]>) s.createQuery(rootcat).list();
			for(Object[] robjs: rootcats){
				CategoriaPreferidaDTO c = new CategoriaPreferidaDTO();
				c.setCategoriaId((Integer)robjs[0]);
				c.setNomeCategoria((String) robjs[1]);
				categorias.add(c);
				c.setParticipanteId(id);
				for(Categoria cat: sel){
					if (cat.getId() == c.getCategoriaId()){
						c.setSelected(true);
						break;
					}
				}
				listCategoriasPreferediasByPessoa(id, c.getCategoriaId(), sel, c, s);
			}			
			return categorias;
			
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}finally{
			if (s != null) s.close();
		}
	}
	
	
	private void listCategoriasPreferediasByPessoa(long id, int idCatParent, List<Categoria> sel, CategoriaPreferidaDTO parent, Session s) throws Exception{
		try{
			String catstr = "select c.id,c.nome from Categoria c where c.pai.id="+idCatParent;
			List<Object[]> cats = (List<Object[]>) s.createQuery(catstr).list();
			if (cats != null && cats.size()>0){
				List<CategoriaPreferidaDTO> categorias = new ArrayList<CategoriaPreferidaDTO>();
				for(Object[] objs: cats){
					CategoriaPreferidaDTO c = new CategoriaPreferidaDTO();
					c.setCategoriaId((Integer)objs[0]);
					c.setNomeCategoria((String) objs[1]);					
					categorias.add(c);
					c.setParticipanteId(id);
					c.setOrdem(parent.getOrdem()+1);
					for(Categoria cat: sel){
						if (cat.getId() == c.getCategoriaId()){
							c.setSelected(true);
							break;
						}
					}
					listCategoriasPreferediasByPessoa(id, c.getCategoriaId(), sel, c, s);
				}			
				parent.setCategorias(categorias);
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}
	
	@Override
	public Destaque loadDestaque() throws Exception {
		Session s=null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			Destaque d = (Destaque) s.load(Destaque.class,1);
			d.getNews();
			d.getPublication();
			d.getSoftware();
			
			return d;
		} catch (Exception e1) {
			e1.printStackTrace();
			throw e1;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}
	
	@Override
	public Destaque getDestaqueIds() throws Exception {
		Session s=null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			Object[] objs= (Object[]) s.createQuery("select d.news.id, d.publication.id, d.software.id from Destaque d where d.id=1").uniqueResult();
			
			Destaque d = new Destaque();
			News n = new News();
			n.setId((Integer) objs[0]);
			d.setNews(n);
			Publication p = new Publication();
			p.setId((Integer) objs[1]);
			d.setPublication(p);
			SoftwareSolution ss= new SoftwareSolution();
			ss.setId((Integer) objs[2]);
			d.setSoftware(ss);
			
			return d;
		} catch (Exception e1) {
			e1.printStackTrace();
			throw e1;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}
	
	@Override
	public Destaque saveAndMerge(News n, Publication p, SoftwareSolution ss) throws Exception {
		Session s=null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
			Destaque d = null;
			try {
				d=(Destaque) s.load(Destaque.class,1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (d == null){
				d = new Destaque();
				d.setId(1);
				s.save(d);
				s.getTransaction().commit();
				s.beginTransaction();
			}
			if (n != null && n.getId()>0){
				s.refresh(n);
				d.setNews(n);
			}
			
			if (p != null && p.getId()>0){
				s.refresh(p);

				d.setPublication(p);
			}
			if (ss != null && ss.getId()>0){
				s.refresh(ss);

				d.setSoftware(ss);
			}
			
			s.merge(d);
			s.getTransaction().commit();
			return d;
		} catch (Exception e1) {
			e1.printStackTrace();
			s.getTransaction().rollback();
			throw e1;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}
	
	@Override
	public void saveOrUpdate(MessageFeedbackCounter counter) throws Exception{
		Session s=null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
			s.saveOrUpdate(counter);
			s.getTransaction().commit();
			
		} catch (Exception e1) {
			e1.printStackTrace();
			s.getTransaction().rollback();
			throw e1;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}
	
	@Override
	public List<MessageFeedbackCounter> listMessageFeedbackCounterBy(FeedbackType type, Date d1, Date d2, Long part_id, Integer target_id) throws Exception{
		Session s=null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			List<MessageFeedbackCounter> list = null;
			String query="select msg from MessageFeedbackCounter msg ";
			if(type != null || (d1 != null && d2 != null) || part_id != null || (target_id != null && type != null))
				query+=" where ";
			if(type != null)
				query+=" msg.feedbackType=:feedbackType ";
			
			if((d1 != null && d2 != null)){
				if(type != null)
					query+=" and ";
				query+=" msg.dataAcesso between :d1 and :d2 ";
			}
			
			if(part_id != null){
				if(type != null || (d1 != null && d2 != null))
					query+=" and ";
				query+=" msg.participant_id=:part_id";
			}
			
			if(target_id != null && type != null){
				if(type != null || (d1 != null && d2 != null) || part_id != null)
					query+=" and ";
				query+=" msg.idTarget=:target_id";
			}
			
			Query q = s.createQuery(query + " order by msg.dataAcesso DESC");
			if(type != null)
				q.setParameter("feedbackType",type);
			
			if((d1 != null && d2 != null)){
				q.setParameter("d1",d1);
				q.setParameter("d2",d2);
			}
			
			if(part_id != null){
				q.setParameter("part_id",part_id);
			}
			
			if(target_id != null && type != null){
				q.setParameter("target_id",target_id);
			}
			list = q.list();
			return list;
		} catch (Exception e1) {
			e1.printStackTrace();
			throw e1;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}
}