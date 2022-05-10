
<%@page import="com.workcase.utils.DatePropertyEditor"%><meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ taglib prefix="jstl" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="validation" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="binder" uri="http://www.springframework.org/tags"%>	
<%@page import="com.adapit.portal.entidades.*"%>
<%@page import="java.util.*"%>
<%
String lang = (String) request.getSession().getAttribute("lang");
if(lang == null)
	lang = "pt";
%>
<%@page import="com.workcase.utils.IdGenerator"%>
<%
SoftwareSolution solution = (SoftwareSolution) request.getAttribute("software");
String tabStr = (String) request.getSession(true).getAttribute("tab");
String tabArray[]=null;
if (solution != null){
	java.util.List<CommercialSolutionDetailTab> tabList = (java.util.List<CommercialSolutionDetailTab>)
		request.getAttribute("tabList");
	tabArray = new String[tabList.size()+4];
	String randomdet = IdGenerator.getInstance().generateId(3);
	
%>
<LINK rel="stylesheet" type="text/css" href="css/main.css"/>

<script type="text/javascript" src="js/jquery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery-ui-1.8.2.custom.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery.bgiframe-2.1.1.js"></script>
<style type="text/css">
   #just_dialog div button { font-size: 62.5%; }
</style>

<script type="text/javascript" src="js/wz_tooltip.js"></script>
<script type="text/javascript">
function openUrl(url){
	window.location=url;
}
</script>
<%@page import="com.adapit.portal.entidades.*" %>
<%
CommercialSolutionDetailTab headerTab = (CommercialSolutionDetailTab) request.getAttribute("headerTab");
CommercialSolutionDetailTab bottomTab = (CommercialSolutionDetailTab) request.getAttribute("bottomTab");
%>

<div style="position:relative; background-color: transparent; vertical-align: top; padding: 10px;">				
		<div style="clear:both; position=relative; " align="left">
			<div class="panel_content ui-corner-all" style = "height: 40px; background-color: transparent;">
				<font style="font-family: arial; font-size: 18; color:red; border-top-width: 1px; ">
				<center><b><%=(lang.equalsIgnoreCase("en")?"You are viewing the area Software -> ":"Voc&ecirc; est&aacute; visualizando a &aacute;rea Solu&ccedil;&otilde;es em Software -> ") %><%=solution.getNome() %></b></center>
				</font>
				&nbsp;&nbsp;&nbsp;&nbsp;<a class="white_link" href=""  onClick="jqueryOpen('produtos.html?information=softdema','contentDiv'); return false;"><%=(lang.equalsIgnoreCase("en")?"Back":"Voltar") %></a>	
			</div>
			
		</div>
	</div>	

<%=headerTab!=null?headerTab.getDetail():"" %>
<div style="clear:both; position=relative; padding-left:10px; padding-right:10px; padding-top:5px;" align="left">
<div style="position=relative;" align="left">	
		<table cellpadding="0" cellspacing="0" width="100%" height="50" style="background-color: transparent;">
	  		<tr>
				<td width="245px" valign="top">
				<div>	
					<div class="panel_title ui-corner-top" >
						<center><font class="font_title"><b><%=solution.getNome() %></b></Font></center>											
					</div>
					<div class="panel_content" style="background-color: #FaFaFa; ">	
						<table border="0" cellpadding="0"
							cellspacing="2" width="100%"
							style="background-color: transparent; padding-top:5px; vertical-align: top; ">
							<%
							int i=1;
							int incr=1;
							if (tabList != null && tabList.size()>0){					
								for(CommercialSolutionDetailTab tab: tabList){
									i=incr;
									tabArray[i-1]=tab.getDetail_tab_name();
									String tabName= tab.getDetailName().replace("_"," ");
									String shortName = tabName;
									int maxLength=30;
									if(tabName.length() > maxLength)
										shortName=tabName.substring(0,maxLength);
									%>
										<tr onclick="openUrl('portal.html?sys=<%=solution.getSigla()+"&tab="+tab.getDetail_tab_name()+"&lang="+lang %>'); return false;"
													bgcolor="#BBBBBB" 
													onMouseOver="this.bgColor='AAAAAA';" 
													title="<%=tabName %>"
													onMouseOut="this.bgColor='#BBBBBB';">
													<td height="24px" style="cursor: hand;	cursor: pointer;" >
													<center>
													<Font color="Black">
													<b>
													<a href="portal.html?sys=<%=(solution.getSigla()+"&tab="+tab.getDetail_tab_name()+"&lang="+lang)%>">
													<%=incr+". "+ shortName %>
													</a>
													</b>
													</Font>
													</center>
													</td>
												</tr>	
									<%
									incr++;
								}		
							}%>
							
							
							
							<%
							int imsize = (Integer) request.getAttribute("imsize");
							if(imsize>0){
								i++;
							
								tabArray[i-1]=lang.equalsIgnoreCase("en")?"Images":"Imagens";
							%>
							<tr onclick="jqueryOpen('showSoftwareSolutionDetail.html?detail=2&sigla=<%=solution.getSigla() %>','softwareContentDiv'); return false;"
													bgcolor="#BBBBBB" 
													onMouseOver="this.bgColor='AAAAAA';" 
													onMouseOut="this.bgColor='#BBBBBB';">
								<td height="24px" style="cursor: hand;	cursor: pointer;" >
									<center>
										<Font color="Black">
										<b><%=(incr++) %>. <%=(lang.equalsIgnoreCase("en")?"Images/Screenshots":"Imagens/Screenshots") %></b></Font>
									</center>
								</td>
							</tr>
							<%
							}
							i++; 
							tabArray[i-1]=lang.equalsIgnoreCase("en")?"Other Informations":"Dados Gerais";
							%>					
							<tr onclick="jqueryOpen('showSoftwareSolutionDetail.html?detail=3&sigla=<%=solution.getSigla() %>','softwareContentDiv'); return false;"
													bgcolor="#BBBBBB" 
													onMouseOver="this.bgColor='AAAAAA';" 
													onMouseOut="this.bgColor='#BBBBBB';">
													<td height="24px" style="cursor: hand;	cursor: pointer;" ><center>
													<Font color="Black">
													<b><%=(incr++) %>. <%=(lang.equalsIgnoreCase("en")?"Other Informations":"Dados Gerais") %></b></Font>
													</center>
													</td>
							</tr>
							<%
							int upsize = (Integer) request.getAttribute("upsize");
							if(upsize>0){
								i++; 
							%>
							<tr onclick="jqueryOpen('showSoftwareSolutionUpdate.html?sigla=<%=solution.getSigla() %>','contentDiv'); return false;"
													bgcolor="#BBBBBB" 
													onMouseOver="this.bgColor='AAAAAA';" 
													onMouseOut="this.bgColor='#BBBBBB';">
								<td height="24px" style="cursor: hand;	cursor: pointer;" >
									<center>
										<Font color="Black">
										<b><%=(incr++) %>. <%=(lang.equalsIgnoreCase("en")?"Software Updates":"Atualiza&ccedil;&otilde;es") %></b></Font>
									</center>
								</td>
							</tr>
							<%
							}
						}%>														
						</table>
					</div>
					<div class=" ui-corner-bottom" style="height: 10px; background-color:fafafa;  border-width: 1px; ">
						&nbsp;											
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
										<center><Font class="font_title"><b><%=sd.getNome() %></b></Font></center>	
										<span id="SDISpan<%=sd.getId()%>"  style="visibility: hidden; left: 0; top: 0; position: absolute;" >
											<table border="1" cellpadding="0"
											cellspacing="2" style="background-color: #AEAEAE; padding-top:5px; vertical-align: top; ">
												<tr><th width="400px"><%=(lang.equalsIgnoreCase("en")?"Description":"Descri&ccedil;&atilde;o") %> - <%=sd.getNome() %></th></tr>
												<tr><td><%=sd.getDescricao()!=null?sd.getDescricao():"" %></td></tr>
											</table>								
										</span>										
								</div>
								<div class="panel_content" style="background-color: #FaFaFa">					
									<table border="0" cellpadding="0"
										cellspacing="2" width="100%"
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
													siglainfo3[l]=ss.getSigla();
													l++;
												}
											
												for(int i=0; i < vetinfo3.length; i++){
													String value = vetinfo3[i];
												%>
												<tr onclick="openUrl('portal.html?sys=<%=siglainfo3[i] %>'); return false;"
													bgcolor="#BBBBBB" 
													onMouseOver="this.bgColor='AAAAAA';" 
													onMouseOut="this.bgColor='#BBBBBB';">
													<td height="24px" style="cursor: pointer;" >
													<center>
													<Font color="Black">
													<b>
													<a href="portal.html?sys=<%=siglainfo3[i] %>">
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
							<%	} 
								%>
							
				<div style="padding-bottom: 10px;">				
					<div class="panel_title ui-corner-top" >
							<center><Font class="font_title">
							
							<b><%=(lang.equalsIgnoreCase("en")?"Related Links":"Links Relacionados") %></b></Font>
							
							</center>											
					</div>
					<div class="panel_content" style="background-color: #FaFaFa">					
						<table border="0" cellpadding="0"
							cellspacing="2" width="256px"
							style="background-color: transparent; padding-top:5px; vertical-align: top; ">
							<tr onclick="jqueryOpen('produtos.html?information=softdema','contentDiv'); return false;"
													bgcolor="#BBBBBB" 
													onMouseOver="this.bgColor='AAAAAA';" 
													onMouseOut="this.bgColor='BBBBBB';">
													<td height="24px" style="cursor: hand;	cursor: pointer;" ><center>
													<Font color="Black">
													<b><%=(lang.equalsIgnoreCase("en")?"Softwares":"Solu&ccedil;&otilde;es em Software") %></b></Font>
													</center>
													</td>
							</tr>
							<tr onclick="jqueryOpen('produtos.html?information=softdema','contentDiv'); return false;"
													bgcolor="#BBBBBB" 
													onMouseOver="this.bgColor='AAAAAA';" 
													onMouseOut="this.bgColor='BBBBBB';">
													<td height="24px" style="cursor: hand;	cursor: pointer;" ><center>
													<Font color="Black"><b>
													<%=(lang.equalsIgnoreCase("en")?"Software Packages":"Pacotes de Software") %></b></Font>
													</center>
													</td>
							</tr>															
						</table>
					</div>
					<div class=" ui-corner-bottom" style="height: 10px; background-color:fafafa;  border-width: 1px; ">
										&nbsp;											
								</div>
				</div>	
								
							<%
							}%>
				</div>			
			</td>		
			<td valign="top">
				<div id="softwareContentDiv" style="margin-left:10px; padding-bottom: 10px;">
				</div>	
				<%
				Integer detail=0;
				if(tabStr != null){
					if(tabStr.equalsIgnoreCase("apresentacao"))
						detail = 0;
					else if(tabStr.equalsIgnoreCase("funcionalidades"))
						detail = 1;
					else if(tabStr.equalsIgnoreCase("imagens"))
						detail = 2;
					else if(tabStr.equalsIgnoreCase("dados gerais"))
						detail = 3;
					else if(tabArray != null && tabArray.length>0){
						for(int i=0; i< tabArray.length;i++){
							if(tabArray[i] != null && tabArray[i].equalsIgnoreCase(tabStr)){
								detail=null;
								break;
							}	
						}
					}
					request.getSession(true).removeAttribute("tab");
				}
				if(detail != null){
				%>
				<script> jqueryOpen('showSoftwareSolutionDetail.html?detail=<%=detail%>&sigla=<%=solution.getSigla() %>','softwareContentDiv');</script>
				<%}else{ %>
				<script> jqueryOpen('showSoftwareSolutionDetail.html?sol_id=<%=solution.getId()+"&tab_name="+tabStr+"&tab_lang=PT-BR&sigla="+solution.getSigla() %>','softwareContentDiv');</script>
				<%} %>														
				</td>
						
			</tr>	
		
		</table>

	</div>


</div>