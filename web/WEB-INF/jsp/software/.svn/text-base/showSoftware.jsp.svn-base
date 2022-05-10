<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
		<font class="font_title"><b>&nbsp;&nbsp;<%=solution.getNome() %></b></font>							
	</div>						
	<div class="panel_content ui-corner-bottom" style="padding:10px;">
			<div align="center" class="ui-corner-all" style="padding: 10px; border: 1px solid #234;">
				<a href="#" onClick="jqueryOpen('showSoftwareSolutionDetail.html?detail=0&sigla=<%=solution.getSigla() %>','softwareSolutionContentDiv'); return false;">1. Apresenta&ccedil;&atilde;o</a> |
				<a href="#" onClick="jqueryOpen('showSoftwareSolutionDetail.html?detail=1&sigla=<%=solution.getSigla() %>','softwareSolutionContentDiv'); return false;">2. Funcionalidades</a>
				<%
					int incr=3;
					if (tabList != null && tabList.size()>0){
					
					for(CommercialSolutionDetailTab tab: tabList){
						
						out.println(" | <a href=\"#\" onClick=\"jqueryOpen('showSoftwareSolutionDetail.html?sol_id="+tab.getCommercial_solution_id()+"&tab_name="+tab.getDetail_tab_name()+"&tab_lang="+tab.getDetail_text_language()+"&sigla="+solution.getSigla() +"','softwareSolutionContentDiv'); return false;\" >"
								+incr+". "+ tab.getDetail_tab_name().replace("_"," ")+"</a>");
						incr++;
					}		
				}
				
				%>
				| <a href="#" onClick="jqueryOpen('showSoftwareSolutionDetail.html?detail=2&sigla=<%=solution.getSigla() %>','softwareSolutionContentDiv'); return false;">
				<%=(incr++) %>. Imagens</a> | 
				<a href="#" onClick="jqueryOpen('showSoftwareSolutionDetail.html?detail=3&sigla=<%=solution.getSigla() %>','softwareSolutionContentDiv'); return false;"><%=(incr++) %>. Dados Gerais</a>					
			</div>
			<br style="height: 10px;"/>
			<div id="softwareSolutionContentDiv" align="center" style="height: 400px; overflow: auto;">
				
			</div>
			<script> jqueryOpen('showSoftwareSolutionDetail.html?detail=0&sigla=<%=solution.getSigla() %>','softwareSolutionContentDiv');</script>
		</div>													
	</div>
</div>
   		
   		
<%}%>



