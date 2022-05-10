<%@ taglib prefix="jstl" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="validation" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="binder" uri="http://www.springframework.org/tags"%>
<%
String sigla = (String) request.getAttribute("sigla");
Integer csid = (Integer) request.getAttribute("csid");
%>
<%
String lang = (String) request.getSession().getAttribute("lang");
if(lang == null)
	lang = "pt";
%>

<div style="position:relative; background-color: transparent; vertical-align: top; padding: 10px;">				
		<div style="clear:both; position=relative; " align="left">
			<div class="panel_content ui-corner-all" style = "height: 40px; background-color: transparent;">
				<font style="font-family: arial; font-size: 18; color:red; border-top-width: 1px; ">
				<center><b><%=(lang.equalsIgnoreCase("en")?"You are viewing the area Software -> ":"Voc&ecirc; est&aacute; visualizando a &aacute;rea Solu&ccedil;&otilde;es em Software -> ") %><%=sigla.toUpperCase() %> -> <%=(lang.equalsIgnoreCase("en")?"Updates":"Atualiza&ccedil;&otilde;es") %></b></center>
				</font>
				&nbsp;&nbsp;&nbsp;&nbsp;<a href="http://www.adapit.com.br/portal.html?sys=<%=sigla%>" 
				class="white_link"><%=(lang.equalsIgnoreCase("en")?"Back":"Voltar") %></a>	
			</div>
		</div>
	</div>
	
<div style="position=relative;" align="left">	

	
		<table cellpadding="0" cellspacing="0" width="100%" height="50" 
			style="background-color: transparent;">
	  		<tr>
				<td width="260px" valign="top">			
		
		
		<div style=" background-color: transparent;
			vertical-align: top; padding-left:10px;" >

			<div style="padding-top: 5px;">				
				<div class="panel_title ui-corner-top">
					<center><Font class="font_title"><b><%=sigla.toUpperCase() %> - <%=(lang.equalsIgnoreCase("en")?"Updates":"Atualiza&ccedil;&otilde;es") %></b></Font></center>											
				</div>
				<div class="panel_content" style="background-color: #FaFaFa">
					 <center>						
											<%
											
											java.util.Date dt = new java.util.Date();
											
											int ano = dt.getYear()+1900;
											for (int i=0; i < 4; i++){
											%>
											<a href="" onclick="jqueryOpen('update_ano.html?csid=<%=csid%>&sigla=<%=sigla%>&ano=<%=(ano-i)%>','updateContentDiv'); return false;">
											<%=(ano-i)%></a>
											<%	
												if (i >= 0 && i != 3) out.print("  ");												
											}
											%>
											<a href="" onclick="jqueryOpen('last_update.html?csid=<%=csid%>&sigla=<%=sigla%>','updateContentDiv'); return false;">
											<%=(lang.equalsIgnoreCase("en")?"All":"Todos") %></a>
					</center>
				</div>
				<div id="updateContentDiv" class="panel_content" >																		    						
																												
				</div>
				<script> jqueryOpen('last_update.html?csid=<%=csid%>&sigla=<%=sigla%>&ano=<%=ano %>','updateContentDiv'); </script>
				<div class="panel_content ui-corner-bottom" style="background-color: #FaFaFa">
					<center>						
											<%
											
											
											for (int i=0; i < 4; i++){
											%>
											<a href="" onclick="jqueryOpen('update_ano.html?csid=<%=csid%>&sigla=<%=sigla%>&ano=<%=(ano-i)%>','updateContentDiv'); return false;">
											<%=(ano-i)%></a>
											<%	
												if (i >= 0 && i != 3) out.print("  ");												
											}
											%>
											<a href="" onclick="jqueryOpen('last_update.html?csid=<%=csid%>&sigla=<%=sigla%>','updateContentDiv'); return false;">
											<%=(lang.equalsIgnoreCase("en")?"All":"Todos") %></a>
					</center>
					<div style="border-top: 1px dotted #999; "><center>						
											<%
											
											int begin=2005;
											int past=ano-4-begin;
											for (int i=past; i >= 0; i--){
											%>
											<a href="" onclick="jqueryOpen('update_ano.html?csid=<%=csid%>&sigla=<%=sigla%>&ano=<%=(begin+i)%>','updateContentDiv'); return false;">
											<%=(begin+i)%></a>
											<%	
												if (i >= 0 && i != 3) out.print("  ");												
											}
											%>
					</center></div>
				</div>	
			</div>
			<br>
				<%List<SoftwareDomainInterest> domainList = (List<SoftwareDomainInterest>) request.getAttribute("domainList"); %>
				<div style="position=relative;" align="left">	
					<%if (domainList != null && domainList.size()>0){
								for (SoftwareDomainInterest sd:domainList){
								%>
							<div style="padding-top: 5px; padding-bottom: 10px;">				
								<div class="panel_title ui-corner-top">
										<center><Font class="font_title">
										<b><%=sd.getNome() %></b></Font></center>											
								</div>
								<div class="panel_content" style="background-color: #FaFaFa">					
									<table border="0" cellpadding="0"
										cellspacing="2" width="100%"
										style="background-color: transparent; padding-top:5px; vertical-align: top; ">
										
										<%
											if (sd.getSoftwares() != null && sd.getSoftwares().size()>0){
												String vetinfo3[] = new String[sd.getSoftwares().size()];
												String urlinfo3[] = new String[sd.getSoftwares().size()];
												int l=0;
												for (SoftwareSolution ss: sd.getSoftwares()){
													vetinfo3[l]= ss.getNome();
													urlinfo3[l]= "produtos.html?sigla="+ss.getSigla();
													l++;
												}
											
												for(int i=0; i < vetinfo3.length; i++){
													String value = vetinfo3[i];
												%>
												<tr onclick="jqueryOpen('<%=urlinfo3[i]%>','contentDiv'); return false;"
													bgcolor="#BBBBBB" 
													onMouseOver="this.bgColor='AAAAAA';" 
													onMouseOut="this.bgColor='#BBBBBB';">
													<td height="24px" style="cursor: pointer;" ><center>
													<Font color="Black"><b><%=value%></b></Font>
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
							<%	} 
							}%>
				</div>				
		</div>
		<br>
		</td>
		<td valign="top">
				<div id="contentUpdateDiv" style="padding-bottom:10px; overflow: inherit; width:100%">
					<%@include file="update_content.jsp" %>	
				</div>
				<br>
			</td>
		</tr>
		</table>	
	</div>
	