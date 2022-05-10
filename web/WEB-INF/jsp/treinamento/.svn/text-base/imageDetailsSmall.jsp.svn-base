<%@page import="com.adapit.portal.entidades.*" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="validation" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="binder" uri="http://www.springframework.org/tags"%>

<%
	Imagem im = (Imagem) request.getSession().getAttribute("image");	
%>

<table cellpadding="0" cellspacing="0" style="background-color: transparent;" width="100%">
	<tr>
		<td width="300px;" height="20px;">
			<center><b><%=im.getRotulo() %></b></center>
		</td>
	</tr>
	<tr>
		 <td width="300px;" height="200px;" style="vertical-align: middle">
		 	<!--<div dojoType="dijit.layout.ContentPane" href="mediumImage.html" style="padding:10px; overflow: inherit;">
				<script type="dojo/connect" event="onLoad">
       							 		console.debug("onload de imagem... carregando dados");									
				</script>
			</div>-->
			<div id="largeImageDiv<%=im.getId() %>" style="padding:10px; overflow: inherit;">
				
			</div>
			<script> jqueryOpen('largeImage.html','largeImageDiv<%=im.getId() %>');</script>		
		</td>
	</tr>
	<tr>
		<td width="300px;" height="80px;" style="border-color: #565656; border-width: 1px; text-align: justify; vertical-align: top;">
				<%=im.getDescription() %>
		</td>
	</tr>
</table>