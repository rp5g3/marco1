<%@ taglib prefix="jstl" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="validation" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="binder" uri="http://www.springframework.org/tags"%>	
<%@page import="com.adapit.portal.entidades.*"%>
<%@page import="java.util.*"%>

<%
	List<Publication> list = (List<Publication>) request.getAttribute("publicationList");
	if (list != null && list.size()>0){
		for(Publication publication : list){			
%>
	<br><Label
title="<%=publication.getTitulo()%>"><font style="font-family: 'Arial',sans-serif; font-variant: small-caps; font-weight: bold; font-size: 12px; line-height: normal; font-size-adjust: none; font-stretch: normal; -x-system-font: none;">	
		<binder:message code="format.date"  arguments="<%= publication.getDataPublicacao() %>"/>
	</font>
	<font style="font-family: 'Arial',sans-serif;   font-size: 11px; line-height: normal; ">
	<a href="" style="cursor: hand; cursor: pointer;"
           onclick="jqueryOpen('show_publication_content.html?publicationid=<%= publication.getId() %>','contentPublicationDiv'); return false;"
         title="<%=publication.getTitulo()%>">
	<%
		if (publication.getTitulo().length() > 24) out.print(publication.getTitulo().substring(0,24)); 
		else out.print(publication.getTitulo());%></a>
	</font></Label> 
	<br>&nbsp;
<% 

		}
	}else{%><br><center><font style="font-family: 'Arial',sans-serif;   font-size: 11px; line-height: normal; ">
Nenhum Artigo</font></center><br>	<%}%>