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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" >
<head runat="server" >
    	<title>MenuMatic Horizontal Example</title>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
		<link rel="stylesheet" type="text/css" href="http://yui.yahooapis.com/2.5.0/build/reset/reset-min.css">
		<link rel="stylesheet" href="css/MenuMatic/style.css" type="text/css" media="all" charset="utf-8" />
		<link rel="stylesheet" href="css/MenuMatic/MenuMatic.css" type="text/css" media="screen" charset="utf-8" />
		<!--[if lt IE 7]>
			<link rel="stylesheet" href="css/MenuMatic/MenuMatic-ie6.css" type="text/css" media="screen" charset="utf-8" />
		<![endif]-->
</head>
<body>
    
   
<div style="width: 800px;"> 		
   		    
		<!-- BEGIN Menu -->
	<ul id="nav">
    <li >
    	<a href="#">Home</a>
    	<ul >  
            <li><a  onClick="jqueryOpen('conteudo_inicial.html','contentDiv');">Inicial</a></li>  
            <li><a  onClick="jqueryOpen('institucional.html?page_id=institucional_apresentacao_.html','contentDiv');">Apresenta&ccedil;&atilde;o</a></li>
            <li><a  onClick="jqueryOpen('institucional.html?page_id=institucional_solucoes_.html','contentDiv');">Solu&ccedil;&otilde;es</a></li>
        <% if (d != null){%> 
        	<li></li>
             <% if (destaqueNews != null){%>
            <li><a  onClick="jqueryOpen('news.html?newsid=<%=destaqueNews.getId()%>','contentDiv');">Not&iacute;cia em Destaque</a></li>
             <%}if (destaqueSoftware != null){%>
            <li><a  onClick="jqueryOpen('produtos.html?sigla=<%=destaqueSoftware.getSigla()%>','contentDiv');">Software em Destaque</a></li>
            <%}if (destaquePublication != null){%>
            <li><a  onClick="jqueryOpen('publication.html?publicationid=<%=destaquePublication.getId()%>','contentDiv');">Publica&ccedil;&atilde;o em Destaque</a></li>
            <%}%>
    	<%}%>
    	<% if (user != null){%>
	    	<li></li>
	    	 <li><a  href="sair.html">Sair</a></li>											
	    <%}else{%>
	   
	    <% }%>
        </ul>
    </li>  
    <li>  
        <a  href="#">Treinamentos</a>  
        <ul >  
             <li><a  onClick="jqueryOpen('showFormacoes_.html?pageUrl=showAboutTrainings.html','contentDiv');">Sobre os Treinamentos</a></li>   
             <li><a  onClick="jqueryOpen('showFormacoes_.html','contentDiv');">Confira Nossas Forma&ccedil;&otilde;es</a></li>  
             <li><a  onClick="jqueryOpen('showFormacoes_.html?pageUrl=showCatalogoTurmas.html','contentDiv');">Turmas Agendadas</a></li>  
        </ul>  
    </li>  
    <li>  
        <a  href="#">Solu&ccedil;&otilde;es em Software</a>  
        <ul >  
             <li><a  onClick="jqueryOpen('produtos.html?information=softdema','contentDiv');">Desenvolvimento de Software por Demanda</a></li>   
             <li><a  onClick="jqueryOpen('produtos.html?information=dif','contentDiv');">Elementos de Diferencia&ccedil;&atilde;o</a></li>  
             <li><a  onClick="jqueryOpen('produtos.html?information=proc','contentDiv');">Processos de Produ&ccedil;&atilde;o</a></li>  
             <li><a  onClick="jqueryOpen('produtos.html?information=contato','contentDiv');">Contato</a></li> 
             <% if (d != null){%>
             <li></li> 
             <li><a  onClick="jqueryOpen('produtos.html?information=softdema','contentDiv');">Confira Nossos Softwares</a></li>  
             <%if (destaqueSoftware != null){%>
             <li><a  onClick="jqueryOpen('produtos.html?sigla=<%=destaqueSoftware.getSigla()%>','contentDiv');">Destaque - <%=destaqueSoftware.getNome()%></a></li>   
             <%}%>
    		 <%}%>   
        </ul> 
    </li>   
    <li>
    	<a  href="#">Institucional</a>
    	<ul >  
             <li><a  onClick="jqueryOpen('institucional.html?page_id=institucional_apresentacao_.html','contentDiv');">Apresenta&ccedil;&atilde;o</a></li>   
             <li><a  onClick="jqueryOpen('institucional.html?page_id=institucional_empresa_.html','contentDiv');">Empresa</a></li>  
             <li><a  onClick="jqueryOpen('institucional.html?page_id=institucional_localizacao_.html','contentDiv');">Localiza&ccedil;&atilde;o</a></li>  
             <li><a  onClick="jqueryOpen('institucional.html?page_id=institucional_diferenciacao_.html','contentDiv');">Diferencia&ccedil;&atilde;o</a></li> 
             <li><a  onClick="jqueryOpen('institucional.html?page_id=institucional_solucoes_.html','contentDiv');">Solu&ccedil;&otilde;es</a></li> 
             <li><a  onClick="jqueryOpen('institucional.html?page_id=institucional_historico_.html','contentDiv');">Hist&oacute;rico</a></li> 
             <li><a  onClick="jqueryOpen('institucional.html?page_id=institucional_tecnologias_.html','contentDiv');">Tecnologias</a></li> 
             <li><a  onClick="jqueryOpen('institucional.html?page_id=institucional_processos_.html','contentDiv');">Processos</a></li> 
             <li><a  onClick="jqueryOpen('institucional.html?page_id=institucional_contato_.html','contentDiv');">Contato</a></li>  
        </ul> 
    </li>
    <li>
    	<a  href="#">Eventos e Artigos</a>
    	<ul >  
             <li><a  onClick="jqueryOpen('news.html?newsid=0','contentDiv');">Not&iacute;cias</a></li>   
             <li><a  onClick="jqueryOpen('publication.html?publicationid=0','contentDiv');">Artigos</a></li>  
             <li><a  onClick="jqueryOpen('palestra.html?palestraid=0','contentDiv');">Palestras</a></li>  
             <li><a  target="_blanck" 
             href="http://zerohora.clicrbs.com.br/zerohora/jsp/default2.jsp?uf=1&local=1&source=a1727029.xml&template=3898.dwt&edition=9069">
             Mat&eacute;ria ZH</a></li> 
              <% if (d != null){%>
              <%if (destaqueNews != null){%>
             <li></li> 
             <li><a  onClick="jqueryOpen('news.html?newsid=<%=destaqueNews.getId()%>','contentDiv');"><%=destaqueNews.getTitulo()%></a></li> 
             <%}%>
    		<%}%>
        </ul> 
    </li>    
    <li><a  onClick="jqueryOpen('news.html?newsid=0','contentDiv');">Not&iacute;cias</a></li> 
    <li><a  onClick="jqueryOpen('institucional.html?page_id=institucional_contato_.html','contentDiv');">Contato</a></li>
</ul>


</div>  
</body>

</html>