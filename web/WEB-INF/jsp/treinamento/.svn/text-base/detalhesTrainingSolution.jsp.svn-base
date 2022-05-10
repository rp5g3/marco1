<%@page import="com.adapit.portal.entidades.*" %>
<%@page import="com.workcase.utils.*" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="validation" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="binder" uri="http://www.springframework.org/tags"%>

<%@page import="com.workcase.utils.IdGenerator"%>
<%@page import="com.adapit.portal.dto.UsuarioDTO"%>
<%@page import="java.util.Vector"%>
<%@page import="com.adapit.portal.util.global.GlobalVariables"%>
	

<%	
	TrainingSolution solution = (TrainingSolution) request.getAttribute("training");
	java.util.List<CommercialSolutionDetailTab> tabList = (java.util.List<CommercialSolutionDetailTab>)
		request.getAttribute("tabList");
	String randomdet = IdGenerator.getInstance().generateId(3);
%>

<%@page import="java.util.Iterator"%>
<LINK rel="stylesheet" type="text/css" href="css/main.css"/>

<script type="text/javascript" src="js/jquery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery-ui-1.8.4.custom.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery.bgiframe-2.1.1.js"></script>
<script type="text/javascript">
	$(function() {
		$("#detalhestabs<%=randomdet %>").tabs();
	});
</script>
<style>
.tab_content{
	font-family: 'Arial',sans-serif;   
	font-size: 12px; 
	line-height: normal; 
	font-size-adjust: none; 
	font-stretch: normal; 
	color: black;
	padding-left: 10px;
	padding-right: 10px;
}

</style>
<script type="text/javascript" src="js/wz_tooltip.js"></script>
<script type="text/javascript" src="js/virtualpaginate.js"></script>

<div class ="tab_content" >

<div id="detalhestabs<%=randomdet %>" >
	<ul>
		
		<li><a href="#conteudoProgramaticoTab<%=solution.getId()+randomdet %>"><b>Conte&uacute;do Program&aacute;tico</b></a></li>
		
		<%if (tabList != null && tabList.size()>0){
					for(CommercialSolutionDetailTab tab: tabList){
						
				%>
				<li><a href="#<%=tab.getDetail_tab_name() %>TrainingTab<%=solution.getId()+randomdet %>"><b><%=tab.getDetail_tab_name().replace("_"," ") %></b></a></li>
								
				<%	} 
		}%>
		<li><a href="#dadosGeraisTab<%=solution.getId()+randomdet %>"><b>Dados Gerais</b></a></li>
	</ul>
				<div id="conteudoProgramaticoTab<%=solution.getId()+randomdet %>" style="padding:10px;">		
						<table border=0 cellpadding=0 cellspacing=0 bordercolor=#000000 width="100%" style="background-color:transparent;" >
							<tr><td><%=solution.getConteudoProgramatico() %></td></tr>
			 			</table>
				</div>
				<%if (tabList != null && tabList.size()>0){
					for(CommercialSolutionDetailTab tab: tabList){
						
				%>
				<div id="<%=tab.getDetail_tab_name() %>TrainingTab<%=solution.getId()+randomdet %>" 
				 style="padding:10px;">		
					<%=tab.getDetail() %>					
				</div>				
				<%	} 
				}%>
				<%try{ %>
				<div id="dadosGeraisTab<%=solution.getId()+randomdet %>" 
				style="padding:10px;" >		
						<table border=1 class="presentation" cellpadding=0 cellspacing=3 bordercolor=#000000 width="100%" style="background-color:transparent;" >
							<tr><td class="presentation">Nome do Treinamento:</td><td class="presentation">&nbsp;<%=solution.getNome() %></td></tr>
							<tr><td class="presentation">Carga Hor&aacute;ria:</td><td class="presentation">&nbsp;<%=solution.getCargaHoraria() %></td></tr>
							<tr><td class="presentation">Palavras-Chave:</td><td class="presentation">&nbsp;<%=solution.getKeyWords() %></td></tr>
							<tr><td class="presentation">Objetivo Principal:</td><td class="presentation">&nbsp;<%=solution.getResumo() %></td></tr>
							<tr><td class="presentation">Objetivos:</td><td class="presentation">&nbsp;<%=solution.getObjetivos() %></td></tr>
							<tr><td class="presentation">Material de Apoio:</td><td class="presentation">&nbsp;<%=solution.getMaterialApoio() %></td></tr>
							<tr><td class="presentation">Tecnologias:</td><td class="presentation">&nbsp;<%=solution.getTecnologias() %></td></tr>
							<tr><td class="presentation">Tipo do Treinamento:</td><td class="presentation">&nbsp;<%=solution.getTipoTreinamento().name().replace("_"," ") %></td></tr>
						</table>					
				</div>		
				<%}catch(Exception ex){ex.printStackTrace();} %>
			</div>
		</div>