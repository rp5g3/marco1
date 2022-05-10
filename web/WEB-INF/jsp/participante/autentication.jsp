<%@include file="../layout/include_css.htm"%>
<%@include file="../layout/include_script.htm"%>	
	<script language="JavaScript">
	if(navigator.appName!='Netscape')
	{
		alert('NAVEGADOR N&Atilde;O INDICADO!\nO Navegador que voc&ecirc; est&aacute; usando n&atilde;o &eacute; o adequado para a visualiza&ccedil;&atilde;o desse site.\nEste site foi projetado para ser visualizado com os seguintes browsers: Mozilla Firefox, Opera, Google Chrome, Netscape');
	}
	</script>
</head>



<%
//Para o IE nao fazer cache do <div>

response.setHeader("Cache-Control","no-cache");

response.setHeader("Pragma","no-cache");

response.setDateHeader ("Expires", 0);

%>

<% 
String pageStr = (String) request.getAttribute("pageStr");
String prodSigla = (String) request.getAttribute("sigla");
UsuarioDTO udto = (UsuarioDTO) request.getSession(true).getAttribute("user");
%>

<body class="bkbody font" style="background-image: url(imgs/bodybg2.jpg); background-repeat: repeat;">
<script type="text/javascript">overlay.init();</script>
<!-- general -->
<div style=" background-color: transparent; background-image: url(imgs/bodybg4.jpg); background-repeat: repeat-x;">	
	<div id="general" style=" background-color: transparent;">
		<div class="ui-corner-top" style="margin-top:2px; float:right;
		 border-bottom:1px solid black; 
		height: 90px; width:100%; background-color:#2a2a2a;">
			<div style="height: 94px; width:200px; float: left;
			margin-left: 20px;	margin-top: 3px; position:relative;">
				<a href="http://www.adapit.com.br"><img border="0" src="imgs/adapit_logo_1.png"/></a>
			</div>
			<div style="margin-left: 210px; padding-top:10px; padding-right: 25px; " align="left">
				<div style="float: left; 
				font: normal 14px arial ; color: white; font-size: 12px;" align="left">
				</div>
			</div>
			<div style="padding-top:25px; padding-bottom:5px; padding-right: 25px; 
				font: bold 14px arial ; color: #DF1E29; font-size: 12px;" align="right">
				<a class="red_link" href="" 
				onclick="jqueryOpen('showPersonListByAttributeType.html?attType=<%=PersonAttributeType.Responsavel_Legal.name() %>','contentDiv'); return false;">
				Empreendedores</a>  |
				<a class="red_link" href="" 
				onclick="jqueryOpen('showPersonListByAttributeType.html?attType=<%=PersonAttributeType.Parceiro.name() %>','contentDiv'); return false;">
				Parceiros</a>  | 
				<a class="red_link" href="" 
				onclick="jqueryOpen('showPersonListByAttributeType.html?attType=<%=PersonAttributeType.Cliente.name() %>','contentDiv'); return false;">
				Clientes</a> | 
				<a class="red_link" href="" 
				onclick="jqueryOpen('showPersonListByAttributeType.html?attType=<%=PersonAttributeType.Pesquisador.name() %>','contentDiv'); return false;">
				Pesquisadores</a>
				<!--<a class="red_link" href="" 
				onclick="jqueryOpen('showPersonListByAttributeType.html?attType=<%=PersonAttributeType.Instrutor.name() %>','contentDiv'); return false;">
				Instrutores</a>-->
			</div>
			<div style="margin-left: 210px; border-top:1px solid black; border-left:1px solid black; 
			padding-top:5px; padding-right: 20px;
			 background-color:#1a1a1a; height: 29px;" align="left">
				<div style="padding:5px; width: 440px; float: left; 
				font: bold 14px arial ; color: white; font-size: 12px;" align="left">
				
					<a class="white_link" href="" target="_blanck"
					onClick="jqueryOpen('loginUsuario.html?init=true','contentDiv'); return false;">Acessar o sistema</a>
					| <a class="white_link" href="" target="_blanck"
					onClick="jqueryOpen('showParticipanteMaintenanceForm.html','contentDiv'); return false;">Registrar-se</a>
				
				</div>
				<form class="default_form"  method="post" id="internalSearchForm" >
				<input name="keyword" id="keyword" type="text" 
				style="width: 220px; height: 22px;" 
				value=""/>
				<button onclick="jquerydoPost('internalSearchForm','showPesquisaInternaView.html','contentDiv'); return false;">
				Pesquisar
				</button>
				</form>
				
			</div>
		</div>
	
		<br></br>
		<%@include file="../layout/menu1.jsp"%>		 
		
		<table width="1002px" style="background-color: transparent;" border="0" cellpadding="0" cellspacing="0">			
			<tr>
				<td >
					<div id="contentDiv" class="ui-corner-all"
						style="margin-top:5px; display: block; float: left; padding-top:5px;
						 position: relative; height: 100%; width: 100%; z-index: 1;
						 background-color: #2a2a2a; background-image: url(imgs/contentdarkbg.jpg); background-repeat: repeat-x;
						  border:1px solid #191919; ">								
						<br></br>
						<center>
						<div  class="default_window"  style=" width:944px;  position:relative; top:0px; left:0px; " >
								<div  class="default_crud   ui-corner-all "  style=" width:909px; height:100px;  left:15px; position:relative;  top:0px; " >
						
									<div class="default_div_title ui-corner-top" style="width:100%; height:28px;">
									<span>Autentica&ccedil;&atilde;o de Usu&aacute;rios</span>
									</div>
									<div  style=" width:886px; height:80px;  left:15px; position:absolute;  top:33px; " >
										<%String msg = (String)request.getAttribute("msg"); %>
										<p><%=msg %></p>
									</div>
								</div>
						</div>
						</center>
						<br></br>
																			
					</div>
					
				</td>
			</tr>
		</table> 
	
		<div class="ui-corner-all" style="
			margin-top:5px; float:right; height: 30px;
		 width:100%; background-color:#2a2a2a;
		 background-image: url(imgs/contentdarkbg.jpg); background-repeat: repeat-x;
		 border:1px solid #191919;
		 font: normal 14px arial ; color: white; font-size: 12px;">
		 <center><%=GlobalVariables.ADAPIT_COPYRIGHT %></center>
			</div>
		<br>&nbsp;</br>
	
	</div>
</div>
</body>

<%@page import="com.adapit.portal.entidades.PersonAttributeType"%>
<%@page import="com.adapit.portal.util.global.GlobalVariables"%></html>


		
