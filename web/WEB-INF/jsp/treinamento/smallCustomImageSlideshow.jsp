<%@page import="com.adapit.portal.entidades.*" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="validation" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="binder" uri="http://www.springframework.org/tags"%>
<style type="text/css">
a.linkopacity img {
filter:alpha(opacity=50); 
-moz-opacity: 0.5; 
opacity: 0.5;}

a.linkopacity:hover img {
filter:alpha(opacity=100);   
-moz-opacity: 1.0;   
opacity: 1.0;
}
</style>

<%
	TrainingSolution p = (TrainingSolution)
		request.getSession().getAttribute("treinamento");
	if (p == null) p = (TrainingSolution) request.getAttribute("training");
	java.util.Date dt = new java.util.Date();
	String random = dt.getHours()+"_"+dt.getMinutes()+"_"+dt.getSeconds();
%>
<table cellpadding="0" cellspacing="0" style="background-color: transparent;" width="100%">
	<tr>
		 <td width="90px">
				<%
					
					java.util.Iterator<Imagem> itim = p.getImagens().iterator();
					int i=1;
										
					//while(itim.hasNext()){
					for (int j=0; j< p.getImagens().size(); j++){
						Imagem im = p.getImagens().get(j);//itim.next();
						int prev=-1;
						int next=-1;
						if (j-1 > 0) prev = p.getImagens().get(j-1).getId();
						if (j+1 > p.getImagens().size()) next = p.getImagens().get(j+1).getId();
				%>
						<a class="linkopacity" style="cursor: hand;	cursor: pointer;"
							onclick="jqueryOpen('imageDetailsSmall.html?image_id=<%=im.getId()%>&img_pos=<%=j %>','largeImageDiv<%=p.getId()%><%=random%>');return false"
							title=<%=im.getRotulo().replace(" ","_") %>>
							<img src="comercialSolutionSmallImage.html?image_id=<%=im.getId()%>" style="cursor: hand;	cursor: pointer;">
						</a>				
				<%} %>
			</td>
			<td width="300px;" height="200px;">
				<div id="largeImageDiv<%=p.getId()%><%=random%>" 
					dojoType="dijit.layout.ContentPane" 
					style="padding:10px;" 
					onUnload="overlay.hide(); vertical-align:middle;" 
					cacheContent="false">		
					<center>Selecione a Imagem</center>									
				</div>
			</td>
		</tr>
	</table>