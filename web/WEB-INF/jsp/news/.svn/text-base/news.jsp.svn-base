<%@ taglib prefix="jstl" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="validation" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="binder" uri="http://www.springframework.org/tags"%>
<%
String lang = (String) request.getSession().getAttribute("lang");
if(lang == null)
	lang = "pt";
%>
<div style="position:relative; background-color: transparent; vertical-align: top; padding: 10px;">				
		<div style="clear:both; position=relative; " align="left">
			<div class="panel_content ui-corner-all" style = "height: 30px; background-color: transparent;">
				<font style="font-family: arial; font-size: 18; color:red; border-top-width: 1px; ">
				<center><b><%=(lang.equalsIgnoreCase("en")?"You are viewing the area News":"Voc&ecirc; est&aacute; visualizando a &aacute;rea Not&iacute;cias") %></b></center>
				</font>	
			</div>
		</div>
	</div>
<div style="position=relative;" align="left">	

	
		<table cellpadding="0" cellspacing="0" width="100%" height="50" 
			style="background-color: transparent;">
	  		<tr>
				<td width="260px" valign="top">			
		
		
		<div width="245px"  style=" background-color: transparent;
			vertical-align: top; padding-left:10px;" >

			<div style="padding-top: 5px;">				
				<div class="panel_title ui-corner-top">
					<center><Font class="font_title"><b>Not&iacute;cias</b></Font></center>											
				</div>
				<div class="panel_content" style="background-color: #FaFaFa">
					 <center>						
											<%
											
											java.util.Date dt = new java.util.Date();
											
											int ano = dt.getYear()+1900;
											for (int i=0; i < 4; i++){
											%>
											<a href="" onclick="jqueryOpen('news_ano.html?ano=<%=(ano-i)%>','newsContentDiv'); return false;"><%=(ano-i)%></a>
											<%	
												if (i >= 0 && i != 3) out.print("  ");												
											}
											%>
											<a href="" onclick="jqueryOpen('last_news.html','newsContentDiv'); return false;">Todos</a>
					</center>
				</div>
				<div id="newsContentDiv" class="panel_content" >																		    						
																												
				</div>
				<script> jqueryOpen('last_news.html?ano=<%=ano %>','newsContentDiv'); </script>
				<div class="panel_content ui-corner-bottom" style="background-color: #FaFaFa">
					<center>						
											<%
											
											
											for (int i=0; i < 4; i++){
											%>
											<a href="" onclick="jqueryOpen('news_ano.html?ano=<%=(ano-i)%>','newsContentDiv'); return false;"><%=(ano-i)%></a>
											<%	
												if (i >= 0 && i != 3) out.print("  ");												
											}
											%>
											<a href="" onclick="jqueryOpen('last_news.html','newsContentDiv'); return false;">Todos</a>
					</center>
					<div style="border-top: 1px dotted #999; "><center>						
											<%
											
											int begin=2005;
											int past=ano-4-begin;
											for (int i=past; i >= 0; i--){
											%>
											<a href="" onclick="jqueryOpen('news_ano.html?ano=<%=(begin+i)%>','newsContentDiv'); return false;"><%=(begin+i)%></a>
											<%	
												if (i >= 0 && i != 3) out.print("  ");												
											}
											%>
					</center></div>
				</div>	
			</div>				
		</div>
		<br>
		</td>
		<td valign="top">
				<div id="contentNewsDiv" style="padding-bottom:10px; overflow: inherit; width:100%">
					<%@include file="news_content.jsp" %>	
				</div>
				<br>
			</td>
		</tr>
		</table>	
	</div>
	