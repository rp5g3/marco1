<%@page import="com.adapit.portal.entidades.*" %>
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
    //int containerPrincipalWidth=GlobalVariables.ContainerPrincipalWidth;
    //int containerPrincipalHeight=GlobalVariables.ContainerPrincipalHeight;
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
			<div dojoType="dijit.layout.TabContainer" id="lotesTabbedPane<%=leilaoId %>" sizeShare="40" style="width:100%; height:350px;" cacheContent="false">
				<div id="lotesTab" dojoType="dijit.layout.ContentPane" title="Treinamentos Agendados" style="padding:10px;display:none;" cacheContent="false">		
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
								java.util.Iterator<Treinamento> trainit= lotes.iterator();
								for (int p = 0; p < pageNumberLotes; p++){
									
									
								%>
								<div class="lotesvirtualpage<%=leilaoId%>" style="position: absolute; top: 0; left: 0; width:100%;">
									<table border=0 cellpadding=0 cellspacing=0 bordercolor=#000000 width="100%" style="background-color:transparent;" >
										<tr>
											<th width="30%" height="20" bgcolor="#999999"><center><b>Datas</b></center></th>
											<th width="<%=(user != null)?"60":"50" %>%" bgcolor="#999999" height="20"><center><b>Treinamento</b></center></th>
											
											<% if (user != null) {%>
											<th width="10%" height="20" bgcolor="#999999"><b>Inscrever-se</b></th>
											<%} %>
										</tr>
										<!-- (itemCountLotes < rowNumberLotes) -->		
											<%
											int count=0;
											
											for (int i=0; (i < maxRowNumberLotes) && trainit.hasNext(); i++){
											//if (lotes != null && lotes.iterator().hasNext()){
								      			//java.util.Iterator<Lote> lit = lotes.iterator();
								      			//while (lit.hasNext()){
								      				//Lote lote = lit.next();
								      			try{
									      			Treinamento lote = trainit.next();//lotes.get(itemCountLotes);
									      			if (lote.getTurma() != null) leilaoId = lote.getTurma().getId();
									      			else leilaoId=p;
									      			count++;
								      		%>	
								      			<TR>
													<TD bgcolor="<%=(count%2>0)?"#BBBBBB":"#CCCCCC" %>" >
														<center>
														<a onclick="addTab('lotesTabbedPane<%=leilaoId %>','itens<%=lote.getCodigo() %><%=random%>','Lote <%=lote.getCodigo() %>','showDetalhesLote.html?lote_id=<%=lote.getId()%>'); return false;"
															 		title="Clique para listar os detalhes deste lote"
															 		class="aBlack" 
															 		style="cursor: hand; cursor: pointer;">
																	<Font color="Black">
															<binder:message code="format.date"  
																arguments="<%=lote.getAgendaTreinamento().getInicioPrevisto() %>"/> a
															<binder:message code="format.date"  
																arguments="<%=lote.getAgendaTreinamento().getTerminoPrevisto() %>"/>
															</Font></a>
														 </center>
													</TD>
													<TD bgcolor="<%=(count%2>0)?"#BBBBBB":"#CCCCCC" %>">
														<a onclick="addTab('lotesTabbedPane<%=leilaoId %>','itens<%=lote.getCodigo() %><%=random%>','Lote <%=lote.getCodigo() %>','showDetalhesLote.html?lote_id=<%=lote.getId()%>'); return false;"
															 		title="Clique para listar os detalhes deste lote"
															 		class="aBlack" 
															 		style="cursor: hand; cursor: pointer;">
																	<Font color="Black">&nbsp;&nbsp;
																	<% if (lote.getTreinamento().getNome().length()>200)
																		 out.print(lote.getTreinamento().getNome().substring(0,150)+"...");
																		 else out.print(lote.getTreinamento().getNome()); %> 
																</Font></a>
														
													</TD>
													
													<%														
													if(user != null){
													%>
													<TD align="center"  bgcolor="<%=(count%2>0)?"#BBBBBB":"#CCCCCC" %>">
													<INPUT dojoType="dijit.form.CheckBox" 
															onClick="adapitPut('participarLeilaoLote.html?usuario_id=<%=user.getId()%>&lote_id=<%=lote.getId()%>'); return false;" type="checkbox"
															name="participarLeilaoLoteCheckBox<%=lote.getId()%>" style="width: 20px; height: 20px" <%if(lotesMarcados != null && lotesMarcados.contains(lote)){ %>checked="checked"<%} %>/>
														<!-- <INPUT type="checkbox"  name="participarLeilaoLoteCheckBox" id="participarLeilaoLoteCheckBox" style="width:20px; height:20px" />-->
													</TD>
													<%} %>
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
	   		Treinamento lote = lit.next();
	   		
			try{ %>

	<div id="agendaLoteDialog<%=lote.getId()+random%>" 
		dojoType="dijit.Dialog" title="Andamento do Leil&atilde;o do Lote <%=lote.getCodigo() %>" 
		style="display:none; width: 300px; height: 180px; top:200px;  overflow: inherit;" 
		bindArgs="{sync: true, preventCache: false}"
		href="/leilao/showEstadoAgendaLeilaoLote.html?lote_id=<%=lote.getId()%>"/>	
	
			<%}catch(Exception ex){ex.printStackTrace();} %>
	<%
	   	}
   	}
	%>