<%@ taglib prefix="jstl" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="validation" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="binder" uri="http://www.springframework.org/tags"%>	
<%@page import="com.adapit.portal.entidades.*"%>
<%@page import="java.util.*"%>
<LINK rel="stylesheet" type="text/css" href="css/main.css"/>

<script type="text/javascript" src="js/jquery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery-ui-1.8.4.custom.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery.bgiframe-2.1.1.js"></script>
<style type="text/css">
   #just_dialog div button { font-size: 62.5%; }
</style>
<link href="css/jquery/ticker-style.css" rel="stylesheet" type="text/css" />
<script src="js/jquery/jquery.ticker.js" type="text/javascript"></script>
<script src="js/jquery/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(function () {
	$('#js-update').ticker();
});


</script>
<div id="ticker-wrapper" class="no-js">
		<ul id="js-update" class="js-hidden">
			
			
		<%
								List<Update> list = (List<Update>) request.getAttribute("updateList");
								if (list != null && list.size()>0){
									java.util.Iterator<Update> it = list.iterator();
									int contador=0;
									
									while(it.hasNext()){
										Update update = it.next();
										
		%>
			
				<li class="update-item"><a href="#" onclick="jqueryOpen('update.html?updateid=<%= update.getId() %>','contentDiv'); return false;">
					<binder:message code="format.date" 
									 arguments="<%= update.getDataPublicacao() %>"/> - <%= update.getTitulo() %> .:
									 <%=update.getResumo() %> 
					
				</a></li>
			<%}
									
		}%>
			
		</ul>
	</div>