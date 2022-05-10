<%@ taglib prefix="jstl" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="validation" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="binder" uri="http://www.springframework.org/tags"%>	
<%@page import="com.adapit.portal.entidades.*"%>
<%@page import="java.util.*"%>
<%
String lang = (String) request.getSession().getAttribute("lang");
if(lang == null)
	lang = "pt";
%>
<%
	List<Update> list = (List<Update>) request.getAttribute("updateList");
	String sigla = (String) request.getAttribute("sigla");
	if (list != null && list.size()>0){
		for(Update update : list){			
%>
	<br>	<Label
title="<%=update.getTitulo()%>"><font style="font-family: 'Arial',sans-serif; font-variant: small-caps; font-weight: bold; font-size: 12px; line-height: normal; font-size-adjust: none; font-stretch: normal; -x-system-font: none;">	
		<binder:message code="format.date"  arguments="<%= update.getDataPublicacao() %>"/>
	</font>
	<font style="font-family: 'Arial',sans-serif;   font-size: 11px; line-height: normal; ">
	<a href="" style="cursor: hand; cursor: pointer;"
           onclick="jqueryOpen('show_update_content.html?sigla=<%=sigla%>&updateid=<%= update.getId() %>','contentUpdateDiv'); return false;"
         title="<%=update.getTitulo()%>">
	<%
		if (update.getTitulo().length() > 24) out.print(update.getTitulo().substring(0,24)); 
		else out.print(update.getTitulo());%></a>
	</font></Label> 
	<br>&nbsp;
<% 

		}
	}else{%><br><center><font style="font-family: 'Arial',sans-serif;   font-size: 11px; line-height: normal; ">
<%=(lang.equalsIgnoreCase("en")?"No update available":"Nenhuma atualiza&ccedil;&atilde;o encontrada") %></font></center><br>	<%}%>	