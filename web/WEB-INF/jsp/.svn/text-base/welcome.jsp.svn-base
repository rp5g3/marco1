<%
String lang = (String) request.getSession().getAttribute("lang");
if(lang == null)
	lang = "pt";
%>
<%@include file="layout/include_css.htm"%>
<%@include file="layout/include_script.htm"%>	
	<script language="JavaScript">
	if(navigator.appName!='Netscape')
	{
		alert('<%=(lang.equalsIgnoreCase("en")?"BROWSER NOT SUPPORTED!\nThe browser you are using is not suitable for viewing this site.\nThis site was designed to be viewed using the following browsers: Mozilla Firefox, Opera, Google Chrome, Netscape":"NAVEGADOR N&Atilde;O SUPORTADO!\nO Navegador que voc&ecirc; est&aacute; usando n&atilde;o &eacute; o adequado para a visualiza&ccedil;&atilde;o desse site.\nEste site foi projetado para ser visualizado com os seguintes browsers: Mozilla Firefox, Opera, Google Chrome, Netscape")%>');
	}
	</script>
</head>



<%
//Para o IE nao fazer cache do <div>

response.setHeader("Cache-Control","no-cache");

response.setHeader("Pragma","no-cache");

response.setDateHeader ("Expires", 0);

%>

<%@page import="com.adapit.portal.dto.UsuarioDTO"%>
			<%@page import="com.adapit.portal.entidades.*"%>
			<%@page import="com.adapit.portal.services.PersonType"%>

<% 
String pageStr = (String) request.getAttribute("pageStr");
String prodSigla = (String) request.getAttribute("sigla");
UsuarioDTO udto = (UsuarioDTO) request.getSession().getAttribute("user");


Destaque d = (Destaque) request.getAttribute("destaque");
News destaqueNews = null;
SoftwareSolution destaqueSoftware = null;
Publication destaquePublication = null;
if (d != null){
	 destaqueNews = d.getNews();
	 destaqueSoftware = d.getSoftware();
	 destaquePublication = d.getPublication();
}
java.util.Date dt = new java.util.Date();
int ano = dt.getYear()+1900;
%>

<body class="bkbody font" style="background-image: url(imgs/bodybg2.jpg); background-repeat: repeat;">
<script type="text/javascript">overlay.init();</script>
<!-- general -->
<div style=" background-color: transparent; background-image: url(imgs/bodybg4.jpg); background-repeat: repeat-x;">	
	<div id="general" style=" background-color: transparent;">
		<!--  background-image: url(imgs/adapit_logo_1.png); background-repeat: no-repeat;  -->
		
		
		
		
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
					<!--<a class="white_link" href="" target="_blanck">Open Source</a>  |  
					<a class="white_link" href="" target="_blanck">Downloads</a>  |
					<a class="white_link" href="" target="_blanck">Blog</a>  | 
					<a class="white_link" href="http://twitter.com/Adapit" target="_blanck">Twitter</a>  |   
					<a class="white_link" href="http://www.omg.org" target="_blanck">OMG</a>-->
					<div style="float:left; margin-top:-10px;">
						<div style="float: left; width: 500px;">&nbsp;
						</div>
						<div class="pais_div ui-corner-bottom" 
							style="float: left;  padding-top: 3px; padding-right: 5px; width: 65px; " 
							align="right">
							<a href="portal.html?lang=pt">
								<img src="imgs/br.png" style="cursor: pointer;" />&nbsp;
							</a> 
							<a href="portal.html?lang=en">
								<img src="imgs/us.png" style="cursor: pointer;" />&nbsp;
							</a>
						</div>
						
					</div>
					
				</div>
			</div>
			<div style="padding-top:25px; padding-bottom:5px; padding-right: 25px; 
				font: bold 14px arial ; color: #DF1E29; font-size: 12px;" align="right">
				<a class="red_link" href="" 
				onclick="jqueryOpen('showPersonListByAttributeType.html?attType=<%=PersonAttributeType.Responsavel_Legal.name() %>','contentDiv'); return false;">
				<%=(lang.equalsIgnoreCase("en")?"Coordinators":"Empreendedores") %></a>  |
				<a class="red_link" href="" 
				onclick="jqueryOpen('showPersonListByAttributeType.html?attType=<%=PersonAttributeType.Parceiro.name() %>','contentDiv'); return false;">
				<%=(lang.equalsIgnoreCase("en")?"Partners":"Parceiros") %></a>  | 
				<a class="red_link" href="" 
				onclick="jqueryOpen('showPersonListByAttributeType.html?attType=<%=PersonAttributeType.Cliente.name() %>','contentDiv'); return false;">
				<%=(lang.equalsIgnoreCase("en")?"Customers":"Clientes") %></a> | 
				<a class="red_link" href="" 
				onclick="jqueryOpen('showPersonListByAttributeType.html?attType=<%=PersonAttributeType.Pesquisador.name() %>','contentDiv'); return false;">
				<%=(lang.equalsIgnoreCase("en")?"Researchers":"Pesquisadores") %></a>
				
			</div>
			<div style="margin-left: 210px; border-top:1px solid black; border-left:1px solid black; 
			padding-top:5px; padding-right: 20px;
			 background-color:#1a1a1a; height: 29px;" align="left">
				<div style="padding:5px; width: 440px; float: left; 
				font: bold 14px arial ; color: white; font-size: 12px;" align="left">
				<%if(udto != null){ %>
					<a class="white_link" href="loginUsuario.html?init=false"><%=(lang.equalsIgnoreCase("en")?"Exit":"Sair do sistema") %></a>
					| 
					<a class="white_link" 
					href="recomendation.html?part_id=<%=udto.getParticipanteId() %>" 
					><%=(lang.equalsIgnoreCase("en")?"Recommend to a friend":"Recomendar para um amigo") %></a>
					
					| 
					<a class="white_link" href="" target="_blanck"
					onClick="jqueryOpen('loginUsuario.html?init=true','contentDiv'); return false;"><%=(lang.equalsIgnoreCase("en")?"Access":"Acessar conta") %></a>
					
					<%} else{%>
					<a class="white_link" href="" target="_blanck"
					onClick="jqueryOpen('loginUsuario.html?init=true','contentDiv'); return false;"><%=(lang.equalsIgnoreCase("en")?"Access":"Acessar conta") %></a>
					| <a class="white_link" href="" target="_blanck"
					onClick="jqueryOpen('showParticipanteMaintenanceForm.html','contentDiv'); return false;"><%=(lang.equalsIgnoreCase("en")?"Register here":"Registre-se aqui") %></a>
					
					
					<%} %>
				
				</div>
				<form class="default_form"  method="post" id="internalSearchForm" >
				<input name="keyword" id="keyword" type="text" 
				style="width: 220px; height: 22px;" 
				value=""/>
				<button onclick="jquerydoPost('internalSearchForm','showPesquisaInternaView.html','contentDiv'); return false;">
				<%=(lang.equalsIgnoreCase("en")?"Search":"Pesquisar") %>
				</button>
				</form>
				
			</div>
		</div>
		<div style="clear:both; display: block; height:36px; width:100%; background-image: url(imgs/menu_bg_dark.png);">
			<div style="padding:10px; width:100%;">
			<center>
			<a class="white_link" href=""  onClick="jqueryOpen('conteudo_inicial.html','contentDiv'); return false;">Home</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a class="white_link" href=""  onClick="jqueryOpen('institucional.html?page_id=institucional_apresentacao_.html','contentDiv'); return false;">
			<%=(lang.equalsIgnoreCase("en")?"About Us":"Sobre N&oacute;s") %></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    <a class="white_link" href=""  onClick="jqueryOpen('showFormacoes_.html?pageUrl=showAboutTrainings.html','contentDiv'); return false;">
		    <%=(lang.equalsIgnoreCase("en")?"Trainings":"Treinamentos") %></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    <a class="white_link" href=""  onClick="jqueryOpen('produtos.html?information=softdema','contentDiv'); return false;">
		    <%=(lang.equalsIgnoreCase("en")?"Softwares":"Solu&ccedil;&otilde;es em Software") %></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    <a class="white_link" href=""  onClick="jqueryOpen('news.html?newsid=<%=destaqueNews != null?destaqueNews.getId():1%>','contentDiv'); return false;">
		    <%=(lang.equalsIgnoreCase("en")?"News":"Not&iacute;cias") %></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    <a class="white_link" href=""  onClick="jqueryOpen('publication.html?publicationid=0','contentDiv'); return false;">
		    <%=(lang.equalsIgnoreCase("en")?"Papers":"Artigos") %></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    <a class="white_link" href=""  onClick="jqueryOpen('institucional.html?page_id=institucional_contato_.html','contentDiv'); return false;">
		    <%=(lang.equalsIgnoreCase("en")?"Contact":"Contato") %></a>
		    </center>
		    </div>
		</div>
		
		<table width="1002px" style="background-color: transparent;" border="0" cellpadding="0" cellspacing="0">			
			<tr>
				<td >
					<div id="contentDiv" class="ui-corner-all"
						style="margin-top:5px; display: block; float: left; padding-top:5px;
						 position: relative; height: 100%; width: 100%; z-index: 1;
						 background-color: #2a2a2a; background-image: url(imgs/contentdarkbg.jpg); background-repeat: repeat-x;
						  border:1px solid #191919; ">								
						<%if(pageStr != null && (pageStr.equals("recomendation") || pageStr.equals("recommendation"))){ %>
						<br></br>
						<center>
						<div  class="default_window"  style=" width:944px;  position:relative; top:0px; left:0px; " >
								<div  class="default_crud   ui-corner-all "  style=" width:909px; height:240px;  left:15px; position:relative;  top:0px; " >
						
									<div class="default_div_title ui-corner-top" style="width:100%; height:28px;">
									<span><%=(lang.equalsIgnoreCase("en")?"Recommendation for Friends":"Recomenda&ccedil;&atilde;o para Amigos") %></span>
									</div>
									<div  style=" width:886px; height:230px;  left:15px; position:absolute;  top:33px; " >
										<%
										String msg = (String)request.getAttribute("msg"); 
										if(msg != null){
										%>
										<p><%=msg %></p>
										<p><a href="recommendation.html<%if(udto != null){ %>?part_id=<%=udto.getParticipanteId() %><%} %>">Recomendar mais</a></p>
										<%}else { %>
											<p>Para nos recomendar para seus contatos, informe o email dos mesmos e separe-os com v&iacute;rgula:</p>
											<%
												String part_id = (String) request.getAttribute("part_id");
												if(udto!=null && part_id==null)
													part_id = udto.getParticipanteId()+"";
											%>
											<form action="recommendation.html">
											<input type="text" id="contact_email" name="contact_email" style="width:100%; height:24px;"/>
											
											<!--
											<p>Escreva a sua mensagem de recomenda&ccedil;&atilde;o:</p> 
											<textarea rows="" cols="" style="width:100%; height:54px;" id="contact_msg" name="contact_msg"></textarea>
											 -->
											<input type="hidden" id="part_id" name="part_id" style="width:100%; height:24px;" value="<%=part_id %>"/>
											<center><input type="submit" value="Enviar"/></center>
											</form>
											<br></br>
											<br></br>
										<%} %>
									</div>
								</div>
						</div>
						</center>
						<br></br>
						<%} else if(pageStr != null && (pageStr.equals("recomendationsucess") || pageStr.equals("recommendationsucess"))){ %>
						<br></br>
						<center>
						<div  class="default_window"  style=" width:944px;  position:relative; top:0px; left:0px; " >
								<div  class="default_crud   ui-corner-all "  style=" width:909px; height:240px;  left:15px; position:relative;  top:0px; " >
						
									<div class="default_div_title ui-corner-top" style="width:100%; height:28px;">
									<span><%=(lang.equalsIgnoreCase("en")?"Recommendation for Friends":"Recomenda&ccedil;&atilde;o para Amigos") %></span>
									</div>
									<div  style=" width:886px; height:230px;  left:15px; position:absolute;  top:33px; " >
										<p><%=(lang.equalsIgnoreCase("en")?"Your recommendation was sent to your friends":"A sua recomenda&ccedil;&atilde;o foi enviada para os seus amigos") %></p>
										<p><a href="recommendation.html<%if(udto != null){ %>?part_id=<%=udto.getParticipanteId() %><%} %>">
										<%=(lang.equalsIgnoreCase("en")?"Recommend to more friends":"Recomendar para mais amigos") %></a></p>
									</div>
								</div>
						</div>
						</center>
						<br></br>
						<%} %>													
					</div>
					<%if(request.getSession().getAttribute("account")!=null){ %>
					<script> jqueryOpen('<%=request.getSession().getAttribute("user")==null?"loginUsuario.html?init=true":"showParticipanteMaintenanceForm.html"%>','contentDiv'); </script>
					<%}else if(pageStr != null && pageStr.equals("welcomeUser")){ %>
					<script> jqueryOpen('loginUsuario.html?init=true','contentDiv'); </script>
					<%}else if(pageStr != null && (pageStr.equals("recomendation") || pageStr.equals("recomendationsucess") || pageStr.equals("recommendation") || pageStr.equals("recommendationsucess"))){ %>
					
					<%}else if(pageStr != null && pageStr.equals("new_register")){ %>
					<script> jqueryOpen('showParticipanteMaintenanceForm.html','contentDiv'); </script>
					<%}else if(pageStr != null && pageStr.equals("produtos")){ %>
					<script> jqueryOpen('produtos.html?sigla=<%=prodSigla%>','contentDiv'); </script>
					<%}else if(pageStr != null && pageStr.equals("news")){ %>
					<script> jqueryOpen('news.html?newsid=<%=request.getAttribute("newsid")%>','contentDiv'); </script>
					<%}else if(pageStr != null && pageStr.equals("paper")){ %>
					<script> jqueryOpen('publication.html?publicationid=<%=request.getAttribute("paperid")%>','contentDiv'); </script>
					<%}else if(pageStr != null && pageStr.equals("update")){
						String sigla = (String) request.getAttribute("sigla");
						%>
					<script> jqueryOpen('update.html?updateid=<%=request.getAttribute("updateid")%>&sigla=<%=sigla%>','contentDiv'); </script>
					<%}else{ %>
					<script> jqueryOpen('conteudo_inicial.html','contentDiv'); </script>
					<%} %>
				</td>
			</tr>
		</table> 
	
		<div class="ui-corner-all" style="
			margin-top:5px; float:right; height: 30px;
		 width:100%; background-color:#2a2a2a;
		 background-image: url(imgs/contentdarkbg.jpg); background-repeat: repeat-x;
		 border:1px solid #191919;
		 font: normal 14px arial ; color: white; font-size: 12px;">
		 <center>
		 <%=GlobalVariables.ADAPIT_COPYRIGHT %></center>
		</div>
		<br>&nbsp;</br>
	
	</div>
</div>
</body>

<%@page import="com.adapit.portal.util.global.GlobalVariables"%></html>


		
