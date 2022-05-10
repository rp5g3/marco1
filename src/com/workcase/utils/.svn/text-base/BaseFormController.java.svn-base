package com.workcase.utils;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.workcase.gui.utils.ResourceMessage;


public abstract class BaseFormController extends SimpleFormController {
	
	protected Hashtable<String,Object> commandObjects = new Hashtable<String,Object>();
	
	protected Vector<String> commandNames = new Vector<String>(); 
	
	protected ResourceMessage messages;

	@SuppressWarnings({ "unchecked" })
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException binder)
			throws Exception {
		System.out.println("onSubmit");
		try {				
			/*Iterator it = commandNames.iterator();
			while (it.hasNext()){
				String key = (String) it.next();
				commandObjects.put(key,request.getAttribute(key));
			}*/
			
			ModelAndView mv = super.onSubmit(request, response, command, binder);
			//bindAndValidate(request,command);
			validate(request,command,binder);
			return mv;
		} catch (Throwable t) {
			try {
				binder.reject(t.getMessage(), t.getMessage());
				
				Map m = referenceData(request, command, binder);
				m.putAll(binder.getModel());
				
				//ModelAndView mv= new ModelAndView(getFormView()/*, m*/);
				/*mv.addObject(m);
				if (commandObjects.size()>0){
					Iterator it = commandObjects.keySet().iterator();
					while (it.hasNext()){
						String key = (String) it.next();
						mv.addObject(key, commandObjects.get(key));
					}
				}*/
				
				return new ModelAndView(getFormView(), m);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {
		super.onBindAndValidate(request, command, errors);
		System.out.println("onBindAndValidate");
		//validate(request,errors);
	}
	
	protected abstract void validate(HttpServletRequest request,
			Object command, BindException errors) throws Exception ;
	
//	/protected abstract void validate(HttpServletRequest request, BindException errors) throws Exception;
	
	@SuppressWarnings("unchecked")
	protected void errorsValidate(BindException errors, ArrayList arr, Hashtable ht){
		System.out.println("errorsValidate");
		if (errors.hasErrors()) {
			Iterator it = errors.getAllErrors().iterator();
			while (it.hasNext()){
				org.springframework.validation.FieldError errorObj = (org.springframework.validation.FieldError) it.next();
				arr.add(
						messages.getMessage(
								errorObj.getDefaultMessage(),
								new Object[][]{{errorObj.getObjectName()+"."+errorObj.getField()}}));
				
				ht.put(errorObj.getField(),messages.getMessage(
						errorObj.getDefaultMessage(),
						new Object[][]{{errorObj.getObjectName()+"."+errorObj.getField()}}));
			}
		}
	}
	
	
	@SuppressWarnings("unchecked")
	protected void populateErrorList(HttpServletRequest request, Object arg1, Errors detectedErrors, Hashtable ht, ArrayList<String> arr) throws Exception {
		System.out.println("populateErroList");
		{
			Iterator it = detectedErrors.getAllErrors().iterator();
			while (it.hasNext()){
				Object obj = it.next();
				if (obj instanceof org.springframework.validation.FieldError ){
					org.springframework.validation.FieldError errorObj = (org.springframework.validation.FieldError) obj;
					arr.add(
							messages.getMessage(
									errorObj.getDefaultMessage(),
									new Object[][]{{errorObj.getObjectName()+"."+errorObj.getField()}}));
					ht.put(errorObj.getField(),messages.getMessage(
							errorObj.getDefaultMessage(),
							new Object[][]{{errorObj.getObjectName()+"."+errorObj.getField()}}));
				}else if (obj instanceof ObjectError){
					try {
						ObjectError errorObj = (ObjectError) obj;
						if (errorObj.getCodes() != null && errorObj.getCodes().length > 0){
							for (int i=0; i < errorObj.getCodes().length; i++){
								try {
									if (errorObj.getArguments() != null && errorObj.getArguments().length > 0){
										Object[][] objs = new Object[1][errorObj.getArguments().length +1];
										objs[0][0] = errorObj.getObjectName()+"."+errorObj.getCodes()[i];
										
										for (int j=0; j < errorObj.getArguments().length; j++){
											objs[0][j+1] = errorObj.getObjectName()+"."+ errorObj.getArguments()[j];
											
										}
										
										for (int j=0; j < errorObj.getArguments().length; j++){
											String str = messages.getMessage(errorObj.getDefaultMessage(),new Object[][]{{objs[0][0]},{objs[0][j+1]}});
											String rest=str.substring(1);											
											arr.add(str.charAt(0)+rest.toLowerCase());
											ht.put(errorObj.getCodes()[i],str.charAt(0)+rest.toLowerCase()/*messages.getMessage(errorObj.getDefaultMessage(),new Object[][]{{objs[0][0]},{objs[0][j+1]}})*/);
										}										
									}
									else{
										if (errorObj.getDefaultMessage() == null) continue;
										String str = messages.getMessage(
												errorObj.getDefaultMessage(),
												new Object[][]{{errorObj.getObjectName()+"."+errorObj.getCodes()[i]}});
										String rest=str.substring(1);											
										arr.add(str.charAt(0)+rest.toLowerCase());
										/*arr.add(
											messages.getMessage(
													errorObj.getDefaultMessage(),
													new Object[][]{{errorObj.getObjectName()+"."+errorObj.getCodes()[i]}}));*/
										ht.put(errorObj.getCodes()[i],str.charAt(0)+rest.toLowerCase());
										/*ht.put(errorObj.getCodes()[i],messages.getMessage(
											errorObj.getDefaultMessage(),
											new Object[][]{{errorObj.getObjectName()+"."+errorObj.getCodes()[i]}}));*/
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