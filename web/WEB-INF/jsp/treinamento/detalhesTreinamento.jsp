<%@page import="com.adapit.portal.entidades.*" %>
<%@page import="com.workcase.utils.IdGenerator"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="validation" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="binder" uri="http://www.springframework.org/tags"%>
	<script type="text/javascript" src="../leilao/js/wz_tooltip.js"/>
      <LINK rel="stylesheet" type="text/css" href="../leilao/css/default.css"/>
      
      <style type="text/css">
        <!--
        <%	
        	java.util.Vector<ComercialSolutionItem> itens = (java.util.Vector<ComercialSolutionItem>) request.getAttribute("itensTreinamentoList");
        	Treinamento lote = (Treinamento) request.getAttribute("treinamento");
        	int containerPrincipalWidth=com.adapit.portal.util.global.GlobalVariables.ContainerPrincipalWidth;
        	int containerPrincipalHeight=com.adapit.portal.util.global.GlobalVariables.ContainerPrincipalHeight;
        	int maxRowNumberLotes=12;
        	int loteId= ((Integer)request.getAttribute("treinamentoId")).intValue();
        	String random = IdGenerator.getInstance().generateId(3);
        %>
		div.compLabel1div{
			position:absolute;
			width:150px; height:20px;
			left:0; top:0;
		}
		div.loteContainerPrincipaldiv{
			position:relative;
			width:<%=containerPrincipalWidth%>px; height:<%=containerPrincipalHeight%>px;
			left:0; top:10;
			background-color: #FFFFFE;
			border:1px <%=com.adapit.portal.util.global.GlobalVariables.ContainerPrincipalBorderColor%> solid;
		}
		div.titledPaneldiv{
			position:absolute;
			width:<%=containerPrincipalWidth-2%>px; height:41px;
			left:0; top:0;
			background-color: #FFFFFE;
		}
		div.leftYellowPanel{
			position:absolute;
			width:12px; height:41px;
			left:0; top:0;
			background-color: #FFFFFE;
			background-image: url("../imgs/left_yellow_border.png");
		}
		div.rightYellowPanel{
			position:absolute;
			width:12px; height:41px;
			left:<%=containerPrincipalWidth-14%>; top:0;
			background-color: #FFFFFE;
			background-image: url("../imgs/right_yellow_border.png");
		}
		div.produtosLotesdiv{
			position:relative;
			width:100%; height:280px;
			left:0; top:0;			
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
			width:250px; height:25px;
			left:0; top:0;
			background-image: url("../imgs/tablebackground.png");
		}
		div.descricaoTexoPaneldiv{
			position:absolute;
			width:250px; height:110px;
			left:0; top:25;
		}
		div.descricaoPaneldiv{
			position:relative;
			width:250px; height:136px;
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

        -->
      </style>
  
    <script type="text/javascript" src="js/wz_tooltip.js"></script>

    <script type="text/javascript">
    <%				
	if (itens != null && itens.iterator().hasNext()){
		java.util.Iterator<ComercialSolutionItem> lit = itens.iterator();
		while (lit.hasNext()){
			ComercialSolutionItem item = lit.next(); %>						
						
						
		function openImgProd<%=item.getTrainingSolution().getId() %>(){ //Define arbitrary function to run desired DHTML Window widget codes
			ajaxwin=dhtmlwindow.open("ajaxbox", "ajax", "showImagensComercialSolution.html?produto_id=<%=item.getTrainingSolution().getId()%>", "Imagens do Treinamento <%=item.getTrainingSolution().getId() %>", "width=600px,height=400px,left=70px,top=100px,resize=1,scrolling=1")
			ajaxwin.onclose=function(){return window.confirm("Voc&ecirc; quer fechar a janela Imagens do Treinamento <%=item.getTrainingSolution().getId() %>?")} //Run custom code when window is about to be closed
		}
												
	<%
		}
	}
	%>
	</script>
    			

      

	<div class="produtosLotesdiv">
				
		<div dojoType="dijit.layout.TabContainer" id="produtosTabbedPane<%=loteId %>" tabPosition="bottom" sizeShare="40" style="width:100%; height:300px;" cacheContent="false">
				<div class="itensvirtualpage<%=loteId%>" style="position: relative; width:100% ">
					<table  width="100%"  class="presentation">
						<tr>
							<th height="40" class="presentation"valign="center" title="C&oacute;digo do Treinamento">C&oacute;d</th>
							<th height="40" class="presentation" valign="center" title="Categoria">Cat</th>
							<th width="60%" height="40" class="presentation" valign="middle"><center>Descri&ccedil;&atilde;o do Treinamento</center></th>
							<th width="15%" height="40" class="presentation" align="center" valign="middle"><center>Valor Individual</center></th>
							<th height="40" class="presentation" align="center" valign="middle" title="Quantidade">Qtd</th>
							<th width="15%" height="40" class="presentation" align="center" valign="middle"><center>Valor Total</center></th>							
							<th height="40" class="presentation" title="Fotos do Treinamento" title="Clique nas fotos para visualizar as imagens do produto">Imgs</th>
						</tr>		
							<%
							int count=0;
							
							for (int i=0;i < itens.size(); i++){
								ComercialSolutionItem item = itens.get(i);
				      		%>	
				      			<TR height="25" onMouseOver="this.bgColor='BBBBBB';" onMouseOut="this.bgColor='#FFFFFF';">
				      				<TD sort="asc||desc||0||1" align="center"><%=item.getTrainingSolution().getId() %></TD>
				      				<TD align="center">
				      					<%if (item.getTrainingSolution() != null && item.getTrainingSolution().getCategoria() != null && item.getTrainingSolution().getCategoria().getCategoriaImagem() != null && item.getTrainingSolution().getCategoria().getCategoriaImagem().getImagem() != null ){%>
											<img src='categoriaImageView.html?categoria_id=<%= item.getTrainingSolution().getCategoria().getId()%>' title="<%= item.getTrainingSolution().getCategoria().getNome() %>"/>						   
										<%}%>
									</TD>
									<TD >
									  <table border=0 cellpadding=0 cellspacing=0>
										<tr>											
											<td style="border: none;"> 
												<% if (item.getTrainingSolution().getDescricao().length()>45) out.print(item.getTrainingSolution().getDescricao().substring(0,45)); else out.print(item.getTrainingSolution().getDescricao()); %>
											</td>
											<td style="border: none;">
												 <img src='imgs/comment.png' title="clique para obter a descri&ccedil;&atilde;o detalhada do produto"
												 onclick="javascript:dijit.byId('descriptionDialog<%=item.getTrainingSolution().getId()+random%>').show();" />
											</td>
										  </tr>
										</table>
									</TD>
									<TD sort="asc||desc||0||1" align="right">R$ <%=item.getTrainingSolution().getAvaliacao() %></TD>
									<TD align="center"></TD>
									<TD sort="asc||desc||0||1" align="right">R$ <%=item.getValorAcertado() %></TD>
									<TD  align="center">
									<!-- 
										var slide = new SlideShow();
										slide.initGalleryScript('galleryContainer<%=item.getTrainingSolution().getId() %>','theImages<%=item.getTrainingSolution().getId() %>','slideEnd<%=item.getTrainingSolution().getId() %>','imageCaption<%=item.getTrainingSolution().getId() %>','arrow_up_image<%=item.getTrainingSolution().getId() %>','arrow_down_image<%=item.getTrainingSolution().getId() %>');
										slideShows[<%=item.getTrainingSolution().getId() %>]=slide; 
										
										    function openImgProd<%=item.getTrainingSolution().getId() %>(){ 
												ajaxwin<%=item.getTrainingSolution().getId() %>=dhtmlwindow.open('ajaxbox<%=item.getTrainingSolution().getId() %>', 'ajax', 'showImagensComercialSolution.html?produto_id=<%=item.getTrainingSolution().getId()%>', 'Imagens do Treinamento <%=item.getTrainingSolution().getId() %>', 'width=600px,height=405px,left=120px,top=200px,resize=1,scrolling=1')
												initGalleryScript();
												ajaxwin<%=item.getTrainingSolution().getId() %>.isResize(true);
												ajaxwin<%=item.getTrainingSolution().getId() %>.isScrolling(false);
												ajaxwin<%=item.getTrainingSolution().getId() %>.onclose=function(){
													destroyNode(ajaxwin<%=item.getTrainingSolution().getId() %>);													
													return true;
												}
												dojo.parser.parse(ajaxwin<%=item.getTrainingSolution().getId() %>);
											}
											openImgProd<%=item.getTrainingSolution().getId() %>();
											
											return false;
											addTab('produtosTabbedPane<%=loteId %>','produto<%=item.getTrainingSolution().getId()%>','Treinamento <%=item.getTrainingSolution().getId() %>','showImagensCommercialSolution.html?produto_id=<%=item.getTrainingSolution().getId()%>'); return false;
										
										
										javascript:dijit.byId('imageDialog<%=item.getTrainingSolution().getId()+random%>').show()
									 -->
										<a 	onclick="modalImageWin('showImagensComercialSolution.html?produto_id=<%=item.getTrainingSolution().getId()%>','Imagens do Treinamento <%=item.getTrainingSolution().getId() %>'); return false;"
											style="cursor: hand; cursor: pointer;">
											
											<img src='imgs/images.png' title="Clique para visualizar as imagens do produto" />
										</a>
										<!-- showDialog('Imagens do Treinamento','imgProd<%=item.getTrainingSolution().getId() %>','showImagensComercialSolution.html?produto_id=<%=item.getTrainingSolution().getId()%>'); -->
									</TD>
								</TR>							
				      		<%
								
				      		}									
							%>
					</table>
				</div>				
				
			</div>		
		
	</div>
<%
	for (int i=0;i < itens.size(); i++){
		ComercialSolutionItem item = itens.get(i);						
%>
<div id="descriptionDialog<%=item.getTrainingSolution().getId()+random%>" 
	dojoType="dijit.Dialog" title="Descri&ccedil;&atilde;o do Treinamento <%=item.getTrainingSolution().getId() %>" 
	style="display:none; width: 400px; height: 200px;  " 
	bindArgs="{sync: true, preventCache: false}"
>
	<table border=0 cellpadding=0 cellspacing=0 bordercolor=#000000 class="presentation" width="95%"  >
		<tr><th colspan=2><div class="tooltip_title">Descri&ccedil;&atilde;o do Treinamento <%=item.getTrainingSolution().getId() %></div></th>
		<tr><td><b><%=itens.size() %> Unidade(s) de 					
		
				<%=(item.getTrainingSolution().getUtilizarFormatador())? item.getTrainingSolution().getFormatedDescricao():item.getTrainingSolution().getDescricao() %>
		
	</table>
</div>	   		
<div id="imageDialog<%=item.getTrainingSolution().getId()+random%>" 
	dojoType="dijit.Dialog" title="Imagens do Treinamento <%=item.getTrainingSolution().getId() %>" 
	style="display:none; width: 300px; height: 200px; overflow: inherit;" 
	bindArgs="{sync: true, preventCache: false}"
	href="showImagensComercialSolution.html?produto_id=<%=item.getTrainingSolution().getId()%>"></div>

<%
}%>	