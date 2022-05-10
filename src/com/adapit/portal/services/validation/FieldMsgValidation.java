package com.adapit.portal.services.validation;

import java.io.Serializable;
import java.util.Hashtable;

public class FieldMsgValidation implements Serializable{

	private static final long serialVersionUID = 2346234723790932452L;
	private Hashtable<String,String> errors;
	
	public FieldMsgValidation(Hashtable<String,String> errors){
		this.errors = errors;
	}

	public Hashtable<String, String> getErrors() {
		return errors;
	}
}
