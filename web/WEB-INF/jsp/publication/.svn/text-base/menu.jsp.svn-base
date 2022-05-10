<%@ taglib prefix="jstl" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="validation" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="binder" uri="http://www.springframework.org/tags"%>
<%@page import="com.adapit.portal.entidades.*"%>
<%@page import="java.util.*"%>


			<table width="285" border="0" cellspacing="0" cellpadding="0" bgcolor="#434343">
	    	  	<tr>
				    <td bgcolor="#434343" >
				    	<div class="glossymenu" > <font style="font-family: 'Arial',sans-serif; font-variant: small-caps; font-weight: bold; font-size: 9px; line-height: normal; font-size-adjust: none; font-stretch: normal; -x-system-font: none;">	
							<%
								List<Publication> list = (List<Publication>) request.getAttribute("publicationList");
								if (list != null && list.size()>0){
									for(Publication publication : list){			
							%>
								<a class="menuitem" style="cursor: hand;	cursor: pointer;" href=""
									onclick="adapitPost('show_publication_content.html?publicationid=<%= publication.getId() %>',contentPublicationDiv); return false;">
								<binder:message code="format.date"  arguments="<%= publication.getDataPublicacao() %>"/>
								<%
									if (publication.getTitulo().length() > 14) out.print(publication.getTitulo().substring(14)); 
									else out.print(publication.getTitulo());%></a>
							
							<% 
							
									}
								}%>			
							
							</font>									
						</div>			    
					</td>
				</tr>
				<tr>
					<td style="border-top:1px #959595 solid; height: 1px; background-color: #959595"></td>
				</tr>
			</table>
			

