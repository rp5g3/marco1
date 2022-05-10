package com.adapit.portal.services.controllers.usuario;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.workcase.gui.utils.ResourceMessage;

public abstract class FormMultiActionController extends MultiActionController {

	protected ResourceMessage messages;

	protected DefaultBeanValidator validator;

	public void setValidator(DefaultBeanValidator validator) {
		this.validator = validator;
	}

	public DefaultBeanValidator getValidator() {
		return this.validator;
	}

	protected abstract BindException validate(HttpServletRequest request) throws Exception;

	@SuppressWarnings("unchecked")
	protected void errorsValidate(BindException errors, ArrayList arr,
			Hashtable ht) {
		System.out.println("errorsValidate");
		if (errors.hasErrors()) {
			Iterator it = errors.getAllErrors().iterator();
			while (it.hasNext()) {
				org.springframework.validation.FieldError errorObj = (org.springframework.validation.FieldError) it
						.next();
				arr.add(messages.getMessage(errorObj.getDefaultMessage(),
						new Object[][] { { errorObj.getObjectName() + "."
								+ errorObj.getField() } }));

				ht.put(errorObj.getField(), messages.getMessage(errorObj
						.getDefaultMessage(), new Object[][] { { errorObj
						.getObjectName()
						+ "." + errorObj.getField() } }));
			}
		}
	}

	@SuppressWarnings("unchecked")
	protected void populateErrorList(HttpServletRequest request, Errors detectedErrors, Hashtable ht, ArrayList<String> arr)
			throws Exception {
		System.out.println("populateErroList");
		if (detectedErrors != null && detectedErrors.getAllErrors() != null){
			Iterator it = detectedErrors.getAllErrors().iterator();
			while (it.hasNext()) {
				Object obj = it.next();
				if (obj instanceof org.springframework.validation.FieldError) {
					org.springframework.validation.FieldError errorObj = (org.springframework.validation.FieldError) obj;
					arr.add(messages.getMessage(errorObj.getDefaultMessage(),
							new Object[][] { { errorObj.getObjectName() + "."
									+ errorObj.getField() } }));
					ht.put(errorObj.getField(), messages.getMessage(errorObj
							.getDefaultMessage(), new Object[][] { { errorObj
							.getObjectName()
							+ "." + errorObj.getField() } }));
				} else if (obj instanceof ObjectError) {
					try {
						ObjectError errorObj = (ObjectError) obj;
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
											String rest = str.substring(1);
											arr.add(str.charAt(0)
													+ rest.toLowerCase());
											ht
													.put(
															errorObj.getCodes()[i],
															str.charAt(0)
																	+ rest
																			.toLowerCase()/*
																							 * messages.getMessage(errorObj.getDefaultMessage(),new
																							 * Object[][]{{objs[0][0]},{objs[0][j+1]}})
																							 */);
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
										arr.add(str.charAt(0)
												+ rest.toLowerCase());
										/*
										 * arr.add( messages.getMessage(
										 * errorObj.getDefaultMessage(), new
										 * Object[][]{{errorObj.getObjectName()+"."+errorObj.getCodes()[i]}}));
										 */
										ht.put(errorObj.getCodes()[i], str
												.charAt(0)
												+ rest.toLowerCase());
										/*
										 * ht.put(errorObj.getCodes()[i],messages.getMessage(
										 * errorObj.getDefaultMessage(), new
										 * Object[][]{{errorObj.getObjectName()+"."+errorObj.getCodes()[i]}}));
										 */
									}
								} catch (RuntimeException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}

					} catch (RuntimeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
}
