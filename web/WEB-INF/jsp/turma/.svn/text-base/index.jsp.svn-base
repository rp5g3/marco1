<LINK rel="stylesheet" type="text/css" href="css/default.css"/>
<LINK rel="stylesheet" type="text/css" href="css/jt_DialogBox.css"/>
<script type="text/javascript" src="js/jt_DialogBox.js"></script>
<script type="text/javascript" src="js/MyApp_dialogs.js"></script>

<script type="text/javascript" src="js/leilao.js"></script>   
<script type="text/javascript" src="js/wz_tooltip.js"></script>

<style>
<!-- /* Style Definitions */
td.newsLeftTitle {
	background-image: url(imgs/newsleftcorner.png);
	background-repeat:no-repeat;
	width: 18px;
	height: 21px;
	border-color: black;
	border-right-width: 0px;
	border-bottom-width: 0px;
	border-top-width: 0px;
	border-left-width: 0px;
}

td.leiloesLeftTitle {
	background-image: url(imgs/leiloesleftcorner.png);
	background-repeat:no-repeat;
	width: 18px;
	height: 21px;
	border-color: black;
	border-right-width: 0px;
	border-bottom-width: 0px;
	border-top-width: 0px;
	border-left-width: 0px;
}

td.userLeftTitle {
	background-image: url(imgs/userleftcorner.png);
	background-repeat:no-repeat;
	width: 18px;
	height: 21px;
	border-color: black;
	border-right-width: 0px;
	border-bottom-width: 0px;
	border-top-width: 0px;
	border-left-width: 0px;
}

td.defaultLeftTitle {
	background-image: url(imgs/menuleftcorner4.png);
	background-repeat:no-repeat;
	width: 12px;
	height: 21px;
	border-color: black;
	border-right-width: 0px;
	border-bottom-width: 0px;
	border-top-width: 0px;
	border-left-width: 0px;
}

td.newsCenterTitle {
	background-image: url(imgs/menubk3.png);
	width: 158px;
	height: 21px;
	border-color: black;
	border-right-width: 1px;
	border-bottom-width: 0px;
	border-top-width: 0px;
	border-left-width: 0px;
	color: white;
	border-style: solid;
	font-family: Arial;
	font-size: 12px;
	vertical-align: middle;
}

td.newsContent {
	background-color: transparent;
	border-bottom-color: black;
	border-bottom-width: 1px;
	border-left-color: black;
	border-left-width: 1px;
	border-right-color: black;
	border-right-width: 1px;
	border-top-color: black;
	border-top-width: 1px;
	border-style: solid;
	height: 100%;
}
-->
</style>

<table width="745px" border="0" align="center" cellpadding="0"
		cellspacing="0" style="background-color: transparent; vertical-align: top; ">
		
		<tr>			
			<td width="560px" style="background-color: transparent; padding-top: 10px; vertical-align: top; " >				

				<table  border="0" align="center"  cellpadding="0"
					cellspacing="0" 
					style="background-color: transparent; vertical-align: top; " width="100%">
					<tr>			
						<td width="100%" style="background-color: transparent;">			
							<div id="leilaoContentDiv" onUnload="overlay.hide();"
							 	dojoType="dijit.layout.ContentPane" 
								bindArgs="{sync: true, preventCache: false}" 
								style="position=relative; width=100%; height=100%; overflow: inherit; padding-left:2px; padding-bottom:10px;">																		    	
								
								<%
									String pagina = "";
									pagina = (String) request.getAttribute("pagina");
									if (pagina != null && !pagina.equals("")){
								%><jsp:include page="<%=pagina%>" /><%
										
									}
								%>
							</div>
						</td>						
					</tr>
				</table>
				
				
											
			</td>
										
			
			<td width="152px"  style="background-color: transparent;
			vertical-align: top; padding-top: 5px; padding-left:10px;" >							 
				
				<table border="0" cellpadding="0"
					cellspacing="0" 
					style="background-color: transparent; padding-top:5px; vertical-align: top; ">
					<tr>	
						<td  class="leiloesLeftTitle" ></td>		
						<td class="newsCenterTitle" >
							<center><b>Filtro de Turmas</b></center>
						</td>						
					</tr>
					<tr>
						<td colspan="2" class="newsContent" style="padding:3px;">						
						<table border="0" cellpadding="0"
							cellspacing="2" 
							style="background-color: transparent; padding-top:5px; vertical-align: top; ">
							<tr><td colspan="2" >
							<a href="" onclick="jqueryOpen('historicoTurmas_.html','leilaoContentDiv'); return false;">Hist&oacute;rico de Turmas</a>	
							<br><a href="" onclick="jqueryOpen('turmassOnline_.html','leilaoContentDiv'); return false;">Turmas online</a>
							<br><a href="" onclick="jqueryOpen('turmassPresenciais_.html','leilaoContentDiv'); return false;">Turmas presenciais</a>
							<br><a href="" onclick="jqueryOpen('contentintro_.html','leilaoContentDiv'); return false;">Todas as turmas</a>
							</td></tr>
							<tr>	
								<td style="width: 16px; height: 16px;">
								<img src="imgs/magnifier.png" style="cursor: hand;	cursor: pointer;" 
									onClick="ajaxdoPost('PesquisaProdLikeDescForm2','listTreinamentoByDescricao.html','contentDiv'); return false;"/>
					
								</td>
								<td style="width: 110px; height: 15px;">
								<FORM method="post" id="PesquisaProdLikeDescForm2" name="PesquisaProdLikeDescForm2" action="listTreinamentoByDescricao.html"
				      					onsubmit="ajaxdoPost('PesquisaProdLikeDescForm2','listTreinamentoByDescricao.html','contentDiv'); return false;" >
				      				<INPUT type="text" name="descricaoProdutoTextField" 
									dojoType="dijit.form.ValidationTextBox"
									trim="false" 
									required="true"
									invalidMessage="Informe a descri&ccedil;&atilde;o do item para pesquisar os treinamentos"
									onfocus="this.value = '';" value=""
									style="width:110px; height:15px" />								
								</FORM>
								</td>
							</tr>
						</table>
						</td>
					</tr>									
				</table>			
				
			</td>			
		</tr>
		
		
</table>
