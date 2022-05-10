<%@page import="com.adapit.portal.entidades.*" %>
<%@page import="com.workcase.utils.*" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jstl/core"%>
<%@taglib prefix="validation" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@taglib prefix="binder" uri="http://www.springframework.org/tags"%>

<%@page import="com.workcase.utils.IdGenerator"%>
<%@page import="com.adapit.portal.dto.UsuarioDTO"%>
<%@page import="java.util.Vector"%>
<%@page import="java.util.Hashtable"%>
<%@page import="java.util.List"%>
<%@page import="com.adapit.portal.util.global.GlobalVariables"%>
	

<%	

    UsuarioDTO usheader = (UsuarioDTO) request.getSession(true).getAttribute("user"); 
	String randcart = IdGenerator.getInstance().generateId(3);
%> 
	<table width="780px" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>			
			<td style="width: 780px; height: 77px; background-color:#F3F3F3;" 
			    background="imgs/top_header.png" align="right">
			    <!-- 
			    <table width="778px" border="0" align="center" cellpadding="0"
					cellspacing="0" style="background-color: transparent;">	
					<tr>
						<td width="778px" height="20px" style="background-color: transparent;" colspan="2">
							&nbsp;
						</td>
					</tr>	
					<tr>
						<td width="420px" height="57" style="background-color: transparent;">
							&nbsp;
						</td>
						<td width="348px" height="57" style="background-color: transparent;">
							<%@include file="fisheye.jsp"%>
						</td>
			
					</tr>
				</table>
				 -->
				 <%if (usheader != null){ %>
				 <div dojoType="dijit.form.DropDownButton">
						<span><img src="imgs/cart.png"/>Confira as suas compras</span>						
						<div dojoType="dijit.TooltipDialog" id="tooltipDlg<%=randcart %>">
						<table border="0" cellpadding="0" width="100%"
							cellspacing="2" 
							style="background-color: white; padding-top:5px; vertical-align: top; ">
							<tr>
								<td valign="top" align="right">
								<button
								 dojoType="dijit.form.Button" 
								 type="button"  
								 height="26px" width="80px" 
								 value="Atualizar a Lista" 
								 onclick="if(!pararAtualizar) setTimeout('refreshShoppingCart()',0); else{pararAtualizar = false; refreshShoppingCart(); pararAtualizar = true; }">
								Atualizar a Lista</button>
								</td>
							</tr>
						</table>
						<table width="500px" border="0" align="center" cellpadding="0"
							cellspacing="0" style="background-color: white;">	
							<tr><td>
								<div dojoType="dijit.layout.ContentPane" 
									id="minhasComprasDiv"
									href="minhasCompras.html" 
									style="padding:10px; overflow: inherit;"
									postCreate="if(!pararAtualizar) setTimeout('refreshShoppingCart()',refreshTime * 1000);">
										<script type="dojo/connect" event="onLoad">
       							 		console.debug("onload de minhas compras ... carregando dados");									
									</script>
								</div>
								</td>
							</tr>
						</table>				
					</div>
						
				</div>
				<%} %>
			</td>				
		</tr>		
	</table>
