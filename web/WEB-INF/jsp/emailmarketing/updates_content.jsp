<%@ taglib prefix="jstl" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="validation" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="binder" uri="http://www.springframework.org/tags"%>	

<%@page import="com.adapit.portal.dto.UsuarioDTO"%><LINK rel="stylesheet" type="text/css" href="css/main.css"/>
<%
String langl = (String) request.getSession().getAttribute("lang");
if(langl == null)
	langl = "pt";
%>
<script type="text/javascript" src="js/jquery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery-ui-1.8.4.custom.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery.bgiframe-2.1.1.js"></script>
<link href="css/jquery/ticker-style.css" rel="stylesheet" type="text/css" />
<script src="js/jquery/jquery.ticker.js" type="text/javascript"></script>
<script src="js/jquery/jquery.min.js" type="text/javascript"></script>
<%@page import="com.adapit.portal.entidades.Update"%>
<%@page import="com.adapit.portal.entidades.UpdateFile"%>

<%@page import="java.util.List"%>
<div style="background-color: #950505">
<style>
p{
	font-family: 'Arial',sans-serif;
 	font-size: 12px;
  	line-height: normal;
   	color: white;
}
table tr td{
	font-family: 'Arial',sans-serif;
 	font-size: 12px;
  	line-height: normal;
   	color: white;
}
table th{
	font-family: 'Arial',sans-serif;
 	font-size: 13px;
  	line-height: normal;
   	font-weight: bold;
	color: white;
}

</style>
<!-- 
<img src="imgs/mailmarketing/adapit_update_top.png"/>
 -->
<%
	String sigleBean = (String) request.getAttribute("sigla");
	Update updateBean = (Update) request.getAttribute("update");
	List<UpdateFile> updateBeanFiles = (List<UpdateFile>) request.getAttribute("updateFiles");
%>
&nbsp;
	<div style="width: 100%; margin-top: 30px; margin-bottom:20px;">
		<div style="width: 144px; height: 244px; float: left; margin-left:50px;">
			<img alt="logo <%=sigleBean %>" src="imgs/mailmarketing/adapit_updates_<%=sigleBean %>_logo.png"/>
		</div>
		<div style="padding:20px; float:left; width:460px; height:150px;">
		<font style="font-family: 'Arial',sans-serif; font-size: 13px; line-height: normal; font-weight: bold; color: white;">
			<binder:message code="format.date"  arguments="<%= updateBean.getDataPublicacao() %>"/>
			<%=updateBean.getTitulo() %> 
		</font>
		<p><%=updateBean.getResumo() %></p>
		
		</div>
	</div>
	<div style="width: 640px;  clear:both; padding-left:30px; padding-right:30px;">
	<p><%=updateBean.getDescricao() %></p>
	</div>
	<%
	if (updateBeanFiles != null && updateBeanFiles.size()>0){
		%>
	<div style="width: 640px; padding:30px; clear:both;">
		<hr></hr>
		<center>
			<font style="font-family: 'Arial',sans-serif; font-size: 13px; line-height: normal; font-weight: bold; color: white;">
				<%=(langl.equalsIgnoreCase("en")?"Available Files to Download":"Arquivos Dispon&iacute;veis para Download") %>:
			</font>
		</center>
		<br>&nbsp;</br>
		<table border="1" width="640px">
			<tr>
				<th><center><%=(langl.equalsIgnoreCase("en")?"Modification<br>Date</br>":"Data da<br>modifica&ccedil;&atilde;o</br>") %></center></th>
				<th><center><%=(langl.equalsIgnoreCase("en")?"File <br>Format</br>":"Tipo do<br>arquivo</br>") %></center></th>
				<th><%=(langl.equalsIgnoreCase("en")?"File":"Arquivo") %></th>
				<th><%=(langl.equalsIgnoreCase("en")?"Help":"Ajuda") %></th>
			</tr>
		<%
			for(UpdateFile up: updateBeanFiles){%>
		
			<% 
			if(up.getCurrentFile() == null)
				continue;
			String fileName = up.getCurrentFile().getName();//up.getCurrentFile().getName().substring(up.getCurrentFile().getName().indexOf("."));
			//String instName = up.getInstallationFile().getName();
			UsuarioDTO ud = (UsuarioDTO) request.getSession().getAttribute("user");
			if(!up.isRestrict() || (ud != null && up.isAutorized(ud.getParticipanteId()))){
			%>
			
			<tr>
				<td><binder:message code="format.date" 
					arguments="<%= up.getDate() %>"/></td>
				<td><%=up.getUpdateFileKind().name().replace("_"," ") %></td>
				<td><a class="white_link"
				href="download_dbfile.html?updatefile_id=<%=up.getId()%>&file_id=<%=up.getCurrentFile().getId() %>"
				><%=fileName %></a></td>
				<td><%if(up.getInstallationFile() != null){ %>
				<a class="white_link" 
				href="download_dbfile.html?updatefile_id=<%=up.getId()%>&file_id=<%=up.getInstallationFile().getId() %>"
				name="<%=up.getInstallationFile().getName() %>"
				>Instru&ccedil;&otilde;es</a>
				<%} else out.print("&nbsp");%></td>
			</tr>		
		<%	}	//end if
		}//end for
		%>
		</table>
	</div>
	<%
	}%>
</div>


