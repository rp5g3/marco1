<%@page import="com.adapit.portal.entidades.*"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jstl/core"%>
<%@taglib prefix="validation" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@taglib prefix="binder" uri="http://www.springframework.org/tags"%>
<%@page import="com.adapit.portal.entidades.FormacaoTreinamento"%>
<%@page import="java.util.*" %>
   		
     <LINK rel="stylesheet" type="text/css" href="css/default.css"/>
     <LINK rel="stylesheet" type="text/css" href="css/jt_DialogBox.css"/>
     <LINK rel="stylesheet" type="text/css" href="css/leilao.css"/>  
	 <script type="text/javascript" src="js/leilao.js"></script> 
       <%
        	java.util.List<TurmaTreinamento> turmas = (java.util.List) request.getAttribute("turmas");      		
        %>

      
   		<script type="text/javascript" src="js/jt_DialogBox.js"></script>
   		<script type="text/javascript" src="js/MyApp_dialogs.js"></script>
   		
   		   		
      <FORM method="post" action="participarTreinamentos.html" >
      
      <%
      	int countControl=0;
      	int treinamentoCountControl=0;
      	java.util.Iterator<TurmaTreinamento> itturma = turmas.iterator();
      	if (!itturma.hasNext()) out.print("<Center>N&atilde;o foram encontradas turmas para a data corrente</Center>");
      			
      %>
		
			<div style="position=relative; width=100%; padding:5px;">				
			<%
				while(itturma.hasNext()){
			      	TurmaTreinamento turma = itturma.next();
					Integer id = new Integer(turma.getId());
		      		//request.removeAttribute("treinamentosList");
		      		//request.removeAttribute("turmaId");
		      		request.setAttribute("turmaId",id);
					request.setAttribute("treinamentosList",turma.getTreinamentos());
					
					%>
					
					<div class="panel_title_lev2 ui-corner-top" 
						style="padding-top:5px; font: normal 12px arial ; color: #FFFFFF; font-size: 12px;">
						<center>
							Turma 
						   <%=turma.getTipoExecucao().name().replace("_"," ") %> - 
						   de <binder:message code="format.date"  arguments="<%=turma.getDataTreinamento() %>"/> at&eacute;
						   <binder:message code="format.date"  arguments="<%=turma.getDataEncerramento() %>"/> -
						   <%=turma.getTurno().name().replace("_"," ")%>
						    
						</center>
					</div>			
					<div class="panel_content ui-corner-bottom" style="padding:10px; margin-bottom:10px;">
												
						<div id="treinamentosByTurmaDiv">
							
						</div>
						<script type="text/javascript">jqueryOpen('showTreinamentosByTurma.html?turma_id=<%=id %>','treinamentosByTurmaDiv');</script>
						<table border=0 cellpadding=0 cellspacing=0 bordercolor=000000 class="presentation" width="100%">
							<tr>
								<td valign="top">
									<a href="" onclick="jqueryOpen('showDetalhesInstrutor.html?turma_id=<%=turma.getId()%>','DetalhesInstrutorDiv'); return false">Informa&ccedil;&otilde;es do Instrutor</a>
									<div id="DetalhesInstrutorDiv">									   
									</div>
								</td>
							</tr>
							<tr>
								<td valign="top">
								<% if(!turma.getTipoExecucao().name().equalsIgnoreCase("virtual")) {%>
									<a href="" onclick="jqueryOpen('showDetalhesEndereco.html?turma_id=<%=turma.getId()%>','DetalhesEnderecoDiv'); return false;">
									Informa&ccedil;&otilde;es do Local de Realiza&ccedil;&atilde;o</a>
						            <div id="DetalhesEnderecoDiv" >									   
									</div>
								   
							    <%}%>						
							    </td>
							</tr>
						</table>
					</div>
						<%
						countControl++;
						treinamentoCountControl=0;
						
			      	}
			
			      	%>				
				</div>					
	      		   		
			
		
		
      	
      </FORM>
      