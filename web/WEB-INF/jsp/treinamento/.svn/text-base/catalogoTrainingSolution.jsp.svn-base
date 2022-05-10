<%@page import="com.adapit.portal.entidades.*"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="validation" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="binder" uri="http://www.springframework.org/tags"%>
<%@page import="com.adapit.portal.entidades.FormacaoTreinamento"%>
<%@page import="com.workcase.utils.IdGenerator"%>
<LINK rel="stylesheet" type="text/css" href="css/main.css"/>

<script type="text/javascript" src="js/jquery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery-ui-1.8.4.custom.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery.bgiframe-2.1.1.js"></script>
<style type="text/css">
   #just_dialog div button { font-size: 62.5%; }
</style>
<%String solrandom = IdGenerator.getInstance().generateId(3); %>
<script type="text/javascript">
				jQuery(document).ready(function(){
					$('.catalogoAccordionDiv<%=solrandom%>.head').click(function() {
						$(this).next().toggle('slow');
						return false;
					}).next().hide();
				});
				$(function() {
					$("#catalogoAccordionDiv<%=solrandom%>").accordion();
				});
							
</script>

   		<script type="text/javascript" src="js/jt_DialogBox.js"></script>
   		<script type="text/javascript" src="js/MyApp_dialogs.js"></script>
      
      

<%
        java.util.Vector<TrainingSolution> solutions = (java.util.Vector) request.getAttribute("solutions");
        
		if(solutions == null || solutions.size()==0)
			out.print("<br><center>Nenhum treinamento dispon&iacute;vel</center><br>");
		else out.print("<br><center>Itens dispon&iacute;veis: "+solutions.size()+"</center><br>");
%>		
	<div id="catalogoAccordionDiv<%=solrandom%>">		
<%     	int countControl=0;
      	int treinamentoCountControl=0;
      	//java.util.Iterator<TrainingSolution> it = solutions.iterator();
      	//if (!solutions.iterator().hasNext()) out.print("<Center>N&atilde;o existem treinamentos cadastrados na forma&ccedil;&atilde;o</Center>");
      	int i=0;
      	for(TrainingSolution solution: solutions){
      		//TrainingSolution solution = it.next();		
      		i++;
      %>
			<h3 >
			<a href="#"><Font color="Black" size="2" face="Arial"><b>Treinamento <%=solution.getNome()%></b></Font></a>
			</h3>
			<div style="position=relative; width=100%; padding:5px;">				
					<%
					Integer id = new Integer(solution.getId());		      		
					%>
					<jsp:include page="/treinamentosEmTurma.html"/>
								
					<div>
						<table cellpadding="0" cellspacing="0" width="100%" style="background-color:transparent;">
							<tr>
								<td width="100px" style="background-color:transparent;">
									<Label>Treinamento: </Label>
								</td>
								<td style="font-family: arial; font-size: 12" colspan="3" style="background-color:transparent;" >
									<%=solution.getNome()%>
								</td>
							</tr>
							<tr>
								<td width="100px" style="background-color:transparent;">
									<Label>Carga Hor&aacute;ria: </Label>
								</td>
								<td style="font-family: arial; font-size: 12" style="background-color:transparent;">
									<%=solution.getCargaHoraria()%> horas
								</td>														
								<td width="100px" style="background-color:transparent;">
									<Label>Valor Individual: </Label>
								</td>
								<td style="font-family: arial; font-size: 12">
									<%=com.workcase.utils.Moeda.getValorReal((Float)solution.getAvaliacao())%>
								</td>
							</tr>
							<tr>
								<td width="100px" style="background-color:transparent;">
									<Label>Categoria: </Label>
								</td>
								<td style="font-family: arial; font-size: 12" style="background-color:transparent;">
									<%=solution.getCategoria().getNome()%>
								</td>
								<td width="100px" style="background-color:transparent;">
									<!--<a onclick="jqueryOpen('showImagensComercialSolution.html?produto_id=<%=solution.getId()%>','detalhesContentTreinamentoDialog<%=solution.getId()+solrandom%>'); return false;"
									  href=""
															 		title="Clique para listar a descri&ccedil;&atilde;o deste treinamento"
															 		class="aBlack" 
															 		style="cursor: hand; cursor: pointer;">
																	<Font color="Blue">Imagens</Font>
									</a>
								--></td>
								<td style="font-family: arial; font-size: 12" style="background-color:transparent;">
									<a onclick="jqueryOpen('showDetalhesTreinamento.html?solution_id=<%=solution.getId()%>','detalhesContentTreinamentoDialog<%=solution.getId()+solrandom%>'); return false;"
															 		title="Clique para listar os detalhes deste treinamento"
															 		class="aBlack" 
															 		style="cursor: hand; cursor: pointer;">
																	<Font color="Blue">Saiba Mais</Font></a> 
								</td>
							</tr>
						</table>						
					</div>
					<div id="detalhesContentTreinamentoDialog<%=solution.getId()+solrandom%>"
					 style="height: 200px;">
					
					</div>					
				</div>					
	      		   		
			
		
		<%
			countControl++;
			treinamentoCountControl=0;
    }%>
     </div>
