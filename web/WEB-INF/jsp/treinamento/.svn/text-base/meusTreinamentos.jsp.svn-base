<%@page import="com.adapit.portal.entidades.*" %>
<%@page import="com.workcase.utils.*" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jstl/core"%>
<%@taglib prefix="validation" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@taglib prefix="binder" uri="http://www.springframework.org/tags"%>

<%@page import="com.workcase.utils.IdGenerator"%>
<%@page import="com.adapit.portal.dto.UsuarioDTO"%>
<%@page import="java.util.Vector"%>
<%@page import="java.util.Hashtable"%>
<%@page import="java.util.List"%>
<%@page import="com.adapit.portal.util.global.GlobalVariables"%>
	

<%	
    
    List<Treinamento> treinamentos = (List<Treinamento>) request.getAttribute("meusTreinamentos");
	Vector<Treinamento> lotesMarcados = (Vector<Treinamento>) request.getAttribute("treinamentosCondirmados");
    String random = IdGenerator.getInstance().generateId(3);
    UsuarioDTO user = (UsuarioDTO) request.getSession(true).getAttribute("user"); 
    Hashtable<Treinamento,ComercialSolutionItem> itens = ( Hashtable<Treinamento,ComercialSolutionItem>) request.getAttribute("itens");
%>    
    <%
    if (treinamentos == null || treinamentos.size()==0){
    	out.print("<center>Sem treinamentos selecionados</center>");
    }else{		
	   		   		
	%>
	   	<div class="containerPrincipaldiv" >
				<table cellpadding=0 cellspacing=1 bordercolor="#000000" width="100%" style="background-color:transparent; " >
					<tr>
						<th width="10%" height="20" bgcolor="#999999" 
							style="border-color: #000000; border-width: 1px;">
							<b>Status</b>
						</th>
						<th width="60%" bgcolor="#999999" height="20" 
							style="border-color: #000000; border-width: 1px;">
							<center><b>Treinamento</b></center>
						</th>
						<th width="15%" height="20" bgcolor="#999999" 
							style="border-color: #000000; border-width: 1px;" align="center">
							<center><b>Valor do Item</b></center>
						</th>
						<th width="15%" height="20" bgcolor="#999999" 
							style="border-color: #000000; border-width: 1px;">
							<center><b>Confirmar Item</b></center>
						</th>
					</tr>
					<%
						int count=0;
						java.util.Iterator<Treinamento> it= treinamentos.iterator();
						float investimento=0.0f;
						while(it.hasNext()){
							Treinamento lote = it.next();	
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
								<td height="24px">
									<center>
									<a onclick="javascript:dijit.byId('detalhesTreinamentoDialog<%=lote.getId()+random%>').show(); return false;"
															 		title="Clique para listar os detalhes deste treinamento"
															 		class="aBlack" 
															 		style="cursor: hand; cursor: pointer;">
									<Font color="Black">
								<% if (lote.getTreinamento().getNome().length()>200)
										out.print(lote.getTreinamento().getNome().substring(0,200)+"...");
									else out.print(lote.getTreinamento().getNome()); %> 
									</Font></a></center>
								</td>	
								<td height="24px">
									<center>									
										<Font color="Black">
										<%
											ComercialSolutionItem item = null;
											if (itens != null && itens.size()>0) item = itens.get(lote);
											float valor = 0.0f;
											if (item != null) valor = item.getValorAcertado();
											else valor = lote.getTreinamento().getAvaliacao();
											investimento+=valor;
											out.print(com.workcase.utils.Moeda.getValorReal(valor)); %> 
										</Font>
									</center>
								</td>												
								<%														
									if(user != null){
								%>
								<td align="center">
									<table cellpadding=0 cellspacing=0 bordercolor="#000000" width="100%" style="background-color:transparent; " >
										<tr>
											<TD align="center"  >
												<div id="inscricaoConfirmDiv<%=user.getId()%><%=lote.getId()%>" dojoType="dijit.layout.ContentPane" style="padding:0px;" onUnload="overlay.hide();" cacheContent="false">		
													<INPUT type="checkbox"  title="Confirme sua participa&ccedil;&atilde;o"
														name="confirmarTreinamentoCheckBox<%=lote.getId()%>" 
														style="width:16px; height:16px" 
														onClick="jqueryOpen('confirmarTurmaTreinamento.html?usuario_id=<%=user.getId()%>&treinamento_id=<%=lote.getId()%>','inscricaoConfirmDiv<%=user.getId()%><%=lote.getId()%>'); return false;"
														<%if(lotesMarcados != null && lotesMarcados.contains(lote)){ %>checked="checked"<%} %>/>
												</div>
											</TD>
											<TD align="center"  >
												<a onclick="jqueryOpen('removerInscricaoTreinamento.html?usuario_id=<%=user.getId()%>&treinamento_id=<%=lote.getId()%>','inscricaoConfirmDiv<%=user.getId()%><%=lote.getId()%>'); return false;"
													title="Clique para remover este treinamento"
													class="aBlack" 
													style="cursor: hand; cursor: pointer;">
													<img src="imgs/cart_delete.png"/>
												</a>									
											</TD>
										</tr>
									</table>
								</td>
								<%} %>
							</TR>
						<%} %>
							<TR bgcolor="#888888">
								<th align="right" colspan="5">
									<Font color="Black">
										Total: <%=com.workcase.utils.Moeda.getValorReal(investimento) %> 
									</Font>
								</th>
							</TR>
						</table>							
					</div>
				<!--</div>	
			
	--><%				
	
    	}
	   
	
	%>
<%
    if (treinamentos != null && treinamentos.iterator().hasNext()){
    	
       	java.util.Iterator<Treinamento> lit = treinamentos.iterator();
	   	while (lit.hasNext()){
	   		Treinamento lote = lit.next();%>

	<div id="detalhesTreinamentoDialog<%=lote.getId()+random%>" 
		dojoType="dijit.Dialog" title="Detalhes do Treinamento <%=lote.getTreinamento().getNome() %>" 
		style="display:none; width: 700px; height: 400px; top:50px;  overflow: inherit;" 
		bindArgs="{sync: true, preventCache: false}"
		href="showDetalhesTreinamento.html?treinamento_id=<%=lote.getId()%>"/>
			
<%
   	}
}
%>