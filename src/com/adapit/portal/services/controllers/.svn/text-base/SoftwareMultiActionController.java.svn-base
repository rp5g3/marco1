package com.adapit.portal.services.controllers;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.adapit.portal.entidades.CommercialSolutionDetailTab;
import com.adapit.portal.entidades.CommercialSolutionDetailTabPK;
import com.adapit.portal.entidades.CssDefinition;
import com.adapit.portal.entidades.SoftwareDomainInterest;
import com.adapit.portal.entidades.SoftwareSolution;
import com.adapit.portal.services.ImagemService;
import com.adapit.portal.services.local.LocalComercialSolutionService;
import com.adapit.portal.services.local.LocalServicesUtility;
import com.adapit.portal.services.local.LocalUpdateService;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.WebResourceMessage;
@SuppressWarnings({"unchecked","unused"})
public class SoftwareMultiActionController  extends MultiActionController implements InitializingBean {

	private ResourceMessage messages;
	
	public final static int PAGE_FORMATION_MAX_NUMBER=6;

	
	private ImagemService imagemService;

	public SoftwareMultiActionController(){
		messages = WebResourceMessage.getInstance();
	}

	public ModelAndView showSoftwareFormView(HttpServletRequest request,
            HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".showSoftwareFormView");
		ApresentacaoViewController.countAcess(request,null,"software");
//		try {
//			request.setCharacterEncoding("UTF-8");
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		}
		String software = request.getParameter("sigla");
		String inf = request.getParameter("information");
		if (software != null && !software.equals("")){
			Session s =null;
			try {			
				s= LocalServicesUtility.getInstance().openSession();
				//System.out.println("Searching software by sigle " + software);
				SoftwareSolution ss = (SoftwareSolution)
					s.createQuery("from SoftwareSolution s where s.sigla=:sigla")
					.setParameter("sigla", software).uniqueResult();
				
				if (ss != null && ss.getImagens() != null){
					int imsize = ss.getImagens().size();
					request.setAttribute("imsize", imsize);
				}else request.setAttribute("imsize", 0);
				
				int upsize = LocalUpdateService.getInstance().countUpdates(ss);
				System.out.println("upsize " + upsize);
				request.setAttribute("upsize", upsize);
				
				java.util.List<CommercialSolutionDetailTab> tabList = s.createQuery("from CommercialSolutionDetailTab" +
						" t where t.toPublish=true and t.commercial_solution_id="+ss.getId()
						+" order by t.index ASC").list();
				if (tabList != null)
					request.setAttribute("tabList",tabList);
				
				CommercialSolutionDetailTab headerTab = (CommercialSolutionDetailTab)
				s.createQuery("from CommercialSolutionDetailTab t where t.commercial_solution_id="+ss.getId()+" and lower(t.detail_tab_name) like 'header'").uniqueResult();
				request.setAttribute("headerTab", headerTab);
				
				CommercialSolutionDetailTab bottomTab = (CommercialSolutionDetailTab)
				s.createQuery("from CommercialSolutionDetailTab t where t.commercial_solution_id="+ss.getId()+" and lower(t.detail_tab_name) like 'bottom'").uniqueResult();
				request.setAttribute("bottomTab", bottomTab);
				
				request.setAttribute("software",ss);
			} catch (Exception e) {
				e.printStackTrace();		
			}finally{
				if (s != null) s.close();
			}
		}else if (inf != null && !inf.equals("")){
			if (inf.equals("dif")){
				return new ModelAndView("software/diferenciacao_");
			}
			else if (inf.equals("proc")){
				return new ModelAndView("institucional/tecnologia_ciclovida_");
			}
			else if (inf.equals("contato")){
				return new ModelAndView("software/contato_");
			}
			else if (inf.equals("softdema")){
				return new ModelAndView("software/ondemand_");
			}
		}
		
		
		return new ModelAndView("software/showSoftware");
	}
	
	public ModelAndView showSoftwareSolutionDetailView(HttpServletRequest request,
            HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".showSoftwaresolutionDetailView");
//		try {
//			request.setCharacterEncoding("Windows-1252");
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		}
		String title="Apresentação";
		String text="";
		String detail = request.getParameter("detail");
		
		String sigla = request.getParameter("sigla");
		
		String sol_id = request.getParameter("sol_id");
		String tab_name = request.getParameter("tab_name");
		String tab_lang = request.getParameter("tab_lang");
		if (detail != null && !detail.equals("")){
			Session s =null;
			System.out.println("detail " + detail);
			try {			
				s= LocalServicesUtility.getInstance().openSession();
				String field="s.resumo";
				if(detail.equals("0")){
					/*CommercialSolutionDetailTabPK pk = new CommercialSolutionDetailTabPK();
					pk.setCommercial_solution_id(Integer.parseInt(sol_id));
					pk.setDetail_tab_name(replaceSimbols(tab_name));
					pk.setDetail_text_language(tab_lang);
					*/
					Integer sid = (Integer) s.createQuery("select s.id from" +
							" SoftwareSolution s where s.sigla=:sigla")
					.setParameter("sigla", sigla).uniqueResult();
					String lang = request.getParameter("lang");
					
					if(lang == null || lang.trim().equals("")){
						lang = (String) request.getSession().getAttribute("lang");
						if(lang == null || lang.trim().equals(""))
							lang = tab_lang;
							
					}
					lang=(lang != null && lang.equals("en")?"en-us":"pt-br");
					 
					List l = s.createQuery("from CommercialSolutionDetailTab tab" +
							" where tab.commercial_solution_id=:id" +
							" and lower(tab.detail_text_language) like :lang" +
							" order by tab.index")
					.setParameter("id",sid)
					.setParameter("lang",lang)
					.setMaxResults(1).list();//s.load(CommercialSolutionDetailTab.class, pk);
					
					if(l != null && l.size()>0){
						CommercialSolutionDetailTab tab = (CommercialSolutionDetailTab) l.get(0);
						if(tab != null){
							title=tab.getDetailName();
							text=tab.getDetail();
							
							try {
								CssDefinition css = (CssDefinition) s.createQuery("select s.cssDefinition from SoftwareSolution s where s.id=:id")
								.setParameter("id",sid).uniqueResult();
								if(css != null)
									text = "<style>"+css.getStyle()+"</style>"+'\n'+text;
							} catch (Exception e) {
								e.printStackTrace();
							}
						}else{
							title="Unavailable";
							text="Unavailable";
							
						}
					}else System.out.println("Nenhuma aba encontrada para " + lang);
					/*title="Apresentação";
					field="s.resumo";
					text = (String)
					s.createQuery("select "+field+" from SoftwareSolution s where s.sigla=:sigla")
					.setParameter("sigla", sigla).uniqueResult();
					try {
						CssDefinition css = (CssDefinition) 
						s.createQuery("select s.cssDefinition from SoftwareSolution s where s.sigla=:sigla")
						.setParameter("sigla",sigla).uniqueResult();
						if(css != null)
							text = "<style>"+css.getStyle()+"</style>"+'\n'+text;
					} catch (Exception e) {
						e.printStackTrace();
					}*/
				}
				else if(detail.equals("1")){
					title="Funcionalidades";
					field="s.funcionalidades";
					text = (String)
					s.createQuery("select "+field+" from SoftwareSolution s where s.sigla=:sigla")
					.setParameter("sigla", sigla).uniqueResult();
					try {
						CssDefinition css = (CssDefinition) 
						s.createQuery("select s.cssDefinition from SoftwareSolution s where s.sigla=:sigla")
						.setParameter("sigla",sigla).uniqueResult();
						if(css != null)
							text = "<style>"+css.getStyle()+"</style>"+'\n'+text;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else if(detail.equals("2")){
					title="Imagens";
					text="image";
					SoftwareSolution ss = (SoftwareSolution) s.createQuery("select s from SoftwareSolution s where s.sigla=:sigla")
					.setParameter("sigla", sigla).uniqueResult();
					if (ss != null && ss.getImagens() != null){
						ss.getImagens().size();
					}
					request.setAttribute("software", ss);
				}else if(detail.equals("3")){
					title="Dados Gerais";
					text="generalData";
					SoftwareSolution ss = (SoftwareSolution) s.createQuery("select s from SoftwareSolution s where s.sigla=:sigla")
					.setParameter("sigla", sigla).uniqueResult();
					request.setAttribute("software", ss);
				}
				
			} catch (Exception e) {
				e.printStackTrace();		
			}finally{
				if (s != null) s.close();
			}
		}else if (sol_id != null && !sol_id.equals("")){
			Session s =null;
			String lang = request.getParameter("lang");
			
			if(lang == null || lang.trim().equals("")){
				lang = (String) request.getSession().getAttribute("lang");
				if(lang == null || lang.trim().equals(""))
					lang = tab_lang;
					
			}
			lang=(lang != null && lang.equals("en")?"en-us":"pt-br");
			
			System.out.println("Buscando " + sol_id + " " + tab_name + " " + lang);
			try {			
				s= LocalServicesUtility.getInstance().openSession();
				CommercialSolutionDetailTabPK pk = new CommercialSolutionDetailTabPK();
				pk.setCommercial_solution_id(Integer.parseInt(sol_id));
				pk.setDetail_tab_name(replaceSimbols(tab_name));
				
				pk.setDetail_text_language(lang.toUpperCase());
				CommercialSolutionDetailTab tab = (CommercialSolutionDetailTab) s.load(CommercialSolutionDetailTab.class, pk);
				title=tab.getDetailName();
				text=tab.getDetail();
				
				try {
					CssDefinition css = (CssDefinition) s.createQuery("select s.cssDefinition from SoftwareSolution s where s.id=:id")
					.setParameter("id",Integer.parseInt(sol_id)).uniqueResult();
					if(css != null)
						text = "<style>"+css.getStyle()+"</style>"+'\n'+text;
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();		
			}finally{
				if (s != null) s.close();
			}
		}		
		
		request.setAttribute("title", title);
		request.setAttribute("text", text);
		
		return new ModelAndView("sys/softwareSolutionContentPane");
	}
	
	public ModelAndView showSoftwareSolutionUpdateView(HttpServletRequest request,
            HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".showSoftwareSolutionUpdateView");
		String sigla = request.getParameter("sigla");
		String name="";
		Integer csid=1;
		if (sigla != null && !sigla.equals("")){
			Session s =null;
			try {			
				//SoftwareSolution
				s= LocalServicesUtility.getInstance().openSession();
				Object[] objs = (Object[]) s.createQuery("select s.nome,s.id from SoftwareSolution s where s.sigla=:sigla")
				.setParameter("sigla", sigla).uniqueResult();
				name=(String)objs[0];
				csid=(Integer)objs[1];
				System.out.println(name + " " + csid);
				
				try {
					List<SoftwareDomainInterest> list = LocalComercialSolutionService.getInstance().listAllSoftwareDomainInterest(csid,null);
					if (list != null && list.size()>0){
						for (SoftwareDomainInterest sd: list){
							List<SoftwareSolution> slist = LocalComercialSolutionService.getInstance().listSoftwaresByDomainName(sd.getNome(),true);
							ArrayList arr = new ArrayList();
							if (slist != null && slist.size()>0){
								for (SoftwareSolution ss: slist){
									ss.getNome();
									arr.add(ss);
								}
							}
							sd.setSoftwares(arr);
						}
						request.setAttribute("domainList", list);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();		
			}finally{
				if (s != null) s.close();
			}
		}
		request.setAttribute("sigla", sigla);
		request.setAttribute("name", name);
		request.setAttribute("csid", csid);
		return new ModelAndView("update/update");
	}
	
	private static String replaceSimbols(String catNames){
		return (catNames.replace("Ã³", "ó")
		.replace("Ã­", "í").replace("Ãª", "ê").replace("Ã¡", "á")
		.replace("ã­", "í").replace("ãª", "ê").replace("Ã©", "é")
		.replace("Ã´", "ô").replace("Ã§", "ç").replace("Ãµ", "õ")
		.replace("Ã£", "ã"));
	}
	
	public ModelAndView showProdutosView(HttpServletRequest request,
            HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".showProdutosView");
		String sigle = request.getParameter("sigla");
		String inf = request.getParameter("information");
		String showPage="sys/sys";
		if (sigle != null){
			System.out.println("Produto: " + sigle);
			request.setAttribute("product", sigle);
			showSoftwareFormView(request,response);
			try {
				List<SoftwareDomainInterest> list = LocalComercialSolutionService.getInstance().listAllSoftwareDomainInterest(null,sigle);
				if (list != null && list.size()>0){
					for (SoftwareDomainInterest sd: list){
						List<SoftwareSolution> slist = LocalComercialSolutionService.getInstance().listSoftwaresByDomainName(sd.getNome(),true);
						ArrayList arr = new ArrayList();
						if (slist != null && slist.size()>0){
							for (SoftwareSolution ss: slist){
								ss.getNome();
								arr.add(ss);
							}
						}
						sd.setSoftwares(arr);
					}
					request.setAttribute("domainList", list);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (inf != null){
			System.out.println("Informação: " + sigle);
			request.setAttribute("information", inf);	
			try {
				List<SoftwareDomainInterest> list = LocalComercialSolutionService.getInstance().listAllSoftwareDomainInterest(null,null);
				if (list != null && list.size()>0){
					for (SoftwareDomainInterest sd: list){
						List<SoftwareSolution> slist = LocalComercialSolutionService.getInstance().listSoftwaresByDomainName(sd.getNome(),true);
						ArrayList arr = new ArrayList();
						if (slist != null && slist.size()>0){
							for (SoftwareSolution ss: slist){
								ss.getNome();
								arr.add(ss);
							}
						}
						sd.setSoftwares(arr);
					}
					request.setAttribute("domainList", list);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			showPage="telaSistemas";
		}
		
		return new ModelAndView(showPage);//telaSistemas
	}
	
	


	@Override
	public void afterPropertiesSet() throws Exception {
		
	}
	
	
}