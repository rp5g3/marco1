<%@ taglib prefix="jstl" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="validation" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="binder" uri="http://www.springframework.org/tags"%>	
<%@page import="com.adapit.portal.entidades.*"%>
<%@page import="java.util.*"%>

<%@page import="com.workcase.utils.IdGenerator"%>
<%
	SoftwareSolution solution = (SoftwareSolution) request.getAttribute("software");
	
%>
										<table border=1 class="presentation" cellpadding=0 cellspacing=3 bordercolor=#000000 width="100%" style="background-color:transparent;" >
											<tr><td class="presentation">Nome do Sistema:</td><td class="presentation">&nbsp;<%=solution.getNome() %></td></tr>
											<tr><td class="presentation">Licen&ccedil;a de Uso:</td><td class="presentation"><%=solution.getLicencaUso() %></td></tr>
											<tr><td class="presentation">Palavras-Chave:</td><td class="presentation"><%=solution.getKeyWords() %></td></tr>
											<tr><td class="presentation">Plataforma:</td><td class="presentation"><%=solution.getPlataforma() %></td></tr>
											<tr><td class="presentation">Sistemas Operacionais:</td><td class="presentation"><%=solution.getSistemasOperacionais() %></td></tr>
											<tr><td class="presentation">URL do projeto:</td><td class="presentation"><%=solution.getUrlProjeto() %></td></tr>											
										</table>