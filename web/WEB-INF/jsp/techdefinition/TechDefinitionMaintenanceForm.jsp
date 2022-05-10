

<%@page import="com.adapit.portal.dto.UsuarioDTO"%>
<%@page import="com.adapit.portal.entidades.PersonAttributeType"%><meta charset="UTF-8" />
<LINK rel="stylesheet" type="text/css" href="css/main.css"/>
<script type="text/javascript" src="js/jquery/jquery.bgiframe-2.1.1.js"></script>
<style type="text/css">
   #just_dialog div button { font-size: 62.5%; }
</style>
<%@taglib prefix="jstl" uri="http://java.sun.com/jstl/core"%>
<%@taglib prefix="validation" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@taglib prefix="binder" uri="http://www.springframework.org/tags"%>
<%@page import="java.util.*"%>
<%
UsuarioDTO userDTO =(UsuarioDTO) session.getAttribute("user");
if(userDTO != null && userDTO.hasAttribute(PersonAttributeType.Pode_alterar_definicoes_tecnicas)){
%>
<%@page import="com.adapit.portal.entidades.TechDefinition"%>
<% TechDefinition techDefinition = (TechDefinition) request.getAttribute("techDefinition"); %>
<% if(techDefinition == null) techDefinition = (TechDefinition) request.getSession().getAttribute("techDefinition"); %>
<% if(techDefinition == null) techDefinition = new TechDefinition();%>
<script type="text/javascript">
	$(function() {
		$("#newTechDefinitionButton").button();
		$("#saveTechDefinitionButton").button();
		$("#removeTechDefinitionButton").button();
		$("#undoTechDefinitionButton").button();
		$("#cancelTechDefinitionButton").button();
		$("#searchButton").button();
		$("#listallTechDefinitionButton").button();
		$("#editselectionTechDefinitionButton").button();
		$("#removeselectionTechDefinitionButton").button();
	});
</script>
<% List<TechDefinition> techDefinitionList = (List<TechDefinition>) request.getAttribute("techDefinitionList"); %>
<script type="text/javascript">
	$.fx.speeds._default = 1000;
	$(function() {
		<% if(techDefinitionList != null && techDefinitionList.size()>0)
		   for(int row=0; row < techDefinitionList.size(); row++){ %>
		<% TechDefinition techDefinitionItem = techDefinitionList.get(row); %>
		$('#removeByPk<%=techDefinitionItem.getId()%>TechDefinition TableConfirmationDialog').dialog({
			resizable: false,
			height:140,
			modal: true,
			buttons: {
				'Remove Row': function() {
					$(this).dialog('close');
					jqueryOpen('deleteTechDefinitionAction_TechDefinitionMaintenanceForm.html?techDefinition.id=<%=techDefinitionItem.getId()%>','contentDiv'); return false;
				},
				Cancel: function() {
					$(this).dialog('close');
				}
			}
		});
		$('#removeByPk<%=techDefinitionItem.getId()%>TechDefinition TableConfirmationDialogButton').click(function() {
			$('#removeByPk<%=techDefinitionItem.getId()%>TechDefinition TableConfirmationDialog').dialog('open');
			return false;
		});
		<% } %>
	});
</script>
	<!-- Edite o JSP userOperationFeedback.jsp para customizar os di&aacute;logos de feedback com o usu&aacute;rio -->
	<%@include file="../userOperationFeedback.jsp" %>
	<div  class="default_window"  style=" width:575px; height:724px; position:relative; top:0px; left:0px; " >
	
	<form class="default_form"  method="post" id="entityTabbedPaneForm" >
		<div  class="default_crud   ui-corner-all "  style=" width:540px; height:295px;  left:15px; position:absolute;  top:23px; " >
		
			<div class="default_div_title    ui-corner-all " style="width:100%; height:25px;">
				<span><binder:message code="Manuten&ccedil;&atilde;o_de_Defini&ccedil;&otilde;es_T&eacute;cnicas"/></span>
			</div>
			<input name="techDefinition.id" id="techDefinition.id" type="hidden" style="width:300px; height:20px;   " value="<%=techDefinition.getId() %>"/>
			<input name="techDefinition.keywords" id="techDefinition.keywords" type="text" 
				style="width:405px; height:20px; position:absolute; top:25px; left:120px;  " 
				value="<jstl:out value='${techDefinition.keywords}'/>"/>
			<div style="width:100px; height:20px; position:absolute; top:25px; left:15px;  "><label style="position:relative; top:25%"><binder:message code="Keywords"/></label> </div>
			<div  style=" width:520px; height:211px;  left:15px; position:absolute;  top:43px; " >
			
				<div style="width:455px; height:20px; position:absolute; top:0px; left:0px;  "><label style="position:relative; top:25%"><binder:message code="Conte&uacute;do_Textual"/></label> </div>
				<textarea name="techDefinition.content" id="techDefinition.content" style="width:509px; height:191px; position:absolute; top:20px; left:0px;  "
				><jstl:out value='${techDefinition.content}'/></textarea>
			</div>
			<div  class="default_div_buttons   ui-corner-all "  align="center"  style=" width:490px; height:30px;  left:15px; position:absolute;  top:257px; " >
			
				<button id="newTechDefinitionButton" style="  " onclick="javascript::newtechDef(); return false;">
					<binder:message code="Novo"/>
					<img src="imgs/page_add.png"/>
					<script type="text/javascript">
					function newtechDef(){
						$("#techDefinition.id").attr("value", "0");
						$("#techDefinition.keywords").attr("value", "");
						$("#techDefinition.content").attr("value", "");
					}
					</script>
				</button>
				<button id="saveTechDefinitionButton" style="  " onClick="jquerydoPost('entityTabbedPaneForm','saveTechDefinitionAction_TechDefinitionMaintenanceForm.html','contentDiv'); return false;" >
					<binder:message code="Salvar"/>
					<img src="imgs/disk.png"/>
				</button>
				<button id="removeTechDefinitionButton" style="  " onClick="jqueryOpen('deleteTechDefinitionAction_TechDefinitionMaintenanceForm.html?techDefinition.id=<%=techDefinition.getId()%>','contentDiv'); return false;" >
					<binder:message code="Remover"/>
					<img src="imgs/delete.png"/>
				</button>
				<button id="undoTechDefinitionButton"  type="reset"style="  " >
					<binder:message code="Desfazer"/>
					<img src="imgs/arrow_undo.png"/>
				</button>
				<button id="cancelTechDefinitionButton" style="  " >
					<binder:message code="Cancelar"/>
					<img src="imgs/cancel.png"/>
				</button>
			</div>
		</div>
	</form>
	<form class="default_form"  method="post" id="filtersPanelForm" >
		<div  class="default_list   ui-corner-all "  
		style=" width:540px; height:80px;  left:15px; position:absolute;  top:325px; " >
			<div class="default_div_title    ui-corner-all " style="width:100%; height:25px;">
					<span style="margin-top:-5px;"><binder:message code="Filtrar_Defini&ccedil;&atilde;o_T&eacute;cnica"/></span>
			</div>
			<input name="techDefinition.keywords" id="techDefinition.keywords" type="text" 
				style="width:407px; height:20px; position:absolute; top:25px; left:120px;  " 
			/>
			<div style="width:100px; height:20px; position:absolute; top:25px; left:15px;  "><label style="position:relative; top:25%"><binder:message code="Keyword"/></label> </div>
			<div  align="center"  style=" width:512px; height:25px;  left:15px; position:absolute;  top:45px; " >
				<button id="searchButton" style="  " onClick="jquerydoPost('filtersPanelForm','filterTechDefinitionByKeyword_TechDefinitionMaintenanceForm.html','contentDiv'); return false;" >
					<binder:message code="Filtrar_..."/>
					<img src="imgs/application_find_list.png"/>
				</button>
			</div>
		
		</div>
	</form>
	<form class="default_form"  method="post" id="TechDefinitionPanelForm" >
		<div  class="default_list   ui-corner-all "  style=" width:540px; height:279px;  left:15px; position:absolute;  top:412px; " >
		
			<div class="default_div_title    ui-corner-all " style="width:100%; height:25px;">
				<span style="margin-top:-5px;"><binder:message code="Lista_de_Defini&ccedil;&otilde;es_T&eacute;cnicas"/></span>
			</div>
			<div class="default_div_table" align="center" style=" width:507px; height:190px; position:absolute; left:15px; top:30px; ">
				
				<table class="default_table" border=0 cellspacing=0 cellpadding=0 style=" width:507px; ">
					<tr class="default_tr_title">
						<th style="width:52px;"><binder:message code="IdCellHeader"/></th>
						<th style="width:453px;"><binder:message code="KeywordsCellHeader"/></th>
						<th style="width:16px;">&nbsp;</th>
						<th style="width:16px;">&nbsp;</th>
						<th style="width:16px;">&nbsp;</th>
					</tr>
					<% if(techDefinitionList != null && techDefinitionList.size()>0)
					   for(int row=0; row < techDefinitionList.size(); row++){ %>
					<% TechDefinition techDefinitionItem = techDefinitionList.get(row); %>
					<tr class="default_tr_content">
						<td><%=techDefinitionItem.getId() %></td>
						<td><%=techDefinitionItem.getKeywords() %></td>
						<td><input type="checkbox" name="techDefinition<%=row%>" value="<%=techDefinitionItem.getId()%>"/></td>
						<td style="cursor: pointer;" id="removeByPk<%=techDefinitionItem.getId()%>TechDefinition TableConfirmationDialogButton"><img src="imgs/delete.png"/></td>
						<td style="cursor: pointer;" onClick="jqueryOpen('loadTechDefinitionAction_TechDefinitionMaintenanceForm.html?techDefinition.id=<%=techDefinitionItem.getId()%>','contentDiv'); return false;"><img src="imgs/page_edit.png"/></td>
					</tr>
					<% } %>
				</table>
					<% if(techDefinitionList != null && techDefinitionList.size()>0)
					   for(int row=0; row < techDefinitionList.size(); row++){ %>
					<% TechDefinition techDefinitionItem = techDefinitionList.get(row); %>
					
						<div id="removeByPk<%=techDefinitionItem.getId()%>TechDefinition TableConfirmationDialog"  title="<binder:message code="RemoveTableRow_ConfirmationDialog_Title"/>">
							<p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>
							<binder:message code="RemoveTableRow_ConfirmationDialog_Message"/>?
							</p>
						</div>
					<%}%></div>
			<div  class="default_div_buttons   ui-corner-all "  align="center"  style=" width:507px; height:33px;  left:15px; position:absolute;  top:240px; " >
			
				<button id="listallTechDefinitionButton" style="  " onClick="jqueryOpen('showListAllTechDefinitionView_TechDefinitionMaintenanceForm.html?techDefinition.id=<%=techDefinition.getId()%>','contentDiv'); return false;" >
					<binder:message code="Listar_Todos"/>
					<img src="imgs/application_view_list.png"/>
				</button>
				<button id="editselectionTechDefinitionButton" style="  " onClick="jquerydoPost('TechDefinitionPanelForm','loadTechDefinitionFromeditselectionTechDefinitionButtonAction_TechDefinitionMaintenanceForm.html','contentDiv'); return false;" >
					<binder:message code="Editar_Registro"/>
					<img src="imgs/page_edit.png"/>
				</button>
				<button id="removeselectionTechDefinitionButton" style="  " onClick="jquerydoPost('TechDefinitionPanelForm','deleteManyTechDefinitionAction_TechDefinitionMaintenanceForm.html','contentDiv'); return false;" >
					<binder:message code="Remover_Registros"/>
					<img src="imgs/delete.png"/>
				</button>
			</div>
		</div>
	</form>
	</div>
	
<%
}
%>