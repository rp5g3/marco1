package com.adapit.portal.services.controllers;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.hibernate.Session;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.adapit.portal.dto.UsuarioDTO;
import com.adapit.portal.entidades.ComercialSolution;
import com.adapit.portal.entidades.Imagem;
import com.adapit.portal.entidades.Participante;
import com.adapit.portal.entidades.Update;
import com.adapit.portal.entidades.UpdateFile;
import com.adapit.portal.entidades.Usuario;
import com.adapit.portal.services.local.LocalImagemService;
import com.adapit.portal.services.local.LocalServicesUtility;
import com.adapit.portal.services.validation.UpdateServiceValidator;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.WebResourceMessage;
import com.workcase.utils.DatePropertyEditor;


public class UpdateViewController extends MultiActionController implements InitializingBean {

	private ResourceMessage messages;	
	
	private UpdateServiceValidator updateService;
	
	
	private DatePropertyEditor datePropertyEditor;
	
	/**
	* @spring.property ref="updateService" singleton="true"
	*/
	public UpdateServiceValidator getUpdateService() {
		return updateService;
	}

	public void setUpdateService(UpdateServiceValidator updateService) {
		this.updateService = updateService;
	}

	public UpdateViewController(){
		messages = WebResourceMessage.getInstance();
		datePropertyEditor = new DatePropertyEditor();
	}
	
	@SuppressWarnings({ "unchecked" })
	public ModelAndView showLastUpdateView(HttpServletRequest request,  HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".showLastUpdateView "+request.getParameter("ano")+ "  "+request.getParameter("csid"));
		int csint = 0;
		try {
			String csid= request.getParameter("csid");		
			if(csid != null)
				csint=Integer.parseInt(csid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Update> list = null;
		if(request.getParameter("ano") != null){
			int ano = Integer.parseInt(request.getParameter("ano"));
			list = updateService.listLastUpdate(30,ano,new ComercialSolution(csint));
		}
		else list = updateService.listLastUpdate(30,new ComercialSolution(csint));
		request.setAttribute("updateList", list);
		request.setAttribute("sigla",request.getParameter("sigla"));
		return new ModelAndView("update/lastupdate");
	}
	
	@SuppressWarnings("unchecked")
	public ModelAndView showUpdateAnoView(HttpServletRequest request,  HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".showUpdateAnoView");
		int ano = Integer.parseInt(request.getParameter("ano"));
		int csint = 0;
		try {
			String csid= request.getParameter("csid");		
			csint=Integer.parseInt(csid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Update> list = updateService.listLastUpdate(30,ano,new ComercialSolution(csint));
		System.out.println(list.size());
		request.removeAttribute("updateList");
		request.setAttribute("updateList", list);
		request.setAttribute("sigla",request.getParameter("sigla"));
		return new ModelAndView("update/lastupdate");
	}
	
	@SuppressWarnings("unchecked")
	public ModelAndView showLargerLastUpdateView(HttpServletRequest request,  HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".showLargerLastUpdateView");
		int csint = 0;
		try {
			String csid= request.getParameter("csid");		
			csint=Integer.parseInt(csid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Update> list = updateService.listLastUpdate(10,new ComercialSolution(csint));
		request.setAttribute("updateList", list);
		request.setAttribute("sigla",request.getParameter("sigla"));
		return new ModelAndView("update/indexupdate");
	}
	@SuppressWarnings("unchecked")
	public ModelAndView showUpdateFormView(HttpServletRequest request,  HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".showUpdateFormView");
		
		String par = request.getParameter("updateid");
		String sigla=request.getParameter("sigla");
		System.out.println("ID= "+par+"  sigla="+sigla);
		
		int id= (Integer.parseInt(par));
		int csid=0;
		
		try {
			Update update = updateService.getUpdateById(id);
			if (update != null){
				csid = update.getCommercialSolution().getId();
				List imgs = updateService.getImagensByUpdateId(id);
				List<UpdateFile> updateFiles = updateService.listAllUpdateFiles(update, true);
				
				update.setImagens(imgs);
				request.getSession(true).setAttribute("update", update);
				request.setAttribute("update", update);
				
				request.setAttribute("updateFiles", updateFiles);
			}
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		request.setAttribute("csid",csid);
		request.setAttribute("sigla",sigla);
		return new ModelAndView("update/update");
	}
	@SuppressWarnings("unchecked")
	public ModelAndView showUpdateContentView(HttpServletRequest request,  HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".showUpdateContentView");
		
		String par = request.getParameter("updateid");
		System.out.println(par);
		int id= (Integer.parseInt(par));
		try {
			Update update = updateService.getUpdateById(id);
			List imgs = updateService.getImagensByUpdateId(id);
			List<UpdateFile> updateFiles = updateService.listAllUpdateFiles(update, true);
			if(updateFiles != null && updateFiles.size()>0){
				for(UpdateFile uf:updateFiles){
					if(uf.isRestrict()){
						List<Usuario> users = updateService.listAutorizedUsers(uf);
						ArrayList<Participante> arr = new ArrayList<Participante>();
						if(users != null && users.size()>0)
							for(Usuario user: users)
							arr.add((Participante) user.getDadosPessoais());
						uf.setAutorizedUsers(arr);
					}					
				}
			}
			
			update.setImagens(imgs);
			request.getSession(true).setAttribute("update", update);
			request.setAttribute("update", update);
			request.setAttribute("updateFiles", updateFiles);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		System.out.println("Sigla "+request.getParameter("sigla"));
		request.setAttribute("sigla",request.getParameter("sigla"));
		return new ModelAndView("update/update_content");
	}
	
	public ModelAndView downloadDbFileView(HttpServletRequest request,HttpServletResponse response){		
		System.out.println(getClass().getSimpleName()+".downloadDbFileView");
		Session s =null;
		try {			
			s= LocalServicesUtility.getInstance().openSession();
			Integer id = Integer.parseInt(request.getParameter("file_id"));
			String fid = request.getParameter("updatefile_id");
			if(fid == null || fid.trim().equals("")){
				return new ModelAndView("error/access_denied");
			}
			UpdateFile up = (UpdateFile) s.load(UpdateFile.class, Integer.parseInt(fid));
			com.adapit.portal.entidades.Arquivo arq = 
				(com.adapit.portal.entidades.Arquivo) 
				s.load(com.adapit.portal.entidades.Arquivo.class, id);
			
			UsuarioDTO ud = (UsuarioDTO) request.getSession().getAttribute("user");
			if(!up.isRestrict() || (ud != null && up.isAutorized(ud.getParticipanteId()))){
				byte barr[] = arq.getFullBytes();
				
				response.setContentType("application/force-download");
				
				response.setHeader("Pragma","public");
				response.setHeader("Expires","0");
				response.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");
				response.setHeader("Cache-Control","private");//,false); // required for certain browsers 
				// change, added quotes to allow spaces in filenames, by Rajkumar Singh
				response.setHeader("Content-Disposition","attachment; filename=\""+arq.getName()+"\";" );
				response.setHeader("Content-Transfer-Encoding","binary");
				response.setHeader("Content-Length",barr.length+"");
				
	            java.io.InputStream in = new java.io.ByteArrayInputStream(barr);
	            OutputStream out = response.getOutputStream();
	            
	            int b;
	            while ((b = in.read()) != -1) {
	                out.write(b);
	            }
	 
	            in.close();
	            out.flush();
	            out.close();
			}else{
				return new ModelAndView("error/access_denied");
			}
		} catch (Exception e) {
			e.printStackTrace();		
		}finally{
			if (s != null) s.close();
		}		
		return null;
	}
	
	public ModelAndView showUpdateBigImageView(HttpServletRequest request,HttpServletResponse response){		
		System.out.println(getClass().getSimpleName()+".showUpdateBigImageView");
		try {	
			Update update = (Update) request.getSession().getAttribute("update");
			Integer id = Integer.parseInt(request.getParameter("image_id"));
			
			if(update != null && update.getImagens() != null && update.getImagens().size()>0){
				
				for(Imagem im : update.getImagens()){
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
	
	public ModelAndView showUpdateSmallImageView(HttpServletRequest request,HttpServletResponse response){		
		System.out.println(getClass().getSimpleName()+".showUpdateSmallImageView");
		try {	
			Update update = (Update) request.getSession().getAttribute("update");
			Integer id = Integer.parseInt(request.getParameter("image_id"));
			
			if(update != null && update.getImagens() != null && update.getImagens().size()>0){
				
				for(Imagem im : update.getImagens()){
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
	
	//@RequestMapping("/showUpdateMaintenanceForm.html")
	public ModelAndView showUpdateMaintenanceForm(HttpServletRequest request ,HttpServletResponse response ){
		System.out.println(getClass().getSimpleName()+".showUpdateMaintenanceForm");

		try{
			if(request.getParameter("update.id")!=null){
				int id  = Integer.parseInt(request.getParameter("update.id"));
				if (id>0)try{
					request.setAttribute("update", updateService.getUpdateById(id));
				}catch(Exception ex){ex.printStackTrace();}
			}else {
				Update n = new Update();
				n.setDataCriacao(new java.util.Date());
				n.setDataPublicacao(new java.util.Date());
				n.setTitulo("Nova Notícia");
				
				request.setAttribute("update", n);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}

		return new ModelAndView("update/UpdateMaintenanceForm");
	}
	
	public Update bindUpdate(HttpServletRequest request ) throws Exception{
		if(datePropertyEditor == null)
			datePropertyEditor = new DatePropertyEditor();
		Update update = new Update();
		if(com.adapit.web.NumberUtil.isNumeric(request.getParameter("update.id")))
			update.setId(java.lang.Integer.parseInt(request.getParameter("update.id")));
		update.setDescricao(request.getParameter("update.descricao"));
		datePropertyEditor.setAsText(request.getParameter("update.dataPublicacao"));
		update.setDataPublicacao((java.util.Date)datePropertyEditor.getValue());
		update.setTitulo(request.getParameter("update.titulo"));
		update.setResumo(request.getParameter("update.resumo"));
		return update;
	}
	
	public void reverseBindUpdate(HttpServletRequest request ) throws Exception{

	}
	
	//@RequestMapping("/saveUpdateAction_UpdateMaintenanceForm.html")
	public ModelAndView saveUpdateAction_UpdateMaintenanceForm(HttpServletRequest request ,HttpServletResponse response ){
		System.out.println(getClass().getSimpleName()+".saveUpdateAction_UpdateMaintenanceForm");
		try{
			Update update=bindUpdate(request);
			updateService.saveOrUpdate(update);
			com.adapit.web.DialogKind kind = com.adapit.web.DialogKind.SucessDialog;
			request.setAttribute("msg", "SucessDialogMessage");
			request.setAttribute("kind", kind);
			request.setAttribute("title", "SucessDialogTitle");
			request.setAttribute("update", update);
			reverseBindUpdate(request);
		} catch(Exception ex6){
			ex6.printStackTrace();
			com.adapit.web.DialogKind kind = com.adapit.web.DialogKind.ErrorDialog;
			request.setAttribute("msg", "ErrorDialogMessage");
			request.setAttribute("kind", kind);
			request.setAttribute("title", "ErrorDialogTitle");
		}
		return new ModelAndView("update/UpdateMaintenanceForm");
	}

	
}