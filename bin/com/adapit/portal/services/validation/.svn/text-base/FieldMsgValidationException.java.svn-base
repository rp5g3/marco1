package com.adapit.portal.services.validation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.WebResourceMessage;
import com.workcase.i18n.I18N;

public class FieldMsgValidationException extends Exception implements Serializable{

	private static final long serialVersionUID = 329921430987134787L;
	private Hashtable<String,String> errorFields;
	
	@SuppressWarnings("unchecked")
	private ArrayList errorMessages;
	
	
	/**
	 * @spring.property ref="messageSource" singleton="true"
	 */
	protected ResourceMessage messages;
	
	public FieldMsgValidationException(BindException errors){
		processErrors(errors);
	}
	
	public FieldMsgValidationException(Hashtable<String,String> errorFields){
		this.errorFields = errorFields;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList getErrorMessages() {
		return errorMessages;
	}

	@SuppressWarnings("unchecked")
	public void setErrorMessages(ArrayList errorMessages) {
		this.errorMessages = errorMessages;
	}

	public Hashtable<String, String> getErrorFields() {
		return errorFields;
	}
	
	public Hashtable<String, String> getErrors() {
		return errorFields;
	}
	
	@SuppressWarnings("unchecked")
	public void processErrors(BindException errors) {
		if (errors.hasErrors()) {
			if(errorMessages == null)
				errorMessages = new ArrayList();
			if(errorFields == null)
				errorFields = new Hashtable<String,String>();
			Iterator it = errors.getAllErrors().iterator();
			while (it.hasNext()) {
				ObjectError or = (ObjectError) it.next();
				//System.out.println(or.getClass().getName());
				if(or instanceof org.springframework.validation.FieldError){
					org.springframework.validation.FieldError errorObj = (org.springframework.validation.FieldError) or;
					try {					
						if(errorObj == null){
							System.out.println("Error object is null");
							continue;
						}else if(messages == null){
							System.out.println("Messages is null");
							messages = WebResourceMessage.getInstance();
						}
						Object[][] values = new Object[][] { { errorObj.getObjectName() + "."
							+ errorObj.getField() } };
						
						errorMessages.add(messages.getMessage(errorObj.getDefaultMessage(),values));
	
						errorFields.put(errorObj.getField(), messages.getMessage(errorObj
								.getDefaultMessage(), values));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					try {					
						if(or == null){
							System.out.println("Error object is null");
							continue;
						}else if(messages == null){
							System.out.println("Messages is null");
							messages = WebResourceMessage.getInstance();
						}
						try {
							ObjectError errorObj = (ObjectError) or;
							if (errorObj.getCodes() != null
									&& errorObj.getCodes().length > 0) {
								for (int i = 0; i < errorObj.getCodes().length; i++) {
									try {
										if (errorObj.getArguments() != null
												&& errorObj.getArguments().length > 0) {
											Object[][] objs = new Object[1][errorObj
													.getArguments().length + 1];
											objs[0][0] = errorObj.getObjectName()
													+ "." + errorObj.getCodes()[i];

											for (int j = 0; j < errorObj
													.getArguments().length; j++) {
												objs[0][j + 1] = errorObj
														.getObjectName()
														+ "."
														+ errorObj.getArguments()[j];
												//System.out.println("first "+objs[0][j+1]);

											}

											for (int j = 0; j < errorObj
													.getArguments().length; j++) {
												String str = messages
														.getMessage(
																errorObj
																		.getDefaultMessage(),
																new Object[][] {
																		{ objs[0][0] },
																		{ objs[0][j + 1] } });
												//System.out.println(str);
												String rest = str.substring(1);
												errorMessages.add(str.charAt(0)
														+ rest.toLowerCase());
												errorFields
														.put(
																errorObj.getCodes()[i],
																str.charAt(0)
																		+ rest
																				.toLowerCase());
											}
										} else {
											if (errorObj.getDefaultMessage() == null)
												continue;
											String str = messages
													.getMessage(
															errorObj
																	.getDefaultMessage(),
															new Object[][] { { errorObj
																	.getObjectName()
																	+ "."
																	+ errorObj
																			.getCodes()[i] } });
											String rest = str.substring(1);
											errorMessages.add(str.charAt(0)
													+ rest.toLowerCase());
											
											errorFields.put(errorObj.getCodes()[i], str
													.charAt(0)
													+ rest.toLowerCase());
											
										}
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							}

						} catch (Exception e) {
							e.printStackTrace();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public static org.springframework.validation.FieldError createFieldError(String objectName, String fieldName, String bundleMessage, Object rejectedValue){
		org.springframework.validation.FieldError oe1 = new org.springframework.validation.FieldError(objectName, fieldName, rejectedValue, true, new String[]{fieldName}, new Object[]{new Integer(6)}, bundleMessage);
		return oe1;
	}
	
	public static org.springframework.validation.FieldError createFieldError(Class entityClass, String fieldName, String bundleMessage, Object rejectedValue){
		org.springframework.validation.FieldError fe = new org.springframework.validation.FieldError(entityClass.getSimpleName(), fieldName, rejectedValue,false, new String[]{fieldName}, new Object[]{new Integer(6)}, bundleMessage);
		return fe;
	}

	
}