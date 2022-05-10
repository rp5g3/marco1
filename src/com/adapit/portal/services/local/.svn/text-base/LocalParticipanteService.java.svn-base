package com.adapit.portal.services.local;


import org.hibernate.NonUniqueObjectException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.xml.XmlBeanFactory;

import com.adapit.portal.entidades.Endereco;
import com.adapit.portal.entidades.Participante;
import com.adapit.portal.entidades.PreferenciaCategoria;
import com.adapit.portal.entidades.TipoPessoa;
import com.adapit.portal.entidades.Usuario;
import com.adapit.portal.services.ParticipanteService;
import com.adapit.portal.services.validation.FieldMsgValidationException;
import com.adapit.portal.services.validation.ValidationException;


public class LocalParticipanteService implements ParticipanteService{
	
	private ParticipanteService participanteService;
	
	private static LocalParticipanteService instance;


	
	private LocalParticipanteService(){
		try{
			XmlBeanFactory beanFactory = new XmlBeanFactory(new org.springframework.core.io.ClassPathResource("localServices.xml"));
			participanteService = (ParticipanteService) beanFactory.getBean("localParticipanteServiceDAOHibernate");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public static LocalParticipanteService getInstance(){
		if (instance == null){
			instance = new LocalParticipanteService();
		}
		return instance;
	}
	
	public Participante saveAndMerge(Participante participante ,Usuario usuario ,TipoPessoa tipoPessoa ,PreferenciaCategoria preferenciaCategoria ,Endereco endereco ) throws FieldMsgValidationException, ValidationException, NonUniqueObjectException, ConstraintViolationException, DataException, Exception{
		try{
			return this.participanteService.saveAndMerge(participante, usuario, tipoPessoa, preferenciaCategoria, endereco);
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}

	@Override
	public Participante load(Participante participante) throws Exception {
		return participanteService.load(participante);
	}

}