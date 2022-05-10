<%@page import="com.adapit.portal.entidades.*" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="validation" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="binder" uri="http://www.springframework.org/tags"%>
<style type="text/css">
a.linkopacity img {
filter:alpha(opacity=50); 
-moz-opacity: 0.5; 
opacity: 0.5;}

a.linkopacity:hover img {
filter:alpha(opacity=100);   
-moz-opacity: 1.0;   
opacity: 1.0;
}
</style>

<%
	TrainingSolution p = (TrainingSolution)
		request.getSession().getAttribute("treinamento");
	java.util.Date dt = new java.util.Date();
	String random = dt.getHours()+"_"+dt.getMinutes()+"_"+dt.getSeconds();
%>
<%if(p != null && p.getImagens() != null && p.getImagens().size()>0){	
										%>
										<table border="0" cellpadding="0"
											cellspacing="0" style="background-color: transparent; vertical-align:top;"
											width="100%">
											<tr>
												<td width="100px;">
													<div class="panel_title_lev2 ui-corner-top">
														<font class="font_title"><center>&nbsp;Imagens&nbsp;</center></font>
													</div>
													<div class="panel_content ui-corner-bottom" style=" height: 300px; overflow: auto;">
														<center>
														
														<%														
															for(Imagem im : p.getImagens()){%>
																<div  class="ui-corner-all" style="padding: 2px; border:solid windowtext 1.0pt; border-color:silver">
																<img title="<%=im.getRotulo()%>" 
																    src="news_small_image.html?image_id=<%=im.getId()%>"
																    style="cursor: hand; cursor: pointer;"
														            onclick="jqueryOpen('comercialSolutionBigImage.html?image_id=<%= im.getId() %>','imageDivTrein<%=random %>'); return false;"
														         ></div>
														<%	}
														  %>
														  </center>
													</div>													
												</td>
												<td width="100%" style=" padding-left: 10px;">
													<div class="panel_title_lev2 ui-corner-top">
														<font class="font_title"><center>Selecione a Imagem da Esquerda</center></font>
													</div>
													<div id="imageDivTrein<%=random %>" class="panel_content ui-corner-all"
														style=" width=100%;  height: 300px; overflow: auto;">																		    				
														&nbsp;
													</div>
												</td>
											</tr>
										</table>	
										<%} %>