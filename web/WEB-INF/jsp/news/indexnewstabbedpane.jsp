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

<script type="text/javascript"><!--

				jQuery(document).ready(function(){
					$('.newsAccordionDiv.head').click(function() {
						$(this).next().toggle('slow');
						return false;
					}).next().hide();
				});
				/*jQuery(document).ready(function(){
					$('.technoAccordionDiv.head').click(function() {
						$(this).next().toggle();
						return false;
					}).next().hide();
				});*/
				$(function() {
					$("#newsAccordionDiv").accordion();
				});
							
</script>


	
<style>
<!-- /* Style Definitions */

div.newsleftcorner{
	background-image: url(imgs/newsleftcorner.png);
	background-repeat:no-repeat;
	width: 17px;
	height: 21px;
	border-color: black;
	border-right-width: 0px;
	border-bottom-width: 0px;
	border-top-width: 0px;
	border-left-width: 0px;
	float:left;
}
div.defaultbigtitledpane{
	background-image: url(imgs/menubk3.png);
	width: 740px;
	height: 21px;
	border-color: black;
	border-right-width: 1px;
	border-bottom-width: 0px;
	border-top-width: 0px;
	border-left-width: 0px;
	color: white;
	border-style: solid;
	font-family: Arial;
	font-size: 12px;
	valign: middle;
	float:left;
}
div.defaultbigcontentpane {
	background-color: transparent;
	border-bottom-color: black;
	border-bottom-width: 1px;
	border-left-color: black;
	border-left-width: 1px;
	border-right-color: black;
	border-right-width: 1px;
	border-top-color: black;
	border-top-width: 1px;
	border-style: solid;
	padding-bottom:5px;

	width:756px;
	clear:both;
}

div.defaultmediumcontentpane {
	background-color: transparent;
	border-bottom-color: black;
	border-bottom-width: 1px;
	border-left-color: black;
	border-left-width: 1px;
	border-right-color: black;
	border-right-width: 1px;
	border-top-color: black;
	border-top-width: 1px;
	border-style: solid;
	padding-bottom:5px;

	width:492px;
	clear:both;
}
div.defaultmediumtitledpane{
	background-image: url(imgs/menubk3.png);
	width: 476px;
	height: 21px;
	border-color: black;
	border-right-width: 1px;
	border-bottom-width: 0px;
	border-top-width: 0px;
	border-left-width: 0px;
	color: white;
	border-style: solid;
	font-family: Arial;
	font-size: 12px;
	valign: middle;
	float:left;
}
-->
</style>
<%News firstNews =null; %>
	<div style="clear:both; position=relative; padding-left:10px; padding-right:10px; padding-top:5px;" align="left">	

					<!--<div class="newsleftcorner"></div>		
					--><div class="panel_title ui-corner-top">
						<center><font class="font_title"><b>&Uacute;ltimas Not&iacute;cias</b></font></center>
											
					</div>
					<div class="panel_content ui-corner-bottom" style="padding:10px;">	
						<div id="newsAccordionDiv" >								
							<%
								List<News> list = (List<News>) request.getAttribute("newsList");
								if (list != null && list.size()>0){
									java.util.Iterator<News> it = list.iterator();
									int contador=0;
									
									while(it.hasNext()){
										News news = it.next();
										if (contador == 0) firstNews = news;
							%>
							<h3 >
					 		<a href="#"><Font color="Black" size="2" face="Arial"><b><binder:message code="format.date" 
									 arguments="<%= news.getDataPublicacao() %>"/> - <%= news.getTitulo() %></b></Font></a>
					 		</h3>
									<div style="padding: 5px;">
									<!--<div class="newsContentLeftImg" ></div>		-->
									<!--<div style="<%if(it.hasNext()){ %>border-bottom-color: gray; border-bottom-width: 1px; border-bottom-style: dashed;<%} %> padding:10px;">-->
										<br><b><binder:message code="format.date"  arguments="<%= news.getDataPublicacao() %>"/> - 
										<i><%= news.getTitulo() %></i></b>
										<p><%= news.getResumo() %></p>
										<p><a href=""			
											onclick="jqueryOpen('news.html?newsid=<%= news.getId() %>','contentDiv'); return false;"			
											style="cursor: hand; cursor: pointer;">Saiba mais ...</a></p>
									<!--</div>	-->					
									</div>
							
							<% 
									contador++;
									}
									%>
									
									<%
							}%>	
						</div>
						<br><center><a href=""			
											onclick="jqueryOpen('news.html?newsid=<%= firstNews.getId() %>','contentDiv'); return false;"			
											style="cursor: hand; cursor: pointer;">Todas as Not&iacute;cias</a></center>
										
					</div>		
				
					
	</div>	