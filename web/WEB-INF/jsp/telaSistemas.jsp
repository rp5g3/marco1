<%@page import="com.adapit.portal.entidades.*" %>
<script type="text/javascript">
function openUrl(url){
	window.location=url;
}
</script>
<%
	String prod = (String) request.getAttribute("product");
	String info = (String) request.getAttribute("information");
	String pageUrl = null;
	if (prod != null) pageUrl = prod;
	else if (info != null) pageUrl = info;
	java.util.List<SoftwareDomainInterest> list =
		(java.util.List<SoftwareDomainInterest>) request.getAttribute("domainList");	
	String lang = (String) request.getSession().getAttribute("lang");
	if(lang == null)
		lang = "pt";
%>
<div style="position:relative; background-color: transparent; vertical-align: top; padding: 10px;">				
		<div style="clear:both; position=relative; " align="left">
			<div class="panel_content ui-corner-all" style = "height: 30px; background-color: transparent;">
				<font style="font-family: arial; font-size: 18; color:red; border-top-width: 1px; ">
				<center><b><%=(lang.equalsIgnoreCase("en")?"You are viewing the area Software":"Voc&ecirc; est&aacute; visualizando a &aacute;rea Solu&ccedil;&otilde;es em Software") %></b></center>
				</font>	
			</div>
		</div>
	</div>
<div style="clear:both; position=relative; padding-left:10px; padding-right:10px; padding-top:5px;" align="left">
<div style="position=relative;" align="left">	
		<table cellpadding="0" cellspacing="0" width="100%" height="50" style="background-color: transparent;">
	  		<tr>
				<td  valign="top">
							
				<%if (list != null && list.size()>0){
					
					%>
					<div style="padding-bottom: 10px;">				
					<div class="panel_title ui-corner-top">
							<center><Font class="font_title"><b><%=(lang.equalsIgnoreCase("en")?"Softwares":"Confira Nossos Softwares") %></b></Font></center>											
					</div>
					<div class="panel_content" style="background-color: #FaFaFa">					
						<table border="0" cellpadding="0"
							cellspacing="2" width="256px"
							style="background-color: transparent; padding-top:5px; vertical-align: top; ">
							<tr >
										<td height="24px" ><center>
										<Font color="Black"><b>
										<%=(lang.equalsIgnoreCase("en")?
											("The software listed below were developed by the team of Adapit. Check out the packages available."):
											("Os softwares listados abaixo foram desenvolvidos pela equipe da Adapit. Confira os pacotes dispon&iacute;veis.")) %></b></Font>
										</center>
										<center><img src="imgs/down_set_1.png"></img></center>
										</td>
				
							</tr>
				
						</table>
					</div>
					<div class=" ui-corner-bottom" style="height: 10px; background-color:fafafa;  border-width: 0px; ">
										&nbsp;											
								</div>
				</div>
				<%
					for (SoftwareDomainInterest sd:list){
					%>
				<div style="padding-bottom: 10px;">				
					<div class="panel_title ui-corner-top" onMouseOver="TagToTip('SDISpan<%=sd.getId()%>') ">
							<center><Font class="font_title">
							
							<b style="cursor: help;" ><%=sd.getNome() %></b></Font>
							<span id="SDISpan<%=sd.getId()%>"  style="visibility: hidden; left: 0; top: 0; position: absolute;" >
								<table border="1" cellpadding="0"
								cellspacing="2" style="background-color: #AEAEAE; padding-top:5px; vertical-align: top; ">
									<tr><th width="400px"><%=(lang.equalsIgnoreCase("en")?"Description":"Descri&ccedil;&atilde;o") %> - <%=sd.getNome() %></th></tr>
									<tr><td><%=sd.getDescricao()!=null?sd.getDescricao():"" %></td></tr>
								</table>								
							</span>
							</center>											
					</div>
					<div class="panel_content" style="background-color: #FaFaFa">					
						<table border="0" cellpadding="0"
							cellspacing="2" width="256px"
							style="background-color: transparent; padding-top:5px; vertical-align: top; ">
							<tr>
								<td height="24px" style="cursor: help;"
								onMouseOver="TagToTip('SDISpan<%=sd.getId()%>') " ><center>
								<Font color="Black"><b><%=(lang.equalsIgnoreCase("en")?"Package Description":"Descri&ccedil;&atilde;o do Pacote") %></b></Font>
								</center>
								</td>
							</tr>
							<%
								if (sd.getSoftwares() != null && sd.getSoftwares().size()>0){
									String vetinfo3[] = new String[sd.getSoftwares().size()];
									String urlinfo3[] = new String[sd.getSoftwares().size()];
									String siglainfo3[] = new String[sd.getSoftwares().size()];
									int l=0;
									for (SoftwareSolution ss: sd.getSoftwares()){
										vetinfo3[l]= ss.getNome();
										urlinfo3[l]= "produtos.html?sigla="+ss.getSigla();
										siglainfo3[l] = ss.getSigla();
										l++;
									}
								
									for(int i=0; i < vetinfo3.length; i++){
										String value = vetinfo3[i];
									%>
									<tr onclick="openUrl('portal.html?sys=<%=siglainfo3[i] %>'); return false;"
										bgcolor="#BBBBBB"  
										onMouseOver="this.bgColor='AAAAAA';" 
										onMouseOut="this.bgColor='#BBBBBB';">
										<td height="24px" style="cursor: hand;	cursor: pointer;" >
										<center>
										<Font color="Black">
										<b>
										<a href="portal.html?sys=<%=siglainfo3[i]%>">
										<%=value%>
										</a>
										</b>
										</Font>
										</center>
										</td>
									</tr>
							<%	}
							}%>														
						</table>
					</div>
					<div class=" ui-corner-bottom" style="height: 10px; background-color:fafafa;  border-width: 1px; ">
										&nbsp;											
								</div>
				</div>
				<%	}//FIM do la&ccedil;o for
				%>
				
				<%
				}%>
				
				
				<div style="padding-bottom: 10px;">	
					<div class="panel_title ui-corner-top">
						<center><font class="font_title"><b><%=(lang.equalsIgnoreCase("en")?"Informations":"Informa&ccedil;&otilde;es") %></b></Font></center>											
					</div>
					<div class="panel_content" style="background-color: #FaFaFa">	
						<table border="0" cellpadding="0"
							cellspacing="2" width="256px"
							style="background-color: transparent; padding-top:5px; vertical-align: top; ">
							<%
								String vetinfo[] = {
									(lang.equalsIgnoreCase("en")?"Services":"Servi&ccedil;os"),									 
									(lang.equalsIgnoreCase("en")?"Differentiation":"Elementos de Diferencia&ccedil;&atilde;o"),
									(lang.equalsIgnoreCase("en")?"Production Process":"Processos de Produ&ccedil;&atilde;o"),
									(lang.equalsIgnoreCase("en")?"Contact":"Contato")};
								String urlinfo[] = {
												"showSoftware.html?information=softdema",
												"showSoftware.html?information=dif",
												"showSoftware.html?information=proc",												
												"institucional_contato_.html"};
										for(int i=0; i < vetinfo.length; i++){
										    String value = vetinfo[i];
										%>
									<tr onclick="jqueryOpen('<%=urlinfo[i]%>','softwareContentDiv'); return false;"
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
						<div class=" ui-corner-bottom" style="height: 10px; background-color:fafafa;  border-width: 1px; ">
										&nbsp;											
						</div>
				</div>
					
				<br>			
			</td>		
			<td valign="top">
			
				<div id="softwareContentDiv" style="padding-bottom: 10px;">
				</div>	
				<script> jqueryOpen('showSoftware.html?<%=prod!=null?"sigla":"information" %>=<%=pageUrl %>','softwareContentDiv'); </script>														
			</td>
						
		</tr>	
		
</table>

</div>

</div>
