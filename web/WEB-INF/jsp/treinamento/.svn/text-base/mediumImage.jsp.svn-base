<%@page import="com.adapit.portal.entidades.*" %>
<%
	Imagem imdet = (Imagem) request.getSession().getAttribute("image");	
%>
<center>
	<a 	onclick="modalImageWin('fullImage.html?image_id=<%=imdet.getId()%>','Imagem image_id=<%=imdet.getRotulo()%>'); return false;"
											style="cursor: hand; cursor: pointer;">
											
	<img src="comercialSolutionMediumImage.html?image_id=<%=imdet.getId()%>" alt="Carregando a Imagem ... Por favor, espere." title="Clique para visualizar a imagem em tamanho original">
	</a>
</center>