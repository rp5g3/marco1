
<%@page import="com.adapit.portal.entidades.Participante"%>
<%@page import="java.util.List"%>


<%@page import="com.adapit.portal.entidades.Imagem"%><div width="100%" style="position:relative; background-color: transparent; vertical-align: top; padding: 10px;">				
		<div style="clear:both; position=relative; " align="left">	

							
					<div class="panel_title ui-corner-top">
						<center><b>
							<font class="font_title">
						<%=request.getAttribute("attType") %></font></b></center>
											
					</div>
					<div class="panel_content ui-corner-bottom" style = "padding:10px;">
		

						<div style="padding:10px;">
											
<%
List<Participante> arr = (List<Participante>) request.getAttribute("personList");
if(arr != null && arr.size()>0){
	int i=0;
	for(Participante part: arr){
		i++;
		Imagem im = (Imagem) part.getLogotipo();
		//out.print("<br>"+part.getNomeFormatado());
%>
		<div class="ui-corner-all" style="background-color:<%=(i%2>0)?"#BBBBBB":"#CCCCCC" %>; margin-top:10px;">
			<table border="0" cellpadding="0" width="100%"
									cellspacing="0" 
									style="background-color: transparent; vertical-align: top; ">		
				<tr>
									<td width="90px;" style="padding: 10px;" >
										<%if (im != null){ %>
										<img src="comercialSolutionSmallImage.html?image_id=<%=im.getId()%>" alt="Carregando a Imagem ... Por favor, espere.">
										<%} else{%>
										<img src="imgs/customers.png" alt="Carregando a Imagem ... Por favor, espere.">
										<%} %>
									</td>
									<td height="24px" style="padding: 10px;" >
										<center>
										<Font color="Black"><b><%=part.getNomeFormatado()%></b></Font>
										</center>
										<br><%=part.getApresentacao() %></br>
									</td>
				</tr>
			</table>							
		</div>
<%
	}
}
%>

						</div>

				</div>
		</div>
						
	</div>	
	<br>