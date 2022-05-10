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

public class FabricaSoftwareViewController extends MultiActionController implements InitializingBean {

	private ResourceMessage messages;	

	public FabricaSoftwareViewController(){
		messages = WebResourceMessage.getInstance();
	}
	
	public ModelAndView showFabricaSoftwareServicosView(HttpServletRequest request,  HttpServletResponse response){
		return new ModelAndView("servicos_fabrica_software/fabrica_software");
	}

	public ModelAndView showFabricaSoftwareServicos_View(HttpServletRequest request,  HttpServletResponse response){
		return new ModelAndView("servicos_fabrica_software/fabrica_software_");
	}
	
	public ModelAndView showFabricaSoftwareConsultoriaView(HttpServletRequest request,  HttpServletResponse response){
		return new ModelAndView("consultoria_fabrica_software/fabrica_software");
	}

	public ModelAndView showFabricaSoftwareConsultoria_View(HttpServletRequest request,  HttpServletResponse response){
		return new ModelAndView("consultoria_fabrica_software/fabrica_software_");
	}
	
	public ModelAndView showFabricaSoftwareDadosView(HttpServletRequest request,  HttpServletResponse response){
		return new ModelAndView("dados_fabrica_software/fabrica_software");
	}

	public ModelAndView showFabricaSoftwareDados_View(HttpServletRequest request,  HttpServletResponse response){
		return new ModelAndView("dados_fabrica_software/fabrica_software_");
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