<%@page import="com.adapit.portal.entidades.*" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="validation" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="binder" uri="http://www.springframework.org/tags"%>	
<%@page import="com.adapit.portal.dto.UsuarioDTO"%>
<%
	News news = (News) request.getAttribute("news");
	UsuarioDTO udto = (UsuarioDTO) request.getSession(true).getAttribute("user");
%>



<div style="clear:both; position=relative; padding-left:10px; padding-right:10px; padding-top:5px;" align="left">
						<div class="panel_title ui-corner-top">
							<font class="font_title"><b>&nbsp;&nbsp;<%if(news!=null){
String tit=news.getTitulo();
//if(tit.length()>45)
//tit=tit.substring(0,45)+" ...";
%><binder:message code="format.date"  arguments="<%= news.getDataPublicacao() %>"/>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=tit %>
										<%}else{%>Selecione a Not&iacute;cia<%}%></b>
							</font>
						</div>									
						<div class="panel_content ui-corner-bottom" style="padding:10px;">	
										<%if(udto != null && udto.hasAttribute(PersonAttributeType.Pode_alterar_news)){ %>
											<br><center><b>Identificador da Not&iacute;cia:</b> <%=news.getId() %></center>
											<br></br>
											<center><%if(news != null){ %>
											<button id="editNewsButton" style="  " 
												onClick="jqueryOpen('showNewsMaintenanceForm.html<%=news!=null?"?news.id="+news.getId():"" %>','contentNewsDiv'); return false;" >
												Editar a Not&iacute;cia
											</button>
											<%} %>
											<button id="newNewsButton" style="  " 
												onClick="jqueryOpen('showNewsMaintenanceForm.html','contentNewsDiv'); return false;" >
												Adicionar uma Not&iacute;cia
											</button>
											</center>
											<hr></hr>				
										<%}if(news != null){%>
											<h1><%=news.getTitulo() %></h1><br><br>
											<%=news.getDescricao() %><%}%>
										<%if(news != null && news.getImagens() != null && news.getImagens().size()>0){	
										%>
										<table border="0" cellpadding="0"
											cellspacing="0" style="background-color: transparent; vertical-align:top;"
											width="100%">
											<tr>
												<td width="100px;">
													<div class="panel_title_lev2 ui-corner-top">
														<font class="font_title"><center>Imagens</center></font>
													</div>
													<div class="panel_content ui-corner-bottom" style=" height: 300px; overflow: auto;">
														<center>
														
														<%														
															for(Imagem im : news.getImagens()){%>
																<div  class="ui-corner-all" style="padding: 2px; border:solid windowtext 1.0pt; border-color:silver">
																<img title="<%=im.getRotulo()%>" 
																    src="news_small_image.html?image_id=<%=im.getId()%>"
																    style="cursor: hand; cursor: pointer;"
														            onclick="jqueryOpen('show_news_image.html?image_id=<%= im.getId() %>','imageDivNews'); return false;"
														         ></div>
														<%	}
														  %>
														  </center>
													</div>													
												</td>
												<td width="100%" style=" padding-left: 10px;">
													<div class="panel_title_lev2 ui-corner-top">
														<font class="font_title"><center>Clique na Imagem da Esquerda para Mostr&aacute;-la em Tamanho M&eacute;dio</center></font>
													</div>
													<div id="imageDivNews<%=news.getId() %>" class="panel_content ui-corner-all"
														style=" width=100%;  height: 300px; overflow: auto;">																		    				
														&nbsp;
													</div>
												</td>
											</tr>
										</table>	
										<%} %>									
						</div>
							
</div>