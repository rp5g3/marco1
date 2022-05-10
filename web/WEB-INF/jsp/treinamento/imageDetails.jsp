<%@page import="com.adapit.portal.entidades.*" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="validation" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="binder" uri="http://www.springframework.org/tags"%>

<%
	//TrainingSolution p = (TrainingSolution)
	//	request.getSession().getAttribute("treinamento");
	Imagem im = (Imagem) request.getSession().getAttribute("image");
	//int prev = (Integer) request.getAttribute("prev");
	//int next = (Integer) request.getAttribute("next");
%>

<table cellpadding="0" cellspacing="0" style="background-color: transparent;" width="100%">
	<tr>
		<td width="400px;" height="20px;">
			<center><br><%=im.getRotulo() %></b></center>
		</td>
	</tr>
	<tr>
		 <td width="400px;" height="300px;" style="vertical-align: middle">
		 	
			<div id="largeImageDiv<%=im.getId() %>" href="largeImage.html" style="padding:10px; overflow: inherit;">
				
			</div>
			<script> jqueryOpen('largeImage.html','largeImageDiv<%=im.getId() %>');</script>			
		</td>
	</tr>	

	<tr>
		<td width="600px;" height="80px;" style="border-color: #565656; border-width: 1px; text-align: justify; vertical-align: top;">
				<%=im.getDescription() %>
		</td>
	</tr>
</table>