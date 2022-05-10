package com.adapit.portal.services.controllers;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.adapit.portal.entidades.Imagem;
import com.adapit.portal.entidades.Publication;
import com.adapit.portal.services.local.LocalFileService;
import com.adapit.portal.services.validation.PublicationServiceValidator;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.WebResourceMessage;

public class PublicationViewController extends MultiActionController implements InitializingBean {

	private ResourceMessage messages;	
	
	private PublicationServiceValidator publicationService;
	
	/**
	* @spring.property ref="publicationService" singleton="true"
	*/
	public PublicationServiceValidator getPublicationService() {
		return publicationService;
	}

	public void setPublicationService(PublicationServiceValidator publicationService) {
		this.publicationService = publicationService;
	}

	public PublicationViewController(){
		messages = WebResourceMessage.getInstance();
	}
	
	@SuppressWarnings("unchecked")
	public ModelAndView showLastPublicationView(HttpServletRequest request,  HttpServletResponse response){
		System.out.println("showLastPublicationView");
		List<Publication> list = publicationService.listLastPublications(30);
		request.setAttribute("publicationList", list);
		return new ModelAndView("publication/lastpublication");
	}
	
	@SuppressWarnings("unchecked")
	public ModelAndView showPublicationAnoView(HttpServletRequest request,  HttpServletResponse response){
		System.out.println("showPublicationAnoView");
		int ano = Integer.parseInt(request.getParameter("ano"));
		List<Publication> list = publicationService.listLastPublications(30,ano);
		System.out.println(list.size());
		request.removeAttribute("publicationList");
		request.setAttribute("publicationList", list);
		return new ModelAndView("publication/lastpublication");
	}
	
	@SuppressWarnings("unchecked")
	public ModelAndView showLargerLastPublicationView(HttpServletRequest request,  HttpServletResponse response){
		System.out.println("showLargerLastPublicationView");
		List<Publication> list = publicationService.listLastPublications(3);
		request.setAttribute("publicationList", list);
		return new ModelAndView("publication/indexpublication");
	}
	@SuppressWarnings("unchecked")
	public ModelAndView showPublicationFormView(HttpServletRequest request,  HttpServletResponse response){
		System.out.println("showPublicationFormView");
		
		String par = request.getParameter("publicationid");
		System.out.println(par);
		
		int id= (Integer.parseInt(par));
		try {
			Publication publication = publicationService.getPublicationById(id);
			if(publication != null){
				try {
					publication.setEditalFile(LocalFileService.getInstance().getPaperFile(id));
				} catch (Exception e) {
					//e.printStackTrace();
				}
				List imgs = publicationService.getImagensByPublicationId(id);
				if (publication != null){
					publication.setImagens(imgs);
					request.getSession(true).setAttribute("publication", publication);
					request.setAttribute("publication", publication);
				}
			}
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		//request.setAttribute("publicationid", par);
		return new ModelAndView("publication/publication");
	}
	@SuppressWarnings("unchecked")
	public ModelAndView showPublicationContentView(HttpServletRequest request,  HttpServletResponse response){
		System.out.println("showPublicationContentView");
		ApresentacaoViewController.countAcess(request,null,"publication");
		String par = request.getParameter("publicationid");
		System.out.println(par);
		int id= (Integer.parseInt(par));
		try {
			Publication publication = publicationService.getPublicationById(id);
			List imgs = publicationService.getImagensByPublicationId(id);
			publication.setImagens(imgs);
			request.getSession(true).setAttribute("publication", publication);
			request.setAttribute("publication", publication);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		return new ModelAndView("publication/publication_content");
	}
	
	public ModelAndView showPublicationImageView(HttpServletRequest request,HttpServletResponse response){		
		
		try {	
			Publication publication = (Publication) request.getSession().getAttribute("publication");
			Integer id = Integer.parseInt(request.getParameter("image_id"));
			
			if(publication.getImagens() != null && publication.getImagens().size()>0){
				
				for(Imagem im : publication.getImagens()){
					if (im.getId() != id) continue;					
					request.setAttribute("image", im);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
					
		}finally{
			
		}		
		
		return new ModelAndView("publication/publication_content_img");
	}
	
	public ModelAndView showPublicationBigImageView(HttpServletRequest request,HttpServletResponse response){		
		
		try {	
			Publication publication = (Publication) request.getSession().getAttribute("publication");
			Integer id = Integer.parseInt(request.getParameter("image_id"));
			
			if(publication.getImagens() != null && publication.getImagens().size()>0){
				
				for(Imagem im : publication.getImagens()){
					if (im.getId() != id) continue; 
					ImageIcon ii = im.getScaledImage(420);
					byte barr[] = Imagem.toByteArray(ii.getImage(), im.getFormat()/*Imagem.getImageFormat(im.getFullImageBytes())*/);
					
					String format = im.getFormat();		
					
					if (format.equalsIgnoreCase("jpg")) response.setContentType("image/jpeg");
					else response.setContentType("image/"+format);			
					           
		            java.io.InputStream in = new java.io.ByteArrayInputStream(barr);
		            OutputStream out = response.getOutputStream();
		            int b;
		            while ((b = in.read()) != -1) {
		                out.write(b);
		            }
		 
		            in.close();
		            out.flush();
		            out.close();
		            break;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
					
		}finally{
			
		}		
		
		return null;
	}
	
	public ModelAndView showPublicationSmallImageView(HttpServletRequest request,HttpServletResponse response){		
		
		try {	
			Publication publication = (Publication) request.getSession().getAttribute("publication");
			Integer id = Integer.parseInt(request.getParameter("image_id"));
			
			if(publication.getImagens() != null && publication.getImagens().size()>0){
				
				for(Imagem im : publication.getImagens()){
					if (im.getId() != id) continue; 
					byte barr[] = im.getSmallImageBytes();
					//ImageIcon ii = im.getScaledImage(450);
					///byte barr[] = Imagem.toByteArray(ii.getImage(), im.getFormat()/*Imagem.getImageFormat(im.getFullImageBytes())*/);
					
					String format = im.getFormat();		
					
					if (format.equalsIgnoreCase("jpg")) response.setContentType("image/jpeg");
					else response.setContentType("image/"+format);			
					           
		            java.io.InputStream in = new java.io.ByteArrayInputStream(barr);
		            OutputStream out = response.getOutputStream();
		            int b;
		            while ((b = in.read()) != -1) {
		                out.write(b);
		            }
		 
		            in.close();
		            out.flush();
		            out.close();	
		            break;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
					
		}finally{
			
		}		
		
		return null;
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