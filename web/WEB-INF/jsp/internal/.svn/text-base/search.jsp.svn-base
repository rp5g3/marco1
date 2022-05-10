<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ taglib prefix="jstl" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="validation" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="binder" uri="http://www.springframework.org/tags"%>	
<%@page import="com.adapit.portal.entidades.*"%>
<%@page import="java.util.*"%>
<%@page import="com.workcase.utils.IdGenerator"%>
<%
	List<SoftwareSolution> solutions = (List<SoftwareSolution>) request.getAttribute("softwareSolutions");
	List<News> news = (List<News>) request.getAttribute("news");
	List<Publication> publications= (List<Publication>) request.getAttribute("publications");
	List<TrainingSolution> trainingSolutions = (List<TrainingSolution>) request.getAttribute("trainingSolutions");
	List<TechDefinition> definitions = (List<TechDefinition>) request.getAttribute("definitions");
	int i=1;
%>
<LINK rel="stylesheet" type="text/css" href="css/main.css"/>

<script type="text/javascript" src="js/jquery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery-ui-1.8.2.custom.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery.bgiframe-2.1.1.js"></script>
<style type="text/css">
   #just_dialog div button { font-size: 62.5%; }
</style>
<script type="text/javascript" src="js/wz_tooltip.js"></script>
<div style="clear:both; position=relative; padding-left:10px; padding-right:10px; padding-top:5px;" align="left">
	<div style="position=relative;" align="left">	
		<table cellpadding="0" cellspacing="0" width="100%" height="50" style="background-color: transparent;">
	  		<tr>
				<td width="100%" valign="top">	
					<div class="panel_title ui-corner-top" >
						<center><font class="font_title"><b><%=definitions==null||definitions.size()==0?"Nenhuma defini&ccedil;&atilde;o t&eacute;cnica encontrada":" Defini&ccedil;&otilde;es t&eacute;cnicas encontradas" %></b></Font></center>											
					</div>
					<div class="panel_content" style="background-color: #FaFaFa; ">	
						<table border="0" cellpadding="0"
							cellspacing="2" width="100%"
							style="background-color: transparent; padding-top:5px; vertical-align: top; ">
							<%
							if(definitions != null && definitions.size()>0){
								for(TechDefinition ss: definitions){
							%>
							<span id="TechDefinitionSpan<%=ss.getId()%>"  style="visibility: hidden; left: 0; top: 0; position: absolute; " >
								<div style="width: 500px;"><%=ss.getContent() %></div>
							</span>
							<tr bgcolor="<%=(i%2>0)?"#BBBBBB":"#CCCCCC" %>" 
										onMouseOver="this.bgColor='AAAAAA'; TagToTip('TechDefinitionSpan<%=ss.getId()%>');" 
										onMouseOut="this.bgColor='<%=(i%2>0)?"#BBBBBB":"#CCCCCC" %>';">
										<td height="24px" style="cursor: help;" ><center>
										<Font color="Black"><%=ss.getKeywords() %></Font>
										</center>
										</td>
										
									</tr>
							<%
								i++;
								}
							}
							%>														
						</table>
					</div>
					<br>			
				</td>						
			</tr>
	  		<tr>
				<td width="100%" valign="top">	
					<div class="panel_title ui-corner-top" >
						<center><font class="font_title"><b><%=news==null||news.size()==0?"Nenhuma Not&iacute;cia Encontrada":" Not&iacute;cias encontradas" %></b></Font></center>											
					</div>
					<div class="panel_content" style="background-color: #FaFaFa; ">	
						<table border="0" cellpadding="0"
							cellspacing="2" width="100%"
							style="background-color: transparent; padding-top:5px; vertical-align: top; ">
							<%
							if(news != null && news.size()>0){
								for(News ss: news){
							%>
							<tr onclick="jqueryOpen('news.html?newsid=<%=ss.getId()%>','contentDiv');"
							bgcolor="<%=(i%2>0)?"#BBBBBB":"#CCCCCC" %>" 
										onMouseOver="this.bgColor='AAAAAA';" 
										onMouseOut="this.bgColor='<%=(i%2>0)?"#BBBBBB":"#CCCCCC" %>';">
										<td height="24px" style="cursor: hand;	cursor: pointer;" ><center>
										<Font color="Black"><%=ss.getTitulo() %></Font>
										</center>
										</td>
									</tr>
							<%
								i++;
								}
							}
							%>														
						</table>
					</div>
					<br>			
				</td>						
			</tr>
			<tr>
				<td width="100%" valign="top">	
					<div class="panel_title ui-corner-top" >
						<center><font class="font_title"><b><%=publications==null||publications.size()==0?"Nenhuma Publica&ccedil;&atilde;o Encontrada":" Publica&ccedil;&otilde;es encontradas" %></b></Font></center>											
					</div>
					<div class="panel_content" style="background-color: #FaFaFa; ">	
						<table border="0" cellpadding="0"
							cellspacing="2" width="100%"
							style="background-color: transparent; padding-top:5px; vertical-align: top; ">
							<%
							if(publications != null && publications.size()>0){
								for(Publication ss: publications){
							%>
							<tr onclick="jqueryOpen('publication.html?publicationid=<%=ss.getId()%>','contentDiv');"
								bgcolor="<%=(i%2>0)?"#BBBBBB":"#CCCCCC" %>" 
										onMouseOver="this.bgColor='AAAAAA';" 
										onMouseOut="this.bgColor='<%=(i%2>0)?"#BBBBBB":"#CCCCCC" %>';">
										<td height="24px" style="cursor: hand;	cursor: pointer;" ><center>
										<Font color="Black"><%=ss.getTitulo() %></Font>
										</center>
										</td>
									</tr>
							<%
								i++;
								}
							}
							%>														
						</table>
					</div>
					<br>			
				</td>						
			</tr>
	  		<tr>
				<td width="100%" valign="top">	
					<div class="panel_title ui-corner-top" >
						<center><font class="font_title"><b><%=solutions==null||solutions.size()==0?"Nenhum Software Encontrado":
							" Softwares encontrados" %></b></Font></center>											
					</div>
					<div class="panel_content" style="background-color: #FaFaFa; ">	
						<table border="0" cellpadding="0"
							cellspacing="2" width="100%"
							style="background-color: transparent; padding-top:5px; vertical-align: top; ">
							<%
				 
							if(solutions != null && solutions.size()>0){
								for(SoftwareSolution ss: solutions){
							%>
							<tr bgcolor="<%=(i%2>0)?"#BBBBBB":"#CCCCCC" %>" 
										onMouseOver="this.bgColor='AAAAAA';" 
										onMouseOut="this.bgColor='<%=(i%2>0)?"#BBBBBB":"#CCCCCC" %>';">
										<td height="24px"  ><center>
										<a href="portal.html?sys=<%=ss.getSigla() %>"><Font color="Black"><%=ss.getNome() %></Font></a>
										</center>
										</td>
									</tr>
							<%
								i++;
								}
							}
							%>														
						</table>
					</div>
					<br>			
				</td>						
			</tr>
			<!-- 
			<tr>
				<td width="100%" valign="top">	
					<div class="panel_title ui-corner-top" >
						<center><font class="font_title"><b><%=trainingSolutions==null||trainingSolutions.size()==0?
								"Nenhum Treinamento Encontrado":" Treinamentos encontrados" %></b></Font></center>											
					</div>
					<div class="panel_content" style="background-color: #FaFaFa; ">	
						<table border="0" cellpadding="0"
							cellspacing="2" width="100%"
							style="background-color: transparent; padding-top:5px; vertical-align: top; ">
							<%
							if(trainingSolutions != null && trainingSolutions.size()>0){
								for(TrainingSolution ss: trainingSolutions){
							%>
							<tr onclick="jqueryOpen('news.html?newsid=<%=ss.getId()%>','contentDiv');" 
							bgcolor="<%=(i%2>0)?"#BBBBBB":"#CCCCCC" %>" 
										onMouseOver="this.bgColor='AAAAAA';" 
										onMouseOut="this.bgColor='<%=(i%2>0)?"#BBBBBB":"#CCCCCC" %>';">
										<td height="24px" style="cursor: hand;	cursor: pointer;" ><center>
										<Font color="Black"><%=ss.getNome() %></Font>
										</center>
										</td>
									</tr>
							<%
								i++;
								}
							}
							%>														
						</table>
					</div>
					<br>			
				</td>						
			</tr>
			 -->
					
		</table>
	</div>
</div>