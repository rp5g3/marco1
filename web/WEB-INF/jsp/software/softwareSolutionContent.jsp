<%@ taglib prefix="jstl" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="validation" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="binder" uri="http://www.springframework.org/tags"%>	
<%@page import="com.adapit.portal.entidades.*"%>
<%@page import="java.util.*"%>

<%@page import="com.workcase.utils.IdGenerator"%>
<%
	SoftwareSolution solution = (SoftwareSolution) request.getAttribute("software");
	if (solution != null){
	java.util.List<CommercialSolutionDetailTab> tabList = (java.util.List<CommercialSolutionDetailTab>)
		request.getAttribute("tabList");
	String randomdet = IdGenerator.getInstance().generateId(3);
%>
<LINK rel="stylesheet" type="text/css" href="css/main.css"/>

<script type="text/javascript" src="js/jquery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery-ui-1.8.2.custom.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery.bgiframe-2.1.1.js"></script>
<style type="text/css">
   #just_dialog div button { font-size: 62.5%; }
</style>

<script type="text/javascript"><!--

				jQuery(document).ready(function(){
					$('.softwareAccordionDiv.head').click(function() {
						$(this).next().toggle('slow');
						return false;
					}).next().hide();
				});
				/*jQuery(document).ready(function(){
					$('.technoAccordionDiv.head').click(function() {
						$(this).next().toggle();
						return false;
					}).next().hide();
				});*/
				$(function() {
					$("#softwareAccordionDiv").accordion();
				});
							
</script>
<script type="text/javascript" src="js/wz_tooltip.js"></script>
<div style="clear:both; position=relative; padding-left:10px; " align="left">
	<div class="panel_title ui-corner-top">
		<font class="font_title"><b>&nbsp;&nbsp;Sistema <%=solution.getNome() %></b></font>							
	</div>						
	<div class="panel_content ui-corner-bottom" style="padding:10px;">			
			<div id="softwareAccordionDiv">		
				<h3 >
					<a href="#"><Font color="Black" size="2" face="Arial"><b>Apresenta&ccedil;&atilde;o</b></Font></a>
				</h3>
				<div style="padding: 5px; padding-left:10px;padding-right:10px;padding-bottom:10px;padding-top:10px;">			
									
										<table border=0 cellpadding=0 cellspacing=0 bordercolor=#000000 width="100%" style="background-color:transparent;" >
											<tr><td><%=solution.getResumo() %></td></tr>
							 			</table>
				</div>
				<h3 >
					<a href="#"><Font color="Black" size="2" face="Arial"><b>Funcionalidades</b></Font></a>
				</h3>
				<div style="padding: 5px; padding-left:10px;padding-right:10px;padding-bottom:10px;padding-top:10px;">					
										<table border=0 cellpadding=0 cellspacing=0 bordercolor=#000000 width="100%" style="background-color:transparent;" >
											<tr><td><%=solution.getFuncionalidades() %></td></tr>
							 			</table>
				</div>
								<%if (tabList != null && tabList.size()>0){
									for(CommercialSolutionDetailTab tab: tabList){
										
								%>
				<h3 >
					<a href="#"><Font color="Black" size="2" face="Arial"><b><%=tab.getDetail_tab_name().replace("_"," ") %></b></Font></a>
				</h3>
				<div style="padding: 5px; padding-left:10px;padding-right:10px;padding-bottom:10px;padding-top:10px;">					
									<%=tab.getDetail() %>					
				</div>				
								<%	} 
								}%>
								<%try{ %>
				<h3 >
					<a href="#"><Font color="Black" size="2" face="Arial"><b>Imagens</b></Font></a>
				</h3>
				<div style="padding: 5px; padding-left:10px;padding-right:10px;padding-bottom:10px;padding-top:10px;">					
									<%@include file="smallCustomImageSlideshow.jsp" %>
				</div>
								<%}catch(Exception ex){ex.printStackTrace();} %>
								<%try{ %>
				<h3 >
					<a href="#"><Font color="Black" size="2" face="Arial"><b>Dados Gerais</b></Font></a>
				</h3>
				<div style="padding: 5px; padding-left:10px;padding-right:10px;padding-bottom:10px;padding-top:10px;">					
										<table border=1 class="presentation" cellpadding=0 cellspacing=3 bordercolor=#000000 width="100%" style="background-color:transparent;" >
											<tr><td class="presentation">Nome do Sistema:</td><td class="presentation">&nbsp;<%=solution.getNome() %></td></tr>
											<tr><td class="presentation">Licen&ccedil;a de Uso:</td><td class="presentation"><%=solution.getLicencaUso() %></td></tr>
											<tr><td class="presentation">Palavras-Chave:</td><td class="presentation"><%=solution.getKeyWords() %></td></tr>
											<tr><td class="presentation">Plataforma:</td><td class="presentation"><%=solution.getPlataforma() %></td></tr>
											<tr><td class="presentation">Sistemas Operacionais:</td><td class="presentation"><%=solution.getSistemasOperacionais() %></td></tr>
											<tr><td class="presentation">URL do projeto:</td><td class="presentation"><%=solution.getUrlProjeto() %></td></tr>											
										</table>					
				</div>		
								<%}catch(Exception ex){ex.printStackTrace();} %>
		</div>													
	</div>
</div>
   		
   		
<%}%>



