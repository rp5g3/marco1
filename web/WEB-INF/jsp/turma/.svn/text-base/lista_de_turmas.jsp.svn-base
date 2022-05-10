<%@ taglib prefix="jstl" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="validation" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="binder" uri="http://www.springframework.org/tags"%>	
<%@page import="com.adapit.portal.entidades.*"%>
<%@page import="java.util.*"%>



	
<style>
<!-- /* Style Definitions */
td.leiloesLeftTitleLarge {
	background-image: url(imgs/leiloesleftcorner.png);
	background-repeat:no-repeat;
	width: 17px;
	height: 21px;
	border-color: black;
	border-right-width: 0px;
	border-bottom-width: 0px;
	border-top-width: 0px;
	border-left-width: 0px;
}

td.newsContentLeftImg {
	background-image: url(imgs/bullet_feed.png);
	background-repeat:no-repeat;
	width: 16px;
	height: 16px;
	border-width: 0px;
}

td.newsCenterTitleLarge {
	background-image: url(imgs/menubk3.png);
	width: 560px;
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
	vertical-align: middle;
}

td.newsContentLarge {
	background-color: transparent;
	border-width: 0px;
	padding-left: 5px;
}
-->
</style>


				<table border="0" cellpadding="0" width="100%"
					cellspacing="0" style="background-color: transparent;">
					<tr>	
						<td class="leiloesLeftTitleLarge"></td>		
						<td class="newsCenterTitleLarge">
							<b>Treinamentos agendados</b>
							<div align="right"><%
							Integer paginate = (Integer) request.getAttribute("paginate");
							Integer pnumber = (Integer) request.getAttribute("pageNumber");
							if (paginate != null && paginate.intValue() > 0){
								for(int i=1; i <= paginate; i++){
							%>
								<%if (pnumber != null){ %>
									<%if(pnumber.intValue() == i) out.print(" "+i+" ");
									  else {%>
										<a href="" onclick="adapitPost('contentintro_.html?pageNumber=<%=i %>','leilaoContentDiv'); return false;"><%=i %></a>
									<%} %>
								<%}else{ %>
									<%if(i == 1) out.print(" 1 ");
									  else {%>
										<a href="" onclick="adapitPost('contentintro_.html?pageNumber=<%=i %>','leilaoContentDiv'); return false;"><%=i %></a>
									<%} %>
								<%} %>
							<%	}
							} %>
							</div>
						</td>						
					</tr>
					<tr>
						<td colspan="2" class="newsContent" >	
							<%try{ %>
								<%@include file="catalogoTurma.jsp" %>								
							<%}catch(Exception ex){ex.printStackTrace();} %>
						</td>
					</tr>									
				</table>