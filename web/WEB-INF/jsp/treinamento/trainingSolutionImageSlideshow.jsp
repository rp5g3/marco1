<%@page import="com.adapit.portal.entidades.*" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="validation" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="binder" uri="http://www.springframework.org/tags"%>
<html>
<head>
	<title>Imagens do Treinamento</title>
	<link rel="stylesheet" href="../adapit/css/image-slideshow-vertical.css">
	
	<script type="text/javascript" src="../adapit/js/image-slideshow-vertical.js">
	/************************************************************************************************************
	(C) www.dhtmlgoodies.com, November 2005
	
	This is a script from www.dhtmlgoodies.com. You will find this and a lot of other scripts at our website.	
	
	Terms of use:
	You are free to use this script as long as the copyright message is kept intact. However, you may not
	redistribute, sell or repost it without our permission.
	
	Thank you!
	
	www.dhtmlgoodies.com
	Alf Magne Kalleland
	
	************************************************************************************************************/
	

	</script>
</head>
<body  bgcolor="#999999">
<%TrainingSolution p = (TrainingSolution) request.getAttribute("treinamento"); %>
<center><h3>Treinamento <%=p.getNome() %></h3></center>
<div id="dhtmlgoodies_slideshow" style="background-color: #999999">
	<div id="previewPane" align="center" style="vertical-align: middle;" >
		<span id="waitMessage">
			<table cellpadding="0" cellspacing="0" width="100%" height="100%">
				<tr><th colspan="4" align="center" style="font-family: verdana; font-size: 13; background-image: url('imgs/tablebackground.png');">Carregando a imagem. Por favor, aguarde...</th></tr>
			</table>
		</span>
		<div id="largeImageCaption"></div>
		<div id="largeImageDescription"></div>
	</div>
	<div id="galleryContainer">
		<div id="arrow_up"><img src="imgs/arrow-up.gif" id="arrow_up_image"></div>
		
		<div id="theImages">
			<div>
				<%
					
					java.util.Iterator<Imagem> itim = p.getImagens().iterator();
					int i=1;
										
					while(itim.hasNext()){
						Imagem im = itim.next();
						
				%>
						<a href="#" onclick="showPreview('comercialSolutionBigImage.html?image_id=<%=im.getId()%>','<%=i++%>');return false"><img src="comercialSolutionSmallImage.html?image_id=<%=im.getId()%>"></a>		
						<div class="imageCaption"><%=im.getRotulo()%></div>
						<div class="imageDescription"><table border=0 cellpadding=0 cellspacing=0 bordercolor=#000000 width="100%" style="background-color:transparent;" >
							<tr><th>Descri&ccedil;&atilde;o da Imagem</th></tr>
							<tr><td><%=im.getDescription()%></td></tr>
						</table></div>
				<%} %>
				<%
					java.util.Iterator<Imagem> itim2 = p.getImagens().iterator();
						
					while(itim2.hasNext()){
						Imagem im = itim2.next();
						
				%>
						<div class="imageCaption"><%=im.getRotulo()%></div>
						<div class="imageDescription"><table border=0 cellpadding=0 cellspacing=0 bordercolor=#000000 width="100%" style="background-color:transparent;" >
							<tr><th>Descri&ccedil;&atilde;o da Imagem</th></tr>
							<tr><td><%=im.getDescription()%></td></tr>
						</table></div>
				<%} %>		
			<div id="slideEnd"></div>
			</div>
		
		</div>
		<div id="arrow_down"><img src="imgs/arrow-down.gif" id="arrow_down_image"></div>
	</div>
</div>

</body>
</html>