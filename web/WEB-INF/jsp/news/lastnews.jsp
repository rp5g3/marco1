<%@ taglib prefix="jstl" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="validation" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="binder" uri="http://www.springframework.org/tags"%>	
<%@page import="com.adapit.portal.entidades.*"%>
<%@page import="java.util.*"%>

<%
	List<News> list = (List<News>) request.getAttribute("newsList");
	if (list != null && list.size()>0){
		for(News news : list){			
%>
	<br>	<Label
title="<%=news.getTitulo()%>"><font style="font-family: 'Arial',sans-serif; font-variant: small-caps; font-weight: bold; font-size: 12px; line-height: normal; font-size-adjust: none; font-stretch: normal; -x-system-font: none;">	
		<binder:message code="format.date"  arguments="<%= news.getDataPublicacao() %>"/>
	</font>
	<font style="font-family: 'Arial',sans-serif;   font-size: 11px; line-height: normal; ">
	<a href="" style="cursor: hand; cursor: pointer;"
           onclick="jqueryOpen('show_news_content.html?newsid=<%= news.getId() %>','contentNewsDiv'); return false;"
         title="<%=news.getTitulo()%>">
	<%
		if (news.getTitulo().length() > 24) out.print(news.getTitulo().substring(0,24)); 
		else out.print(news.getTitulo());%></a>
	</font></Label> 
	<br>&nbsp;
<% 

		}
	}else{%><br><center><font style="font-family: 'Arial',sans-serif;   font-size: 11px; line-height: normal; ">
Nenhuma Not&iacute;cia</font></center><br>	<%}%>	