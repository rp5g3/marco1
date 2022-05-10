<%@ taglib prefix="jstl" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="validation" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="binder" uri="http://www.springframework.org/tags"%>
<%@page import="com.adapit.portal.entidades.*"%>
<%@page import="com.adapit.portal.services.PersonType"%>
<%@page import="com.adapit.portal.dto.UsuarioDTO"%>

<link rel="stylesheet" type="text/css" href="css/domenu/office_xp.css" />
<script type="text/javascript" src="js/domenu/jsdomenu.js"></script>
<script type="text/javascript" src="js/domenu/jsdomenubar.js"></script>
<script type="text/javascript" src="js/domenu/jsdomenu.inc.js"></script>

<%
	UsuarioDTO user = null;
	user = (UsuarioDTO) request.getSession(true).getAttribute("user");
%>	
<script type="text/javascript">
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
%>
function createjsDOMenu() {
   homeMenu = new jsDOMenu(240, "absolute");
  with (homeMenu) {
    addMenuItem(new menuItem("Inicial", "", "conteudo_inicial.html"));
    addMenuItem(new menuItem("Apresenta&ccedil;&atilde;o", "item2", "institucional.html?page_id=institucional_apresentacao_.html"));
    addMenuItem(new menuItem("Solu&ccedil;&otilde;es", "", "institucional.html?page_id=institucional_solucoes_.html"));
    <% if (d != null){%> addMenuItem(new menuItem("-"));
    <% if (destaqueNews != null){%>
    addMenuItem(new menuItem("Not&iacute;cia em Destaque", "", "news.html?newsid=<%=destaqueNews.getId()%>"));
    <%}if (destaqueSoftware != null){%>
    addMenuItem(new menuItem("Software em Destaque", "", "produtos.html?sigla=<%=destaqueSoftware.getSigla()%>"));
    <%}if (destaquePublication != null){%>
    addMenuItem(new menuItem("Publica&ccedil;&atilde;o em Destaque", "", "publication.html?publicationid=<%=destaquePublication.getId()%>"));
    <%}%>
    <%}%>
    <% if (user != null){%>
     addMenuItem(new menuItem("-"));
    addMenuItem(new menuItem("Sair", "", "fullscreen:sair.html"));
												
    <%}else{%>
   
    <% }%>
  }

  allProdutosMenu = new jsDOMenu(340, "absolute");
  with (allProdutosMenu) {
    addMenuItem(new menuItem("Desenvolvimento de Software por Demanda", "", "produtos.html?information=softdema"));
    addMenuItem(new menuItem("Elementos de Diferencia&ccedil;&atilde;o", "", "produtos.html?information=dif"));
    addMenuItem(new menuItem("Processos de Produ&ccedil;&atilde;o", "", "produtos.html?information=proc"));
    addMenuItem(new menuItem("Contato", "", "produtos.html?information=contato"));
    <% if (d != null){%>
    addMenuItem(new menuItem("-"));
    addMenuItem(new menuItem("Confira Nossos Softwares", "", "produtos.html?information=softdema"));
    <%if (destaqueSoftware != null){%>
    addMenuItem(new menuItem("Destaque - <%=destaqueSoftware.getNome()%>", "", "produtos.html?sigla=<%=destaqueSoftware.getSigla()%>"));
    <%}%>
    <%}%>
    
  }

  materiasMenu = new jsDOMenu(340, "absolute");
  with (materiasMenu) {
    addMenuItem(new menuItem("Not&iacute;cias", "", "news.html?newsid=0"));
    addMenuItem(new menuItem("Artigos", "", "publication.html?publicationid=0"));
    addMenuItem(new menuItem("Palestras", "", "palestra.html?palestraid=0"));
    addMenuItem(new menuItem("Mat&eacute;ria ZH", "", "http://zerohora.clicrbs.com.br/zerohora/jsp/default2.jsp?uf=1&local=1&source=a1727029.xml&template=3898.dwt&edition=9069"));
    <% if (d != null){%>
    addMenuItem(new menuItem("-"));
    <%if (destaqueNews != null){%>
    addMenuItem(new menuItem("<%=destaqueNews.getTitulo()%>", "", "news.html?newsid=<%=destaqueNews.getId()%>"));
    <%}%>
    <%}%>
    
  }

  produtosMenu = new jsDOMenu(340, "absolute");
  with (produtosMenu) {
    addMenuItem(new menuItem("WCT - Sistema de MDA, MDD e MDE", "", "produtos_wct.html"));
    addMenuItem(new menuItem("WCT - Ger&ecirc;ncia de Configura&ccedil;&atilde;o", "", "produtos_wct_config_management.html"));
    addMenuItem(new menuItem("WCT Java - Transformadores MDD para Java", "", "produtos_wct_java.html"));
    addMenuItem(new menuItem("WCT BPMN - Desenvolvimento MDD e BPMN", "", "produtos_wct_bpmn.html"));
    addMenuItem(new menuItem("-"));
    addMenuItem(new menuItem("Sistema de Com&eacute;rcio Eletr&ocirc;nico", "", "produtos_ecommerce.html"));
  }
  
  fabricaSoftwareMenu2 = new jsDOMenu(400, "absolute");
  with (fabricaSoftwareMenu2) {   
    addMenuItem(new menuItem("Para Automatiza&ccedil;&atilde;o de Processos de Desenvolvimento", "negocios", "blank.htm"));
    addMenuItem(new menuItem("De Gera&ccedil;&atilde;o de C&oacute;digo para Plataformas Diversas", "negocios", "blank.htm"));
    addMenuItem(new menuItem("Acompanhamento na Execu&ccedil;&atilde;o de Projetos Usando MDD", "negocios", "blank.htm"));
    addMenuItem(new menuItem("Para Definir de Linhas de Produto de Software", "pla", "blank.htm"));

  }
  
  fabricaSoftwareMenu3 = new jsDOMenu(230, "absolute");
  with (fabricaSoftwareMenu3) {   
    addMenuItem(new menuItem("Na Arquitetura Java", "pla", "blank.htm"));
    addMenuItem(new menuItem("Em UML e Projetos de Sistemas", "pla", "blank.htm"));
    addMenuItem(new menuItem("Em Mapeamento Objeto Relacional", "pla", "blank.htm"));
    addMenuItem(new menuItem("Em BPMN e BPEL", "pla", "blank.htm"));  
    addMenuItem(new menuItem("Em MDD, MDA e MDE", "pla", "blank.htm")); 
  }

  fabricaSoftwareMenu1 = new jsDOMenu(300, "absolute");
  with (fabricaSoftwareMenu1) {   
    addMenuItem(new menuItem("Servi&ccedil;os para F&aacute;bricas de Software", "fabrica_software", "fabrica_software_servicos.html"));    
    addMenuItem(new menuItem("Servi&ccedil;os de Consultoria", "consultoria", "fabrica_software_consultoria.html"));
    addMenuItem(new menuItem("-"));
    addMenuItem(new menuItem("Conhe&ccedil;a a Nossa de F&aacute;brica de Software", "fabrica_projetos", "fabrica_software_nossafabrica.html"));
    addMenuItem(new menuItem("Conhe&ccedil;a o Nossa Linha de Produ&ccedil;&atilde;o", "processo_ciclovida", "fabrica_software_nossapla.html"));
    addMenuItem(new menuItem("-"));
    addMenuItem(new menuItem("Nossos Parceiros para os Servi&ccedil;os", "pla", "fabrica_software_parceiros.html"));
    addMenuItem(new menuItem("Torne-se um Parceiro", "pla", "fabrica_software_torneseparceiro.html"));
  } 
  
  fabricaSoftwareMenu1.items.fabrica_software.setSubMenu(fabricaSoftwareMenu2);
  fabricaSoftwareMenu1.items.consultoria.setSubMenu(fabricaSoftwareMenu3);
  
  servicosMenu = new jsDOMenu(300, "absolute");
  with (servicosMenu) { 
  	addMenuItem(new menuItem("Desenvolvimento de Software", "item1", "blank.htm"));
  	addMenuItem(new menuItem("-"));   
    addMenuItem(new menuItem("Modelagem de Processos de Neg&oacute;cios", "item2", "blank.htm"));
    addMenuItem(new menuItem("Automa&ccedil;&atilde;o de Processos de Neg&oacute;cios", "item3", "blank.htm"));
    addMenuItem(new menuItem("Consultoria em BPMN e BPEL", "pla", "blank.htm")); 
    
  }
  
  treinamentosMenu1_1 = new jsDOMenu(260, "absolute");
  with (treinamentosMenu1_1) {    
   	addMenuItem(new menuItem("Sobre os Treinamentos", "sobre", "showFormacoes_.html?pageUrl=showAboutTrainings.html"));
    addMenuItem(new menuItem("Confira Nossas Forma&ccedil;&otilde;es", "formacoes", "showFormacoes_.html"));
    addMenuItem(new menuItem("Turmas Agendadas", "turmas", "showFormacoes_.html?pageUrl=showCatalogoTurmas.html"));    
  }  

  treinamentosTIMenu2 = new jsDOMenu(230, "absolute");
  with (treinamentosTIMenu2) {
    addMenuItem(new menuItem("Para o Diretor", "", "blank.htm"));
    addMenuItem(new menuItem("Para o Gerente de Projetos", "", "blank.htm"));
    addMenuItem(new menuItem("Para o Gerente de Teste", "", "blank.htm"));
    addMenuItem(new menuItem("Para o Gerente de Requisitos", "", "blank.htm"));
    addMenuItem(new menuItem("Para o Arquiteto de Software", "", "blank.htm"));
    addMenuItem(new menuItem("Para o Analista", "", "blank.htm"));
    addMenuItem(new menuItem("Para o Projetista", "", "blank.htm"));
    addMenuItem(new menuItem("Para o Programador", "", "blank.htm"));
    addMenuItem(new menuItem("Para o Testador", "", "blank.htm"));
  }
 
  

  treinamentosTecnoMenu2 = new jsDOMenu(250, "absolute");
  with (treinamentosTecnoMenu2) {
    <%
		com.adapit.portal.entidades.Technologies tecvet[] = com.adapit.portal.entidades.Technologies.values();		
	%>
    addMenuItem(new menuItem("Desenvolvimento de Software", "tecdes", "black.htm"));
    addMenuItem(new menuItem("Banco de Dados", "tecdb", "black.htm"));
    addMenuItem(new menuItem("Desenvolvimento para Web", "tecweb", "black.htm"));
    addMenuItem(new menuItem("An&aacute;lise de Sistemas", "tecan", "black.htm"));
    addMenuItem(new menuItem("Processos", "tecproc", "black.htm"));
  }
  
  treinamentosTecnoMenu3 = new jsDOMenu(350, "absolute");
  with (treinamentosTecnoMenu3) {
    <%
		for(int i=0; i < 5; i++){
		    String value = tecvet[i].name().replace("_"," ");
		%>
    	addMenuItem(new menuItem("<%=value%>", "", "trainingBy.html?tec=<%=tecvet[i].name()%>"));
    <%}%>
  }
  
  treinamentosTecnoMenu4 = new jsDOMenu(350, "absolute");
  with (treinamentosTecnoMenu4) {
    <%
		for(int i=5; i < 7; i++){
		    String value = tecvet[i].name().replace("_"," ");
		%>
    	addMenuItem(new menuItem("<%=value%>", "", "trainingBy.html?tec=<%=tecvet[i].name()%>"));
    <%}%>
  }
  
  treinamentosTecnoMenu5 = new jsDOMenu(350, "absolute");
  with (treinamentosTecnoMenu5) {
    <%
		for(int i=7; i < 10; i++){
		    String value = tecvet[i].name().replace("_"," ");
		%>
    	addMenuItem(new menuItem("<%=value%>", "", "trainingBy.html?tec=<%=tecvet[i].name()%>"));
    <%}%>
  }
  
  treinamentosTecnoMenu6 = new jsDOMenu(350, "absolute");
  with (treinamentosTecnoMenu6) {
    <%
		
		for(int i=10; i < 17; i++){
		    String value = tecvet[i].name().replace("_"," ");
		%>
    	addMenuItem(new menuItem("<%=value%>", "", "trainingBy.html?tec=<%=tecvet[i].name()%>"));
    <%}%>
  }

	treinamentosTecnoMenu7 = new jsDOMenu(350, "absolute");
  with (treinamentosTecnoMenu7) {
    <%
		
		for(int i=17; i < tecvet.length; i++){
		    String value = tecvet[i].name().replace("_"," ");
		%>
    	addMenuItem(new menuItem("<%=value%>", "", "trainingBy.html?tec=<%=tecvet[i].name()%>"));
    <%}%>
  }
  clientesMenu = new jsDOMenu(180, "absolute");
  with (clientesMenu) {
    addMenuItem(new menuItem("Dos Produtos", "produtos", "blank.htm"));
    addMenuItem(new menuItem("Dos Servi&ccedil;os", "servicos", "blank.htm"));
    addMenuItem(new menuItem("Todos os Clientes", "", "blank.htm"));
  }
  
  clientesProdutosMenu_1 = new jsDOMenu(350, "absolute");
  with (clientesProdutosMenu_1) {
    addMenuItem(new menuItem("Do Software de MDA WCT", "", "blank.htm"));
    addMenuItem(new menuItem("Do Software de MDA WCT para JAVA", "", "blank.htm"));
    addMenuItem(new menuItem("-"));
    addMenuItem(new menuItem("Do Software de Leil&atilde;o Virtual", "", "blank.htm"));
    addMenuItem(new menuItem("Do Software de Com&eacute;rcio Eletr&ocirc;nico", "", "blank.htm"));
    addMenuItem(new menuItem("-"));
    addMenuItem(new menuItem("De Todos os Softwares", "", "blank.htm"));
  }

  clientesServicosMenu_1 = new jsDOMenu(350, "absolute");
  with (clientesServicosMenu_1) {
    addMenuItem(new menuItem("Dos Servi&ccedil;os para F&aacute;brica de Software", "", "blank.htm"));
    addMenuItem(new menuItem("Dos Servi&ccedil;os para F&aacute;brica de Projetos", "", "blank.htm"));
    addMenuItem(new menuItem("Dos Servi&ccedil;os para Automatiza&ccedil;&atilde;o de Linhas de Produto", "", "blank.htm"));
    addMenuItem(new menuItem("-"));
    addMenuItem(new menuItem("Dos Servi&ccedil;os para Treinamentos", "", "blank.htm"));
    addMenuItem(new menuItem("-"));
    addMenuItem(new menuItem("Dos Servi&ccedil;os para Modelagem de Processos de Neg&oacute;cio", "", "blank.htm"));
    addMenuItem(new menuItem("-"));
    addMenuItem(new menuItem("De Todos os Servi&ccedil;os", "", "blank.htm"));
  }

  clientesMenu.items.produtos.setSubMenu(clientesProdutosMenu_1);
  clientesMenu.items.servicos.setSubMenu(clientesServicosMenu_1);
  
  institucionalMenu = new jsDOMenu(150, "absolute");
  with (institucionalMenu) {
    addMenuItem(new menuItem("Apresenta&ccedil;&atilde;o", "", "institucional.html?page_id=institucional_apresentacao_.html"));
    addMenuItem(new menuItem("Empresa", "", "institucional.html?page_id=institucional_empresa_.html"));
    addMenuItem(new menuItem("Localiza&ccedil;&atilde;o", "", "institucional.html?page_id=institucional_localizacao_.html"));
    addMenuItem(new menuItem("Diferencia&ccedil;&atilde;o", "", "institucional.html?page_id=institucional_diferenciacao_.html"));
    addMenuItem(new menuItem("Solu&ccedil;&otilde;es", "", "institucional.html?page_id=institucional_solucoes_.html"));
    addMenuItem(new menuItem("Hist&oacute;rico", "", "institucional.html?page_id=institucional_historico_.html"));
    addMenuItem(new menuItem("Tecnologias", "", "institucional.html?page_id=institucional_tecnologias_.html"));
    addMenuItem(new menuItem("Processos", "", "institucional.html?page_id=institucional_processos_.html"));
    addMenuItem(new menuItem("Contato", "", "institucional.html?page_id=institucional_contato_.html"));
  }
  
  
  
  pesquisaDesenvMenu = new jsDOMenu(200, "absolute");
  with (pesquisaDesenvMenu) {
    addMenuItem(new menuItem("Projetos da Adapit", "", "blank.htm"));
    addMenuItem(new menuItem("Inova&ccedil;&atilde;o em Tecnologias", "", "blank.htm"));
    addMenuItem(new menuItem("-"));
    addMenuItem(new menuItem("Publica&ccedil;&otilde;es", "", "blank.htm"));
    addMenuItem(new menuItem("-"));
    addMenuItem(new menuItem("Termos e Conceitos", "conceitos", "blank.htm"));
  }
  
  pesquisaDesenvMenu_1 = new jsDOMenu(350, "absolute");
  with (pesquisaDesenvMenu_1) {
    addMenuItem(new menuItem("Model Driven Architecture (MDA)", "", "blank.htm"));
    addMenuItem(new menuItem("Model Driven Develoment (MDD)", "", "blank.htm"));
    addMenuItem(new menuItem("Agile Model Driven Architecture (AMDA)", "", "blank.htm"));
    addMenuItem(new menuItem("-"));
    addMenuItem(new menuItem("Model Driven Develoment (MDD)", "", "blank.htm"));
    addMenuItem(new menuItem("Software Process Engineering Metamodel (SPEM)", "", "blank.htm"));
    addMenuItem(new menuItem("Business Process Modeling Notation (BPMN)", "", "blank.htm"));
    addMenuItem(new menuItem("-"));
    addMenuItem(new menuItem("Services Oriented Architecture (SOA)", "", "blank.htm")); 
    addMenuItem(new menuItem("Product Line Architectures (PLA)", "", "blank.htm")); 
    addMenuItem(new menuItem("Domain Engineering", "", "blank.htm")); 
  }
  pesquisaDesenvMenu.items.conceitos.setSubMenu(pesquisaDesenvMenu_1);
  


  treinamentosTecnoMenu2.items.tecdes.setSubMenu(treinamentosTecnoMenu3);
  treinamentosTecnoMenu2.items.tecdb.setSubMenu(treinamentosTecnoMenu4);
  treinamentosTecnoMenu2.items.tecweb.setSubMenu(treinamentosTecnoMenu5);
  treinamentosTecnoMenu2.items.tecan.setSubMenu(treinamentosTecnoMenu6);
  treinamentosTecnoMenu2.items.tecproc.setSubMenu(treinamentosTecnoMenu7);

  cadastroMenu = new jsDOMenu(300, "absolute");
  with (cadastroMenu) {   
 
 <% if (user != null){%>
    addMenuItem(new menuItem("Minha Conta", "", "contaUsuarioTelaInicial.html"));
    addMenuItem(new menuItem("Editar Meus Dados Pessoais", "","<%
												if (user.getTipoPessoa() == PersonType.Fisica){%>contaUsuarioPessoaFisicaCadastreForm<%
												}else{%>contaUsuarioPessoaJuridicaCadastreForm<%
												}%>.html?id_usuario=<%=
													user.getId()%>&ticket=<%=
													user.getTicket()%>"));
												
    <%}else{%>

    addMenuItem(new menuItem("Fazer o Meu Cadastro", "", "novoUsuario.html?id_usuario=0"));
    addMenuItem(new menuItem("Acessar Minha Conta", "", "novoUsuario.html?id_usuario=0&pageName=logging_.html"));
    addMenuItem(new menuItem("Esqueci Minha Senha", "", "novoUsuario.html?id_usuario=0&pageName=esqueciMinhaSenha_.html"));
    <% }%>
    
  }   

  
  absoluteMenuBar = new jsDOMenuBar("static", "staticMenuBar");
  with (absoluteMenuBar) {
    addMenuBarItem(new menuBarItem("Home", homeMenu));
    addMenuBarItem(new menuBarItem("Treinamentos", treinamentosMenu1_1));	
    addMenuBarItem(new menuBarItem("Solu&ccedil;&otilde;es em Software", allProdutosMenu));
    addMenuBarItem(new menuBarItem("Institucional", institucionalMenu));
    //addMenuBarItem(new menuBarItem("Conta de Usu&aacute;rio",cadastroMenu));
 addMenuBarItem(new menuBarItem("Eventos e Artigos", materiasMenu));
    
  }
}
</script>


