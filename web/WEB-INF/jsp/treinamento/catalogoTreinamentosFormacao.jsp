<%@ taglib prefix="jstl" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="validation" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="binder" uri="http://www.springframework.org/tags"%>	
<%@page import="com.adapit.portal.entidades.*"%>
<%@page import="java.util.*"%>

<%@page import="com.adapit.portal.util.global.GlobalVariables"%>
<%
GlobalVariables.countApresentacaoTab++;
%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="validation" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="binder" uri="http://www.springframework.org/tags"%>	
<%@page import="com.adapit.portal.entidades.*"%>
<%@page import="java.util.*"%>

<div style="clear:both; position=relative; padding-left:10px; " align="left">
	<div class="panel_title ui-corner-top">
		<font class="font_title"><b>&nbsp;&nbsp;<b>Lista dos Treinamentos por Forma&ccedil;&atilde;o</b>
							<div align="right"><%
							Integer paginate = (Integer) request.getAttribute("paginate");
							Integer pnumber = (Integer) request.getAttribute("pageNumber");
							if (paginate != null && paginate.intValue() > 0){
								for(int i=1; i <= paginate; i++){
							%>
								<%if (pnumber != null){ %>
									<%if(pnumber.intValue() == i) out.print(" "+i+" ");
									  else {%>
										<a href="" onclick="jquerydoPost('contentintro_.html?pageNumber=<%=i %>','leilaoContentDiv'); return false;"><%=i %></a>
									<%} %>
								<%}else{ %>
									<%if(i == 1) out.print(" 1 ");
									  else {%>
										<a href="" onclick="jquerydoPost('contentintro_.html?pageNumber=<%=i %>','leilaoContentDiv'); return false;"><%=i %></a>
									<%} %>
								<%} %>
							<%	}
							} %>
							</div></b></font>							
	</div>						
	<div class="panel_content ui-corner-bottom">	
							<%try{ %>					
								<%@include file="catalogoTrainingSolution.jsp" %>								
							<%}catch(Exception ex){ex.printStackTrace();} %>	
							<%try{ %>					
								<%@include file="formationDataTable.jsp" %>								
							<%}catch(Exception ex){ex.printStackTrace();} %>
							<%try{ %>
								<%@include file="treinamentosAgendadosTurma.jsp" %>								
							<%}catch(Exception ex){ex.printStackTrace();} %>						
	</div>
</div>
