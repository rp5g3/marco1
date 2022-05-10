<%@page import="com.adapit.portal.entidades.*" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="validation" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="binder" uri="http://www.springframework.org/tags"%>	
<%@page import="com.adapit.portal.dto.UsuarioDTO"%>
<%
	Update update = (Update) request.getAttribute("update");
	UsuarioDTO udto = (UsuarioDTO) request.getSession(true).getAttribute("user");
%>
<%
String lang12 = (String) request.getSession().getAttribute("lang");
if(lang12 == null)
	lang12 = "pt";
%>


<div style="clear:both; position=relative; padding-left:10px; padding-right:10px; padding-top:5px;" align="left">
						<div class="panel_title ui-corner-top">
							<font class="font_title"><b>&nbsp;&nbsp;<%if(update!=null){
String tit=update.getTitulo();
//if(tit.length()>45)
//tit=tit.substring(0,45)+" ...";
%><binder:message code="format.date"  arguments="<%= update.getDataPublicacao() %>"/>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=tit %>
										<%}else{%>
										<%=(lang12.equalsIgnoreCase("en")?"Select the update version":"Selecione a vers&atilde;o de atualiza&ccedil;&atilde;o") %><%}%></b>
							</font>
						</div>									
						<div class="panel_content ui-corner-bottom" style="padding:10px;">	
										<%if(udto != null && udto.hasAttribute(PersonAttributeType.Pode_alterar_update)){ %>
											<br><center><b><%=(lang12.equalsIgnoreCase("en")?"Update Id":"Id da Atualiza&ccedil;&atilde;o") %>:</b> <%=update.getId() %></center>
											<br></br>
											<center><%if(update != null){ %>
											<button id="editUpdateButton" style="  " 
												onClick="jqueryOpen('showUpdateMaintenanceForm.html<%=update!=null?"?update.id="+update.getId():"" %>','contentUpdateDiv'); return false;" >
												<%=(lang12.equalsIgnoreCase("en")?"Edit Update":"Editar a Atualiza&ccedil;&atilde;o") %>
											</button>
											<%} %>
											<button id="newUpdateButton" style="  " 
												onClick="jqueryOpen('showUpdateMaintenanceForm.html','contentUpdateDiv'); return false;" >
												<%=(lang12.equalsIgnoreCase("en")?"Add Update":"Adicionar uma Atualiza&ccedil;&atilde;o") %>
											</button>
											</center>
											<hr></hr>				
										<%}if(update != null){%>
											<%@include file="../emailmarketing/updates_content.jsp" %>
										<%}%>
										<%if(update != null && update.getImagens() != null && update.getImagens().size()>0){	
										%>
										<table border="0" cellpadding="0"
											cellspacing="0" style="background-color: transparent; vertical-align:top;"
											width="100%">
											<tr>
												<td width="100px;">
													<div class="panel_title_lev2 ui-corner-top">
														<font class="font_title">
														<center><%=(lang12.equalsIgnoreCase("en")?"Images":"Imagens") %></center></font>
													</div>
													<div class="panel_content ui-corner-bottom" style=" height: 300px; overflow: auto;">
														<center>
														
														<%														
															for(Imagem im : update.getImagens()){%>
																<div  class="ui-corner-all" style="padding: 2px; border:solid windowtext 1.0pt; border-color:silver">
																<img title="<%=im.getRotulo()%>" 
																    src="update_small_image.html?image_id=<%=im.getId()%>"
																    style="cursor: hand; cursor: pointer;"
														            onclick="jqueryOpen('show_update_image.html?image_id=<%= im.getId() %>','imageDivUpdate'); return false;"
														         ></div>
														<%	}
														  %>
														  </center>
													</div>													
												</td>
												<td width="100%" style=" padding-left: 10px;">
													<div class="panel_title_lev2 ui-corner-top">
														<font class="font_title">
														<center><%=(lang12.equalsIgnoreCase("en")?"Select the image":"Clique na imagem") %></center></font>
													</div>
													<div id="imageDivUpdate<%=update.getId() %>" class="panel_content ui-corner-all"
														style=" width=100%;  height: 300px; overflow: auto;">																		    				
														&nbsp;
													</div>
												</td>
											</tr>
										</table>	
										<%} %>									
						</div>
							
</div>