
<meta charset="UTF-8" />
<LINK rel="stylesheet" type="text/css" href="css/main.css"/>
<script type="text/javascript" src="js/jquery/jquery.bgiframe-2.1.1.js"></script>
<style type="text/css">
   #just_dialog div button { font-size: 62.5%; }
</style>
<%@taglib prefix="jstl" uri="http://java.sun.com/jstl/core"%>
<%@taglib prefix="validation" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@taglib prefix="binder" uri="http://www.springframework.org/tags"%>
<%@page import="java.util.*"%>
<%@page import="com.adapit.portal.entidades.Pais"%>
<%@page import="com.adapit.portal.entidades.Sexo"%>
<%@page import="com.adapit.portal.entidades.PrimeiroContatoIniciado"%>
<%@page import="com.adapit.portal.entidades.Participante"%>
<% Participante participante = (Participante) request.getAttribute("participante"); %>
<% if(participante == null) participante = (Participante) request.getSession().getAttribute("participante"); %>
<% if(participante == null) participante = new Participante();%>
<%@page import="com.adapit.portal.entidades.Usuario"%>
<% Usuario usuario = (Usuario) request.getAttribute("usuario"); %>
<% if(usuario == null) usuario = (Usuario) request.getSession().getAttribute("usuario"); %>
<% if(usuario == null) {
usuario = new Usuario();
}%>
<%@page import="com.adapit.portal.entidades.Fisica"%>
<% Fisica fisica = (Fisica) request.getAttribute("fisica"); %>
<% if(fisica == null) fisica = (Fisica) request.getSession().getAttribute("fisica"); %>
<% if(fisica == null) {
fisica = new Fisica();
}%>
<%@page import="com.adapit.portal.entidades.PreferenciaCategoria"%>
<% PreferenciaCategoria preferenciaCategoria = (PreferenciaCategoria) request.getAttribute("preferenciaCategoria"); %>
<% if(preferenciaCategoria == null) preferenciaCategoria = (PreferenciaCategoria) request.getSession().getAttribute("preferenciaCategoria"); %>
<% if(preferenciaCategoria == null) {
preferenciaCategoria = new PreferenciaCategoria();
}%>
<%@page import="com.adapit.portal.entidades.Endereco"%>
<% Endereco endereco = (Endereco) request.getAttribute("endereco"); %>
<% if(endereco == null) endereco = (Endereco) request.getSession().getAttribute("endereco"); %>
<% if(endereco == null) {
endereco = new Endereco();
}%>
<script type="text/javascript">
	$(function() {
		$('#fisica\\.dataNascimento').datepicker($.datepicker.regional['pt-BR']);
		<%if(fisica.getDataNascimento() == null){%>
			$('#fisica\\.dataNascimento').attr('value', '');
		<%}%>
		$("#saveParticipanteButton").button();
	});
</script>
<center>	
<div  class="default_window"  style=" width:944px;  position:relative; top:0px; left:0px; " >
	<!-- Edite o JSP userOperationFeedback.jsp para customizar os di&aacute;logos de feedback com o usu&aacute;rio -->
	<div style=" width:909px;  left:15px; position:relative;  top:0px; " >
		<%@include file="../userOperationFeedback.jsp" %>
	</div>
	<form class="default_form"  method="post" id="baseParticipanteTabForm" enctype="multipart/form-data">
		<div  class="default_crud   ui-corner-all "  style=" width:909px; height:673px;  left:15px; position:relative;  top:0px; " >
			
			<div class="default_div_title ui-corner-top" style="width:100%; height:28px;">
			<span><binder:message code="Registro_de_Novo_Usu&aacute;rio"/></span>
			</div>
			<div  style=" width:886px; height:330px;  left:15px; position:absolute;  top:33px; " >
			
				<div  class="dates_vig"  style=" width:435px; height:310px;  left:0px; position:absolute;  top:0px; " >
					<div class="default_titled_pane  ui-corner-bottom " style="width:100%; height:30px;">
					<span><binder:message code="Informe_os_Dados_Pessoais"/></span>
					</div>
					<div  style=" width:412px; height:66px;  left:15px; position:absolute;  top:33px; " >
					
						<input name="usuario.login" id="usuario.login" type="text" 
							style="width:270px; height:20px; position:absolute; top:0px; left:135px;  " 
							value="<jstl:out value='${usuario.login}'/>"
							<%if(participante.getId()>0) {%>
							disabled="disabled"<%} %>
							/>
						<input name="usuario.login2" id="usuario.login2" type="hidden" 
							value="<jstl:out value='${usuario.login}'/>"
							/>
						<div style="width:130px; height:20px; position:absolute; top:0px; left:0px;  ">
							<label style="position:relative; top:25%"><binder:message code="Login"/> *</label> </div>
						<input name="usuario.password" id="usuario.password" type="password" 
							style="width:270px; height:20px; position:absolute; top:23px; left:135px;  " 
							
							<%if(participante.getId()>0) {%>
							disabled="disabled"<%} %>
							/>
						<div style="width:130px; height:20px; position:absolute; top:23px; left:0px;  ">
							<label style="position:relative; top:25%"><binder:message code="Senha"/> *</label> </div>
						<input name="usuario.passwordConf" id="usuario.passwordConf" type="password" 
						style="width:270px; height:20px; position:absolute; top:46px; left:135px;  " 
						
						<%if(participante.getId()>0) {%>
							disabled="disabled"<%} %>/>
						<div style="width:130px; height:20px; position:absolute; top:46px; left:0px;  ">
							<label style="position:relative; top:25%"><binder:message code="Confirme_a_Senha"/> *</label> </div>
					</div>
					<input name="participante.id" id="participante.id" type="hidden" style="width:270px; height:20px;   " value="<%=participante.getId() %>"/>
					<input name="participante.nome" id="participante.nome" type="text" 
						style="width:270px; height:20px; position:absolute; top:102px; left:150px;  " 
						value="<jstl:out value='${participante.nome}'/>"/>
					<div style="width:130px; height:20px; position:absolute; top:102px; left:15px;  ">
						<label style="position:relative; top:25%"><binder:message code="Nome"/> *</label> </div>
					<input name="participante.email" id="participante.email" type="text" style="width:270px; height:20px; position:absolute; top:125px; left:150px;  " value="<jstl:out value='${participante.email}'/>"/>
					<div style="width:130px; height:20px; position:absolute; top:125px; left:15px;  ">
						<label style="position:relative; top:25%"><binder:message code="Email"/> *</label> </div>
					<input name="participante.telefone" id="participante.telefone" type="text" style="width:270px; height:20px; position:absolute; top:148px; left:150px;  " value="<jstl:out value='${participante.telefone}'/>"/>
					<div style="width:130px; height:20px; position:absolute; top:148px; left:15px;  "><label style="position:relative; top:25%"><binder:message code="Telefone"/></label> </div>
					<input name="participante.fax" id="participante.fax" type="text" style="width:270px; height:20px; position:absolute; top:171px; left:150px;  " value="<jstl:out value='${participante.fax}'/>"/>
					<div style="width:130px; height:20px; position:absolute; top:171px; left:15px;  "><label style="position:relative; top:25%"><binder:message code="Fax"/></label> </div>
					
					<select name="participante.primeiroContato" id="participante.primeiroContato"  
					style="width:270px; height:20px; position:absolute; top:194px; left:150px;  ">
					<%
						PrimeiroContatoIniciado vals2[] =  PrimeiroContatoIniciado.values();
						for(int i=0; i< vals2.length; i++){
							String val = vals2[i].name();
							PrimeiroContatoIniciado p = vals2[i];
						%>
						<OPTION value="<%=val %>" <% if(participante.getPrimeiroContato() == p ) {%>selected="selected"<%} %> ><%=val.replace("_"," ") %></OPTION>
						<%
						}
						%>
					</select>
					<div style="width:130px; height:20px; position:absolute; top:194px; left:15px;  ">
					<label style="position:relative; top:25%">
					Chegou at&eacute; n&oacute;s por:
					</label> 
					</div>
					
					
					<div  style=" width:412px; height:89px;  left:15px; position:absolute;  top:217px; " >
					
						<input name="fisica.id" id="fisica.id" type="hidden" style="width:270px; height:20px;   " value="<%=fisica.getId() %>"/>
						<input name="fisica.sobrenome" id="fisica.sobrenome" type="text" style="width:270px; height:20px; position:absolute; top:0px; left:135px;  " value="<jstl:out value='${fisica.sobrenome}'/>"/>
						<div style="width:130px; height:20px; position:absolute; top:0px; left:0px;  "><label style="position:relative; top:25%"><binder:message code="Apelido"/></label> </div>
						<input name="fisica.profissao" id="fisica.profissao" type="text" style="width:270px; height:20px; position:absolute; top:23px; left:135px;  " value="<jstl:out value='${fisica.profissao}'/>"/>
						<div style="width:130px; height:20px; position:absolute; top:23px; left:0px;  "><label style="position:relative; top:25%"><binder:message code="Profiss&atilde;o"/></label> </div>
						<input type="text" name="fisica.dataNascimento" id="fisica.dataNascimento"							
							style="width:160px; height:20px; position:absolute; top:46px; left:135px;  "  value="<binder:message code="dojoformats.date"  arguments="<%=fisica.getDataNascimento() %>"/>"/>
						<div style="width:130px; height:20px; position:absolute; top:46px; left:0px;  "><label style="position:relative; top:25%"><binder:message code="Data_de_Nascimento"/></label> </div>
						<select name="fisica.sexo" id="fisica.sexo"  style="width:160px; height:20px; position:absolute; top:69px; left:135px;  ">
							<OPTION value="Masculino" <% if(fisica.getSexo() == Sexo.Masculino ) {%>selected="selected"<%} %> ><binder:message code="Masculino"/></OPTION>
							<OPTION value="Feminino" <% if(fisica.getSexo() == Sexo.Feminino ) {%>selected="selected"<%} %> ><binder:message code="Feminino"/></OPTION>
						</select>
						<div style="width:130px; height:20px; position:absolute; top:69px; left:0px;  ">
							<label style="position:relative; top:25%"><binder:message code="Sexo"/></label>
						</div>
					</div>
				</div>
				<div  class="dates_vig"  style=" width:435px; height:310px;  left:445px; position:absolute;  top:0px; " >
					<div class="default_titled_pane  ui-corner-bottom " style="width:100%; height:30px;">
					<span><binder:message code="Detalhe_as_Suas_Prefer&ecirc;ncias"/></span>
					</div>
					<input name="preferenciaCategoria.id" id="preferenciaCategoria.id" type="hidden" style="width:300px; height:20px;   " value="<%=preferenciaCategoria.getId() %>"/>
					<input type="checkbox" name="preferenciaCategoria.receberEmailAtualizacoesSoftware" 
						id="preferenciaCategoria.receberEmailAtualizacoesSoftware"  
						style="height:20px; position:absolute; top:27px; left:15px;" <%if(preferenciaCategoria.isReceberEmailAtualizacoesSoftware()){ %>checked="checked"<%}%>></input>
					<span style="width:406px; height:20px; position:absolute; top:36px; left:35px; "><Label><binder:message code="Quero_receber_emails_de_atualiza&ccedil;&otilde;es_dos_softwares"/></Label></span>
					<input type="checkbox" name="preferenciaCategoria.receberEmailNovosProdutos" 
					id="preferenciaCategoria.receberEmailNovosProdutos"  style="height:20px; position:absolute; top:50px; left:15px;" <%if(preferenciaCategoria.isReceberEmailNovosProdutos()){ %>checked="checked"<%}%>></input>
					<span style="width:406px; height:20px; position:absolute; top:59px; left:35px; "><Label><binder:message code="Quero_receber_emails_sobre_publica&ccedil;&otilde;es"/></Label></span>
					<input type="checkbox" name="preferenciaCategoria.receberEmailSobreEventos" id="preferenciaCategoria.receberEmailSobreEventos" 
					 style="height:20px; position:absolute; top:73px; left:15px;" <%if(preferenciaCategoria.isReceberEmailSobreEventos()){ %>checked="checked"<%}%>></input>
					<span style="width:406px; height:20px; position:absolute; top:82px; left:35px; "><Label><binder:message code="Quero_receber_emails_de_divulga&ccedil;&atilde;o_de_eventos"/></Label></span>
					<input type="checkbox" name="preferenciaCategoria.receberNotificacaoNewsByEmail" 
					id="preferenciaCategoria.receberNotificacaoNewsByEmail"  style="height:20px; position:absolute; top:96px; left:15px;" <%if(preferenciaCategoria.isReceberNotificacaoNewsByEmail()){ %>checked="checked"<%}%>></input>
					<span style="width:406px; height:20px; position:absolute; top:105px; left:35px; "><Label><binder:message code="Quero_receber_emails_das_not&iacute;cias_da_Adapit"/></Label></span>
					<input type="checkbox" name="preferenciaCategoria.interesseEmConsultoria" id="preferenciaCategoria.interesseEmConsultoria"  
					style="height:20px; position:absolute; top:119px; left:15px;" <%if(preferenciaCategoria.isInteresseEmConsultoria()){ %>checked="checked"<%}%>></input>
					<span style="width:406px; height:20px; position:absolute; top:128px; left:35px; "><Label><binder:message code="Tenho_interesse_nas_consultorias"/></Label></span>
					<input type="checkbox" name="preferenciaCategoria.interesseEmTreinamentos" id="preferenciaCategoria.interesseEmTreinamentos"  
					style="height:20px; position:absolute; top:142px; left:15px;" <%if(preferenciaCategoria.isInteresseEmTreinamentos()){ %>checked="checked"<%}%>></input>
					<span style="width:406px; height:20px; position:absolute; top:151px; left:35px; "><Label><binder:message code="Tenho_interesse_nos_treinamentos"/></Label></span>
					<div  style=" width:413px; height:141px;  left:15px; position:absolute;  top:171px; " >
						<div style="width:406px; height:20px; position:absolute; top:0px; left:0px;  "><label style="position:relative; top:25%"><binder:message code="Especifique_aqui_as_suas_prefer&ecirc;ncias"/></label> </div>
						<textarea name="preferenciaCategoria.preferencias" id="preferenciaCategoria.preferencias" 
							style="width:405px; height:66px; position:absolute; top:20px; left:0px;  " ><jstl:out value='${preferenciaCategoria.preferencias}'/></textarea>
					</div>
				</div>
			</div>
			<div  class="dates_vig"  style=" width:880px; height:178px;  left:15px; position:absolute;  top:360px; " >
				<div class="default_titled_pane ui-corner-bottom " style="width:100%; height:30px;">
					<span><binder:message code="Endere&ccedil;o_(Preenchimento_Opcional)"/></span>
				</div>
				<div  style=" width:420px; height:141px;  left:15px; position:absolute;  top:33px; " >
				
					<input name="endereco.id" id="endereco.id" type="hidden" style="width:270px; height:20px;   " value="<%=endereco.getId() %>"/>
					<select name="endereco.pais" id="endereco.pais"  style="width:270px; height:20px; position:absolute; top:0px; left:135px;  ">
						<%
						Pais vals[] =  Pais.values();
						for(int i=0; i< vals.length; i++){
							String val = vals[i].name();
							Pais p = vals[i];
						%>
						<OPTION value="<%=val %>" <% if(endereco.getPais() == p ) {%>selected="selected"<%} %> ><%=val.replace("_"," ") %></OPTION>
						<%
						}
						%>
					</select>
					<div style="width:130px; height:20px; position:absolute; top:0px; left:0px;  "><label style="position:relative; top:25%"><binder:message code="Pais"/></label> </div>
					<input name="endereco.estado" id="endereco.estado" type="text" style="width:270px; height:20px; position:absolute; top:23px; left:135px;  " value="<jstl:out value='${endereco.estado}'/>"/>
					<div style="width:130px; height:20px; position:absolute; top:23px; left:0px;  "><label style="position:relative; top:25%"><binder:message code="Estado"/></label> </div>
					<input name="endereco.cidade" id="endereco.cidade" type="text" style="width:270px; height:20px; position:absolute; top:46px; left:135px;  " value="<jstl:out value='${endereco.cidade}'/>"/>
					<div style="width:130px; height:20px; position:absolute; top:46px; left:0px;  "><label style="position:relative; top:25%"><binder:message code="Cidade"/></label> </div>
					<input name="endereco.bairro" id="endereco.bairro" type="text" style="width:270px; height:20px; position:absolute; top:69px; left:135px;  " value="<jstl:out value='${endereco.bairro}'/>"/>
					<div style="width:130px; height:20px; position:absolute; top:69px; left:0px;  "><label style="position:relative; top:25%"><binder:message code="Bairro"/></label> </div>
					<input name="endereco.rua" id="endereco.rua" type="text" style="width:270px; height:20px; position:absolute; top:92px; left:135px;  " value="<jstl:out value='${endereco.rua}'/>"/>
					<div style="width:130px; height:20px; position:absolute; top:92px; left:0px;  "><label style="position:relative; top:25%"><binder:message code="Rua"/></label> </div>
				</div>
				<div  style=" width:429px; height:142px;  left:445px; position:absolute;  top:33px; " >
				
					<div  style=" width:406px; height:63px;  left:15px; position:absolute;  top:3px; " >					
						<div style="width:300px; height:20px; position:absolute; top:0px; left:0px;  ">
							<label style="position:relative; top:25%"><binder:message code="Complemento"/></label>
						</div>
						<textarea name="endereco.complemento" id="endereco.complemento" 
							style="width:394px; height:43px; position:absolute; top:20px; left:0px;  " ><jstl:out value='${endereco.complemento}'/></textarea>
					</div>
					<input name="endereco.numero" id="endereco.numero" type="text" 
						style="width:160px; height:20px; position:absolute; top:69px; left:120px;  " 
						value="<jstl:out value='${endereco.numero}'/>"/>
					<div style="width:100px; height:20px; position:absolute; top:69px; left:15px;  ">
						<label style="position:relative; top:25%"><binder:message code="N&uacute;mero"/></label>
					</div>
					<input name="endereco.caixaPostal" id="endereco.caixaPostal" type="text" 
						style="width:160px; height:20px; position:absolute; top:92px; left:120px;  " 
						value="<jstl:out value='${endereco.caixaPostal}'/>"/>
					<div style="width:100px; height:20px; position:absolute; top:92px; left:15px;  ">
						<label style="position:relative; top:25%"><binder:message code="Caixa_Postal"/></label>
					</div>
					<input name="endereco.cep" id="endereco.cep" type="text" 
						style="width:160px; height:20px; position:absolute; top:115px; left:120px;  " 
						value="<jstl:out value='${endereco.cep}'/>"/>
					<div style="width:100px; height:20px; position:absolute; top:115px; left:15px;  ">
						<label style="position:relative; top:25%"><binder:message code="Cep"/></label>
					</div>
				</div>
			</div>
			<!--
			<div  class="dates_vig" style=" width:880px; height:60px;  left:15px; position:absolute;  top:557px; ">
				<div class="default_titled_pane ui-corner-bottom " style="width:100%; height:30px;">
					<span>Selecione a Sua Imagem (Preenchimento Opcional)</span>
				</div>
				<center>
				<input name="participante.logotipo" id="participante.logotipo"
					 type="file" style="width:100%; height:25px;"/>
				</center>	 
			</div>-->
			
			<div  class="default_div_buttons   ui-corner-all "  align="center"  
				style=" width:887px; height:36px;  left:15px; position:absolute;  top:637px; " >
				<button id="saveParticipanteButton" style="  " onClick="jquerydoPost('baseParticipanteTabForm','saveParticipanteAction_ParticipanteMaintenanceForm.html','contentDiv'); return false;" >
					<binder:message code="Salvar"/>
					<img src="imgs/disk.png"/>
				</button>
			</div>
		</div>
	</form>
	</div>
	
	<div style=" width:909px;  left:15px; position:relative;  top:0px; " >
		<%@page import="com.adapit.web.DialogKind"%>
					<%@page import="java.util.Iterator"%>
					<%if(kind != null)try{ %>
							
					<br>
							<div class="ui-widget">
								<div class="ui-state-highlight ui-corner-all" style="margin-top: 20px; padding: 0 .7em;"> 
									<p><span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span>
									<strong><binder:message code="<%=title %>"/></strong></p>
									<table border="0" cellpadding="0" width="100%"
										cellspacing="2" 
										style="background-color: transparent; padding-top:5px; vertical-align: top; ">
										<tr>
											<td><%try{%><binder:message code="<%=msg %>"/><%}catch(Exception e1){out.print(msg);} %></td>
											<td width="32px" height="32px">
											<img src="imgs/<%
											if(kind== DialogKind.ErrorDialog) out.print("ErrorDialog");
											else if(kind== DialogKind.InformationDialog) out.print("Inform");
											else if(kind== DialogKind.QuestionDialog) out.print("ConfirmationDialog");
											else if(kind== DialogKind.SucessDialog) out.print("SucessDialog");
											else if(kind== DialogKind.WarningDialog) out.print("warn");
											else if(kind== DialogKind.WarningConfirmationDialog) out.print("dialog-warning");
											%>.png"/>
											</td>
										</tr>
										<%if(complement != null){ %>
										<tr><td colspan="2"><%=complement %></td></tr>
										<%} %>
										<% if(ht != null && ht.size() > 0){
											//O formul&aacute;rio cont&eacute;m erros
											//Apresenta&ccedil;&atilde;o da lista de erros do formul&aacute;rio
										%>
										<tr><td colspan="2"  >					
											
												<div class="ui-widget">
													<div class="ui-state-error ui-corner-all" > 
												    <jstl:forEach var="errorMessage" items="${errorMessages}">
												    	<div>
															
												    			<p><span class="ui-icon ui-icon-alert" style="float: left; "></span>
												    			<jstl:out value="${errorMessage}"/></p>				     		
													     	
													    </div>			    
												    </jstl:forEach>		
												    </div>
												 </div>	
										</td></tr>	
										<%} %>			
								</table>
								</div>
							</div>
							
							<br>
							<%	} catch(Exception ex){ex.printStackTrace();}%>
	</div>
</center>