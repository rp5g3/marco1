<%@page import="com.adapit.portal.entidades.*" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="validation" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="binder" uri="http://www.springframework.org/tags"%>	

<%
	Publication publication = (Publication) request.getAttribute("publication");
%>
<div style="clear:both; position=relative; padding-left:10px; padding-right:10px; padding-top:5px;" align="left">
						<div class="panel_title ui-corner-top">
							<font class="font_title"><b>&nbsp;&nbsp;
										<%if (publication ==null){%>Selecione o Artigo<%}else{
String tit=publication.getTitulo();
if(tit.length()>65)
tit=tit.substring(0,65)+" ...";
										%>
										
										<binder:message code="format.date"  arguments="<%= publication.getDataPublicacao() %>"/>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=tit %>
										<%}%></b>
									</font>
						</div>
						<div class="panel_content ui-corner-bottom" style="padding:10px;">						
										<%if (publication != null){%>

										<h1><%=publication.getTitulo() %></h1>
										<%try{if (publication.getEditalFile() != null && publication.getEditalFile().getId() != 0){%>
											<br><center>
											
												<a href="http://www.adapit.com.br/<binder:message code="webfilesurl"/><%=publication.getEditalFile().getName()%>"
													 target="blanck">
												<img src='imgs/<%
													if (publication.getEditalFile().getFormat().trim().equalsIgnoreCase("pdf"))
														out.print("page_white_acrobat");
													else if (publication.getEditalFile().getFormat().trim().equalsIgnoreCase("doc"))
														out.print("page_white_word");
													else if (publication.getEditalFile().getFormat().trim().equalsIgnoreCase("ppt"))
														out.print("page_white_powerpoint");
													else if (publication.getEditalFile().getFormat().trim().equalsIgnoreCase("xsl"))
														out.print("page_white_excel");
													else if (publication.getEditalFile().getFormat().trim().equalsIgnoreCase("tux"))
														out.print("page_white_tux");%>.png' width="16" height="16"
													 style="cursor: hand; cursor: pointer;"
													 title="Clique para fazer o download do arquivo"
													 border="0"/>
											<%
											if (publication.getEditalFile().getFormat().trim().equalsIgnoreCase("ppt")) out.print("Arquivo da Apresenta&ccedil;&atilde;o");
											else if (publication.getEditalFile().getFormat().trim().equalsIgnoreCase("xsl")) out.print("Arquivo");
											else out.print("Artigo Completo");
											%></a></center>
										<%}}catch(Exception ex){ex.printStackTrace();}%>
										<hr>
										<%=publication.getLocation() %>
										<%if (publication.getEditalFile() != null && (publication.getEditalFile().getFormat().trim().equalsIgnoreCase("doc")
												|| publication.getEditalFile().getFormat().trim().equalsIgnoreCase("pdf"))) {%>
										<!--<p>Qualis: <%=publication.getQualis() %>, M&iacute;dia: <%=publication.getMidia() %>, <%=publication.getClassification() %></p>
										--><%} %>
										<hr>
										<%=publication.getDescricao() %>
										<hr>										
										<%=publication.getResumo() %><%}%>
										<table border="0" cellpadding="0"
											cellspacing="0" style="background-color: transparent; vertical-align:top;"
											width="100%">
											<tr>
												<td width="100px;">
													<div dojoType="dijit.layout.ContentPane" 
													     bindArgs="{sync: true, preventCache: false}" 
													     style="position=relative; width=100%; height=100%; overflow: auto; padding-left:2px;">																		    				
														<%if(publication != null && publication.getImagens() != null && publication.getImagens().size()>0){															
															for(Imagem im : publication.getImagens()){%>
																<img title="<%=im.getRotulo()%>" 
																    src="publication_small_image.html?image_id=<%=im.getId()%>"
																    style="cursor: hand; cursor: pointer; padding-top:5px;"
														            onclick="adapitPost('show_publication_image.html?image_id=<%= im.getId() %>','imageDiv'); return false;"
														         ><br>
														<%	}
														  }%>
													</div>
												</td>
												<td width="450px;" style="border-width: 1px; border-color: blue;">
													<div id="imageDiv" onUnload="overlay.hide();"
														dojoType="dijit.layout.ContentPane" bindArgs="{sync: true, preventCache: false}" 
														style="position=relative; width=100%; height=100%; overflow: inherit;">																		    				
														&nbsp;
													</div>
												</td>
											</tr>
										</table>									
								
						</div>
</div>
