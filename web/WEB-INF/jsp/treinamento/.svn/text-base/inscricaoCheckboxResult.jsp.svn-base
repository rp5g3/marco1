<% 
	int treinamentoId = (Integer) request.getAttribute("treinamentoid"); 
	String login = (String) request.getAttribute("login");
	boolean marcado = (Boolean) request.getAttribute("marcado");
%>
<INPUT type="checkbox"  
	name="participarTreinamentoCheckBox<%=treinamentoId%>2" 
	style="width:16px; height:16px" 
	onClick="jqueryOpen('participarTurmaTreinamento.html?usuario_id=<%=login%>&treinamento_id=<%=treinamentoId%>','inscricaoResultDiv<%=login%><%=treinamentoId%>'); return false;"
	<%if(marcado){ %>checked="checked"<%} %>/>
