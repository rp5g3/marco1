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
        	Instrutor instrutor = (Instrutor) request.getAttribute("instrutor");      	
       		Imagem im = (Imagem) instrutor.getLogotipo();	
       %>

    <script type="text/javascript" src="js/jt_DialogBox.js"></script>
   	<script type="text/javascript" src="js/MyApp_dialogs.js"></script>
   		
  <%@page import="com.adapit.portal.entidades.*" %>
<%
	
%>
 	
<table cellpadding="0" cellspacing="0" style="background-color:transparent;" width="100%">
	<tr>
		<th align="center" style="font-family: arial; font-size: 13; background-color:#969696" colspan="2">
		Instrutor<%if (((Fisica)instrutor.getTipoPessoa()).getSexo() == Sexo.Feminino) out.print("a");%>
		<%=instrutor.getNome()+" "+ ((Fisica)instrutor.getTipoPessoa()).getSobrenome()%>
		</th>
	</tr>
	<tr>
		<td width="90px;" height="90px;">
			<center>
				<%if (im != null){ %>
				<img src="comercialSolutionSmallImage.html?image_id=<%=im.getId()%>" alt="Carregando a Imagem ... Por favor, espere.">
				<%} else{%>
				<img src="imgs/customers.png" alt="Carregando a Imagem ... Por favor, espere.">
				<%} %>
			</center>
		</td>	
		<td style="font-family: arial; font-size: 11">
			<%=instrutor.getApresentacao()%>
		</td>
	</tr>								
</table>