<%@page import="com.adapit.portal.util.global.GlobalVariables"%>
<%
GlobalVariables.countApresentacaoTab++;
%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="validation" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="binder" uri="http://www.springframework.org/tags"%>	
<%@page import="com.adapit.portal.entidades.*"%>
<%@page import="java.util.*"%>
<% String tec = (String) request.getAttribute("tec");%>
<div style="clear:both; position=relative; padding-left:10px; " align="left">
	<div class="panel_title ui-corner-top">
		<font class="font_title"><b>&nbsp;&nbsp;Confira Nossas Forma&ccedil;&otilde;es <%=tec != null? (" - " + (Technologies.values()[Integer.parseInt(tec)]).name().replace("_"," ")):"" %></b></font>
	</div>						
	<div class="panel_content ui-corner-bottom">
							<%
							Integer paginate = (Integer) request.getAttribute("paginate");
							Integer pnumber = (Integer) request.getAttribute("pageNumber");
							%>
							<%
							if (paginate != null && paginate.intValue() > 0){
								out.print("<center>");
								for(int i=0; i <= paginate; i++){
							%>
								<%if (pnumber != null){ %>
									<%/*if(pnumber.intValue() == i) out.print(" "+(i+1)+" ");
									  else*/ {%>
										<a href="" style="cursor: pointer;" onclick="adapitPost('showFormacoes.html?pageNumber=<%=i %><%if (tec != null){%>&tec=<% out.print(tec);} %>','treinamentoContentDiv'); return false;"><%=(i+1) %></a>
									<%} %>
								<%}else{ %>
									<%if(i == 1) out.print(" 1 ");
									  else {%>
										<a href="" style="cursor: pointer;" onclick="adapitPost('contentintro_.html?pageNumber=<%=i %>','contentDiv'); return false;"><%=i %></a>
									<%} %>
								<%} %>
							<%	}
								out.print("</center>");
							} %>	
							<%@include file="formacoesListView.jsp" %>					
							
							<%
							if (paginate != null && paginate.intValue() > 0){
								out.print("<center>");
								for(int i=0; i <= paginate; i++){
							%>
								<%if (pnumber != null){ %>
									<%/*if(pnumber.intValue() == i) out.print(" "+(i+1)+" ");
									  else*/ {%>
										<a href="" style="cursor: pointer;" onclick="adapitPost('showFormacoes.html?pageNumber=<%=i %><%if (tec != null){%>&tec=<% out.print(tec);} %>','treinamentoContentDiv'); return false;"><%=(i+1) %></a>
									<%} %>
								<%}else{ %>
									<%if(i == 1) out.print(" 1 ");
									  else {%>
										<a href="" style="cursor: pointer;" onclick="adapitPost('contentintro_.html?pageNumber=<%=i %>','contentDiv'); return false;"><%=i %></a>
									<%} %>
								<%} %>
							<%	}
								out.print("</center>");
							} %>
	</div>
</div>