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
        	Endereco ender = (Endereco) request.getAttribute("endereco");  	
       %>

    <script type="text/javascript" src="js/jt_DialogBox.js"></script>
   	<script type="text/javascript" src="js/MyApp_dialogs.js"></script>
   		
  <%@page import="com.adapit.portal.entidades.*" %>
<%
	
%>
 <table cellpadding="0" cellspacing="0" style="background-color:transparent;">
									<tr><th colspan="4" align="center" style="font-family: arial; font-size: 13; background-color:#969696">Endere&ccedil;o:</th></tr>
									<tr>
										<td width="80px">
											<Label>Cidade: </Label>
										</td>
										<td style="font-family: arial; font-size: 11">
											<%=ender.getCidade()%>
										</td>
										<td width="80px">
											<Label>Estado: </Label>
										</td>
										<td style="font-family: arial; font-size: 11">
											<%=ender.getEstado()%>
										</td>
									</tr>
									<tr>
										<td width="80px">
											<Label>Bairro: </Label>
										</td>
										<td style="font-family: arial; font-size: 11" >
											<%=ender.getBairro()%>
										</td>
										<td width="80px">
											<Label>Rua: </Label>
										</td>
										<td style="font-family: arial; font-size: 11" >
											<%=ender.getRua()%>
										</td>
									</tr>
									<tr>
										<td width="80px">
											<Label>N&uacute;mero: </Label>
										</td>
										<td >
											<font style="font-family: arial; font-size: 11">
												<%=ender.getNumero()%>
											</font>
										</td>
										<td width="80px">
											<Label>CEP: </Label>
										</td>
										<td >
											<font style="font-family: arial; font-size: 11">
												<%=ender.getCep()%>
											</font>
										</td>
									</tr>
								</table>