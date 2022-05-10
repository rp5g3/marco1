<%@page import="com.adapit.portal.entidades.FormacaoTreinamento"%>
<%@page import="com.adapit.portal.util.global.GlobalVariables"%>						
<%@ taglib prefix="jstl" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="validation" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="binder" uri="http://www.springframework.org/tags"%>

      <style type="text/css">
        <!--
   
        <%	
        	int containerPrincipalWidth=GlobalVariables.ContainerPrincipalWidth;
        	int containerPrincipalHeight=GlobalVariables.ContainerPrincipalHeight;
        	int maxRowNumber=GlobalVariables.MaxTableRowNumber;
		
        %>
        
                		
		div.FormacaoContentPanel{
			position:relative;
			width:235px; height:150px;
			left:0; top:0;	
			border:1px #BBBBBB solid;		
		}
		
		div.FormacaoClassificLabeldiv{
			position:relative;
			width:235px; height:29px;
			left:0; top:0;						
			color:#AAAAAA;
		    	font-family:Arial,'trebuchet ms',helvetica,sans-serif;
		   	font-size:16px;
		   	font-weight:normal;			
        		vertical-align: top;
        		text-align: center;
        		border-bottom:1px #AAAAAA solid;
        		background-color:#FAFAFA;
		}
		
		div.FormacaoSubClassificLabeldiv{
			position:relative;
			width:235px; height:28px;			
			color:#000000;
		    	font-family:Arial,'trebuchet ms',helvetica,sans-serif;
		   	font-size:14px;
		   	font-weight:normal;
        		background-color:#FAFAFA;
        		vertical-align: top;
        		text-align: center;
        		border-top:1px #AAAAAA solid;
		}
		
		div.solutionImagediv{
			position:absolute;
			width:80px; height:80px;
			left:0; top:0;			
		}
		
		div.solutionComitentePaneldiv{
			position:absolute;
			width:130px; height:95px;
			left:35; top:30;
		}
		div.imageHouseDiv{
			position:absolute;
			width:20px; height:110px;
			left:15; top:50;
		}
		div.imageTimeDiv{
			position:absolute;
			width:20px; height:110px;
			left:125; top:50;
		}
		
		div.imageIntrutorDiv{
			position:absolute;
			width:20px; height:110px;
			left:125; top:80;
		}
		
		div.imageRegrasParticipacaoDiv{
			position:absolute;
			width:20px; height:110px;
			left:15; top:80;
		}
		
		div.chamadadiv{
			position:absolute;
			width:150px; height:16px;
			left:0; top:108;
		}
		div.dataOnlineLabeldiv{
			position:absolute;
			width:150px; height:16px;
			left:0; top:122;
		}
		div.dataPresencialLabeldiv{
			position:absolute;
			width:150px; height:16px;
			left:0; top:135;
		}
		
        -->
      </style>
<style type="text/css">
a.linkopacity img {
filter:alpha(opacity=50); 
-moz-opacity: 0.5; 
opacity: 0.5;}

a.linkopacity:hover img {
filter:alpha(opacity=100);   
-moz-opacity: 1.0;   
opacity: 1.0;
}
</style>
   
	  <script type="text/javascript" src="js/leilao.js"></script>   
      <script type="text/javascript" src="js/wz_tooltip.js"></script>

	
				
	<table border="0" style="background-color: transparent;" cellpadding="0" cellspacing="0" width="100%">
		<tr style="background-color: transparent;"><td colspan="3" style="background-color: transparent;">&nbsp;</td></tr>
		<%
			java.util.Vector formacaoList = (java.util.Vector) request.getAttribute("formacoes");
			if (formacaoList != null && formacaoList.size() >0)try{
	        	java.util.Iterator<FormacaoTreinamento> it = formacaoList.iterator();
	        	int countColControl=0;
	        	int countLineControl=0;

	        	while(it.hasNext()){
	        		FormacaoTreinamento formacao = it.next();
	        	%>
					  <% if (countColControl == 0){%><tr><%}%>
					  <td style="background-color: transparent;" align="center">
						<div class="FormacaoContentPanel">
							<div class="FormacaoClassificLabeldiv">
								<center>
									<font style="font-family: verdana; font-size: 11; font: bold" color="#222222">
										<%=formacao.getNome()%>
									</font>
								</center>
							</div>
							<div style="position: relative; width: 100%; height: 90px;">
								<table cellpadding="0" cellspacing="0" width="100%" height="90px" style="background-color: transparent;">
									<tr>
										<td width="25px" height="25px" bgcolor="#FFFFFF">
											<%try{
												java.util.List<Object[]> itens = (java.util.List<Object[]>) request.getAttribute("itens"+formacao.getId());
												if (itens != null && itens.size() > 0){

											%>
												<a href="#" class="linkopacity" onclick="return false;">
												<img  src='imgs/training_list.png' style="cursor: help;" onMouseOver="TagToTip('EnderecoSpan<%=formacao.getId()%>')" />
												</a>
												<span id="EnderecoSpan<%=formacao.getId()%>"  style="visibility: hidden; left: 0; top: 0; position: absolute;" >
													<table cellpadding="0" cellspacing="0" width="700" height="100" style="background-color: transparent;">
														<tr><th colspan="4" align="center" style="font-family: arial; font-size: 15; background-color:#555555"><br><center><font style="font-family: arial; font-size: 14; font: bold" color="#FFFFFF"><b>Treinamentos da Forma&ccedil;&atilde;o <%=formacao.getNome()%></b></font></center><br></th></tr>
														<tr><td>
															 <table cellpadding="0" cellspacing="0" width="700" style="border-bottom-width:1px; border-bottom-color:#999999; background-color:#444444;">
																<tr>
																	<td bgcolor="#FFFFFF">
																		<center><font style="font-family: arial; font-size: 12; font: bold" color="#991122">Carga Hor&aacute;ria Total: <%= formacao.getCargaHorariaTotal() %> horas de treinamento</font></center> 
																	</td>
																</tr>
																<tr>
																	<td bgcolor="#FFFFFF">
																		<center><font style="font-family: arial; font-size: 12; font: bold" color="#991122">Investimento: <%= com.workcase.utils.Moeda.getValorReal(formacao.getAvaliacao()) %></font></center>
																	</td>
																</tr>
															</table>
													</td></tr>
											<%		int i=0;
													for(Object[] objs: itens){
														i++;
											%>
													
													<tr><td width="100%">
													   <table cellpadding="0" cellspacing="0" width="700" style="border-bottom-width:1px; border-bottom-color:#444444; background-color:#<%=(i%2==0)?"AAAAAA":"888888"%>;">
														<tr>
															<td width="100px" style="background-color:transparent;">
																<Label>Treinamento: </Label>
															</td>
															<td style="font-family: arial; font-size: 11" colspan="3" style="background-color:transparent;" >
																<%=objs[1]%>
															</td>
														
															<td width="140px" style="background-color:transparent;">
																<Label>Carga Hor&aacute;ria: </Label>
															 &nbsp;&nbsp;<font style="font-family: arial; font-size: 11">
																<%=objs[2]%> horas</font>
															</td>														
															<td width="170px" style="background-color:transparent;">
																<Label>Valor Individual: </Label>
															&nbsp;&nbsp;<font style="font-family: arial; font-size: 11">
																	<%=com.workcase.utils.Moeda.getValorReal((Float)objs[3])%></font>
															</td>
														</tr>
														<tr>
															<td width="100px" style="background-color:transparent;">
																<Label>Objetivo: </Label>
															</td>
															<td style="font-family: arial; font-size: 11" colspan="5" style="background-color:transparent;">
																<%=objs[4]%>
															</td>
														</tr>
													    </table>
													</td></tr><tr><td width="100%" style="background-color:#CCCCCC; height:2px;"></td></tr>
													<%} %>														
													</table>
												</span>						   
											<%}}catch(Exception e){e.printStackTrace();}%>
										</td>
										
										<td width="100%" rowspan="3" bgcolor="#FFFFFF" align="center" valign="middle">
											<table cellpadding="0" cellspacing="0" width="90" height="90">
												<tr>
													<td align="center" valign="middle" bgcolor="#FFFFFF" >
														<%try{if (formacao.getImagem() != null){%>
														<a class="linkopacity"
														onclick="jqueryOpen('showCatalogoFormacao_.html?formacao_id=<%=formacao.getId()%>','treinamentoContentDiv'); return false;"
														
														style="cursor: hand; cursor: pointer;"
														><img src='showImageByImageIdView.html?image_id=<%=formacao.getImagem().getId()%>&image_format=<%=formacao.getImagem().getFormat() %>' title="Clique para visualizar os treinamentos agendados nesta forma&ccedil;&atilde;o" border="0"/></a>
														<%}}catch(Exception ex){ex.printStackTrace();}%>
													</td>
												</tr>
											</table>
										</td>
										
										<td width="25px" height="25px" bgcolor="#FFFFFF">
										<%try{ %>
											<a href="#" class="linkopacity" onclick="return false;">
												<img class="linkopacity" src='imgs/formacoes.png' style="cursor: help;" onMouseOver="TagToTip('TimeSpan<%=formacao.getId()%>')" />
											</a>
											<span id="TimeSpan<%=formacao.getId()%>"  style="visibility: hidden; left: 0; top: 0; position: absolute;" >
												<table cellpadding="0" cellspacing="0" width="500" height="50">
													<tr><th colspan="4" align="center" style="font-family: arial; font-size: 15; background-color:#555555"><br><center><font style="font-family: arial; font-size: 14; font: bold" color="#FFFFFF">Objetivos da Forma&ccedil;&atilde;o <%=formacao.getNome()%></font></center><br></th></tr>

														<tr>
															<td bgcolor="#FFFFFF" style="font-family: arial; font-size: 11">
																<%= formacao.getDescricao() %>
															</td>
														</tr>												
													
													</table>
											</span>						   
										<%}catch(Exception ex){ex.printStackTrace();}%>
										</td>
									</tr>
									<tr>
										
										<td width="25px" height="25px" bgcolor="#FFFFFF">&nbsp;</td>
										
										<td width="25px" height="25px" bgcolor="#FFFFFF">&nbsp;</td>
									</tr>
									
								</table>	
								
							</div>
							<div class="FormacaoSubClassificLabeldiv">
								<%try{ %>
								<center>
								<font style="font-family: verdana; font-size: 11; font: bold" color="#222222">
								Tecnologia: <%=formacao.getTechnology().name().replaceAll("_"," ")%>
								</font>
								</center>
								<%}catch(Exception ex){ex.printStackTrace();} %>
							</div>
							
						</div>
						</td>
					<% if (countColControl == 1 || formacaoList.size() == 1 || formacaoList.size() == 2){%>
					</tr>
					<tr ><td colspan="2" >&nbsp;</td></tr>
					<%}

					if (!it.hasNext()){%><%}%>
					<%
					   if (countColControl == 1){
							countLineControl++;
							countColControl=0;
					   }
					   else countColControl++;
					}//fim da itera&ccedil;&atilde;o nos leil&otilde;es
			        
			     }catch(Exception ex){ex.printStackTrace();}//fim (se n&uacute;mero de leil&otilde;es > 0)%>
			     <tr  ><td colspan="3" >&nbsp;</td></tr>
				</table>		
						
		
