<meta charset="UTF-8" />
<LINK rel="stylesheet" type="text/css" href="css/main.css"/>
<script type="text/javascript" src="js/jquery/jquery.bgiframe-2.1.1.js"></script>
<!-- ==============jquery=================== -->
	<link type="text/css" href="css/jquery/jquery-ui-1.8.4.custom.css" rel="stylesheet" />	
	<!-- ==============rich text=================== -->
	<link rel="stylesheet" href="css/jwysiwyg/jquery.wysiwyg.css" type="text/css" />
<style type="text/css">
   #just_dialog div button { font-size: 62.5%; }
</style>
<%@taglib prefix="jstl" uri="http://java.sun.com/jstl/core"%>
<%@taglib prefix="validation" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@taglib prefix="binder" uri="http://www.springframework.org/tags"%>
<%@page import="java.util.*"%>

<%@page import="com.adapit.portal.entidades.*"%>
<% News news = (News) request.getAttribute("news"); %>
<% if(news == null) news = (News) request.getSession().getAttribute("news"); %>
<% if(news == null) news = new News();%>
<!-- jQuery -->
	<script type="text/javascript" src="js/jquery/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="js/jquery/jquery-ui-1.8.4.custom.min.js"></script>
	<!-- BR jQuery datePicker -->
	<script type="text/javascript" src="js/jquery/jquery.ui.datepicker-pt-BR.js"></script>
	<!-- RICH TEXT -->
	<script type="text/javascript" src="css/jwysiwyg/jquery.wysiwyg.js"></script>
<script type="text/javascript">
	$(function() {
		$('#news\\.dataPublicacao').datepicker($.datepicker.regional['pt-BR']);
		<%if(news.getDataPublicacao() == null){%>
			$('#news\\.dataPublicacao').attr('value', '');
		<%}%>
		$("#newNewsButton").button();
		$("#saveNewsButton").button();
	});
</script>

<div style="clear:both; position=relative; padding-left:10px; padding-right:10px; padding-top:5px;" align="left">
	<div class="panel_title ui-corner-top">
		<font class="font_title"><b>&nbsp;&nbsp;
		<%if(news!=null){String tit=news.getTitulo();%><binder:message code="format.date"  arguments="<%= news.getDataPublicacao() %>"/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=tit %>
		<%}else{%>Selecione a Not&iacute;cia<%}%></b>
		</font>
	</div>									
	<div class="panel_content ui-corner-bottom" style="padding:10px;">	
	
	<!-- Edite o JSP userOperationFeedback.jsp para customizar os di&aacute;logos de feedback com o usu&aacute;rio -->
	<%@include file="../userOperationFeedback.jsp" %>
	<div  class="default_window"  style=" width:668px; height:560px; position:relative; top:0px; left:0px; " >
	
	<form class="default_form"  method="post" id="entityTabbedPaneForm" >
		<div  class="default_crud   ui-corner-all "  style=" width:633px; height:495px;  left:15px; position:absolute;  top:23px; " >
		
			<div class="default_div_title    ui-corner-all " style="width:100%; height:25px;">
				<span><binder:message code="Formul&aacute;rio_de_Cadastro_de_News"/></span>
			</div>
			<input name="news.id" id="news.id" type="hidden" 
			style="width:300px; height:20px;   " 
			value="<%=news.getId() %>"/>
			<input name="news.titulo" id="news.titulo" type="text" 
				style="width:463px; height:20px; position:absolute; top:40px; left:160px;  " 
				value="<jstl:out value='${news.titulo}'/>"/>
			<div style="width:140px; height:20px; position:absolute; top:40px; left:15px;  ">
				<label style="position:relative; top:25%"><binder:message code="T&iacute;tulo"/>:</label>
			</div>
			<input type="text" name="news.dataPublicacao" id="news.dataPublicacao" 
				
				style="width:172px; height:20px; position:absolute; top:65px; left:160px;  "  
				value="<binder:message code="format.date"  arguments="<%=news.getDataPublicacao() %>"/>"/>
			<div style="width:140px; height:20px; position:absolute; top:65px; left:15px;  ">
				<label style="position:relative; top:25%"><binder:message code="Data_da_Publica&ccedil;&atilde;o"/>:</label> 
			</div>
			<div  style=" width:608px; height:136px;  left:15px; position:absolute;  top:90px; " >
			
				<div style="width:600px; height:20px; position:absolute; top:0px; left:0px;  ">
				<label style="position:relative; top:25%"><binder:message code="Resumo_da_News_em_formato_HTML"/>:</label> 
				</div>
				<textarea name="news.resumo" id="news.resumo" 
					style="width:600px; height:104px; position:absolute; top:20px; left:0px;  " ><jstl:out value='${news.resumo}'/></textarea>
			</div>
			<div  style=" width:609px; height:216px;  left:15px; position:absolute;  top:231px; " >
			
				<div style="width:601px; height:20px; position:absolute; top:0px; left:0px;  ">
				<label style="position:relative; top:25%"><binder:message code="Texto_da_News_em_formato_HTML"/>:</label> 
				</div>
				<textarea name="news.descricao" id="news.descricao" 
					style="width:600px; height:186px; position:absolute; top:20px; left:0px;  " ><jstl:out value='${news.descricao}'/></textarea>
			</div>
			<div  class="default_div_buttons   ui-corner-all "  align="center"  
				style=" width:610px; height:38px;  left:15px; position:absolute;  top:452px; " >
			
				<button id="newNewsButton" style="  " onclick="javascript:newNewsOperation(); return false;">
					<binder:message code="Novo"/>
					<img src="imgs/page_add.png"/>
					<script type="text/javascript">
					function newNewsOperation(){
						$('#news\\.id').attr('value', '0');
						$('#news\\.titulo').attr('value', '');
						$('#news\\.dataPublicacao').attr('value', '');
						$('#news\\.resumo').attr('value', '');
						$('#news\\.descricao').attr('value', '');
						return false;
					}
					</script>
				</button>
				<button id="saveNewsButton" style="  " 
					onClick="jquerydoPost('entityTabbedPaneForm','saveNewsAction_NewsMaintenanceForm.html','contentNewsDiv'); return false;" >
					<binder:message code="Salvar"/>
					<img src="imgs/disk.png"/>
				</button>
			</div>
		</div>
	</form>
	</div>
	
	</div>				
</div>
	