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
	<ul class="topnav">  
    <li style="margin-left: 120px;" >
    	<a onClick="jqueryOpen('conteudo_inicial.html','contentDiv');">Home</a>
    </li>
    <!-- 
    	<ul class="subnav">  
            <li><a class="menu_link" onClick="jqueryOpen('conteudo_inicial.html','contentDiv');">Inicial</a></li>  
            <li><a class="menu_link" onClick="jqueryOpen('institucional.html?page_id=institucional_apresentacao_.html','contentDiv');">Apresenta&ccedil;&atilde;o</a></li>
            <li><a class="menu_link" onClick="jqueryOpen('institucional.html?page_id=institucional_solucoes_.html','contentDiv');">Solu&ccedil;&otilde;es</a></li>
        <% if (d != null){%> 
        	 <li style="font: bold 14px arial ; color: #DF1E29; font-size: 13px;"><center>Destaques</center></li> 
             <% if (destaqueNews != null){%>
            <li><a class="menu_link" onClick="jqueryOpen('news.html?newsid=<%=destaqueNews.getId()%>','contentDiv');">Not&iacute;cia</a></li>
             <%}if (destaqueSoftware != null){%>
            <li><a class="menu_link" onClick="jqueryOpen('produtos.html?sigla=<%=destaqueSoftware.getSigla()%>','contentDiv');">Sistema</a></li>
            <%}if (destaquePublication != null){%>
            <li><a class="menu_link" onClick="jqueryOpen('publication.html?publicationid=<%=destaquePublication.getId()%>','contentDiv');">Publica&ccedil;&atilde;o</a></li>
            <%}%>
    	<%}%>
    	<% if (user != null){%>
	    	<li></li>
	    	 <li><a class="menu_link" href="sair.html">Sair</a></li>											
	    <%}else{%>
	   
	    <% }%>
        </ul>
    </li>
     -->
     <li>
    	<a class="menu_link"  onClick="jqueryOpen('institucional.html?page_id=institucional_apresentacao_.html','contentDiv');">Institucional</a>
    	<ul class="subnav">  
             <li><a class="menu_link" onClick="jqueryOpen('institucional.html?page_id=institucional_apresentacao_.html','contentDiv');">Apresenta&ccedil;&atilde;o</a></li>   
             <li><a class="menu_link" onClick="jqueryOpen('institucional.html?page_id=institucional_empresa_.html','contentDiv');">Empresa</a></li>  
             <li><a class="menu_link" onClick="jqueryOpen('institucional.html?page_id=institucional_localizacao_.html','contentDiv');">Localiza&ccedil;&atilde;o</a></li>  
             <li><a class="menu_link" onClick="jqueryOpen('institucional.html?page_id=institucional_diferenciacao_.html','contentDiv');">Diferencia&ccedil;&atilde;o</a></li> 
             <li><a class="menu_link" onClick="jqueryOpen('institucional.html?page_id=institucional_solucoes_.html','contentDiv');">Solu&ccedil;&otilde;es</a></li> 
             <li><a class="menu_link" onClick="jqueryOpen('institucional.html?page_id=institucional_historico_.html','contentDiv');">Hist&oacute;rico</a></li> 
             <li><a class="menu_link" onClick="jqueryOpen('institucional.html?page_id=institucional_tecnologias_.html','contentDiv');">Tecnologias</a></li> 
             <li><a class="menu_link" onClick="jqueryOpen('institucional.html?page_id=institucional_processos_.html','contentDiv');">Processos</a></li> 
             <li><a class="menu_link" onClick="jqueryOpen('institucional.html?page_id=institucional_contato_.html','contentDiv');">Contato</a></li>  
        </ul> 
    </li>  
    <li>  
        <a class="menu_link" onClick="jqueryOpen('showFormacoes_.html?pageUrl=showAboutTrainings.html','contentDiv');">Treinamentos</a>  
        <ul class="subnav">  
             <li><a class="menu_link" onClick="jqueryOpen('showFormacoes_.html?pageUrl=showAboutTrainings.html','contentDiv');">Sobre os Treinamentos</a></li>   
             <li><a class="menu_link" onClick="jqueryOpen('showFormacoes_.html','contentDiv');">Confira Nossas Forma&ccedil;&otilde;es</a></li>  
             <li><a class="menu_link" onClick="jqueryOpen('showFormacoes_.html?pageUrl=showCatalogoTurmas.html','contentDiv');">Turmas Agendadas</a></li>  
        </ul>  
    </li>  
    <li>  
        <a class="menu_link"  onClick="jqueryOpen('produtos.html?information=softdema','contentDiv');">Solu&ccedil;&otilde;es em Software</a>  
        <ul class="subnav">  
             <li><a class="menu_link" onClick="jqueryOpen('produtos.html?information=softdema','contentDiv');">Servi&ccedil;os</a></li>   
             <li><a class="menu_link" onClick="jqueryOpen('produtos.html?information=dif','contentDiv');">Elementos de Diferencia&ccedil;&atilde;o</a></li>  
             <li><a class="menu_link" onClick="jqueryOpen('produtos.html?information=proc','contentDiv');">Processos de Produ&ccedil;&atilde;o</a></li>  
             <% if (d != null){%>
             <li style="font: bold 14px arial ; color: #DF1E29; font-size: 13px;"><center>Sistema em Destaque</center></li> 
             <%if (destaqueSoftware != null){%>
             <li><a class="menu_link" onClick="jqueryOpen('produtos.html?sigla=<%=destaqueSoftware.getSigla()%>','contentDiv');"><%=destaqueSoftware.getNome()%></a></li>   
             <%}%>
    		 <%}%>   
        </ul> 
    </li>   
    <li><a class="menu_link" onClick="jqueryOpen('news.html?newsid=<%=destaqueNews != null?destaqueNews.getId():1%>','contentDiv');">Not&iacute;cias</a></li>
    <li><a class="menu_link" onClick="jqueryOpen('publication.html?publicationid=0','contentDiv');">Artigos</a></li>
    <li><a class="menu_link" onClick="jqueryOpen('institucional.html?page_id=institucional_contato_.html','contentDiv');">Contato</a></li>
    <%if(user != null){ %>
    
    <%} %>
</ul>