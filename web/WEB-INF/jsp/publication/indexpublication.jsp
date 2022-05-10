<%@ taglib prefix="jstl" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="validation" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="binder" uri="http://www.springframework.org/tags"%>	
<%@page import="com.adapit.portal.entidades.*"%>
<%@page import="java.util.*"%>



	
<style>
<!-- /* Style Definitions */

div.publicationleftcorner{
	background-image: url(imgs/publicationleftcorner.png);
	background-repeat:no-repeat;
	width: 17px;
	height: 21px;
	border-color: black;
	border-right-width: 0px;
	border-bottom-width: 0px;
	border-top-width: 0px;
	border-left-width: 0px;
	float:left;
}
div.defaultbigtitledpane{
	background-image: url(imgs/menubk3.png);
	width: 740px;
	height: 21px;
	border-color: black;
	border-right-width: 1px;
	border-bottom-width: 0px;
	border-top-width: 0px;
	border-left-width: 0px;
	color: white;
	border-style: solid;
	font-family: Arial;
	font-size: 12px;
	valign: middle;
	float:left;
}
div.defaultbigcontentpane {
	background-color: transparent;
	border-bottom-color: black;
	border-bottom-width: 1px;
	border-left-color: black;
	border-left-width: 1px;
	border-right-color: black;
	border-right-width: 1px;
	border-top-color: black;
	border-top-width: 1px;
	border-style: solid;
	padding-bottom:5px;

	width:756px;
	clear:both;
}

div.defaultmediumcontentpane {
	background-color: transparent;
	border-bottom-color: black;
	border-bottom-width: 1px;
	border-left-color: black;
	border-left-width: 1px;
	border-right-color: black;
	border-right-width: 1px;
	border-top-color: black;
	border-top-width: 1px;
	border-style: solid;
	padding-bottom:5px;

	width:492px;
	clear:both;
}
div.defaultmediumtitledpane{
	background-image: url(imgs/menubk3.png);
	width: 476px;
	height: 21px;
	border-color: black;
	border-right-width: 1px;
	border-bottom-width: 0px;
	border-top-width: 0px;
	border-left-width: 0px;
	color: white;
	border-style: solid;
	font-family: Arial;
	font-size: 12px;
	valign: middle;
	float:left;
}
-->
</style>
	<div style="clear:both; position=relative; padding-left:10px; padding-top:5px;" align="left">	

					<div class="publicationleftcorner"></div>		
					<div class="defaultmediumtitledpane">
						<center><b>&Uacute;ltimas Not&iacute;cias</b></center>
											
					</div>
					<div class="defaultmediumcontentpane" >									
							<%
								List<Publication> list = (List<Publication>) request.getAttribute("publicationList");
								if (list != null && list.size()>0){
									java.util.Iterator<Publication> it = list.iterator();
									int contador=0;
									Publication firstPublication =null;
									while(it.hasNext()){
										Publication publication = it.next();
										if (contador == 0) firstPublication = publication;
							%>
							
									<div dojoType="dijit.TitlePane" title="<binder:message code="format.date" 
									 arguments="<%= publication.getDataPublicacao() %>"/> - <%= publication.getTitulo() %>" 
									 style="<%if(contador == 0){ %>padding:10px;<%}else{%>padding-left:10px; padding-right:10px; padding-bottom:10px;<%} %>" open="<%=contador==0%>">
									<!--<div class="publicationContentLeftImg" ></div>		-->
									<!--<div style="<%if(it.hasNext()){ %>border-bottom-color: gray; border-bottom-width: 1px; border-bottom-style: dashed;<%} %> padding:10px;">-->
										<br><b><binder:message code="format.date"  arguments="<%= publication.getDataPublicacao() %>"/> - 
										<i><%= publication.getTitulo() %></i></b>
										<p><%= publication.getResumo() %></p>
										<p><a href=""			
											onclick="adapitPost('publication.html?publicationid=<%= publication.getId() %>','contentDiv'); return false;"			
											style="cursor: hand; cursor: pointer;">Saiba mais ...</a></p>
									<!--</div>	-->					
									</div>
							
							<% 
									contador++;
									}
									%>
									<br><center><a href=""			
											onclick="adapitPost('publication.html?publicationid=<%= firstPublication.getId() %>','contentDiv'); return false;"			
											style="cursor: hand; cursor: pointer;">Todas as Publica&ccedil;&otilde;es</a></center>
											<br>
									<%
							}%>	
						
					</div>		
				

	</div>	