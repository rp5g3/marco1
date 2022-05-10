package com.adapit.portal.services.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.adapit.portal.entidades.Imagem;
import com.adapit.portal.entidades.Participante;
import com.adapit.portal.entidades.PersonAttributeType;
import com.adapit.portal.services.local.LocalPessoaService;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.WebResourceMessage;
@SuppressWarnings({"serial","unchecked","unused","static-access"})
public class InstitucionalViewController extends MultiActionController implements InitializingBean {

	private ResourceMessage messages;	

	public InstitucionalViewController(){
		messages = WebResourceMessage.getInstance();
	}
	
	public ModelAndView showInstitucionalView(HttpServletRequest request,  HttpServletResponse response){
		
		String pageid = request.getParameter("page_id");
		System.out.println("institucional " + pageid);
		request.setAttribute("pageUrl", pageid);
		return new ModelAndView("telaInstitucional");
	}
	
	public ModelAndView showEmpresaView(HttpServletRequest request,  HttpServletResponse response){
		return new ModelAndView("institucional/empresa");
	}

	public ModelAndView showLocalizacaoView(HttpServletRequest request,  HttpServletResponse response){
		return new ModelAndView("institucional/localizacao");
	}
	
	public ModelAndView showLocalizacaoGmView(HttpServletRequest request,  HttpServletResponse response){
		return new ModelAndView("institucional/localizacaogm");
	}
	
	public ModelAndView showTecnologiasView(HttpServletRequest request,  HttpServletResponse response){
		return new ModelAndView("institucional/tecnologias");
	}
	
	public ModelAndView showProcessosView(HttpServletRequest request,  HttpServletResponse response){
		return new ModelAndView("institucional/tecnologia_ciclovida");
	}
	
	public ModelAndView showEquipeView(HttpServletRequest request,  HttpServletResponse response){
		return new ModelAndView("institucional/equipe");
	}
	
	public ModelAndView showParceriasView(HttpServletRequest request,  HttpServletResponse response){
		return new ModelAndView("institucional/parcerias");
	}
	
	public ModelAndView showContatoView(HttpServletRequest request,  HttpServletResponse response){
		return new ModelAndView("institucional/contato");
	}
	
	public ModelAndView showApresentacaoView(HttpServletRequest request,  HttpServletResponse response){
		
		return new ModelAndView("institucional/apresentacao");
	}
	
	public ModelAndView showHistoricoView(HttpServletRequest request,  HttpServletResponse response){
		
		return new ModelAndView("institucional/historico");
	}
	
	public ModelAndView showSolucoesView(HttpServletRequest request,  HttpServletResponse response){
		
		return new ModelAndView("institucional/solucoes");
	}
	
	public ModelAndView showDiferenciacaoView(HttpServletRequest request,  HttpServletResponse response){
		return new ModelAndView("institucional/diferenciacao");
	}
	
	
	
	public ModelAndView showEmpresa_View(HttpServletRequest request,  HttpServletResponse response){
		return new ModelAndView("institucional/empresa_");
	}

	public ModelAndView showLocalizacao_View(HttpServletRequest request,  HttpServletResponse response){
		return new ModelAndView("institucional/localizacao_");
	}
	
	public ModelAndView showTecnologias_View(HttpServletRequest request,  HttpServletResponse response){
		return new ModelAndView("institucional/tecnologias_");
	}
	
	public ModelAndView showProcessos_View(HttpServletRequest request,  HttpServletResponse response){
		return new ModelAndView("institucional/tecnologia_ciclovida_");
	}
	
	public ModelAndView showEquipe_View(HttpServletRequest request,  HttpServletResponse response){
		return new ModelAndView("institucional/equipe_");
	}
	
	public ModelAndView showParcerias_View(HttpServletRequest request,  HttpServletResponse response){
		return new ModelAndView("institucional/parcerias_");
	}
	
	public ModelAndView showContato_View(HttpServletRequest request,  HttpServletResponse response){
		return new ModelAndView("institucional/contato_");
	}
	
	public ModelAndView showApresentacao_View(HttpServletRequest request,  HttpServletResponse response){
		try {
			List<Long> list = LocalPessoaService.getInstance().listPersonIdByAttribute(PersonAttributeType.Responsavel_Legal);
			
			if (list != null && list.size()>0){
				List<Participante> parts = new ArrayList<Participante>();
				for(Long l: list){
					Participante p = LocalPessoaService.getInstance().getParticipante(l);
					//System.out.println("Representante " + p.getNomeFormatado());
					Imagem im = LocalPessoaService.getInstance().getPersonalImage(p.getId());
					p.setLogotipo(im);
					parts.add(p);
					
				}
				request.setAttribute("representantes", parts);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("institucional/apresentacao_");
	}
	
	public ModelAndView showHistorico_View(HttpServletRequest request,  HttpServletResponse response){
		return new ModelAndView("institucional/historico_");
	}
	
	public ModelAndView showSolucoes_View(HttpServletRequest request,  HttpServletResponse response){
		return new ModelAndView("institucional/solucoes_");
	}
	
	public ModelAndView showDiferenciacao_View(HttpServletRequest request,  HttpServletResponse response){
		return new ModelAndView("institucional/diferenciacao_");
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