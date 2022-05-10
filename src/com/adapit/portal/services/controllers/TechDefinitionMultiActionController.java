package com.adapit.portal.services.controllers;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.NonUniqueObjectException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.adapit.portal.entidades.TechDefinition;
import com.adapit.portal.services.TechDefinitionService;
import com.adapit.portal.services.validation.FieldMsgValidationException;
import com.adapit.portal.services.validation.ValidationException;
import com.workcase.utils.DatePropertyEditor;


//@Controller
public class TechDefinitionMultiActionController extends MultiActionController{
	
	//@Resource(name="fakeDateEditor")
	//private DatePropertyEditor datePropertyEditor;
	
	/**
	* @spring.property ref="techDefinitionService" singleton="true"
	*/
	private TechDefinitionService techDefinitionService;


	
	public TechDefinitionService getTechDefinitionService() {
		return techDefinitionService;
	}

	public void setTechDefinitionService(TechDefinitionService techDefinitionService) {
		this.techDefinitionService = techDefinitionService;
	}

	//@RequestMapping("/showTechDefinitionMaintenanceForm.html")
	public ModelAndView showTechDefinitionMaintenanceForm(HttpServletRequest request ,HttpServletResponse response ){
		System.out.println(getClass()+".showTechDefinitionMaintenanceForm");
		try{
			if(request.getParameter("techDefinition.id")!=null){
				int id  = Integer.parseInt(request.getParameter("techDefinition.id"));
				if (id>0)try{
					TechDefinition techDefinition = new TechDefinition();
					techDefinition.setId(id);
					request.setAttribute("techDefinition", techDefinition);
				}catch(Exception ex){ex.printStackTrace();}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}

		return new ModelAndView("techdefinition/TechDefinitionMaintenanceForm");
	}
	
	public TechDefinition bindTechDefinition(HttpServletRequest request ) throws Exception{
		TechDefinition techDefinition = new TechDefinition();
		if(com.adapit.web.NumberUtil.isNumeric(request.getParameter("techDefinition.id")))
			techDefinition.setId(java.lang.Integer.parseInt(request.getParameter("techDefinition.id")));
		techDefinition.setContent(request.getParameter("techDefinition.content"));
		techDefinition.setKeywords(request.getParameter("techDefinition.keywords"));
		return techDefinition;
	}
	
	public void reverseBindTechDefinition(HttpServletRequest request ) throws Exception{

	}
	
	//@RequestMapping("/saveTechDefinitionAction_TechDefinitionMaintenanceForm.html")
	public ModelAndView saveTechDefinitionAction_TechDefinitionMaintenanceForm(HttpServletRequest request ,HttpServletResponse response ){
		System.out.println(getClass()+".saveTechDefinitionAction_TechDefinitionMaintenanceForm");
		try{
			TechDefinition techDefinition=bindTechDefinition(request);
			techDefinitionService.saveOrUpdate(techDefinition);
			com.adapit.web.DialogKind kind = com.adapit.web.DialogKind.SucessDialog;
			request.setAttribute("msg", "SucessDialogMessage");
			request.setAttribute("kind", kind);
			request.setAttribute("title", "SucessDialogTitle");
			//Dados para editar o formulário
					request.setAttribute("techDefinition", techDefinition);
			reverseBindTechDefinition(request);
		}
		catch(FieldMsgValidationException ex1){
			ex1.printStackTrace();
			com.adapit.web.DialogKind kind = com.adapit.web.DialogKind.ErrorDialog;
			request.setAttribute("msg", "ErrorDialogMessage");
			request.setAttribute("kind", kind);
			request.setAttribute("title", "ErrorDialogTitle");
			request.setAttribute("errorFields", ex1.getErrorFields());
			request.setAttribute("errorMessages", ex1.getErrorMessages());
		}
		catch(ValidationException ex2){
			ex2.printStackTrace();
			com.adapit.web.DialogKind kind = com.adapit.web.DialogKind.ErrorDialog;
			request.setAttribute("msg", "ErrorDialogMessage");
			request.setAttribute("kind", kind);
			request.setAttribute("title", "ErrorDialogTitle");
		}
		catch(NonUniqueObjectException ex3){
			ex3.printStackTrace();
			com.adapit.web.DialogKind kind = com.adapit.web.DialogKind.ErrorDialog;
			request.setAttribute("msg", "ErrorDialogMessage");
			request.setAttribute("kind", kind);
			request.setAttribute("title", "ErrorDialogTitle");
		}
		catch(ConstraintViolationException ex4){
			ex4.printStackTrace();
			com.adapit.web.DialogKind kind = com.adapit.web.DialogKind.ErrorDialog;
			request.setAttribute("msg", "ErrorDialogMessage");
			request.setAttribute("kind", kind);
			request.setAttribute("title", "ErrorDialogTitle");
		}
		catch(DataException ex5){
			ex5.printStackTrace();
			com.adapit.web.DialogKind kind = com.adapit.web.DialogKind.ErrorDialog;
			request.setAttribute("msg", "ErrorDialogMessage");
			request.setAttribute("kind", kind);
			request.setAttribute("title", "ErrorDialogTitle");
		}
		catch(Exception ex6){
			ex6.printStackTrace();
			com.adapit.web.DialogKind kind = com.adapit.web.DialogKind.ErrorDialog;
			request.setAttribute("msg", "ErrorDialogMessage");
			request.setAttribute("kind", kind);
			request.setAttribute("title", "ErrorDialogTitle");
		}
		return new ModelAndView("techdefinition/TechDefinitionMaintenanceForm");
	}
	
	//@RequestMapping("/deleteTechDefinitionAction_TechDefinitionMaintenanceForm.html")
	public ModelAndView deleteTechDefinitionAction_TechDefinitionMaintenanceForm(HttpServletRequest request ,HttpServletResponse response ){
		System.out.println(getClass()+".deleteTechDefinitionAction_TechDefinitionMaintenanceForm");

		try{
			int id  = Integer.parseInt(request.getParameter("techDefinition.id"));
			if (id > 0 )try{
				TechDefinition techDefinition = new TechDefinition();
				techDefinition.setId(id);
				techDefinitionService.delete(techDefinition);
				com.adapit.web.DialogKind kind = com.adapit.web.DialogKind.SucessDialog;
				request.setAttribute("msg", "SucessDialogMessage");
				request.setAttribute("kind", kind);
				request.setAttribute("title", "SucessDialogTitle");
			}catch(Exception ex){
				com.adapit.web.DialogKind kind = com.adapit.web.DialogKind.ErrorDialog;
				request.setAttribute("msg", "ErrorDialogMessage");
				request.setAttribute("kind", kind);
				request.setAttribute("title", "ErrorDialogTitle");
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return new ModelAndView("techdefinition/TechDefinitionMaintenanceForm");
	}
	
	//@RequestMapping("/filterTechDefinitionByKeyword_TechDefinitionMaintenanceForm.html")
	public ModelAndView filterTechDefinitionByKeyword_TechDefinitionMaintenanceForm(HttpServletRequest request ,HttpServletResponse response ){
		System.out.println(getClass()+".filterTechDefinitionByKeyword_TechDefinitionMaintenanceForm");

		try{
			if(request.getParameter("techDefinition.id")!=null){
				int id  = Integer.parseInt(request.getParameter("techDefinition.id"));
				if (id>0)try{
					TechDefinition techDefinition = new TechDefinition();
					techDefinition.setId(id);
					request.setAttribute("techDefinition", techDefinition);
				}catch(Exception ex){ex.printStackTrace();}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		try{
			String arg0 = null;
			String arg0Str = request.getParameter("techDefinition.keywords");
			if(arg0Str != null && ! arg0Str.trim().equals("")){
				arg0 = arg0Str;
			}
			java.util.Collection<TechDefinition> techDefinitionList = techDefinitionService.filterTechDefinitionByKeyword(arg0);
			request.setAttribute("techDefinitionList", techDefinitionList);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return new ModelAndView("techdefinition/TechDefinitionMaintenanceForm");
	}
	
	//@RequestMapping("/loadTechDefinitionAction_TechDefinitionMaintenanceForm.html")
	@SuppressWarnings("unchecked")
	public ModelAndView loadTechDefinitionAction_TechDefinitionMaintenanceForm(HttpServletRequest request ,HttpServletResponse response ){
		System.out.println(getClass()+".loadTechDefinitionAction_TechDefinitionMaintenanceForm");

		try{
			java.util.Enumeration parNames= request.getParameterNames();
			TechDefinition techDefinition= new TechDefinition();
			while(parNames.hasMoreElements()){
				String name = (String) parNames.nextElement();
				String val = request.getParameter(name);
				if (name.indexOf("techDefinition") == 0) {
					techDefinition.setId(java.lang.Integer.parseInt(val));
					break;
				}
			}
			try {
			   //screen initialization parameters
				techDefinition = techDefinitionService.loadTechDefinition(techDefinition.getId());
					request.setAttribute("techDefinition", techDefinition);
			reverseBindTechDefinition(request);
			}catch(Exception ex){
				ex.printStackTrace();
			}

		}catch(Exception ex){
			ex.printStackTrace();
		}
		return new ModelAndView("techdefinition/TechDefinitionMaintenanceForm");
	}
	
	//@RequestMapping("/showListAllTechDefinitionView_TechDefinitionMaintenanceForm.html")
	public ModelAndView showListAllTechDefinitionView_TechDefinitionMaintenanceForm(HttpServletRequest request ,HttpServletResponse response ){
		System.out.println(getClass()+".showListAllTechDefinitionView_TechDefinitionMaintenanceForm");

		try{
			if(request.getParameter("techDefinition.id")!=null){
				int id  = Integer.parseInt(request.getParameter("techDefinition.id"));
				if (id>0)try{
					TechDefinition techDefinition = new TechDefinition();
					techDefinition.setId(id);
					request.setAttribute("techDefinition", techDefinition);
				}catch(Exception ex){ex.printStackTrace();}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}

		try{
			java.util.Collection<TechDefinition> techDefinitionList = techDefinitionService.listAll();
			request.setAttribute("techDefinitionList", techDefinitionList);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return new ModelAndView("techdefinition/TechDefinitionMaintenanceForm");
	}
	
	//@RequestMapping("/loadTechDefinitionFromeditselectionTechDefinitionButtonAction_TechDefinitionMaintenanceForm.html")
	@SuppressWarnings("unchecked")
	public ModelAndView loadTechDefinitionFromeditselectionTechDefinitionButtonAction_TechDefinitionMaintenanceForm(HttpServletRequest request ,HttpServletResponse response ){
		System.out.println(getClass()+".loadTechDefinitionFromeditselectionTechDefinitionButtonAction_TechDefinitionMaintenanceForm");

		try{
			java.util.Enumeration parNames= request.getParameterNames();
			TechDefinition techDefinition= new TechDefinition();
			while(parNames.hasMoreElements()){
				String name = (String) parNames.nextElement();
				String val = request.getParameter(name);
				if (name.indexOf("techDefinition") == 0) {
					techDefinition.setId(java.lang.Integer.parseInt(val));
					break;
				}
			}
			try {
			   //screen initialization parameters
				techDefinition = techDefinitionService.loadTechDefinition(techDefinition.getId());
					request.setAttribute("techDefinition", techDefinition);
			reverseBindTechDefinition(request);
			}catch(Exception ex){
				ex.printStackTrace();
			}

		}catch(Exception ex){
			ex.printStackTrace();
		}
		return new ModelAndView("techdefinition/TechDefinitionMaintenanceForm");
	}
	
	//@RequestMapping("/deleteManyTechDefinitionAction_TechDefinitionMaintenanceForm.html")
	@SuppressWarnings("unchecked")
	public ModelAndView deleteManyTechDefinitionAction_TechDefinitionMaintenanceForm(HttpServletRequest request ,HttpServletResponse response ){
		System.out.println(getClass()+".deleteManyTechDefinitionAction_TechDefinitionMaintenanceForm");
		try{
			java.util.Enumeration parNames= request.getParameterNames();
			java.util.ArrayList<TechDefinition> remList = new java.util.ArrayList<TechDefinition>();
			while(parNames.hasMoreElements()){
				String name = (String) parNames.nextElement();
				String val = request.getParameter(name);
				if(name.indexOf("techDefinition")==0) try {
					int id = (java.lang.Integer.parseInt(val));
						java.util.Collection<TechDefinition> arr = techDefinitionService.listAll();
						if(arr != null && arr.size()>0){
							for(TechDefinition techDefinition: arr){
								if (id > 0  && (id ==  techDefinition.getId() )){
									remList.add(techDefinition);
									break;
								}
							}
						}
				}catch(Exception ex){ex.printStackTrace();}
			}
			if(remList != null && remList.size()>0){
				try {
					techDefinitionService.deleteMany(remList);
					//SUCESS FORM
					com.adapit.web.DialogKind kind = com.adapit.web.DialogKind.SucessDialog;
					request.setAttribute("msg", "SucessDialogMessage");
					request.setAttribute("kind", kind);
					request.setAttribute("title", "SucessDialogTitle");
				}catch(Exception ex){
					ex.printStackTrace();
					//ERROR FORM
					com.adapit.web.DialogKind kind = com.adapit.web.DialogKind.ErrorDialog;
					request.setAttribute("msg", "ErrorDialogMessage");
					request.setAttribute("kind", kind);
					request.setAttribute("title", "ErrorDialogTitle");
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return new ModelAndView("techdefinition/TechDefinitionMaintenanceForm");
	}



}