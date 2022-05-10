<%@page import="com.adapit.portal.entidades.*"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="validation" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="binder" uri="http://www.springframework.org/tags"%>
<%@page import="com.adapit.portal.entidades.FormacaoTreinamento"%>
   		
     <LINK rel="stylesheet" type="text/css" href="css/default.css"/>
     <LINK rel="stylesheet" type="text/css" href="css/jt_DialogBox.css"/>
     <LINK rel="stylesheet" type="text/css" href="css/leilao.css"/>  
	 <script type="text/javascript" src="js/leilao.js"></script> 
     <style type="text/css">
        <!--
        <%
        	java.util.Hashtable<Integer,java.util.Vector<Treinamento>> treinamentosHashtable = (java.util.Hashtable<Integer,java.util.Vector<Treinamento>>) request.getAttribute("treinamentosTurma");
      		java.util.Vector<TurmaTreinamento> turmas = (java.util.Vector) request.getAttribute("turmas");
      		int containerPrincipalWidth=com.adapit.portal.util.global.GlobalVariables.ContainerPrincipalWidth;
        	int containerPrincipalHeight=com.adapit.portal.util.global.GlobalVariables.ContainerPrincipalHeight;
        	int maxRowNumber=com.adapit.portal.util.global.GlobalVariables.MaxTableRowNumber;
        %>

		div.dadosLeilaoPaneldiv{
			position:absolute;
			width:<%=containerPrincipalWidth+22%>px;  height:90px;
			left:0; top:40;
		}

		div.leilaoPaneldiv{
			position:absolute;
			width:<%=containerPrincipalWidth+20%>px; height:100px;
			left:0; top:0;
		}

		
		
		div.treinamentoCatalogoPaneldiv{
				position:absolute;
				width:<%=(containerPrincipalWidth+10)%>px; height:<%=(containerPrincipalHeight+200)%>px;
				left:0; top:150;							
		}
		
		div.leilaotitledpanel{
			position:absolute;
			width:<%=(containerPrincipalWidth)%>px; height:40px;
			left:0; top:0;
			background-color: #FFFFFE;
		}
		
		div.imageHouseDiv{
			position:relative;
			width:20px; height:20px;
			left:10; top:10;
		}
		
        -->
      </style>
      
   		<script type="text/javascript" src="js/jt_DialogBox.js"></script>
   		<script type="text/javascript" src="js/MyApp_dialogs.js"></script>
   		
   		   		
      <FORM method="post" action="participarTreinamentos.html" >
      
      <%
      	int countControl=0;
      	int treinamentoCountControl=0;
      	java.util.Iterator<TurmaTreinamento> it = turmas.iterator();
      	if (!turmas.iterator().hasNext()) out.print("<Center>N&atilde;o foram encontradas turmas para a data corrente</Center>");
      	while(it.hasNext()){
      		TurmaTreinamento turma = it.next();			
      %>
		
			<div style="position=relative; width=100%; padding:5px;">				
					<%
					Integer id = new Integer(turma.getId());
		      		request.removeAttribute("treinamentoList");
		      		request.removeAttribute("turmaId");
		      		request.setAttribute("turmaId",new Integer(turma.getId()));
					request.setAttribute("treinamentoList",treinamentosHashtable.get(id));
					%>
					<jsp:include page="/treinamentosEmTurma.html"/>
								
					<div dojoType="dijit.TitlePane" title="Turma 
						   <%=turma.getTipoExecucao().name().replace("_"," ") %> - 
						   de <binder:message code="format.date"  arguments="<%=turma.getDataTreinamento() %>"/> at&eacute;
						   <binder:message code="format.date"  arguments="<%=turma.getDataEncerramento() %>"/> -
						   <%=turma.getTurno().name().replace("_"," ")%> 
						   "
					 open="false">
						
						<table border=0 cellpadding=0 cellspacing=0 bordercolor=000000 class="presentation" width="<%=(containerPrincipalWidth)%>px">
							<tr>
								<% FormacaoTreinamento formacao = (FormacaoTreinamento) request.getAttribute("formacao");
									if (formacao != null){
								%>
								<th colspan="2" align="center" style="font-family: arial; font-size: 13; background-color:#969696">Forma&ccedil;&atilde;o <%=formacao.getNome()%></th>
								<%}else{%>
								<th colspan="2" align="center" style="font-family: arial; font-size: 13; background-color:#969696">Condi&ccedil;&otilde;es de Participa&ccedil;&atilde;o</th>
								<%}%>
							</tr>
							<tr>
								<%
									if(formacao != null){
									   Object[] objs = new Object[5];
									   objs[1]=formacao.getTechnology().name().replace("_", " ");
									   objs[2]=formacao.getCargaHorariaTotal();
									   objs[3]=com.workcase.utils.Moeda.getValorReal(formacao.getAvaliacao()); 
									   objs[4]=formacao.getDescricao();
									%>
								<td colspan="2" style="font-family: arial; font-size: 11">
									<table cellpadding="0" cellspacing="0" style="border-bottom-width:1px; border-bottom-color:#444444; background-color:transparent;">
														<tr>
															<td width="100px" style="background-color:transparent;" valign="top">
																<Label>Tecnologia: </Label>
															</td>
															<td valign="top" style="font-family: arial; font-size: 11" colspan="3" style="background-color:transparent;" valign="top" >
																<%=objs[1]%>
															</td>
														</tr>
														<tr>
															<td width="100px" style="background-color:transparent;" valign="top">
																<Label>Carga Hor&aacute;ria: </Label>
															</td>
															<td valign="top" style="font-family: arial; font-size: 11" style="background-color:transparent;">
																<%=objs[2]%> horas de treinamento
															</td>														
															<td width="100px" style="background-color:transparent;" valign="top">
																<Label>Invetimento: </Label>
															</td>
															<td valign="top" style="font-family: arial; font-size: 11">
																<%=objs[3]%>
															</td>
														</tr>
														<tr>
															<td width="100px" style="background-color:transparent;" valign="top">
																<Label>Objetivo: </Label>
															</td>
															<td valign="top" style="font-family: arial; font-size: 11" colspan="3" style="background-color:transparent;">
																<%=objs[4]%>
															</td>
														</tr>
									</table>
								   </td>
								<%}else{%>
									<td colspan="2" style="font-family: arial; font-size: 11"><div style="position: relative; overflow:auto;"><%=turma.getCondicaoParticipacao().getTexto()%></div></td>
								<%}%>
							</tr>							
							
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
						
					</div>
										
				</div>					
	      		   		
			
		
		<%
			countControl++;
			treinamentoCountControl=0;
      	}
      	%>
      	
      </FORM>
      