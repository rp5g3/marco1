<%
String lang = (String) request.getSession().getAttribute("lang");
if(lang == null)
	lang = "pt";
%>
<%
	String pageUrl = (String) request.getAttribute("pageUrl");	
%>
<div style="position:relative; background-color: transparent; vertical-align: top; padding: 10px;">				
		<div style="clear:both; position=relative; " align="left">
			<div class="panel_content ui-corner-all" style = "height: 30px; background-color: transparent;">
				<font style="font-family: arial; font-size: 18; color:red; border-top-width: 1px; ">
				<center><b><%=(lang.equalsIgnoreCase("en")?"You are viewing the area About Us":"Voc&ecirc; est&aacute; visualizando a &aacute;rea Sobre N&oacute;s") %></b></center>
				</font>	
			</div>
		</div>
	</div>
<div style="clear:both; position=relative; padding-left:10px; padding-right:10px; padding-top:5px;" align="left">
	<div style="position=relative;" align="left">	
		<table cellpadding="0" cellspacing="0" width="100%" height="50" style="background-color: transparent;">
	  		<tr>
				<td width="260px" valign="top">	
					<div class="panel_title ui-corner-top">
						<center><font class="font_title"><b><%=(lang.equalsIgnoreCase("en")?"About Us":"Sobre N&oacute;s") %></b></center>
					</div>
					<div class="panel_content ui-corner-bottom">
						<div style="padding:10px;">
							<table border="0" cellpadding="0"
							cellspacing="2" width="256px"
							style="background-color: transparent; padding-top:5px; vertical-align: top; ">
									<%
										String vet[] = {
											(lang.equalsIgnoreCase("en")?"About Us":"Sobre N&oacute;s"),
											(lang.equalsIgnoreCase("en")?"Business":"Neg&oacute;cio"),
											(lang.equalsIgnoreCase("en")?"Location":"Localiza&ccedil;&atilde;o"),
											(lang.equalsIgnoreCase("en")?"Differentiation":"Diferencia&ccedil;&atilde;o"),
											(lang.equalsIgnoreCase("en")?"Solutions":"Solu&ccedil;&otilde;es"),
											(lang.equalsIgnoreCase("en")?"History":"Hist&oacute;rico"),
											(lang.equalsIgnoreCase("en")?"Technology":"Tecnologias"),
											(lang.equalsIgnoreCase("en")?"Development Process":"Processos"),
											(lang.equalsIgnoreCase("en")?"Contact":"Contato"),
											(lang.equalsIgnoreCase("en")?"Team":"Equipe"),
											(lang.equalsIgnoreCase("en")?"Partners":"Parceiras")};
										String url[] = {
												"institucional_apresentacao_.html",
												"institucional_empresa_.html",
												"institucional_localizacao_.html",
												"institucional_diferenciacao_.html",
												"institucional_solucoes_.html",
												"institucional_historico_.html",
												"institucional_tecnologias_.html",
												"institucional_processos_.html",
												"institucional_contato_.html",
												"institucional_equipe_.html",
												"institucional_parcerias_.html"};
										for(int i=0; i < 9; i++){
										    String value = vet[i];
										%>
									<tr onclick="jqueryOpen('<%=url[i]%>','institucionalContentDiv'); return false;"
										bgcolor="#BBBBBB" 
										onMouseOver="this.bgColor='AAAAAA';" 
										onMouseOut="this.bgColor='#BBBBBB';">
										<td height="24px" style="cursor: hand;	cursor: pointer;" ><center>
										<Font color="Black"><b><%=value%></b></Font>
										</center>
										</td>
									</tr>
										<%}%>							
																			
								</table>
						</div>
					</div>
				</td>
				<td valign="top">
					<div id="institucionalContentDiv" style="padding-bottom:10px; overflow: inherit; width:100%">
								
					</div>
					<script type="text/javascript"> jqueryOpen('<%=pageUrl %>','institucionalContentDiv');</script>
				</td>
			</tr>
		</table>	
</div>
</div>		