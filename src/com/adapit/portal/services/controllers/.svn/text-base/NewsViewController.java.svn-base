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
import com.adapit.portal.entidades.News;
import com.adapit.portal.services.local.LocalImagemService;
import com.adapit.portal.services.validation.NewsServiceValidator;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.WebResourceMessage;
import com.workcase.utils.DatePropertyEditor;

public class NewsViewController extends MultiActionController implements InitializingBean {

	private ResourceMessage messages;	
	
	private NewsServiceValidator newsService;
	
	
	private DatePropertyEditor datePropertyEditor;
	
	/**
	* @spring.property ref="newsService" singleton="true"
	*/
	public NewsServiceValidator getNewsService() {
		return newsService;
	}

	public void setNewsService(NewsServiceValidator newsService) {
		this.newsService = newsService;
	}

	public NewsViewController(){
		messages = WebResourceMessage.getInstance();
		datePropertyEditor = new DatePropertyEditor();
	}
	
	@SuppressWarnings({ "unchecked" })
	public ModelAndView showLastNewsView(HttpServletRequest request,  HttpServletResponse response){
		System.out.println("showLastNewsView "+request.getParameter("ano"));
		List<News> list = null;
		if(request.getParameter("ano") != null){
			int ano = Integer.parseInt(request.getParameter("ano"));
			list = newsService.listLastNews(30,ano);
		}
		else list = newsService.listLastNews(30);
		request.setAttribute("newsList", list);
		return new ModelAndView("news/lastnews");
	}
	
	@SuppressWarnings("unchecked")
	public ModelAndView showNewsAnoView(HttpServletRequest request,  HttpServletResponse response){
		System.out.println("showNewsAnoView");
		int ano = Integer.parseInt(request.getParameter("ano"));
		List<News> list = newsService.listLastNews(30,ano);
		System.out.println(list.size());
		request.removeAttribute("newsList");
		request.setAttribute("newsList", list);
		return new ModelAndView("news/lastnews");
	}
	
	@SuppressWarnings("unchecked")
	public ModelAndView showLargerLastNewsView(HttpServletRequest request,  HttpServletResponse response){
		System.out.println("showLargerLastNewsView");
		List<News> list = newsService.listLastNews(10);
		request.setAttribute("newsList", list);
		return new ModelAndView("news/indexnews");
	}
	@SuppressWarnings("unchecked")
	public ModelAndView showNewsFormView(HttpServletRequest request,  HttpServletResponse response){
		System.out.println("showNewsFormView");
		
		String par = request.getParameter("newsid");
		System.out.println(par);
		
		int id= (Integer.parseInt(par));
		try {
			News news = newsService.getNewsById(id);
			List imgs = newsService.getImagensByNewsId(id);
			if (news != null){
				news.setImagens(imgs);
				request.getSession(true).setAttribute("news", news);
				request.setAttribute("news", news);
			}
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		//request.setAttribute("newsid", par);
		return new ModelAndView("news/news");
	}
	@SuppressWarnings("unchecked")
	public ModelAndView showNewsContentView(HttpServletRequest request,  HttpServletResponse response){
		System.out.println("showNewsContentView");
		
		String par = request.getParameter("newsid");
		System.out.println(par);
		int id= (Integer.parseInt(par));
		try {
			News news = newsService.getNewsById(id);
			List imgs = newsService.getImagensByNewsId(id);
			news.setImagens(imgs);
			request.getSession(true).setAttribute("news", news);
			request.setAttribute("news", news);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		return new ModelAndView("news/news_content");
	}
	
	public ModelAndView showNewsImageView(HttpServletRequest request,HttpServletResponse response){		
		System.out.println(getClass().getSimpleName()+".showNewsImageView");
		try {	
			News news = (News) request.getSession().getAttribute("news");
			Integer id = Integer.parseInt(request.getParameter("image_id"));
			
			if(news != null && news.getImagens() != null && news.getImagens().size()>0){
				
				for(Imagem im : news.getImagens()){
					if (im.getId() != id) continue;					
					request.setAttribute("image", im);
				}
			}else if(id != null){
				Imagem im = LocalImagemService.getInstance().loadImagem(id);
				request.setAttribute("image", im);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
					
		}finally{
			
		}		
		
		return new ModelAndView("news/news_content_img");
	}
	
	public ModelAndView showNewsBigImageView(HttpServletRequest request,HttpServletResponse response){		
		System.out.println(getClass().getSimpleName()+".showNewsBigImageView");
		try {	
			News news = (News) request.getSession().getAttribute("news");
			Integer id = Integer.parseInt(request.getParameter("image_id"));
			
			if(news != null && news.getImagens() != null && news.getImagens().size()>0){
				
				for(Imagem im : news.getImagens()){
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
			}else if(id != null){
				Imagem im = LocalImagemService.getInstance().loadImagem(id);
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
			}
			
		} catch (Exception e) {
			e.printStackTrace();
					
		}finally{
			
		}		
		
		return null;
	}
	
	public ModelAndView showNewsSmallImageView(HttpServletRequest request,HttpServletResponse response){		
		System.out.println(getClass().getSimpleName()+".showNewsSmallImageView");
		try {	
			News news = (News) request.getSession().getAttribute("news");
			Integer id = Integer.parseInt(request.getParameter("image_id"));
			
			if(news != null && news.getImagens() != null && news.getImagens().size()>0){
				
				for(Imagem im : news.getImagens()){
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
			}else if(id != null){
				 
				try{
					Imagem im = LocalImagemService.getInstance().loadImagem(id);
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
		            
				}catch(Exception ex){ex.printStackTrace();}
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
	
	//@RequestMapping("/showNewsMaintenanceForm.html")
	public ModelAndView showNewsMaintenanceForm(HttpServletRequest request ,HttpServletResponse response ){
		System.out.println(getClass()+".showNewsMaintenanceForm");

		try{
			if(request.getParameter("news.id")!=null){
				int id  = Integer.parseInt(request.getParameter("news.id"));
				if (id>0)try{
					request.setAttribute("news", newsService.getNewsById(id));
				}catch(Exception ex){ex.printStackTrace();}
			}else {
				News n = new News();
				n.setDataCriacao(new java.util.Date());
				n.setDataPublicacao(new java.util.Date());
				n.setTitulo("Nova Notícia");
				
				request.setAttribute("news", n);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}

		return new ModelAndView("news/NewsMaintenanceForm");
	}
	
	public News bindNews(HttpServletRequest request ) throws Exception{
		if(datePropertyEditor == null)
			datePropertyEditor = new DatePropertyEditor();
		News news = new News();
		if(com.adapit.web.NumberUtil.isNumeric(request.getParameter("news.id")))
			news.setId(java.lang.Integer.parseInt(request.getParameter("news.id")));
		news.setDescricao(request.getParameter("news.descricao"));
		datePropertyEditor.setAsText(request.getParameter("news.dataPublicacao"));
		news.setDataPublicacao((java.util.Date)datePropertyEditor.getValue());
		news.setTitulo(request.getParameter("news.titulo"));
		news.setResumo(request.getParameter("news.resumo"));
		return news;
	}
	
	public void reverseBindNews(HttpServletRequest request ) throws Exception{

	}
	
	//@RequestMapping("/saveNewsAction_NewsMaintenanceForm.html")
	public ModelAndView saveNewsAction_NewsMaintenanceForm(HttpServletRequest request ,HttpServletResponse response ){
		System.out.println(getClass()+".saveNewsAction_NewsMaintenanceForm");
		try{
			News news=bindNews(request);
			newsService.saveOrUpdate(news);
			com.adapit.web.DialogKind kind = com.adapit.web.DialogKind.SucessDialog;
			request.setAttribute("msg", "SucessDialogMessage");
			request.setAttribute("kind", kind);
			request.setAttribute("title", "SucessDialogTitle");
			request.setAttribute("news", news);
			reverseBindNews(request);
		} catch(Exception ex6){
			ex6.printStackTrace();
			com.adapit.web.DialogKind kind = com.adapit.web.DialogKind.ErrorDialog;
			request.setAttribute("msg", "ErrorDialogMessage");
			request.setAttribute("kind", kind);
			request.setAttribute("title", "ErrorDialogTitle");
		}
		return new ModelAndView("news/NewsMaintenanceForm");
	}

	
}