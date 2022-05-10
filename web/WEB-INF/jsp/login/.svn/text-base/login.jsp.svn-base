
<%@page import="com.adapit.portal.dto.UsuarioDTO"%><meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ taglib prefix="jstl" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="validation" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="binder" uri="http://www.springframework.org/tags"%>	
<%@page import="com.adapit.portal.entidades.*"%>
<%@page import="java.util.*"%>

<%@page import="com.workcase.utils.IdGenerator"%>

<LINK rel="stylesheet" type="text/css" href="css/main.css"/>

<script type="text/javascript" src="js/jquery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery-ui-1.8.2.custom.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery.bgiframe-2.1.1.js"></script>
<style type="text/css">
   #just_dialog div button { font-size: 62.5%; }
</style>

<script type="text/javascript" src="js/wz_tooltip.js"></script>

<%@page import="com.adapit.portal.entidades.*" %>
<%
UsuarioDTO udto = (UsuarioDTO) request.getSession(true).getAttribute("user");
%>
<div style="clear:both; position=relative; padding-left:10px; padding-right:10px; padding-top:5px;" align="left">
	<div style="position=relative;" align="left">	
		<table cellpadding="0" cellspacing="0" width="100%" height="50" style="background-color: transparent;">
	  		<tr>
				<td width="100%" valign="top">	
					<div class="panel_title ui-corner-top" >
						<center><font class="font_title"><b><%=udto==null?"Acesso ao Sistema":"Bem vindo ao sistema Adapit" %></b></Font></center>											
					</div>
					<div class="panel_content" style="background-color: #FaFaFa; padding:10px;">
						<%String msg = (String) request.getAttribute("message");
						if(udto == null){
							if(msg != null && msg.equals("error")) out.print("<h1>Usu&aacute;rio incorreto. Tente novamente</h1>");
						%>
						<center>
						<br></br>
						<FORM method="post" id="loginUsuarioForm" action="loginUsuario.html">
								<table>
								<tr>
								<td><Label>Login:</Label></td>
								<td><INPUT type="text" name="loginTextField" id="loginTextField" 
											onfocus="this.value = '';" value=""
											style="width:200px; height:25px" />
								</td>
								<br></br>
								</tr>
								<tr>
									<td><Label>Password:</Label></td>
									<td><INPUT type="password" name="passwordTextField"
											id="passwordTextField" 
											onfocus="this.value = '';" value=""
											style="width:200px; height:25px" />
									</td>
									<br></br>
								</tr>
								<tr> <td colspan="2"><center>	
								<!-- onclick="jquerydoPost('loginUsuarioForm','loginUsuario.html','contentDiv'); return false;" -->
								<button type="submit">
								Acessar</button></center>
								</td>
								</tr>
								</table>							
						</FORM>
						
						<%} else if(udto != null && udto.isAuthenticated() && udto.isActive()){
							
						%>
						Bem Vindo(a) <%=udto.getWelcomeName() %>
						<br></br>
						<br>Para editar os dados das Not&iacute;cias, clique no link Not&iacute;cias, abra a not&iacute;cia desejada e clique em editar.</br>
						<% if(udto.hasAttribute(PersonAttributeType.Pode_alterar_definicoes_tecnicas)){						
							%>
							<br></br>
							<a href="" 
							onclick="jqueryOpen('showListAllTechDefinitionView_TechDefinitionMaintenanceForm.html','contentDiv'); return false;">
							Cadastro de Defini&ccedil;&otilde;es T&eacute;cnicas</a>
							<%}
						%>
						<br></br><a href=""
					onClick="jqueryOpen('showParticipanteMaintenanceForm.html?participante.id=<%=udto.getParticipanteId() %>','contentDiv'); return false;">Editar os dados pessoais</a>
						<%} else if(udto != null && udto.isActive()){
							
						%>
							Voc&ecirc; precisa autenticar o seu usu&aacute;rio via email para ter acesso &agrave;s opera&ccedil;&otilde;es restritas &agrave; sua conta.
						<%} else if(udto != null && udto.isAuthenticated()){
							
						%>
							O seu acesso foi desativado do sistema. Contate a nossa ger&ecirc;ncia para avaliar a sua situa&ccedil;&atilde;o.
						<%} %>
						<%if(udto != null){ %>
						<br></br><a href="" target="_blanck"
						onClick="jqueryOpen('loginUsuario.html?init=false','contentDiv'); return false;">Sair do sistema</a>
						<%} %>
						</center>
						
					</div>
					<br>			
				</td>	
			</tr>		
		</table>
	</div>
</div>