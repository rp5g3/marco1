<%@page import="com.adapit.portal.entidades.*"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="validation" uri="http://www.springmodules.org/tags/commons-validator"%>
<%@ taglib prefix="binder" uri="http://www.springframework.org/tags"%>
<%@page import="com.adapit.portal.entidades.FormacaoTreinamento"%>
<%@page import="com.workcase.utils.IdGenerator"%>
     <LINK rel="stylesheet" type="text/css" href="css/default.css"/>
     <LINK rel="stylesheet" type="text/css" href="css/jt_DialogBox.css"/>
     <LINK rel="stylesheet" type="text/css" href="css/leilao.css"/>  
	 <script type="text/javascript" src="js/leilao.js"></script> 
	 
     <STYLE TYPE="text/css">
				<!--
					@page { margin-right: 3cm; margin-top: 2.5cm; margin-bottom: 2.5cm }
					P { margin-bottom: 0.21cm; direction: ltr; color: #000000; widows: 2; orphans: 2 }
					P.western { font-family: "Arial", serif; font-size: 10pt }
					P.cjk { font-family: "Arial", serif; font-size: 10pt }
					P.ctl { font-family: "Arial", serif; font-size: 10pt; so-language: ar-SA }
					H1 { margin-bottom: 0.11cm; direction: ltr; color: #000000; widows: 2; orphans: 2 }
					H1.western { font-family: "Arial", sans-serif; font-size: 10pt }
					H1.cjk { font-family: "Arial", serif; font-size: 10pt }
					H1.ctl { font-family: "Arial", sans-serif; font-size: 10pt; so-language: ar-SA }
					p.MsoNormal, li.MsoNormal, div.MsoNormal
					{margin:0cm;
					margin-bottom:.0001pt;
					font-size:10.0pt;
					font-family:Arial;}
				-->
				</STYLE>
        <%
        	String sobrerandom = IdGenerator.getInstance().generateId(3);
      		
        %>
      
     
   		<script type="text/javascript" src="js/jt_DialogBox.js"></script>
   		<script type="text/javascript" src="js/MyApp_dialogs.js"></script>
      	<script type="text/javascript">
	// increase the default animation speed to exaggerate the effect
	$.fx.speeds._default = 1000;
	$(function() {
		$('#trainVirtualDialog<%=sobrerandom%>').dialog({
			autoOpen: false,
			show: 'blind',
			hide: 'explode',
			minWidth: 700,
			minHeight: 550
		});
		
		$('#openTrainVirtualDialog<%=sobrerandom%>').click(function() {
			$('#trainVirtualDialog<%=sobrerandom%>').dialog('open');
			return false;
		});

		$('#trainCompanyDialog<%=sobrerandom%>').dialog({
			autoOpen: false,
			show: 'blind',
			hide: 'explode',
			minWidth: 700,
			minHeight: 550
		});
		
		$('#openTrainCompanyDialog<%=sobrerandom%>').click(function() {
			$('#trainCompanyDialog<%=sobrerandom%>').dialog('open');
			return false;
		});

		$('#trainPresencialDialog<%=sobrerandom%>').dialog({
			autoOpen: false,
			show: 'blind',
			hide: 'explode',
			minWidth: 700,
			minHeight: 550
		});
		
		$('#openTrainPresencialDialog<%=sobrerandom%>').click(function() {
			$('#trainPresencialDialog<%=sobrerandom%>').dialog('open');
			return false;
		});
	});
	</script>
    
   
	<div style="position=relative; width=100%; padding:5px;">

		<p><center><a href="files/Apresentacao_Treinamento_Adapit.pdf"			
			target="blanck" style="cursor: hand; cursor: pointer;">
			Confira a Nossa Carta de Apresenta&ccedil;&atilde;o</a>
			</center>
		</p>
			<P class=MsoNormal style='text-align:justify;text-indent:15pt'>A
			Adapit oferece cursos <I>in	Company</I>
			(nas instala&ccedil;&otilde;es do cliente) ou &agrave; dist&acirc;ncia
			para capacita&ccedil;&atilde;o profissional em duas principais &aacute;reas,
			Gerenciamento de Processos de Neg&oacute;cio (<I>BPM</I>)
			e Tecnologias <I>Java</I>.		
			</P>
			<P class=MsoNormal style='text-align:justify;text-indent:15pt'>A
			empresa conta com cursos para a forma&ccedil;&atilde;o completa de
			diferentes perfis profissionais, que s&atilde;o ministrados por
			instrutores altamente qualificados e com atua&ccedil;&atilde;o
			profissional no segmento. No entanto, o grande diferencial da Adapit
			&eacute; a <B>flexibilidade</B>	e <B>personaliza&ccedil;&atilde;o</B> de
			seus treinamentos. O material did&aacute;tico e conte&uacute;do
			program&aacute;tico dos cursos &eacute; desenvolvido para atender as
			necessidades espec&iacute;ficas de cada empresa cliente. Al&eacute;m
			disso, a pr&oacute;pria empresa cliente pode determinar os cursos que
			ser&atilde;o inclu&iacute;dos na forma&ccedil;&atilde;o desejada. Ou
			seja, um representante da Adapit entrar&aacute; em contato,
			previamente, com a empresa cliente para identificar suas
			necessidades. Estas informa&ccedil;&otilde;es ser&atilde;o utilizadas
			pela equipe de profissionais da Adapit para montar um plano de
			capacita&ccedil;&atilde;o t&eacute;cnica personalizado, adaptando os
			cursos oferecidos (ou criando novos cursos), de forma que estes
			atendam plenamente as necessidades do cliente.
			</P>
			<P class=MsoNormal style='text-align:justify;text-indent:15pt'>Entretanto,
			al&eacute;m da flexibilidade e personaliza&ccedil;&atilde;o, a Adapit
			tamb&eacute;m oferece a <B>melhor
			rela&ccedil;&atilde;o custo x benef&iacute;cio</B>.
			O custo de cada treinamento fica abaixo da m&eacute;dia de mercado.  
			</P>
			<br><center>
							<button id="openTrainVirtualDialog<%=sobrerandom%>"
							 type="button"  
							 height="26px" width="80px" >
							Virtual</button>
							&nbsp;
							<button id="openTrainCompanyDialog<%=sobrerandom%>"
							 type="button"  
							 height="26px" width="80px" >
							In Company</button>
							&nbsp;
							<button id="openTrainPresencialDialog<%=sobrerandom%>"
							 type="button"  
							 height="26px" width="80px" >
							Presencial</button>
							
							<br>	

		<p><center><a href="files/Apresentacao_Treinamento_Adapit.pdf"			
			target="blanck" style="cursor: hand; cursor: pointer;">
			Confira a Nossa Carta de Apresenta&ccedil;&atilde;o</a>
			</center>
		</p>
		<br>						
	</div>					
	
	<div id="trainVirtualDialog<%=sobrerandom%>" title="Treinamento Virtual" style="width: 700px; height: 500px; top:50px;  overflow: inherit;" >
		<div style="position=relative; width=100%; padding:5px; overflow: auto; width: 670px; height: 450px;">	
		<%try{ %>					
			<%@include file="treinamento_virtual.html" %>								
		<%}catch(Exception ex){ex.printStackTrace();} %>
		</div>
	</div>		   		
		
	
	<div id="trainCompanyDialog<%=sobrerandom%>" title="Treinamento In Company" style="width: 700px; height: 500px; top:50px;  overflow: inherit;" >
		<div style="position=relative; width=100%; padding:5px; overflow: auto; width: 670px; height: 450px;">	
		<%try{ %>					
			<%@include file="treinamento_in_company.html" %>								
		<%}catch(Exception ex){ex.printStackTrace();} %>
		</div>
	</div>
	
	<div id="trainPresencialDialog<%=sobrerandom%>" title="Treinamento Presencial" style="width: 700px; height: 500px; top:50px;  overflow: inherit;" >
		<div style="position=relative; width=100%; padding:5px; overflow: auto; width: 670px; height: 450px;">	
		<%try{ %>					
			<%@include file="treinamento_presencial.html" %>							
		<%}catch(Exception ex){ex.printStackTrace();} %>
		</div>
	</div>	
	
