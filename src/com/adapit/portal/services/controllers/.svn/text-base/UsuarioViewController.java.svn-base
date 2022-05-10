package com.adapit.portal.services.controllers;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.adapit.portal.dto.LoteReportDTO;
import com.adapit.portal.dto.UsuarioDTO;
import com.adapit.portal.entidades.DeactivationReason;
import com.adapit.portal.entidades.Endereco;
import com.adapit.portal.entidades.Fisica;
import com.adapit.portal.entidades.Juridica;
import com.adapit.portal.entidades.Participante;
import com.adapit.portal.entidades.PreferenciaCategoria;
import com.adapit.portal.entidades.Usuario;
import com.adapit.portal.services.ParticipanteService;
import com.adapit.portal.services.local.LocalPessoaService;
import com.adapit.portal.services.local.LocalServicesUtility;
import com.adapit.portal.services.mail.NovaSenhaMailServiceImpl;
import com.adapit.portal.services.validation.PessoaServiceValidator;
import com.adapit.portal.services.validation.UserServiceValidator;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.WebResourceMessage;

@SuppressWarnings({"unchecked","unused"})
public class UsuarioViewController extends MultiActionController implements InitializingBean {

	private ResourceMessage messages;
	
	private UserServiceValidator userService;
	
	private PessoaServiceValidator pessoaService;
	
	/**
	* @spring.property ref="pessoaService" singleton="true"
	*/
	public PessoaServiceValidator getPessoaService() {
		return pessoaService;
	}

	public void setPessoaService(PessoaServiceValidator pessoaService) {
		this.pessoaService = pessoaService;
	}

	private NovaSenhaMailServiceImpl novaSenhaMailService;
	
	/**
	* @spring.property ref="userService" singleton="true"
	*/
	public UserServiceValidator getUserService() {
		return userService;
	}

	public void setUserService(UserServiceValidator userService) {
		this.userService = userService;
	}
	
	/**
	* @spring.property ref="novaSenhaMailService" singleton="true"
	*/
	public NovaSenhaMailServiceImpl getNovaSenhaMailService() {
		return novaSenhaMailService;
	}

	public void setNovaSenhaMailService(NovaSenhaMailServiceImpl pwdService) {
		this.novaSenhaMailService = pwdService;
	}

	public UsuarioViewController(){
		messages = WebResourceMessage.getInstance();
	}

	/**
	 *  Método usado para mostrar a jsp cadastroPessoa em que o usuário seleciona o tipo de pessoa
	 * 	É necessário para poder acessar os JSPs contidos dentro do WEB-INF
	 */
	public ModelAndView filterFormView(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("cadastroPessoa");
	}
	
	/**
	 *  Método usado para abrir a jsp formularioCadastroPessoaFisica
	 * 	É necessário para poder acessar os JSPs contidos dentro do WEB-INF
	 */
	public ModelAndView showFormularioCadastroPessoaFisicaView(HttpServletRequest request,
            HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".showFormularioCadastroPessoaFisica");
//		try {
//			request.setCharacterEncoding("UTF-8");
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		}
		Usuario u = new Usuario();
		Endereco e = new Endereco();
		Fisica f = new Fisica();
		Participante p = new Participante();
		PreferenciaCategoria pref = new PreferenciaCategoria();
		u.setDadosPessoais(p);
		u.setPreferencia(pref);
		p.setEndereco(e);
		p.setTipoPessoa(f);
		
		request.setAttribute("usuario",u);
		request.setAttribute("endereco",e);
		request.setAttribute("fisica",f);
		request.setAttribute("participante",p);
		request.setAttribute("preferencia",pref);
		request.setAttribute("errorFields",new Hashtable());
		return new ModelAndView("formularioCadastroPessoaFisica");
	}
	
	public ModelAndView showLoginUsuarioView(HttpServletRequest request,  HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".showLoginUsuarioView");
		//Usuario u = new Usuario();
		/*Cookie[] cookies = request.getCookies();
		for (int i=0; i < cookies.length; i++){
			Cookie c = cookies[i];
			c.
		}*/
		//request.setAttribute("user",u);
		String login = request.getParameter("loginTextField");
		String password = request.getParameter("passwordTextField");
		String init=request.getParameter("init");
		//System.out.println(init);
		//System.out.println("Login usuario");
		if(init == null || init.equals("")){
			
			password = Usuario.encript(password);
			boolean b = userService.isValid(login, password);
			if(b)try {
				
				//System.out.println("User ok");
				UsuarioDTO u = userService.loggingUser(login, password);
				request.getSession(true).setAttribute("user", u);
				request.setAttribute("msg", "sucess");
				request.getSession().removeAttribute("account");
				request.setAttribute("pageStr", "welcomeUser");
				return new ModelAndView("welcome");
			} catch (Exception e) {
				e.printStackTrace();
			} else{
				//System.out.println("User not ok");				
				request.setAttribute("message", "error");
				if(request.getSession().getAttribute("account")==null)
					request.getSession().setAttribute("account", "login");
				return new ModelAndView("welcome");
			}
		}else{
			
			//System.out.println("tem init");
			if(init.equalsIgnoreCase("false")){
				request.getSession(true).removeAttribute("user");
				request.getSession().removeAttribute("account");
				return new ModelAndView("welcome");
			}else{
				//request.getSession(true).removeAttribute("user");
				//request.getSession().removeAttribute("account");
			}
			
		}
		
		return new ModelAndView("login/login");
	}
	
	@SuppressWarnings("null")
	public ModelAndView changeCategoriaView(HttpServletRequest request,  HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".changeCategoriaView");
//		try {
//			request.setCharacterEncoding("UTF-8");
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		}
		String categoria = request.getParameter("categoria");
		String userid = request.getParameter("user_id");
		if (userid != null && categoria != null){
			try {
				//Usuario u = userService.getUserById(Integer.parseInt(userid));
				Session s =null;
				try {
					Usuario u= (Usuario) s.load(Usuario.class, userid);
					if (categoria.equalsIgnoreCase("portifolio")){
						u.getPreferencia().setReceberNotificacaoNewsByEmail(!u.getPreferencia().isReceberNotificacaoNewsByEmail());
					}else if (categoria.equalsIgnoreCase("veiculos")){
						u.getPreferencia().setInteresseEmConsultoria(!u.getPreferencia().isInteresseEmConsultoria());
					}else if (categoria.equalsIgnoreCase("maquinarios")){
						u.getPreferencia().setInteresseEmTreinamentos(!u.getPreferencia().isInteresseEmTreinamentos());
					}else if (categoria.equalsIgnoreCase("informatica")){
						u.getPreferencia().setReceberEmailAtualizacoesSoftware(!u.getPreferencia().isReceberEmailAtualizacoesSoftware());
					}else if (categoria.equalsIgnoreCase("imoveis")){
						u.getPreferencia().setReceberEmailNovosProdutos(!u.getPreferencia().isReceberEmailNovosProdutos());
					}
				}catch(Exception ex){
					
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	private final String wrongUserUrlOld="loginUsuarioFormTable";
	private final String wrongUserUrl="welcomeLoging";
	private final String sucessUserUrl="welcomeUser";
	
	public ModelAndView logging(HttpServletRequest request,  HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".logging");
//		try {
//			request.setCharacterEncoding("UTF-8");
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		}
		Usuario u = new Usuario();
		
		String login = request.getParameter("login");
		String pass = request.getParameter("password");
		if (login == null || login.equals("") || pass == null || pass.equals("")){
			request.setAttribute("error", "Login e/ou Senha informados estão inválidos!");
			
			return new ModelAndView(wrongUserUrl);
		}
		u.setLogin(login);
		u.setPassword(Usuario.encript(pass));
		
		boolean b = userService.isValid(login);
		//Se não existir usuário com login informado
		if (!b){
			
			ArrayList<String> arr = new ArrayList<String>();
			arr.add(messages.getMessage("errors.invalidlogin",new Object[][]{{"form.access"}}));
			request.setAttribute("user",u);
			request.setAttribute("errorMessages",arr);
			request.setAttribute("mustCadastre",new Boolean(true));
			request.setAttribute("error", "O Login informado não existe!");
			return new ModelAndView(wrongUserUrl);
		}//se não existir usuário com login e senha
		else if (!userService.isValid(u.getLogin(),u.getPassword())){	
			
			ArrayList<String> arr = new ArrayList<String>();
			arr.add(messages.getMessage("errors.invalidpassword",new Object[][]{{"form.access"}}));
			request.setAttribute("user",u);
			request.setAttribute("errorMessages",arr);
			request.setAttribute("error", "Dados de usuário inválidos!");
			return new ModelAndView(wrongUserUrl);
		}
		
		try {
			UsuarioDTO udto = userService.loggingUser(u.getLogin(),u.getPassword()); 
			
			if (udto.isActive() && udto.isAuthenticated()){
				request.setAttribute("myLoteList",udto.getLoteReports());
				udto.setLoteReports(null);
				if (udto.getLastAcess() != null){					
					request.setAttribute("lastAccess",udto.getLastAcess());
				}
				HttpSession hs = request.getSession(true);
				if (hs.getAttribute("user") == null) hs.setAttribute("user", udto);
			}else{
				if (udto.isActive()){
					ArrayList<String> arr = new ArrayList<String>();
					arr.add(messages.getMessage("errors.naoautenticado",new Object[][]{{"form.access"}}));
					request.setAttribute("user",u);
					request.setAttribute("errorMessages",arr);
					request.setAttribute("mustCadastre",new Boolean(false));
					
					request.setAttribute("error", "Este usuário não está autenticado! Autentique-se pelo seu email!");
					return new ModelAndView(wrongUserUrl);
				}else{
					ArrayList<String> arr = new ArrayList<String>();
					arr.add(messages.getMessage("errors.inativo",new Object[][]{{"form.access"}}));
					request.setAttribute("user",u);
					request.setAttribute("errorMessages",arr);
					request.setAttribute("mustCadastre",new Boolean(false));
					request.setAttribute("error", "Este usuário foi desativado do sistema! Entre em contato com nosso suporte para mais informações");
					
					return new ModelAndView(wrongUserUrl);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			
			request.setAttribute("error", "Problemas para logar o seu usuário!");
			return new ModelAndView(wrongUserUrl);
		}
		
		return new ModelAndView(sucessUserUrl);
	}
	
	public ModelAndView logging_(HttpServletRequest request,  HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".logging_");
		return new ModelAndView("login/loginMenuForm");		
	}
	
	public ModelAndView esqueciMinhaSenha_(HttpServletRequest request,  HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".esqueciMinhaSenha_");
		return new ModelAndView("login/esqueciSenhaForm");		
	}
	
	public ModelAndView esqueciMinhaSenha(HttpServletRequest request,  HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".esqueciMinhaSenha");
		return new ModelAndView("login/telaEsqueciMinhaSenha");		
	}
	
	public ModelAndView comoAutenticarConta(HttpServletRequest request,  HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".comoAutenticarConta");
		return new ModelAndView("login/comoAutenticarForm");		
	}
	
	public ModelAndView loggingTermosUso(HttpServletRequest request,  HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".loggingTermosUso");
		return new ModelAndView("telaTermosUso");		
	}
	
	public ModelAndView loggingTermosUso_(HttpServletRequest request,  HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".loggingTermosUso_");
		return new ModelAndView("welcome/termosuso_");		
	}
	
	public ModelAndView showPessoaFisicaCadastreFormView(HttpServletRequest request,
            HttpServletResponse response){
//		try {
//			request.setCharacterEncoding("UTF-8");
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		}
		String id = request.getParameter("id_usuario");
		String ticket = request.getParameter("ticket");
		
		Endereco e = null;
		Fisica f = null;
		Participante p = null;
		Usuario usuario=null;
		PreferenciaCategoria pref = null;
		
		if (id != null && !id.equals("") && !id.equals("0")){
			Session s =null;
			try {
				System.out.println(id);
				s= LocalServicesUtility.getInstance().openSession();
				usuario = (Usuario) s.load(Usuario.class,id);
				usuario.setNewUser(false);
				if (ticket != null && usuario.getTicket() != null && usuario.getTicket().equals(ticket)){
					usuario.getDadosPessoais().getId();
					usuario.setPasswordConf(usuario.getPassword());
					pref = usuario.getPreferencia();
					if (pref == null) pref = new PreferenciaCategoria();
					p = (Participante) usuario.getDadosPessoais();
					f = (Fisica) s.createQuery("from Fisica f where f.pessoa.id="+p.getId()).uniqueResult();
					p.setTipoPessoa(f);	
					p.getEndereco().getId();
					e=p.getEndereco();
				} else{
					return new ModelAndView("areaRestrita.html");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				return new ModelAndView("errorPage");
			}finally{
				if (s != null) s.close();
			}		
		}else{
			usuario = new Usuario();
			f = new Fisica();
			e = new Endereco();
			p = new Participante();
			pref = new PreferenciaCategoria();
			request.setAttribute("errorFields",new Hashtable());
		}
		request.setAttribute("usuario",usuario);
		request.setAttribute("endereco",e);
		request.setAttribute("fisica",f);
		request.setAttribute("participante",p);
		request.setAttribute("preferencia",pref);
		
		return new ModelAndView("pessoafisica/telaCadastroPessoaFisica");
	}
	
	public ModelAndView showPessoaFisicaEstCadastreFormView(HttpServletRequest request,
            HttpServletResponse response){
//		try {
//			request.setCharacterEncoding("UTF-8");
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		}
		String id = request.getParameter("id_usuario");
		String ticket = request.getParameter("ticket");
		
		Endereco e = null;
		Fisica f = null;
		Participante p = null;
		Usuario usuario=null;
		PreferenciaCategoria pref = null;
		
		if (id != null && !id.equals("") && !id.equals("0")){
			Session s =null;
			try {
				System.out.println(id);
				s= LocalServicesUtility.getInstance().openSession();
				usuario = (Usuario) s.load(Usuario.class,id);
				if (ticket != null && usuario.getTicket() != null && usuario.getTicket().equals(ticket)){
					usuario.getDadosPessoais().getId();
					usuario.setPasswordConf(usuario.getPassword());
					pref = usuario.getPreferencia();
					if (pref == null) pref = new PreferenciaCategoria();
					p = (Participante) usuario.getDadosPessoais();
					f = (Fisica) s.createQuery("from Fisica f where f.pessoa.id="+p.getId()).uniqueResult();
					p.setTipoPessoa(f);	
					p.getEndereco().getId();
					e=p.getEndereco();
				} else{
					return new ModelAndView("areaRestrita.html");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				return new ModelAndView("errorPage");
			}finally{
				if (s != null) s.close();
			}		
		}else{
			usuario = new Usuario();
			f = new Fisica();
			e = new Endereco();
			p = new Participante();
			pref = new PreferenciaCategoria();
			request.setAttribute("errorFields",new Hashtable());
		}
		request.setAttribute("usuario",usuario);
		request.setAttribute("endereco",e);
		request.setAttribute("fisica",f);
		request.setAttribute("participante",p);
		request.setAttribute("preferencia",pref);
		
		return new ModelAndView("pessoafisica/telaCadastroPessoaFisica");
	}
	
	public ModelAndView showPessoaJuridicaRepCadastreFormView(HttpServletRequest request,
            HttpServletResponse response){
//		try {
//			request.setCharacterEncoding("UTF-8");
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		}
		String id = request.getParameter("id_usuario");
		String ticket = request.getParameter("ticket");
		
		Endereco e = null;
		Fisica f = null;
		Participante p = null;
		Usuario usuario=null;
		PreferenciaCategoria pref = null;
		
		if (id != null && !id.equals("") && !id.equals("0")){
			Session s =null;
			try {
				System.out.println(id);
				s= LocalServicesUtility.getInstance().openSession();
				usuario = (Usuario) s.load(Usuario.class,id);
				if (ticket != null && usuario.getTicket() != null && usuario.getTicket().equals(ticket)){
					usuario.getDadosPessoais().getId();
					usuario.setPasswordConf(usuario.getPassword());
					pref = usuario.getPreferencia();
					if (pref == null) pref = new PreferenciaCategoria();
					p = (Participante) usuario.getDadosPessoais();
					f = (Fisica) s.createQuery("from Fisica f where f.pessoa.id="+p.getId()).uniqueResult();
					p.setTipoPessoa(f);	
					p.getEndereco().getId();
					e=p.getEndereco();
				} else{
					return new ModelAndView("areaRestrita.html");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				return new ModelAndView("errorPage");
			}finally{
				if (s != null) s.close();
			}		
		}else{
			usuario = new Usuario();
			f = new Fisica();
			e = new Endereco();
			p = new Participante();
			pref = new PreferenciaCategoria();
			request.setAttribute("errorFields",new Hashtable());
		}
		request.setAttribute("usuario",usuario);
		request.setAttribute("endereco",e);
		request.setAttribute("fisica",f);
		request.setAttribute("participante",p);
		request.setAttribute("preferencia",pref);
		
		return new ModelAndView("pessoajuridica/telaCadastroPessoaFisica");
	}

	public ModelAndView showPessoaJuridicaCadastreFormView(HttpServletRequest request,
            HttpServletResponse response){
//		try {
//			request.setCharacterEncoding("UTF-8");
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		}
		String id = request.getParameter("id_usuario");
		String ticket = request.getParameter("ticket");
		
		Endereco e = null;
		Juridica j = null;
		Participante p = null;
		Usuario usuario=null;
		PreferenciaCategoria pref = null;
		
		if (id != null && !id.equals("") && !id.equals("0")){
			Session s =null;
			try {
				System.out.println(id);
				s= LocalServicesUtility.getInstance().openSession();
				usuario = (Usuario) s.load(Usuario.class,id);
				if (ticket != null && usuario.getTicket() != null && usuario.getTicket().equals(ticket)){
					usuario.getDadosPessoais().getId();
					usuario.setPasswordConf(usuario.getPassword());
					pref = usuario.getPreferencia();
					if (pref == null) pref = new PreferenciaCategoria();
					p = (Participante) usuario.getDadosPessoais();
					j = (Juridica) LocalPessoaService.getInstance().getPessoaJuridicaByIdPessoa(p.getId());
					p.setTipoPessoa(j);	
					p.getEndereco().getId();
					e=p.getEndereco();
				} else{
					return new ModelAndView("areaRestrita.html");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				return new ModelAndView("errorPage");
			}finally{
				if (s != null) s.close();
			}		
		}else{
			usuario = new Usuario();
			j = new Juridica();
			e = new Endereco();
			p = new Participante();
			pref = new PreferenciaCategoria();
			request.setAttribute("errorFields",new Hashtable());
		}
		request.setAttribute("usuario",usuario);
		request.setAttribute("endereco",e);
		request.setAttribute("juridica",j);
		request.setAttribute("participante",p);
		request.setAttribute("preferencia",pref);
		
		return new ModelAndView("pessoajuridica/telaCadastroPessoaJuridica");
	}
	
	public ModelAndView showPessoaCadastreFormView(HttpServletRequest request,
            HttpServletResponse response){
//		try {
//			request.setCharacterEncoding("UTF-8");
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		}
		String id = request.getParameter("id_usuario");
		String page = request.getParameter("pageName");
		String ticket = request.getParameter("ticket");
		
		if (page != null && page.length()>3){
			System.out.println(page);
			request.setAttribute("pageUrl", page);
			return new ModelAndView("telaCadastroPessoa");
		}
		
		Endereco e = null;
		Fisica f = null;
		Juridica j = null;
		Participante p = null;
		Usuario usuario=null;
		PreferenciaCategoria pref = null;
		
		if (id != null && !id.equals("") && !id.equals("0")){
			Session s =null;
			try {
				System.out.println(id);
				s= LocalServicesUtility.getInstance().openSession();
				usuario = (Usuario) s.load(Usuario.class,id);
				if (ticket != null && usuario.getTicket() != null && usuario.getTicket().equals(ticket)){
					usuario.getDadosPessoais().getId();
					usuario.setPasswordConf(usuario.getPassword());
					pref = usuario.getPreferencia();
					if (pref == null) pref = new PreferenciaCategoria();
					p = (Participante) usuario.getDadosPessoais();
					f = (Fisica) LocalPessoaService.getInstance().getPessoaFisicaByIdPessoa(p.getId());//s.createQuery("from Fisica f where f.pessoa.id="+p.getId()).uniqueResult();
					if (f != null)
						p.setTipoPessoa(f);
					else{
						j = (Juridica) LocalPessoaService.getInstance().getPessoaJuridicaByIdPessoa(p.getId());
						p.setTipoPessoa(j);
					}
					p.getEndereco().getId();
					e=p.getEndereco();
				} else{
					return new ModelAndView("areaRestrita.html");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				return new ModelAndView("errorPage");
			}finally{
				if (s != null) s.close();
			}		
		}else{
			usuario = new Usuario();
			f = new Fisica();
			j = new Juridica();
			e = new Endereco();
			p = new Participante();
			pref = new PreferenciaCategoria();
			request.setAttribute("errorFields",new Hashtable());
		}
		request.setAttribute("usuario",usuario);
		request.setAttribute("endereco",e);
		request.setAttribute("fisica",f);
		request.setAttribute("juridica",j);
		request.setAttribute("participante",p);
		request.setAttribute("preferencia",pref);
		
		return new ModelAndView("telaCadastroPessoa");
	}
	
	public ModelAndView showFisicaCadastreFormView(HttpServletRequest request,
            HttpServletResponse response){
		request.setAttribute("fisica",new Fisica());
		return new ModelAndView("fisicaCadastreForm");
	}
	
	public ModelAndView showOutrasInformacoesView(HttpServletRequest request,
            HttpServletResponse response){
		//request.setAttribute("preferencia",new PreferenciaCategoria());
		return new ModelAndView("outrasInformacoes");
	}
	
	public ModelAndView showEnderecoCadastreFormView(HttpServletRequest request,
            HttpServletResponse response){
		request.setAttribute("endereco",new Endereco());
		request.setAttribute("pessoa",new Participante());
		return new ModelAndView("enderecoCadastreForm");
	}
	
	public ModelAndView showUserCadastreFormView(HttpServletRequest request,
            HttpServletResponse response){
		request.setAttribute("usuario",new Usuario());
		return new ModelAndView("userCadastreForm");
	}
	
	/**
	 *  Método usado para abrir a jsp formularioCadastroPessoaJuridica
	 * 	É necessário para poder acessar os JSPs contidos dentro do WEB-INF
	 */
	public ModelAndView showFormularioCadastroPessoaJuridicaView(HttpServletRequest request,
            HttpServletResponse response){
		return new ModelAndView("formularioCadastroPessoaJuridica");
	}
	
	
	public ModelAndView trocarSenha(HttpServletRequest request,
            HttpServletResponse response){
//		try {
//			request.setCharacterEncoding("UTF-8");
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		}
		String email = request.getParameter("email");
		//System.out.println("Modificando senha para " + email);
		try {
			boolean b = novaSenhaMailService.changePasswordByEmail(email);
			request.setAttribute("result", new Boolean(b));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ModelAndView("login/esqueciSenhaForm");
	}
	
	public ModelAndView trocarSenhaUsuario(HttpServletRequest request,
            HttpServletResponse response){
//		try {
//			request.setCharacterEncoding("UTF-8");
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		}
		String login = request.getParameter("login");
		String senhaAtual = request.getParameter("senhaAtual");
		String novaSenha = request.getParameter("novaSenha");
		String senhaConf = request.getParameter("senhaConf");
		
		System.out.println("Login: " + login + "   Senha Atual: " + senhaAtual
				+"   Nova Senha:"+novaSenha + "   Conha conf:"+senhaConf);
		
		if (login != null && senhaAtual != null)try {
			if (senhaAtual == null || senhaAtual.length() < 6
					|| login == null){
				request.setAttribute("error", "Dados de login e senha estão incorretos!");
			}else{
				Boolean b = userService.isValid(login, Usuario.encript(senhaAtual));			
				if(b){
					if (novaSenha == null || novaSenha.length() < 6){
						request.setAttribute("error", "A nova senha não pode ser menor do que seis caracteres!");
						b = new Boolean(false);
					}else if (senhaConf == null || senhaConf.length() < 6){
						request.setAttribute("error", "A confirmação da senha não confere!");
						b = new Boolean(false);
					}else if (!novaSenha.equals(senhaConf)){
						request.setAttribute("error", "A nova senha e a confirmação da senha não conferem!");
						b = new Boolean(false);
					}else{
						Usuario user = new Usuario();
						user.setLogin(login);
						user.setPassword(Usuario.encript(novaSenha));
						user.setPasswordConf(Usuario.encript(novaSenha));
						user.setAutenticado(true);
						user.setActive(true);
						userService.updateUsuario(user);
						request.getSession().removeAttribute("user");
						b = new Boolean(true);
					}
				}else{
					request.setAttribute("error", "Login e senha informados não conferem!");
				}
				request.setAttribute("result", new Boolean(b));
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result", new Boolean(false));
		}
		
		return new ModelAndView("login/trocarSenhaForm");
	}

	public void afterPropertiesSet() throws Exception {
		//if (person==null || personType==null) throw new Exception("exceptions.local.BussinessNull");
	}
	
	public ModelAndView cadastrarPessoaFisica(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return new ModelAndView("personWelcome");
	}
	
	private void errorsValidate(BindException errors, ArrayList arr){
		if (errors.hasErrors()) {
			Iterator it = errors.getAllErrors().iterator();
			while (it.hasNext()){
				org.springframework.validation.FieldError errorObj = (org.springframework.validation.FieldError) it.next();
				arr.add(
						messages.getMessage(
								errorObj.getDefaultMessage(),
								new Object[][]{{errorObj.getObjectName()+"."+errorObj.getField()}}));
			}
		}
	}
	
	String loteOption="";
	
	public ModelAndView showLotesParticipacaoView(HttpServletRequest request,  HttpServletResponse response){
//		try {
//			request.setCharacterEncoding("UTF-8");
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		}
		UsuarioDTO user = (UsuarioDTO) request.getSession(true).getAttribute("user");
				
		if (user!=null){
			try {	
				String userid = user.getId() +"";
				List<LoteReportDTO> myLoteList = userService.getLotesParticipacao(userid);				
				request.setAttribute("myLoteList",myLoteList);				
			} catch (Exception e) {
				e.printStackTrace();	
				loteOption = wrongUserUrl;
				return new ModelAndView(wrongUserUrl);
			}
			System.out.println("directing to lote/telaLanceParticipacao");
			loteOption = "lote/telaLanceParticipacao";
			return new ModelAndView("lote/telaLanceParticipacao");
		}
		loteOption = "telaLoginUsuario";
		return new ModelAndView("telaLoginUsuario");
	}

	public ModelAndView showTodosLotesEmLeilaoView(HttpServletRequest request,  HttpServletResponse response){
		System.out.println("Apresentação de todos os lotes");
//		try {
//			request.setCharacterEncoding("UTF-8");
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		}
		UsuarioDTO user = (UsuarioDTO) request.getSession(true).getAttribute("user");
				
		
		try {	
			String userid = user.getId() +"";
				
			List<LoteReportDTO> myLoteList = userService.getAllLotesParticipacao(userid);				
			request.setAttribute("myLoteList",myLoteList);				
		} catch (Exception e) {
			e.printStackTrace();	
			loteOption = wrongUserUrl;
			return new ModelAndView(wrongUserUrl);
		}
		loteOption = "lote/telaLanceParticipacao";
		return new ModelAndView("lote/telaLanceParticipacao");
		
	}
	
	public ModelAndView showContaUsuarioPessoaJuridicaCadastreFormView(HttpServletRequest request,
            HttpServletResponse response){
		System.out.println("showContaUsuarioPessoaJuridicaCadastreFormView");
		showPessoaJuridicaCadastreFormView(request, response);
		request.setAttribute("pagina","pessoajuridica/telaCadastroPessoaJuridica.jsp");
		return new ModelAndView("telaContaUsuario");
	}
	
	public ModelAndView showContaUsuarioPessoaFisicaCadastreFormView(HttpServletRequest request,
            HttpServletResponse response){
		System.out.println("showContaUsuarioPessoaFisicaCadastreFormView");
		showPessoaFisicaCadastreFormView(request, response);
		request.setAttribute("pagina","pessoafisica/telaCadastroPessoaFisica.jsp");
		return new ModelAndView("telaContaUsuario");
	}
	
	public ModelAndView showContaUsuarioLotesParticipacaoView(HttpServletRequest request,
            HttpServletResponse response){
		System.out.println("showContaUsuarioLotesParticipacaoView");
		showLotesParticipacaoView(request, response);
		request.setAttribute("pagina",loteOption+".jsp");
		return new ModelAndView("telaContaUsuario");
	}
	
	public ModelAndView showContaUsuarioTodosLotesEmLeilaoView(HttpServletRequest request,
            HttpServletResponse response){
		System.out.println("showContaUsuarioTodosLotesEmLeilaoView");
		showTodosLotesEmLeilaoView(request, response);
		request.setAttribute("pagina",loteOption+".jsp");
		return new ModelAndView("telaContaUsuario");
	}
	
	public ModelAndView showLotesParticipacaoRefreshView(HttpServletRequest request,
            HttpServletResponse response){		
		showLotesParticipacaoView(request, response);
		System.out.println("Lotes participação");
		return new ModelAndView("lote/lanceTempoRealRefresh");
	}
	
	public ModelAndView showTodosLotesEmLeilaoRefreshView(HttpServletRequest request,
            HttpServletResponse response){
		try {
			showTodosLotesEmLeilaoView(request, response);
			System.out.println("Todos os Lotes");
			return new ModelAndView("lote/lanceTempoRealRefresh");
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return new ModelAndView("lote/lanceTempoRealRefresh");
	}
	
	public ModelAndView showContaUsuarioTelaInicialView(HttpServletRequest request,
            HttpServletResponse response){
		System.out.println("showContaUsuarioTelaInicialView");
		request.setAttribute("pagina","contausuario/personWelcome.jsp");
		return new ModelAndView("telaContaUsuario");
	}
	
	public ModelAndView showAutenticarUsuarioView(HttpServletRequest request,
            HttpServletResponse response){
		System.out.println("showAutenticarUsuarioView");
		String login = request.getParameter("id");
		String ticket = request.getParameter("ticket");
		boolean aut = Boolean.parseBoolean(request.getParameter("aut"));
		Usuario usuario = new Usuario();
		usuario.setLogin(login);
		usuario.setTicket(ticket);
		try {
			if(aut)
				userService.autenticateUsuario(usuario, aut);
			else{
				DeactivationReason d = new DeactivationReason();
				
				d.setDate(new Date());
				d.setReason("cadastro cancelado pelo próprio usuário");
				d.setUsuario(usuario);
				userService.deactiveUsuario(usuario, d);
				/*userService.remove(new Usuario[]{usuario});
				try {
					request.getSession(true).removeAttribute("user");
				} catch (Exception e) {
					e.printStackTrace();
				}*/
			}
			request.setAttribute("msg", aut?
					"<p>Você foi autenticado no sistema e pode ter acesso ao mesmo.</p>"
					:"<p>O seu acesso ao sistema foi suspenso, bem como o recebimento de emails.</p>");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "Problemas ao executar a operação");
		}
		return new ModelAndView("participante/autentication");
	}
	
	public ModelAndView showRecommendationView(HttpServletRequest request,
            HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".showRecommendationView");
		//request.setAttribute("pagina","participante/personWelcome.jsp");
		String part_id = request.getParameter("part_id");
		String contacts = request.getParameter("contact_email");
		String msg = request.getParameter("contact_message");
		System.out.println(part_id + "  " + contacts);
		if(contacts != null){
			String emails[] = null;
			Participante p = null;
			if(contacts.indexOf(";")>0){
				emails = contacts.split(";");
			}else if(contacts.indexOf(",")>0){
				emails = contacts.split(",");
			}
			else emails = new String[]{contacts};
			if(part_id != null && !part_id.equals("") && !part_id.equals("null")){
				try {
					p=pessoaService.getParticipante(Long.parseLong(part_id));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(emails != null ){
				try {
					adapitAutenticateUserMailService.sendInvitationMsg(p, emails, msg);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			request.setAttribute("pageStr", "recommendationsucess");
		}
		else{
			if(part_id != null && !part_id.equals(""))
				request.setAttribute("part_id", part_id);
			request.setAttribute("pageStr", "recommendation");
		}
		return new ModelAndView("welcome");
	}
	

	private com.adapit.portal.services.mail.AdapitAutenticateUserMailServiceImpl adapitAutenticateUserMailService;

	public com.adapit.portal.services.mail.AdapitAutenticateUserMailServiceImpl getAdapitAutenticateUserMailService() {
		return adapitAutenticateUserMailService;
	}
	

	public void setAdapitAutenticateUserMailService(
			com.adapit.portal.services.mail.AdapitAutenticateUserMailServiceImpl adapitAutenticateUserMailService) {
		this.adapitAutenticateUserMailService = adapitAutenticateUserMailService;
	}
}