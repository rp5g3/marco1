<%@page import="com.adapit.portal.dto.UsuarioDTO"%>
<%
	UsuarioDTO user = null;
	user = (UsuarioDTO) request.getSession(true).getAttribute("user");
%>	
<%@page import="com.adapit.portal.entidades.*"%>
<%@page import="com.adapit.portal.services.PersonType"%>

<%
	Destaque d = (Destaque) request.getAttribute("destaque");
	News destaqueNews = null;
	SoftwareSolution destaqueSoftware = null;
	Publication destaquePublication = null;
	if (d != null){
	 destaqueNews = d.getNews();
	 destaqueSoftware = d.getSoftware();
	 destaquePublication = d.getPublication();
	}
	java.util.Date dt = new java.util.Date();
	
	int ano = dt.getYear()+1900;
%>
	<div class="topnav" style="height: 30px; background-color: #232323; padding-top: -30px;">  
    	<a class="menu_link" onClick="jqueryOpen('conteudo_inicial.html','contentDiv');">Home</a>
    	&nbsp;|&nbsp;<a class="menu_link" onClick="jqueryOpen('showFormacoes_.html?pageUrl=showAboutTrainings.html','contentDiv');">Treinamentos</a>  
        &nbsp;|&nbsp;<a class="menu_link" onClick="jqueryOpen('produtos.html?information=softdema','contentDiv');">Solu&ccedil;&otilde;es em Software</a>  
        &nbsp;|&nbsp;<a class="menu_link" onClick="jqueryOpen('institucional.html?page_id=institucional_apresentacao_.html','contentDiv');">Institucional</a>
    	&nbsp;|&nbsp;<a class="menu_link" onClick="jqueryOpen('publication.html?publicationid=0','contentDiv');">Artigos</a>
    	&nbsp;|&nbsp;<a class="menu_link" onClick="jqueryOpen('news.html?newsid=0','contentDiv');">Not&iacute;cias</a></li> 
    	&nbsp;|&nbsp;<a class="menu_link"onClick="jqueryOpen('institucional.html?page_id=institucional_contato_.html','contentDiv');">Contato</a></li>
</div>