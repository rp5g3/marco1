package com.adapit.portal.services.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.adapit.portal.entidades.Imagem;
import com.adapit.portal.entidades.Participante;
import com.adapit.portal.entidades.PersonAttributeType;
import com.adapit.portal.services.local.LocalImagemService;
import com.adapit.portal.services.local.LocalPessoaService;
import com.workcase.gui.utils.ResourceMessage;

public class RHMultiActionController extends MultiActionController {

	private ResourceMessage messages;


	public ModelAndView showPersonListByAttributeTypeView(HttpServletRequest request,  HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".showPersonListByAttributeTypeView");
		try {
			ApresentacaoViewController.countAcess(request,null,"rh");
			String attType = request.getParameter("attType");
			List<Long> list = LocalPessoaService.getInstance().listPersonIdByAttribute(PersonAttributeType.valueOf(attType));
			setPersonList(list,request);
			if(attType.equalsIgnoreCase(PersonAttributeType.Cliente.name()))
				request.setAttribute("attType", "Nossos Clientes");
			if(attType.equalsIgnoreCase(PersonAttributeType.Instrutor.name()))
				request.setAttribute("attType", "Nossos Instrutores");
			if(attType.equalsIgnoreCase(PersonAttributeType.Colaborador.name()))
				request.setAttribute("attType", "Nossos Colaboradores");
			if(attType.equalsIgnoreCase(PersonAttributeType.Pesquisador.name()))
				request.setAttribute("attType", "Pesquisadores que Colaboram com o Projeto WCT");
			if(attType.equalsIgnoreCase(PersonAttributeType.Responsavel_Legal.name()))
				request.setAttribute("attType", "Empreendedores do Projeto WCT");
			if(attType.equalsIgnoreCase(PersonAttributeType.Parceiro.name()))
				request.setAttribute("attType", "Nossos Parceiros");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("rh/rhList");
	}
	
	private void setPersonList(List<Long> list, HttpServletRequest request) throws Exception{
		List<Participante> arr = new ArrayList<Participante>();
		if(list != null && list.size()>0){
			for(Long l:list){
				Participante p = LocalPessoaService.getInstance().getParticipante(l);
				Imagem im = LocalPessoaService.getInstance().getPersonalImage(p.getId());
				if(im != null)
					im.setSmallImageBytes(LocalImagemService.getInstance().getSmallImageBytesFromImage(im.getId()));
				p.setLogotipo(im);
				arr.add(p);
				
			}
		}
		request.setAttribute("personList", arr);
	}
	
}
