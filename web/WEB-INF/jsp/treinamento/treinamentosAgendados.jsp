<%@page import="com.adapit.portal.entidades.*" %>
<%@page import="com.workcase.utils.*" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="validation" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="binder" uri="http://www.springframework.org/tags"%>

<%@page import="com.workcase.utils.IdGenerator"%>
<%@page import="com.adapit.portal.dto.UsuarioDTO"%>
<%@page import="java.util.Vector"%>
<%@page import="com.adapit.portal.util.global.GlobalVariables"%>
	

<%	
    Vector<Treinamento> lotes = (Vector<Treinamento>) request.getAttribute("treinamentosList");
    Vector<Treinamento> lotesMarcados = (Vector<Treinamento>) request.getAttribute("treinamentosMarcados");
    int containerPrincipalWidth=GlobalVariables.ContainerPrincipalWidth;
    int containerPrincipalHeight=GlobalVariables.ContainerPrincipalHeight;
    int maxRowNumberLotes=8;
    String random = IdGenerator.getInstance().generateId(3);
    UsuarioDTO user = (UsuarioDTO) request.getSession(true).getAttribute("user");
        	
%>


       
      <style type="text/css">
        <!--
        
		div.compLabel1div{
			position:absolute;
			width:150px; height:20px;
			left:0; top:0;
		}
		div.containerPrincipaldiv{
			position:relative;
			width:100%; height:280px;
			left:0; top:0;
			background-color: #FFFFFF;
		}
		div.titledPaneldiv{
			position:absolute;
			width:<%=containerPrincipalWidth-2%>px; height:25px;
			border-bottom:3px #FFFFFF dashed;
			left:0; top:0;
			background-color: #FF2335;
		}
		div.lotesTableScrollPanediv{
			position:absolute;
			width:<%=containerPrincipalWidth-10%>px; height:<%=containerPrincipalHeight-60%>px;
			left:0; top:35;
			padding:4px;
			background-color: #FF2335;
		}
		div.compContainerScreenArea2div{
			position:absolute;
			width:180px; height:25px;
			left:0; top:0;
		}
		div.iniciadoNoValorValordiv{
			position:absolute;
			width:150px; height:20px;
			left:20; top:5;
		}
		div.lanceVencendodiv{
			position:absolute;
			width:150px; height:20px;
			left:20; top:25;
		}
		div.numeroLancesLabeldiv{
			position:absolute;
			width:150px; height:20px;
			left:20; top:45;
		}
		div.valoresPaneldiv{
			position:absolute;
			width:180px; height:70px;
			left:0; top:25;
		}
		div.dadosStatusPaneldiv{
			position:relative;
			width:181px; height:97px;
			left:0; top:0;
		}
		div.segundaChamadaPaneldiv{
			position:absolute;
			width:247px; height:25px;
			left:0; top:0;
		}
		div.inicioOnlinePaneldiv{
			position:absolute;
			width:210px; height:25px;
			left:20; top:5;
		}
		div.inicioPresencialPaneldiv{
			position:absolute;
			width:210px; height:25px;
			left:20; top:30;
		}
		div.inicioAgendaPaneldiv{
			position:absolute;
			width:247px; height:64px;
			left:0; top:25;
		}
		div.dadosAgendaPaneldiv{
			position:relative;
			width:248px; height:90px;
			left:0; top:0;
		}
		div.descricaoTitlePaneldiv{
			position:absolute;
			width:300px; height:25px;
			left:0; top:0;
			background-image: url("../imgs/tablebackground.png");
		}
		div.descricaoTexoPaneldiv{
			position:absolute;
			width:300px; height:100%;
			left:0; top:25;
		}
		div.descricaoPaneldiv{
			position:relative;
			width:300px; height:100%;
			left:0; top:0;
		}
		div.produtosTitlePaneldiv{
			position:absolute;
			width:250px; height:25px;
			left:0; top:0;
			background-image: url("../imgs/tablebackground.png");
		}
		div.produtosTexoPaneldiv{
			position:absolute;
			width:250px; height:110px;
			left:0; top:25;
		}
		div.produtosPaneldiv{
			position:relative;
			width:250px; height:136px;
			left:0; top:0;
		}
		div.interessadosTitlePaneldiv{
			position:absolute;
			width:250px; height:25px;
			left:0; top:0;
			background-image: url("../imgs/tablebackground.png");
		}
		div.interessadosTexoPaneldiv{
			position:absolute;
			width:250px; height:110px;
			left:0; top:25;
		}
		div.interessadosPaneldiv{
			position:relative;
			width:250px; height:136px;
			left:0; top:0;
		}
		div.ContainerScreeArea0div{
			position:absolute;
			width:488px; height:251px;
			left:0; top:0;
		}
		
		/*Sample CSS used for the Virtual Pagination Demos. Modify/ remove as desired*/
		
		.virtualpage, .virtualpage2, .virtualpage3{
		/*hide the broken up pieces of contents until script is called. Remove if desired*/
		display: none;
		}
		
		.paginationstyle{ /*Style for demo pagination divs*/
		width: 250px;
		text-align: center;
		padding: 2px 0;
		margin: 10px 0;
		}
		
		.paginationstyle select{ /*Style for demo pagination divs' select menu*/
		border: 1px solid navy;
		margin: 0 15px;
		}
		
		.paginationstyle a{ /*Pagination links style*/
		padding: 0 5px;
		text-decoration: none;
		border: 1px solid black;
		color: navy;
		background-color: white;
		}
		
		.paginationstyle a:hover, .paginationstyle a.selected{
		color: #000;
		background-color: #DBE98D;
		}
		
		.paginationstyle a.imglinks{ /*Pagination Image links style (class="imglinks") */
		border: 0;
		padding: 0;
		}
		
		.paginationstyle a.imglinks img{
		vertical-align: bottom;
		border: 0;
		}
		
		.paginationstyle a.imglinks a:hover{
		background: none;
		}
		
		.paginationstyle .flatview a:hover, .paginationstyle .flatview a.selected{ /*Pagination div "flatview" links style*/
		color: #000;
		background-color: #B7DC82;
		}
	

        -->
      </style>

  	<script type="text/javascript" src="js/wz_tooltip.js"></script>
    <script type="text/javascript" src="js/virtualpaginate.js"></script>

    
    <%
    if (lotes == null || (!lotes.iterator().hasNext())){
    	out.print("<br><br><br><br><br><center>N&atilde;o foram encontrados treinamentos para a consulta</center><br><br>");
    }else{
		int leilaoId=0;
    				
    //if (lotes != null && lotes.iterator().hasNext()){
	   	java.util.Iterator<Treinamento> lit = lotes.iterator();
	   	while (lit.hasNext()){
	   		Treinamento lote = lit.next();
	   		leilaoId = lote.getTurma().getId();	   		
	   		
	%>
	
	<%try{ %>
	
	<div id="descriptionLoteDialog<%=lote.getId()+random%>" 
		dojoType="dijit.Dialog" title="Descri&ccedil;&atilde;o do Treinamento <%=lote.getResumo() %>" 
		style="display:none; width: 400px; height: 200px;  overflow: inherit; background-color:#FFFFFF;" 
		bindArgs="{sync: true, preventCache: false}">
		
			
		<%=lote.getTreinamento().getUtilizarFormatador()?lote.getTreinamento().getFormatedDescricao():lote.getTreinamento().getDescricao() %>
	
			
		
	</div>
	<%}catch(Exception ex){ex.printStackTrace();} %>
	<%
   	}
	%>		      
			<div dojoType="dijit.layout.TabContainer" id="treinamentosTabbedPane<%=leilaoId %>" sizeShare="40" style="width:100%; height:350px;" cacheContent="false">
				<div id="lotesTab" dojoType="dijit.layout.ContentPane" title="Agenda" style="padding:10px;display:none;" cacheContent="false">		
					<div class="containerPrincipaldiv" >
							
								<%
									
								int pageCountLotes=1;
								int rowNumberLotes=lotes.size();
								int pageNumberLotes=1;
								if (rowNumberLotes > maxRowNumberLotes){
									pageNumberLotes = rowNumberLotes/maxRowNumberLotes;
									int resto = rowNumberLotes%maxRowNumberLotes;
									if (resto > 0) pageNumberLotes++;
								}
								int itemCountLotes=0;
								java.util.Iterator<Treinamento> it= lotes.iterator();
								for (int p = 0; p < pageNumberLotes; p++){
									
									
								%>
								<div class="lotesvirtualpage<%=leilaoId%>" style="position: absolute; top: 0; left: 0; width:100%;">
									<table border=0 cellpadding=0 cellspacing=0 bordercolor=#000000 width="100%" style="background-color:transparent;" >
										<tr>
											<th width="5px" height="20" bgcolor="#999999">&nbsp;</th>
											<th width="20%" height="20" bgcolor="#999999"><center><b>Datas</b></center></th>
											<th width="50px" bgcolor="#999999" height="20"><center><b>C&oacute;digo</b></center></th>
											<th width="<%=(user != null)?"60":"70" %>%" bgcolor="#999999" height="20"><center><b>Treinamento</b></center></th>											
											<% if (user != null) {%>
											<!--<th width="10%" height="20" bgcolor="#999999"><b>Inscreva-se</b></th>
											--><%} %>
										</tr>		
											<%
											int count=0;
											
											for (int i=0; (i < maxRowNumberLotes) && it.hasNext(); i++){
											//if (lotes != null && lotes.iterator().hasNext()){
								      			//java.util.Iterator<Lote> lit = lotes.iterator();
								      			//while (lit.hasNext()){
								      				//Lote lote = lit.next();
								      			try{
									      			Treinamento lote = it.next();//lotes.get(itemCountLotes);
									      			if (lote.getTurma() != null) leilaoId = lote.getTurma().getId();
									      			else leilaoId=p;
									      			count++;
								      		%>	
										      		
								      			<TR bgcolor="<%=(count%2>0)?"#BBBBBB":"#CCCCCC" %>" onMouseOver="this.bgColor='AAAAAA';" onMouseOut="this.bgColor='<%=(count%2>0)?"#BBBBBB":"#CCCCCC" %>';">
													<td width="16px" height="16px"><img src="imgs/
											<%
												if(lote.getAgendaTreinamento().getStatus() == StatusAgenda.Confirmada)out.print("flag_green");
												else if(lote.getAgendaTreinamento().getStatus() == StatusAgenda.Cadastrada)out.print("flag_yellow");
												else out.print("flag_red");
											%>.png" title="<%
												if(lote.getAgendaTreinamento().getStatus() == StatusAgenda.Confirmada)out.print("Turma Confirmada!");
												else if(lote.getAgendaTreinamento().getStatus() == StatusAgenda.Cadastrada)out.print("Turma em planejamento! Fa&ccedil;a sua inscri&ccedil;&atilde;o");
												else out.print("Turma Cancelada!");
											%>"/></td>
													<TD >
														<center>
														<a onclick="javascript:dijit.byId('agendaDialog<%=lote.getId()+random%>').show(); return false;"
															 		title="Clique para listar detalhes da execu&ccedil;&atilde;o deste treinamento"
															 		class="aBlack" 
															 		style="cursor: hand; cursor: pointer;">
																	<Font color="Black">
															<binder:message code="format.date"  
																arguments="<%=lote.getAgendaTreinamento().getInicioPrevisto() %>"/> 
															<binder:message code="format.date"  
																arguments="<%=lote.getAgendaTreinamento().getTerminoPrevisto() %>"/>
															</Font></a>
															
														 </center>
													</TD>
													<TD >
														<a onclick="addTab('treinamentosTabbedPane<%=leilaoId %>','itens<%=lote.getCodigo() %><%=random%>','<%=lote.getTreinamento().getCodigo() %>','showDetalhesTreinamento.html?treinamento_id=<%=lote.getId()%>'); return false;"
															 		title="Clique para listar os detalhes deste treinamento"
															 		class="aBlack" 
															 		style="cursor: hand; cursor: pointer;">
														<Font color="Black">
															&nbsp;&nbsp;<%=lote.getTreinamento().getCodigo()%>
														</Font></a>														
													</TD>
													<TD >
														<center><a onclick="addTab('treinamentosTabbedPane<%=leilaoId %>','itens<%=lote.getCodigo() %><%=random%>','<%=lote.getTreinamento().getCodigo() %>','showDetalhesTreinamento.html?treinamento_id=<%=lote.getId()%>'); return false;"
															 		title="Clique para listar os detalhes deste treinamento"
															 		class="aBlack" 
															 		style="cursor: hand; cursor: pointer;">
																	<Font color="Black">
																	<% if (lote.getTreinamento().getNome().length()>200)
																		 out.print(lote.getTreinamento().getNome().substring(0,200)+"...");
																		 else out.print(lote.getTreinamento().getNome()); %> 
																</Font></a></center>
														<center>
															<!--<a onclick="javascript:dijit.byId('descriptionDialog<%=lote.getId()+random%>').show(); return false;"
															 		title="Clique para listar a descri&ccedil;&atilde;o deste treinamento"
															 		class="aBlack" 
															 		style="cursor: hand; cursor: pointer;">
																	<Font color="Blue">Descri&ccedil;&atilde;o</Font></a> |
															 
															<a onclick="javascript:dijit.byId('imageDialog<%=lote.getId()+random%>').show(); return false;"
															 		title="Clique para listar a descri&ccedil;&atilde;o deste treinamento"
															 		class="aBlack" 
															 		style="cursor: hand; cursor: pointer;">
																	<Font color="Blue">Imagens</Font></a> | -->
															<a onclick="javascript:dijit.byId('investimentoDialog<%=lote.getId()+random%>').show(); return false;"
															 		title="Clique para listar a descri&ccedil;&atilde;o deste treinamento"
															 		class="aBlack" 
															 		style="cursor: hand; cursor: pointer;"> 
																	<Font color="Blue">Investimento</Font></a> | 
															<a onclick="javascript:dijit.byId('detalhesTreinamentoDialog<%=lote.getId()+random%>').show(); return false;"
															 		title="Clique para listar os detalhes deste treinamento"
															 		class="aBlack" 
															 		style="cursor: hand; cursor: pointer;">
																	<Font color="Blue">Saiba Mais</Font></a> 
															
														</center>
													</TD>
													<%														
													if(user != null){
													%>
													<!--<TD align="center"  >
														
														<div id="inscricaoResultDiv<%=user.getId()%><%=lote.getId()%>" dojoType="dijit.layout.ContentPane" style="padding:0px;" onUnload="overlay.hide();" cacheContent="false">		
															<INPUT type="checkbox"  
															name="participarTreinamentoCheckBox<%=lote.getId()%>2" 
															style="width:16px; height:16px" 
															onClick="jqueryOpen('participarTurmaTreinamento.html?usuario_id=<%=user.getId()%>&treinamento_id=<%=lote.getId()%>','inscricaoResultDiv<%=user.getId()%><%=lote.getId()%>'); return false;"
															<%if(lotesMarcados != null && lotesMarcados.contains(lote)){ %>checked="checked"<%} %>/>
														</div>
													</TD>
													--><%} %>
												</TR>							
								      		<%
								      			}catch(Exception ex){ex.printStackTrace();}
												itemCountLotes++;
								      		}									
											%>
									</table>
								</div>
								
							<%}if (pageNumberLotes > 1){%>	
							<!-- Pagination -->
							<div parseWidget="false" id="lotesgallerypaginate<%=leilaoId%>" class="paginationstyle" style="position: absolute; top: <%=containerPrincipalHeight-65%>; width: <%=containerPrincipalWidth-10%>;">
									<a rel="previous" 
									 style="cursor: hand; cursor: pointer;"			
									 onClick="return false;">Anterior</a>
									<span class="flatview"></span>
									<a rel="next"
									 style="cursor: hand; cursor: pointer;"
									 onclick="paginate('lotesvirtualpage<%=leilaoId%>','lotesgallerypaginate<%=leilaoId%>'); return false;">Pr&oacute;xima</a>
							</div>
							<script type="text/javascript">paginate('lotesvirtualpage<%=leilaoId%>','lotesgallerypaginate<%=leilaoId%>');</script>
							<%}%>  
					</div>
				</div>		
			</div>		
				

      				
	<%
			   	}
				
	%>
<%
    if (lotes != null && lotes.iterator().hasNext()){
    	
       	java.util.Iterator<Treinamento> lit = lotes.iterator();
	   	while (lit.hasNext()){
	   		Treinamento lote = lit.next();%>

	<div id="descriptionDialog<%=lote.getId()+random%>" 
		dojoType="dijit.Dialog" title="<%=lote.getTreinamento().getNome() %>" 
		style="display:none; width: 400px; height: 200px; background-color:White;" 
		bindArgs="{sync: true, preventCache: false}"
	>
		
			<%=
				(lote.getTreinamento().getUtilizarFormatador())?
				 lote.getTreinamento().getFormatedDescricao():
				lote.getTreinamento().getDescricao() %>		
		
	</div>

	<div id="investimentoDialog<%=lote.getId()+random%>" 
		dojoType="dijit.Dialog" title="<%=lote.getTreinamento().getNome() %>" 
		style="display:none; width: 400px; height: 200px; background-color:White;" 
		bindArgs="{sync: true, preventCache: false}"
	>
		Valor &agrave; vista com desconto de 10%: <%=Moeda.getValorReal(lote.getTreinamento().getAvaliacao() - lote.getTreinamento().getAvaliacao()*0.10f )%>
		<br>Parcelado em at&eacute; 3x: <%=Moeda.getValorReal(lote.getTreinamento().getAvaliacao() )%>
		<br>Parcelado em 12x com juros de 8% a.a.: <%=Moeda.getValorReal(lote.getTreinamento().getAvaliacao()*0.8f + lote.getTreinamento().getAvaliacao()) %>
	</div>

	<%try{
			if (lote.getAgendaTreinamento() != null){
		%>
	
	<div id="agendaDialog<%=lote.getId()+random%>" 
		dojoType="dijit.Dialog" title="Agenda do Treinamento <%=lote.getTreinamento().getNome() %>" 
		style="display:none; width: 500px; height: 200px; background-color:White;" 
		bindArgs="{sync: true, preventCache: false}"
	>
		In&iacute;cio agendado para <binder:message code="format.date"  
		arguments="<%=lote.getAgendaTreinamento().getInicioPrevisto() %>"/>
		<br>Encerramento previsto para 
		<binder:message code="format.date"  
		arguments="<%=lote.getAgendaTreinamento().getTerminoPrevisto() %>"/> 
				
		<br><br>O treinamento inicia as <%= lote.getAgendaTreinamento().getHoraInicio()%> 
		<br>E encerra as <%= lote.getAgendaTreinamento().getHoraInicio()%> 
		<br><br>Intervalo: <%= lote.getAgendaTreinamento().getIntervalo()%>
		<br><br>Status do Treinamento: <%= lote.getAgendaTreinamento().getStatus().name().replace("_"," ")%>
		
		
	</div>
	<%		
			}	
		}catch(Exception ex){
	%>
	<div id="agendaDialog<%=lote.getId()+random%>" 
		dojoType="dijit.Dialog" title="Agenda do Treinamento <%=lote.getTreinamento().getNome() %>" 
		style="display:none; width: 400px; height: 200px; top:200px;  overflow: inherit;" 
		bindArgs="{sync: true, preventCache: false}"
		href="showAgendaTreinamento.html?treinamento_id=<%=lote.getId()%>"/>	
	
	<% 		
		} %>

	<div id="imageDialog<%=lote.getId()+random%>" 
		dojoType="dijit.Dialog" title="Imagens do Treinamento <%=lote.getTreinamento().getNome() %>" 
		style="display:none; width: 700px; height: 500px; top:50px;  overflow: inherit;" 
		bindArgs="{sync: true, preventCache: false}"
		href="showImagensComercialSolution.html?produto_id=<%=lote.getTreinamento().getId()%>"/>	
	
	<div id="detalhesTreinamentoDialog<%=lote.getId()+random%>" 
		dojoType="dijit.Dialog" title="Detalhes do Treinamento <%=lote.getTreinamento().getNome() %>" 
		style="display:none; width: 700px; height: 400px; top:50px;  overflow: inherit;" 
		bindArgs="{sync: true, preventCache: false}"
		href="showDetalhesTreinamento.html?treinamento_id=<%=lote.getId()%>"/>
			
<%
   	}
}
%>