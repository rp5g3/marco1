<%@page import="com.adapit.portal.util.global.GlobalVariables"%>
<%
GlobalVariables.countApresentacaoTab++;
%>


			<table border="0" align="left" cellpadding="0" cellspacing="0" width="150px" style="background-color: transparent;">
				<tr>	
					<td align="center"  height="35px"
					  width="150px" style="padding-left: 0px; padding-right: 0px; padding-bottom: 0px; padding-top: 0px; background-color: transparent;">
						<font color="#333333" size="+1" face="ARIAL">
							Produtos
						</font>
					</td>							
				</tr>
				<tr>
					<td align="left" width="150px" style="padding-left: 0px; padding-right: 0px; padding-bottom: 10px; padding-top: 0px; background-color: transparent;">
							<%@include file="menu.jsp" %>
					</td>								
				</tr>							
			</table>	
			<table border="0" align="left" cellpadding="0" cellspacing="0" width="622px" style="background-color: transparent;">
				<tr>
					<td align="left" width="622px"  bgcolor="#FFFFFF">
						<div id="contentProdutosDiv" onUnload="overlay.hide();"
						dojoType="dijit.layout.ContentPane" bindArgs="{sync: true, preventCache: false}" style="position=relative; width=100%; height=100%; overflow: inherit;">								
														
							<table style="background-color: transparent;" border="0" width="100%" cellspacing="0" cellpadding="0">
							 <tr>	 		 	
							 	<td width="31px" height="35px" align="left" background="imgs/title_top_border_left.gif">		 	
							 	</td>
							 	<td height="35px" align="center" class="bordaTop">
								 	<table style="background-color: transparent;" border="0" cellspacing="0" cellpadding="0">
										 <tr>				 		
										 	<td width="34px" height="35px" align="left" background="imgs/title_left_border_bg.gif">
											 	&nbsp;
										 	</td>			 
										 		 
							 				<td align="center" background="imgs/title_border_bg.gif" height="35px">
											 	<p class="ContentTitle"><b>Work CASE Toolkit</b></p>
										 	</td>
										 				
										 	<td width="34px" height="35px" align="right" background="imgs/title_rigth_border_bg.gif">
											 	&nbsp;
										 	</td>
							 			</tr>
							 		</table>
							 	</td>
							 	<td width="31px" height="35px" align="right" background="imgs/title_top_border_right.gif">		 	
							 	</td>	 	
							 </tr>
						 </table>
						 <table style="background-color: transparent;" border="0" width="100%" cellspacing="0" cellpadding="0">
							 <tr>
							 	<td class="bordasNotTop">
								 	<%@include file="wct.htm" %>
							 	</td>	 	 	
							 </tr>
						 </table>
							
									
						</div>
					</td>								
				</tr>							
			</table>	
			
	

