<%@page import="com.adapit.portal.entidades.*" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="validation" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="binder" uri="http://www.springframework.org/tags"%>	

<%
	Imagem im = (Imagem) request.getAttribute("image");
	if(im != null){
%>
<br>
<center><img src="news_big_image.html?image_id=<%=im.getId()%>"">
<br><center><b><%=im.getRotulo()%></b></center><br>
<%	} %>
