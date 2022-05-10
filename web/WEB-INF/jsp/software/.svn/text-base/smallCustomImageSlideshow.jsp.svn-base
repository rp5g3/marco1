<%@page import="com.adapit.portal.entidades.*" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="validation" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="binder" uri="http://www.springframework.org/tags"%>
<style type="text/css">
a.linkopacity img {
filter:alpha(opacity=80); 
-moz-opacity: 0.5; 
opacity: 0.5;}

a.linkopacity:hover img {
filter:alpha(opacity=100);   
-moz-opacity: 1.0;   
opacity: 1.0;
}
</style>

<%
	SoftwareSolution p = (SoftwareSolution)
		request.getSession().getAttribute("software");
	if (p == null) p = (SoftwareSolution) request.getAttribute("software");
	java.util.Date dt = new java.util.Date();
	String random = dt.getHours()+"_"+dt.getMinutes()+"_"+dt.getSeconds();
%>
<table cellpadding="0" cellspacing="0" border="1" style="background-color: transparent;" width="100%">
	<tr>
		 <td width="100px" height="400px;" valign="top">
			<div style="overflow:auto; height: 400px;">		 
				<center>
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
						<a class="linkopacity" style="cursor: pointer;"
							onclick="jqueryOpen('imageDetailsSmall.html?image_id=<%=im.getId()%>&img_pos=<%=j %>','largeImageDiv<%=p.getId()%><%=random%>');return false"
							title=<%=im.getRotulo().replace(" ","&nbsp;") %>
							>
							<img border="1" src="comercialSolutionSmallImage.html?image_id=<%=im.getId()%>" style="cursor: hand;	cursor: pointer;">
						</a>				
				<%} %>
				</center>
				</div>
			</td>
			<td height="200px;" valign="top">
				<div id="largeImageDiv<%=p.getId()%><%=random%>" 
					style="padding:10px;">		
					<%if(p.getImagens().size()>0){ %>
					<center><b>Selecione a imagem para visualiza&ccedil;&atilde;o</b></center>
					<%} else{%>
					<center><b>Nenhuma imagem dispon&iacute;vel</b></center>
					<%} %>									
				</div>
			</td>
		</tr>
	</table>