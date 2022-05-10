<% java.util.Random rd = new java.util.Random();
	int i=0;
	int taxe=1000;
		try {
			i = rd.nextInt(taxe);
		} catch (Exception e) {
			e.printStackTrace();
		} %>
		<% String email = (String) request.getAttribute("email"); %>
		<% String mensagem = (String) request.getAttribute("mensagem"); %>
		<% String nome = (String) request.getAttribute("nome"); %>
		<% String assunto = (String) request.getAttribute("assunto"); %>
<div id="contatoEmailDiv">	
    <form class="default_form" id="formContatoemail<%=i%>" name="formContatoemail<%=i%>" method="post">
    <table width="100%" border="0" cellpadding="4" cellspacing="4">
      <tbody><tr>
        <td width="34%"><label>Nome</label></td>

        <td width="66%"><input name="nome" id="nome" size="50" maxlength="100" type="text"
        <%if(nome != null) {%>value="<%=nome %>"<%} %>
        ></td>
        

      </tr>
      <tr>
        <td width="34%"><label>E-mail</label></td>
        <td width="66%"><input name="email" id="email" size="50" maxlength="100" type="text"
        <%if(email != null) {%>value="<%=email %>"<%} %>></td>
      </tr>
      <tr>
        <td width="34%"><label>Assunto</label></td>
        <td width="66%"><input  name="assunto" id="assunto" size="25" maxlength="50" type="text"
        <%if(assunto != null) {%>value="<%=assunto %>"<%} %>></td>

      </tr>
	<tr>
        <td width="34%"><label>O que est&aacute; escrito?</label></td>
        <td width="66%"><img src="imgs/confirm/<%= request.getSession().getAttribute("imageid")%>"/></td>
      </tr>
      <tr>
        <td><a href="javascript:alert('Esta informa&ccedil;&atilde;o &eacute; necess&aacute;ria para evitar programas mal intencionados (SPAM).')"><span style="font-size: 9px;"> (o que &eacute; isso?)</span> </a></td>
        <td><input name="word" id="word" size="25" maxlength="20" type="text"> 
         </td>
      </tr>
      <tr>

        <td><label>Mensagem</label></td>
        <td><textarea name="mensagem" cols="46" rows="5" id="mensagem"
        ><%if(mensagem != null) {%><%=mensagem %><%} %></textarea></td>
      </tr>
      

    </tbody></table>
    <br><center><button  
				onclick="jquerydoPost('formContatoemail<%=i%>','processContatoSendEmail.html','contatoEmailDiv'); return false;"
				 name="enviarEmail">Enviar</button>
        
      </center>
    </form>

</div>
