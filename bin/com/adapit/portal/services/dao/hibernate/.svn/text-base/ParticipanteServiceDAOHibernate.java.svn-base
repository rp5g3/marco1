package com.adapit.portal.services.dao.hibernate;


import org.hibernate.NonUniqueObjectException;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;

import com.adapit.portal.entidades.Endereco;
import com.adapit.portal.entidades.Participante;
import com.adapit.portal.entidades.PreferenciaCategoria;
import com.adapit.portal.entidades.TipoPessoa;
import com.adapit.portal.entidades.Usuario;
import com.adapit.portal.services.ParticipanteService;
import com.adapit.portal.services.validation.FieldMsgValidationException;
import com.adapit.portal.services.validation.ValidationException;
import com.workcase.hibernate.GenericDAO;
import com.workcase.hibernate.GenericDAOHibernate;


/**
* @spring.bean id="participanteServiceDAOHibernate" singleton="true"
* @@org.springframework.transaction.interceptor.DefaultTransactionAttribute(propagationBehaviorName="PROPAGATION_REQUIRED")
*/

public class ParticipanteServiceDAOHibernate extends GenericDAOHibernate implements ParticipanteService, GenericDAO{
	
	private SessionFactory sessionFactory;


	
	public ParticipanteServiceDAOHibernate(){

	}
	
	public Participante saveAndMerge(Participante participante ,Usuario usuario ,TipoPessoa tipoPessoa ,PreferenciaCategoria preferenciaCategoria ,Endereco endereco ) throws FieldMsgValidationException, ValidationException, NonUniqueObjectException, ConstraintViolationException, DataException, Exception{
		org.hibernate.Session session = getHibernateTemplate().getSessionFactory().openSession();
		try{
			session.beginTransaction();
			boolean newUser= participante.getId()<=0;
			
			session.saveOrUpdate(participante);
			if(newUser){
				usuario.setPassword(Usuario.encript(usuario.getPassword()));
				session.saveOrUpdate(usuario);
			}else{
				usuario = (Usuario) session.load(Usuario.class, usuario.getLogin());
				//usuario.setPassword((String)session.createQuery("select u.password from Usuario u where u.login='"+usuario.getLogin()+"'").uniqueResult());
			}
			
			if(tipoPessoa.getId()<=0)
				session.saveOrUpdate(tipoPessoa);
			else {
				session.update(tipoPessoa);
			}
			if(preferenciaCategoria.getId()<=0)
				session.saveOrUpdate(preferenciaCategoria);
			else session.update(preferenciaCategoria);
			if(endereco.getId()<=0)
				session.saveOrUpdate(endereco);
			else session.update(endereco);
			if(usuario != null)
				participante.setUser(usuario);
			participante.setTipoPessoa(tipoPessoa);
			if(usuario != null){
				usuario.setPreferencia(preferenciaCategoria);
				preferenciaCategoria.setUsuario(usuario);
				
				usuario.setDadosPessoais(participante);
			}
			tipoPessoa.setPessoa(participante);
			participante.setEndereco(endereco);
			if(usuario != null)
				session.merge(usuario);
			session.merge(tipoPessoa);
			session.merge(preferenciaCategoria);
			session.merge(endereco);
			session.merge(participante);
			session.getTransaction().commit();
			return participante;
		}catch(Exception ex){
			ex.printStackTrace();
			session.getTransaction().rollback();
			throw ex;
		}
		finally{
			if(session != null && session.isOpen()) session.close();
		}

	}


	@Override
	public Participante load(Participante participante) throws Exception {
		org.hibernate.Session session = getHibernateTemplate().getSessionFactory().openSession();
		try{
			participante = (Participante) session.load(Participante.class,participante.getId());
			if(participante.getUser() != null){
				if(participante.getUser().getPreferencia() != null)
					 participante.getUser().getPreferencia().getId();
			}
			if(participante.getEndereco() != null)
				participante.getEndereco().getId();
			if(participante.getTipoPessoa() != null)
				participante.getTipoPessoa().getId();
			return participante;
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
		finally{
			if(session != null && session.isOpen()) session.close();
		}
	}
}