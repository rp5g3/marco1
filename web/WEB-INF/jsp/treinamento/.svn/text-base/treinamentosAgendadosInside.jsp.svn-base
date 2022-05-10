<%@page import="com.adapit.portal.entidades.*" %>
<%@page import="com.workcase.utils.*" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="validation" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="binder" uri="http://www.springframework.org/tags"%>

<%@page import="com.workcase.utils.IdGenerator"%>
<%@page import="com.adapit.portal.dto.UsuarioDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.adapit.portal.util.global.GlobalVariables"%>
	

<%	
    List<Treinamento> lotes = (List<Treinamento>) request.getAttribute("treinamentosList");
    List<Treinamento> lotesMarcados = (List<Treinamento>) request.getAttribute("treinamentosMarcados");
    int containerPrincipalWidth=GlobalVariables.ContainerPrincipalWidth;
    int containerPrincipalHeight=GlobalVariables.ContainerPrincipalHeight;
    int maxRowNumberLotes=8;
    String random = IdGenerator.getInstance().generateId(3);
    UsuarioDTO user = (UsuarioDTO) request.getSession(true).getAttribute("user");
    
%>

	<script type="text/javascript">
	// increase the default animation speed to exaggerate the effect
	$.fx.speeds._default = 1000;
	$(function() {
		<%
		if(lotes != null && lotes.size()>0)
    		for(Treinamento lote: lotes){%>
		$('#descriptionDialog<%=lote.getId()+random%>').dialog({
			autoOpen: false,
			show: 'blind',
			hide: 'explode',
			minWidth: 500,
			minHeight: 450
		});
		
		$('#open_descriptionDialog<%=lote.getId()+random%>').click(function() {
			$('#descriptionDialog<%=lote.getId()+random%>').dialog('open');
			return false;
		});

		$('#investimentoDialog<%=lote.getId()+random%>').dialog({
			autoOpen: false,
			show: 'blind',
			hide: 'explode',
			minWidth: 500,
			minHeight: 450
		});
		
		$('#open_investimentoDialog<%=lote.getId()+random%>').click(function() {
			$('#investimentoDialog<%=lote.getId()+random%>').dialog('open');
			return false;
		});
		
		
		$('#agendaDialog<%=lote.getId()+random%>').dialog({
			autoOpen: false,
			show: 'blind',
			hide: 'explode',
			minWidth: 500,
			minHeight: 450
		});
		
		$('#open_agendaDialog<%=lote.getId()+random%>').click(function() {
			$('#agendaDialog<%=lote.getId()+random%>').dialog('open');
			return false;
		});

		$('#imageDialog<%=lote.getId()+random%>').dialog({
			autoOpen: false,
			show: 'blind',
			hide: 'explode',
			minWidth: 500,
			minHeight: 450
		});
		
		$('#open_imageDialog<%=lote.getId()+random%>').click(function() {
			$('#imageDialog<%=lote.getId()+random%>').dialog('open');
			return false;
		});

		$('#detalhesTreinamentoDialog<%=lote.getId()+random%>').dialog({
			autoOpen: false,
			show: 'blind',
			hide: 'explode',
			minWidth: 500,
			minHeight: 450
		});
		
		$('#open_detalhesTreinamentoDialog<%=lote.getId()+random%>').click(function() {
			$('#detalhesTreinamentoDialog<%=lote.getId()+random%>').dialog('open');
			return false;
		});
		<%}%>
	});	
	</script>


  	<script type="text/javascript" src="js/wz_tooltip.js"></script>
    <script type="text/javascript" src="js/virtualpaginate.js"></script>

    
    <%
    if (lotes == null || (!lotes.iterator().hasNext())){
    	out.print("<br><br><br><br><br><center>N&atilde;o foram encontrados treinamentos para a consulta</center><br><br>");
    }else{
		java.util.Iterator<Treinamento> it= lotes.iterator();
		int leilaoId=0;
	%>
					<table border=0 cellpadding=0 cellspacing=0 bordercolor=#000000 width="100%" style="background-color:transparent;" >
										<tr>
											<th width="5px" height="20" bgcolor="#999999">&nbsp;</th>
											<th width="20%" height="20" bgcolor="#999999"><center><b>Datas</b></center></th>
											<th width="50px" bgcolor="#999999" height="20"><center><b>C&oacute;digo</b></center></th>
											<th width="<%=(user != null)?"70":"70" %>%" bgcolor="#999999" height="20"><center><b>Treinamento</b></center></th>											
											<% if (user != null) {%>
											<!--<th width="10%" height="20" bgcolor="#999999"><b>Inscreva-se</b></th>
											--><%} %>
										</tr>	
							<%											
							try{
								int count=0;
								while(it.hasNext()){   			
									Treinamento lote = it.next();//lotes.get(itemCountLotes);
									if (lote.getTurma() != null) leilaoId = lote.getTurma().getId();
									count++;									
							%>	
										      		
								      			<TR bgcolor="<%=(count%2>0)?"#BBBBBB":"#CCCCCC" %>" onMouseOver="this.bgColor='AAAAAA';" onMouseOut="this.bgColor='<%=(count%2>0)?"#BBBBBB":"#CCCCCC" %>';">
													<td width="16px" height="16px"><img src="imgs/
											<%
												if(lote.getAgendaTreinamento().getStatus() == StatusAgenda.Confirmada)out.print("flag_green");
												else if(lote.getAgendaTreinamento().getStatus() == StatusAgenda.Cadastrada)out.print("flag_yellow");
												else out.print("flag_red");
											%>.png" title="<%
												if(lote.getAgendaTreinamento().getStatus() == StatusAgenda.Confirmada)out.print("Turma Confirmada!");
												else if(lote.getAgendaTreinamento().getStatus() == StatusAgenda.Cadastrada)out.print("Turma em planejamento! Fa&ccedil;a sua inscri&ccedil;&atilde;o");
												else out.print("Turma Cancelada!");
											%>"/></td>
													<TD >
														<center>
														<a id="open_agendaDialog<%=lote.getId()+random%>"
															 		title="Clique para listar detalhes da execu&ccedil;&atilde;o deste treinamento"
															 		class="aBlack" 
															 		style="cursor: hand; cursor: pointer;">
																	<Font color="Black">
															<binder:message code="format.date"  
																arguments="<%=lote.getAgendaTreinamento().getInicioPrevisto() %>"/> 
															<binder:message code="format.date"  
																arguments="<%=lote.getAgendaTreinamento().getTerminoPrevisto() %>"/>
															</Font></a>
															
														 </center>
													</TD>
													<TD >
														<a onclick="addTab('treinamentosTabbedPane<%=leilaoId %>','itens<%=lote.getCodigo() %><%=random%>','<%=lote.getTreinamento().getCodigo() %>','showDetalhesTreinamento.html?treinamento_id=<%=lote.getId()%>'); return false;"
															 		title="Clique para listar os detalhes deste treinamento"
															 		class="aBlack" 
															 		style="cursor: hand; cursor: pointer;">
														<Font color="Black">
															&nbsp;&nbsp;<%=lote.getTreinamento().getCodigo()%>
														</Font></a>														
													</TD>
													<TD >
														<center>
														<a onclick="addTab('treinamentosTabbedPane<%=leilaoId %>','itens<%=lote.getCodigo() %><%=random%>','<%=lote.getTreinamento().getCodigo() %>','showDetalhesTreinamento.html?treinamento_id=<%=lote.getId()%>'); return false;"
															 		title="Clique para listar os detalhes deste treinamento"
															 		class="aBlack" 
															 		style="cursor: hand; cursor: pointer;">
																	<Font color="Black">
																	<% if (lote.getTreinamento().getNome().length()>200)
																		 out.print(lote.getTreinamento().getNome().substring(0,200)+"...");
																		 else out.print(lote.getTreinamento().getNome()); %> 
																</Font></a></center>
														<center>

															<!--<a onclick="javascript:dijit.byId('descriptionDialog<%=lote.getId()+random%>').show(); return false;"
															 		title="Clique para listar a descri&ccedil;&atilde;o deste treinamento"
															 		class="aBlack" 
															 		style="cursor: hand; cursor: pointer;">
																	<Font color="Blue">Descri&ccedil;&atilde;o</Font></a> |
															 
															<a onclick="javascript:dijit.byId('imageDialog<%=lote.getId()+random%>').show(); return false;"
															 		title="Clique para listar a descri&ccedil;&atilde;o deste treinamento"
															 		class="aBlack" 
															 		style="cursor: hand; cursor: pointer;">
																	<Font color="Blue">Imagens</Font></a> | -->
															<a id="open_investimentoDialog<%=lote.getId()+random%>"
															 		title="Clique para listar a descri&ccedil;&atilde;o deste treinamento"
															 		class="aBlack" 
															 		style="cursor: hand; cursor: pointer;"> 
																	<Font color="Blue">Investimento</Font></a> | 
															<a id="open_detalhesTreinamentoDialog<%=lote.getId()+random%>"
															 		title="Clique para listar os detalhes deste treinamento"
															 		class="aBlack" 
															 		style="cursor: hand; cursor: pointer;">
																	<Font color="Blue">Saiba Mais</Font></a> 
															
														</center>
													</TD>
													<%														
													if(user != null){
													%>
													<!--<TD align="center"  >
														<div id="inscricaoResultDiv<%=user.getId()%><%=lote.getId()%>" dojoType="dijit.layout.ContentPane" style="padding:0px;" onUnload="overlay.hide();" cacheContent="false">		
														
															<INPUT type="checkbox"  
															name="participarTreinamentoCheckBox<%=lote.getId()%>2" 
															style="width:20px; height:20px" 
															onClick="jqueryOpen('participarTurmaTreinamento.html?usuario_id=<%=user.getId()%>&treinamento_id=<%=lote.getId()%>','inscricaoResultDiv<%=user.getId()%><%=lote.getId()%>'); return false;"
															<%if(lotesMarcados != null && lotesMarcados.contains(lote)){ %>checked="checked"<%} %>/>
														</div>
													</TD>
													--><%} %>
												</TR>							
								      		<%	}
								      		}catch(Exception ex){
								      			ex.printStackTrace();
								      		}
												
								      									
									%>
									</table>
	
	<%
			   	}
				
	%>
<%
    if (lotes != null && lotes.iterator().hasNext()){
    	
       	java.util.Iterator<Treinamento> lit = lotes.iterator();
	   	while (lit.hasNext()){
	   		Treinamento lote = lit.next();%>

	
	<div id="descriptionDialog<%=lote.getId()+random%>" 
		title="<%=lote.getTreinamento().getNome() %>">
		<%=
				(lote.getTreinamento().getUtilizarFormatador())?
				 lote.getTreinamento().getFormatedDescricao():
				lote.getTreinamento().getDescricao() %>		
		
	</div>
	
	<div id="investimentoDialog<%=lote.getId()+random%>" 
		title="<%=lote.getTreinamento().getNome() %>">
		Valor &agrave; vista com desconto de 10%: <%=Moeda.getValorReal(lote.getTreinamento().getAvaliacao() - lote.getTreinamento().getAvaliacao()*0.10f )%>
		<br>Parcelado em at&eacute; 3x: <%=Moeda.getValorReal(lote.getTreinamento().getAvaliacao() )%>
		<br>Parcelado em 12x com juros de 8% a.a.: <%=Moeda.getValorReal(lote.getTreinamento().getAvaliacao()*0.8f + lote.getTreinamento().getAvaliacao()) %>
	</div>

	<%try{
			if (lote.getAgendaTreinamento() != null){
		%>
	
	<div id="agendaDialog<%=lote.getId()+random%>" 
		title="Agenda do Treinamento <%=lote.getTreinamento().getNome() %>">
		Turno: <%=lote.getAgendaTreinamento().getTurno().name().replace("_"," ") %>
		<br>In&iacute;cio agendado para <binder:message code="format.date"  
		arguments="<%=lote.getAgendaTreinamento().getInicioPrevisto() %>"/>
		<br>Encerramento previsto para 
		<binder:message code="format.date"  
		arguments="<%=lote.getAgendaTreinamento().getTerminoPrevisto() %>"/> 
		<!--		
		<br><br>O treinamento inicia as <%= lote.getAgendaTreinamento().getHoraInicio()%> horas
		<br>E encerra as <%= lote.getAgendaTreinamento().getHoraInicio()%> horas
		<br><br>Intervalo: <%= lote.getAgendaTreinamento().getIntervalo()%>
		<br><br>Status do Treinamento: <%= lote.getAgendaTreinamento().getStatus().name().replace("_"," ")%>
		-->
		
	</div>
	
	<%		
			}	
		}catch(Exception ex){
	%>
	
	<div id="agendaDialog<%=lote.getId()+random%>" 
		title="Agenda do Treinamento <%=lote.getTreinamento().getNome() %>">
		<div id="agendaDialog<%=lote.getId()+random%>Div" >
					
		</div>
		<script> jqueryOpen('showAgendaTreinamento.html?treinamento_id=<%=lote.getId()%>','agendaDialog<%=lote.getId()+random%>Div'); </script>
	</div>
	<% 		
		} %>

	<div id="imageDialog<%=lote.getId()+random%>" 
		title="Imagens do Treinamento <%=lote.getTreinamento().getNome() %>">
		<div id="imageDialog<%=lote.getId()+random%>Div" >
					
		</div>
		<script> jqueryOpen('showImagensComercialSolution.html?produto_id=<%=lote.getTreinamento().getId()%>','imageDialog<%=lote.getId()+random%>Div'); </script>
	</div>
	
	<div id="detalhesTreinamentoDialog<%=lote.getId()+random%>" 
		title="Detalhes do Treinamento <%=lote.getTreinamento().getNome() %>" >
		<div id="detalhesTreinamentoDialog<%=lote.getId()+random%>Div" >
					
		</div>
		<script> jqueryOpen('showDetalhesTreinamento.html?treinamento_id=<%=lote.getId()%>','detalhesTreinamentoDialog<%=lote.getId()+random%>Div'); </script>
	</div>
			
<%
   	}
}
%>