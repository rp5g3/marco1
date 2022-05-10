<%@taglib prefix="jstl" uri="http://java.sun.com/jstl/core"%>
<%@taglib prefix="validation" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@taglib prefix="binder" uri="http://www.springframework.org/tags"%>	
<%@page import="com.adapit.portal.entidades.*"%>
<%@page import="java.util.*"%>
<%@page import="com.adapit.portal.dto.LoteReportDTO"%>
<%@page import="com.adapit.portal.dto.UsuarioDTO"%>
<%@page import="com.adapit.portal.services.PersonType"%>
<LINK rel="stylesheet" type="text/css" href="css/default.css"/>
<LINK rel="stylesheet" type="text/css" href="css/jt_DialogBox.css"/>
<LINK rel="stylesheet" type="text/css" href="css/leilao.css"/>  
<script type="text/javascript" src="js/leilao.js"></script> 
<script type="text/javascript" src="js/jt_DialogBox.js"></script>
<script type="text/javascript" src="js/MyApp_dialogs.js"></script>
<script type="text/javascript" src="js/wz_tooltip.js"></script>
<script type="text/javascript" src="js/virtualpaginate.js"></script>

<style>
<!-- /* Style Definitions */
td.leiloesLeftTitleLarge {
	background-image: url(imgs/leiloesleftcorner.png);
	background-repeat:no-repeat;
	width: 17px;
	height: 21px;
	border-color: black;
	border-right-width: 0px;
	border-bottom-width: 0px;
	border-top-width: 0px;
	border-left-width: 0px;
}

td.newsContentLeftImg {
	background-image: url(imgs/bullet_feed.png);
	background-repeat:no-repeat;
	width: 16px;
	height: 16px;
	border-width: 0px;
}

td.newsCenterTitleLarge {
	background-image: url(imgs/menubk3.png);
	width: 560px;
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

td.newsContentLarge {
	background-color: transparent;
	border-width: 0px;
	padding-left: 5px;
}
-->
</style>
<style>
<!--
 /* Style Definitions */
 p.MsoNormal, li.MsoNormal, div.MsoNormal
	{margin:0cm;
	margin-bottom:.0001pt;
	font-size:10.0pt;
	font-family:Arial;}
p.MsoBodyText2, li.MsoBodyText2, div.MsoBodyText2
	{margin:0cm;
	margin-bottom:.0001pt;
	text-align:justify;
	font-size:10.0pt;
	font-family:Arial;}
	-->
</style>


				<table border="0" cellpadding="0" width="100%"
					cellspacing="0" style="background-color: transparent;">
					<tr>	
						<td class="leiloesLeftTitleLarge"></td>		
						<td class="newsCenterTitleLarge">
							&nbsp;&nbsp;<b>Bem Vindo! Dados de Conta de Usu&aacute;rio</b>							
						</td>						
					</tr>
					<tr>
						<td colspan="2" class="newsContent" >	
							<table width="100%" align="center" border="0" cellspacing="0"
								cellpadding="0" style="background-color: transparent;" >
								<tr valign="top">
									<td height="20" style="background-color: transparent;">
										<div align="center">
										<%
											UsuarioDTO usuario =(UsuarioDTO) session.getAttribute("user");	
											java.util.ArrayList<LoteReportDTO> lotes = (java.util.ArrayList<LoteReportDTO>) request.getAttribute("myLoteList"); 
											//Participante participante =null;
											//if (usuario.getDadosPessoais() instanceof Participante){
											//	participante = (Participante) usuario.getDadosPessoais();
											//}
										%>
											<br><br><font color="#000000"><% out.print(usuario.getWelcomeName()); %>, Bem Vindo ao Sistema de Usu&aacute;rios da Adapit</font>
										</div>
									</td>
								</tr>
								<tr valign="top">
									<td>
										<div align="center" >
											<table border="0" cellspacing="1" cellpadding="1" width="100%" style="background-color: transparent;">
												<tr>
													<td colspan="4" style="background-color: transparent;" align="center">
													<p>
														<%
															java.util.Date d = (java.util.Date) request.getAttribute("lastAccess");
															if (d != null){					
														%>
															<font color="RED">Seu &uacute;ltimo acesso ocorreu em <binder:message code="format.date"  arguments="<%= d %>"/> as <% out.print(d.getHours()+":"+d.getMinutes());%></font>
														<%}else out.print("Primeiro Acesso! ");%>			
													</p>
													
													</td>	
												</tr>
											</table>
											<div dojoType="dijit.TitlePane" title="Minha Conta e Meus Acessos" width="100%" >
												<table border="0" cellspacing="0"
													cellpadding="0" style="background-color: transparent;">	
													<tr>
														<td colspan="4" align="center" style="background-color: transparent;">
															<a href="minhasPreferencias.html"><font color="Black">Minhas prefer&ecirc;ncias</font></a>			
														</td>	
													</tr>
													<tr>
														<td colspan="4" align="center" style="background-color: transparent;">									
															<a style="cursor: hand;	cursor: pointer;"
															<%if(usuario != null){%>onClick="jqueryOpen('<%if (usuario.getTipoPessoa() == PersonType.Fisica)	out.print("pessoaFisicaCadastreForm");	else out.print("pessoaJuridicaCadastreForm");%>.html?id_usuario=<% out.print(usuario.getId());%>&ticket=<% out.print(usuario.getTicket());%>','leilaoContentDiv'); return false;"<%} else out.print("href=\"\"");%>><font color="Black">Editar meus dados pessoais</font></a>			
														</td>	
													</tr>
													<tr>
														<td colspan="4" align="center" style="background-color: transparent;">
															<a href="historicoAcessos.html"><font color="Black">Hist&oacute;rico de acessos</font></a>			
														</td>	
													</tr>								
												</table>
											</div>
											
											
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>									
				</table>