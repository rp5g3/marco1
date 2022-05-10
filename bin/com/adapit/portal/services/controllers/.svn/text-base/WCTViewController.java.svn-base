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

public class WCTViewController extends MultiActionController implements InitializingBean {

	private ResourceMessage messages;	

	public WCTViewController(){
		messages = WebResourceMessage.getInstance();
	}
	
	public ModelAndView showEmpresaView(HttpServletRequest request,  HttpServletResponse response){
		return new ModelAndView("institucional/empresa");
	}

	@SuppressWarnings("unchecked")
	public ModelAndView showBomUsoFerramentasView(HttpServletRequest request,
            HttpServletResponse response){		
		return new ModelAndView("ti/wct/index_bom_uso_capital_investido");
	}
	
	@SuppressWarnings("unchecked")
	public ModelAndView showWctVisaoGlobalView(HttpServletRequest request,
            HttpServletResponse response){		
		return new ModelAndView("ti/wct/index_wct_visao_global");
	}
	
	@SuppressWarnings("unchecked")
	public ModelAndView showWctDiretorView(HttpServletRequest request,
            HttpServletResponse response){		
		return new ModelAndView("ti/diretor/wct");
	}
	
	@SuppressWarnings("unchecked")
	public ModelAndView showSolucoesProducaoSoftwareView(HttpServletRequest request,
            HttpServletResponse response){		
		return new ModelAndView("ti/index");
	}
	
	@SuppressWarnings("unchecked")
	public ModelAndView showWctLowDescriptionView(HttpServletRequest request,
            HttpServletResponse response){		
		return new ModelAndView("index_linhaproduto");
	}
	
	

	@SuppressWarnings("unchecked")
	public ModelAndView showWctGeracaoGuiView(HttpServletRequest request,
            HttpServletResponse response){	
		System.out.println("ok");
		return new ModelAndView("wct/wct_geracao_gui");
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