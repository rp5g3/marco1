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
        	java.util.Hashtable<Integer,java.util.Vector<Treinamento>> treinamentosHashtable = (java.util.Hashtable<Integer,java.util.Vector<Treinamento>>) request.getAttribute("treinamentosTurma");
      		java.util.Vector<TurmaTreinamento> turmas = (java.util.Vector) request.getAttribute("turmas");
      		
        %>

      
   		<script type="text/javascript" src="js/jt_DialogBox.js"></script>
   		<script type="text/javascript" src="js/MyApp_dialogs.js"></script>
   		
   		   		
      <FORM method="post" action="participarTreinamentos.html" >
      
      <%
      	int countControl=0;
      	int treinamentoCountControl=0;
      	java.util.Iterator<TurmaTreinamento> itturma = turmas.iterator();
      	if (!itturma.hasNext()) out.print("<Center>N&atilde;o foram encontradas turmas para a data corrente</Center>");
      	while(itturma.hasNext()){
      		TurmaTreinamento turma = itturma.next();			
      %>
		
			<div style="position=relative; width=100%; padding:5px;">				
					<%
					Integer id = new Integer(turma.getId());
		      		request.removeAttribute("treinamentoList");
		      		request.removeAttribute("turmaId");
		      		request.setAttribute("turmaId",new Integer(turma.getId()));
					request.setAttribute("treinamentoList",treinamentosHashtable.get(id));
					%>
					
								
					<div >
						<label>Turma 
						   <%=turma.getTipoExecucao().name().replace("_"," ") %> - 
						   de <binder:message code="format.date"  arguments="<%=turma.getDataTreinamento() %>"/> at&eacute;
						   <binder:message code="format.date"  arguments="<%=turma.getDataEncerramento() %>"/> -
						   <%=turma.getTurno().name().replace("_"," ")%> 
						   </label>
						<table border=0 cellpadding=0 cellspacing=0 bordercolor=000000 class="presentation" width="100%">
							<tr>
								<td valign="top">
								   <table cellpadding="0" cellspacing="0" style="background-color:transparent;">
									<tr><th align="center" style="font-family: arial; font-size: 13; background-color:#969696">
										Instrutor<%if (((Fisica)turma.getInstrutor().getTipoPessoa()).getSexo() == Sexo.Feminino) out.print("a");%></th></tr>
									<tr><td style="font-family: arial; font-size: 11">
										<center><%=turma.getInstrutor().getNome()+" "+ ((Fisica)turma.getInstrutor().getTipoPessoa()).getSobrenome()%>
										</center></td>
									<tr><td style="font-family: arial; font-size: 11">
										<%=turma.getInstrutor().getApresentacao()%></td>								
								   </table>
								</td>
								<td valign="top">						<% if(!turma.getTipoExecucao().name().equalsIgnoreCase("virtual")) {%>
						                   <table cellpadding="0" cellspacing="0" style="background-color:transparent;">
									<tr><th colspan="4" align="center" style="font-family: arial; font-size: 13; background-color:#969696">Endere&ccedil;o:</th></tr>
									<tr>
										<td width="80px">
											<Label>Cidade: </Label>
										</td>
										<td style="font-family: arial; font-size: 11">
											<%=turma.getEnderecoPresencial().getCidade()%>
										</td>
										<td width="80px">
											<Label>Estado: </Label>
										</td>
										<td style="font-family: arial; font-size: 11">
											<%=turma.getEnderecoPresencial().getEstado()%>
										</td>
									</tr>
									<tr>
										<td width="80px">
											<Label>Bairro: </Label>
										</td>
										<td style="font-family: arial; font-size: 11" >
											<%=turma.getEnderecoPresencial().getBairro()%>
										</td>
										<td width="80px">
											<Label>Rua: </Label>
										</td>
										<td style="font-family: arial; font-size: 11" >
											<%=turma.getEnderecoPresencial().getRua()%>
										</td>
									</tr>
									<tr>
										<td width="80px">
											<Label>N&uacute;mero: </Label>
										</td>
										<td >
											<font style="font-family: arial; font-size: 11">
												<%=turma.getEnderecoPresencial().getNumero()%>
											</font>
										</td>
										<td width="80px">
											<Label>CEP: </Label>
										</td>
										<td >
											<font style="font-family: arial; font-size: 11">
												<%=turma.getEnderecoPresencial().getCep()%>
											</font>
										</td>
									</tr>
								</table>
							    <%}%>						
							    </td>
							</tr>
						</table>
						<%try{ %>
								<%@include file="treinamentosAgendadosInside.jsp" %>								
							<%}catch(Exception ex){ex.printStackTrace();} %>
					</div>
										
				</div>					
	      		   		
			
		
		<%
			countControl++;
			treinamentoCountControl=0;
      	}
      	%>
      	
      </FORM>
      