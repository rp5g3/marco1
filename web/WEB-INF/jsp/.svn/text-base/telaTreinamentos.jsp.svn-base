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
				<center><b><%=(lang.equalsIgnoreCase("en")?"You are viewing the area Trainings":"Voc&ecirc; est&aacute; visualizando a &aacute;rea Treinamentos") %></b></center>
				</font>	
			</div>
		</div>
	</div>
<div style="clear:both; position=relative; padding-left:10px; padding-right:10px; padding-top:5px;" align="left">
<div style="position=relative;" align="left">	
	<table cellpadding="0" cellspacing="0" width="100%" height="50" style="background-color: transparent;">
	  	<tr>
			<td width="260px" valign="top">	
				<div style="padding-bottom: 10px;">				
					<div class="panel_title ui-corner-top">
							<center><Font class="font_title"><b><%=(lang.equalsIgnoreCase("en")?"Informations":"Informa&ccedil;&otilde;es") %></b></Font></center>											
					</div>
					<div class="panel_content" style="background-color: #FaFaFa">					 				
						<table border="0" cellpadding="0"
							cellspacing="2" width="256px"
							style="background-color: transparent; padding-top:5px; vertical-align: top; ">
							<%
								String vetinfo[] = {
									(lang.equalsIgnoreCase("en")?"About Trainings":"Sobre os Treinamentos"),									 
									(lang.equalsIgnoreCase("en")?"All Formations":"Todas as Forma&ccedil;&otilde;es"),
									(lang.equalsIgnoreCase("en")?"Schedulled Classes":"Turmas Agendadas"),
									(lang.equalsIgnoreCase("en")?"Contact":"Contato")};
								String urlinfo[] = {
												"showAboutTrainings.html",
												
												"showFormacoes.html",
												"showCatalogoTurmas.html",
												"institucional_contato_.html"};
										for(int i=0; i < vetinfo.length; i++){
										    String value = vetinfo[i];
										%>
									<tr onclick="jqueryOpen('<%=urlinfo[i]%>','treinamentoContentDiv'); return false;"
										bgcolor="#BBBBBB" 
										onMouseOver="this.bgColor='AAAAAA';" 
										onMouseOut="this.bgColor='#BBBBBB';">
										<td height="24px" style="cursor: pointer;" ><center>
										<Font color="Black"><b><%=value%></b></Font>
										</center>
										</td>
									</tr>
							<%}%>														
						</table>
					</div>
					<div class=" ui-corner-bottom" style="height: 10px; background-color:fafafa;  border-width: 1px; ">
										&nbsp;											
								</div>
				</div>
			
				<div style="padding-bottom: 10px;">				
					<div class="panel_title ui-corner-top">
							<center><Font class="font_title"><b><%=(lang.equalsIgnoreCase("en")?"Formations Filter":"Filtro de Forma&ccedil;&otilde;es") %></b></Font></center>											
					</div>
					<div class="panel_content" style="background-color: #FaFaFa">
							<div style="padding-top:5px; padding-left:2px; padding-right:2px;">		
								<%
									com.adapit.portal.entidades.Technologies tecvet[] = new com.adapit.portal.entidades.Technologies[]{
										com.adapit.portal.entidades.Technologies.Linguagem_de_Programação_Java,
										com.adapit.portal.entidades.Technologies.Modelagem_de_Processos_de_Negócios
								};//com.adapit.portal.entidades.Technologies.values();
									for(int i=0; i < tecvet.length; i++){
										String value = tecvet[i].name().replace("_"," ");
										String valueEn[] = {
												"Java Programming Language",
												"Business Process Modeling"
										};
										    //if(tecvet.length/2 == i) out.print("</td><td>");
								%>
									<div style="margin-bottom:2px;  width:100%; height:25px; background-color:#BBBBBB;
										cursor: pointer;"
										onclick="jqueryOpen('showFormacoes.html?tec=<%=tecvet[i].ordinal()%>','treinamentoContentDiv'); return false;"
										onMouseOver="this.style.backgroundColor='#AAAAAA'" 
										onMouseOut="this.style.backgroundColor='#BBBBBB'">
										<center>
										<a style="cursor: hand;	cursor: pointer;" 
											>
											<Font color="Black" size="2" face="Arial"><b>
											<%=(lang.equalsIgnoreCase("pt")?value:valueEn[i])%>
											</b></Font>
										</a></center>
										
									</div>
								<%}%>				
								
							</div>
							</div>
						<div class=" ui-corner-bottom" style="height: 10px; background-color:fafafa;  border-width: 1px; ">
										&nbsp;											
								</div>
			</div>
		</td>
		<td valign="top">
				<div id="treinamentoContentDiv" style="padding-bottom: 10px;">
				</div>	
				<script> jqueryOpen('<%=pageUrl %>','treinamentoContentDiv'); </script>												
			</td>						
		</tr>		
</table>
</div>
</div>
