<%@page import="com.adapit.portal.entidades.*"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="validation" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="binder" uri="http://www.springframework.org/tags"%>
<%@page import="com.adapit.portal.entidades.FormacaoTreinamento"%>
   		
     <LINK rel="stylesheet" type="text/css" href="css/default.css"/>
     <LINK rel="stylesheet" type="text/css" href="css/jt_DialogBox.css"/>
     <LINK rel="stylesheet" type="text/css" href="css/leilao.css"/>  
	 <script type="text/javascript" src="js/leilao.js"></script> 
    
     <%
     FormacaoTreinamento formacao = (FormacaoTreinamento) request.getAttribute("formacao");
     if (formacao != null){
     %>

      
   	<script type="text/javascript" src="js/jt_DialogBox.js"></script>
   	<script type="text/javascript" src="js/MyApp_dialogs.js"></script>
   	<div style="position=relative; width=100%; padding:5px;">				
		<div dojoType="dijit.TitlePane" title="Dados da Forma&ccedil;&atilde;o <%=formacao.getNome()%>"
					 open="false">
			<table border=0 cellpadding=0 cellspacing=0 bordercolor=000000 class="presentation" width="100%">
				<tr>
					<%
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
								
							</tr>
			</table>						
		</div>										
	</div>
	<%} %>
      