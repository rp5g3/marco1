<%
String title = (String) request.getAttribute("title");
String text = (String) request.getAttribute("text");
%>		
				<div class="panel_title_lev2 ui-corner-top">
					<font class="font_title"><b>&nbsp;&nbsp;<%=title %></b></font>
				</div>
				<div class="panel_content ui-corner-bottom" style="padding-left:10px;padding-right:10px;padding-bottom:10px;padding-top:10px;">
					<%if(text.equals("image")){ %>
					<%@include file="smallCustomImageSlideshow.jsp" %>
					<%} else if(text.equals("generalData")){ %>
					<%@include file="dadosGerais.jsp" %>
					<%} else{%>
					<table border=0 cellpadding=0 cellspacing=0 bordercolor=#000000 width="100%" style="background-color:transparent;" >
						<tr><td><%=text %></td></tr>
					</table>
					<%} %>
				</div>