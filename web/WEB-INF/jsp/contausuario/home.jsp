<table width="780px" border="0" align="center" cellpadding="0"
	cellspacing="0" background="imgs/categoria_bg.gif"
	style="background-color: transparent;">

	<tr valign="top">
		<td width="2px"/>
		<td width="774px" class="bordasBlack" align="center" >			
			<!-- ################################# Janela de Conte&uacute;do ################################### -->
					
			<table border="0" align="left" cellpadding="0" cellspacing="0" width="774px" style="background-color: transparent;">
				<tr>
					<td align="left" width="774px" style="padding-left: 0px; padding-right: 0px; padding-bottom: 0px; padding-top: 10px;" bgcolor="#FFFFFF">
						<div id="contentDiv" dojoType="dijit.layout.ContentPane"
						onUnload="overlay.hide();"
						 bindArgs="{sync: true, preventCache: false}" 
						 style="position=relative; width=100%; height=100%; overflow: inherit;">								
							<%@include file="home_welcome.jsp" %>										
						</div>
					</td>								
				</tr>							
			</table>				
			
			<!-- ############################ Final da Janela de Conte&uacute;do ############################### -->
		</td>
		<td width="2px"/>
	</tr>
</table>