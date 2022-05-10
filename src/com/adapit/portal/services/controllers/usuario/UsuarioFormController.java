package com.adapit.portal.services.controllers.usuario;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.brazilutils.br.cpfcnpj.CpfCnpj;
import org.hibernate.Session;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.ServletRequestDataBinder;

import com.adapit.portal.entidades.AddressType;
import com.adapit.portal.entidades.Endereco;
import com.adapit.portal.entidades.EstadoCivil;
import com.adapit.portal.entidades.Fisica;
import com.adapit.portal.entidades.Pais;
import com.adapit.portal.entidades.Participante;
import com.adapit.portal.entidades.PreferenciaCategoria;
import com.adapit.portal.entidades.Sexo;
import com.adapit.portal.entidades.UserCadastreType;
import com.adapit.portal.entidades.Usuario;
import com.adapit.portal.services.controllers.EstadoCivilPropertyEditor;
import com.adapit.portal.services.controllers.PaisPropertyEditor;
import com.adapit.portal.services.local.LocalServicesUtility;
import com.adapit.portal.services.validation.UserServiceValidator;
import com.workcase.gui.utils.WebResourceMessage;
import com.workcase.utils.BaseFormController;
import com.workcase.utils.DatePropertyEditor;
import com.workcase.utils.IdGenerator;

/**
 * @spring.bean id="usuarioForm" singleton="true"
 */
public class UsuarioFormController extends BaseFormController {

	private UserServiceValidator userService;

	private Usuario usuario;

	private com.adapit.portal.services.mail.AdapitAutenticateUserMailServiceImpl adapitAutenticateUserMailService;

	public UsuarioFormController() {
		setCommandClass(Usuario.class);
		setCommandName("usuario");
		setFormView("telaCadastroPessoaFisica");
		setSuccessView("userCadastreSucess");
		setBindOnNewForm(true);
		messages = WebResourceMessage.getInstance();
	}

	protected void doSubmitAction(Object command) throws Exception {
		usuario = (Usuario) command;
		System.out.println("Salvando " + usuario.getLogin());
		try {
			usuario.setUserCadastreType(UserCadastreType.Cliente);
			if (usuario.isNewUser())
				usuario.setTicket(IdGenerator.getInstance().generateId(25));
			usuario.setActive(true);

			usuario.setDadosPessoais(pessoa);
			pessoa.setUser(usuario);

			pessoa.setEndereco(endereco);

			pessoa.setTipoPessoa(tipoPessoa);
			tipoPessoa.setPessoa(pessoa);

			usuario.setPreferencia(preferencia);
			preferencia.setUsuario(usuario);

			boolean novo = false;

			Session s = LocalServicesUtility.getInstance().openSession();
			try {
				s.beginTransaction();
				
				usuario.setPassword(Usuario.encript(usuario.getPassword()));
				usuario.setPasswordConf(Usuario.encript(usuario.getPassword()));
				if (!usuario.isNewUser()) {
					novo = true;
					//Não pode atulizar dados do usuário!!
					//s.update(usuario);
					s.update(endereco);
					s.update(pessoa);
					s.update(tipoPessoa);
					s.update(preferencia);
				} else {
					endereco.setId(0);
					s.persist(endereco);										
					s.persist(pessoa);										
					s.persist(tipoPessoa);					
					s.persist(usuario);										
					s.persist(preferencia);											
				}

				s.getTransaction().commit();

				if (novo)
					adapitAutenticateUserMailService.sendAutenticateUserMsg(usuario);
			} catch (Exception ex) {
				ex.printStackTrace();
				s.getTransaction().rollback();
			} finally {
				if (s != null)
					s.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder arg1) throws Exception {
		super.initBinder(request, arg1);
		arg1.registerCustomEditor(Pais.class, new PaisPropertyEditor());
		arg1.registerCustomEditor(EstadoCivil.class,
				new EstadoCivilPropertyEditor());

	}

	protected void onBindAndValidate(HttpServletRequest request,
			Object command, BindException errors) throws Exception {
		super.onBindAndValidate(request, command, errors);
		validate(request, command, errors);
		/*
		 * usuario = (Usuario)command;
		 * 
		 * 
		 * String userid= request.getParameter("id_usuario"); if (userid !=
		 * null){ try { usuario.setId(Integer.parseInt(userid)); } catch
		 * (Exception e) { usuario.setId(0); } }else usuario.setId(0);
		 * 
		 * if (errors == null || errors.getErrorCount() < 1){ if
		 * (!isValidUsuario(request,usuario,errors)){ //throw errors; return; }
		 * else if (!isValidPessoa(request)){ //throw errors; ObjectError oe1 =
		 * new ObjectError("form", new String[]{"error"},
		 * null,"errors.default"); errors.addError(oe1); } else if
		 * (!isValidPessoaFisica(request)){ //throw errors; ObjectError oe1 =
		 * new ObjectError("form", new String[]{"error"},
		 * null,"errors.default"); errors.addError(oe1); } else if
		 * (!isValidEndereco(request)){ //throw errors; ObjectError oe1 = new
		 * ObjectError("form", new String[]{"error"}, null,"errors.default");
		 * errors.addError(oe1); } else if (!isValidEndereco(request)){ //throw
		 * errors; ObjectError oe1 = new ObjectError("form", new
		 * String[]{"error"}, null,"errors.default"); errors.addError(oe1); }
		 * }else{ System.out.println("Existem erros de formulário detectados na
		 * classe base"); }
		 */

	}

	protected void validate(HttpServletRequest request, Object command,
			BindException errors) throws Exception {
		usuario = (Usuario) command;
		referenceData(request, usuario, errors);
		// String userid= request.getParameter("id_usuario");
		String temLogin = request.getParameter("id_usuario");
		usuario.setNewUser(temLogin == null || temLogin.equals(""));
		/*
		 * if (userid != null){ try {
		 * usuario.setNewUser(Boolean.parseBoolean(userid)); } catch (Exception
		 * e) { usuario.setNewUser(true); } }else usuario.setNewUser(true);
		 */

		if (errors == null || errors.getErrorCount() < 1) {
			if (!isValidUsuario(request, usuario, errors)) {
				throw errors;
				// return;
			} else if (!isValidPessoa(request)) {

				ObjectError oe1 = new ObjectError("form",
						new String[] { "error" }, null, "errors.default");
				errors.addError(oe1);
				throw errors;
			} else if (!isValidPessoaFisica(request)) {
				// throw errors;
				ObjectError oe1 = new ObjectError("form",
						new String[] { "error" }, null, "errors.default");
				errors.addError(oe1);
				throw errors;
			} else if (!isValidEndereco(request)) {
				// throw errors;
				ObjectError oe1 = new ObjectError("form",
						new String[] { "error" }, null, "errors.default");
				errors.addError(oe1);
				throw errors;
			} else if (!isValidEndereco(request)) {
				// throw errors;
				ObjectError oe1 = new ObjectError("form",
						new String[] { "error" }, null, "errors.default");
				errors.addError(oe1);
				throw errors;
			}
		} else {
			System.out
					.println("Existem erros de formulário detectados na classe base");
		}

	}

	protected boolean isValidUsuario(HttpServletRequest request,
			Usuario usuario, BindException errors) throws Exception {
		if (usuario.getPassword().equals(usuario.getPasswordConf())) {
			System.out.println("Validando o usuário");
			if (!(usuario.getPassword().length() < 6 || usuario.getPassword()
					.length() > 8)) {
				if (usuario.isNewUser()) {
					boolean existe = usuarioExiste(usuario);
					if (existe) {
						System.out.println("Usuario ja existe!");
						ObjectError oe = new ObjectError("usuario",
								new String[] { "login" }, null,
								"errors.loginjustexist");
						errors.addError(oe);
						return false;
					} else {
						System.out
								.println("Usuario não existe e pode ser cadastrado");
					}
				} else
					return true;
			} else {
				System.out.println("Senha ou login incorretos!");
				ObjectError oe1 = new ObjectError("usuario", new String[] {
						"password", "passwordConf" },
						new Object[] { new Integer(6) }, "errors.minlength");
				ObjectError oe2 = new ObjectError("usuario", new String[] {
						"password", "passwordConf" },
						new Object[] { new Integer(8) }, "errors.maxlength");
				errors.addError(oe1);
				errors.addError(oe2);
				return false;
			}
		} else {
			System.out.println("Senhas não conferem");
			if (!(usuario.getPassword().length() < 6 || usuario.getPassword()
					.length() > 8)) {
				ObjectError oe1 = new ObjectError("usuario",
						new String[] { "password" },
						new Object[] { "passwordConf" }, "errors.differentpass");
				errors.addError(oe1);
				return false;

			} else {
				ObjectError oe1 = new ObjectError("usuario", new String[] {
						"password", "passwordConf" },
						new Object[] { new Integer(6) }, "errors.minlength");
				ObjectError oe2 = new ObjectError("usuario", new String[] {
						"password", "passwordConf" },
						new Object[] { new Integer(8) }, "errors.maxlength");
				errors.addError(oe1);
				errors.addError(oe2);
				return false;
			}

		}

		return true;
	}

	public boolean usuarioExiste(Usuario usuario) throws Exception {
		try {
			boolean usuarioExiste = userService.isValid(usuario.getLogin());
			return usuarioExiste;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Map referenceData(HttpServletRequest request, Object arg1,
			Errors detectedErrors) throws Exception {
		usuario = (Usuario) getCommand(request);

		// super.bindAndValidate(request, usuario);
		// System.out.println("Usuario " + usuario.getLogin());

		Map m = // super.referenceData(request,arg1,detectedErrors);
		new HashMap();

		Hashtable ht = new Hashtable();
		ArrayList<String> arr = new ArrayList();

		try {
			bindPessoa(request, m, arr, ht);
			bindEndereco(request, m, arr, ht);
			bindTipoPessoa(request, m, arr, ht);
			bindPreferencia(request, m, arr, ht);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		if (ht.size() > 0)
			populateErrorList(request, arg1, detectedErrors, ht, arr);

		m.put("usuario", usuario);

		m.put("errorFields", ht);
		m.put("errorMessages", arr);
		return m;
	}

	boolean dataNascInvalida = false;

	@SuppressWarnings("unchecked")
	protected void bindTipoPessoa(HttpServletRequest request, java.util.Map m,
			ArrayList arr, Hashtable ht) throws Exception {

		try {
			tipoPessoa.setId(Integer
					.parseInt(request.getParameter("fisica_id")));
		} catch (Exception ex) {

		}

		try {
			DatePropertyEditor dt = new DatePropertyEditor();
			dt.setFormat(messages.getMessage("formats.date"));
			dt.setAsText(request.getParameter("dataNascimento"));
			tipoPessoa.setDataNascimento((Date) dt.getValue());
			dataNascInvalida = false;
		} catch (RuntimeException e1) {
			e1.printStackTrace();
			dataNascInvalida = true;
		}

		try {
			String value = request.getParameter("estadoCivil");
			if (value != null && value.equals("empty")) {
				ht.put("estadoCivil", "errors.required");
				arr.add(messages.getMessage("participante.estadoCivil",
						new Object[][] { { "errors.required" } }));
			} else {
				String estadocivil = request.getParameter("estadoCivil");
				if (estadocivil != null) {
					estadocivil = estadocivil.substring(0,
							estadocivil.length() - 3);
					tipoPessoa.setEstadoCivil(EstadoCivil.valueOf(estadocivil
							.replaceAll(" ", "_")));
				}
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		tipoPessoa.setProfissao(request.getParameter("profissao"));

		tipoPessoa.setCelular(request.getParameter("celular"));
		tipoPessoa.setCpf(request.getParameter("cpf"));
		// validar cpf
		tipoPessoa.setRg(request.getParameter("rg"));
		// validar rg

		String sobrenome = request.getParameter("sobrenome");
		if (sobrenome != null)
			tipoPessoa.setSobrenome(sobrenome);
		String sexo = request.getParameter("sexo");
		if (sexo != null)
			tipoPessoa.setSexo(Sexo.valueOf(sexo));

		org.springframework.validation.BindException errors = new org.springframework.validation.BindException(
				tipoPessoa, "fisica");
		getValidator().validate(tipoPessoa, errors);
		errorsValidate(errors, arr, ht);

		pessoa.setTipoPessoa(tipoPessoa);

		m.put("fisica", tipoPessoa);

	}

	Fisica tipoPessoa = new Fisica();

	protected boolean isValidPessoaFisica(HttpServletRequest request)
			throws Exception {

		tipoPessoa.setCelular(request.getParameter("celular"));

		try {
			DatePropertyEditor dt = new DatePropertyEditor();

			dt.setFormat(messages.getMessage("formats.date"));
			dt.setAsText(request.getParameter("dataNascimento"));
			tipoPessoa.setDataNascimento((Date) dt.getValue());
		} catch (RuntimeException e1) {
			e1.printStackTrace();
			return false;
		}

		try {
			String estadocivil = request.getParameter("estadoCivil");
			if (estadocivil.equals("empty")) {
				return false;
			} else {
				estadocivil = estadocivil
						.substring(0, estadocivil.length() - 3);
				tipoPessoa.setEstadoCivil(EstadoCivil.valueOf(estadocivil
						.replaceAll(" ", "_")));
				// tipoPessoa.setEstadoCivil(EstadoCivil.valueOf(value.replaceAll("(a)","").replaceAll("
				// ","_")));
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			return false;
		}
		tipoPessoa.setProfissao(request.getParameter("profissao"));
		// validar rg
		tipoPessoa.setRg(request.getParameter("rg"));
		// validar cpf
		tipoPessoa.setCpf(request.getParameter("cpf"));

		tipoPessoa.setSobrenome(request.getParameter("sobrenome"));
		
		
		org.springframework.validation.BindException errors = new org.springframework.validation.BindException(
				tipoPessoa, "fisica");
		getValidator().validate(tipoPessoa, errors);

		if (!CpfCnpj.isValid(tipoPessoa.getCpf())) {
			ObjectError oe1 = new ObjectError("participante",
					new String[] { "cpf" }, null, "errors.invalidcpf");
			errors.addError(oe1);
		}

		if (errors.hasErrors())
			return false;
		else
			return true;

	}

	private Endereco endereco = new Endereco();

	@SuppressWarnings("unchecked")
	protected void bindEndereco(HttpServletRequest request, java.util.Map m,
			ArrayList arr, Hashtable ht) throws Exception {

		endereco = new Endereco();
		try {
			endereco.setId(Integer
					.parseInt(request.getParameter("endereco_id")));
		} catch (Exception ex) {

		}
		endereco.setBairro(request.getParameter("bairro"));
		endereco.setCidade(request.getParameter("cidade"));
		String pais = request.getParameter("pais");
		if (pais != null)
			try {
				endereco.setPais(Pais.valueOf(pais.replaceAll(" ", "_")));
			} catch (RuntimeException e) {
				e.printStackTrace();
			}

		String estado = request.getParameter("estadocombo");
		if (estado == null || estado.equals(".."))
			endereco.setEstado(request.getParameter("estado"));
		else {
			endereco.setEstado(estado);
		}

		try {
			endereco
					.setNumero(Integer.parseInt(request.getParameter("numero")));
		} catch (Exception ex) {
			ex.printStackTrace();
			arr.add(messages.getMessage("errors.integer",
					new Object[][] { { "endereco.numero" } }));
		}
		endereco.setCaixaPostal(request.getParameter("caixaPostal"));
		// validar cep
		endereco.setCep(request.getParameter("cep"));
		endereco.setRua(request.getParameter("rua"));
		try {
			endereco.setComplemento(request.getParameter("complemento"));
		} catch (Exception ex) {
			arr.add(messages.getMessage("errors.integer",
					new Object[][] { { "endereco.complemento" } }));
		}
		endereco.setTipo(AddressType.Residencial);

		org.springframework.validation.BindException errors = new org.springframework.validation.BindException(
				endereco, "endereco");
		getValidator().validate(endereco, errors);
		errorsValidate(errors, arr, ht);

		pessoa.setEndereco(endereco);

		m.put("endereco", endereco);

	}

	protected boolean isValidEndereco(HttpServletRequest request)
			throws Exception {

		endereco.setBairro(request.getParameter("bairro"));
		endereco.setCidade(request.getParameter("cidade"));
		try {
			endereco.setPais(Pais.valueOf(request.getParameter("pais")
					.replaceAll(" ", "_")));
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		endereco.setEstado(request.getParameter("estado"));
		try {
			endereco
					.setNumero(Integer.parseInt(request.getParameter("numero")));
		} catch (Exception ex) {
			return false;
		}
		endereco.setCaixaPostal(request.getParameter("caixaPostal"));
		// validar cep
		endereco.setCep(request.getParameter("cep"));
		endereco.setRua(request.getParameter("rua"));
		try {
			endereco.setComplemento(request.getParameter("complemento"));
		} catch (Exception ex) {
			return false;
		}

		org.springframework.validation.BindException errors = new org.springframework.validation.BindException(
				endereco, "endereco");
		getValidator().validate(endereco, errors);

		if (errors.hasErrors())
			return false;
		else
			return true;
	}

	private Participante pessoa = new Participante();

	@SuppressWarnings("unchecked")
	protected void bindPessoa(HttpServletRequest request, java.util.Map m,
			ArrayList arr, Hashtable ht) throws Exception {
		String id = request.getParameter("id_participante");
		if (id != null)
			pessoa.setId(Integer.parseInt(id));
		pessoa.setNome(request.getParameter("nome"));
		pessoa.setEmail(request.getParameter("email"));
		pessoa.setTelefone(request.getParameter("telefone"));
		pessoa.setFax(request.getParameter("fax"));
		org.springframework.validation.BindException errors = new org.springframework.validation.BindException(
				pessoa, "participante");
		getValidator().validate(pessoa, errors);
		errorsValidate(errors, arr, ht);

		usuario.setDadosPessoais(pessoa);
		m.put("participante", pessoa);
	}

	protected boolean isValidPessoa(HttpServletRequest request)
			throws Exception {
		pessoa.setNome(request.getParameter("nome"));
		pessoa.setEmail(request.getParameter("email"));
		pessoa.setTelefone(request.getParameter("telefone"));
		pessoa.setFax(request.getParameter("fax"));
		org.springframework.validation.BindException errors = new org.springframework.validation.BindException(
				pessoa, "participante");
		getValidator().validate(pessoa, errors);

		if (errors.hasErrors())
			return false;
		else
			return true;
	}

	PreferenciaCategoria preferencia = new PreferenciaCategoria();

	@SuppressWarnings("unchecked")
	protected void bindPreferencia(HttpServletRequest request, java.util.Map m,
			ArrayList arr, Hashtable ht) throws Exception {
		String id = request.getParameter("id_preferencia");
		if (id != null)
			preferencia.setId(Integer.parseInt(id));
		preferencia.setReceberEmailSobreEventos(request.getParameter("diversos") != null);
		preferencia.setReceberEmailNovosProdutos(request.getParameter("imoveis") != null);
		preferencia.setReceberEmailAtualizacoesSoftware(request.getParameter("informatica") != null);
		preferencia.setInteresseEmTreinamentos(request.getParameter("maquinarios") != null);
		preferencia.setPreferencias(request.getParameter("preferencias"));
		preferencia.setReceberNotificacaoNewsByEmail(request
				.getParameter("receberNotificacaoEmail") != null);
		preferencia.setInteresseEmConsultoria(request.getParameter("veiculos") != null);

		usuario.setPreferencia(preferencia);
		m.put("preferencia", preferencia);
	}

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
	 * @spring.property ref="adapitAutenticateUserMailService" singleton="true"
	 */
	public com.adapit.portal.services.mail.AdapitAutenticateUserMailServiceImpl getAdapitAutenticateUserMailService() {
		return adapitAutenticateUserMailService;
	}

	public void setAdapitAutenticateUserMailService(
			com.adapit.portal.services.mail.AdapitAutenticateUserMailServiceImpl leilaoMailService) {
		this.adapitAutenticateUserMailService = leilaoMailService;
	}

}