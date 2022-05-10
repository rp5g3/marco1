
<%@page import="com.adapit.portal.entidades.*"%>
<script type="text/javascript">
function abrirWctAdmin(){
	window.location='http://www.adapit.com.br/portal.html?sys=wctadmin';
}
function abrirWctDownload(){
	window.location='http://www.adapit.com.br/adapitfiles/files/WCT.zip';
}
function abrirWctFomda(){
	window.location='http://www.adapit.com.br/portal.html?sys=wctfomda';
}
function abrirWctEng(){
	window.location='http://www.adapit.com.br/portal.html?sys=wctengwd';
}
function abrirWct(){
	window.location='http://www.adapit.com.br/portal.html?sys=wct';
}
</script>
<style type="text/css" media="screen">
		#flashContent { width:100%; height:100%; }
</style>

<div style="background-color: transparent;">
<%
	Destaque d = (Destaque) request.getAttribute("destaque");
	if (d != null){
		News n = d.getNews();
	%>
	<%
String lg1 = (String) request.getSession().getAttribute("lang");
if(lg1 == null)
	lg1 = "pt";
%>
	<div style="position:relative; background-color: transparent; vertical-align: top; padding: 10px;">				
		<div style="clear:both; position=relative; " align="left">
			<div class="panel_content ui-corner-all" style = "height: 50px; background-color: transparent;">
				<font style="font-family: arial; font-size: 18; color:red; border-top-width: 1px; ">
					<center>
						<b>
							<%=(lg1.equalsIgnoreCase("en")?
							"R&D - Software Engineering Research and Development":
							"P&D - Pesquisa e Desenvolvimento para Engenharia de Software")
							%>
						</b>
					</center>
				</font>	
				<font style="font-family: arial; font-size: 15; color:white; border-top-width: 1px; ">
					<center>
						<i>
							<%=(lg1.equalsIgnoreCase("en")?
							"Industry and Academy in Total Synergy":
							"Ind&uacute;stria e Academia em Total Sinergia")
							%>
						</i>
					</center>
				</font>	
			</div>
		</div>
	</div>	
	
	<div id="proxNews" style=" overflow: inherit;">
	</div>
	<script type="text/javascript" language="javascript">javascript: jqueryOpen('larger_last_news.html','proxNews'); </script>	
				
	
	<div style="position:relative; background-color: transparent; vertical-align: top; padding: 10px;">				
		<div style="clear:both; position=relative; " align="left">
			<div class="panel_content ui-corner-all" style = "padding:5px; height: 320px; ">
					<!--  
					<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" 
						codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,0,0" 
						width="100%" height="100%" 
						id="bannerAdapit" align="middle">
					  <param name="allowScriptAccess" value="sameDomain" />
					  <param name="allowFullScreen" value="false" />
					  <param name="movie" value="res/banner_adapit.swf" />
					  <param name="quality" value="high" />
					  <param name="wmode" value="transparent" />
					  <param name="bgcolor" value="transparent" />
					  <embed src="res/banner_adapit.swf" 
					  quality="high" wmode="transparent" bgcolor="transparent" width="100%" height="100%" 
					  name="bannerAdapit" align="middle" allowscriptaccess="sameDomain" allowfullscreen="false" 
					  type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />
					</object>
					-->
			
			<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" width="969" height="316" id="banner_adapit" align="middle"
			>
				<param name="movie" value="res/banner_adapit.swf" />
				<param name="quality" value="high" />
				<param name="bgcolor" value="#ffffff" />
				<param name="play" value="true" />
				<param name="loop" value="true" />
				<param name="wmode" value="window" />
				<param name="scale" value="showall" />
				<param name="menu" value="true" />
				<param name="devicefont" value="false" />
				<param name="salign" value="" />
				<param name="allowScriptAccess" value="sameDomain" />
				<!--[if !IE]>-->
				<object type="application/x-shockwave-flash" data="res/banner_adapit.swf" width="969" height="316" >
					<param name="movie" value="banner_adapit.swf" />
					<param name="quality" value="high" />
					<param name="bgcolor" value="#ffffff" />
					<param name="play" value="true" />
					<param name="loop" value="true" />
					<param name="wmode" value="window" />
					<param name="scale" value="showall" />
					<param name="menu" value="true" />
					<param name="devicefont" value="false" />
					<param name="salign" value="" />
					<param name="allowScriptAccess" value="sameDomain" />
				<!--<![endif]-->
					<a href="http://www.adobe.com/go/getflash">
						<img src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif" alt="Get Adobe Flash player" />
					</a>
				<!--[if !IE]>-->
				</object>
				<!--<![endif]-->
			</object>
			
					
				<!-- 
				<table border="0" cellpadding="0" cellspacing="0" style="background: transparent; width: 100%; height: ">
					<tr>
						<td width="537px" onclick="abrirWct();" style="cursor: pointer">&nbsp;</td>
						<td  width="214px" onclick="abrirWctDownload();" style="cursor: pointer"></td>
						<td  width="234px" height="322">
							<table border="0" cellpadding="0" cellspacing="0" 
								style="background: transparent; width: 188px; margin-left:16px; margin-top: 30px; margin-bottom:34px;">
								<tr><td style="cursor: pointer" height="72px;" width="188px;"
									onclick="abrirWctAdmin();">&nbsp;</td></tr>
								<tr><td height="26px;">&nbsp;</td></tr>
								<tr><td style="cursor: pointer" height="72px;"
									onclick="abrirWctFomda();">&nbsp;</td></tr>
								<tr><td height="23px;">&nbsp;</td></tr>
								<tr><td style="cursor: pointer" height="72px;"
								onclick="abrirWctEng();">&nbsp;</td></tr>
							</table>
						</td>					
					</tr>		
				</table>
				 -->
				<!--
				<div style="padding-top:15px; width: 80px;" align="left">
					
					 
					<div style="margin-left: 540px; height: 29px;  
					font: bold 14px arial ; color: white; font-size: 12px;" align="left">	
						<a class="red_link" href="http://www.adapit.com.br/portal.html?sys=wctadmin">Gerente de Requisitos</a>
					</div>
					
					<div style="padding-top:35px; width: 80px;  
					font: bold 14px arial ; color: white; font-size: 12px;" align="left">
						<a class="red_link" href="http://www.adapit.com.br/portal.html?sys=wctfomda">
						Arquiteto de Sistemas</a>  
					</div>
					
					<div style="padding-top:35px; width: 80px; 
					font: bold 14px arial ; color: white; font-size: 12px;" align="left">
						<a class="red_link" href="http://www.adapit.com.br/portal.html?sys=wctengwd" 
						>
						Engenheiro de Sistemas</a>   
					</div>
					
					<div style="padding-top:36px; padding-left:40px; width: 80px;  
					font: bold 14px arial ; color: white; font-size: 12px;" align="left">	
						<a class="red_link" href="http://www.adapit.com.br/portal.html?sys=wct"
						onMouseOver="document.descubra.src='imgs/descubra2.png'"
						onMouseOut="document.descubra.src='imgs/descubra1.png'" >
						<img  border=0 src="imgs/descubra1.png" name="descubra">
						</a>
					</div>
					 
				</div>-->
			</div>
		</div>
	</div>
	<!--<div width="100%" style="position:relative; background-color: transparent; vertical-align: top; position=relative;">				
		<div style="clear:both; position=relative; padding-left:10px; padding-top:5px;" align="left">	

					<div class="informationtopleftcorner"></div>		
					<div class="defaultbigtitledpane">
						<center><b>
<font style="font-family: 'Comic Sans MS',sans-serif; font-style: italic; font-variant: small-caps; font-weight: bold; font-size: 14px; line-height: normal; font-size-adjust: none; font-stretch: normal; -x-system-font: none;">
						CONSULTORIAS EM GEST&Atilde;O PARA EMPRESAS DE TI PARTICIPANTES DO PRIME</font></b></center>
											
					</div>
					<div class="defaultbigcontentpane" style=" background:#FaFeF1;">
						<div width="100%" style="padding:10px;">									
					<p >Adapit lan&ccedil;a um pacote de servi&ccedil;os de
 gest&atilde;o para empresas do PRIME com foco em TI que ir&acirc;o utilizar tecnologia livre, como Java.
 O pacote &eacute; denominado 
<font style="font-family: 'Comic Sans MS',sans-serif; font-style: italic; font-variant: small-caps; font-weight: bold; font-size: 14px; line-height: normal; font-size-adjust: none; font-stretch: normal; -x-system-font: none;">
SUA EMPRESA EM PRODU&ccedil;&Atilde;O</font>
 &nbsp;e t&ecirc;m por objetivo qualificar a empresa para
 executar o projeto de TI. 
 Aproveite a oportunidade e construa o seu sistema
 no prazo, contando com a nossa experi&ecirc;ncia.
 Tamb&eacute;m estamos no PRIME e nossa inova&ccedil;&atilde;o faz parte
 do pacote que estamos oferecendo.</p>
					<p><a href="" onclick="adapitPost('news.html?newsid=16','contentDiv'); return false;" style="cursor: pointer;">Saiba mais ...</a></p>
						</div>
					</div>		
				

		</div>
						
	</div>	
	-->
	
	
	<!-- 
	<div width="100%" style="position:relative; background-color: transparent; vertical-align: top; padding: 10px;">				
		<div style="clear:both; position=relative; " align="left">	

							
					<div class="panel_title ui-corner-top">
						<center><b>
							<font class="font_title">
						<%=n.getTitulo() %></font></b></center>
											
					</div>
					<div class="panel_content ui-corner-bottom" style = "padding:10px;">
															
						<%=n.getResumo() %>
					
						
						<br>... <a href="" onclick="jqueryOpen('news.html?newsid=<%=n.getId() %>','contentDiv'); return false;" style="cursor: pointer;">Saiba mais ...</a>
					</div>		
				

		</div>
						
	</div>	
	<br>
	-->
	<%} %>
	
	<div class="soltuionsTabbedPane">				
		<%@include file="solucoes.htm" %>
	</div>
	<br>
	<!-- 
	<div style="position=relative;" align="left">	

	
		<table cellpadding="0" cellspacing="0" width="100%" height="50" style="background-color: transparent;">
	  		<tr>
				<td  width="260px" valign="top">	
					
								
		
		
		<div style="width:245px; background-color: transparent;
			vertical-align: top; padding-left:10px;" >
			<div style="padding-top: 5px; ">				
				<div class="panel_title ui-corner-top" >
						<center><Font class="font_title"><b>Pr&oacute;ximos Treinamentos</b></Font></center>
											
				</div>
				<div class="panel_content ui-corner-bottom">						
					<div id="proxTreinamentos"  style="padding:10px; overflow: inherit;">
									
					</div>
					<script> jqueryOpen('schedtrainingcontentintro.html','proxTreinamentos'); </script>	
				</div>		
			</div>					
				
			<div style="padding-top: 5px;">				
				<div class="panel_title ui-corner-top">
						<center><Font class="font_title"><b>Filtro de Forma&ccedil;&otilde;es</b></Font></center>											
				</div>
				<div id="formacaoFilterPanel" class="panel_content ui-corner-bottom">
				
				</div>	
				<script> jqueryOpen('formacaoFilterPanel.html','formacaoFilterPanel'); </script>	
			</div>								
				
								
		</div>
		<br>
			</td>
			<td valign="top">
				<div id="proxNews" style="padding-bottom:10px; overflow: inherit;">
				</div>
				
				<script type="text/javascript" language="javascript">javascript: jqueryOpen('larger_last_news.html','proxNews'); </script>	
				
				<br>
			</td>
		</tr>
		</table>	
	</div>
	-->
	<!--  			
	<div width="100%" style="position:relative; background-color: transparent; vertical-align: top; position=relative;">				
				
		<div dojoType="dijit.layout.ContentPane" href="larger_last_news.html" style="padding-bottom:10px; overflow: inherit;">
					<script type="dojo/connect" event="onLoad">
       						console.debug("onload de news ... carregando dados");									
					</script>
		</div>							
	</div>	
	-->		
	

</div>