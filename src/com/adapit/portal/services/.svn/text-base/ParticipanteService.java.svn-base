package com.adapit.portal.services;


import org.hibernate.NonUniqueObjectException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;

import com.adapit.portal.entidades.Endereco;
import com.adapit.portal.entidades.Participante;
import com.adapit.portal.entidades.PreferenciaCategoria;
import com.adapit.portal.entidades.TipoPessoa;
import com.adapit.portal.entidades.Usuario;
import com.adapit.portal.services.validation.FieldMsgValidationException;
import com.adapit.portal.services.validation.ValidationException;


public interface ParticipanteService{


	
	public Participante saveAndMerge(Participante participante ,Usuario usuario ,TipoPessoa tipoPessoa ,PreferenciaCategoria preferenciaCategoria ,Endereco endereco ) throws FieldMsgValidationException, ValidationException, NonUniqueObjectException, ConstraintViolationException, DataException, Exception;

	public Participante load(Participante participante) throws Exception;



}