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
    Hashtable<String,List<Treinamento>> treinHtList = (Hashtable) request.getAttribute("treinamentosHtList");
    Vector<Treinamento> lotesMarcados = (Vector<Treinamento>) request.getAttribute("treinamentosMarcados");
    String random = IdGenerator.getInstance().generateId(3);
    UsuarioDTO user = (UsuarioDTO) request.getSession(true).getAttribute("user"); 
    Vector<Treinamento> lotes = new Vector<Treinamento>();
%>
 

    
    <%
    if (treinHtList == null || (!(treinHtList.size()>0))){
    	out.print("<center>Treinamentos em Defini&ccedil;&atilde;o.</center><center>Por favor, aguarde.</center>");
    }else{		
	   	java.util.Iterator<String> lit = treinHtList.keySet().iterator();
	   	int i=0;
	   	while (lit.hasNext()){	   		
	   		String datas = lit.next();
	   		List<Treinamento> treinamentos = treinHtList.get(datas);	   		
	%>
	   <div dojoType="dijit.TitlePane" 
			title="<%=datas %> - Treinamentos : <%=treinamentos.size() %>"					
				open="<%=(i==0)?"true":"false"%>">		
					<div class="containerPrincipaldiv" >
					<table border=0 cellpadding=0 cellspacing=0 bordercolor=#000000 width="100%" style="background-color:transparent;" >
							<!-- <tr>
								<th width="<%=(user != null)?"95":"100" %>%" bgcolor="#999999" height="20">
									<center><b>Treinamento</b></center>
								</th>											
							<% if (user != null) {%>
								<th width="10%" height="20" bgcolor="#999999"><b>S/N</b></th>
							<%} %>
							</tr> -->
					<%
						int count=0;
						java.util.Iterator<Treinamento> it= treinamentos.iterator();
						while(it.hasNext()){
							Treinamento lote = it.next();	
							lotes.add(lote);
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
								<td height="24px" onclick="javascript:dijit.byId('detalhesTreinamentoDialog<%=lote.getId()+random%>').show(); return false;">
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
								<%														
									if(user != null){
								%>
								<TD align="center"  >
									<div id="inscricaoResultDiv<%=user.getId()%><%=lote.getId()%>" dojoType="dijit.layout.ContentPane" style="padding:0px;" onUnload="overlay.hide();" cacheContent="false">		
										<INPUT type="checkbox"  title="Selecione para participar do treinamento"
											name="participarTreinamentoCheckBox<%=lote.getId()%>2" 
											style="width:20px; height:20px" 
											onClick="jqueryOpen('participarTurmaTreinamento.html?usuario_id=<%=user.getId()%>&treinamento_id=<%=lote.getId()%>','inscricaoResultDiv<%=user.getId()%><%=lote.getId()%>'); return false;"
											<%if(lotesMarcados != null && lotesMarcados.contains(lote)){ %>checked="checked"<%} %>/>
									</div>
								</TD>
								<%} %>
							</TR>
						<%} %>
						</table>							
					</div>
				</div>		
			
	<%			i++;		}
	   
	}	
	%>
<%
    if (lotes != null && lotes.iterator().hasNext()){
    	
       	java.util.Iterator<Treinamento> lit = lotes.iterator();
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