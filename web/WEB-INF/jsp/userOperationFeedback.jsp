		<%@page import="com.adapit.web.DialogKind"%>
		<%
			String title = (String) request.getAttribute("title");
			String msg = (String) request.getAttribute("msg");
			DialogKind kind = (DialogKind) request.getAttribute("kind");
			String complement = (String) request.getAttribute("complement");
			java.util.Hashtable<String,String> ht = (java.util.Hashtable) request.getAttribute("errorFields");
		%>
		<%if(kind != null)try{ %>
		
<%@page import="java.util.Iterator"%><br>
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