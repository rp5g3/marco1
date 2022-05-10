package com.adapit.portal.services.controllers;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.WebResourceMessage;

public class ComercialSolutionsViewController extends MultiActionController implements InitializingBean {

	private ResourceMessage messages;	

	public ComercialSolutionsViewController(){
		messages = WebResourceMessage.getInstance();
	}
	
	public ModelAndView showWctView(HttpServletRequest request,  HttpServletResponse response){
		return new ModelAndView("produtos/wct");
	}

	public ModelAndView showWctJavaView(HttpServletRequest request,  HttpServletResponse response){
		return new ModelAndView("produtos/wct_java");
	}
	
	public ModelAndView showWctConfigManagementView(HttpServletRequest request,  HttpServletResponse response){
		return new ModelAndView("produtos/wct_config_management");
	}
	
	public ModelAndView showWctBpmnView(HttpServletRequest request,  HttpServletResponse response){
		return new ModelAndView("produtos/wct_bpmn");
	}
	
	public ModelAndView showEcommerceView(HttpServletRequest request,  HttpServletResponse response){
		return new ModelAndView("produtos/ecommerce");
	}
	
	public ModelAndView showWct_View(HttpServletRequest request,  HttpServletResponse response){
		return new ModelAndView("produtos/wct_");
	}

	public ModelAndView showWctJava_View(HttpServletRequest request,  HttpServletResponse response){
		return new ModelAndView("produtos/wct_java_");
	}
	
	public ModelAndView showWctConfigManagement_View(HttpServletRequest request,  HttpServletResponse response){
		return new ModelAndView("produtos/wct_config_management_");
	}
	
	public ModelAndView showWctBpmn_View(HttpServletRequest request,  HttpServletResponse response){
		return new ModelAndView("produtos/wct_bpmn_");
	}
	
	public ModelAndView showEcommerce_View(HttpServletRequest request,  HttpServletResponse response){
		return new ModelAndView("produtos/ecommerce_");
	}

	public void afterPropertiesSet() throws Exception {
		// if (person==null || personType==null) throw new
		// Exception("exceptions.local.BussinessNull");
	}

	
	@SuppressWarnings({ "unused", "unchecked" })
	private void errorsValidate(BindException errors, ArrayList arr){
		if (errors.hasErrors()) {
			Iterator it = errors.getAllErrors().iterator();
			while (it.hasNext()){
				org.springframework.validation.FieldError errorObj = (org.springframework.validation.FieldError) it.next();
				arr.add(
						messages.getMessage(
								errorObj.getDefaultMessage(),
								new Object[][]{{errorObj.getObjectName()+"."+errorObj.getField()}}));
			}
		}
	}
	
	
}